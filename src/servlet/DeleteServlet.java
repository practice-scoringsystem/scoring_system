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
import common.CommonUtil;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));
		String[] idArr = request.getParameterValues("answers_id");

		//CommonUtilにてメソッドを定義 stringの配列をintegerにしている
		CommonUtil cu = new CommonUtil();
		int answers_ids[] = cu.parseInts(idArr);

		try {
			QuestionsDAO questionsDao = new QuestionsDAO();

			CorrectAnswersDAO answersDao = new CorrectAnswersDAO();
			//配列でbeanを作る
			for (int i = 0; i < answers_ids.length; i++) {
				answersDao.delete(answers_ids[i]);
			}

			int count = questionsDao.delete(QuestionsId);

			request.setAttribute("count", count);
			request.getRequestDispatcher("./List").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
