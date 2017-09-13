package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.CaseDAO;
import entities.CaseItem;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class CaseItemController {
	
	@Autowired
	private CaseDAO dao;
	
	@RequestMapping(path = "caseItems/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path = "caseItems", method = RequestMethod.GET)
	public Set<CaseItem> index() {
		return dao.index();
	}
	
	@RequestMapping(path = "caseItems/{id}", method = RequestMethod.GET)
	public CaseItem show(@PathVariable int id) {
		return dao.show(id);
	}
	
	@RequestMapping(path = "caseItems", method = RequestMethod.POST)
	public CaseItem create(@RequestBody String jsonCaseItem) {
		return dao.create(jsonCaseItem);
	}
	
	@RequestMapping(path = "caseItems/{id}", method = RequestMethod.PUT)
	public CaseItem update(@PathVariable int id, @RequestBody String jsonCaseItem) {
		return dao.update(id, jsonCaseItem);
	}
	
	@RequestMapping(path = "caseItems/{id}", method = RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id) {
		return dao.destroy(id);
	}

}
