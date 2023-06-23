package pt.iscte.madco.poo.sokoban.interfaces;
import pt.iul.ista.poo.utils.Direction;

/**
 * Represents an active object, in other words, one that can move or be moved,
 * such as a player or a box (since it can be pushed)
 */
public interface ActiveObject {
    /**
     * <p>
     * Moves an object
     * </p>
     *
     * @param direction Direction in which to move
     * @return whether the object can move in the desired direction or not, kinda of works like an isMoved() or hasMoved()
     */
    boolean move(Direction direction);
}
