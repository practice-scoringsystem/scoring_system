package model;

public class Questions {
		private int id;
		private String question;
		private String created_at;
		private String updated_at;
		public Questions(){}
		public Questions(int id,String question,String created_at, String updated_at){
			this.id=id;
			this.question=question;
			this.created_at=created_at;
			this.updated_at=updated_at;
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
