package pt.iscte.madco.poo.sokoban.commands;

import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iul.ista.poo.utils.Point2D;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TeleportCommand implements Command {
    private final Movable  teleportable;
    private final Point2D targetPos;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public TeleportCommand(Movable teleportable, Point2D targetPos) {
        this.teleportable = teleportable;
        this.targetPos = targetPos;
    }

    @Override
    public Boolean execute() {
        logger.log(Level.INFO, "TeleportCommand :: execute() called");

        return teleportable.teleport(targetPos);
    }
}
