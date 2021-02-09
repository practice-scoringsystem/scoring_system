package model;

public class Questions {

		private int id;
		private String question;
		private String created_at;
		private String updated_at;

		public Questions(int id,String question,String created_at, String updated_at){
			this.id=id;
			this.question=question;
			this.created_at=created_at;
			this.updated_at=updated_at;
		}
		public int getId() {
			return id;
		}
		public String getQuestion() {
			return question;
		}
		public String getCreatedAt() {
			return created_at;
		}
		public String getUpdatedAt() {
			return updated_at;
		}
	}
