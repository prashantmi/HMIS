package registration.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import registration.config.CharacterEncoding;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class ExternalPatientRegistrationSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;

	/*protected String patCrNo;
	protected String clientName;
	protected String mlcNo;
	protected String configFlag;*/
	
	protected String patPrimaryCat;
	protected String patPrimaryCatCode;
	protected String patPrimaryCatGrp; 
    protected String patSecondaryCat;
	protected String patSecondaryCatCode;
    protected String patMaritalStatusCode;
    protected String patReligionCode;
    protected String patMaritalStatus;
    protected String patReligion;
    protected String departmentCode;
    protected String departmentUnitCode;
    protected String roomCode;  
    protected String roundRobinUnitFlag;
    protected String roundRobinRoomFlag;
	protected String hmode;
	protected String patRefGnctdHospitalCode;
	protected String patRefHospitalName;//hospital name in case reffered from some other hospital
    protected String patFirstName;
    protected String patMiddleName;
    protected String patLastName;
    protected String patFirstNameInMultiLang;
    protected String patMiddleNameInMultiLang;
    protected String patLastNameInMultiLang;
    protected String referringInst;
    protected String patMomName;
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
	
	protected String patCatCardNo;
	protected String patAddLandMarks;
	protected String patAddEmailId;
	protected String patAddPhoneNo;
	protected String patAddPhoneOwner;
	protected String patAddMobileNo;
	
	protected String patAadharNo;
	protected String patVisitReason;
	
	protected String patCasteCode;
	protected String patCaste;
	protected String patDocType;
	protected String patDocTypeName;
	protected String patBirthPlace;
	protected String patCatShortName;
	protected String hiddenPatIdNo;
	protected String alreadyRegisteredFlag;
	protected String hiddenCatOrRegstrdPopupFlag;
	protected String strAreadyRegisteredFlag;
	
	protected String creditBillFlag;
	protected String creditLetterRefNo;
	protected String creditLetterDate;
	
	protected String patCatDocCode;
	protected String patCatDocIsAlternateId;
	
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
	protected String asNewPatient;
	
	protected String isUnitWiseRegistration;
	protected String seniorCitizenAgeLimit;
	
	protected String callerName;

	
	
	public String getIsUnitWiseRegistration() {
		return isUnitWiseRegistration;
	}

	public void setIsUnitWiseRegistration(String isUnitWiseRegistration) {
		this.isUnitWiseRegistration = isUnitWiseRegistration;
	}

	public String getSeniorCitizenAgeLimit() {
		return seniorCitizenAgeLimit;
	}

	public void setSeniorCitizenAgeLimit(String seniorCitizenAgeLimit) {
		this.seniorCitizenAgeLimit = seniorCitizenAgeLimit;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public ExternalPatientRegistrationSUP()
	{
		patPrimaryCat="";
		patPrimaryCatCode="";
	    patSecondaryCat="";
		patSecondaryCatCode="";
	    patMaritalStatusCode="";
	    patReligionCode="";
	    departmentCode="";
	    departmentUnitCode="";
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
	    patAddPhoneNo="";
	    patAddMobileNo="";
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
		patCatCardNo="";
		patAddLandMarks="";
		patAddEmailId="";
		patAddPhoneNo="";
		patAddPhoneOwner="0";
		patAddMobileNo="";
		patAadharNo="";
		patVisitReason="";
		
		clientCode="";
		clientName="";
		staffCardNo="";
		staffCardName="";
		relationWithStaff="";
		cardvalidityDate="";
		relationNameWithStaff="";
		agsDistrictCode="";
		agsCounterNo="";
		agsNo="";
		patActualAmount="";
		asNewPatient="";
	}
	
	public void clear()
	{
		
		
		patPrimaryCat="";
		patPrimaryCatCode="";
	    patMaritalStatusCode="-1";
	    patReligionCode="-1";
	    departmentCode="-1";
		patRefGnctdHospitalCode="-1";
		patRefHospitalName="";//hospital name in case reffered from some other hospital
	    patFirstName="";
	    patMiddleName="";
	    patMomName="";
	    patLastName="";
	    patGender="";
		patGenderCode="-1";
	    patAge="";
	    patDOB="";
	    patGuardianName="";
	    patHusbandName="";
	    patMotherName="";
	    patNationalId="";	
	    isActualDob="0";//if (age provided) isActualDob=0 else isActualDob=1
	    patIsUrban="";
	    patMonthlyIncome="";
	    patAmountCollected="";
	    patAgeUnit="-1";    
	    patCaste="";
	    patCasteCode="-1";
	    
	    prevCrNo="";
		patIdNo="-1";
		entryDate="";
		
		
		//Address Details corresponding to AddressVO
		//patAddTypeCode="";
	    //patAreaCategoryCode="";
		patAddCountry="";
		patAddCountryCode="";
		patAddState="";
		patAddStateCode="";
		patAddDistrict="";
		patAddDistrictCode="";
		patAddHNo="";
		patAddStreet="";
	    patAddCity="";
	    patAddPIN="";
	    patAddPhoneNo="";
	    patAddMobileNo="";
	    patAddLandMarks="";
		patAddEmailId="";
		patAddPhoneNo="";
		patAddPhoneOwner="0";
		patAddMobileNo="";
		patCatCardNo="";
		patAadharNo="";
		
		patVisitReason="";
		
		referringInstType="G";
		patRefGnctdHospitalCode="-1";
		patRefGnctdHospitalCrno="";
		patRefDoctor="";
		patRefGnctdHospitalDept="-1";
		patRefHospitalDeptOther="";
		patRefGnctdHospitalDeptUnit="";
		isReferred="0";
		
		clientCode="";
		clientName="";
		staffCardNo="";
		staffCardName="";
		relationWithStaff="";
		cardvalidityDate="";
		relationNameWithStaff="";
		agsDistrictCode="";
		agsCounterNo="";
		agsNo="";
		patActualAmount="";
		asNewPatient="";
		
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

	public String getPatPrimaryCatGrp() {
		return patPrimaryCatGrp;
	}

	public void setPatPrimaryCatGrp(String patPrimaryCatGrp) {
		this.patPrimaryCatGrp = patPrimaryCatGrp;
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

	public String getPatMaritalStatus() {
		return patMaritalStatus;
	}

	public void setPatMaritalStatus(String patMaritalStatus) {
		this.patMaritalStatus = patMaritalStatus;
	}

	public String getPatReligion() {
		return patReligion;
	}

	public void setPatReligion(String patReligion) {
		this.patReligion = patReligion;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoundRobinUnitFlag() {
		return roundRobinUnitFlag;
	}

	public void setRoundRobinUnitFlag(String roundRobinUnitFlag) {
		this.roundRobinUnitFlag = roundRobinUnitFlag;
	}

	public String getRoundRobinRoomFlag() {
		return roundRobinRoomFlag;
	}

	public void setRoundRobinRoomFlag(String roundRobinRoomFlag) {
		this.roundRobinRoomFlag = roundRobinRoomFlag;
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

	public String getReferringInst() {
		return referringInst;
	}

	public void setReferringInst(String referringInst) {
		this.referringInst = referringInst;
	}

	public String getPatMomName() {
		return patMomName;
	}

	public void setPatMomName(String patMomName) {
		this.patMomName = patMomName;
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

	public String getPatAddPhoneNo() {
		return patAddPhoneNo;
	}

	public void setPatAddPhoneNo(String patAddPhoneNo) {
		this.patAddPhoneNo = patAddPhoneNo;
	}

	public String getPatAddMobileNo() {
		return patAddMobileNo;
	}

	public void setPatAddMobileNo(String patAddMobileNo) {
		this.patAddMobileNo = patAddMobileNo;
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

	public String getPatCatCardNo() {
		return patCatCardNo;
	}

	public void setPatCatCardNo(String patCatCardNo) {
		this.patCatCardNo = patCatCardNo;
	}

	public String getPatAddLandMarks() {
		return patAddLandMarks;
	}

	public void setPatAddLandMarks(String patAddLandMarks) {
		this.patAddLandMarks = patAddLandMarks;
	}

	public String getPatAddEmailId() {
		return patAddEmailId;
	}

	public void setPatAddEmailId(String patAddEmailId) {
		this.patAddEmailId = patAddEmailId;
	}

	public String getPatAddPhoneOwner() {
		return patAddPhoneOwner;
	}

	public void setPatAddPhoneOwner(String patAddPhoneOwner) {
		this.patAddPhoneOwner = patAddPhoneOwner;
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

	public String getPatAadharNo() {
		return patAadharNo;
	}

	public void setPatAadharNo(String patAadharNo) {
		this.patAadharNo = patAadharNo;
	}

	public String getPatVisitReason() {
		return patVisitReason;
	}

	public void setPatVisitReason(String patVisitReason) {
		this.patVisitReason = patVisitReason;
	}

	public String getPatCasteCode() {
		return patCasteCode;
	}

	public void setPatCasteCode(String patCasteCode) {
		this.patCasteCode = patCasteCode;
	}

	public String getPatCaste() {
		return patCaste;
	}

	public void setPatCaste(String patCaste) {
		this.patCaste = patCaste;
	}

	public String getPatDocType() {
		return patDocType;
	}

	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}

	public String getPatDocTypeName() {
		return patDocTypeName;
	}

	public void setPatDocTypeName(String patDocTypeName) {
		this.patDocTypeName = patDocTypeName;
	}

	public String getPatBirthPlace() {
		return patBirthPlace;
	}

	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}

	public String getPatCatShortName() {
		return patCatShortName;
	}

	public void setPatCatShortName(String patCatShortName) {
		this.patCatShortName = patCatShortName;
	}

	public String getHiddenPatIdNo() {
		return hiddenPatIdNo;
	}

	public void setHiddenPatIdNo(String hiddenPatIdNo) {
		this.hiddenPatIdNo = hiddenPatIdNo;
	}

	public String getAlreadyRegisteredFlag() {
		return alreadyRegisteredFlag;
	}

	public void setAlreadyRegisteredFlag(String alreadyRegisteredFlag) {
		this.alreadyRegisteredFlag = alreadyRegisteredFlag;
	}

	public String getHiddenCatOrRegstrdPopupFlag() {
		return hiddenCatOrRegstrdPopupFlag;
	}

	public void setHiddenCatOrRegstrdPopupFlag(String hiddenCatOrRegstrdPopupFlag) {
		this.hiddenCatOrRegstrdPopupFlag = hiddenCatOrRegstrdPopupFlag;
	}

	public String getStrAreadyRegisteredFlag() {
		return strAreadyRegisteredFlag;
	}

	public void setStrAreadyRegisteredFlag(String strAreadyRegisteredFlag) {
		this.strAreadyRegisteredFlag = strAreadyRegisteredFlag;
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
		CharacterEncoding.setCharacterEncoding(this.objRequest);
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
	
	public void prepare() throws Exception {
		
		
	}

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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPatActualAmount() {
		return patActualAmount;
	}

	public void setPatActualAmount(String patActualAmount) {
		this.patActualAmount = patActualAmount;
	}

	public String getAsNewPatient() {
		return asNewPatient;
	}

	public void setAsNewPatient(String asNewPatient) {
		this.asNewPatient = asNewPatient;
	}
	
}
