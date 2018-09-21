package org.test.beans;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PruebaJoda {
public static void main(String[] args) {
	// Crear un formatter con una representación específica
	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MMMM-yyyy");
			
	// Obtener un formatter localizado
	DateTimeFormatter americanFmt = fmt.withLocale(Locale.US);
			
	// Obtener una fecha a partir de su representación
	DateTime dt = fmt.parseDateTime("25-junio-2010");
			
	// Escribir una fecha con el formato especificado
	System.out.println(dt);
	System.out.println(fmt.print(dt)); // escribe "25-junio-2010"
	System.out.println(americanFmt.print(dt)); // escribe "25-June-2010"

	// Métodos de acceso directo
	System.out.println(dt.toString("dd-MMMM-yyyy")); // escribe "25-junio-2010"
	System.out.println(dt.toString("dd-MMMM-yyyy", Locale.US)); // escribe "25-June-2010"
	
	DateTime dte = new DateTime();
	System.out.println("esta es por defecto "+dte);
	DateTimeFormatter fmter = DateTimeFormat.forPattern("dd/MMMM/yyyy");
	System.out.println(fmter.print(dte));
	
	
	//probando la cronologia
	// Especificar cronología
	DateTime date = new DateTime(BuddhistChronology.getInstance());
	System.out.println("esta es con cronologia BUD "+date);
	DateTime date1 = new DateTime(ISOChronology.getInstance());
	System.out.println("esta es con cronologia ISO "+date1);	
	DateTime date2 = new DateTime(GregorianChronology.getInstance());
	System.out.println("esta es con cronologia greg "+date2);
	DateTime date3 = new DateTime(JulianChronology.getInstance());
	System.out.println("esta es con cronologia juli"+date3);
	
	final LocalDate today = new LocalDate(ISOChronology.getInstance());
	
	System.out.println("today is "+today);
	//2018-07-20
	//sacando la hora y fecha de resultado
	LocalDateTime date5 = new LocalDateTime(ISOChronology.getInstance());
	System.out.println("today5 "+ date5);
	date5.getHourOfDay();
	date5.getMinuteOfHour();
	date5.getSecondOfMinute();
	
	System.out.println("Hora "+ date5.getHourOfDay() + " minuto "+ date5.getMinuteOfHour()+ " segundo "+ date5.getSecondOfMinute());
	
}
}
