package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class NursingStationLoginFB extends ActionForm 
{
	private String departmentUnitCode;
	private String wardCode;
	private String hmode;
	private String unitList;
	private String roomCode;
	private String roomList;
	
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
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
	public String getUnitList() {
		return unitList;
	}
	public void setUnitList(String unitList) {
		this.unitList = unitList;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRoomList() {
		return roomList;
	}
	public void setRoomList(String roomList) {
		this.roomList = roomList;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDepartmentUnitCode("");
		this.setWardCode("");
		this.setRoomCode("");
		this.setRoomList("");
		this.setUnitList("");
	}
}
