package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserEditConfirmServlet
 */
@WebServlet("/UserEditConfirm")
public class UserEditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEditConfirmServlet() {
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

		} else if ((byte)session.getAttribute("a_flag") != 1) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Top.jsp");
			dispatcher.forward(request, response);

		} else {

			request.setCharacterEncoding("UTF-8");

			String userId = request.getParameter("user_id");
			String name = request.getParameter("name");
			String pw = request.getParameter("password");
			String cpw = request.getParameter("passwordConfirm");
			String adc = request.getParameter("adminCheck");

			//各種バリデーション
			if (pw.isEmpty()) {
				request.setAttribute("error_message", "パスワードが入力がされていません");
				RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
				rd.forward(request, response);
			} else if (!pw.matches("^[A-Za-z0-9]+$")) {
				request.setAttribute("error_message", "パスワードは半角英数字のみ使用できます");
				RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
				rd.forward(request, response);
			} else if (cpw.isEmpty()) {
				request.setAttribute("error_message", "確認用のパスワードが入力がされていません");
				RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
				rd.forward(request, response);
			} else if (!cpw.matches("^[A-Za-z0-9]+$")) {
				request.setAttribute("error_message", "パスワードは半角英数字のみ使用できます");
				RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
				rd.forward(request, response);
			} else {
				//バリデーションOKだったらパスワード＆パスワード確認が一致しているかを確認してOKなら次の画面へ
				if (pw.equals(cpw)) {
					request.setAttribute("user_id", userId);
					request.setAttribute("name", name);
					request.setAttribute("password", pw);
					request.setAttribute("adc", adc);
					request.getRequestDispatcher("UserEditConfirm.jsp").forward(request, response);
				} else {
					request.setAttribute("error_message", "パスワードと確認用パスワードが一致しません");
					RequestDispatcher rd = request.getRequestDispatcher("UserEdit");
					rd.forward(request, response);
				}
			}
		}
	}
}