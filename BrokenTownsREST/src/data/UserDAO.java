package data;

import java.util.Set;

import entities.User;

public interface UserDAO {
	
	Set<User> index();
	User show(int id);
	User create(String userJson);
	User update(int id, String userJson);
	Boolean destroy(int id);

}
