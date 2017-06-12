package QAPlatform.service;

import QAPlatform.model.Role;
import QAPlatform.model.User;
import QAPlatform.repository.RoleRepository;
import QAPlatform.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Klasa testująca serwis szczegółów pytań
 */

public class UserDetailsServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Before
	    public void setUp() {
		userRepository = mock(UserRepository.class);
		roleRepository = mock(RoleRepository.class);
		bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
		userDetailsService = new UserDetailsServiceImpl(userRepository);
	    }
	/**
	 * Testowanie czy metoda loadUserByUsername(:username) z serwisu zwraca poprawne uzytkownika
	 */
	    @Test
	    public void loadUserByUsernameTest(){
//			User user = mock(User.class);
			User user = new User();
			user.setId(1L);
			user.setUsername("mockUsername");
			user.setPassword("mockRandom");
			Set<Role> roles = new HashSet<Role>();
			Role roleFirst = new Role();
			roleFirst.setId(2L);
			roleFirst.setName("mockName");
			Set<User> users = new HashSet<User>();
			users.add(user);
			roleFirst.setUsers(users);
			roles.add(roleFirst);
			user.setRoles(roles);

			when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			for (Role role : user.getRoles()){
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
			}

			assertEquals(new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities), userDetailsService.loadUserByUsername(user.getUsername()));
	    }

}
