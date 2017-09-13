package controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.MunicipalityDAO;
import entities.Municipality;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class MunicipalityController {
	
	@Autowired
	private MunicipalityDAO dao;
	
	@RequestMapping(path = "municipalities/ping", method = RequestMethod.GET)
	public String ping() {
		return "municipality pong";
	}
	
	@RequestMapping(path = "municipalities", method = RequestMethod.GET)
	public Set<Municipality> index(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("WEEEEEE INDEX!!!!");
		return dao.index();
	}
	
	@RequestMapping(path = "municipalities/{id}", method = RequestMethod.GET) 
	public Municipality show(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		System.out.println("IN MUNICIPALITY SHOW ROUTE WITH ID: " + id);
		return dao.show(id);
	}
	
	@RequestMapping(path = "municipalities", method = RequestMethod.POST)
	public Municipality create(HttpServletRequest req, HttpServletResponse res, @RequestBody String municipalityJson) {
		return dao.create(municipalityJson);
	}
	
	@RequestMapping(path="municipalities/{id}", method = RequestMethod.PUT) 
	public Municipality update(HttpServletRequest req, HttpServletResponse res, @RequestBody String municipalityJson, @PathVariable int id) {
		return dao.update(id, municipalityJson);
	
	}
	
	@RequestMapping(path="municipalities/{id}", method = RequestMethod.DELETE)
	public Boolean delete(HttpServletRequest req, HttpServletResponse res, @PathVariable int id) {
		return dao.destroy(id);
	}

}
