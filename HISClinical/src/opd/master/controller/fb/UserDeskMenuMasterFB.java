package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserDeskMenuMasterFB extends ActionForm
{
	private String hmode;

	private String isGoPressed;
	
	private String deskType;
	private String mappingType;
	private String selectedMappingTypeDesc;
	private String selectedDeskTypeDesc;	
	private String unitId;
	private String wardsList;
	private String[] selectedWards;
	private ArrayList selectedWardsName;
	private String mappedTo;
	private String mappingSeqNo;
	private String deskTypeName;
	private String chk[];
	private String controls[];
	private String isActive;

	private String deptId;
	private String deptName;

	private String unitName;
	private String seatName;
	private String deskName;

	private String deptUnitCode;
	private String userSeatId;
	private String deskId;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String deskMenuId;

	private String additionMode;

	private ArrayList unitsList;
	private ArrayList mainUnitsList;
	private String[] selectedUnits;
	private ArrayList selectedUnitsName;

	private ArrayList seatsList;
	private String[] selectedSeats;
	



	private ArrayList deskList;
	private String deskTypeDesc;
	private String group;
	private String hospitalCode;
	
	//Added By Chetan
	private ArrayList mainSeatsList;
	private ArrayList selectedSeatsName;
	

	
	public String getSelectedMappingTypeDesc() {
		return selectedMappingTypeDesc;
	}

	public void setSelectedMappingTypeDesc(String selectedMappingTypeDesc) {
		this.selectedMappingTypeDesc = selectedMappingTypeDesc;
	}

	public String getSelectedDeskTypeDesc() {
		return selectedDeskTypeDesc;
	}

	public void setSelectedDeskTypeDesc(String selectedDeskTypeDesc) {
		this.selectedDeskTypeDesc = selectedDeskTypeDesc;
	}

	
	
	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDeskTypeDesc()
	{
		return deskTypeDesc;
	}

	public void setDeskTypeDesc(String deskTypeDesc)
	{
		this.deskTypeDesc = deskTypeDesc;
	}

	public UserDeskMenuMasterFB()
	{
		this.hmode = "";
		this.controls = new String[1];
		this.deptId = "-1";
		this.deptName = "";

		this.unitName = "";
		this.seatName = "";
		this.deskName = "";

		this.deptUnitCode = "";
		this.userSeatId = "";
		this.deskId = "-1";
		this.entryDate = "";
		this.seatId = "";
		this.isValid = "";

		this.unitsList = null;
		this.mainUnitsList = null;
		this.selectedUnits = new String[0];
		this.selectedUnitsName = null;

		
		this.seatsList = null;
		this.selectedSeats = new String[0];

		this.deskList = null;

		this.additionMode = OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE;
		this.isGoPressed = OpdConfig.CHOICE_NO;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.deptId = "-1";
		this.deptName = "";

		this.unitName = "";
		this.seatName = "";
		this.deskName = "";

		this.deptUnitCode = "";
		this.userSeatId = "";
		this.deskId = "-1";
		this.entryDate = "";
		this.seatId = "";
		this.isValid = "";

		this.unitsList = null;
		this.mainUnitsList = null;
		this.selectedUnits = new String[0];
		this.selectedUnitsName = null;

		this.seatsList = null;
		this.selectedSeats = new String[0];

		this.deskList = null;

		this.additionMode = OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE;
		this.isGoPressed = OpdConfig.CHOICE_NO;
	}

	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getUserSeatId()
	{
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId)
	{
		this.userSeatId = userSeatId;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public ArrayList getSeatsList()
	{
		return seatsList;
	}

	public void setSeatsList(ArrayList seatsList)
	{
		this.seatsList = seatsList;
	}

	public String[] getSelectedSeats()
	{
		return selectedSeats;
	}

	public void setSelectedSeats(String[] selectedSeats)
	{
		this.selectedSeats = selectedSeats;
	}

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
	}

	public String getDeskId()
	{
		return deskId;
	}

	public void setDeskId(String deskId)
	{
		this.deskId = deskId;
	}

	public ArrayList getDeskList()
	{
		return deskList;
	}

	public void setDeskList(ArrayList deskList)
	{
		this.deskList = deskList;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String[] getSelectedUnits()
	{
		return selectedUnits;
	}

	public void setSelectedUnits(String[] selectedUnits)
	{
		this.selectedUnits = selectedUnits;
	}

	public ArrayList getUnitsList()
	{
		return unitsList;
	}

	public void setUnitsList(ArrayList unitsList)
	{
		this.unitsList = unitsList;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public ArrayList getSelectedUnitsName()
	{
		return selectedUnitsName;
	}

	public void setSelectedUnitsName(ArrayList selectedUnitsName)
	{
		this.selectedUnitsName = selectedUnitsName;
	}

	public String getSeatName()
	{
		return seatName;
	}

	public void setSeatName(String seatName)
	{
		this.seatName = seatName;
	}

	public String getUnitName()
	{
		return unitName;
	}

	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}

	public String getDeskName()
	{
		return deskName;
	}

	public void setDeskName(String deskName)
	{
		this.deskName = deskName;
	}

	public String[] getChk()
	{
		return chk;
	}

	public void setChk(String[] chk)
	{
		this.chk = chk;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String[] getControls()
	{
		return controls;
	}

	public void setControls(String[] controls)
	{
		this.controls = controls;
	}

	public String getIsGoPressed()
	{
		return isGoPressed;
	}

	public void setIsGoPressed(String isGoPressed)
	{
		this.isGoPressed = isGoPressed;
	}

	public String getAdditionMode()
	{
		return additionMode;
	}

	public void setAdditionMode(String additionMode)
	{
		this.additionMode = additionMode;
	}

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}

	public ArrayList getMainUnitsList()
	{
		return mainUnitsList;
	}

	public void setMainUnitsList(ArrayList mainUnitsList)
	{
		this.mainUnitsList = mainUnitsList;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getWardsList() {
		return wardsList;
	}

	public void setWardsList(String wardsList) {
		this.wardsList = wardsList;
	}
	public String[] getSelectedWards() {
		return selectedWards;
	}

	public void setSelectedWards(String[] selectedWards) {
		this.selectedWards = selectedWards;
	}

	public ArrayList getSelectedWardsName() {
		return selectedWardsName;
	}

	public void setSelectedWardsName(ArrayList selectedWardsName) {
		this.selectedWardsName = selectedWardsName;
	}

	public ArrayList getMainSeatsList() {
		return mainSeatsList;
	}

	public void setMainSeatsList(ArrayList mainSeatsList) {
		this.mainSeatsList = mainSeatsList;
	}

	public ArrayList getSelectedSeatsName() {
		return selectedSeatsName;
	}

	public void setSelectedSeatsName(ArrayList selectedSeatsName) {
		this.selectedSeatsName = selectedSeatsName;
	}

	public String getMappedTo() {
		return mappedTo;
	}

	public void setMappedTo(String mappedTo) {
		this.mappedTo = mappedTo;
	}

	public String getMappingSeqNo() {
		return mappingSeqNo;
	}

	public void setMappingSeqNo(String mappingSeqNo) {
		this.mappingSeqNo = mappingSeqNo;
	}

	public String getDeskTypeName() {
		return deskTypeName;
	}

	public void setDeskTypeName(String deskTypeName) {
		this.deskTypeName = deskTypeName;
	}

}
