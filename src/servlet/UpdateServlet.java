package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CorrectAnswersDAO;
import DAO.QuestionsDAO;
import beans.CorrectAnswersBean;
import beans.QuestionsBean;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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

		int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));
		String question = request.getParameter("question");

		String[] arrAnswer = request.getParameterValues("answers_id");
		String id = arrAnswer[0];
		Integer answersId = Integer.parseInt(id);

		String[] arrAnswer2 = request.getParameterValues("answer");
		String answer = arrAnswer2[0];

		try {
			//DAOとbeanをnewしてインスタンス化
			QuestionsDAO questionsDao = new QuestionsDAO();
			QuestionsBean questionsBean = new QuestionsBean(QuestionsId);

			questionsBean.setQuestion(question);

			//DAOのupdateメソッドを使う
			questionsDao.update(questionsBean);

			//セットアトリビュート
			request.setAttribute("question", question);

			//CorrectAnswersもnewする
			CorrectAnswersDAO answersDao = new CorrectAnswersDAO();
			CorrectAnswersBean answersBean = new CorrectAnswersBean(answersId);

			if (answersId == null) {
				answersBean.setAnswersId(answersId);
				answersBean.setAnswer(answer);
				// 新規登録
				answersDao.create(answersBean);
			} else {
				// 更新
				answersDao.update(answersBean);
			}
			// 新規登録または更新した情報を再度画面に表示
			request.setAttribute("answers_id", answersId);
			request.setAttribute("answer", answer);

			request.getRequestDispatcher("./List").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//String[]をparseIntするメソッド（取ってきたanswerの配列の中のidだけintにする）
	//answerが配列できているから配列をカラムごとに分ける
	//配列の中の一部を取り出す
	//paseIntでint型にする

	//方法１　string配列をint配列にして、また必要な分をstringに戻す
	//方法2  配列からidを取り出してintにする

}
