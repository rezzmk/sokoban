package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.commands.NextLevelCommand;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iscte.madco.poo.sokoban.interfaces.TargetMarker;
import pt.iul.ista.poo.utils.Point2D;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Target extends AbstractSObject implements Interactable {
	private boolean isMarked = false;
	public boolean isMarked() {
		return isMarked;
	}

	public Target(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return GameConstants.SPRITE_TARGET_IMAGE;
	}

	@Override
	public int getLayer() {
		return GameConstants.SPRITE_TARGET_LAYER;
	}

	@Override
	public List <Command> afterInteractionCommands(AbstractSObject object) {
		// TODO: Make this a list of Boxes
		List<AbstractSObject> targets = Sokoban
				.getInstance()
				.getGameSession()
				.getLoadedTiles()
				.stream()
				.filter(o -> this.getClass().isInstance(o)).collect(Collectors.toList());

		for (AbstractSObject target : targets) {
			if (!((Target)target).isMarked()) return Collections.emptyList();
		}

		return Collections.singletonList(new NextLevelCommand());
	}

	@Override
	public boolean interact(AbstractSObject object) {
		isMarked = object instanceof TargetMarker;
		return true;
	}
}
