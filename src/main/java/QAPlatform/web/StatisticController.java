package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.model.Answer;
import QAPlatform.model.User;
import QAPlatform.service.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Klasa będąca kontrolerem statystyk.
 * @author Amadeusz Mileszko
 */

@Controller
public class StatisticController {
	private final QuestionService questionService;
    private final AnswerService answerService; 
	private final UserService userService;   

	@Autowired
	public StatisticController(QuestionService questionService, AnswerService answerService, UserService userService) {
		this.questionService = questionService;
        this.answerService = answerService;   
		this.userService = userService;	     
	}

	/**
     * @param model
     *      model statystyk
	 * @return widok statystyk
	 */
	@RequestMapping(value = "/Statistics", method = RequestMethod.GET)
	public String getStatistics( Model model){
		User currentUSer = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		List<Question> questions = new ArrayList<>();
		questions = questionService.getByUserId(currentUSer);

        List<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            answers.addAll(answerService.getAllAnswersByQuestionId(question));
        }

		List<Answer> ownAnswers = new ArrayList<>();
        ownAnswers = answerService.getByUserId(currentUSer);
		
		model.addAttribute("QuestionsCount", questions.size());
        model.addAttribute("AnswersCount", answers.size());
		model.addAttribute("OwnAnswersCount", ownAnswers.size());

		return "statistic";		
	}	
}
