package beans;

import java.sql.Timestamp;

public class QuestionsCorrectAnswersBean {
	private int questions_id;
	private String answer;
	private Timestamp created_at;
	private Timestamp updated_at;

	/**
	 * コンストラクタ
	 */
	public QuestionsCorrectAnswersBean(int questions_id, String answer) {
		this.questions_id = questions_id;
		this.answer = answer;
	}

	/** 引数無しのコンストラクタ **/
	public QuestionsCorrectAnswersBean() {

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

}
