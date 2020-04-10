package com.jacob.models;

import java.util.UUID;

public class User {
	UUID userId;
	String firstName;
	String lastName;
	
	public UUID getId() { return userId; }
	public void setId(UUID newId) { userId = newId; }
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String newFirstName) { firstName = newFirstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String newLastName) { lastName = newLastName; }
}
