package pt.iscte.dcti.poo.sokoban.interfaces;

import pt.iul.ista.poo.observer.Observed;

/**
 * Base Game Engine interface, abstracted of whatever we use to paint the images  
 */
public interface BiDimGameEngine {
	/**
	 * <p>
	 * Clears the screen
	 * </p>
	 */
	abstract void clearCanvas();
	
	/**
	 * <p>
	 * Adds objects to the screen
	 * </p>
	 * @param obj objects (in the form of an object, such as any collection) to add
	 */
	abstract void addObjectsToCanvas(Object obj);
	
	/**
	 * <p>
	 * Updates the session status, usually games have something showing a score that is updated in real time,
	 * we use this for that purpose
	 * </p>
	 * @param status Status message to show, IE: "Level: x, Moves: y, Energy: z"
	 */
	abstract void updateSessionStatus(String status);
	
	/**
	 * <p>
	 * Updates the screen
	 * </p>	 
	 */
	abstract void updateScreen();
	
	/**
	 * <p>
	 * Sets the window size
	 * </p>
	 * @param w width
	 * @param h height
	 */
	abstract void setCanvasSize(int w, int h);

	/**
	 * <p>
	 * Starts the Game
	 * </p>	
	 */
	abstract void start();
	
	/**
	 * <p>
	 * Gets the last key pressed
	 * </p>	
	 */
	abstract int getLastKeyPressed(Observed arg);
}
