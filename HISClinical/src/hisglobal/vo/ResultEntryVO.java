package hisglobal.vo;

import hisglobal.utility.Entry;
import investigation.InvestigationConfig;
import investigation.cacheImplementation.cachemanager.CacheLaboratoryGroupVOManager;
import investigation.cacheImplementation.cachemanager.CacheLaboratoryTestVOManager;
import investigation.cacheImplementation.cachevo.CacheLaboratoryGroupVO;
import investigation.cacheImplementation.cachevo.CacheLaboratoryTestVO;
import investigation.vo.AntiBioticVOClass;
import investigation.vo.ImageDetailVO;
import investigation.vo.OrganismVO;
import investigation.vo.PentaStringObject;
import investigation.vo.SiteVO;
import investigation.vo.TriStringObject;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

public class ResultEntryVO extends ValueObject{
	CacheLaboratoryTestVO cacheLaboratoryTestVO;
	CacheLaboratoryGroupVO cacheLaboratoryGroupVO;
	private String patCRNo;
	private String patType;
	private String patName;
	private String patAge;
	private String patGender;
	private String patGenderShortName;
	private String patEpisodeCode;
	private String patSampleID;
	private String patVisitNo;
	private String requisitionDNo;
	private String requisitionTypeCode;
	private String requisitionTypeName;
	
	
	private String sampleNo;
	private String accessionNo;
	
	
	private String laboratoryCode;
	private String testCode;
	/*private String testName;
	private String laboratoryName;
	
	private String testType;
	private String isPartialTansmission;
	private String isMultiSessionRegrouped;
	private String isTestMultiSession;
	private String labName;
	private String isRequisitionFormRequired="";//feild from gblt_test_mst
	private String isSampleCollectionFormRequired="";private String isconfidential;*/
	
	
	private String groupCode;
	/*private String groupType;
	private String groupName;
	*/
	
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
	private String sessionId="0";
	

	private String resultValidatedBy;
	private String validatedBy="";
	private List<TriStringObject> associatedWorkOrders;
	
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
	
	private String patAdvisedBy;
	private String testStatusCode;
	private String testStatus;
	private String isResultViewingReady;
	private String resultEnteredBy="";
	private String priorityColor="";
	
	private String detpUnitCode="";
	
	private List<SiteVO> originalSiteDiagnosisList;
	private List<SiteVO> siteDiagnosisList;
	private List<ImageDetailVO> uploadedImageList;
	private List<ImageDetailVO> editedImageList;
	private String clinicianName;
	
	private Map<String,List<AntiBioticVOClass>> originalAntibioticsList;
	private Map<String,List<AntiBioticVOClass>> antibioticsList;
	

	//for pdf list data 
	private String pdfFileCode;	
	private String pdfFileName;	
	private String PdfFtpUrl;
	private String pdfFilePassword;
	private String footer;
	private String associatedToWorkOrder;
	
	private String userSampleNo;
	private String fileCreationDateTime;//change request dated:25 mar 2011
	
	private String currentDiagnosis;
	private String opdRoomName;
	private String currentResultEntryVOIndex;
	
	private String printedWithTemplateID;
	private String printingType;
	
	private String resultValidationDate;
	private String resultvalidationdate; // Used in PDF Report for resultvalidationdate
	private String resultEntryDate;
	private List<PentaStringObject> associatedViewingWorkOrders; 
	String isWorkOrderUpdateble="false";
	
