package QAPlatform.web;

import QAPlatform.model.Answer;
import QAPlatform.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Klasa będąca kontrolerem Odpowiedzi.
 * @author Ahmad Anadani
 */

@Controller
public class AnswerController {
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private AnswerValidator answerValidator;
	
	@RequestMapping(value="/newAnswer", method=RequestMethod.GET)
	public String newAnswer(Model model){
		model.addAttribute("newanswer", new Answer());
		return "newAnswer";
	}
	
	@RequestMapping(value="/newAnswer", method=RequestMethod.POST)
	public String newAnswer(@ModelAttribute("newanswer") Answer answer, BindingResult result){
		answerValidator.validate(answer,result);
		
		if(result.hasErrors()){
			return "newAnswer";
		}
		
		// PS: tutaj nie powinno setQuestion zeby ustawic odpowiedz do konkretnego pytania?
		//answer.setQuestion()
		
		answer.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		answerService.addAnswer(answer);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/editAnswer",method=RequestMethod.GET)
	public String editAnswer(Model model){
		model.addAttribute("editanswer");
		return "/editAnswer";
	}
	
	@RequestMapping(value="/editAnswer",method=RequestMethod.POST)
	public String editAnswer(@ModelAttribute("editanswer") Answer answer, @RequestParam("body") String body,
			BindingResult result){
		answerValidator.validate(answer,result);
		
		if(result.hasErrors()){
			return "editAnswer";
		}
		
		answerService.editAnswer(answer,body);
		
		return "redirect:/";
	}
	
}
