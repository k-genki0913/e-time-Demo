package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Monthly_Report_DTO;

public class Monthly_Report_DAO {
	
	public List<Monthly_Report_DTO> getMonthly_Report_not_id(String user_id, int year, int month) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Monthly_Report_DTO> monthly_report_list = new ArrayList<>();
		
		String sql = "SELECT monthly_report_id, target_year, target_month, user_id, workingDay, total_working_hours, "
				+ "total_overtime_hours, work_on_a_day, totalWork_on_a_day_Hour, totalWork_on_a_day_Overtime, updated_date, updated_user "
				+ "FROM Monthly_Report WHERE target_year = ? and target_month = ?, user_id = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			pstmt.setString(3, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Integer monthly_report_id = rs.getInt("monthly_report_id");
				int target_year = rs.getInt("target_year");
				int target_month = rs.getInt("target_month");
				String record_user_id = rs.getString("user_id");
				int workingDay = rs.getInt("workingDat");
				double total_working_hours = rs.getDouble("total_working_hours");
				double total_overtime_hours = rs.getDouble("total_overtime_hours");
				int work_on_a_day = rs.getInt("work_on_a_day");
				double totalWork_on_a_day_Hour = rs.getDouble("totalWork_on_a_day_Hour");
				double totalWork_on_a_day_OverTime = rs.getDouble("totalWork_on_a_day_OverTime");
				Date updated_date = rs.getDate("updated_date");
				String updated_user = rs.getString("updated_user");
				Monthly_Report_DTO monthly_report_DTO = new Monthly_Report_DTO(monthly_report_id, target_year, target_month, record_user_id, 
							workingDay, total_working_hours, total_overtime_hours, work_on_a_day, totalWork_on_a_day_Hour, totalWork_on_a_day_OverTime,
							updated_date, updated_user);
				monthly_report_list.add(monthly_report_DTO);
			}
		} catch(SQLException e) {
			System.out.println("Monthly_Reportテーブルから情報の取得に失敗しました");
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
		return monthly_report_list;
	}
}
