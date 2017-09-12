package data;

import entities.User;

public interface AuthDAO {
	
	public User register(String jsonUser);
	public User login(User u);
	public String encryptPassword(String password);

}
