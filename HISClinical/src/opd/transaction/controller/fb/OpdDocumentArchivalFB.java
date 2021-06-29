package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import registration.controller.fb.CRNoFB;

public class OpdDocumentArchivalFB extends CRNoFB {
	private String serialNo;
	private String episodeCode;//for HRGNUM_EPISODE_NO
	private String episodeVisitNo;//for HRGNUM_VISIT_NO
	private String documentTitle;
	private String documentCode;
	private String documentName;
	private String documentDirectoryPath;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String uploadedFileName;	
	private String uploadType;
	private String hmode;
	private String fileType;
	private String[] prevAddDocTitle;
	private String[] currentlyAddDocTitle; 
	private String mlcNo;
	private String removeReason[];
	private String revoke[];
	private String selectedRevoke;
	private int CurrentPage=1;
	private String admissionNo;
	
	public String getSelectedRevoke() {
		return selectedRevoke;
	}
	public void setSelectedRevoke(String selectedRevoke) {
		this.selectedRevoke = selectedRevoke;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	
	public String getUploadedFileName() {
		return uploadedFileName;
	}
	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public String getDocumentCode() {
		return documentCode;
	}
	public void setDocumentCode(String documentCode) {
		this.documentCode = documentCode;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentDirectoryPath() {
		return documentDirectoryPath;
	}
	public void setDocumentDirectoryPath(String documentDirectoryPath) {
		this.documentDirectoryPath = documentDirectoryPath;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.setDocumentCode("");
		this.setDocumentDirectoryPath("");
		this.setDocumentName("");
		this.setDocumentTitle("");
		this.setEntryDate("");
		this.setEpisodeCode("");
		this.setEpisodeVisitNo("");
		this.setIsValid("");
		this.setUploadType("");
		this.setHmode("");
		this.setUploadedFileName("");
		this.setFileType("");
		this.setRemoveReason(new String[]{""});
		this.setSelectedRevoke("");
		super.reset(mapping, request);
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String[] getPrevAddDocTitle() {
		return prevAddDocTitle;
	}
	public void setPrevAddDocTitle(String[] prevAddDocTitle) {
		this.prevAddDocTitle = prevAddDocTitle;
	}
	public String[] getCurrentlyAddDocTitle() {
		return currentlyAddDocTitle;
	}
	public void setCurrentlyAddDocTitle(String[] currentlyAddDocTitle) {
		this.currentlyAddDocTitle = currentlyAddDocTitle;
	}
	public String[] getRevoke() {
		return revoke;
	}
	public void setRevoke(String[] revoke) {
		this.revoke = revoke;
	}
	public String[] getRemoveReason() {
		return removeReason;
	}
	public void setRemoveReason(String[] removeReason) {
		this.removeReason = removeReason;
	}
	public int getCurrentPage() {
		return CurrentPage;
	}
	public void setCurrentPage(int CurrentPage) {
		this.CurrentPage = CurrentPage;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
}
