package pl.atena.dao.polisa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import pl.atena.core.BaseDao;
import pl.atena.core.connectors.DB;
import pl.atena.domain.polisa.Polisa;

public class PolisaDao implements BaseDao<Polisa> {

	private Logger log = Logger.getLogger(PolisaDao.class.getCanonicalName());

	public void create(Polisa entity) throws SQLException {
		Connection c = DB.get();

		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO POLISA(NR_POLISY, D_PODPISANIA, D_ROZPOCZECIA, D_KONCA, SKLADKA) VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, entity.getNrPolisy());
		ps.setString(2, entity.getdPodpisania().format(DateTimeFormatter.ISO_LOCAL_DATE));
		ps.setString(3, entity.getdRozpoczecia().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		ps.setString(4, entity.getdKonca().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		ps.setBigDecimal(5, entity.getSkladka());
		int row = ps.executeUpdate();
		if (row > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			entity.setId(rs.getInt(1));
		}
		log.info("Dodano " + row + " wierszy do tabeli POLISA: " + entity);
	}

	public Polisa retrieve(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Polisa entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void delete(Polisa entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	public <F> List<Polisa> select(F filter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
