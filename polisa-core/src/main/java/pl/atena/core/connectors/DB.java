package pl.atena.core.connectors;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DB {

	private static Logger log = Logger.getLogger(DB.class.getCanonicalName());

	private static java.sql.Connection conn = null;

	private DB() {
	}

	private static void init() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:D:/projekty_akademia/db1/polisa.db");
			log.info("Nawiązano połączenie z bazą danych");
		} catch (Exception e) {
			log.throwing(DB.class.getCanonicalName(), "init", e);
			throw new SQLException(e);
		}
	}

	public java.sql.Connection getConn() {
		return conn;
	}

	public static java.sql.Connection get() throws SQLException {
		if (conn == null || conn.isClosed()) {
			init();
		}
		return conn;
	}

	public static void close() throws SQLException {
		if (conn != null) {
			if (!conn.isClosed())
				conn.close();
		}
	}

}
