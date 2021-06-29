/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg 
 ## Module Name					: MRD
 ## Process/Database Object Name: Medical and Fitness certificate issue process
 ## Purpose						: Medical and Fitness certificate issue process
 ## Date of Creation			: 
 ## Modification Log			:				
 ##		Modify Date				: 04-Dec-2014 
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: Amit Garg

*/


package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class MedicalCertificateFB extends CRNoFB
{
	//private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String patAdmNo;
	private String serialNo;
	private String empNo;
	private String sufferingFrom;
	private String adviceDays;
	private String fromDate;
	private String toDate;
	private String hmode;
	private String tempMode;
	private String selectedEpiCode;
	private String selectedRest;
	private String diagnosis;
	private String consultantName;
	private String chkNewAdvice;
	
	private String newSufferingFrom;
	private String newAdviceDays;
	private String newAdvDays;
	private String newFromDate;
	private String newToDate="";
	private String newEmpNo;
	private String isOverlapped;
	private String newDiagnosis;
	private String newMedicalCertificateId="";
	private String medicalCertificateId="";
	private String generationMode;
	private String deptUnitCode;
	private String fitnessDate;
	private String index;
	private String fitnessCertificateId="";
	private String medicalCertificateDesc;
	private String modSufferingFrom;
	private String modToDate="";
	private String modDiagnosis;
	private String modFromDate;
	private String modAdviceDays;
	private String remarks;
	private String unitDiagnosisCodeType;
	private String selectedCode;
	private String icdCode[];
	private String dignosisName[];
	private String hospitalDiagnosisCode[];
	private String hospitalDiagnosisName[];
	private String searchCode;
	private String searchDisease;
	private String numberOfRow="1";
	private String certificateType;
	private String hiddenStartDate[];
	private String hiddenEndDate[];
	private String hiddenAdviceDay[];
	private String hiddenConsultantName[];
	private String hiddenSufferingFrom[];
	private String hiddenMCNo[];
	private String hiddenMCName[];
	private String hiddenFitnessDate[];
	private String hiddenMaxDays[];
	
	private String flagForMCSave;
	
	private String selectedRadioFitness;
	private String selectedRadioModify;
	private String modConsultantName;
	private String sysDate;
	private String modifyIndex;
	
	private String hiddenRestStartDate[];
	private String hiddenRestEndDate[];
	private String hiddenRestAdviceDay[];
	private String hiddenRestConsultantName[];
	private String templateId="2010014";
	private String HtmlCode;
	private String consentHtmlCode;
	private String backDatedFlagMC;
	private String backDatedFlagFC;
	private String fitnessDt;
	private String extendFlag;
	private String empMappingFlag;
	private String empConsultantName;
	private String maxDaysMC;
	private String onlineEmpMaxDays;
	private String episodeCloseDate="";
	private String extendedAdviceDays;
	private String modMaxDays;
	private String selectRecord;
	
	private String startIdx;
	private String endIdx;
	private int currentPage=1;
	//Medical and fitness certificate issue process 
	private String certificateId;
	private String recordType;
	private String duration;
	private String surgery;
	private String advisedBy;
	private String givenBy;
	private String relationCode;
	private String relativeName;
	private String relativeAddr;
	private String relativeContactNo;
	private String patDesignation;
	private String patOrganisation;
	private String billNo;
	private String quantity;
	private String previousMcNo;
	private String gdt_entry_date;
	private String emp_dept;
	private String empDesig;
	public MedicalCertificateFB()
	{
		this.currentPage=1;
	}
	
	
	public String getSelectedEpiCode() {
		return selectedEpiCode;
	}
	public void setSelectedEpiCode(String selectedEpiCode) {
		this.selectedEpiCode = selectedEpiCode;
	}
	/*public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}*/
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
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getSufferingFrom() {
		return sufferingFrom;
	}
	public void setSufferingFrom(String sufferingFrom) {
		this.sufferingFrom = sufferingFrom;
	}
	public String getAdviceDays() {
		return adviceDays;
	}
	public void setAdviceDays(String adviceDays) {
		this.adviceDays = adviceDays;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSelectedRest() {
		return selectedRest;
	}
	public void setSelectedRest(String selectedRest) {
		this.selectedRest = selectedRest;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("");
		this.setSelectedEpiCode("");
		this.setEmpNo("");
		this.setSelectedRest("");
		
	}
	public String getChkNewAdvice() {
		return chkNewAdvice;
	}
	public void setChkNewAdvice(String chkNewAdvice) {
		this.chkNewAdvice = chkNewAdvice;
	}
	public String getNewSufferingFrom() {
		return newSufferingFrom;
	}
	public void setNewSufferingFrom(String newSufferingFrom) {
		this.newSufferingFrom = newSufferingFrom;
	}
	public String getNewAdviceDays() {
		return newAdviceDays;
	}
	public void setNewAdviceDays(String newAdviceDays) {
		this.newAdviceDays = newAdviceDays;
	}
	public String getNewFromDate() {
		return newFromDate;
	}
	public void setNewFromDate(String newFromDate) {
		this.newFromDate = newFromDate;
	}
	public String getNewToDate() {
		return newToDate;
	}
	public void setNewToDate(String newToDate) {
		this.newToDate = newToDate;
	}
	public String getNewEmpNo() {
		return newEmpNo;
	}
	public void setNewEmpNo(String newEmpNo) {
		this.newEmpNo = newEmpNo;
	}
	public String getNewAdvDays() {
		return newAdvDays;
	}
	public void setNewAdvDays(String newAdvDays) {
		this.newAdvDays = newAdvDays;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String getIsOverlapped() {
		return isOverlapped;
	}
	public void setIsOverlapped(String isOverlapped) {
		this.isOverlapped = isOverlapped;
	}
	public String getNewDiagnosis() {
		return newDiagnosis;
	}
	public void setNewDiagnosis(String newDiagnosis) {
		this.newDiagnosis = newDiagnosis;
	}
	public String getNewMedicalCertificateId() {
		return newMedicalCertificateId;
	}
	public void setNewMedicalCertificateId(String newMedicalCertificateId) {
		this.newMedicalCertificateId = newMedicalCertificateId;
	}
	
	public String getMedicalCertificateId() {
		return medicalCertificateId;
	}
	public void setMedicalCertificateId(String medicalCertificateId) {
		this.medicalCertificateId = medicalCertificateId;
	}
	public String getGenerationMode() {
		return generationMode;
	}
	public void setGenerationMode(String generationMode) {
		this.generationMode = generationMode;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getFitnessDate() {
		return fitnessDate;
	}


	public void setFitnessDate(String fitnessDate) {
		this.fitnessDate = fitnessDate;
	}


	public String getIndex() {
		return index;
	}


	public void setIndex(String index) {
		this.index = index;
	}


	public String getFitnessCertificateId() {
		return fitnessCertificateId;
	}


	public void setFitnessCertificateId(String fitnessCertificateId) {
		this.fitnessCertificateId = fitnessCertificateId;
	}


	public String getMedicalCertificateDesc() {
		return medicalCertificateDesc;
	}


	public void setMedicalCertificateDesc(String medicalCertificateDesc) {
		this.medicalCertificateDesc = medicalCertificateDesc;
	}


	public String getModSufferingFrom() {
		return modSufferingFrom;
	}


	public void setModSufferingFrom(String modSufferingFrom) {
		this.modSufferingFrom = modSufferingFrom;
	}


	public String getModToDate() {
		return modToDate;
	}


	public void setModToDate(String modToDate) {
		this.modToDate = modToDate;
	}


	public String getModDiagnosis() {
		return modDiagnosis;
	}


	public void setModDiagnosis(String modDiagnosis) {
		this.modDiagnosis = modDiagnosis;
	}


	public String getModFromDate() {
		return modFromDate;
	}


	public void setModFromDate(String modFromDate) {
		this.modFromDate = modFromDate;
	}


	public String getModAdviceDays() {
		return modAdviceDays;
	}


	public void setModAdviceDays(String modAdviceDays) {
		this.modAdviceDays = modAdviceDays;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getUnitDiagnosisCodeType() {
		return unitDiagnosisCodeType;
	}


	public void setUnitDiagnosisCodeType(String unitDiagnosisCodeType) {
		this.unitDiagnosisCodeType = unitDiagnosisCodeType;
	}


	public String getSelectedCode() {
		return selectedCode;
	}


	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}


	public String[] getIcdCode() {
		return icdCode;
	}


	public void setIcdCode(String[] icdCode) {
		this.icdCode = icdCode;
	}


	public String[] getDignosisName() {
		return dignosisName;
	}


	public void setDignosisName(String[] dignosisName) {
		this.dignosisName = dignosisName;
	}


	public String[] getHospitalDiagnosisCode() {
		return hospitalDiagnosisCode;
	}


	public void setHospitalDiagnosisCode(String[] hospitalDiagnosisCode) {
		this.hospitalDiagnosisCode = hospitalDiagnosisCode;
	}


	public String[] getHospitalDiagnosisName() {
		return hospitalDiagnosisName;
	}


	public void setHospitalDiagnosisName(String[] hospitalDiagnosisName) {
		this.hospitalDiagnosisName = hospitalDiagnosisName;
	}


	public String getSearchCode() {
		return searchCode;
	}


	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}


	public String getSearchDisease() {
		return searchDisease;
	}


	public void setSearchDisease(String searchDisease) {
		this.searchDisease = searchDisease;
	}


	public String getNumberOfRow() {
		return numberOfRow;
	}


	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}


	public String getCertificateType() {
		return certificateType;
	}


	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}


	public String[] getHiddenStartDate() {
		return hiddenStartDate;
	}


	public void setHiddenStartDate(String[] hiddenStartDate) {
		this.hiddenStartDate = hiddenStartDate;
	}


	public String[] getHiddenEndDate() {
		return hiddenEndDate;
	}


	public void setHiddenEndDate(String[] hiddenEndDate) {
		this.hiddenEndDate = hiddenEndDate;
	}


	public String[] getHiddenAdviceDay() {
		return hiddenAdviceDay;
	}


	public void setHiddenAdviceDay(String[] hiddenAdviceDay) {
		this.hiddenAdviceDay = hiddenAdviceDay;
	}


	public String[] getHiddenConsultantName() {
		return hiddenConsultantName;
	}


	public void setHiddenConsultantName(String[] hiddenConsultantName) {
		this.hiddenConsultantName = hiddenConsultantName;
	}


	public String getFlagForMCSave() {
		return flagForMCSave;
	}


	public void setFlagForMCSave(String flagForMCSave) {
		this.flagForMCSave = flagForMCSave;
	}


	public String getSelectedRadioFitness() {
		return selectedRadioFitness;
	}


	public void setSelectedRadioFitness(String selectedRadioFitness) {
		this.selectedRadioFitness = selectedRadioFitness;
	}


	public String[] getHiddenSufferingFrom() {
		return hiddenSufferingFrom;
	}


	public void setHiddenSufferingFrom(String[] hiddenSufferingFrom) {
		this.hiddenSufferingFrom = hiddenSufferingFrom;
	}


	public String[] getHiddenMCNo() {
		return hiddenMCNo;
	}


	public void setHiddenMCNo(String[] hiddenMCNo) {
		this.hiddenMCNo = hiddenMCNo;
	}


	public String[] getHiddenMCName() {
		return hiddenMCName;
	}


	public void setHiddenMCName(String[] hiddenMCName) {
		this.hiddenMCName = hiddenMCName;
	}


	public String getModConsultantName() {
		return modConsultantName;
	}


	public void setModConsultantName(String modConsultantName) {
		this.modConsultantName = modConsultantName;
	}


	public String getSelectedRadioModify() {
		return selectedRadioModify;
	}


	public void setSelectedRadioModify(String selectedRadioModify) {
		this.selectedRadioModify = selectedRadioModify;
	}


	public String[] getHiddenFitnessDate() {
		return hiddenFitnessDate;
	}


	public void setHiddenFitnessDate(String[] hiddenFitnessDate) {
		this.hiddenFitnessDate = hiddenFitnessDate;
	}


	public String getSysDate() {
		return sysDate;
	}


	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}


	public String getModifyIndex() {
		return modifyIndex;
	}


	public void setModifyIndex(String modifyIndex) {
		this.modifyIndex = modifyIndex;
	}


	public String[] getHiddenRestStartDate() {
		return hiddenRestStartDate;
	}


	public void setHiddenRestStartDate(String[] hiddenRestStartDate) {
		this.hiddenRestStartDate = hiddenRestStartDate;
	}


	public String[] getHiddenRestEndDate() {
		return hiddenRestEndDate;
	}


	public void setHiddenRestEndDate(String[] hiddenRestEndDate) {
		this.hiddenRestEndDate = hiddenRestEndDate;
	}


	public String[] getHiddenRestAdviceDay() {
		return hiddenRestAdviceDay;
	}


	public void setHiddenRestAdviceDay(String[] hiddenRestAdviceDay) {
		this.hiddenRestAdviceDay = hiddenRestAdviceDay;
	}


	public String[] getHiddenRestConsultantName() {
		return hiddenRestConsultantName;
	}


	public void setHiddenRestConsultantName(String[] hiddenRestConsultantName) {
		this.hiddenRestConsultantName = hiddenRestConsultantName;
	}

	public String getBackDatedFlagMC() {
		return backDatedFlagMC;
	}


	public void setBackDatedFlagMC(String backDatedFlagMC) {
		this.backDatedFlagMC = backDatedFlagMC;
	}


	public String getBackDatedFlagFC() {
		return backDatedFlagFC;
	}


	public void setBackDatedFlagFC(String backDatedFlagFC) {
		this.backDatedFlagFC = backDatedFlagFC;
	}


	public String getFitnessDt() {
		return fitnessDt;
	}


	public void setFitnessDt(String fitnessDt) {
		this.fitnessDt = fitnessDt;
	}


	public String getExtendFlag() {
		return extendFlag;
	}


	public void setExtendFlag(String extendFlag) {
		this.extendFlag = extendFlag;
	}


	public String getEmpMappingFlag() {
		return empMappingFlag;
	}


	public void setEmpMappingFlag(String empMappingFlag) {
		this.empMappingFlag = empMappingFlag;
	}


	public String getEmpConsultantName() {
		return empConsultantName;
	}


	public void setEmpConsultantName(String empConsultantName) {
		this.empConsultantName = empConsultantName;
	}


	public String getMaxDaysMC() {
		return maxDaysMC;
	}


	public void setMaxDaysMC(String maxDaysMC) {
		this.maxDaysMC = maxDaysMC;
	}


	public String getOnlineEmpMaxDays() {
		return onlineEmpMaxDays;
	}


	public void setOnlineEmpMaxDays(String onlineEmpMaxDays) {
		this.onlineEmpMaxDays = onlineEmpMaxDays;
	}


	public String getEpisodeCloseDate() {
		return episodeCloseDate;
	}


	public void setEpisodeCloseDate(String episodeCloseDate) {
		this.episodeCloseDate = episodeCloseDate;
	}


	public String getExtendedAdviceDays() {
		return extendedAdviceDays;
	}


	public void setExtendedAdviceDays(String extendedAdviceDays) {
		this.extendedAdviceDays = extendedAdviceDays;
	}


	public String[] getHiddenMaxDays() {
		return hiddenMaxDays;
	}


	public void setHiddenMaxDays(String[] hiddenMaxDays) {
		this.hiddenMaxDays = hiddenMaxDays;
	}


	public String getModMaxDays() {
		return modMaxDays;
	}


	public void setModMaxDays(String modMaxDays) {
		this.modMaxDays = modMaxDays;
	}


	public String getSelectRecord() {
		return selectRecord;
	}


	public void setSelectRecord(String selectRecord) {
		this.selectRecord = selectRecord;
	}


	public String getStartIdx() {
		return startIdx;
	}


	public void setStartIdx(String startIdx) {
		this.startIdx = startIdx;
	}


	public String getEndIdx() {
		return endIdx;
	}


	public void setEndIdx(String endIdx) {
		this.endIdx = endIdx;
	}


	public String getCertificateId() {
		return certificateId;
	}


	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}


	public String getRecordType() {
		return recordType;
	}


	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getSurgery() {
		return surgery;
	}


	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}


	public String getAdvisedBy() {
		return advisedBy;
	}


	public void setAdvisedBy(String advisedBy) {
		this.advisedBy = advisedBy;
	}


	public String getGivenBy() {
		return givenBy;
	}


	public void setGivenBy(String givenBy) {
		this.givenBy = givenBy;
	}


	public String getRelationCode() {
		return relationCode;
	}


	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}


	public String getRelativeName() {
		return relativeName;
	}


	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}


	public String getRelativeAddr() {
		return relativeAddr;
	}


	public void setRelativeAddr(String relativeAddr) {
		this.relativeAddr = relativeAddr;
	}


	public String getRelativeContactNo() {
		return relativeContactNo;
	}


	public void setRelativeContactNo(String relativeContactNo) {
		this.relativeContactNo = relativeContactNo;
	}


	public String getPatDesignation() {
		return patDesignation;
	}


	public void setPatDesignation(String patDesignation) {
		this.patDesignation = patDesignation;
	}


	public String getPatOrganisation() {
		return patOrganisation;
	}


	public void setPatOrganisation(String patOrganisation) {
		this.patOrganisation = patOrganisation;
	}


	public String getBillNo() {
		return billNo;
	}


	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getTemplateId() {
		return templateId;
	}


	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


	public String getHtmlCode() {
		return HtmlCode;
	}


	public void setHtmlCode(String htmlCode) {
		HtmlCode = htmlCode;
	}


	public String getConsentHtmlCode() {
		return consentHtmlCode;
	}


	public void setConsentHtmlCode(String consentHtmlCode) {
		this.consentHtmlCode = consentHtmlCode;
	}


	public String getPreviousMcNo() {
		return previousMcNo;
	}


	public void setPreviousMcNo(String previousMcNo) {
		this.previousMcNo = previousMcNo;
	}


	


	public String getGdt_entry_date() {
		return gdt_entry_date;
	}


	public void setGdt_entry_date(String gdt_entry_date) {
		this.gdt_entry_date = gdt_entry_date;
	}



	public String getEmp_dept() {
		return emp_dept;
	}


	public void setEmp_dept(String emp_dept) {
		this.emp_dept = emp_dept;
	}


	public String getEmpDesig() {
		return empDesig;
	}


	public void setEmpDesig(String empDesig) {
		this.empDesig = empDesig;
	}


	}
