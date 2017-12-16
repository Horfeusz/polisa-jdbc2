package pl.atena.dao.test;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.atena.core.connectors.DB;
import pl.atena.dao.polisa.PolisaDao;
import pl.atena.domain.polisa.Polisa;

public class PolisaDaoTest {

	private PolisaDao dao;

	@BeforeClass
	public static void runOnceBeforeClass() throws SQLException {
		DB.get();
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
	public void testCreate() throws SQLException {
		Polisa polisa = new Polisa();
		polisa.setNrPolisy("10645256");
		polisa.setdPodpisania(LocalDate.now());
		polisa.setdRozpoczecia(LocalDateTime.now());
		polisa.setdKonca(LocalDateTime.now().plusYears(1));
		polisa.setSkladka(BigDecimal.valueOf(463.45));
		dao.create(polisa);
		assertTrue(polisa.getId() != null);
		assertThat(polisa.getId().intValue(), greaterThan(0));
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
