package dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class User_Department_DTO implements Serializable, Comparable<User_Department_DTO>{
	private String user_id;
	private Integer department_id;
	private Date update_date;
	private String update_user;
	
	public User_Department_DTO() {}
	
	public User_Department_DTO(String user_id, Integer department_id, Date update_date, String update_user) {
		this.user_id = user_id;
		this.department_id = department_id;
		this.update_date = update_date;
		this.update_user = update_user;
	}
	
	public int compareTo(User_Department_DTO dto) {
		return this.user_id.compareTo(dto.getUser_id());
	}
	
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public Integer getDepartment_id() {
		return this.department_id;
	}
	
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	
	public Date getUpdateDate() {
		return this.update_date;
	}
	
	public void setUpdateDate(Date update_date) {
		this.update_date = update_date;
	}
	
	public String getUpdateUser() {
		return this.update_user;
	}
	
	public void setUpdateUser(String update_user) {
		this.update_user = update_user;
	}
}
