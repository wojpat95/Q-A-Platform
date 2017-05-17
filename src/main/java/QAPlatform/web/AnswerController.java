package QAPlatform.web;

import QAPlatform.model.Answer;
import QAPlatform.service.*;
import QAPlatform.validator.AnswerValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Klasa będąca kontrolerem Odpowiedzi.
 * @author Ahmad Anadani
 */

@Controller
public class AnswerController {
	@Autowired
	private AnswerService answerService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private AnswerValidator answerValidator;
	
	@RequestMapping(value="/Answer/new", method=RequestMethod.GET)
	public String newAnswer(Model model){
		
		model.addAttribute("newanswer", new Answer());
		return "newAnswer";
		
	}
	
	@RequestMapping(value="/Answer/new", method=RequestMethod.POST)
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
	
	@RequestMapping(value="/Answer/edit/{id}",method=RequestMethod.GET)
	public String editAnswer(@PathVariable("id") int id,Model model){
		
		model.addAttribute("editanswer",answerService.getAnswerById(id));
		return "editAnswer";
		
	}
	
	@RequestMapping(value="/Answer/edit/{id}",method=RequestMethod.POST)//czy id konieczne?
	public String editAnswer(@ModelAttribute("editanswer") Answer answer, BindingResult result){
		
		answerValidator.validate(answer,result);
		
		if(result.hasErrors()){
			return "editAnswer";
		}
		
		answerService.addAnswer(answer);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/Answer/remove/{id}", method=RequestMethod.POST)
	public String removeQuestion(@PathVariable("id") int id, BindingResult result){
		
		answerService.removeAnswer(id);
		
		return "redirect:/";
	}
}
