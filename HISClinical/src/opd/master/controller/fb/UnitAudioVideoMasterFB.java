package opd.master.controller.fb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UnitAudioVideoMasterFB extends ActionForm
{
	private String hmode;
	private String unitCode;
	private ArrayList AVfileCode;
	private String isValid;
	private String entryDate;
	private String seatID;
	private String[] selectedAVFileCode; 
	private String unitName;
	private String chk;
	private String tempMode;
	private String controls[];
	private ArrayList unitsList;
	private ArrayList mainUnitsList;
	private String deptCode;
	private String[] selectedUnit;

	public String getDeptCode() {
		return deptCode;
	}


	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public ArrayList getMainUnitsList() {
		return mainUnitsList;
	}


	public void setMainUnitsList(ArrayList mainUnitsList) {
		this.mainUnitsList = mainUnitsList;
	}


	public ArrayList getUnitsList() {
		return unitsList;
	}


	public void setUnitsList(ArrayList unitsList) {
		this.unitsList = unitsList;
	}


	public UnitAudioVideoMasterFB()
	{
		this.controls = new String[3];
	}
	
	
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public ArrayList getAVfileCode() {
		return AVfileCode;
	}
	public void setAVfileCode(ArrayList vfileCode) {
		AVfileCode = vfileCode;
	}
	public String[] getSelectedAVFileCode() {
		return selectedAVFileCode;
	}
	public void setSelectedAVFileCode(String[] selectedAVFileCode) {
		this.selectedAVFileCode = selectedAVFileCode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setUnitCode("");
		
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public String[] getSelectedUnit() {
		return selectedUnit;
	}


	public void setSelectedUnit(String[] selectedUnit) {
		this.selectedUnit = selectedUnit;
	}
	
}
