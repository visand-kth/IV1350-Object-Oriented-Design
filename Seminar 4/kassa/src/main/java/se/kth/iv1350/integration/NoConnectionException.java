package se.kth.iv1350.integration;

class NoConnectionException extends Exception {

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
