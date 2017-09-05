package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Photo;

@Transactional
@Repository
public class PhotoDAOImpl implements PhotoDAO {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Set<Photo> index(int id) {
		String query = "SELECT p FROM Photo p";
		List<Photo> listPhoto = em.createQuery(query, Photo.class).getResultList();
		Set<Photo> setPhoto = new HashSet(listPhoto);
		return setPhoto;
	}

	@Override
	public Photo show(int id) {
		em.find(Photo.class, id);
		return null;
	}

	@Override
	public Photo create(int uid, String photoJson) {
		ObjectMapper om = new ObjectMapper();
		Photo p = null;
		try {
			p = om.readValue(photoJson, Photo.class);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Photo update(int uid, int pid, String photoJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo destroy(int uid, int pid) {
		// TODO Auto-generated method stub
		return null;
	}

}
