package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class DeceasedTileFB extends ActionForm
{
	private String hmode;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patAge;
	private String patGender;
	private String patGenderCode;
	private String mlcNo;
	private String deceasedNo;
	private String receivedDateTime;
	private String receivedBy;
	private String deathDate;
	private String receivedByName;
	private String isMlcCase;
	private String patGuardianName;
	private String caseNo;
	

	
	

	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getReceivedDateTime() {
		return receivedDateTime;
	}
	public void setReceivedDateTime(String receivedDateTime) {
		this.receivedDateTime = receivedDateTime;
	}
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getReceivedByName() {
		return receivedByName;
	}
	public void setReceivedByName(String receivedByName) {
		this.receivedByName = receivedByName;
	}
	public String getIsMlcCase() {
		return isMlcCase;
	}
	public void setIsMlcCase(String isMlcCase) {
		this.isMlcCase = isMlcCase;
	}
	
}
