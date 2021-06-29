package hisglobal.vo;

public class MortuaryDeceasedImageDtlVO extends ValueObject
{
	private String deceasedNo;
	private String slNo;
	private String patCrNo;
	private String episodeCode;
	private String uploadMode;
	private String isDefaultImage;
	private String fileNo;
	private String filePath;
	private byte[] imageFile;
	private String entryDate;
	private String uploadRemarks;
	private String imageHeader;
	
	
	public String getImageHeader() {
		return imageHeader;
	}
	public void setImageHeader(String imageHeader) {
		this.imageHeader = imageHeader;
	}
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getUploadMode() {
		return uploadMode;
	}
	public void setUploadMode(String uploadMode) {
		this.uploadMode = uploadMode;
	}
	public String getIsDefaultImage() {
		return isDefaultImage;
	}
	public void setIsDefaultImage(String isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getUploadRemarks() {
		return uploadRemarks;
	}
	public void setUploadRemarks(String uploadRemarks) {
		this.uploadRemarks = uploadRemarks;
	}
	
}
