package hisglobal.utility.dynamicdesk.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DynamicDeskFB extends ActionForm
{
	private String hmode;
	private String deskMode;
	private String deskType;
	private String userSeatID;
	private String departmentUnitCode;
	private String wardCode;
	
	private String hospitalCode;
	private String deskID;
	private String menuLocation;
	private String selListItemKey;
	private String compositionType;

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
	}

	public String getDeskMode()
	{
		return deskMode;
	}

	public void setDeskMode(String deskMode)
	{
		this.deskMode = deskMode;
	}

	public String getDeskType()
	{
		return deskType;
	}

	public void setDeskType(String deskType)
	{
		this.deskType = deskType;
	}

	public String getUserSeatID()
	{
		return userSeatID;
	}

	public void setUserSeatID(String userSeatID)
	{
		this.userSeatID = userSeatID;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getDeskID()
	{
		return deskID;
	}

	public void setDeskID(String deskID)
	{
		this.deskID = deskID;
	}

	public String getMenuLocation()
	{
		return menuLocation;
	}

	public void setMenuLocation(String menuLocation)
	{
		this.menuLocation = menuLocation;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getSelListItemKey() {
		return selListItemKey;
	}

	public void setSelListItemKey(String selListItemKey) {
		this.selListItemKey = selListItemKey;
	}

	public String getCompositionType() {
		return compositionType;
	}

	public void setCompositionType(String compositionType) {
		this.compositionType = compositionType;
	}
}
