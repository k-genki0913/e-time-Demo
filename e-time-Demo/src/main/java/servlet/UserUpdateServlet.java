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

import dao.UserUpdateTransactionDAO;
import dao.User_Department_DAO;
import dao.User_Password_DAO;
import dao.User_Role_DAO;
import dao.Users_DAO;
import dto.User_Department_DTO;
import dto.User_Password_DTO;
import dto.User_Role_DTO;
import dto.Users_DTO;
import model.UpdateDate;
import model.UserRegistCheck;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String user_id = (String) request.getParameter("user_id");
		
		Users_DTO users_DTO = new Users_DAO().getUser(user_id);
		User_Password_DTO user_password_DTO = new User_Password_DAO().getUser_Password(user_id);
		User_Role_DTO user_role_DTO = new User_Role_DAO().getUser_Role(user_id);
		User_Department_DTO user_department_DTO = new User_Department_DAO().getUser_Department(user_id);
		
		request.setAttribute("users_DTO", users_DTO);
		request.setAttribute("user_password_DTO", user_password_DTO);
		request.setAttribute("user_role_DTO", user_role_DTO);
		request.setAttribute("user_department_DTO", user_department_DTO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userUpdateErrorMsg = null;
		UserRegistCheck userRegistCheck = new UserRegistCheck();
		
		String user_id = request.getParameter("user_id");
		String new_password = request.getParameter("password");
		String present_password = request.getParameter("present_password");
		String name = request.getParameter("name");
		String mail_address = request.getParameter("mail_address");
		Integer admin = Integer.parseInt(request.getParameter("admin"));
		Integer is_valid = Integer.parseInt(request.getParameter("is_valid"));
		String role_id = request.getParameter("role");
		Integer department_id = Integer.parseInt(request.getParameter("department"));
		
		System.out.println(new_password);
		
		if(new_password != null || new_password != "" || new_password.length() != 0) {
			userUpdateErrorMsg = userRegistCheck.inputCheck(user_id, new_password);
			if(!userUpdateErrorMsg.equals("")) {
			request.setAttribute("userUpdateErrorMsg", userUpdateErrorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;
			}
		}
		
		HttpSession session = request.getSession();
		String userRegister = (String) session.getAttribute("user_id");
		Date updateDate = new UpdateDate().getUpdateDate();
		
		Users_DTO users_DTO = new Users_DTO(user_id, name, mail_address, admin, is_valid, updateDate, userRegister);
		User_Password_DTO user_password_DTO = null;
		if(new_password != null || new_password != "" || new_password.length() != 0) {
			user_password_DTO = new User_Password_DTO(user_id, new_password, updateDate, userRegister);
		} else {
			user_password_DTO = new User_Password_DTO(user_id, present_password, updateDate, userRegister);
		}
		User_Role_DTO user_role_DTO = new User_Role_DTO(user_id, role_id, updateDate, userRegister);
		User_Department_DTO user_department_DTO = new User_Department_DTO(user_id, department_id, updateDate, userRegister);
		
		userUpdateErrorMsg = new UserUpdateTransactionDAO().userUpdate(users_DTO, user_password_DTO, user_role_DTO, user_department_DTO);
		
		if(!userUpdateErrorMsg.equals("")) {
			request.setAttribute("userUpdateErrorMsg", userUpdateErrorMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userUpdateComplete.jsp");
		dispatcher.forward(request, response);
	}

}
