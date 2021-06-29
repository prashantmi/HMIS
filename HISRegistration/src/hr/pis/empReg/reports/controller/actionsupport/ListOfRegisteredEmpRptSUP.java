package hr.pis.empReg.reports.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;



public class ListOfRegisteredEmpRptSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware
{
	protected static final long serialVersionUID = 1L; 
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	
	protected String strIsWatermarkReq= "";
	protected String strIsLogoReq="";
	protected String strIsHeaderReq= "";
	protected String strIsFooterReq="";
	protected String strReportType="";
	protected String strLabelLang="";
	protected String strPageFlag="";
	protected String intEmpDesig="";
	protected String strEmpDesig="";
	protected String intEmpDept="";
	protected String strEmpDept="";
	protected String intGender="";
	protected String strGender="";
	protected String intFinalStatus="";
	protected String strFinalStatus="";
	protected String intNatureOfJob="";
	protected String strNatureOfJob="";
	protected String dtPeriodFrom="";
	protected String dtPeriodTo="";
	protected String strIsPeriodReq="";
	protected String strRptGenType="";
	protected String strEmpNo="";
	protected String strEmpName="";
	
	
	
	
	
	public String getStrEmpNo() {
		return strEmpNo;
	}

	public void setStrEmpNo(String strEmpNo) {
		this.strEmpNo = strEmpNo;
	}

	public String getStrEmpName() {
		return strEmpName;
	}

	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
	}

	public String getStrRptGenType() {
		return strRptGenType;
	}

	public void setStrRptGenType(String strRptGenType) {
		this.strRptGenType = strRptGenType;
	}

	public String getIntGender() {
		return intGender;
	}

	public void setIntGender(String intGender) {
		this.intGender = intGender;
	}

	public String getStrGender() {
		return strGender;
	}

	public void setStrGender(String strGender) {
		this.strGender = strGender;
	}

	public String getIntFinalStatus() {
		return intFinalStatus;
	}

	public void setIntFinalStatus(String intFinalStatus) {
		this.intFinalStatus = intFinalStatus;
	}

	public String getStrFinalStatus() {
		return strFinalStatus;
	}

	public void setStrFinalStatus(String strFinalStatus) {
		this.strFinalStatus = strFinalStatus;
	}

	public String getStrIsPeriodReq() {
		return strIsPeriodReq;
	}

	public void setStrIsPeriodReq(String strIsPeriodReq) {
		this.strIsPeriodReq = strIsPeriodReq;
	}

	public String getStrLabelLang() {
		return strLabelLang;
	}

	public void setStrLabelLang(String strLabelLang) {
		this.strLabelLang = strLabelLang;
	}

	public HttpServletRequest getObjRequest() {
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest) {
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse() {
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse) {
		this.objResponse = objResponse;
	}

	public ServletContext getObjContext() {
		return objContext;
	}

	public void setObjContext(ServletContext objContext) {
		this.objContext = objContext;
	}

	public Map getMapSesion() {
		return mapSesion;
	}

	public void setMapSesion(Map mapSesion) {
		this.mapSesion = mapSesion;
	}

	public String getStrIsWatermarkReq() {
		return strIsWatermarkReq;
	}

	public void setStrIsWatermarkReq(String strIsWatermarkReq) {
		this.strIsWatermarkReq = strIsWatermarkReq;
	}

	public String getStrIsLogoReq() {
		return strIsLogoReq;
	}

	public void setStrIsLogoReq(String strIsLogoReq) {
		this.strIsLogoReq = strIsLogoReq;
	}

	public String getStrIsHeaderReq() {
		return strIsHeaderReq;
	}

	public void setStrIsHeaderReq(String strIsHeaderReq) {
		this.strIsHeaderReq = strIsHeaderReq;
	}

	public String getStrIsFooterReq() {
		return strIsFooterReq;
	}

	public void setStrIsFooterReq(String strIsFooterReq) {
		this.strIsFooterReq = strIsFooterReq;
	}

	public String getStrReportType() {
		return strReportType;
	}

	public void setStrReportType(String strReportType) {
		this.strReportType = strReportType;
	}

	public String getStrPageFlag() {
		return strPageFlag;
	}

	public void setStrPageFlag(String strPageFlag) {
		this.strPageFlag = strPageFlag;
	}

	public String getIntEmpDesig() {
		return intEmpDesig;
	}

	public void setIntEmpDesig(String intEmpDesig) {
		this.intEmpDesig = intEmpDesig;
	}

	public String getStrEmpDesig() {
		return strEmpDesig;
	}

	public void setStrEmpDesig(String strEmpDesig) {
		this.strEmpDesig = strEmpDesig;
	}

	public String getIntEmpDept() {
		return intEmpDept;
	}

	public void setIntEmpDept(String intEmpDept) {
		this.intEmpDept = intEmpDept;
	}

	public String getStrEmpDept() {
		return strEmpDept;
	}

	public void setStrEmpDept(String strEmpDept) {
		this.strEmpDept = strEmpDept;
	}

	public String getIntNatureOfJob() {
		return intNatureOfJob;
	}

	public void setIntNatureOfJob(String intNatureOfJob) {
		this.intNatureOfJob = intNatureOfJob;
	}

	public String getStrNatureOfJob() {
		return strNatureOfJob;
	}

	public void setStrNatureOfJob(String strNatureOfJob) {
		this.strNatureOfJob = strNatureOfJob;
	}

	public String getDtPeriodFrom() {
		return dtPeriodFrom;
	}

	public void setDtPeriodFrom(String dtPeriodFrom) {
		this.dtPeriodFrom = dtPeriodFrom;
	}

	public String getDtPeriodTo() {
		return dtPeriodTo;
	}

	public void setDtPeriodTo(String dtPeriodTo) {
		this.dtPeriodTo = dtPeriodTo;
	}

	public void clear()
	{
		this.strIsWatermarkReq= "";
		this.strIsLogoReq="";
		this.strIsHeaderReq= "";
		this.strIsFooterReq="";
		this.intEmpDesig="";
		this.strEmpDesig="";
		this.intEmpDept="";
		this.strEmpDept="";
		this.intNatureOfJob="";
		this.strNatureOfJob="";
		this.dtPeriodFrom="";
		this.dtPeriodTo="";
		this.strIsPeriodReq="";
		this.strRptGenType="";
		this.strEmpNo="";
		this.strEmpName="";
			
	}
	
	@Override 
	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	@Override 
	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}

	@Override 
	public void setSession(Map sessionMap)
	{
		this.mapSesion = sessionMap;
	}

	public void prepare() throws Exception {
		
		
	}

	
	

}
