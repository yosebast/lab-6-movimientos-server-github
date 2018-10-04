package org.test.service;

import java.util.List;

import org.test.beans.MovAgrupProdAndPartnertsByMsisdn;
import org.test.exception.MovException;
import org.test.model.IsterDTO;

public interface MovService {

	IsterDTO getAllMov() throws MovException;
	IsterDTO getMovFromProductsSubscribeByMsisdn(final String msisdn, final String initdate, final String enddate) throws MovException;
	List<MovAgrupProdAndPartnertsByMsisdn> getCargosAndSuscripcionesTercerosByMsisdnAndDate(final String customer_id, final String msisdn, final String initdate, final String enddate) throws MovException;
	void validaExisteMsisdn(String customer_id, String msisdn) throws MovException;
}
