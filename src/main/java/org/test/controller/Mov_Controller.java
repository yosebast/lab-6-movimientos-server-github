package org.test.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.test.beans.MovAgrupProdAndPartnertsByMsisdn;
import org.test.beans.ResponseError;
import org.test.exception.MovException;
import org.test.exception.ValidationException;
import org.test.model.IsterDTO;
import org.test.service.MovService;
import org.test.util.date.KyuwDateUtils;
import org.test.util.date.StringConstants;
import org.test.util.date.ValidatorUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Mov_Controller {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Mov_Controller.class);
	
	
	
	@Autowired
	MovService movService;	
	
	/*@PostMapping("allMov")
	public IsterDTO getAllMovimientos(IsterDTO isterDto) {*/
	
	@GetMapping("allMov")
	public IsterDTO getAllMovimientos() throws JsonParseException, JsonMappingException, IOException {
		
		IsterDTO obj_isterDto = null;
		try {
			obj_isterDto = movService.getAllMov();
		} catch (MovException e) {
			obj_isterDto = createdMessageError(e.getMessage());
		}
		
		return obj_isterDto;
	}	
	
	@GetMapping("mov/{msisdn}/{initdate}/{enddate}")
	public IsterDTO getMovFromProductsSubscribeByMsisdn(@PathVariable("msisdn") String msisdn, @PathVariable("initdate") String initdate, @PathVariable("enddate") String enddate) throws JsonParseException, JsonMappingException, IOException {
			LOGGER.debug("Execute getMovFromProductsSubscribeByMsisdn [msisdn={}, initdate={}, enddate={}]", new Object[] {msisdn, initdate, enddate});
		IsterDTO obj_isterDto = null;
		try {
			//validamos los datos de entrada
			ValidatorUtils.validaIfMsisdnIsCorrect(IsterDTO.MSISDN, msisdn, true);
			ValidatorUtils.validateStringDate(IsterDTO.MSISDN, initdate, KyuwDateUtils.FORMAT_DD_MM_YY, true);
			ValidatorUtils.validateStringDate(IsterDTO.MSISDN, enddate, KyuwDateUtils.FORMAT_DD_MM_YY, true);			
			obj_isterDto = movService.getMovFromProductsSubscribeByMsisdn(msisdn, initdate, enddate);
			
		} catch (MovException | ValidationException e) {
			obj_isterDto = createdMessageError(e.getMessage());
		}
		return obj_isterDto;
	}
	
	private IsterDTO createdMessageError(String messageException) throws JsonParseException, JsonMappingException, IOException {	
		IsterDTO isterDTO = new IsterDTO();
		isterDTO.setHasError(true);
		ObjectMapper mapper = new ObjectMapper();
		String mensaje = "{\"code\":115, \"message\":\"Error el parámetro msisdn es obligatorio\"}";
		ResponseError errorMessage = mapper.readValue(mensaje, ResponseError.class);		
		isterDTO.setError(errorMessage);
		return isterDTO;
	}
	
	@GetMapping("movimientos/{Customer_id}/{MSISDN}/{Fecha_inicial}/{Fecha_final}")
	public List<MovAgrupProdAndPartnertsByMsisdn> getCargosAndSuscripcionesTercerosByMsisdnAndDate(@PathVariable("Customer_id") String customer_id, @PathVariable("MSISDN") String msisdn, @PathVariable("Fecha_inicial") String initdate, @PathVariable("Fecha_final") String enddate) {
			LOGGER.debug("Execute getCargosAndSuscripcionesTercerosByMsisdnAndDate [customer_id={}, msisdn={}, initdate={}, enddate={}]", new Object[] {customer_id, msisdn, initdate, enddate});
			List<MovAgrupProdAndPartnertsByMsisdn>  movAgrupProdAndPartnertsByMsisdn = null;
			ResponseError responseError = null;
		try {
			//validamos los datos de entrada
			ValidatorUtils.validaIfMsisdnIsCorrect(StringConstants.MSISDN, msisdn, true);
			ValidatorUtils.validateStringDate(StringConstants.MSISDN, initdate, KyuwDateUtils.FORMAT_DD_MM_YY, true);
			ValidatorUtils.validateStringDate(StringConstants.MSISDN, enddate, KyuwDateUtils.FORMAT_DD_MM_YY, true);			
			//buscamos si existe el msisdn informado para el customer_id informado		
			 movService.validaExisteMsisdn(customer_id, msisdn);			
			 movAgrupProdAndPartnertsByMsisdn = movService.getCargosAndSuscripcionesTercerosByMsisdnAndDate(customer_id, msisdn, initdate, enddate);
			
		} catch (MovException | ValidationException e) {
		
				//return responseError = createdMessageResponseError(e.getMessage());
			
		}
		return movAgrupProdAndPartnertsByMsisdn;
	}

	/*private ResponseError createdMessageResponseError(String message) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String mensaje = "{\"code\":115, \"message\":\"Error el parámetro msisdn es obligatorio\"}";
		ResponseError errorMessage = mapper.readValue(mensaje, ResponseError.class);		
		return errorMessage;
	}*/
}
