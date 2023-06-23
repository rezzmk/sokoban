package pt.iscte.dcti.poo.sokoban;

import java.util.List;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

/**
 * Represents an ingame object, such as a floortile or a player
 */
public abstract class AbstractSObject implements ImageTile {
	
	private Point2D position;
		
	public AbstractSObject(Point2D position) {
		this.position = position;
	}
	
	/**
	 * <p>
	 * Checks if current tile is colliding with the one in the position it's moving to
	 * </p>
	 * @param tile tile in the position it's moving to
	 */
	protected boolean isCollidingWith(ImageTile tile) {
		// Higher or same layer levels mean colliding = true
		return tile.getLayer() >= this.getLayer(); 
	}
	
	protected boolean isCollidingWith(List<ImageTile> tiles, Point2D position) {
		// Higher or same layer levels mean colliding = true
		List<ImageTile> tilesInPosition = AssetHelper.getTiles(tiles, position); 
		
		for(ImageTile tile : tilesInPosition) {
			if (tile.getLayer() >= this.getLayer()) return true;
		}
		
		return false; 
	}
	
	/**
	 * <p>
	 * Checks if the tile is movable in respect to the layer of this object.
	 * </p>
	 * @param tile Target tile
	 * @param layer layer to compare
	 */
	protected boolean isMovable(ImageTile tile, int layer) {
		return tile.getLayer() == layer;
	}
	
	/**
	 * <p>
	 * Checks if one tile can move the other.
	 * </p>	 
	 */
	protected boolean isMovable(ImageTile a, ImageTile b) {
		return a.getLayer() == b.getLayer();
	}
	
	/**
	 * <p>
	 * Checks if position is valid, criteria is: has to be inside the canvas
	 * </p>
	 * @param pos Position
	 */
	protected boolean isValidDirection(Point2D pos) {
		return 
				pos.getX() >= 0  
			 && pos.getX() < SokobanConstants.GAME_WIDTH   
			 && pos.getY() >= 0                            
			 && pos.getY() < SokobanConstants.GAME_HEIGHT;
	}
	
	/**
	 * <p>
	 * Gets current position of ImageTile
	 * </p>	
	 */
	public Point2D getPosition() {
		return position;
	}
	
	/**
	 * <p>
	 * Gets current position of ImageTile
	 * </p>	
	 */
	protected void setPosition(Point2D pos) {
		this.position = pos;
	}

	/**
	 * <p>
	 * updates the window with the latest changes made
	 * </p>	
	 */
	protected void updateScreen() {
		Sokoban.getInstance().updateScreen();
	}
	
	protected Point2D getAndValidateNewPosition(Direction direction) {
		Point2D newPos = position.plus(direction.asVector());
		if (!isValidDirection(newPos)) throw new IllegalArgumentException("Position out of bounds");
		
		return newPos;
	}
}
