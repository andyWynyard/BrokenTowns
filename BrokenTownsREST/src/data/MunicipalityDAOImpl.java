package data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Municipality;

@Transactional
@Repository
public class MunicipalityDAOImpl implements MunicipalityDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Municipality> index() {
		String query = "SELECT m FROM Municipality m JOIN FETCH m.users";
		
		List<Municipality> tempList = em.createQuery(query, Municipality.class).getResultList();
		Set<Municipality> municipalities = new LinkedHashSet<>(tempList);
		return municipalities;
	}

	@Override
	public Municipality show(int id) {
		return em.find(Municipality.class, id);
	}

	@Override
	public Municipality create(String municipalityJson) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Municipality newMunicipality = mapper.readValue(municipalityJson, Municipality.class);
			em.persist(newMunicipality);
			em.flush();

			return newMunicipality;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Municipality update(int id, String municipalityJson) {
		Municipality managed = em.find(Municipality.class, id);
		Municipality updated = null;
		;
		ObjectMapper mapper = new ObjectMapper();
		try {
			updated = mapper.readValue(municipalityJson, Municipality.class);
			managed.setName(updated.getName());
			managed.setState(updated.getState());
			return managed;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Boolean destroy(int id) {
		Municipality municipality = em.find(Municipality.class, id);
		if (municipality != null) {
			em.remove(municipality);
			return true;
		} else {
			return false;
		}
	}

}
