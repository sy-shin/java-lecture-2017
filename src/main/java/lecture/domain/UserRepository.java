package lecture.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
	//<어떤 클래스에 대한 repository 인지, 객체 타입>
	

}
