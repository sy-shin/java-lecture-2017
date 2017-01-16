package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	
	Calculator cal;
	
	@Before
	public void setup(){
		cal = new Calculator();
		System.out.println("setup");
	}
	

	@Test
	public void add() {
		//Calculator cal = new Calculator();
		//int result = ;
		
		
		//Static import는 밑에꺼처럼 Assert.xxx이렇게 불르는 것이 아니라 저 스태틱 메소드만 가져오는 거
		assertEquals(6,cal.add(5,1));
		assertThat(cal.add(5, 1), is(7));//인자값 순서가 헷깔리니까(위에꺼) 요렇게 영어권 아이들이 사용함
	}
	
	@Test
	public void minus() throws Exception {
		//test 자동완성 두번 누르고 junit4 (3는 너무 구버전)
		//test 먼저 만들고 빨간불보고 초록불로 바꾸는 것이 TDD방법이얌
		
		//Calculater를 따로 필드로 빼는 것을 권유하지 않음->junit에서
		//초기화-실행-후처리 이런 메커니즘
		//이런 전처리를 하기 위하여 @before사용
		//test method는 무작위로 실행됑 애노테이션 달은 애들 말고 ㅎㅎ
		
		//메소드마다 before와 after 실행
		
		//커서를 메소드 위에 위치시킨다음에 실행하면 걔만 켜졍
		
		
		/*
		 * prepareTest template 추가하여서 static import자동으로 하게 끔 추가하였음
		 * */
		Calculator cal = new Calculator();
		int result = cal.minus(5,1);
		assertEquals(4,result);
	}
	
	@After
	public void teardown(){
		
	}
}
