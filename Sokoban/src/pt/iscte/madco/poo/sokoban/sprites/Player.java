package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends Movable implements Actor {
	public Player(Point2D initialPosition){
		super(initialPosition);
	}

	@Override
	public String getName() {
		return imageName;
	}

	@Override
	public int getLayer() {
		return GameConstants.SPRITE_PLAYER_LAYER;
	}
}
