package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import dto.Clock_in_DTO;

public class Clock_in_DAO {
	
	public ArrayList<Clock_in_DTO> getCalendar(String user_id, Date start_Date, Date last_Date){
		ArrayList<Clock_in_DTO> calendarList = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT user_id, clock_in_date_branch, clock_in_date, clock_in_no, work_pattern, work_remarks, work_category, start_time, end_time, break_time, over_time, work_on_a_day FROM CLOCK_IN WHERE USER_ID = ? AND CLOCK_IN_DATE BETWEEN ? AND ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			pstmt.setDate(2,start_Date);
			pstmt.setDate(3, last_Date);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user_id = rs.getString("user_id");
				Date clock_in_date = rs.getDate("clock_in_date");
				Integer clock_in_no = rs.getInt("clock_in_no");
				String clock_in_date_branch  = rs.getString("clock_in_date_branch");
				String work_pattern = rs.getString("work_pattern");
				String work_remarks = rs.getString("work_remarks");
				String work_category = rs.getString("work_category");
				Time start_time = rs.getTime("start_time");
				Time end_time = rs.getTime("end_time");
				Integer break_time = rs.getInt("break_time");
				Integer over_time = rs.getInt("over_time");
				Integer work_on_a_day = rs.getInt("work_on_a_day");
				Clock_in_DTO clock_in_DTO = new Clock_in_DTO(user_id, clock_in_date_branch,clock_in_date, clock_in_no,  work_pattern, work_remarks,
						work_category, start_time, end_time, break_time, over_time, work_on_a_day);
				calendarList.add(clock_in_DTO);
			}
		} catch(SQLException e) {
			System.out.println("Clock_inテーブルから情報の取得に失敗しました");
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
	
		return calendarList;
	}
	
	public boolean addCalendar_Clock_in_DAO(String user_id, int year, int month) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql = "INSERT INTO Clock_in(user_id, clock_in_date_branch, clock_in_date, clock_in_no) VALUES (?, ?, ?, 1)";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, 0);
			
			int monthLastDay = calendar.get(Calendar.DAY_OF_MONTH);
			for(int i = 1; i <= monthLastDay; i++) {
				String str_year = String.valueOf(year);
				String str_month = StringUtils.leftPad(String.valueOf(month), 2, "0");
				String day = StringUtils.leftPad(String.valueOf(i), 2, "0");
				String clock_in_date_branch = str_year + str_month + day + "01";
				String str_date = str_year + "-" + str_month + "-" + day;
				System.out.println(str_date);
				Date clock_in_date = Date.valueOf(str_date);
				
				pstmt.setString(1, user_id);
				pstmt.setString(2, clock_in_date_branch);
				pstmt.setDate(3, clock_in_date);
				pstmt.addBatch();
			}
			int[] sqlResult = pstmt.executeBatch();
			System.out.println("登録：" + sqlResult.length + "件");
			try {
				con.commit();
				System.out.println("登録完了");
				result = true;
			} catch(SQLException e) {
				con.rollback();
				System.out.println("登録失敗：ロールバック実行");
				e.printStackTrace();
			}
		} catch(SQLException e) {
			System.out.println("カレンダーの追加を失敗しました");
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
	
	public boolean insertClock_in_DAO(Clock_in_DTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		String sql = "INSERT INTO Clock_in(user_id, clock_in_date_branch, clock_in_date, clock_in_no, work_pattern,"
				+ "work_remarks, work_category, start_time, end_time, break_time, over_time, work_on_a_day)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getClock_in_date_branch());
			pstmt.setDate(3, dto.getClock_in_date());
			pstmt.setInt(4, dto.getClock_in_no());
			pstmt.setString(5, dto.getWork_pattern());
			pstmt.setString(6, dto.getWork_remarks());
			pstmt.setString(7, dto.getWork_category());
			pstmt.setTime(8, dto.getStart_time());
			pstmt.setTime(9, dto.getEnd_time());
			pstmt.setInt(10, dto.getBreak_time());
			pstmt.setInt(11, dto.getOver_time());
			pstmt.setInt(12, dto.getWork_on_a_day());
			
			int sqlResult = pstmt.executeUpdate();
			
			if(sqlResult == 1) {
				result = true;
			} else {
				result = false;
			}
		}catch(SQLException e) {
			System.out.println("Clock_in_DAOへの追加を失敗しました");
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
	
	public boolean updateClock_in_DAO(Clock_in_DTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;;
		
		String sql = "UPDATE Clock_in SET clock_in_date = ?, clock_in_no = ?, work_pattern = ?, work_remarks = ?,"
				+ "work_category = ?, start_time = ?, end_time = ?, break_time = ?, over_time = ?, work_on_a_day = ?"
				+ "WHERE user_id = ? AND clock_in_date_branch = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setDate(1, dto.getClock_in_date());
			pstmt.setInt(2, dto.getClock_in_no());
			pstmt.setString(3, dto.getWork_pattern());
			pstmt.setString(4, dto.getWork_remarks());
			pstmt.setString(5, dto.getWork_category());
			pstmt.setTime(6, dto.getStart_time());
			pstmt.setTime(7, dto.getEnd_time());
			pstmt.setInt(8, dto.getBreak_time());
			pstmt.setInt(9, dto.getOver_time());
			pstmt.setInt(10, dto.getWork_on_a_day());
			pstmt.setString(11, dto.getUser_id());
			pstmt.setString(12, dto.getClock_in_date_branch());
			
			int sqlResult = pstmt.executeUpdate();
			
			if(sqlResult == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch(SQLException e) {
			System.out.println("clock_in_dtoの更新に失敗しました");
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
	
	
	public Clock_in_DTO getClock_in_DTO(String user_id, String clock_in_date_branch) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		Clock_in_DTO clock_in_DTO = new Clock_in_DTO();
		
		String sql = "SELECT user_id, clock_in_date_branch, clock_in_date, clock_in_no, work_pattern, work_remarks, work_category, start_time, end_time, break_time, over_time, work_on_a_day FROM CLOCK_IN WHERE USER_ID = ? AND CLOCK_IN_DATE_BRANCH = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			pstmt.setString(2, clock_in_date_branch);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_id = rs.getString("user_id");
				Date clock_in_date = rs.getDate("clock_in_date");
				Integer clock_in_no = rs.getInt("clock_in_no");
				clock_in_date_branch  = rs.getString("clock_in_date_branch");
				String work_pattern = rs.getString("work_pattern");
				String work_remarks = rs.getString("work_remarks");
				String work_category = rs.getString("work_category");
				Time start_time = rs.getTime("start_time");
				Time end_time = rs.getTime("end_time");
				Integer break_time = rs.getInt("break_time");
				Integer over_time = rs.getInt("over_time");
				Integer work_on_a_day = rs.getInt("work_on_a_day");
				clock_in_DTO = new Clock_in_DTO(user_id, clock_in_date_branch,clock_in_date, clock_in_no,  work_pattern, work_remarks,
						work_category, start_time, end_time, break_time, over_time, work_on_a_day);
			} else {
				clock_in_DTO.setClock_in_date_branch("9999999999");
			}
		} catch(SQLException e) {
			System.out.println("Clock_inテーブルの取得に失敗しました");
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
		return clock_in_DTO;
	}
}
