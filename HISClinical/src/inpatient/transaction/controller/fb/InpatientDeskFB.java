package inpatient.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class InpatientDeskFB extends ActionForm
{
	private String departmentUnitCode;
	private String wardCode;
	private String roomCode;
	private String hmode;
	private String selectedPatCrNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGender;
	private String patCrNo;
	private String orderBy;
	
	private String strSearchByType;
	private String strSearchBy;
	
	// For Pagination
	private int currentPage;
	
	
	public InpatientDeskFB()
	{
		this.currentPage=1;
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
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


	public String getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}


	public String getRoomCode() {
		return roomCode;
	}


	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}


	public String getStrSearchByType()
	{
		return strSearchByType;
	}


	public void setStrSearchByType(String strSearchByType)
	{
		this.strSearchByType = strSearchByType;
	}


	public String getStrSearchBy()
	{
		return strSearchBy;
	}


	public void setStrSearchBy(String strSearchBy)
	{
		this.strSearchBy = strSearchBy;
	}
}
