package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.MessagePostDAO;
import entities.MessagePost;

@RestController
public class MessageController {
	
	@Autowired
	MessagePostDAO dao;
	
	@RequestMapping(path = "messages/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path = "messages", method = RequestMethod.GET)
	public Set<MessagePost> index() {
		return dao.index();
	}
	
	@RequestMapping(path = "messages/{id}", method = RequestMethod.GET)
	public MessagePost show(@PathVariable int id) {
		return dao.show(id);
	}
	
	@RequestMapping(path = "messages", method = RequestMethod.POST)
	public MessagePost create(@RequestBody String jsonMessagePost) {
		return dao.create(jsonMessagePost);
	}
	
	@RequestMapping(path = "messages/{id}", method = RequestMethod.PUT)
	public MessagePost update(@PathVariable int id, @RequestBody String jsonMessagePost) {
		return dao.update(id, jsonMessagePost);
	}
	
	@RequestMapping(path = "messages/{id}", method = RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id) {
		return dao.destroy(id);
	}

}
