package pt.iscte.dcti.poo.sokoban.models;

import java.util.List;

import pt.iscte.dcti.poo.sokoban.AbstractSObject;
import pt.iscte.dcti.poo.sokoban.Asset;
import pt.iscte.dcti.poo.sokoban.AssetHelper;
import pt.iscte.dcti.poo.sokoban.SokobanConstants;
import pt.iscte.dcti.poo.sokoban.interfaces.ActiveObject;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends AbstractSObject implements ActiveObject {

	public SmallStone(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return SokobanConstants.SPRITE_SMALLROCK_IMAGE;
	}

	@Override
	public int getLayer() {
		return SokobanConstants.SPRITE_SMALLROCK_LAYER;
	}

	@Override
	public boolean move(Direction direction, List<ImageTile> tiles) {
		Point2D targetPosition = getAndValidateNewPosition(direction);
		
		List<ImageTile> tilesInTargetPos = AssetHelper.getObjectsInPosition(tiles, targetPosition);

		// If it's colliding with something it shouldn't move
		if (this.isCollidingWith(tilesInTargetPos, targetPosition))
			return false;

		if (Asset.hasHole(tilesInTargetPos)) {
			AssetHelper.removeTile(Asset.getTile(Asset.SMALLSTONE, this.getPosition()).getClass(), tiles, this.getPosition());
		}
		
		setPosition(targetPosition);
		updateScreen();

		return true;
	}	
}
