package mrd.vo;

import hisglobal.vo.ValueObject;

public class CertificateBEntryFormReqVO extends ValueObject {

	private String patCrNo;
	private String certificateId;
	private String episodeCode;
	private String episodeVisitNo;
	private String reqStatus;
	private String empNo;
	private String patAdmNo;
	private String billNo;
	private String isValid;
	private String handoverTo;
	private String handoverToName;
	private String seatId;
	private String handoverToRel;
	private String handoverToAddress;
	private String hospitsalCode;
	private String handoverToContact;
	private String systemIPAddress;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifySeatId;	
	private String remarks;
	private String patAge;
	
	
	private String patName;
	private String approvedBy;
	private String patPrimaryCatCode;
	private String recordType;
	
	private String patGenderCode;
	private String patContactNo;
	
	private String documentTitle;
	private String documentCode;
	private String documentName;
	private String documentDirectoryPath;
	private String documentTypeName;
	private byte[] docFile;
	private String mlcNo;
	private String removeReason;
	private String fileType;
	private String removeDate;
	private String removeSeatId;
	private String pathForWindows;
	private String pathPathLinux;
	private String FileName;
	private String docSlNo;
	
	
	
	
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatContactNo() {
		return patContactNo;
	}
	public void setPatContactNo(String patContactNo) {
		this.patContactNo = patContactNo;
	}
	
	
	
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String advisedBy) {
		this.approvedBy = advisedBy;
	}
	
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
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
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	public String getHandoverToName() {
		return handoverToName;
	}
	public void setHandoverToName(String handoverToName) {
		this.handoverToName = handoverToName;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getHandoverToRel() {
		return handoverToRel;
	}
	public void setHandoverToRel(String handoverToRel) {
		this.handoverToRel = handoverToRel;
	}
	public String getHospitsalCode() {
		return hospitsalCode;
	}
	public void setHospitsalCode(String hospitsalCode) {
		this.hospitsalCode = hospitsalCode;
	}
	
	public String getHandoverToContact() {
		return handoverToContact;
	}
	public void setHandoverToContact(String handoverToContact) {
		this.handoverToContact = handoverToContact;
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
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifySeatId() {
		return lastModifySeatId;
	}
	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}
	public String getHandoverToAddress() {
		return handoverToAddress;
	}
	public void setHandoverToAddress(String handoverToAddress) {
		this.handoverToAddress = handoverToAddress;
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
	public String getDocumentTypeName() {
		return documentTypeName;
	}
	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}
	public byte[] getDocFile() {
		return docFile;
	}
	public void setDocFile(byte[] docFile) {
		this.docFile = docFile;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getRemoveReason() {
		return removeReason;
	}
	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(String removeDate) {
		this.removeDate = removeDate;
	}
	public String getRemoveSeatId() {
		return removeSeatId;
	}
	public void setRemoveSeatId(String removeSeatId) {
		this.removeSeatId = removeSeatId;
	}
	public String getPathForWindows() {
		return pathForWindows;
	}
	public void setPathForWindows(String pathForWindows) {
		this.pathForWindows = pathForWindows;
	}
	public String getPathPathLinux() {
		return pathPathLinux;
	}
	public void setPathPathLinux(String pathPathLinux) {
		this.pathPathLinux = pathPathLinux;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getDocSlNo() {
		return docSlNo;
	}
	public void setDocSlNo(String docSlNo) {
		this.docSlNo = docSlNo;
	}
	
}
