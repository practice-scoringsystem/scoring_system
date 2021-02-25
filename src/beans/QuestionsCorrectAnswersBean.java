package beans;

import java.sql.Timestamp;

public class QuestionsCorrectAnswersBean {
	private int questions_id;
	private int id;
	private String answers_id;
	private String answer;
	private Timestamp created_at;
	private Timestamp updated_at;

	/**
	 * コンストラクタ
	 */
	public QuestionsCorrectAnswersBean(int questions_id, String answers_id, String answer) {
		this.questions_id = questions_id;
		this.answers_id = answers_id;
		this.answer = answer;
	}

	public QuestionsCorrectAnswersBean(String answers_id) {
		this.answers_id = answers_id;
	}

	public QuestionsCorrectAnswersBean(int id, int questions_id, String answer) {
		this.id = id;
		this.questions_id = questions_id;
		this.answer = answer;
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

	public String getAnswersId() {
		return answers_id;
	}

	public void setAnswersId(String answers_id) {
		this.answers_id = answers_id;
	}

	public int getId() {
		return id;
	}
}
