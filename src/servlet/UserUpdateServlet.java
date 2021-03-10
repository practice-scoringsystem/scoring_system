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
import beans.UsersBean;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdate")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
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

			request.setCharacterEncoding("UTF-8");

			int userId = (int) (Integer.parseInt(request.getParameter("user_id")));
			String name = request.getParameter("name");
			String pw = request.getParameter("password");
			String adc = request.getParameter("adminCheck");
			byte aflag = Byte.parseByte(adc);

			try {
				UsersDAO dao = new UsersDAO();
				UsersBean ub = new UsersBean();

				ub.setId(userId); //これはいらない
				ub.setName(name);
				ub.setPassword(pw);
				ub.setAdminFlag(aflag);

				//DAOのupdateメソッドを使う
				dao.update(ub);

				//セットアトリビュート
				request.setAttribute("ub", ub);

				request.getRequestDispatcher("./UsersList").forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}