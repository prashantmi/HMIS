/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplateHelper.vo;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name						: PGI HIS
 ## Name of Developer		 			: 
 ## Module Name							: Investigation
 ## Process/Database Object Name		: 
 ## Purpose								: Result Entry VO
 ## Date of Creation					: 
 ## Modification Log					:				
 ##		Modify Date						: 10 Dec 2014 
 ##		Reason	(CR/PRS)				: CR #2217(Result Report Printing common remark option)
 ##		Modify By						: Amit Chhikara
 ## Modification Log					:				
 ##		Modify Date						: 16 JAN 2015 
 ##		Reason	(CR/PRS)				:Result report Printing and Duplicate Result Printing functionality for all labs 
 ##		Modify By	                    : Amit Garg
 */
import DataHelper.Config;
import TemplateHelper.PentaStringObject;
import TemplateHelper.TriStringObject;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.w3c.dom.Document;

//import static hisglobal.tools.LoggerConfiguration.LOGGER_INV;
public class ResultEntryVO extends ValueObject {

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
    private String updateType;
    private String sampleNo;
    private String accessionNo;
    private String hospitalCode;
    private String patAcceptanceDate;

    private String laboratoryCode;
    private String testCode;
    private String laboratoryName;
    private String testName;
    private String requisitionNo;
    private String groupCode;

    private String referredHospitalName;
    private String referredHospitalCode;
    private String labNo;
    private Document testDocument;
    private String templateDocumentString;
    private String requisitionDate;
    private String priorityCode;
    private String priorityName;
    private String sampleGroupCode;
    private String isTestReady;
    private String packingListNo;
    private String sessionId;

    private String resultValidatedBy = "";
    private String validatedBy = "";
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
    private String resultEnteredBy = "";
    private String priorityColor = "";

    private String detpUnitCode = "";

    private String clinicianName;

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
    private String isFullColspanRequired = "0";
    private String resultValidationDate;
    private String resultEntryDate;
    private List<PentaStringObject> associatedViewingWorkOrders;
//	private String pageSize=InvestigationStaticConfigurator.pagewidthheight;

    String isWorkOrderUpdateble = "false";

    private String validatedDate = "";

    private String sampleCollectionOfflineRemarks;

    private String tempSampleNo;
    private String hospitalName;
    private String hospitalCity;
    private String hospitalPhone;
    private boolean printWithStandardRanges;
    private String printWithDynamicTemplate = "0";
    private boolean isDoCreateTemplate = true;
    private String dynamicTemplatePrintedGroup = "0";
    private String isdynamicGroupTemplate;
    private String isdynamicTestTemplate;
    
    private String hosAdd1 = "";
    
	private String hosAdd2 = "";
    private String hosFax = "";
    private String hosEmail = "";
    private String hosPin = "";
    private String labDeptCode = "";
    private String labDeptName = "";
    private String hosDistrict="";
    private String hosState="";
    private String concatLabNo="";
    private String concatSampleName="";
    private String displayHeader="";
    private String finalRemark="";
    private String finalRemarkReqd="";
    
    private String oldReportUrl;
    private String newReportUrl;
    private String isReportChange;
    private String reportChangedBy;
    private String addendumRemark;
    private String addendumDate;
    
    //for dept print 
    private String isDeptTestTemplatePrinted;
    private String isDeptTestTemplateResultEntry;
    private String isDeptEntry;
    private String changeCount;
    private String deptResultEnteredBy;
    private String deptResultModifyBy;
    
    private String collDate;
    private String reportDate;
    private String currentDiagnosisName;
    private String isNablAccritedTest;
    private String testCodeAccrebited;
    
    private String sampleCode;
    
    public String getChangeCount() {
    	
    	
		return changeCount;
	}

	public void setChangeCount(String changeCount) {
		this.changeCount = changeCount;
	}

	public String getIsDeptEntry() {
    	if(isDeptEntry==null)
    		isDeptEntry="0";
    	
		return isDeptEntry;
	}

	public void setIsDeptEntry(String isDeptEntry) {
		this.isDeptEntry = isDeptEntry;
	}

	public String getIsDeptTestTemplatePrinted() {
    	
    	if(isDeptTestTemplatePrinted==null)
    		isDeptTestTemplatePrinted="0";
    	
		return isDeptTestTemplatePrinted;
	}

	public void setIsDeptTestTemplatePrinted(String isDeptTestTemplatePrinted) {
		this.isDeptTestTemplatePrinted = isDeptTestTemplatePrinted;
	}

