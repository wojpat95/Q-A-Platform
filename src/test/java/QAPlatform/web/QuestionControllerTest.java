package QAPlatform.web;

import static org.hamcrest.Matchers.*;
import QAPlatform.model.Question;
import QAPlatform.model.User;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.service.QuestionService;
import QAPlatform.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;



public class QuestionControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private QuestionService questionServiceMock;

    @Autowired
    private UserServiceImpl userServiceMock;
    
    @Autowired
    private QuestionController questionController;
    @Before
    public void setUp() {
        questionServiceMock = mock(QuestionService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionController(questionServiceMock)).build();
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
    
}