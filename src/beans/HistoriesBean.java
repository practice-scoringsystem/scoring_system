package beans;

import java.sql.Timestamp;

public class HistoriesBean {
	private int id;
	private int user_id;
	private int point;
	private Timestamp created_at;

/**
 * コンストラクタ
 */
	public HistoriesBean(int id, int user_id, int point) {
		this.id = id;
		this.user_id = user_id;
		this.point = point;
	}
	/** 引数無しのコンストラクタ **/
	public HistoriesBean() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

}
