package beans;

import java.sql.Timestamp;

public class CorrectAnswersBean {

	private int id;
	private int questions_id;
	private String answer;
	private int answers_id;
	private Timestamp created_at;
	private Timestamp updated_at;

	/**
	 * コンストラクタ
	 */
	public CorrectAnswersBean(int id, int questions_id, String answer) {
		this.id = id;
		this.questions_id = questions_id;
		this.answer = answer;
	}

	public CorrectAnswersBean(int answers_id) {
		this.answers_id = answers_id;
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

	public int getQuestionsId() {
		return questions_id;
	}

	public void setQuestionsId(int questions_id) {
		this.questions_id = questions_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public int getAnswersId() {
		return answers_id;
	}

	public void setAnswersId(int answers_id) {
		this.answers_id = answers_id;
	}


	public void debugDiaplay() {
		System.out.println("id:" + String.valueOf(id));
		System.out.println("answer:" + answer);
		System.out.println("questions_id:" + String.valueOf(questions_id));
		System.out.println("answers_id:" + String.valueOf(answers_id));
		System.out.println("answer:" + answer);
	}



}
