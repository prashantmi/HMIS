/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : OPD IPD
 ## Process/Database Object Name	    : PATIENT CLINICAL DOCUMENTS
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2017 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package emr.vo;

import hisglobal.vo.ValueObject;

public class PatientClinicalDocDetailVO extends ValueObject
{
    private String documentId;
    private String serialNo;
    private String patCrNo;
    private String episodeCode;
    private String visitNo;
    private String documentTitle;
    private String admNo;
    private String remark;
    private String isValid;
    private String documentType;
    private String seatId;
    private String createdBy;
    private String modifiedBy;
    private String generatedBy;
    private String lstModDate;
    private String dataOwner;
    private String hospCode;
    private String dataStatus;
    private String deptUnitcode;
    private String docStatus;
    private String empNo;
    private String accessType;
    private String documentDesc;
    private String documentTypeDesc;
   
    
    private String clinicalSectionCode;
    private String clinicalSectionName;
    private String clinicalSectionOrder;
    private String clinicalSecCompCode;
    private String clinicalSecCompKey;
    private String clinicalSecCompMappingVnd;
    private String clinicalSecCompOrder;
    private String clinicalSecCompTitle;
    private String clinicalSecCompDataOwner;
    private String clinicalSecCompDataStatus;
    private String clinicalSecComplstmodDate;
    private String clinicalSecComplstmodSeatId;
    private String clinicalSecComplstmodAction;
    private String entryDate;
   
    private String clinicalDocCompSelectJSON;  //json
    private String clinicalDocCompHTMLJSON; // jsonhtml
    private String clinicalCompSelectJSON;  
    private String clinicalDocCompSelectJSONModify;
    
    private String documentData;
    
    //Added by Dheeraj on 15-Oct-2018
    private String fileName;
    private String fileType;
    private byte[] docData;
    private String pathForWindows;
    private String pathForLinux;
    private String visitDate;
    private String prescribedBy;
    
    //Added by Vasu on 04.Dec.2018
    private String clinicalSecTemplateKey;
    private String clinicalSecTemplateContent;
    private String clinicalSecParentShort;
    private String wardCode;
    private String htmlString;
    private String chkSelectedSections;
    private String isToggable;
    
