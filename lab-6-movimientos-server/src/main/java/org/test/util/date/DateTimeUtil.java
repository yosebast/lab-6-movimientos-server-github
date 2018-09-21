package org.test.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

//import org.apache.poi.ss.usermodel.DateUtil;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {	

	private static final String DEFAULT_DATE_TIME_ZONE = "Etc/UTC";
	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtil.class);
	private static final int DEFAULT_SPOT_LAG = 0;
	private static DateTimeUtil instance;
	private final Chronology chronology;
	//private final HolidayCalendar holidayCalendar;
	//private final String languageConfig;

	private static final String EXPORT_DATE_FORMAT = "dd/MM/yyyy";

	private static final String EXPORT_EXCEL_DATE_FORMAT = "MM/dd/yyyy";

	private static final String TYPHOON_DATE_FORMAT = "yyyy-MM-dd";

	private static final String EXPORT_DATE_REST_FORMAT = "ddMMyyyy";

	private static final String EXPORT_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

	private static final String EXPORT_DATETIMEMILLIS_FORMAT = "dd/MM/yyyy HH:mm:ss.SSS";

	private static final String EXPORT_DATE_BUCKET_FORMAT = "dd/MM/yyyy";

	private static final String EXPORT_TYPHOON_FORMAT = "yyyy-MM-ddTHH:mm:ss";

	private static final String TENOR_FORMAT = "MMM yy";

	private static final String FORWARD_DATE_FORMAT = "yyyyMMdd";

	private static final String MDRS_DATE_FORMAT = "yyyyMMdd";

	private static final String PAST_DATE_FORMAT = "yyyyMMddHHmmss";
	
	private static final String EXPORT_DATE_FORMAT_IST = "dd/MM/yy HH:mm:ss";
	
	//PropertiesHandler properties;
	
	//days convetion
	private static final int ON = 0;
	private static final int TN = 1;
	private static final int SPOT = 2;
	private static final int WEEK = 7;	
	
	private DateTimeUtil() {		
		this.chronology = ISOChronology.getInstance();		
	}
	
	public static DateTimeUtil getInstance() {
		if (instance == null) {
			instance = new DateTimeUtil();
		}
		return instance;
	}	
	
	/**
	 *
	 * @return
	 */
	public LocalDate today() {
		final LocalDate today = new LocalDate(this.chronology);
		if(LOGGER.isTraceEnabled()){
			LOGGER.trace("Today: {}", today);
		}
		return today;
	}	
	
	public String formatDateTimeToView(final long timeInMillis) {
		final DateTimeFormatter fmt = this.createDateTimeFormatter(EXPORT_DATETIME_FORMAT);
		return fmt.print(timeInMillis);
	}
	
	public String formatDateToView(final long timeInMillis) {
		final DateTimeFormatter fmt = this.createDateTimeFormatter(EXPORT_DATE_FORMAT_IST);		
		return fmt.print(timeInMillis);
	}
	
	public String formatDateToTyphoonMessage(final long timeInMillis) {
		final DateTimeFormatter fmt = this.createDateTimeFormatter(EXPORT_TYPHOON_FORMAT);
		return fmt.print(timeInMillis);
	}
	
	public DateTimeFormatter createDateTimeFormatter(final String pattern) {
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
		return formatter.withChronology(this.chronology);
	}
	
	public long dateStringFromTyphoonToLong(final String stringDate) {
		final DateTimeFormatter formatter = this.createDateTimeFormatter(TYPHOON_DATE_FORMAT);
		final LocalDateTime dt = formatter.parseLocalDateTime(stringDate);
		return dt.toDateTime(DateTimeZone.forID(DEFAULT_DATE_TIME_ZONE)).getMillis();
	}
	
	/*public Date getTyphoonDate(final long date){
		final LocalDate localDate = this.getLocalDate(date);
		return localDate.toDate();
	}*/	
	
	/**
	 * Convert a long date (dd/MM/yyyy) into Date.
	 *
	 * @param dateLong
	 * 			the date to convert
	 * @return
	 * 			the date in Date format
	 */
	public Date convertLongToDate(final Long dateLong) {
		try {
			return new SimpleDateFormat(EXPORT_DATE_FORMAT_IST).parse(DateTimeUtil.getInstance().formatDateToView(dateLong));
		} catch (final ParseException e) {
			LOGGER.warn("Error converting date: {} - {}", dateLong, e.getStackTrace());
		}

		return null;
	}

	/**
	 * Convert a date into string (dd/MM/yyyy).
	 *
	 * @param date
	 * 			the date to convert
	 * @return
	 * 			the date in dd/MM/yyyy format
	 */
	public String convertDateToString(final Date date) {
		return new SimpleDateFormat(EXPORT_DATE_FORMAT).format(date);
	}

}
