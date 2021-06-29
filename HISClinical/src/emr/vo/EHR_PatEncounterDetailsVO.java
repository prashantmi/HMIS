/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : OPD IPD
 ## Process/Database Object Name	    : PATIENT CLINICAL DOCUMENTS
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2017 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package emr.vo;
import java.util.List;

import hisglobal.vo.ValueObject;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.followup.vo.EHRSection_FollowupVO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import ehr.visitreason.vo.EHRSection_VisitReasonVO;
import ehr.hospitalcourse.vo.EHRSection_HospitalCourseVO;

public class EHR_PatEncounterDetailsVO extends ValueObject {

	private String username;
	private String hmode;
	private String patCrNo;
	private String serialNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String empNo;
	private String loginSeatId;
    private String departmentUnitCode;
	private String deskType;   
	private String entryDate;
	private String isConfirmed;
	private String isPatDead;
	
	private List <EHRSection_DiagnosisVO> listSaveAllDiagnosis;
	private List <EHRSection_TreatmentVO> listSaveAllTreatment;
	private EHRSection_VisitReasonVO listSaveAllVisitReason;
	private EHRSection_FollowupVO listSaveAllFollowUp;
	private EHRSection_StatusAtDischargeVO listSaveAllDischarge;
	
	private EHRSection_HospitalCourseVO listSaveAllHospitalCourse;
	//private List <EHRSection_InvestigationVO> listSaveAllVisitInvestigation;
	
	
	
	
	private String isEpisodeAlreadyOpen;
	private String episodeIsOpen;
	private String episodeNextVisitDate;
	//private String visitSummary;
	private String visitNotes;		// Visit Progress Notes
	
	private String nextVisitCriteria;
	private String nextVisitDuration;
	private String nextVisitDurationCriteria;

	private String seatId;

	private String episodeTypeCode;
	private String episodeDate;
	private String episodeCloseDate;
	private String episodeCloseType;
	private String triageDuration;

	private String isDiagnosisDtlExists;
	
	private String targetFunction;

	private String ehrVisitReason;
	private String visitNo;


