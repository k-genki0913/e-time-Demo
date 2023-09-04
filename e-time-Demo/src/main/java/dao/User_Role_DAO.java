package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User_Role_DTO;

public class User_Role_DAO {
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
