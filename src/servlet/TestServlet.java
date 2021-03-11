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
import DAO.HistoriesDAO;
import DAO.QuestionsDAO;
import beans.HistoriesBean;
import beans.QuestionsCorrectAnswersBean;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/Test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		・correct_answersテーブルにあるanswerカラムの情報とtest.jspから送られてきた回答欄のparameterが等しいか比較
		//		・等しい値だったらHistoriesテーブルのpointカラムに数を+1する
		//		・sessionを取得してログインユーザー名をsetAttributeして次の画面に送る
		//		・問題数÷正解数をして採点をする
		//		（問題数は配列取得の方がいい？正解数はHistoriesテーブルのpointカラムの数値？で取ってくる）
		HttpSession session = request.getSession(false);
		if (session.getAttribute("login_id") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		} else {

			request.setCharacterEncoding("UTF-8");

			String[] questions_ids = request.getParameterValues("questions_id");
			String ans[] = request.getParameterValues("answer");

			try {
				//answerをfor文で回すためにCorrectAnswersDAOから引っ張ってくる
				List<QuestionsCorrectAnswersBean> CAlist = new ArrayList<QuestionsCorrectAnswersBean>();
				CorrectAnswersDAO CAdao = new CorrectAnswersDAO();

				//Questionsに紐づくCorrectAnswerのanswerをlistにしてセット
				String answers[];

				HistoriesBean bean = new HistoriesBean();
				HistoriesDAO dao = new HistoriesDAO();

				int qId[] = new int[questions_ids.length];
				answers = new String[CAlist.size()];

				//カウントで書き換える
				double dubcount = 0;
				for (int i = 0; i < qId.length; i++) {
					if (questions_ids[i] != null) {
						qId[i] = Integer.parseInt(questions_ids[i]);

						CAlist = CAdao.findByQuestionsId(qId[i]);
						answers = new String[CAlist.size()];

						for (int j = 0; j < CAlist.size(); j++) {
							if (CAlist.get(j) != null) {

								//answersを１つずつ詰めていく
								answers[j] = CAlist.get(j).getAnswer();
								//もし入力の値がcorrect_answersのanswerと文字列がイコールだったらHistoriesのpointカラムを+1する

								for (int k = 0; k < ans.length; k++) {
									if (answers[j].equals(ans[k])) {
										dubcount++;
										break;
									}
								}
							}
						}
					}
				}

				//ここから計算
				QuestionsDAO questionsDao = new QuestionsDAO();

				//質問数のカウント
				double dubqCount = questionsDao.getQuestionsCount();

				//計算をする 正解数÷問題数(四捨五入をするのでdouble型になる)
				long lresult = Math.round(100 * dubcount / dubqCount);
				int result = (int) lresult;

				String name = (String) session.getAttribute("login_name");
				int userId = (int) session.getAttribute("login_id");

				bean.setUserId(userId);
				bean.setPoint(result);
				dao.create(bean);

				//質問数と回答数表示のため整数にする
				int qCount = (int) dubqCount;
				int count = (int) dubcount;

				request.setAttribute("qCount", qCount);
				request.setAttribute("result", result);
				request.setAttribute("count", count);
				request.setAttribute("answers", answers);

				request.setAttribute("name", name);

				RequestDispatcher rd = request.getRequestDispatcher("TestResult.jsp");
				rd.forward(request, response);

				//例外処理
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error_message", "内部でエラーが発生しました");
				RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
				rd.forward(request, response);
			}
		}
	}
}
