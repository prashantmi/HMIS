package ehr.medicationAdvice.dataentry;

/**
 * Created By Nilesh Gupta
 * Date: 27.Oct.17
 * */
import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import registration.RegistrationConfig;

public class EHRSection_Medication_AdviceFB extends ActionForm{

	private String hmode;
	private String patCrNo;
	
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	
	private String departmentUnitCode;
	private String deskType;
	private String entryDate;

	
	private String medicationNotes;
	private String medication;
	private String snomdPTMedication;
	private String snomdCIDMedication;
	
	private String dataExist;//for changing the value of pmode
	
	private String seatId;
	private String isSetSD;
	private String ipAddress;
	private String isConfirmed;
	
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
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getDeskType() {
		return deskType;
	}
	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getMedicationNotes() {
		return medicationNotes;
	}
	public void setMedicationNotes(String medicationNotes) {
		this.medicationNotes = medicationNotes;
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
	public String getDataExist() {
		return dataExist;
	}
	public void setDataExist(String dataExist) {
		this.dataExist = dataExist;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsSetSD() {
		return isSetSD;
	}
	public void setIsSetSD(String isSetSD) {
		this.isSetSD = isSetSD;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	
}
