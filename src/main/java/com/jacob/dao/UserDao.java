package com.jacob.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.jacob.models.User;

public class UserDao implements IDao<User> {
	
	public User get(String id) throws SQLException{
		if (!Database.openConnection()) return null;
		
		PreparedStatement st = Database.connection.prepareStatement(
			"SELECT * FROM users WHERE users.userid = ?"
		);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		
		if (!rs.next()) return null;
		
		User u = new User();
		u.setId(UUID.fromString(id));
		u.setFirstName(rs.getString("fristname"));
		u.setLastName(rs.getString("lastname"));
		
		st.close();
		Database.connection.close();
		
		return u;
	}
	
	public User getByFullName(String firstname, String lastname) throws SQLException {
		if (!Database.openConnection()) return null;
		
		PreparedStatement st = Database.connection.prepareStatement(
			"SELECT * FROM users WHERE users.firstname = ? AND users.lastname = ?"
		);
		st.setString(1, firstname);
		st.setString(2, lastname);
		ResultSet rs = st.executeQuery();
		
		if (!rs.next()) return null;
		
		User u = new User();
		u.setId(UUID.fromString(rs.getString("userid")));
		u.setFirstName(firstname);
		u.setLastName(lastname);
		
		st.close();
		Database.connection.close();
		
		return u;
	}
	
	public boolean create(User item) throws SQLException {
		if (!Database.openConnection()) return false;
		
		PreparedStatement st = Database.connection.prepareStatement(
			"INSERT INTO users (userid, firstname, lastname) VALUES (?, ?, ?)"
		);
		st.setString(1, item.getId().toString());
		st.setString(2, item.getFirstName());
		st.setString(3, item.getLastName());
		int rowsChanged = st.executeUpdate();
		//Database.connection.commit();
		
		// Log rows changed here
		
		st.close();
		Database.connection.close();
		
		return true;
	}
	
	public boolean delete(User item) throws SQLException {
		if (!Database.openConnection()) return false;
		
		PreparedStatement st = Database.connection.prepareStatement(
			"DELETE FROM users WHERE userid = ?"
		);
		st.setString(1, item.getId().toString());
		int rowsChanged = st.executeUpdate();
		//Database.connection.commit();
		
		// Log rows changed here
		
		st.close();
		Database.connection.close();
		
		return true;
	}
	
	public boolean update(User item) throws SQLException {
		if (!Database.openConnection()) return false;
		
		PreparedStatement st = Database.connection.prepareStatement(
			"UPDATE users SET firstname = ?, lastname = ? WHERE userid = ?"
		);
		st.setString(1, item.getFirstName());
		st.setString(2, item.getLastName());
		st.setString(3, item.getId().toString());	
		int rowsChanged = st.executeUpdate();
		//Database.connection.commit();
		
		// Log rows changed here
		
		st.close();
		Database.connection.close();
		
		return true;
	}
}
