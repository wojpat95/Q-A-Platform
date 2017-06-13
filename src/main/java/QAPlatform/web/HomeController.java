package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.model.QuestionCategory;
import QAPlatform.service.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Klasa będąca głównym kontrolerem aplikacji.
 * @author Ahmad Anadani
 */

@Controller
public class HomeController {
	private final QuestionService questionService;
	
	@Autowired
	private QuestionCategoryService questionCategoriesService;
	
	@Autowired
	public HomeController(QuestionService questionService) {
		this.questionService = questionService;
	}
	boolean createdCategories = false;
	/**
	 * @param model
	 * 		model zawierający listę pytań
	 * @return widok strony głównej z listą wszystkich zadanych pytań
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model){
		
		List<Question> questions = null;
		questions = questionService.getAllQuestions();
		
		if(!createdCategories){
			questionCategoriesService.addCategory("General");
			questionCategoriesService.addCategory("Sport");
			questionCategoriesService.addCategory("History");
			createdCategories = true;
		}
		
		List<QuestionCategory> categories = questionCategoriesService.getAllCategories();
		
		String type = "All Questions";
		
		model.addAttribute("AllQuestions", questions);
		model.addAttribute("allCategories", categories);
		model.addAttribute("questionListType", type);
		return "home";
		
	}
	/**
	 * @param topic temat, wg którego wyszukiwane są pytania
	 * @param model model pytania
	 * @return przekierowanie do strony głównej z listą wyszukiwanych pytań
	 */
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String home(@RequestParam("search_expr") String topic,Model model){
		
		List<Question> questions = questionService.searchQuestionByTopic(topic);

		model.addAttribute("AllQuestions", questions);
		return "home";
		
	}
	
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") int id, Model model){
		
		QuestionCategory category = questionCategoriesService.getQuestionCategoryById(id);
		List<Question> questions = questionService.searchQuestionByCategory(id);
		List<QuestionCategory> categories = questionCategoriesService.getAllCategories();
		model.addAttribute("AllQuestions", questions);
		model.addAttribute("allCategories", categories);
		model.addAttribute("questionListType", category.getName());
		return "home";
		
	}
	
}
