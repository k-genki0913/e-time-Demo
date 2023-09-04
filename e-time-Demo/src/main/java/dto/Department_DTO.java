package dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Department_DTO implements Serializable, Comparable<Department_DTO>{
	private Integer department_id;
	private String department_name;
	
	public Department_DTO() {}
	
	public Department_DTO(Integer department_id, String department_name) {
		this.department_id = department_id;
		this.department_name = department_name;
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public int compareTo(Department_DTO dto) {
		if(this.department_id < dto.getDepartment_id()) {
			return -1;
		}
		if(this.department_id > dto.getDepartment_id()) {
			return 1;
		}
		return 0;
	}
	
	public Integer getDepartment_id() {
		return this.department_id;
	}
	
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	
	public String getDepartment_name() {
		return this.department_name;
	}
	
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
}
