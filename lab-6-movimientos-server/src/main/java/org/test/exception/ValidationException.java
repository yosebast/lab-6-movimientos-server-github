package org.test.exception;

import org.test.util.date.StringConstants;

public class ValidationException extends Exception {	
	
	private static final long serialVersionUID = 1L;
	
	private static final String MSG = "Validation error";
	
	
	public ValidationException(String msg, Throwable t) {
		super(new StringBuilder().append(MSG).append(StringConstants.COLON).append(StringConstants.SPACE).append(msg).toString(), t);
	}	
	
	public ValidationException(String msg) {
		super(new StringBuilder().append(MSG).append(StringConstants.COLON).append(StringConstants.SPACE).append(msg).toString());
	}
	
	public ValidationException(Throwable t) {
		super(t);
	}

}
