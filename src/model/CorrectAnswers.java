package model;

public class CorrectAnswers {
	private int id;
	private int questions_id;
	private String answer;
	private String created_at;
	private String updated_at;
	public CorrectAnswers(){}
	public CorrectAnswers(int id,int questions_id,String answer,String created_at,String updated_at){
		this.id=id;
		this.questions_id=questions_id;
		this.answer=answer;
		this.created_at=created_at;
		this.updated_at=updated_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestions_id() {
		return questions_id;
	}
	public void setQuestions_id(int questions_id) {
		this.questions_id = questions_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

}
