package inpatient.masters.controller.fb;

/**
 * @author  CDAC
 */

import inpatient.InpatientConfig;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import inpatient.InpatientConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UnitInvParaMappingMstFB extends ActionForm
{
	private String hmode;
	private String unitId;
	private ArrayList unitsList;
	private String[] selectedPara;
	private String[] selectedParaForModify;
	private ArrayList wardsList;
	private ArrayList paraList;
	private ArrayList paraListForModify;
	private String[] selectedWards;
	private String gotWards;
	private String wardCode;
	private String wardName;
	private String chk[];
	private String isActive;
	private String controls[];
	private String unitName;
	private String deptUnitCode;	
	private String entryDate;
	private String seatId;
	private String isValid;
	private String slNo;
	private String hospCode;
	private String additionMode;
	private String displayValue;
	private String paraId;
	private String[] selectedUnit;
	private String isGoPressed;
	private String paraName;
	
		
	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	

	public String[] getSelectedUnit() {
		return selectedUnit;
	}

	public void setSelectedUnit(String[] selectedUnit) {
		this.selectedUnit = selectedUnit;
	}

	public UnitInvParaMappingMstFB() 
	{
		this.hmode="";

		this.additionMode = InpatientConfig.UNITWISE_MODE;
		this.unitId="-1";
		this.unitsList=null;
		this.selectedPara=new String[0];
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		super.reset(mapping,request);
		
		this.additionMode = InpatientConfig.UNITWISE_MODE;
		this.unitId="-1";
		this.unitsList=null;
		this.selectedPara=new String[0];
	
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	

	public ArrayList getUnitsList() {
		return unitsList;
	}

	public void setUnitsList(ArrayList unitsList) {
		this.unitsList = unitsList;
	}

	

	public ArrayList getWardsList() {
		return wardsList;
	}

	public void setWardsList(ArrayList wardsList) {
		this.wardsList = wardsList;
	}

	public String[] getSelectedWards() {
		return selectedWards;
	}

	public void setSelectedWards(String[] selectedWards) {
		this.selectedWards = selectedWards;
	}

	public String getGotWards() {
		return gotWards;
	}

	public void setGotWards(String gotWards) {
		this.gotWards = gotWards;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getDeptUnitCode() {
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public String getAdditionMode() {
		return additionMode;
	}

	public void setAdditionMode(String additionMode) {
		this.additionMode = additionMode;
	}

	
	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public ArrayList getParaList() {
		return paraList;
	}

	public void setParaList(ArrayList paraList) {
		this.paraList = paraList;
	}

	public String[] getSelectedPara() {
		return selectedPara;
	}

	public void setSelectedPara(String[] selectedPara) {
		this.selectedPara = selectedPara;
	}

	public String getIsGoPressed() {
		return isGoPressed;
	}

	public void setIsGoPressed(String isGoPressed) {
		this.isGoPressed = isGoPressed;
	}

	public String[] getSelectedParaForModify() {
		return selectedParaForModify;
	}

	public void setSelectedParaForModify(String[] selectedParaForModify) {
		this.selectedParaForModify = selectedParaForModify;
	}

	public ArrayList getParaListForModify() {
		return paraListForModify;
	}

	public void setParaListForModify(ArrayList paraListForModify) {
		this.paraListForModify = paraListForModify;
	}

}
