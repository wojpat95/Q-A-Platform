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
 * @author Bartosz Gierczak
 *
 */
@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	/**
	 * Konstruktor na potrzeby testowania
	 * @param qr Mock Repository
	 */
	public QuestionService(QuestionRepository qr){
		questionRepository = qr;
	}
	/**
	 * Zwraca listę wszystkich pytań
	 * @return Lista wszystkich pytań
	 */
	public List<Question> getAllQuestions(){
		List<Question> questions = new ArrayList<>();
		questionRepository.findAll().forEach(questions::add);
		return questions;
	}
	/**
	 * Znajdź pytania po identyfikatorze
	 * @param id identyfikator
	 * @return Pytania o danym id
	 */
	public Question getQuestionById(long id){
		return questionRepository.findOne(id);
	}
	/**
	 * Znajdź pytania po części tematu
	 * @param topic szukana fraza
	 * @return Pytania zawierające frazę
	 */
	public List<Question> searchQuestionByTopic(String topic){
		return questionRepository.findByTopicIgnoreCaseContaining(topic);
	}
	/**
	 * Dodaj pytanie lub uaktualnij
	 * @param question Pytanie
	 */
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	/**
	 * Usuń pytanie po id
	 * @param id identyfikator
	 */
	public void removeQuestion(int id){
		questionRepository.delete((long)id);
	}
}
