package pt.iscte.madco.poo.sokoban.abstractions;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.helpers.sokoban.AssetBehaviorHandler;
import pt.iscte.madco.poo.sokoban.interfaces.ActiveObject;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Movable extends AbstractSObject implements ActiveObject {
    private Direction getCurrentDirectionSet;
    protected String imageName = GameConstants.SPRITE_PLAYER_UP_IMAGE;

    public Movable(Point2D position) {
        super(position);
    }

    @Override
    public boolean move (Direction direction) {
        // Store the direction the object is taking
        setLastDirection(direction);

        // Directional behavior logic, in this case we just set the right image for the direction
        if (this instanceof Actor) this.imageName = getImageNameFromDirection(direction);

        // Run all the interactions with the target position objects
        return AssetBehaviorHandler.runInteractions(this, direction);
    }

    public Direction getCurrentDirectionSet(){
        return this.getCurrentDirectionSet;
    }

    /**
     * @param newPosition target position
     * Teleports the object to the target position. Logic that handles if teleportation is allowed or not is handled
     * by the objects themselves.
     */
    public boolean teleport(Point2D newPosition) {
        setPosition(newPosition);
        Sokoban.getInstance().updateScreen();
        return true;
    }

    protected void setLastDirection(Direction direction) {
        this.getCurrentDirectionSet = direction;
    }

    private static String getImageNameFromDirection(Direction direction) {
    	switch (direction) {
            case LEFT: return GameConstants.SPRITE_PLAYER_LEFT_IMAGE;
            case RIGHT: return GameConstants.SPRITE_PLAYER_RIGHT_IMAGE;
            case UP: return GameConstants.SPRITE_PLAYER_UP_IMAGE;
            case DOWN: return GameConstants.SPRITE_PLAYER_DOWN_IMAGE;
            default: return GameConstants.SPRITE_PLAYER_UP_IMAGE;
        }        
    }
}
