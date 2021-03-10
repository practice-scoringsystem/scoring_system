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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String str_id = request.getParameter("id");
		String str_pw = request.getParameter("password");

		if (isEmpty(str_id) || isEmpty(str_pw)) {
			request.setAttribute("error_message", "ユーザーIDとパスワードを入力してください");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");

			rd.forward(request, response);
		} else {

			try {
				int form_id = Integer.parseInt(str_id);
				UsersDAO dao = new UsersDAO();
				List<UsersBean> list = dao.findAll();
				for (int i = 0; i < list.size(); i++) {
					int db_id = list.get(i).getId();
					String db_name = list.get(i).getName();
					String db_pw = list.get(i).getPassword();
					byte a_flag = list.get(i).getAdminFlag();

					if (form_id == db_id && str_pw.equals(db_pw)) {
						UsersBean ub = new UsersBean();
						ub.setId(db_id);
						ub.setName(db_name);
						ub.setPassword(db_pw);
						ub.setAdminFlag(a_flag);

						HttpSession session = request.getSession();

						session.setAttribute("ub", ub);

						RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
						rd.forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "内部でエラーが発生しました");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			request.setAttribute("error_message", "ユーザーIDかパスワードが違います");
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean isEmpty(String str) {
		return (str == null || str.length() == 0);

	}
}
