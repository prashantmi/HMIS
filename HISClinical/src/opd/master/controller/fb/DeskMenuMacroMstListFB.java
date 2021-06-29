package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeskMenuMacroMstListFB extends ActionForm 
{
	private String deskType;
	private String deskMenuID;
	private String unitCode;
	private String seatID;
	private String hmode;
	private String macroID[];
	private String chk;
	
	
	public String getDeskType() {
		return deskType;
	}
	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}
	
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getMacroID() {
		return macroID;
	}
	public void setMacroID(String[] macroID) {
		this.macroID = macroID;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDeskType("");
		this.setDeskMenuID("");
		this.setMacroID(new String[]{});
		this.setSeatID("");
		this.setUnitCode("");
		
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getDeskMenuID() {
		return deskMenuID;
	}
	public void setDeskMenuID(String deskMenuID) {
		this.deskMenuID = deskMenuID;
	}
	
}
