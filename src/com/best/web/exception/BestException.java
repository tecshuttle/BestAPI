package com.best.web.exception;

public class BestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2995426686099615379L;

	public BestException(){
		super();
	}
	
	public BestException(String message) {
        super(message);
    }

    public BestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BestException(Throwable cause) {
        super(cause);
    }
}