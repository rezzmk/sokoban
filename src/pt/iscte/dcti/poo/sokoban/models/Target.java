package pt.iscte.dcti.poo.sokoban.models;

import pt.iscte.dcti.poo.sokoban.AbstractSObject;
import pt.iscte.dcti.poo.sokoban.SokobanConstants;
import pt.iul.ista.poo.utils.Point2D;

public class Target extends AbstractSObject {
	public Target(Point2D position) {
		super(position);		
	}

	@Override
	public String getName() {
		return SokobanConstants.SPRITE_TARGET_IMAGE;
	}

	@Override
	public int getLayer() {
		return SokobanConstants.SPRITE_TARGET_LAYER;
	}
}
