package data;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.CaseItem;
import entities.Photo;
import entities.PhotoDTO;
import services.S3Service;
import entities.User;


@Transactional
@Repository
public class S3ImageDAOImpl implements S3ImageDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	  // include the s3 service which actually talks to amazon
		@Autowired
		private S3Service s3;

		@Override
		public Photo create(File file, String dataJSON, String fileName) {
			Photo image = null;
			String s3Url = null;
			PhotoDTO dto = null;
			System.out.println("**************************************");
			 System.out.println("**************************************");
			 System.out.println("************json**********");
			 System.out.println(dataJSON);
			 System.out.println("**************************************");
			 System.out.println("**************************************");
			 System.out.println("**************************************");
			
			
			try {
		      // use service to upload image and get URL response
					 s3Url = s3.uploadFileToS3(fileName, file);
					 System.out.println("**************************************");
					 System.out.println("**************************************");
					 System.out.println("************S3URL**********");
					 System.out.println(s3Url);
					 System.out.println("**************************************");
					 System.out.println("**************************************");
					 System.out.println("**************************************");
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			// create my own enitity (S3Image) to persist in database with association
		    // to a user
			
			ObjectMapper om = new ObjectMapper();
			try {
				dto = om.readValue(dataJSON, PhotoDTO.class);
				dto.setS3Key(s3Url.substring(s3Url.lastIndexOf("/")+1));
				dto.setUrl(s3Url);
				image = generatePhoto(dto);
				 System.out.println("**************************************");
				 System.out.println("**************************************");
				 System.out.println("*************imgurl in photo***************");
				System.out.println(image.getUrl());
				 System.out.println("**************************************");
				 System.out.println("**************************************");
				 System.out.println("**************************************");
				em.persist(image);
				em.find(CaseItem.class,image.getCaseItem().getId())
					.setPhotoUrl(s3Url);
				em.flush();
			} catch(Exception e) {
				e.printStackTrace();
			}
	
		
			return image;

		}
		
		private Photo generatePhoto(PhotoDTO dto) {
			Photo p = new Photo();
			p.setaCase(em.find(CaseItem.class, dto.getCaseItemId()));
			p.setS3Key(dto.getS3Key());
			p.setUrl(dto.getUrl());
			p.setUser(em.find(User.class, dto.getUserId()));
			return p;
		}

	

}
