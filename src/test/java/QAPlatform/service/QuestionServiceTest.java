package QAPlatform.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import QAPlatform.model.Question;
import QAPlatform.repository.QuestionRepository;

public class QuestionServiceTest {

	 private QuestionService qs;
	 private QuestionRepository qrMock;

	    @Before
	    public void setUp() {
	        qrMock = mock(QuestionRepository.class);
	        qs = new QuestionService(qrMock);
	    }

	    @Test
	    public void returnAllQuestionsTest(){
	    	
	    	List<Question> list = new ArrayList<Question>();
	    	list.add(mock(Question.class));
	    	list.add(mock(Question.class));
	    	
	        when(qrMock.findAll()).thenReturn(list);

	        assertEquals(list, qs.getAllQuestions());
	    }
	    @Test
	    public void getQuestionByIdTest(){
	    	
	    	Question mockQ = mock(Question.class);
	    	
	        when(qrMock.findOne((long)1)).thenReturn(mockQ);

	        assertEquals(mockQ, qs.getQuestionById(1));
	    }
	    @Test
	    public void searchQuestionByTopicTest(){
	    	
	    	List<Question> list = new ArrayList<Question>();
	    	list.add(mock(Question.class));
	    	list.add(mock(Question.class));
	    	
	        when(qrMock.findByTopicIgnoreCaseContaining("Topic")).thenReturn(list);

	        assertEquals(list, qs.searchQuestionByTopic("Topic"));
	    }
}
