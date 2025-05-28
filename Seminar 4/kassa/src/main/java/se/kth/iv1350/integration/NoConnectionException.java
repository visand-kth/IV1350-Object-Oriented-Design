package se.kth.iv1350.integration;

/**
 * Thrown when no connection can be established
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class NoConnectionException extends Exception {

	/**
	 * When connection to database cannot be established or the database cannot be
	 * reached
	 * 
	 * @param message
	 */
	public NoConnectionException(String message) {
		super(message);
	}

}
