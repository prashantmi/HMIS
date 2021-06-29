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

/**
 * @author s.singaravelan
 * Creation Date:- 04-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */

@SuppressWarnings({ "rawtypes", "serial" })
public class PrimaryCategoryChangeSUP extends CRNoSUP implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String afterGo;	
	
	protected String patPrimaryCatCode;
	private String patPrimaryCat;
    private String patPrevPrimaryCatCode;
	private String patPrevPrimaryCat;
    private String patNewPrimaryCatCode;
	private String patNewPrimaryCat;
	private String patAge;
    private String remarks;
    private String hmode;
    private String patIdNo;
    private String cardNo;
    private String employeeValidUpto;
    private String cardValidUpto;
    private String issuingAuthority;
    private String verificationDocument;
    private String primaryCatAndExpiryDay;
    private String empIdChk;
    private String patCatCode;
    
    private String entrydate;
    private String isDetailAvailable;
    private String isRenewal;
    private String isCatExpired;
    private String isIdRequired;
    private String prevPatIdNo;
    private String unavailValidUpto;
    private String prevVerificationDocument;
    private String isAlternateId;
    private String strpatcatlog;
    
	protected String strNormalMsg;
	protected String strWarningMsg;
	protected String errorMessage;
	
	protected String patcatchangelogFlag;
	
	protected String regConfigCatCode;
	protected String regConfigCatAgeLimit;
	
	private String patFirstName;
	private String clientCode;
	private String clientName;

	private String staffCardNo;
	private String staffCardName;
	private String relationWithStaff;
	private String relationNameWithStaff;
	private String cardvalidityDate;
	
	private String agsDistrictCode;
	private String agsCounterNo;
	private String agsNo;
	/*Start: Surabhi
	 * reason: for adding the credit limit field in change patient category if the category is aarogyashri
	 * date : 29-7-2016*/
	private String agsCreditLimit;
