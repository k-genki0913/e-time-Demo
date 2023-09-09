package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User_Department_DTO;

public class User_Department_DAO {
	
	public User_Department_DTO getUser_Department(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		User_Department_DTO user_department_DTO = new User_Department_DTO();
		
		String sql = "SELECT USER_ID, DEPARTMENT_ID, UPDATE_DATE, UPDATE_USER FROM USER_DEPARTMENT WHERE USER_ID = ?";
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_department_DTO.setUser_id(rs.getString("user_id"));
				user_department_DTO.setDepartment_id(rs.getInt("department_id"));
				user_department_DTO.setUpdateDate(rs.getDate("update_date"));
				user_department_DTO.setUpdateUser(rs.getString("update_user"));
			}
		} catch(SQLException e) {
			System.out.println("User_Departmentテーブルから情報の取得に失敗しました");
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
		return user_department_DTO;
	}
	
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
