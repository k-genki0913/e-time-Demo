package model;

import dao.Users_DAO;
import dto.Users_DTO;

public class ExistCheck {
	public boolean checkUser_id(String user_id) {
		Users_DAO users_DAO = new Users_DAO();
		
		Users_DTO users_DTO = users_DAO.getUser(user_id);
		
		if(user_id.equals(users_DTO.getUser_id())){
			return true;
		} else {
			return false;
		}
	}
}
