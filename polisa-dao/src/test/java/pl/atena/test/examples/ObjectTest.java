package pl.atena.test.examples;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pl.atena.test.examples.collections.Fruit;

public class ObjectTest {

	// Single Object
	@Test
	public void testClassProperty() {

		Fruit obj = new Fruit("GRUSZKA", 10);

		assertThat(obj, hasProperty("name"));
		assertThat(obj, hasProperty("name", is("GRUSZKA")));

		assertThat(obj, hasProperty("qty"));
		assertThat(obj, hasProperty("qty", is(10)));

	}

	// List Objects
	@SuppressWarnings("unchecked")
	@Test
	public void testClassPropertyInList() {

		List<Fruit> list = Arrays.asList(new Fruit("GRUSZKA", 10), new Fruit("POMARAŃCZA", 34));

		assertThat(list, contains(hasProperty("name", is("GRUSZKA")), hasProperty("name", is("POMARAŃCZA"))));

	}

}
