package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import dao.Clock_in_DAO;
import dto.Clock_in_DTO;


public class GetCalendar {
	public List<Clock_in_DTO> put(String user_id){
		List<Clock_in_DTO> calendarList = new ArrayList<>();
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int start_Day = 1;
		int last_Day = getLastDay();
		
		Date start_date = changeDate(year, month, start_Day);
		Date last_date = changeDate(year, month, last_Day);
		
		Clock_in_DAO clock_in_DAO = new Clock_in_DAO();
		calendarList = clock_in_DAO.getCalendar(user_id, start_date, last_date);
		
		Comparator<Clock_in_DTO> comparator = Comparator.comparing(Clock_in_DTO::getClock_in_date).thenComparing(Clock_in_DTO::getClock_in_no);
		
		List<Clock_in_DTO> sortedCalendarList = calendarList.stream()
															.sorted(comparator)
															.collect(Collectors.toList());
		
		return sortedCalendarList;
		
	}
	
	public List<Clock_in_DTO> change(String user_id, int year, int month){
		List<Clock_in_DTO> calendarList = new ArrayList<>();
		
		int start_Day = 1;
		int last_Day = getLastDay();
		
		Date start_date = changeDate(year, month, start_Day);
		Date last_date = changeDate(year, month, last_Day);
		
		Clock_in_DAO clock_in_DAO = new Clock_in_DAO();
		calendarList = clock_in_DAO.getCalendar(user_id, start_date, last_date);
		
		Comparator<Clock_in_DTO> comparator = Comparator.comparing(Clock_in_DTO::getClock_in_date).thenComparing(Clock_in_DTO::getClock_in_no);
		
		List<Clock_in_DTO> sortedCalendarList = calendarList.stream()
															.sorted(comparator)
															.collect(Collectors.toList());
		
		return sortedCalendarList;
		
	}
	
	private int getLastDay() {
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		int thisMonth = calendar.get(Calendar.MONTH);
		
		calendar.set(thisYear, thisMonth + 1, 0);
		int thisMonthLastDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		return thisMonthLastDay;
	}
	
	private Date changeDate(int year, int month, int day) {
		String str_date = String.valueOf(year) + "-" + StringUtils.leftPad(String.valueOf(month), 2, "0") + "-" + StringUtils.leftPad(String.valueOf(day), 2, "0");
		Date sqlDate = Date.valueOf(str_date);
		return sqlDate;
	}
}
