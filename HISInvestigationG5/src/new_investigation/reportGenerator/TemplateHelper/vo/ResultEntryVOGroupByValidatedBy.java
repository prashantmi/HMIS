 
package new_investigation.reportGenerator.TemplateHelper.vo;

 
//import new_investigation.InvestigationStaticConfigurator;
import new_investigation.reportGenerator.DataHelper.Config;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ResultEntryVOGroupByValidatedBy extends ValueObject {

    private String testName = "";
    private String testCode;
    private String laboratoryName = "";
    private String laboratoryCode;
    private String departmentName = "";
    private String resultValidatedBy = "";
    private String patCRNo = "";
    private String patType;
    private String patName = "";
    private String patAge = "";
    private String patGender = "";
    private String patGenderShortName = "";
    private String requisitionDNo;
    private String requisitionNo;
    private String requisitionTypeCode;
    private String requisitionTypeName;
    private String sampleNo;
    private String accessionNo;
    private String patEpisodeCode;
    private String patSampleID;
    private String patVisitNo;
    private String patDeptUnit = "";
    private String detpUnitCode;
    private String wardName = "";
    private String bedName = "";
    private String roomName = "";
    private String visitDate = "";
    private String hospitalCode = "";
    private String patAcceptanceDate;

    private String hospitalName;
    private String hospitalCity;
    private String hospitalPhone;
    private String referredHospitalName;
    private String referredHospitalCode;
    private String labNo = "";
    private String requisitionDate;
    private String priorityCode;
    private String priorityName;
    private String priorityColor;
    private String patAdvisedBy;
    private String resultEnteredBy = "";
    private String clinicianName;
    private String clinicianCode;
    private String isconfidential;
    private String sessionId;

    private String patAgeSex = "";
    private String opdorWard = "";

    private String validatedBy = "";
    private Map<String, String> sampleNameListMap;
    private List<String> sampleNameList;
    private Map<String, String> labNoListMap;
    private List<String> labNoList;
    private List<ResultEntryVO> resultEntryVOListValidatedBy;

    private String pdfFileCode;
    private String pdfFileName;
    private String PdfFtpUrl;
    private String pdfFilePassword;
    private String groupCode;
    private String groupType;

    private String groupTemplateString;
    private Document groupTemplateDocument;
    private String printingTemplateString;
    private Document printingTemplateDocument;
    private String groupName;

    private String sampleName;

    private String isFooterRequired = "Y";
    private String footer;
    private String footerHeight = "0";
    private String footerWidth = Config.pagewidthprinting;

    private String isHeaderRequired = "Y";
    private String header;
    private String headerHeight = "0";
    private String headerWidth = Config.pagewidthprinting;
    private String pageSize = Config.pagewidthheight;

    private String printedWithTemplateID;
    private String printingType;
    private Map<String, String> groupMap; // contains the Map of different group present in the GroupValidatedByVO 
    private String currentDiagnosis; //add for Change Request 29 dec 2010 :change in Report Header Add Diagnosis.
    private String userSampleNo; //add for Change Request 31 jan 2011 :change in Report Header Add Diagnosis.
    private String patAdmissionNo; //for add admission no in pdf report.
    private boolean hasSpecialGroupTemplate;

    private String resultValidationDate;
    private String resultEntryDate;
    private List<Node> images;

    private String validationPendingCount = "0";
    private String validatedCount = "0";
    private String validationTotalCount = "0";
    private String resultEnterdCount = "0";
    private String printedTotalCount = "0";
    private String raisedTotalCount = "0";

    private String validatedDate = "";
    private String sampleCollectionOfflineRemarks;
    private String printWithDynamicTemplate = "0";
    private String isdynamicGroupTemplate="0";
    private String isdynamicTestTemplate="0";
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
    
    //for dept template
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
    private float headerheight;
    
    private String sampleCode;
    
    private String isreportlablabelchanged;
    private String isreportcollectionlabelchanged;
    private String isreportsamplelabelrequired;
    private String groupname;
    private String isgroupnameprint;
    private String ispidshow;
    private String  pidno;
    private String nablogo;
    private String revalidatedBy = "";
    private List<String> xmlgenereatedrequisitiondno;
    private String xmlgenereatedrequisitiondnoo;
    private String enteredby = "";
    private String isenteredbyrequired="";
    private String fileuploaddata="";
    private String fileuploadname="";
    private String isfileuploaddadd="";
    private String  isfileuploadprint="";
    private String  isfirstpagereq="";
    private int  islabeldoubleline;

    

    private String resultenteredbycode = "";
    private String validatedbycode = "";
    private String enteredbycode = "";
    private String isaddendumfirstreportprint="";
    private String isextrafooterreqval="";
    private String isextrafooterreq="";

    private String acceptanceDate="";
    private String advicedby="";

    
    public String getChangeCount() {
    	if(changeCount==null)
    		changeCount="";
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


    
    public String getPrintWithDynamicTemplate() {
		return printWithDynamicTemplate;
	}

	public void setPrintWithDynamicTemplate(String printWithDynamicTemplate) {
		this.printWithDynamicTemplate = printWithDynamicTemplate;
	}
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
        return printedWithTemplateID ;
        //return printedWithTemplateID;
    }

    public void setPrintedWithTemplateID(String printedWithTemplateID) {
        this.printedWithTemplateID = printedWithTemplateID;

    }

    public String getPrintingType() {
        //return printingType;
        return "1";
    }

    public void setPrintingType(String printingType) {
		//this.printingType = printingType;

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

    public List<String> getSampleNameList() {
        return sampleNameList;
    }

    public void setSampleNameList(List<String> sampleNameList) {
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
    	if(patVisitNo == null || patVisitNo.isEmpty() || patVisitNo == "")
    		patVisitNo = "0";
        return patVisitNo;
    }

    public void setPatVisitNo(String patVisitNo) {
        this.patVisitNo = patVisitNo;
    }

    public String getTestName() {

        if (this.getResultEntryVOListValidatedBy() != null && this.getResultEntryVOListValidatedBy().get(0).getGroupCode() != null) {
            if (this.getResultEntryVOListValidatedBy() != null && this.getResultEntryVOListValidatedBy().size() == 1) {
                this.testName = this.getResultEntryVOListValidatedBy().get(0).getTestName();

            } else {

                //	this.testName=this.getResultEntryVOListValidatedBy().get(0).getGroupName();
            }
        } else {

            if (this.getResultEntryVOListValidatedBy() != null && this.getResultEntryVOListValidatedBy().size() == 1) {
                this.testName = this.getResultEntryVOListValidatedBy().get(0).getTestName();

            } else {
                int i = 0;
                if (this.getResultEntryVOListValidatedBy() != null) {
                    for (ResultEntryVO entryVO : this.getResultEntryVOListValidatedBy()) {
                        if (i == 0) {
                            this.testName = entryVO.getTestName();
                        } else {
                            this.testName += "," + entryVO.getTestName();
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
        priorityCode = "0";
        if (this.getResultEntryVOListValidatedBy() != null) {
            for (ResultEntryVO resultEntryVO : this.getResultEntryVOListValidatedBy()) {
                if (Integer.parseInt(resultEntryVO.getPriorityCode()) > Integer.parseInt(priorityCode)) {
                    priorityCode = resultEntryVO.getPriorityCode();
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

    public String getSessionId() {

        if (this.getResultEntryVOListValidatedBy() != null && this.getResultEntryVOListValidatedBy().size() == 0) {
            this.sessionId = this.getResultEntryVOListValidatedBy().get(0).getSessionId();

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

    String isGroupUpdateble = "false";

    public String getIsGroupUpdateble() {
        return isGroupUpdateble;
    }

    public void setIsGroupUpdateble(String isGroupUpdateble) {
        this.isGroupUpdateble = isGroupUpdateble;
    }

    public String getRequisitionNo() {
        //this.requisitionNo=requisitionDNo.substring(0, 17);
        return requisitionNo;
    }

    public void setRequisitionNo(String requisitionNo) {
        this.requisitionNo = requisitionNo;
    }

    public String getPatAgeSex() {
        return this.getPatAge() + "/" + this.getPatGenderShortName();
    }

    public void setPatAgeSex(String patAgeSex) {
        this.patAgeSex = this.getPatAge() + "/" + this.getPatGenderShortName();
    }

    public String getOpdorWard() {
        if (this.getWardName() != null && (this.getWardName().equals("") == false)) {
            opdorWard = this.getWardName();
        } else {
            opdorWard = this.getPatDeptUnit();
        }

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

     
    public String getHospitalCode() {
        return hospitalCode;
    }

     
    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

     
    public String getHospitalName() {
    	if(hospitalName==null)
    		hospitalName="";
        return hospitalName;
    }

     
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

     
    public String getHospitalCity() {
    	if(hospitalCity == null)
    	{
    		hospitalCity = "";
    	}
        return hospitalCity;
    }

     
    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }

     
    public String getHospitalPhone() {
    	
    	if(hospitalPhone==null)
    		hospitalPhone="";
        return hospitalPhone;
    }

     
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

	public String getNablogo() {
		return nablogo;
	}

	public void setNablogo(String nablogo) {
		this.nablogo = nablogo;
	}

	public float getHeaderheight() {
		return headerheight;
	}

	public void setHeaderheight(float headerheight) {
		this.headerheight = headerheight;
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

	public String getIsreportlablabelchanged() {
		return isreportlablabelchanged;
	}

	public void setIsreportlablabelchanged(String isreportlablabelchanged) {
		this.isreportlablabelchanged = isreportlablabelchanged;
	}

	public String getIsreportcollectionlabelchanged() {
		return isreportcollectionlabelchanged;
	}

	public void setIsreportcollectionlabelchanged(
			String isreportcollectionlabelchanged) {
		this.isreportcollectionlabelchanged = isreportcollectionlabelchanged;
	}

	public String getIsreportsamplelabelrequired() {
		return isreportsamplelabelrequired;
	}

	public void setIsreportsamplelabelrequired(String isreportsamplelabelrequired) {
		this.isreportsamplelabelrequired = isreportsamplelabelrequired;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getIsgroupnameprint() {
		return isgroupnameprint;
	}

	public void setIsgroupnameprint(String isgroupnameprint) {
		this.isgroupnameprint = isgroupnameprint;
	}

	public String getIspidshow() {
		return ispidshow;
	}

	public void setIspidshow(String ispidshow) {
		this.ispidshow = ispidshow;
	}

	public String getPidno() {
		return pidno;
	}

	public void setPidno(String pidno) {
		this.pidno = pidno;
	}

	public String getRevalidatedBy() {
		return revalidatedBy;
	}

	public void setRevalidatedBy(String revalidatedBy) {
		this.revalidatedBy = revalidatedBy;
	}

	public List<String> getXmlgenereatedrequisitiondno() {
		return xmlgenereatedrequisitiondno;
	}

	public void setXmlgenereatedrequisitiondno(
			List<String> xmlgenereatedrequisitiondno) {
		this.xmlgenereatedrequisitiondno = xmlgenereatedrequisitiondno;
	}

	public String getXmlgenereatedrequisitiondnoo() {
		return xmlgenereatedrequisitiondnoo;
	}

	public void setXmlgenereatedrequisitiondnoo(String xmlgenereatedrequisitiondnoo) {
		this.xmlgenereatedrequisitiondnoo = xmlgenereatedrequisitiondnoo;
	}

	public String getEnteredby() {
		return enteredby;
	}

	public void setEnteredby(String enteredby) {
		this.enteredby = enteredby;
	}

	public String getIsenteredbyrequired() {
		return isenteredbyrequired;
	}

	public void setIsenteredbyrequired(String isenteredbyrequired) {
		this.isenteredbyrequired = isenteredbyrequired;
	}

	public String getFileuploaddata() {
		return fileuploaddata;
	}

	public void setFileuploaddata(String fileuploaddata) {
		this.fileuploaddata = fileuploaddata;
	}

	public String getFileuploadname() {
		return fileuploadname;
	}

	public void setFileuploadname(String fileuploadname) {
		this.fileuploadname = fileuploadname;
	}

	public String getIsfileuploaddadd() {
		return isfileuploaddadd;
	}

	public void setIsfileuploaddadd(String isfileuploaddadd) {
		this.isfileuploaddadd = isfileuploaddadd;
	}

	public String getIsfileuploadprint() {
		return isfileuploadprint;
	}

	public void setIsfileuploadprint(String isfileuploadprint) {
		this.isfileuploadprint = isfileuploadprint;
	}

	public String getIsfirstpagereq() {
		return isfirstpagereq;
	}

	public void setIsfirstpagereq(String isfirstpagereq) {
		this.isfirstpagereq = isfirstpagereq;
	}

	public int getIslabeldoubleline() {
		return islabeldoubleline;
	}

	public void setIslabeldoubleline(int islabeldoubleline) {
		this.islabeldoubleline = islabeldoubleline;
	}

	public String getResultenteredbycode() {
		return resultenteredbycode;
	}

	public void setResultenteredbycode(String resultenteredbycode) {
		this.resultenteredbycode = resultenteredbycode;
	}

	public String getValidatedbycode() {
		return validatedbycode;
	}

	public void setValidatedbycode(String validatedbycode) {
		this.validatedbycode = validatedbycode;
	}

	public String getEnteredbycode() {
		return enteredbycode;
	}

	public void setEnteredbycode(String enteredbycode) {
		this.enteredbycode = enteredbycode;
	}

	public String getIsaddendumfirstreportprint() {
		return isaddendumfirstreportprint;
	}

	public void setIsaddendumfirstreportprint(String isaddendumfirstreportprint) {
		this.isaddendumfirstreportprint = isaddendumfirstreportprint;
	}

	public String getIsextrafooterreqval() {
		return isextrafooterreqval;
	}

	public void setIsextrafooterreqval(String isextrafooterreqval) {
		this.isextrafooterreqval = isextrafooterreqval;
	}

	public String getIsextrafooterreq() {
		return isextrafooterreq;
	}

	public void setIsextrafooterreq(String isextrafooterreq) {
		this.isextrafooterreq = isextrafooterreq;
	}

	public String getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(String acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public String getAdvicedby() {
		return advicedby;
	}

	public void setAdvicedby(String advicedby) {
		this.advicedby = advicedby;
	}

	
	
	
 
	

}
