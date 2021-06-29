package hisglobal.vo;

import java.util.ArrayList;

public class UnitInvParaMappingVO extends ValueObject
{
	private String hmode;
	private String unitId;
	
	private ArrayList unitsList;
	private String selectedUnit;
	private ArrayList wardsList;
	private String[] selectedWards;

	private String gotWards;

	private String wardCode;
	private String wardName;
	private String displayValue;
	private String paraId;
	
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
	private String paraName;

	private String additionMode;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
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
	public String getSelectedUnit() {
		return selectedUnit;
	}
	public void setSelectedUnit(String selectedUnit) {
		this.selectedUnit = selectedUnit;
	}
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}


	
}
