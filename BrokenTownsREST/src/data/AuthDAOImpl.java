package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Municipality;
import entities.User;
import entities.UserDTO;

@Transactional
@Repository
public class AuthDAOImpl implements AuthDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(String jsonUser) {
		ObjectMapper om = new ObjectMapper();
		UserDTO dto = null;
		User user = new User();
		try {
			dto = om.readValue(jsonUser, UserDTO.class);
			user.setAdmin(dto.isAdmin());
			user.setCaseItems(dto.getCaseItems());
			user.setEmail(dto.getEmail());
			user.setFirstName(dto.getFirstName());
			user.setLastName(dto.getLastName());
			user.setMessages(dto.getMessages());
			user.setMunicipality(em.find(Municipality.class, dto.getMunicipalityId()));
			user.setPassword(dto.getPassword());
			user.setPhotos(dto.getPhotos());
			user.setMunicipalityId(dto.getMunicipalityId());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		user.setPassword(encoder.encode(user.getPassword()));
		em.persist(user);
		em.flush();
		return user;
	}

	@Override
	public User login(User u) {
		
		String query = "SELECT u FROM User u WHERE u.email = :email";
		
		User managedUser = em.createQuery(query, User.class).setParameter("email", u.getEmail()).getResultList().get(0);
		 if(encoder.matches(u.getPassword(), managedUser.getPassword())) {
			 if (managedUser.getMunicipality() != null) {
				 managedUser.setMunicipalityId(managedUser.getMunicipality().getId());
			 }
			 System.out.println("Managed User: " + managedUser);
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
