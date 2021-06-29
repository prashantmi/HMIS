package inpatient.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class RegisteredMlcPatientsFB extends ReportFB
{
	private String allHospitalCode;
	private String[] multipleHospitalCode;
	private String wardCode;
	private String strDateOrWard="0";
	
	public String getAllHospitalCode() {
		return allHospitalCode;
	}
	public void setAllHospitalCode(String allHospitalCode) {
		this.allHospitalCode = allHospitalCode;
	}
	public String[] getMultipleHospitalCode() {
		return multipleHospitalCode;
	}
	public void setMultipleHospitalCode(String[] multipleHospitalCode) {
		this.multipleHospitalCode = multipleHospitalCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	public String getStrDateOrWard() {
		return strDateOrWard;
	}
	public void setStrDateOrWard(String strDateOrWard) {
		this.strDateOrWard = strDateOrWard;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping, request);
	}
}
