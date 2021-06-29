package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class FitnessCertificateFB extends CRNoFB
{
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String empNo;
	private String patAdmNo;
	private String sufferingFrom;
	private String fitnessDate;
	private String hmode;
	private String selectedEpiCode;
	private String selectedMC;
	private String adviceDays;
	private String fromDate;
	private String toDate;
	private String generationMode;
	private String deptUnitCode;
	private String fitnessCertificateId;
	
	
	public String getFitnessCertificateId() {
		return fitnessCertificateId;
	}
	public void setFitnessCertificateId(String fitnessCertificateId) {
		this.fitnessCertificateId = fitnessCertificateId;
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
	public String getSelectedEpiCode() {
		return selectedEpiCode;
	}
	public void setSelectedEpiCode(String selectedEpiCode) {
		this.selectedEpiCode = selectedEpiCode;
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
	public String getFitnessDate() {
		return fitnessDate;
	}
	public void setFitnessDate(String fitnessDate) {
		this.fitnessDate = fitnessDate;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSelectedMC() {
		return selectedMC;
	}
	public void setSelectedMC(String selectedMC) {
		this.selectedMC = selectedMC;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("");
		this.setSelectedEpiCode("");
		this.setSelectedMC("");
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
}
