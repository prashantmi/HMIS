package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

public class SpecialityWiseSundayClinicPatientsFB extends ReportFB
{
	private String weekDay;
	private String unit;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	
}
