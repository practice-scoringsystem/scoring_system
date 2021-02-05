package model;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;

public class Users {

//	private Connection con = null;
//	private PreparedStatement ps = null;
//	private ResultSet rs = null;
//
//	public ArryList<Users> getUserList() {
//
//		ArrayList<Users> list = new ArrayList<Users>();
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			String url = "jdbc:mysql://localhost/java_db";
//			String user = "root";
//			String password = "";
//
//			con = DriverManager.getConnection(url, user, password);
//
//			String sql = "SELECT * FROM java_db";
//			ps = con.prepareStatement(sql);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//
//				int id = rs.getInt("id");
//				String name = rs.getString("name");
//				Users users = new Users(id, name);
//				list.add(users);
//			}
//
//			close();
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//	private void close() throws SQLException {
//		if (con != null) {
//			con.close();
//		}
//		if (ps != null) {
//			ps.close();
//		}
//		if (rs != null) {
//			rs.close();
//		}
//	}

	private int id;
	private String name;
	private String password;
	private String created_at;
	private String deleted_at;
	private String updated_at;
	private byte deleteflag;
	public Users(){}
	public Users(int id,String name,String password,String created_at,
			      String deleted_at,String updated_at,byte deleteflag){
		this.id=id;
		this.name=name;
		this.password=password;
		this.created_at=created_at;
		this.deleted_at=deleted_at;
		this.updated_at=updated_at;
		this.deleteflag=deleteflag;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public byte getDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}


}
