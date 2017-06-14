package QAPlatform.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QAPlatform.model.Answer;
import QAPlatform.model.Question;
import QAPlatform.model.User;
import QAPlatform.repository.AnswerRepository;

/**
 * Service do odpowiedzi
 * @author Bartosz Gierczak
 *
 */
@Service
public class AnswerService{
	@Autowired
	private AnswerRepository answerRepository;
	/**
	 * Konstruktor na potrzeby testowania
	 * @param an Mock Repository
	 */
	public AnswerService(AnswerRepository an){
		this.answerRepository = an;
	}
	
	/**
	 * Zwróć wszystkie odpowiedzi do danego pytania
	 * @param q Pytanie, do którego szukamy odpowiedzi
	 * @return Lista odpowiedzi na dane pytanie
	 */
	public List<Answer> getAllAnswersByQuestionId(Question q){
		return answerRepository.findByQuestion(q);
	}
	/**
	 * Zwraca listę pytań uzytkownika
	 * @param user uzytkownik
	 * @return lista pytan uzytkownika
	 */
	public List<Answer> getByUserId(User user)
	{
		List<Answer> answers = new ArrayList<>();
		answerRepository.findByUser(user).forEach(answers::add);
		return answers;
	}
	/**
	 * Dodaj lub uaktualnij odpowiedź
	 * @param an odpowiedź
	 */
	public void addAnswer(Answer an){
		answerRepository.save(an);
	}
	/**
	 * Znajdź odpowiedź po id
	 * @param id identyfikator odpowiedzi
	 * @return Odpowiedź
	 */
	public Answer getAnswerById(long id){
		return answerRepository.findOne(id);
	}
	/**
	 * Usuwa odpowiedź
	 * @param id identyfikator odpowiedzi
	 */
	public void removeAnswer(int id){
		answerRepository.delete((long)id);
	}
}