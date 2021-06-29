/**********************************************************
 Project:	   AHIMS_G5	
 File:         LoginFeatureSUP.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hislogin.transactions.actionsupport;

import java.util.List;

import hislogin.transactions.utl.LoginFeaturesUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class LoginFeatureSUP extends ActionSupport implements ServletRequestAware, ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;

	protected String varSSOTicketGrantingTicket;

	protected String varStatus;

	
	// Forgot Password
	protected String varUserName;
	protected String varQuestionId;
	protected String varHintAnswer;
	protected String varOldHintAnswer;
	protected String varUserId;
	protected String varUserSeatId;

	protected String varOldPassword;
	protected String varPassword;
	protected String varConfirmPassword;
	protected String frDate;
	protected String toDate;
	
	// First Login
	protected String varUsrName;
	private String varMobileNumber;
	private String varEmailId;
	private String varMenuId;
	private String varModuleId;
	private String[] varFavMenuId;
	private String captchaResponse;

	private String varSecurePass;
	private String varSK;
	
	//Added by Vasu for Password Validation
	private String newPassword;
	
	
	
	public LoginFeatureSUP()
	{
		varStatus =  LoginFeaturesUTL.LOG_FEATURE_STATUS_INITIAL;
		varUserName = "";
		varQuestionId = "";
		varHintAnswer = "";
		varOldPassword = "";
		varPassword = "";
		varConfirmPassword = "";
		varFavMenuId = new String[0];
	}

	public void clear()
	{
		varStatus =  LoginFeaturesUTL.LOG_FEATURE_STATUS_INITIAL;
		varUserName = "";
		varQuestionId = "";
		varHintAnswer = "";
		varOldPassword = "";
		varPassword = "";
		varConfirmPassword = "";
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

	public String getVarUserName()
	{
		return varUserName;
	}

	public void setVarUserName(String varUserName)
	{
		this.varUserName = varUserName;
	}

	public String getVarPassword()
	{
		return varPassword;
	}

	public void setVarPassword(String varPassword)
	{
		this.varPassword = varPassword;
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

	public String getVarSSOTicketGrantingTicket()
	{
		return varSSOTicketGrantingTicket;
	}

	public void setVarSSOTicketGrantingTicket(String varSSOTicketGrantingTicket)
	{
		this.varSSOTicketGrantingTicket = varSSOTicketGrantingTicket;
	}

	public String getVarQuestionId()
	{
		return varQuestionId;
	}

	public void setVarQuestionId(String varQuestionId)
	{
		this.varQuestionId = varQuestionId;
	}

	public String getVarHintAnswer()
	{
		return varHintAnswer;
	}

	public void setVarHintAnswer(String varHintAnswer)
	{
		this.varHintAnswer = varHintAnswer;
	}
	

	public String getVarOldHintAnswer() {
		return varOldHintAnswer;
	}

	public void setVarOldHintAnswer(String varOldHintAnswer) {
		this.varOldHintAnswer = varOldHintAnswer;
	}

	public String getVarUserId()
	{
		return varUserId;
	}

	public void setVarUserId(String varUserId)
	{
		this.varUserId = varUserId;
	}

	public String getVarUserSeatId()
	{
		return varUserSeatId;
	}

	public void setVarUserSeatId(String varUserSeatId)
	{
		this.varUserSeatId = varUserSeatId;
	}

	public String getVarConfirmPassword()
	{
		return varConfirmPassword;
	}

	public void setVarConfirmPassword(String varConfirmPassword)
	{
		this.varConfirmPassword = varConfirmPassword;
	}

	public String getFrDate() {
		return frDate;
	}

	public void setFrDate(String frDate) {
		this.frDate = frDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getVarStatus()
	{
		return varStatus;
	}

	public void setVarStatus(String varStatus)
	{
		this.varStatus = varStatus;
	}

	public String getVarUsrName()
	{
		return varUsrName;
	}

	public void setVarUsrName(String varUsrName)
	{
		this.varUsrName = varUsrName;
	}

	public String getVarOldPassword()
	{
		return varOldPassword;
	}

	public void setVarOldPassword(String varOldPassword)
	{
		this.varOldPassword = varOldPassword;
	}


	public String getVarMobileNumber() {
		return varMobileNumber;
	}

	public void setVarMobileNumber(String varMobileNumber) {
		this.varMobileNumber = varMobileNumber;
	}

	public String getVarEmailId() {
		return varEmailId;
	}

	public void setVarEmailId(String varEmailId) {
		this.varEmailId = varEmailId;
	}
	public String getVarMenuId() {
		return varMenuId;
	}

	public void setVarMenuId(String varMenueId) {
		this.varMenuId = varMenueId;
	}
	public String getVarModuleId() {
		return varModuleId;
		
	}

	public void setVarModuleId(String varModuleId) {
		this.varModuleId = varModuleId;
	}

	public String[] getVarFavMenuId() {
		return varFavMenuId;
	}

	public void setVarFavMenuId(String[] varFavMenuId) {
		this.varFavMenuId = varFavMenuId;
	}

	



	public String getCaptchaResponse()
	{
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse)
	{
		this.captchaResponse = captchaResponse;
	}

	public String getVarSecurePass() {
		return varSecurePass;
	}

	public void setVarSecurePass(String varSecurePass) {
		this.varSecurePass = varSecurePass;
	}

	public String getVarSK() {
		return varSK;
	}

	public void setVarSK(String varSK) {
		this.varSK = varSK;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}	


}
