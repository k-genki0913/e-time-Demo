package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Monthly_Report_ApplyList_DTO;

public class Monthly_Report_ApplyList_DAO {
	public List<Monthly_Report_ApplyList_DTO> getList(String user_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Monthly_Report_ApplyList_DTO> list = new ArrayList<>();
		
		String sql = "SELECT monthly_report_id, approve_level, approve_user_id_1, approve_role_1, "
				+ "approve_1, comment_1, approve_date_1, approve_user_id_2, approve_role_2, approve_2, "
				+ "comment_2, approve_date_2, updated_date FROM Monthly_Report_ApplyList "
				+ "WHERE (approve_user_id_1 = ? AND approve_1 = 0) or (approve_user_id_2 = ? AND approve_2 = 0)";
		
		try {
			con = BaseDAO.getConnection();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Monthly_Report_ApplyList_DTO dto = new Monthly_Report_ApplyList_DTO();
				dto.setMonthly_report_id(rs.getInt("monthly_report_id"));
				dto.setApprove_level(rs.getInt("approve_level"));
				dto.setApprove_user_id_1(rs.getString("approve_user_id_1"));
				dto.setApprove_role_1(rs.getString("approve_role_1"));
				dto.setApprove_1(rs.getInt("approve_1"));
				dto.setComment_1(rs.getString("comment_1"));
				dto.setApprove_date_1(rs.getDate("approve_date_1"));
				dto.setApprove_user_id_2(rs.getString("approve_user_id_2"));
				dto.setApprove_role_2(rs.getString("approve_role_2"));
				dto.setApprove_2(rs.getInt("approve_2"));
				dto.setComment_2(rs.getString("comment_2"));
				dto.setApprove_date_2(rs.getDate("approve_date_2"));
				dto.setUpdated_date(rs.getDate("updated_date"));
				list.add(dto);
				
			}
		} catch(SQLException e) {
			System.out.println("Monthly_Report_ApplyListテーブルからの取得に失敗しました");
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
		return list;
	}
}
