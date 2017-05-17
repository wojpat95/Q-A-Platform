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
	List<Answer> findByQuestion(Question q);
}
