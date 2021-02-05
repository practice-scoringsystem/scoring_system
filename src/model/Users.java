package model;



public class Users {
	private int id;
	private String name;
	private String password;
	private String created_at;
	private String deleted_at;
	private String updated_at;
	private byte deleteflag;
	
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
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getCreated_at() {
		return created_at;
	}
	public String getDeleted_at() {
		return deleted_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public byte getDeleteflag() {
		return deleteflag;
	}
}
