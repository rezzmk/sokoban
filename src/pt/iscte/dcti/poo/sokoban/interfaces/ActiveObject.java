package pt.iscte.dcti.poo.sokoban.interfaces;

import java.util.List;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;

/**
 * Represents an active object, in other words, one that can move,
 * such as a player or a box (since it can be pushed)
 */
public interface ActiveObject {
	/**
	 * <p>
	 * Sends move signal to object
	 * </p>
	 * @param direction Direction in which to move
	 * @param tiles Current tiles in game
	 * @return wether the object can move in the desired direction or not, kinda of works like an isMoved() or hasMoved()
	 */
	boolean move(Direction direction, List<ImageTile> tiles);
}
