package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iscte.madco.poo.sokoban.interfaces.TargetMarker;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends Movable implements TargetMarker {
	public Box(Point2D position) {
		super(position);
	}
	
	@Override
	public String getName() {
		return GameConstants.SPRITE_BOX_IMAGE;
	}

	@Override
	public int getLayer() {
		return GameConstants.SPRITE_BOX_LAYER;
	}
}
