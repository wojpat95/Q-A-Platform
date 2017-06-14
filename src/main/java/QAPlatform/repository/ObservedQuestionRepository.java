package QAPlatform.repository;

import QAPlatform.model.ObservedQuestion;
import QAPlatform.model.Question;
import QAPlatform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wojtek on 13.06.17.
 */
public interface ObservedQuestionRepository extends CrudRepository<ObservedQuestion,Long> {

    @Transactional
    void deleteByUserAndQuestion(User user, Question question);

    List<ObservedQuestion> findByUser(User user);

    ObservedQuestion findByUserAndQuestion(User user, Question question);
}
