package lecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lecture.domain.User;
import lecture.domain.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public void input(User user){
		userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findOne(Long id){
		return userRepository.findOne(id);
	}
	
	public User findByUserId(String userId){
		return userRepository.findByUserId(userId);
	}
	
	public void update(Long id, User user){
		User originUser = findOne(id);
		originUser.update(user);
		input(originUser);
	}
	
	public boolean checkPassword(User dbUser, String password) {
		return dbUser.checkPassword(password);
	}
}
