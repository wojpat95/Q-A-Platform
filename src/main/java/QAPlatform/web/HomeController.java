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
import org.springframework.security.core.Authentication;
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

	private final QuestionCategoryService questionCategoriesService;
	

	@Autowired
	public HomeController(QuestionService questionService, ObservedQuestionService observedQuestionService, UserService userService, QuestionCategoryService questionCategoriesService) {
		this.questionService = questionService;
		this.observedQuestionService = observedQuestionService;
		this.userService = userService;
		this.questionCategoriesService = questionCategoriesService;
	}
	boolean createdCategories = false;
	/**
	 * @param model model zawierający listę pytań
	 * @param request zapytanie
	 * @return widok strony głównej z listą wszystkich zadanych pytań
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request){
		if(!createdCategories){
			questionCategoriesService.addCategory("General");
			questionCategoriesService.addCategory("Sport");
			questionCategoriesService.addCategory("History");
			createdCategories = true;
		}
		String questionResultType="All questions";
		boolean observed = false;
		if(request.getSession().getAttribute("observed") != null){
			observed = (boolean)request.getSession().getAttribute("observed");
		}

		List<Question> questions = null;

		if(observed){
			questions = new ArrayList<>();
			Authentication a = SecurityContextHolder.getContext().getAuthentication();
			List<ObservedQuestion> observedQuestions = observedQuestionService.getAllObservedQuestions(
					userService.findByUsername(a.getName())
					);
			for(ObservedQuestion observedQuestionObj: observedQuestions){
				questions.add(observedQuestionObj.getQuestion());
			}
			questionResultType = "Observed questions";
		}else{
			questions = questionService.getAllQuestions();
		}
		
		List<QuestionCategory> categories = questionCategoriesService.getAllCategories();
		
		int sort = -1;
		if(request.getSession().getAttribute("sort") != null){
			sort = (int)request.getSession().getAttribute("sort");
		}
		if(sort == 1){
			Collections.sort(questions, new Comparator<Question>(){
				public int compare(Question question1, Question other){
					return question1.getTopic().compareTo(other.getTopic());
				}
			});
		}else if(sort == 2){
			Collections.sort(questions, new Comparator<Question>(){
				public int compare(Question question1, Question other){
					return question1.getUser().getUsername().compareTo(other.getUser().getUsername());
				}
			});
		}
		request.getSession().setAttribute("sort",-1);
		model.addAttribute("AllQuestions", questions);
		model.addAttribute("observed", observed);
		model.addAttribute("allCategories", categories);
		model.addAttribute("questionListType", questionResultType);
		return "home";
		
	}
		/**
		 * 
		 * @param model model pytania
		 * @return widok strony głównej z wylosowanym pytaniem
	 */
		@RequestMapping(value="/random",method = RequestMethod.GET)
		public String drawQuestion(Model model){
			List<Question> allQuestions = null;
			allQuestions = questionService.getAllQuestions();
			
			int listsize =allQuestions.size();
			Random n = new Random();
			Question question = allQuestions.get(n.nextInt(listsize));

			return "redirect:/Question/"+question.getId();
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
	
	@RequestMapping(value="/sort/{id}", method = RequestMethod.GET)
	public String sort(HttpServletRequest request, @PathVariable("id") int id){
		request.getSession().setAttribute("sort",id);
		return "redirect:/";
	}
	@RequestMapping(value="/stopObserve", method = RequestMethod.GET)
	public String stopObserve(HttpServletRequest request){

		request.getSession().setAttribute("observed",false);

		return "redirect:/";
	}
}
