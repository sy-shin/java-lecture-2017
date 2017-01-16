package lecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lecture.domain.Question;
import lecture.domain.QuestionDelete;
import lecture.domain.QuestionDeleteRepository;
import lecture.domain.QuestionRepository;
import lecture.domain.User;
@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	QuestionDeleteRepository questionDeleteRepository;
	
	public void input(Question question){
		questionRepository.save(question);
	}
	
	public List<Question> getQuestionList(){
		List<Question> questionList = questionRepository.findAll();
		return new Question().getQuestionList(questionList);
	}
	
	public Question findOne(Long id){
		return questionRepository.findOne(id);
	}
	
	private void inputQuestionDelete(User deleter, Question question){
		QuestionDelete questionDelete = new QuestionDelete(deleter, question);
		questionDeleteRepository.save(questionDelete);
	}
	
	private void setDeleteFlag(Question question){
		question.setDeleteFlag();
	}
	
	public void delete(Long id, User loginUser){
		Question question = findOne(id);
		inputQuestionDelete(loginUser, question);
		setDeleteFlag(question);
		input(question);
	}
	
	public int getAnswerCount(Question question){
		return question.getAnswerCount();
	}
	
	public void setAnswerWriterFlag(Question question, User loginUser){
		question.setAnswerWriterFlag(loginUser);
	}
	
	public boolean isDeleteOk(User loginUser,Question question){
		return question.isDeleteOk(loginUser);
	}
}