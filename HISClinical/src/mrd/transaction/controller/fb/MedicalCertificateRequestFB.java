package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MedicalCertificateRequestFB  extends ActionForm
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String departmentUnitCode;
	private String sNo;
	private String empNo;
	private String empName;
	private String patAdmNo;
	private String sufferingFrom;
	private String adviceDays;
	private String fromDate="";
	private String toDate="";
	private String entryDate;
	private String seatId;
	private String isValid;
	private String medicalCertificateId;
	private String medicalCertificateName;
	private String medicalCertificateDesc;
	private String unitName;
	private String unitCode;
	private String patName;
	private String fitnessDate;
	private String remarks;
	private String fitnessCertificateId;
	private String maxDays;
	private String diagnosis;
	private String surgery;
	private String diagnosticCode;
	private String dignosisName;
	private String advisedBy;
	private String fitness;
	private String hmode;
	private String wardCode;
	private String strCategoryCode;
	private String admissionNo;
	private String medicalFitnessFlag="0";
	private String strPatName;
	private String selectedFitnessRow;
	

	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDiagnosis("");
		this.setSurgery("");
		this.setAdviceDays("");
		this.setToDate("");
		this.setFromDate("");
		this.setFitnessDate("");
		this.setRemarks("");
		this.setAdvisedBy("");	
		this.setFitness("");
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
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getSNo() {
		return sNo;
	}
	public void setSNo(String no) {
		sNo = no;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
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
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getMedicalCertificateId() {
		return medicalCertificateId;
	}
	public void setMedicalCertificateId(String medicalCertificateId) {
		this.medicalCertificateId = medicalCertificateId;
	}
	public String getMedicalCertificateName() {
		return medicalCertificateName;
	}
	public void setMedicalCertificateName(String medicalCertificateName) {
		this.medicalCertificateName = medicalCertificateName;
	}
	public String getMedicalCertificateDesc() {
		return medicalCertificateDesc;
	}
	public void setMedicalCertificateDesc(String medicalCertificateDesc) {
		this.medicalCertificateDesc = medicalCertificateDesc;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getFitnessDate() {
		return fitnessDate;
	}
	public void setFitnessDate(String fitnessDate) {
		this.fitnessDate = fitnessDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFitnessCertificateId() {
		return fitnessCertificateId;
	}
	public void setFitnessCertificateId(String fitnessCertificateId) {
		this.fitnessCertificateId = fitnessCertificateId;
	}
	public String getMaxDays() {
		return maxDays;
	}
	public void setMaxDays(String maxDays) {
		this.maxDays = maxDays;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getSurgery() {
		return surgery;
	}
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}
	public String getDiagnosticCode() {
		return diagnosticCode;
	}
	public void setDiagnosticCode(String diagnosticCode) {
		this.diagnosticCode = diagnosticCode;
	}
	public String getDignosisName() {
		return dignosisName;
	}
	public void setDignosisName(String dignosisName) {
		this.dignosisName = dignosisName;
	}
	public String getAdvisedBy() {
		return advisedBy;
	}
	public void setAdvisedBy(String advisedBy) {
		this.advisedBy = advisedBy;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getFitness() {
		return fitness;
	}
	public void setFitness(String fitness) {
		this.fitness = fitness;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getStrCategoryCode() {
		return strCategoryCode;
	}
	public void setStrCategoryCode(String strCategoryCode) {
		this.strCategoryCode = strCategoryCode;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getMedicalFitnessFlag() {
		return medicalFitnessFlag;
	}
	public void setMedicalFitnessFlag(String medicalFitnessFlag) {
		this.medicalFitnessFlag = medicalFitnessFlag;
	}
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
	public String getSelectedFitnessRow() {
		return selectedFitnessRow;
	}
	public void setSelectedFitnessRow(String selectedFitnessRow) {
		this.selectedFitnessRow = selectedFitnessRow;
	}
}

