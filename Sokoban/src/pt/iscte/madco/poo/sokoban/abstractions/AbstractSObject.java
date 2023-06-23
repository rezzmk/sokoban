package pt.iscte.madco.poo.sokoban.abstractions;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSObject implements ImageTile {
    // Every game object can have attributes. This should be a decent approach in terms of extendability
    private final Map <String, Object> attributes;
    private Point2D position;
    private int layer;

    @Override
    public int getLayer() {
        return layer;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    // We might need to change layer (easiest way to determine when an object should turn unmovable by the player)
    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public AbstractSObject(Point2D position) {
        this.position = position;
        this.attributes = new HashMap<>();
    }

    public void setPosition(Point2D pos) {
        this.position = pos;
    }

    public Point2D setAndValidateNewPosition(Direction direction) {
        Point2D position = this.position.plus(direction.asVector());

        // We should never reach out of bounds unless the levels are badly designed without walls
        if (!ImageMatrixGUI.getInstance().isWithinBounds(position))
            throw new IllegalArgumentException("Position out of bounds");

        return position;
    }
}