	public String getCurrentDiagnosis() {
		return currentDiagnosis;
	}
	public void setCurrentDiagnosis(String currentDiagnosis) {
		this.currentDiagnosis = currentDiagnosis;
	}
	public String getOpdRoomName() {
		return opdRoomName;
	}
	public void setOpdRoomName(String opdRoomName) {
		this.opdRoomName = opdRoomName;
	}
	public String getFileCreationDateTime() {
		return fileCreationDateTime;
	}
	public void setFileCreationDateTime(String fileCreationDateTime) {
		this.fileCreationDateTime = fileCreationDateTime;
	}
	public String getUserSampleNo() {
		return userSampleNo;
	}
	public void setUserSampleNo(String userSampleNo) {
		this.userSampleNo = userSampleNo;
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
		return cacheLaboratoryTestVO.getIsconfidential();
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
		public String getClinicianName() {
		return clinicianName;
	}
	public void setClinicianName(String clinicianName) {
		this.clinicianName = clinicianName;
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
	public String getIsSampleCollectionFormRequired() {
		return cacheLaboratoryTestVO.getIsSampleCollectionFormRequired();
	}
	
	public String getIsRequisitionFormRequired() {
		return cacheLaboratoryTestVO.getIsRequisitionFormrequired();
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	private String departmentName;
	public String getResultEnteredBy() {
		return resultEnteredBy;
	}
	public void setResultEnteredBy(String resultEnteredBy) {
		this.resultEnteredBy = resultEnteredBy;
	}
	
	public String getPatAdvisedBy() {
		return patAdvisedBy;
	}
	public void setPatAdvisedBy(String patAdvisedBy) {
		this.patAdvisedBy = patAdvisedBy;
	}
	public String getIsPartialTansmission() {
		return cacheLaboratoryTestVO.getIsPartialTansmission();
	}
	
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
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
	public String getLabName() {
		return cacheLaboratoryTestVO.getLaboratoryName();
	}
	
	public List<TriStringObject> getAssociatedWorkOrders() {
		return associatedWorkOrders;
	}
	public void setAssociatedWorkOrders(List<TriStringObject> associatedWorkOrders) {
		this.associatedWorkOrders = associatedWorkOrders;
	}
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
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
		System.out.println("requisitionTypeCode "+requisitionTypeCode);
		if(this.requisitionTypeName==null)
		{
		if(this.requisitionTypeCode.equals("1"))
			this.requisitionTypeName="IPD";
		else if(this.requisitionTypeCode.equals("2"))
			this.requisitionTypeName="OPD";
		else if(this.requisitionTypeCode.equals("3"))
			this.requisitionTypeName="Outside-patient";
		else if(this.requisitionTypeCode.equals("4"))
			this.requisitionTypeName="Outside-sample";
		else
			this.requisitionTypeName="";
		}
		
		System.out.println("this.requisitionTypeName  ---------------->"+this.requisitionTypeName);
		return this.requisitionTypeName;
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
	public String getTestName() {
		return cacheLaboratoryTestVO.getTestName();
	}
	
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLaboratoryName() {
		System.out.println(" calling  getLaboratory NAme :: result Entry vo "+cacheLaboratoryTestVO.getLaboratoryName());
		return cacheLaboratoryTestVO.getLaboratoryName();
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
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getPriorityName() {
		if(this.priorityName==null)
		{
			makePriorityName();
		}
		return priorityName;
	}
	public void makePriorityName() {
		if(this.priorityName!=null)
		{
		 if(this.priorityCode.equals("0"))
			 this.priorityName="Normal";
		 else if(this.priorityCode.equals("1"))
			 this.priorityName="Urgent";
			 else
				 this.priorityName="Low";
			 
		}
		
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
		return cacheLaboratoryTestVO.getMultisessionRegrouped();
	}
	public String getTestType() {
		return cacheLaboratoryTestVO.getTestType();
	}
	public String getIsTestMultiSession() {
		return cacheLaboratoryTestVO.getIsMultiSessionTest();
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
	public String getPatGenderShortName() {
		return patGenderShortName;
	}
	public void setPatGenderShortName(String patGenderShortName) {
		this.patGenderShortName = patGenderShortName;
	}
	public List<PentaStringObject> getAssociatedViewingWorkOrders() {
		return associatedViewingWorkOrders;
	}
	public void setAssociatedViewingWorkOrders(
			List<PentaStringObject> associatedViewingWorkOrders) {
		this.associatedViewingWorkOrders = associatedViewingWorkOrders;
	}
	public String getIsResultViewingReady() {
		return isResultViewingReady;
	}
	public void setIsResultViewingReady(String isResultViewingReady) {
		this.isResultViewingReady = isResultViewingReady;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getTestStatusCode() {
		return testStatusCode;
	}
	public void setTestStatusCode(String testStatusCode) {
		this.testStatusCode = testStatusCode;
	}
	public String resultPrintingDate;
	
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
	public String getDetpUnitCode() {
		return detpUnitCode;
	}
	public void setDetpUnitCode(String detpUnitCode) {
		this.detpUnitCode = detpUnitCode;
	}
	public String getAssociatedToWorkOrder() {
		return associatedToWorkOrder;
	}
	public void setAssociatedToWorkOrder(String associatedToWorkOrder) {
		this.associatedToWorkOrder = associatedToWorkOrder;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupType() {
		return  (cacheLaboratoryGroupVO==null)?null:cacheLaboratoryGroupVO.getGroupTypeCode();
	}
	public String getCurrentResultEntryVOIndex() {
		return currentResultEntryVOIndex;
	}
	public void setCurrentResultEntryVOIndex(String currentResultEntryVOIndex) {
		this.currentResultEntryVOIndex = currentResultEntryVOIndex;
	}
	public String getGroupName() {
		return (cacheLaboratoryGroupVO==null)?null:cacheLaboratoryGroupVO.getGroupName();
	}
	
	public String getIsWorkOrderUpdateble() {
		return isWorkOrderUpdateble;
	}
	public void setIsWorkOrderUpdateble(String isWorkOrderUpdateble) {
		this.isWorkOrderUpdateble = isWorkOrderUpdateble;
	}
	public String getPrintedWithTemplateID() {
		if(this.printedWithTemplateID==null)
		{
			makePrintedWithTemplateID();
		}
		
		return this.printedWithTemplateID;
	}
	public void makePrintedWithTemplateID() {
		if(cacheLaboratoryGroupVO!=null && cacheLaboratoryGroupVO.getPrintedWithTemplateID()!=null)
		{
			this.printedWithTemplateID =cacheLaboratoryGroupVO.getPrintedWithTemplateID();
		}
		else
		{
			this.printedWithTemplateID =cacheLaboratoryTestVO.getPrintedWithTemplateID();
		}
		
		
	}
	public String getPrintingType() 
	{
		if(this.printingType==null)
		{
			makePrintingType();
		}
		
		return printingType;
	}
	
	
	public void makePrintingType() {
		if(cacheLaboratoryGroupVO!=null && cacheLaboratoryGroupVO.getPrintingType()!=null)
		{
			System.out.println("cacheLaboratoryGroupVO.getPrintingType()::"+cacheLaboratoryGroupVO.getPrintingType());
			this.printingType =cacheLaboratoryGroupVO.getPrintingType();
		}
		else
		{
			System.out.println("cacheLaboratoryTestVO.getPrintingType()::"+cacheLaboratoryTestVO.getPrintingType());
			this.printingType =cacheLaboratoryTestVO.getPrintingType();
		}
	}
	public String getResultPrintingDate() {
		return resultPrintingDate;
	}
	public void setResultPrintingDate(String resultPrintingDate) {
		this.resultPrintingDate = resultPrintingDate;
	}
	
	public ResultEntryVO(String laboratoryCode,String groupCode,String testCode,String hospitalCode) throws Exception
	{
		System.out.println("ResultEntryVO:::::::::::::::::::::::::::::laboratoryCode::"+laboratoryCode+" testCode::"+testCode+" groupCode::"+groupCode+" hospitalCode"+hospitalCode);
		if(testCode!=null)
			this.testCode=testCode;
			
		if(laboratoryCode!=null && testCode!=null)
			cacheLaboratoryTestVO=CacheLaboratoryTestVOManager.getInstance().getCacheLaboratoryTestVObj(laboratoryCode+testCode, hospitalCode);
		
		if(groupCode!=null)
			cacheLaboratoryGroupVO=CacheLaboratoryGroupVOManager.getInstance().getCacheLaboratoryGroupVObj(laboratoryCode+groupCode, hospitalCode);
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
	public String getResultvalidationdate() {
		return resultvalidationdate;
	}
	public void setResultvalidationdate(String resultvalidationdate) {
		System.out.println(" resultvalidation date: "+ resultvalidationdate);
		this.resultvalidationdate = resultvalidationdate;
	}
	
}
