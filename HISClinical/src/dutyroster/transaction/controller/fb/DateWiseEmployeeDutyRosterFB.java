
package dutyroster.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DateWiseEmployeeDutyRosterFB extends ActionForm {

	private String year;
	private String month;
	private String startDate;
	private String endDate;
	private String rosterId;
	private String areaTypeCode;
	private String areaCode;
	private String hmode;
	private String isValid;
	private String hospitalCode;
	private String serialNo;
	private String calendar;
	private String concatedValueToBeSaved;
	private String shiftIdArray;
	private String shiftNameArray;
	private String shiftStartTimeArray;
	private String shiftEndTimeArray;
	private String shiftFullNameArray;
	private String maxDaysofMonth;
	private String maxNoOfEmployees;
	private String currentDate;
	private String designationId;
	private String rosterCategory;
	private String distinctRosterList;
	private String modeOfRoster;
	private String generatedRosterId;
	private String startDateOld;
	private String endDateOld;
	private String sysDate;
	private String isGenerated;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.startDate="";
		this.endDate="";
		this.areaTypeCode = "-1";		
		this.areaCode = "-1";		
		this.rosterId = "-1";		
		this.isValid = "-1";
		this.hospitalCode = "";	
		this.serialNo = "";	
		this.calendar="";		
		this.concatedValueToBeSaved="";
		this.shiftIdArray="";
		this.shiftNameArray="";
		this.shiftStartTimeArray="";
		this.shiftEndTimeArray="";
		this.shiftFullNameArray="";
		this.maxDaysofMonth="";
		this.maxNoOfEmployees="";
		this.currentDate="";
		this.designationId="ALL";
		this.rosterCategory="";
		this.distinctRosterList="";
		this.modeOfRoster="";
		this.generatedRosterId="";	
		this.startDateOld="";
		this.endDateOld="";
		this.sysDate="";
		this.isGenerated="";
		
	}

	
	
	
	public String getModeOfRoster() {
		return modeOfRoster;
	}
	public void setModeOfRoster(String modeOfRoster) {
		this.modeOfRoster = modeOfRoster;
	}

	public String getDistinctRosterList() {
		return distinctRosterList;
	}


	public void setDistinctRosterList(String distinctRosterList) {
		this.distinctRosterList = distinctRosterList;
	}


	public String getShiftFullNameArray() {
		return shiftFullNameArray;
	}


	public void setShiftFullNameArray(String shiftFullNameArray) {
		this.shiftFullNameArray = shiftFullNameArray;
	}


	public String getRosterCategory() {
		return rosterCategory;
	}

	public void setRosterCategory(String rosterCategory) {
		this.rosterCategory = rosterCategory;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	
	public String getRosterId() {
		return rosterId;
	}

	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCalendar() {
		return calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public String getConcatedValueToBeSaved() {
		return concatedValueToBeSaved;
	}

	public void setConcatedValueToBeSaved(String concatedValueToBeSaved) {
		this.concatedValueToBeSaved = concatedValueToBeSaved;
	}

	public String getShiftIdArray() {
		return shiftIdArray;
	}

	public void setShiftIdArray(String shiftIdArray) {
		this.shiftIdArray = shiftIdArray;
	}

	public String getShiftNameArray() {
		return shiftNameArray;
	}

	public void setShiftNameArray(String shiftNameArray) {
		this.shiftNameArray = shiftNameArray;
	}

	public String getShiftStartTimeArray() {
		return shiftStartTimeArray;
	}

	public void setShiftStartTimeArray(String shiftStartTimeArray) {
		this.shiftStartTimeArray = shiftStartTimeArray;
	}

	public String getShiftEndTimeArray() {
		return shiftEndTimeArray;
	}

	public void setShiftEndTimeArray(String shiftEndTimeArray) {
		this.shiftEndTimeArray = shiftEndTimeArray;
	}

	public String getMaxDaysofMonth() {
		return maxDaysofMonth;
	}

	public void setMaxDaysofMonth(String maxDaysofMonth) {
		this.maxDaysofMonth = maxDaysofMonth;
	}

	public String getMaxNoOfEmployees() {
		return maxNoOfEmployees;
	}

	public void setMaxNoOfEmployees(String maxNoOfEmployees) {
		this.maxNoOfEmployees = maxNoOfEmployees;
	}

	public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}




	public String getGeneratedRosterId() {
		return generatedRosterId;
	}




	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
	}




	public String getStartDateOld() {
		return startDateOld;
	}




	public void setStartDateOld(String startDateOld) {
		this.startDateOld = startDateOld;
	}




	public String getEndDateOld() {
		return endDateOld;
	}




	public void setEndDateOld(String endDateOld) {
		this.endDateOld = endDateOld;
	}




	public String getSysDate() {
		return sysDate;
	}




	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}




	public String getIsGenerated() {
		return isGenerated;
	}




	public void setIsGenerated(String isGenerated) {
		this.isGenerated = isGenerated;
	}




	

		
}
