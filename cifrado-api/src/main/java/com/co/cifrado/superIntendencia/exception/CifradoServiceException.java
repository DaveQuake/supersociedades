package com.co.cifrado.superIntendencia.exception;

/**
 * @author cesar omar
 */

public class CifradoServiceException  extends Exception {

	public CifradoServiceException(String message) {
		super(message);
	}

	public CifradoServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CifradoServiceException(Throwable cause) {
		super(cause);
	}

	protected CifradoServiceException(
		String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

}