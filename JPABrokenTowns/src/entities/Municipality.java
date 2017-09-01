package entities;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Municipality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//note, this doesn't have to be a map, but if the API is going to be pulling up 
	//a list of people to contact, maybe we need to do a key value pair for if the 
	//works contact has a phone number, an email, etc. 
	@Column(name="works_contact")
	private Map<Integer, String> worksContact;

	private String name;
	
	private String state;

	public Map<Integer, String> getWorksContact() {
		return worksContact;
	}

	public void setWorksContact(Map<Integer, String> worksContact) {
		this.worksContact = worksContact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}
	
	
}
