package dto;

import java.sql.Time;

public class Constract_DTO {
	private String constract_id;
	private String workplace;
	private Time start_time;
	private Time end_time;
	private Integer break_time;
	private Integer work_time;
	private Integer is_valid;
	
	public Constract_DTO() {}
	
	public Constract_DTO(String constract_id, String workplace, Time start_time, Time end_time, Integer break_time, Integer work_time, Integer is_valid) {
		this.constract_id = constract_id;
		this.workplace = workplace;
		this.start_time = start_time;
		this.end_time = end_time;
		this.break_time = break_time;
		this.work_time = work_time;
		this.is_valid = is_valid;
	}
	
	public Constract_DTO(String workplace, Time start_time, Time end_time, Integer break_time, Integer work_time, Integer is_valid) {
		this.workplace = workplace;
		this.start_time = start_time;
		this.end_time = end_time;
		this.break_time = break_time;
		this.work_time = work_time;
		this.is_valid = is_valid;
	}
	
	public String getConstract_id() {
		return this.constract_id;
	}
	public void setConstract_id(String constract_id) {
		this.constract_id = constract_id;
	}
	
	public String getWorkplace() {
		return this.workplace;
	}
	
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
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
	
	public Integer getWork_time() {
		return this.work_time;
	}
	
	public void setWork_time(Integer work_time) {
		this.work_time = work_time;
	}
	
	public Integer getIs_valid() {
		return this.is_valid;
	}
	
	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}
}
