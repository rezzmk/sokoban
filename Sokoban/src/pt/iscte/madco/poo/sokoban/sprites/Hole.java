package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.commands.RaiseObjectLayerCommand;
import pt.iscte.madco.poo.sokoban.commands.RemoveObjectCommand;
import pt.iscte.madco.poo.sokoban.commands.RestartLevelCommand;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;

import java.util.Collections;
import java.util.List;

public class Hole extends AbstractSObject implements Interactable {
	private boolean isBlocked = false;

	public Hole(Point2D position){
		super(position);
	}
	
	@Override
	public String getName() {
		return GameConstants.SPRITE_HOLE_IMAGE;
	}

	@Override
	public int getLayer() {
		return GameConstants.SPRITE_HOLE_LAYER;
	}

	@Override
	public List <Command> afterInteractionCommands(AbstractSObject object) {
		if (object instanceof Actor) {
			return Collections.singletonList(new RestartLevelCommand());
		}

		if ((boolean) object.getAttributes().getOrDefault("blocker", false)) {
			isBlocked = true;
			return Collections.singletonList(new RaiseObjectLayerCommand(object));
		}

		return Collections.singletonList(new RemoveObjectCommand(object));
	}

	@Override
	public boolean interact(AbstractSObject object) {
		return !isBlocked;
	}
}
