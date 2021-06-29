package registration.transactions.controller.actionsupport;
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

public class AppointmentCancellationSUP extends CRNoSUP implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String AfterGo;
	protected String patCrNo ;
	protected String patAptNo;
	protected String patFirstName;
	protected String patAge;
	protected String patAddContactNo;
	protected String patAptSlot;
	protected String department;
	protected String remarks;
	protected String mode;
	public String getPatAptNoToRegister() {
		return patAptNoToRegister;
	}
	public void setPatAptNoToRegister(String patAptNoToRegister) {
		this.patAptNoToRegister = patAptNoToRegister;
	}
	protected String patIdNo;
	protected String isDetailAvailable;
    
	protected String strNormalMsg;
	protected String strWarningMsg;
	protected String errorMessage;
	private String hmode;
	private String searchId;
	private String searchValue;
	private String patAptNoToRegister;

	public String getSearchId() {
		return searchId;
	}
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getAfterGo() {
		return AfterGo;
	}
	public void setAfterGo(String afterGo) {
		AfterGo = afterGo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getIsDetailAvailable() {
		return isDetailAvailable;
	}
	public void setIsDetailAvailable(String isDetailAvailable) {
		this.isDetailAvailable = isDetailAvailable;
	}
	public String getPatIdNo() {
		return patIdNo;
	}
	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatAptNo() {
		return patAptNo;
	}
	public void setPatAptNo(String patAptNo) {
		this.patAptNo = patAptNo;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatAddContactNo() {
		return patAddContactNo;
	}
	public void setPatAddContactNo(String patAddContactNo) {
		this.patAddContactNo = patAddContactNo;
	}
	public String getPatAptSlot() {
		return patAptSlot;
	}
	public void setPatAptSlot(String patAptSlot) {
		this.patAptSlot = patAptSlot;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setMapSesion(Map mapSesion) {
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
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
	
	
}
