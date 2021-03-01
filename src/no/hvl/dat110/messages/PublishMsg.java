package no.hvl.dat110.messages;

// import no.hvl.dat110.common.TODO;

public class PublishMsg extends Message {
	
	// message sent from client to create publish a message on a topic 

	// TODO:
	// Implement object variables - a topic and a message is required

	// Constructor, get/set-methods, and toString method
	// as described in the project text
	
	//private MessageType type;
	//private String user;
	private String message;
	private String topic;
	
	/**
	 * Constructor ++ more JavaDoc
	 */
	public PublishMsg (String user, String topic, String message) {
		super(MessageType.PUBLISH, user);
		this.message = message;
		this.topic = topic;
	}
	
	
	/**
	 *  getters and setters ++ more Javadoc
	 */
	//public MessageType getType() { return this.type; }
	
	//public String getUser() { return this.user; }
	
	public String getTopic() { return this.topic; }
	
	public String getMessage() { return this.message; }
	
	public void setTopic(String topic) { this.topic = topic; }
	
	public void setMessage(String message) { this.message = message; }
	
	//public void setUser(String user) { this.user = user; }
	
	/**
	 * toString ++ more Javadoc
	 */
	public String toString() { 
		return "Message [type=" + super.getType() + ", user=" + super.getUser() + ", topic=" + topic + ", messageText=" + message +  "]";
		
	}
}