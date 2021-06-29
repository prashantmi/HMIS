package hisglobal.vo;

import hisglobal.vo.ValueObject;

public class ImageUtilityVO extends ValueObject {


	private String fileCode;
	
	private String fileName;
	private String ftpserver;
	private String ftplocation;
	private String fileUrl;
	private String fileDesc;
	private String originalFileName;
	private String extraFolder;
	private String savingFTPUrl;
	
	
	
	
	public String getSavingFTPUrl() {
		return savingFTPUrl;
	}
	public void setSavingFTPUrl(String savingFTPUrl) {
		this.savingFTPUrl = savingFTPUrl;
	}
	public String getExtraFolder() {
		return extraFolder;
	}
	public void setExtraFolder(String extraFolder) {
		this.extraFolder = extraFolder;
	}
	private byte[] imageByteArray;
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public byte[] getImageByteArray() {
		return imageByteArray;
	}
	public void setImageByteArray(byte[] imageByteArray) {
		this.imageByteArray = imageByteArray;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFtpserver() {
		return ftpserver;
	}
	public void setFtpserver(String ftpserver) {
		this.ftpserver = ftpserver;
	}
	public String getFtplocation() {
		return ftplocation;
	}
	public void setFtplocation(String ftplocation) {
		this.ftplocation = ftplocation;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
}
