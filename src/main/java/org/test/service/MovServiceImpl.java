package org.test.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.beans.MovAgrupProdAndPartnertsByMsisdn;
import org.test.dao.MovDao;
import org.test.exception.MovException;
import org.test.model.IsterDTO;
import org.test.util.date.KyuwDateUtils;

@Service
public class MovServiceImpl implements MovService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovServiceImpl.class);
	
	private static final String MSISDN_NO_EXISTE = "El msisdn no existe para el cistomer_id";
	
	@Autowired
	MovDao movDao;
	
	public IsterDTO getAllMov() throws MovException {
		IsterDTO isterDto = new IsterDTO();
		try {
			isterDto.setResponseData(movDao.getAllMov());
		} catch (SQLException e) {
			throw new MovException(e.getMessage());
		}
	
		return isterDto;
	}

	@Override
	public IsterDTO getMovFromProductsSubscribeByMsisdn(String msisdn, String initdate, String enddate) throws MovException {		
		
		final IsterDTO isterDto = toIsterDTO(msisdn, initdate, enddate);
		
		try {
			//add formatter to date			
			Date init_date = KyuwDateUtils.obtainDateFromString(isterDto.getInit_date(), KyuwDateUtils.FORMAT_DD_MM_YY, true);
			Date end_date = KyuwDateUtils.obtainDateFromString(isterDto.getEnd_date(), KyuwDateUtils.FORMAT_DD_MM_YY, true);			
			isterDto.setResponseData(movDao.getMovFromProductsSubscribeByMsisdn(isterDto.getMsisdn(), init_date, end_date));
		} catch (SQLException | ParseException e) {
			LOGGER.error("MovServiceImpl - getMovFromProductsSubscribeByMsisdn:{}", e.getMessage());
			throw new MovException(e.getMessage());			
		}
		return isterDto;
	}

	private IsterDTO toIsterDTO(String msisdn, String initdate, String enddate) {
		IsterDTO isterDTO = new IsterDTO();
		isterDTO.setMsisdn(msisdn);
		isterDTO.setInit_date(initdate);
		isterDTO.setEnd_date(enddate);
		return isterDTO;
	}	
	
	@Override
	public List<MovAgrupProdAndPartnertsByMsisdn> getCargosAndSuscripcionesTercerosByMsisdnAndDate(String customer_id, String msisdn, String initdate, String enddate) throws MovException {		
		try {
			//add formatter to date			
			Date init_date = KyuwDateUtils.obtainDateFromString(initdate, KyuwDateUtils.FORMAT_DD_MM_YY, true);
			Date end_date = KyuwDateUtils.obtainDateFromString(enddate, KyuwDateUtils.FORMAT_DD_MM_YY, true);			
			return movDao.getCargosAndSuscripcionesTercerosByMsisdnAndDate(msisdn, init_date, end_date);
		} catch (SQLException | ParseException e) {
			LOGGER.error("MovServiceImpl - getCargosAndSuscripcionesTercerosByMsisdnAndDate:{}", e.getMessage());
			throw new MovException(e.getMessage());			
		}	
	}

	@Override
	public void validaExisteMsisdn(String customer_id, String msisdn) throws MovException {
		List<String> listMsisdn;
		boolean existeMsisdn = false;
		try {
			listMsisdn = movDao.getListMsisdn(customer_id);
			// verificamos si existe el msisdn que hemos recibido
			if (listMsisdn != null && !listMsisdn.isEmpty()) {
				for (String smsisdn : listMsisdn) {
					if (smsisdn.equalsIgnoreCase(msisdn)) {
						existeMsisdn = true;
						break;
					}
				}
			}

			if (!existeMsisdn) {
				new Exception(MSISDN_NO_EXISTE);
			}

		} catch (Exception e) {
			LOGGER.error("MovServiceImpl - validaExisteMsisdn:{}", e.getMessage());
			throw new MovException(e.getMessage());
		}
	}
	
/*	private IsterDTO createdMessageError(String messageException) {		
		IsterDTO isterDTO = new IsterDTO();
		isterDTO.setHasError(true);
		isterDTO.setMessage(messageException);
		return isterDTO;
	}*/
}
