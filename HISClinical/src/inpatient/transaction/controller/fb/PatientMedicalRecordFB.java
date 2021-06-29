package inpatient.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class PatientMedicalRecordFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
}
