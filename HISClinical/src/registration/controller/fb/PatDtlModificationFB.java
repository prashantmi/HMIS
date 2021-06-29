package registration.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;


import registration.RegistrationConfig;

public class PatDtlModificationFB extends CRNoFB {

	
	private String patAgeSelection;
	private String patAddCityLocName;
	private String patNationalityCode;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
    private String patSecondaryCat;
	private String patSecondaryCatCode;
    private String patMaritalStatusCode;
    private String patReligionCode;
    private String departmentCode;
	private String hmode;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;//hospital name in case reffered from some other hospital
    private String patFirstName;
    private String referringInst;
    private String patMiddleName;
    private String patLastName;
    private String patGender;
	private String patGenderCode;
    private String patAge;
    private String patDOB;
    private String isActualDob;//if (age provided) isActualDob=0 else isActualDob=1
    private String pat;
    private String patRefDoctor;
    private String patIsUrban;
    private String patMonthlyIncome;
    private String patAmountCollected;
    private String patGuardianName;
    private String isReferred;
    private String patAgeUnit;    
    private String prevCrNo;
	private String patIdNo;
	//private String patRegCatCode;
	//private String entryDate;
	//private String isUnknown;
	private String seatId="1";
	private String stateRadio;
	//Address Details corresponding to AddressVO
	private String patAddTypeCode;
    private String patAreaCategoryCode;
    private String patAddCityLoc;
	private String patAddCityLocCode;
    private String patAddState;
	private String patAddStateCode;
    private String patAddCountry;
	private String patAddCountryCode;
    private String patAddHNo;
    private String patAddStreet;
    private String patAddDistrict;
    private String patAddDistrictCode;
    private String patAddCity;
    private String patAddPIN;
	private String patAddContactNo;
	private String isAddressDelhi;
    private String patContactNo;
    private String departmentdiv;
	private String isCurrentAddress;	
	private String referringInstType;
	private String valid;
	// Episode Detials Corresponding to EpisodeVO
	private String episodeType;
	//Array of departments to visitstamp
	private String[] departmentsToVisitStamp;
	private String[] newdepartmentsToVisitStamp;
	private String[] alreadyVisitedDept;
	//private String[] 
	private String removeDept;
	private String newremoveDept;
	private String crNoToModify;
	private String patRefGnctdHospitalCrno;
	private String patRefGnctdHospitalDept;
	private String patRefGnctdHospitalDeptUnit;
	private String getCrNoToRetrieve;
	
	private String patHusbandName;
	private String patMomName;
	private String patNationalId;	
	private String patMotherName;
	private String department;
	private String patAddStateName;
	private String patOccupation;
	private String patFatherOccupation;
	private String patHusbandOccupation;
	private String patNickName;
	private String patCardNo;
	private String patAddDistrictCodeHidden;
	private String patAddPINHidden;
	private String patAddCityHidden;
	private String patIsUrbanHidden;
	private String patRefHospitalDeptOther="";
	
	//new fields added for arogya online.
	
	private String familyHeadName;
	private String strPatAddress;
	private String strPatAddressTehsil;
	private String patCasteCategoryCode;
	private String patRelationCode;
	
	
	public String getFamilyHeadName() {
		return familyHeadName;
	}


	public void setFamilyHeadName(String familyHeadName) {
		this.familyHeadName = familyHeadName;
	}


	public String getStrPatAddress() {
		return strPatAddress;
	}


	public void setStrPatAddress(String strPatAddress) {
		this.strPatAddress = strPatAddress;
	}


	public String getStrPatAddressTehsil() {
		return strPatAddressTehsil;
	}


	public void setStrPatAddressTehsil(String strPatAddressTehsil) {
		this.strPatAddressTehsil = strPatAddressTehsil;
	}


	public String getPatCasteCategoryCode() {
		return patCasteCategoryCode;
	}


	public void setPatCasteCategoryCode(String patCasteCategoryCode) {
		this.patCasteCategoryCode = patCasteCategoryCode;
	}


