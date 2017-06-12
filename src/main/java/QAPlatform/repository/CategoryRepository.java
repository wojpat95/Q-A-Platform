package QAPlatform.repository;

import org.springframework.data.repository.CrudRepository;

import QAPlatform.model.Category;

/**
 * @author Bartosz Gierczak
 * Repository interface.
 */
public interface CategoryRepository extends CrudRepository<Category,Long>{

}
