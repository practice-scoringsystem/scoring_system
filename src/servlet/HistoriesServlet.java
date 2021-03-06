package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HistoriesDAO;
import DAO.UsersDAO;
import beans.HistoriesBean;
import beans.UsersBean;

/**
 * Servlet implementation class HistoriesServlet
 */
@WebServlet("/Histories")
public class HistoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoriesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

			try {
				int userId = (int) session.getAttribute("login_id");

				//履歴を全件表示する
				List<HistoriesBean> list = new ArrayList<HistoriesBean>();
				HistoriesDAO dao = new HistoriesDAO();
				//user_idが一致した履歴を取得する　listとして
				list = dao.findByUserId(userId);

				//int userId = (int) session.getAttribute("login_id");
				//list = dao.findByUserId(userId);

				//履歴をsetAttribute
				request.setAttribute("list", list);

				//user一覧取得
				UsersBean ub = new UsersBean();
				UsersDAO udao = new UsersDAO();

				//UserDaoでuser_idが一致するデータを持ってくるメソッドに変更 beanになる
				//UsersBean ub = new UsersBean
				//UsersDAO dao = new UsersDAO();
				//ub = dao.find(userId);
				ub = udao.find(userId);

				//listにセットしてjsp側でlistで呼び出せるようにする
				request.setAttribute("ub", ub);

				RequestDispatcher rd = request.getRequestDispatcher("Histories.jsp");
				rd.forward(request, response);

				//例外処理 Top.jspへ飛ばす
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "内部でエラーが発生しました");
				RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
				rd.forward(request, response);
			}
		}
	}
}
