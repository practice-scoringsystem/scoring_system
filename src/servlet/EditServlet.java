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
import javax.servlet.http.HttpSession;

import DAO.CorrectAnswersDAO;
import DAO.QuestionsDAO;
import beans.QuestionsBean;
import beans.QuestionsCorrectAnswersBean;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("login_id") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		} else {

			//questions_idがパラメーターとして飛んできていてQuestionsId（変数）へ入れている
			int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));

			try {

				QuestionsDAO questionsDAO = new QuestionsDAO();
				QuestionsBean questionsbean = new QuestionsBean(QuestionsId);

				//DaoファイルにてQuestionsを引っ張ってくる
				questionsbean = questionsDAO.find(QuestionsId);

				//answerをfor文で回すためにCorrectAnswersDAOから引っ張ってくる
				List<QuestionsCorrectAnswersBean> CAlist = new ArrayList<QuestionsCorrectAnswersBean>();
				CorrectAnswersDAO CAdao = new CorrectAnswersDAO();
				CAlist = CAdao.findByQuestionsId(QuestionsId);

				//answers_idをループで回す
				//Questionsに紐づくCorrectAnswerの中身をlistにしてセット
				int answers_ids[];
				answers_ids = new int[CAlist.size()];
				for (int i = 0; i < answers_ids.length; i++) {
					if (CAlist.get(i) != null) {
						answers_ids[i] = CAlist.get(i).getId();
					}
				}

				request.setAttribute("questions_id", QuestionsId);
				//Questionsの中身を入れたbeanをセット
				request.setAttribute("questionsBean", questionsbean);
				request.setAttribute("answers_ids", answers_ids);
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
