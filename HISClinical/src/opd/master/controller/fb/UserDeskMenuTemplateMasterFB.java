package opd.master.controller.fb;

/**
 * @author  CDAC
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserDeskMenuTemplateMasterFB extends ActionForm
{
	private String hmode;
	private String deptId;
	
	private ArrayList unitsList;
	private String[] selectedUnits;

	private ArrayList seatsList;
	private String[] selectedSeats;
	private ArrayList wardsList;
	private String[] selectedWards;

	private String selectedDesk;
	private String gotSeats;
	private String gotWards;

	private String deskId;
	private String deskName;
	private String wardId;
	private String wardName;
	private String htmlTemplateList;
	private String dataTemplateList;
	private String deskMenuId;
	private String templateId;
	
	private String chk[];
	private String isActive;
	private String checkdeskwithunit;
	
	private String controls[];
	
	private String deptName;
	private String unitName;
	private String seatName;
	private String deptUnitCode;
	private String userSeatId;

	private String entryDate;
	private String seatId;
	private String isValid;
	private String slNo;
	private String hospCode;

	private String isGoModifyPressed;
	private String isGoPressed;
	private String additionMode;


	private ArrayList selectedUnitsName;
	private ArrayList deskList;
	private String deskTypeDesc;
	private String group;

	public UserDeskMenuTemplateMasterFB() 
	{
		this.hmode="";

		this.additionMode = OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE;
		this.isGoPressed = OpdConfig.STEP0;

		this.deptId="-1";
		
		this.unitsList=null;
		this.selectedUnits=new String[0];

		this.seatsList=null;
		this.selectedSeats=new String[0];
		this.gotSeats="0";
		
		this.deskId="-1";
		this.deskName="";

		this.htmlTemplateList="";
		this.dataTemplateList="";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		super.reset(mapping,request);
		
		//this.additionMode = OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE;
		this.isGoPressed = OpdConfig.STEP0;

		this.deptId="-1";

		this.unitsList=null;
		this.selectedUnits=new String[0];

		this.seatsList=null;
		this.selectedSeats=new String[0];
		this.gotSeats="0";
		
		this.deskId="-1";
		this.deskName="";

		this.htmlTemplateList="";
		this.dataTemplateList="";
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public ArrayList getUnitsList() {
		return unitsList;
	}

	public void setUnitsList(ArrayList unitsList) {
		this.unitsList = unitsList;
	}

	public String[] getSelectedUnits() {
		return selectedUnits;
	}

	public void setSelectedUnits(String[] selectedUnits) {
		this.selectedUnits = selectedUnits;
	}

	public ArrayList getSeatsList() {
		return seatsList;
	}

	public void setSeatsList(ArrayList seatsList) {
		this.seatsList = seatsList;
	}

	public String[] getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(String[] selectedSeats) {
		this.selectedSeats = selectedSeats;
	}

	public String getGotSeats() {
		return gotSeats;
	}

	public void setGotSeats(String gotSeats) {
		this.gotSeats = gotSeats;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public String getDeskName() {
		return deskName;
	}

	public void setDeskName(String deskName) {
		this.deskName = deskName;
	}

	public String getHtmlTemplateList() {
		return htmlTemplateList;
	}

	public void setHtmlTemplateList(String htmlTemplateList) {
		this.htmlTemplateList = htmlTemplateList;
	}

	public String getDataTemplateList() {
		return dataTemplateList;
	}

	public void setDataTemplateList(String dataTemplateList) {
		this.dataTemplateList = dataTemplateList;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public String getDeptUnitCode() {
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}

	public String getUserSeatId() {
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId) {
		this.userSeatId = userSeatId;
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

	public String getIsGoPressed() {
		return isGoPressed;
	}

	public void setIsGoPressed(String isGoPressed) {
		this.isGoPressed = isGoPressed;
	}

	public String getAdditionMode() {
		return additionMode;
	}

	public void setAdditionMode(String additionMode) {
		this.additionMode = additionMode;
	}

	public ArrayList getSelectedUnitsName() {
		return selectedUnitsName;
	}

	public void setSelectedUnitsName(ArrayList selectedUnitsName) {
		this.selectedUnitsName = selectedUnitsName;
	}

	public ArrayList getDeskList() {
		return deskList;
	}

	public void setDeskList(ArrayList deskList) {
		this.deskList = deskList;
	}

	public String getDeskTypeDesc() {
		return deskTypeDesc;
	}

	public void setDeskTypeDesc(String deskTypeDesc) {
		this.deskTypeDesc = deskTypeDesc;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCheckdeskwithunit() {
		return checkdeskwithunit;
	}

	public void setCheckdeskwithunit(String checkdeskwithunit) {
		this.checkdeskwithunit = checkdeskwithunit;
	}

	public String getSelectedDesk() {
		return selectedDesk;
	}

	public void setSelectedDesk(String selectedDesk) {
		this.selectedDesk = selectedDesk;
	}

	public String getIsGoModifyPressed() {
		return isGoModifyPressed;
	}

	public void setIsGoModifyPressed(String isGoModifyPressed) {
		this.isGoModifyPressed = isGoModifyPressed;
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

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
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
	

}
