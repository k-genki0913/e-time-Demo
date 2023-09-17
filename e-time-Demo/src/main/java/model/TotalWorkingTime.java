package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import dto.Clock_in_DTO;

public class TotalWorkingTime {
	public Integer execute(List<Clock_in_DTO> calendarList, Integer workingDay) {
		TimeChange timeChange = new TimeChange();
		Integer totalTime = 0;
		
		for(Clock_in_DTO record: calendarList) {
			if(record.getStart_time() != null && record.getEnd_time() != null) {
				if(record.getWork_on_a_day() == 0) {
					String str_start_time = record.getStart_time().toString();
					String str_end_time = record.getEnd_time().toString();
					String year = record.getClock_in_date().toString().substring(0, 4);
					String month = record.getClock_in_date().toString().substring(5, 7);
					String day = record.getClock_in_date().toString().substring(8, 10);
					LocalDateTime local_start_time = timeChange.getLocalDateTime(year, month, day, str_start_time);
					LocalDateTime local_end_time = timeChange.getLocalDateTime(year, month, day, str_end_time);
					Integer work_time = (int) ChronoUnit.MINUTES.between(local_start_time, local_end_time);
					totalTime += work_time;
				}
			}
		}
		Integer totalWorkTime = totalTime - ( 60 * workingDay);
		return totalWorkTime;
	}
	
	public Integer work_on_a_day_execute(List<Clock_in_DTO> calendarList, Integer workingDay) {
		TimeChange timeChange = new TimeChange();
		Integer totalTime = 0;
		
		for(Clock_in_DTO record: calendarList) {
			if(record.getStart_time() != null && record.getEnd_time() != null) {
				if(record.getWork_on_a_day() == 1) {
					String str_start_time = record.getStart_time().toString();
					String str_end_time = record.getEnd_time().toString();
					String year = record.getClock_in_date().toString().substring(0, 4);
					String month = record.getClock_in_date().toString().substring(5, 7);
					String day = record.getClock_in_date().toString().substring(8, 10);
					LocalDateTime local_start_time = timeChange.getLocalDateTime(year, month, day, str_start_time);
					LocalDateTime local_end_time = timeChange.getLocalDateTime(year, month, day, str_end_time);
					Integer work_time = (int) ChronoUnit.MINUTES.between(local_start_time, local_end_time);
					totalTime += work_time;
				}
			}
		}
		Integer totalWorkTime = totalTime - ( 60 * workingDay);
		return totalWorkTime;
	}
}
