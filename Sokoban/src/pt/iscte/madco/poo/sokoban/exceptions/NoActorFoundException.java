package pt.iscte.madco.poo.sokoban.exceptions;

/**
 * Triggered when no actor is set in the game. Usually this means there is no player set
 */
public class NoActorFoundException extends Exception {
    private static final long serialVersionUID = 1L;

	public NoActorFoundException (String errorMessage) {
        super(errorMessage);
    }
}
