package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.CaseItem;
import entities.CaseItemDTO;
import entities.Municipality;
import entities.User;

@Transactional
@Repository
public class CaseDAOImpl implements CaseDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<CaseItem> index() {
		String query = "SELECT c FROM CaseItem c";
		List<CaseItem> caseList = em.createQuery(query, CaseItem.class).getResultList();

		Set<CaseItem> allCases = new HashSet<>(caseList);
		return allCases;
	}

	@Override
	public CaseItem show(int id) {
		return em.find(CaseItem.class, id);
	}

	@Override
	public CaseItem create(String jsonCaseItem) {
		CaseItem ci = null;
		CaseItemDTO dto = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = mapper.readValue(jsonCaseItem, CaseItemDTO.class);
			ci = generateCaseItem(dto);
			em.persist(ci);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ci;
	}

	@Override
	public CaseItem update(int id, String jsonCaseItem) {
		CaseItem managed = em.find(CaseItem.class, id);
		CaseItem updated = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			updated = mapper.readValue(jsonCaseItem, CaseItem.class);

			managed.setCompleteDate(updated.getCompleteDate());
			managed.setDescription(updated.getDescription());
			managed.setDone(updated.isDone());
			managed.setMessagePosts(updated.getMessagePosts());
			managed.setSeverity(updated.getSeverity());
			managed.setTitle(updated.getTitle());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return managed;
	}

	@Override
	public boolean destroy(int id) {
		try {
			CaseItem cm = em.find(CaseItem.class, id);
			em.remove(cm);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private CaseItem generateCaseItem(CaseItemDTO dto) {
		CaseItem ci = new CaseItem();
		ci.setCompleteDate(dto.getCompleteDate());
		ci.setDescription(dto.getDescription());
		ci.setDone(dto.isDone());
		ci.setLatitude(dto.getLatitude());
		ci.setLongitude(dto.getLongitude());
		ci.setMunicipality(em.find(Municipality.class, dto.getMunicipalityId()));
		ci.setPhoto(dto.getPhoto());
		ci.setSeverity(dto.getSeverity());
		ci.setTitle(dto.getTitle());
		ci.setUser(em.find(User.class, dto.getUserId()));
		ci.setMessagePosts(null);
		
//		ci.setUser(userDao.show(dto.getUserId()));
//		ci.setMunicipality(municDao.show(dto.getMunicipalityId()));
//		ci.setPhoto(photo);
		return ci;
	}
}