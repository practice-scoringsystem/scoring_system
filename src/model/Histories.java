package model;

public class Histories {
  private int id;
  private int users_id;
  private int point;
  private String created_at;
  public Histories(){}
	public Histories(int id,int users_id,int point,String created_at){
		this.id=id;
		this.users_id=users_id;
		this.point=point;
		this.created_at=created_at;
	}
	public int getId() {
		return id;
	}
	public int getUsers_id() {
		return users_id;
	}
	public int getPoint() {
		return point;
	}
	public String getCreated_at() {
		return created_at;
	}
}
