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

public abstract class UnknownToKnownSUP extends CRNoSUP implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String AfterGo;
	protected String patCasteCode;
	protected String patBirthPlace;
	protected String	isMLC="";
	private String registerDate;
	
	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getIsMLC() {
		return isMLC;
	}

	public void setIsMLC(String isMLC) {
		this.isMLC = isMLC;
	}

	public String getPatCasteCode() {
		return patCasteCode;
	}

	public void setPatCasteCode(String patCasteCode) {
		this.patCasteCode = patCasteCode;
	}

	public String getPatBirthPlace() {
		return patBirthPlace;
	}

	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}

		// Address Details corresponding to AddressVO
		protected String patFBAddTypeCode;
		protected String[] arrSelectedVerifyDocs;
	

	/*protected String patCrNo;
	protected String clientName;
	protected String mlcNo;
	protected String configFlag;*/
	protected String patCrNo;

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	protected String patPrimaryCat;
	protected String patPrimaryCatCode;
    protected String patSecondaryCat;
	protected String patSecondaryCatCode;
    protected String patMaritalStatusCode;
    protected String patReligionCode;
    protected String departmentCode;
	protected String hmode;
	protected String patRefGnctdHospitalCode;
	protected String patRefHospitalName;//hospital name in case reffered from some other hospital
    protected String patFirstName;
    protected String referringInst;
    protected String patMiddleName;
    protected String patMomName;
    protected String patLastName;
    protected String patGender;
	protected String patGenderCode;
    protected String patAge;
    protected String patDOB;
    protected String isActualDob;//if (age provided) isActualDob=0 else isActualDob=1
    protected String patIsUrban;
    protected String patRefDoctor;
    protected String patMonthlyIncome;
    protected String patAmountCollected;
    protected String patGuardianName;
    protected String isReferred;
    protected String patAgeUnit;    
    protected String prevCrNo;
	protected String patIdNo;
	protected String patRegCatCode;
	protected String entryDate;
	protected String isUnknown;
	protected String seatId="1";
	protected String stateRadio;
	//Address Details corresponding to AddressVO
	protected String patAddTypeCode;
    protected String patAreaCategoryCode;
    protected String patAddCityLoc;
	protected String patAddCityLocCode;
    protected String patAddState;
	protected String patAddStateCode;
    protected String patAddCountry;
	protected String patAddCountryCode;
    protected String patAddHNo;
    protected String patAddStreet;
    protected String patAddDistrict;
    protected String patAddDistrictCode;
    protected String patAddCity;
    protected String patAddPIN;
	protected String patAddContactNo;
	protected String isAddressDelhi;
    protected String patContactNo;
    protected String departmentdiv;
	protected String isCurrentAddress;	
	protected String referringInstType;
	protected String valid;
	// Episode Detials Corresponding to EpisodeVO
	protected String episodeType;
	//Array of departments to visitstamp
	protected String[] departmentsToVisitStamp;
	protected String[] newdepartmentsToVisitStamp;
	protected String[] alreadyVisitedDept;
	//protected String[] 
	protected String removeDept;
	protected String newremoveDept;
	protected String patNationalityCode;
	protected String patRefGnctdHospitalCrno;
	protected String patRefGnctdHospitalDept;
	protected String patRefGnctdHospitalDeptUnit;
	protected String patHusbandName;
	protected String patMotherName;
	protected String patNationalId;	
	protected String fileNo[];
	protected String empIdChk;
	protected String stateName;
	protected String patAddStateName;
	protected String patOccupation;
	protected String patFatherOccupation;
	protected String patHusbandOccupation;
	protected String patNickName;
	protected String patCardNo;
	protected String patDataFromEmployeeTable;
	protected String isIdRequired;
	protected String patCatIdNo;
	protected String patAddDistrictCodeHidden;
	protected String patAddPINHidden;
	protected String patAddCityHidden;
	protected String patIsUrbanHidden;
	protected String patRefHospitalDeptOther;
	protected String isDuplicatePatientPopup;
	protected String errorMessage;
	protected String isOldPatient;
	protected String oldPatCrNo;
	protected String addModify;
	protected String patAddLandMarks;
	
	protected String  patAddPhoneNo;
	protected String  patAddPhoneOwner;
	protected String patDocType;
	protected String tempIsActualDOB;
	
	protected String patIdMark1;
	protected String patIdMark2;
	
	/*Modify Date			: 24thNov'14
	  Reason	(CR/PRS)	: Secondary UHID check incorporation
	  Modify By				: Sheeldarshi */
	protected String patCatDocCode;
	protected String patCatDocIsAlternateId;
	protected String asNewPatient;
	protected String  isDuplicatePatient;
	//End:Sheeldarshi
	
