package QAPlatform.service;

import QAPlatform.model.ObservedQuestion;
import QAPlatform.model.Question;
import QAPlatform.model.User;

import java.util.List;

/**
 * Created by wojtek on 13.06.17.
 */
public interface ObservedQuestionService {
    List<ObservedQuestion> getAllObservedQuestions(User user);

    ObservedQuestion getObservedQuestion(User user, Question question);

    void observeQuestion(ObservedQuestion question);

    void removeQuestion(User user, Question question);
}
