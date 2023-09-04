package model;

import dao.UserRegistTransaction_DAO;
import dto.User_Department_DTO;
import dto.User_Password_DTO;
import dto.User_Role_DTO;
import dto.Users_DTO;

public class UserRegist {
	public String registResult(Users_DTO users_DTO, User_Password_DTO user_Password_DTO,
			User_Role_DTO user_Role_DTO, User_Department_DTO user_Department_DTO) {
		
		UserRegistTransaction_DAO userRegistTransaction_DAO = new UserRegistTransaction_DAO();
		String userRegistErrorMsg = userRegistTransaction_DAO.userRegist(users_DTO, user_Password_DTO, user_Role_DTO, user_Department_DTO);
		
		return userRegistErrorMsg;
	}
}
