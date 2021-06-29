package registration.controller.actionsupport;

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

public abstract class CRNoSUP extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{


	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;

	protected String patCrNo;
	protected String clientName;
	protected String mlcNo;
	protected String mlcDetail;
	protected String configFlag;
	protected String patFirstName;
	protected String patMiddleName;
	protected String patLastName;
	protected String patFirstNameInMultiLang;
    protected String patMiddleNameInMultiLang;
    protected String patLastNameInMultiLang;
	protected String patAge;
	protected String patAgeUnit;
	protected String patGender;
	protected String patGenderCode;
	protected String patPrimaryCat;
	protected String patGuardianName;
	protected String patHusbandName;
	protected String patMotherName;
	protected String registerDate;
	protected String patPrimaryCatValid;
	protected String isCatExpired;
	protected String patIsDead;
	protected String otherHospitalFlag;
	protected String isImageUploaded;
	protected String imageFileName;


		public String getOtherHospitalFlag() {
		return otherHospitalFlag;
	}

	public void setOtherHospitalFlag(String otherHospitalFlag) {
		this.otherHospitalFlag = otherHospitalFlag;
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

	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}

	public void setSession(Map sessionMap)
	{
		this.mapSesion = sessionMap;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}

	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}

	public String getPatGender() {
		return patGender;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}

	public String getPatGenderCode() {
		return patGenderCode;
	}

	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}

	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}

	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}

	public String getPatGuardianName() {
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}

	public String getPatHusbandName() {
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}

	public CRNoSUP()
	{
		patCrNo="";
		clientName="";
		mlcNo="";
		configFlag="";
		isImageUploaded="";
	}
	
	public void clear()
	{
		patCrNo="";
		clientName="";
		mlcNo="";
		configFlag="";
		isImageUploaded="";
	}
	
	
	public String getPatFirstName() {
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}

	public String getPatMiddleName() {
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	
	public String getPatMotherName() {
		return patMotherName;
	}
	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}

	public String getPatLastName() {
		return patLastName;
	}

	public String getPatFirstNameInMultiLang() {
		return patFirstNameInMultiLang;
	}

	public void setPatFirstNameInMultiLang(String patFirstNameInMultiLang) {
		this.patFirstNameInMultiLang = patFirstNameInMultiLang;
	}

	public String getPatMiddleNameInMultiLang() {
		return patMiddleNameInMultiLang;
	}

	public void setPatMiddleNameInMultiLang(String patMiddleNameInMultiLang) {
		this.patMiddleNameInMultiLang = patMiddleNameInMultiLang;
	}

	public String getPatLastNameInMultiLang() {
		return patLastNameInMultiLang;
	}

	public void setPatLastNameInMultiLang(String patLastNameInMultiLang) {
		this.patLastNameInMultiLang = patLastNameInMultiLang;
	}

	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
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

	public String getMlcDetail() {
		return mlcDetail;
	}

	public void setMlcDetail(String mlcDetail) {
		this.mlcDetail = mlcDetail;
	}

	public String getConfigFlag() {
		return configFlag;
	}

	public void setConfigFlag(String configFlag) {
		this.configFlag = configFlag;
	}

	
	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getPatPrimaryCatValid() {
		return patPrimaryCatValid;
	}

	public void setPatPrimaryCatValid(String patPrimaryCatValid) {
		this.patPrimaryCatValid = patPrimaryCatValid;
	}

	public String getIsCatExpired() {
		return isCatExpired;
	}

	public void setIsCatExpired(String isCatExpired) {
		this.isCatExpired = isCatExpired;
	}

	public String getPatIsDead() {
		return patIsDead;
	}

	public void setPatIsDead(String patIsDead) {
		this.patIsDead = patIsDead;
	}

	public String getIsImageUploaded() {
		return isImageUploaded;
	}

	public void setIsImageUploaded(String isImageUploaded) {
		this.isImageUploaded = isImageUploaded;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

}
