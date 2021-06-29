package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class EpisodeAllergyFB extends CRNoFB {
	
	private String patCrNo;
	private String hmode;
	private String allergyCode;
	private String reason[];
	private String remarks[];
	private String sensitivity[];
	private String selectedAllergy[];
	private String allergyTableId;
	private String reasonDetalRowId;
	private String hiddenAllergyCode[];
	private String numberOfTables="0";
	private String[] allergySite;
	private String[] allergySymtoms;
	private String advice;
	private String admissionNo;
	private String allergyTypeName;
	private String allergyName;
	private String selectedAllergyCode;
	private String selectedAllergyTypeName;
	private String selectedAllergyName;
	private String selectedAllergyID;
	private String selAllergyTypeForSymp;
	private String adviceHeader;
	private String adviceText;
	private String[] duration;
	private String[] adviceChk;
	
	private String year;
	private String month;
	private String day;
	private String calculatedDays;;
	
	private String viewRemarks;
	private String revokeAllergyId;
	private String[] revokeRemarks;
	private String index;
	private String hiddenPatDeadStatus;
	
	private String patDOB; // added by Pawan Kumar B N on 04-11-2011
	private String patAge; // added by Pawan Kumar B N on 04-11-2011
	private String sysDate; // added by Pawan Kumar B N on 04-11-2011
	

	public String getHiddenPatDeadStatus() {
		return hiddenPatDeadStatus;
	}

	public void setHiddenPatDeadStatus(String hiddenPatDeadStatus) {
		this.hiddenPatDeadStatus = hiddenPatDeadStatus;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getViewRemarks() {
		return viewRemarks;
	}

	public void setViewRemarks(String viewRemarks) {
		this.viewRemarks = viewRemarks;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCalculatedDays() {
		return calculatedDays;
	}

	public void setCalculatedDays(String calculatedDays) {
		this.calculatedDays = calculatedDays;
	}

	public String getAdviceHeader() {
		return adviceHeader;
	}

	public void setAdviceHeader(String adviceHeader) {
		this.adviceHeader = adviceHeader;
	}

	public String getAdviceText() {
		return adviceText;
	}

	public void setAdviceText(String adviceText) {
		this.adviceText = adviceText;
	}

	public String getSelectedAllergyName() {
		return selectedAllergyName;
	}

	public void setSelectedAllergyName(String selectedAllergyName) {
		this.selectedAllergyName = selectedAllergyName;
	}

	public String getSelectedAllergyCode() {
		return selectedAllergyCode;
	}

	public void setSelectedAllergyCode(String selectedAllergyCode) {
		this.selectedAllergyCode = selectedAllergyCode;
	}

	public String getNumberOfTables() {
		return numberOfTables;
	}

	public void setNumberOfTables(String numberOfTables) {
		this.numberOfTables = numberOfTables;
	}

	public String[] getHiddenAllergyCode() {
		return hiddenAllergyCode;
	}

	public void setHiddenAllergyCode(String[] hiddenAllergyCode) {
		this.hiddenAllergyCode = hiddenAllergyCode;
	}

	public String getReasonDetalRowId() {
		return reasonDetalRowId;
	}

	public void setReasonDetalRowId(String reasonDetalRowId) {
		this.reasonDetalRowId = reasonDetalRowId;
	}

	public String getAllergyTableId() {
		return allergyTableId;
	}

	public void setAllergyTableId(String allergyTableId) {
		this.allergyTableId = allergyTableId;
	}

	public String[] getSelectedAllergy() {
		return selectedAllergy;
	}

	public void setSelectedAllergy(String[] selectedAllergy) {
		this.selectedAllergy = selectedAllergy;
	}

	
	

	public String[] getReason() {
		return reason;
	}

	public void setReason(String[] reason) {
		this.reason = reason;
	}

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String[] getSensitivity() {
		return sensitivity;
	}

	public void setSensitivity(String[] sensitivity) {
		this.sensitivity = sensitivity;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getAllergyCode() {
		return allergyCode;
	}

	public void setAllergyCode(String allergyCode) {
		this.allergyCode = allergyCode;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getAllergyTypeName() {
		return allergyTypeName;
	}

	public void setAllergyTypeName(String allergyTypeName) {
		this.allergyTypeName = allergyTypeName;
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

	public String getSelectedAllergyID() {
		return selectedAllergyID;
	}

	public void setSelectedAllergyID(String selectedAllergyID) {
		this.selectedAllergyID = selectedAllergyID;
	}

	public String getSelectedAllergyTypeName() {
		return selectedAllergyTypeName;
	}

	public void setSelectedAllergyTypeName(String selectedAllergyTypeName) {
		this.selectedAllergyTypeName = selectedAllergyTypeName;
	}

	public String[] getAllergySite() {
		return allergySite;
	}

	public void setAllergySite(String[] allergySite) {
		this.allergySite = allergySite;
	}

	public String[] getAllergySymtoms() {
		return allergySymtoms;
	}

	public void setAllergySymtoms(String[] allergySymtoms) {
		this.allergySymtoms = allergySymtoms;
	}

	public String getSelAllergyTypeForSymp() {
		return selAllergyTypeForSymp;
	}

	public void setSelAllergyTypeForSymp(String selAllergyTypeForSymp) {
		this.selAllergyTypeForSymp = selAllergyTypeForSymp;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setAllergyCode("");
		this.setAllergyName("");
		this.setAllergyTypeName("");
		this.setAllergyTableId("");
		this.setAllergySymtoms(null);
		this.setAllergySite(null);
		this.setAdviceText("");
		this.setAdviceHeader("-1");
		this.setNumberOfTables("0");
		this.reason = null;
		this.sensitivity = null;
		this.selectedAllergy = null;
		this.allergySite = null;
		this.remarks = null;
		this.duration = null;
		this.adviceChk = null;
		this.allergySymtoms = null;		
	}

	public String[] getDuration() {
		return duration;
	}

	public void setDuration(String[] duration) {
		this.duration = duration;
	}

	public String[] getAdviceChk() {
		return adviceChk;
	}

	public void setAdviceChk(String[] adviceChk) {
		this.adviceChk = adviceChk;
	}

	public String getRevokeAllergyId() {
		return revokeAllergyId;
	}

	public void setRevokeAllergyId(String revokeAllergyId) {
		this.revokeAllergyId = revokeAllergyId;
	}

	public String[] getRevokeRemarks() {
		return revokeRemarks;
	}

	public void setRevokeRemarks(String[] revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}

	public String getPatDOB() {
		return patDOB;
	}

	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	
	
}
