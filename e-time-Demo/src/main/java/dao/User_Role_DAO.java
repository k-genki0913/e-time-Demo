package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User_Role_DTO;

public class User_Role_DAO {
	
	public User_Role_DTO getUser_Role(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		User_Role_DTO user_role_DTO = new User_Role_DTO();
		
		String sql = "SELECT USER_ID, ROLE_ID, UPDATE_DATE, UPDATE_USER FROM USER_ROLE WHERE USER_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_role_DTO.setUser_id(rs.getString("USER_ID"));
				user_role_DTO.setRole_id(rs.getString("Role_ID"));
				user_role_DTO.setUpdate_date(rs.getDate("UPDATE_DATE"));
				user_role_DTO.setUpdate_user(rs.getString("UPDATE_USER"));
			}
		} catch(SQLException e) {
			System.out.println("User_Roleテーブルから情報の取得に失敗しました");
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
		return user_role_DTO;
	}
	
	public boolean insertNewRecord(User_Role_DTO user_Role_DTO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO User_Role VALUES(?, ?, ?, ?)";
		
		boolean result = false;
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_Role_DTO.getUser_id());
			pstmt.setString(2, user_Role_DTO.getRole_id());
			pstmt.setDate(3, user_Role_DTO.getUpdate_date());
			pstmt.setString(4, user_Role_DTO.getUpdate_user());
			
			int sqlResult = pstmt.executeUpdate();
			
			if(sqlResult == 1) {
				result = true;
			}
		} catch(SQLException e) {
			System.out.println("User_Roleテーブルへの追加に失敗しました");
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {}
			}
		}
		return result;
	}
}
