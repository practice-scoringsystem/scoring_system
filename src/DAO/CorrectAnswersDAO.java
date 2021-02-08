package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CorrectAnswersBean;

public class CorrectAnswersDAO extends ConnectionDAO {
	public CorrectAnswersDAO() throws SQLException  {
		setConnection();
	}
	public List<CorrectAnswersBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questions_id, answer FROM correct_answers;";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int question_id = rs.getInt("question_id");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, question_id, answer);
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
		public CorrectAnswersBean find(int pid) throws SQLException {
			if (con == null) {
				setConnection();
			}
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT id, question_id, answer FROM correct_answers WHERE id = ?";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);
				st.setInt(1, pid);
				rs = st.executeQuery();
				CorrectAnswersBean bean = new CorrectAnswersBean();
				while (rs.next()) {
					int id = rs.getInt("id");
					int question_id = rs.getInt("question_id");
					String answer = rs.getString("answer");
					bean.setId(id);
					bean.setQuestion_id(question_id);
					bean.setAnswer(answer);
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
}
