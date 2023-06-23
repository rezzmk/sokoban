package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends Movable {
    public SmallStone(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return GameConstants.SPRITE_SMALLROCK_IMAGE;
    }

    @Override
    public int getLayer() {
        return GameConstants.SPRITE_SMALLROCK_LAYER;
    }
}



