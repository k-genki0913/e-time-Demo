package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User_Department_DTO;
import dto.User_Password_DTO;
import dto.User_Role_DTO;
import dto.Users_DTO;

public class UserUpdateTransactionDAO {
	public String userUpdate(Users_DTO users_DTO, User_Password_DTO user_Password_DTO, User_Role_DTO user_Role_DTO, User_Department_DTO user_Department_DTO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String userUpdateErrorMsg = "";
		
		String users_Sql = "UPDATE USERS "
				+ "SET user_id = ?, name = ?, mail_address = ?, admin = ?, is_valid = ?, update_date = ?, update_user = ?";
		String user_password_Sql = "UPDATE USER_PASSWORD "
				+ "SET user_id = ?, password = ?, invalid_count = 0, update_date = ?, update_user = ?";
		String user_role_Sql = "UPDATE USER_ROLE "
				+ "SET user_id = ?, role_id = ?, update_date = ?, update_user = ?";
		String user_department_Sql = "UPDATE USER_DEPARTMENT "
				+ "SET user_id = ?, department_id = ?, update_date = ?, update_user = ?";
		
		int users_Result = 0;
		int user_Password_Result = 0;
		int user_Role_Result = 0;
		int user_Department_Result = 0;
		
		try {
			con = BaseDAO.getConnection();
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(users_Sql);
			
			pstmt.setString(1, users_DTO.getUser_id());
			pstmt.setString(2, users_DTO.getName());
			pstmt.setString(3, users_DTO.getMail_address());
			pstmt.setInt(4, users_DTO.getAdmin());
			pstmt.setInt(5, users_DTO.getIs_valid());
			pstmt.setDate(6, users_DTO.getUpdate_date());
			pstmt.setString(7, users_DTO.getUpdate_user());
			
			users_Result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = con.prepareStatement(user_password_Sql);
			
			pstmt.setString(1, user_Password_DTO.getUser_id());
			pstmt.setString(2, user_Password_DTO.getPassword());
			pstmt.setDate(3, user_Password_DTO.getUpdate_date());
			pstmt.setString(4, user_Password_DTO.getUpdate_user());
			
			user_Password_Result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = con.prepareStatement(user_role_Sql);
			
			pstmt.setString(1, user_Role_DTO.getUser_id());
			pstmt.setString(2, user_Role_DTO.getRole_id());
			pstmt.setDate(3, user_Role_DTO.getUpdate_date());
			pstmt.setString(4, user_Role_DTO.getUpdate_user());
			
			user_Role_Result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = con.prepareStatement(user_department_Sql);
			
			pstmt.setString(1, user_Department_DTO.getUser_id());
			pstmt.setInt(2, user_Department_DTO.getDepartment_id());
			pstmt.setDate(3, user_Department_DTO.getUpdateDate());
			pstmt.setString(4, user_Department_DTO.getUpdateUser());
			
			user_Department_Result = pstmt.executeUpdate();
			
			con.commit();
			
			pstmt.close();
		} catch(SQLException e) {
			if(con != null) {
				try {
					con.rollback();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch(SQLException ignore) {}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
			if(pstmt != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
		}
		userUpdateErrorMsg = this.checkErrorMsg(users_Result, user_Password_Result, user_Role_Result, user_Department_Result);
		return userUpdateErrorMsg;
	}
	
	public String checkErrorMsg(int users_Result, int user_Password_Result, int user_Role_Result, int user_Department_Result) {
		String errorMsg = "";
		String lineCd = System.getProperty("line.separator");
		
		if(users_Result != 1) {
			errorMsg += "Usersテーブルへの更新に失敗しました" + lineCd;
		}
		if(user_Password_Result != 1) {
			errorMsg += "User_Passwordテーブルへの更新に失敗しました" + lineCd;
		}
		if(user_Role_Result != 1) {
			errorMsg += "User_Roleテーブルへの更新に失敗しました" + lineCd;
		}
		if(user_Department_Result != 1) {
			errorMsg += "user_Departmentテーブルへの更新に失敗しました" + lineCd;
		}
		return errorMsg;
	}
}
