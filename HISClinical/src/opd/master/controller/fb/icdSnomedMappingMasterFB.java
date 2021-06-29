package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class icdSnomedMappingMasterFB extends ActionForm{
	
	private String deskmode="";
	private String hmode;
	private String patAlertId;
	private String remarks;
	private String index;
	private String snomedCtId;
	private String sysDate;
	private String prefferedTerm;
	private String conceptId;
	private String icdCode;
	private String icdName;
	private String conceptName;
	private String[] selsnomed;
	private String selsnomedArray;
	private String selSnomedNameArray;
	private String chk;
	private String[] selSnomedName;
	

	
	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public void reset(ActionMapping mapping,HttpServletRequest req)
	{		 
		deskmode="";
		this.conceptId="";
		this.conceptName="";
		this.icdCode="";
		this.remarks="";
	}

	public String getDeskmode() {
		return deskmode;
	}

	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}

	
	public String getPatAlertId() {
		return patAlertId;
	}

	public void setPatAlertId(String patAlertId) {
		this.patAlertId = patAlertId;
	}



	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getSnomedCtId() {
		return snomedCtId;
	}

	public void setSnomedCtId(String snomedCtId) {
		this.snomedCtId = snomedCtId;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}


	public String getPrefferedTerm() {
		return prefferedTerm;
	}

	public void setPrefferedTerm(String prefferedTerm) {
		this.prefferedTerm = prefferedTerm;
	}

	public String getConceptId() {
		return conceptId;
	}

	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}

	public String getIcdName() {
		return icdName;
	}

	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public String[] getSelsnomed() {
		return selsnomed;
	}

	public void setSelsnomed(String[] selsnomed) {
		this.selsnomed = selsnomed;
	}



	public String getSelsnomedArray() {
		return selsnomedArray;
	}

	public void setSelsnomedArray(String selsnomedArray) {
		this.selsnomedArray = selsnomedArray;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}



	public String getSelSnomedNameArray() {
		return selSnomedNameArray;
	}

	public void setSelSnomedNameArray(String selSnomedNameArray) {
		this.selSnomedNameArray = selSnomedNameArray;
	}



	public String[] getSelSnomedName() {
		return selSnomedName;
	}

	public void setSelSnomedName(String[] selSnomedName) {
		this.selSnomedName = selSnomedName;
	}

}

