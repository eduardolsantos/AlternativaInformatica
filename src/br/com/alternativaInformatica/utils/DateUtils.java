package br.com.alternativaInformatica.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getDate(String format) {
		DateFormat tmpDateFormat = new SimpleDateFormat(format);
		Date tmpDate = new Date();
		
		return tmpDateFormat.format(tmpDate);
	}
	
	public static String getCurrentDate() {
		return getDate("dd/MM/yyyy");
	}
	
	public static String getCurrentDateSql() {
		return getDate("dd-MM-yyyy");
	}
	
	public static String getCurrentTime() {
		return getDate("HH:mm:ss");
	}
	
	public static String getCurrentYear() {
		return getDate("yyyy");
	}
}
