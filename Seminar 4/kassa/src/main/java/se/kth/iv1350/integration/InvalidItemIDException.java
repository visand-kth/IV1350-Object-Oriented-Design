package se.kth.iv1350.integration;

/**
 * Exception thrown when an item could not be found from the provided itemID
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class InvalidItemIDException extends Exception {

	/**
	 * The itemID provided was not found in the inventory database
	 * 
	 * @param message The message of the exception
	 */
	public InvalidItemIDException(String message) {
		super(message);
	}

}
