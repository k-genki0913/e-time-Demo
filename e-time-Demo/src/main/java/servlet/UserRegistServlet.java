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

import dto.User_Department_DTO;
import dto.User_Password_DTO;
import dto.User_Role_DTO;
import dto.Users_DTO;
import model.ExistCheck;
import model.InputCheck;
import model.UpdateDate;
import model.UserRegist;
import model.UserRegistCheck;

/**
 * Servlet implementation class UserRegistServlet
 */
@WebServlet("/UserRegistServlet")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		InputCheck inputCheck = new InputCheck();
		ExistCheck existCheck = new ExistCheck();
		String userRegistErrorMsg = null;
		UserRegistCheck userRegistCheck = new UserRegistCheck();
		
		//入力値を取得
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String mail_address = request.getParameter("mail_address");
		String inputAdmin = request.getParameter("admin");
		Integer admin = Integer.parseInt(inputAdmin);
		String role_id = request.getParameter("role");
		String department = request.getParameter("department");
		Integer department_id = Integer.parseInt(department);
		
		//入力チェック：入力値が仕様に沿っているか確認し、沿っていない場合はuserRegistErrorMsgにエラー内容を格納してuserRegist.jspへ戻す
		userRegistErrorMsg = userRegistCheck.inputCheck(user_id, password);
		if(!userRegistErrorMsg.equals("")) {
			request.setAttribute("input_user_id", user_id);
			request.setAttribute("name", name);
			request.setAttribute("mail_address", mail_address);
			request.setAttribute("admin", admin);
			request.setAttribute("role_id", role_id);
			request.setAttribute("department_id", department_id);
			request.setAttribute("userRegistErrorMsg", userRegistErrorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegist.jsp");
			dispatcher.forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		String userRegister = (String) session.getAttribute("user_id");
		Date updateDate = new UpdateDate().getUpdateDate();
				
		Users_DTO users_DTO = new Users_DTO(user_id, name, mail_address, admin, updateDate, userRegister);	
		User_Password_DTO user_Password_DTO = new User_Password_DTO(user_id, password, updateDate, userRegister);
		User_Role_DTO user_Role = new User_Role_DTO(user_id, role_id, updateDate, userRegister);
		User_Department_DTO user_Department_DTO = new User_Department_DTO(user_id, department_id, updateDate, userRegister);
		
		String daoRegistErrorMsg = new UserRegist().registResult(users_DTO, user_Password_DTO, user_Role, user_Department_DTO);
		
		if(!daoRegistErrorMsg.equals("")) {
			request.setAttribute("daoRegistErrorMsg", daoRegistErrorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegist.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//response.sendRedirect("UserRegistComplete.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegistComplete.jsp");
		dispatcher.forward(request, response);
	}

}
