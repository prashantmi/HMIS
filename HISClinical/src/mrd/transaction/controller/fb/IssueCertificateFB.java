package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class IssueCertificateFB extends ActionForm
{
	private String certificateId;
	private String slNo;
	private String recordType;
	private String handoverTo;
	private String isReceiptTaken;
	private String remarks;
	private String hmode;
	private String patCrNo;
	private String certificateType;
	private String selectedGenMC;
	private String selectedGenFC;
	private String selectedDupMC;
	private String selectedDupFC;
	private String sufferingFrom;
	private String adviceDays;
	private String fromDate;
	private String toDate;
	private String fitnessCertificateId;
	private String fitnessCertificateDesc;
	private String fitnessDate;
	private String medicalCertificateId;
	private String medicalCertificateDesc;
	private String dupCertificateType;
	
	
	private String hiddenMCSufferingFrom[];
	private String hiddenMCAdviceDays[];
	private String hiddenMCFromDate[];
	private String hiddenMCToDate[];
	private String hiddenMCMedicalCertificateDesc[];
	
	private String hiddenDMCSufferingFrom[];
	private String hiddenDMCAdviceDays[];
	private String hiddenDMCFromDate[];
	private String hiddenDMCToDate[];
	private String hiddenDMCMedicalCertificateDesc[];
	
	private String hiddenFCFitnessCertificateDesc[];
	private String hiddenFCFitnessDate[];
	private String hiddenFCSufferingFrom[];
	private String hiddenFCMedicalCertificateDesc[];
	
	private String hiddenDFCFitnessCertificateDesc[];
	private String hiddenDFCFitnessDate[];
	private String hiddenDFCSufferingFrom[];
	private String hiddenDFCMedicalCertificateDesc[];
	private String tempMode;
	
	
	
	
	public String getDupCertificateType() {
		return dupCertificateType;
	}
	public void setDupCertificateType(String dupCertificateType) {
		this.dupCertificateType = dupCertificateType;
	}
	public String getFitnessCertificateId() {
		return fitnessCertificateId;
	}
	public void setFitnessCertificateId(String fitnessCertificateId) {
		this.fitnessCertificateId = fitnessCertificateId;
	}
	public String getFitnessDate() {
		return fitnessDate;
	}
	public void setFitnessDate(String fitnessDate) {
		this.fitnessDate = fitnessDate;
	}
	public String getMedicalCertificateId() {
		return medicalCertificateId;
	}
	public void setMedicalCertificateId(String medicalCertificateId) {
		this.medicalCertificateId = medicalCertificateId;
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
	public String getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	public String getIsReceiptTaken() {
		return isReceiptTaken;
	}
	public void setIsReceiptTaken(String isReceiptTaken) {
		this.isReceiptTaken = isReceiptTaken;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
	//	this.setPatCrNo("");
		this.setSelectedDupFC("");
		this.setSelectedDupMC("");
		this.setSelectedGenFC("");
		this.setSelectedGenMC("");
		
		
	
	}
	public String getSelectedGenMC() {
		return selectedGenMC;
	}
	public void setSelectedGenMC(String selectedGenMC) {
		this.selectedGenMC = selectedGenMC;
	}
	public String getSelectedGenFC() {
		return selectedGenFC;
	}
	public void setSelectedGenFC(String selectedGenFC) {
		this.selectedGenFC = selectedGenFC;
	}
	public String getSelectedDupMC() {
		return selectedDupMC;
	}
	public void setSelectedDupMC(String selectedDupMC) {
		this.selectedDupMC = selectedDupMC;
	}
	public String getSelectedDupFC() {
		return selectedDupFC;
	}
	public void setSelectedDupFC(String selectedDupFC) {
		this.selectedDupFC = selectedDupFC;
	}
	public String getMedicalCertificateDesc() {
		return medicalCertificateDesc;
	}
	public void setMedicalCertificateDesc(String medicalCertificateDesc) {
		this.medicalCertificateDesc = medicalCertificateDesc;
	}
	public String getFitnessCertificateDesc() {
		return fitnessCertificateDesc;
	}
	public void setFitnessCertificateDesc(String fitnessCertificateDesc) {
		this.fitnessCertificateDesc = fitnessCertificateDesc;
	}
	public String[] getHiddenMCSufferingFrom() {
		return hiddenMCSufferingFrom;
	}
	public void setHiddenMCSufferingFrom(String[] hiddenMCSufferingFrom) {
		this.hiddenMCSufferingFrom = hiddenMCSufferingFrom;
	}
	public String[] getHiddenMCAdviceDays() {
		return hiddenMCAdviceDays;
	}
	public void setHiddenMCAdviceDays(String[] hiddenMCAdviceDays) {
		this.hiddenMCAdviceDays = hiddenMCAdviceDays;
	}
	public String[] getHiddenMCFromDate() {
		return hiddenMCFromDate;
	}
	public void setHiddenMCFromDate(String[] hiddenMCFromDate) {
		this.hiddenMCFromDate = hiddenMCFromDate;
	}
	public String[] getHiddenMCToDate() {
		return hiddenMCToDate;
	}
	public void setHiddenMCToDate(String[] hiddenMCToDate) {
		this.hiddenMCToDate = hiddenMCToDate;
	}
	public String[] getHiddenMCMedicalCertificateDesc() {
		return hiddenMCMedicalCertificateDesc;
	}
	public void setHiddenMCMedicalCertificateDesc(
			String[] hiddenMCMedicalCertificateDesc) {
		this.hiddenMCMedicalCertificateDesc = hiddenMCMedicalCertificateDesc;
	}
	public String[] getHiddenDMCSufferingFrom() {
		return hiddenDMCSufferingFrom;
	}
	public void setHiddenDMCSufferingFrom(String[] hiddenDMCSufferingFrom) {
		this.hiddenDMCSufferingFrom = hiddenDMCSufferingFrom;
	}
	public String[] getHiddenDMCAdviceDays() {
		return hiddenDMCAdviceDays;
	}
	public void setHiddenDMCAdviceDays(String[] hiddenDMCAdviceDays) {
		this.hiddenDMCAdviceDays = hiddenDMCAdviceDays;
	}
	public String[] getHiddenDMCFromDate() {
		return hiddenDMCFromDate;
	}
	public void setHiddenDMCFromDate(String[] hiddenDMCFromDate) {
		this.hiddenDMCFromDate = hiddenDMCFromDate;
	}
	public String[] getHiddenDMCToDate() {
		return hiddenDMCToDate;
	}
	public void setHiddenDMCToDate(String[] hiddenDMCToDate) {
		this.hiddenDMCToDate = hiddenDMCToDate;
	}
	public String[] getHiddenDMCMedicalCertificateDesc() {
		return hiddenDMCMedicalCertificateDesc;
	}
	public void setHiddenDMCMedicalCertificateDesc(
			String[] hiddenDMCMedicalCertificateDesc) {
		this.hiddenDMCMedicalCertificateDesc = hiddenDMCMedicalCertificateDesc;
	}
	public String[] getHiddenFCFitnessCertificateDesc() {
		return hiddenFCFitnessCertificateDesc;
	}
	public void setHiddenFCFitnessCertificateDesc(
			String[] hiddenFCFitnessCertificateDesc) {
		this.hiddenFCFitnessCertificateDesc = hiddenFCFitnessCertificateDesc;
	}
	public String[] getHiddenFCFitnessDate() {
		return hiddenFCFitnessDate;
	}
	public void setHiddenFCFitnessDate(String[] hiddenFCFitnessDate) {
		this.hiddenFCFitnessDate = hiddenFCFitnessDate;
	}
	public String[] getHiddenFCSufferingFrom() {
		return hiddenFCSufferingFrom;
	}
	public void setHiddenFCSufferingFrom(String[] hiddenFCSufferingFrom) {
		this.hiddenFCSufferingFrom = hiddenFCSufferingFrom;
	}
	public String[] getHiddenFCMedicalCertificateDesc() {
		return hiddenFCMedicalCertificateDesc;
	}
	public void setHiddenFCMedicalCertificateDesc(
			String[] hiddenFCMedicalCertificateDesc) {
		this.hiddenFCMedicalCertificateDesc = hiddenFCMedicalCertificateDesc;
	}
	public String[] getHiddenDFCFitnessCertificateDesc() {
		return hiddenDFCFitnessCertificateDesc;
	}
	public void setHiddenDFCFitnessCertificateDesc(
			String[] hiddenDFCFitnessCertificateDesc) {
		this.hiddenDFCFitnessCertificateDesc = hiddenDFCFitnessCertificateDesc;
	}
	public String[] getHiddenDFCFitnessDate() {
		return hiddenDFCFitnessDate;
	}
	public void setHiddenDFCFitnessDate(String[] hiddenDFCFitnessDate) {
		this.hiddenDFCFitnessDate = hiddenDFCFitnessDate;
	}
	public String[] getHiddenDFCSufferingFrom() {
		return hiddenDFCSufferingFrom;
	}
	public void setHiddenDFCSufferingFrom(String[] hiddenDFCSufferingFrom) {
		this.hiddenDFCSufferingFrom = hiddenDFCSufferingFrom;
	}
	public String[] getHiddenDFCMedicalCertificateDesc() {
		return hiddenDFCMedicalCertificateDesc;
	}
	public void setHiddenDFCMedicalCertificateDesc(
			String[] hiddenDFCMedicalCertificateDesc) {
		this.hiddenDFCMedicalCertificateDesc = hiddenDFCMedicalCertificateDesc;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	
	
}
