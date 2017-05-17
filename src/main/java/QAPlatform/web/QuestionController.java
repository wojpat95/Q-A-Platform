package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.service.*;
import QAPlatform.validator.QuestionValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Klasa będąca kontrolerem pytań.
 * @author Ahmad Anadani
 */

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@Autowired
    private UserService userService;	

	@Autowired
	private QuestionValidator questionValidator;
	/**
	 * @param model
	 * 		model pytania
	 * @return widok formularza słuzącego do dodawania pytań
	 */
	@RequestMapping(value="/newQuestion", method = RequestMethod.GET)
	public String newQuestion(Model model){
		model.addAttribute("newquestion", new Question()); 
		return "newQuestion";
	}
	
	/**
	 * Obsługa formularza dodawania pytań
	 * @param question
	 * 		zadane pytanie
	 * @param result
	 * 		rezultat łączenia danych z modelem
	 * @return widok formularza słuzącego do dodawania pytań wraz z błędami w wypadu niepowodzenia lub strona główna, w przypadku pomyślnej operacji
	 */
	@RequestMapping(value="/newQuestion",method =RequestMethod.POST)
	public String newQuestion(@ModelAttribute("newquestion") Question question, BindingResult result){
		
		questionValidator.validate(question, result);
		
		if(result.hasErrors()){
			return "newQuestion";
		}	

		question.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

		questionService.addQuestion(question);			

		return "redirect:/";
	}
	
	@RequestMapping(value="/editQuestion",method=RequestMethod.GET)
	public String editQuestion(Model model){
		model.addattribute("editquestion"); // Bartek, czy tutaj powinno przekazac new Answer() ??
		return "editQuestion";
	}
	
	@RequestMapping(value="/editQuestion", method=RequestMethod.POST)
	public String editQuestion(@ModelAttribute("editquestion") Question question, @RequestParam("topic") String topic,
			@RequestParam("body") String body,BindingResult result){
		questionValidator.validate(question,result);
		if(result.hasErrors()){
			return "editQuestion";
		}
		questionService.editQuestion(question,topic,body);
		
		return "redirect:/";
	}
	
}
