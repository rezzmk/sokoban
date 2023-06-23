package pt.iscte.madco.poo.sokoban.commands;

import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.gui.Dialog;
import pt.iscte.madco.poo.sokoban.interfaces.Command;

public class ShowScoreboardCommand implements Command {

    @Override
    public Boolean execute() {
        Dialog.showMessage(Sokoban.getInstance().getGameSession().getScoreboard());
        return true;
    }
}
