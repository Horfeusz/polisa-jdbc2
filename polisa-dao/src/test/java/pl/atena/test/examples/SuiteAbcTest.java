package pl.atena.test.examples;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Wiele przypadków testowych
 * 
 * @see <a href=
 *      "http://www.mkyong.com/unittest/junit-4-tutorial-5-suite-test/">http://www.mkyong.com/unittest/junit-4-tutorial-5-suite-test/</a>
 * 
 * 
 * @author michalh
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ExampleTest1.class, // test case 1
		TimeoutTest.class // test case 2
})
public class SuiteAbcTest {

}
