package main.java.quemepongo2.persistence;

import java.util.List;

public interface IDaoInterface<T> {
	List<T> findAll();
	void save(T entity);
}
