package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.User;

@Transactional
@Repository
public class AuthDAOImpl implements AuthDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User u) {
		u.setPassword(encoder.encode(u.getPassword()));
		em.persist(u);
		em.flush();
		return u;
	}

	@Override
	public User login(User u) {
		
		String query = "SELECT u FROM User u WHERE u.email = :email";
		
		User managedUser = em.createQuery(query, User.class).setParameter("email", u.getEmail()).getResultList().get(0);
		 if(encoder.matches(u.getPassword(), managedUser.getPassword())) {
			 return managedUser;
		 }
		
		return null;
	}

	@Override
	public String encryptPassword(String rawPassword) {
		String encryptedPassword = encoder.encode(rawPassword);
		return encryptedPassword;
	}
	
	

}
