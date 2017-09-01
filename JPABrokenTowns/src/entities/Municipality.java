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
	 
	private String worksEmail;
	
	private String worksPhoneNumber;

	private String name;
	
	private String state;

	
	public String getWorksEmail() {
		return worksEmail;
	}

	public void setWorksEmail(String worksEmail) {
		this.worksEmail = worksEmail;
	}

	public String getWorksPhoneNumber() {
		return worksPhoneNumber;
	}

	public void setWorksPhoneNumber(String worksPhoneNumber) {
		this.worksPhoneNumber = worksPhoneNumber;
	}

	public void setId(int id) {
		this.id = id;
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
