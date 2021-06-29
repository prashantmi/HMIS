package opd.transaction.controller.fb;

import registration.controller.fb.CRNoFB;

public class OpdPatientProfileFB extends CRNoFB {
	private String patProfileId;
	private String serialNo;
	private String departmentCode;
	private String departmentUnitCode;
	private String patProfileHeader;
	private String patProfileRemark;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String lastModifyDate;
	private String lastModifiedSeatID;
	private String effectiveFrom="";
	private String effectiveTo="";
	private String selectedPatCrNo;
	private String hmode;
	private String profileType;
	private String selectedEpisode;
	private String selectedMenu;
	private String diagnosticCode[]; 
	private String dignosisName[];
	private String diagnosticTypeName[];
	private String remarks[];
	private String selectedRow[];
	private String selectedProfileSerialNo;
	private String selectedProfileId;
	private String modifyChoice;
	private String selectedEffectiveFrom[];
	private String selectedEffectiveTo[];
	private String htmlValue;
	private String fromDate="";
	private String toDate="";
	private String flag="0";
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getModifyChoice() {
		return modifyChoice;
	}
	public void setModifyChoice(String modifyChoice) {
		this.modifyChoice = modifyChoice;
	}
	public String getSelectedProfileId() {
		return selectedProfileId;
	}
	public void setSelectedProfileId(String selectedProfileId) {
		this.selectedProfileId = selectedProfileId;
	}
	public String getSelectedProfileSerialNo() {
		return selectedProfileSerialNo;
	}
	public void setSelectedProfileSerialNo(String selectedProfileSerialNo) {
		this.selectedProfileSerialNo = selectedProfileSerialNo;
	}
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public String[] getDiagnosticCode() {
		return diagnosticCode;
	}
	public void setDiagnosticCode(String[] diagnosticCode) {
		this.diagnosticCode = diagnosticCode;
	}
	public String[] getDignosisName() {
		return dignosisName;
	}
	public void setDignosisName(String[] dignosisName) {
		this.dignosisName = dignosisName;
	}
	public String[] getDiagnosticTypeName() {
		return diagnosticTypeName;
	}
	public void setDiagnosticTypeName(String[] diagnosticTypeName) {
		this.diagnosticTypeName = diagnosticTypeName;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getSelectedMenu() {
		return selectedMenu;
	}
	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}
	public String getSelectedEpisode() {
		return selectedEpisode;
	} 
	public void setSelectedEpisode(String selectedEpisode) {
		this.selectedEpisode = selectedEpisode;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatProfileId() {
		return patProfileId;
	}
	public void setPatProfileId(String patProfileId) {
		this.patProfileId = patProfileId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getPatProfileHeader() {
		return patProfileHeader;
	}
	public void setPatProfileHeader(String patProfileHeader) {
		this.patProfileHeader = patProfileHeader;
	}
	public String getPatProfileRemark() {
		return patProfileRemark;
	}
	public void setPatProfileRemark(String patProfileRemark) {
		this.patProfileRemark = patProfileRemark;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifiedSeatID() {
		return lastModifiedSeatID;
	}
	public void setLastModifiedSeatID(String lastModifiedSeatID) {
		this.lastModifiedSeatID = lastModifiedSeatID;
	}
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
	public void reset() {
		this.setDepartmentCode("");
		this.setDepartmentUnitCode("");
		this.setEffectiveFrom("");
		this.setEffectiveTo("");
		this.setEntryDate("");
		this.setIsValid("");
		this.setLastModifiedSeatID("");
		this.setLastModifyDate("");
		this.setPatCrNo("");
		this.setPatProfileId("");
		this.setPatProfileRemark("");
		this.setSeatId("");
		this.setSerialNo("");
		this.setHmode("");
		this.setSelectedEpisode("");
		this.setDiagnosticCode(new String[]{""});
		this.setDignosisName(new String[]{""});
		this.setDiagnosticTypeName(new String[]{""});
		this.setRemarks(new String[]{""});
		this.setSelectedRow(new String[]{""});
		this.setSelectedProfileSerialNo("");
		this.setSelectedProfileId("");
		this.setModifyChoice("");
		this.setSelectedEffectiveFrom(new String[]{""});
		this.setSelectedEffectiveTo(new String[]{""});
		this.setHtmlValue("");
		this.setFromDate("");
		this.setToDate("");
		
	}
	public String getSelectedPatCrNo() {
		return selectedPatCrNo;
	}
	public void setSelectedPatCrNo(String selectedPatCrNo) {
		this.selectedPatCrNo = selectedPatCrNo;
	}
	public String[] getSelectedEffectiveFrom() {
		return selectedEffectiveFrom;
	}
	public void setSelectedEffectiveFrom(String[] selectedEffectiveFrom) {
		this.selectedEffectiveFrom = selectedEffectiveFrom;
	}
	public String[] getSelectedEffectiveTo() {
		return selectedEffectiveTo;
	}
	public void setSelectedEffectiveTo(String[] selectedEffectiveTo) {
		this.selectedEffectiveTo = selectedEffectiveTo;
	}
	public String getHtmlValue() {
		return htmlValue;
	}
	public void setHtmlValue(String htmlValue) {
		this.htmlValue = htmlValue;
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
