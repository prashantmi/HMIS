package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpdDeskLoginFb extends ActionForm
{
	private String departmentUnitCode;
	private String roomCode;
	private String hmode;
	private String showRommList;

	public String getShowRommList()
	{
		return showRommList;
	}

	public void setShowRommList(String showRommList)
	{
		this.showRommList = showRommList;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.setDepartmentUnitCode("");
		this.setRoomCode("");
		this.setHmode("");
		this.setShowRommList("");
		super.reset(arg0, arg1);
	}

	public String getRoomCode()
	{
		return roomCode;
	}

	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}
}
