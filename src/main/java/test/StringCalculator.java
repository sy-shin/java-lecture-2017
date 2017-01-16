package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	int add(String input) {
		if (numberEmpty(input))
			return 0;

		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);

		if (m.find()) {
			return customCal(m);
		}
		return normalCal(input);
	}
	
	private int normalCal(String input) {
		String[] numbers;
		numbers = input.split(",|:");
		int result = 0;
		result = sum(result, numbers);
		return result;
	}

	private int customCal(Matcher m) {
		String[] numbers;
		/*
		 * group(0)은 문자열 전체
		 * group(1)은 첫 번째 (.)
		 * m.find는 찾았는지 여부에 따라 boolean
		 * */
		String custom = m.group(1);
		numbers = m.group(2).split(custom);
		int result = 0;
		result = sum(result, numbers);
		return result;
	}

	private void numberCheck(String number) {
		if (Integer.parseInt(number) < 0) {
			throw new RuntimeException();
		}
	}

	private boolean numberEmpty(String input) {
		return input == null || input.isEmpty();
	}

	private int sum(int result, String[] numbers) {
		for (String i : numbers) {
			result += Integer.parseInt(i);
			numberCheck(i);
		}
		return result;
	}

}
