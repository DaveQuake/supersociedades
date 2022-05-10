package com.co.cifrado.superIntendencia.exception;

/**
 * @author cesar omar
 */

public class CifradoOperationException extends Exception {

	public CifradoOperationException(String message) {
		super(message);
	}

	public CifradoOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CifradoOperationException(Throwable cause) {
		super(cause);
	}

	protected CifradoOperationException(
		String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

}