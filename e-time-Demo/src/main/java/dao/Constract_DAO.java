package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

import dto.ConstractList_Record;
import dto.Constract_DTO;

public class Constract_DAO {
	
	public ArrayList<ConstractList_Record> getConstractList(){
		ArrayList<ConstractList_Record> constractList = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT * FROM CONSTRACT "
				+ "LEFT JOIN USER_CONSTRACT "
				+ "ON CONSTRACT.CONSTRACT_ID = USER_CONSTRACT.CONSTRACT_ID "
				+ "WHERE IS_VALID = 1";
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Integer constract_id = rs.getInt("constract_id");
				String workplace = rs.getString("workplace");
				Time start_time = rs.getTime("start_time");
				Time end_time = rs.getTime("end_time");
				Integer work_time = rs.getInt("work_time");
				Integer break_time = rs.getInt("break_time");
				Integer is_valid = rs.getInt("is_valid");
				String user_id = rs.getString("user_id");
				Date constract_start = rs.getDate("constract_start");
				Date constract_end = rs.getDate("constract_end");
				ConstractList_Record constractList_Record = new ConstractList_Record(constract_id, workplace, start_time, end_time, work_time, break_time, is_valid, user_id, constract_start, constract_end);
				constractList.add(constractList_Record);
			}
			if(constractList != null || constractList.size() > 0) {
				Collections.sort(constractList, (x, y) -> (x.getConstract_id() - y.getConstract_id()));
			}
		} catch(SQLException e) {
			System.out.println("ConstractListの取得に失敗しました");
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
		return constractList;
	}
	
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
