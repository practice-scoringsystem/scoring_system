package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CorrectAnswersDAO;
import DAO.QuestionsDAO;
import model.CorrectAnswers;
import model.Questions;

/**
 * Servlet implementation class NewQuestionsAnswersServlet
 */
@WebServlet("/New")
public class NewQuestionsAnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewQuestionsAnswersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//新規登録　question
		//入力フォームの値を受け取る　getparameterで取ってきて　引数でinsert
		//DAOにいれる

		//QuestionDAOとAnswersDAOをnewする
		try {
			//入力フォームの値を日本語にする
			request.setCharacterEncoding("UTF-8");
			//入力フォームからパラメーターを受け取る
			String QuestionsContent = request.getParameter("content");
			String Answers1 = request.getParameter("answer1");
			String Answers2 = request.getParameter("answer2");

			//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
			//リクエストパラメーターから受け取った値をセッタを使って書き込む
			Questions q = new Questions();
			q.setContent(content);


			CorrectAnswers ca = new CorrectAnswers();
			ca.setAnswers(answer1);
			ca.setAnswers(answer2);

			//DAOに追加
			QuestionsDAO questionsDAO = new QuestionsDAO();
			CorrectAnswersDAO answersDAO = new CorrectAnswersDAO();
			//DAOのInsertを実行 引数はQuestionsオブジェクト
			int line = questionsDAO.insert(q);
			int line = answersDAO.insert(ca);

			//lineに中身があれば成功メッセージをセットしてList.jspへ遷移
			if (line>0) {
				request.setAttribute("success_message", "登録が完了しました");
				RequestDispatcher rd = request.getRequestDispatcher("List.jsp");

				rd.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