	private String snomdPTVisitReason;
	private String snomdCIdVisitReason;
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
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getLoginSeatId() {
		return loginSeatId;
	}
	public void setLoginSeatId(String loginSeatId) {
		this.loginSeatId = loginSeatId;
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
	public String getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	public String getIsPatDead() {
		return isPatDead;
	}
	public void setIsPatDead(String isPatDead) {
		this.isPatDead = isPatDead;
	}
	public String getIsEpisodeAlreadyOpen() {
		return isEpisodeAlreadyOpen;
	}
	public void setIsEpisodeAlreadyOpen(String isEpisodeAlreadyOpen) {
		this.isEpisodeAlreadyOpen = isEpisodeAlreadyOpen;
	}
	public String getEpisodeIsOpen() {
		return episodeIsOpen;
	}
	public void setEpisodeIsOpen(String episodeIsOpen) {
		this.episodeIsOpen = episodeIsOpen;
	}
	public String getEpisodeNextVisitDate() {
		return episodeNextVisitDate;
	}
	public void setEpisodeNextVisitDate(String episodeNextVisitDate) {
		this.episodeNextVisitDate = episodeNextVisitDate;
	}
	public String getVisitNotes() {
		return visitNotes;
	}
	public void setVisitNotes(String visitNotes) {
		this.visitNotes = visitNotes;
	}
	public String getNextVisitCriteria() {
		return nextVisitCriteria;
	}
	public void setNextVisitCriteria(String nextVisitCriteria) {
		this.nextVisitCriteria = nextVisitCriteria;
	}
	public String getNextVisitDuration() {
		return nextVisitDuration;
	}
	public void setNextVisitDuration(String nextVisitDuration) {
		this.nextVisitDuration = nextVisitDuration;
	}
	public String getNextVisitDurationCriteria() {
		return nextVisitDurationCriteria;
	}
	public void setNextVisitDurationCriteria(String nextVisitDurationCriteria) {
		this.nextVisitDurationCriteria = nextVisitDurationCriteria;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEpisodeTypeCode() {
		return episodeTypeCode;
	}
	public void setEpisodeTypeCode(String episodeTypeCode) {
		this.episodeTypeCode = episodeTypeCode;
	}
	public String getEpisodeDate() {
		return episodeDate;
	}
	public void setEpisodeDate(String episodeDate) {
		this.episodeDate = episodeDate;
	}
	public String getEpisodeCloseDate() {
		return episodeCloseDate;
	}
	public void setEpisodeCloseDate(String episodeCloseDate) {
		this.episodeCloseDate = episodeCloseDate;
	}
	public String getEpisodeCloseType() {
		return episodeCloseType;
	}
	public void setEpisodeCloseType(String episodeCloseType) {
		this.episodeCloseType = episodeCloseType;
	}
	public String getTriageDuration() {
		return triageDuration;
	}
	public void setTriageDuration(String triageDuration) {
		this.triageDuration = triageDuration;
	}
	public String getIsDiagnosisDtlExists() {
		return isDiagnosisDtlExists;
	}
	public void setIsDiagnosisDtlExists(String isDiagnosisDtlExists) {
		this.isDiagnosisDtlExists = isDiagnosisDtlExists;
	}
	public String getTargetFunction() {
		return targetFunction;
	}
	public void setTargetFunction(String targetFunction) {
		this.targetFunction = targetFunction;
	}
	
	public String getSnomdPTVisitReason() {
		return snomdPTVisitReason;
	}
	public void setSnomdPTVisitReason(String snomdPTVisitReason) {
		this.snomdPTVisitReason = snomdPTVisitReason;
	}
	public String getSnomdCIdVisitReason() {
		return snomdCIdVisitReason;
	}
	public void setSnomdCIdVisitReason(String snomdCIdVisitReason) {
		this.snomdCIdVisitReason = snomdCIdVisitReason;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getEhrVisitReason() {
		return ehrVisitReason;
	}
	public void setEhrVisitReason(String ehrVisitReason) {
		this.ehrVisitReason = ehrVisitReason;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List <EHRSection_DiagnosisVO> getListSaveAllDiagnosis() {
		return listSaveAllDiagnosis;
	}
	public void setListSaveAllDiagnosis(List <EHRSection_DiagnosisVO> listSaveAllDiagnosis) {
		this.listSaveAllDiagnosis = listSaveAllDiagnosis;
	}
	public List <EHRSection_TreatmentVO> getListSaveAllTreatment() {
		return listSaveAllTreatment;
	}
	public void setListSaveAllTreatment(List <EHRSection_TreatmentVO> listSaveAllTreatment) {
		this.listSaveAllTreatment = listSaveAllTreatment;
	}
	
	
	public EHRSection_VisitReasonVO getListSaveAllVisitReason() {
		return listSaveAllVisitReason;
	}
	public void setListSaveAllVisitReason(EHRSection_VisitReasonVO listSaveAllVisitReason) {
		this.listSaveAllVisitReason = listSaveAllVisitReason;
	}
	public EHRSection_FollowupVO getListSaveAllFollowUp() {
		return listSaveAllFollowUp;
	}
	public void setListSaveAllFollowUp(EHRSection_FollowupVO listSaveAllFollowUp) {
		this.listSaveAllFollowUp = listSaveAllFollowUp;
	}
	/* Added By Nilesh Gupta*/
	public EHRSection_StatusAtDischargeVO getListSaveAllDischarge() {
		return listSaveAllDischarge;
	}
	public void setListSaveAllDischarge(
			EHRSection_StatusAtDischargeVO listSaveAllDischarge) {
		this.listSaveAllDischarge = listSaveAllDischarge;
	}
	
	/* Added By Nilesh Vasu*/
	public EHRSection_HospitalCourseVO  getListSaveAllHospitalCourse() {
		return listSaveAllHospitalCourse;
	}
	public void setListSaveAllHospitalCourse(
			EHRSection_HospitalCourseVO  listSaveAllHospitalCourse) {
		this.listSaveAllHospitalCourse = listSaveAllHospitalCourse;
	}
}
