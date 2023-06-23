package pt.iscte.madco.poo.sokoban.commands;

import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iul.ista.poo.utils.Direction;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveCommand implements Command {
    private final Movable movable;
    private final Direction direction;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public MoveCommand(Movable movable, Direction direction) {
        this.movable = movable;
        this.direction = direction;
    }

    public MoveCommand(Direction direction) {
        movable = (Movable) Sokoban.getInstance().getGameSession().getMainActor();
        this.direction = direction;
    }

    @Override
    public Boolean execute () {
        logger.log(Level.INFO, "MoveCommand :: execute() called");

        return movable.move(direction);
    }
}
