package org.test.util.date;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.test.exception.ValidationException;

public class ValidatorUtils {
	
	private static final String MANDATORY = "field is mandatory";
	private static final String FIELD_VALID = "is an invalid value for";
	private static final String FIELD_LENGTH = "field must have a size of";	
	
	/*los siguientes parametros es para el proyecto  los que sirven*/
	
	//private static final String ERROR_MSISDN = "code:115, message:Error: el parámetro msisdn es obligatorio";
	
	
	private ValidatorUtils(){
		throw new IllegalAccessError("Utility class");
	}
	
	public static void validateNotEmpty(final String fieldName, final String value) throws ValidationException {
		if (StringUtils.isEmpty(value)) {
			throw new ValidationException(createValidationExceptionMessageForMandatoryField(fieldName));
		}
	}	

	public static void validateCollectionNotEmpty(final String fieldName, @SuppressWarnings("rawtypes") final Collection value) throws ValidationException {
		if (value==null || value.isEmpty()) {
			throw new ValidationException(fieldName + " collection can't be empty");
		}
	}
	
	public static final void validaIfMsisdnIsCorrect(String field, String fieldName, final boolean mandatory) throws ValidationException {
		
		if(StringUtils.isEmpty(fieldName) && mandatory) {
			throw new ValidationException(createValidationExceptionMessageForMandatoryField(field));
		}else if(!StringUtils.isEmpty(fieldName)) {			
				//validaremos si el msisdn tiene el formato de orange correctamente
			try {
				Integer.parseInt(fieldName);
			} catch (Exception e) {
				throw new ValidationException(createValidationExceptionMessageForInvalidValue(field, fieldName));
			}		
		}		
	}
	
	public static void validateStringDate(final String fieldName, final String dateStr, final String format, final boolean mandatory) throws ValidationException {
		if (StringUtils.isEmpty(dateStr) && mandatory) {
			throw new ValidationException(createValidationExceptionMessageForMandatoryField(fieldName));
		} else if (!StringUtils.isEmpty(dateStr)) {
			try {
				KyuwDateUtils.obtainDateFromString(dateStr, format, true);
			} catch (final Exception e) {
				throw new ValidationException(createValidationExceptionMessageForInvalidValue(dateStr, fieldName));
			}
		}
	}	

	private static String createValidationExceptionMessageForMandatoryField(String fieldName) {
		StringBuilder sb = new StringBuilder();
		return sb.append(fieldName).append(StringConstants.SPACE).append(MANDATORY).toString();
	}
	
	private static String createValidationExceptionMessageForInvalidValue(String field, String fieldName) {
		StringBuilder sb = new StringBuilder();
		return sb.append(fieldName).append(StringConstants.SPACE).append(FIELD_VALID).append(StringConstants.SPACE).append(field).toString();		
	}
	
	public static void validateExactLength(final String fieldName, final String value, final int exactLength) throws ValidationException {
		if (StringUtils.isEmpty(value) || value.length()!=exactLength) {
			throw new ValidationException(createValidationExceptionMessageForExactLengthField(fieldName, exactLength));
		}
	}
	
	private static String createValidationExceptionMessageForExactLengthField(final String fieldName, final int exactLength) {
		StringBuilder sb = new StringBuilder();
		sb.append(fieldName).append(StringConstants.SPACE).append(FIELD_LENGTH).append(StringConstants.SPACE).append(exactLength);
		return sb.toString();
	}	
}
	
	