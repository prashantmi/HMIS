package hisglobal.vo;

import hisglobal.utility.Entry;
import investigation.vo.AntiBioticVOClass;
import investigation.vo.ImageDetailVO;
import investigation.vo.SiteVO;
import investigation.vo.TriStringObject;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

public class AllLabTestRegisterVO extends ValueObject 
{
	private String patCRNo;
	private String patType;
	private String patName;
	private String patAge;
	private String patGender;
	private String patGenderShortName;
	private String requisitionDNo;
	private String requisitionTypeCode;
	private String requisitionTypeName;
	private String sampleNo;
	private String accessionNo;
	private String patEpisodeCode;
	private String patSampleID;
	private String patVisitNo;
	private String testName;
	private String testCode;
	private String laboratoryName;
	private String laboratoryCode;
	private String referredHospitalName;
	private String referredHospitalCode;
	private String labNo;
	private List<Entry> mandatoryInfoDtl;//test mandatory Code,test mandatory range value
	private Document testDocument;
	private String templateDocumentString;
	private String requisitionDate;
	private String priorityCode;
	private String priorityName;
	private String sampleGroupCode;
	private String isTestReady;
	private String packingListNo;
	private String sessionId;
	private String isMultiSessionRegrouped;
	private String testType;
	private String resultValidatedBy;
	private String validatedBy;
	private List<TriStringObject> associatedWorkOrders;
	private String isTestMultiSession;
	private String labName;
	private String cannedFileName;
	private String cannedFileLocation;
	private String selectedFile;
	private String cannedFileContent;
	private String patDeptUnit;
	private String wardName;
	private String bedName;
	private String roomName;
	private String visitDate;
	private String sampleName;
	private String isPartialTansmission;
	private String patAdvisedBy;
	private String testStatusCode;
	private String testStatus;
	private String isResultViewingReady;
	private String resultEnteredBy="";
	private String priorityColor="";
	private String isRequisitionFormRequired="";//feild from gblt_test_mst
	private String detpUnitCode="";
	private String isSampleCollectionFormRequired="";
	private List<SiteVO> originalSiteDiagnosisList;
	private List<SiteVO> siteDiagnosisList;
	private List<ImageDetailVO> uploadedImageList;
	private List<ImageDetailVO> editedImageList;
	private String clinicianName;
	
	private Map<String,List<AntiBioticVOClass>> originalAntibioticsList;
	private Map<String,List<AntiBioticVOClass>> antibioticsList;
	private String isconfidential;

	private String diagnosis;
	private String sampleCollectiondate;
	private String sampleAcceptancedate;
	private String dispatchdate;
	private String headOfDept;
	private String designation;
	private String wardOpd;
	private String sampleQty; 
	private String amtpaid;
	private String billNo;
	private List<Entry> parameters;
	private String groupName;
	private List<Entry> parameter;
	private String requisitionNo;
	
