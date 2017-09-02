package entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
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

	@OneToMany(mappedBy = "messagePosts", cascade = CascadeType.ALL)
	@JoinColumn(name = "case_item_id")
	private CaseItem caseItem;

	@Column(name = "user_id")
	private User user;

	private String text;

	@Column(name = "create_date")
	private Timestamp createDate;

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

	public void setaCase(CaseItem aCase) {
		this.caseItem = aCase;
	}

	public int getId() {
		return id;
	}

}
