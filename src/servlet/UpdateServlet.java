package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		doPost(request,response);
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

			request.setCharacterEncoding("UTF-8");

			int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));
			String question = request.getParameter("question");
			String[] arrAnswer = request.getParameterValues("answer");
			String[] idArr = request.getParameterValues("answers_id");

			//intの配列にidを詰めていく
			int answers_ids[];
			answers_ids = new int[idArr.length];
			for (int i = 0; i < answers_ids.length; i++) {
				if (idArr[i] != null) {
					answers_ids[i] = Integer.parseInt(idArr[i]);
				}
			}

			try {
				//DAOとbeanをnewしてインスタンス化
				QuestionsDAO questionsDao = new QuestionsDAO();
				QuestionsBean questionsBean = new QuestionsBean(QuestionsId);

				questionsBean.setQuestionsId(QuestionsId); //これはいらない
				questionsBean.setQuestion(question);

				//DAOのupdateメソッドを使う
				questionsDao.update(questionsBean);

				//セットアトリビュート
				request.setAttribute("question", question);

				CorrectAnswersDAO answersDao = new CorrectAnswersDAO();
				//配列でbeanを作る
				for (int i = 0; i < answers_ids.length; i++) {
					//beanをインスタンス化
					CorrectAnswersBean answersBean = new CorrectAnswersBean(answers_ids[i]);
					answersBean.setAnswer(arrAnswer[i]);
					answersDao.update(answersBean);
				}

				request.getRequestDispatcher("./List").forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("error_message", "内部でエラーが発生しました");
				RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
				rd.forward(request, response);
			}
		}
	}
}
