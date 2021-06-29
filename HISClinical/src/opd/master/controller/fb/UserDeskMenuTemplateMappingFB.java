package opd.master.controller.fb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserDeskMenuTemplateMappingFB extends ActionForm
{
	private String hmode;

	private String additionMode;	// For Add Mode : Desk-, Unit-, Unit Seat-, Unit Ward-, Unit Ward Seat- Wise 
	private String isGoPressed;		// For Step of Selection From : Desk Type, Desk, Unit, Ward, Seat etc.
	private String isDeskSelected;	// For Desk Selection Done or not
	private String length;			// Decided if There is Templates Attached or not
	private String addedLength;		// Count of Added Template Mappings
	private String isMappingStart;	// For Template Mapping Add/Mod Started or not
	private String isModificationStart;	// For Template Modification Started or not

	private String deskType;
	private String deskId;
	private String unitCode;
	private String wardCode;
	private String userSeatId;

	private String deskMenuId;
	private String templateId;
	private String isDefault;
	private String hiddenTemplateId;

	private String deptId;					// Selected Department
	private ArrayList mainUnitsList;		// All Units List
	private ArrayList unitsList;			// Units for Selection
	private String[] selectedUnits;			// Selected Units

	private String group;					// Selected Group
	private ArrayList mainSeatsList;		// All Seats List
	private ArrayList seatsList;			// Seats for Selection	
	private String[] selectedSeats;			// Selected Seats

	private ArrayList wardsList;			// Wrads for Selection
	private String[] selectedWards;			// Selected Wards

	

	
	private ArrayList mainWardsList;
	private ArrayList selectedUnitsName;	// Selected Units to Show only
	private String tempMode;
	private String templateName;
	private String chk[];
	private String deskName;
	private String wardId;
	private String wardName;
	private String deptName;
	private String unitName;
	private String seatName;
	private String deptUnitCode;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String slNo;
	private String deskTypeDesc;
	private String displayOrder;

	private ArrayList selectedWardsName;

	private ArrayList selectedSeatsName;

	private String tempUnitCode;
	private String tempWardCode;
	private String tempSeatId;

	// private ArrayList unitsList;
	// private String[] selectedUnits;
	// private ArrayList seatsList;
	// private String[] selectedSeats;
	// private ArrayList wardsList;
	// private String[] selectedWards;
	// private ArrayList selectedUnitsName;
	// private ArrayList deskList;

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.deskType = "";
		this.deskId = "";
		this.unitCode = "";
		this.wardCode = "";
		this.userSeatId = "";
		
		this.isDeskSelected = OpdConfig.NO;
		this.isMappingStart = OpdConfig.NO;
		this.isModificationStart = OpdConfig.NO;
		this.length = "0";
		this.addedLength = "0";
		
		this.deskMenuId = "-1";
		this.templateId = "-1";
		this.isDefault = OpdConfig.YES;
		this.hiddenTemplateId = "";
		
		
		this.deptId = "-1";
		this.mainUnitsList = null;
		this.unitsList = null;
		this.selectedUnits = null;
		
		this.group = "-1";
		this.mainSeatsList = null;
		this.seatsList = null;
		this.selectedSeats = null;
		
		this.wardsList = null;
		this.selectedWards = null;
		
		
		
		
		
		
		this.selectedUnitsName = null;		
		this.tempMode = "";
	}

	public String getIsDeskSelected()
	{
		return isDeskSelected;
	}

	public void setIsDeskSelected(String isDeskSelected)
	{
		this.isDeskSelected = isDeskSelected;
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

	public ArrayList getSelectedUnitsName()
	{
		return selectedUnitsName;
	}

	public void setSelectedUnitsName(ArrayList selectedUnitsName)
	{
		this.selectedUnitsName = selectedUnitsName;
	}

	public ArrayList getUnitsList()
	{
		return unitsList;
	}

	public void setUnitsList(ArrayList unitsList)
	{
		this.unitsList = unitsList;
	}

	public ArrayList getMainUnitsList()
	{
		return mainUnitsList;
	}

	public void setMainUnitsList(ArrayList mainUnitsList)
	{
		this.mainUnitsList = mainUnitsList;
	}

	public String[] getSelectedUnits()
	{
		return selectedUnits;
	}

	public void setSelectedUnits(String[] selectedUnits)
	{
		this.selectedUnits = selectedUnits;
	}

	public String getDisplayOrder()
	{
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	public String getTempMode()
	{
		return tempMode;
	}

	public void setTempMode(String tempMode)
	{
		this.tempMode = tempMode;
	}

	public String getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault(String isDefault)
	{
		this.isDefault = isDefault;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	/*
	 * public ArrayList getUnitsList() { return unitsList; } public void setUnitsList(ArrayList unitsList) { this.unitsList =
	 * unitsList; } public String[] getSelectedUnits() { return selectedUnits; } public void setSelectedUnits(String[]
	 * selectedUnits) { this.selectedUnits = selectedUnits; } public ArrayList getSeatsList() { return seatsList; } public
	 * void setSeatsList(ArrayList seatsList) { this.seatsList = seatsList; } public String[] getSelectedSeats() { return
	 * selectedSeats; } public void setSelectedSeats(String[] selectedSeats) { this.selectedSeats = selectedSeats; } public
	 * ArrayList getWardsList() { return wardsList; } public void setWardsList(ArrayList wardsList) { this.wardsList =
	 * wardsList; } public String[] getSelectedWards() { return selectedWards; } public void setSelectedWards(String[]
	 * selectedWards) { this.selectedWards = selectedWards; }
	 */
	public String getDeskId()
	{
		return deskId;
	}

	public void setDeskId(String deskId)
	{
		this.deskId = deskId;
	}

	public String getDeskName()
	{
		return deskName;
	}

	public void setDeskName(String deskName)
	{
		this.deskName = deskName;
	}

	public String getWardId()
	{
		return wardId;
	}

	public void setWardId(String wardId)
	{
		this.wardId = wardId;
	}

	public String getWardName()
	{
		return wardName;
	}

	public void setWardName(String wardName)
	{
		this.wardName = wardName;
	}

	public String[] getChk()
	{
		return chk;
	}

	public void setChk(String[] chk)
	{
		this.chk = chk;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getUnitName()
	{
		return unitName;
	}

	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}

	public String getSeatName()
	{
		return seatName;
	}

	public void setSeatName(String seatName)
	{
		this.seatName = seatName;
	}

	public String getDeptUnitCode()
	{
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode)
	{
		this.deptUnitCode = deptUnitCode;
	}

	public String getUserSeatId()
	{
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId)
	{
		this.userSeatId = userSeatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getAdditionMode()
	{
		return additionMode;
	}

	public void setAdditionMode(String additionMode)
	{
		this.additionMode = additionMode;
	}

	/*
	 * public ArrayList getSelectedUnitsName() { return selectedUnitsName; } public void setSelectedUnitsName(ArrayList
	 * selectedUnitsName) { this.selectedUnitsName = selectedUnitsName; } public ArrayList getDeskList() { return deskList; }
	 * public void setDeskList(ArrayList deskList) { this.deskList = deskList; }
	 */
	public String getDeskTypeDesc()
	{
		return deskTypeDesc;
	}

	public void setDeskTypeDesc(String deskTypeDesc)
	{
		this.deskTypeDesc = deskTypeDesc;
	}

	public String getLength()
	{
		return length;
	}

	public void setLength(String length)
	{
		this.length = length;
	}

	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}

	public String getHiddenTemplateId()
	{
		return hiddenTemplateId;
	}

	public void setHiddenTemplateId(String hiddenTemplateId)
	{
		this.hiddenTemplateId = hiddenTemplateId;
	}

	public String getIsGoPressed()
	{
		return isGoPressed;
	}

	public void setIsGoPressed(String isGoPressed)
	{
		this.isGoPressed = isGoPressed;
	}

	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public ArrayList getSelectedSeatsName()
	{
		return selectedSeatsName;
	}

	public void setSelectedSeatsName(ArrayList selectedSeatsName)
	{
		this.selectedSeatsName = selectedSeatsName;
	}

	public ArrayList getWardsList()
	{
		return wardsList;
	}

	public void setWardsList(ArrayList wardsList)
	{
		this.wardsList = wardsList;
	}

	public ArrayList getMainWardsList()
	{
		return mainWardsList;
	}

	public void setMainWardsList(ArrayList mainWardsList)
	{
		this.mainWardsList = mainWardsList;
	}

	public String[] getSelectedWards()
	{
		return selectedWards;
	}

	public void setSelectedWards(String[] selectedWards)
	{
		this.selectedWards = selectedWards;
	}

	public ArrayList getSelectedWardsName()
	{
		return selectedWardsName;
	}

	public void setSelectedWardsName(ArrayList selectedWardsName)
	{
		this.selectedWardsName = selectedWardsName;
	}

	public String getTempUnitCode()
	{
		return tempUnitCode;
	}

	public void setTempUnitCode(String tempUnitCode)
	{
		this.tempUnitCode = tempUnitCode;
	}

	public String getTempWardCode()
	{
		return tempWardCode;
	}

	public void setTempWardCode(String tempWardCode)
	{
		this.tempWardCode = tempWardCode;
	}

	public String getTempSeatId()
	{
		return tempSeatId;
	}

	public void setTempSeatId(String tempSeatId)
	{
		this.tempSeatId = tempSeatId;
	}

	public String getAddedLength()
	{
		return addedLength;
	}

	public void setAddedLength(String addedLength)
	{
		this.addedLength = addedLength;
	}

	public String getIsMappingStart()
	{
		return isMappingStart;
	}

	public void setIsMappingStart(String isMappingStart)
	{
		this.isMappingStart = isMappingStart;
	}

	public ArrayList getMainSeatsList()
	{
		return mainSeatsList;
	}

	public void setMainSeatsList(ArrayList mainSeatsList)
	{
		this.mainSeatsList = mainSeatsList;
	}

	public String getIsModificationStart()
	{
		return isModificationStart;
	}

	public void setIsModificationStart(String isModificationStart)
	{
		this.isModificationStart = isModificationStart;
	}

}