//End	

	private String patPrimaryCatGrp; 
	private String patPrimaryCatGrpCode;
	private String patGuardianName;
	private String patMotherName;
	private String patHusbandName;
	private String oldhosPrimaryCatCode;
	private String memSlNo;
	
	public String getMemSlNo() {
		return memSlNo;
	}
	public void setMemSlNo(String memSlNo) {
		this.memSlNo = memSlNo;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}	
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	
	public String getPatPrevPrimaryCatCode() {
		return patPrevPrimaryCatCode;
	}
	public void setPatPrevPrimaryCatCode(String patPrevPrimaryCatCode) {
		this.patPrevPrimaryCatCode = patPrevPrimaryCatCode;
	}
	public String getPatPrevPrimaryCat() {
		return patPrevPrimaryCat;
	}
	public void setPatPrevPrimaryCat(String patPrevPrimaryCat) {
		this.patPrevPrimaryCat = patPrevPrimaryCat;
	}
	public String getPatNewPrimaryCatCode() {
		return patNewPrimaryCatCode;
	}
	public void setPatNewPrimaryCatCode(String patNewPrimaryCatCode) {
		this.patNewPrimaryCatCode = patNewPrimaryCatCode;
	}
	public String getPatNewPrimaryCat() {
		return patNewPrimaryCat;
	}
	public void setPatNewPrimaryCat(String patNewPrimaryCat) {
		this.patNewPrimaryCat = patNewPrimaryCat;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPatIdNo() {
		return patIdNo;
	}
	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getEmployeeValidUpto() {
		return employeeValidUpto;
	}
	public void setEmployeeValidUpto(String employeeValidUpto) {
		this.employeeValidUpto = employeeValidUpto;
	}
	public String getCardValidUpto() {
		return cardValidUpto;
	}
	public void setCardValidUpto(String cardValidUpto) {
		this.cardValidUpto = cardValidUpto;
	}
	public String getIssuingAuthority() {
		return issuingAuthority;
	}
	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}
	public String getVerificationDocument() {
		return verificationDocument;
	}
	public void setVerificationDocument(String verificationDocument) {
		this.verificationDocument = verificationDocument;
	}
	public String getPrimaryCatAndExpiryDay() {
		return primaryCatAndExpiryDay;
	}
	public void setPrimaryCatAndExpiryDay(String primaryCatAndExpiryDay) {
		this.primaryCatAndExpiryDay = primaryCatAndExpiryDay;
	}
	public String getEmpIdChk() {
		return empIdChk;
	}
	public void setEmpIdChk(String empIdChk) {
		this.empIdChk = empIdChk;
	}
	public String getPatCatCode() {
		return patCatCode;
	}
	public void setPatCatCode(String patCatCode) {
		this.patCatCode = patCatCode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getConfigFlag() {
		return configFlag;
	}
	public void setConfigFlag(String configFlag) {
		this.configFlag = configFlag;
	}
	public void reset(){
		this.patCrNo="";
		this.patIdNo="";
		this.cardNo="";
		this.verificationDocument="";
		this.patCatCode="";
		this.remarks="";
		this.isDetailAvailable="";
		this.isRenewal="";
		this.isCatExpired="";
		this.isIdRequired="";
		this.prevPatIdNo="";
		/*Start : Surabhi
		 * Reason : Changes done reset the form when cancel button is pressed
		 * date : 7th oct 2016 */
		this.agsCreditLimit="";
		this.agsCounterNo="";
		this.agsDistrictCode="-1";
		this.agsNo="";
		// end
	}
	public void clearMessageField(){
		this.setStrNormalMsg("");
		this.setErrorMessage("");
		this.setStrWarningMsg("");
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getIsDetailAvailable() {
		return isDetailAvailable;
	}
	public void setIsDetailAvailable(String isDetailAvailable) {
		this.isDetailAvailable = isDetailAvailable;
	}
	public String getIsRenewal() {
		return isRenewal;
	}
	public void setIsRenewal(String isRenewal) {
		this.isRenewal = isRenewal;
	}
	public String getIsCatExpired() {
		return isCatExpired;
	}
	public void setIsCatExpired(String isCatExpired) {
		this.isCatExpired = isCatExpired;
	}
	public String getIsIdRequired() {
		return isIdRequired;
	}
	public void setIsIdRequired(String isIdRequired) {
		this.isIdRequired = isIdRequired;
	}
	public String getPrevPatIdNo() {
		return prevPatIdNo;
	}
	public void setPrevPatIdNo(String prevPatIdNo) {
		this.prevPatIdNo = prevPatIdNo;
	}
	public String getUnavailValidUpto() {
		return unavailValidUpto;
	}
	public void setUnavailValidUpto(String unavailValidUpto) {
		this.unavailValidUpto = unavailValidUpto;
	}
	public String getPrevVerificationDocument() {
		return prevVerificationDocument;
	}
	public void setPrevVerificationDocument(String prevVerificationDocument) {
		this.prevVerificationDocument = prevVerificationDocument;
	}
	public String getIsAlternateId() {
		return isAlternateId;
	}
	public void setIsAlternateId(String isAlternateId) {
		this.isAlternateId = isAlternateId;
	}
	public String getstrpatcatlog() {
		return strpatcatlog;
	}
	public void setstrpatcatlog(String strpatcatlog) {
		this.strpatcatlog= strpatcatlog;
	}
	public String getpatcatchangelogFlag() {
		return patcatchangelogFlag;
	}
	public void setpatcatchangelogFlag(String patcatchangelogFlag) {
		this.patcatchangelogFlag= patcatchangelogFlag;
	}
	public String getentrydate() {
		return entrydate;
	}
	public void setentrydate(String entrydate) {
		this.entrydate= entrydate;
	}
	public String getRegConfigCatCode() {
		return regConfigCatCode;
	}
	public void setRegConfigCatCode(String regConfigCatCode) {
		this.regConfigCatCode = regConfigCatCode;
	}
	public String getRegConfigCatAgeLimit() {
		return regConfigCatAgeLimit;
	}
	public void setRegConfigCatAgeLimit(String regConfigCatAgeLimit) {
		this.regConfigCatAgeLimit = regConfigCatAgeLimit;
	}
	public String getRelationWithStaff() {
		return relationWithStaff;
	}

	public void setRelationWithStaff(String relationWithStaff) {
		this.relationWithStaff = relationWithStaff;
	}
	public String getAgsDistrictCode() {
		return agsDistrictCode;
	}

	public void setAgsDistrictCode(String agsDistrictCode) {
		this.agsDistrictCode = agsDistrictCode;
	}

	public String getAgsCounterNo() {
		return agsCounterNo;
	}

	public void setAgsCounterNo(String agsCounterNo) {
		this.agsCounterNo = agsCounterNo;
	}

	public String getAgsNo() {
		return agsNo;
	}
	public void setAgsNo(String agsNo) {
		this.agsNo = agsNo;
	}
	public String getRelationNameWithStaff() {
		return relationNameWithStaff;
	}

	public void setRelationNameWithStaff(String relationNameWithStaff) {
		this.relationNameWithStaff = relationNameWithStaff;
	}
	public String getPatPrimaryCatGrp() {
		return patPrimaryCatGrp;
	}
	public void setPatPrimaryCatGrp(String patPrimaryCatGrp) {
		this.patPrimaryCatGrp = patPrimaryCatGrp;
	}
	public String getPatPrimaryCatGrpCode() {
		return patPrimaryCatGrpCode;
	}

	public void setPatPrimaryCatGrpCode(String patPrimaryCatGrpCode) {
		this.patPrimaryCatGrpCode = patPrimaryCatGrpCode;
	}
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	public String getStaffCardNo() {
		return staffCardNo;
	}

	public void setStaffCardNo(String staffCardNo) {
		this.staffCardNo = staffCardNo;
	}

	public String getStaffCardName() {
		return staffCardName;
	}

	public void setStaffCardName(String staffCardName) {
		this.staffCardName = staffCardName;
	}
	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	/**
	 * Retrieves patFirstName.
	 * @return Value of patFirstName.	
	 */
	public String getPatFirstName()
	{
		return patFirstName;
	}
	public String getPatMotherName()
	{
		return patMotherName;
	}

	public void setPatMotherName(String patMotherName)
	{
		this.patMotherName = patMotherName;
	}
	public String getPatHusbandName()
	{
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName)
	{
		this.patHusbandName = patHusbandName;
	}
	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	/**
	 * Retrieves patGuardianName.
	 * @return Value of patGuardianName.	
	 */
	public String getPatGuardianName()
	{
		return patGuardianName;
	}
	public String getCardvalidityDate() {
		return cardvalidityDate;
	}

	public void setCardvalidityDate(String cardvalidityDate) {
		this.cardvalidityDate = cardvalidityDate;
	}
	public String getOldhosPrimaryCatCode() {
		return oldhosPrimaryCatCode;
	}

	public void setOldhosPrimaryCatCode(String oldhosPrimaryCatCode) {
		this.oldhosPrimaryCatCode = oldhosPrimaryCatCode;
	}
	public String getAgsCreditLimit() {
		return agsCreditLimit;
	}
	public void setAgsCreditLimit(String agsCreditLimit) {
		this.agsCreditLimit = agsCreditLimit;
	}

}
