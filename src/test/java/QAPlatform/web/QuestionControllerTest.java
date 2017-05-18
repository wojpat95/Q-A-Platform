package QAPlatform.web;

import QAPlatform.model.Question;
import QAPlatform.model.User;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.service.QuestionService;
import QAPlatform.service.UserServiceImpl;
import QAPlatform.validator.QuestionValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class QuestionControllerTest {

    private class TestUtil {

        public String createStringWithLength(int length) {
            StringBuilder builder = new StringBuilder();

            for (int index = 0; index < length; index++) {
                builder.append("a");
            }

            return builder.toString();
        }
    }
    private TestUtil testUtil;
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

    @Autowired
    private QuestionValidator questionValidator;



    @Before
    public void setUp() {
        questionRepositoryMock = mock(QuestionRepository.class);
        questionServiceMock = mock(QuestionService.class);
        questionValidator = mock(QuestionValidator.class);
        userServiceMock = mock(UserServiceImpl.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionController(questionServiceMock,userServiceMock,questionValidator)).build();


    }

    @Test
    public void newQuestionTest() throws Exception {

        testUtil = new TestUtil();
        User user = new User();
        user.setId(1L);
        user.setUsername("mockUsername");
        String topic = testUtil.createStringWithLength(201);
        String body = testUtil.createStringWithLength(1001);
        when(userServiceMock.findByUsername(anyString())).thenReturn(user);
        mockMvc.perform(post("/newQuestion")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("topic", topic)
                .param("body", body)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(forwardedUrl(null))
                .andExpect(model().attributeHasFieldErrors("newquestion", "topic"))
                .andExpect(model().attributeHasFieldErrors("newquestion", "body"))
                .andExpect(model().attribute("newquestion", hasProperty("id", nullValue())))
                .andExpect(model().attribute("newquestion", hasProperty("topic", is(topic))))
                .andExpect(model().attribute("newquestion", hasProperty("body", is(body))));

//        verifyZeroInteractions(todoServiceMock);
    }
}