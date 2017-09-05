package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="municipality")
public class Municipality {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String state;

	private String email;

	@OneToMany(mappedBy = "municipality")
	@JsonManagedReference(value = "caseToMunicipality")
	private List<CaseItem> caseItems;

	public List<CaseItem> getCaseItems() {
		return caseItems;
	}

	public void setCaseItems(List<CaseItem> caseItems) {
		this.caseItems = caseItems;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
