package medicalboard.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ReferMappingMstFB extends ActionForm{

	private String controls[];
	private String chk[];
	private String hmode;
	
	private String certificateTypeID;
	private String slNo;
	private String referType;
	private String deptCode;
	private String deptUnitCode;
	private String remarks;
	private String isOptional;
	private String certificateTypeName;
	private String index;
	private String mode;
	private String noOfRow;
	
	
	public ReferMappingMstFB()
	{
	  this.controls= new String[2];
	}
		 
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		//this.controls= new String[2];
		 this.deptCode="-1";
		 this.deptUnitCode="-1";
		 this.remarks="";
		 this.isOptional="-1";
		 this.referType="1";
	}
	
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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

	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getNoOfRow() {
		return noOfRow;
	}

	public void setNoOfRow(String noOfRow) {
		this.noOfRow = noOfRow;
	}
}
