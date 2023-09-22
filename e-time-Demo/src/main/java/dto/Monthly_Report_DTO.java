package dto;

import java.sql.Date;

import model.User_id_change_Name;

public class Monthly_Report_DTO {
	private Integer monthly_report_id;
	private Integer target_year;
	private Integer target_month;
	private String user_id;
	private String name;
	private Integer workingDay;
	private Double total_working_hours;
	private Double total_overtime_hours;
	private Integer work_on_a_day;
	private Double total_work_on_a_day_Hour;
	private Double total_work_on_a_day_OverTime;
	private Date updated_date;
	private String updated_user;
	
	public Monthly_Report_DTO() {}
	
	public Monthly_Report_DTO(Integer monthly_report_id, Integer year, Integer month, String user_id, Integer workingDay,
			Double total_working_hours, Double total_overtime_hours, Integer work_on_a_day, Double total_work_on_a_day_Hour,
			Double total_work_on_a_day_OverTime, Date updated_date, String updated_user) {
		this.monthly_report_id = monthly_report_id;
		this.target_year = year;
		this.target_month = month;
		this.user_id = user_id;
		this.workingDay = workingDay;
		this.total_working_hours = total_working_hours;
		this.total_overtime_hours = total_overtime_hours;
		this.work_on_a_day = work_on_a_day;
		this.total_work_on_a_day_Hour = total_work_on_a_day_Hour;
		this.total_work_on_a_day_OverTime = total_work_on_a_day_OverTime;
		this.updated_date = updated_date;
		this.updated_user = updated_user;
	}
	
	public Monthly_Report_DTO(Integer year, Integer month, String user_id, Integer workingDay,
			Double total_working_hours, Double total_overtime_hours, Integer work_on_a_day, Double total_work_on_a_day_Hour,
			Double total_work_on_a_day_OverTime, Date updated_date, String updated_user) {
		this.target_year = year;
		this.target_month = month;
		this.user_id = user_id;
		this.workingDay = workingDay;
		this.total_working_hours = total_working_hours;
		this.total_overtime_hours = total_overtime_hours;
		this.work_on_a_day = work_on_a_day;
		this.total_work_on_a_day_Hour = total_work_on_a_day_Hour;
		this.total_work_on_a_day_OverTime = total_work_on_a_day_OverTime;
		this.updated_date = updated_date;
		this.updated_user = updated_user;
	}
	
	public Integer getMonthlyReport_id() {
		return this.monthly_report_id;
	}
	
	public void setMonthlyReport_id(int monthly_report_id) {
		this.monthly_report_id = monthly_report_id;
	}
	
	public Integer getTarget_year() {
		return this.target_year;
	}
	
	public void setTarget_year(int year) {
		this.target_year = year;
	}
	
	public Integer getTarget_month() {
		return this.target_month;
	}
	
	public void setTarget_month(int month) {
		this.target_month = month;
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getName() {
		User_id_change_Name obj = new User_id_change_Name();
		this.name = obj.execute(this.user_id);
		return this.name;
	}
	
	public Integer getWorkingDay() {
		return this.workingDay;
	}
	
	public void setWorkingDay(int workingDay) {
		this.workingDay = workingDay;
	}
	
	public Double getTotal_working_hours() {
		return this.total_working_hours;
	}
	
	public void setTotal_working_hours(double total_working_hours) {
		this.total_working_hours = total_working_hours;
	}
	
	public double getTotal_overtime_hours() {
		return this.total_overtime_hours;
	}
	
	public void setTotal_overtime_hours(double total_overtime_hours) {
		this.total_overtime_hours = total_overtime_hours;
	}
	
	public Integer getWork_on_a_day() {
		return this.work_on_a_day;
	}
	
	public void setWork_on_a_day(Integer work_on_a_day) {
		this.work_on_a_day = work_on_a_day;
	}
	
	public Double getTotal_work_on_a_day_Hour() {
		return this.total_work_on_a_day_Hour;
	}
	
	public void setTotal_work_on_a_day_Hour(double total_work_on_a_day_Hour) {
		this.total_work_on_a_day_Hour = total_work_on_a_day_Hour;
	}
	
	public Double getTotal_work_on_a_day_OverTime() {
		return this.total_work_on_a_day_OverTime;
	}
	
	public void setTotal_work_on_a_day_OverTime(double work_on_a_day_OverTime) {
		this.total_work_on_a_day_OverTime = work_on_a_day_OverTime;
	}
	
	public Date getUpdated_date() {
		return this.updated_date;
	}
	
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	
	public String getUpdated_user() {
		return this.updated_user;
	}
	
	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
	}
}
