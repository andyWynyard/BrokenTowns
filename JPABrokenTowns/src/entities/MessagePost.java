package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "message")
public class MessagePost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "case_item_id")
	private CaseItem caseItem;

	
	//removed @JsonBackReference from user as it is necessary needed to display the user name that is posting the message
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String text;

	@Column(name = "create_date")
	private String createDate;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCaseItem(CaseItem caseItem) {
		this.caseItem = caseItem;
	}

	public CaseItem getCaseItem() {
		return caseItem;
	}

	public int getId() {
		return id;
	}

}
