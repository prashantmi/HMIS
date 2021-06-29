package hisglobal.vo;

import java.util.List;

public class Inv_RequisitionRaisingEpisodeVO extends ValueObject{
	
	private String patCRNo;
	private String patEpisodeCode;
	private String patVisitDate;
    private String patVisitNo;
	private String patDeptUnitCode;
    private String patDeptUnit;
    private String patMLC;
	private String patMLCNo;
	private String patStatus;
	private String patEpisodeTypeCode;
	private String patVisitTypeCode;
	private String diagnosis;
	
	//IPD details
	
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	private String admittedDepartmentCode;
	private String admittedDepartmentName;
	private String patUnit;
	private String patUnitCode;
	private String patAdmissionNo;
	private String patWardName;
	private String patWardCode;
	private String patRoomNo;
	private String patRoomName; 
	private String bedCode;
	private String bedName;
	private String hospitalCode;
    
	public String getAdmittedDepartmentCode() {
		return admittedDepartmentCode;
	}
	public void setAdmittedDepartmentCode(String admittedDepartmentCode) {
		this.admittedDepartmentCode = admittedDepartmentCode;
	}
	public String getAdmittedDepartmentName() {
		return admittedDepartmentName;
	}
	public void setAdmittedDepartmentName(String admittedDepartmentName) {
		this.admittedDepartmentName = admittedDepartmentName;
	}
	public String getPatUnit() {
		return patUnit;
	}
	public void setPatUnit(String patUnit) {
		this.patUnit = patUnit;
	}
	public String getPatUnitCode() {
		return patUnitCode;
	}
	public void setPatUnitCode(String patUnitCode) {
		this.patUnitCode = patUnitCode;
	}
	public String getPatAdmissionNo() {
		return patAdmissionNo;
	}
	public void setPatAdmissionNo(String patAdmissionNo) {
		this.patAdmissionNo = patAdmissionNo;
	}
	public String getPatWardName() {
		return patWardName;
	}
	public void setPatWardName(String patWardName) {
		this.patWardName = patWardName;
	}
	public String getPatWardCode() {
		return patWardCode;
	}
	public void setPatWardCode(String patWardCode) {
		this.patWardCode = patWardCode;
	}
	public String getPatRoomNo() {
		return patRoomNo;
	}
	public void setPatRoomNo(String patRoomNo) {
		this.patRoomNo = patRoomNo;
	}
	public String getPatRoomName() {
		return patRoomName;
	}
	public void setPatRoomName(String patRoomName) {
		this.patRoomName = patRoomName;
	}
	public String getBedCode() {
		return bedCode;
	}
	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public String getPatEpisodeTypeCode() {
		return patEpisodeTypeCode;
	}
	public void setPatEpisodeTypeCode(String patEpisodeTypeCode) {
		this.patEpisodeTypeCode = patEpisodeTypeCode;
	}
	public String getPatVisitTypeCode() {
		return patVisitTypeCode;
	}
	public void setPatVisitTypeCode(String patVisitTypeCode) {
		this.patVisitTypeCode = patVisitTypeCode;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getPatMLC() {
		return patMLC;
	}
	public void setPatMLC(String patMLC) {
		this.patMLC = patMLC;
	}
	public String getPatMLCNo() {
		return patMLCNo;
	}
	public void setPatMLCNo(String patMLCNo) {
		this.patMLCNo = patMLCNo;
	}
	public String getPatDeptUnit() {
		return patDeptUnit;
	}
	public void setPatDeptUnit(String patDeptUnit) {
		this.patDeptUnit = patDeptUnit;
	}
	public String getPatDeptUnitCode() {
		return patDeptUnitCode;
	}
	public void setPatDeptUnitCode(String patDeptUnitCode) {
		this.patDeptUnitCode = patDeptUnitCode;
	}
	public String getPatEpisodeCode() {
		return patEpisodeCode;
	}
	public void setPatEpisodeCode(String patEpisodeCode) {
		this.patEpisodeCode = patEpisodeCode;
	}
	public String getPatVisitDate() {
		return patVisitDate;
	}
	public void setPatVisitDate(String patVisitDate) {
		this.patVisitDate = patVisitDate;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
}
