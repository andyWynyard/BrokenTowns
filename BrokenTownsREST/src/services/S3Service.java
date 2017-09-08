package services;

import java.io.File;

public interface S3Service {
	public String uploadFileToS3(String fileName, File file);
	public boolean deleteFileFromS3(String key);
}
