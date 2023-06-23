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

public class LargeStone extends AbstractSObject implements ActiveObject {

	public LargeStone(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return SokobanConstants.SPRITE_LSTONE_IMAGE;
	}

	private int layer = SokobanConstants.SPRITE_LSTONE_LAYER;
	
	private void setLayer(int layer) {
		this.layer = layer;
	}
	
	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public boolean move(Direction direction, List<ImageTile> tiles) {
		Point2D targetPosition = getAndValidateNewPosition(direction);

		List<ImageTile> tilesInTargetPos = AssetHelper.getObjectsInPosition(tiles, targetPosition);	
		
		// If it's colliding with something it shouldn't move
		if (this.isCollidingWith(tilesInTargetPos, targetPosition))
			return false;
		
		if (Asset.hasHole(tilesInTargetPos)) {
			setLayer(layer + 1);
		}
		
		setPosition(targetPosition);
		updateScreen();
		
		return true;
	}
}
