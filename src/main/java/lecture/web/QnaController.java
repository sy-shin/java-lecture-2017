package lecture.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lecture.domain.Question;
import lecture.domain.QuestionRepository;

@Controller
@RequestMapping("/questions")
public class QnaController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(){
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(Question question){
		
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String str = dayTime.format(new Date(time));
		question.setTime(str);
		System.out.println("Question : "+question);
		
		questionRepository.save(question);
		return "redirect:/questions";
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("questions",questionRepository.findAll());
		return "redirect:/";
	}
	
	@GetMapping("{id}/show")
	public String show(@PathVariable Long id, Model model){
		Question question = questionRepository.findOne(id);
		model.addAttribute("question", question);
		return "/qna/show";
	}
}