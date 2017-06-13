package QAPlatform.model;

import javax.persistence.*;

/**
 * Created by wojtek on 12.06.17.
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


    public ObservedQuestion(User user, Question question){
        this.user = user;
        this.question = question;
    }

    public ObservedQuestion(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
