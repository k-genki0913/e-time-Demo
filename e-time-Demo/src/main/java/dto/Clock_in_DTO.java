package dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Clock_in_DTO implements Serializable{
	private String user_id;
	private String clock_in_date_branch;
	private Date clock_in_date;
	private Integer clock_in_no;
	private String work_pattern;
	private String work_remarks;
	private String work_category;
	private Time start_time;
	private Time end_time;
	private Integer break_time;
	private Integer over_time;
	private Integer work_on_a_day;
	
	public Clock_in_DTO() {}
	
	public Clock_in_DTO(String user_id, String clock_in_date_branch, Date clock_in_date, Integer clock_in_no, 
			String work_pattern, String work_remarks, String work_category, Time start_time, Time end_time, Integer break_time, Integer over_time,
			Integer work_on_a_day) {
		this.user_id = user_id;
		this.clock_in_date = clock_in_date;
		this.clock_in_no = clock_in_no;
		this.clock_in_date_branch = clock_in_date_branch;
		this.work_pattern = work_pattern;
		this.work_remarks = work_remarks;
		this.work_category = work_category;
		this.start_time = start_time;
		this.end_time = end_time;
		this.break_time = break_time;
		this.over_time = over_time;
		this.work_on_a_day = work_on_a_day;
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Date getClock_in_date() {
		return this.clock_in_date;
	}
	
	public void setClock_in_date(Date clock_in_date) {
		this.clock_in_date = clock_in_date;
	}
	
	public Integer getClock_in_no(){
		return this.clock_in_no;
	}
	
	public void setClock_in_no(Integer clock_in_no) {
		this.clock_in_no = clock_in_no;
	}
	
	public String getClock_in_date_branch() {
		return this.clock_in_date_branch;
	}
	
	public void setClock_in_date_branch(String clock_in_date_branch) {
		this.clock_in_date_branch = clock_in_date_branch;
	}
	
	public String getWork_pattern() {
		return this.work_pattern;
	}
	
	public void setWork_pattern(String work_pattern) {
		this.work_pattern = work_pattern;
	}
	
	public String getWork_remarks() {
		return this.work_remarks;
	}
	
	public void setWork_remarks(String work_remarks) {
		this.work_remarks = work_remarks;
	}
	
	public String getWork_category() {
		return this.work_category;
	}
	
	public void setWork_category(String work_category) {
		this.work_category = work_category;
	}
	
	public Time getStart_time() {
		return this.start_time;
	}
	
	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}
	
	public Time getEnd_time() {
		return this.end_time;
	}
	
	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}
	
	public Integer getBreak_time() {
		return this.break_time;
	}
	
	public void setBreak_time(Integer break_time) {
		this.break_time = break_time;
	}
	
	public Integer getOver_time() {
		return this.over_time;
	}
	
	public void setOver_time(Integer over_time) {
		this.over_time = over_time;
	}
	
	public Integer getWork_on_a_day() {
		return this.work_on_a_day;
	}
	
	public void setWork_on_a_day(Integer work_on_a_day) {
		this.work_on_a_day = work_on_a_day;
	}
}
