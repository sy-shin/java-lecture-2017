package lecture.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Entity: DB에 이어줄라 
@Entity
public class User {
	
	//pk를 위하여 생성
	//gener	얘는 자동 증
	@Id
	@GeneratedValue
	private long id;
	
	//Column얘는 컬럼설정 다르게 하고플때 애노테이
	@Column(name="uid", length=20, nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	public void update(User user) {
		this.name = user.name;
		this.email = user.email;
	}
}
