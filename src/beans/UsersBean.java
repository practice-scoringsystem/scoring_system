package beans;

import java.sql.Timestamp;

public class UsersBean {
	private int id;
	private String name;
	private String password;
	private Timestamp created_at;
	private Timestamp updated_at;
	private byte deleteflag;
	private Timestamp deleted_at;

/**
 * コンストラクタ
 */
	public UsersBean(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	/** 引数無しのコンストラクタ **/
	public UsersBean() {

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
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public byte getDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}
	public Timestamp getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(Timestamp deleted_at) {
		this.deleted_at = deleted_at;
	}

}
