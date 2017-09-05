package data;

import java.util.Set;

import entities.Photo;

public interface PhotoDAO {
	
	public Set<Photo> index();
	public Photo show(int id);
	public Photo create(String photoJson);
	public Photo update(int id, String photoJson);
	public boolean destroy(int id);
	
	

}
