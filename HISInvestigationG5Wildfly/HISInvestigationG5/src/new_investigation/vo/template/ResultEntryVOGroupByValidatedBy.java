package new_investigation.vo.template;

import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;
//import new_investigation.InvestigationStaticConfigurator;

import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.template.ResultEntryVO;

import java.util.List;
import java.util.Map;
//import java.util.logging.Level;




import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ResultEntryVOGroupByValidatedBy extends ValueObject {
	
	private String patCRNo;
	private String patepisodecode;
	 
	private String departmentcode;
	private String patdeptunitcode;
	private String patdeptunit;
	private String department;
	private String room;
	private String patepisodetypecode;
	private String patvisittypecode;
	private String hospitalcode;
	private String patmlcno;
	private String diagnosis;
	
	private String patVisitNo;
	
	
	
	private String dignosisName;
	
	private String fromDate="";
	 
	 
	private String toDate="";
   
	private String labCode;
	private String patLabName;
	private String patReqDate;
	private String patName;
	private String patAge;
	private String patStatus;
	private String patUnitName;
	
	private String reqNo;
	private String selectedCheckbox;
	private String isPatDetailPage;
	private String chkSamplePatient;
	private String patCategory;
	private String patWardName;
	private String patRoomName;
	private String patBedName;
	private String patOrderByDoc;
	public String patPuk;
	public String patReqNo;
	private String patVisitDat;
	private String billDetail;
	private String labName;
	private String sampleCode;
	private String sampleQnty;
	private String defaultUOMCode;
	private String defaultContainerCode;
	private String priority;
	private String labNoConfiguration;
	private String accepted;
	private String rejectionAction;
	private String rejectionId;
	private String rejectionReason;
	private String mobileNo;
	private String emailId;
	private String labCodeSamp;
	private String address;
	private String reqDtlStatus;
	private String billNo;
	private String consQty;
	private String tempPatCRNo;
	private String TempDailyLabNo;
	private String strDailyLabNo;
	private String labNoFormat;
	private String fromSeries;
	private String toSeries;
	private String runningLabNo;
	private String entryDate;
	private String initType;
	private String noOfSeqDigit;
	
	
	
	
	private String testName="";
	private String testCode;
	private String laboratoryName="";
	private String laboratoryCode;
	private String departmentName="";
	private String resultValidatedBy="";
	private String patType;
	private String patGender="";
	private String patGenderShortName="";
	private String requisitionDNo;
	private String requisitionNo;
	private String requisitionTypeCode;
	private String requisitionTypeName;
	private String sampleNo;
	private String accessionNo;
	private String patEpisodeCode;
	private String patSampleID;
	private String patDeptUnit="";
	private String detpUnitCode;
	private String wardName="";
	private String bedName="";
	private String roomName="";
	private String visitDate="";
	
	private String referredHospitalName;
	private String referredHospitalCode;
	private String labNo="";
	private String requisitionDate;
	private String priorityCode;
	private String priorityName;
	private String priorityColor;
	private String patAdvisedBy;
	private String resultEnteredBy="";
	private String clinicianName;
	private String clinicianCode;
	private String isconfidential;
	private String sessionId;
	
	private String patAgeSex="";
	private String opdorWard="";
	
	private String validatedBy="";
	private Map<String,String> sampleNameListMap;
	private List<String> sampleNameList;
	private Map<String,String> labNoListMap;
	private List<String> labNoList;
	private List<ResultEntryVO> resultEntryVOListValidatedBy;
	
	
	private String pdfFileCode;	
	private String pdfFileName;	
	private String PdfFtpUrl;
	private String pdfFilePassword;
	private String groupCode;
	private String groupType;
	
	private String   groupTemplateString;
	private Document groupTemplateDocument;
	private String   printingTemplateString;
	private Document printingTemplateDocument;
	private String   groupName;
	
	private String sampleName;
	
	private String isFooterRequired="Y";
	private String footer;
	private String footerHeight="0";
	private String footerWidth=InvestigationConfig.pagewidthprinting;
	
	private String isHeaderRequired="Y";
	private String header;
	private String headerHeight="0";
	private String headerWidth=InvestigationConfig.pagewidthprinting;
	private String pageSize=InvestigationConfig.pagewidthheight;
	
	private String printedWithTemplateID;
	private String printingType;
	private Map<String,String> groupMap; // contains the Map of different group present in the GroupValidatedByVO 
	private String currentDiagnosis; //add for Change Request 29 dec 2010 :change in Report Header Add Diagnosis.
	private String userSampleNo; //add for Change Request 31 jan 2011 :change in Report Header Add Diagnosis.
	private String patAdmissionNo; //for add admission no in pdf report.
	private boolean hasSpecialGroupTemplate;
	
	private String resultValidationDate;
	private String resultEntryDate;
	private List<Node> images;
	
	
	private String validationPendingCount="0";
	private String validatedCount="0";
	private String validationTotalCount="0";
	private String resultEnterdCount="0";
	private String printedTotalCount="0";
	private String raisedTotalCount="0";
	
	private String validatedDate="";
	private String sampleCollectionOfflineRemarks;
	
	private String finalRemarkReqd;
	private String finalRemarkValue[];
	private String deptReport;
	
	public String getValidatedDate() {
		return validatedDate;
	}
	public void setValidatedDate(String validatedDate) {
		this.validatedDate = validatedDate;
	}
	public String getRaisedTotalCount() {
		return raisedTotalCount;
	}
	public void setRaisedTotalCount(String raisedTotalCount) {
		this.raisedTotalCount = raisedTotalCount;
	}
	public String getPatAdmissionNo() {
		return patAdmissionNo;
	}
	public void setPatAdmissionNo(String patAdmissionNo) {
		this.patAdmissionNo = patAdmissionNo;
	}
	public String getUserSampleNo() {
		return userSampleNo;
	}
	public void setUserSampleNo(String userSampleNo) {
		this.userSampleNo = userSampleNo;
	}
	public String getCurrentDiagnosis() {
		return currentDiagnosis;
	}
	public void setCurrentDiagnosis(String currentDiagnosis) {
		this.currentDiagnosis = currentDiagnosis;
	}
	public Map<String, String> getGroupMap() {
		return groupMap;
	}
	public void setGroupMap(Map<String, String> groupMap) {
		this.groupMap = groupMap;
	}
	public String getPrintedWithTemplateID() {
		return printedWithTemplateID;
	}
	public void setPrintedWithTemplateID(String printedWithTemplateID) {
		this.printedWithTemplateID = printedWithTemplateID;
	}
	public String getPrintingType() {
		return printingType;
	}
	public void setPrintingType(String printingType) {
		this.printingType = printingType;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getPdfFileCode() {
		return pdfFileCode;
	}
	public void setPdfFileCode(String pdfFileCode) {
		this.pdfFileCode = pdfFileCode;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public String getPdfFtpUrl() {
		return PdfFtpUrl;
	}
	public void setPdfFtpUrl(String pdfFtpUrl) {
		PdfFtpUrl = pdfFtpUrl;
	}
	public String getPdfFilePassword() {
		return pdfFilePassword;
	}
	public void setPdfFilePassword(String pdfFilePassword) {
		this.pdfFilePassword = pdfFilePassword;
	}
	public String getIsconfidential() {
		return isconfidential;
	}
	public void setIsconfidential(String isconfidential) {
		this.isconfidential = isconfidential;
	}
	public String getClinicianName() {
		return clinicianName;
	}
	public void setClinicianName(String clinicianName) {
		this.clinicianName = clinicianName;
	}
	public String getClinicianCode() {
		return clinicianCode;
	}
	public void setClinicianCode(String clinicianCode) {
		this.clinicianCode = clinicianCode;
	}
	public String getResultEnteredBy() {
		return resultEnteredBy;
	}
	public void setResultEnteredBy(String resultEnteredBy) {
		this.resultEnteredBy = resultEnteredBy;
	}
	
	public String getValidatedBy() {
		return validatedBy;
	}
	public void setValidatedBy(String validatedBy) {
		this.validatedBy = validatedBy;
	}
	public List<String>  getSampleNameList() {
		return sampleNameList;
	}
	public void setSampleNameList(List<String>  sampleNameList) {
		this.sampleNameList = sampleNameList;
	}
	public List<ResultEntryVO> getResultEntryVOListValidatedBy() {
		return resultEntryVOListValidatedBy;
	}
	public void setResultEntryVOListValidatedBy(
			List<ResultEntryVO> resultEntryVOListValidatedBy) {
		this.resultEntryVOListValidatedBy = resultEntryVOListValidatedBy;
	}
	public String getPatDeptUnit() {
		return patDeptUnit;
	}
	public void setPatDeptUnit(String patDeptUnit) {
		this.patDeptUnit = patDeptUnit;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	public String getPatType() {
		return patType;
	}
	public void setPatType(String patType) {
		this.patType = patType;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderShortName() {
		return patGenderShortName;
	}
	public void setPatGenderShortName(String patGenderShortName) {
		this.patGenderShortName = patGenderShortName;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getRequisitionTypeCode() {
		return requisitionTypeCode;
	}
	public void setRequisitionTypeCode(String requisitionTypeCode) {
		this.requisitionTypeCode = requisitionTypeCode;
	}
	public String getRequisitionTypeName() {
		return requisitionTypeName;
	}
	public void setRequisitionTypeName(String requisitionTypeName) {
		this.requisitionTypeName = requisitionTypeName;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getAccessionNo() {
		return accessionNo;
	}
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	public String getPatEpisodeCode() {
		return patEpisodeCode;
	}
	public void setPatEpisodeCode(String patEpisodeCode) {
		this.patEpisodeCode = patEpisodeCode;
	}
	public String getPatSampleID() {
		return patSampleID;
	}
	public void setPatSampleID(String patSampleID) {
		this.patSampleID = patSampleID;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	public String getTestName() {
		
		if(this.getResultEntryVOListValidatedBy()!=null && this.getResultEntryVOListValidatedBy().get(0).getGroupCode()!=null)
		{
			if(this.getResultEntryVOListValidatedBy()!=null && this.getResultEntryVOListValidatedBy().size()==1)
			{	
				this.testName=this.getResultEntryVOListValidatedBy().get(0).getTestName();
				
			}
			else
			{
			
				this.testName=this.getResultEntryVOListValidatedBy().get(0).getGroupName();
			}
		}
		else
			{
				
				if(this.getResultEntryVOListValidatedBy()!=null && this.getResultEntryVOListValidatedBy().size()==1)
				{	
					this.testName=this.getResultEntryVOListValidatedBy().get(0).getTestName();
					
				}
				else
				{
					int i=0;
					if(this.getResultEntryVOListValidatedBy()!=null)
					{
					for(ResultEntryVO entryVO: this.getResultEntryVOListValidatedBy())
					{
						if(i==0)
						{
						this.testName=entryVO.getTestName();
						}
						else
						{
							this.testName+=","+entryVO.getTestName();
						}
						
						i++;
					}
					}
				}
				
				
			
			}
		
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLaboratoryName() {
		return laboratoryName;
	}
	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	public String getLaboratoryCode() {
		return laboratoryCode;
	}
	public void setLaboratoryCode(String laboratoryCode) {
		this.laboratoryCode = laboratoryCode;
	}
	public String getReferredHospitalName() {
		return referredHospitalName;
	}
	public void setReferredHospitalName(String referredHospitalName) {
		this.referredHospitalName = referredHospitalName;
	}
	public String getReferredHospitalCode() {
		return referredHospitalCode;
	}
	public void setReferredHospitalCode(String referredHospitalCode) {
		this.referredHospitalCode = referredHospitalCode;
	}
	public String getLabNo() {
		return labNo;
	}
	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getPriorityCode() {
		priorityCode="0";
		if(this.getResultEntryVOListValidatedBy()!=null)
		{
			for(ResultEntryVO resultEntryVO:this.getResultEntryVOListValidatedBy())
			{
				if(Integer.parseInt(resultEntryVO.getPriorityCode())>Integer.parseInt(priorityCode))
				{
					priorityCode=resultEntryVO.getPriorityCode();
				}
			}
			
		}
		
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getPriorityName() {
		return priorityName;
	}
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	public String getResultValidatedBy() {
		return resultValidatedBy;
	}
	public void setResultValidatedBy(String resultValidatedBy) {
		this.resultValidatedBy = resultValidatedBy;
	}
	public Map<String, String> getSampleNameListMap() {
		return sampleNameListMap;
	}
	public void setSampleNameListMap(Map<String, String> sampleNameListMap) {
		this.sampleNameListMap = sampleNameListMap;
	}
	public String getPatAdvisedBy() {
		return patAdvisedBy;
	}
	public void setPatAdvisedBy(String patAdvisedBy) {
		this.patAdvisedBy = patAdvisedBy;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGroupTemplateString() {
		return groupTemplateString;
	}
	public void setGroupTemplateString(String groupTemplateString) {
		this.groupTemplateString = groupTemplateString;
	}
	public Document getGroupTemplateDocument() {
		return groupTemplateDocument;
	}
	public void setGroupTemplateDocument(Document groupTemplateDocument) {
		this.groupTemplateDocument = groupTemplateDocument;
	}
public String getPriorityColor() {
		
		String pColor=this.priorityColor;
		if(priorityCode!=null)
		{
			 pColor=InvestigationConfig.PRIORITY_NORMALCOLOR;
		if(priorityCode.equals("1"))
			pColor=InvestigationConfig.PRIORITY_HIGHCOLOR;
		else if(priorityCode.equals("0"))
			pColor=InvestigationConfig.PRIORITY_NORMALCOLOR;
		else
			pColor=InvestigationConfig.PRIORITY_LOWCOLOR;
		}
		
		return pColor;
	}
	public void setPriorityColor(String priorityColor) {
		this.priorityColor = priorityColor;
	}
	public String getSessionId() {
		
		if(this.getResultEntryVOListValidatedBy()!=null && this.getResultEntryVOListValidatedBy().size()==0)
		{	
			this.sessionId=this.getResultEntryVOListValidatedBy().get(0).getSessionId();
			
		}
			
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getDetpUnitCode() {
		return detpUnitCode;
	}
	public void setDetpUnitCode(String detpUnitCode) {
		this.detpUnitCode = detpUnitCode;
	}
	
	String isGroupUpdateble="false";


	public String getIsGroupUpdateble() {
		return isGroupUpdateble;
	}
	public void setIsGroupUpdateble(String isGroupUpdateble) {
		this.isGroupUpdateble = isGroupUpdateble;
	}
	public String getRequisitionNo() {
				return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getPatAgeSex() {
		return this.getPatAge()+"/"+this.getPatGenderShortName();
	}
	public void setPatAgeSex(String patAgeSex) {
		this.patAgeSex = this.getPatAge()+"/"+this.getPatGenderShortName();
	}
	public String getOpdorWard() {
		if(this.getWardName()!=null && (this.getWardName().equals("")==false))
			opdorWard=this.getWardName();
		else
			opdorWard=this.getPatDeptUnit();
		
		return opdorWard;
	}
	public void setOpdorWard(String opdorWard) {
		this.opdorWard = opdorWard;
	}
	public String getFooterHeight() {
		return footerHeight;
	}
	public void setFooterHeight(String footerHeight) {
		this.footerHeight = footerHeight;
	}
	public String getHeader() {
		//LOGGER_INV.log(Level.INFO,header);
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getHeaderHeight() {
		return headerHeight;
	}
	public void setHeaderHeight(String headerHeight) {
		this.headerHeight = headerHeight;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getFooterWidth() {
		return footerWidth;
	}
	public void setFooterWidth(String footerWidth) {
		this.footerWidth = footerWidth;
	}
	public String getHeaderWidth() {
		return headerWidth;
	}
	public void setHeaderWidth(String headerWidth) {
		this.headerWidth = headerWidth;
	}
	public String getIsFooterRequired() {
		return isFooterRequired;
	}
	public void setIsFooterRequired(String isFooterRequired) {
		this.isFooterRequired = isFooterRequired;
	}
	public String getIsHeaderRequired() {
		return isHeaderRequired;
	}
	public void setIsHeaderRequired(String isHeaderRequired) {
		this.isHeaderRequired = isHeaderRequired;
	}
	public Map<String, String> getLabNoListMap() {
		return labNoListMap;
	}
	public void setLabNoListMap(Map<String, String> labNoListMap) {
		this.labNoListMap = labNoListMap;
	}
	public List<String> getLabNoList() {
		return labNoList;
	}
	public void setLabNoList(List<String> labNoList) {
		this.labNoList = labNoList;
	}
	public boolean isHasSpecialGroupTemplate() {
		return hasSpecialGroupTemplate;
	}
	public void setHasSpecialGroupTemplate(boolean hasSpecialGroupTemplate) {
		this.hasSpecialGroupTemplate = hasSpecialGroupTemplate;
	}
	public String getPrintingTemplateString() {
		return printingTemplateString;
	}
	public void setPrintingTemplateString(String printingTemplateString) {
		this.printingTemplateString = printingTemplateString;
	}
	public Document getPrintingTemplateDocument() {
		return printingTemplateDocument;
	}
	public void setPrintingTemplateDocument(Document printingTemplateDocument) {
		this.printingTemplateDocument = printingTemplateDocument;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getResultValidationDate() {
		return resultValidationDate;
	}
	public void setResultValidationDate(String resultValidationDate) {
		this.resultValidationDate = resultValidationDate;
	}
	public String getResultEntryDate() {
		return resultEntryDate;
	}
	public void setResultEntryDate(String resultEntryDate) {
		this.resultEntryDate = resultEntryDate;
	}
	public List<Node> getImages() {
		return images;
	}
	public void setImages(List<Node> images) {
		this.images = images;
	}
	public String getValidationPendingCount() {
		return validationPendingCount;
	}
	public void setValidationPendingCount(String validationPendingCount) {
		this.validationPendingCount = validationPendingCount;
	}
	public String getValidatedCount() {
		return validatedCount;
	}
	public void setValidatedCount(String validatedCount) {
		this.validatedCount = validatedCount;
	}
	public String getValidationTotalCount() {
		return validationTotalCount;
	}
	public void setValidationTotalCount(String validationTotalCount) {
		this.validationTotalCount = validationTotalCount;
	}
	public String getResultEnterdCount() {
		return resultEnterdCount;
	}
	public void setResultEnterdCount(String resultEnterdCount) {
		this.resultEnterdCount = resultEnterdCount;
	}
	public String getPrintedTotalCount() {
		return printedTotalCount;
	}
	public void setPrintedTotalCount(String printedTotalCount) {
		this.printedTotalCount = printedTotalCount;
	}
	public String getSampleCollectionOfflineRemarks() {
		return sampleCollectionOfflineRemarks;
	}
	public void setSampleCollectionOfflineRemarks(
			String sampleCollectionOfflineRemarks) {
		this.sampleCollectionOfflineRemarks = sampleCollectionOfflineRemarks;
	}
	public String getPatepisodecode() {
		return patepisodecode;
	}
	public void setPatepisodecode(String patepisodecode) {
		this.patepisodecode = patepisodecode;
	}
	public String getDepartmentcode() {
		return departmentcode;
	}
	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}
	public String getPatdeptunitcode() {
		return patdeptunitcode;
	}
	public void setPatdeptunitcode(String patdeptunitcode) {
		this.patdeptunitcode = patdeptunitcode;
	}
	public String getPatdeptunit() {
		return patdeptunit;
	}
	public void setPatdeptunit(String patdeptunit) {
		this.patdeptunit = patdeptunit;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getPatepisodetypecode() {
		return patepisodetypecode;
	}
	public void setPatepisodetypecode(String patepisodetypecode) {
		this.patepisodetypecode = patepisodetypecode;
	}
	public String getPatvisittypecode() {
		return patvisittypecode;
	}
	public void setPatvisittypecode(String patvisittypecode) {
		this.patvisittypecode = patvisittypecode;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getPatmlcno() {
		return patmlcno;
	}
	public void setPatmlcno(String patmlcno) {
		this.patmlcno = patmlcno;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDignosisName() {
		return dignosisName;
	}
	public void setDignosisName(String dignosisName) {
		this.dignosisName = dignosisName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getPatLabName() {
		return patLabName;
	}
	public void setPatLabName(String patLabName) {
		this.patLabName = patLabName;
	}
	public String getPatReqDate() {
		return patReqDate;
	}
	public void setPatReqDate(String patReqDate) {
		this.patReqDate = patReqDate;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getPatUnitName() {
		return patUnitName;
	}
	public void setPatUnitName(String patUnitName) {
		this.patUnitName = patUnitName;
	}
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}
	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
	}
	public String getIsPatDetailPage() {
		return isPatDetailPage;
	}
	public void setIsPatDetailPage(String isPatDetailPage) {
		this.isPatDetailPage = isPatDetailPage;
	}
	public String getChkSamplePatient() {
		return chkSamplePatient;
	}
	public void setChkSamplePatient(String chkSamplePatient) {
		this.chkSamplePatient = chkSamplePatient;
	}
	public String getPatCategory() {
		return patCategory;
	}
	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}
	public String getPatWardName() {
		return patWardName;
	}
	public void setPatWardName(String patWardName) {
		this.patWardName = patWardName;
	}
	public String getPatRoomName() {
		return patRoomName;
	}
	public void setPatRoomName(String patRoomName) {
		this.patRoomName = patRoomName;
	}
	public String getPatBedName() {
		return patBedName;
	}
	public void setPatBedName(String patBedName) {
		this.patBedName = patBedName;
	}
	public String getPatOrderByDoc() {
		return patOrderByDoc;
	}
	public void setPatOrderByDoc(String patOrderByDoc) {
		this.patOrderByDoc = patOrderByDoc;
	}
	public String getPatPuk() {
		return patPuk;
	}
	public void setPatPuk(String patPuk) {
		this.patPuk = patPuk;
	}
	public String getPatReqNo() {
		return patReqNo;
	}
	public void setPatReqNo(String patReqNo) {
		this.patReqNo = patReqNo;
	}
	public String getPatVisitDat() {
		return patVisitDat;
	}
	public void setPatVisitDat(String patVisitDat) {
		this.patVisitDat = patVisitDat;
	}
	public String getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(String billDetail) {
		this.billDetail = billDetail;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getSampleQnty() {
		return sampleQnty;
	}
	public void setSampleQnty(String sampleQnty) {
		this.sampleQnty = sampleQnty;
	}
	public String getDefaultUOMCode() {
		return defaultUOMCode;
	}
	public void setDefaultUOMCode(String defaultUOMCode) {
		this.defaultUOMCode = defaultUOMCode;
	}
	public String getDefaultContainerCode() {
		return defaultContainerCode;
	}
	public void setDefaultContainerCode(String defaultContainerCode) {
		this.defaultContainerCode = defaultContainerCode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLabNoConfiguration() {
		return labNoConfiguration;
	}
	public void setLabNoConfiguration(String labNoConfiguration) {
		this.labNoConfiguration = labNoConfiguration;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getRejectionAction() {
		return rejectionAction;
	}
	public void setRejectionAction(String rejectionAction) {
		this.rejectionAction = rejectionAction;
	}
	public String getRejectionId() {
		return rejectionId;
	}
	public void setRejectionId(String rejectionId) {
		this.rejectionId = rejectionId;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLabCodeSamp() {
		return labCodeSamp;
	}
	public void setLabCodeSamp(String labCodeSamp) {
		this.labCodeSamp = labCodeSamp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReqDtlStatus() {
		return reqDtlStatus;
	}
	public void setReqDtlStatus(String reqDtlStatus) {
		this.reqDtlStatus = reqDtlStatus;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getConsQty() {
		return consQty;
	}
	public void setConsQty(String consQty) {
		this.consQty = consQty;
	}
	public String getTempPatCRNo() {
		return tempPatCRNo;
	}
	public void setTempPatCRNo(String tempPatCRNo) {
		this.tempPatCRNo = tempPatCRNo;
	}
	public String getTempDailyLabNo() {
		return TempDailyLabNo;
	}
	public void setTempDailyLabNo(String tempDailyLabNo) {
		TempDailyLabNo = tempDailyLabNo;
	}
	public String getStrDailyLabNo() {
		return strDailyLabNo;
	}
	public void setStrDailyLabNo(String strDailyLabNo) {
		this.strDailyLabNo = strDailyLabNo;
	}
	public String getLabNoFormat() {
		return labNoFormat;
	}
	public void setLabNoFormat(String labNoFormat) {
		this.labNoFormat = labNoFormat;
	}
	public String getFromSeries() {
		return fromSeries;
	}
	public void setFromSeries(String fromSeries) {
		this.fromSeries = fromSeries;
	}
	public String getToSeries() {
		return toSeries;
	}
	public void setToSeries(String toSeries) {
		this.toSeries = toSeries;
	}
	public String getRunningLabNo() {
		return runningLabNo;
	}
	public void setRunningLabNo(String runningLabNo) {
		this.runningLabNo = runningLabNo;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getInitType() {
		return initType;
	}
	public void setInitType(String initType) {
		this.initType = initType;
	}
	public String getNoOfSeqDigit() {
		return noOfSeqDigit;
	}
	public void setNoOfSeqDigit(String noOfSeqDigit) {
		this.noOfSeqDigit = noOfSeqDigit;
	}
	public String getFinalRemarkReqd() {
		return finalRemarkReqd;
	}
	public void setFinalRemarkReqd(String finalRemarkReqd) {
		this.finalRemarkReqd = finalRemarkReqd;
	}
	public String[] getFinalRemarkValue() {
		return finalRemarkValue;
	}
	public void setFinalRemarkValue(String[] finalRemarkValue) {
		this.finalRemarkValue = finalRemarkValue;
	}
	public String getDeptReport() {
		return deptReport;
	}
	public void setDeptReport(String deptReport) {
		this.deptReport = deptReport;
	}
	
	
	
	
}
