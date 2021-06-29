package enquiry.vo;

import hisglobal.vo.ValueObject;

public class HospitalHolidayEnquiryVO extends ValueObject {

	private String holidayId;
	private String holidayName;
	private String holidayType;
	private String day;
	private String holidayDate;
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
	public String getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}
	
	
	
	
}
