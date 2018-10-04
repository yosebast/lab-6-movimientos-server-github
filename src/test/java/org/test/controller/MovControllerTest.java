package org.test.controller;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.beans.Ist_ProductosSubscribeByMsisdn;
import org.test.exception.MovException;
import org.test.model.IsterDTO;
import org.test.service.MovService;
import org.test.util.date.DateTimeUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(MockitoJUnitRunner.class)
public class MovControllerTest {

	@Mock
	MovService movService;
	
	@InjectMocks
	Mov_Controller mov_Controller;
	
	String msisdn;
	String initdate;
	String enddate;
	
	@Before
	public void setUp() {
		msisdn = "677";
		initdate = "01-07-18";
		enddate = "11-07-18";
	}
	
	
	@Test	
	public void testGetMovFromProductsSubscribeByMsisdn() throws MovException, JsonParseException, JsonMappingException, IOException {		
		when(movService.getMovFromProductsSubscribeByMsisdn(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(getIsterDto());
		Assert.assertNotNull(this.mov_Controller.getMovFromProductsSubscribeByMsisdn(msisdn, initdate, enddate));
		System.out.println("prueba test");	
	}

	private IsterDTO getIsterDto() {
		IsterDTO isterDTO = new IsterDTO();
		isterDTO.setMsisdn(msisdn);
		isterDTO.setInit_date(initdate);
		isterDTO.setEnd_date(enddate);
		isterDTO.setResponseData(getCollection());
		return isterDTO;
	}


	private Collection<?> getCollection() {	
		List<Ist_ProductosSubscribeByMsisdn> list_istProByMsisdn = new ArrayList<Ist_ProductosSubscribeByMsisdn>();	
		Ist_ProductosSubscribeByMsisdn istProByMsisdn = new Ist_ProductosSubscribeByMsisdn();
		istProByMsisdn.setFlagSubcripcion("N");
		istProByMsisdn.setImporte(24.9);
		istProByMsisdn.setMultisim(null);
		istProByMsisdn.setPartnerName("Partner77");
		istProByMsisdn.setProductName("NAME_1");		
		istProByMsisdn.setTimestamp(DateTimeUtil.getInstance().formatDateToView(1531231510000L));				
		list_istProByMsisdn.add(istProByMsisdn);
		return list_istProByMsisdn;
	}
}
