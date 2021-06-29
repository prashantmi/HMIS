package hisglobal.vo;

public class MbCertificateTypeMstVO extends ValueObject{

	private String certificateTypeID;
	private String slNo;
	private String certificateTypeName;
	private String certificateCatID;
	private String boardType;
	private String minRequest;
	private String maxRequest;
	private String requisitionBy;
	private String description;
	private String deptUnitCode;
	private String locationBound;
	private String maxAge;
	private String minAge;
	private String districtID;
	private String requestBy;
	private String isBillApply;
	private String departmentUnitCode;
	private String registeredPatientCount;
	private String issueType;
	private String templateCode;
	private String isCertNoRequired;
	private String certNoStartFrom;
	
	
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getCertificateTypeName() {
		return certificateTypeName;
	}
	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	public String getCertificateCatID() {
		return certificateCatID;
	}
	public void setCertificateCatID(String certificateCatID) {
		this.certificateCatID = certificateCatID;
	}

	public String getMinRequest() {
		return minRequest;
	}
	public void setMinRequest(String minRequest) {
		this.minRequest = minRequest;
	}
	public String getMaxRequest() {
		return maxRequest;
	}
	public void setMaxRequest(String maxRequest) {
		this.maxRequest = maxRequest;
	}

	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getRequisitionBy() {
		return requisitionBy;
	}
	public void setRequisitionBy(String requisitionBy) {
		this.requisitionBy = requisitionBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getLocationBound() {
		return locationBound;
	}
	public void setLocationBound(String locationBound) {
		this.locationBound = locationBound;
	}
	public String getIsBillApply() {
		return isBillApply;
	}
	public void setIsBillApply(String isBillApply) {
		this.isBillApply = isBillApply;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getMinAge() {
		return minAge;
	}
	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}
	public String getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	public String getRegisteredPatientCount() {
		return registeredPatientCount;
	}
	public void setRegisteredPatientCount(String registeredPatientCount) {
		this.registeredPatientCount = registeredPatientCount;
	}
	
	public String getTemplateCode() {
		return templateCode ;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getIsCertNoRequired() {
		return isCertNoRequired ;
	}
	public void setISCertNoRequired(String isCertNoRequired) {
		this.isCertNoRequired = isCertNoRequired;
	}
	public String getCertNoStartFrom() {
		return certNoStartFrom ;
	}	
	public void setCertNoStartFrom(String CertNoStartFrom) {
		this.certNoStartFrom = certNoStartFrom;
	}
}
