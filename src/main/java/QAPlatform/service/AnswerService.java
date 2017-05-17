package QAPlatform.service;

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
	private AnswerRepository answerRepository;
	
	public AnswerService(AnswerRepository an){
		this.answerRepository = an;
	}
	
	public List<Answer> getAllAnswers(Question q){
		List<Answer> answers = new ArrayList<>();
		answerRepository.findAll().forEach(answers::add);
		for(Answer answer : answers){
			if(!(answer.getQuestion().getId()==q.getId())){
				answers.remove(answers.indexOf(answer));
			}	
		}
		return answers;
	}
	
	public List<Answer> getAllAnswersByQuestionId(long id){
		return answerRepository.findById(id);
	}
	public void addAnswer(Answer an){
		answerRepository.save(an);
	}
	
	public Answer getAnswerById(int id){
		return answerRepository.findOne((long)id);
	}
	public void removeAnswer(int id){
		answerRepository.delete((long)id);
	}
}