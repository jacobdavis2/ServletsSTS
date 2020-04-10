package com.jacob.servlets;

import com.jacob.dao.Database;
import com.jacob.dao.UserDao;

import com.jacob.models.User;

import java.io.PrintWriter;
import java.util.UUID;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class HelloWorldServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		try {
			UserDao userDao = new UserDao();
			User u = new User();
			u.setId(UUID.randomUUID());
			u.setFirstName("Jacob");
			u.setLastName("Davis");
					
			userDao.create(u);
			
			User f = userDao.getByFullName("Jacob", "Davis");
			
			PrintWriter writer = res.getWriter();
			writer.println("Hello from inside your servlet!");
			writer.println("User: " + f.getLastName() + ", " + f.getFirstName() + "::" + f.getId());
			
			f.setFirstName("Dacob");
			f.setLastName("Javis");
			userDao.update(f);
			writer.println("User: " + f.getLastName() + ", " + f.getFirstName() + "::" + f.getId());
			
			userDao.delete(f);
			writer.println("Deleted!");
			
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
