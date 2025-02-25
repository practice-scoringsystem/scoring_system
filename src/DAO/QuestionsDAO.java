package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.QuestionsBean;

public class QuestionsDAO extends ConnectionDAO {
	public QuestionsDAO() throws SQLException {
		setConnection();
	}

	@SuppressWarnings("unchecked")
	public List<QuestionsBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question FROM questions";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<QuestionsBean> list = new ArrayList<QuestionsBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionsBean bean = new QuestionsBean(id, question);
				//beanにidとquestionをいれる
				list.add(bean);
			}
			return list;
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

	/**
	 * 全件ランダム表示をする
	 */
	public List<QuestionsBean> randAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM questions ORDER BY RAND()";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<QuestionsBean> list = new ArrayList<QuestionsBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionsBean bean = new QuestionsBean(id, question);
				//beanにidとquestionをいれる
				list.add(bean);
			}
			return list;
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

	/**
	 * レコードの件数をカウントする（全件分）
	 * @throws SQLException
	 */
	// 問題数の取得
	public int getQuestionsCount() throws SQLException {
	  try {
	    List<QuestionsBean>list = findAll();
	    return list.size();
	    } catch (Exception e) {
				e.printStackTrace();
				throw new SQLException("レコードの取得に失敗しました");
	    }
	}

	/**
	 * 指定IDのレコードを取得する
	 */
	public QuestionsBean find(int QuestionsId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question FROM questions WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, QuestionsId);
			rs = st.executeQuery();
			QuestionsBean bean = new QuestionsBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				bean.setId(id);
				bean.setQuestion(question);
			}
			return bean;
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

	//Qustionsに紐づくAnswersを検索する
	public QuestionsBean find_ans(int QuestionsId) throws SQLException {
		if (con == null) {
			setConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;
		QuestionsBean bean = new QuestionsBean();
		try {
			String sql = "SELECT " //検索内容
					+ "questions.id, "
					+ "questions.question, "
					+ "correct_answers.questions_id, "
					+ "correct_answers.id, "
					+ "correct_answers.answer "
					+ "FROM " //検索対象
					+ "correct_answers JOIN "
					+ "questions ON correct_answers.questions_id = questions.id "
					+ "WHERE " //検索条件
					+ "questions.id = ?";

			// SQL文にQuestionsIdをセットして検索を実行
			st = con.prepareStatement(sql);
			st.setInt(1, QuestionsId);
			rs = st.executeQuery();

			while (rs.next()) {
				int questionsId = rs.getInt("id");
				String question = rs.getString("question");
				int answersId = rs.getInt("id");
				String answer = rs.getString("answer");
				bean.setQuestionsId(questionsId);
				bean.setQuestion(question);
				bean.setAnswersId(answersId);
				bean.setAnswer(answer);
			}
			// 検索結果をつめたオブジェクトを返却
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("情報の取得に失敗しました");
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

	/**
	 *	レコードの更新
	 */
	public void update(QuestionsBean questionsBean) throws SQLException {
		if (con == null) {
			setConnection();
		}

		PreparedStatement st = null;

		int questionsId = questionsBean.getQuestionsId();
		String question = questionsBean.getQuestion();
		try {
			String sql = "UPDATE questions "
					+ "SET question = ? "
					+ "WHERE id = ?";

			st = con.prepareStatement(sql);
			st.setString(1, question);
			st.setInt(2, questionsId);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("更新に失敗しました");
		}
	}

	/**
	 * レコードの新規作成
	 */
	public void create(QuestionsBean questionsBean) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO questions (question, created_at, updated_at) values (?, current_timestamp(),current_timestamp())";
			st = con.prepareStatement(sql);
			st.setString(1, questionsBean.getQuestion());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
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

	//CorrectAnswersを新規登録するためにQuestionsIdをセットする
	public int getLatestQuestionId() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select id from questions order by created_at desc limit 1;";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("レコードの操作に失敗しました。");
		}
	}

	/**
	 * レコードの削除
	 */

	//voidをbooleanが返る型にした
	/**
	 *
	 * @param questionId
	 * @return o:falseで1:true
	 */
	public int delete(int questionId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = " DELETE FROM questions WHERE id = ?";

			st = con.prepareStatement(sql);
			st.setInt(1, questionId);
			int result = st.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
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