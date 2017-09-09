package data;

import java.util.Set;

import entities.MessagePost;
import entities.User;


public interface MessagePostDAO {
	
	public Set<MessagePost> index(int caseId);
	public MessagePost show(int caseId, int id);
	public MessagePost create(int caseId, String jsonMessagePost, User user);
	public MessagePost update(int caseId, int id, String jsonMessagePost);
	public boolean destroy(int caseId, int id);

}
