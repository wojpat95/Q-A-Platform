/**
 * 
 */
package QAPlatform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import QAPlatform.model.Question;
import QAPlatform.model.QuestionCategory;
import QAPlatform.model.User;

/**
 * @author Bartosz Gierczak
 * Repository interface.
 */
public interface QuestionRepository extends CrudRepository<Question,Long> {
		/**
		 * Znajdowanie pytań o temacie, który zawiera dany ciąg znaków
		 * @param topic Ciąg znaków, który chcemy znaleźć w temacie pytania
		 * @return Lista pytań z szukaną frazą
		 */
		List<Question> findByTopicIgnoreCaseContaining(String topic);
		List<Question> findByUser(User user);
		/**
		 * Znajdowanie pytań po kotegorii
		 * @param category identyfikator kategorii
		 * @return Lista pytań z danej kategorii
		 */
		List<Question> findByCategory(long category);
}
