package data;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Photo;
import entities.User;
import services.S3Service;

@Transactional
@Repository
public class S3ImageDAOImpl implements S3ImageDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	  // include the s3 service which actually talks to amazon
		@Autowired
		private S3Service s3;

		@Override
		public Photo create(File file, User user, String fileName) {
			Photo image = new Photo();
			String s3Url = null;
			
			try {
		      // use service to upload image and get URL response
					 s3Url = s3.uploadFileToS3(user.getEmail() + fileName, file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			// create my own enitity (S3Image) to persist in database with association
		    // to a user
			image.setUser(user);
			image.setUserFileName(fileName);
			image.setUrl(s3Url);
			image.setS3Key(s3Url.substring(s3Url.lastIndexOf("/")+1));

			em.persist(image);
			em.flush();

			return image;

		}

	

}