	public String getIsDeptTestTemplateResultEntry() {
		
		if(isDeptTestTemplateResultEntry==null)
			isDeptTestTemplateResultEntry="0";
		return isDeptTestTemplateResultEntry;
	}

	public void setIsDeptTestTemplateResultEntry(
			String isDeptTestTemplateResultEntry) {
		this.isDeptTestTemplateResultEntry = isDeptTestTemplateResultEntry;
	}

	
	
    public String getFinalRemark() {
    	if(finalRemark==null)
    		finalRemark="";
		return finalRemark;
	}

	public void setFinalRemark(String finalRemark) {
		this.finalRemark = finalRemark;
	}

	public String getHosState() {
    	if(hosState==null)
    		hosState="";
		return hosState;
	}

	public void setHosState(String hosState) {
		this.hosState = hosState;
	}

	public String getHosDistrict() {
    	if(hosDistrict==null)
    		hosDistrict="";
    	
		return hosDistrict;
	}

	public void setHosDistrict(String hosDistrict) {
		this.hosDistrict = hosDistrict;
	}

	public String getHosAdd1() {
    	if(hosAdd1 == null) {
    		hosAdd1 = "";
    	}
		return hosAdd1;
	}

	public void setHosAdd1(String hosAdd1) {
		this.hosAdd1 = hosAdd1;
	}

	public String getHosAdd2() {
		if(hosAdd2 == null) {
    		hosAdd2 = "";
    	}
		return hosAdd2;
	}

	public void setHosAdd2(String hosAdd2) {
		this.hosAdd2 = hosAdd2;
	}

	public String getHosFax() {
		if(hosFax == null) {
			hosFax = "";
    	}
		return hosFax;
	}

	public void setHosFax(String hosFax) {
		this.hosFax = hosFax;
	}

	public String getHosEmail() {
		if(hosEmail == null) {
			hosEmail = "";
    	}
		return hosEmail;
	}

	public void setHosEmail(String hosEmail) {
		this.hosEmail = hosEmail;
	}

	public String getHosPin() {
		if(hosPin == null) {
			hosPin = "";
    	}
		return hosPin;
	}

	public void setHosPin(String hosPin) {
		this.hosPin = hosPin;
	}

	public String getLabDeptCode() {
		return labDeptCode;
	}

	public void setLabDeptCode(String labDeptCode) {
		this.labDeptCode = labDeptCode;
	}

	public String getLabDeptName() {
		if(labDeptName == null) {
			labDeptName = "";
    	}
		return labDeptName;
	}

	public void setLabDeptName(String labDeptName) {
		this.labDeptName = labDeptName;
	}


	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public boolean isDoCreateTemplate() {
		return isDoCreateTemplate;
	}

	public void setDoCreateTemplate(boolean isDoCreateTemplate) {
		this.isDoCreateTemplate = isDoCreateTemplate;
	}

	public String getDynamicTemplatePrintedGroup() {
		return dynamicTemplatePrintedGroup;
	}

	public void setDynamicTemplatePrintedGroup(String dynamicTemplatePrintedGroup) {
		this.dynamicTemplatePrintedGroup = dynamicTemplatePrintedGroup;
	}
	
    
    
    public String getPrintWithDynamicTemplate() {
		return printWithDynamicTemplate;
	}

	public void setPrintWithDynamicTemplate(String printWithDynamicTemplate) {
		this.printWithDynamicTemplate = printWithDynamicTemplate;
	}

	public boolean getPrintWithStandardRanges() {
		return printWithStandardRanges;
	}

	public void setPrintWithStandardRanges(boolean printWithStandardRanges) {
		this.printWithStandardRanges = printWithStandardRanges;
	}

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
        //System.out.println("requisitionTypeCode " + requisitionTypeCode);
        if (this.requisitionTypeName == null) {
            if (this.requisitionTypeCode.equals("2")) {
                this.requisitionTypeName = "IPD";
            } else if (this.requisitionTypeCode.equals("1")) {
                this.requisitionTypeName = "OPD";
            } else if (this.requisitionTypeCode.equals("3")) {
                this.requisitionTypeName = "OPD";//"Casuality";
            } else if (this.requisitionTypeCode.equals("4")) {
             this.requisitionTypeName = "OPD";//"Outside-sample";
            }
              else {
                this.requisitionTypeName = "";
            }
        }

