package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="admin")
	private boolean admin;

	@OneToOne
	@JoinColumn(name="municipality")
	private Municipality municipality;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String password;

	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<CaseItem> caseItems;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Photo> photos;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<MessagePost> messages;

	//GETS AND SETS
	
	
	
	
	public List<Photo> getPhotos() {
		return photos;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getMunicipality() {
		return municipality;
	}

	public void setMunicipality(int municipality) {
		this.municipality = municipality;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public void setCaseItems(List<CaseItem> caseItems) {
		this.caseItems = caseItems;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CaseItem> getCaseItems() {
		return caseItems;
	}

	public int getId() {
		return id;
	}

	public List<MessagePost> getMessages() {
		return messages;
	}

	public void setMessages(List<MessagePost> messages) {
		this.messages = messages;
	}

}
