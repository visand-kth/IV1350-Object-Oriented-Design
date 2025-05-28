package se.kth.iv1350.integration;

/**
 * Exception thrown when an specified CustomerID could not be found
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class InvalidCustomerIDException extends Exception {

	/**
	 * The customerID provided was not found in the discount database
	 * 
	 * @param message The message of the exception
	 */
	public InvalidCustomerIDException(String message) {
		super(message);
	}

}
