package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User_Password_DTO;
import model.InputCheck;
import model.LoginCheck;
import model.RegistItemList;
import model.ValidCheck;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** Session Scopeにログイン情報があるか確認  */
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		/** forward先を格納するためのローカル変数 */
		String url;
		
		/** Session Scopeのログイン情報の有無でforward先を決定 */
		if(user_id != null) {
			/** ログイン情報がある場合はHome画面へ */
			url = "WEB-INF/jsp/home.jsp";
		} else {
			/** ログイン情報がない場合はlogin画面へ */
			url = "WEB-INF/jsp/login.jsp";
		}
		
		/** forward先情報を元にforward */
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/** login.jspからPOSTされたuser_id、passwordを取得 */	
		String input_user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String lineCd = System.getProperty("line.separator");
		String url;
		String inputErrorMsg;
		ValidCheck validCheck = new ValidCheck();
		
		/*
		 * 入力値(user_id、password)のチェックをLoginInputCheckを用いて行う
		 */
		InputCheck loginInputCheck = new InputCheck();
		boolean bool_user_id = loginInputCheck.inputUserIDCheck(input_user_id);
		boolean input_password = loginInputCheck.inputPasswordCheck(password);
		/*
		 * 入力されたuser_idのis_validが有効(true)か無効(false)か判断し、
		 * 向こうの場合はinputErrorMsgに文を格納してlogin.jspへ戻す
		 * 入力チェック(LoginInputCheck)でuser_id、passwordどちらも正しかった場合にLoginCheckを行う
		 */
		if(validCheck.getIs_Valid(input_user_id) == 0) {
			url = "WEB-INF/jsp/login.jsp";
			inputErrorMsg = "このユーザーは無効となっております。管理者へ問い合わせてください";
			request.setAttribute("inputErrorMsg", inputErrorMsg);
		} else if(bool_user_id && input_password) {
			LoginCheck loginCheck = new LoginCheck();
			User_Password_DTO userLoginDTO = loginCheck.loginCheck(input_user_id, password);
			if(userLoginDTO.getLogin_result()) {
				HttpSession session = request.getSession();
				
				RegistItemList registItemList = new RegistItemList();
				
				Map<String, String> roleList = registItemList.getRoleList();
				
				Map<Integer, String> departmentList= registItemList.getDepartmentList();
				
				session.setAttribute("roleList", roleList);
				session.setAttribute("departmentList", departmentList);
				
				/*
				 * LoginCheckによってUserLoginDTOがTRUEと判断された場合、Home画面にRedirectし、
				 * session scopeにuser_idを格納する
				 */ 
				
				session.setAttribute("user_id", input_user_id);
				response.sendRedirect("HomeServlet");
				return;
			} else {
				/*
				 * UserLoginDTOがFALSEと判断された場合、forward先をlogin画面にし
				 * inputErrorMsgにエラー内容を追記し、inputErrorMsgをrequest scopeへ格納する
				 */
				url = "WEB-INF/jsp/login.jsp";
				if(userLoginDTO.getUser_id().equals("0000000000")) {
					inputErrorMsg = "ユーザーが存在しません";
				} else if(userLoginDTO.getInvalid_count() > 5) {
					inputErrorMsg = "アカウントがロックされました。管理者に問い合わせてください";
				} else {
					inputErrorMsg = "パスワードが間違っております";
				}
				request.setAttribute("inputErrorMsg", inputErrorMsg);
			}
		} else {
			/*
			 * 入力値チェックでuser_id、passwordのどちらかが間違っていた場合、forward先をlogin画面にし
			 * inputErrorMsgにエラー内容を追記し、inputErrorMsgをrequest scopeへ格納する
			 */
			url = "WEB-INF/jsp/login.jsp";
			inputErrorMsg = "ログインIDが数字10桁か確認してください。" + lineCd
						+ "パスワードがローマ字大文字小文字、数字、記号(-_)のうち３種類含み、8文字以上か確認してください";
			request.setAttribute("inputErrorMsg", inputErrorMsg);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}
