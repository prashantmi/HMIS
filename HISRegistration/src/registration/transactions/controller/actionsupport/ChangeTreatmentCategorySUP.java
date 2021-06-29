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

public abstract class ChangeTreatmentCategorySUP extends CRNoSUP implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String errorMessage;
	protected String normalMessage;
	
	protected String goFlag;
	protected String AfterGo;
	
	private String department;
	private String departmentCode;
	private String patPrimaryCatCode;
	private String patPrimaryCat;
	private String patSecondaryCatCode;
    private String patSecondaryCat;  
    private String patPrevSecondaryCatCode;
    private String patPrevSecondaryCat;  	
    private String patNewSecondaryCatCode;
    private String patNewSecondaryCat; 
   
    private String hmode;
    private String[] selectEpisode;
    private String[] episodeToChangeSecondaryCategory;
    private String[] newSecCatCode;
    private String[] arrRemarks;
    private String[] arrValidUpto;
    private String[] categoryExpiryDate;
    private String[] identifySecCatCode;
    private String SecCatCodeAndExpiryDay;
    private String[] arrExpiryText;
    private String isIpdFlag;
    private String[] selectEpisodeVisitNo;
    private String[] hiddenNewSecCatCode;
    private String sysDate="";
    private String[] revokeChk; 
    private String isInvalidCatCode;
    
    private String[] catClientCode;
	

	

	public String[] getCatClientCode() {
		return catClientCode;
	}

	public void setCatClientCode(String[] catClientCode) {
		this.catClientCode = catClientCode;
	}

	public String getAfterGo() {
		return AfterGo;
	}

	public void setAfterGo(String afterGo) {
		AfterGo = afterGo;
	}

	public String getGoFlag() {
		return goFlag;
	}

	public void setGoFlag(String goFlag) {
		this.goFlag = goFlag;
	}

	protected String patCrNo;
	protected String crNoToModify;
	private String selectedEpisode;
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String patAdmNo;
	private String fromDepartmentCode;
	private String fromDepartment;
	private String fromDepartmentUnitCode;
	private String fromDepartmentUnit;
	private String fromDoctorCode;
	private String choice;
	private String patRefHospitalDeptOther;
	private String patRefGnctdHospitalDept;
	private String PatRefGnctdHospitalDeptUnit;
	private String PatRefHospitalName;
	private String isAssociated;
	private String PatRefGnctdHospitalCode;
	private String DepartmentCode;
	private String DepartmentUnitCode;
	
	private String fromWardCode;
	private String fromWard;
	private String fromEpisodeCode;
	private String toDepartmentCode;
	private String toDepartment;
	private String toDepartmentUnitCode;
	private String toDepartmentUnit;
	private String toDoctorCode;
	private String toWardCode;
	private String toWard;
	private String toEpisodeCode;
	private String toEpisodeVisitNo;
	private String externalHospitalCode;
	private String externalHospitalName;
	private String externalHospitalDoctorName;
	private String externalHospitalPatCrNo;
	private String externalHospitalDepartment;
	private String externalHospitalDepartmentUnit;
	private String isRefferInOut;
	private String reffDateTime;
	private String episodeReferAcceptDate;
	private String systemIPAddress;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String remarks;
	private String externalReferRemarks;
	
	//Added by Vasu for Adding multiple categories for a particular episode dated on 9April18
	private String[] selectedEpisodeForTreatmentCategory;
    private String[] selectedDeptCode;
    private String[] selectedDeptUnitCode;
    private String[] secCatCode;
    private String[] remarksArr;
    private String[] validUpto;
	private String selectedEpisodeIndex;
	private String VisitNo;
    private String[] letterReferenceNo;
    private String[] letterDate;
    private String[] creditLimit;
    private String ApplicableServices;
    private String[] applicableServiceCode;
    private String visitType;
    private String selectedEpisodeIPD;
    private String[] AdmissionNo;
    private String[] applicableServicesName;
    private String[] selectedCategoryName;
    
    
	public String getSelectedEpisodeIPD() {
		return selectedEpisodeIPD;
	}

	public void setSelectedEpisodeIPD(String selectedEpisodeIPD) {
		this.selectedEpisodeIPD = selectedEpisodeIPD;
	}

	public String getExternalReferRemarks() {
		return externalReferRemarks;
	}

	public void setExternalReferRemarks(String externalReferRemarks) {
		this.externalReferRemarks = externalReferRemarks;
	}

	public String getIsAssociated() {
		return isAssociated;
	}

	public void setIsAssociated(String isAssociated) {
		this.isAssociated = isAssociated;
	}

	public String getPatRefGnctdHospitalCode() {
		return PatRefGnctdHospitalCode;
	}

	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode) {
		PatRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}



	public String getDepartmentUnitCode() {
		return DepartmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		DepartmentUnitCode = departmentUnitCode;
	}

	public String getPatRefGnctdHospitalDeptUnit() {
		return PatRefGnctdHospitalDeptUnit;
	}

	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit) {
		PatRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	public String getPatRefHospitalName() {
		return PatRefHospitalName;
	}

	public void setPatRefHospitalName(String patRefHospitalName) {
		PatRefHospitalName = patRefHospitalName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getNormalMessage() {
		return normalMessage;
	}

	public void setNormalMessage(String normalMessage) {
		this.normalMessage = normalMessage;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getPatRefHospitalDeptOther() {
		return patRefHospitalDeptOther;
	}

	public void setPatRefHospitalDeptOther(String patRefHospitalDeptOther) {
		this.patRefHospitalDeptOther = patRefHospitalDeptOther;
	}

	public String getPatRefGnctdHospitalDept() {
		return patRefGnctdHospitalDept;
	}

	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept) {
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}
	public String getSelectedEpisode() {
		return selectedEpisode;
	}

	public void setSelectedEpisode(String selectedEpisode) {
		this.selectedEpisode = selectedEpisode;
	}

	

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getFromDepartmentCode() {
		return fromDepartmentCode;
	}

	public void setFromDepartmentCode(String fromDepartmentCode) {
		this.fromDepartmentCode = fromDepartmentCode;
	}

	public String getFromDepartment() {
		return fromDepartment;
	}

	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}

	public String getFromDepartmentUnitCode() {
		return fromDepartmentUnitCode;
	}

	public void setFromDepartmentUnitCode(String fromDepartmentUnitCode) {
		this.fromDepartmentUnitCode = fromDepartmentUnitCode;
	}

	public String getFromDepartmentUnit() {
		return fromDepartmentUnit;
	}

	public void setFromDepartmentUnit(String fromDepartmentUnit) {
		this.fromDepartmentUnit = fromDepartmentUnit;
	}

	public String getFromDoctorCode() {
		return fromDoctorCode;
	}

	public void setFromDoctorCode(String fromDoctorCode) {
		this.fromDoctorCode = fromDoctorCode;
	}

	public String getFromWardCode() {
		return fromWardCode;
	}

	public void setFromWardCode(String fromWardCode) {
		this.fromWardCode = fromWardCode;
	}

	public String getFromWard() {
		return fromWard;
	}

	public void setFromWard(String fromWard) {
		this.fromWard = fromWard;
	}

	public String getFromEpisodeCode() {
		return fromEpisodeCode;
	}

	public void setFromEpisodeCode(String fromEpisodeCode) {
		this.fromEpisodeCode = fromEpisodeCode;
	}

	public String getToDepartmentCode() {
		return toDepartmentCode;
	}

	public void setToDepartmentCode(String toDepartmentCode) {
		this.toDepartmentCode = toDepartmentCode;
	}

	public String getToDepartment() {
		return toDepartment;
	}

	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}

	public String getToDepartmentUnitCode() {
		return toDepartmentUnitCode;
	}

	public void setToDepartmentUnitCode(String toDepartmentUnitCode) {
		this.toDepartmentUnitCode = toDepartmentUnitCode;
	}

	public String getToDepartmentUnit() {
		return toDepartmentUnit;
	}

	public void setToDepartmentUnit(String toDepartmentUnit) {
		this.toDepartmentUnit = toDepartmentUnit;
	}

	public String getToDoctorCode() {
		return toDoctorCode;
	}

	public void setToDoctorCode(String toDoctorCode) {
		this.toDoctorCode = toDoctorCode;
	}

	public String getToWardCode() {
		return toWardCode;
	}

	public void setToWardCode(String toWardCode) {
		this.toWardCode = toWardCode;
	}

	public String getToWard() {
		return toWard;
	}

	public void setToWard(String toWard) {
		this.toWard = toWard;
	}

	public String getToEpisodeCode() {
		return toEpisodeCode;
	}

	public void setToEpisodeCode(String toEpisodeCode) {
		this.toEpisodeCode = toEpisodeCode;
	}

	public String getToEpisodeVisitNo() {
		return toEpisodeVisitNo;
	}

	public void setToEpisodeVisitNo(String toEpisodeVisitNo) {
		this.toEpisodeVisitNo = toEpisodeVisitNo;
	}

	public String getExternalHospitalCode() {
		return externalHospitalCode;
	}

	public void setExternalHospitalCode(String externalHospitalCode) {
		this.externalHospitalCode = externalHospitalCode;
	}

	public String getExternalHospitalName() {
		return externalHospitalName;
	}

	public void setExternalHospitalName(String externalHospitalName) {
		this.externalHospitalName = externalHospitalName;
	}

	public String getExternalHospitalDoctorName() {
		return externalHospitalDoctorName;
	}

	public void setExternalHospitalDoctorName(String externalHospitalDoctorName) {
		this.externalHospitalDoctorName = externalHospitalDoctorName;
	}

	public String getExternalHospitalPatCrNo() {
		return externalHospitalPatCrNo;
	}

	public void setExternalHospitalPatCrNo(String externalHospitalPatCrNo) {
		this.externalHospitalPatCrNo = externalHospitalPatCrNo;
	}

	public String getExternalHospitalDepartment() {
		return externalHospitalDepartment;
	}

	public void setExternalHospitalDepartment(String externalHospitalDepartment) {
		this.externalHospitalDepartment = externalHospitalDepartment;
	}

	public String getExternalHospitalDepartmentUnit() {
		return externalHospitalDepartmentUnit;
	}

	public void setExternalHospitalDepartmentUnit(
			String externalHospitalDepartmentUnit) {
		this.externalHospitalDepartmentUnit = externalHospitalDepartmentUnit;
	}

	public String getIsRefferInOut() {
		return isRefferInOut;
	}

	public void setIsRefferInOut(String isRefferInOut) {
		this.isRefferInOut = isRefferInOut;
	}

	public String getReffDateTime() {
		return reffDateTime;
	}

	public void setReffDateTime(String reffDateTime) {
		this.reffDateTime = reffDateTime;
	}

	public String getEpisodeReferAcceptDate() {
		return episodeReferAcceptDate;
	}

	public void setEpisodeReferAcceptDate(String episodeReferAcceptDate) {
		this.episodeReferAcceptDate = episodeReferAcceptDate;
	}

	public String getSystemIPAddress() {
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}

	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}


		

	public String getCrNoToModify() {
		return crNoToModify;
	}

	public void setCrNoToModify(String crNoToModify) {
		this.crNoToModify = crNoToModify;
	}

	

	

	public ChangeTreatmentCategorySUP()
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

	
	
	public String getIsInvalidCatCode() {
		return isInvalidCatCode;
	}

	public void setIsInvalidCatCode(String isInvalidCatCode) {
		this.isInvalidCatCode = isInvalidCatCode;
	}

	public String[] getRevokeChk() {
		return revokeChk;
	}

	public void setRevokeChk(String[] revokeChk) {
		this.revokeChk = revokeChk;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String[] getArrExpiryText() {
		return arrExpiryText;
	}

	public void setArrExpiryText(String[] arrExpiryText) {
		this.arrExpiryText = arrExpiryText;
	}

	public String[] getIdentifySecCatCode() {
		return identifySecCatCode;
	}

	public void setIdentifySecCatCode(String[] identifySecCatCode) {
		this.identifySecCatCode = identifySecCatCode;
	}

	

	public String[] getEpisodeToChangeSecondaryCategory() {
		return episodeToChangeSecondaryCategory;
	}

	public void setEpisodeToChangeSecondaryCategory(
			String[] episodeToChangeSecondaryCategory) {
		this.episodeToChangeSecondaryCategory = episodeToChangeSecondaryCategory;
	}

	public String[] getNewSecCatCode() {
		return newSecCatCode;
	}

	public void setNewSecCatCode(String[] newSecCatCode) {
		this.newSecCatCode = newSecCatCode;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getPatNewSecondaryCat() {
		return patNewSecondaryCat;
	}

	public void setPatNewSecondaryCat(String patNewSecondaryCat) {
		this.patNewSecondaryCat = patNewSecondaryCat;
	}

	public String getPatNewSecondaryCatCode() {
		return patNewSecondaryCatCode;
	}

	public void setPatNewSecondaryCatCode(String patNewSecondaryCatCode) {
		this.patNewSecondaryCatCode = patNewSecondaryCatCode;
	}

	public String getPatPrevSecondaryCat() {
		return patPrevSecondaryCat;
	}

	public void setPatPrevSecondaryCat(String patPrevSecondaryCat) {
		this.patPrevSecondaryCat = patPrevSecondaryCat;
	}

	public String getPatPrevSecondaryCatCode() {
		return patPrevSecondaryCatCode;
	}

	public void setPatPrevSecondaryCatCode(String patPrevSecondaryCatCode) {
		this.patPrevSecondaryCatCode = patPrevSecondaryCatCode;
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

	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}

	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}

	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}

	public String getPatSecondaryCat() {
		return patSecondaryCat;
	}

	public void setPatSecondaryCat(String patSecondaryCat) {
		this.patSecondaryCat = patSecondaryCat;
	}

	

	public String[] getArrRemarks() {
		return arrRemarks;
	}

	public void setArrRemarks(String[] arrRemarks) {
		this.arrRemarks = arrRemarks;
	}

	public String[] getArrValidUpto() {
		return arrValidUpto;
	}

	public void setArrValidUpto(String[] arrValidUpto) {
		this.arrValidUpto = arrValidUpto;
	}

	public String getSecCatCodeAndExpiryDay() {
		return SecCatCodeAndExpiryDay;
	}

	public void setSecCatCodeAndExpiryDay(String secCatCodeAndExpiryDay) {
		SecCatCodeAndExpiryDay = secCatCodeAndExpiryDay;
	}

	

	public String[] getCategoryExpiryDate() {
		return categoryExpiryDate;
	}

	public void setCategoryExpiryDate(String[] categoryExpiryDate) {
		this.categoryExpiryDate = categoryExpiryDate;
	}

	public String getIsIpdFlag() {
		return isIpdFlag;
	}

	public void setIsIpdFlag(String isIpdFlag) {
		this.isIpdFlag = isIpdFlag;
	}

	public String[] getSelectEpisodeVisitNo() {
		return selectEpisodeVisitNo;
	}

	public void setSelectEpisodeVisitNo(String[] selectEpisodeVisitNo) {
		this.selectEpisodeVisitNo = selectEpisodeVisitNo;
	}

	public String[] getHiddenNewSecCatCode() {
		return hiddenNewSecCatCode;
	}

	public void setHiddenNewSecCatCode(String[] hiddenNewSecCatCode) {
		this.hiddenNewSecCatCode = hiddenNewSecCatCode;
	}
	public String[] getSelectEpisode() {
		return selectEpisode;
	}

	public void setSelectEpisode(String[] selectEpisode) {
		this.selectEpisode = selectEpisode;
	}

	//Added by Vasu Dated on 9-April-2018
	public String[] getSelectedEpisodeForTreatmentCategory() {
		return selectedEpisodeForTreatmentCategory;
	}

	public void setSelectedEpisodeForTreatmentCategory(
			String[] selectedEpisodeForTreatmentCategory) {
		this.selectedEpisodeForTreatmentCategory = selectedEpisodeForTreatmentCategory;
	}

	public String[] getSelectedDeptCode() {
		return selectedDeptCode;
	}

	public void setSelectedDeptCode(String[] selectedDeptCode) {
		this.selectedDeptCode = selectedDeptCode;
	}

	public String[] getSelectedDeptUnitCode() {
		return selectedDeptUnitCode;
	}

	public void setSelectedDeptUnitCode(String[] selectedDeptUnitCode) {
		this.selectedDeptUnitCode = selectedDeptUnitCode;
	}

	public String[] getSecCatCode() {
		return secCatCode;
	}

	public void setSecCatCode(String[] secCatCode) {
		this.secCatCode = secCatCode;
	}

	public String[] getValidUpto() {
		return validUpto;
	}

	public void setValidUpto(String[] validUpto) {
		this.validUpto = validUpto;
	}

	public String[] getRemarksArr() {
		return remarksArr;
	}

	public void setRemarksArr(String[] remarksArr) {
		this.remarksArr = remarksArr;
	}

	public String getSelectedEpisodeIndex() {
		return selectedEpisodeIndex;
	}

	public void setSelectedEpisodeIndex(String selectedEpisodeIndex) {
		this.selectedEpisodeIndex = selectedEpisodeIndex;
	}

	public String getVisitNo() {
		return VisitNo;
	}

	public void setVisitNo(String visitNo) {
		VisitNo = visitNo;
	}

	public String[] getLetterReferenceNo() {
		return letterReferenceNo;
	}

	public void setLetterReferenceNo(String[] letterReferenceNo) {
		this.letterReferenceNo = letterReferenceNo;
	}

	public String[] getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(String[] letterDate) {
		this.letterDate = letterDate;
	}

	public String[] getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String[] creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getApplicableServices() {
		return ApplicableServices;
	}

	public void setApplicableServices(String applicableServices) {
		ApplicableServices = applicableServices;
	}

	public String[] getApplicableServiceCode() {
		return applicableServiceCode;
	}

	public void setApplicableServiceCode(String[] applicableServiceCode) {
		this.applicableServiceCode = applicableServiceCode;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String[] getAdmissionNo() {
		return AdmissionNo;
	}

	public void setAdmissionNo(String[] admissionNo) {
		AdmissionNo = admissionNo;
	}

	public String[] getApplicableServicesName() {
		return applicableServicesName;
	}

	public void setApplicableServicesName(String[] applicableServicesName) {
		this.applicableServicesName = applicableServicesName;
	}

	public String[] getSelectedCategoryName() {
		return selectedCategoryName;
	}

	public void setSelectedCategoryName(String[] selectedCategoryName) {
		this.selectedCategoryName = selectedCategoryName;
	}
}
