package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PhotoUploadFB extends DeceasedTileFB
{
	private String hmode;
	private String deceasedNo;
	private String postmortemId;
	private String fileNo;
	private String filePath;
	private byte[] imageFile;
	private String isDefaultImage;
	private String removeImageIndex;
	private String patCrNo;
	private String index;
	private String imageAddedFlag;
	private String uploadRemarks;
	private String imageHeader;
	private String hiddenRemarks[];
	private String viewRemarks;
	
	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getRemoveImageIndex() {
		return removeImageIndex;
	}
	public void setRemoveImageIndex(String removeImageIndex) {
		this.removeImageIndex = removeImageIndex;
	}
	public String getIsDefaultImage() {
		return isDefaultImage;
	}
	public void setIsDefaultImage(String isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
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
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getImageAddedFlag() {
		return imageAddedFlag;
	}
	public void setImageAddedFlag(String imageAddedFlag) {
		this.imageAddedFlag = imageAddedFlag;
	}
	public String getImageHeader() {
		return imageHeader;
	}
	public void setImageHeader(String imageHeader) {
		this.imageHeader = imageHeader;
	}
	public String getUploadRemarks() {
		return uploadRemarks;
	}
	public void setUploadRemarks(String uploadRemarks) {
		this.uploadRemarks = uploadRemarks;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setImageHeader("");
		this.setUploadRemarks("");
	}
	public String[] getHiddenRemarks() {
		return hiddenRemarks;
	}
	public void setHiddenRemarks(String[] hiddenRemarks) {
		this.hiddenRemarks = hiddenRemarks;
	}
	public String getViewRemarks() {
		return viewRemarks;
	}
	public void setViewRemarks(String viewRemarks) {
		this.viewRemarks = viewRemarks;
	}
}
