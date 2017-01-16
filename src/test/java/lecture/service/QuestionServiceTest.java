package lecture.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import lecture.domain.Answer;
import lecture.domain.AnswerRepository;
import lecture.domain.Question;
import lecture.domain.QuestionRepository;
import lecture.domain.User;
import lecture.domain.UserRepository;

@RunWith(MockitoJUnitRunner.class)

public class QuestionServiceTest {
	@Mock private QuestionRepository questionRepository;
	@Mock private UserRepository userRepository;
	@Mock private AnswerRepository answerRepository;
	@InjectMocks private AnswerService answerService;
	@InjectMocks private QuestionService questionService;
	@InjectMocks private UserService userService;
	
	@Test
	public void findOne() {
		User user = new User(1L, "userId", "password", "name", "email");
		Question newQuestion = new Question(1L, user, "title", "contents");
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionService.findOne(1L);
		assertEquals(question, newQuestion);
	}
	
	@Test
	public void save(){
		User user = new User(1L, "userId", "password", "name", "email");
		Question question = new Question(1L, user, "title", "contents");
		
		questionService.input(question);
		verify(questionRepository).save(question);
	}
	
	@Test
	public void deleteNoAnswer() throws Exception {
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

		boolean deleteFlag = questionService.isDeleteOk(user, question);
		
		assertEquals(deleteFlag,true);
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

		assertEquals(deleteFlag,false);
	}
}
