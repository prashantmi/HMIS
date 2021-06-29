package registration.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class MlcToNonMlcNewSUP extends CRNoSUP implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String afterGo;
	protected String strNormalMsg;
	protected String strWarningMsg;
	protected String strErrorMessage;
	
	protected String episodeCode;
	protected String episodeVisitNo;
	protected String remarks;
	protected String mlcNo;
	protected String[] selEpisodeCode;
	protected String[] selEpisodeVisitNo;
	protected String[] selRemarks;
	protected String[] selMlcNo;

	
	public HttpServletRequest getObjRequest()
	{
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest)
	{
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse()
	{
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse)
	{
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

	public String getAfterGo() {
		return afterGo;
	}

	public void setAfterGo(String afterGo) {
		this.afterGo = afterGo;
	}

	public String getStrNormalMsg() {
		return strNormalMsg;
	}

	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}

	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	public String getStrErrorMessage() {
		return strErrorMessage;
	}

	public void setStrErrorMessage(String strErrorMessage) {
		this.strErrorMessage = strErrorMessage;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMlcNo() {
		return mlcNo;
	}

	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}

	public String[] getSelEpisodeCode() {
		return selEpisodeCode;
	}

	public void setSelEpisodeCode(String[] selEpisodeCode) {
		this.selEpisodeCode = selEpisodeCode;
	}

	public String[] getSelEpisodeVisitNo() {
		return selEpisodeVisitNo;
	}

	public void setSelEpisodeVisitNo(String[] selEpisodeVisitNo) {
		this.selEpisodeVisitNo = selEpisodeVisitNo;
	}

	public String[] getSelRemarks() {
		return selRemarks;
	}

	public void setSelRemarks(String[] selRemarks) {
		this.selRemarks = selRemarks;
	}

	public String[] getSelMlcNo() {
		return selMlcNo;
	}

	public void setSelMlcNo(String[] selMlcNo) {
		this.selMlcNo = selMlcNo;
	}
	
	
}
