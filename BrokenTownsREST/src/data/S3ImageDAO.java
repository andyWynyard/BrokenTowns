package data;

import java.io.File;

import entities.Photo;
import entities.PhotoDTO;
import entities.User;

public interface S3ImageDAO {
	public Photo create(File file, String dataJSON, String fileName);
}
