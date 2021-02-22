package beans;

import java.sql.Timestamp;

public class QuestionsBean {
	private int id;
	private String question;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int questions_id;
	private int answers_id;
	private String answer;

	/**
	 * コンストラクタ
	 */
	public QuestionsBean(int id, String question) {
		this.id = id;
		this.question = question;
	}

	public QuestionsBean(int questions_id) {
		this.questions_id = questions_id;
	}

	//オーバーロードしてanswersを受け取る
	public QuestionsBean(int questions_id, String question, int answers_id, String answer) {
		this.questions_id = questions_id;
		this.question = question;
		this.answers_id = answers_id;
		this.answer = answer;
	}

	public QuestionsBean(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	//voidでdisplay的なメソッド名でデバッグできる

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

	//answersを受け取るgetterとsetter
	public int getQuestionsId() {
		return questions_id;
	}

	public void setQuestionsId(int questions_id) {
		this.questions_id = questions_id;
	}

	public int getAnswersId() {
		return answers_id;
	}

	public void setAnswersId(int answers_id) {
		this.answers_id = answers_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
