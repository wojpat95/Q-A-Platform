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
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
        questionRepositoryMock = mock(QuestionRepository.class);
        questionServiceMock = new QuestionService(questionRepositoryMock);

    }

    @Test
    public void homeTest() throws Exception {
        User firstUser = new User();
        firstUser.setPassword("123456789");
        firstUser.setUsername("mockUser");
        Question first = new Question("Mock topic 1", "Mock body 1", 1,1,firstUser);
        first.setId(1);
        Question second = new Question("Mock topic 2", "Mock body 2", 2,2,firstUser);
        second.setId(2);

//        List<Question> list = new ArrayList<Question>();
//        list.add(mock(Question.class));
//        list.add(mock(Question.class));

       List<Question> list = new ArrayList<Question>();
        list.add(first);
        list.add(second);

        when(questionRepositoryMock.findAll()).thenReturn(list);
        when(questionServiceMock.getAllQuestions()).thenReturn(list);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());

        verify(questionServiceMock, times(1)).getAllQuestions();
        verifyNoMoreInteractions(questionServiceMock);
    }
}