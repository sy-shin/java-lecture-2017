package lecture.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lecture.domain.User;
import lecture.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}
	
	@PostMapping("")
	public String input(User user){
		//log.debug("user : "+user);	//문자먼저 들어가고 디버그가 들어가는 순서얌
		log.debug("user : {}", user);//얘로 적으면 debu가 찍히는 상태면은 그 때서야 문자로 들어가는겨, {}에 리플레이스 됨. "{}/{}",user2,user1	이렇
		userService.input(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}
	
	@GetMapping("{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model){
		model.addAttribute("user", userService.findOne(id));
		return "/user/updateForm";
	}
	
	@PostMapping("{id}/update")
	public String update(@PathVariable Long id, User user){
		userService.update(id, user);
		return "redirect:/users";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(){
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session){
		User dbUser = userService.findByUserId(userId);
		
		if(userService.checkPassword(dbUser, password))
			session.setAttribute("loginUser", dbUser);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}