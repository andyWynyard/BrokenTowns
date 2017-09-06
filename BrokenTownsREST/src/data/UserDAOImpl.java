package data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private AuthDAO dao;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<User> index() {
		String query = "SELECT u FROM User u";
		List<User> tempList = em.createQuery(query, User.class).getResultList();
		Set<User> users = new LinkedHashSet<>(tempList);
		return users;
	}

	@Override
	public User show(int id) {
		String query = "SELECT u FROM User u WHERE u.id = :id";
		return em.createQuery(query, User.class).setParameter("id", id).getResultList().get(0);
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
			managed.setFirstName(updated.getFirstName());
			managed.setLastName(updated.getLastName());
			managed.setPassword(dao.encryptPassword(updated.getPassword()));
			managed.setEmail(updated.getEmail());

			return managed;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
