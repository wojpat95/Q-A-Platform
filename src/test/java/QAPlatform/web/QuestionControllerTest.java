package QAPlatform.web;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import QAPlatform.model.Question;
import QAPlatform.model.User;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.service.QuestionService;
import QAPlatform.service.UserServiceImpl;
import QAPlatform.validator.QuestionValidator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



public class QuestionControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private QuestionService questionServiceMock;

    @Autowired
    private UserServiceImpl userServiceMock;
    @Autowired
    private QuestionValidator questionValidator;
    @Autowired
    private QuestionController questionController;
    @Before
    public void setUp() {
        questionServiceMock = mock(QuestionService.class);
        questionValidator = mock(QuestionValidator.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionController(questionServiceMock,questionValidator)).build();
    }

    @Test
    public void newQuestionTest() throws Exception {

        mockMvc.perform(get("/Question/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("newQuestion"))
                .andExpect(model().attribute("newquestion", instanceOf(Question.class)));

    }
    
    @Test
    public void editQuestionTest() throws Exception {
    	
    	Question q = mock(Question.class);
    	when(questionServiceMock.getQuestionById(3)).thenReturn(q);
    	
        mockMvc.perform(get("/Question/edit/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("editQuestion"))
                .andExpect(model().attribute("editquestion", instanceOf(Question.class)));

    }
    
    @Test
    public void newQuestionPOSTTestfalse() throws Exception {
    	
    	Question q = mock(Question.class);
    	doNothing().when(q).setUser(mock(User.class));
    	BindingResult result = mock(BindingResult.class);
    	doNothing().when(questionValidator).validate(q,result);
    	doNothing().when(questionServiceMock).addQuestion(q);
    	when(result.hasErrors()).thenReturn(true);
    	
    	QuestionController qc = new QuestionController(questionServiceMock,questionValidator);
    	assertEquals(qc.newQuestion(q, result),"newQuestion");
    	
        verify(questionValidator, times(1)).validate(q,result);
        verify(result, times(1)).hasErrors();
    }
    @Test
    public void editQuestionPOSTTestOK() throws Exception {
    	
    	Question q = mock(Question.class);
    	when(q.getId()).thenReturn(3L);
    	doNothing().when(q).setUser(mock(User.class));
    	BindingResult result = mock(BindingResult.class);
    	doNothing().when(questionValidator).validate(q,result);
    	doNothing().when(questionServiceMock).addQuestion(q);
    	when(result.hasErrors()).thenReturn(false);
    	
    	QuestionController qc = new QuestionController(questionServiceMock,questionValidator);
    	assertEquals(qc.editQuestion(q, result),"redirect:/Question/3");
    	
    	verify(questionServiceMock, times(1)).addQuestion(q);
        verify(questionValidator, times(1)).validate(q,result);
        verify(result, times(1)).hasErrors();
    }
    @Test
    public void editQuestionPOSTTestfalse() throws Exception {
    	
    	Question q = mock(Question.class);
    	when(q.getId()).thenReturn(3L);
    	BindingResult result = mock(BindingResult.class);
    	doNothing().when(questionValidator).validate(q,result);
    	doNothing().when(questionServiceMock).addQuestion(q);
    	when(result.hasErrors()).thenReturn(true);
    	
    	QuestionController qc = new QuestionController(questionServiceMock,questionValidator);
    	assertEquals(qc.editQuestion(q, result),"editQuestion");
    	
        verify(questionValidator, times(1)).validate(q,result);
        verify(result, times(1)).hasErrors();
    }
}