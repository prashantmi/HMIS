package new_investigation.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

public class jQueryDataTableParamModel {

	private String sSearch;
	private String iTotalRecords;
	private String iTotalDisplayRecords;
	private String iDisplayLength;
	private String iDisplayStart;
	private String iColumns;
	private String iSortingCols;
	private String sColumns;
	private String aaData;
	
	
	public jQueryDataTableParamModel(HttpServletRequest request) {
		super();
		this.sEcho = request.getParameter("sEcho");
				
		this.sSearch = request.getParameter("sSearch");
		this.iTotalRecords = request.getParameter("iTotalRecords");
		this.iTotalDisplayRecords = request.getParameter("iTotalDisplayRecords");
		this.iDisplayLength = request.getParameter("iDisplayLength");
		this.iDisplayStart = request.getParameter("iDisplayStart");
		this.iColumns = request.getParameter("iColumns");
		this.iSortingCols =request.getParameter("iSortingCols");
		this.sColumns = request.getParameter("sColumns");
		//this.aaData = request.getParameter("aaData");
	}
	private String sEcho;
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public String getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(String iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public String getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public String getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(String iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(String iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public String getiColumns() {
		return iColumns;
	}
	public void setiColumns(String iColumns) {
		this.iColumns = iColumns;
	}
	public String getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(String iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public String getsColumns() {
		return sColumns;
	}
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	public String getAaData() {
		return aaData;
	}
	public void setAaData(String aaData) {
		this.aaData = aaData;
	}
	
	
}
