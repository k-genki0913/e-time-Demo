package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.User_Department_DTO;

public class User_Department_DAO {
	public boolean insertNewRecord(User_Department_DTO user_Department_DTO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		String sql = "INSERT INTO User_Department VALUES(?, ?, ?, ?)";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_Department_DTO.getUser_id());
			pstmt.setInt(2, user_Department_DTO.getDepartment_id());
			pstmt.setDate(3, user_Department_DTO.getUpdateDate());
			pstmt.setString(4, user_Department_DTO.getUpdateUser());
			
			int sqlResult = pstmt.executeUpdate();
			
			if(sqlResult == 1) {
				result = true;
			}
		} catch(SQLException e) {
			System.out.println("User_Departmentテーブルへの登録に失敗しました");
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
