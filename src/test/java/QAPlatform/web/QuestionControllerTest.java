package QAPlatform.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
 
import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import java.util.ArrayList;
import java.util.List;

import QAPlatform.model.Question;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.service.QuestionService;
import QAPlatform.service.UserService;
import QAPlatform.validator.QuestionValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class QuestionControllerTest {
	
	@Autowired
	QuestionController questionController;
	
	@Test
    public void getNewQuestion() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.questionController).build();
        mockMvc.perform(get("/Question/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("newQuestion"))
                .andExpect(forwardedUrl("/WEB-INF/newQuestion.jsp"))
                .andExpect(model().attribute("newquestion", hasSize(1)))
                .andExpect(model().attribute("newquestion", hasItem(
                        allOf(
                                hasProperty("topic", is("")),
                                hasProperty("body", is(""))
                        )
                )));
    }

}

