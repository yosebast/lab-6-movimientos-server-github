package org.test.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.test.beans.Ist_Movimientos;
import org.test.beans.MovAgrupProdAndPartnertsByMsisdn;

public interface MovDao {

	List<Ist_Movimientos> getAllMov() throws SQLException;
	Collection<?> getMovFromProductsSubscribeByMsisdn(String msisdn, Date initdate, Date enddate) throws SQLException;
	List<MovAgrupProdAndPartnertsByMsisdn> getCargosAndSuscripcionesTercerosByMsisdnAndDate(String msisdn, Date initdate, Date enddate) throws SQLException;
	List<String> getListMsisdn(String customer_id) throws SQLException;

}
