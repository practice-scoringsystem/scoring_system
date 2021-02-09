package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;

import beans.CorrectAnswersBean;

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
	public ArrayList<CorrectAnswersBean> findByQuestionId(int questionId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question_id, answer FROM correct_answers WHERE question_id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, questionId);
			rs = st.executeQuery();
			List<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
                //重複ローカル変数questionIdと表示されていたためintを外した。同じスコープ内なのでローカル変数は使い回せる。
				questionId = rs.getInt("question_id");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questionId, answer);
				list.add(bean);
			}
			return (ArrayList<CorrectAnswersBean>)list;
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
	public void create(CorrectAnswersBean cab) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO correct_amswers (id, question_id, answer, created_at, updated_at) values (?,?,?,?,?)";
			// 現在時刻を取得
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String strTimestamp = sdf.format(timestamp);
			st = con.prepareStatement(sql);
			st.setInt(1, cab.getId());
			st.setInt(2, cab.getQuestionId());
			st.setString(3, cab.getAnswer());
			st.setString(4, strTimestamp);
			st.setString(5, strTimestamp);
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