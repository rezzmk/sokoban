package pt.iscte.dcti.poo.sokoban;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: CHANGE NAMESPACE, WHAT's dcti???
import pt.iscte.dcti.poo.sokoban.interfaces.ActiveObject;
import pt.iscte.dcti.poo.sokoban.interfaces.Controllable;
import pt.iscte.dcti.poo.sokoban.interfaces.Immobile;
import pt.iscte.dcti.poo.sokoban.models.Target;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

/**
* Represents a sokoban session, which is defined here as a level being played
*
* @author Marcos Caramalho 90292 LEI-PL
*/
public class SokobanSession {
	private static final Logger logger = Logger.getLogger(SokobanSession.class.getName());

	private int score;
	private int energy;
	private int level;
	private int bestScore;
	private GameEngine engine;
	private Map<Point2D, Boolean> targetPositions = new HashMap<>();
	private List<ImageTile> loadedTiles;
	private Controllable player;
	
	/**
	 * <p>
	 * Updates the status bar of the window
	 * </p>
	 */
	public void updateSession() {
		engine.updateSessionStatus(getCurrentStatusString());
	}
	
    /**
	 * SokobanSession CTOR
	 * @param engine Takes in a GameEngine to take care of the window update logistics
	 */
	public SokobanSession(GameEngine engine) {
		logger.log(Level.INFO, "SokobanGame :: CTOR Called SokobanGame(level: {0})", level);
		
		setEngine(engine);
		
		setCurrentLevel(0);
		
		reset();
		
    	setupLevel(level);
	}
	
	/**
	 * Current best score saved in file
	 */
	public int getCurrentBestScore() {
		return bestScore;
	}
	
	/**
	 * Current level being played
	 */
	public int getCurrentLevel() {
		return this.level;
	}
	
	/**
	 * Current level setter
	 */
	public void setCurrentLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Moves player in determined direction. Player is set uppon loading a level
	 */
	public void movePlayer(Direction direction) {
		player.move(direction, loadedTiles);
	}
	
	/**
	 * Loads next level (currentLevel + 1). 
	 * Also checks if the level exists
	 */
	public void nextLevel() {
		// TODO: CHECK WHAT TO DO IF LEVEL DOESNT EXIST, PERHAPS CLOSE THE GAME??
		logger.log(Level.INFO, "SokobanGame :: nextLevel() called, incrementing");
		if (!LevelBuilder.levelExists(getCurrentLevel() + 1)) {
			logger.log(Level.WARNING, "SokobanGame :: {0} level does not exist", getCurrentLevel() + 1);
			return;
		}
		
		saveScores();
		
		// TODO: Animations are buggy currently , runWinningAnimation() 
		
		setupLevel(getCurrentLevel() + 1);
	}
	
	/**
	 * Restarts the current level, resetting all scores and loading a losing animation
	 */
	public void restartLevel() {		
		// TODO: Animations are buggy currently , runLosingAnimation()
		
		setupLevel(getCurrentLevel());
	}
	
	/**
	 * Getter for player
	 */
	public Controllable getPlayer() {
		return player;
	}
	
	/**
	 * <p>
	 * Gets the target positions for the current level, with values representing
	 * wether a box in on top or not
	 * </p>
	 * @return Dictionary with key being position and value boxIsOnTop (bool)
	 */
	public Map<Point2D, Boolean> getTargetPositions() {
		return targetPositions;
	}
	
	/**
	 * <p>
	 * Gets the number of targets in the current level. This value doesn't change throghout the gameplay
	 * of a single level
	 * </p>
	 * @return number of targets
	 */
	public int getNumberOfTargets() {
		return this.targetPositions.size();
	}
	
	/**
	 * <p>
	 * Updates a target position
	 * </p>
	 * @param position Position of target
	 * @param boxOnTop Boolean representing wether a box/cratter is on target or not
	 */
	public void updateTargetPositions(Point2D position, boolean boxOnTop) {
		if (targetPositions.containsKey(position)) {
			targetPositions.put(position, boxOnTop);
		}
	}
	
	/**
	 * <p>
	 * Adds a target entry
	 * </p>
	 * @param position Position of target
	 * @param boxOnTop Boolean representing wether a box/cratter is on target or not
	 */
	public void addTargetPosition(Point2D position, boolean boxOnTop) {
		targetPositions.put(position, boxOnTop);
	}
	
	/**
	 * <p>
	 * Checks if all targets have boxes on top
	 * </p>
	 */
	public boolean areAllTargetsFilled() {
		return Collections.frequency(targetPositions.values(), true) == targetPositions.size();
	}
	
	/**
	 * <p>
	 * Checks if a given position contains a target
	 * </p>
	 * @param pos Position of target
	 */
	public boolean hasTargetInPosition(Point2D pos) {
		return targetPositions.containsKey(pos);
	}

	/**
	 * <p>
	 * Gets the current score of the session
	 * </p>
	 */
	public int getCurrentScore() { 
		return score; 
	}
	
