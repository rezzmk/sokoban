package pt.iscte.madco.poo.sokoban.exceptions;

public class LevelDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public LevelDoesNotExistException  (String errorMessage) {
        super(errorMessage);
    }
}
