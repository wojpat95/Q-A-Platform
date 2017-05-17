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

/**
 * Klasa będąca modelem pytań.
 * @author Bartosz Gierczak
 */
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
	private String Category;
	//private String Time;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(name = "user_question", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
	
	/**
	 * @param topic temat pytania
	 * @param body ciało pytania
	 * @param likes liczba polubień pytania
	 * @param dislikes liczba niepolubień pytania
	 * @param user użytkownik, który zadał pytanie
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
	 * @return użytkownik który zadał pytanie
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user użytkownik, który ustawiony będzie jako właściciel pytania
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return identyfikator pytania
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id identyfikator do ustawienia
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return temat pytania
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic temat do ustawienia
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return ciało pytania
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body ciało do ustawienia
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return liczba polubień
	 */
	public long getLikes() {
		return likes;
	}
	/**
	 * @param likes liczba polubień do ustawienia
	 */
	public void setLikes(long likes) {
		this.likes = likes;
	}
	/**
	 * @return liczba nielubiących
	 */
	public long getDislikes() {
		return dislikes;
	}
	/**
	 * @param dislikes liczba nielubiącyh do ustawienia
	 */
	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
	/**
	 * @return zwraca kategorię
	 */
	public String getCategory() {
		return Category;
	}
	/**
	 * @param category ustawia kategorię
	 */
	public void setCategory(String category) {
		Category = category;
	}
	
}