public String getPatIdMark1() {
		return patIdMark1;
	}

	public void setPatIdMark1(String patIdMark1) {
		this.patIdMark1 = patIdMark1;
	}

	public String getPatIdMark2() {
		return patIdMark2;
	}

	public void setPatIdMark2(String patIdMark2) {
		this.patIdMark2 = patIdMark2;
	}

protected String patDocTypeName;
	
	public String getPatDocTypeName() {
	return patDocTypeName;
}

public void setPatDocTypeName(String patDocTypeName) {
	this.patDocTypeName = patDocTypeName;
}

	public String getTempIsActualDOB() {
		return tempIsActualDOB;
	}

	public void setTempIsActualDOB(String tempIsActualDOB) {
		this.tempIsActualDOB = tempIsActualDOB;
	}

	public String getPatDocType() {
		return patDocType;
	}

	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}

	public String getPatAddPhoneOwner() {
		return patAddPhoneOwner;
	}

	public void setPatAddPhoneOwner(String patAddPhoneOwner) {
		this.patAddPhoneOwner = patAddPhoneOwner;
	}

	public String getPatAddPhoneNo() {
		return patAddPhoneNo;
	}

	public void setPatAddPhoneNo(String patAddPhoneNo) {
		this.patAddPhoneNo = patAddPhoneNo;
	}

	public String getPatAddLandMarks() {
		return patAddLandMarks;
	}

	public void setPatAddLandMarks(String patAddLandMarks) {
		this.patAddLandMarks = patAddLandMarks;
	}

	public String getAddModify() {
		return addModify;
	}

	public void setAddModify(String addModify) {
		this.addModify = addModify;
	}

	public String getPatAddTypeLable() {
		return patAddTypeLable;
	}

	public void setPatAddTypeLable(String patAddTypeLable) {
		this.patAddTypeLable = patAddTypeLable;
	}

	public String getStrPatAddressTehsil() {
		return strPatAddressTehsil;
	}

	public void setStrPatAddressTehsil(String strPatAddressTehsil) {
		this.strPatAddressTehsil = strPatAddressTehsil;
	}

	public String getPatAddMobileNo() {
		return patAddMobileNo;
	}

	public void setPatAddMobileNo(String patAddMobileNo) {
		this.patAddMobileNo = patAddMobileNo;
	}

	public String getPatAddFaxNo() {
		return patAddFaxNo;
	}

	public void setPatAddFaxNo(String patAddFaxNo) {
		this.patAddFaxNo = patAddFaxNo;
	}

	public String getPatAddEmailId() {
		return patAddEmailId;
	}

	public void setPatAddEmailId(String patAddEmailId) {
		this.patAddEmailId = patAddEmailId;
	}

	public String getConstableDesig() {
		return constableDesig;
	}

	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}

	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}

	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}


	public String getPatAddType() {
		return patAddType;
	}

	public void setPatAddType(String patAddType) {
		this.patAddType = patAddType;
	}

	protected String patAddTypeLable;
	protected String strPatAddressTehsil;
	protected String patAddMobileNo;
	protected String patAddFaxNo;
	protected String patAddEmailId;
	protected String constableDesig;
	protected String constableBadgeNo;
	protected String patAddType;
	protected String requestBy;
	protected String requestRelation;
	protected String requestByName;
	protected String requestByAddress;
	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getRequestRelation() {
		return requestRelation;
	}

	public void setRequestRelation(String requestRelation) {
		this.requestRelation = requestRelation;
	}

	public String getRequestByName() {
		return requestByName;
	}

	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}

	public String getRequestByAddress() {
		return requestByAddress;
	}

	public void setRequestByAddress(String requestByAddress) {
		this.requestByAddress = requestByAddress;
	}

	public String getVerificationDocumentAdded() {
		return verificationDocumentAdded;
	}

	public void setVerificationDocumentAdded(String verificationDocumentAdded) {
		this.verificationDocumentAdded = verificationDocumentAdded;
	}

	protected String verificationDocumentAdded;
	
	protected String crNoToModify;
	protected String isRelative;
	

	public String getIsRelative() {
		return isRelative;
	}

	public void setIsRelative(String isRelative) {
		this.isRelative = isRelative;
	}
	
	public String getCrNoToModify() {
		return crNoToModify;
	}

	public void setCrNoToModify(String crNoToModify) {
		this.crNoToModify = crNoToModify;
	}

	protected String beforeModificationAge;
	public String getPatFBAddTypeCode() {
		return patFBAddTypeCode;
	}

	public void setPatFBAddTypeCode(String patFBAddTypeCode) {
		this.patFBAddTypeCode = patFBAddTypeCode;
	}

	public String[] getArrSelectedVerifyDocs() {
		return arrSelectedVerifyDocs;
	}

	public void setArrSelectedVerifyDocs(String[] arrSelectedVerifyDocs) {
		this.arrSelectedVerifyDocs = arrSelectedVerifyDocs;
	}

