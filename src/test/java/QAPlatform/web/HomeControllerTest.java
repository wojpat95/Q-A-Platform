package QAPlatform.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import static org.hamcrest.Matchers.*;

public class HomeControllerTest {
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext webApplicationContext;
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


    }

    @Test
    public void homeTest() throws Exception {

        List<Question> list = new ArrayList<Question>();
        list.add(mock(Question.class));
        list.add(mock(Question.class));

        when(questionRepositoryMock.findAll()).thenReturn(list);
        when(questionServiceMock.getAllQuestions()).thenReturn(list);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(forwardedUrl("home"))
                .andExpect(model().attribute("AllQuestions", hasSize(2)));


        verify(questionServiceMock, times(1)).getAllQuestions();
        verifyNoMoreInteractions(questionServiceMock);
    }
}