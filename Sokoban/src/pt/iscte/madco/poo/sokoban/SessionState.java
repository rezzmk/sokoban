package pt.iscte.madco.poo.sokoban;

import pt.iscte.madco.poo.sokoban.data.UserRecord;
import pt.iscte.madco.poo.sokoban.data.UserSave;
import pt.iscte.madco.poo.sokoban.helpers.ObjectSerializer;
import pt.iscte.madco.poo.sokoban.data.Tuple;

import java.util.ArrayList;

public class SessionState {
    private ArrayList<UserRecord> userRecords;
    private UserRecord currentUser;
    private int score;
    private int currentLevel;
    private int energy;

    /**
     * <p>
     * Gets the formatted status string, IE: 'Level: 0 Moves: 1 Energy: 99'
     * </p>
     */
    public String getCurrentStatusString() {
        String levelPortion      = String.format("Level: %d", getCurrentLevel() + 1);
        String scorePortion      = String.format("Moves: %d, Energy: %d", getScore(), getEnergy());
        String bestPersonPortion = String.format("Best personal: %d", getBestScoreForCurrentUser());

        // Gets a tuple of k,v pairs with (Username, Best score)
        Tuple<String, Integer> bestScoreOverall = getBestScoreOverall();

        // Checks if the current user holds the best record or not
        String bestOverall = bestScoreOverall.key.equals(getCurrentUser().getUsername())
                ? "You're the best"
                : String.format("Best overall: %d by %s", bestScoreOverall.value, bestScoreOverall.key);

        // Returns formatted string with all of the above portions
        return String.format("%s | %s | %s | %s", levelPortion, scorePortion, bestPersonPortion, bestOverall);
    }

    protected String getScoreboardString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Scoreboard\n\n");
        for (UserRecord record : getUserRecords()) {
            sb.append("Username: ");
            sb.append(record.getUsername());
            sb.append("\n");
            for (UserSave save : record.getSaves()) {
                sb.append("  Level: ");
                sb.append(save.getLevel() + 1);
                sb.append("  Best Score: ");
                sb.append(save.getScore() + 1);
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    protected UserRecord getCurrentUser() {
        return this.currentUser;
    }

    protected void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    protected void setCurrentUser(UserRecord currentUser) {
        this.currentUser = currentUser;
    }

    protected ArrayList<UserRecord> getUserRecords() {
        return userRecords;
    }

    protected void resetScore() {
        setScore(0);
        setEnergy(GameConstants.MAX_ENERGY);
    }

    protected void setScore(int score) {
        this.score = score;
    }

    protected int getScore() {
        return this.score;
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    protected int getEnergy() {
        return this.energy;
    }

    protected void decrementEnergy() {
        this.energy--;
    }

    protected void incrementScore() {
        this.score++;
        decrementEnergy();
    }

    protected void setUserRecords(ArrayList<UserRecord> userRecords) {
        this.userRecords = userRecords;
        userRecords.forEach(System.out::println);
    }

    protected int getCurrentLevel() {
        return currentLevel;
    }

    protected void saveCurrentScore() {
        UserSave bestSave = getBestSaveForCurrentUser();
        if (bestSave != null) {
            if (bestSave.getScore() > getScore()) {
                bestSave.setScore(getScore());
                persistUserRecords();
            }
            return;
        }

        if (currentUser != null) currentUser.getSaves().add(new UserSave(getCurrentLevel(), getScore()));

        persistUserRecords();
    }

    protected void loadRecords(String username) {
        // Get the username for the current game session

        // Get a list of user records persisted in file
        ArrayList<UserRecord> records = getPersistedUserRecords();

        // If records == null, means the file has not been created yet, IE: first use of the game
        if (records == null) {
            records = new ArrayList<UserRecord>() {{
                add(new UserRecord(username, null));
            }};

            setCurrentUser(records.get(0));
        } else {
            UserRecord user = records.stream().filter(x -> x.getUsername().equals(username)).findFirst().orElse(null);
            if (user == null) {
                UserRecord newUser = new UserRecord(username, null);
                records.add(newUser);
                setCurrentUser(newUser);
            } else {
                setCurrentUser(user);
            }
        }

        setUserRecords(records);
    }

    private int getBestScoreForCurrentUser() {
        UserSave save = getBestSaveForCurrentUser();
        return save != null ? save.getScore() : 0;
    }

    private UserSave getBestSaveForCurrentUser() {
        return currentUser.getSaves().stream().filter(x -> x.getLevel() == getCurrentLevel()).findFirst().orElse(null);
    }

    private Tuple<String, Integer> getBestScoreOverall() {
        String user = getCurrentUser().getUsername();

        int best;
        if (getCurrentUser().getSaves().size() > 0) {
            best = getCurrentUser().getSaves().get(0).getScore();
        }
        else {
            best = 0;
        }
        for (UserSave save : getCurrentUser().getSaves()) {
            if (save.getScore() < best) best = save.getScore();
        }

        for(UserRecord record : getUserRecords()) {
            for (UserSave save : record.getSaves()) {
                if (save.getLevel() == getCurrentLevel() && save.getScore() < best) {
                    best = save.getScore();
                    user = record.getUsername();
                }
            }
        }

        return new Tuple<>(user, best);
    }

    private void persistUserRecords() {
        ObjectSerializer<ArrayList<UserRecord>> serializer = new ObjectSerializer<>();
        ArrayList<UserRecord> records = new ArrayList<>(getUserRecords());
        serializer.serializeObject(records, GameConstants.SCORES_FILE_NAME);
    }

    protected ArrayList<UserRecord> getPersistedUserRecords() {
        ObjectSerializer<ArrayList<UserRecord>> serializer = new ObjectSerializer<>();
        return serializer.deserializeObject(GameConstants.SCORES_FILE_NAME);
    }
}
