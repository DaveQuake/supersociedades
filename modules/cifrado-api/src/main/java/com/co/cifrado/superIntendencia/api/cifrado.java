package com.co.cifrado.superIntendencia.api;

import com.co.cifrado.superIntendencia.exception.CifradoOperationException;

/**
 * @author LENOVO
 */
public interface cifrado {
	/**
	 *
	 * @param cadena numero de radicado
	 * @param decifrar decifrar boleano
	 * @param urlEncode urlEncode boleano
	 * @return Returns cadena cifrada del documento.
	 * @throws CalculatorOperationException
	 */
	public String cifrado(String cadena, Boolean decifrar,Boolean urlEncode)
		throws CifradoOperationException;

}