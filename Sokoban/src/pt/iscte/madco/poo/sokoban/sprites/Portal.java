package pt.iscte.madco.poo.sokoban.sprites;
import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iscte.madco.poo.sokoban.commands.TeleportCommand;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iul.ista.poo.utils.Point2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Portal extends AbstractSObject implements Interactable {
    private String imageName = GameConstants.SPRITE_PORTAL_ONE_IMAGE;

    public void swapImage() {
        imageName = imageName.equals(GameConstants.SPRITE_PORTAL_ONE_IMAGE)
                ? GameConstants.SPRITE_PORTAL_TWO_IMAGE
                : GameConstants.SPRITE_PORTAL_ONE_IMAGE;
    }

    public Portal (Point2D position) {
        super(position);

        getAttributes().put(GameConstants.OBJECT_BLOCKED_ATTR_NAME, false);
    }

    @Override
    public String getName () {
        return imageName;
    }

    @Override
    public int getLayer () {
        return GameConstants.SPRITE_PORTAL_LAYER;
    }

    @Override
    public List <Command> afterInteractionCommands(AbstractSObject object) {
        if (object instanceof Actor) getAttributes().put(GameConstants.OBJECT_BLOCKED_ATTR_NAME, false);
        if ((boolean) getAttributes().get(GameConstants.OBJECT_BLOCKED_ATTR_NAME)) return Collections.emptyList();
        if (object instanceof Movable) {
            Portal counterPortal = (Portal)Sokoban
                    .getInstance()
                    .getGameSession()
                    .getLoadedTiles()
                    .stream()
                    .filter(
                            p -> this.getClass().isInstance(p) && !p.getPosition().equals(this.getPosition())
                    )
                    .findFirst()
                    .get();

            if ((boolean) counterPortal.getAttributes().get(GameConstants.OBJECT_BLOCKED_ATTR_NAME)) {
                if (!(object instanceof Actor)) getAttributes().put(GameConstants.OBJECT_BLOCKED_ATTR_NAME, true);
                return Collections.emptyList();
            }

            ArrayList<Command> listOfCommands = new ArrayList<Command>();

            if (!(object instanceof Actor)) counterPortal.getAttributes().put(GameConstants.OBJECT_BLOCKED_ATTR_NAME, true);
            listOfCommands.add(new TeleportCommand((Movable) object, counterPortal.getPosition()));

            return listOfCommands;
        }

        return Collections.emptyList();
    }

    @Override
    public boolean interact(AbstractSObject object) {
        return true;
    }
}
