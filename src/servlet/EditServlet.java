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
import DAO.QuestionsCorrectAnswersDAO;
import DAO.QuestionsDAO;
import beans.CorrectAnswersBean;
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
			RequestDispatcher rd = request.getRequestDispatcher("List.jsp");
			rd.forward(request, response);

			//例外処理 Top.jspへ飛ばす
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_message", "内部でエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
