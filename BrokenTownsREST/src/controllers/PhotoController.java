package controllers;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.PhotoDAO;
import entities.Photo;

@RestController
public class PhotoController {
	
	@Autowired
	private PhotoDAO dao;
	
	@RequestMapping(path="photo/ping", method=RequestMethod.GET)
	public String ping() {
		return "PhotoPong";
	}
	
	@RequestMapping(path="photo", method=RequestMethod.GET)
	public Set<Photo> index(HttpServletResponse rep) {
		return dao.index();
	}
	
	@RequestMapping(path="photo/{id}", method=RequestMethod.GET)
	public Photo show(@PathVariable int id, HttpServletResponse res) {
		return dao.show(id);
	}
	
	@RequestMapping(path="photo", method=RequestMethod.POST)
	public Photo create(@RequestBody String photoJson, HttpServletResponse res) {
		return dao.create(photoJson);
	}
	
	@RequestMapping(path="photo/{id}", method=RequestMethod.PUT)
	public Photo update(@PathVariable int id, @RequestBody String photoJson, HttpServletResponse res) {
		return dao.update(id, photoJson);
	}
	
	@RequestMapping(path="photo/{id}", method=RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id, HttpServletResponse res) {
		return dao.destroy(id);
	}
	
	
	

}
