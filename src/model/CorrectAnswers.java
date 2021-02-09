package model;

public class CorrectAnswers {

	private int id;
	private int question_id;
	private String answer;
	private String created_at;
	private String updated_at;

	public CorrectAnswers(int id, int question_id, String answer, String created_at, String updated_at) {
		this.id = id;
		this.question_id = question_id;
		this.answer = answer;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public int getQuestionId() {
		return question_id;
	}

	public String getAnswer() {
		return answer;
	}

	public String getCreatedAt() {
		return created_at;
	}

	public String getUpdatedAt() {
		return updated_at;
	}

}
