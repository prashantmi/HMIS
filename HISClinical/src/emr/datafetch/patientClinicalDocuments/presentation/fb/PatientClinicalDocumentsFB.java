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

package emr.datafetch.patientClinicalDocuments.presentation.fb;

import hisglobal.vo.EpisodeDiagnosisVO;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ehr.EHRConfig;

public class PatientClinicalDocumentsFB extends ActionForm
{
	private String mode;
	private String patCrNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGender;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	
	private String queNo;
	private String patGenderAge;
	private String departmentUnitCode;
	private String hmode;
	private String orderBy;
	private String episodeNextVisitDate;
	private String episodeIsOpen;
	private String patChoice;
	private String selectedPatCrNo;
	private String roomCode;
	private String showRommList;
	private String visitType;
	private String hospitalCode;
	private String isCasualty;
	private String remarks[];
	
	private String departmentCode;
	private String episodeCode;
	
	private String snomedCIdRemarks[]; // snomed integration
	private String snomedPTRemarks[];
	private String deptUnit;
	private String visitDate;
	private String visitReason;
	private String snomdPTRemarks;
	private String snomdCIdRemarks;

	private String diagnosisCount="0";
	private String advisedBy;
	private String episodeVisitNo;
	
	//added for profile type
	/*private String admissionNo;
	private String patCategoryCode;
	private String deskType;
	private String deskId;
	private String entryDate;
	private String profileGenerationType;
	private String profileType;
	private String profileHeader;
	private String profileGenerationMode;
*/	
  
	private String admissionNo;
	private String patCategoryCode;
	private String deskType;
	private String deskId;
	private String entryDate;
	private String documentGenerationType;
//	private String documentType;
	private String documentHeader;
	private String documentGenerationMode;
	private String wardCode;
	private String selecteddocumentId;
	
	
	//  patient clinical document details
	    private String documentId;
	    private String serialNo;
	    private String visitNo;
	    private String documentTitle;
	    private String admNo;
	    private String remark;
	    private String isValid;
	    private String documentType;
	    private String seatId;
	    private String createdBy;
	    private String accessType;
	    private String documentHTML;
	    private String documentTypeDesc;

	
	//clinical section composition 
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
	    
	    
	    private String modifiedBy;
	    private String generatedBy;
	    private String lstModDate;
	    private String dataOwner;
	    private String hospCode;
	    private String dataStatus;
	    private String deptUnitcode;
	    private String docStatus;
	    private String empNo;
	     private String[] selectedIndex;
		
	    
	    
	// json
	    private String clinicalSectionCompJSON;
	    private String clinicalDocCompSelectJSON;
	    private String clinicalDocCompHTMLJSON; 
	    private String flagPreview; 
	    public static final String flagDefaultSelectionOnload = "1";
	    private String saveFlag;
	    
	    private String clinicalDocCompSelectJSONModify;
	    
	   private String clinicalDocumentMode;
	   
	   private String htmlDocHeader;
	    
	    public String getSaveFlag() {
			return saveFlag;
		}

		public void setSaveFlag(String saveFlag) {
			this.saveFlag = saveFlag;
		}

		public PatientClinicalDocumentsFB()
		{
			this.documentId="";
		
			this.documentHeader="";
			this.documentGenerationType="";
			//this.remarks="";
			this.documentType=EHRConfig.PATIENT_DOCUMENT_TYPE_FORWORD;
		//	this.accessType=OpdConfig.PATIENT_DOCUMENT_ACCESS_TYPE_ALL;
			this.setAccessType("");
			// this.userLevel="-1";
		

			this.entryDate="";

			this.selectedIndex= new String[0];
			
			
			
			
		}
		
		public void reset()
		{
			this.documentId="";
			
			this.documentHeader="";
			this.documentGenerationType="";
			//this.remarks="";
			this.documentType="";
			this.accessType=EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_ALL;
			this.entryDate="";

			this.selectedIndex= new String[0];
				
			this.setPatCrNo("");
			this.setPatFirstName("");
			this.setPatMiddleName("");
			this.setPatLastName("");
			this.setPatGender("");
			this.setPatPrimaryCat("");
			this.setPatPrimaryCatCode("");
			this.setDepartmentUnitCode("");
			this.setOrderBy("");
			this.setMode("");
			this.setHmode("");
			this.setPatChoice("");
			this.setSnomedPTRemarks(null);
			this.setSnomedCIdRemarks(null);
			this.setRemarks(null);
		}

	    
	    
		
	    
	    
	public String getClinicalDocCompSelectJSON() {
			return clinicalDocCompSelectJSON;
		}

		public void setClinicalDocCompSelectJSON(
				String clinicalDocCompSelectJSON) {
			this.clinicalDocCompSelectJSON = clinicalDocCompSelectJSON;
		}

	public String getClinicalSectionCompJSON() {
			return clinicalSectionCompJSON;
		}

