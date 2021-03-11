package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UsersDAO;
import beans.UsersBean;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEdit")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session.getAttribute("login_id") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);

		} else if ((byte)session.getAttribute("a_flag") != 1) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Top.jsp");
			dispatcher.forward(request, response);

		} else {

			int userId = (int) (Integer.parseInt(request.getParameter("user_id")));

			try {

				UsersDAO dao = new UsersDAO();
				UsersBean ub = new UsersBean();

				ub = dao.find(userId);

				request.setAttribute("ub", ub);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserEdit.jsp");
				requestDispatcher.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "内部でエラーが発生しました");
				RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
				rd.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