public String getAfterGo() {
	return AfterGo;
}

public void setAfterGo(String afterGo) {
	AfterGo = afterGo;
}
	public UnknownToKnownSUP()
	{
		patPrimaryCat="";
		patPrimaryCatCode="";
	    patSecondaryCat="";
		patSecondaryCatCode="";
	    patMaritalStatusCode="";
	    patReligionCode="";
	    departmentCode="";
		hmode="";
		patRefGnctdHospitalCode="";
		patRefHospitalName="";//hospital name in case reffered from some other hospital
	    patFirstName="";
	    referringInst="";
	    patMiddleName="";
	    patMomName="";
	    patLastName="";
	    patGender="";
		patGenderCode="";
	    patAge="";
	    patDOB="";
	    isActualDob="";//if (age provided) isActualDob=0 else isActualDob=1
	    patIsUrban="";
	    patRefDoctor="";
	    patMonthlyIncome="";
	    patAmountCollected="";
	    patGuardianName="";
	    isReferred="";
	    patAgeUnit="";    
	    prevCrNo="";
		patIdNo="";
		patRegCatCode="";
		entryDate="";
		isUnknown="";
		seatId="1";
		stateRadio="";
		//Address Details corresponding to AddressVO
		patAddTypeCode="";
	    patAreaCategoryCode="";
	    patAddCityLoc="";
		patAddCityLocCode="";
	    patAddState="";
		patAddStateCode="";
	    patAddCountry="";
		patAddCountryCode="";
	    patAddHNo="";
	    patAddStreet="";
	    patAddDistrict="";
	    patAddDistrictCode="";
	    patAddCity="";
	    patAddPIN="";
		patAddContactNo="";
		isAddressDelhi="";
	    patContactNo="";
	    departmentdiv="";
		isCurrentAddress="";	
		referringInstType="";
		valid="";
		// Episode Detials Corresponding to EpisodeVO
		episodeType="";
		//Array of departments to visitstamp
		/*departmentsToVisitStamp;
		newdepartmentsToVisitStamp;
		alreadyVisitedDept;*/
		
		removeDept="";
		newremoveDept="";
		patNationalityCode="";
		patRefGnctdHospitalCrno="";
		patRefGnctdHospitalDept="";
		patRefGnctdHospitalDeptUnit="";
		patHusbandName="";
		patMotherName="";
		patNationalId="";	
		//fileNo[]="";
		empIdChk="";
		stateName="";
		patAddStateName="";
		patOccupation="";
		patFatherOccupation="";
		patHusbandOccupation="";
		patNickName="";
		patCardNo="";
		patDataFromEmployeeTable="";
		isIdRequired="";
		patCatIdNo="";
		patAddDistrictCodeHidden="";
		patAddPINHidden="";
		patAddCityHidden="";
		patIsUrbanHidden="";
		patRefHospitalDeptOther="";
		isDuplicatePatientPopup="";
		errorMessage="";
		isOldPatient="";
		oldPatCrNo="";
	}
	
	public void clear()
	{
		addModify="";
		patPrimaryCat="";
		patPrimaryCatCode="";
	    patSecondaryCat="";
		patSecondaryCatCode="";
	    patMaritalStatusCode="";
	    patReligionCode="";
	    departmentCode="";
		hmode="";
		patRefGnctdHospitalCode="";
		patRefHospitalName="";//hospital name in case reffered from some other hospital
	    patFirstName="";
	    referringInst="";
	    patMiddleName="";
	    patMomName="";
	    patLastName="";
	    patGender="";
		patGenderCode="";
	    patAge="";
	    patDOB="";
	    isActualDob="";//if (age provided) isActualDob=0 else isActualDob=1
	    patIsUrban="";
	    patRefDoctor="";
	    patMonthlyIncome="";
	    patAmountCollected="";
	    patGuardianName="";
	    isReferred="";
	    patAgeUnit="";    
	    prevCrNo="";
		patIdNo="";
		patRegCatCode="";
		entryDate="";
		isUnknown="";
		seatId="1";
		stateRadio="";
		//Address Details corresponding to AddressVO
		patAddTypeCode="";
	    patAreaCategoryCode="";
	    patAddCityLoc="";
		patAddCityLocCode="";
	    patAddState="";
		patAddStateCode="";
	    patAddCountry="";
		patAddCountryCode="";
	    patAddHNo="";
	    patAddStreet="";
	    patAddDistrict="";
	    patAddDistrictCode="";
	    patAddCity="";
	    patAddPIN="";
		patAddContactNo="";
		isAddressDelhi="";
	    patContactNo="";
	    departmentdiv="";
		isCurrentAddress="";	
		referringInstType="";
		valid="";
		// Episode Detials Corresponding to EpisodeVO
		episodeType="";
		//Array of departments to visitstamp
		departmentsToVisitStamp=null;
		newdepartmentsToVisitStamp=null;
		alreadyVisitedDept=null;
		
		removeDept="";
		newremoveDept="";
		patNationalityCode="";
		patRefGnctdHospitalCrno="";
		patRefGnctdHospitalDept="";
		patRefGnctdHospitalDeptUnit="";
		patHusbandName="";
		patMotherName="";
		patNationalId="";	
		//fileNo[]="";
		empIdChk="";
		stateName="";
		patAddStateName="";
		patOccupation="";
		patFatherOccupation="";
		patHusbandOccupation="";
		patNickName="";
		patCardNo="";
		patDataFromEmployeeTable="";
		isIdRequired="";
		patCatIdNo="";
		patAddDistrictCodeHidden="";
		patAddPINHidden="";
		patAddCityHidden="";
		patIsUrbanHidden="";
		patRefHospitalDeptOther="";
		isDuplicatePatientPopup="";
		//errorMessage="";
		isOldPatient="";
		oldPatCrNo="";
	}
	
	
	/*public String getPatCrNo() {
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
	}*/
	public String getBeforeModificationAge() {
		return beforeModificationAge;
	}
	
	public void setBeforeModificationAge(String beforeModificationAge) {
		this.beforeModificationAge = beforeModificationAge;
	}

	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}

	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}

	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getPatSecondaryCat() {
		return patSecondaryCat;
	}

	public void setPatSecondaryCat(String patSecondaryCat) {
		this.patSecondaryCat = patSecondaryCat;
	}

	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}

	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	public String getPatMaritalStatusCode() {
		return patMaritalStatusCode;
	}

	public void setPatMaritalStatusCode(String patMaritalStatusCode) {
		this.patMaritalStatusCode = patMaritalStatusCode;
	}

	public String getPatReligionCode() {
		return patReligionCode;
	}

	public void setPatReligionCode(String patReligionCode) {
		this.patReligionCode = patReligionCode;
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

	public String getPatFirstName() {
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}

	public String getReferringInst() {
		return referringInst;
	}

	public void setReferringInst(String referringInst) {
		this.referringInst = referringInst;
	}

	public String getPatMiddleName() {
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}

	public String getPatMomName() {
		return patMomName;
	}

	public void setPatMomName(String patMomName) {
		this.patMomName = patMomName;
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

	public String getPatGenderCode() {
		return patGenderCode;
	}

	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getPatDOB() {
		return patDOB;
	}

	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}

	public String getIsActualDob() {
		return isActualDob;
	}

	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}

	public String getPatIsUrban() {
		return patIsUrban;
	}

	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}

	public String getPatRefDoctor() {
		return patRefDoctor;
	}

	public void setPatRefDoctor(String patRefDoctor) {
		this.patRefDoctor = patRefDoctor;
	}

	public String getPatMonthlyIncome() {
		return patMonthlyIncome;
	}

	public void setPatMonthlyIncome(String patMonthlyIncome) {
		this.patMonthlyIncome = patMonthlyIncome;
	}

	public String getPatAmountCollected() {
		return patAmountCollected;
	}

	public void setPatAmountCollected(String patAmountCollected) {
		this.patAmountCollected = patAmountCollected;
	}

	public String getPatGuardianName() {
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}

	public String getIsReferred() {
		return isReferred;
	}

	public void setIsReferred(String isReferred) {
		this.isReferred = isReferred;
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

	public String getPatIdNo() {
		return patIdNo;
	}

	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}

	public String getPatRegCatCode() {
		return patRegCatCode;
	}

	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getIsUnknown() {
		return isUnknown;
	}

	public void setIsUnknown(String isUnknown) {
		this.isUnknown = isUnknown;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getStateRadio() {
		return stateRadio;
	}

	public void setStateRadio(String stateRadio) {
		this.stateRadio = stateRadio;
	}

	public String getPatAddTypeCode() {
		return patAddTypeCode;
	}

	public void setPatAddTypeCode(String patAddTypeCode) {
		this.patAddTypeCode = patAddTypeCode;
	}

	public String getPatAreaCategoryCode() {
		return patAreaCategoryCode;
	}

	public void setPatAreaCategoryCode(String patAreaCategoryCode) {
		this.patAreaCategoryCode = patAreaCategoryCode;
	}

	public String getPatAddCityLoc() {
		return patAddCityLoc;
	}

	public void setPatAddCityLoc(String patAddCityLoc) {
		this.patAddCityLoc = patAddCityLoc;
	}

	public String getPatAddCityLocCode() {
		return patAddCityLocCode;
	}

	public void setPatAddCityLocCode(String patAddCityLocCode) {
		this.patAddCityLocCode = patAddCityLocCode;
	}

	public String getPatAddState() {
		return patAddState;
	}

	public void setPatAddState(String patAddState) {
		this.patAddState = patAddState;
	}

	public String getPatAddStateCode() {
		return patAddStateCode;
	}

	public void setPatAddStateCode(String patAddStateCode) {
		this.patAddStateCode = patAddStateCode;
	}

	public String getPatAddCountry() {
		return patAddCountry;
	}

	public void setPatAddCountry(String patAddCountry) {
		this.patAddCountry = patAddCountry;
	}

	public String getPatAddCountryCode() {
		return patAddCountryCode;
	}

	public void setPatAddCountryCode(String patAddCountryCode) {
		this.patAddCountryCode = patAddCountryCode;
	}

	public String getPatAddHNo() {
		return patAddHNo;
	}

	public void setPatAddHNo(String patAddHNo) {
		this.patAddHNo = patAddHNo;
	}

	public String getPatAddStreet() {
		return patAddStreet;
	}

	public void setPatAddStreet(String patAddStreet) {
		this.patAddStreet = patAddStreet;
	}

	public String getPatAddDistrict() {
		return patAddDistrict;
	}

	public void setPatAddDistrict(String patAddDistrict) {
		this.patAddDistrict = patAddDistrict;
	}

	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}

	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
	}

	public String getPatAddCity() {
		return patAddCity;
	}

	public void setPatAddCity(String patAddCity) {
		this.patAddCity = patAddCity;
	}

	public String getPatAddPIN() {
		return patAddPIN;
	}

	public void setPatAddPIN(String patAddPIN) {
		this.patAddPIN = patAddPIN;
	}

	public String getPatAddContactNo() {
		return patAddContactNo;
	}

	public void setPatAddContactNo(String patAddContactNo) {
		this.patAddContactNo = patAddContactNo;
	}

	public String getIsAddressDelhi() {
		return isAddressDelhi;
	}

	public void setIsAddressDelhi(String isAddressDelhi) {
		this.isAddressDelhi = isAddressDelhi;
	}

	public String getPatContactNo() {
		return patContactNo;
	}

	public void setPatContactNo(String patContactNo) {
		this.patContactNo = patContactNo;
	}

	public String getDepartmentdiv() {
		return departmentdiv;
	}

	public void setDepartmentdiv(String departmentdiv) {
		this.departmentdiv = departmentdiv;
	}

	public String getIsCurrentAddress() {
		return isCurrentAddress;
	}

	public void setIsCurrentAddress(String isCurrentAddress) {
		this.isCurrentAddress = isCurrentAddress;
	}

	public String getReferringInstType() {
		return referringInstType;
	}

	public void setReferringInstType(String referringInstType) {
		this.referringInstType = referringInstType;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getEpisodeType() {
		return episodeType;
	}

	public void setEpisodeType(String episodeType) {
		this.episodeType = episodeType;
	}

	public String[] getDepartmentsToVisitStamp() {
		return departmentsToVisitStamp;
	}

	public void setDepartmentsToVisitStamp(String[] departmentsToVisitStamp) {
		this.departmentsToVisitStamp = departmentsToVisitStamp;
	}

	public String[] getNewdepartmentsToVisitStamp() {
		return newdepartmentsToVisitStamp;
	}

	public void setNewdepartmentsToVisitStamp(String[] newdepartmentsToVisitStamp) {
		this.newdepartmentsToVisitStamp = newdepartmentsToVisitStamp;
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

	public String getNewremoveDept() {
		return newremoveDept;
	}

	public void setNewremoveDept(String newremoveDept) {
		this.newremoveDept = newremoveDept;
	}

	public String getPatNationalityCode() {
		return patNationalityCode;
	}

	public void setPatNationalityCode(String patNationalityCode) {
		this.patNationalityCode = patNationalityCode;
	}

	public String getPatRefGnctdHospitalCrno() {
		return patRefGnctdHospitalCrno;
	}

	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno) {
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}

	public String getPatRefGnctdHospitalDept() {
		return patRefGnctdHospitalDept;
	}

	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept) {
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}

	public String getPatRefGnctdHospitalDeptUnit() {
		return patRefGnctdHospitalDeptUnit;
	}

	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit) {
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	public String getPatHusbandName() {
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}

	public String getPatMotherName() {
		return patMotherName;
	}

	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}

	public String getPatNationalId() {
		return patNationalId;
	}

	public void setPatNationalId(String patNationalId) {
		this.patNationalId = patNationalId;
	}

	public String[] getFileNo() {
		return fileNo;
	}

	public void setFileNo(String[] fileNo) {
		this.fileNo = fileNo;
	}

	public String getEmpIdChk() {
		return empIdChk;
	}

	public void setEmpIdChk(String empIdChk) {
		this.empIdChk = empIdChk;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getPatAddStateName() {
		return patAddStateName;
	}

	public void setPatAddStateName(String patAddStateName) {
		this.patAddStateName = patAddStateName;
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

	public String getPatNickName() {
		return patNickName;
	}

	public void setPatNickName(String patNickName) {
		this.patNickName = patNickName;
	}

	public String getPatCardNo() {
		return patCardNo;
	}

	public void setPatCardNo(String patCardNo) {
		this.patCardNo = patCardNo;
	}

	public String getPatDataFromEmployeeTable() {
		return patDataFromEmployeeTable;
	}

	public void setPatDataFromEmployeeTable(String patDataFromEmployeeTable) {
		this.patDataFromEmployeeTable = patDataFromEmployeeTable;
	}

	public String getIsIdRequired() {
		return isIdRequired;
	}

	public void setIsIdRequired(String isIdRequired) {
		this.isIdRequired = isIdRequired;
	}

	public String getPatCatIdNo() {
		return patCatIdNo;
	}

	public void setPatCatIdNo(String patCatIdNo) {
		this.patCatIdNo = patCatIdNo;
	}

	public String getPatAddDistrictCodeHidden() {
		return patAddDistrictCodeHidden;
	}

	public void setPatAddDistrictCodeHidden(String patAddDistrictCodeHidden) {
		this.patAddDistrictCodeHidden = patAddDistrictCodeHidden;
	}

	public String getPatAddPINHidden() {
		return patAddPINHidden;
	}

	public void setPatAddPINHidden(String patAddPINHidden) {
		this.patAddPINHidden = patAddPINHidden;
	}

	public String getPatAddCityHidden() {
		return patAddCityHidden;
	}

	public void setPatAddCityHidden(String patAddCityHidden) {
		this.patAddCityHidden = patAddCityHidden;
	}

	public String getPatIsUrbanHidden() {
		return patIsUrbanHidden;
	}

	public void setPatIsUrbanHidden(String patIsUrbanHidden) {
		this.patIsUrbanHidden = patIsUrbanHidden;
	}

	public String getPatRefHospitalDeptOther() {
		return patRefHospitalDeptOther;
	}

	public void setPatRefHospitalDeptOther(String patRefHospitalDeptOther) {
		this.patRefHospitalDeptOther = patRefHospitalDeptOther;
	}

	public String getIsDuplicatePatientPopup() {
		return isDuplicatePatientPopup;
	}

	public void setIsDuplicatePatientPopup(String isDuplicatePatientPopup) {
		this.isDuplicatePatientPopup = isDuplicatePatientPopup;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getIsOldPatient() {
		return isOldPatient;
	}

	public void setIsOldPatient(String isOldPatient) {
		this.isOldPatient = isOldPatient;
	}

	public String getOldPatCrNo() {
		return oldPatCrNo;
	}

	public void setOldPatCrNo(String oldPatCrNo) {
		this.oldPatCrNo = oldPatCrNo;
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
	/*Modify Date			: 24thNov'14
	  Reason	(CR/PRS)	: Secondary UHID check incorporation
	  Modify By				: Sheeldarshi */
	public String getPatCatDocCode() {
		return patCatDocCode;
	}
	
	public void setPatCatDocCode(String patCatDocCode) {
		this.patCatDocCode = patCatDocCode;
	}

	
	public String getPatCatDocIsAlternateId() {
		return patCatDocIsAlternateId;
	}

	public void setPatCatDocIsAlternateId(String patCatDocIsAlternateId) {
		this.patCatDocIsAlternateId = patCatDocIsAlternateId;
	}

	public String getAsNewPatient() {
		return asNewPatient;
	}

	public void setAsNewPatient(String asNewPatient) {
		this.asNewPatient = asNewPatient;
	}
	public String getisDuplicatePatient() {
		return isDuplicatePatient;
	}

	public void setisDuplicatePatient(String isDuplicatePatient) {
		this.isDuplicatePatient = isDuplicatePatient;
	}
	
	//END:Sheeldarshi
}
