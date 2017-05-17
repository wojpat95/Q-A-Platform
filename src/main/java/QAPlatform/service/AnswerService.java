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
	
	public List<Answer> getAllAnswersByQuestionId(Question q){
		return answerRepository.findByQuestion(q);
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