	public String getClinicalDocCompSelectJSON() {
		return clinicalDocCompSelectJSON;
	}
	public void setClinicalDocCompSelectJSON(String clinicalDocCompSelectJSON) {
		this.clinicalDocCompSelectJSON = clinicalDocCompSelectJSON;
	}
	public String getClinicalSecCompCode() {
		return clinicalSecCompCode;
	}
	public void setClinicalSecCompCode(String clinicalSecCompCode) {
		this.clinicalSecCompCode = clinicalSecCompCode;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getSlNo() {
		return serialNo;
	}
	public void setSlNo(String serialNo) {
		this.serialNo = serialNo;
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
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public String getAdmNo() {
		return admNo;
	}
	public void setAdmNo(String admNo) {
		this.admNo = admNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getGeneratedBy() {
		return generatedBy;
	}
	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}
	public String getLstModDate() {
		return lstModDate;
	}
	public void setLstModDate(String lstModDate) {
		this.lstModDate = lstModDate;
	}
	public String getDataOwner() {
		return dataOwner;
	}
	public void setDataOwner(String dataOwner) {
		this.dataOwner = dataOwner;
	}
	public String getHospCode() {
		return hospCode;
	}
	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getDeptUnitcode() {
		return deptUnitcode;
	}
	public void setDeptUnitcode(String deptUnitcode) {
		this.deptUnitcode = deptUnitcode;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getClinicalSectionCode() {
		return clinicalSectionCode;
	}
	public void setClinicalSectionCode(String clinicalSectionCode) {
		this.clinicalSectionCode = clinicalSectionCode;
	}
	public String getClinicalSectionName() {
		return clinicalSectionName;
	}
	public void setClinicalSectionName(String clinicalSectionName) {
		this.clinicalSectionName = clinicalSectionName;
	}
	
	public String getClinicalSectionOrder() {
		return clinicalSectionOrder;
	}
	public void setClinicalSectionOrder(String clinicalSectionOrder) {
		this.clinicalSectionOrder = clinicalSectionOrder;
	}
	public String getClinicalSecCompKey() {
		return clinicalSecCompKey;
	}
	public void setClinicalSecCompKey(String clinicalSecCompKey) {
		this.clinicalSecCompKey = clinicalSecCompKey;
	}
	public String getClinicalSecCompMappingVnd() {
		return clinicalSecCompMappingVnd;
	}
	public void setClinicalSecCompMappingVnd(String clinicalSecCompMappingVnd) {
		this.clinicalSecCompMappingVnd = clinicalSecCompMappingVnd;
	}
	public String getClinicalSecCompOrder() {
		return clinicalSecCompOrder;
	}
	public void setClinicalSecCompOrder(String clinicalSecCompOrder) {
		this.clinicalSecCompOrder = clinicalSecCompOrder;
	}
	public String getClinicalSecCompTitle() {
		return clinicalSecCompTitle;
	}
	public void setClinicalSecCompTitle(String clinicalSecCompTitle) {
		this.clinicalSecCompTitle = clinicalSecCompTitle;
	}
	public String getClinicalSecCompDataOwner() {
		return clinicalSecCompDataOwner;
	}
	public void setClinicalSecCompDataOwner(String clinicalSecCompDataOwner) {
		this.clinicalSecCompDataOwner = clinicalSecCompDataOwner;
	}
	public String getClinicalSecCompDataStatus() {
		return clinicalSecCompDataStatus;
	}
	public void setClinicalSecCompDataStatus(String clinicalSecCompDataStatus) {
		this.clinicalSecCompDataStatus = clinicalSecCompDataStatus;
	}
	public String getClinicalSecComplstmodDate() {
		return clinicalSecComplstmodDate;
	}
	public void setClinicalSecComplstmodDate(String clinicalSecComplstmodDate) {
		this.clinicalSecComplstmodDate = clinicalSecComplstmodDate;
	}
	public String getClinicalSecComplstmodSeatId() {
		return clinicalSecComplstmodSeatId;
	}
	public void setClinicalSecComplstmodSeatId(String clinicalSecComplstmodSeatId) {
		this.clinicalSecComplstmodSeatId = clinicalSecComplstmodSeatId;
	}
	public String getClinicalSecComplstmodAction() {
		return clinicalSecComplstmodAction;
	}
	public void setClinicalSecComplstmodAction(String clinicalSecComplstmodAction) {
		this.clinicalSecComplstmodAction = clinicalSecComplstmodAction;
	}
	public String getClinicalDocCompHTMLJSON() {
		return clinicalDocCompHTMLJSON;
	}
	public void setClinicalDocCompHTMLJSON(String clinicalDocCompHTMLJSON) {
		this.clinicalDocCompHTMLJSON = clinicalDocCompHTMLJSON;
	}
	public String getClinicalCompSelectJSON() {
		return clinicalCompSelectJSON;
	}
	public void setClinicalCompSelectJSON(String clinicalCompSelectJSON) {
		this.clinicalCompSelectJSON = clinicalCompSelectJSON;
	}
	public String getDocumentDesc() {
		return documentDesc;
	}
	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}
	public String getDocumentTypeDesc() {
		return documentTypeDesc;
	}
	public void setDocumentTypeDesc(String documentTypeDesc) {
		this.documentTypeDesc = documentTypeDesc;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getDocumentData() {
		return documentData;
	}
	public void setDocumentData(String documentData) {
		this.documentData = documentData;
	}
	public String getClinicalDocCompSelectJSONModify() {
		return clinicalDocCompSelectJSONModify;
	}
	public void setClinicalDocCompSelectJSONModify(
			String clinicalDocCompSelectJSONModify) {
		this.clinicalDocCompSelectJSONModify = clinicalDocCompSelectJSONModify;
	}
	

	/*private String profileId;
	private String serialNo;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String profileHeader;
	private String remarks;
	private String profileType;
	private String profileTypeDesc;
	private String accessType;
	private String userLevel;
	private String departmentUnitCode;
	private String wardCode;
	private String departmentUnit;
	private String patientName;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String hospitalCode;
	private String effectiveFrom;
	private String effectiveTo;

	private String profileData;
	private String profileStatus;
	private String patCategoryCode;
	private String policyType;
	private String accessTypeFlag;
	private String treatmentType;
	private String reportMode;
	private String snomdPTRemarks;
	private String snomdCIdRemarks;
	*/
    
    //Added by Dheeraj on 15-Oct-2018
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getDocData() {
		return docData;
	}
	public void setDocData(byte[] docData) {
		this.docData = docData;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getPathForWindows() {
		return pathForWindows;
	}
	public void setPathForWindows(String pathForWindows) {
		this.pathForWindows = pathForWindows;
	}
	public String getPathForLinux() {
		return pathForLinux;
	}
	public void setPathForLinux(String pathForLinux) {
		this.pathForLinux = pathForLinux;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getPrescribedBy() {
		return prescribedBy;
	}
	public void setPrescribedBy(String prescribedBy) {
		this.prescribedBy = prescribedBy;
	}
	public String getClinicalSecTemplateKey() {
		return clinicalSecTemplateKey;
	}
	public void setClinicalSecTemplateKey(String clinicalSecTemplateKey) {
		this.clinicalSecTemplateKey = clinicalSecTemplateKey;
	}
	public String getClinicalSecParentShort() {
		return clinicalSecParentShort;
	}
	public void setClinicalSecParentShort(String clinicalSecParentShort) {
		this.clinicalSecParentShort = clinicalSecParentShort;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getClinicalSecTemplateContent() {
		return clinicalSecTemplateContent;
	}
	public void setClinicalSecTemplateContent(String clinicalSecTemplateContent) {
		this.clinicalSecTemplateContent = clinicalSecTemplateContent;
	}
	public String getHtmlString() {
		return htmlString;
	}
	public void setHtmlString(String htmlString) {
		this.htmlString = htmlString;
	}
	public String getChkSelectedSections() {
		return chkSelectedSections;
	}
	public void setChkSelectedSections(String chkSelectedSections) {
		this.chkSelectedSections = chkSelectedSections;
	}
	public String getIsToggable() {
		return isToggable;
	}
	public void setIsToggable(String isToggable) {
		this.isToggable = isToggable;
	}

	
	
	
  
	
	}

