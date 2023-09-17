package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dto.Clock_in_DTO;

public class WorkingDay {
	public List<Date> getNormalDay(List<Clock_in_DTO> calendarList){
		List<Date> workingDayList = new ArrayList<>();
		
		for(Clock_in_DTO record: calendarList) {
			if(record.getStart_time() != null && record.getEnd_time() != null) {
				if(record.getWork_on_a_day() == 0) {
					workingDayList.add(record.getClock_in_date());
				}
			}
		}
		
		workingDayList = (ArrayList<Date>) workingDayList.stream().distinct().collect(Collectors.toList());
		
		return workingDayList;
	}
	
	public List<Date> getWork_on_a_day(List<Clock_in_DTO> calendarList){
		List<Date> workingDayList = new ArrayList<>();
		
		for(Clock_in_DTO record: calendarList) {
			if(record.getStart_time() != null && record.getEnd_time() != null) {
				if(record.getWork_on_a_day() == 1) {
					workingDayList.add(record.getClock_in_date());
				}
			}
		}
		
		workingDayList = (ArrayList<Date>) workingDayList.stream().distinct().collect(Collectors.toList());
		
		return workingDayList;
	}
}
