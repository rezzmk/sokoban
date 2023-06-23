package pt.iscte.madco.poo.sokoban;

import pt.iscte.madco.poo.sokoban.abstractions.GameEngine;
import pt.iscte.madco.poo.sokoban.commands.MoveCommand;
import pt.iscte.madco.poo.sokoban.commands.ShowScoreboardCommand;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.utils.Direction;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sokoban extends GameEngine {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private static final Sokoban instance = new Sokoban();
    private Session session;

    /**
     * Gets the current Sokoban game instance (Singleton pattern)
     */
    public static Sokoban getInstance() {
        return instance;
    }

    // Creates the single instance of the SokobanGame
    // Initialized at class loading
    private Sokoban() {
        // Protect against reflection
        if (instance != null) {
            logger.log(Level.SEVERE, "Sokoban is already initialized but private ctor was called via reflection");
            throw new IllegalStateException(String.format("%s already initialized", this.getClass().getName()));
        }
    }

    public void startSession() {
        session = new Session(this);
        logger.log(Level.INFO, "Starting game session");
        start();
    }

    public Session getGameSession() {
        if (session == null) session = new Session(this);
        return session;
    }

    @Override
    public synchronized void update(Observed source) {
        int lastKeyPressed = getLastKeyPressed(source);
        if (!Direction.isDirection(lastKeyPressed)) {
            if (lastKeyPressed == KeyEvent.VK_C) {
                new ShowScoreboardCommand().execute();
            }

            return;
        }
        new MoveCommand(Direction.directionFor(lastKeyPressed)).execute();
    }
}
