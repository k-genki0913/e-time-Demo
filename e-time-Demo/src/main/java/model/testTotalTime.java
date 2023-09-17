package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dto.Clock_in_DTO;

public class testTotalTime {
	public static void main(String[] args) {
		TimeChange timeChange = new TimeChange();
		int totalTime = 0;
		ArrayList<Date> dateList = new ArrayList<>();
		List<Clock_in_DTO> calendarList = new GetCalendar().put("9999999999");
		for(Clock_in_DTO record: calendarList) {
			System.out.println(record.getClock_in_date());
			if(record.getStart_time() != null && record.getEnd_time() != null) {
				String str_start_time = record.getStart_time().toString();
				String str_end_time = record.getEnd_time().toString();
				System.out.println(record.getClock_in_date());
				System.out.println(str_start_time);
				System.out.println(str_end_time);
				String year = record.getClock_in_date().toString().substring(0, 4);
				String month = record.getClock_in_date().toString().substring(5, 7);
				String day = record.getClock_in_date().toString().substring(8, 10);
				LocalDateTime local_start_time = timeChange.getLocalDateTime(year, month, day, str_start_time);
				LocalDateTime local_end_time = timeChange.getLocalDateTime(year, month, day, str_end_time);
				Integer work_time = (int) ChronoUnit.MINUTES.between(local_start_time, local_end_time);
				totalTime += work_time;
				System.out.println(totalTime);
				dateList.add(record.getClock_in_date());
			}
		}
		ArrayList<Date> withoutDateList = (ArrayList<Date>) dateList.stream()
											.distinct()
											.collect(Collectors.toList());
		System.out.println("日にち数：" + withoutDateList.size());
		int totalWorkTime = totalTime - (60 * withoutDateList.size());
		int overtime = totalTime - (480 * withoutDateList.size());
		
		System.out.println("総業務時間：" + totalWorkTime);
		System.out.println("残業時間：" + overtime);
	}
}
