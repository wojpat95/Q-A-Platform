/**
 * 
 */
package QAPlatform.repository;

import org.springframework.data.repository.CrudRepository;

import QAPlatform.model.Question;

/**
 * @author Bartosz Gierczak
 * Repository interface.
 */
public interface QuestionRepository extends CrudRepository<Question,Long> {

}
