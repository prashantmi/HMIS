package ehr.hospitalcourse.dataentry;

/**
 * Created By Nilesh Gupta
 * Date: 27.Oct.17
 * */
import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import registration.RegistrationConfig;

public class EHRSection_HospitalCourseFB extends ActionForm{

	
	private String hmode;
	private String patCrNo;
	
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	
	private String departmentUnitCode;
	private String deskType;
	private String entryDate;
	
	//private String dischargeNotes;
	private String statusAtDischarge;
	private String snomdPTStatusAtDischarge;
	private String snomdCIdStatusAtDischarge;
	
	private String dataExist;//for changing the value of pmode
	
	private String seatId;
	private String isSetSD;
	private String ipAddress;
	private String isConfirmed;
	
	private String hospitalCourse;
	private String snomdPTHospitalCourse;
	private String snomdCIdHospitalCourse;
	
	
	
	public String getDataExist() {
		return dataExist;
	}
	public void setDataExist(String dataExist) {
		this.dataExist = dataExist;
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
	
	public String getStatusAtDischarge() {
		return statusAtDischarge;
	}
	public void setStatusAtDischarge(String statusAtDischarge) {
		this.statusAtDischarge = statusAtDischarge;
	}
	public String getSnomdPTStatusAtDischarge() {
		return snomdPTStatusAtDischarge;
	}
	public void setSnomdPTStatusAtDischarge(String snomdPTStatusAtDischarge) {
		this.snomdPTStatusAtDischarge = snomdPTStatusAtDischarge;
	}
	public String getSnomdCIdStatusAtDischarge() {
		return snomdCIdStatusAtDischarge;
	}
	public void setSnomdCIdStatusAtDischarge(String snomdCIdStatusAtDischarge) {
		this.snomdCIdStatusAtDischarge = snomdCIdStatusAtDischarge;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getIsSetSD() {
		//System.out.println("GetIsSetSD: "+isSetSD);
		return isSetSD;
	}
	public void setIsSetSD(String isSetSD) {
		//System.out.println("SetIsSetSD: "+isSetSD);
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
	/*public String getDischargeNotes() {
		return dischargeNotes;
	}
	public void setDischargeNotes(String dischargeNotes) {
		this.dischargeNotes = dischargeNotes;
	}*/
	public String getHospitalCourse() {
		return hospitalCourse;
	}
	public void setHospitalCourse(String hospitalCourse) {
		this.hospitalCourse = hospitalCourse;
	}
	public String getSnomdPTHospitalCourse() {
		return snomdPTHospitalCourse;
	}
	public void setSnomdPTHospitalCourse(String snomdPTHospitalCourse) {
		this.snomdPTHospitalCourse = snomdPTHospitalCourse;
	}
	public String getSnomdCIdHospitalCourse() {
		return snomdCIdHospitalCourse;
	}
	public void setSnomdCIdHospitalCourse(String snomdCIdHospitalCourse) {
		this.snomdCIdHospitalCourse = snomdCIdHospitalCourse;
	}
	


	
}
