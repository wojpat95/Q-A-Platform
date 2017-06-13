package QAPlatform.repository;

import org.springframework.data.repository.CrudRepository;

import QAPlatform.model.QuestionCategory;

/**
 * @author Bartosz Gierczak
 * Repository interface.
 */
public interface QuestionCategoryRepository extends CrudRepository<QuestionCategory,Long>{

}
