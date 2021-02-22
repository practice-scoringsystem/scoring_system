package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuestionsDAO;
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
		//フォームからpostで情報が飛んでくる
		//DAOとbeanをnewしてDAOでupdateのメソッドを実行
		//beanに入れたやつをservletに持ってきてjspに渡す
		//jspで表示
		request.setCharacterEncoding("UTF-8");
		int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");

		try {
			//DAOとbeanをnewしてインスタンス化
			QuestionsDAO questionsDao = new QuestionsDAO();
			QuestionsBean questionsBean = new QuestionsBean(QuestionsId);
			questionsBean.setQuestion(question);
			questionsBean.setAnswer(answer);

			//DAOのupdateメソッドを使う
			questionsDao.update(questionsBean);
			//セットアトリビュート
			request.setAttribute("question", question);
			request.setAttribute("answer", answer);
			request.getRequestDispatcher("./List").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
