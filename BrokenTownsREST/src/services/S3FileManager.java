package services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3FileManager implements S3Service {
	
	// from the bean
	@Autowired
	private AmazonS3 s3;
	
	// generates unique key names for uploaded images
	@Autowired
	private UniqueS3KeyGenerator keyGen;
	
	 // loads environment variable by name dynamically from .properties file
	@Value("${aws_namecard_bucket}")
	private String bucketName;

	@Override
	public String uploadFileToS3(String fileName, File file) {
		// generate unique key for file for user
		String fileKey = keyGen.generateKey(fileName);
		try {
		// upsert image to s3	
			s3.putObject(new PutObjectRequest(this.bucketName, fileKey, file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (AmazonServiceException e) {
			e.printStackTrace();
			return null;
		}
		
		// return the url for the file with key
		return getUploadURL(fileKey);
		
	}

	@Override
	public boolean deleteFileFromS3(String fileName) {
		// delete request to s3
		try {
			s3.deleteObject(this.bucketName, fileName);
			return true;
		} catch (AmazonServiceException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	  private String getUploadURL(String fileName) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("https://");
	        sb.append(this.bucketName);
	        sb.append(".s3.amazonaws.com/");
	        sb.append(fileName);
	        return sb.toString();
	    }

}
