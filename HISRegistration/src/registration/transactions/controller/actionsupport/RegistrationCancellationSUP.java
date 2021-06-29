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

public abstract class RegistrationCancellationSUP extends CRNoSUP implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String AfterGo;
	protected String valid;
	protected String errorMessage;
	protected String patCrNo;
	protected String hmode;
	protected String duplicateRenewRemarks;
	protected String duplicateRenewCost;
	protected String searchName;
	protected String crNoToRetrieve;
	protected String choice;
	protected String duplicateCharge;
	protected String print;
	protected String tempAfterGo;
	protected String departmentToGenerateDupCard;
	protected String RegistrationCancellationEpisodeList;
	protected String registerDate;
	protected String printHtml;
	protected String printHtmlTemp;
	protected String isChoiceBack;
	protected String isPrintFlag;
	protected String isReprint; 

	//By Mukund on 15.07.2016
	protected String isCancellationFlag;
	
	public String getIsCancellationFlag() {
		return isCancellationFlag;
	}

	public void setIsCancellationFlag(String isCancellationFlag) {
		this.isCancellationFlag = isCancellationFlag;
	}
	//End
	
	public String getPrintHtml() {
		return printHtml;
	}

	public void setPrintHtml(String printHtml) {
		this.printHtml = printHtml;
	}

	public String getIsPrintFlag() {
		return isPrintFlag;
	}

	public void setIsPrintFlag(String isPrintFlag) {
		this.isPrintFlag = isPrintFlag;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getDepartmentToGenerateDupCard() {
		return departmentToGenerateDupCard;
	}

	public void setDepartmentToGenerateDupCard(String departmentToGenerateDupCard) {
		this.departmentToGenerateDupCard = departmentToGenerateDupCard;
	}
	
	public String getRegistrationCancellationEpisodeList() {
		return RegistrationCancellationEpisodeList;
	}

	public void setRegistrationCancellationEpisodeList(String RegistrationCancellationEpisodeList) {
		this.RegistrationCancellationEpisodeList = RegistrationCancellationEpisodeList;
	}

	public String getTempAfterGo() {
		return tempAfterGo;
	}

	public void setTempAfterGo(String tempAfterGo) {
		this.tempAfterGo = tempAfterGo;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getDuplicateRenewRemarks() {
		return duplicateRenewRemarks;
	}

	public void setDuplicateRenewRemarks(String duplicateRenewRemarks) {
		this.duplicateRenewRemarks = duplicateRenewRemarks;
	}

	public String getDuplicateRenewCost() {
		return duplicateRenewCost;
	}

	public void setDuplicateRenewCost(String duplicateRenewCost) {
		this.duplicateRenewCost = duplicateRenewCost;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getCrNoToRetrieve() {
		return crNoToRetrieve;
	}

	public void setCrNoToRetrieve(String crNoToRetrieve) {
		this.crNoToRetrieve = crNoToRetrieve;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getDuplicateCharge() {
		return duplicateCharge;
	}

	public void setDuplicateCharge(String duplicateCharge) {
		this.duplicateCharge = duplicateCharge;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}


	public String getAfterGo() {
		return AfterGo;
	}
	
	public void setAfterGo(String afterGo) {
		this.AfterGo = afterGo;
	}
	public RegistrationCancellationSUP()
	{
		valid="";
		errorMessage="";
	}
	
	public void clear()
	{
		valid="";
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}


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

	public String getPrintHtmlTemp() {
		return printHtmlTemp;
	}

	public void setPrintHtmlTemp(String printHtmlTemp) {
		this.printHtmlTemp = printHtmlTemp;
	}

	public String getIsChoiceBack() {
		return isChoiceBack;
	}

	public void setIsChoiceBack(String isChoiceBack) {
		this.isChoiceBack = isChoiceBack;
	}

	public String getIsReprint() {
		return isReprint;
	}

	public void setIsReprint(String isReprint) {
		this.isReprint = isReprint;
	}
	
}
