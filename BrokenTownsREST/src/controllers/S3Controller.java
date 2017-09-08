package controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.S3ImageDAO;
import entities.Photo;
import entities.PhotoDTO;
import entities.User;

@RestController
public class S3Controller {
	
	@Autowired
	private S3ImageDAO s3DAO;
	
		
		  // upload an image (or other file) to s3, persist in DB and return created object
		@RequestMapping(path = "/upload", method = RequestMethod.POST)
		public Photo upload(
					HttpServletRequest req,
					HttpServletResponse res,
					@RequestParam("file") MultipartFile file,
					@RequestParam("data") String dataJSON
							  ) {
			

			return s3DAO.create(multipartToFile(file), dataJSON, file.getOriginalFilename());
		}
		
		  // converts a multipart file to a file for s3 upload
		private File multipartToFile(MultipartFile multipart) {
			try {
				File convFile = new File( multipart.getOriginalFilename());
				multipart.transferTo(convFile);
				return convFile;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		  // DRY extraction of user from Session
		private User getUserFromRequest(HttpServletRequest req) {
			User u = null;
			try {
				u = (User) req.getSession().getAttribute("user");
			} catch (Exception e){
				e.printStackTrace();
			}
			return u;
		}
		
		

}
