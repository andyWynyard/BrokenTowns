package controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;
import entities.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO dao;
	
	@RequestMapping(path = "users/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path = "users", method = RequestMethod.GET)
	public Set<User> index(HttpServletRequest req, HttpServletResponse res) {
		return dao.index();
	}
	
	@RequestMapping(path = "users/{id}", method = RequestMethod.GET) 
	public User show(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		return dao.show(id);
	}
	
	@RequestMapping(path = "users", method = RequestMethod.POST)
	public User create(HttpServletRequest req, HttpServletResponse res, @RequestBody String userJson) {
		return dao.create(userJson);
	}
	
	@RequestMapping(path="users/{id}", method = RequestMethod.PUT) 
	public User update(HttpServletRequest req, HttpServletResponse res, @RequestBody String userJson, @PathVariable int id) {
		return dao.update(id, userJson);
	
	}
	
	@RequestMapping(path="users/{id}", method = RequestMethod.DELETE)
	public Boolean delete(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		return dao.destroy(id);
	}
	
}
