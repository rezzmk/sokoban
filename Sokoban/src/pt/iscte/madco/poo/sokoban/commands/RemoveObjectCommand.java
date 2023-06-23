package pt.iscte.madco.poo.sokoban.commands;

import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.TargetMarker;
import pt.iul.ista.poo.gui.ImageMatrixGUI;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RemoveObjectCommand implements Command {
    private final AbstractSObject object;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public RemoveObjectCommand(AbstractSObject object) {
        this.object = object;
    }

    @Override
    public Boolean execute() {
        logger.log(Level.INFO, "RemoveObjectCommand :: execute() called");

        if (object instanceof TargetMarker) {
            boolean isLastInGame = Sokoban
                    .getInstance()
                    .getGameSession()
                    .getLoadedTiles()
                    .stream()
                    .filter(a -> a instanceof TargetMarker).count()
                    == 1;

            if (isLastInGame) new RestartLevelCommand().execute();
        }

        ImageMatrixGUI.getInstance().removeImage(object);
        Sokoban.getInstance().getGameSession().getLoadedTiles().remove(object);

        return true;
    }
}
