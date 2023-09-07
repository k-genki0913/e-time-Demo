package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConstractTransaction_DAO;
import dto.ConstractErrorMsg;
import dto.Constract_DTO;
import dto.User_Constract_DTO;
import model.ConstractInputCheck;
import model.CreateConstract_DTO;
import model.CreateUser_Constract_DTO;
import model.UpdateDate;

/**
 * Servlet implementation class InputConstractServlet
 */
@WebServlet("/InputConstractServlet")
public class InputConstractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/inputConstract.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String workplace = request.getParameter("workplace");
		String constract_start = request.getParameter("constract_start");
		String constract_end = request.getParameter("constract_end");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String break_time = request.getParameter("break_time");
		String user_id = request.getParameter("user_id");
		
		ConstractErrorMsg constractErrorMsg = new ConstractInputCheck().checkError(workplace, constract_start, constract_end, start_time, end_time, break_time, user_id);
		if(constractErrorMsg.getWorkplace_errorMsg() != null || constractErrorMsg.getConstract_start_errorMsg() != null || constractErrorMsg.getConstract_end_errorMsg() != null ||constractErrorMsg.getStart_time_errorMsg() != null || constractErrorMsg.getEnd_time_errorMsg() != null || constractErrorMsg.getBreak_time_errorMsg() != null || constractErrorMsg.getUser_id_errorMsg() != null) {
			if(constractErrorMsg.getWorkplace_errorMsg() != null) {
				String workplace_errorMsg = constractErrorMsg.getWorkplace_errorMsg();
				request.setAttribute("workplace_errorMsg", workplace_errorMsg);
			}
			
			if(constractErrorMsg.getConstract_start_errorMsg() != null) {
				String constract_start_errorMsg = constractErrorMsg.getConstract_start_errorMsg();
				request.setAttribute("constract_start_errorMsg", constract_start_errorMsg);
			}
			
			if(constractErrorMsg.getConstract_end_errorMsg() != null) {
				String constract_end_errorMsg = constractErrorMsg.getConstract_end_errorMsg();
				request.setAttribute("constract_end_errorMsg", constract_end_errorMsg);
			}
			
			if(constractErrorMsg.getStart_time_errorMsg() != null) {
				String start_time_errorMsg = constractErrorMsg.getStart_time_errorMsg();
				request.setAttribute("start_time_errorMsg", start_time_errorMsg);
			}
			if(constractErrorMsg.getEnd_time_errorMsg() != null) {
				String end_time_errorMsg = constractErrorMsg.getEnd_time_errorMsg();
				request.setAttribute("end_time_errorMsg", end_time_errorMsg);
			}
			if(constractErrorMsg.getBreak_time_errorMsg() != null) {
				String break_time_errorMsg = constractErrorMsg.getBreak_time_errorMsg();
				request.setAttribute("break_time_errorMsg", break_time_errorMsg);
			}
			if(constractErrorMsg.getUser_id_errorMsg() != null) {
				String user_id_errorMsg = constractErrorMsg.getUser_id_errorMsg();
				request.setAttribute("user_id_errorMsg", user_id_errorMsg);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/inputConstract.jsp");
			dispatcher.forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		String update_user = (String)session.getAttribute("user_id");
		Date update_date = new UpdateDate().getUpdateDate();
		
		User_Constract_DTO user_constract_DTO = new CreateUser_Constract_DTO().setInput(user_id, constract_start, constract_end, update_user, update_date);
		
		Constract_DTO constract_DTO = new CreateConstract_DTO().put(workplace, start_time, end_time, break_time);
		
		ConstractTransaction_DAO constractTransaction_DAO = new ConstractTransaction_DAO();
		String registErrorMsg = constractTransaction_DAO.constractRegist(user_constract_DTO, constract_DTO);
		
		if(registErrorMsg != "") {
			request.setAttribute("registErrorMsg", registErrorMsg);;
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/inputConstract.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		response.sendRedirect("ConstractListServlet");
	}

}
