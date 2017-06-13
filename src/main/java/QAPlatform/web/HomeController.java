package QAPlatform.web;

import QAPlatform.model.ObservedQuestion;
import QAPlatform.model.Question;
import QAPlatform.service.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public HomeController(QuestionService questionService, ObservedQuestionService observedQuestionService, UserService userService) {
		this.questionService = questionService;
		this.observedQuestionService = observedQuestionService;
		this.userService = userService;
	}
	/**
	 * @param model
	 * 		model zawierający listę pytań
	 * @return widok strony głównej z listą wszystkich zadanych pytań
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request){
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

		// ADD FILTERS

		model.addAttribute("AllQuestions", questions);
		model.addAttribute("observed", observed);
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
}
