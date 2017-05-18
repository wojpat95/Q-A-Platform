package QAPlatform.service;

import QAPlatform.model.Question;
import QAPlatform.model.Role;
import QAPlatform.model.User;
import QAPlatform.repository.QuestionRepository;
import QAPlatform.repository.RoleRepository;
import QAPlatform.repository.UserRepository;
import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

/**
 * Klasa testująca serwis użytkownika
 */
public class UserServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserServiceImpl userService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Before
	    public void setUp() {
		userRepository = mock(UserRepository.class);
		roleRepository = mock(RoleRepository.class);
		bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
		userService = new UserServiceImpl(userRepository,bCryptPasswordEncoder,roleRepository);
	    }

	/**
	 * Testowanie czy metoda save(:user) z serwisu wykonuje się tylko raz
	 */
	    @Test
	    public void saveTest(){
	    	User user = mock(User.class);
			List<Role> roles = new ArrayList<Role>();
			roles.add(mock(Role.class));
			when(roleRepository.findAll()).thenReturn(roles);
			userService.save(user);
			verify(userRepository,times(1)).findByUsername(user.getUsername());
	    }

	/**
	 * Testowanie czy metoda findByUsername(:username) z serwisu poprawnie zwraca użytkownika
	 */
		@Test
		public void findByUsernameTest(){
			User user = mock(User.class);
			when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
			assertEquals(user, userService.findByUsername(user.getUsername()));
		}
}
