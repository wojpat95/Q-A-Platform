package QAPlatform.web;

import java.util.ArrayList;
import java.util.List;

import QAPlatform.model.Question;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.service.ObservedQuestionService;
import QAPlatform.service.QuestionService;
import QAPlatform.service.UserService;
import QAPlatform.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private UserService userService;

    @Autowired
    private ObservedQuestionService observedQuestionService;

    @Autowired
    private HomeController homeController;

    @Before
    public void setUp() {
        questionRepositoryMock = mock(QuestionRepository.class);
        questionServiceMock = mock(QuestionService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController(questionServiceMock, observedQuestionService, userService)).build();

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

    /**
     * Sprawdza czy metoda searchQuestionByTopic(:topic) wykonana jest tylko raz i zapewnia  ze inne metody nie zostały
     * wykonane oraz ze zapytanie przekierowane jest do "/home"
     */
    @Test
    public void searchTest() throws Exception {
        List<Question> list = new ArrayList<Question>();
        list.add(mock(Question.class));
        list.add(mock(Question.class));
        Model model = mock(Model.class);

        when(questionServiceMock.searchQuestionByTopic(anyString())).thenReturn(list);
        HomeController homeController = new HomeController(questionServiceMock, observedQuestionService, userService);
        assertEquals(homeController.home(anyString(),model),"home");

        verify(questionServiceMock, times(1)).searchQuestionByTopic(anyString());
        verifyNoMoreInteractions(questionServiceMock);

    }

    /**
     * Zapewnia ze kod statusu HTTP jest 302
     */
    @Test
    public void observeTestStatus() throws Exception {
        mockMvc.perform(get("/observe"))
                .andExpect(status().is(302));

    }
    /**
     * Zapewnia ze zapytanie jest przekierowane do "/"
     */

    @Test
    public void observeTestForwardedUrl() throws Exception {

        mockMvc.perform(get("/observe"))
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Zapewnia ze kod statusu HTTP jest 302
     */

    @Test
    public void stopObserveTestStatus() throws Exception {
        mockMvc.perform(get("/stopObserve"))
                .andExpect(status().is(302));

    }
    /**
     * Zapewnia ze zapytanie jest przekierowane do "/"
     */

    @Test
    public void stopObserveTestForwardedUrl() throws Exception {

        mockMvc.perform(get("/stopObserve"))
                .andExpect(redirectedUrl("/"));
    }

//    @Test
//    public void stopObserveTestSession() throws Exception {
//
//        HttpServletRequest requestMock = mock(HttpServletRequest.class);
//        HttpSession httpSession = mock(HttpSession.class);
//        when(requestMock.getSession()).thenReturn(httpSession);
//
//        mockMvc.perform(get("/stopObserve"));
//
//        verify(httpSession, times(1)).setAttribute("observed",false);
////        httpSession.setAttribute("observed",false);
////        assertEquals(httpSession.getAttribute("observed"), false);
//    }





}