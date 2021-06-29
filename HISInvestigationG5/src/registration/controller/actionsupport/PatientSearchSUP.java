package registration.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import vo.registration.AddressVO;

import com.opensymphony.xwork2.ActionSupport;

public abstract class PatientSearchSUP extends CRNoSUP implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String errorMessage;

	protected String searchType;
	private AddressVO patAddress = new AddressVO();	

	protected String uniqueIdType;
	protected String patIdNo;
	protected String docType;
	protected String hospitalCode;
	protected String isGlobal;
	protected String isDemographicSearch;
	
	protected String defaultpatAddStateCode;
	protected String defaultpatAddDistrictCode;
	/*  ## 		Modification Log							
		##		Modify Date				:18thDec'14 
		##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
		##		Modify By				:Sheeldarshi */
	protected String fromDate = "";
	protected String toDate = "";
	protected String isUnknown="";
	//End:Sheeldarshi
	public String getDefaultpatAddStateCode() {
		return defaultpatAddStateCode;
	}

	public void setDefaultpatAddStateCode(String defaultpatAddStateCode) {
		this.defaultpatAddStateCode = defaultpatAddStateCode;
	}

	public String getDefaultpatAddDistrictCode() {
		return defaultpatAddDistrictCode;
	}

	public void setDefaultpatAddDistrictCode(String defaultpatAddDistrictCode) {
		this.defaultpatAddDistrictCode = defaultpatAddDistrictCode;
	}

	public void reset()
	{
		this.uniqueIdType="";
		this.patIdNo="";
		this.docType="";
		this.hospitalCode="";
		this.patAddress=new AddressVO();
	}
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}	

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getUniqueIdType() {
		return uniqueIdType;
	}

	public void setUniqueIdType(String uniqueIdType) {
		this.uniqueIdType = uniqueIdType;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPatIdNo() {
		return patIdNo;
	}

	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}

	public PatientSearchSUP()
	{
		
	}
	
	public void clear()
	{
		
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

	/*
	 * @Override public CountryModel getModel() { System.out.println("Inside CountryAction ::: getModel()"); return
	 * modelCountry; }
	 */

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

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public AddressVO getPatAddress() {
		return patAddress;
	}

	public void setPatAddress(AddressVO patAddress) {
		this.patAddress = patAddress;
	}
	
	public void setPatAddCountryCode(String patAddCountryCode){
		patAddress.setPatAddCountryCode(patAddCountryCode);		
	}
	
	public String getPatAddCountryCode(){
		return patAddress.getPatAddCountryCode();		
	}
	
	public void setPatAddStateCode(String patAddStateCode){
		patAddress.setPatAddStateCode(patAddStateCode);		
	}
	
	public String getPatAddStateCode(){
		return patAddress.getPatAddStateCode();		
	}
	
	public void setPatAddHNo(String patAddHNo){
		patAddress.setPatAddHNo(patAddHNo);		
	}
	
	public String getPatAddHNo(){
		return patAddress.getPatAddHNo();		
	}
	
	public void setPatAddStreet(String patAddStreet){
		patAddress.setPatAddStreet(patAddStreet);		
	}
	
	public String getPatAddStreet(){
		return patAddress.getPatAddStreet();		
	}
	
	public void setPatAddStateName(String patAddStateName){
		patAddress.setPatAddStateName(patAddStateName);		
	}
	
	public String getPatAddStateName(){
		return patAddress.getPatAddStateName();		
	}
		
	public void setPatAddCityLoc(String patAddCityLoc){
		patAddress.setPatAddCityLoc(patAddCityLoc);		
	}
	
	public String getPatAddCityLoc(){
		return patAddress.getPatAddCityLoc();		
	}
	
	public void setPatAddDistrictCode(String patAddDistrictCode){
		patAddress.setPatAddDistrictCode(patAddDistrictCode);		
	}
	
	public String getPatAddDistrictCode(){
		return patAddress.getPatAddDistrictCode();		
	}
	
	public String getPatAddDistrict(){
		return patAddress.getPatAddDistrict();		
	}
	
	public void setPatAddDistrict(String patAddDistrict){
		patAddress.setPatAddDistrict(patAddDistrict);		
	}
	
	public void setPatAddCity(String patAddCity){
		patAddress.setPatAddCity(patAddCity);		
	}
	
	public String getPatAddCity(){
		return patAddress.getPatAddCity();		
	}
	
	public void setPatAddPIN(String patAddPIN){
		patAddress.setPatAddPIN(patAddPIN);		
	}
	
	public String getPatAddPIN(){
		return patAddress.getPatAddPIN();		
	}
	
	public void setPatAddLandMarks(String patAddLandMarks){
		patAddress.setPatAddLandMarks(patAddLandMarks);		
	}
	
	public String getPatAddLandMarks(){
		return patAddress.getPatAddLandMarks();		
	}

	public String getIsglobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	public String getIsDemographicSearch() {
		return isDemographicSearch;
	}

	public void setIsDemographicSearch(String isDemographicSearch) {
		this.isDemographicSearch = isDemographicSearch;
	}
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	/*  ## 		Modification Log							
		##		Modify Date				:18thDec'14 
		##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
		##		Modify By				:Sheeldarshi */
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getIsUnknown() {
		return isUnknown;
	}

	public void setIsUnknown(String isUnknown) {
		this.isUnknown = isUnknown;
	}
	//End:Sheeldarshi
}
