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

public abstract class BarcodeGenerationSUP extends CRNoSUP implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
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
	protected String departmentToGenerateBarcode;
	protected String registerDate;
	protected String printHtml;
	protected String printHtmlTemp;
	protected String isChoiceBack;
	protected String isPrintFlag;
	protected String isReprint; 
	protected String saveConfirmFlag;
	protected String barCodeGenSize;
	protected String patAge;
	protected String patAgeUnit;
	protected String patGenderCode;
	protected String patGender;
	protected String patName;
	protected String patMobile;
	

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

	public String getDepartmentToGenerateBarcode() {
		return departmentToGenerateBarcode;
	}

	public void setDepartmentToGenerateBarcode(String departmentToGenerateBarcode) {
		this.departmentToGenerateBarcode = departmentToGenerateBarcode;
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
	public BarcodeGenerationSUP()
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

	public String getSaveConfirmFlag() {
		return saveConfirmFlag;
	}

	public void setSaveConfirmFlag(String saveConfirmFlag) {
		this.saveConfirmFlag = saveConfirmFlag;
	}

	public String getBarCodeGenSize() {
		return barCodeGenSize;
	}

	public void setBarCodeGenSize(String barCodeGenSize) {
		this.barCodeGenSize = barCodeGenSize;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getPatAgeUnit() {
		return patAgeUnit;
	}

	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}

	public String getPatGenderCode() {
		return patGenderCode;
	}

	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}

	public String getPatGender() {
		return patGender;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatMobile() {
		return patMobile;
	}

	public void setPatMobile(String patMobile) {
		this.patMobile = patMobile;
	}
}
