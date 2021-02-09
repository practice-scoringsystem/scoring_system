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

import DAO.UsersDAO;
import beans.UsersBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str_id = req.getParameter("id");
		String str_pw = req.getParameter("password");
		if (isEmpty(str_id) || isEmpty(str_pw)) {
			req.setAttribute("error_message", "ユーザーIDとパスワードを入力してください");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/Login.jsp");

			rd.forward(req, res);
		} else {
			int form_id = Integer.parseInt(str_id);
			UsersDAO dao = new UsersDAO();
			List<UsersBean> list = dao.findAll();
			for (int i = 0; i < list.size(); i++) {
				int db_id = list.get(i).getId();
				String db_name = list.get(i).getName();
				String db_pw = list.get(i).getPassword();

				if (form_id == db_id && str_pw.equals(db_pw)) {
					HttpSession session = req.getSession();
					session.setAttribute("login_id", db_id);
					session.setAttribute("login_name", db_name);
					session.setAttribute("login_pw", db_pw);

					RequestDispatcher rd = req.getRequestDispatcher("/jsp/Top.jsp");
					rd.forward(req, res);
				}
			}
				req.setAttribute("error_message", "ユーザーIDかパスワードが違います");
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/Login.jsp");
				rd.forward(req, res);
			}

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

	private boolean isEmpty(String str) {
		return (str == null || str.length() == 0);

	}
}
