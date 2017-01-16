package lecture.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Long>{
	//<어떤 클래스에 대한 repository 인지, 객체 타입>
	User findByUserId(String userId);
}
