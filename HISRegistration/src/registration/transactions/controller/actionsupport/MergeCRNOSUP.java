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
import com.opensymphony.xwork2.Preparable;

public class MergeCRNOSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String afterGo;
	
	protected String patCrNo;
	protected String patMainCrNo;
	protected String patNotUsedCrNo;
	protected String isMerged;
	protected String reason;
	protected String remarks;
	protected String hmode;
	protected String hiddenNotUsedCRNo[];
	protected String deleteCrNo;
	protected String revokedCrNo;
	protected String crNoToRetrieve[];
	protected String concatedCrNo;
	protected String hiddenMergedCRNo[];
	
	protected String hrgstr_fname;
	protected String hrgstr_mname;
	protected String hrgstr_lname;
	protected String hrgnum_id_no;
	protected String hrgstr_contact_no;
	protected String hrgstr_national_id;
	
	protected String strNormalMsg;
	protected String strWarningMsg;
	protected String errorMessage;
	
	protected String patPrimaryCatCode;		
	protected String patPrimaryCatGrpCode;
	protected String sysdate;
	
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
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
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
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	
	public String getPatPrimaryCatGrpCode() {
		return patPrimaryCatGrpCode;
	}
	public void setPatPrimaryCatGrpCode(String patPrimaryCatGrpCode) {
		this.patPrimaryCatGrpCode = patPrimaryCatGrpCode;
	}
	public String getSysdate() {
		return sysdate;
	}
	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	public void reset(){
		this.setPatCrNo("");
		this.setPatPrimaryCatCode("");
		this.setAfterGo("0");
		//this.setStrNormalMsg("");
		//this.setErrorMessage("");
		//this.setStrWarningMsg("");
		//this.setPrint("");
		//this.setSaveSuccessful("");
	}
	public void clearMessageField(){
		this.setStrNormalMsg("");
		this.setErrorMessage("");
		this.setStrWarningMsg("");
		//this.setSaveSuccessful("");
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getPatMainCrNo() {
		return patMainCrNo;
	}
	public void setPatMainCrNo(String patMainCrNo) {
		this.patMainCrNo = patMainCrNo;
	}
	public String getPatNotUsedCrNo() {
		return patNotUsedCrNo;
	}
	public void setPatNotUsedCrNo(String patNotUsedCrNo) {
		this.patNotUsedCrNo = patNotUsedCrNo;
	}
	public String getIsMerged() {
		return isMerged;
	}
	public void setIsMerged(String isMerged) {
		this.isMerged = isMerged;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getHiddenNotUsedCRNo() {
		return hiddenNotUsedCRNo;
	}
	public void setHiddenNotUsedCRNo(String[] hiddenNotUsedCRNo) {
		this.hiddenNotUsedCRNo = hiddenNotUsedCRNo;
	}
	public String getDeleteCrNo() {
		return deleteCrNo;
	}
	public void setDeleteCrNo(String deleteCrNo) {
		this.deleteCrNo = deleteCrNo;
	}
	public String getRevokedCrNo() {
		return revokedCrNo;
	}
	public void setRevokedCrNo(String revokedCrNo) {
		this.revokedCrNo = revokedCrNo;
	}
	public String[] getCrNoToRetrieve() {
		return crNoToRetrieve;
	}
	public void setCrNoToRetrieve(String[] crNoToRetrieve) {
		this.crNoToRetrieve = crNoToRetrieve;
	}
	public String getConcatedCrNo() {
		return concatedCrNo;
	}
	public void setConcatedCrNo(String concatedCrNo) {
		this.concatedCrNo = concatedCrNo;
	}
	public String[] getHiddenMergedCRNo() {
		return hiddenMergedCRNo;
	}
	public void setHiddenMergedCRNo(String[] hiddenMergedCRNo) {
		this.hiddenMergedCRNo = hiddenMergedCRNo;
	}
	public String getHrgstr_fname() {
		return hrgstr_fname;
	}
	public void setHrgstr_fname(String hrgstr_fname) {
		this.hrgstr_fname = hrgstr_fname;
	}
	public String getHrgstr_mname() {
		return hrgstr_mname;
	}
	public void setHrgstr_mname(String hrgstr_mname) {
		this.hrgstr_mname = hrgstr_mname;
	}
	public String getHrgstr_lname() {
		return hrgstr_lname;
	}
	public void setHrgstr_lname(String hrgstr_lname) {
		this.hrgstr_lname = hrgstr_lname;
	}
	public String getHrgnum_id_no() {
		return hrgnum_id_no;
	}
	public void setHrgnum_id_no(String hrgnum_id_no) {
		this.hrgnum_id_no = hrgnum_id_no;
	}
	public String getHrgstr_contact_no() {
		return hrgstr_contact_no;
	}
	public void setHrgstr_contact_no(String hrgstr_contact_no) {
		this.hrgstr_contact_no = hrgstr_contact_no;
	}
	public String getHrgstr_national_id() {
		return hrgstr_national_id;
	}
	public void setHrgstr_national_id(String hrgstr_national_id) {
		this.hrgstr_national_id = hrgstr_national_id;
	}
	
	
}
