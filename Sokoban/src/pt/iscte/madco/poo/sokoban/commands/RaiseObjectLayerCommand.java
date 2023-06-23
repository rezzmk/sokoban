package pt.iscte.madco.poo.sokoban.commands;

import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.interfaces.Command;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RaiseObjectLayerCommand implements Command {
    private final AbstractSObject object;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public RaiseObjectLayerCommand(AbstractSObject object) {
        this.object = object;
    }

    @Override
    public Boolean execute() {
        logger.log(Level.INFO, "RaiseObjectLayerCommand :: execute() called");

        object.setLayer(object.getLayer() + 1);
        return true;
    }
}
