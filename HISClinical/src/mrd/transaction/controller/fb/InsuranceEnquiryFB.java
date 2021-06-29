package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InsuranceEnquiryFB extends ActionForm
{
	private String hmode;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patCrNo;
	private String companyId;
	private String policyNo;
	private String selectedIndex;
	
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.patCrNo="";
		this.patFirstName="";
		this.patLastName="";
		this.patMiddleName="";
		this.policyNo="";
		this.companyId="-1";
	}
	
	

	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public String getPatFirstName() {
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}

	public String getPatMiddleName() {
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}

	public String getPatLastName() {
		return patLastName;
	}

	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	
}
