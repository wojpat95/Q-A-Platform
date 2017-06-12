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
	
	public List<Question> searchQuestionByTopic(Question qu){
		List<Question> questions = getAllQuestions();
		for(Question question : questions){
			if(question.getTopic().equals(qu.getTopic())){
				break;
			}
			questions.remove(questions.indexOf(question));
		}
		return questions;
	}
	
	public List<Question> searchQuestionByBody(Question qu){
		List<Question> questions = getAllQuestions();
		for(Question question : questions){
			if(question.getBody().toLowerCase().contains(qu.getBody().toLowerCase())){
				break;
			}
			questions.remove(questions.indexOf(question));
		}
		return questions;
	}
	
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	
<<<<<<< HEAD
	public void removeQuestion(int id){
		questionRepository.delete((long)id);
=======
	public void editQuestion(Question question, String topic, String body){
		//questionRepository.findByid(question.getId());
		question.setTopic(topic);
		question.setBody(body);
		questionRepository.save(question);
		
>>>>>>> 886ae56895b82ef413b271289dba896c4ece89ec
	}
}
