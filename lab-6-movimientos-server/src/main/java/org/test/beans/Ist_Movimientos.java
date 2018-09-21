package org.test.beans;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("Ist_Movimientos")
public class Ist_Movimientos {
	
	private Integer idtrans;
	private Integer idprod;
	private Integer idcli;
	private Long timestamp;
	private String tipo_mov;
	private double importe;
	private String multisim;
	private String status_mov;
	private String idtrans_tercero;
	private String market;
	
	
	public Ist_Movimientos() {
		super();
	}
	public Ist_Movimientos(Integer idtrans, Integer idprod, Integer idcli, Long timestamp, String tipo_mov,
			double importe, String multisim, String status_mov, String idtrans_tercero, String market) {
		super();
		this.idtrans = idtrans;
		this.idprod = idprod;
		this.idcli = idcli;
		this.timestamp = timestamp;
		this.tipo_mov = tipo_mov;
		this.importe = importe;
		this.multisim = multisim;
		this.status_mov = status_mov;
		this.idtrans_tercero = idtrans_tercero;
		this.market = market;
	}
	public Integer getIdtrans() {
		return idtrans;
	}
	public void setIdtrans(Integer idtrans) {
		this.idtrans = idtrans;
	}
	public Integer getIdprod() {
		return idprod;
	}
	public void setIdprod(Integer idprod) {
		this.idprod = idprod;
	}
	public Integer getIdcli() {
		return idcli;
	}
	public void setIdcli(Integer idcli) {
		this.idcli = idcli;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getTipo_mov() {
		return tipo_mov;
	}
	public void setTipo_mov(String tipo_mov) {
		this.tipo_mov = tipo_mov;
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
	public String getStatus_mov() {
		return status_mov;
	}
	public void setStatus_mov(String status_mov) {
		this.status_mov = status_mov;
	}
	public String getIdtrans_tercero() {
		return idtrans_tercero;
	}
	public void setIdtrans_tercero(String idtrans_tercero) {
		this.idtrans_tercero = idtrans_tercero;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}	
}
