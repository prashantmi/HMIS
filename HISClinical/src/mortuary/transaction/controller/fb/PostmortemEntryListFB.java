package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class PostmortemEntryListFB extends ActionForm
{
	private String hmode;
	private String selectedPatCrNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGender;
	private String patCrNo;
	private String orderBy;
	private String postmortemId;
	private String postmortemIdSelected;
	
	// For Pagination
	private int currentPage;
	
	public PostmortemEntryListFB()
	{
		this.currentPage=1;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getSelectedPatCrNo() {
		return selectedPatCrNo;
	}

	public void setSelectedPatCrNo(String selectedPatCrNo) {
		this.selectedPatCrNo = selectedPatCrNo;
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

	public String getPatGender() {
		return patGender;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