		public void setClinicalSectionCompJSON(String clinicalSectionCompJSON) {
			this.clinicalSectionCompJSON = clinicalSectionCompJSON;
		}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getPatCategoryCode() {
		return patCategoryCode;
	}

	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}

	public String getDeskType() {
		return deskType;
	}

	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	
	    public String getDocumentId() {
			return documentId;
		}

		public void setDocumentId(String documentId) {
			this.documentId = documentId;
		}

		public String getSerialNo() {
			return serialNo;
		}

		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
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

	
	
	public String getSelectedPatCrNo()
	{
		return selectedPatCrNo;
	}

	public void setSelectedPatCrNo(String selectedPatCrNo)
	{
		this.selectedPatCrNo = selectedPatCrNo;
	}

	public String getEpisodeIsOpen()
	{
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen)
	{
		this.episodeIsOpen = episodeIsOpen;
	}

	public String getEpisodeNextVisitDate()
	{
		return episodeNextVisitDate;
	}

	public void setEpisodeNextVisitDate(String episodeNextVisitDate)
	{
		this.episodeNextVisitDate = episodeNextVisitDate;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getPatGenderAge()
	{
		return patGenderAge;
	}

	public void setPatGenderAge(String patGenderAge)
	{
		this.patGenderAge = patGenderAge;
	}

	public String getMode()
	{
		return mode;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getPatFirstName()
	{
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	public String getPatGender()
	{
		return patGender;
	}

	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	public String getPatLastName()
	{
		return patLastName;
	}

	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	public String getPatPrimaryCat()
	{
		return patPrimaryCat;
	}

	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getQueNo()
	{
		return queNo;
	}

	public void setQueNo(String queNo)
	{
		this.queNo = queNo;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	
	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getPatChoice()
	{
		return patChoice;
	}

	public void setPatChoice(String patChoice)
	{
		this.patChoice = patChoice;
	}

	

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getShowRommList() {
		return showRommList;
	}

	public void setShowRommList(String showRommList) {
		this.showRommList = showRommList;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getIsCasualty() {
		return isCasualty;
	}

	public void setIsCasualty(String isCasualty) {
		this.isCasualty = isCasualty;
	}

	


	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	
		
	public String[] getSnomedCIdRemarks() {
		return snomedCIdRemarks;
	}

	public void setSnomedCIdRemarks(String[] snomedCIdRemarks) {
		this.snomedCIdRemarks = snomedCIdRemarks;
	}

	public String[] getSnomedPTRemarks() {
		return snomedPTRemarks;
	}

	public void setSnomedPTRemarks(String[] snomedPTRemarks) {
		this.snomedPTRemarks = snomedPTRemarks;
	}

	

	public String getDeptUnit() {
		return deptUnit;
	}

	public void setDeptUnit(String deptUnit) {
		this.deptUnit = deptUnit;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	public String getSnomdPTRemarks() {
		return snomdPTRemarks;
	}

	public void setSnomdPTRemarks(String snomdPTRemarks) {
		this.snomdPTRemarks = snomdPTRemarks;
	}

	public String getSnomdCIdRemarks() {
		return snomdCIdRemarks;
	}

	public void setSnomdCIdRemarks(String snomdCIdRemarks) {
		this.snomdCIdRemarks = snomdCIdRemarks;
	}

	
	public String getDiagnosisCount() {
		return diagnosisCount;
	}

	public void setDiagnosisCount(String diagnosisCount) {
		this.diagnosisCount = diagnosisCount;
	}

	public String getAdvisedBy() {
		return advisedBy;
	}

	public void setAdvisedBy(String advisedBy) {
		this.advisedBy = advisedBy;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	

	public String[] getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String[] selectedIndex) {
		this.selectedIndex = selectedIndex;
	}



	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
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

	public String getClinicalSecCompCode() {
		return clinicalSecCompCode;
	}

	public void setClinicalSecCompCode(String clinicalSecCompCode) {
		this.clinicalSecCompCode = clinicalSecCompCode;
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

	public String getFlagPreview() {
		return flagPreview;
	}

	public void setFlagPreview(String flagPreview) {
		this.flagPreview = flagPreview;
	}




	public String getDocumentGenerationType() {
		return documentGenerationType;
	}




	public void setDocumentGenerationType(String documentGenerationType) {
		this.documentGenerationType = documentGenerationType;
	}




	public String getDocumentHeader() {
		return documentHeader;
	}




	public void setDocumentHeader(String documentHeader) {
		this.documentHeader = documentHeader;
	}




	public String getDocumentGenerationMode() {
		return documentGenerationMode;
	}




	public void setDocumentGenerationMode(String documentGenerationMode) {
		this.documentGenerationMode = documentGenerationMode;
	}

	public String getDocumentHTML() {
		return documentHTML;
	}

	public void setDocumentHTML(String documentHTML) {
		this.documentHTML = documentHTML;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getSelecteddocumentId() {
		return selecteddocumentId;
	}

	public void setSelecteddocumentId(String selecteddocumentId) {
		this.selecteddocumentId = selecteddocumentId;
	}

	public String getFlagDefaultSelectionOnload() {
		return flagDefaultSelectionOnload;
	}

	public String getClinicalDocCompSelectJSONModify() {
		return clinicalDocCompSelectJSONModify;
	}

	public void setClinicalDocCompSelectJSONModify(
			String clinicalDocCompSelectJSONModify) {
		this.clinicalDocCompSelectJSONModify = clinicalDocCompSelectJSONModify;
	}

	public String getDocumentTypeDesc() {
		return documentTypeDesc;
	}

	public void setDocumentTypeDesc(String documentTypeDesc) {
		this.documentTypeDesc = documentTypeDesc;
	}

	public String getClinicalDocumentMode() {
		return clinicalDocumentMode;
	}

	public void setClinicalDocumentMode(String clinicalDocumentMode) {
		this.clinicalDocumentMode = clinicalDocumentMode;
	}

	public String getHtmlDocHeader() {
		return htmlDocHeader;
	}

	public void setHtmlDocHeader(String htmlDocHeader) {
		this.htmlDocHeader = htmlDocHeader;
	}

	

	
	

	
	
}
