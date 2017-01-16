package lecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lecture.domain.Answer;
import lecture.domain.AnswerRepository;

@Service
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	
	public void save(Answer answer){
		answerRepository.save(answer);
	}
	
	public void delete(Long id){
		answerRepository.delete(id);
	}
}
