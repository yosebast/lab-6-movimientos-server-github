package org.test.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KyuwDateUtils {

	public static final String FORMAT_OFFICIAL_TIMESTAMP = "yyyy-MM-dd' 'HH:mm:ss";
	
	public static final String FORMAT_YYYY_MM_DD_T_HH_MI_SS_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.S";
	public static final String FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FORMAT_HH_MI_SS = "HH:mm:ss";
	public static final String FORMAT_DD_MM_YYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMAT_DDMMYY = "dd/MM/yy";
	public static final String FORMAT_DD_MM_YY = "dd-MM-yy";
	public static final String FORMAT_DDMMYYHHMMSS = "dd/MM/yy HH:mm:ss";
	public static final String FORMAT_ISTER_DDMMYYHHMMSS = "dd-MM-yy HH:mm:ss";
	
	
	private KyuwDateUtils(){
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * Reset hour, minute, second and millisecond fields from a Calendar
	 * @param cal Calendar
	 */
	public static void modificarCalendarToHoraMinima(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}
	
	/**
	 * Date to String
	 * @param date Date
	 * @param format Format
	 * @return Result
	 */
	public static String obtainStringFromDate(Date date, final String format, String defaultValue){
		String dateStr = defaultValue;
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			dateStr = sdf.format(date);
		}
		return dateStr;
	}
	
	/**
	 * String to Date
	 * @param dateStr String date
	 * @param format Format
	 * @param strictFormat Strict format
	 * @return Date
	 * @throws ParseException Parse exception
	 */
	public static Date obtainDateFromString(String dateStr, final String format, boolean strictFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(!strictFormat);
		return formatter.parse(dateStr);
	}
	
	public static Calendar obtainCalendarFromString(String dateStr, final String format, boolean strictFormat) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(!strictFormat);
		cal.setTime(formatter.parse(dateStr));
		return cal;
	}
	
	public static Calendar toCalendar(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}