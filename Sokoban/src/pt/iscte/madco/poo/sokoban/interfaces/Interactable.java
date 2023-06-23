package pt.iscte.madco.poo.sokoban.interfaces;

import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;

import java.util.List;

/**
 * Represents an interactable object
 */
public interface Interactable {
    /**
     * Runs a list of commands after the initial interaction
     */
    List <Command> afterInteractionCommands(AbstractSObject object);

    /**
     * Runs the interactions with the object
     */
    boolean interact(AbstractSObject object);
}
