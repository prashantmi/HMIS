package hisglobal.vo;

public class Inv_EpisodeVO extends ValueObject{
	private String episodeCode;
    private String episodeVisitNo;
	private String episodeVisitType;
    private String episodeVisitTypeCode;
    private String episodeType;
    private String episodeTypeCode;
    private String department;
	private String patCrNo;
	private String departmentCode;
	private String departmentUnit;
    private String departmentUnitCode;
    
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnit() {
		return departmentUnit;
	}
	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
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
	public String getEpisodeType() {
		return episodeType;
	}
	public void setEpisodeType(String episodeType) {
		this.episodeType = episodeType;
	}
	public String getEpisodeTypeCode() {
		return episodeTypeCode;
	}
	public void setEpisodeTypeCode(String episodeTypeCode) {
		this.episodeTypeCode = episodeTypeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getEpisodeVisitType() {
		return episodeVisitType;
	}
	public void setEpisodeVisitType(String episodeVisitType) {
		this.episodeVisitType = episodeVisitType;
	}
	public String getEpisodeVisitTypeCode() {
		return episodeVisitTypeCode;
	}
	public void setEpisodeVisitTypeCode(String episodeVisitTypeCode) {
		this.episodeVisitTypeCode = episodeVisitTypeCode;
	}
}
