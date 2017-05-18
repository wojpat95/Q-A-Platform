package QAPlatform.web;

import java.util.ArrayList;
import java.util.List;

import QAPlatform.model.Question;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.service.QuestionService;
import QAPlatform.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.*;

/**
 * Klasa testująca kontroler widoku Home
 */
public class HomeControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private QuestionService questionServiceMock;

    @Autowired
    private UserServiceImpl userServiceMock;

    @Autowired
    private QuestionRepository questionRepositoryMock;


    @Autowired
    private HomeController homeController;

    @Before
    public void setUp() {
        questionRepositoryMock = mock(QuestionRepository.class);
        questionServiceMock = mock(QuestionService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController(questionServiceMock)).build();

        List<Question> list = new ArrayList<Question>();
        list.add(mock(Question.class));
        list.add(mock(Question.class));

        when(questionRepositoryMock.findAll()).thenReturn(list);
        when(questionServiceMock.getAllQuestions()).thenReturn(list);

    }

    /**
     * Zapewnia ze kod statusu HTTP jest 200
     */
    @Test
    public void homeTestStatus() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

    }

    /**
     * Zapewnia ze nazwa widoku to "home"
     */
    @Test
    public void homeTestViewName() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }

    /**
     * Zapewnia ze zapytanie jest przekierowane do "/home"
     */

    @Test
    public void homeTestForwardedUrl() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(forwardedUrl("home"));
    }

    /**
     * Sprawdza czy metoda getAllQuestions() wykonana jest tylko raz i zapewnia  ze inne metody nie zostały wykonane
     */
    @Test
    public void homeTestSpecificMethodCalled() throws Exception {

        mockMvc.perform(get("/"));

        verify(questionServiceMock, times(1)).getAllQuestions();
        verifyNoMoreInteractions(questionServiceMock);

    }
    


}