package model;

import dao.MonthlyReportApplyTransaction;
import dao.User_Role_DAO;
import dto.Monthly_Report_DTO;
import dto.User_Role_DTO;

public class MonthlyReportApply {
	public String regist(Monthly_Report_DTO monthly_report_DTO, String smg_user_id, String mg_user_id, String gm_user_id, String role_id) {
		String errorMsg = "";
		boolean result = false;
		if(role_id.equals("Member")) {
			User_Role_DTO sbm_user_role_DTO = new User_Role_DAO().getUser_Role(smg_user_id);
			User_Role_DTO mg_user_role_DTO = new User_Role_DAO().getUser_Role(mg_user_id);
			errorMsg = new MonthlyReportApplyTransaction().applyMonthlyReport(monthly_report_DTO, sbm_user_role_DTO.getUser_id(), mg_user_role_DTO.getUser_id());
			
		} else if(role_id.equals("TechSBM")) {
			User_Role_DTO mg_user_role_DTO = new User_Role_DAO().getUser_Role(mg_user_id);
			errorMsg = new MonthlyReportApplyTransaction().applyMonthlyReport(mg_user_role_DTO.getUser_id(), "TechMG", monthly_report_DTO);
		} else if(role_id.equals("TechMG")) {
			User_Role_DTO gm_user_role_DTO = new User_Role_DAO().getUser_Role(gm_user_id);
			errorMsg = new MonthlyReportApplyTransaction().applyMonthlyReport(gm_user_role_DTO.getUser_id(), "GM", monthly_report_DTO);
		}
		
		return errorMsg;
	}
}
