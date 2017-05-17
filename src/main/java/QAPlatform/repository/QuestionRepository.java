/**
 * 
 */
package QAPlatform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import QAPlatform.model.Question;

/**
 * @author Bartosz Gierczak
 * Repository interface.
 */
public interface QuestionRepository extends CrudRepository<Question,Long> {

		List<Question> findByTopicIgnoreCaseContaining(String topic);
}
