package model;

public class CorrectAnswers {

	private int id;
	private int questions_id;
	private String answer;
	private String created_at;
	private String updated_at;

	public CorrectAnswers(int id, int questions_id, String answer, String created_at, String updated_at) {
		this.id = id;
		this.questions_id = questions_id;
		this.answer = answer;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public CorrectAnswers() {
	}

	public int getId() {
		return id;
	}

	public int getQuestionId() {
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

	public String getCreatedAt() {
		return created_at;
	}

	public String getUpdatedAt() {
		return updated_at;
	}

}
