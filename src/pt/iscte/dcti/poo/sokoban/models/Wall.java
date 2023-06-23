package pt.iscte.dcti.poo.sokoban.models;

import pt.iscte.dcti.poo.sokoban.AbstractSObject;
import pt.iscte.dcti.poo.sokoban.SokobanConstants;
import pt.iscte.dcti.poo.sokoban.interfaces.Immobile;
import pt.iul.ista.poo.utils.Point2D;

public class Wall extends AbstractSObject implements Immobile {
	public Wall(Point2D position){
		super(position);
	}
	
	@Override
	public String getName() {
		return SokobanConstants.SPRITE_WALL_IMAGE;
	}

	@Override
	public int getLayer() {
		return SokobanConstants.SPRITE_WALL_LAYER;
	}
}
