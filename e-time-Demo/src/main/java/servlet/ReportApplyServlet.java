package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.User_Department_DAO;
import dao.User_Role_DAO;
import dao.Users_UserRole_DAO;
import dao.Users_UsersRole_UserDepartment_DAO;
import dto.Monthly_Report_DTO;
import dto.User_Department_DTO;
import dto.User_Role_DTO;
import model.MonthlyReportApply;
import model.ReportApplyCheck;
import model.UpdateDate;

/**
 * Servlet implementation class ReportApplyServlet
 */
@WebServlet("/ReportApplyServlet")
public class ReportApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		request.setCharacterEncoding("UTF-8");
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		Integer workingDay = Integer.parseInt(request.getParameter("workingDay"));
		double totalWorkTime = Double.parseDouble(request.getParameter("totalWorkTime"));
		double totalOverTime = Double.parseDouble(request.getParameter("totalOverTime"));
		Integer work_on_a_day = Integer.parseInt(request.getParameter("work_on_a_Day"));
		double totalWork_on_a_day_Hour = Double.parseDouble(request.getParameter("totalWork_on_a_day_Hour"));
		double totalWork_on_a_day_OverTime = Double.parseDouble(request.getParameter("totalWork_on_a_day_OverTime"));
		
		boolean existCheck = new ReportApplyCheck().exist(user_id, year, month);
		
		if(existCheck) {
			request.setAttribute("yaer", year);
			request.setAttribute("month", month);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/monthlyReportApplyError.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		User_Department_DTO user_Department_DTO = new User_Department_DAO().getUser_Department(user_id);
		User_Role_DTO user_Role_DTO = new User_Role_DAO().getUser_Role(user_id);
		String applyRole = user_Role_DTO.getRole_id();
		Integer applyDepartment = user_Department_DTO.getDepartment_id();
		
		
		if(applyRole.equals("Member")) {
			Map<String, String> smg_user_id = new Users_UsersRole_UserDepartment_DAO().getDepartmentRoleUser(applyDepartment, "TechSBM");
			Map<String, String> mg_user_id = new Users_UsersRole_UserDepartment_DAO().getDepartmentRoleUser(applyDepartment, "TechMG");
			request.setAttribute("smg_user_id", smg_user_id);
			request.setAttribute("mg_user_id", mg_user_id);
		}
		if(applyRole.equals("TechSBM")) {
			Map<String, String> mg_user_id = new Users_UsersRole_UserDepartment_DAO().getDepartmentRoleUser(applyDepartment, "TechMG");
			request.setAttribute("mg_user_id", mg_user_id);
		}
		if(applyRole.equals("TechMG")) {
			Map<String, String> gm_user_id = new Users_UserRole_DAO().getRoleUser("GM");
			request.setAttribute("gm_user_id", gm_user_id);
		}
		Monthly_Report_DTO monthly_report_DTO = new Monthly_Report_DTO();
		monthly_report_DTO.setTarget_year(year);
		monthly_report_DTO.setTarget_month(month);
		monthly_report_DTO.setWorkingDay(workingDay);
		monthly_report_DTO.setTotal_working_hours(totalWorkTime);
		monthly_report_DTO.setTotal_overtime_hours(totalOverTime);
		monthly_report_DTO.setWork_on_a_day(work_on_a_day);
		monthly_report_DTO.setTotal_work_on_a_day_Hour(totalWork_on_a_day_Hour);
		monthly_report_DTO.setTotal_work_on_a_day_OverTime(totalWork_on_a_day_OverTime);
		request.setAttribute("monthly_report_DTO", monthly_report_DTO);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/applyConfirm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		request.setCharacterEncoding("UTF-8");
		String applyRole = request.getParameter("applyRole");
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		Integer workingDay = Integer.parseInt(request.getParameter("workingDay"));
		double totalWorkTime = Double.parseDouble(request.getParameter("totalWorkTime"));
		double totalOverTime = Double.parseDouble(request.getParameter("totalOverTime"));
		Integer work_on_a_day = Integer.parseInt(request.getParameter("work_on_a_Day"));
		double totalWork_on_a_day_Hour = Double.parseDouble(request.getParameter("totalWork_on_a_day_Hour"));
		double totalWork_on_a_day_OverTime = Double.parseDouble(request.getParameter("totalWork_on_a_day_OverTime"));
		
		Date updateDate = new UpdateDate().getUpdateDate();
		
		Monthly_Report_DTO monthly_report_DTO = new Monthly_Report_DTO(year, month, user_id, workingDay,totalWorkTime, totalOverTime,
					work_on_a_day, totalWork_on_a_day_Hour, totalWork_on_a_day_OverTime, updateDate, user_id);
		
		String role_id = new User_Role_DAO().getUser_Role(user_id).getRole_id();
		String smg_user_id = request.getParameter("smg_user_id");
		String mg_user_id = request.getParameter("mg_user_id");
		String gm_user_id = request.getParameter("gm_user_id");
		
		String errorMsg = new MonthlyReportApply().regist(monthly_report_DTO, smg_user_id, mg_user_id, gm_user_id, role_id);
		if(errorMsg.equals("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/applyComplete.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/applyConfirm.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

}
