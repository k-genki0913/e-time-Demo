package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Users_UsersRole_UserDepartment_DAO {
	
	public Map<String, String> getDepartmentRoleUser(Integer department_id, String role_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Map<String, String> userList = new HashMap<>();
		
		String sql = "SELECT Users.USER_ID, Users.NAME, ROLE_ID, DEPARTMENT_ID FROM Users "
				+ "JOIN User_Role ON Users.user_id = User_Role.user_id "
				+ "JOIN User_Department ON Users.user_id = User_Department.user_id "
				+ "WHERE DEPARTMENT_ID = ? AND ROLE_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, department_id);
			pstmt.setString(2, role_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userList.put(rs.getString("Users.USER_ID"), rs.getString("Users.NAME"));
			}
		} catch(SQLException e) {
			System.out.println("Usersテーブル、User_Roleテーブル、User_Departmentテーブルから情報の取得に失敗しました");
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {}
			}
			if(con != null) {
				try {
					con.close(); 
				} catch (SQLException ignore) {}
			}
		}
		return userList;
	}
}
