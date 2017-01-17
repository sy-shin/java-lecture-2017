package lecture;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class MyWebIntializer extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Lecture01Application.class);
	}
}
/* 저 파일의 메인메소드는 임베디드 컴파일에서만 필요하당.*/
//@@SpringBootApplication으로 초기화를 하는..?