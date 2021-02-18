package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CorrectAnswersDAO;
import DAO.QuestionsCorrectAnswersDAO;
import DAO.QuestionsDAO;
import beans.QuestionsBean;
import beans.QuestionsCorrectAnswersBean;
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

		try {
			//入力フォームの値を日本語にする
			request.setCharacterEncoding("UTF-8");

			//入力フォームからパラメーターを受け取る
			String question = request.getParameter("question");

			//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
			//リクエストパラメーターから受け取った値をセッタを使って書き込む
			Questions q = new Questions();
			q.setQuestion(question);

			//questions_idを取ってくる処理をいれる　set question_id
			CorrectAnswers ca = new CorrectAnswers();

			//get parameter valuesでStringの配列が取れそう nameをanswerに統一
			//if answers1 が存在したらcreate answers2 も存在したらcreate 配列で取ってくるイメージが近い for文を回す
			String[] arr = request.getParameterValues("answer");

			//DAOに追加
			QuestionsDAO questionsDAO = new QuestionsDAO();
			CorrectAnswersDAO answersDAO = new CorrectAnswersDAO();

			int max_id = questionsDAO.getMaxQuestionId();

			//DAOのInsertを実行 引数はQuestionsオブジェクト
			questionsDAO.create(q);
			//SELECT MAX(id) FROM questions;をquestion_idにいれる（+で１ずつ増やす）
			max_id = max_id + 1;

			//caにquestuons_id をセット
			ca.setQuestionsId(max_id);
			for (int i = 0; i < arr.length; i++) {
				// 答えの入力値が空じゃない場合はセットしてinsert
				if (!arr[i].isEmpty()) {
					ca.setAnswer(arr[i]);
					answersDAO.create(ca);
				}
			}

			try {
				//QuestionsDAOで取ってきた情報をArrayListにつめる
				List<QuestionsBean> list = new ArrayList<QuestionsBean>();
				QuestionsDAO dao = new QuestionsDAO();

				//QuestionsDAOにて定義した全データを取ってくるfindAllを指示
				list = dao.findAll();

				//全データをlistにセットしてjsp側でlistで呼び出せるようにする
				request.setAttribute("list", list);

				//QCAがあるのでそれを含めた後にフォワードをする
				//QuestionsDAOで取ってきた情報をArrayListにつめる
				List<QuestionsCorrectAnswersBean> QCAlist = new ArrayList<QuestionsCorrectAnswersBean>();
				QuestionsCorrectAnswersDAO QCAdao = new QuestionsCorrectAnswersDAO();

				//QuestionsDAOにて定義した全データを取ってくるfindAllを指示
				QCAlist = QCAdao.findAll();

				//全データをlistにセットしてjsp側でlistで呼び出せるようにする
				request.setAttribute("QCAlist", QCAlist);
				request.setAttribute("success_message", "登録が完了しました");
				RequestDispatcher rd = request.getRequestDispatcher("List.jsp");
				rd.forward(request, response);

			//例外処理 Top.jspへ飛ばす
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "内部でエラーが発生しました");
				RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
