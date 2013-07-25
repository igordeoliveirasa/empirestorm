package app.models;

@javax.persistence.Entity
public class Feedback extends Entity {

	private String facebookUserId;
	private String subject;
	private String message;

	public void setFacebookUserId(String facebookUserId) {
		this.facebookUserId = facebookUserId;
	}

	public String getFacebookUserId() {
		return facebookUserId;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
