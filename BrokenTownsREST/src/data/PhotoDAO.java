package data;

import java.util.Set;

import entities.Photo;

public interface PhotoDAO {
	
	public Set<Photo> index(int id);
	public Photo show(int id);
	public Photo create(int uid, String photoJson);
	public Photo update(int uid, int pid, String photoJson);
	public Photo destroy(int uid, int pid);
	
	

}
