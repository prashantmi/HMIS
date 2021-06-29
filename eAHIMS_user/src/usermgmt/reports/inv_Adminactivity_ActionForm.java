package usermgmt.reports;

import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import javax.servlet.http.*;
import javax.servlet.*;



public class inv_Adminactivity_ActionForm extends ActionForm
{
	
	private String seat		="";
	private String seatname ="";
	private String tablelist ="";
	private String tablename ="";
	private String table="";
	private String userList="";
	private String activity1="";
	private String activity2="";
	private String menuid="";
	private String moduleid="";
	private String user	="";	
	private String mod	="";
	private String menu	="";
	private String fromDate	="";
	private String toDate	="";
	private String choice 	= "2";
	private String hmode	="";
	private String seatId 	= "";
	private String auditLogPath = "";
	private String dtDate="";
	private String filename="";
	
	public String getDtDate() {
		return dtDate;
	}
	public void setDtDate(String dtDate) {
		this.dtDate = dtDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getActivity1() {
		return activity1;
	}
	public void setActivity1(String activity1) {
		this.activity1 = activity1;
	}
	public String getActivity2() {
		return activity2;
	}
	public void setActivity2(String activity2) {
		this.activity2 = activity2;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		System.out.println("SETTER CALLED");
		this.hmode = hmode;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getSeatname() {
		return seatname;
	}
	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getTablelist() {
		return tablelist;
	}
	public void setTablelist(String tablelist) {
		this.tablelist = tablelist;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserList() {
		return userList;
	}
	public void setUserList(String userList) {
		this.userList = userList;
	}
	public String getAuditLogPath() {
		return auditLogPath;
	}
	public void setAuditLogPath(String auditLogPath) {
		this.auditLogPath = auditLogPath;
	}

	
	
	
	
	 

}
