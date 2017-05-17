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
 * Klasa będąca modelem odpowiedzi.
 * @author Bartosz Gierczak
 */
@Entity
@Table(name = "answers")
public class Answer {
	/**
	 * @param id identyfikator odpowiedzi
	 * @param body ciało odpowiedzi
	 * @param likes liczba polubień 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String body;
	private long likes;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(name = "user_answer", joinColumns = @JoinColumn(name = "answer_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(name = "question_answer", joinColumns = @JoinColumn(name = "answer_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private Question question;

	/**
	 * @return identyfikator odpowiedzi
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
	 * 
	 * @return ciało odpowiedzi
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
	 * 
	 * @return likes liczba polubień
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
	 * @return user użytkownik który udzielił odpowiedź
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @param user użytkownik do ustawienia
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return question pytanie, którego dotyczy odpowiedź
	 */
	public Question getQuestion() {
		return question;
	}
	
	/**
	 * @param question pytanie do ustawienie
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
}
