package QAPlatform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QAPlatform.model.Category;
import QAPlatform.model.Question;
import QAPlatform.repository.CategoryRepository;

/**
 * @author Bartosz Gierczak
 *
 */
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void addCategory(String name){
		categoryRepository.save(new Category(name));
	}
	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(categories::add);
		return categories;
	}
}
