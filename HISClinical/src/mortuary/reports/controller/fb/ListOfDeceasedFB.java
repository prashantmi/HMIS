package mortuary.reports.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.ReportFB;

public class ListOfDeceasedFB extends ReportFB
{
	private String entryMode;
	private String entryModeLabel;
	private String[] multipleHospitalCode; //Anant Patel
	private String allHospitalCode;

	public String getEntryMode() {
		return entryMode;
	}

	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}

	public String getEntryModeLabel() {
		return entryModeLabel;
	}

	public void setEntryModeLabel(String entryModeLabel) {
		this.entryModeLabel = entryModeLabel;
	}
	//Anant Patel
	public void setMultipleHospitalCode(String[] multipleHospitalCode) {
		this.multipleHospitalCode = multipleHospitalCode;
	}

	public String[] getMultipleHospitalCode() {
		return multipleHospitalCode;
	}
	
	public String getAllHospitalCode() {
		return allHospitalCode;
	}
	
	public void setAllHospitalCode(String allHospitalCode) {
		this.allHospitalCode = allHospitalCode;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		this.setEntryModeLabel("");
		this.setEntryMode("");
	}
}
