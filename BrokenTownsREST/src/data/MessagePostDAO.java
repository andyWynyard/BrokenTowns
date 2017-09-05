package data;

import java.util.Set;
import entities.MessagePost;


public interface MessagePostDAO {
	
	public Set<MessagePost> index();
	public MessagePost show(int id);
	public MessagePost create(String jsonMessagePost);
	public MessagePost update(int id, String jsonMessagePost);
	public boolean destroy(int id);

}
