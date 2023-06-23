package pt.iscte.madco.poo.sokoban.commands;

import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.interfaces.Command;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NextLevelCommand implements Command {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Boolean execute() {
        logger.log(Level.INFO, "NextLevelCommand  :: execute() called");

        Sokoban.getInstance().getGameSession().nextLevel();
        return true;
    }
}
