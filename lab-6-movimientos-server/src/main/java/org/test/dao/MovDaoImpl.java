package org.test.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.test.beans.Ist_Movimientos;
import org.test.beans.Ist_ProductosSubscribeByMsisdn;
import org.test.beans.MovAgrupProdAndPartnertsByMsisdn;
import org.test.jdbc.NamedParameterStatement;
import org.test.jdbc.SqlUtils;
import org.test.util.date.DateTimeUtil;


@Repository
public class MovDaoImpl implements MovDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovDaoImpl.class);
	
	@Autowired
	DataSource dataSource;
	
	public List<Ist_Movimientos> getAllMov() throws SQLException {		
		Connection conn = dataSource.getConnection();
		NamedParameterStatement namedParameterStatement = null;
		ResultSet rs = null;
		
		String sQuery = "SELECT * FROM IST.TB_HIST_MOVIMIENTOS";
		
		List<Ist_Movimientos> listaMov = new ArrayList<Ist_Movimientos>();
		try {
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);
			rs = namedParameterStatement.executeQuery();
		
			while(rs.next()) {
				Ist_Movimientos istMov = new Ist_Movimientos();
				
				istMov.setIdcli(rs.getInt("IDCLI"));
				istMov.setIdprod(rs.getInt("IDPROD"));
				istMov.setIdtrans(rs.getInt("IDTRANS"));
				istMov.setIdtrans_tercero(rs.getString("IDTRANS_TERCERO"));
				istMov.setImporte(rs.getDouble("IMPORTE"));
				istMov.setMarket(rs.getString("MARKET"));
				istMov.setMultisim(rs.getString("MULTISIM"));
				istMov.setStatus_mov(rs.getString("STATUS_MOV"));
				istMov.setTimestamp(rs.getTimestamp("FECHA").getTime());
				istMov.setTipo_mov(rs.getString("TIPO_MOV"));
			
				listaMov.add(istMov);				
			}
		} finally {
			SqlUtils.closeResulSet(rs);
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();
		}
		return listaMov;
	}

	
	public Collection<?> getMovFromProductsSubscribeByMsisdn(String msisdn, Date initdate, Date enddate) throws SQLException {
		Connection conn = dataSource.getConnection();
		NamedParameterStatement namedParameterStatement = null;
		ResultSet rs = null;		
		List<Ist_ProductosSubscribeByMsisdn> list_istProByMsisdn = new ArrayList<Ist_ProductosSubscribeByMsisdn>();	
		
		String sQuery = "SELECT DISTINCT T.NAME, P.NAME, H.FECHA, H.IMPORTE, H.MULTISIM, P.FLAG_SUSCRIPCION FROM IST.TB_CLIENTES C INNER JOIN IST.TB_HIST_MOVIMIENTOS H ON C.IDCLI = H.IDCLI INNER JOIN IST.TB_PRODUCTOS P ON P.IDPROD = H.IDPROD INNER JOIN IST.TB_PARTNERS T ON P.IDPART = T.IDPART AND C.MSISDN =:MSDIN AND H.FECHA >= :fecha1 AND H.FECHA <= :fecha2 ORDER BY  H.FECHA DESC";
		try {
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);
			namedParameterStatement.setString("MSDIN", msisdn);
			namedParameterStatement.setDate("fecha1", new java.sql.Date(initdate.getTime()));
			namedParameterStatement.setDate("fecha2", new java.sql.Date(enddate.getTime()));
		
			rs = namedParameterStatement.executeQuery();

			while (rs.next()) {
				Ist_ProductosSubscribeByMsisdn istProByMsisdn = new Ist_ProductosSubscribeByMsisdn();
				istProByMsisdn.setFlagSubcripcion(rs.getString("FLAG_SUSCRIPCION"));
				istProByMsisdn.setImporte(rs.getDouble("IMPORTE"));
				istProByMsisdn.setMultisim(rs.getString("MULTISIM"));
				istProByMsisdn.setPartnerName(rs.getString("NAME"));
				istProByMsisdn.setProductName(rs.getString("PRODUCTO_NAME"));
				istProByMsisdn.setTimestamp(DateTimeUtil.getInstance().formatDateToView(rs.getTimestamp("FECHA").getTime()));				
				list_istProByMsisdn.add(istProByMsisdn);
			}		
		} finally {
			SqlUtils.closeResulSet(rs);
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();
		}
		/*Ist_ProductosSubscribeByMsisdn istProByMsisdn = new Ist_ProductosSubscribeByMsisdn();
		istProByMsisdn.setFlagSubcripcion("N");
		istProByMsisdn.setImporte(24.9);
		istProByMsisdn.setMultisim(null);
		istProByMsisdn.setPartnerName("Partner77");
		istProByMsisdn.setProductName("NAME_1");		
		istProByMsisdn.setTimestamp(DateTimeUtil.getInstance().formatDateToView(1531231510000L));				
		list_istProByMsisdn.add(istProByMsisdn);	*/	
		/*if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("msisdn:{} | initdate:{} | enddate:{} | flagSubcripcion:{} | importe:{} | multisim:{} | partnerName:{} | productName:{} | fechaMov:{}", new Object[] {msisdn, initdate, enddate, "N", 24.9, null, "Partner77", "NAME_1", DateTimeUtil.getInstance().formatDateToView(1531231510000L)});
		}*/	 
		return list_istProByMsisdn;		
	}
	
	
	public List<MovAgrupProdAndPartnertsByMsisdn> getCargosAndSuscripcionesTercerosByMsisdnAndDate(String msisdn, Date initdate, Date enddate) throws SQLException {
		Connection conn = dataSource.getConnection();
		NamedParameterStatement namedParameterStatement = null;
		ResultSet rs = null;		
		List<MovAgrupProdAndPartnertsByMsisdn> list_movAgrupProdAndPartnertsByMsisdn = new ArrayList<MovAgrupProdAndPartnertsByMsisdn>();	
		
		String sQuery = "SELECT DISTINCT C.MSISDN, H.MULTISIM, H.FECHA, H.IDTRANS, H.IMPORTE, A.NAME, P.NAME PRODUCTO_NAME, H.MARKET FROM IST.TB_HIST_MOVIMIENTOS H INNER JOIN IST.TB_CLIENTES C ON C.IDCLI = H.IDCLI INNER JOIN IST.TB_PRODUCTOS P ON P.IDPROD = H.IDPROD INNER JOIN IST.TB_PARTNERS T ON P.IDPART = T.IDPART INNER JOIN IST.TB_AGRUPADORES A ON T.IDAGR = A.IDAGR AND C.MSISDN = :MSDIN AND H.FECHA >= :fecha1 AND H.FECHA <= :fecha2 ORDER BY  H.FECHA DESC";
		try {
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);
			namedParameterStatement.setString("MSDIN", msisdn);
			namedParameterStatement.setDate("fecha1", new java.sql.Date(initdate.getTime()));
			namedParameterStatement.setDate("fecha2", new java.sql.Date(enddate.getTime()));
		
			rs = namedParameterStatement.executeQuery();

			while (rs.next()) {
				MovAgrupProdAndPartnertsByMsisdn movAgrupProdAndPartnertsByMsisdn = new MovAgrupProdAndPartnertsByMsisdn();
				movAgrupProdAndPartnertsByMsisdn.setMsisdn(rs.getString("MSISDN"));
				movAgrupProdAndPartnertsByMsisdn.setMultisim(rs.getString("MULTISIM"));
				movAgrupProdAndPartnertsByMsisdn.setTimestamp(DateTimeUtil.getInstance().formatDateToView(rs.getTimestamp("FECHA").getTime()));
				movAgrupProdAndPartnertsByMsisdn.setIdtrans(rs.getInt("IDTRANS"));
				movAgrupProdAndPartnertsByMsisdn.setImporte(rs.getDouble("IMPORTE"));
				movAgrupProdAndPartnertsByMsisdn.setAgrupadores(rs.getString("NAME"));
				movAgrupProdAndPartnertsByMsisdn.setProductName(rs.getString("PRODUCTO_NAME"));
				movAgrupProdAndPartnertsByMsisdn.setMarket(rs.getString("MARKET"));								
				list_movAgrupProdAndPartnertsByMsisdn.add(movAgrupProdAndPartnertsByMsisdn);
				
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("msisdn:{} | initdate:{} | enddate:{} | multisim:{} | fechaMov:{} | idtrans:{} | importe:{} | agrupadores:{} | productName:{} | market:{}", new Object[] {msisdn, initdate, enddate, rs.getString("MULTISIM"), DateTimeUtil.getInstance().formatDateToView(rs.getTimestamp("FECHA").getTime()), rs.getInt("IDTRANS"), rs.getDouble("IMPORTE"), rs.getString("NAME"), rs.getString("PRODUCTO_NAME"), rs.getString("MARKET")});
				}		 
			}		
		} finally {
			SqlUtils.closeResulSet(rs);
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();
		}		
		return list_movAgrupProdAndPartnertsByMsisdn;
	}


	@Override
	public List<String> getListMsisdn(String customer_id) throws SQLException {
		Connection conn = dataSource.getConnection();
		NamedParameterStatement namedParameterStatement = null;		
		ResultSet rs = null;
		List<String> listaMsisdn = new ArrayList<String>();
		
		String sQuery = "SELECT C.MSISDN FROM IST.TB_CLIENTES C WHERE C.CUSTOMER_ID = :customer_id";		
		
		try {
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);
			namedParameterStatement.setString("customer_id", customer_id);
			rs = namedParameterStatement.executeQuery();		
			while(rs.next()) {				
				listaMsisdn.add(rs.getString("MSISDN"));			
			}
		} finally {
			SqlUtils.closeResulSet(rs);
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("customer_id:{}", new Object[] {customer_id});
		}
		return listaMsisdn;
	}


	
	
	/*public static void main(String[] args) throws JsonProcessingException {
	
	ObjectMapper oMapper = new ObjectMapper();
	MovAgrupProdAndPartnertsByMsisdn isterDto = new MovAgrupProdAndPartnertsByMsisdn();
	
	//lo siguiente convierte un objeto java em un Ma
	//Map<String, Object> map = oMapper.convertValue(isterDto, Map.class);
	//System.out.println(map);
	
	
	//lo siguiente convierte un objeto java en Json
	String jsonInString = oMapper.writeValueAsString(isterDto);		
	System.out.println(jsonInString);
	
}*/
	
	
}
