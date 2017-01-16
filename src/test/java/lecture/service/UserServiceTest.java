package lecture.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnit44Runner;

import lecture.domain.User;
import lecture.domain.UserRepository;

@RunWith(MockitoJUnit44Runner.class)
public class UserServiceTest {
	@Mock private UserRepository userRepository;
	
	@InjectMocks private UserService userService;
	
	@Test
	public void findOne() {
		User newUser = new User(1L, "userId", "password", "name", "email");
		when(userRepository.findOne(1L)).thenReturn(newUser);
		//userRepository.findOne을 호출할 때 new
		User user = userService.findOne(1L);
		assertEquals(newUser, user);
	}
	
	@Test
	public void create() throws Exception {
		User user = new User(1L, "userId", "password", "name", "email");
		userService.input(user);
		verify(userRepository).save(user);
	}
}