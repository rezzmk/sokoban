package pt.iscte.madco.poo.sokoban.commands;

import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class CloseAppCommand implements Command {
    @Override
    public Object execute() {
        ImageMatrixGUI.getInstance().dispose();
        return null;
    }
}
