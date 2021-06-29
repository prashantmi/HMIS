
package dutyroster.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LocationDutyRosterFB extends ActionForm {

	
	
	private String rosterId;
	private String areaTypeCode;
	private String areaCode;
	private String startDateTime;
	private String endDateTime;
	private String hmode;
	private String isValid;
	private String hospitalCode;
	private String locationWiseRoster;
	private String concatedValueToBeSaved;
	private String maxNoOfAreaMapped;
	private String maxNoOfShiftsMapped;
	private String maxNoOfDesignation;
	private String distinctRoster;
	private String distinctRosterList;
	private String modeOfRoster;
	private String sysDate;
	private String startDateTimeOld;
	private String endDateTimeOld;
	private String fromDateCheck;
	private String toDateCheck;
	private String fromDate;
	private String toDate;
	private String reportHeader;
	
	
	
	
	
	public String getReportHeader() {
		return reportHeader;
	}



	public void setReportHeader(String reportHeader) {
		this.reportHeader = reportHeader;
	}



	public String getFromDate() {
		return fromDate;
	}



	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}



	public String getToDate() {
		return toDate;
	}



	public void setToDate(String toDate) {
		this.toDate = toDate;
	}



	public String getFromDateCheck() {
		return fromDateCheck;
	}



	public void setFromDateCheck(String fromDateCheck) {
		this.fromDateCheck = fromDateCheck;
	}



	public String getToDateCheck() {
		return toDateCheck;
	}



	public void setToDateCheck(String toDateCheck) {
		this.toDateCheck = toDateCheck;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
			
		this.areaTypeCode = "-1";		
		this.areaCode = "-1";		
		this.rosterId = "-1";	
		this.startDateTime = "";
		this.endDateTime = "";
		this.isValid = "-1";
		this.hospitalCode = "";	
		this.locationWiseRoster="";		
		this.concatedValueToBeSaved="";
		this.maxNoOfDesignation="";
		this.distinctRoster="";
		this.distinctRosterList="";
		this.modeOfRoster="";
		this.sysDate="";
		this.startDateTimeOld="";
		this.endDateTimeOld="";
		this.fromDateCheck="";
		this.toDateCheck="";
		this.fromDate="";
		this.toDate="";
	}

	
	
	public String getSysDate() {
		return sysDate;
	}



	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}



	public String getMaxNoOfAreaMapped() {
		return maxNoOfAreaMapped;
	}


	public void setMaxNoOfAreaMapped(String maxNoOfAreaMapped) {
		this.maxNoOfAreaMapped = maxNoOfAreaMapped;
	}


	public String getMaxNoOfShiftsMapped() {
		return maxNoOfShiftsMapped;
	}


	public void setMaxNoOfShiftsMapped(String maxNoOfShiftsMapped) {
		this.maxNoOfShiftsMapped = maxNoOfShiftsMapped;
	}


	public String getMaxNoOfDesignation() {
		return maxNoOfDesignation;
	}


	public void setMaxNoOfDesignation(String maxNoOfDesignation) {
		this.maxNoOfDesignation = maxNoOfDesignation;
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

	public String getLocationWiseRoster() {
		return locationWiseRoster;
	}

	public void setLocationWiseRoster(String locationWiseRoster) {
		this.locationWiseRoster = locationWiseRoster;
	}

	public String getConcatedValueToBeSaved() {
		return concatedValueToBeSaved;
	}

	public void setConcatedValueToBeSaved(String concatedValueToBeSaved) {
		this.concatedValueToBeSaved = concatedValueToBeSaved;
	}


	public String getStartDateTime() {
		return startDateTime;
	}


	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}


	public String getEndDateTime() {
		return endDateTime;
	}


	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}


	public String getDistinctRoster() {
		return distinctRoster;
	}


	public void setDistinctRoster(String distinctRoster) {
		this.distinctRoster = distinctRoster;
	}


	public String getDistinctRosterList() {
		return distinctRosterList;
	}


	public void setDistinctRosterList(String distinctRosterList) {
		this.distinctRosterList = distinctRosterList;
	}


	public String getModeOfRoster() {
		return modeOfRoster;
	}


	public void setModeOfRoster(String modeOfRoster) {
		this.modeOfRoster = modeOfRoster;
	}



	public String getStartDateTimeOld() {
		return startDateTimeOld;
	}



	public void setStartDateTimeOld(String startDateTimeOld) {
		this.startDateTimeOld = startDateTimeOld;
	}



	public String getEndDateTimeOld() {
		return endDateTimeOld;
	}



	public void setEndDateTimeOld(String endDateTimeOld) {
		this.endDateTimeOld = endDateTimeOld;
	}

	
		
}
