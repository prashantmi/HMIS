package inpatient.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class IpdPatDocDeskFB extends ActionForm
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
	private String legends;
	
	// For Pagination
	private int currentPage;
	
	private String selListItemKey;

	private String showRommList;
	private String visitType;

	private String crNoSelected;
	private String services;
	
	// For new Search Criteria
	private String srchCriteria;
	private String srchValue;
	
	public IpdPatDocDeskFB()
	{
		this.currentPage=1;
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


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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


	public String getSelListItemKey() {
		return selListItemKey;
	}


	public void setSelListItemKey(String selListItemKey) {
		this.selListItemKey = selListItemKey;
	}


	public String getShowRommList() {
		return showRommList;
	}


	public void setShowRommList(String showRommList) {
		this.showRommList = showRommList;
	}


	public String getVisitType() {
		return visitType;
	}


	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}


	public String getCrNoSelected() {
		return crNoSelected;
	}


	public void setCrNoSelected(String crNoSelected) {
		this.crNoSelected = crNoSelected;
	}


	public String getServices() {
		return services;
	}


	public void setServices(String services) {
		this.services = services;
	}


	public String getLegends() {
		return legends;
	}


	public void setLegends(String legends) {
		this.legends = legends;
	}


	public String getSrchCriteria() {
		return srchCriteria;
	}


	public void setSrchCriteria(String srchCriteria) {
		this.srchCriteria = srchCriteria;
	}


	public String getSrchValue() {
		return srchValue;
	}


	public void setSrchValue(String srchValue) {
		this.srchValue = srchValue;
	}
}
