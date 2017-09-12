package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "case_item")
public class CaseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
//	@JsonIgnore
	private User user;

	private String title;

	private String description;

	@ManyToOne
	@JoinColumn(name = "municipality_id")
	@JsonBackReference(value = "caseToMunicipality")
	private Municipality municipality;

	private double longitude;

	private double latitude;

	private boolean done;

	@Column(name = "complete_date")
	private String completeDate;

	@Min(1)
	@Max(5)
	private int severity;

	@Column(name="photo_url")
	private String photoUrl;

	// Note, 'Message' could not be used as a class name due to 'Message' being a
	// deprecated Java Keyword, so that's why the class is called 'MessagePost'
	@OneToMany(mappedBy = "caseItem")
	@JsonIgnore
	private List<MessagePost> messagePosts;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MessagePost> getMessagePosts() {
		return messagePosts;
	}

	public void setMessagePosts(List<MessagePost> messagePosts) {
		this.messagePosts = messagePosts;
	}

	public int getId() {
		return id;
	}

}
