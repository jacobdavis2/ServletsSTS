package com.jacob.dao;

import java.sql.SQLException;

public interface IDao<T> {
	
	public T get(String id) throws SQLException;
	
	public boolean create(T item) throws SQLException;
	
	public boolean delete(T item) throws SQLException;
	
	public boolean update(T item) throws SQLException;
}