	/**
	 * <p>
	 * Gets the formatted status string, IE: 'Level: 0 Moves: 1 Energy: 99'
	 * </p>
	 */
	public String getCurrentStatusString() { 
		return String.format("Level: %d Moves: %d Energy: %d | Best Score: %d", level + 1, score, energy, getCurrentBestScore());
	}

	/**
	 * <p>
	 * Replenish Energy to 100
	 * </p>
	 */
	public void reloadEnergy() {
		energy = 100;
	}
	
	/**
	 * <p>
	 * Adds a box/cratter to a target. Also checks if game can be won or not.
	 * Game is won if all targets are "filled"
	 * </p>
	 */
	public boolean addBoxInTarget(Point2D position) {
		updateTargetPositions(position, true);

		if (areAllTargetsFilled()) {			
			nextLevel();
			return false;
		}
		
		return true;
	}
	
	/**
	 * <p>
	 * Removes/Moves box/cratter from a target
	 * </p>
	 */
	public void removeBoxFromTarget(Point2D position) {
		updateTargetPositions(position, false);
	}
	
	/**
	 * <p>
	 * Adds 1 point to Score, Removes 1 from energy. (score++, energy--). 
	 * If energy gets to 0, level restarts/ends
	 * </p>
	 */
	public void incrementScore() {
		score++;
		energy--;
		
		if (energy <= 0) {
			restartLevel();
			reset();
		}
	}
	
	/**
	 * <p>
	 * Resets the level stats
	 * </p>
	 */
	public void reset() {
		score = 0;
		energy = 100;
	}
	
	/**
	 * <p>
	 * Saves the current score in file
	 * </p>
	 */
	public void saveScores() {
		Path filePath = new File(SokobanConstants.SOKOBAM_SCORES_FILENAME).toPath();
		String currentSavedScore = FileHelper.getLineFromFile(filePath, String.format("l%d", getCurrentLevel()));
		
		if (currentSavedScore.isEmpty()) {
			FileHelper.writeToFile(filePath, getScoreLineForFile());
		} else {
			String scoreInString = FileHelper.getFileSplit(filePath, String.format("l%d", getCurrentLevel()), ":");
			int parsedInt = tryParseInt(scoreInString) ? Integer.parseInt(scoreInString) : 0;
			if (getCurrentScore() < parsedInt)
				FileHelper.replaceLineInFile(filePath, currentSavedScore, getScoreLineForFile());
		}
	}
	
	/**
	 * <p>
	 * Gets the score for current level from the scores file
	 * </p>
	 */
	public String getScore(int level) {		
		return FileHelper.getFileSplit(new File(SokobanConstants.SOKOBAM_SCORES_FILENAME).toPath(), String.format("l%d", level), ":");
	}
	
	private void 
	setEngine(GameEngine engine) {
		if (engine == null) {
			throw new IllegalArgumentException("engine can't be null");
		}
		
		this.engine = engine;
	}
	
	private String getScoreLineForFile() {
		return String.format("l%d:%d", getCurrentLevel(), getCurrentScore());
	}
		
	private void runLosingAnimation() {
		Iterator<ImageTile> i = loadedTiles.iterator();

		while (i.hasNext()) {
			ImageTile tile = i.next();
			
			if (!(tile instanceof Controllable)) {				
				ImageMatrixGUI.getInstance().removeImage(tile);
				i.remove();
				engine.updateScreen();		
				
				try {
					Thread.sleep(SokobanConstants.SOKOBAN_LOSE_ANIMATION_TIMEOUT);
				} catch(InterruptedException e) {
				    Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	private void runWinningAnimation() {		
		Iterator<ImageTile> i = loadedTiles.iterator();

		while (i.hasNext()) {
			ImageTile tile = i.next();
			
			if (tile instanceof Immobile) {
				ImageMatrixGUI.getInstance().removeImage(tile);
				i.remove();
				engine.updateScreen();
				
				try {
					Thread.sleep(SokobanConstants.SOKOBAN_WIN_ANIMATION_TIMEOUT);
				} catch(InterruptedException e) {
				    Thread.currentThread().interrupt();
				}
			}
		}			
	}
	
	private void setPlayer(Controllable player) {
		this.player = player;
	}
	
	private boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	private void setupLevel(int level) {
		setCurrentLevel(level);
		
		String scoreInStringFormat = getScore(level);
		bestScore = tryParseInt(scoreInStringFormat) ? Integer.parseInt(scoreInStringFormat) : 0;
		
		reset();
		engine.clearCanvas();
		
		loadedTiles = LevelBuilder.buildLevel(level);

		for(ImageTile tile : loadedTiles) {
			if (tile instanceof Controllable) setPlayer((Controllable) tile);
			if (tile instanceof Target) {
				addTargetPosition(tile.getPosition(), false);
			}
		}
		
		engine.addObjectsToCanvas(loadedTiles);
		engine.updateSessionStatus(getCurrentStatusString());
		
		engine.updateScreen();
	}
}

