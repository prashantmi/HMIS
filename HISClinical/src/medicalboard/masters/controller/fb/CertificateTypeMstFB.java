package medicalboard.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateTypeMstFB extends ActionForm{

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
	private String hmode;
	private String certificateCatName;
	private String[] district;
	private String[] selectedDistrict;
	private String ageBound;
	private String maxAge;
	private String minAge;
	private String boardTypeName;
	private String chk[];
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


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.certificateTypeName="";
		this.boardType="-1";
		this.maxRequest="";
		this.minRequest="";
		this.maxAge="";
		this.minAge="";
		this.requisitionBy="-1";
		this.deptUnitCode="-1";
		this.certificateCatID="-1";
		this.locationBound="0";
		this.ageBound="0";
		this.description="";
		this.templateCode="-1";
		this.isCertNoRequired="0";
		this.certNoStartFrom="";
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
	public String getLocationBound() {
		return locationBound;
	}
	public void setLocationBound(String locationBound) {
		this.locationBound = locationBound;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getCertificateCatName() {
		return certificateCatName;
	}
	public void setCertificateCatName(String certificateCatName) {
		this.certificateCatName = certificateCatName;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getRequisitionBy() {
		return requisitionBy;
	}
	public void setRequisitionBy(String requisitionBy) {
		this.requisitionBy = requisitionBy;
	}
	
	public String getAgeBound() {
		return ageBound;
	}
	public void setAgeBound(String ageBound) {
		this.ageBound = ageBound;
	}
	public String getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}
	public String getMinAge() {
		return minAge;
	}
	public void setMinAge(String minAge) {
		this.minAge = minAge;
	}
	public String getBoardTypeName() {
		return boardTypeName;
	}
	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}


	public String[] getChk() {
		return chk;
	}


	public void setChk(String[] chk) {
		this.chk = chk;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String[] getDistrict() {
		return district;
	}


	public void setDistrict(String[] district) {
		this.district = district;
	}


	public String[] getSelectedDistrict() {
		return selectedDistrict;
	}


	public void setSelectedDistrict(String[] selectedDistrict) {
		this.selectedDistrict = selectedDistrict;
	}


	public String getTemplateCode() {
		return templateCode;
	}


	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}


	public String getIsCertNoRequired()
	{
		return isCertNoRequired;
	}


	public void setIsCertNoRequired(String isCertNoRequired)
	{
		this.isCertNoRequired = isCertNoRequired;
	}


	public String getCertNoStartFrom()
	{
		return certNoStartFrom;
	}


	public void setCertNoStartFrom(String certNoStartFrom)
	{
		this.certNoStartFrom = certNoStartFrom;
	}


	
	
	
}
