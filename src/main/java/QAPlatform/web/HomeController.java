package QAPlatform.web;

import QAPlatform.model.ObservedQuestion;
import QAPlatform.model.Question;
import QAPlatform.model.QuestionCategory;
import QAPlatform.service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Klasa będąca głównym kontrolerem aplikacji.
 * @author Ahmad Anadani
 */

@Controller
public class HomeController {
	private final QuestionService questionService;
	private final ObservedQuestionService observedQuestionService;
	private final UserService userService;


	@Autowired

	private QuestionCategoryService questionCategoriesService;
	

	@Autowired


	public HomeController(QuestionService questionService, ObservedQuestionService observedQuestionService, UserService userService) {
		this.questionService = questionService;
		this.observedQuestionService = observedQuestionService;
		this.userService = userService;
	}
	boolean createdCategories = false;
	/**
	 * @param model
	 * 		model zawierający listę pytań
	 * @return widok strony głównej z listą wszystkich zadanych pytań
	 */
	@RequestMapping(value = "/{string}", method = RequestMethod.GET)
	public String home(@PathVariable("string") String sortKey,Model model, HttpServletRequest request){

		if(!createdCategories){
			questionCategoriesService.addCategory("General");
			questionCategoriesService.addCategory("Sport");
			questionCategoriesService.addCategory("History");
			createdCategories = true;
		}
		

		boolean observed = false;
		if(request.getSession().getAttribute("observed") != null){
			observed = (boolean)request.getSession().getAttribute("observed");
		}

		List<Question> questions = null;

		if(observed){
			questions = new ArrayList<>();
			List<ObservedQuestion> observedQuestions = observedQuestionService.getAllObservedQuestions(
					userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
					);
			for(ObservedQuestion observedQuestionObj: observedQuestions){
				questions.add(observedQuestionObj.getQuestion());
			}

		}else{
			questions = questionService.getAllQuestions();
		}

		
		List<QuestionCategory> categories = questionCategoriesService.getAllCategories();

		// ADD FILTERS
		
		boolean sort = false;
		if(request.getSession().getAttribute("sort") != null){
			sort = (boolean)request.getSession().getAttribute("sort");
		}
		
		switch(sortKey){
		case "topic":
			Collections.sort(questions, new Comparator<Question>(){
				public int compare(Question question1, Question other){
					return question1.getTopic().compareTo(other.getTopic());
				}
			});
			break;
		case "userName":
			Collections.sort(questions, new Comparator<Question>(){
				public int compare(Question question1, Question other){
					return question1.getUser().getUsername().compareTo(other.getUser().getUsername());
				}
			});
			break;
		default:
			//	throw new Exception("Uncorrect sort key");
			break;
		}
		model.addAttribute("AllQuestions", questions);
		model.addAttribute("observed", observed);

		model.addAttribute("allCategories", categories);
		model.addAttribute("questionListType", "All Questions");

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
	@RequestMapping(value="/observe", method = RequestMethod.GET)
	public String observe(HttpServletRequest request){
		request.getSession().setAttribute("observed",true);
		return "redirect:/";
	}

	@RequestMapping(value="/stopObserve", method = RequestMethod.GET)
	public String stopObserve(HttpServletRequest request){

		request.getSession().setAttribute("observed",false);

		return "redirect:/";
	}
	
	/**
	 * 
	 * @param model model pytania
	 * @return widok strony głównej z wylosowanym pytaniem
	 */
	@RequestMapping(value="/draw",method = RequestMethod.POST)
	public String drawQuestion(Model model){
		List<Question> questions = null;
		questions = questionService.getAllQuestions();
		
		int listsize = questions.size();
		Random n = new Random();
		Question question = questions.get(n.nextInt(listsize-1));
		
		model.addAttribute("AllQuestions",question);
		return "home";
}
}
