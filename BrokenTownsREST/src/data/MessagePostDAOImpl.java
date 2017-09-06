package data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.CaseItem;
import entities.MessagePost;

@Transactional
@Repository
public class MessagePostDAOImpl implements MessagePostDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<MessagePost> index(int caseId) {
		String query = "SELECT m FROM MessagePost m WHERE m.caseItem.id = :caseId";
		List<MessagePost> allMessagesList = em.createQuery(query, MessagePost.class).setParameter("caseId",  caseId).getResultList();
		Set<MessagePost> allMessagesSet = new LinkedHashSet<>(allMessagesList);

		return allMessagesSet;
	}

	@Override
	public MessagePost show(int caseId, int id) {
		return em.find(MessagePost.class, id);
	}

	@Override
	public MessagePost create(int caseId, String jsonMessagePost) {
		ObjectMapper mapper = new ObjectMapper();
		MessagePost mp = null;
		try {
			mp = mapper.readValue(jsonMessagePost, MessagePost.class);
			mp.setCaseItem(em.find(CaseItem.class, caseId));
			em.persist(mp);
			em.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mp;
	}

	@Override
	public MessagePost update(int caseId, int id, String jsonMessagePost) {
		MessagePost managed = em.find(MessagePost.class, id);
		MessagePost updated = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			updated = mapper.readValue(jsonMessagePost, MessagePost.class);
			updated.setCaseItem(em.find(CaseItem.class, caseId));
			managed.setCaseItem(updated.getCaseItem());
			managed.setCreateDate(updated.getCreateDate());
			managed.setText(updated.getText());
			managed.setUser(updated.getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managed;
	}

	@Override
	public boolean destroy(int caseId, int id) {
		try {
			MessagePost mp = em.find(MessagePost.class, id);
			em.remove(mp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
