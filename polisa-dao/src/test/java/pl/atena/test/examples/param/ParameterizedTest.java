package pl.atena.test.examples.param;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Parametryzowanie testów konstruktor
 * 
 * @see <a href=
 *      "http://www.mkyong.com/unittest/junit-4-tutorial-6-parameterized-test/">http://www.mkyong.com/unittest/junit-4-tutorial-6-parameterized-test/</a>
 * 
 * 
 * @author michalh
 *
 */
@RunWith(value = Parameterized.class)
public class ParameterizedTest {

	private int numberA;
	private int numberB;
	private int expected;

	// Inject via constructor
	// for {8, 2, 10}, numberA = 8, numberB = 2, expected = 10
	public ParameterizedTest(int numberA, int numberB, int expected) {
		this.numberA = numberA;
		this.numberB = numberB;
		this.expected = expected;
	}

	// name attribute is optional, provide an unique name for test
	// multiple parameters, uses Collection<Object[]>
	@Parameters(name = "{index}: testAdd({0}+{1}) = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{ 1, 1, 2 }, { 2, 2, 4 }, { 8, 2, 10 }, { 4, 5, 9 }, { 5, 5, 10 } });
	}

	@Test
	public void test_addTwoNumbes() {
		assertThat(MathUtils.add(numberA, numberB), is(expected));
	}

}
