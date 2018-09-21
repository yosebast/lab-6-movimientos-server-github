package org.test.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.test.beans.ResponseError;

public class IsterDTO {	
	public static final String MSISDN = "msisdn";
	
	private boolean hasError = false;
	private ResponseError error;	
	private String msisdn;
	private String init_date;
	private String end_date;
	private String PartnerName;
	private Map<String, Object> actionObjects;	
	private Collection<?> responseData;	
	private String uuid;
	
	
	
	public IsterDTO() {
		this.actionObjects = new HashMap<>();
	}

	/**
	 * Get the data from the collection and remove
	 * 
	 * @param key
	 * @return
	 */
	public Object getActionObjectAndRemove(final String key) {
		Object object = null;
		if (this.actionObjects.containsKey(key)) {
			object = this.actionObjects.remove(key);
		}
		return object;
	}
	
	/**
	 * Get the data from the collection
	 * 
	 * @param key
	 * @return
	 */
	public Object getActionObject(final String key) {
		Object object = null;
		if (this.actionObjects.containsKey(key)) {
			object = this.actionObjects.get(key);
		}
		return object;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}	

	public String getInit_date() {
		return init_date;
	}

	public void setInit_date(String init_date) {
		this.init_date = init_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getPartnerName() {
		return PartnerName;
	}

	public void setPartnerName(String partnerName) {
		PartnerName = partnerName;
	}

	public Map<String, Object> getActionObjects() {
		return actionObjects;
	}

	public void setActionObjects(Map<String, Object> actionObjects) {
		this.actionObjects = actionObjects;
	}	

	public Collection<?> getResponseData() {
		return responseData;
	}

	public void setResponseData(Collection<?> responseData) {
		this.responseData = responseData;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public ResponseError getError() {
		return error;
	}

	public void setError(ResponseError error) {
		this.error = error;
	}	
}
