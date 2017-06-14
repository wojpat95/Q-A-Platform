package QAPlatform.model;

import javax.persistence.*;

/**
 * Klasa będąca modelem obserwowanych pytań.
 * @author Wojciech Patyna
 */

@Entity
@Table(name = "observedQuestions")
public class ObservedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne()
    @JoinTable(name = "observedQuestion_user", joinColumns = @JoinColumn(name = "observedQuestions_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @ManyToOne()
    @JoinTable(name = "observedQuestion_question", joinColumns = @JoinColumn(name = "observedQuestions_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Question question;

    /**
     *
     * @param user użytkownik który obserwuje pytanie
     * @param question pytanie które jest obserwowane
     */
    public ObservedQuestion(User user, Question question){
        this.user = user;
        this.question = question;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return użytkownik który obserwuje pytanie
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user użytkownik który obserwuje pytanie
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return pytanie które jest obserwowane
     */
    public Question getQuestion() {
        return question;
    }

    /**
     *
     * @param question pytanie które bedzie obserwowane
     */
    public void setQuestion(Question question) {
        this.question = question;
    }
}
