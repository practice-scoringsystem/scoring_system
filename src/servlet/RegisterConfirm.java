package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String content = request.getParameter("content");
		String answer1 = request.getParameter("answer1");
		String answer2 = request.getParameter("answer2");

		if (isEmpty(content) || isEmpty(content)) {
			request.setAttribute("error_message", "入力がされていません");
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");

			rd.forward(request, response);
		} else if (content.length() > 255) {
			request.setAttribute("error_message", "最大文字数を超えています");
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");

			rd.forward(request, response);
		} else {

			request.setAttribute("content", content);
			request.setAttribute("answer1", answer1);
			request.setAttribute("answer2", answer2);

			request.getRequestDispatcher("RegisterConfirm.jsp").forward(request, response);
		}

	}

	private boolean isEmpty(String content) {
		return (content == null || content.length() == 0);

	}
}
