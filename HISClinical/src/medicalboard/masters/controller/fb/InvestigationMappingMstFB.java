package medicalboard.masters.controller.fb;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class InvestigationMappingMstFB extends ActionForm{

	
	private String certificateTypeID;
	private String certificateTypeName;
	private String labTestCode;
	private String isOptional;
	private String hmode;
	private String controls[];
	private String chk[];
	private String index;
	private String[] indexArray;
	
	
	private ArrayList labTestList;
	private String[] selLabTestList; 
	
	
	public InvestigationMappingMstFB()
	{
	  this.controls= new String[2];
	}
	
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getLabTestCode() {
		return labTestCode;
	}
	public void setLabTestCode(String labTestCode) {
		this.labTestCode = labTestCode;
	}
	public String getIsOptional() {
		return isOptional;
	}
	public void setIsOptional(String isOptional) {
		this.isOptional = isOptional;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getCertificateTypeName() {
		return certificateTypeName;
	}
	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
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


	public ArrayList getLabTestList() {
		return labTestList;
	}


	public void setLabTestList(ArrayList labTestList) {
		this.labTestList = labTestList;
	}


	public String[] getSelLabTestList() {
		return selLabTestList;
	}


	public void setSelLabTestList(String[] selLabTestList) {
		this.selLabTestList = selLabTestList;
	}


	public String[] getIndexArray() {
		return indexArray;
	}


	public void setIndexArray(String[] indexArray) {
		this.indexArray = indexArray;
	}
}
