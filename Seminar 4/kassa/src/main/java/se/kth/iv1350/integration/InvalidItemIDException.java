package se.kth.iv1350.integration;

public class InvalidItemIDException extends Exception {

	/**
	 * The itemID provided was not found in the inventory database
	 * 
	 * @param message
	 */
	public InvalidItemIDException(String message) {
		super(message);
	}

}
