package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	StringCalculator cal;
	
	@Before
	public void setup(){
		cal = new StringCalculator();
	}

	@Test
	public void add_null() {
		assertThat(cal.add(""),is(0));
	}
	
	@Test
	public void add_1number(){
		assertThat(cal.add("1"), is(1));
	}
	
	@Test
	public void add_comma_2numbers() {
		assertThat(cal.add("1,2"),is(3));
	}
	
	@Test
	public void add_comma_3numbers() {
		assertThat(cal.add("1,2,3"),is(6));
	}
	
	@Test
	public void add_colon_2numbers() {
		assertThat(cal.add("1:2"),is(3));
	}
	
	@Test
	public void add_colon_3numbers() {
		assertThat(cal.add("1:2:3"),is(6));
	}
	
	@Test
	public void add_mix() {
		assertThat(cal.add("1,2:3"),is(6));
	}
	
	@Test
	public void add_custom(){
		assertThat(cal.add("//;\n1;2;3"), is(6));
	}
	
	@Test(expected = RuntimeException.class)
	public void add_minus(){
		cal.add("-1");
	}

}
