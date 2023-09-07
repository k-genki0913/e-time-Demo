package model;

import java.sql.Date;

import dto.User_Constract_DTO;

public class CreateUser_Constract_DTO {
	public User_Constract_DTO setInput(String user_id, String constract_start, String constract_end, String update_user, Date update_date) {
		Date sql_constract_start = Date.valueOf(constract_start);
		Date sql_constract_end = Date.valueOf(constract_end);
		
		User_Constract_DTO user_constract_DTO = new User_Constract_DTO(user_id, sql_constract_start, sql_constract_end, update_date, update_user);
		
		return user_constract_DTO;
	}
}
