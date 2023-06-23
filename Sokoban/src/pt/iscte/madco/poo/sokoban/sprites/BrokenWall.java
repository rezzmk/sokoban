package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.commands.RemoveObjectCommand;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;

import java.util.Collections;
import java.util.List;

public class BrokenWall extends AbstractSObject implements Interactable {
    public BrokenWall (Point2D position) {
        super(position);
    }

    @Override
    public String getName () {
        return GameConstants.SPRITE_BRWALL_IMAGE;
    }

    @Override
    public int getLayer () {
        return GameConstants.SPRITE_BRWALL_LAYER;
    }

    @Override
    public List <Command> afterInteractionCommands(AbstractSObject object) {
        if (!(object instanceof Actor)) return Collections.emptyList();

        if ((boolean) object.getAttributes().getOrDefault(GameConstants.HAMMER_INTERACTION_PROPERTY, false)) {
            return Collections.singletonList(new RemoveObjectCommand(this));
        }

        return null;
    }

    @Override
    public boolean interact(AbstractSObject object) {
        if (object instanceof Actor) return true;

        return false;
    }
}
