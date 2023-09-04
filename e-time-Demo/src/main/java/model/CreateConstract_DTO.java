package model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import dto.Constract_DTO;

public class CreateConstract_DTO {
	public Constract_DTO put(String workplace, String start_time, String end_time, String break_time) {
		Integer int_break_time = Integer.parseInt(break_time);
		Time sql_start_time = Time.valueOf(TimeChange.sqlTime(start_time));
		Time sql_end_time = Time.valueOf(TimeChange.sqlTime(end_time));
		Integer work_time = calculate_worktime(start_time, end_time, int_break_time);
		
		Constract_DTO constract_DTO = new Constract_DTO(workplace, sql_start_time, sql_end_time, int_break_time, work_time, 1);
		
		return constract_DTO;
		
	}
	
	private Integer calculate_worktime(String start_time, String end_time, Integer break_time) {
		String str_start_time = TimeChange.sqlTime(start_time);
		String str_end_time = TimeChange.sqlTime(end_time);
		LocalDateTime local_start_time = TimeChange.getLocalDateTime("2023", "10", "10", str_start_time);
		LocalDateTime local_end_time = TimeChange.getLocalDateTime("2023", "10", "10", str_end_time);
		Integer work_time = (int) ChronoUnit.MINUTES.between(local_start_time, local_end_time);
		work_time -= break_time;
		return work_time;
	}
}
