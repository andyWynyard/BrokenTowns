package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {

	private boolean admin;

	private int municipalityId;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private List<CaseItem> caseItems;

	private List<Photo> photos;

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

	public int getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(int municipalityId) {
		this.municipalityId = municipalityId;
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

	public List<MessagePost> getMessages() {
		return messages;
	}

	public void setMessages(List<MessagePost> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "User admin=" + admin + ", municipality=" + municipalityId + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", caseItems="
				+ caseItems + ", photos=" + photos + ", messages=" + messages + "]";
	}

}
