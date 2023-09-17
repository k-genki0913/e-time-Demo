package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Users_UserRole_DAO {
	public Map<String, String> getRoleUser(String role_id){
		Map<String, String> userList = new HashMap<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT Users.USER_ID, Users.NAME, ROLE_ID FROM USERS "
				+ "JOIN USER_ROLE ON USERS.USER_ID = USER_ROLE.USER_ID "
				+ "WHERE ROLE_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, role_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userList.put(rs.getString("Users.USER_ID"), rs.getString("Users.NAME"));
			}
		} catch(SQLException e) {
			System.out.println("Usersテーブル、User_Roleテーブルから情報の取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
		}
		return userList;
	}
}
