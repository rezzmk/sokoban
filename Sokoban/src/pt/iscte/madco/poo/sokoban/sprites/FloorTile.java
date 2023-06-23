package pt.iscte.madco.poo.sokoban.sprites;
import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iul.ista.poo.utils.Point2D;

public class FloorTile extends AbstractSObject {
	public FloorTile(Point2D position){
		super(position);
	}
	
	@Override
	public String getName() {
		return GameConstants.SPRITE_FLOOR_IMAGE;
	}

	@Override
	public int getLayer() {
		return GameConstants.SPRITE_FLOOR_LAYER;
	}
}
