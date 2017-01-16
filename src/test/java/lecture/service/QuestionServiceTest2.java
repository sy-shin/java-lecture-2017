package lecture.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lecture.domain.Answer;
import lecture.domain.Question;
import lecture.domain.QuestionRepository;
import lecture.domain.User;

//@SpringApplicationConfiguration(classes = QuestionService.class)
//@ContextConfiguration(locations = "classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@WebIntegrationTest
@ActiveProfiles(profiles = "test")
public class QuestionServiceTest2 {
	
	@Qualifier("questionRepository")
	@Autowired
	private QuestionRepository questionRepository;

	
//	@Autowired private AnswerService answerService;
	@Autowired 
	private QuestionService questionService;
	@Autowired private UserService userService;
	
	@Test
	public void deleteNoAnswer() throws Exception {
		User user = new User(1L, "userId", "password", "name", "email");

		Question question = new Question(1L, user, "title", "contents");
		System.out.println(questionService);
		
		assertEquals(questionService.isDeleteOk(user, question), true);
	}
	
	@Test
	public void deleteOnlyMyAnswer(){
		User user = new User(1L, "userId", "password", "name", "email");
		userService.input(user);
		verify(userRepository).save(user);
		
		when(userRepository.findOne(1L)).thenReturn(user);
		user = userService.findOne(1L);

		Question question = new Question(1L, user, "title", "contents");
		questionService.input(question);
		verify(questionRepository).save(question);
		
		when(questionRepository.findOne(1L)).thenReturn(question);
		question = questionService.findOne(1L);
		
		Answer answer = new Answer(user, "contents", question);
		answerService.save(answer);
		verify(answerRepository).save(answer);
		
		when(answerRepository.findOne(1L)).thenReturn(answer);

		question.junitTestSetAnswer(answer);
		boolean deleteFlag = questionService.isDeleteOk(user, question);

		assertEquals(deleteFlag,true);
	}
	
	@Test
	public void deleteOtherAnswer(){
		User user = new User(1L, "userId", "password", "name", "email");
		userService.input(user);
		verify(userRepository).save(user);
		
		when(userRepository.findOne(1L)).thenReturn(user);
		user = userService.findOne(1L);
		
		User otherUser = new User(2L, "userId2", "password", "name", "email");
		userService.input(otherUser);
		verify(userRepository).save(otherUser);
		
		when(userRepository.findOne(2L)).thenReturn(otherUser);
		otherUser = userService.findOne(2L);
		
		Question question = new Question(1L, user, "title", "contents");
		questionService.input(question);
		verify(questionRepository).save(question);
		
		when(questionRepository.findOne(1L)).thenReturn(question);
		question = questionService.findOne(1L);
		
		Answer answer = new Answer(otherUser, "contents", question);
		answerService.save(answer);
		verify(answerRepository).save(answer);
		
		when(answerRepository.findOne(1L)).thenReturn(answer);
		
		answerService.save(answer);
		
		question.junitTestSetAnswer(answer);
		
		boolean deleteFlag = questionService.isDeleteOk(user, question);

		System.out.println("FLAG"+deleteFlag);
		assertEquals(deleteFlag,false);
	}
}