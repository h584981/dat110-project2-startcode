package no.hvl.dat110.messages;

public class CreateTopicMsg extends Message {
	
	// message sent from client to create topic on the broker
	
	// TODO: 
	// Implement object variables - a topic is required
	
	// Constructor, get/set-methods, and toString method
    // as described in the project text	
	
	
	/*
	 * These are not neccecary, why?
	 */
	//private MessageType type;
	// private String user;
	
	
	private String topic;
	
	/**
	 * Constructor ++ more JavaDoc
	 */
	public CreateTopicMsg (String user, String topic) {
		super(MessageType.CREATETOPIC, user);
		this.topic = topic;
	}
	
	
	/*
	 * These are not neccecary, why?
	 */
	// public MessageType getType() { return this.type; }
	// public String getUser() { return this.user; }
	// public void setUser(String user) { this.user = user; }
	
	
	/**
	 *  getters and setters ++ more Javadoc
	 */
	
	public String getTopic() { return this.topic; }
	
	
	public void setTopic(String topic) { this.topic = topic; }
	
	
	/**
	 * toString ++ more Javadoc
	 */
	public String toString() { return super.toString(); }
	
}
