package pl.atena.core;

import java.util.Objects;

import pl.atena.domain.base.DTO;
import pl.atena.domain.base.Entity;

public abstract class ABaseDao<T extends DTO> {

	protected String tableName(Class<T> entity) {
		Objects.nonNull(entity);
		Entity a = entity.getAnnotation(Entity.class);
		if (a == null) {
			return null;
		}
		return a.name();
	}

	protected abstract String insertQuery(Class<T> entity);

	protected abstract String updateQuery(Class<T> entity);

	protected abstract String selectQuery(Class<T> Entity);
}
