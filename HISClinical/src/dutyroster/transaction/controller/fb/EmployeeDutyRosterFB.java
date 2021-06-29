package dutyroster.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmployeeDutyRosterFB extends ActionForm {

	private String year;
	private String month;
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
	private String currentYear;
	private String currentMonth;
	private String currentDate;
	private String designationId;
	private String rosterCategory;
	private String[] selectEmp;
	private String dutyType;
	private String empListToBeUpdated;
	private String empId;
	private String rosterGenList;
	private String generatedRosterId;
	private String reliverEmpList;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		this.year = "-1";
		this.month = "-1";	
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
		this.currentYear="";
		this.currentMonth="";
		this.currentDate="";
		this.designationId="";
		this.rosterCategory="";
		this.selectEmp=null;
		this.dutyType="";
		this.empListToBeUpdated="";
		this.empId="";
		this.rosterGenList="";
		this.generatedRosterId="";
		this.reliverEmpList="";		
	}

	
	
	public String getCurrentYear() {
		return currentYear;
	}


	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}


	public String getCurrentMonth() {
		return currentMonth;
	}


	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}


	public String[] getSelectEmp() {
		return selectEmp;
	}


	public void setSelectEmp(String[] selectEmp) {
		this.selectEmp = selectEmp;
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


	public String getDutyType() {
		return dutyType;
	}


	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}



	public String getEmpListToBeUpdated() {
		return empListToBeUpdated;
	}



	public void setEmpListToBeUpdated(String empListToBeUpdated) {
		this.empListToBeUpdated = empListToBeUpdated;
	}



	public String getEmpId() {
		return empId;
	}



	public void setEmpId(String empId) {
		this.empId = empId;
	}



	public String getRosterGenList() {
		return rosterGenList;
	}



	public void setRosterGenList(String rosterGenList) {
		this.rosterGenList = rosterGenList;
	}



	public String getGeneratedRosterId() {
		return generatedRosterId;
	}



	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
	}



	public String getReliverEmpList() {
		return reliverEmpList;
	}



	public void setReliverEmpList(String reliverEmpList) {
		this.reliverEmpList = reliverEmpList;
	}
	
	
		
}
