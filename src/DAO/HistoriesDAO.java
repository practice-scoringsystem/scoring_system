package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;

import beans.HistoriesBean;

public class HistoriesDAO extends ConnectionDAO {
	public HistoriesDAO() throws SQLException  {
		setConnection();
	}
	public List<HistoriesBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, user_id, point FROM histories";
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
				HistoriesBean bean = new HistoriesBean(id, user_id, point);
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
		public HistoriesBean find(int pid) throws SQLException {
			if (con == null) {
				setConnection();
			}
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT id, user_id, point FROM histories WHERE id = ?";
				/** PreparedStatement オブジェクトの取得**/
				st = con.prepareStatement(sql);
				st.setInt(1, pid);
				rs = st.executeQuery();
				HistoriesBean bean = new HistoriesBean();
				while (rs.next()) {
					int id = rs.getInt("id");
					int user_id = rs.getInt("user_id");
					int point = rs.getInt("point");
					bean.setId(id);
					bean.setUser_id(user_id);
					bean.setPoint(point);
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

		/**
		 * レコードの新規作成
		 */
		public void create(HistoriesBean ub) throws SQLException {
			if (con == null) {
				setConnection();
			}
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				String sql = "INSERT INTO histories (user_id, point, created_at) values (?,?,?)";
				// 現在時刻を取得
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String strTimestamp = sdf.format(timestamp);
				st = con.prepareStatement(sql);
				st.setInt(1, ub.getUser_id());
				st.setInt(2, ub.getPoint());
				st.setString(3, strTimestamp);
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
	}
