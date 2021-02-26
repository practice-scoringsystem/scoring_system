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
import beans.QuestionsBean;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		int QuestionsId = (int) (Integer.parseInt(request.getParameter("questions_id")));
		String inputAnswer = request.getParameter("input_answer");

		try {

			//answerをfor文で回すためにCorrectAnswersDAOから引っ張ってくる
			List<QuestionsCorrectAnswersBean> CAlist = new ArrayList<QuestionsCorrectAnswersBean>();
			CorrectAnswersDAO CAdao = new CorrectAnswersDAO();
			CAlist = CAdao.findByQuestionsId(QuestionsId);

			//Questionsに紐づくCorrectAnswerのanswerをlistにしてセット
			String answers[];
			HistoriesBean bean = new HistoriesBean();
	    	HistoriesDAO dao = new HistoriesDAO();
			answers = new String[CAlist.size()];
			//カウントで書き換える
			int count = 0;
			for (int i = 0; i < answers.length; i++) {
			  if (CAlist.get(i) != null){
				//answersを１つずつ詰めていく
			    answers[i] = CAlist.get(i).getAnswer();
			    //もし入力の値がcorrect_answersのanswerと文字列がイコールだったらHistoriesのpointカラムを+1する
			    if (inputAnswer.equals(answers[i])) {
			    	count += 1;
			    	break;
				}
			  }
			}

			//ここで計算をする 正解数÷問題数
			QuestionsDAO questionsDao = new QuestionsDAO();
			QuestionsBean questionsBean = new QuestionsBean();
			int qCount = questionsDao.getQuestionsCount();
			int result = (Math.round(count/qCount));

			HttpSession session = request.getSession(false);
			String name = (String)session.getAttribute("login_name");
			int userId = (int)session.getAttribute("login_id");

			bean.setUserId(userId);

			bean.setPoint(result);
			dao.create(bean);

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
			RequestDispatcher rd = request.getRequestDispatcher("./TestList");
			rd.forward(request, response);
		}
	}
}
