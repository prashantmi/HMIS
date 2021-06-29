package hisglobal.vo;

public class MbReferMappingMstVO extends ValueObject {

	private String certificateTypeID;
	private String slNo;
	private String referType;
	private String deptCode;
	private String deptUnitCode;
	private String remarks;
	private String isOptional;
	
	
	private String deptOrUnitName;
	private String referTypeName;
	private String isOptionalLabel;
	private String certificateTypeName;
	
	
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
	public String getReferType() {
		return referType;
	}
	public void setReferType(String referType) {
		this.referType = referType;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}
	public String getDeptOrUnitName() {
		return deptOrUnitName;
	}
	public void setDeptOrUnitName(String deptOrUnitName) {
		this.deptOrUnitName = deptOrUnitName;
	}
	public String getReferTypeName() {
		return referTypeName;
	}
	public void setReferTypeName(String referTypeName) {
		this.referTypeName = referTypeName;
	}
	public String getIsOptionalLabel() {
		return isOptionalLabel;
	}
	public void setIsOptionalLabel(String isOptionalLabel) {
		this.isOptionalLabel = isOptionalLabel;
	}
	public String getCertificateTypeName() {
		return certificateTypeName;
	}
	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	
	
	
	
}
