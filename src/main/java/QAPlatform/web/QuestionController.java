package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.model.QuestionCategory;
import QAPlatform.service.*;
import QAPlatform.validator.QuestionValidator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Klasa będąca kontrolerem pytań.
 * @author Ahmad Anadani
 */

@Controller
public class QuestionController {
	private final QuestionService questionService;
	private final UserService userService;

	private final QuestionValidator questionValidator;
	
	@Autowired
	private QuestionCategoryService questionCategoriesService;
	@Autowired
	public QuestionController(QuestionService questionService, UserService userService, QuestionValidator questionValidator) {
		this.questionService = questionService;
		this.userService = userService;
		this.questionValidator = questionValidator;
	}

	/**
	 * @param model
	 * 		model pytania
	 * @return widok formularza słuzącego do dodawania pytań
	 */
	@RequestMapping(value="/Question/new", method = RequestMethod.GET)
	public String newQuestion(Model model){
		List<QuestionCategory> categories = questionCategoriesService.getAllCategories();
		model.addAttribute("newquestion", new Question()); 
		model.addAttribute("allCategories", categories);
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
	@RequestMapping(value="/Question/new",method =RequestMethod.POST)
	public String newQuestion(@ModelAttribute("newquestion") Question question, BindingResult result){
		System.out.println(question.getCategory());
		questionValidator.validate(question, result);
		
		if(result.hasErrors()){
			return "redirect:/Question/new/";
		}	

		question.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

		questionService.addQuestion(question);

		return "redirect:/";
	}
	
	/**
	 * @param id identyfikator pytania
	 * @param model model pytania
	 * @return widok formularza służący do edycji pytania
	 */
	@RequestMapping(value="/Question/edit/{id}",method=RequestMethod.GET)
	public String editQuestion(@PathVariable("id") int id, Model model){
		
		model.addAttribute("editquestion",questionService.getQuestionById(id));
		return "editQuestion";
		
	}
	/**
	 * @param question pytanie, które zostanie edytowane
	 * @param result rezultat łączenia danych z modelem
	 * @return widok formularza służący do edycji pytania lub strona główna w przypadku pomyślnej operacji
	 */
	@RequestMapping(value="/Question/edit", method=RequestMethod.POST)
	public String editQuestion(@ModelAttribute("editquestion") Question question, BindingResult result){
		
		questionValidator.validate(question,result);
		
		if(result.hasErrors()){
			return "editQuestion";
		}
		question.setUser(questionService.getQuestionById(question.getId()).getUser());
		questionService.addQuestion(question);
		
		return "redirect:/Question/"+question.getId();
	}
	/**
	 * @param id identyfikator pytania
	 * @param result rezultat łączenia danych z modelem
	 * @return przekierowanie do strony głównej
	 */
	@RequestMapping(value="/Question/remove/{id}", method=RequestMethod.POST)
	public String removeQuestion(@PathVariable("id") int id, BindingResult result){
		
		questionService.removeQuestion(id);
		
		return "redirect:/";
	}
}
