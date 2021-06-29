package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class IcdHospitalMasterFB extends ActionForm {
	private String hospitalDiseaseCode;
	private boolean displayList;
	private String hmode;
	private String diagnosticCode[] = {};
	private String diagnosticName[] = {};
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.hospitalDiseaseCode="-1";
	}

	public IcdHospitalMasterFB() {
		this.displayList = false;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public boolean isDisplayList() {
		return displayList;
	}

	public void setDisplayList(boolean displayList) {
		this.displayList = displayList;
	}

	public String getHospitalDiseaseCode() {
		return hospitalDiseaseCode;
	}

	public void setHospitalDiseaseCode(String hospitalDiseaseCode) {
		this.hospitalDiseaseCode = hospitalDiseaseCode;
	}

	public String[] getDiagnosticCode() {
		return diagnosticCode;
	}

	public void setDiagnosticCode(String[] diagnosticCode) {
		this.diagnosticCode = diagnosticCode;
	}

	public String[] getDiagnosticName() {
		return diagnosticName;
	}

	public void setDiagnosticName(String[] diagnosticName) {
		this.diagnosticName = diagnosticName;
	}

	
}
