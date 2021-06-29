package enquiry.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class SearchDeceasedEnquiryFB extends ActionForm
{
	private String hmode;
	private String deceasedNo;
	private String firstName;
	private String middleName;
	private String lastName;
	private String age;
	private String fromDate="";
	private String toDate="";
	private String genderCode;
	private String chkUnknown;
	private String chkUnclaimed;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getChkUnknown() {
		return chkUnknown;
	}
	public void setChkUnknown(String chkUnknown) {
		this.chkUnknown = chkUnknown;
	}
	public String getChkUnclaimed() {
		return chkUnclaimed;
	}
	public void setChkUnclaimed(String chkUnclaimed) {
		this.chkUnclaimed = chkUnclaimed;
	}
}
