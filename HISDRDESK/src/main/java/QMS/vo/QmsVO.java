package QMS.vo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

public class QmsVO {

	private String mode;
	private String deskMenuId;
	private String departmentUnitCode;
	private String sortType;
	private String crNoSelected;
	private String strHospitalCode;
	private String strSeatId;
	private String strMsgString = "";
	private String strMsgType = "";
	private WebRowSet strInitialWb=null;
	private WebRowSet strTestWb=null;
	private WebRowSet strDiagnosisWb=null;
	private WebRowSet strDrugWb=null;
	private WebRowSet strDosageWb=null;
	private WebRowSet strMacrosWb=null;	
	private WebRowSet strCinicalProcudreWb=null;	
	private WebRowSet strDeptWb=null;
	private WebRowSet strRefferalDeptWb=null;
	private WebRowSet strBookMarksTestWb=null;
	private String strDeptCode;
	private String strDeptUnitCode;
	private String strRoomCode;
	private String strPuk;
	private String strEpisodeCode;
	private String strVistNo;
	private ArrayList<JSONObject> strDrugPrevCodeJSON=null;
	private ArrayList<JSONObject> strInvPrevCodeJSON=null;
	private ArrayList<JSONObject> strInvPrevVistReasonJSON=null;
	private ArrayList<JSONObject> strInvPrevDiagnosisJSON=null;
	private ArrayList<JSONObject> strPreVitalJSON=null;
	
	
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}

	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}

	public WebRowSet getStrCinicalProcudreWb() {
		return strCinicalProcudreWb;
	}

	public void setStrCinicalProcudreWb(WebRowSet strCinicalProcudreWb) {
		this.strCinicalProcudreWb = strCinicalProcudreWb;
	}

	public WebRowSet getStrRefferalDeptWb() {
		return strRefferalDeptWb;
	}

	public void setStrRefferalDeptWb(WebRowSet strRefferalDeptWb) {
		this.strRefferalDeptWb = strRefferalDeptWb;
	}

	public ArrayList<JSONObject> getStrPreVitalJSON() {
		return strPreVitalJSON;
	}

	public void setStrPreVitalJSON(ArrayList<JSONObject> strPreVitalJSON) {
		this.strPreVitalJSON = strPreVitalJSON;
	}

	private String strPrevDate="";
	
	public String getStrVistNo() {
		return strVistNo;
	}

	public void setStrVistNo(String strVistNo) {
		this.strVistNo = strVistNo;
	}

	public String getStrPrevDate() {
		return strPrevDate;
	}

	public void setStrPrevDate(String strPrevDate) {
		this.strPrevDate = strPrevDate;
	}

	public WebRowSet getStrDiagnosisWb() {
		return strDiagnosisWb;
	}

	public void setStrDiagnosisWb(WebRowSet strDiagnosisWb) {
		this.strDiagnosisWb = strDiagnosisWb;
	}

	public ArrayList<JSONObject> getStrInvPrevDiagnosisJSON() {
		return strInvPrevDiagnosisJSON;
	}

	public void setStrInvPrevDiagnosisJSON(
			ArrayList<JSONObject> strInvPrevDiagnosisJSON) {
		this.strInvPrevDiagnosisJSON = strInvPrevDiagnosisJSON;
	}

	public ArrayList<JSONObject> getStrInvPrevVistReasonJSON() {
		return strInvPrevVistReasonJSON;
	}

	public void setStrInvPrevVistReasonJSON(
			ArrayList<JSONObject> strInvPrevVistReasonJSON) {
		this.strInvPrevVistReasonJSON = strInvPrevVistReasonJSON;
	}

	public ArrayList<JSONObject> getStrDrugPrevCodeJSON() {
		return strDrugPrevCodeJSON;
	}

	public void setStrDrugPrevCodeJSON(ArrayList<JSONObject> strDrugPrevCodeJSON) {
		this.strDrugPrevCodeJSON = strDrugPrevCodeJSON;
	}

	public ArrayList<JSONObject> getStrInvPrevCodeJSON() {
		return strInvPrevCodeJSON;
	}

	public void setStrInvPrevCodeJSON(ArrayList<JSONObject> strInvPrevCodeJSON) {
		this.strInvPrevCodeJSON = strInvPrevCodeJSON;
	}

	public String getStrPuk() {
		return strPuk;
	}

	public void setStrPuk(String strPuk) {
		this.strPuk = strPuk;
	}

	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}

	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
	}

	public WebRowSet getStrBookMarksTestWb() {
		return strBookMarksTestWb;
	}

	public void setStrBookMarksTestWb(WebRowSet strBookMarksTestWb) {
		this.strBookMarksTestWb = strBookMarksTestWb;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrRoomCode() {
		return strRoomCode;
	}

	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}

	public WebRowSet getStrDeptWb() {
		return strDeptWb;
	}

	public void setStrDeptWb(WebRowSet strDeptWb) {
		this.strDeptWb = strDeptWb;
	}

	public WebRowSet getStrMacrosWb() {
		return strMacrosWb;
	}

	public void setStrMacrosWb(WebRowSet strMacrosWb) {
		this.strMacrosWb = strMacrosWb;
	}

	public WebRowSet getStrDosageWb() {
		return strDosageWb;
	}

	public void setStrDosageWb(WebRowSet strDosageWb) {
		this.strDosageWb = strDosageWb;
	}

	public WebRowSet getStrDrugWb() {
		return strDrugWb;
	}

	public void setStrDrugWb(WebRowSet strDrugWb) {
		this.strDrugWb = strDrugWb;
	}

	public WebRowSet getStrTestWb() {
		return strTestWb;
	}

	public void setStrTestWb(WebRowSet strTestWb) {
		this.strTestWb = strTestWb;
	}

	public WebRowSet getStrInitialWb() {
		return strInitialWb;
	}

	public void setStrInitialWb(WebRowSet strInitialWb) {
		this.strInitialWb = strInitialWb;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getCrNoSelected()
	{
		return crNoSelected;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public void setCrNoSelected(String crNoSelected)
	{
		this.crNoSelected = crNoSelected;
	}

	public String getSortType()
	{
		return sortType;
	}

	public void setSortType(String sortType)
	{
		this.sortType = sortType;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{

		this.setDeskMenuId("");
		this.setSortType("");
		this.setDepartmentUnitCode("");
		this.setMode("");
		this.setCrNoSelected("");
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}



}
