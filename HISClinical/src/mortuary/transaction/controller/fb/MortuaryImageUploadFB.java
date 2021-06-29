package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MortuaryImageUploadFB extends ActionForm
{
	private String deceasedNo;
	private String patCrNo;
	private String slNo;
	private String episodeCode;
	private String seatId;
	private String isDefault;
	private String systemIPAddress;
	private String entryDate;
	private String isValid;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String fileNo;
	private String filePath;
	private String maxSerialNo;
	private String removeImageIndex;
	private String uploadRemarks[];
	private String imageHeader[];
	private String noOfImages="0";
	private String hmode;
	private String chk[];
	
	private int currentPage;
	
	public MortuaryImageUploadFB()
	{
		this.currentPage=1;
	}
	
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
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

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getSystemIPAddress() {
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}

	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
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

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getNoOfImages() {
		return noOfImages;
	}

	public void setNoOfImages(String noOfImages) {
		this.noOfImages = noOfImages;
	}

	public String getMaxSerialNo() {
		return maxSerialNo;
	}

	public void setMaxSerialNo(String maxSerialNo) {
		this.maxSerialNo = maxSerialNo;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.patCrNo="";
		this.maxSerialNo="";
		this.deceasedNo="";
		this.slNo="";
		this.noOfImages="0";
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsDefault() {
		return isDefault;
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

	public String[] getUploadRemarks() {
		return uploadRemarks;
	}

	public void setUploadRemarks(String[] uploadRemarks) {
		this.uploadRemarks = uploadRemarks;
	}

	public String[] getImageHeader() {
		return imageHeader;
	}

	public void setImageHeader(String[] imageHeader) {
		this.imageHeader = imageHeader;
	}

	public String getRemoveImageIndex() {
		return removeImageIndex;
	}

	public void setRemoveImageIndex(String removeImageIndex) {
		this.removeImageIndex = removeImageIndex;
	}
	
	
}
