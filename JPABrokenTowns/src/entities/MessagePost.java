package entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class MessagePost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String text;
	
	private Timestamp createDate;
	
	@Column(name="user_id")
	private int userId;
	
	//For some reason, 'case' is an invalid variable declarator, so therfore I named it 
	//'aCase' as opposed to 'case'
	@OneToMany(mappedBy="messagePosts")
	@JoinColumn(name="case_id")
	private Case aCase;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Case getaCase() {
		return aCase;
	}

	public void setaCase(Case aCase) {
		this.aCase = aCase;
	}

	public int getId() {
		return id;
	}
	

}
