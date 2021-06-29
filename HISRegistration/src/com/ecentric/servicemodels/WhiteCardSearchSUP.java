package com.ecentric.servicemodels;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import registration.config.CharacterEncoding;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class WhiteCardSearchSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map<?, ?> mapSesion;
	
	protected String patPrimaryCatCode;
	protected String adhaarRegisteredFlag;
	protected String fromProcess;
	protected String aadhaarId;

	public HttpServletRequest getObjRequest() {
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest) {
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse()
	{
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

	public Map<?, ?> getMapSesion() {
		return mapSesion;
	}

	public void setMapSesion(Map<?, ?> mapSesion) {
		this.mapSesion = mapSesion;
	}

	@Override 
	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
		CharacterEncoding.setCharacterEncoding(this.objRequest);
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

	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getAdhaarRegisteredFlag() {
		return adhaarRegisteredFlag;
	}

	public void setAdhaarRegisteredFlag(String adhaarRegisteredFlag) {
		this.adhaarRegisteredFlag = adhaarRegisteredFlag;
	}

	public String getFromProcess() {
		return fromProcess;
	}

	public void setFromProcess(String fromProcess) {
		this.fromProcess = fromProcess;
	}

	public String getAadhaarId() {
		return aadhaarId;
	}

	public void setAadhaarId(String aadhaarId) {
		this.aadhaarId = aadhaarId;
	}


}
