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


public class RegisteredEmpDtlRptSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware
{
	protected static final long serialVersionUID = 1L; 
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	

	protected String strEmpNo="";
	protected String strEmpName="";
	protected String strIsWatermarkReq= "";
	protected String strIsLogoReq="";
	protected String strIsHeaderReq= "";
	protected String strIsFooterReq="";
	protected String strReportType="";
	protected String strPageMode="";
	protected String strLabelLang="";
	protected String intRptMode="";
	
	
	


public String getIntRptMode() {
		return intRptMode;
	}

	public void setIntRptMode(String intRptMode) {
		this.intRptMode = intRptMode;
	}

public String getStrLabelLang() {
		return strLabelLang;
	}

	public void setStrLabelLang(String strLabelLang) {
		this.strLabelLang = strLabelLang;
	}

public void setStrEmpNo(String strEmpNo) {
	this.strEmpNo = strEmpNo;
}

public String getStrEmpNo() {
	return strEmpNo;
}



public String getStrEmpName() {
	return strEmpName;
}

public void setStrEmpName(String strEmpName) {
	this.strEmpName = strEmpName;
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

public String getStrReportType() {
	return strReportType;
}

public void setStrReportType(String strReportType) {
	this.strReportType = strReportType;
}





public String getStrPageMode() {
	return strPageMode;
}

public void setStrPageMode(String strPageMode) {
	this.strPageMode = strPageMode;
}

public void clear()
{
	
	this.strEmpNo="";
	this.strEmpName="";
	this.strIsWatermarkReq= "";
	this.strIsLogoReq="";
	this.strIsHeaderReq= "";
	this.strIsFooterReq="";
	this.strReportType="";
	this.strPageMode="";
	this.strLabelLang="";
	this.intRptMode="";
	
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
