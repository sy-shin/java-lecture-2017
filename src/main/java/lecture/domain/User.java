package lecture.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="uid", length=20, nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String email;
	
	public User() {
		super();
	}
	public User(long id, String userId, String password, String name, String email) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public void update(User user) {
		if(!checkPassword(user.password))
			throw new IllegalStateException("비밀번호가 다르다.");
		
		this.name = user.name;
		this.email = user.email;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	boolean isWriter(User loginUser){
		System.out.println("User-isWirter");
		System.out.println("loginUser.id"+loginUser.id);
		System.out.println("this.id"+this.id);
		return loginUser.id == this.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}
}