package pl.atena.test.examples.collections;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class ListObjectTest {

	@Test
	public void testAssertList() {

		List<Fruit> list = Arrays.asList(new Fruit("Banana", 99), new Fruit("Apple", 20));

		// Test equals
		assertThat(list, hasItems(new Fruit("Banana", 99), new Fruit("Apple", 20)));

		assertThat(list, containsInAnyOrder(new Fruit("Apple", 20), new Fruit("Banana", 99)));

		// Test class property, and its value
		assertThat(list, containsInAnyOrder(hasProperty("name", is("Apple")), hasProperty("name", is("Banana"))));

	}

}
