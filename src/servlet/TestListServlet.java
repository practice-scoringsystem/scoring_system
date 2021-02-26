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

import DAO.QuestionsDAO;
import beans.QuestionsBean;

/**
 * Servlet implementation class TestListServlet
 */
@WebServlet("/TestList")
public class TestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestListServlet() {
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
		String questions_id = request.getParameter("questions_id");
		String[] arr = request.getParameterValues("answer");

		try {
			//QuestionsDAOで取ってきた情報をArrayListにつめる
			List<QuestionsBean> list = new ArrayList<QuestionsBean>();
			QuestionsDAO dao = new QuestionsDAO();

			//QuestionsDAOにて定義した全データを取ってくるfindAllを指示
			list = dao.findAll();

			//全データをlistにセットしてjsp側でlistで呼び出せるようにする
			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("Test.jsp");
			rd.forward(request, response);

		//例外処理 Top.jspへ飛ばす
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_message", "内部でエラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
			rd.forward(request, response);
		}

	}

	}

