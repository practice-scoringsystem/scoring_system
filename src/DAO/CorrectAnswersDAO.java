package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CorrectAnswersBean;
import beans.QuestionsCorrectAnswersBean;

public class CorrectAnswersDAO extends ConnectionDAO {
	public CorrectAnswersDAO() throws SQLException {
		setConnection();
	}

	public List<CorrectAnswersBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questionsId, answer FROM correct_answers";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int questionId = rs.getInt("questionId");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questionId, answer);
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
	 * 指定IDのレコードを取得する
	 */
	public List<QuestionsCorrectAnswersBean> findByQuestionsId(int QuestionsId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE questions_id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, QuestionsId);
			rs = st.executeQuery();
			List<QuestionsCorrectAnswersBean> list = new ArrayList<QuestionsCorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				QuestionsCorrectAnswersBean bean = new QuestionsCorrectAnswersBean(id, questions_id, answer);
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
	 * レコードの新規作成
	 */
	public void create(CorrectAnswersBean answersBean) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO correct_answers (questions_id, answer, created_at, updated_at) values (?,?,current_timestamp(),current_timestamp())";
			st = con.prepareStatement(sql);
			st.setInt(1, answersBean.getQuestionsId());
			st.setString(2, answersBean.getAnswer());
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

	/**
	 *	レコードの更新
	 */
	public void update(CorrectAnswersBean answersBean) throws SQLException {
		if (con == null) {
			setConnection();
		}

		PreparedStatement st = null;

		int id = answersBean.getId();
		String answer = answersBean.getAnswer();
		try {
			String sql = "UPDATE correct_answers "
					+ "SET answer = ? "
					+ "WHERE id = ?";

			st = con.prepareStatement(sql);
			st.setString(1, answer);
			st.setInt(2, id);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("更新に失敗しました");
		}
	}

	/**
	 * レコードの削除
	 */
	public int delete(int correct_answerId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "DELETE FROM correct_answers WHERE id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, correct_answerId);
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