package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UsersDAO;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDelete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session.getAttribute("login_id") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);

		} else {

			int userId = (int) (Integer.parseInt(request.getParameter("user_id")));

			try {
				if (session.getAttribute("login_id") == request.getParameter("user_id")) {
					session.removeAttribute("login_id");
					RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
					dispatcher.forward(request, response);
				}

				UsersDAO dao = new UsersDAO();
				dao.delete(userId);

				request.getRequestDispatcher("./UsersList").forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
}
