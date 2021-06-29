package ehr.medicationAdvice.vo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ehr.diagnosis.vo.EHRSection_DiagnosisVO.JSONPart;
import hisglobal.utility.HelperMethods;
/**
 * Created By Nilesh Gupta
 * Date: 27.Oct.17
 * 
 * */
import hisglobal.vo.ValueObject;

public class EHRSection_Medication_AdviceVO extends ValueObject 
{
	
private String selectedSectionData;
	
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String deptCode;
	private String deptUnitCode;
	
	private String caseSummaryNotes;
	private String medication;
	private String snomdPTMedication;
	private String snomdCIDMedication;
	public String getSelectedSectionData() {
		return selectedSectionData;
	}
	public void setSelectedSectionData(String selectedSectionData) {
		this.selectedSectionData = selectedSectionData;
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
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getCaseSummaryNotes() {
		return caseSummaryNotes;
	}
	public void setCaseSummaryNotes(String caseSummaryNotes) {
		this.caseSummaryNotes = caseSummaryNotes;
	}
	
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
	}
	public String getSnomdPTMedication() {
		return snomdPTMedication;
	}
	public void setSnomdPTMedication(String snomdPTMedication) {
		this.snomdPTMedication = snomdPTMedication;
	}
	public String getSnomdCIDMedication() {
		return snomdCIDMedication;
	}
	public void setSnomdCIDMedication(String snomdCIDMedication) {
		this.snomdCIDMedication = snomdCIDMedication;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}


}
