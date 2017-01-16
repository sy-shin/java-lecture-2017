package lecture.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lecture.domain.Answer;
import lecture.domain.Question;
import lecture.domain.User;
import lecture.service.AnswerService;
import lecture.service.QuestionService;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session, Model model){
		User loginUser = null;
		if (session.getAttribute("loginUser") != null)
			loginUser = (User)session.getAttribute("loginUser");
		
		Question question = questionService.findOne(questionId);
		Answer answer = new Answer(loginUser, contents, question);
		
		answerService.save(answer);
		
		//System.out.println("##Answer##"+answer);
		model.addAttribute("answers",answer);
		
		return String.format("redirect:/questions/%d/show", questionId);
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long questionId, @PathVariable Long id){
		answerService.delete(id);
		return String.format("redirect:/questions/%d/show", questionId);
	}
}