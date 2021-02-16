package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuestionsDAO;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//新規登録　question
//入力フォームの値を受け取る　getparameterで取ってきて　引数でinsert
//DAOにいれる
//値が空ならそのままregisterに
//値を持ったまま確認画面に飛ばす
		request.setCharacterEncoding("UTF-8");
		String QuestionsContent = request.getParameter("content");

		try {
		QuestionsDAO questionsDAO = new QuestionsDAO();


		Questions content =
				new Questions(QuestionsContent);

		if(content != null) {
			// 新規登録
			QuestionsDAO.insertQuestion(question);
			message = "質問と答えを登録しました";
		} else {
			message = "入力項目に誤りがあります";
		}

	} catch (SQLException e) {
		message = e.getMessage();
		request.setAttribute("error", "true");
		e.printStackTrace();
	}
	// 次の画面に遷移
	request.setAttribute("message", message);
	request.getRequestDispatcher("RegisterConfirm.jsp").forward(request, response);
}

}
