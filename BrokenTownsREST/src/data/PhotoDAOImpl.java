package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.CaseItem;
import entities.Photo;
import entities.PhotoDTO;
import entities.User;

@Transactional
@Repository
public class PhotoDAOImpl implements PhotoDAO {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Set<Photo> index() {
		String query = "SELECT p FROM Photo p";
		List<Photo> listPhoto = em.createQuery(query, Photo.class).getResultList();
		Set<Photo> setPhoto = new HashSet(listPhoto);
		return setPhoto;
	}

	@Override
	public Photo show(int id) {
		return em.find(Photo.class, id);

	}

	@Override
	public Photo create(String photoJson) {
		ObjectMapper om = new ObjectMapper();
		Photo p = null;
		PhotoDTO dto = null;
		try {
			
			dto = om.readValue(photoJson, PhotoDTO.class);
			p = generatePhoto(dto);
			em.persist(p);
			em.flush();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Photo update(int id, String photoJson) {
		Photo managed = em.find(Photo.class, id);
		Photo newPhoto = null;
		ObjectMapper om = new ObjectMapper();
		try {
			newPhoto = om.readValue(photoJson, Photo.class);
			managed.setS3Key(newPhoto.getS3Key());
			managed.setUrl(newPhoto.getUrl());
			managed.setUser(newPhoto.getUser());
			managed.setaCase(newPhoto.getCaseItem());
			return managed;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean destroy(int id) {
		boolean removed;
		try {
			Photo p = em.find(Photo.class, id);
			em.remove(p);
			removed = true;
		}catch(Exception e) {
			e.printStackTrace();
			removed = false;
		}
		return removed;
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
