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
 * Servlet implementation class RegisterConfirm
 */
@WebServlet("/RegisterConfirm")
public class RegisterConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterConfirm() {
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
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		session.setAttribute("is_register", "0");

		String question = request.getParameter("question");
		String[] arr = request.getParameterValues("answer");

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].length() > 200) {
				//配列の中身も空じゃないかを確認,文字数も確認(文字数はテーブル定義に合わせておく)
				request.setAttribute("error_message", "最大文字数を超えています");
				RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if ("".equals(arr[i])) {
				//配列の中身も空じゃないかを確認 空文字入力されているようなので空文字比較で
				request.setAttribute("error_message", "答えが未入力です");
				RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
			}
		}

		if (isEmpty(question) || arr.length == 0) {
			request.setAttribute("error_message", "入力がされていません");
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);

		} else if (question.length() > 500) {
			request.setAttribute("error_message", "最大文字数を超えています");
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("question", question);
			request.setAttribute("answer", arr);

			request.getRequestDispatcher("RegisterConfirm.jsp").forward(request, response);
		}
	}

	private boolean isEmpty(String question) {
		return (question == null || question.length() == 0);

	}
}
