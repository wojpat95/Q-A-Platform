package QAPlatform.repository;

import QAPlatform.model.ObservedQuestion;
import QAPlatform.model.Question;
import QAPlatform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wojciech Patyna
 * Repository interface.
 */
public interface ObservedQuestionRepository extends CrudRepository<ObservedQuestion,Long> {
    /**
     * Usuwanie oznaczonego pytania po użytkowniku i pytaniu
     * @param user użytkownik
     * @param question pytanie
     */
    @Transactional
    void deleteByUserAndQuestion(User user, Question question);

    /**
     *
     * @param user użytkownik
     * @return zwraca obserwowane pytania danego użytkownika
     */
    List<ObservedQuestion> findByUser(User user);

    /**
     * szuka pytania po użytkowniku i pytaniu
     * @param user użytkownik
     * @param question pytanie
     * @return pytanie komkretnego użytkownika
     */
    ObservedQuestion findByUserAndQuestion(User user, Question question);
}
