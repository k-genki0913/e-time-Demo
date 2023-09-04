package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.Department_DAO;
import dao.Role_DAO;
import dto.Department_DTO;
import dto.Role_DTO;

public class RegistItemList {
	public Map<String, String> getRoleList(){
		Map<String, String> roleMap = new LinkedHashMap<>();
		
		Role_DAO role_DAO = new Role_DAO();
		
		List<Role_DTO> roleList = role_DAO.getRole_List();
		
		for(Role_DTO role_DTO: roleList) {
			roleMap.put(role_DTO.getRole_id(), role_DTO.getRole_name());
		}
		return roleMap;
	}
	
	public Map<Integer, String> getDepartmentList(){
		Map<Integer, String> departmentMap = new LinkedHashMap<>();
		
		Department_DAO department_DAO = new Department_DAO();
		
		List<Department_DTO> departmentList = department_DAO.getDepartmentList();
		
		for(Department_DTO department_DTO: departmentList) {
			departmentMap.put(department_DTO.getDepartment_id(), department_DTO.getDepartment_name());
		}
		return departmentMap;
	}
}