	public String getPatRelationCode() {
		return patRelationCode;
	}


	public void setPatRelationCode(String patRelationCode) {
		this.patRelationCode = patRelationCode;
	}


	public String getPatRefHospitalDeptOther() {
		return patRefHospitalDeptOther;
	}


	public void setPatRefHospitalDeptOther(String patRefHospitalDeptOther) {
		this.patRefHospitalDeptOther = patRefHospitalDeptOther;
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


	public String getPatCardNo() {
		return patCardNo;
	}


	public void setPatCardNo(String patCardNo) {
		this.patCardNo = patCardNo;
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


	public String getPatOccupation() {
		return patOccupation;
	}


	public void setPatOccupation(String patOccupation) {
		this.patOccupation = patOccupation;
	}


	public String getPatAddStateName() {
		return patAddStateName;
	}


	public void setPatAddStateName(String patAddStateName) {
		this.patAddStateName = patAddStateName;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
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


	public String getPatMomName() {
		return patMomName;
	}


	public void setPatMomName(String patMomName) {
		this.patMomName = patMomName;
	}


	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}


	

	public String getPatNationalId() {
		return patNationalId;
	}


	public void setPatNationalId(String patNationalID) {
		this.patNationalId = patNationalID;
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

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("            ");
		this.setPatPrimaryCatCode("");
		this.setPatSecondaryCatCode("");
		this.setPatMaritalStatusCode("");
		this.setPatReligionCode("");
		this.setPatRefGnctdHospitalCode("");		
		this.setPatRefHospitalName("");
		this.setPatFirstName("");
		this.setPatMiddleName("");
		this.setPatLastName("");
		this.setPatGenderCode("");
		this.setPatAge("");
		this.setPatDOB("");
		this.setIsActualDob(""); 
		this.setPat("");
		this.setPatRefDoctor("");
		this.setPatMonthlyIncome("");
		this.setPatAmountCollected("");
		this.setPatGuardianName("");
		this.setIsReferred("");
		this.setPrevCrNo("");
		this.setPatIdNo("");
		this.setPatRefGnctdHospitalCrno("");
		this.setPatRefGnctdHospitalDept("");
		this.setPatRefGnctdHospitalDeptUnit("");
		//this.setPatRegCatCode("");
		//this.setEntryDate("");
		//this.setIsUnknown("");
		this.setSeatId("");
		this.setPatAddTypeCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_ADDRESS_TYPE_CODE);
		this.setPatAddCityLoc("");
		this.setPatAddCityLocCode("");
		this.setPatAddState("");
		this.setPatAddStateCode("");
		this.setPatAddCountry("");
		this.setPatAddCountryCode("");
		this.setPatAddHNo("");
		this.setPatAddStreet("");
		this.setPatAddDistrict("");
		this.setPatAddCity("");
		this.setPatAddPIN("");
		this.setPatAddContactNo("");
		this.setIsAddressDelhi("");
		this.setPatContactNo("");
		this.setIsCurrentAddress("1");
		this.setEpisodeType("");
		this.setDepartmentsToVisitStamp(new String[]{});
		this.setRemoveDept("");
		this.setPatAreaCategoryCode("");
		this.setHmode("");
		this.setDepartmentdiv("");
		//this.setStateRadio("0");
		this.setPatRefDoctor("");
		this.setPatMomName("");
		this.setPatNationalId("");
		this.setPatHusbandName("");
		this.setPatMotherName("");
		this.setDepartment("");
		this.setPatAddStateName("");
		this.setPatOccupation("");
		this.setPatFatherOccupation("");
		this.setPatHusbandOccupation("");
		this.setPatNickName("");
		this.setPatCardNo("");
		this.familyHeadName="";
		this.strPatAddress="";
		this.strPatAddressTehsil="";
		this.patCasteCategoryCode="-1";
		this.patRelationCode="-1";
		this.patRelationCode="";
	}	

	


	/*public String getDatePicker(String fieldName)	
	{		
	String dateString = new String("");		
	dateString = "	<input type=\"text\" name=\""+fieldName+"\" id=\"f_date_c\" > "+
	     		 "	<img src=\"../../hisglobal/images/iconPicDate.gif\" id=\"f_trigger_c\" style=\"cursor: pointer; border: 1px solid red;\"  "+					
                 "	title=\"Date selector\"  "+
	    		 "	 onmouseover=\"this.style.background='red';\"  "+
	          	 "	onmouseout=\"this.style.background=''\"> "+					
     	         "	 <script language=\"JavaScript\" src=\"../hisglobal/js/dateSetup.js\"></script> ";
		return dateString;	
	}*/
	
	public String getRemoveDept() {
		return removeDept;
	}


	public void setRemoveDept(String removeDept) {
		this.removeDept = removeDept;
	}
	
	public String[] getDepartmentsToVisitStamp() {
		return departmentsToVisitStamp;
	}

	public void setDepartmentsToVisitStamp(String[] departmentsToVisitStamp) {
		this.departmentsToVisitStamp = departmentsToVisitStamp;
	}

	
	public String getEpisodeType() {
		return episodeType;
	}

	public void setEpisodeType(String episodeType) {
		this.episodeType = episodeType;
	}

	public String getIsActualDob() {
		return isActualDob;
	}

	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}

