package pl.atena.core;

import java.sql.SQLException;
import java.util.List;

import pl.atena.domain.base.Entity;

public interface BaseDao<T extends Entity> {

	void create(T entity) throws SQLException;

	T retrieve(Long id) throws SQLException;

	void update(T entity) throws SQLException;

	void delete(T entity) throws SQLException;

	<F extends Object> List<T> select(F filter) throws SQLException;

}
