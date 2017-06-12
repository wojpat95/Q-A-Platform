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


/**
 * Klasa testująca kontroler widoku Question
 */
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
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionController(questionServiceMock,userServiceMock,questionValidator)).build();
    }

    /**
     * Zapewnia ze kod statusu HTTP jest 200
     */
    @Test
    public void newQuestionTestStatus() throws Exception {

        mockMvc.perform(get("/Question/new"))
                .andExpect(status().isOk());

    }

    /**
     * Zapewnia ze nazwa widoku to "newQuestion"
     */
    @Test
    public void newQuestionTestViewName() throws Exception {

        mockMvc.perform(get("/Question/new"))
                .andExpect(view().name("newQuestion"));

    }

    /**
     * Zapewnia ze atrybut "newQuestion" jest klasą Question
     */
    @Test
    public void newQuestionTestAttribute() throws Exception {

        mockMvc.perform(get("/Question/new"))
                .andExpect(model().attribute("newquestion", instanceOf(Question.class)));

    }



    /**
     * Zapewnia ze atrybut "editquestion" jest klasą Question
     */
    @Test
    public void editQuestionTestAttribute() throws Exception {
    	
    	Question q = mock(Question.class);
    	when(questionServiceMock.getQuestionById(3)).thenReturn(q);
    	
        mockMvc.perform(get("/Question/edit/3"))
                .andExpect(model().attribute("editquestion", instanceOf(Question.class)));

    }

    /**
     * Zapewnia ze kod statusu HTTP jest 200
     */
    @Test
    public void editQuestionTestStatus() throws Exception {

        Question q = mock(Question.class);
        when(questionServiceMock.getQuestionById(3)).thenReturn(q);

        mockMvc.perform(get("/Question/edit/3"))
                .andExpect(status().isOk());

    }

    /**
     * Zapewnia ze nazwa widoku to "editQuestion"
     */
    @Test
    public void editQuestionTestViewName() throws Exception {

        Question q = mock(Question.class);
        when(questionServiceMock.getQuestionById(3)).thenReturn(q);

        mockMvc.perform(get("/Question/edit/3"))
                .andExpect(view().name("editQuestion"));

    }
    /**
     * Zapewnia ze nazwa widoku to "newQuestion"
     */
    @Test
    public void newQuestionPOSTTestfalse() throws Exception {

    	Question q = mock(Question.class);
    	doNothing().when(q).setUser(mock(User.class));
    	BindingResult result = mock(BindingResult.class);
    	doNothing().when(questionValidator).validate(q,result);
    	doNothing().when(questionServiceMock).addQuestion(q);

    	when(result.hasErrors()).thenReturn(true);
    	
    	QuestionController qc = new QuestionController(questionServiceMock,userServiceMock, questionValidator);
    	assertEquals(qc.newQuestion(q, result),"newQuestion");
    	
        verify(questionValidator, times(1)).validate(q,result);
        verify(result, times(1)).hasErrors();
    }

    /**
     * Zapewnia ze jeżeli wystąpi błąd podczas edytowania pytania to przekieruje nas do "/editQuestion", walidacja wykona się tylko raz
     */

    @Test
    public void editQuestionPOSTTestfalse() throws Exception {
    	
    	Question q = mock(Question.class);
    	when(q.getId()).thenReturn(3L);
    	BindingResult result = mock(BindingResult.class);
    	doNothing().when(questionValidator).validate(q,result);
    	doNothing().when(questionServiceMock).addQuestion(q);
    	when(result.hasErrors()).thenReturn(true);
    	
    	QuestionController qc = new QuestionController(questionServiceMock, userServiceMock, questionValidator);
    	assertEquals(qc.editQuestion(q, result),"editQuestion");
    	
        verify(questionValidator, times(1)).validate(q,result);
        verify(result, times(1)).hasErrors();
    }
}