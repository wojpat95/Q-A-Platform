package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@Autowired
    private UserService userService;
	
	@RequestMapping(value="/newQuestion", method = RequestMethod.GET)
	public String newQuestion(Model model){
		model.addAttribute("newquestion", new Question()); 
		return "newQuestion";
	}
	
	@RequestMapping(value="/newQuestion",method =RequestMethod.POST)
	public String newQuestion(@ModelAttribute("newquestion") Question question, BindingResult result){
		if(result.hasErrors()){
			return "newQuestion";
		}		
		question.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		questionService.addQuestion(question);

		return "home";
	}
}
