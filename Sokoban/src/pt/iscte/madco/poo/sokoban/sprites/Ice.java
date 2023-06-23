package pt.iscte.madco.poo.sokoban.sprites;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.commands.MoveCommand;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iul.ista.poo.utils.Point2D;

import java.util.Collections;
import java.util.List;

public class Ice extends AbstractSObject implements Interactable {
    public Ice (Point2D position) {
        super(position);
    }

    @Override
    public String getName () {
        return GameConstants.SPRITE_ICE_IMAGE;
    }

    @Override
    public int getLayer () {
        return GameConstants.SPRITE_ICE_LAYER;
    }

    @Override
    public List <Command> afterInteractionCommands(AbstractSObject object) {
        Command command = new MoveCommand((Movable) object, ((Movable)object).getCurrentDirectionSet());

        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Collections.singletonList(command);
    }

    @Override
    public boolean interact(AbstractSObject object) {
        return true;
    }
}
