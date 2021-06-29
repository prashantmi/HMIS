/*
## Copyright Information				: C-DAC, Noida  
 ## Project Name						: INVESTIGATION
 ## Name of Developer		 			: JATIN KUMAR
 ## Module Name							: Investigation
 ## Process/Database Object Name		: 
 ## Purpose								:  TEST WISE CONSUMABLE VO
 ## Date of Creation					: 06/09/2016
*/
package new_investigation.vo.template;

import hisglobal.utility.Entry;
//import investigation.InvestigationConfig;
//import investigation.InvestigationStaticConfigurator;
//import investigation.cacheImplementation.cachemanager.CacheLaboratoryGroupVOManager;
//import investigation.cacheImplementation.cachemanager.CacheLaboratoryTestVOManager;
//import investigation.cacheImplementation.cachemanager.CacheLaboratoryVOManager;
//import investigation.cacheImplementation.cachevo.CacheLaboratoryGroupVO;
//import investigation.cacheImplementation.cachevo.CacheLaboratoryTestVO;
//import investigation.cacheImplementation.cachevo.CacheLaboratoryVO;
//import investigation.vo.AntiBioticVOClass;
//import investigation.vo.ImageDetailVO;
//import investigation.vo.PentaStringObject;
//import investigation.vo.SiteVO;
//import investigation.vo.TriStringObject;

import hisglobal.vo.ValueObject;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;
import new_investigation.vo.template.TriStringObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import new_investigation.InvestigationConfig;

import org.w3c.dom.Document;

//import static hisglobal.tools.LoggerConfiguration.LOGGER_INV;

public class TestWiseConsumableVO extends ValueObject{
	 
	//LaboratoryTestVO laboratoryTestVO;
	//LaboratoryGroupVO laboratoryGroupVO;
	//LaboratoryVO laboratoryVO;
	private String patCRNo;
	private String patType;
	private String patName;
	private String patAge;
	private String patGender;
	private String patGenderShortName;
//	private String patEpisodeCode;
	private String patSampleID;
	private String patVisitNo;
	private String requisitionDNo;
	private String requisitionTypeCode;
	private String requisitionTypeName;
	private String updateType;
	private String sampleNo;
	private String accessionNo;
	private String fromDate;
	private String toDate;
	private String labCode;
	private Document groupTemplateDocument;
	private String strRefRange;
	private String cannedLabCode;
	private String cannedName;
	private String cannedContent;
	private String macroText;
	private String getSearchType;
	private String testGroupCode;
	private String toSampleNo;
	private String fromSampleNo;
	private String fromLabNo;
	private String toLabNo;
	private String generationType;
	private String testWiseCode;
	private String testGroupCodeWise;
	private String onLoadValue;
	private String newEntry;
	private String previousValue;
	private String userCannedCode;
	private boolean isDynamicTemplate = false;

	private String dynamicTemplatePrintedGroup = "0";
	private String dynamnicTemplateResultEntryGroup = "0";
	private boolean doCreateTemplate = true;

