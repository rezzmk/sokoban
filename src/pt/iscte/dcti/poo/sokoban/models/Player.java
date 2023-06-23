package pt.iscte.dcti.poo.sokoban.models;

import java.util.List;

import pt.iscte.dcti.poo.sokoban.AbstractSObject;
import pt.iscte.dcti.poo.sokoban.AssetHelper;
import pt.iscte.dcti.poo.sokoban.SokobanConstants;
import pt.iscte.dcti.poo.sokoban.Sokoban;
import pt.iscte.dcti.poo.sokoban.Asset;
import pt.iscte.dcti.poo.sokoban.interfaces.ActiveObject;
import pt.iscte.dcti.poo.sokoban.interfaces.Controllable;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends AbstractSObject implements Controllable {
	private String imageName;
	
	public Player(Point2D initialPosition){
		super(initialPosition);
		imageName = getImageNameFromDirection(Direction.UP);
	}
	
	@Override
	public String getName() {		
		return imageName;
	}
	
	@Override
	public int getLayer() {
		return SokobanConstants.SPRITE_PLAYER_LAYER;
	}
	
	@Override
	public boolean move(Direction direction, List<ImageTile> tiles) {		
		Point2D targetPosition = getAndValidateNewPosition(direction);
		
		List<ImageTile> tilesInTargetPos = AssetHelper.getObjectsInPosition(tiles, targetPosition);
		
		boolean reloadEnergy = false;
		
		// Leave early pattern, if it's a wall, just leave, no need to check for anything else
		// pottentially costing more resourses than needed.
		if (Asset.hasWall(tilesInTargetPos)) {
			return false;
		}
		// Check if collision and will happen and see if it's an active object we can push
		else if (isCollidingWith(tilesInTargetPos, targetPosition)) {
			for (ImageTile tile : AssetHelper.getActiveObjects(tilesInTargetPos)) {
				if (isMovable(tile, this.getLayer())) {
					if (!((ActiveObject) tile).move(direction, tiles)) return false;
				} else return false;
			}
		}
		// Reload energy if we're passing by a battery
		else if (Asset.hasBattery(tilesInTargetPos)) {
			AssetHelper.removeTile(Asset.getTile(Asset.BATTERY, targetPosition).getClass(), tiles, targetPosition);
			reloadEnergy = true;
		}
		// Droping into a hole is an instant loss and we restart
		else if (Asset.hasHole(tilesInTargetPos)) {
			Sokoban.getInstance().getSessionInstance().restartLevel();
			return true;
		}
		
		imageName = getImageNameFromDirection(direction);

		updateScore(reloadEnergy);
		update(targetPosition);
		
		return true;
	}
	
	private void update(Point2D position) {
		setPosition(position);
		updateScreen();
	}
	
	private void updateScore(boolean reloadEnergy) {
		Sokoban sokoban = Sokoban.getInstance();
		
		sokoban.getSessionInstance().incrementScore();
		
		if (reloadEnergy) sokoban.getSessionInstance().reloadEnergy();

		sokoban.getSessionInstance().updateSession();
	}
	
	private static String getImageNameFromDirection(Direction direction) {
		switch(direction) {
			case LEFT:	return SokobanConstants.SPRITE_PLAYER_LEFT_IMAGE;
			case RIGHT: return SokobanConstants.SPRITE_PLAYER_RIGHT_IMAGE;
			case UP:    return SokobanConstants.SPRITE_PLAYER_UP_IMAGE;
			case DOWN:  return SokobanConstants.SPRITE_PLAYER_DOWN_IMAGE;
			
			default: throw new IllegalArgumentException("Invalid direction of type Direction");
		}		
	}	
}
