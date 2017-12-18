package pl.atena.dao.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import pl.atena.core.connectors.DB;
import pl.atena.dao.polisa.PolisaDao;
import pl.atena.domain.polisa.Polisa;
import pl.atena.domain.polisa.PolisaFiltr;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PolisaDaoTest {

	private PolisaDao dao;

	private static final String NR_POLISY = "10645256";

	@BeforeClass
	public static void runOnceBeforeClass() throws SQLException {
		Connection c = DB.get();
		c.createStatement().executeUpdate("DELETE FROM POLISA");
	}

	@AfterClass
	public static void runOnceAfterClass() throws SQLException {
		DB.close();
	}

	@Before
	public void runOnceBeforeTestMethod() {
		dao = new PolisaDao();
	}

	@Test
	public void test1Create() throws SQLException {
		Polisa polisa = new Polisa();
		polisa.setNrPolisy(NR_POLISY);
		polisa.setdPodpisania(LocalDate.now());
		polisa.setdRozpoczecia(LocalDateTime.now());
		polisa.setdKonca(LocalDateTime.now().plusYears(1));
		polisa.setSkladka(BigDecimal.valueOf(463.45));
		dao.create(polisa);
		assertTrue(polisa.getId() != null);
		assertThat(polisa.getId().intValue(), greaterThan(0));
	}

	@Test
	public void test2Select() throws SQLException {
		PolisaFiltr filtr = new PolisaFiltr();
		filtr.setNrPolisy(NR_POLISY);
		List<Polisa> polisy = dao.select(filtr);

		assertThat(polisy, hasSize(1));
		assertThat(polisy, contains(hasProperty("nrPolisy", is(NR_POLISY))));
	}

	@Test
	public void test3Update() throws SQLException {
		PolisaFiltr filtr = new PolisaFiltr();
		filtr.setNrPolisy(NR_POLISY);
		List<Polisa> polisy = dao.select(filtr);

		assertThat(polisy, hasSize(1));

		Polisa polisa = polisy.iterator().next();
		polisa.setSkladka(BigDecimal.valueOf(100.45));

		dao.update(polisa);

		Polisa polisaU = dao.retrieve(polisa.getId());
		assertThat(polisaU, hasProperty("skladka", is(BigDecimal.valueOf(100.45))));
	}

	@Test
	public void test4Delete() throws SQLException {
		PolisaFiltr filtr = new PolisaFiltr();
		filtr.setNrPolisy(NR_POLISY);
		List<Polisa> polisy = dao.select(filtr);

		assertThat(polisy, hasSize(1));

		Polisa polisa = polisy.iterator().next();

		dao.delete(polisa);

		Polisa polisaD = dao.retrieve(polisa.getId());
		assertNull(polisaD);
	}

	@Test(expected = NullPointerException.class)
	public void test5Delete() throws SQLException {
		dao.delete(null);
	}

	@Test(expected = SQLException.class)
	public void testCreateException() throws SQLException {
		Polisa polisa = new Polisa();
		polisa.setdPodpisania(LocalDate.now());
		polisa.setdRozpoczecia(LocalDateTime.now());
		polisa.setdKonca(LocalDateTime.now().plusYears(1));
		dao.create(polisa);
	}

}
