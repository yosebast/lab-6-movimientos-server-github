package org.test.beans;

public class Ist_ProductosSubscribeByMsisdn {
	
	private String partnerName;
	private String productName;
	private String timestamp;
	private double importe;
	private String multisim;
	private String flagSubcripcion;
	
	
	public Ist_ProductosSubscribeByMsisdn() {
		super();
	}


	public Ist_ProductosSubscribeByMsisdn(String partnerName, String productName, String timestamp, double importe,
			String multisim, String flagSubcripcion) {
		super();
		this.partnerName = partnerName;
		this.productName = productName;
		this.timestamp = timestamp;
		this.importe = importe;
		this.multisim = multisim;
		this.flagSubcripcion = flagSubcripcion;
	}


	public String getPartnerName() {
		return partnerName;
	}


	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public double getImporte() {
		return importe;
	}


	public void setImporte(double importe) {
		this.importe = importe;
	}


	public String getMultisim() {
		return multisim;
	}


	public void setMultisim(String multisim) {
		this.multisim = multisim;
	}


	public String getFlagSubcripcion() {
		return flagSubcripcion;
	}


	public void setFlagSubcripcion(String flagSubcripcion) {
		this.flagSubcripcion = flagSubcripcion;
	}
	
	

}
