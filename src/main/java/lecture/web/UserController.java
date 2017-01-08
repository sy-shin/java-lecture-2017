package lecture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lecture.domain.User;
import lecture.domain.UserRepository;

@Controller
@RequestMapping("/users")//대표 url 이러면 밑에 ("")얘네들은 얘를 따름
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}
	
	
	@PostMapping("")
	public String create(User user){
		System.out.println("User : "+user);
		userRepository.save(user);
		return "redirect:/users";//리다이렉트하여서 list로 보냄
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	@GetMapping("{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model){
		model.addAttribute("user", userRepository.findOne(id));
		return "/user/updateForm";
	}
	
	@PostMapping("{id}/update")
	public String update(@PathVariable Long id, User user){
		User originUser = userRepository.findOne(id);
		if(originUser.getPassword().equals(user.getPassword())){
			originUser.update(user);
			userRepository.save(originUser);
		}
		return "redirect:/users";
	}
}