package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Clock_in_DAO;
import model.ReportDataCheck;

/**
 * Servlet implementation class CreateCalendarServlet
 */
@WebServlet("/CreateCalendarServlet")
public class CreateCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		String calendarErrorMsg = request.getParameter("calendarErrorMsg");
		
		if(calendarErrorMsg != null) {
			request.setAttribute("calendarErrorMsg", calendarErrorMsg);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/createMonthlyReportCalendar.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String clock_in_date_branch = year + month + "0101";
		
		boolean reportExist = new ReportDataCheck().reportExist(user_id, clock_in_date_branch);
		if(reportExist) {
			response.sendRedirect("CalendarServlet");
			return;
		}
		
		Integer int_year = Integer.parseInt(year);
		Integer int_month = Integer.parseInt(month);
		
		Clock_in_DAO clock_in_DAO = new Clock_in_DAO();
		boolean createResult = clock_in_DAO.addCalendar_Clock_in_DAO(user_id, int_year, int_month);
		if(createResult) {
			request.setAttribute("resultMsg", "登録完了しました");
		} else {
			request.setAttribute("resultMsg", "登録に失敗しました");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/createMonthlyReportCalendar.jsp");
		dispatcher.forward(request, response);
	}

}
