package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Klasa będąca głównym kontrolerem aplikacji.
 * @author Ahmad Anadani
 */

@Controller
public class HomeController {
	private final QuestionService questionService;

	@Autowired
	public HomeController(QuestionService questionService) {
		this.questionService = questionService;
	}
	/**
	 * @param model
	 * 		model zawierający listę pytań
	 * @return widok strony głównej z listą wszystkich zadanych pytań
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model){
		List<Question> questions = null;
		questions = questionService.getAllQuestions();
		
		model.addAttribute("AllQuestions", questions);
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
	/**
	 * 
	 * @param model model pytania
	 * @return widok strony głównej z wylosowanym pytaniem
	 */
	@RequestMapping(value="/draw",method = RequestMethod.GET)
	public String drawQuestion(Model model){
		List<Question> questions = null;
		List<Question> question = null;
		questions = questionService.getAllQuestions();
		
		int listsize = questions.size();
		Random n = new Random();
		question.add(questions.get(n.nextInt(listsize-1)));
		System.out.println("MY OUTPUT     " + n.nextInt(listsize-1));
		model.addAttribute("AllQuestions",question);
		return "home";
	}
	
	@RequestMapping(value="/sort/{string}",method = RequestMethod.POST)
	public String sortQuestions(@PathVariable("string") String string,Model model){
		List<Question> questions = null;
		
		switch(string){
		case "Topic":
			{
				questions = questionService.getQuestionsSortedByTopic();
				break;
			}
		//case "Category":
		//	{
			//	questions = questionService.getQuestionsSortedByCategory();
			//break;
			//}
		}
		
		model.addAttribute("AllQuestions",questions);
		return "home";
		
	}
}
