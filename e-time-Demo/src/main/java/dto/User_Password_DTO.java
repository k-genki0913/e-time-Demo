package dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class User_Password_DTO implements Serializable{
	private String user_id;
	private String password;
	private int invalid_count;
	private Date update_date;
	private String update_user;
	private boolean login_result;
	
	public User_Password_DTO() {}
	
	public User_Password_DTO(String user_id, String password, Date update_date, String update_user) {
		this.user_id = user_id;
		this.password = password;
		this.invalid_count = 0;
		this.update_date = update_date;
		this.update_user = update_user;
	}
	
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setInvalid_count(int invalid_count) {
		this.invalid_count = invalid_count;
	}
	
	public int getInvalid_count() {
		return this.invalid_count;
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
	
	public void setLogin_result(boolean login_result) {
		this.login_result = login_result;
	}
	
	public boolean getLogin_result() {
		return this.login_result;
	}
}
