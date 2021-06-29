package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ShiftTypeMstFB extends ActionForm
{
	private String shiftCode;
	private String hmode;
	private String chk;
	private String isValid;
	private String hospitalCode;
	private String shiftDescription;
	private String shiftTypeCode;
	private String shiftTypeName;
	private String[] startTimeHrUser;
	private String[] startTimeMinUser;
	private String[] endTimeHrUser;
	private String[] endTimeMinUser;
	private String startTimeTable;
	private String endTimeTable;
	private String serialNo;
	private String shiftShortName;
	private String isDayWiseShift;
	

	
	



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.shiftCode = "";
		this.isValid = "-1";
		this.serialNo = "";	
		this.hospitalCode = "";
		this.shiftDescription = "";
		this.shiftTypeCode = "-1";	
		this.startTimeHrUser = new String[]{"","","","","","",""};
		this.startTimeMinUser = new String[]{"","","","","","",""};
		this.endTimeHrUser = new String[]{"","","","","","",""};	
		this.endTimeMinUser = new String[]{"","","","","","",""};
		this.startTimeTable = "";
		this.endTimeTable = "";	
		this.shiftShortName="";
		this.isDayWiseShift="0";
		
		
	}

	
	
	public String getIsDayWiseShift() {
		return isDayWiseShift;
	}



	public void setIsDayWiseShift(String isDayWiseShift) {
		this.isDayWiseShift = isDayWiseShift;
	}



	public String getShiftShortName() {
		return shiftShortName;
	}



	public void setShiftShortName(String shiftShortName) {
		this.shiftShortName = shiftShortName;
	}



	public String getShiftCode() {
		return shiftCode;
	}



	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
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



	public String getShiftDescription() {
		return shiftDescription;
	}



	public void setShiftDescription(String shiftDescription) {
		this.shiftDescription = shiftDescription;
	}



	public String getShiftTypeCode() {
		return shiftTypeCode;
	}



	public void setShiftTypeCode(String shiftTypeCode) {
		this.shiftTypeCode = shiftTypeCode;
	}



	public String[] getStartTimeHrUser() {
		return this.startTimeHrUser;
	}



	public void setStartTimeHrUser(String[] startTimeHrUser) {
		this.startTimeHrUser = startTimeHrUser;
	}



	public String[] getStartTimeMinUser() {
		return this.startTimeMinUser;
	}



	public void setStartTimeMinUser(String[] startTimeMinUser) {
		this.startTimeMinUser = startTimeMinUser;
	}



	public String[] getEndTimeHrUser() {
		return this.endTimeHrUser;
	}



	public void setEndTimeHrUser(String[] endTimeHrUser) {
		this.endTimeHrUser = endTimeHrUser;
	}



	public String[] getEndTimeMinUser() {
		return this.endTimeMinUser;
	}



	public void setEndTimeMinUser(String[] endTimeMinUser) {
		this.endTimeMinUser = endTimeMinUser;
	}



	public String getStartTimeTable() {
		return startTimeTable;
	}



	public void setStartTimeTable(String startTimeTable) {
		this.startTimeTable = startTimeTable;
	}



	public String getEndTimeTable() {
		return endTimeTable;
	}



	public void setEndTimeTable(String endTimeTable) {
		this.endTimeTable = endTimeTable;
	}



	public String getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getShiftTypeName() {
		return shiftTypeName;
	}

	public void setShiftTypeName(String shiftTypeName) {
		this.shiftTypeName = shiftTypeName;
	}

	

	
}
