package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import dto.Clock_in_DTO;

public class CreateClock_in_DTO {
	public Clock_in_DTO get(String year, String month, String day, String user_id, String branch, String work_pattern,
			String work_category, String start_time, String end_time, String break_time, String work_remarks, String work_on_a_day) {
		
		String clock_in_date_branch = year + month + day + branch;
		Date clock_in_date = changeClock_in_date(year, month, day);
		Integer clock_in_no = Integer.parseInt(branch);
		Time sql_start_time = getSqlTime(start_time);
		Time sql_end_time = getSqlTime(end_time);
		Integer sql_break_time = Integer.parseInt(break_time);
		Integer over_time = calculate_over_time(work_pattern, year, month, day, start_time, end_time, sql_break_time);
		Integer sql_work_on_a_day = Integer.parseInt(work_on_a_day);
		
		Clock_in_DTO clock_in_DTO = new Clock_in_DTO(user_id, clock_in_date_branch, clock_in_date, clock_in_no,
				work_pattern, work_remarks, work_category, sql_start_time, sql_end_time, sql_break_time, over_time,
				sql_work_on_a_day);
		return clock_in_DTO;
	}
	
	public Date changeClock_in_date(String year, String month, String day) {
		String str_date = year + "-" + month + "-" + day;
		Date date = Date.valueOf(str_date);
		return date;
	}
	
	public Time getSqlTime(String time) {
		TimeChange timeChange = new TimeChange();
		String str_time = timeChange.sqlTime(time);
		Time sqlTime = Time.valueOf(str_time);
		return sqlTime;
	}
	
	public Integer calculate_over_time(String work_pattern, String year, String month, String day, String start_time, String end_time, Integer break_time) {
		
		Integer over_time = 0;
		
		if(work_pattern.equals("normal")) {
			TimeChange timeChange = new TimeChange();
			String str_start_time = timeChange.sqlTime(start_time);
			String str_end_time = timeChange.sqlTime(end_time);
			LocalDateTime local_start_time = timeChange.getLocalDateTime(year, month, day, str_start_time);
			LocalDateTime local_end_time = timeChange.getLocalDateTime(year, month, day, str_end_time);
			Integer work_time = (int) ChronoUnit.MINUTES.between(local_start_time, local_end_time);
			over_time = work_time - 480 - break_time;
		}
		return over_time;
	}
}
