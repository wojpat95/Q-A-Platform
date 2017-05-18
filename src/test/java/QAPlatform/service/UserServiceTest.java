package QAPlatform.service;

import QAPlatform.model.Question;
import QAPlatform.model.Role;
import QAPlatform.model.User;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.repository.RoleRepository;
import QAPlatform.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

public class UserServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserServiceImpl userService;

	    @Before
	    public void setUp() {
		userRepository = mock(UserRepository.class);
		roleRepository = mock(RoleRepository.class);
		userService = new UserServiceImpl(userRepository);
	    }

	@Test
	public void findByUsernameTest(){
		User user = mock(User.class);
		when(userRepository.findByUsername("mockUsername")).thenReturn(user);
		assertEquals(user, userService.findByUsername("mockUsername"));
	}
}
