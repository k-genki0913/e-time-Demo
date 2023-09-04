package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Constract_DAO;
import dto.ConstractList_Record;

/**
 * Servlet implementation class ConstractListServlet
 */
@WebServlet("/ConstractListServlet")
public class ConstractListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Constract_DAO constract_DAO = new Constract_DAO();
		List<ConstractList_Record> constractList = constract_DAO.getConstractList();
		
		request.setAttribute("constractList", constractList);
		
		if(constractList.size() == 0) {
			request.setAttribute("constractListErrorMsg", "有効な契約がありません");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/constractList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
