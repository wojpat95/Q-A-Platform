package QAPlatform.service;

import QAPlatform.model.ObservedQuestion;
import QAPlatform.model.Question;
import QAPlatform.model.User;
import QAPlatform.repository.ObservedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wojtek on 13.06.17.
 */

@Service
public class ObservedQuestionServiceImpl implements ObservedQuestionService {

    @Autowired
    private ObservedQuestionRepository observedQuestionRepository;

    @Override
    public List<ObservedQuestion> getAllObservedQuestions(User user) {
        List<ObservedQuestion> observedQuestions = new ArrayList<>();
        observedQuestionRepository.findByUser(user).forEach(observedQuestions::add);
        return observedQuestions;
    }

    @Override
    public ObservedQuestion getObservedQuestion(User user, Question question) {
        return observedQuestionRepository.findByUserAndQuestion(user, question);
    }

    @Override
    public void observeQuestion(ObservedQuestion question){
        observedQuestionRepository.save(question);
    }

    @Override
    public void removeQuestion(User user, Question question) {
        observedQuestionRepository.deleteByUserAndQuestion(user, question);
    }



}
