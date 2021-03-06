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

		HttpSession session = request.getSession(false);
		if (session.getAttribute("login_id") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		} else {

			String questionsId = request.getParameter("questions_id");
			String question = request.getParameter("question");

			String[] idArr = request.getParameterValues("answers_id");

			int answers_ids[];
			answers_ids = new int[idArr.length];
			for (int i = 0; i < answers_ids.length; i++) {
				if (idArr[i] != null) {
					answers_ids[i] = Integer.parseInt(idArr[i]);
				}
			}

			//下でsetAttributeしてるのにこれを消すとぬるぽになってしまう・・・
			request.setAttribute("answers_ids", answers_ids);

			String[] arr = request.getParameterValues("answer");
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].length() > 200 || arr[i] == null) {
					//配列の中身も空じゃないかを確認,文字数も確認(文字数はテーブル定義に合わせておく)
					request.setAttribute("error_message", "最大文字数を超えています");
					RequestDispatcher rd = request.getRequestDispatcher("Edit.jsp");
					rd.forward(request, response);
				}
			}

//ぬるぽになるよ！！
//			for (int i = 0; i < arr.length; i++) {
//				if (isEmpty(arr[i])) {
//					System.out.println(arr[i]);
//					//配列の中身も空じゃないかを確認 空文字入力されているよう
//					request.setAttribute("error_message", "答えが未入力です");
//					RequestDispatcher rd = request.getRequestDispatcher("Edit.jsp");
//					rd.forward(request, response);
//				}
//			}

			if (isEmpty(question) || arr.length == 0) {
				request.setAttribute("error_message", "入力がされていません");
				RequestDispatcher rd = request.getRequestDispatcher("Edit.jsp");
				rd.forward(request, response);

			} else if (question.length() > 500) {
				request.setAttribute("error_message", "最大文字数を超えています");
				RequestDispatcher rd = request.getRequestDispatcher("Edit.jsp");
				rd.forward(request, response);

			} else {
				request.setAttribute("questions_id", questionsId);
				request.setAttribute("question", question);
				request.setAttribute("answewrs_ids", answers_ids);
				request.setAttribute("answer", arr);
				request.getRequestDispatcher("EditConfirm.jsp").forward(request, response);
			}
		}
	}

	private boolean isEmpty(String question) {
		return (question == null || question.length() == 0);

	}
}
