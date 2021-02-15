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
 * Servlet implementation class ListServlet
 */
@WebServlet("/List")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//QuestionsDAOで取ってきた情報をArrayListにつめる
			List<QuestionsBean> list = new ArrayList<QuestionsBean>();
			QuestionsDAO dao = new QuestionsDAO();
			//QuestionsDAOにて定義した全データを取ってくるfindAllを指示
			list = dao.findAll();
			//全データをlistにセットしてjsp側でlistで呼び出せるようにする
			request.setAttribute("list", list);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


//DAOファイルにてQuestionsbeanの中にArrayListでfindAll（問題一覧が格納されている）
//やらないといけないのはQuestionsbeanの中の全てのデータを表示すること
//データベースへの接続とSQL文の発行指示はDAOConnectとQuestionsDAOでできているはず
//ArrayListの中身を表示するのをservletとjspでしなきゃいけない

//ログインチェック
//topからlistへのページ遷移
//新規投稿ボタンを設置
//listに問題と答えを表示
//編集と削除ボタンを置く
