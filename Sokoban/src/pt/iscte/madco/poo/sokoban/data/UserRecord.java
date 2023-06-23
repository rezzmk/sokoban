package pt.iscte.madco.poo.sokoban.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a user record, which has multiple best score saves, one for each level
 */
public class UserRecord implements Serializable {
    static final long serialVersionUID = 0L;

    private final ArrayList<UserSave> saves;
    private final String username;

    public UserRecord(String username, ArrayList<UserSave> saves) {
        this.username = username;

        this.saves = new ArrayList<>();
        if (saves != null) this.saves.addAll(saves);
    }

    public ArrayList<UserSave> getSaves() {
        return saves;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // This might be the ugliest thing I've ever done.
        for (UserSave save : getSaves()) {
            sb.append(String.format("\n\tLevel: %d Score: %d", save.getLevel(), save.getScore()));
        }

        return String.format("USER: %s %s\n", getUsername(), sb.toString());
    }
}
