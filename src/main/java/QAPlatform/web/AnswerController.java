package QAPlatform.web;

import QAPlatform.model.Answer;
import QAPlatform.model.Question;
import QAPlatform.service.*;
import QAPlatform.validator.AnswerValidator;

import java.util.List;

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
	private QuestionService questionService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private AnswerValidator answerValidator;
	
	/*
	 * wyświetlenie szczegółów pytania i odpowiedzi do niego
	 */
	@RequestMapping(value="/Question/{id}", method=RequestMethod.GET)
	public String showAnswersToQuestion(@PathVariable("id") int id, Model model){
		
		Question question = questionService.getQuestionById(id);
		
		List<Answer> answers = null;
		answers = answerService.getAllAnswersByQuestionId(question);
		
		
		System.out.println("Liczba odpowiedzi: "+answers.size());
		model.addAttribute("allAnswers", answers);
		model.addAttribute("question", question);
		model.addAttribute("newanswer", new Answer());
		return "questionAnswers";
		
	}

	/**
	 * Formularz do udzielenia odpowiedzi
	 * @param answer 
	 * 				model odpowiedzi
	 * @param result 
	 * 				rezultat łączenie danych z modelem
	 * @return 
	 * 				widok formularza do udzielenia odpowiedzi na zadane pytanie
	 */
	@RequestMapping(value="/Answer/new", method=RequestMethod.POST)
	public String newAnswer(@ModelAttribute("newanswer") Answer answer, BindingResult result){
		System.out.println("Dodaje odpowiedz");

		answerValidator.validate(answer,result);
		
		if(result.hasErrors()){
			return "newAnswer";
		}

		answer.setUser(userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		answerService.addAnswer(answer);
		
		return "redirect:/Question/"+answer.getQuestion().getId();
	}
	/**
	 * 
	 * @param id 
	 * 				identyfikator do edycji
	 * @param model 
	 * 				model zawierający pytanie, które zostaje edytowane
	 * @return 
	 * 				widok z formułarzem do edycji pytania
	 */
	@RequestMapping(value="/Answer/edit/{id}",method=RequestMethod.GET)
	public String editAnswer(@PathVariable("id") int id,Model model){
		
		model.addAttribute("editAnswer",answerService.getAnswerById(id));
		return "editAnswer";
		
	}
	/**
	 * @param answer 
	 * 				odpowiedz, która zostanie edytowana
	 * @param result 
	 * 				rezultat łączenia danych z modelem
	 * @return 
	 * 				widok formularza służący do edytowania odpowiedzi
	 */
	@RequestMapping(value="/Answer/edit",method=RequestMethod.POST)
	public String editAnswer(@ModelAttribute("editAnswer") Answer answer, BindingResult result){
		
		answerValidator.validate(answer,result);
		
		if(result.hasErrors()){
			return "editAnswer";
		}
		answer.setUser(answerService.getAnswerById(answer.getId()).getUser());
		answer.setQuestion(answerService.getAnswerById(answer.getId()).getQuestion());
		answerService.addAnswer(answer);
		
		return "redirect:/Question/"+answer.getQuestion().getId();
	}
	
	/**
	 * @param id 
	 * 				identyfikator odpowiedzi
	 * @param result 
	 * 				rezultat łączenia danych z modelem
	 * @return 
	 * 				przekierowanie do strony głównej
	 */
	@RequestMapping(value="/Answer/remove/{id}", method=RequestMethod.POST)
	public String removeQuestion(@PathVariable("id") int id, BindingResult result){
		
		answerService.removeAnswer(id);
		
		return "redirect:/";
	}
}
