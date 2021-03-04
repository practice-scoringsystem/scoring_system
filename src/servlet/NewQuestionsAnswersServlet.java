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
import javax.servlet.http.HttpSession;

import DAO.CorrectAnswersDAO;
import DAO.QuestionsCorrectAnswersDAO;
import DAO.QuestionsDAO;
import beans.CorrectAnswersBean;
import beans.QuestionsBean;
import beans.QuestionsCorrectAnswersBean;

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
				//入力フォームの値を日本語にする
				request.setCharacterEncoding("UTF-8");

				// ブラウザの更新ボタン対応 "is_register"が入ってたらifの中は実行しない
				String is_register = (String) session.getAttribute("is_register");

				//入力フォームからパラメーターを受け取る
				String question = request.getParameter("question");

				//登録処理が行われてない場合
				if (is_register == null || is_register.equals("0")) {

					//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
					//リクエストパラメーターから受け取った値をセッタを使って書き込む
					QuestionsBean questionsBean = new QuestionsBean();
					questionsBean.setQuestion(question);

					//questions_idを取ってくる処理をいれる　set question_id
					CorrectAnswersBean answersBean = new CorrectAnswersBean();

					//get parameter valuesでStringの配列を取る nameをanswerに統一
					String[] arr = request.getParameterValues("answer");

					//DAOに追加
					QuestionsDAO questionsDAO = new QuestionsDAO();
					CorrectAnswersDAO answersDAO = new CorrectAnswersDAO();

					//DAOのInsertを実行 引数はQuestionsオブジェクト
					questionsDAO.create(questionsBean);

					//最新のquestionsのidを取得する
					int id = questionsDAO.getLatestQuestionId();

					//answersBeanにquestuons_id をセット
					answersBean.setQuestionsId(id);
					for (int i = 0; i < arr.length; i++) {
						// 答えの入力値が空じゃない場合はセットしてinsert
						if (!arr[i].isEmpty()) {
							answersBean.setAnswer(arr[i]);
							answersDAO.create(answersBean);
						}
					}
					// 一回登録処理が終わったらセッションにフラグをセット
					is_register = "1";
					session.setAttribute("is_register", is_register);
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
}
