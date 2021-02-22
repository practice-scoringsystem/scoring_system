package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuestionsDAO;
import beans.QuestionsBean;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/Edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リンクで飛ばしているためget処理になる

		//hrefの=questions_idがパラメーターとして飛んできていてQuestionsId（変数）へ入れている
		//sessionはpostで送り続けないといけない？
		int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));

		try {
				//QuestionsDAOとQuestionsBeanのインスタンスを作成
				QuestionsDAO questionsDAO = new QuestionsDAO();
				QuestionsBean questionsbean = new QuestionsBean(QuestionsId);

				//DaoファイルのQuestionsに紐づいたAnswersを取ってくるメソッド
				questionsbean = questionsDAO.find_ans(QuestionsId);

				//Questionsのidを取ってきてセット
				request.setAttribute("questionsBean", questionsbean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_message", "内部でエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("List.jsp");
			rd.forward(request, response);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Edit.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
