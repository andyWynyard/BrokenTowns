package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import data.PhotoDAO;

@RestController
public class PhotoController {
	
	@Autowired
	private PhotoDAO dao;
	
	

}
