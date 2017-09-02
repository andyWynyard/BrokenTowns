package tests;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.CaseItem;
import entities.MessagePost;
import entities.Municipality;
import entities.Photo;
import entities.User;

public class EntityTests {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private User user;
	private Photo photo;
	private Municipality municipality;
	private MessagePost messagePost;
	private CaseItem caseItem;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("brokentown");
		em = emf.createEntityManager();
		
		user = em.find(User.class, 1);
		photo = em.find(Photo.class, 1);
		municipality = em.find(Municipality.class, 1);
		messagePost = em.find(MessagePost.class, 1);
		caseItem = em.find(CaseItem.class, 1);
	}
	
	@After
	public void tearDown() {
		em.close();
		emf.close();
		
		user = null;
		photo = null;
		municipality = null;
		messagePost = null;
		caseItem = null;
	}

	@Test
	public void test_for_user_first_name() {
		String firstName = user.getFirstName();
		assertEquals("chicken", firstName);
		
	}

}
