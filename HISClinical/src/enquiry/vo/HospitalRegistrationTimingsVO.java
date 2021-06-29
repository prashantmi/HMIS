package enquiry.vo;

import hisglobal.vo.ValueObject;

public class HospitalRegistrationTimingsVO extends ValueObject {

	private String regCategoryCode;
	private String regCategoryDesc;	
	private String seasonCode;
	private String seasonDesc;
	private String weekdayCode;
	private String weekDay;	
	private String shiftCode;
	private String shiftDesc;
	
	
	
	
	
	public String getRegCategoryCode() {
		return regCategoryCode;
	}
	public void setRegCategoryCode(String regCategoryCode) {
		this.regCategoryCode = regCategoryCode;
	}
	public String getRegCategoryDesc() {
		return regCategoryDesc;
	}
	public void setRegCategoryDesc(String regCategoryDesc) {
		this.regCategoryDesc = regCategoryDesc;
	}
	public String getSeasonCode() {
		return seasonCode;
	}
	public void setSeasonCode(String seasonCode) {
		this.seasonCode = seasonCode;
	}
	public String getSeasonDesc() {
		return seasonDesc;
	}
	public void setSeasonDesc(String seasonDesc) {
		this.seasonDesc = seasonDesc;
	}
	public String getWeekdayCode() {
		return weekdayCode;
	}
	public void setWeekdayCode(String weekdayCode) {
		this.weekdayCode = weekdayCode;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getShiftCode() {
		return shiftCode;
	}
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String getShiftDesc() {
		return shiftDesc;
	}
	public void setShiftDesc(String shiftDesc) {
		this.shiftDesc = shiftDesc;
	}
	
	
	
}
