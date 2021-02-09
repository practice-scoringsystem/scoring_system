package model;

public class Histories {
	private int id;
	private int user_id;
	private int point;
	private String created_at;

	public Histories() {
	}

	public Histories(int id, int user_id, int point, String created_at) {
		this.id = id;
		this.user_id = user_id;
		this.point = point;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public int getUsersId() {
		return user_id;
	}

	public int getPoint() {
		return point;
	}

	public String getCreatedAt() {
		return created_at;
	}
}
