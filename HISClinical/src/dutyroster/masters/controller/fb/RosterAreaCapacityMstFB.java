package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RosterAreaCapacityMstFB extends ActionForm {

	private String rosterId;
	private String areaTypeCode;
	private String areaTypeName;
	private String areaCode;
	private String areaName;
	private String morningCapacity;
	private String eveningCapacity;
	private String nightCapacity;
	private String earlyMorningCapacity;
	private String dayCapacity;
	private String hmode;
	private String chk;
	private String isValid;
	private String hospitalCode;
	private String serialNo;
	private String rosterName;
	private String dynamicShifts;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		this.rosterId = "-1";
		this.areaTypeCode = "";
		this.areaTypeName = "";
		this.areaCode = "-1";	
		this.areaName = "";	
		this.morningCapacity = "";
		this.eveningCapacity = "";
		this.nightCapacity = "";
		this.earlyMorningCapacity = "";	
		this.dayCapacity = "";	
		this.chk = "";
		this.isValid = "-1";
		this.hospitalCode = "";
		this.serialNo = "";	
		this.rosterName = "";	
		this.dynamicShifts="";
	
}
	
	
	public String getDynamicShifts() {
		return dynamicShifts;
	}


	public void setDynamicShifts(String dynamicShifts) {
		this.dynamicShifts = dynamicShifts;
	}


	public String getRosterId() {
		return rosterId;
	}
	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getMorningCapacity() {
		return morningCapacity;
	}
	public void setMorningCapacity(String morningCapacity) {
		this.morningCapacity = morningCapacity;
	}
	public String getEveningCapacity() {
		return eveningCapacity;
	}
	public void setEveningCapacity(String eveningCapacity) {
		this.eveningCapacity = eveningCapacity;
	}
	public String getNightCapacity() {
		return nightCapacity;
	}
	public void setNightCapacity(String nightCapacity) {
		this.nightCapacity = nightCapacity;
	}
	public String getEarlyMorningCapacity() {
		return earlyMorningCapacity;
	}
	public void setEarlyMorningCapacity(String earlyMorningCapacity) {
		this.earlyMorningCapacity = earlyMorningCapacity;
	}
	public String getDayCapacity() {
		return dayCapacity;
	}
	public void setDayCapacity(String dayCapacity) {
		this.dayCapacity = dayCapacity;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
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

	public String getRosterName() {
		return rosterName;
	}

	public void setRosterName(String rosterName) {
		this.rosterName = rosterName;
	}
	
	
}
