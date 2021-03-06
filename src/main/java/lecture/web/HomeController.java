package lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lecture.service.QuestionService;

@Controller
public class HomeController {
	
	@Autowired
	private QuestionService questionService;
	
	//RequestMapping에 method방식을 적어두지 않으면 모든 방식을 다 사용을 해
	//Post랑 Get만 있는 것이 아님
	//따라서 4.3버전 이상부터는 아래와 같음(확실히 맵핑)
	//ex) PutMapping, DeleteMapping, PostMapping
	@GetMapping("/")
	   public String qnaList(Model model){
	      model.addAttribute("questions", questionService.getQuestionList());
	      return "index";
	   }
}