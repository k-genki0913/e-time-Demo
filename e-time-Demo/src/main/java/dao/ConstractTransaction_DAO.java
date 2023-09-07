package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Constract_DTO;
import dto.User_Constract_DTO;

public class ConstractTransaction_DAO {
	public String constractRegist(User_Constract_DTO user_constract_DTO, Constract_DTO constract_DTO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String constractRegistError = "";
		
		String constract_Sql = "INSERT INTO CONSTRACT(WORKPLACE, START_TIME, END_TIME, WORK_TIME, BREAK_TIME, IS_VALID) VALUES(?, ?, ?, ?, ?, 1)";
		String constract_get_Sql = "SELECT CONSTRACT_ID FROM CONSTRACT ORDER BY CONSTRACT_ID DESC LIMIT 1";
		String user_constract_Sql = "INSERT INTO USER_CONSTRACT(USER_ID, CONSTRACT_ID, CONSTRACT_START, CONSTRACT_END, UPDATED_DATE, UPDATED_USER) VALUES(?, ?, ?, ?, ?, ?)";
		
		int constract_sql_result = 0;
		int constract_id = 0;
		int user_constract_sql_result = 0;
		
		try {
			con = BaseDAO.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(constract_Sql);
			
			pstmt.setString(1, constract_DTO.getWorkplace());
			pstmt.setTime(2, constract_DTO.getStart_time());
			pstmt.setTime(3, constract_DTO.getEnd_time());
			pstmt.setInt(4, constract_DTO.getWork_time());
			pstmt.setInt(5,  constract_DTO.getBreak_time());
			
			constract_sql_result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = con.prepareStatement(constract_get_Sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				constract_id = rs.getInt("constract_id");
			}
			
			pstmt.close();
			
			pstmt = con.prepareStatement(user_constract_Sql);
			
			pstmt.setString(1, user_constract_DTO.getUser_id());
			pstmt.setInt(2, constract_id);
			pstmt.setDate(3, user_constract_DTO.getConstract_start());
			pstmt.setDate(4, user_constract_DTO.getConstract_end());
			pstmt.setDate(5, user_constract_DTO.getUpdate_date());
			pstmt.setString(6, user_constract_DTO.getUpdate_user());
			
			user_constract_sql_result = pstmt.executeUpdate();
			
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
			System.out.println("契約関係の新規登録に失敗しました");
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
		constractRegistError = this.checkErrorMsg(constract_sql_result, constract_id, user_constract_sql_result);
		return constractRegistError;
	}
	
	public String checkErrorMsg(int constract_sql_result, int constract_id, int user_constract_sql_result) {
		String errorMsg = "";
		String lineCd = System.getProperty("line.separator");
		
		if(constract_sql_result != 1) {
			errorMsg += "Constractテーブルへの登録に失敗しました" + lineCd;
		}
		if(constract_id == 0) {
			errorMsg += "ConstractテーブルからConstract_idを取得できませんでした" + lineCd;
		}
		if(user_constract_sql_result != 1) {
			errorMsg += "User_Constractテーブルへの登録に失敗しました" + lineCd;
		}
		
		return errorMsg;
	}
}
