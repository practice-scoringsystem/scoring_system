package beans;

import java.sql.Timestamp;

public class QuestionsBean {
	private int id;
	private String question;
	private Timestamp created_at;
	private Timestamp updated_at;

/**
 * コンストラクタ
 */
	public QuestionsBean(int id, String question) {
		this.id = id;
		this.question = question;
	}
	/** 引数無しのコンストラクタ **/
	public QuestionsBean() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
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

}
