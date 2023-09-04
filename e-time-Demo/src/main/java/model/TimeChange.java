package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeChange {
	public static String sqlTime(String time) {
		String changeTime = time.substring(0,2) + ":" + time.substring(2, 4) + ":00";
		return changeTime;
	}
	
	public static String localTime(String year, String month, String day, String time) {
		String localDate = year + "/" + month + "/" + day + " " + time;
		
		return localDate;
	}
	
	public static LocalDateTime getLocalDateTime(String year, String month, String day, String time) {
		String localDate = localTime(year, month, day, time);
		
		DateTimeFormatter dtFt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime datePar = LocalDateTime.parse(localDate, dtFt);
		
		return datePar;
	}
}
