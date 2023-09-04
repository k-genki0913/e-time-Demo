package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import dao.Clock_in_DAO;
import dto.Clock_in_DTO;
import dto.ReportErrorMsg;
import model.CreateClock_in_DTO;
import model.ReportDataCheck;
import model.ReportInputCheck;

/**
 * Servlet implementation class MonthlyReportServlet
 */
@WebServlet("/MonthlyReportServlet")
public class MonthlyReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/monthlyReport.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String input_start_time = request.getParameter("start_time");
		String input_end_time = request.getParameter("end_time");
		String input_break_time = request.getParameter("break_time");
		ReportErrorMsg reportErrorMsg = new ReportInputCheck().checkError(input_start_time, input_end_time, input_break_time);
		if(reportErrorMsg.getStart_time_errorMsg() != null || reportErrorMsg.getEnd_time_errorMsg() != null || reportErrorMsg.getBreak_time_errorMsg() != null) {
			if(reportErrorMsg.getStart_time_errorMsg() != null) {
				String start_time_errorMsg = reportErrorMsg.getStart_time_errorMsg();
				request.setAttribute("start_time_errorMsg", start_time_errorMsg);
			}
			if(reportErrorMsg.getEnd_time_errorMsg() != null) {
				String end_time_errorMsg = reportErrorMsg.getEnd_time_errorMsg();
				request.setAttribute("end_time_errorMsg", end_time_errorMsg);
			}
			if(reportErrorMsg.getBreak_time_errorMsg() != null) {
				String break_time_errorMsg = reportErrorMsg.getBreak_time_errorMsg();
				request.setAttribute("break_time_errorMsg", break_time_errorMsg);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/monthlyReport.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		String year = request.getParameter("year");
		String month = StringUtils.leftPad(request.getParameter("month"), 2, "0");
		String day = StringUtils.leftPad(request.getParameter("day"), 2, "0");
		String day_of_week = request.getParameter("day_of_week");
		String user_id = request.getParameter("user_id");
		String branch = request.getParameter("branch");
		String work_pattern = request.getParameter("work_pattern");
		String work_category = request.getParameter("work_category");
		String work_remarks = request.getParameter("work_remarks");
		String work_on_a_day = request.getParameter("work_on_a_day");
		
		Clock_in_DTO clock_in_DTO = new CreateClock_in_DTO().get(year, month, day, user_id, branch, work_pattern,
				work_category, input_start_time, input_end_time, input_break_time, work_remarks, work_on_a_day);
		
		ReportDataCheck reportDataCheck = new ReportDataCheck();
		boolean reportExist = reportDataCheck.reportExist(clock_in_DTO.getUser_id(), clock_in_DTO.getClock_in_date_branch());
		
		boolean sqlResult = false;
		
		if(reportExist) {
			sqlResult = new Clock_in_DAO().updateClock_in_DAO(clock_in_DTO);
		} else {
			sqlResult = new Clock_in_DAO().insertClock_in_DAO(clock_in_DTO);
		}
		
		if(sqlResult = false) {
			request.setAttribute("registErrorMsg", "登録できませんでした");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/monthlyReport.jsp");
			dispatcher.forward(request, response);
			return ;
		} else {
			response.sendRedirect("CalendarServlet");
		}
		
	}

}
