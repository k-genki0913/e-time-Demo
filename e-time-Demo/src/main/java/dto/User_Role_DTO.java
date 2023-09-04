package dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User_Role_DTO implements Serializable, Comparable<User_Role_DTO>{
	private String user_id;
	private String role_id;
	private Date update_date;
	private String update_user;
	
	public User_Role_DTO() {}
	
	public User_Role_DTO(String user_id, String role_id, Date update_date, String update_user) {
		this.user_id = user_id;
		this.role_id = role_id;
		this.update_date = update_date;
		this.update_user = update_user;
	}
	
	public int compareTo(User_Role_DTO dto) {
		return this.user_id.compareTo(dto.getUser_id());
	}
	
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getRole_id() {
		return this.role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
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
