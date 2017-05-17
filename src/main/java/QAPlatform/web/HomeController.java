package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.service.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		List<Question> questions = new ArrayList<>();
		questions = questionService.getAllQuestions();
//		System.out.println(questions.toString());
		model.addAttribute("AllQuestions", questions);
		return "home";
	}
}
