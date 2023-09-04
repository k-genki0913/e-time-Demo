package dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Role_DTO implements Serializable, Comparable<Role_DTO>{
	private String role_id;
	private String role_name;
	private Integer approve_level;
	
	public Role_DTO() {}
	
	public Role_DTO(String role_id, String role_name, Integer approve_level) {
		this.role_id = role_id;
		this.role_name = role_name;
		this.approve_level = approve_level;
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public int compareTo(Role_DTO dto) {
		if(this.approve_level < dto.approve_level) {
			return -1;
		}
		if(this.approve_level > dto.approve_level) {
			return 1;
		}
		return 0;
	}
	
	public String getRole_id() {
		return this.role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	public String getRole_name() {
		return this.role_name;
	}
	
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public Integer getApprove_level() {
		return this.approve_level;
	}
	
	public void setApprove_level(Integer approve_level) {
		this.approve_level = approve_level;
	}
	
}
