package opd.master.controller.fb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddUnitImageDescMasterFB extends ActionForm
{
	private String hmode;
	private String unitCode;
	private String unitName;
	private String imageId;
	private String imageDesc;
	private String color;
	private String dataColorDescList;
	private String htmlColorDescList;
	private String tempMode;
	private ArrayList unitsList;
	private ArrayList mainUnitsList;
	private String[] selectedUnit;
	private String deptCode;
	private String slNo;
	private ArrayList imageDescCode;
	private String[] selectedImageDescCode; 
	private String chk;
	private String controls[];
	
	
	
	public AddUnitImageDescMasterFB()
	{
		this.controls = new String[4];
	}
	
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String[] getSelectedUnit() {
		return selectedUnit;
	}
	public void setSelectedUnit(String[] selectedUnit) {
		this.selectedUnit = selectedUnit;
	}
	public ArrayList getUnitsList() {
		return unitsList;
	}
	public void setUnitsList(ArrayList unitsList) {
		this.unitsList = unitsList;
	}
	public ArrayList getMainUnitsList() {
		return mainUnitsList;
	}
	public void setMainUnitsList(ArrayList mainUnitsList) {
		this.mainUnitsList = mainUnitsList;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String getDataColorDescList() {
		return dataColorDescList;
	}
	public void setDataColorDescList(String dataColorDescList) {
		this.dataColorDescList = dataColorDescList;
	}
	public String getHtmlColorDescList() {
		return htmlColorDescList;
	}
	public void setHtmlColorDescList(String htmlColorDescList) {
		this.htmlColorDescList = htmlColorDescList;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageDesc() {
		return imageDesc;
	}
	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setUnitCode("");
		this.setDeptCode("");
		this.setUnitName("");
		this.setImageId("");
		this.setImageDesc("");
		this.setColor("");
		this.setDataColorDescList("");
		this.setHtmlColorDescList("");
		this.setTempMode("");
	}
	
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
	public ArrayList getImageDescCode() {
		return imageDescCode;
	}
	public void setImageDescCode(ArrayList imageDescCode) {
		this.imageDescCode = imageDescCode;
	}
	public String[] getSelectedImageDescCode() {
		return selectedImageDescCode;
	}
	public void setSelectedImageDescCode(String[] selectedImageDescCode) {
		this.selectedImageDescCode = selectedImageDescCode;
	}
}
