package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeskWiseDefaultProfileMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isActive;
	private String controls[];
	private String deskTypeId;
	private String desktypeName;
	private String hospitalCode;
	private String serialNo;
	private String deskId;
	private String deskName;
	private String deskMenuId;
	private String isDefault;
	
	private String menuListDefault[];
	private String menuListNonDefault[];
	private int profileOrder;
	
	private String seatId;
	private String entryDate;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
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


	public String getDeskTypeId() {
		return deskTypeId;
	}


	public void setDeskTypeId(String deskTypeId) {
		this.deskTypeId = deskTypeId;
	}


	public String getDesktypeName() {
		return desktypeName;
	}


	public void setDesktypeName(String desktypeName) {
		this.desktypeName = desktypeName;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


	public String getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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



	public int getProfileOrder() {
		return profileOrder;
	}


	public void setProfileOrder(int profileOrder) {
		this.profileOrder = profileOrder;
	}


	public String getSeatId() {
		return seatId;
	}


	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}


	public String getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}




	public String[] getMenuListDefault() {
		return menuListDefault;
	}


	public void setMenuListDefault(String[] menuListDefault) {
		this.menuListDefault = menuListDefault;
	}


	public String[] getMenuListNonDefault() {
		return menuListNonDefault;
	}


	public void setMenuListNonDefault(String[] menuListNonDefault) {
		this.menuListNonDefault = menuListNonDefault;
	}


	public String getDeskMenuId() {
		return deskMenuId;
	}


	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}


	public String getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}