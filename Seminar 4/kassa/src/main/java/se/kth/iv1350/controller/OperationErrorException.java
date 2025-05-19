package se.kth.iv1350.controller;

class OperationErrorException extends Exception {

    public OperationErrorException(String message, Exception e) {
		super(message, e);
	}

}
