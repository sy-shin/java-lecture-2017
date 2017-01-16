package lecture.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lecture.domain.Question;
import lecture.domain.User;
import lecture.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/form")
	public String form(){
		return "/qna/form";
	}
	
	@PostMapping("")
	public String input(String title, String contents, HttpSession session){
		User loginUser = null;
		if (session.getAttribute("loginUser") != null)
			loginUser = (User)session.getAttribute("loginUser");
		
		Question question = new Question(loginUser, title, contents);
		questionService.input(question);
		return "redirect:/questions";
	}
	
	@GetMapping("")
	public String list(){
		return "redirect:/";
	}
	
	@GetMapping("{id}/show")
	public String show(@PathVariable Long id, Model model, HttpSession session){
		User loginUser = null;
		if (session.getAttribute("loginUser") != null)
			loginUser = (User)session.getAttribute("loginUser");
		
		Question question = questionService.findOne(id);
		
		model.addAttribute("question", question);
		model.addAttribute("isDeleteOk",questionService.isDeleteOk(loginUser, question));
		model.addAttribute("countAnswer", questionService.getAnswerCount(question));
		
		questionService.setAnswerWriterFlag(question, loginUser);
		
		return "/qna/show";
	}
	
	@GetMapping("{id}/delete")
	public String delete(@PathVariable Long id, HttpSession session){
		User loginUser = null;
		if (session.getAttribute("loginUser") != null)
			loginUser = (User)session.getAttribute("loginUser");
		
		questionService.delete(id, loginUser);
		return "redirect:/";
	}
}