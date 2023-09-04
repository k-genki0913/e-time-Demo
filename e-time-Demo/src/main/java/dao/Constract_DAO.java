package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Constract_DTO;

public class Constract_DAO {
	public boolean insertConstract(Constract_DTO constract_DTO) {
		
		boolean result = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO CONSTRACT(WORKPLACE, START_TIME, END_TIME, WORK_TIME, BREAK_TIME, IS_VALID) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, constract_DTO.getWorkplace());
			pstmt.setTime(2, constract_DTO.getStart_time());
			pstmt.setTime(3, constract_DTO.getEnd_time());
			pstmt.setInt(4, constract_DTO.getWork_time());
			pstmt.setInt(5, constract_DTO.getBreak_time());
			pstmt.setInt(6, constract_DTO.getIs_valid());
			
			int sqlResult = pstmt.executeUpdate();
			
			if(sqlResult == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch(SQLException e) {
			System.out.println("Constractへの追加に失敗しました");
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
				} catch (SQLException ignore) {}
			}
		}
		return result;
	}
}
