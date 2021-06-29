package hisglobal.vo;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

import java.util.*;

public class DeptUnitConsultantInitialVO extends ValueObject
{
	
	private String departmentUnitCode;
	private String daywiseConsultantInitials;
	private String dayOfWeek;
	private String isValid;
	private String entryDate;
	private String lastModifyDate;	
	private String lastModifySeatId;
	private String seatId;
	private String hospitalCode;
	
	
	
	
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getDaywiseConsultantInitials() {
		return daywiseConsultantInitials;
	}
	public void setDaywiseConsultantInitials(String daywiseConsultantInitials) {
		this.daywiseConsultantInitials = daywiseConsultantInitials;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifySeatId() {
		return lastModifySeatId;
	}
	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	

	
	
	
	
	
}
