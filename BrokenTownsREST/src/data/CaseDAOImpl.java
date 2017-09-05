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
		CaseItem cm = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			cm = mapper.readValue(jsonCaseItem, CaseItem.class);
			em.persist(cm);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cm;
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
			managed.setLatitude(updated.getLatitude());
			managed.setLongitude(updated.getLongitude());
			managed.setMessagePosts(updated.getMessagePosts());
			managed.setMunicipality(updated.getMunicipality());
			managed.setPhoto(updated.getPhoto());
			managed.setSeverity(updated.getSeverity());
			managed.setTitle(updated.getTitle());
			managed.setUser(updated.getUser());

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

}
