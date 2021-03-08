package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;

import beans.UsersBean;

public class UsersDAO extends ConnectionDAO {

	public UsersDAO() throws SQLException {
		setConnection();
	}

	@SuppressWarnings("unchecked")
	public List<UsersBean> findAll() throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, name, password, admin_flag FROM users WHERE deleteflag = 0";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<UsersBean> list = new ArrayList<UsersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				byte admin_flag = rs.getByte("admin_flag");
				UsersBean bean = new UsersBean(id, name, pass, admin_flag);
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
	public UsersBean find(int pid) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, name, password FROM users WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, pid);
			rs = st.executeQuery();
			UsersBean bean = new UsersBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(pass);
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

	public void create(UsersBean ub) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO users (name, password, created_at, updated_at) values (?,?,?,?)";
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd HH:mm:ss");
			String strTimestamp = sdf.format(timestamp);
			st = con.prepareStatement(sql);
			st.setString(1, ub.getName());
			st.setString(2, ub.getPassword());
			st.setString(3, strTimestamp);
			st.setString(4, strTimestamp);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("レコードの操作に失敗しました。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException("リソースの開放に失敗しました");
			}
		}
	}

	public void delete(int userId) throws SQLException {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE users SET deleteflag = ?, deleted_at = ? WHERE id = ?";
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String strTimestamp = sdf.format(timestamp);
			st = con.prepareStatement(sql);

			//削除フラグを立てる
			st.setInt(1, 1);
			st.setString(2, strTimestamp);
			st.setInt(3, userId);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("レコードの操作に失敗しました。");
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
