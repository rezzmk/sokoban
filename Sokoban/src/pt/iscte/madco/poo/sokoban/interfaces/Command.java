package pt.iscte.madco.poo.sokoban.interfaces;

/**
 * Command pattern implementation contract
 */
public interface Command {
    /**
     * Executes the command, returns any object, usually just a boolean
     */
    Object execute();
}