	public String getIsAddressDelhi() {
		return isAddressDelhi;
	}

	public void setIsAddressDelhi(String isAddressDelhi) {
		this.isAddressDelhi = isAddressDelhi;
	}

	public String getIsCurrentAddress() {
		return isCurrentAddress;
	}

	public void setIsCurrentAddress(String isCurrentAddress) {
		this.isCurrentAddress = isCurrentAddress;
	}

	public String getIsReferred() {
		return isReferred;
	}

	public void setIsReferred(String isReferred) {
		this.isReferred = isReferred;
	}

	/*public String getIsUnknown() {
		return isUnknown;
	}

	public void setIsUnknown(String isUnknown) {
		this.isUnknown = isUnknown;
	}*/

	public String getPatAddCity() {
		return patAddCity;
	}

	public void setPatAddCity(String patAddCity) {
		this.patAddCity = patAddCity;
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
		System.out.println("setter value"+ patAddCityLocCode);
	}

	public String getPatAddContactNo() {
		return patAddContactNo;
	}

	public void setPatAddContactNo(String patAddContactNo) {
		this.patAddContactNo = patAddContactNo;
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

	public String getPatAddDistrict() {
		return patAddDistrict;
	}

	public void setPatAddDistrict(String patAddDistrict) {
		this.patAddDistrict = patAddDistrict;
	}

	public String getPatAddHNo() {
		return patAddHNo;
	}

	public void setPatAddHNo(String patAddHNo) {
		this.patAddHNo = patAddHNo;
	}

	public String getPatAddPIN() {
		return patAddPIN;
	}

	public void setPatAddPIN(String patAddPIN) {
		this.patAddPIN = patAddPIN;
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

	public String getPatAddStreet() {
		return patAddStreet;
	}

	public void setPatAddStreet(String patAddStreet) {
		this.patAddStreet = patAddStreet;
	}

	public String getPatAddTypeCode() {
		return patAddTypeCode;
	}

	public void setPatAddTypeCode(String patAddTypeCode) {
		this.patAddTypeCode = patAddTypeCode;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getPatAmountCollected() {
		return patAmountCollected;
	}

	public void setPatAmountCollected(String patAmountCollected) {
		this.patAmountCollected = patAmountCollected;
	}

	public String getPatContactNo() {
		return patContactNo;
	}

	public void setPatContactNo(String patContactNo) {
		this.patContactNo = patContactNo;
	}

	

	public String getPatDOB() {
		return patDOB;
	}

	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}

	public String getPatFirstName() {
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
		
	}

	public String getPatGenderCode() {
		return patGenderCode;
	}

	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
		
	}

	public String getPatGuardianName() {
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}

	public String getPatIdNo() {
		return patIdNo;
	}

	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}

