package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iul.ista.poo.utils.Point2D;

public class LargeStone extends Movable {
	public LargeStone(Point2D position) {
		super(position);

		getAttributes().put("blocker", true);
		setLayer(GameConstants.SPRITE_LSTONE_LAYER);
	}

	@Override
	public String getName() {
		return GameConstants.SPRITE_LSTONE_IMAGE;
	}
}
