package pl.atena.test.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @see <a href=
 *      "http://www.mkyong.com/unittest/junit-4-tutorial-3-ignore-test/">http://www.mkyong.com/unittest/junit-4-tutorial-3-ignore-test/</a>
 * 
 * 
 * @author michalh
 *
 */
public class IgnoreTest {

	@Test
	public void testMath1() {
		assertThat(1 + 1, is(2));
	}

	@Ignore
	@Test
	public void testMath2() {
		assertThat(1 + 2, is(5));
	}

	@Ignore("some one please create a test for Math3!")
	@Test
	public void testMath3() {
		// ...
	}

}
