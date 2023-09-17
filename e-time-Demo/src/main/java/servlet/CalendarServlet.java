package servlet;

import java.io.IOException;
import java.sql.Date;
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
import model.TotalWorkingTime;
import model.WorkingDay;

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
		List<Date> workingDayList = new WorkingDay().getNormalDay(calendarList);
		List<Date> work_on_a_dayList = new WorkingDay().getWork_on_a_day(calendarList);
		Integer workingDay = workingDayList.size();
		Integer work_on_a_Day = work_on_a_dayList.size();
		if(!(work_on_a_Day > 0)) {
			work_on_a_Day = 0;
		}
		double totalWorkTime = new TotalWorkingTime().execute(calendarList, workingDay);
		double totalWortHour = totalWorkTime / 60.0;
		double totalWorkHourRound = ((double)Math.round(totalWortHour * 100)) / 100;
		double totalOverTime = ((double)totalWorkTime - (480 * workingDay)) / 60;
		double totalOverTimeRound = ((double)Math.round(totalOverTime * 100)) / 100;
		
		double totalWork_on_a_day_Time = new TotalWorkingTime().work_on_a_day_execute(calendarList, work_on_a_Day);
		Double totalWork_on_a_day_Hour = totalWork_on_a_day_Time / 60.0;
		double totalWork_on_a_day_OverTime = ((double)totalWork_on_a_day_Time - (480 * work_on_a_Day)) / 60;
		Calendar calendar = Calendar.getInstance();
		request.setAttribute("calendar", calendar);
		request.setAttribute("calendarList", calendarList);
		request.setAttribute("workingDay", workingDay);
		request.setAttribute("totalWorkTime", totalWorkHourRound);
		request.setAttribute("totalOverTime", totalOverTimeRound);
		request.setAttribute("work_on_a_Day", work_on_a_Day);
		request.setAttribute("totalWork_on_a_day_Hour", totalWork_on_a_day_Hour);
		request.setAttribute("totalWork_on_a_day_OverTime", totalWork_on_a_day_Hour);
		
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
		Integer month = Integer.parseInt(request.getParameter("month")) - 1;
		String change = request.getParameter("change");
		
		if(change.equals("last")) {
			month -= 1;
		} else if(change.equals("next")) {
			month += 1;
		}
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		List<Clock_in_DTO> calendarList = new GetCalendar().change(user_id, year, (month + 1));
		if(calendarList.size() == 0) {
			response.sendRedirect("CreateCalendarServlet");
			return;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		
		List<Date> workingDayList = new WorkingDay().getNormalDay(calendarList);
		List<Date> work_on_a_dayList = new WorkingDay().getWork_on_a_day(calendarList);
		Integer workingDay = workingDayList.size();
		Integer work_on_a_Day = work_on_a_dayList.size();
		if(!(work_on_a_Day > 0)) {
			work_on_a_Day = 0;
		}
		double totalWorkTime = new TotalWorkingTime().execute(calendarList, workingDay);
		double totalWortHour = totalWorkTime / 60.0;
		double totalWorkHourRound = ((double)Math.round(totalWortHour * 100)) / 100;
		double totalOverTime = ((double)totalWorkTime - (480 * workingDay)) / 60;
		double totalOverTimeRound = ((double)Math.round(totalOverTime * 100)) / 100;
		
		double totalWork_on_a_day_Time = new TotalWorkingTime().work_on_a_day_execute(calendarList, work_on_a_Day);
		Double totalWork_on_a_day_Hour = totalWork_on_a_day_Time / 60.0;
		double totalWork_on_a_day_OverTime = ((double)totalWork_on_a_day_Time - (480 * work_on_a_Day)) / 60;
		
		request.setAttribute("calendar", calendar);
		request.setAttribute("calendarList", calendarList);
		request.setAttribute("workingDay", workingDay);
		request.setAttribute("totalWorkTime", totalWorkHourRound);
		request.setAttribute("totalOverTime", totalOverTimeRound);
		request.setAttribute("work_on_a_Day", work_on_a_Day);
		request.setAttribute("totalWork_on_a_day_Hour", totalWork_on_a_day_Hour);
		request.setAttribute("totalWork_on_a_day_OverTime", totalWork_on_a_day_Hour);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/monthlyReportCalendar.jsp");
		dispatcher.forward(request, response);
	}

}
