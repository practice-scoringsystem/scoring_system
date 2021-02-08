package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.QuestionsBean;

public class QuestionsDAO extends ConnectionDAO {
	public QuestionsDAO() throws SQLException  {
		setConnection();
	}
	public List<QuestionsBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question FROM questions;";
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
		public QuestionsBean find(int pid) throws SQLException {
			if (con == null) {
				setConnection();
			}
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT id, question FROM questions WHERE id = ?";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);
				st.setInt(1, pid);
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
}