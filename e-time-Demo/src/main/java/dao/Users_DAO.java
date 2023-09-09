package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import dto.Users_DTO;

public class Users_DAO {
	
	public ArrayList<Users_DTO> getUserList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		ArrayList<Users_DTO> userList = new ArrayList<>();
		
		String sql = "SELECT USER_ID, NAME, MAIL_ADDRESS, ADMIN, IS_VALID, UPDATE_DATE, UPDATE_USER FROM USERS";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Users_DTO users_DTO = new Users_DTO();
				users_DTO.setUser_id(rs.getString("USER_ID"));
				users_DTO.setName(rs.getString("NAME"));
				users_DTO.setMail_address(rs.getString("MAIL_ADDRESS"));
				users_DTO.setAdmin(rs.getInt("ADMIN"));
				users_DTO.setIs_valid(rs.getInt("IS_VALID"));
				users_DTO.setUpdate_date(rs.getDate("UPDATE_DATE"));
				users_DTO.setUpdate_user(rs.getString("UPDATE_USER"));
				userList.add(users_DTO);
			}
			if(userList != null || userList.size() > 0) {
				Collections.sort(userList, (x, y) -> (x.getUser_id().compareTo(y.getUser_id())));
			}
		} catch(SQLException e) {
			System.out.println("Usersテーブルからの情報取得に失敗しました");
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
	
	public Users_DTO getUser(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		Users_DTO users_DTO = new Users_DTO();
		
		String sql = "SELECT USER_ID, NAME, MAIL_ADDRESS, ADMIN, IS_VALID, UPDATE_DATE, UPDATE_USER FROM USERS WHERE USER_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				users_DTO.setUser_id(rs.getString("USER_ID"));
				users_DTO.setName(rs.getString("NAME"));
				users_DTO.setMail_address(rs.getString("MAIL_ADDRESS"));
				users_DTO.setAdmin(rs.getInt("ADMIN"));
				users_DTO.setIs_valid(rs.getInt("IS_VALID"));
				users_DTO.setUpdate_date(rs.getDate("UPDATE_DATE"));
				users_DTO.setUpdate_user(rs.getString("UPDATE_USER"));
			} 
		} catch(SQLException e) {
			System.out.println("Usersテーブルからの情報の取得に失敗しました");
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
		return users_DTO;
	}
	
	public boolean insertNewRecord(Users_DTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		boolean result = false;
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getMail_address());
			pstmt.setInt(4, dto.getAdmin());
			pstmt.setInt(5, dto.getIs_valid());
			pstmt.setDate(6, dto.getUpdate_date());
			pstmt.setString(7, dto.getUpdate_user());
			
			int sqlResult = pstmt.executeUpdate();
			if(sqlResult == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch(SQLException e) {
			System.out.println("Usersテーブルへのユーザーの追加に失敗しました");
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
		return result;
	}
}
