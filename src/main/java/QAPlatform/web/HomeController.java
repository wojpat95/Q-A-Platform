package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.service.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model){
		List<Question> questions = null;
		questions = questionService.getAllQuestions();
		System.out.println(questions.toString());
		model.addAttribute("AllQuestions", questions);
		return "home";
	}
}
