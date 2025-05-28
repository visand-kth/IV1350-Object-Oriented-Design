package se.kth.iv1350.integration;

public class InvalidCustomerIDException extends Exception {

	/**
	 * The customerID provided was not found in the discount database
	 * 
	 * @param message
	 */
	public InvalidCustomerIDException(String message) {
		super(message);
	}

}
