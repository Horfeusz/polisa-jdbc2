package pl.atena.core;

import java.sql.SQLException;
import java.util.List;

import pl.atena.domain.base.DTO;

public interface BaseDao<T extends DTO, F extends Object> {

	void create(T entity) throws SQLException;

	T retrieve(Integer id) throws SQLException;

	boolean update(T entity) throws SQLException;

	boolean delete(T entity) throws SQLException;

	List<T> select(F filter) throws SQLException;

}
