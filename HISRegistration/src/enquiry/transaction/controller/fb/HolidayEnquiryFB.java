package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HolidayEnquiryFB extends ActionForm {
	

	private String holidayId;
	private String holidayName;
	private String holidayType;
	private String day;
	private String holidayDate;
	private String year;
	
	private String hmode;
	
	private String isDirectCall;

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.year="";
	}


	public String getHolidayId() {
		return holidayId;
	}


	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}


	public String getHolidayName() {
		return holidayName;
	}


	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}


	public String getHolidayType() {
		return holidayType;
	}


	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getHolidayDate() {
		return holidayDate;
	}


	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getIsDirectCall() {
		return isDirectCall;
	}


	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}


	
		
}
