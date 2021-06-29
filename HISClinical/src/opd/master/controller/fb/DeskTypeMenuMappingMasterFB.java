package opd.master.controller.fb;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class DeskTypeMenuMappingMasterFB extends ActionForm 
{
	private String hmode;
	private ArrayList menuIdLst;
	private String[] selmenuIdLst; 
	private String[] controls;
	private String deskType;
	private String deskTypeDesc;
	private String chk; 
	

	
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getDeskTypeDesc() {
		return deskTypeDesc;
	}
	public void setDeskTypeDesc(String deskTypeDesc) {
		this.deskTypeDesc = deskTypeDesc;
	}
	public String getDeskType() {
		return deskType;
	}
	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}
	public DeskTypeMenuMappingMasterFB()
	{
		this.controls=new String[2];
	}
	public ArrayList getMenuIdLst() {
		return menuIdLst;
	}

	public void setMenuIdLst(ArrayList menuIdLst) {
		this.menuIdLst = menuIdLst;
	}

	public String[] getSelmenuIdLst() {
		return selmenuIdLst;
	}

	public void setSelmenuIdLst(String[] selmenuIdLst) {
		this.selmenuIdLst = selmenuIdLst;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
}
