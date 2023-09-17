package model;

import java.util.List;

import dao.Monthly_Report_DAO;
import dto.Monthly_Report_DTO;

public class ReportApplyCheck {
	public boolean exist(String user_id, int year, int month) {
		Monthly_Report_DAO monthly_report_DAO = new Monthly_Report_DAO();
		List<Monthly_Report_DTO> monthly_report_list = monthly_report_DAO.getMonthly_Report_not_id(user_id, year, month);
		
		boolean result = true;
		
		if(monthly_report_list.size() == 0) {
			result = false;
		} 
		
		return result;
	}
}
