package dto;

import java.sql.Date;

public class User_Constract_DTO {
	private String user_id;
	private Integer constract_id;
	private Date constract_start;
	private Date constract_end;
	private Date update_date;
	private String update_user;
	
	public User_Constract_DTO() {}
	
	public User_Constract_DTO(String user_id, Date constract_start, Date constract_end, Date update_date, String update_user) {
		this.user_id = user_id;
		this.constract_start = constract_start;
		this.constract_end = constract_end;
		this.update_date = update_date;
		this.update_user = update_user;
	}
	
	public User_Constract_DTO(String user_id, Integer constract_id, Date constract_start, Date constract_end, Date update_date, String update_user) {
		this.user_id = user_id;
		this.constract_id = constract_id;
		this.constract_start = constract_start;
		this.constract_end = constract_end;
		this.update_date = update_date;
		this.update_user = update_user;
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Integer getConstract_id() {
		return this.constract_id;
	}
	
	public void setConstract_id(Integer constract_id) {
		this.constract_id = constract_id;
	}
	
	public Date getConstract_start() {
		return this.constract_start;
	}
	
	public void setConstract_start(Date constract_start) {
		this.constract_start = constract_start;
	}
	
	public Date getConstract_end() {
		return this.constract_end;
	}
	
	public void setConstract_end(Date constract_end) {
		this.constract_end = constract_end;
	}
	
	public Date getUpdate_date() {
		return this.update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	public String getUpdate_user() {
		return this.update_user;
	}
	
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
}
