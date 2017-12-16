package pl.atena.test.examples;

//http://www.mkyong.com/tutorials/junit-tutorials/

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Exception3Test {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testDivisionWithException() {

		thrown.expect(ArithmeticException.class);
		thrown.expectMessage(containsString("/ by zero"));

		int i = 1 / 0;

	}

}
