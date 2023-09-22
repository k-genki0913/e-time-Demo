package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Monthly_Report_DTO;

public class MonthlyReportApplyTransaction {
	//承認者が2人の場合のメソッド
	public String applyMonthlyReport(Monthly_Report_DTO monthly_report_DTO, String smg_user_id, String mg_user_id) {
		
		String errorMsg = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insert_Monthly_Report_Sql = "INSERT INTO Monthly_Report(target_year, target_month, user_id, workingDay, total_working_hours, "
										+ "total_overtime_hours, work_on_a_day, totalWork_on_a_day_Hour, totalWork_on_a_day_OverTime, updated_date, updated_user) "
										+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String select_Monthly_Report_Sql = "SELECT monthly_report_id FROM Monthly_Report WHERE target_year = ? AND target_month = ? AND user_id = ?";
		
		String insert_Monthly_Report_ApplyList_Sql = "INSERT INTO Monthly_Report_ApplyList(monthly_report_id, approve_level, approve_user_id_1, approve_role_1, "
													+ "approve_1, approve_user_id_2, approve_role_2, approve_2, updated_date) "
													+ "VALUES(?, 1, ?, 'TechSBM', 0, ?, 'TechMG', 0, ?)";
		
		int insert_Monthly_report_result = 0;
		int monthly_report_id = 0;
		int insert_Monthly_Report_ApplyList_result = 0;
		
		try {
			con = BaseDAO.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(insert_Monthly_Report_Sql);
			
			pstmt.setInt(1, monthly_report_DTO.getTarget_year());
			pstmt.setInt(2, monthly_report_DTO.getTarget_month());
			pstmt.setString(3, monthly_report_DTO.getUser_id());
			pstmt.setInt(4, monthly_report_DTO.getWorkingDay());
			pstmt.setDouble(5, monthly_report_DTO.getTotal_working_hours());
			pstmt.setDouble(6, monthly_report_DTO.getTotal_overtime_hours());
			pstmt.setInt(7, monthly_report_DTO.getWork_on_a_day());
			pstmt.setDouble(8, monthly_report_DTO.getTotal_work_on_a_day_Hour());
			pstmt.setDouble(9, monthly_report_DTO.getTotal_work_on_a_day_OverTime());
			pstmt.setDate(10, monthly_report_DTO.getUpdated_date());
			pstmt.setString(11, monthly_report_DTO.getUpdated_user());
			
			insert_Monthly_report_result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = con.prepareStatement(select_Monthly_Report_Sql);
			
			pstmt.setInt(1, monthly_report_DTO.getTarget_year());
			pstmt.setInt(2, monthly_report_DTO.getTarget_month());
			pstmt.setString(3, monthly_report_DTO.getUser_id());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				monthly_report_id = rs.getInt("monthly_report_id");
			}
			
			pstmt.close();
			
			pstmt = con.prepareStatement(insert_Monthly_Report_ApplyList_Sql);
			
			pstmt.setInt(1, monthly_report_id);
			pstmt.setString(2, smg_user_id);
			pstmt.setString(3, mg_user_id);
			pstmt.setDate(4, monthly_report_DTO.getUpdated_date());
			
			insert_Monthly_Report_ApplyList_result = pstmt.executeUpdate();
			
			con.commit();
			
			pstmt.close();
			
		} catch(SQLException e) {
			System.out.println("Monthly_Report、Monthly_Report_ApplyListテーブルへの情報登録、取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ignore) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
		}
		errorMsg = checkError(insert_Monthly_report_result, monthly_report_id, insert_Monthly_Report_ApplyList_result);
		return errorMsg;
		
	}
	
public String applyMonthlyReport(String approve_user_id, String role_id, Monthly_Report_DTO monthly_report_DTO ) {
		
		String errorMsg = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String insert_Monthly_Report_Sql = "INSERT INTO Monthly_Report(target_year, target_month, user_id, workingDay, total_working_hours, "
										+ "total_overtime_hours, work_on_a_day, totalWork_on_a_day_Hour, totalWork_on_a_day_OverTime, updated_date, updated_user) "
										+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String select_Monthly_Report_Sql = "SELECT monthly_report_id FROM Monthly_Report WHERE target_year = ? AND target_month = ? AND user_id = ?";
		
		String insert_Monthly_Report_ApplyList_Sql = "INSERT INTO Monthly_Report_ApplyList(monthly_report_id, approve_level, approve_user_id_1, approve_role_1, "
													+ "approve_1, updated_date) "
													+ "VALUES(?, 1, ?, ?, 0, ?)";
		
		int insert_Monthly_report_result = 0;
		int monthly_report_id = 0;
		int insert_Monthly_Report_ApplyList_result = 0;
		
		try {
			con = BaseDAO.getConnection();
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(insert_Monthly_Report_Sql);
			
			pstmt.setInt(1, monthly_report_DTO.getTarget_year());
			pstmt.setInt(2, monthly_report_DTO.getTarget_month());
			pstmt.setString(3, monthly_report_DTO.getUser_id());
			pstmt.setInt(4, monthly_report_DTO.getWorkingDay());
			pstmt.setDouble(5, monthly_report_DTO.getTotal_working_hours());
			pstmt.setDouble(6, monthly_report_DTO.getTotal_overtime_hours());
			pstmt.setInt(7, monthly_report_DTO.getWork_on_a_day());
			pstmt.setDouble(8, monthly_report_DTO.getTotal_work_on_a_day_Hour());
			pstmt.setDouble(9, monthly_report_DTO.getTotal_work_on_a_day_OverTime());
			pstmt.setDate(10, monthly_report_DTO.getUpdated_date());
			pstmt.setString(11, monthly_report_DTO.getUpdated_user());
			
			insert_Monthly_report_result = pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt = con.prepareStatement(select_Monthly_Report_Sql);
			
			pstmt.setInt(1, monthly_report_DTO.getTarget_year());
			pstmt.setInt(2, monthly_report_DTO.getTarget_month());
			pstmt.setString(3, monthly_report_DTO.getUser_id());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				monthly_report_id = rs.getInt("monthly_report_id");
			}
			
			pstmt.close();
			
			pstmt = con.prepareStatement(insert_Monthly_Report_ApplyList_Sql);
			
			pstmt.setInt(1, monthly_report_id);
			pstmt.setString(2, approve_user_id);
			pstmt.setString(3, role_id);
			pstmt.setDate(4, monthly_report_DTO.getUpdated_date());
			
			insert_Monthly_Report_ApplyList_result = pstmt.executeUpdate();
			
			con.commit();
			
			pstmt.close();
			
		} catch(SQLException e) {
			System.out.println("Monthly_Report、Monthly_Report_ApplyListテーブルへの情報登録、取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ignore) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
		}
		errorMsg = checkError(insert_Monthly_report_result, monthly_report_id, insert_Monthly_Report_ApplyList_result);
		return errorMsg;
		
	}
	
	private String checkError(int insert_Monthly_report_result, int monthly_report_id, int insert_Monthly_Report_ApplyList_result) {
		String errorMsg = "";
		String lineCd = System.getProperty("line.separator");
		
		if(insert_Monthly_report_result != 1) {
			errorMsg += "Monthly_Reportテーブルへの登録に失敗しました" + lineCd;
		}
		if(monthly_report_id == 0) {
			errorMsg += "Monthly_ReportテーブルからMonthly_Report_idを取得できませんでした。" + lineCd;
		}
		if(insert_Monthly_Report_ApplyList_result != 1) {
			errorMsg += "Monthly_Report_ArrayListテーブルへの登録に失敗しました";
		}
		return errorMsg;
	}
}