	public String getSampleQty() {
		return sampleQty;
	}
	public void setSampleQty(String sampleQty) {
		this.sampleQty = sampleQty;
	}
	public String getSampleCollectiondate() {
		return sampleCollectiondate;
	}
	public void setSampleCollectiondate(String sampleCollectiondate) {
		this.sampleCollectiondate = sampleCollectiondate;
	}
	public String getSampleAcceptancedate() {
		return sampleAcceptancedate;
	}
	public void setSampleAcceptancedate(String sampleAcceptancedate) {
		this.sampleAcceptancedate = sampleAcceptancedate;
	}
	public String getDispatchdate() {
		return dispatchdate;
	}
	public void setDispatchdate(String dispatchdate) {
		this.dispatchdate = dispatchdate;
	}
	public String getHeadOfDept() {
		return headOfDept;
	}
	public void setHeadOfDept(String headOfDept) {
		this.headOfDept = headOfDept;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
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
	public List<Entry> getMandatoryInfoDtl() {
		return mandatoryInfoDtl;
	}
	public void setMandatoryInfoDtl(List<Entry> mandatoryInfoDtl) {
		this.mandatoryInfoDtl = mandatoryInfoDtl;
	}
	public Document getTestDocument() {
		return testDocument;
	}
	public void setTestDocument(Document testDocument) {
		this.testDocument = testDocument;
	}
	public String getTemplateDocumentString() {
		return templateDocumentString;
	}
	public void setTemplateDocumentString(String templateDocumentString) {
		this.templateDocumentString = templateDocumentString;
	}
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getPriorityCode() {
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
	public String getSampleGroupCode() {
		return sampleGroupCode;
	}
	public void setSampleGroupCode(String sampleGroupCode) {
		this.sampleGroupCode = sampleGroupCode;
	}
	public String getIsTestReady() {
		return isTestReady;
	}
	public void setIsTestReady(String isTestReady) {
		this.isTestReady = isTestReady;
	}
	public String getPackingListNo() {
		return packingListNo;
	}
	public void setPackingListNo(String packingListNo) {
		this.packingListNo = packingListNo;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIsMultiSessionRegrouped() {
		return isMultiSessionRegrouped;
	}
	public void setIsMultiSessionRegrouped(String isMultiSessionRegrouped) {
		this.isMultiSessionRegrouped = isMultiSessionRegrouped;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getResultValidatedBy() {
		return resultValidatedBy;
	}
	public void setResultValidatedBy(String resultValidatedBy) {
		this.resultValidatedBy = resultValidatedBy;
	}
	public String getValidatedBy() {
		return validatedBy;
	}
	public void setValidatedBy(String validatedBy) {
		this.validatedBy = validatedBy;
	}
	public List<TriStringObject> getAssociatedWorkOrders() {
		return associatedWorkOrders;
	}
	public void setAssociatedWorkOrders(List<TriStringObject> associatedWorkOrders) {
		this.associatedWorkOrders = associatedWorkOrders;
	}
	public String getIsTestMultiSession() {
		return isTestMultiSession;
	}
	public void setIsTestMultiSession(String isTestMultiSession) {
		this.isTestMultiSession = isTestMultiSession;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getCannedFileName() {
		return cannedFileName;
	}
	public void setCannedFileName(String cannedFileName) {
		this.cannedFileName = cannedFileName;
	}
	public String getCannedFileLocation() {
		return cannedFileLocation;
	}
	public void setCannedFileLocation(String cannedFileLocation) {
		this.cannedFileLocation = cannedFileLocation;
	}
	public String getSelectedFile() {
		return selectedFile;
	}
	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}
	public String getCannedFileContent() {
		return cannedFileContent;
	}
	public void setCannedFileContent(String cannedFileContent) {
		this.cannedFileContent = cannedFileContent;
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
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getIsPartialTansmission() {
		return isPartialTansmission;
	}
	public void setIsPartialTansmission(String isPartialTansmission) {
		this.isPartialTansmission = isPartialTansmission;
	}
	public String getPatAdvisedBy() {
		return patAdvisedBy;
	}
	public void setPatAdvisedBy(String patAdvisedBy) {
		this.patAdvisedBy = patAdvisedBy;
	}
	public String getTestStatusCode() {
		return testStatusCode;
	}
	public void setTestStatusCode(String testStatusCode) {
		this.testStatusCode = testStatusCode;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getIsResultViewingReady() {
		return isResultViewingReady;
	}
	public void setIsResultViewingReady(String isResultViewingReady) {
		this.isResultViewingReady = isResultViewingReady;
	}
	public String getResultEnteredBy() {
		return resultEnteredBy;
	}
	public void setResultEnteredBy(String resultEnteredBy) {
		this.resultEnteredBy = resultEnteredBy;
	}
	public String getPriorityColor() {
		return priorityColor;
	}
	public void setPriorityColor(String priorityColor) {
		this.priorityColor = priorityColor;
	}
	public String getIsRequisitionFormRequired() {
		return isRequisitionFormRequired;
	}
	public void setIsRequisitionFormRequired(String isRequisitionFormRequired) {
		this.isRequisitionFormRequired = isRequisitionFormRequired;
	}
	public String getDetpUnitCode() {
		return detpUnitCode;
	}
	public void setDetpUnitCode(String detpUnitCode) {
		this.detpUnitCode = detpUnitCode;
	}
	public String getIsSampleCollectionFormRequired() {
		return isSampleCollectionFormRequired;
	}
	public void setIsSampleCollectionFormRequired(
			String isSampleCollectionFormRequired) {
		this.isSampleCollectionFormRequired = isSampleCollectionFormRequired;
	}
	public List<SiteVO> getOriginalSiteDiagnosisList() {
		return originalSiteDiagnosisList;
	}
	public void setOriginalSiteDiagnosisList(List<SiteVO> originalSiteDiagnosisList) {
		this.originalSiteDiagnosisList = originalSiteDiagnosisList;
	}
	public List<SiteVO> getSiteDiagnosisList() {
		return siteDiagnosisList;
	}
	public void setSiteDiagnosisList(List<SiteVO> siteDiagnosisList) {
		this.siteDiagnosisList = siteDiagnosisList;
	}
	public List<ImageDetailVO> getUploadedImageList() {
		return uploadedImageList;
	}
	public void setUploadedImageList(List<ImageDetailVO> uploadedImageList) {
		this.uploadedImageList = uploadedImageList;
	}
	public List<ImageDetailVO> getEditedImageList() {
		return editedImageList;
	}
	public void setEditedImageList(List<ImageDetailVO> editedImageList) {
		this.editedImageList = editedImageList;
	}
	public String getClinicianName() {
		return clinicianName;
	}
	public void setClinicianName(String clinicianName) {
		this.clinicianName = clinicianName;
	}
	public Map<String, List<AntiBioticVOClass>> getOriginalAntibioticsList() {
		return originalAntibioticsList;
	}
	public void setOriginalAntibioticsList(
			Map<String, List<AntiBioticVOClass>> originalAntibioticsList) {
		this.originalAntibioticsList = originalAntibioticsList;
	}
	public Map<String, List<AntiBioticVOClass>> getAntibioticsList() {
		return antibioticsList;
	}
	public void setAntibioticsList(
			Map<String, List<AntiBioticVOClass>> antibioticsList) {
		this.antibioticsList = antibioticsList;
	}
	public String getIsconfidential() {
		return isconfidential;
	}
	public void setIsconfidential(String isconfidential) {
		this.isconfidential = isconfidential;
	}
	public String getWardOpd() {
		return wardOpd;
	}
	public void setWardOpd(String wardOpd) {
		this.wardOpd = wardOpd;
	}
	public String getAmtpaid() {
		return amtpaid;
	}
	public void setAmtpaid(String amtpaid) {
		this.amtpaid = amtpaid;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public List<Entry> getParameters() {
		return parameters;
	}
	public void setParameters(List<Entry> parameters) {
		this.parameters = parameters;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<Entry> getParameter() {
		return parameter;
	}
	public void setParameter(List<Entry> parameter) {
		this.parameter = parameter;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	
	
}
