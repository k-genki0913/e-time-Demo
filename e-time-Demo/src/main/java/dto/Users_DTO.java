package dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Users_DTO implements Serializable, Comparable<Users_DTO>{
	private String user_id;
	private String name;
	private String mail_address;
	private Integer admin;
	private Integer is_valid;
	private Date update_date;
	private String update_user;
	
	public Users_DTO() {}
	
	public Users_DTO(String user_id, String name, String mail_address, 
			Integer admin,  Date update_date, String update_user) {
		this.user_id = user_id;
		this.name = name;
		this.mail_address = mail_address;
		this.admin = admin;
		this.is_valid = 1;
		this.update_date = update_date;
		this.update_user = update_user;
	}
	
	public int compareTo(Users_DTO dto) {
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
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail_address() {
		return this.mail_address;
	}
	
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	
	public Integer getAdmin() {
		return this.admin;
	}
	
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	
	public Integer getIs_valid() {
		return this.is_valid;
	}
	
	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
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
