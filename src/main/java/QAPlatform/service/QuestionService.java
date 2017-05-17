/**
 * 
 */
package QAPlatform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QAPlatform.repository.QuestionRepository;
import QAPlatform.model.Question;

/**
 * @author root
 *
 */
@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository qr){
		questionRepository = qr;
	}
	public List<Question> getAllQuestions(){
		List<Question> questions = new ArrayList<>();
		questionRepository.findAll().forEach(questions::add);
		return questions;
	}
	
	public Question getQuestionById(int id){
		return questionRepository.findOne((long)id);
	}
	
	public List<Question> searchQuestionByTopic(String topic){
		return questionRepository.findByTopicIgnoreCaseContaining(topic);
	}
	
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	public void removeQuestion(int id){
		questionRepository.delete((long)id);
	}
}