	public String getPat() {
		return pat;
	}

	public void setPat(String pat) {
		this.pat = pat;
	}

	public String getPatLastName() {
		return patLastName;
	}

	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}

	public String getPatMaritalStatusCode() {
		return patMaritalStatusCode;
	}

	public void setPatMaritalStatusCode(String patMaritalStatusCode) {
		this.patMaritalStatusCode = patMaritalStatusCode;
	}

	public String getPatMiddleName() {
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}

	public String getPatMonthlyIncome() {
		return patMonthlyIncome;
	}

	public void setPatMonthlyIncome(String patMonthlyIncome) {
		this.patMonthlyIncome = patMonthlyIncome;
	}

	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
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
	/*public String getPatRegCatCode() {
		return patRegCatCode;
	}

	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}*/

	public String getPatReligionCode() {
		return patReligionCode;
	}
	public void setPatReligionCode(String patReligionCode) {
		this.patReligionCode = patReligionCode;
	}
	public String getPatSecondaryCatCode() {
		return patSecondaryCatCode;
	}

	public void setPatSecondaryCatCode(String patSecondaryCatCode) {
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	public String getPrevCrNo() {
		return prevCrNo;
	}

	public void setPrevCrNo(String prevCrNo) {
		this.prevCrNo = prevCrNo;
	}

	
	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}


	public String getDepartmentCode() {
		return departmentCode;
	}


	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}


	public String getPatAreaCategoryCode() {
		return patAreaCategoryCode;
	}


	public void setPatAreaCategoryCode(String patAreaCategoryCode) {
		this.patAreaCategoryCode = patAreaCategoryCode;
	}


	public String getReferringInst() {
		return referringInst;
	}


	public void setReferringInst(String referringInst) {
		this.referringInst = referringInst;
	}


	public String getPatAgeUnit() {
		return patAgeUnit;
	}


	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getDepartmentdiv() {
		return departmentdiv;
	}


	public void setDepartmentdiv(String departmentdiv) {
		this.departmentdiv = departmentdiv;
	}


	public String getReferringInstType() {
		return referringInstType;
	}


	public void setReferringInstType(String referringInstType) {
		this.referringInstType = referringInstType;
	}


	public String getStateRadio() {
		return stateRadio;
	}


	public void setStateRadio(String stateRadio) {
		this.stateRadio = stateRadio;
	}


	public String getValid() {
		return valid;
	}


	public void setValid(String valid) {
		this.valid = valid;
	}


	public String getPatGender() {
		return patGender;
	}


	public void setPatGender(String patGender) {
		this.patGender = patGender;
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


	public String[] getAlreadyVisitedDept() {
		return alreadyVisitedDept;
	}


	public void setAlreadyVisitedDept(String[] alreadyVisitedDept) {
		this.alreadyVisitedDept = alreadyVisitedDept;
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


	public String getPatAddCityLocName() {
		return patAddCityLocName;
	}


	public void setPatAddCityLocName(String patAddCityLocName) {
		this.patAddCityLocName = patAddCityLocName;
	}


	public String getPatIsUrban() {
		return patIsUrban;
	}


	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}


	public String getPatAgeSelection() {
		return patAgeSelection;
	}


	public void setPatAgeSelection(String patAgeSelection) {
		this.patAgeSelection = patAgeSelection;
	}


	public String getCrNoToModify() {
		return crNoToModify;
	}


	public void setCrNoToModify(String crNoToModify) {
		this.crNoToModify = crNoToModify;
	}


	public String getPatNationalityCode() {
		return patNationalityCode;
	}


	public void setPatNationalityCode(String patNationalityCode) {
		this.patNationalityCode = patNationalityCode;
	}


	public String getCrNoToRetrieve() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getGetCrNoToRetrieve() {
		return getCrNoToRetrieve;
	}


	public void setGetCrNoToRetrieve(String getCrNoToRetrieve) {
		this.getCrNoToRetrieve = getCrNoToRetrieve;
	}


	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}


	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
	}


	

}

