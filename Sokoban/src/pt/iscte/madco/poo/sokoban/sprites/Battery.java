package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.commands.ReloadStatsCommand;
import pt.iscte.madco.poo.sokoban.commands.RemoveObjectCommand;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;

import java.util.Collections;
import java.util.List;

public class Battery extends AbstractSObject implements Interactable {
	public Battery(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return GameConstants.SPRITE_BATTERY_IMAGE;
	}
	
	@Override
	public int getLayer() {
		return GameConstants.SPRITE_BATTERY_LAYER;
	}

	@Override
	public List <Command> afterInteractionCommands(AbstractSObject object) {
		if (object instanceof Actor) return Collections.singletonList(new RemoveObjectCommand(this));
		return Collections.emptyList();
	}

	@Override
	public boolean interact(AbstractSObject object) {
		if (object instanceof Actor) new ReloadStatsCommand().execute();
		return true;
	}
}
