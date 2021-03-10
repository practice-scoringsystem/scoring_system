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
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session.getAttribute("login_id") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);

		} else {

			try {
				//入力フォームの値を日本語にする
				request.setCharacterEncoding("UTF-8");

				// ブラウザの更新ボタン対応 "is_register"が入ってたらifの中は実行しない
				String is_register = (String) session.getAttribute("is_register");

				//入力フォームからパラメーターを受け取る
				String name = request.getParameter("name");
				String pw = request.getParameter("password");
				String adc = request.getParameter("adminCheck");
				byte aflag = Byte.parseByte(adc);

				//登録処理が行われてない場合
				if (is_register == null || is_register.equals("OK")) {

					//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
					//リクエストパラメーターから受け取った値をセッタを使って書き込む
					UsersBean ub = new UsersBean();
					ub.setName(name);
					ub.setPassword(pw);
					ub.setAdminFlag(aflag);

					//DAOに追加
					UsersDAO dao = new UsersDAO();

					//DAOのInsertを実行
					dao.create(ub);
					// 一回登録処理が終わったらセッションにフラグをセット
					is_register = "OK";
					session.setAttribute("is_register", is_register);
				}

				RequestDispatcher rd = request.getRequestDispatcher("./UsersList");
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
