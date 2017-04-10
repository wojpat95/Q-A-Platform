package QAPlatform.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String topic;
	private String body;
	private long likes;
	private long dislikes;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(name = "user_question", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @param id
	 * @param topic
	 * @param body
	 * @param likes
	 * @param dislikes
	 */
	public Question(String topic, String body, long likes, long dislikes,User user) {
		this.topic = topic;
		this.body = body;
		this.likes = likes;
		this.dislikes = dislikes;
		this.user = user;
	}
	public Question() {
		this.topic = "";
		this.body = "";
		this.likes = 0;
		this.dislikes = 0;
		this.user = null;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the likes
	 */
	public long getLikes() {
		return likes;
	}
	/**
	 * @param likes the likes to set
	 */
	public void setLikes(long likes) {
		this.likes = likes;
	}
	/**
	 * @return the dislikes
	 */
	public long getDislikes() {
		return dislikes;
	}
	/**
	 * @param dislikes the dislikes to set
	 */
	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
	
}
