package beans;

import java.sql.Timestamp;

public class CorrectAnswersBean {

	private int id;
	private int question_id;
	private String answer;
	private Timestamp created_at;
	private Timestamp updated_at;

/**
 * コンストラクタ
 */
	public CorrectAnswersBean(int id, int question_id, String answer) {
		this.id = id;
		this.question_id = question_id;
		this.answer = answer;
	}
	/** 引数無しのコンストラクタ **/
	public CorrectAnswersBean() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
