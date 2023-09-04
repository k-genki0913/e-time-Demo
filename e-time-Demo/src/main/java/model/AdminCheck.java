package model;

import dao.Users_DAO;
import dto.Users_DTO;

public class AdminCheck {
	public Integer getAdminFlag(String user_id) {
		Users_DAO users_DAO = new Users_DAO();
		
		Users_DTO users_DTO = users_DAO.getUser(user_id);
		
		return users_DTO.getAdmin();
	}
}
