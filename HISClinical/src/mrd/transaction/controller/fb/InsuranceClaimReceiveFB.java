package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InsuranceClaimReceiveFB extends ActionForm
{
	private String hmode;
	private String recevingDate;
	private String recevingTimeHr;
	private String recevingTimeMin;
	private String cidNoFlag;
	private String CIDNo;
	private String ageGender;
	private String patCrNo;
	private String patAdmNo;
	private String patAdmDate;
	private String admissionDeptUnit;
	private String companyId;
	private String policyNo;
	private String remarks;
	private String patName;
	private String episodeCode;
	private String episodeVisitNo;
	
	
	private String sysHour;
	private String sysMinute;
	private String sysDate;
	
	private String CIDNoArray;
	private String recevingDateArray;
	private String recevingTimeHoursArray;
	private String recevingTimeMinuteArray;
	
	private String str_patCrNo;
	private String str_patAdmNo;
	private String str_firstName;
	private String str_middleName;
	private String str_lastName;
	
	private String[] patAdmNoArray;
	private String[] patNameArray;
	private String[] patGenderAndAgeArray;
	private String[] patCrNoArray;
	private String[] patDeptUnitArray;
	private String[] patAdmDateArray;
	private String[] episodeCodeArray;
	private String[] episodeVisitNoArray; 
	private String[] dischargeDateArray;
	
	private String dischargeDate;
	private String receivingMode;
	
	public String getReceivingMode() {
		return receivingMode;
	}

	public void setReceivingMode(String receivingMode) {
		this.receivingMode = receivingMode;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String[] getDischargeDateArray() {
		return dischargeDateArray;
	}

	public void setDischargeDateArray(String[] dischargeDateArray) {
		this.dischargeDateArray = dischargeDateArray;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.recevingDate="";
		this.recevingTimeHr="";
		this.recevingTimeMin="";
		this.patName="";
		this.patCrNo="";
		this.ageGender="";
		this.patAdmNo="";
		this.patAdmDate="";
		this.admissionDeptUnit="";
		this.companyId="-1";
		this.policyNo="";
		this.remarks="";
		this.receivingMode="";
		
		
	}

	public String[] getEpisodeCodeArray() {
		return episodeCodeArray;
	}

	public void setEpisodeCodeArray(String[] episodeCodeArray) {
		this.episodeCodeArray = episodeCodeArray;
	}

	public String[] getEpisodeVisitNoArray() {
		return episodeVisitNoArray;
	}

	public void setEpisodeVisitNoArray(String[] episodeVisitNoArray) {
		this.episodeVisitNoArray = episodeVisitNoArray;
	}

	public String[] getPatNameArray() {
		return patNameArray;
	}

	public void setPatNameArray(String[] patNameArray) {
		this.patNameArray = patNameArray;
	}

	public String[] getPatGenderAndAgeArray() {
		return patGenderAndAgeArray;
	}

	public void setPatGenderAndAgeArray(String[] patGenderAndAgeArray) {
		this.patGenderAndAgeArray = patGenderAndAgeArray;
	}

	public String[] getPatCrNoArray() {
		return patCrNoArray;
	}

	public void setPatCrNoArray(String[] patCrNoArray) {
		this.patCrNoArray = patCrNoArray;
	}

	public String[] getPatDeptUnitArray() {
		return patDeptUnitArray;
	}

	public void setPatDeptUnitArray(String[] patDeptUnitArray) {
		this.patDeptUnitArray = patDeptUnitArray;
	}

	public String[] getPatAdmDateArray() {
		return patAdmDateArray;
	}

	public void setPatAdmDateArray(String[] patAdmDateArray) {
		this.patAdmDateArray = patAdmDateArray;
	}

	public String[] getPatAdmNoArray() {
		return patAdmNoArray;
	}

	public void setPatAdmNoArray(String[] patAdmNoArray) {
		this.patAdmNoArray = patAdmNoArray;
	}

	public String getStr_patCrNo() {
		return str_patCrNo;
	}

	public void setStr_patCrNo(String str_patCrNo) {
		this.str_patCrNo = str_patCrNo;
	}

	public String getStr_patAdmNo() {
		return str_patAdmNo;
	}

	public void setStr_patAdmNo(String str_patAdmNo) {
		this.str_patAdmNo = str_patAdmNo;
	}

	public String getStr_firstName() {
		return str_firstName;
	}

	public void setStr_firstName(String str_firstName) {
		this.str_firstName = str_firstName;
	}

	public String getStr_middleName() {
		return str_middleName;
	}

	public void setStr_middleName(String str_middleName) {
		this.str_middleName = str_middleName;
	}

	public String getStr_lastName() {
		return str_lastName;
	}

	public void setStr_lastName(String str_lastName) {
		this.str_lastName = str_lastName;
	}

	public String getCIDNoArray() {
		return CIDNoArray;
	}

	public void setCIDNoArray(String noArray) {
		CIDNoArray = noArray;
	}

	public String getRecevingDateArray() {
		return recevingDateArray;
	}

	public void setRecevingDateArray(String recevingDateArray) {
		this.recevingDateArray = recevingDateArray;
	}

	public String getRecevingTimeHoursArray() {
		return recevingTimeHoursArray;
	}

	public void setRecevingTimeHoursArray(String recevingTimeHoursArray) {
		this.recevingTimeHoursArray = recevingTimeHoursArray;
	}

	public String getRecevingTimeMinuteArray() {
		return recevingTimeMinuteArray;
	}

	public void setRecevingTimeMinuteArray(String recevingTimeMinuteArray) {
		this.recevingTimeMinuteArray = recevingTimeMinuteArray;
	}

	public String getCIDNo() {
		return CIDNo;
	}

	public void setCIDNo(String no) {
		CIDNo = no;
	}

	public String getCidNoFlag() {
		return cidNoFlag;
	}

	public void setCidNoFlag(String cidNoFlag) {
		this.cidNoFlag = cidNoFlag;
	}

	public String getRecevingDate() {
		return recevingDate;
	}

	public void setRecevingDate(String recevingDate) {
		this.recevingDate = recevingDate;
	}

	public String getRecevingTimeHr() {
		return recevingTimeHr;
	}

	public void setRecevingTimeHr(String recevingTimeHr) {
		this.recevingTimeHr = recevingTimeHr;
	}

	public String getRecevingTimeMin() {
		return recevingTimeMin;
	}

	public void setRecevingTimeMin(String recevingTimeMin) {
		this.recevingTimeMin = recevingTimeMin;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getSysHour() {
		return sysHour;
	}

	public void setSysHour(String sysHour) {
		this.sysHour = sysHour;
	}

	public String getSysMinute() {
		return sysMinute;
	}

	public void setSysMinute(String sysMinute) {
		this.sysMinute = sysMinute;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getAgeGender() {
		return ageGender;
	}

	public void setAgeGender(String ageGender) {
		this.ageGender = ageGender;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getPatAdmDate() {
		return patAdmDate;
	}

	public void setPatAdmDate(String patAdmDate) {
		this.patAdmDate = patAdmDate;
	}

	public String getAdmissionDeptUnit() {
		return admissionDeptUnit;
	}

	public void setAdmissionDeptUnit(String admissionDeptUnit) {
		this.admissionDeptUnit = admissionDeptUnit;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
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
}
