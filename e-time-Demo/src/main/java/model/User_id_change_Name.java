package model;

import dao.Users_DAO;
import dto.Users_DTO;

public class User_id_change_Name {
	public String execute(String user_id) {
		Users_DTO users_DTO = new Users_DAO().getUser(user_id);
		String name = users_DTO.getName();
		return name;
	}
}
