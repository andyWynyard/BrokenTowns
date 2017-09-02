package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

	//User Entity Tests
	@Test
	public void test_for_user_first_name() {
		assertNotEquals("chicken", user.getFirstName());
		assertEquals("Some", user.getFirstName());
		
	}
	
	@Test
	public void test_user_for_a_message() {
		String text = user.getMessages().get(0).getText();
		assertNotEquals("chicken", text);
		assertEquals("stuff's broken", text);
	}
	
	@Test
	public void test_user_for_photo() {
		String superSecretKey = user.getPhotos().get(0).getS3Key();
		assertNotEquals("chicken", superSecretKey);
		assertEquals("secret key", superSecretKey);
	}
	
	@Test
	public void test_user_for_case_item() {
		String title = user.getCaseItems().get(0).getTitle();
		assertNotEquals("chicken", title);
		assertEquals("stuff", title);
	}
	
	//Message Entity Tests
	@Test
	public void test_message_for_id() {
		int id = messagePost.getId();
		assertNotEquals(17, id);
		assertEquals(1, id);
	}
	
	@Test
	public void test_message_for_user() {
		int userId = messagePost.getUser().getId();
		assertNotEquals(67, userId);
		assertEquals(1, userId);
	}
	
	@Test
	public void test_message_for_case_id() {
		int caseId = messagePost.getCaseItem().getId();
		assertNotEquals(43, caseId);
		assertEquals(1, caseId);
	}
	
	//CaseItem Entity Tests
	@Test
	public void test_case_item_for_description() {
		assertNotEquals("mailbox", caseItem.getDescription());
		assertEquals("things be broke", caseItem.getDescription());
	}
	
	@Test
	public void test_case_item_for_user_last_name() {
		assertNotEquals("kangaroo", caseItem.getUser().getLastName());
		assertEquals("Dude", caseItem.getUser().getLastName());
	}
	
	@Test
	public void test_case_item_for_photo() {
		assertNotEquals("pretty", caseItem.getPhoto().getUrl());
		assertEquals("apicture@picture.com", caseItem.getPhoto().getUrl());
	}
	
	@Test
	public void test_case_item_for_municipality() {
		assertNotEquals("middle of nowhere", caseItem.getMunicipality().getName());
		assertEquals("some city", caseItem.getMunicipality().getName());
	}
	
	@Test
	public void test_case_item_for_message_user_id() {
		assertNotEquals(872, caseItem.getMessagePosts().get(0).getUser().getId());
		assertEquals(1, caseItem.getMessagePosts().get(0).getUser().getId());
	}
	
	//Municipality Entity Tests
	@Test
	public void test_municipality_for_state() {
		assertNotEquals("La La Land", municipality.getState());
		assertEquals("some state", municipality.getState());
	}
	
	@Test
	public void test_municipality_for_latitude() {
		assertNotEquals(9999, municipality.getCaseItems().get(0).getLatitude());
		assertEquals(5678, municipality.getCaseItems().get(0).getLatitude());
	}
	
	//Photo Entity Tests
	@Test
	public void test_photo_for_url() {
		assertNotEquals("piggy bank", photo.getUrl());
		assertEquals("apicture@picture.com", photo.getUrl());
	}
	
	@Test
	public void test_photo_for_case_item_longitude() {
		assertNotEquals(666, photo.getCaseItem().getLongitude());
		assertEquals(1234, photo.getCaseItem().getLongitude());
	}
	
	@Test
	public void test_photo_for_user_email() {
		assertNotEquals("paint", photo.getUser().getEmail());
		assertEquals(null, photo.getUser().getEmail());
	}
}
