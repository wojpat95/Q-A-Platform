package QAPlatform.service;

import QAPlatform.model.ObservedQuestion;
import QAPlatform.model.Question;
import QAPlatform.model.User;
import QAPlatform.repository.ObservedQuestionRepository;
import QAPlatform.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Klasa testująca serwis pytań
 */
public class ObservedQuestionServiceTest {

	 private ObservedQuestionServiceImpl observedQuestionServiceMock;
	 private ObservedQuestionRepository observedQuestionRepositoryMock;

	    @Before
	    public void setUp() {
	        observedQuestionRepositoryMock = mock(ObservedQuestionRepository.class);
			observedQuestionServiceMock = new ObservedQuestionServiceImpl(observedQuestionRepositoryMock);
	    }

	/**
	 * Testowanie czy metoda getAllObservedQuestions(:user) z serwisu zwraca poprawne pytania
	 */
	@Test
	    public void getAllObservedQuestionsTest(){
	    	User user = mock(User.class);
	    	List<ObservedQuestion> list = new ArrayList<ObservedQuestion>();
	    	list.add(mock(ObservedQuestion.class));
	    	list.add(mock(ObservedQuestion.class));
	    	
	        when(observedQuestionRepositoryMock.findByUser(user)).thenReturn(list);

	        assertEquals(list, observedQuestionServiceMock.getAllObservedQuestions(user));
	    }

	/**
	 * Testowanie czy metoda getObservedQuestion(:user,:question) z serwisu zwraca poprawne pytanie
	 */
	@Test
	public void getObservedQuestionTest(){
		User user = mock(User.class);
		Question question = mock(Question.class);
		ObservedQuestion observedQuestion = mock(ObservedQuestion.class);

		when(observedQuestionRepositoryMock.findByUserAndQuestion(user, question)).thenReturn(observedQuestion);

		assertEquals(observedQuestion, observedQuestionServiceMock.getObservedQuestion(user, question));
	}

}
