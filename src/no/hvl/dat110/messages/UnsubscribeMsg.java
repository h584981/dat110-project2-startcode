package no.hvl.dat110.messages;

public class UnsubscribeMsg extends Message {

	// message sent from client to unsubscribe on a topic 

	// TODO:
	// Implement object variables - a topic is required

	// Constructor, get/set-methods, and toString method
	// as described in the project text
	
	//	These are not neccecary, why?
//	private MessageType type;
//	private String user;
	
	private String topic;
	
	/**
	 * Constructor ++ more JavaDoc
	 */
	public UnsubscribeMsg (String user, String topic) {
		super(MessageType.UNSUBSCRIBE, user);
		this.topic = topic;
	}
	
	
//		These are not neccecary, why?
//	public MessageType getType() { return this.type; }
//	public String getUser() { return this.user; }
//	public void setUser(String user) { this.user = user; }

	
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
