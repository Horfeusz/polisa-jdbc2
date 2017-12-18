package pl.atena.dao.polisa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import pl.atena.core.ABaseDao;
import pl.atena.core.BaseDao;
import pl.atena.core.connectors.DB;
import pl.atena.domain.polisa.Polisa;
import pl.atena.domain.polisa.PolisaFiltr;

public class PolisaDao extends ABaseDao<Polisa> implements BaseDao<Polisa, PolisaFiltr> {

	private Logger log = Logger.getLogger(PolisaDao.class.getCanonicalName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.atena.core.BaseDao#create(pl.atena.domain.base.DTO)
	 */
	public void create(Polisa entity) throws SQLException {
		Connection c = DB.get();
		PreparedStatement ps = c.prepareStatement(insertQuery(Polisa.class), Statement.RETURN_GENERATED_KEYS);
		setValue(ps, entity);
		int row = ps.executeUpdate();
		if (row > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			entity.setId(rs.getInt(1));
		}
		log.info("Dodano " + row + " wierszy do tabeli POLISA: " + entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.atena.core.BaseDao#retrieve(java.lang.Integer)
	 */
	public Polisa retrieve(Integer id) throws SQLException {
		StringBuilder sb = new StringBuilder(selectQuery(Polisa.class)).append(" WHERE ID = ").append(id);
		PreparedStatement ps = DB.get().prepareStatement(sb.toString());
		ResultSet rs = ps.executeQuery();
		Polisa result = null;
		while (rs.next()) {
			result = getPolisaFromRs(rs);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.atena.core.BaseDao#select(java.lang.Object)
	 */
	public List<Polisa> select(PolisaFiltr filter) throws SQLException {
		Objects.nonNull(filter);

		StringBuilder sb = new StringBuilder(selectQuery(Polisa.class));
		if (filter.getNrPolisy() != null && !filter.getNrPolisy().equals("")) {
			sb.append(" WHERE NR_POLISY = ?");
		}

		PreparedStatement ps = DB.get().prepareStatement(sb.toString());
		ps.setString(1, filter.getNrPolisy());
		ResultSet rs = ps.executeQuery();

		List<Polisa> policies = new ArrayList<>();
		while (rs.next()) {
			policies.add(getPolisaFromRs(rs));
		}

		return policies;
	}

	/**
	 * Pobranie danych z ResultSeta
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Polisa getPolisaFromRs(ResultSet rs) throws SQLException {
		Polisa polisa = new Polisa();
		polisa.setId(rs.getInt("ID"));
		polisa.setNrPolisy(rs.getString("NR_POLISY"));
		polisa.setdPodpisania(LocalDate.parse(rs.getString("D_PODPISANIA")));
		polisa.setdRozpoczecia(LocalDateTime.parse(rs.getString("D_ROZPOCZECIA")));
		polisa.setdKonca(LocalDateTime.parse(rs.getString("D_KONCA")));
		polisa.setSkladka(rs.getBigDecimal("SKLADKA"));
		return polisa;
	}

	@Override
	protected String insertQuery(Class<Polisa> entity) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(this.tableName(entity))
				.append("(NR_POLISY, D_PODPISANIA, D_ROZPOCZECIA, D_KONCA, SKLADKA) VALUES (?,?,?,?,?)");
		return sb.toString();
	}

	@Override
	protected String updateQuery(Class<Polisa> entity) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ").append(this.tableName(entity))
				.append(" SET NR_POLISY = ?, D_PODPISANIA = ?, D_ROZPOCZECIA = ?, D_KONCA = ?, SKLADKA = ?");
		return sb.toString();
	}

	@Override
	public boolean update(Polisa entity) throws SQLException {
		Connection c = DB.get();
		String update = updateQuery(Polisa.class) + " WHERE ID = " + entity.getId();
		PreparedStatement ps = c.prepareStatement(update);
		setValue(ps, entity);
		int row = ps.executeUpdate();
		return row > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.atena.core.BaseDao#delete(pl.atena.domain.base.DTO)
	 */
	@Override
	public boolean delete(Polisa entity) throws SQLException {
		Objects.requireNonNull(entity);
		int ret = DB.get().createStatement().executeUpdate("DELETE FROM POLISA WHERE ID= " + entity.getId());
		return ret > 0 ? true : false;
	}

	/**
	 * Przygotowanie danych obiektu do zapisu
	 * 
	 * @param ps
	 * @param entity
	 * @throws SQLException
	 */
	private void setValue(PreparedStatement ps, Polisa entity) throws SQLException {
		ps.setString(1, entity.getNrPolisy());
		ps.setString(2, entity.getdPodpisania().format(DateTimeFormatter.ISO_LOCAL_DATE));
		ps.setString(3, entity.getdRozpoczecia().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		ps.setString(4, entity.getdKonca().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		ps.setBigDecimal(5, entity.getSkladka());
	}

	@Override
	protected String selectQuery(Class<Polisa> Entity) {
		return "SELECT * FROM " + tableName(Entity);
	}

}
