package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.HistoriesBean;

public class HistoriesDAO extends ConnectionDAO {
	public HistoriesDAO() throws SQLException {
		setConnection();
	}

	public List<HistoriesBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, user_id, point, created_at FROM histories";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<HistoriesBean> list = new ArrayList<HistoriesBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int point = rs.getInt("point");
				Timestamp created_at = rs.getTimestamp("created_at");
				HistoriesBean bean = new HistoriesBean(id, user_id, point, created_at);
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
	public List<HistoriesBean> findByUserId(int userId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, user_id, point, created_at FROM histories WHERE user_id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			rs = st.executeQuery();
			List<HistoriesBean> list = new ArrayList<HistoriesBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				userId = rs.getInt("user_id");
				int point = rs.getInt("point");
				Timestamp created_at = rs.getTimestamp("created_at");
				HistoriesBean bean = new HistoriesBean(id, userId, point, created_at);
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
	public void create(HistoriesBean hb) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO histories (user_id, point, created_at) values (?,?,current_timestamp())";
			st = con.prepareStatement(sql);
			st.setInt(1, hb.getUserId());
			st.setInt(2, hb.getPoint());
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
	public int delete(int historyId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "DELETE FROM histories WHERE id = ?";
			st = con.prepareStatement(sql);

			st.setInt(1, historyId);
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

	/*
	 * pointカラムを更新する
	 */
	public void update(HistoriesBean historiesBean) throws SQLException {
		if (con == null) {
			setConnection();
		}

		PreparedStatement st = null;

		int id = historiesBean.getId();
		int point = historiesBean.getPoint();
		try {
			String sql = "UPDATE histories "
					+ "SET point = "
					+ "point + 1 "
					+ "WHERE id = ?";

			st = con.prepareStatement(sql);
			st.setInt(1, point);
			st.setInt(2, id);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("更新に失敗しました");
		}
	}

}
