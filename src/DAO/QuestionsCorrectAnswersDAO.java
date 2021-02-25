package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.QuestionsCorrectAnswersBean;

public class QuestionsCorrectAnswersDAO extends ConnectionDAO {
	public QuestionsCorrectAnswersDAO() throws SQLException {
		setConnection();
	}

	@SuppressWarnings("unchecked")
	public List<QuestionsCorrectAnswersBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT q.id as questions_id, ca.id as answers_id, ca.answer FROM questions q INNER JOIN correct_answers ca ON q.id = ca.questions_id";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<QuestionsCorrectAnswersBean> QCAlist = new ArrayList<QuestionsCorrectAnswersBean>();
			while (rs.next()) {
				int question_id = rs.getInt("questions_id");
				String answers_id = rs.getString("answers_id");
				String answer = rs.getString("answer");
				QuestionsCorrectAnswersBean QCAbean = new QuestionsCorrectAnswersBean(question_id, answers_id, answer);
				//beanにquestion_idとanswerをいれる
				QCAlist.add(QCAbean);
			}
			return QCAlist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException("リソースの開放に失敗しました");
			}
		}
	}

}
