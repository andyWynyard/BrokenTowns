package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Case {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name="photo_url")
	private Photo photo;

	private String title;

	private String description;

	@ManyToOne
	@JoinColumn(name = "municipality_id")
	@JsonManagedReference
	private List<Municipality> municipalities;

	@OneToMany
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	// Note, 'Message' could not be used as a class name due to 'Message' being a
	// deprecated Java Keyword, so that's why the class is called 'MessagePost'
	@ManyToOne
	@JsonManagedReference
	private List<MessagePost> messagePosts;

	private int longitude;

	private int latitude;

	@Min(1)
	@Max(5)
	private int severity;

	private boolean done;
	

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
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

	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MessagePost> getMessagePosts() {
		return messagePosts;
	}

	public void setMessagePosts(List<MessagePost> messagePosts) {
		this.messagePosts = messagePosts;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public int getId() {
		return id;
	}

}
