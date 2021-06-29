package opd.master.controller.fb;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeskMenuMacroMstAddFB extends ActionForm
{
	private String deskType;
	private String deskName;
	private String deskMenuID;
	private String deskMenu;
	private String deptUnitCode;
	private String deptUnitName;
	private String macroHead;
	private String macroID;
	private String macroDesc;
	private String chk;
	private String entryDate;
	private ArrayList unitsList;
	private String[] selectedUnits;
	private ArrayList selectedUnitsName;
	private String mode;
	private String hmode;

	
	public String[] getSelectedUnits() {
		return selectedUnits;
	}
	public void setSelectedUnits(String[] selectedUnits) {
		this.selectedUnits = selectedUnits;
	}
	public ArrayList getUnitsList() {
		return unitsList;
	}
	public void setUnitsList(ArrayList unitsList) {
		this.unitsList = unitsList;
	}
	public String getDeskType() {
		return deskType;
	}
	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}
	public String getDeskName() {
		return deskName;
	}
	public void setDeskName(String deskName) {
		this.deskName = deskName;
	}
	public String getDeskMenu() {
		return deskMenu;
	}
	public void setDeskMenu(String deskMenu) {
		this.deskMenu = deskMenu;
	}
	
	public String getDeskMenuID() {
		return deskMenuID;
	}
	public void setDeskMenuID(String deskMenuID) {
		this.deskMenuID = deskMenuID;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	
	public String getDeptUnitName() {
		return deptUnitName;
	}
	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}
	public String getMacroHead() {
		return macroHead;
	}
	public void setMacroHead(String macroHead) {
		this.macroHead = macroHead;
	}
	public String getMacroID() {
		return macroID;
	}
	public void setMacroID(String macroID) {
		this.macroID = macroID;
	}
	public String getMacroDesc() {
		return macroDesc;
	}
	public void setMacroDesc(String macroDesc) {
		this.macroDesc = macroDesc;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDeptUnitCode("");
		this.setDeskMenu("");
		this.setDeskName("");
		this.setMacroDesc("");
		this.setMacroHead("");
		this.unitsList=null;
		this.selectedUnits=new String[0];
		this.selectedUnitsName=null;
		
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public ArrayList getSelectedUnitsName() {
		return selectedUnitsName;
	}
	public void setSelectedUnitsName(ArrayList selectedUnitsName) {
		this.selectedUnitsName = selectedUnitsName;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
}
