package entities;

public class PhotoDTO {
	
	private String url;
	private int caseItemId;
	private String s3Key;
	private int userId;
	
	
	//GETTERS AND SETTERS
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCaseItemId() {
		return caseItemId;
	}
	public void setCaseItemId(int caseItemId) {
		this.caseItemId = caseItemId;
	}
	public String getS3Key() {
		return s3Key;
	}
	public void setS3Key(String s3Key) {
		this.s3Key = s3Key;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	

}
