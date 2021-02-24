package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

			//DaoファイルのQuestionsと紐づいたAnswerを取ってくるメソッド
			questionsbean = questionsDAO.find_ans(QuestionsId);

			//answerをfor文で回すためにCorrectAnswersDAOから引っ張ってくる
			List<CorrectAnswersBean> CAlist = new ArrayList<CorrectAnswersBean>();
			CorrectAnswersDAO CAdao = new CorrectAnswersDAO();
			CAlist = CAdao.findByQuestionsId(QuestionsId);

			request.setAttribute("questions_id", QuestionsId);

			//Questionsの中身を入れたbeanをセット
			request.setAttribute("questionsBean", questionsbean);

			//Questionsに紐づくCorrectAnswerの中身をlistにしてセット
			request.setAttribute("CAlist", CAlist);

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

	}
}
