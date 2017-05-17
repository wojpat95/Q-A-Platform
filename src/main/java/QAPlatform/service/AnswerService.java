package QAPlatform.web;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QAPlatform.model.Answer;
import QAPlatform.model.Question;
import QAPlatform.repository.AnswerRepository;

@Service
public class AnswerService{
	@Autowired
	private AnswerRepository answerrepository;
	
	public AnswerService(AnswerRepository an){
		this.answerrepository = an;
	}
	
	public List<Answer> getAllAnswers(Question q){
		List<Answer> answers = new ArrayList<>();
		answerrepository.findAll().forEach(answers::add);
		for(Answer answer : answers){
			if(!(answer.getQuestion().getId()==q.getId())){
				answers.remove(answers.indexOf(answer));
			}	
		}
		return answers;
	}
	
	
	public void addAnswer(Answer an){
		answerrepository.save(an);
	}
	
	
	public void editAnswer(Answer ans, String body){
		ans.setBody(body);
		answerrepository.save(ans);
	}
}