package model;

import dao.Clock_in_DAO;
import dto.Clock_in_DTO;

public class ReportDataCheck {
	public boolean reportExist(String user_id, String clock_in_date_branch) {
		Clock_in_DAO clock_in_DAO = new Clock_in_DAO();
		
		Clock_in_DTO clock_in_DTO = clock_in_DAO.getClock_in_DTO(user_id, clock_in_date_branch);
		
		if(clock_in_DTO.getClock_in_date_branch().equals("9999999999")) {
			return false;
		} else {
			return true;
		}
	}
}
