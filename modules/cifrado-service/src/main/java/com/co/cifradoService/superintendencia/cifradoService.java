package com.co.cifradoService.superintendencia;

import com.co.cifrado.superIntendencia.api.cifrado;
import com.co.cifrado.superIntendencia.exception.CifradoOperationException;
import com.co.cifrado.superIntendencia.exception.CifradoServiceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.xml.ws.WebServiceException;

import org.osgi.service.component.annotations.Component;
import org.tempuri.IUtilitiesServices;
import org.tempuri.IUtilitiesServicesCifradoFaultExceptionFaultFaultMessage;




/**
 * @author LENOVO
 */
@Component(
	immediate = true,
	property = {
	},
	service = cifrado.class
)
public class cifradoService implements cifrado {

	@Override
	public String cifrado(String cadena, Boolean decifrar, Boolean urlEncode) throws CifradoOperationException {
		System.out.println("entramos a cifrado service ----------------------->");
		try {
			return _getService().cifrado(cadena, decifrar, urlEncode);
		}
		catch (CifradoServiceException | WebServiceException | IUtilitiesServicesCifradoFaultExceptionFaultFaultMessage cse) {
			System.out.println("CifradoServiceException ----------------------->"+cse);
			if (_log.isErrorEnabled()) {
				_log.error(cse.getMessage(), cse);
			}
			throw new CifradoOperationException(cse.getMessage(), cse);
		}

	}

	private IUtilitiesServices _getService() throws CifradoServiceException {
		if (Validator.isNull(_cifradoSoap)) {
			try {
				org.tempuri.UtilitiesServices cifrado = new org.tempuri.UtilitiesServices();
				_cifradoSoap =  cifrado.getBasicHttpBindingIUtilitiesServices();
			}
			catch (WebServiceException wse) {
				if (_log.isErrorEnabled()) {
					_log.error(wse.getMessage(), wse);
				}
				System.out.println("error en servicio ----------------------->"+wse);
				throw new CifradoServiceException(wse.getMessage(), wse);
			}

		}
		return _cifradoSoap;
	}

	private IUtilitiesServices _cifradoSoap;

	private Log _log = LogFactoryUtil.getLog(
			cifradoService.class);

}