package data;

import java.io.File;

import entities.Photo;
import entities.User;

public interface S3ImageDAO {
	public Photo create(File file, User user, String fileName);
}
