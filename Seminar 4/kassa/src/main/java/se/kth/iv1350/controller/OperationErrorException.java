package se.kth.iv1350.controller;

class OperationErrorException extends Exception {

	/**
	 * General operation error exception for when an operation fails
	 * @param message
	 * @param e The underlying exception
	 */
    public OperationErrorException(String message, Exception e) {
		super(message, e);
	}

}
