package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Monthly_Report_ApplyList_DAO;
import dao.Monthly_Report_DAO;
import dao.User_Role_DAO;
import dto.Monthly_Report_DTO;
import model.Monthly_Report_List;

/**
 * Servlet implementation class MonthlyReportApproveServlet
 */
@WebServlet("/MonthlyReportApproveServlet")
public class MonthlyReportApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		Integer monthly_report_id = Integer.parseInt(request.getParameter("monthly_report_id"));
		
		Monthly_Report_DTO monthly_report_DTO = new Monthly_Report_DAO().getMonthly_Report(monthly_report_id);
		
		if(monthly_report_DTO == null) {
			String errorMsg = "承認画面へ移動できませんでした。管理者に連絡してください";
			request.setAttribute("errorMsg", errorMsg);
			
			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("user_id");
			List<Monthly_Report_DTO> applyList = new Monthly_Report_List().get(user_id);
			
			request.setAttribute("applyList", applyList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/applyList.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("monthly_report_DTO", monthly_report_DTO);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approveMonthlyReport.jsp");
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
		String role_id = new User_Role_DAO().getUser_Role(user_id).getRole_id();
		
		Integer monthly_report_id = Integer.parseInt(request.getParameter("monthly_report_id"));
		String judgement = request.getParameter("judgement");
		
		Monthly_Report_ApplyList_DAO applyList_DAO = new Monthly_Report_ApplyList_DAO();
		boolean result = false;
		
		if(role_id.equals("TechSBM")) {
			if(judgement.equals("approve")) {
				result = applyList_DAO.techSMG_FirstApprove(monthly_report_id, 2, 1);
			} else if(judgement.equals("denial")) {
				result = applyList_DAO.techSMG_FirstApprove(monthly_report_id, 0, 99);
			}
		} else {
			
		}
		
	}

}
