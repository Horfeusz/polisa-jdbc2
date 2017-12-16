package pl.atena.test.examples;

import org.junit.Test;

/**
 * @see <a href=
 *      "http://www.mkyong.com/unittest/junit-4-tutorial-4-time-test/">http://www.mkyong.com/unittest/junit-4-tutorial-4-time-test/</a>
 * 
 * 
 * @author michalh
 *
 */
public class TimeoutTest {

	// This test will always failed :)
	@Test(timeout = 1000)
	public void infinity() {
		while (true)
			;
	}

	// This test can't run more than 5 seconds, else failed
	@Test(timeout = 5000)
	public void testSlowMethod() {
		// ...
	}
}
