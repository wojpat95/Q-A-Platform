/**
 * 
 */
package QAPlatform.web;


import QAPlatform.model.Question;
import QAPlatform.service.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * @author root
 *
 */
@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String welcome(Model model){
		List<Question> questions = null;
		questions = questionService.getAllQuestions();
		model.addAttribute("AllQuestions", questions);
		return "welcome";
	}
	
	@RequestMapping(value="/newQuestion", method = RequestMethod.GET)
	public String newQuestion(Model model){
		model.addAttribute("newquestion", new Question()); // tutaj jest jakis dev o id="newquestion"
		return "/newQuestion";
	}
	
	@RequestMapping(value="/newQuestion",method =RequestMethod.POST)
	public String newQuestion(@ModelAttribute("newquestion") Question question, BindingResult result){
		if(result.hasErrors()){
			return "newQiestion";
		}
		questionService.addQuestion(question);
		return "/welcome";
	
	}
	
	
}
