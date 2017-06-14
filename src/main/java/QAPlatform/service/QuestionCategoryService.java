package QAPlatform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QAPlatform.model.QuestionCategory;
import QAPlatform.model.Question;
import QAPlatform.repository.QuestionCategoryRepository;

/**
 * @author Bartosz Gierczak
 *
 */
@Service
public class QuestionCategoryService {
	
	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;
	
	public void addCategory(String name){
		questionCategoryRepository.save(new QuestionCategory(name));
	}
	public List<QuestionCategory> getAllCategories(){
		List<QuestionCategory> categories = new ArrayList<>();
		questionCategoryRepository.findAll().forEach(categories::add);
		return categories;
	}
	
	public QuestionCategory getQuestionCategoryById(long id){
		return questionCategoryRepository.findOne(id);
	}
}
