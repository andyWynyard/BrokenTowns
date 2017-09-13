package controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.AuthDAO;
import entities.User;
import entities.UserDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthDAO authDAO;

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public User register(HttpSession session, HttpServletResponse res,  @RequestBody String jsonUser) {
	
		User registeredUser = authDAO.register(jsonUser);
		
		if(registeredUser == null) {
			res.setStatus(422);
			return null;
		}
		session.setAttribute("user", registeredUser);
		res.setStatus(201);
		return registeredUser;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public User login(HttpSession session, HttpServletResponse res, @RequestBody String json) {
		
		System.out.println(json);
		
		ObjectMapper om = new ObjectMapper();
		User user = null;
		try {
			user = om.readValue(json, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		User loggedInUser = authDAO.login(user);
		if(loggedInUser == null) {
			res.setStatus(401);
			return null;
		}
		session.setAttribute("user", loggedInUser);
		System.out.println(loggedInUser);
		
		return loggedInUser;
	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public Boolean logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("user");
		return null;
	}

	@RequestMapping(path = "/unauthorized")
	public String unauth(HttpServletResponse response) {
		response.setStatus(401);
		return "unauthorized";
	}

}