package model;

public class Cnvrt_DayOfWeek {
	public String putBack(int x) {
		String day_of_week = "";
		switch(x) {
		case 1:
			day_of_week = "日曜日";
			break;
		case 2:
			day_of_week = "月曜日";
			break;
		case 3:
			day_of_week = "火曜日";
			break;
		case 4:
			day_of_week = "水曜日";
			break;
		case 5:
			day_of_week = "木曜日";
			break;
		case 6:
			day_of_week = "金曜日";
			break;
		case 7:
			day_of_week = "土曜日";
			break;
		}
		return day_of_week;
	}
}
