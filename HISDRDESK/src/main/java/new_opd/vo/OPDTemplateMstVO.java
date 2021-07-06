package new_opd.vo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;

public class OPDTemplateMstVO {


	private String mode;
	private String deskMenuId;
	private String strModifyData;
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
	private WebRowSet strTemplateCatWb=null;
	private WebRowSet strTemalteListingData=null;
	private String strTemplateModifyHtml;
	//private String strDeptCode;
	private String strIntiialDeptCode;
	private String strDeptUnitCode;
	private String strRoomCode;
	private String strPuk;
	private String strEpisodeCode;
	private String strVistNo;
	private WebRowSet strDrugProfileWs=null;
	private ArrayList<JSONObject> strDrugPrevCodeJSON=null;
	private ArrayList<JSONObject> strInvPrevCodeJSON=null;
	private ArrayList<JSONObject> strInvPrevVistReasonJSON=null;
	private ArrayList<JSONObject> strInvPrevDiagnosisJSON=null;
	private ArrayList<JSONObject> strPreVitalJSON=null;
	private String strHiddenDeptCode;
	
	private String strHtmlString;
	private String strJsonParaMetereIdString;
	private String strTemplateName;
	private String[] strDeptCode;
	
	private String strTemplateCatCode = "";
	private String strIsActive = "";
	private String strTemplateType = "";
	private String strTemplateNo;
	private String StrReplaceTerm="";
	private WebRowSet strHiddenParameter=null;
	private WebRowSet strAutoCompleteWs=null;
	private WebRowSet strTempCatWs=null;
	
	


	public String getStrTemplateModifyHtml() {
		return strTemplateModifyHtml;
	}

	public String getStrModifyData() {
		return strModifyData;
	}

	public void setStrModifyData(String strModifyData) {
		this.strModifyData = strModifyData;
	}

	public void setStrTemplateModifyHtml(String strTemplateModifyHtml) {
		this.strTemplateModifyHtml = strTemplateModifyHtml;
	}

	public String getStrTemplateType() {
		return strTemplateType;
	}

	public void setStrTemplateType(String strTemplateType) {
		this.strTemplateType = strTemplateType;
	}

	public String getStrTemplateCatCode() {
		return strTemplateCatCode;
	}

	public void setStrTemplateCatCode(String strTemplateCatCode) {
		this.strTemplateCatCode = strTemplateCatCode;
	}

	public String getStrIsActive() {
		return strIsActive;
	}

	public void setStrIsActive(String strIsActive) {
		this.strIsActive = strIsActive;
	}

	public WebRowSet getStrTemplateCatWb() {
		return strTemplateCatWb;
	}

	public void setStrTemplateCatWb(WebRowSet strTemplateCatWb) {
		this.strTemplateCatWb = strTemplateCatWb;
	}

	public WebRowSet getStrTemalteListingData() {
		return strTemalteListingData;
	}

	public void setStrTemalteListingData(WebRowSet strTemalteListingData) {
		this.strTemalteListingData = strTemalteListingData;
	}

	public String getStrTemplateName() {
		return strTemplateName;
	}

	public void setStrTemplateName(String strTemplateName) {
		this.strTemplateName = strTemplateName;
	}

	public void setStrDeptCode(String[] strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrTemplateNo() {
		return strTemplateNo;
	}

	public void setStrTemplateNo(String strTemplateNo) {
		this.strTemplateNo = strTemplateNo;
	}

	public String getStrHtmlString() {
		return strHtmlString;
	}

	public void setStrHtmlString(String strHtmlString) {
		this.strHtmlString = strHtmlString;
	}

	public String getStrJsonParaMetereIdString() {
		return strJsonParaMetereIdString;
	}

	public void setStrJsonParaMetereIdString(String strJsonParaMetereIdString) {
		this.strJsonParaMetereIdString = strJsonParaMetereIdString;
	}

	public String getStrHiddenDeptCode() {
		return strHiddenDeptCode;
	}

	public void setStrHiddenDeptCode(String strHiddenDeptCode) {
		this.strHiddenDeptCode = strHiddenDeptCode;
	}

	public WebRowSet getStrDrugProfileWs() {
		return strDrugProfileWs;
	}

	public void setStrDrugProfileWs(WebRowSet strDrugProfileWs) {
		this.strDrugProfileWs = strDrugProfileWs;
	}

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

	/*public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}*/

	public String getStrRoomCode() {
		return strRoomCode;
	}

	public String[] getStrDeptCode() {
		return strDeptCode;
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

	public WebRowSet getStrHiddenParameter() {
		return strHiddenParameter;
	}

	public void setStrHiddenParameter(WebRowSet strHiddenParameter) {
		this.strHiddenParameter = strHiddenParameter;
	}
	

	public String getStrReplaceTerm() {
		return StrReplaceTerm;
	}

	public void setStrReplaceTerm(String strReplaceTerm) {
		StrReplaceTerm = strReplaceTerm;
	}

	public WebRowSet getStrAutoCompleteWs() {
		return strAutoCompleteWs;
	}

	public void setStrAutoCompleteWs(WebRowSet strAutoCompleteWs) {
		this.strAutoCompleteWs = strAutoCompleteWs;
	}

	public WebRowSet getStrTempCatWs() {
		return strTempCatWs;
	}

	public void setStrTempCatWs(WebRowSet strTempCatWs) {
		this.strTempCatWs = strTempCatWs;
	}



}