	Map<String,List<String>> autoList;
	private String tempPatName;
	private String finalRemarkReqd;
	private String finalRemarkValue[];
	private String crNoReqNoString[];
	private String finalRemarkString;
	private String instructionPat;
	private String crNo;
	private String itemName;
	private String slNo;
	private String otherItemID;
	private String lotNo;
	private String remarks;
	private String itemID;
	private String itemTypeID;
	private String isMapped;
	private String unitCode;
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemTypeID() {
		return itemTypeID;
	}
	public void setItemTypeID(String itemTypeID) {
		this.itemTypeID = itemTypeID;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getOtherItemID() {
		return otherItemID;
	}
	public void setOtherItemID(String otherItemID) {
		this.otherItemID = otherItemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDefaultQuantity() {
		return defaultQuantity;
	}
	public void setDefaultQuantity(String defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	private String defaultQuantity;
	private String unit;
	private String batchNo;
	private String manufacture;
	private String expiryDate;
	private String storeName;
	private String itemType;
	private String unitName;
 public boolean isDoCreateTemplate() {
		return doCreateTemplate;
	}
	public void setDoCreateTemplate(boolean doCreateDynamicTemplate) {
		this.doCreateTemplate = doCreateDynamicTemplate;
	}
public String getDynamicTemplatePrintedGroup() {
		return dynamicTemplatePrintedGroup;
	}
	public void setDynamicTemplatePrintedGroup(String dynamicTemplatePrintedGroup) {
		this.dynamicTemplatePrintedGroup = dynamicTemplatePrintedGroup;
	}
	public String getDynamnicTemplateResultEntryGroup() {
		return dynamnicTemplateResultEntryGroup;
	}
	public void setDynamnicTemplateResultEntryGroup(
			String dynamnicTemplateResultEntryGroup) {
		this.dynamnicTemplateResultEntryGroup = dynamnicTemplateResultEntryGroup;
	}
public boolean isDynamicTemplate() {
		return isDynamicTemplate;
	}
	public void setDynamicTemplate(boolean isDynamicTemplate) {
		this.isDynamicTemplate = isDynamicTemplate;
	}

public String getOnLoadValue() {
		return onLoadValue;
	}
	public void setOnLoadValue(String onLoadValue) {
		this.onLoadValue = onLoadValue;
	}
public String getTestGroupCodeWise() {
		return testGroupCodeWise;
	}
	public void setTestGroupCodeWise(String testGroupCodeWise) {
		this.testGroupCodeWise = testGroupCodeWise;
	}
public String getTestWiseCode() {
		return testWiseCode;
	}
	public void setTestWiseCode(String testWiseCode) {
		this.testWiseCode = testWiseCode;
	}
public String getGenerationType() {
		return generationType;
	}
	public void setGenerationType(String generationType) {
		this.generationType = generationType;
	}
public String getTestGroupCode() {
		return testGroupCode;
	}
	public void setTestGroupCode(String testGroupCode) {
		this.testGroupCode = testGroupCode;
	}
	public String getToSampleNo() {
		return toSampleNo;
	}
	public void setToSampleNo(String toSampleNo) {
		this.toSampleNo = toSampleNo;
	}
	public String getFromSampleNo() {
		return fromSampleNo;
	}
	public void setFromSampleNo(String fromSampleNo) {
		this.fromSampleNo = fromSampleNo;
	}
	public String getFromLabNo() {
		return fromLabNo;
	}
	public void setFromLabNo(String fromLabNo) {
		this.fromLabNo = fromLabNo;
	}
	public String getToLabNo() {
		return toLabNo;
	}
	public void setToLabNo(String toLabNo) {
		this.toLabNo = toLabNo;
	}
public String getMacroText() {
		return macroText;
	}
	public void setMacroText(String macroText) {
		this.macroText = macroText;
	}
public String getCannedName() {
		return cannedName;
	}
	public void setCannedName(String cannedName) {
		this.cannedName = cannedName;
	}
	public String getCannedContent() {
		return cannedContent;
	}
	public void setCannedContent(String cannedContent) {
		this.cannedContent = cannedContent;
	}
public String getCannedLabCode() {
		return cannedLabCode;
	}
	public void setCannedLabCode(String cannedLabCode) {
		this.cannedLabCode = cannedLabCode;
	}
public String getStrRefRange() {
		return strRefRange;
	}
	public void setStrRefRange(String strRefRange) {
		this.strRefRange = strRefRange;
	}
private String	parameterRequisitionDNo;
 private String patVisitDat;
 private String departmentcode;
 private String patdeptunitcode;
  private String reportAvailableAfter;
  private String reqDtlStatus;
  private String parantParaCode;
  
  private String resultEntryValue;
  private String testParaMeterCode;
  private String patUnitName;
 private String patLabName;
 private String patStatus;
 private String testParameterName;
  
 private boolean printWithStandardRanges;
 
  
public boolean getPrintWithStandardRanges() {
	return printWithStandardRanges;
}
public void setPrintWithStandardRanges(boolean printWithStandardRanges) {
	this.printWithStandardRanges = printWithStandardRanges;
}
public String getTestParameterName() {
	return testParameterName;
}
public void setTestParameterName(String testParameterName) {
	this.testParameterName = testParameterName;
}
public String getPatLabName() {
	return patLabName;
}
public void setPatLabName(String patLabName) {
	this.patLabName = patLabName;
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
public String getParantParaCode() {
	return parantParaCode;
}
public void setParantParaCode(String parantParaCode) {
	this.parantParaCode = parantParaCode;
}
public String getResultEntryValue() {
	return resultEntryValue;
}
public void setResultEntryValue(String resultEntryValue) {
	this.resultEntryValue = resultEntryValue;
}
public String getTestParaMeterCode() {
	return testParaMeterCode;
}
public void setTestParaMeterCode(String testParaMeterCode) {
	this.testParaMeterCode = testParaMeterCode;
}
public String getReqDtlStatus() {
	return reqDtlStatus;
}
public void setReqDtlStatus(String reqDtlStatus) {
	this.reqDtlStatus = reqDtlStatus;
}
public String getReportAvailableAfter() {
	return reportAvailableAfter;
}
public void setReportAvailableAfter(String reportAvailableAfter) {
	this.reportAvailableAfter = reportAvailableAfter;
}
public String getPatVisitDat() {
	return patVisitDat;
}
public void setPatVisitDat(String patVisitDat) {
	this.patVisitDat = patVisitDat;
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
public String getParameterRequisitionDNo() {
	return parameterRequisitionDNo;
}
public void setParameterRequisitionDNo(String parameterRequisitionDNo) {
	this.parameterRequisitionDNo = parameterRequisitionDNo;
}
	public Document getGroupTemplateDocument() {
		return groupTemplateDocument;
	}
	public void setGroupTemplateDocument(Document groupTemplateDocument) {
		this.groupTemplateDocument = groupTemplateDocument;
	}
	private String groupTemplateString;
	
	public String getGroupTemplateString() {
		return groupTemplateString;
	}
	public void setGroupTemplateString(String groupTemplateString) {
		this.groupTemplateString = groupTemplateString;
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
	private List<TestWiseConsumableVO> resultEntryVOListValidatedBy;
	
	/*public LaboratoryTestVO getLaboratoryTestVO() {
		return laboratoryTestVO;
	}
	public void setLaboratoryTestVO(LaboratoryTestVO laboratoryTestVO) {
		this.laboratoryTestVO = laboratoryTestVO;
	}
	public LaboratoryGroupVO getLaboratoryGroupVO() {
		return laboratoryGroupVO;
	}
	public void setLaboratoryGroupVO(LaboratoryGroupVO laboratoryGroupVO) {
		this.laboratoryGroupVO = laboratoryGroupVO;
	}
	public LaboratoryVO getLaboratoryVO() {
		return laboratoryVO;
	}
	public void setLaboratoryVO(LaboratoryVO laboratoryVO) {
		this.laboratoryVO = laboratoryVO;
	}*/
	public List<TestWiseConsumableVO> getResultEntryVOListValidatedBy() {
		return resultEntryVOListValidatedBy;
	}
	public void setResultEntryVOListValidatedBy(
			List<TestWiseConsumableVO> resultEntryVOListValidatedBy) {
		this.resultEntryVOListValidatedBy = resultEntryVOListValidatedBy;
	}
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	public void setPrintedWithTemplateID(String printedWithTemplateID) {
		this.printedWithTemplateID = printedWithTemplateID;
	}
	public void setPrintingType(String printingType) {
		this.printingType = printingType;
	}
	private String laboratoryCode;
	private String testCode;
	private String laboratoryName;
	private String sampleCode;
	private String hospitalcode;
	private String testName;
	private String loincCode;
	private String uomCode;
	private String episodeCode;
	private String requisitionNo;
	HashMap<String,String> paraLoinc;
	HashMap<String,String> refUOM;
	public HashMap<String, String> getRefUOM() {
		return refUOM;
	}
	public void setRefUOM(HashMap<String, String> refUOM) {
		this.refUOM = refUOM;
	}
	public String getRefRange() {
		return refRange;
	}
	public void setRefRange(String refRange) {
		this.refRange = refRange;
	}
	private String refRange;
	
	
	/*private String testName;
	private String laboratoryName;
	
	private String testType;
	private String isPartialTansmission;
	private String isMultiSessionRegrouped;
	private String isTestMultiSession;
	private String labName;
	private String isRequisitionFormRequired="";//feild from gblt_test_mst
	private String isSampleCollectionFormRequired="";private String isconfidential;*/
	
	
	public HashMap<String, String> getParaLoinc() {
		return paraLoinc;
	}
	public void setParaLoinc(HashMap<String, String> paraLoinc) {
		this.paraLoinc = paraLoinc;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	public String getLoincCode() {
		return loincCode;
	}
	public void setLoincCode(String loincCode) {
		this.loincCode = loincCode;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	private String groupCode;
	/*private String groupType;
	*/
	private String groupName;
	
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
	

	private String resultValidatedBy="";
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
	
//	private List<SiteVO> originalSiteDiagnosisList;
//	private List<SiteVO> siteDiagnosisList;
//	private List<ImageDetailVO> uploadedImageList;
//	private List<ImageDetailVO> editedImageList;
	private String clinicianName;
	
//	private Map<String,List<AntiBioticVOClass>> originalAntibioticsList;
//	private Map<String,List<AntiBioticVOClass>> antibioticsList;
//	

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
	private String isFullColspanRequired="0";
	private String resultValidationDate;
	private String resultEntryDate;
	private List<PentaStringObject> associatedViewingWorkOrders; 
//	private String pageSize=InvestigationStaticConfigurator.pagewidthheight;
	
	String isWorkOrderUpdateble="false";
	
	private String validatedDate="";
	
	private String sampleCollectionOfflineRemarks;
	
	private String defaultQuantityString;
	
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
	/*	public String getIsconfidential() {
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
	}*/
		public String getClinicianName() {
		return clinicianName;
	}
	public void setClinicianName(String clinicianName) {
		this.clinicianName = clinicianName;
	}
	/*public List<ImageDetailVO> getUploadedImageList() {
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
	}*/
	
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
/*	public String getIsPartialTansmission() {
		return cacheLaboratoryTestVO.getIsPartialTansmission();
	}*/
	
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
	//	return cacheLaboratoryTestVO.getLaboratoryName();
		return null;
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
			this.requisitionTypeName="Emergency";
		/*else if(this.requisitionTypeCode.equals("4"))
			this.requisitionTypeName="Outside-sample";*/
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
	/*public String getPatEpisodeCode() {
		return patEpisodeCode;
	}
	public void setPatEpisodeCode(String patEpisodeCode) {
		this.patEpisodeCode = patEpisodeCode;
	}*/
	public String getPatSampleID() {
		return patSampleID;
	}
	public void setPatSampleID(String patSampleID) {
		this.patSampleID = patSampleID;
	}
	public String getTestName() {
		//return cacheLaboratoryTestVO.getTestName();
		return testName;
	}
	
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLaboratoryName() {
		//System.out.println(" calling  getLaboratory NAme :: result Entry vo "+cacheLaboratoryTestVO.getLaboratoryName());
		//return cacheLaboratoryTestVO.getLaboratoryName();
		return null;
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
	
	public String getPriorityName()
	{
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
	//	return cacheLaboratoryTestVO.getMultisessionRegrouped();
		return null;
	}
	public String getTestType() {
		//return cacheLaboratoryTestVO.getTestType();
		return null;
	}
	public String getIsTestMultiSession() {
		//return cacheLaboratoryTestVO.getIsMultiSessionTest();
		return null;
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
		return null;// (laboratoryGroupVO==null)?null:laboratoryGroupVO.getGroupTypeCode();
	
	}
	public String getCurrentResultEntryVOIndex() {
		return currentResultEntryVOIndex;
	}
	public void setCurrentResultEntryVOIndex(String currentResultEntryVOIndex) {
		this.currentResultEntryVOIndex = currentResultEntryVOIndex;
	}
	 
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
		/*if(laboratoryGroupVO!=null && laboratoryGroupVO.getPrintedWithTemplateID()!=null)
		{
			this.printedWithTemplateID =laboratoryGroupVO.getPrintedWithTemplateID();
		}
		else
		{
			this.printedWithTemplateID =laboratoryTestVO.getPrintedWithTemplateID();
		}*/
		
		
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
		/*if(laboratoryGroupVO!=null && laboratoryGroupVO.getPrintingType()!=null)
		{
			this.printingType =laboratoryGroupVO.getPrintingType();
		}
		else
		{
			//this.printingType =cacheLaboratoryTestVO.getPrintingType();
		}*/
	}
	public String getResultPrintingDate() {
		return resultPrintingDate;
	}
	public void setResultPrintingDate(String resultPrintingDate) {
		this.resultPrintingDate = resultPrintingDate;
	}
	
	public TestWiseConsumableVO()
	{
		// Default Constructor
	}
	
	public TestWiseConsumableVO(String laboratoryCode,String testCode,String groupCode,String hospitalCode) 
	{
		//LOGGER_INV.log(Level.INFO,"ResultEntryVO:::::::::::::::::::::::::::::laboratoryCode::"+laboratoryCode+" testCode::"+testCode+" groupCode::"+groupCode+" hospitalCode"+hospitalCode);
		if(testCode!=null)
			this.testCode=testCode;
			
		/*if(laboratoryCode!=null && testCode!=null)
			laboratoryTestVO=InvestigationTemplateDataHelper.getInstance().getLaboratoryTestVObj(laboratoryCode+testCode);
		
		if(groupCode!=null)
			laboratoryGroupVO=InvestigationTemplateDataHelper.getInstance().getLaboratoryGroupVObj(laboratoryCode+groupCode);
		
		if(laboratoryCode!=null)
		{
			laboratoryVO=InvestigationTemplateDataHelper.getInstance().getLaboratoryVObj(laboratoryCode);
		}*/
		
		
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
	/*public String getPageSize() {
		if(cacheLaboratoryVO!=null)
			return	cacheLaboratoryVO.getDefaultPageHeight();
		else
			return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}*/
	public String getValidatedDate() {
		return validatedDate;
	}
	public void setValidatedDate(String validatedDate) {
		this.validatedDate = validatedDate;
	}
	public String getSampleCollectionOfflineRemarks() {
		return sampleCollectionOfflineRemarks;
	}
	public void setSampleCollectionOfflineRemarks(
			String sampleCollectionOfflineRemarks) {
		this.sampleCollectionOfflineRemarks = sampleCollectionOfflineRemarks;
	}
	/*public CacheLaboratoryVO getCacheLaboratoryVO() {
		return cacheLaboratoryVO;
	}
	public void setCacheLaboratoryVO(CacheLaboratoryVO cacheLaboratoryVO) {
		this.cacheLaboratoryVO = cacheLaboratoryVO;
	}*/
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getIsFullColspanRequired() {
		return isFullColspanRequired;
	}
	public void setIsFullColspanRequired(String isFullColspanRequired) {
		this.isFullColspanRequired = isFullColspanRequired;
	}
	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	public String getTemplateDocumentString() {
		return templateDocumentString;
	}
	public void setTemplateDocumentString(String templateDocumentString) {
		this.templateDocumentString = templateDocumentString;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	private String tempSampleNo;



	public String getTempSampleNo() {
		return tempSampleNo;
	}
	public void setTempSampleNo(String tempSampleNo) {
		this.tempSampleNo = tempSampleNo;
	}
	public String getGetSearchType() {
		return getSearchType;
	}
	public void setGetSearchType(String getSearchType) {
		this.getSearchType = getSearchType;
	}
	public String getNewEntry() {
		return newEntry;
	}
	public void setNewEntry(String newEntry) {
		this.newEntry = newEntry;
	}
	public String getPreviousValue() {
		return previousValue;
	}
	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}
	public String getUserCannedCode() {
		return userCannedCode;
	}
	public void setUserCannedCode(String userCannedCode) {
		this.userCannedCode = userCannedCode;
	}
	public Map<String, List<String>> getAutoList() {
		return autoList;
	}
	public void setAutoList(Map<String, List<String>> autoList) {
		this.autoList = autoList;
	}
	public String getTempPatName() {
		return tempPatName;
	}
	public void setTempPatName(String tempPatName) {
		this.tempPatName = tempPatName;
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
	public String[] getCrNoReqNoString() {
		return crNoReqNoString;
	}
	public void setCrNoReqNoString(String[] crNoReqNoString) {
		this.crNoReqNoString = crNoReqNoString;
	}
	public String getFinalRemarkString() {
		return finalRemarkString;
	}
	public void setFinalRemarkString(String finalRemarkString) {
		this.finalRemarkString = finalRemarkString;
	}
	public String getInstructionPat() {
		return instructionPat;
	}
	public void setInstructionPat(String instructionPat) {
		this.instructionPat = instructionPat;
	}
	public String getCrNo() {
		return crNo;
	}
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}
	public String getDefaultQuantityString() {
		return defaultQuantityString;
	}
	public void setDefaultQuantityString(String defaultQuantityString) {
		this.defaultQuantityString = defaultQuantityString;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsMapped() {
		return isMapped;
	}
	public void setIsMapped(String isMapped) {
		this.isMapped = isMapped;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


}
