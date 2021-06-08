package com.constructora.mundoFuturo.exceptions;

public class AplicacionException extends Exception {


    /**
	 * 
	 */
	private static final long serialVersionUID = 5522281717571981037L;

	public AplicacionException(String message) {
        super(message);
    }

    public AplicacionException(String message, Throwable cause) {
        super(message, cause);
    }

}
