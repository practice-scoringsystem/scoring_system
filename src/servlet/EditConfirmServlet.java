package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditConfirmServlet
 */
@WebServlet("/EditConfirm")
public class EditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String question = request.getParameter("question");
		String[] arr = request.getParameterValues("answer");

		if (isEmpty(question) || arr.length == 0) {
			request.setAttribute("error_message", "入力がされていません");
			RequestDispatcher rd = request.getRequestDispatcher("Edit.jsp");

			rd.forward(request, response);
		} else if (question.length() > 255) {
			request.setAttribute("error_message", "最大文字数を超えています");
			RequestDispatcher rd = request.getRequestDispatcher("Edit.jsp");

			rd.forward(request, response);
		} else {

			request.setAttribute("question", question);
			request.setAttribute("answer", arr);
			request.getRequestDispatcher("EditConfirm.jsp").forward(request, response);

		}
	}

	private boolean isEmpty(String question) {
		return (question == null || question.length() == 0);

	}
}
