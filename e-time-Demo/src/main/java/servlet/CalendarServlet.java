package servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Clock_in_DTO;
import model.GetCalendar;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		List<Clock_in_DTO> calendarList = new GetCalendar().put(user_id);
		if(calendarList.size() == 0) {
			String calendarErrorMsg = "カレンダーの取得に失敗しました";
			request.setAttribute("calendarErrorMsg", calendarErrorMsg);
			response.sendRedirect("CreateCalendarServlet");
			return;
		}
		Calendar calendar = Calendar.getInstance();
		request.setAttribute("calendar", calendar);
		request.setAttribute("calendarList", calendarList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/monthlyReportCalendar.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		String change = request.getParameter("change");
		
		if(change.equals("last")) {
			month -= 1;
		} else if(change.equals("next")) {
			month += 1;
		}
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		List<Clock_in_DTO> calendarList = new GetCalendar().change(user_id, year, month);
		if(calendarList.size() == 0) {
			response.sendRedirect("CreateCalendarServlet");
			return;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		
		request.setAttribute("calendar", calendar);
		request.setAttribute("calendarList", calendarList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/monthlyReportCalendar.jsp");
		dispatcher.forward(request, response);
	}

}
