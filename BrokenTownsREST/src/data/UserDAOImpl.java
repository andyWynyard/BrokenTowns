package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<User> index() {
		String query = "SELECT * FROM user";
		List<User> tempList = em.createNativeQuery(query).getResultList();
		Set<User> users = new HashSet<>(tempList);
		return users;
	}

	@Override
	public User show(int id) {
		return em.find(User.class, id);
	}

	@Override
	public User create(String userJson) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			User newUser = mapper.readValue(userJson, User.class);
			em.persist(newUser);
			em.flush();

			return newUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User update(int id, String userJson) {
		User managed = em.find(User.class, id);
		User updated = null;;
		ObjectMapper mapper = new ObjectMapper();
		try {
			updated = mapper.readValue(userJson, User.class);
			managed.setFirstName(managed.getFirstName());
			managed.setLastName(managed.getLastName());
			managed.setPassword(managed.getPassword());
			managed.setEmail(managed.getEmail());

			return updated;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Boolean destroy(int id) {
		User user = em.find(User.class, id);
		if (user != null) {
			em.remove(user);
			return true;
		} else {
			return false;
		}
	}

}
