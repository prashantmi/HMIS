package hisglobal.vo;

public class ProfileDiagnosisDtlVO extends ValueObject

{

	private String profileId;
	private String diagnosticTypeName;
	private String diseaseName;
	private String diagnosticTypeCode;
	private String diagnosticCode;
	private String entryDate;
	private String remarks;
	private String diagnosisCodeType;
	private String episodeDate;
	private String revokeDate;
	private String episodeVisitNo;
	private String episodeCode;
	private String dignosisName;
	
	
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getDiagnosticTypeName() {
		return diagnosticTypeName;
	}
	public void setDiagnosticTypeName(String diagnosticTypeName) {
		this.diagnosticTypeName = diagnosticTypeName;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDiagnosticTypeCode() {
		return diagnosticTypeCode;
	}
	public void setDiagnosticTypeCode(String diagnosticTypeCode) {
		this.diagnosticTypeCode = diagnosticTypeCode;
	}
	public String getDiagnosticCode() {
		return diagnosticCode;
	}
	public void setDiagnosticCode(String diagnosticCode) {
		this.diagnosticCode = diagnosticCode;
	}
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDiagnosisCodeType() {
		return diagnosisCodeType;
	}
	public void setDiagnosisCodeType(String diagnosisCodeType) {
		this.diagnosisCodeType = diagnosisCodeType;
	}

	public String getRevokeDate() {
		return revokeDate;
	}
	public void setRevokeDate(String revokeDate) {
		this.revokeDate = revokeDate;
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
	public String getEpisodeDate() {
		return episodeDate;
	}
	public void setEpisodeDate(String episodeDate) {
		this.episodeDate = episodeDate;
	}
	public String getDignosisName() {
		return dignosisName;
	}
	public void setDignosisName(String dignosisName) {
		this.dignosisName = dignosisName;
	}
	
}
