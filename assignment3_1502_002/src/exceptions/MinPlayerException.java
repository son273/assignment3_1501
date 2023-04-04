package exceptions;

public class MinPlayerException extends Exception{ //Custom Exception Class

	/**
	 * Sends a custom message
	 * @param message of exception
	 */
	public MinPlayerException (String message) {
		super (message);
	}
}
