/**
 * 
 */
package QAPlatform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import QAPlatform.model.Answer;
import QAPlatform.model.Question;

/**
 * @author Ahmad Anadani
 * Repository interface.
 */
public interface AnswerRepository extends CrudRepository<Answer,Long> {
	/**
	 * Znajdowanie odpowiedzi do danego pytania
	 * @param q Pytanie, do którego chcemy znaleźć odpowiedzi
	 * @return Lista odpowiedzi do danego pytania
	 */
	List<Answer> findByQuestion(Question q);
}
