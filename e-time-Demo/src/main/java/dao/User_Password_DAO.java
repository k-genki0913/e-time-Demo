package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User_Password_DTO;

public class User_Password_DAO {
	/*user_idを用いてUser_Passwordテーブルからpassword、invalid_countを取得し
	 * UserLoginDTOに格納して戻す
	 */
	public User_Password_DTO getUser_Password(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		User_Password_DTO userLoginDTO = new User_Password_DTO();
		
		String sql = "SELECT USER_ID, PASSWORD, INVALID_COUNT FROM USER_PASSWORD WHERE USER_ID=?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userLoginDTO.setUser_id(rs.getString("USER_ID"));
				userLoginDTO.setPassword(rs.getString("PASSWORD"));
				userLoginDTO.setInvalid_count(rs.getInt("INVALID_COUNT"));
			} else {
				userLoginDTO.setUser_id("0000000000");
				userLoginDTO.setPassword("0000000000");
				userLoginDTO.setInvalid_count(99);
			}
		} catch(SQLException e) {
			System.out.println("User_Passwordテーブルからの情報取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {
					
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {
					
				}
			}
		}
		return userLoginDTO;
	}
	
	public void setInvalid_Count(String user_id, int count) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE USER_PASSWORD SET INVALID_COUNT = ? WHERE USER_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, user_id);
			
			int result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException e) {
					
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					
				}
			}
		}
	}
	
	public boolean insertNewRecord(User_Password_DTO user_Password_DTO) {
		Connection con = null;
		PreparedStatement pstmt = null;;
		
		String sql = "INSERT INTO USER_PASSWORD VALUES(?, ?, ?, ?, ?)";
		
		boolean result = false;
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_Password_DTO.getUser_id());
			pstmt.setString(2, user_Password_DTO.getPassword());
			pstmt.setInt(3, user_Password_DTO.getInvalid_count());
			pstmt.setDate(4, user_Password_DTO.getUpdate_date());
			pstmt.setString(5, user_Password_DTO.getUpdate_user());
			
			int sqlResult = pstmt.executeUpdate();
			
			if(sqlResult == 1) {
				result = true;
			}
		} catch(SQLException e) {
			System.out.println("User_Passwordテーブルへの追加に失敗しました");
			e.printStackTrace();
		} finally {
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
		return result;
	}
}
