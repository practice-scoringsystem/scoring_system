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
import beans.QuestionsBean;
import beans.QuestionsCorrectAnswersBean;

/**
 * Servlet implementation class DeleteConfirmServlet
 */
@WebServlet("/DeleteConfirm")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConfirmServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//questions_idがパラメーターとして飛んできていてQuestionsId（変数）へ入れている
		int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));

		try {

			QuestionsDAO questionsDAO = new QuestionsDAO();
			QuestionsBean questionsbean = new QuestionsBean(QuestionsId);

			//DaoファイルのQuestionsと紐づいたAnswerを取ってくるメソッド
			questionsbean = questionsDAO.find_ans(QuestionsId);

			//answerをfor文で回すためにCorrectAnswersDAOから引っ張ってくる
			List<QuestionsCorrectAnswersBean> CAlist = new ArrayList<QuestionsCorrectAnswersBean>();
			CorrectAnswersDAO CAdao = new CorrectAnswersDAO();
			CAlist = CAdao.findByQuestionsId(QuestionsId);

			request.setAttribute("questions_id", QuestionsId);
			System.out.println(QuestionsId);

			//Questionsの中身を入れたbeanをセット
			request.setAttribute("questionsBean", questionsbean);

			//Questionsに紐づくCorrectAnswerの中身をlistにしてセット
			int answers_ids[];
			answers_ids = new int[CAlist.size()];
			for (int i = 0; i < answers_ids.length; i++) {
			  if (CAlist.get(i) != null){
			    answers_ids[i] = CAlist.get(i).getId();
			  }
			}

			//answers_idをループで回す
			request.setAttribute("answers_ids", answers_ids);
			System.out.println(answers_ids);
			request.setAttribute("CAlist", CAlist);
			System.out.println(CAlist);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_message", "内部でエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("List.jsp");
			rd.forward(request, response);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("DeleteConfirm.jsp");
		requestDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
