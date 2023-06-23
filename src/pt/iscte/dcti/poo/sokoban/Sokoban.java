package pt.iscte.dcti.poo.sokoban;

import java.util.logging.Level;
import java.util.logging.Logger;

import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.utils.Direction;

public class Sokoban extends GameEngine {
	private SokobanSession sessionInstance;
	
	static Logger logger = Logger.getLogger(Sokoban.class.getName());
	private static final Sokoban instance = new Sokoban();

    // Create the single instance of the SokobanGame.
	// Initialized at class loading
	private Sokoban() {
        // protect against reflection
        if(instance != null) {
        	logger.log(Level.SEVERE, "Sokoban is already initialized but private ctor was called via reflection");
            throw new IllegalStateException("Sokoban already initialized");
        }
        
        String loggingInfo = String.format("Sokoban :: Setting canvas size to (%d,%d)", SokobanConstants.GAME_WIDTH, SokobanConstants.GAME_HEIGHT);
        logger.log(Level.INFO, loggingInfo);
        
		setCanvasSize(SokobanConstants.GAME_WIDTH, SokobanConstants.GAME_HEIGHT);	
	}
	
	/**
	 * Starts a gaming session, setting up the whole environment (score, level, screen update)	 
	 */
	public void startSession() {
		sessionInstance = new SokobanSession(this);
		start();
	}
	
	/**
	 * Gets the current Sokoban game instance (Singleton pattern)
	 */
    public static Sokoban getInstance() {
    	return instance;    	
	}
	
    /**
	 * Gets a sokoban session, which is what takes care of the game logic. 
	 * Instantiates a new session in case it's set to null still
	 */
	public SokobanSession getSessionInstance() {
		if (sessionInstance == null) sessionInstance = new SokobanSession(this);
		return sessionInstance;
	}
	
	@Override
	public void update(Observed arg) {
		int lastKeyPressed = getLastKeyPressed(arg);
		
		if (!Direction.isDirection(lastKeyPressed)) return;
		
		getSessionInstance().movePlayer(Direction.directionFor(lastKeyPressed));
	}
}
