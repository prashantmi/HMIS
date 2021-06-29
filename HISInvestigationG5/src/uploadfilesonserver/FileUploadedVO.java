package uploadfilesonserver;

public class FileUploadedVO {

	private String crno;
	private String storagePath;
	private String id;
	private String fileName;
	public String getCrno() {
		return crno;
	}
	public void setCrno(String crno) {
		this.crno = crno;
	}
	public String getStoragePath() {
		return storagePath;
	}
	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
