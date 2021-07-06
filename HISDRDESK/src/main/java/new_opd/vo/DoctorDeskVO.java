package new_opd.vo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;

public class DoctorDeskVO {

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
	private WebRowSet strOPDConfigWb=null;
	private WebRowSet strTeleConsultancyDataWb=null;
	private WebRowSet strRefeConsultancyDataWb=null;
	private WebRowSet strTestWb=null;
	private WebRowSet strDiagnosisWb=null;
	private WebRowSet strDrugWb=null;
	private WebRowSet strDosageWb=null;
	private WebRowSet strMacrosWb=null;	
	private WebRowSet strCinicalProcudreWb=null;	
	private WebRowSet strServiceAreaWebRowSet=null;	
	private WebRowSet strDeptWb=null;
	private String strMode;
	private FormFile strLocation = null;
	private String strDocumenttype;
	private String strFileData;
	private String strFtpPath;
	
	public WebRowSet getStrRefeConsultancyDataWb() {
		return strRefeConsultancyDataWb;
	}

	public void setStrRefeConsultancyDataWb(WebRowSet strRefeConsultancyDataWb) {
		this.strRefeConsultancyDataWb = strRefeConsultancyDataWb;
	}

	public FormFile getStrLocation() {
		return strLocation;
	}

	public void setStrLocation(FormFile strLocation) {
		this.strLocation = strLocation;
	}

	public String getStrDocumenttype() {
		return strDocumenttype;
	}

	public void setStrDocumenttype(String strDocumenttype) {
		this.strDocumenttype = strDocumenttype;
	}

	public String getStrFileData() {
		return strFileData;
	}

	public void setStrFileData(String strFileData) {
		this.strFileData = strFileData;
	}

	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	private WebRowSet strRefferalDeptWb=null;
	private WebRowSet strBookMarksTestWb=null;
	private String strDeptCode;
	private String strIntiialDeptCode;
	private String strDeptUnitCode;
	private String strRoomCode;
	private String strPuk;
	private String strEpisodeCode;
	private String strVistNo;
	private String streTeleconsultancyReqId;
	private WebRowSet strExternalHospitalweb=null;
	private WebRowSet strExternalInstituteweb=null;
	private WebRowSet strDrugProfileWs=null;
	private ArrayList<JSONObject> strDrugPrevCodeJSON=null;
	private ArrayList<JSONObject> strVisitReasonJSON=null;
	private ArrayList<JSONObject> strInvPrevCodeJSON=null;
	private ArrayList<JSONObject> strInvPrevVistReasonJSON=null;
	private ArrayList<JSONObject> strInvPrevDiagnosisJSON=null;
	private ArrayList<JSONObject> strPreVitalJSON=null;
	private ArrayList<JSONObject> strEtelePreVitalJSON=null;
	private ArrayList<JSONObject> strPrescriptionProfileJSON=null;
	private JSONArray strPrescriptionProfileJSONArray; 
	private String strHiddenDeptCode;
	private WebRowSet strTemplateWebRowSet=null;
	private WebRowSet strHospitalHeaderWs=null;
	private WebRowSet strExternalDepartmentListweb=null;
	



	public WebRowSet getStrExternalInstituteweb() {
		return strExternalInstituteweb;
	}

	public void setStrExternalInstituteweb(WebRowSet strExternalInstituteweb) {
		this.strExternalInstituteweb = strExternalInstituteweb;
	}

	public WebRowSet getStrServiceAreaWebRowSet() {
		return strServiceAreaWebRowSet;
	}

	public void setStrServiceAreaWebRowSet(WebRowSet strServiceAreaWebRowSet) {
		this.strServiceAreaWebRowSet = strServiceAreaWebRowSet;
	}

	public WebRowSet getStrExternalDepartmentListweb() {
		return strExternalDepartmentListweb;
	}

	public void setStrExternalDepartmentListweb(WebRowSet strExternalDepartmentListweb) {
		this.strExternalDepartmentListweb = strExternalDepartmentListweb;
	}

	public WebRowSet getStrExternalHospitalweb() {
		return strExternalHospitalweb;
	}

	public void setStrExternalHospitalweb(WebRowSet strExternalHospitalweb) {
		this.strExternalHospitalweb = strExternalHospitalweb;
	}

	public WebRowSet getStrOPDConfigWb() {
		return strOPDConfigWb;
	}

	public void setStrOPDConfigWb(WebRowSet strOPDConfigWb) {
		this.strOPDConfigWb = strOPDConfigWb;
	}

	public WebRowSet getStrHospitalHeaderWs() {
		return strHospitalHeaderWs;
	}

	public void setStrHospitalHeaderWs(WebRowSet strHospitalHeaderWs) {
		this.strHospitalHeaderWs = strHospitalHeaderWs;
	}

	public ArrayList<JSONObject> getStrVisitReasonJSON() {
		return strVisitReasonJSON;
	}

	public void setStrVisitReasonJSON(ArrayList<JSONObject> strVisitReasonJSON) {
		this.strVisitReasonJSON = strVisitReasonJSON;
	}

	public ArrayList<JSONObject> getStrEtelePreVitalJSON() {
		return strEtelePreVitalJSON;
	}

	public void setStrEtelePreVitalJSON(ArrayList<JSONObject> strEtelePreVitalJSON) {
		this.strEtelePreVitalJSON = strEtelePreVitalJSON;
	}

	public String getStreTeleconsultancyReqId() {
		return streTeleconsultancyReqId;
	}

	public void setStreTeleconsultancyReqId(String streTeleconsultancyReqId) {
		this.streTeleconsultancyReqId = streTeleconsultancyReqId;
	}


	public JSONArray getStrPrescriptionProfileJSONArray() {
		return strPrescriptionProfileJSONArray;
	}

	public void setStrPrescriptionProfileJSONArray(
			JSONArray strPrescriptionProfileJSONArray) {
		this.strPrescriptionProfileJSONArray = strPrescriptionProfileJSONArray;
	}

	public ArrayList<JSONObject> getStrPrescriptionProfileJSON() {
		return strPrescriptionProfileJSON;
	}

	public void setStrPrescriptionProfileJSON(
			ArrayList<JSONObject> strPrescriptionProfileJSON) {
		this.strPrescriptionProfileJSON = strPrescriptionProfileJSON;
	}

	public WebRowSet getStrTemplateWebRowSet() {
		return strTemplateWebRowSet;
	}

	public void setStrTemplateWebRowSet(WebRowSet strTemplateWebRowSet) {
		this.strTemplateWebRowSet = strTemplateWebRowSet;
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

	public WebRowSet getStrTeleConsultancyDataWb() {
		return strTeleConsultancyDataWb;
	}

	public void setStrTeleConsultancyDataWb(WebRowSet strTeleConsultancyDataWb) {
		this.strTeleConsultancyDataWb = strTeleConsultancyDataWb;
	}

	public String getStrFtpPath() {
		return strFtpPath;
	}

	public void setStrFtpPath(String strFtpPath) {
		this.strFtpPath = strFtpPath;
	}

	

}
