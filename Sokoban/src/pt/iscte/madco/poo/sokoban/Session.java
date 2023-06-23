package pt.iscte.madco.poo.sokoban;

import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.abstractions.GameEngine;
import pt.iscte.madco.poo.sokoban.commands.CloseAppCommand;
import pt.iscte.madco.poo.sokoban.commands.RestartLevelCommand;
import pt.iscte.madco.poo.sokoban.data.UserRecord;
import pt.iscte.madco.poo.sokoban.exceptions.LevelDoesNotExistException;
import pt.iscte.madco.poo.sokoban.exceptions.NoActorFoundException;
import pt.iscte.madco.poo.sokoban.gui.Animation;
import pt.iscte.madco.poo.sokoban.gui.Dialog;
import pt.iscte.madco.poo.sokoban.helpers.sokoban.LevelBuilder;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iul.ista.poo.gui.ImageTile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a gaming session. This is effectively what takes cares of all backend logic, one level above the
 * graphical interface wrapper, provided by the Graph Pack.
 */
public class Session {
    private GameEngine engine;
    private Actor mainActor;
    private List<AbstractSObject> loadedTiles;
    private final SessionState sessionState;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public String getScoreboard() {
        return sessionState.getScoreboardString();
    }

    /**
     * @return {@link Actor} instance, in other words, the main character
     */
    public Actor getMainActor() {
        return mainActor;
    }

    /**
     * @return {@link List<AbstractSObject>} , list of tiles loaded in game
     */
    public List<AbstractSObject> getLoadedTiles() {
        return loadedTiles;
    }

    /**
     * Restarts the current level, resetting all scores and loading a losing animation
     */
    public void restartLevel() {
        sessionState.saveCurrentScore();
        Dialog.showMessageAndWait(
        		"Oops!, you lost!, press OK to try again, or Cancel to close",
        		() -> new CloseAppCommand().execute(), 
        		null
        );

        engine.updateScreen();
        initLevel(sessionState.getCurrentLevel());
    }
    /**
     * Starts next level
     */
    public void nextLevel() {
        sessionState.saveCurrentScore();
        sessionState.setCurrentLevel(sessionState.getCurrentLevel() + 1);
        Animation.runWinningAnimation(getEngine(), getLoadedTiles());
        engine.updateScreen();
        initLevel(sessionState.getCurrentLevel());
    }

    /**
     * Updates the current state of the game
     *
     * TODO(Marcos): This should probably be somewhere else.
     */
    public void updateScores() {
        sessionState.incrementScore();
        if (sessionState.getEnergy() == 0) new RestartLevelCommand().execute();
        engine.updateSessionStatus(sessionState.getCurrentStatusString());
    }

    /**
     * Reloads energy
     */
    public void reloadEnergy() {
        sessionState.setEnergy(GameConstants.MAX_ENERGY);
        engine.updateSessionStatus(sessionState.getCurrentStatusString());
    }

    /**
     * SokobanSession CTOR
     * @param engine Takes in a GameEngine to take care of the window update logistics
     */
    public Session(GameEngine engine) {
        this.sessionState = new SessionState();
        setEngine(engine);
        initLevel(0);
    }

    private void initLevel(int level) {
        try {
            String username;
            if (sessionState.getCurrentUser() == null) {
                ArrayList<UserRecord> records = sessionState.getPersistedUserRecords();
                if (records != null && records.size() > 0) {
                    List<String> names = new ArrayList<>();
                    records.forEach(x -> names.add(x.getUsername()));
                    username = Dialog.getUserNameInput(names.toArray());
                } else {
                    username = Dialog.getUserNameInput(null);
                }
            } else {
                username = sessionState.getCurrentUser().getUsername();
            }
            sessionState.loadRecords(username);

            loadLevelToGui(level);
        } catch(NoActorFoundException noActorFoundException) {
            logger.log(Level.SEVERE, "No player (Actor) found in game!!");
        }
    }


    // Takes care of everything needed to start a new level
    private void loadLevelToGui(int level) throws NoActorFoundException {
        sessionState.setCurrentLevel(level);

        try {
            loadedTiles = LevelBuilder.build(level);
        }
        catch (LevelDoesNotExistException e) {
            if (!LevelBuilder.isLastLevel(level)) {
                logger.log(Level.SEVERE, "Missing level!");
                return;
            } else {
                Dialog.showMessageAndWait(
                		"You won, press OK or Cancel to close",
                		() -> new CloseAppCommand().execute(),
                		() -> new CloseAppCommand().execute()
                );
                
            }
        }

        Actor actor = (Actor) loadedTiles.stream().filter(a -> a instanceof Actor).findFirst().orElse(null);

        if (actor == null) {
            logger.log(Level.SEVERE, "No player (Actor) found in game!!");
            throw new NoActorFoundException("No player was found in the map");
        }

        this.mainActor = actor;

        engine.clearCanvas();
        engine.addObjectsToCanvas(new ArrayList <ImageTile>(loadedTiles));

        sessionState.resetScore();
        engine.updateSessionStatus(sessionState.getCurrentStatusString());
        engine.updateScreen();
    }

    // Stores a reference to the game engine
    private void setEngine(GameEngine engine) {
        if (engine == null) {
            throw new IllegalArgumentException("engine can't be null");
        }

        this.engine = engine;
    }

    private GameEngine getEngine() {
        return this.engine;
    }
}
