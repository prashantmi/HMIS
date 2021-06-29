package registration.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class CreditPatientVisitSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String afterGo;
	
	
	protected String patPrimaryCatCode;
	protected String patIdNo;
    protected String[] index;
    protected String patSecondaryCatCode;
    protected String searchName;
    protected String crNoToRetrieve;
    protected String allCheckbox;
    protected String departmentCode;
	protected String hmode;
	protected String patFirstName;
    protected String patMiddleName;
    protected String patLastName;
    protected String patGender;
	protected String patAge;
    protected String patGuardianName;
    protected String patAgeUnit;    
    protected String prevCrNo;
	protected String departmentdiv;
	protected String valid;
	protected String[] departmentsToVisitStamp;
	protected String[] chkdepartmentsToVisitStamp;
	protected String[] alreadyVisitedDept;
	protected String removeDept;
	protected String[] deptsAlreadyVisited;
	protected String patRegCatCode;
	protected String getCrNoToRetrieve;
    protected String patPrimaryCat;
    protected String patSecondaryCat;
    protected String referringInst;
    protected String patGenderCode;
	protected String stateRadio;
	protected String patVisitReason;
	protected String creditBillFlag;
	protected String creditLetterRefNo;
	protected String creditLetterDate;
	protected String[] arrPatVisitReason;
	protected String[] arrCreditBillFlag;
	protected String[] arrCreditLetterRefNo;
	protected String[] arrCreditLetterDate;
	protected String[] newdepartmentsToVisitStamp;
	protected String newremoveDept;
	protected String[] hiddenEpisode;
	protected String hcode;
	protected String episodeCode;
	protected String isReferred;
	protected String isRefferInOut;
	protected String referringInstType;
	protected String isAssociated;
	protected String patRefGnctdHospitalDept;
	protected String patRefDoctor;
	protected String patRefGnctdHospitalCode;
	protected String patRefHospitalName;
	protected String patRefGnctdHospitalCrno;
	protected String patRefGnctdHospitalDeptUnit;
	protected String fromDepartment;
	protected String refferringOPDEpisode;
	protected String renewal;
	protected String patAmountHospitalWise;
	protected String patAmountDeptWise;
	protected String patRenewalAmountDeptWise;
	protected String patOldDeptVisitAmount;
	protected String patNewDeptVisitAmount;
	
	protected String[] departmentsToRenew;
	protected String indexID;
	protected String selectedRefCrNo;
	protected String isPatReferByList;
	protected String deptOrUnitName;
	protected String onRequestVisit="";
	protected String patRefHospitalDeptOther;
	protected String saveSuccessful;
	protected String newDepartmentVisit="";
	protected String oldDepartmentVisit="";
	protected String modeCase;
	

	
	//for new department Visit
	
	protected String patAmountCollected;
	protected String fileNo;
	
	protected String[] arrFileNo;
	protected String fromDepartmentCode;
	protected String fromDepartmentUnitCode;
	protected String fromDepartmentUnit;
	protected String toDepartment;
	protected String toDepartmentCode;
	protected String toDepartmentUnit;
	protected String toDepartmentUnitCode;
	protected String selectedFromDept;
	protected String patBillAmountWithoutGrouping;
	protected String referInternalExternal;
	protected String referingRowIndex;
	protected String selectedReffereCrNo;
	protected String onlineReferedIndex;
	protected String captureMandatoryField="false";
	///for mandaoty fields
	protected String patReligionCode;
	
	protected String patNickName;
	protected String patMaritalStatusCode;
	protected String patMotherName;
	protected String patHusbandName;
	protected String patMonthlyIncome;
	protected String patOccupation;
	protected String patFatherOccupation;
	protected String patHusbandOccupation;
	protected String patNationalId;
	protected String patCardNo;
	protected String entryDate;
	protected String episodeVisitNo;
	
	protected String departmentUnitCode;
	
	protected String selectedCheckBox;
	protected String print;
	protected String printDivContent;
			
	//Added by Garima for Hospital Wise Renewal for Maharshtra
	protected String regRenewalRequired;
	protected String isPrintCard;
	protected String selectedReferal;
	
	protected String patCrNo;
	protected String strRenewalType;
	protected String opdRenewalRequired;
	
	protected String strNormalMsg;
	protected String strWarningMsg;
	protected String errorMessage;
	
	protected String goWithoutDeptVisitOn="";
	protected String otherHospitalFlag;
	protected String otherHospitalDataFound;
	
	protected String patPrimaryCatGrpCode;
	protected String sysdate;
	
	protected String clientCode;
	protected String clientName;
	protected String staffCardNo;
	protected String staffCardName;
	protected String relationWithStaff;
	protected String cardvalidityDate;
	protected String relationNameWithStaff;

	protected String agsDistrictCode;
	protected String agsCounterNo;
	protected String agsNo;
	protected String patActualAmount;
	protected String isPatAdmitted;
	protected String patRenewalActualAmount;
	private WebRowSet strResultWs = null;
	private String patAmountCrConsultation;
	private String patAmountNCrConsultation;
	private String patIsCrossConsultantWithReferal;
	private String patIsCrossConsultant;
	protected String registerDate;
	private String patStaffNo;
	private String patStaffName;
	

	private String patStaffDeptName;
	private String patStaffRelationName;
	
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
	public String getPatIdNo() {
		return patIdNo;
	}
	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}
	public String[] getIndex() {
		return index;
	}
	public void setIndex(String[] index) {
		this.index = index;
	}
	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}
	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
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
	public String getAllCheckbox() {
		return allCheckbox;
	}
	public void setAllCheckbox(String allCheckbox) {
		this.allCheckbox = allCheckbox;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPrevCrNo() {
		return prevCrNo;
	}
	public void setPrevCrNo(String prevCrNo) {
		this.prevCrNo = prevCrNo;
	}
	public String getDepartmentdiv() {
		return departmentdiv;
	}
	public void setDepartmentdiv(String departmentdiv) {
		this.departmentdiv = departmentdiv;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String[] getDepartmentsToVisitStamp() {
		return departmentsToVisitStamp;
	}
	public void setDepartmentsToVisitStamp(String[] departmentsToVisitStamp) {
		this.departmentsToVisitStamp = departmentsToVisitStamp;
	}
	public String[] getChkdepartmentsToVisitStamp() {
		return chkdepartmentsToVisitStamp;
	}
	public void setChkdepartmentsToVisitStamp(String[] chkdepartmentsToVisitStamp) {
		this.chkdepartmentsToVisitStamp = chkdepartmentsToVisitStamp;
	}
	public String[] getAlreadyVisitedDept() {
		return alreadyVisitedDept;
	}
	public void setAlreadyVisitedDept(String[] alreadyVisitedDept) {
		this.alreadyVisitedDept = alreadyVisitedDept;
	}
	public String getRemoveDept() {
		return removeDept;
	}
	public void setRemoveDept(String removeDept) {
		this.removeDept = removeDept;
	}
	public String[] getDeptsAlreadyVisited() {
		return deptsAlreadyVisited;
	}
	public void setDeptsAlreadyVisited(String[] deptsAlreadyVisited) {
		this.deptsAlreadyVisited = deptsAlreadyVisited;
	}
	public String getPatRegCatCode() {
		return patRegCatCode;
	}
	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}
	public String getGetCrNoToRetrieve() {
		return getCrNoToRetrieve;
	}
	public void setGetCrNoToRetrieve(String getCrNoToRetrieve) {
		this.getCrNoToRetrieve = getCrNoToRetrieve;
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
	public String getReferringInst() {
		return referringInst;
	}
	public void setReferringInst(String referringInst) {
		this.referringInst = referringInst;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getStateRadio() {
		return stateRadio;
	}
	public void setStateRadio(String stateRadio) {
		this.stateRadio = stateRadio;
	}
	
	public String getPatVisitReason() {
		return patVisitReason;
	}
	public void setPatVisitReason(String patVisitReason) {
		this.patVisitReason = patVisitReason;
	}
	public String getCreditBillFlag() {
		return creditBillFlag;
	}
	public void setCreditBillFlag(String creditBillFlag) {
		this.creditBillFlag = creditBillFlag;
	}
	public String getCreditLetterRefNo() {
		return creditLetterRefNo;
	}
	public void setCreditLetterRefNo(String creditLetterRefNo) {
		this.creditLetterRefNo = creditLetterRefNo;
	}
	public String getCreditLetterDate() {
		return creditLetterDate;
	}
	public void setCreditLetterDate(String creditLetterDate) {
		this.creditLetterDate = creditLetterDate;
	}
	public String[] getArrPatVisitReason() {
		return arrPatVisitReason;
	}
	public void setArrPatVisitReason(String[] arrPatVisitReason) {
		this.arrPatVisitReason = arrPatVisitReason;
	}
	public String[] getArrCreditBillFlag() {
		return arrCreditBillFlag;
	}
	public void setArrCreditBillFlag(String[] arrCreditBillFlag) {
		this.arrCreditBillFlag = arrCreditBillFlag;
	}
	public String[] getArrCreditLetterRefNo() {
		return arrCreditLetterRefNo;
	}
	public void setArrCreditLetterRefNo(String[] arrCreditLetterRefNo) {
		this.arrCreditLetterRefNo = arrCreditLetterRefNo;
	}
	public String[] getArrCreditLetterDate() {
		return arrCreditLetterDate;
	}
	public void setArrCreditLetterDate(String[] arrCreditLetterDate) {
		this.arrCreditLetterDate = arrCreditLetterDate;
	}
	public String[] getNewdepartmentsToVisitStamp() {
		return newdepartmentsToVisitStamp;
	}
	public void setNewdepartmentsToVisitStamp(String[] newdepartmentsToVisitStamp) {
		this.newdepartmentsToVisitStamp = newdepartmentsToVisitStamp;
	}
	public String getNewremoveDept() {
		return newremoveDept;
	}
	public void setNewremoveDept(String newremoveDept) {
		this.newremoveDept = newremoveDept;
	}
	public String[] getHiddenEpisode() {
		return hiddenEpisode;
	}
	public void setHiddenEpisode(String[] hiddenEpisode) {
		this.hiddenEpisode = hiddenEpisode;
	}
	public String getHcode() {
		return hcode;
	}
	public void setHcode(String hcode) {
		this.hcode = hcode;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getIsReferred() {
		return isReferred;
	}
	public void setIsReferred(String isReferred) {
		this.isReferred = isReferred;
	}
	public String getIsRefferInOut() {
		return isRefferInOut;
	}
	public void setIsRefferInOut(String isRefferInOut) {
		this.isRefferInOut = isRefferInOut;
	}
	public String getReferringInstType() {
		return referringInstType;
	}
	public void setReferringInstType(String referringInstType) {
		this.referringInstType = referringInstType;
	}
	public String getIsAssociated() {
		return isAssociated;
	}
	public void setIsAssociated(String isAssociated) {
		this.isAssociated = isAssociated;
	}
	public String getPatRefGnctdHospitalDept() {
		return patRefGnctdHospitalDept;
	}
	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept) {
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}
	public String getPatRefDoctor() {
		return patRefDoctor;
	}
	public void setPatRefDoctor(String patRefDoctor) {
		this.patRefDoctor = patRefDoctor;
	}
	public String getPatRefGnctdHospitalCode() {
		return patRefGnctdHospitalCode;
	}
	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode) {
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}
	public String getPatRefHospitalName() {
		return patRefHospitalName;
	}
	public void setPatRefHospitalName(String patRefHospitalName) {
		this.patRefHospitalName = patRefHospitalName;
	}
	public String getPatRefGnctdHospitalCrno() {
		return patRefGnctdHospitalCrno;
	}
	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno) {
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}
	public String getPatRefGnctdHospitalDeptUnit() {
		return patRefGnctdHospitalDeptUnit;
	}
	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit) {
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}
	public String getFromDepartment() {
		return fromDepartment;
	}
	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}
	public String getRefferringOPDEpisode() {
		return refferringOPDEpisode;
	}
	public void setRefferringOPDEpisode(String refferringOPDEpisode) {
		this.refferringOPDEpisode = refferringOPDEpisode;
	}
	public String getRenewal() {
		return renewal;
	}
	public void setRenewal(String renewal) {
		this.renewal = renewal;
	}
	
	
	public String getPatAmountHospitalWise() {
		return patAmountHospitalWise;
	}
	public void setPatAmountHospitalWise(String patAmountHospitalWise) {
		this.patAmountHospitalWise = patAmountHospitalWise;
	}
	public String getPatRenewalAmountDeptWise() {
		return patRenewalAmountDeptWise;
	}
	public void setPatRenewalAmountDeptWise(String patRenewalAmountDeptWise) {
		this.patRenewalAmountDeptWise = patRenewalAmountDeptWise;
	}
	public String getPatAmountDeptWise() {
		return patAmountDeptWise;
	}
	public void setPatAmountDeptWise(String patAmountDeptWise) {
		this.patAmountDeptWise = patAmountDeptWise;
	}
	public String getPatOldDeptVisitAmount() {
		return patOldDeptVisitAmount;
	}
	public void setPatOldDeptVisitAmount(String patOldDeptVisitAmount) {
		this.patOldDeptVisitAmount = patOldDeptVisitAmount;
	}
	public String getPatNewDeptVisitAmount() {
		return patNewDeptVisitAmount;
	}
	public void setPatNewDeptVisitAmount(String patNewDeptVisitAmount) {
		this.patNewDeptVisitAmount = patNewDeptVisitAmount;
	}
	public String[] getDepartmentsToRenew() {
		return departmentsToRenew;
	}
	public void setDepartmentsToRenew(String[] departmentsToRenew) {
		this.departmentsToRenew = departmentsToRenew;
	}
	public String getIndexID() {
		return indexID;
	}
	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}
	public String getSelectedRefCrNo() {
		return selectedRefCrNo;
	}
	public void setSelectedRefCrNo(String selectedRefCrNo) {
		this.selectedRefCrNo = selectedRefCrNo;
	}
	public String getIsPatReferByList() {
		return isPatReferByList;
	}
	public void setIsPatReferByList(String isPatReferByList) {
		this.isPatReferByList = isPatReferByList;
	}
	public String getDeptOrUnitName() {
		return deptOrUnitName;
	}
	public void setDeptOrUnitName(String deptOrUnitName) {
		this.deptOrUnitName = deptOrUnitName;
	}
	public String getOnRequestVisit() {
		return onRequestVisit;
	}
	public void setOnRequestVisit(String onRequestVisit) {
		this.onRequestVisit = onRequestVisit;
	}
	public String getPatRefHospitalDeptOther() {
		return patRefHospitalDeptOther;
	}
	public void setPatRefHospitalDeptOther(String patRefHospitalDeptOther) {
		this.patRefHospitalDeptOther = patRefHospitalDeptOther;
	}
	public String getSaveSuccessful() {
		return saveSuccessful;
	}
	public void setSaveSuccessful(String saveSuccessful) {
		this.saveSuccessful = saveSuccessful;
	}
	public String getNewDepartmentVisit() {
		return newDepartmentVisit;
	}
	public void setNewDepartmentVisit(String newDepartmentVisit) {
		this.newDepartmentVisit = newDepartmentVisit;
	}
	public String getOldDepartmentVisit() {
		return oldDepartmentVisit;
	}
	public void setOldDepartmentVisit(String oldDepartmentVisit) {
		this.oldDepartmentVisit = oldDepartmentVisit;
	}
	public String getModeCase() {
		return modeCase;
	}
	public void setModeCase(String modeCase) {
		this.modeCase = modeCase;
	}
	public String getPatAmountCollected() {
		return patAmountCollected;
	}
	public void setPatAmountCollected(String patAmountCollected) {
		this.patAmountCollected = patAmountCollected;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String[] getArrFileNo() {
		return arrFileNo;
	}
	public void setArrFileNo(String[] arrFileNo) {
		this.arrFileNo = arrFileNo;
	}
	public String getFromDepartmentCode() {
		return fromDepartmentCode;
	}
	public void setFromDepartmentCode(String fromDepartmentCode) {
		this.fromDepartmentCode = fromDepartmentCode;
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
	public String getToDepartment() {
		return toDepartment;
	}
	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}
	public String getToDepartmentCode() {
		return toDepartmentCode;
	}
	public void setToDepartmentCode(String toDepartmentCode) {
		this.toDepartmentCode = toDepartmentCode;
	}
	public String getToDepartmentUnit() {
		return toDepartmentUnit;
	}
	public void setToDepartmentUnit(String toDepartmentUnit) {
		this.toDepartmentUnit = toDepartmentUnit;
	}
	public String getToDepartmentUnitCode() {
		return toDepartmentUnitCode;
	}
	public void setToDepartmentUnitCode(String toDepartmentUnitCode) {
		this.toDepartmentUnitCode = toDepartmentUnitCode;
	}
	public String getSelectedFromDept() {
		return selectedFromDept;
	}
	public void setSelectedFromDept(String selectedFromDept) {
		this.selectedFromDept = selectedFromDept;
	}
	public String getPatBillAmountWithoutGrouping() {
		return patBillAmountWithoutGrouping;
	}
	public void setPatBillAmountWithoutGrouping(String patBillAmountWithoutGrouping) {
		this.patBillAmountWithoutGrouping = patBillAmountWithoutGrouping;
	}
	public String getReferInternalExternal() {
		return referInternalExternal;
	}
	public void setReferInternalExternal(String referInternalExternal) {
		this.referInternalExternal = referInternalExternal;
	}
	public String getReferingRowIndex() {
		return referingRowIndex;
	}
	public void setReferingRowIndex(String referingRowIndex) {
		this.referingRowIndex = referingRowIndex;
	}
	public String getSelectedReffereCrNo() {
		return selectedReffereCrNo;
	}
	public void setSelectedReffereCrNo(String selectedReffereCrNo) {
		this.selectedReffereCrNo = selectedReffereCrNo;
	}
	public String getOnlineReferedIndex() {
		return onlineReferedIndex;
	}
	public void setOnlineReferedIndex(String onlineReferedIndex) {
		this.onlineReferedIndex = onlineReferedIndex;
	}
	public String getCaptureMandatoryField() {
		return captureMandatoryField;
	}
	public void setCaptureMandatoryField(String captureMandatoryField) {
		this.captureMandatoryField = captureMandatoryField;
	}
	public String getPatReligionCode() {
		return patReligionCode;
	}
	public void setPatReligionCode(String patReligionCode) {
		this.patReligionCode = patReligionCode;
	}
	public String getPatNickName() {
		return patNickName;
	}
	public void setPatNickName(String patNickName) {
		this.patNickName = patNickName;
	}
	public String getPatMaritalStatusCode() {
		return patMaritalStatusCode;
	}
	public void setPatMaritalStatusCode(String patMaritalStatusCode) {
		this.patMaritalStatusCode = patMaritalStatusCode;
	}
	public String getPatMotherName() {
		return patMotherName;
	}
	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getPatMonthlyIncome() {
		return patMonthlyIncome;
	}
	public void setPatMonthlyIncome(String patMonthlyIncome) {
		this.patMonthlyIncome = patMonthlyIncome;
	}
	public String getPatOccupation() {
		return patOccupation;
	}
	public void setPatOccupation(String patOccupation) {
		this.patOccupation = patOccupation;
	}
	public String getPatFatherOccupation() {
		return patFatherOccupation;
	}
	public void setPatFatherOccupation(String patFatherOccupation) {
		this.patFatherOccupation = patFatherOccupation;
	}
	public String getPatHusbandOccupation() {
		return patHusbandOccupation;
	}
	public void setPatHusbandOccupation(String patHusbandOccupation) {
		this.patHusbandOccupation = patHusbandOccupation;
	}
	public String getPatNationalId() {
		return patNationalId;
	}
	public void setPatNationalId(String patNationalId) {
		this.patNationalId = patNationalId;
	}
	public String getPatCardNo() {
		return patCardNo;
	}
	public void setPatCardNo(String patCardNo) {
		this.patCardNo = patCardNo;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getSelectedCheckBox() {
		return selectedCheckBox;
	}
	public void setSelectedCheckBox(String selectedCheckBox) {
		this.selectedCheckBox = selectedCheckBox;
	}
	public String getPrint() {
		return print;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	public String getPrintDivContent() {
		return printDivContent;
	}
	public void setPrintDivContent(String printDivContent) {
		this.printDivContent = printDivContent;
	}
	public String getRegRenewalRequired() {
		return regRenewalRequired;
	}
	public void setRegRenewalRequired(String regRenewalRequired) {
		this.regRenewalRequired = regRenewalRequired;
	}
	public String getIsPrintCard() {
		return isPrintCard;
	}
	public void setIsPrintCard(String isPrintCard) {
		this.isPrintCard = isPrintCard;
	}
	public String getSelectedReferal() {
		return selectedReferal;
	}
	public void setSelectedReferal(String selectedReferal) {
		this.selectedReferal = selectedReferal;
	}
	
	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getStrRenewalType() {
		return strRenewalType;
	}
	public void setStrRenewalType(String strRenewalType) {
		this.strRenewalType = strRenewalType;
	}
	public String getOpdRenewalRequired() {
		return opdRenewalRequired;
	}
	public void setOpdRenewalRequired(String opdRenewalRequired) {
		this.opdRenewalRequired = opdRenewalRequired;
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
	
	public String getGoWithoutDeptVisitOn() {
		return goWithoutDeptVisitOn;
	}
	public void setGoWithoutDeptVisitOn(String goWithoutDeptVisitOn) {
		this.goWithoutDeptVisitOn = goWithoutDeptVisitOn;
	}
	public String getOtherHospitalFlag() {
		return otherHospitalFlag;
	}
	public void setOtherHospitalFlag(String otherHospitalFlag) {
		this.otherHospitalFlag = otherHospitalFlag;
	}
	public String getOtherHospitalDataFound() {
		return otherHospitalDataFound;
	}
	public void setOtherHospitalDataFound(String otherHospitalDataFound) {
		this.otherHospitalDataFound = otherHospitalDataFound;
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
		this.setPatGenderCode("");
		this.setPatCrNo("");
		this.setPatPrimaryCatCode("");
		this.setPatSecondaryCatCode("");
		this.setPatFirstName("");
		this.setPatMiddleName("");
		this.setPatLastName("");
		this.setPatAge("");
		this.setPatGender("");
		this.setPatGuardianName("");
		this.setDepartmentsToVisitStamp(new String[]{});
		this.setAlreadyVisitedDept(new String[]{});
		this.setDeptsAlreadyVisited(new String[]{});
		this.setArrPatVisitReason(new String[]{});
		this.setRemoveDept("");
		this.setHmode("");
		this.setDepartmentdiv("");
		this.setHiddenEpisode(new String[]{});
		this.setEpisodeCode("");
		this.setHcode("");
		this.setIsReferred("");
		this.setPatOldDeptVisitAmount("0.00");
		this.setPatNewDeptVisitAmount("0.00");
		this.setIsPatReferByList("");
		this.setOnRequestVisit("");
		this.setGoWithoutDeptVisitOn("");
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
		this.setPrint("");
		//this.setSaveSuccessful("");
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	public String getRelationWithStaff() {
		return relationWithStaff;
	}
	public void setRelationWithStaff(String relationWithStaff) {
		this.relationWithStaff = relationWithStaff;
	}
	public String getCardvalidityDate() {
		return cardvalidityDate;
	}
	public void setCardvalidityDate(String cardvalidityDate) {
		this.cardvalidityDate = cardvalidityDate;
	}
	public String getRelationNameWithStaff() {
		return relationNameWithStaff;
	}
	public void setRelationNameWithStaff(String relationNameWithStaff) {
		this.relationNameWithStaff = relationNameWithStaff;
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
	public String getPatActualAmount() {
		return patActualAmount;
	}
	public void setPatActualAmount(String patActualAmount) {
		this.patActualAmount = patActualAmount;
	}
	public String getIsPatAdmitted() {
		return isPatAdmitted;
	}
	public void setIsPatAdmitted(String isPatAdmitted) {
		this.isPatAdmitted = isPatAdmitted;
	}
	public String getPatRenewalActualAmount() {
		return patRenewalActualAmount;
	}
	public void setPatRenewalActualAmount(String patRenewalActualAmount) {
		this.patRenewalActualAmount = patRenewalActualAmount;
	}	

	/* #Start					:Sheeldarshi 
	#Modify Date(CR/PRS)	:22ndMay'15 
	#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	public WebRowSet getStrResultWs() {
		return strResultWs;
	}

	public void setStrResultWs(WebRowSet strResultWs) {
		this.strResultWs = strResultWs;
	}
	//End
	//Sheel
	public String getPatAmountNCrConsultation() {
		return patAmountNCrConsultation;
	}

	public void setPatAmountNCrConsultation(String patAmountNCrConsultation) {
		this.patAmountNCrConsultation = patAmountNCrConsultation;
	}

	public String getPatAmountCrConsultation() {
		return patAmountCrConsultation;
	}

	public void setPatAmountCrConsultation(String patAmountCrConsultation) {
		this.patAmountCrConsultation = patAmountCrConsultation;
	}

	public String getPatIsCrossConsultantWithReferal() {
		return patIsCrossConsultantWithReferal;
	}

	public void setPatIsCrossConsultantWithReferal(
			String patIsCrossConsultantWithReferal) {
		this.patIsCrossConsultantWithReferal = patIsCrossConsultantWithReferal;
	}
	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getPatStaffNo() {
		return patStaffNo;
	}
	public void setPatStaffNo(String patStaffNo) {
		this.patStaffNo = patStaffNo;
	}
	public String getPatStaffName() {
		return patStaffName;
	}
	public void setPatStaffName(String patStaffName) {
		this.patStaffName = patStaffName;
	}
	public String getPatStaffDeptName() {
		return patStaffDeptName;
	}
	public void setPatStaffDeptName(String patStaffDeptName) {
		this.patStaffDeptName = patStaffDeptName;
	}
	public String getPatStaffRelationName() {
		return patStaffRelationName;
	}
	public void setPatStaffRelationName(String patStaffRelationName) {
		this.patStaffRelationName = patStaffRelationName;
	}
		//End
}
