package registration.controller.fb;

/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PatientModificationFB extends CRNoFB
{
	private String hmode;
	private String addModify;
	private String patStatusCode;
	

	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patFirstNameInMultiLang;
    private String patMiddleNameInMultiLang;
    private String patLastNameInMultiLang;
	private String isActualDob;// if (age provided) isActualDob=0 else isActualDob=1
	private String patAge;
	private String patAgeUnit;
	private String patDOB;
	private String patGenderCode;
	private String patCasteCode;
	private String patGuardianName;
	private String patHusbandName;
	private String patMotherName;
	private String patNationalityCode;
	private String patReligionCode;
	private String patNationalId;	// Unique Id
	private String isMLC;
	private String mlcNo;
	private String isNewImageUploaded;
	private String beforeModificationAge;
	
	// Address Details corresponding to AddressVO
	private String patFBAddTypeCode;
	private String[] arrSelectedVerifyDocs;
	private String isAddressDelhi;
	private String patAddTypeCode;
	private String patAddTypeLable;
	private String strPatAddressTehsil;
	private String patAddCountryCode;
	private String patAddStateCode;
	private String patAddStateName;
	private String patAddCityLocCode;
	private String patAddCityLoc;
	private String patAddHNo;
	private String patAddStreet;
	private String patAddDistrictCode;
	private String patAddDistrict;
	private String patAddCity;
	private String patAddPIN;
	private String patIsUrban;
	private String patAddContactNo;
	private String patAddMobileNo;
	private String patAddFaxNo;
	private String patAddEmailId;
	private String requestBy;
	private String requestRelation;
	private String requestByName;
	private String requestByAddress;
	private String constableDesig;
	private String constableBadgeNo;
	private String verificationDocumentAdded;
	private String isLocationComboReq;
	private String patAddType;
	
	private String patIdNo;

	private String patPrimaryCat;
	private String patCardNo; //MMJRK No
	private String patNickName; //familyHeadName
	private String patHusbandOccupation;//relation
	private String patPrimaryCatCode;	
	
	private String patCasteCategoryCode;
	private String searchName;
	private String addressTypeLeft;	
	
	private String patAgeSelection;
	private String patAddCityLocName;
	private String patSecondaryCat;
	private String patSecondaryCatCode;
	private String patMaritalStatusCode;
	private String departmentCode;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;// hospital name in case reffered from some other hospital
	private String referringInst;
	private String patGender;
	private String pat;
	private String patRefDoctor;
	private String patMonthlyIncome;
	private String patAmountCollected;
	private String isReferred;
	private String prevCrNo;
	// private String patRegCatCode;
	private String entryDate;
	// private String isUnknown;
	private String seatId = "1";
	private String stateRadio;
	// Address Details corresponding to AddressVO
	private String patAreaCategoryCode;
	private String patAddState;
	private String patAddCountry;
	private String patContactNo;
	private String departmentdiv;
	private String isCurrentAddress;
	private String referringInstType;
	private String valid;
	// Episode Detials Corresponding to EpisodeVO
	private String episodeType;
	// Array of departments to visitstamp
	private String[] departmentsToVisitStamp;
	private String[] newdepartmentsToVisitStamp;
	private String[] alreadyVisitedDept;
	// private String[]
	private String removeDept;
	private String newremoveDept;
	private String crNoToModify;
	private String patRefGnctdHospitalCrno;
	private String patRefGnctdHospitalDept;
	private String patRefGnctdHospitalDeptUnit;
	private String getCrNoToRetrieve;
	private String patFatherName;
	private String patOccupation;
	private String patFatherOccupation;
	private String patAddDistrictCodeHidden;
	private String patAddPINHidden;
	private String patAddCityHidden;
	private String patIsUrbanHidden;

	private String patCatIdNo;
	
	private String patDocType;
	private String patBirthPlace;
	private String deskmode;
	
	private String deskModFlag;


	public PatientModificationFB()
	{
		this.patCasteCode = "";
		this.verificationDocumentAdded = "0";
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.patCasteCode = "";
		this.verificationDocumentAdded = "0";
		
		this.setPatCrNo("            ");
		this.setHmode("");
		this.setPatAddCity("");
		this.setIsAddressDelhi("1");
		this.setPatAddCityLoc("");
		this.setPatAddState("");
		this.setPatAddCountry("");
		this.setPatFBAddTypeCode("-1");
		this.setArrSelectedVerifyDocs(new String[]{});
		this.setPatAddMobileNo("");
		this.setPatAddFaxNo("");
		this.setPatAddEmailId("");
		this.setPatIdNo("");
		this.setPatAddStateName("");
		this.setAddModify("");
		this.setVerificationDocumentAdded("0");
	}

	public void resetAddressDetails(ActionMapping mapping, HttpServletRequest request)
	{
		this.setPatAddCity("");
		this.setIsAddressDelhi("1");
		this.setPatAddCityLoc("");
		this.setPatAddState("");
		this.setPatAddCountry("");
		this.setPatFBAddTypeCode("-1");
	}

	public void resetAddress()
	{
		patAddTypeCode = "";
		patAddType = "";
		patIsUrban = "";
		patAddCityLoc = "";
		patAddCityLocCode = "";
		patAddState = "";
		patAddStateCode = "";
		patAddCountry = "";
		patAddCountryCode = "";
		patAddHNo = "";
		patAddStreet = "";
		patAddDistrict = "";
		patAddCity = "";
		patAddPIN = "";
		patAddContactNo = "";
		isAddressDelhi = "1";
		isCurrentAddress = "";
		this.setPatAddMobileNo("");
		this.setPatAddFaxNo("");
		this.setPatAddEmailId("");
		this.setPatAddStateName("");
		this.setPatAddTypeLable("");
		this.setStrPatAddressTehsil("");
	}

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1)
	{
		return super.validate(arg0, arg1);
	}

	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getIsLocationComboReq()
	{
		return isLocationComboReq;
	}

	public void setIsLocationComboReq(String isLocationComboReq)
	{
		this.isLocationComboReq = isLocationComboReq;
	}

	public String getPatAddTypeLable()
	{
		return patAddTypeLable;
	}

	public void setPatAddTypeLable(String patAddTypeLable)
	{
		this.patAddTypeLable = patAddTypeLable;
	}

	public String getAddModify()
	{
		return addModify;
	}

	public void setAddModify(String addModify)
	{
		this.addModify = addModify;
	}

	public String getPatAddStateName()
	{
		return patAddStateName;
	}

	public void setPatAddStateName(String patAddStateName)
	{
		this.patAddStateName = patAddStateName;
	}

	public String getPatAddMobileNo()
	{
		return patAddMobileNo;
	}

	public void setPatAddMobileNo(String patAddMobileNo)
	{
		this.patAddMobileNo = patAddMobileNo;
	}

	public String getPatAddFaxNo()
	{
		return patAddFaxNo;
	}

	public void setPatAddFaxNo(String patAddFaxNo)
	{
		this.patAddFaxNo = patAddFaxNo;
	}

	public String getPatAddEmailId()
	{
		return patAddEmailId;
	}

	public void setPatAddEmailId(String patAddEmailId)
	{
		this.patAddEmailId = patAddEmailId;
	}

	public String getSearchName()
	{
		return searchName;
	}

	public void setSearchName(String searchName)
	{
		this.searchName = searchName;
	}

	public String[] getArrSelectedVerifyDocs()
	{
		return arrSelectedVerifyDocs;
	}

	public void setArrSelectedVerifyDocs(String[] arrSelectedVerifyDocs)
	{
		this.arrSelectedVerifyDocs = arrSelectedVerifyDocs;
	}

	public String getIsAddressDelhi()
	{
		return isAddressDelhi;
	}

	public void setIsAddressDelhi(String isAddressDelhi)
	{
		this.isAddressDelhi = isAddressDelhi;
	}

	public String getIsCurrentAddress()
	{
		return isCurrentAddress;
	}

	public void setIsCurrentAddress(String isCurrentAddress)
	{
		this.isCurrentAddress = isCurrentAddress;
	}

	public String getPatAddCity()
	{
		return patAddCity;
	}

	public void setPatAddCity(String patAddCity)
	{
		this.patAddCity = patAddCity;
	}

	public String getPatAddCityLoc()
	{
		return patAddCityLoc;
	}

	public void setPatAddCityLoc(String patAddCityLoc)
	{
		this.patAddCityLoc = patAddCityLoc;
	}

	public String getPatAddCityLocCode()
	{
		return patAddCityLocCode;
	}

	public void setPatAddCityLocCode(String patAddCityLocCode)
	{
		this.patAddCityLocCode = patAddCityLocCode;
	}

	public String getPatAddContactNo()
	{
		return patAddContactNo;
	}

	public void setPatAddContactNo(String patAddContactNo)
	{
		this.patAddContactNo = patAddContactNo;
	}

	public String getPatAddCountry()
	{
		return patAddCountry;
	}

	public void setPatAddCountry(String patAddCountry)
	{
		this.patAddCountry = patAddCountry;
	}

	public String getPatAddCountryCode()
	{
		return patAddCountryCode;
	}

	public void setPatAddCountryCode(String patAddCountryCode)
	{
		this.patAddCountryCode = patAddCountryCode;
	}

	public String getPatAddDistrict()
	{
		return patAddDistrict;
	}

	public void setPatAddDistrict(String patAddDistrict)
	{
		this.patAddDistrict = patAddDistrict;
	}

	public String getPatAddHNo()
	{
		return patAddHNo;
	}

	public void setPatAddHNo(String patAddHNo)
	{
		this.patAddHNo = patAddHNo;
	}

	public String getPatAddPIN()
	{
		return patAddPIN;
	}

	public void setPatAddPIN(String patAddPIN)
	{
		this.patAddPIN = patAddPIN;
	}

	public String getPatAddState()
	{
		return patAddState;
	}

	public void setPatAddState(String patAddState)
	{
		this.patAddState = patAddState;
	}

	public String getPatAddStateCode()
	{
		return patAddStateCode;
	}

	public void setPatAddStateCode(String patAddStateCode)
	{
		this.patAddStateCode = patAddStateCode;
	}

	public String getPatAddStreet()
	{
		return patAddStreet;
	}

	public void setPatAddStreet(String patAddStreet)
	{
		this.patAddStreet = patAddStreet;
	}

	public String getPatAddTypeCode()
	{
		return patAddTypeCode;
	}

	public void setPatAddTypeCode(String patAddTypeCode)
	{
		this.patAddTypeCode = patAddTypeCode;
	}

	public String getPatIsUrban()
	{
		return patIsUrban;
	}

	public void setPatIsUrban(String patIsUrban)
	{
		this.patIsUrban = patIsUrban;
	}

	public String getValid()
	{
		return valid;
	}

	public void setValid(String valid)
	{
		this.valid = valid;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getPatAddType()
	{
		return patAddType;
	}

	public void setPatAddType(String patAddType)
	{
		this.patAddType = patAddType;
	}

	public String getPatFBAddTypeCode()
	{
		return patFBAddTypeCode;
	}

	public void setPatFBAddTypeCode(String patFBAddTypeCode)
	{
		this.patFBAddTypeCode = patFBAddTypeCode;
	}

	public String getGetCrNoToRetrieve()
	{
		return getCrNoToRetrieve;
	}

	public void setGetCrNoToRetrieve(String getCrNoToRetrieve)
	{
		this.getCrNoToRetrieve = getCrNoToRetrieve;
	}

	public String getPatIdNo()
	{
		return patIdNo;
	}

	public void setPatIdNo(String patIdNo)
	{
		this.patIdNo = patIdNo;
	}

	public String getPatAddDistrictCode()
	{
		return patAddDistrictCode;
	}

	public void setPatAddDistrictCode(String patAddDistrictCode)
	{
		this.patAddDistrictCode = patAddDistrictCode;
	}

	public String getRequestBy()
	{
		return requestBy;
	}

	public void setRequestBy(String requestBy)
	{
		this.requestBy = requestBy;
	}

	public String getRequestRelation()
	{
		return requestRelation;
	}

	public void setRequestRelation(String requestRelation)
	{
		this.requestRelation = requestRelation;
	}

	public String getRequestByName()
	{
		return requestByName;
	}

	public void setRequestByName(String requestByName)
	{
		this.requestByName = requestByName;
	}

	public String getConstableDesig()
	{
		return constableDesig;
	}

	public void setConstableDesig(String constableDesig)
	{
		this.constableDesig = constableDesig;
	}

	public String getConstableBadgeNo()
	{
		return constableBadgeNo;
	}

	public void setConstableBadgeNo(String constableBadgeNo)
	{
		this.constableBadgeNo = constableBadgeNo;
	}

	public String getRequestByAddress()
	{
		return requestByAddress;
	}

	public void setRequestByAddress(String requestByAddress)
	{
		this.requestByAddress = requestByAddress;
	}

	public String getIsMLC()
	{
		return isMLC;
	}

	public void setIsMLC(String isMLC)
	{
		this.isMLC = isMLC;
	}

	public String getVerificationDocumentAdded()
	{
		return verificationDocumentAdded;
	}

	public void setVerificationDocumentAdded(String verificationDocumentAdded)
	{
		this.verificationDocumentAdded = verificationDocumentAdded;
	}

	public String getPatStatusCode()
	{
		return patStatusCode;
	}

	public void setPatStatusCode(String patStatusCode)
	{
		this.patStatusCode = patStatusCode;
	}

	public String getMlcNo()
	{
		return mlcNo;
	}

	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	public String getPatAge()
	{
		return patAge;
	}

	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	public String getAddressTypeLeft()
	{
		return addressTypeLeft;
	}

	public void setAddressTypeLeft(String addressTypeLeft)
	{
		this.addressTypeLeft = addressTypeLeft;
	}

	public String getPatAgeSelection()
	{
		return patAgeSelection;
	}

	public void setPatAgeSelection(String patAgeSelection)
	{
		this.patAgeSelection = patAgeSelection;
	}

	public String getPatAddCityLocName()
	{
		return patAddCityLocName;
	}

	public void setPatAddCityLocName(String patAddCityLocName)
	{
		this.patAddCityLocName = patAddCityLocName;
	}

	public String getPatNationalityCode()
	{
		return patNationalityCode;
	}

	public void setPatNationalityCode(String patNationalityCode)
	{
		this.patNationalityCode = patNationalityCode;
	}

	public String getPatPrimaryCat()
	{
		return patPrimaryCat;
	}

	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	public String getPatSecondaryCat()
	{
		return patSecondaryCat;
	}

	public void setPatSecondaryCat(String patSecondaryCat)
	{
		this.patSecondaryCat = patSecondaryCat;
	}

	public String getPatSecondaryCatCode()
	{
		return patSecondaryCatCode;
	}

	public void setPatSecondaryCatCode(String patSecondaryCatCode)
	{
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	public String getPatMaritalStatusCode()
	{
		return patMaritalStatusCode;
	}

	public void setPatMaritalStatusCode(String patMaritalStatusCode)
	{
		this.patMaritalStatusCode = patMaritalStatusCode;
	}

	public String getPatReligionCode()
	{
		return patReligionCode;
	}

	public void setPatReligionCode(String patReligionCode)
	{
		this.patReligionCode = patReligionCode;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}

	public String getPatFirstName()
	{
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	public String getReferringInst()
	{
		return referringInst;
	}

	public void setReferringInst(String referringInst)
	{
		this.referringInst = referringInst;
	}

	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	public String getPatLastName()
	{
		return patLastName;
	}

	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
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

	public String getPatGender()
	{
		return patGender;
	}

	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	public String getPatGenderCode()
	{
		return patGenderCode;
	}

	public void setPatGenderCode(String patGenderCode)
	{
		this.patGenderCode = patGenderCode;
	}

	public String getPatDOB()
	{
		return patDOB;
	}

	public void setPatDOB(String patDOB)
	{
		this.patDOB = patDOB;
	}

	public String getIsActualDob()
	{
		return isActualDob;
	}

	public void setIsActualDob(String isActualDob)
	{
		this.isActualDob = isActualDob;
	}

	public String getPat()
	{
		return pat;
	}

	public void setPat(String pat)
	{
		this.pat = pat;
	}

	public String getPatRefDoctor()
	{
		return patRefDoctor;
	}

	public void setPatRefDoctor(String patRefDoctor)
	{
		this.patRefDoctor = patRefDoctor;
	}

	public String getPatMonthlyIncome()
	{
		return patMonthlyIncome;
	}

	public void setPatMonthlyIncome(String patMonthlyIncome)
	{
		this.patMonthlyIncome = patMonthlyIncome;
	}

	public String getPatAmountCollected()
	{
		return patAmountCollected;
	}

	public void setPatAmountCollected(String patAmountCollected)
	{
		this.patAmountCollected = patAmountCollected;
	}

	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	public String getIsReferred()
	{
		return isReferred;
	}

	public void setIsReferred(String isReferred)
	{
		this.isReferred = isReferred;
	}

	public String getPatAgeUnit()
	{
		return patAgeUnit;
	}

	public void setPatAgeUnit(String patAgeUnit)
	{
		this.patAgeUnit = patAgeUnit;
	}

	public String getPrevCrNo()
	{
		return prevCrNo;
	}

	public void setPrevCrNo(String prevCrNo)
	{
		this.prevCrNo = prevCrNo;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getStateRadio()
	{
		return stateRadio;
	}

	public void setStateRadio(String stateRadio)
	{
		this.stateRadio = stateRadio;
	}

	public String getPatAreaCategoryCode()
	{
		return patAreaCategoryCode;
	}

	public void setPatAreaCategoryCode(String patAreaCategoryCode)
	{
		this.patAreaCategoryCode = patAreaCategoryCode;
	}

	public String getPatContactNo()
	{
		return patContactNo;
	}

	public void setPatContactNo(String patContactNo)
	{
		this.patContactNo = patContactNo;
	}

	public String getDepartmentdiv()
	{
		return departmentdiv;
	}

	public void setDepartmentdiv(String departmentdiv)
	{
		this.departmentdiv = departmentdiv;
	}

	public String getReferringInstType()
	{
		return referringInstType;
	}

	public void setReferringInstType(String referringInstType)
	{
		this.referringInstType = referringInstType;
	}

	public String getEpisodeType()
	{
		return episodeType;
	}

	public void setEpisodeType(String episodeType)
	{
		this.episodeType = episodeType;
	}

	public String[] getDepartmentsToVisitStamp()
	{
		return departmentsToVisitStamp;
	}

	public void setDepartmentsToVisitStamp(String[] departmentsToVisitStamp)
	{
		this.departmentsToVisitStamp = departmentsToVisitStamp;
	}

	public String[] getNewdepartmentsToVisitStamp()
	{
		return newdepartmentsToVisitStamp;
	}

	public void setNewdepartmentsToVisitStamp(String[] newdepartmentsToVisitStamp)
	{
		this.newdepartmentsToVisitStamp = newdepartmentsToVisitStamp;
	}

	public String[] getAlreadyVisitedDept()
	{
		return alreadyVisitedDept;
	}

	public void setAlreadyVisitedDept(String[] alreadyVisitedDept)
	{
		this.alreadyVisitedDept = alreadyVisitedDept;
	}

	public String getRemoveDept()
	{
		return removeDept;
	}

	public void setRemoveDept(String removeDept)
	{
		this.removeDept = removeDept;
	}

	public String getNewremoveDept()
	{
		return newremoveDept;
	}

	public void setNewremoveDept(String newremoveDept)
	{
		this.newremoveDept = newremoveDept;
	}

	public String getCrNoToModify()
	{
		return crNoToModify;
	}

	public void setCrNoToModify(String crNoToModify)
	{
		this.crNoToModify = crNoToModify;
	}

	public String getPatRefGnctdHospitalCrno()
	{
		return patRefGnctdHospitalCrno;
	}

	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno)
	{
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}

	public String getPatRefGnctdHospitalDept()
	{
		return patRefGnctdHospitalDept;
	}

	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept)
	{
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}

	public String getPatRefGnctdHospitalDeptUnit()
	{
		return patRefGnctdHospitalDeptUnit;
	}

	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit)
	{
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	public String getPatFatherName()
	{
		return patFatherName;
	}

	public void setPatFatherName(String patFatherName)
	{
		this.patFatherName = patFatherName;
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

	public String getPatNationalId()
	{
		return patNationalId;
	}

	public void setPatNationalId(String patNationalId)
	{
		this.patNationalId = patNationalId;
	}

	public String getPatOccupation()
	{
		return patOccupation;
	}

	public void setPatOccupation(String patOccupation)
	{
		this.patOccupation = patOccupation;
	}

	public String getPatFatherOccupation()
	{
		return patFatherOccupation;
	}

	public void setPatFatherOccupation(String patFatherOccupation)
	{
		this.patFatherOccupation = patFatherOccupation;
	}

	public String getPatHusbandOccupation()
	{
		return patHusbandOccupation;
	}

	public void setPatHusbandOccupation(String patHusbandOccupation)
	{
		this.patHusbandOccupation = patHusbandOccupation;
	}

	public String getPatNickName()
	{
		return patNickName;
	}

	public void setPatNickName(String patNickName)
	{
		this.patNickName = patNickName;
	}

	public String getPatCardNo()
	{
		return patCardNo;
	}

	public void setPatCardNo(String patCardNo)
	{
		this.patCardNo = patCardNo;
	}

	public String getPatAddDistrictCodeHidden()
	{
		return patAddDistrictCodeHidden;
	}

	public void setPatAddDistrictCodeHidden(String patAddDistrictCodeHidden)
	{
		this.patAddDistrictCodeHidden = patAddDistrictCodeHidden;
	}

	public String getPatAddPINHidden()
	{
		return patAddPINHidden;
	}

	public void setPatAddPINHidden(String patAddPINHidden)
	{
		this.patAddPINHidden = patAddPINHidden;
	}

	public String getPatAddCityHidden()
	{
		return patAddCityHidden;
	}

	public void setPatAddCityHidden(String patAddCityHidden)
	{
		this.patAddCityHidden = patAddCityHidden;
	}

	public String getPatIsUrbanHidden()
	{
		return patIsUrbanHidden;
	}

	public void setPatIsUrbanHidden(String patIsUrbanHidden)
	{
		this.patIsUrbanHidden = patIsUrbanHidden;
	}

	public String getBeforeModificationAge()
	{
		return beforeModificationAge;
	}

	public void setBeforeModificationAge(String beforeModificationAge)
	{
		this.beforeModificationAge = beforeModificationAge;
	}

	public String getIsNewImageUploaded()
	{
		return isNewImageUploaded;
	}

	public void setIsNewImageUploaded(String isNewImageUploaded)
	{
		this.isNewImageUploaded = isNewImageUploaded;
	}

	public String getPatCatIdNo()
	{
		return patCatIdNo;
	}

	public void setPatCatIdNo(String patCatIdNo)
	{
		this.patCatIdNo = patCatIdNo;
	}

	public String getPatCasteCategoryCode()
	{
		return patCasteCategoryCode;
	}

	public void setPatCasteCategoryCode(String patCasteCategoryCode)
	{
		this.patCasteCategoryCode = patCasteCategoryCode;
	}

	public String getPatCasteCode()
	{
		return patCasteCode;
	}

	public void setPatCasteCode(String patCasteCode)
	{
		this.patCasteCode = patCasteCode;
	}

	public String getStrPatAddressTehsil()
	{
		return strPatAddressTehsil;
	}

	public void setStrPatAddressTehsil(String strPatAddressTehsil)
	{
		this.strPatAddressTehsil = strPatAddressTehsil;
	}

	public String getPatDocType() {
		return patDocType;
	}

	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}

	public String getPatBirthPlace() {
		return patBirthPlace;
	}

	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}

	public String getDeskModFlag() {
		return deskModFlag;
	}

	public void setDeskModFlag(String deskModFlag) {
		this.deskModFlag = deskModFlag;
	}

	public String getDeskmode() {
		return deskmode;
	}

	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}

}
