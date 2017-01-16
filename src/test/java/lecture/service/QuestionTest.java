package lecture.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import lecture.domain.Answer;
import lecture.domain.Question;
import lecture.domain.User;

public class QuestionTest {

	Question question;
	User user;
	
	@Before
	public void setup(){
		user = new User(1L, "userId", "password", "name", "email");
		question = new Question(user, "title", "contents");
	}
	
	@Test
	public void delete_글쓴이일치_댓글없음() {
		assertEquals(question.isDeleteOk(user), true);
	}
	
	@Test
	public void delete_글쓴이일치_글쓴이댓글(){
		Answer answer = new Answer(user, "contents", question);
		question.junitTestSetAnswer(answer);
		assertEquals(question.isDeleteOk(user), true);
	}
	
	@Test
	public void delete_글쓴이일치_다른사람댓글(){
		User otherUser = new User(2L, "userId", "password", "name", "email");
		Answer answer = new Answer(otherUser, "contents", question);
		question.junitTestSetAnswer(answer);
		assertEquals(question.isDeleteOk(user), false);
	}
	
	@Test
	public void delete_글쓴이불일치(){
		User otherUser = new User(2L, "userId", "password", "name", "email");
		assertEquals(question.isDeleteOk(otherUser), false);
	}
}