        //("this.requisitionTypeName  ---------------->" + this.requisitionTypeName);
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

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
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
    	if(patVisitNo == null || patVisitNo.isEmpty() || patVisitNo == "")
    		patVisitNo = "0";
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
        if (this.priorityName == null) {
            makePriorityName();
        }
        return priorityName;
    }

    public void makePriorityName() {
        if (this.priorityName != null) {
            if (this.priorityCode.equals("0")) {
                this.priorityName = "Normal";
            } else if (this.priorityCode.equals("1")) {
                this.priorityName = "Urgent";
            } else {
                this.priorityName = "Low";
            }

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
        return "2";//sessionId;
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

        String pColor = this.priorityColor;
        if (priorityCode != null) {
            pColor = Config.PRIORITY_NORMALCOLOR;
            if (priorityCode.equals("1")) {
                pColor = Config.PRIORITY_HIGHCOLOR;
            } else if (priorityCode.equals("0")) {
                pColor = Config.PRIORITY_NORMALCOLOR;
            } else {
                pColor = Config.PRIORITY_LOWCOLOR;
            }
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

    public String getCurrentResultEntryVOIndex() {
        return currentResultEntryVOIndex;
    }

    public void setCurrentResultEntryVOIndex(String currentResultEntryVOIndex) {
        this.currentResultEntryVOIndex = currentResultEntryVOIndex;
    }

    public String getIsWorkOrderUpdateble() {
        return isWorkOrderUpdateble;
    }

    public void setIsWorkOrderUpdateble(String isWorkOrderUpdateble) {
        this.isWorkOrderUpdateble = isWorkOrderUpdateble;
    }

    public String getResultPrintingDate() {
        return resultPrintingDate;
    }

    public void setResultPrintingDate(String resultPrintingDate) {
        this.resultPrintingDate = resultPrintingDate;
    }

    public ResultEntryVO(String laboratoryCode, String testCode, String groupCode, String hospitalCode,String sampleCode) throws Exception {
        //LOGGER_INV.log(Level.INFO,"ResultEntryVO:::::::::::::::::::::::::::::laboratoryCode::"+laboratoryCode+" testCode::"+testCode+" groupCode::"+groupCode+" hospitalCode"+hospitalCode);
        if (testCode != null) {
            this.testCode = testCode;
        }
        
        if (sampleCode != null) {
            this.sampleCode = sampleCode;
        }
        

        if (laboratoryCode != null && testCode != null) //	laboratoryTestVO=InvestigationTemplateDataHelper.getInstance().getLaboratoryTestVObj(laboratoryCode+testCode);
        {
            if (groupCode != null) //	laboratoryGroupVO=InvestigationTemplateDataHelper.getInstance().getLaboratoryGroupVObj(laboratoryCode+groupCode);
            {
                if (laboratoryCode != null) {
                    //	laboratoryVO=InvestigationTemplateDataHelper.getInstance().getLaboratoryVObj(laboratoryCode);
                }
            }
        }
        this.hospitalCode = hospitalCode;

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

    /**
     * @return the printedWithTemplateID
     */
    public String getPrintedWithTemplateID() {
        return printedWithTemplateID;
    }

    /**
     * @param printedWithTemplateID the printedWithTemplateID to set
     */
    public void setPrintedWithTemplateID(String printedWithTemplateID) {
        this.printedWithTemplateID = printedWithTemplateID;
    }

    /**
     * @return the printingType
     */
    public String getPrintingType() {
        return printingType;
    }

    /**
     * @param printingType the printingType to set
     */
    public void setPrintingType(String printingType) {
        this.printingType = printingType;
    }

    /**
     * @return the hospitalCode
     */
    public String getHospitalCode() {
        return hospitalCode;
    }

    /**
     * @param hospitalCode the hospitalCode to set
     */
    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    /**
     * @return the laboratoryName
     */
    public String getLaboratoryName() {
        return laboratoryName;
    }

    /**
     * @return the testName
     */
    public String getTestName() {
        return testName;
    }

    /**
     * @sets the testName
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * @return the requisitionNo
     */
    public String getRequisitionNo() {
        return requisitionNo;
    }

    /**
     * @param requisitionNo the requisitionNo to set
     */
    public void setRequisitionNo(String requisitionNo) {
        this.requisitionNo = requisitionNo;
    }

    /**
     * @return the tempSampleNo
     */
    public String getTempSampleNo() {
        return tempSampleNo;
    }

    /**
     * @param tempSampleNo the tempSampleNo to set
     */
    public void setTempSampleNo(String tempSampleNo) {
        this.tempSampleNo = tempSampleNo;
    }

    /**
     * @return the hospitalName
     */
    public String getHospitalName() {
    	if(hospitalName==null)
    		hospitalName="";
        return hospitalName;
    }

    /**
     * @param hospitalName the hospitalName to set
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     * @return the hospitalCity
     */
    public String getHospitalCity() {
    	if(hospitalCity == null)
    	{
    		hospitalCity = "";
    	}
        return hospitalCity;
    }

    /**
     * @param hospitalCity the hospitalCity to set
     */
    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }

    /**
     * @return the hospitalPhone
     */
    public String getHospitalPhone() {
    	if(hospitalPhone==null);
    	hospitalPhone="";
        return hospitalPhone;
    }

    /**
     * @param hospitalPhone the hospitalPhone to set
     */
    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

	public String getIsdynamicGroupTemplate() {
		return isdynamicGroupTemplate;
	}

	public void setIsdynamicGroupTemplate(String isdynamicGroupTemplate) {
		this.isdynamicGroupTemplate = isdynamicGroupTemplate;
	}

	public String getIsdynamicTestTemplate() {
		return isdynamicTestTemplate;
	}

	public void setIsdynamicTestTemplate(String isdynamicTestTemplate) {
		this.isdynamicTestTemplate = isdynamicTestTemplate;
	}

	public String getConcatLabNo() {
		if(concatLabNo==null)
			concatLabNo="";
		return concatLabNo;
	}

	public void setConcatLabNo(String concatLabNo) {
		this.concatLabNo = concatLabNo;
	}

	public String getConcatSampleName() {
		if(concatSampleName==null)
			concatSampleName="";
		return concatSampleName;
	}

	public void setConcatSampleName(String concatSampleName) {
		this.concatSampleName = concatSampleName;
	}

	public String getDisplayHeader() {
		if(displayHeader==null)
			displayHeader="";
		return displayHeader;
	}

	public void setDisplayHeader(String displayHeader) {
		this.displayHeader = displayHeader;
	}

	public String getFinalRemarkReqd() {
		return finalRemarkReqd;
	}

	public void setFinalRemarkReqd(String finalRemarkReqd) {
		this.finalRemarkReqd = finalRemarkReqd;
	}

	public String getOldReportUrl() {
		return oldReportUrl;
	}

	public void setOldReportUrl(String oldReportUrl) {
		this.oldReportUrl = oldReportUrl;
	}

	public String getNewReportUrl() {
		return newReportUrl;
	}

	public void setNewReportUrl(String newReportUrl) {
		this.newReportUrl = newReportUrl;
	}

	public String getIsReportChange() {
		return isReportChange;
	}

	public void setIsReportChange(String isReportChange) {
		this.isReportChange = isReportChange;
	}

	public String getReportChangedBy() {
		return reportChangedBy;
	}

	public void setReportChangedBy(String reportChangedBy) {
		this.reportChangedBy = reportChangedBy;
	}

	public String getAddendumRemark() {
		return addendumRemark;
	}

	public void setAddendumRemark(String addendumRemark) {
		this.addendumRemark = addendumRemark;
	}

	public String getAddendumDate() {
		return addendumDate;
	}

	public void setAddendumDate(String addendumDate) {
		this.addendumDate = addendumDate;
	}

	public String getDeptResultEnteredBy() {
		return deptResultEnteredBy;
	}

	public void setDeptResultEnteredBy(String deptResultEnteredBy) {
		this.deptResultEnteredBy = deptResultEnteredBy;
	}

	public String getDeptResultModifyBy() {
		return deptResultModifyBy;
	}

	public void setDeptResultModifyBy(String deptResultModifyBy) {
		this.deptResultModifyBy = deptResultModifyBy;
	}

	public String getCollDate() {
		return collDate;
	}

	public void setCollDate(String collDate) {
		this.collDate = collDate;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getCurrentDiagnosisName() {
		return currentDiagnosisName;
	}

	public void setCurrentDiagnosisName(String currentDiagnosisName) {
		this.currentDiagnosisName = currentDiagnosisName;
	}

	public String getIsNablAccritedTest() {
		return isNablAccritedTest;
	}

	public void setIsNablAccritedTest(String isNablAccritedTest) {
		this.isNablAccritedTest = isNablAccritedTest;
	}

	public String getTestCodeAccrebited() {
		return testCodeAccrebited;
	}

	public void setTestCodeAccrebited(String testCodeAccrebited) {
		this.testCodeAccrebited = testCodeAccrebited;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getPatAcceptanceDate() {
		return patAcceptanceDate;
	}

	public void setPatAcceptanceDate(String patAcceptanceDate) {
		this.patAcceptanceDate = patAcceptanceDate;
	}

	
}
