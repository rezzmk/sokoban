package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;

import java.util.List;


public class Wall extends AbstractSObject implements Interactable {
	public Wall(Point2D position){
		super(position);
	}
	
	@Override
	public String getName() {
		return GameConstants.SPRITE_WALL_IMAGE;
	}

	@Override
	public int getLayer() {
		return GameConstants.SPRITE_WALL_LAYER;
	}

	@Override
	public List <Command> afterInteractionCommands(AbstractSObject object) {
		return null;
	}

	@Override
	public boolean interact(AbstractSObject object) {
		return false;
	}
}
