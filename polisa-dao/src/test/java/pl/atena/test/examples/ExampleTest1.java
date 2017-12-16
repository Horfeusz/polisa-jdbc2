package pl.atena.test.examples;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Podstawowe testy
 *
 */
@SuppressWarnings({ "rawtypes", "unused" })
public class ExampleTest1 {

	@Test(expected = ArithmeticException.class)
	public void testDivisionWithException() {
		int i = 1 / 0;
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testEmptyList() {
		(new ArrayList()).get(0);
	}
}
