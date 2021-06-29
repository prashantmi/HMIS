package registration.transactions.controller.fb;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionMapping;

import registration.config.RegistrationConfig;


public class newSplRegFB extends CRNoFB {
	
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
	    private String patMomName;
	    private String patLastName;
	    private String patGender;
		private String patGenderCode;
	    private String patAge;
	    private String patDOB;
	    private String patEducation;
		private String patEducationCode;
	    private String isActualDob;//if (age provided) isActualDob=0 else isActualDob=1
	    private String patIsUrban;
	    private String patRefDoctor;
	    private String patMonthlyIncome;
	    private String patAmountCollected;
	    private String patGuardianName;
	    private String isReferred;
	    private String patAgeUnit;    
	    private String prevCrNo;
		private String patIdNo;
		private String patRegCatCode;
		private String entryDate;
		private String isUnknown;
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
		private String patNationalityCode;
		private String patRefGnctdHospitalCrno;
		private String patRefGnctdHospitalDept;
		private String patRefGnctdHospitalDeptUnit;
		private String patHusbandName;
		private String patMotherName;
		private String patNationalId;	
		private String fileNo[];
		private String empIdChk;
		private String stateName;
		private String patAddStateName;
		private String patOccupation;
		private String patFatherOccupation;
		private String patHusbandOccupation;
		private String patNickName;
		private String patCardNo;
		private String patDataFromEmployeeTable;
		private String isIdRequired;
		private String patCatIdNo;
		private String patAddDistrictCodeHidden;
		private String patAddPINHidden;
		private String patAddCityHidden;
		private String patIsUrbanHidden;
		private String patRefHospitalDeptOther;
		private String isDuplicatePatientPopup;
		private String errorMessage;
		private String isOldPatient;
		private String oldPatCrNo;

		//new fields added for arogya online.
		
		private String familyHeadName;
		private String strPatAddress;
		private String strPatAddressTehsil;
		private String patCasteCategoryCode;
		private String bplRelationCode;
		private String patCasteCode="";
		private String patBPLCardNo="";
		private String memberName="";
		private String bplFamilyId="";
		private String bplStateId="";
		private String bplMMJRKNo="";
		private String bplDetailsFound="";
		
		
		//Checkng so as to make sure while saving duplicate record as a new patient, It wont check duplicacy again!
		private String isDuplicate="";
		private String print;
		private String patBirthPlace;
		private String patDocType;
		
		private String patAptId;
		private String patAptStatus;
		private String patAptNo;	
		private String patAptNoToRegister;		
		private String searchId;
		private String searchValue;
		private String patAptSlot;
		private String patAddEmailId;
		private String departmentUnitCode;
		private String department;
		private String patAptQueueNO;
		private String creditBillFlag;

		//Start:Sheeldarshi
		private String creditLetterRefNo;
		private String creditLetterDate;
		private String PatPrimaryCatGrp;
		private String clientCode;
		private String clientName;
		private String staffCardNo;
		private String staffCardName;
		private String relationWithStaff;
		private String cardvalidityDate;
		private String relationNameWithStaff;

		private String agsDistrictCode;
		private String agsCounterNo;
		private String agsNo;
		private String patActualAmount;
		private String localLanguage;
		
		private String patAddPhoneNo;
		private String patAddPhoneOwner;
		private String patVisitReason;
		private String patFirstNameInMultiLang;
		private String patMiddleNameInMultiLang;
		private String patLastNameInMultiLang;
		protected String patAddMobileNo;
		protected String asNewPatient;
		protected String patCatDocIsAlternateId;
		protected String isApprovalReq;
		protected String patRMOEmployee;
		private WebRowSet strResultWs = null;
		private String seniorCitizenAgeLimit;
		private String  seniorCitizenCatCode;
		//End:Sheeldarshi
		
		//added by Mukund on 25.07.2016
		private String creditLimit;
		private String letterType;
		private String agsCreditLimit;
		private String deptToSearchFrom;
		private String isAadharConsentGiven;
		private String patEmgCntNo;
		private String isBarcodeSlipPrint;
		private String roomCode;
		private String paymentModeRefId; //by warish 20-aug
		private String paymentModeCode;  //by warish 20-aug
		

	public String getDeptToSearchFrom() {
			return deptToSearchFrom;
		}


		public void setDeptToSearchFrom(String deptToSearchFrom) {
			this.deptToSearchFrom = deptToSearchFrom;
		}


	public String getCreditLimit() {
			return creditLimit;
		}


		public void setCreditLimit(String creditLimit) {
			this.creditLimit = creditLimit;
		}


		public String getLetterType() {
			return letterType;
		}


		public void setLetterType(String letterType) {
			this.letterType = letterType;
		}


		public String getAgsCreditLimit() {
			return agsCreditLimit;
		}


		public void setAgsCreditLimit(String agsCreditLimit) {
			this.agsCreditLimit = agsCreditLimit;
		}


	public String getIsDuplicate() {
			return isDuplicate;
		}


		public void setIsDuplicate(String isDuplicate) {
			this.isDuplicate = isDuplicate;
		}


	public String getBplDetailsFound() {
			return bplDetailsFound;
		}


		public void setBplDetailsFound(String bplDetailsFound) {
			this.bplDetailsFound = bplDetailsFound;
		}


	public String getPatCatIdNo() {
		return patCatIdNo;
	}


	public void setPatCatIdNo(String patCatIdNo) {
		this.patCatIdNo = patCatIdNo;
	}


	public String getIsIdRequired() {
		return isIdRequired;
	}


	public void setIsIdRequired(String isIdRequired) {
		this.isIdRequired = isIdRequired;
	}


	public String getPatDataFromEmployeeTable() {
		return patDataFromEmployeeTable;
	}


	public void setPatDataFromEmployeeTable(String patDataFromEmployeeTable) {
		this.patDataFromEmployeeTable = patDataFromEmployeeTable;
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


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public String getEmpIdChk() {
		return empIdChk;
	}


	public void setEmpIdChk(String empIdChk) {
		this.empIdChk = empIdChk;
	}


	public String getPatNationalId() {
		return patNationalId;
	}


	public void setPatNationalId(String patNationalId) {
		this.patNationalId = patNationalId;
	}


	public String getPatHusbandName() {
		return patHusbandName;
	}


	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
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


	public String getPatNationalityCode() {
		return patNationalityCode;
	}


	public void setPatNationalityCode(String patNationalityCode) {
		this.patNationalityCode = patNationalityCode;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("            ");
		this.setPatRefGnctdHospitalCrno("");
		this.setPatRefGnctdHospitalDept("");
		this.setPatRefGnctdHospitalDeptUnit("");
		
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
		this.setPatEducationCode("");
		this.setPatAge("");
		this.setPatAgeUnit("");
		this.setPatDOB("");
		this.setIsActualDob("0"); 
		this.setPatIsUrban("");
		this.setPatRefDoctor("");
		this.setPatMonthlyIncome("");
		this.setPatAmountCollected("");
		this.setPatGuardianName("");
		this.setIsReferred("0");
		this.setPrevCrNo("");
		this.setPatIdNo("");
		this.setPatRegCatCode("");
		this.setEntryDate("");
		this.setIsUnknown("");
		this.setSeatId("");
		this.setPatAddTypeCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_ADDRESS_TYPE_CODE);
		this.setPatAddCityLoc("");
		this.setPatAddCityLocCode("");
		this.setPatAddState("");
		//this.setPatAddStateCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
		this.setPatAddCountry("");
		this.setPatAddCountryCode("");
		this.setPatAddHNo("");
		this.setPatAddStreet("");
		this.setPatAddDistrict("");
		this.setPatAddCity("");
		this.setPatAddPIN("");
		this.setPatAddContactNo("");
		this.setIsAddressDelhi("1");
		this.setPatContactNo("");
		this.setIsCurrentAddress(RegistrationConfig.IS_ADDRESS_DELHI_TRUE);
		this.setEpisodeType("");
		this.setDepartmentsToVisitStamp(new String[]{});
		this.setRemoveDept("");
		this.setPatAreaCategoryCode("");
		this.setHmode("");
		this.setDepartmentdiv("");
		this.setStateRadio("0");
		this.setReferringInstType("G");
		//this.setDepartmentCode("");//retaing the default value of department
		this.setPatNationalityCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
		this.setFileNo(new String[]{});
		this.setPatMomName("");
		this.setPatHusbandName("");
		this.setPatNationalId("");
		this.setPatMotherName("");
		this.setEmpIdChk("");
		this.setStateName("");
		this.setPatAddStateName("");
		this.setPatNickName("");
		this.setPatOccupation("");
		this.setPatFatherOccupation("");
		this.setPatHusbandOccupation("");
		this.setPatCardNo("");
		this.setPatDataFromEmployeeTable("");
		this.setPatMotherName("");
		this.setIsIdRequired("");
		this.setPatCatIdNo("");
		this.setPatAddDistrictCode("");
		
		//by Mukund on 25.07.2016
		this.setCreditLimit("");
		this.setAgsCreditLimit("");
		this.letterType="-1";
		this.deptToSearchFrom="0";
		
		this.isDuplicatePatientPopup="0";
		this.familyHeadName="";
		this.strPatAddress="";
		this.strPatAddressTehsil="";
		this.patCasteCategoryCode="-1";
		this.bplRelationCode="-1";
		this.patBirthPlace="";
		this.patDocType="-1";
		
		this.searchId="1";
		this.searchValue="";
	}	

	public String getDatePicker(String fieldName)	
	{		
	String dateString = new String("");		
	dateString = "	<input type=\"text\" name=\""+fieldName+"\" id=\"f_date_c\" > "+
	     		 "	<img src=\"../../hisglobal/images/iconPicDate.gif\" id=\"f_trigger_c\" style=\"cursor: pointer; border: 1px solid red;\"  "+					
                 "	title=\"Date selector\"  "+
	    		 "	 onmouseover=\"this.style.background='red';\"  "+
	          	 "	onmouseout=\"this.style.background=''\"> "+					
     	         "	 <script language=\"JavaScript\" src=\"../hisglobal/js/dateSetup.js\"></script> ";
		return dateString;	
	}
	
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

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
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

	public String getIsUnknown() {
		return isUnknown;
	}

	public void setIsUnknown(String isUnknown) {
		this.isUnknown = isUnknown;
	}

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

	public String getPatEducation() {
		return patEducation;
	}


	public void setPatEducation(String patEducation) {
		this.patEducation = patEducation;
	}

	public String getPatEducationCode() {
		return patEducationCode;
	}


	public void setPatEducationCode(String patEducationCode) {
		this.patEducationCode = patEducationCode;
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

	public String getPatIsUrban() {
		return patIsUrban;
	}

	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
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

	public String getPatRegCatCode() {
		return patRegCatCode;
	}

	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}

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

	public void setRefHospCrNo3( java.lang.String crNo3 )
	{	//size 7 <== last seven digits
		char[] arrCrNo;
		char[] arrCrNo3;
		if(this.patCrNo==null || this.patCrNo.length() < 13)
			arrCrNo = new char[13];
		else	
			arrCrNo = this.patCrNo.toCharArray();
		
		arrCrNo3 = crNo3.toCharArray();
		
		//for(int j=arrCrNo)
		for(int i=0; i<8&&arrCrNo3.length==8;i++)
			arrCrNo[5+i]=arrCrNo3[i];		
		
		this.patCrNo = new String(arrCrNo);
	}

	public java.lang.String getRefHospCrNo3( )
	{
		char[] arrCrNo;	
		char[] arrCrNo3 = new char[8];
		if(this.patCrNo==null || this.patCrNo.length() < 13)
			arrCrNo = new char[13];
		else	
			arrCrNo = this.patCrNo.toCharArray();
		
		for(int i=5; i<13;i++)
			arrCrNo3[i-5]=arrCrNo[i];	
		
		return (new String(arrCrNo3)).trim();
	}

	public void setRefHospCrNo2( java.lang.String crNo2 )
	{	//size 7 <== last seven digits
		char[] arrCrNo;
		char[] arrCrNo2;
		if(this.patCrNo==null || this.patCrNo.length() < 13)
			{arrCrNo = new char[13];
			}
		else	
			arrCrNo = this.patCrNo.toCharArray();
		arrCrNo2 = crNo2.toCharArray();
		//for(int j=arrCrNo)
		for(int i=0; i<2&&arrCrNo2.length==2;i++)
		{
			arrCrNo[3+i]=arrCrNo2[i];
		}
		this.patCrNo = new String(arrCrNo);
	}

	public java.lang.String getRefHospCrNo2( )
	{
		char[] arrCrNo;
		char[] arrCrNo2 = new char[2];
		
		if(this.patCrNo==null || this.patCrNo.length() < 13)
			return "";
		else	
			arrCrNo = this.patCrNo.toCharArray();
		
		for(int i=3; i<5;i++)
			arrCrNo2[i-3]=arrCrNo[i];	
		
		return (new String(arrCrNo2)).trim();
	}

	public void setRefHospCrNo1( java.lang.String crNo1 )
	{	//size 7 <== last seven digits
		char[] arrCrNo;
		char[] arrCrNo1;
		if(this.patCrNo==null || this.patCrNo.length() < 13)
			arrCrNo = new char[13];
		else	
			arrCrNo = this.patCrNo.toCharArray();
		
		arrCrNo1 = crNo1.toCharArray();
		
		//for(int j=arrCrNo)
		for(int i=0; i<3&&arrCrNo1.length==3;i++)
			arrCrNo[i]=arrCrNo1[i];		
		
		this.patCrNo = new String(arrCrNo);
	}

	public java.lang.String getRefHospCrNo1( )
	{
		char[] arrCrNo;
		char[] arrCrNo1 = new char[3];
		
		if(this.patCrNo==null || this.patCrNo.length() < 13)
			return "";
		else	
			arrCrNo = this.patCrNo.toCharArray();
		
		for(int i=0; i<3; i++)
			arrCrNo1[i]=arrCrNo[i];	
		
		return (new String(arrCrNo1)).trim();
	}	

	public void setFileNo(String[] fileNo) {
		this.fileNo = fileNo;
	}


	public String[] getFileNo() {
		return fileNo;
	}


	public String getPatMotherName() {
		return patMotherName;
	}


	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}


	public String getPatMomName() {
		return patMomName;
	}


	public void setPatMomName(String patMomName) {
		this.patMomName = patMomName;
	}


	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}


	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
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


	public String getIsDuplicatePatientPopup() {
		return isDuplicatePatientPopup;
	}


	public void setIsDuplicatePatientPopup(String isDuplicatePatientPopup) {
		this.isDuplicatePatientPopup = isDuplicatePatientPopup;
	}


	public String getPatRefHospitalDeptOther() {
		return patRefHospitalDeptOther;
	}


	public void setPatRefHospitalDeptOther(String patRefHospitalDeptOther) {
		this.patRefHospitalDeptOther = patRefHospitalDeptOther;
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


	public String getPatCasteCode() {
		return patCasteCode;
	}


	public void setPatCasteCode(String patCasteCode) {
		this.patCasteCode = patCasteCode;
	}


	public String getPatBPLCardNo() {
		return patBPLCardNo;
	}


	public void setPatBPLCardNo(String patBPLCardNo) {
		this.patBPLCardNo = patBPLCardNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getBplFamilyId() {
		return bplFamilyId;
	}


	public void setBplFamilyId(String bplFamilyId) {
		this.bplFamilyId = bplFamilyId;
	}


	public String getBplRelationCode() {
		return bplRelationCode;
	}


	public void setBplRelationCode(String bplRelationCode) {
		this.bplRelationCode = bplRelationCode;
	}


	public String getBplStateId() {
		return bplStateId;
	}


	public void setBplStateId(String bplStateId) {
		this.bplStateId = bplStateId;
	}


	public String getBplMMJRKNo() {
		return bplMMJRKNo;
	}


	public void setBplMMJRKNo(String bplMMJRKNo) {
		this.bplMMJRKNo = bplMMJRKNo;
	}


	public String getPrint() {
		return print;
	}


	public void setPrint(String print) {
		this.print = print;
	}


	public String getPatBirthPlace() {
		return patBirthPlace;
	}


	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}


	public String getPatDocType() {
		return patDocType;
	}


	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}


	public String getPatAptId() {
		return patAptId;
	}


	public void setPatAptId(String patAptId) {
		this.patAptId = patAptId;
	}


	public String getPatAptStatus() {
		return patAptStatus;
	}


	public void setPatAptStatus(String patAptStatus) {
		this.patAptStatus = patAptStatus;
	}


	public String getPatAptNo() {
		return patAptNo;
	}


	public void setPatAptNo(String patAptNo) {
		this.patAptNo = patAptNo;
	}


	public String getPatAptNoToRegister() {
		return patAptNoToRegister;
	}


	public void setPatAptNoToRegister(String patAptNoToRegister) {
		this.patAptNoToRegister = patAptNoToRegister;
	}


	public String getSearchId() {
		return searchId;
	}


	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}


	public String getSearchValue() {
		return searchValue;
	}


	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}


	public String getPatAptSlot() {
		return patAptSlot;
	}


	public void setPatAptSlot(String patAptSlot) {
		this.patAptSlot = patAptSlot;
	}


	public String getPatAddEmailId() {
		return patAddEmailId;
	}


	public void setPatAddEmailId(String patAddEmailId) {
		this.patAddEmailId = patAddEmailId;
	}


	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}


	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}


	public String getPatAptQueueNO() {
		return patAptQueueNO;
	}


	public void setPatAptQueueNO(String patAptQueueNO) {
		this.patAptQueueNO = patAptQueueNO;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}
	//Start:Sheeldarshi
	
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
	
	public String getPatPrimaryCatGrp() {
		return PatPrimaryCatGrp;
	}

	public void setPatPrimaryCatGrp(String PatPrimaryCatGrp) {
		this.PatPrimaryCatGrp = PatPrimaryCatGrp;
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


	public String getCreditBillFlag() {
		return creditBillFlag;
	}


	public void setCreditBillFlag(String creditBillFlag) {
		this.creditBillFlag = creditBillFlag;
	}
	
	public String getlocalLanguage() {
		return localLanguage;
	}


	public void setlocalLanguage(String localLanguage) {
		this.localLanguage = localLanguage;
	}
	
	public String getPatAddPhoneNo() {
		return patAddPhoneNo;
	}

	public void setPatAddPhoneNo(String patAddPhoneNo) {
		this.patAddPhoneNo = patAddPhoneNo;
	}
	
	public String getPatAddPhoneOwner() {
		return patAddPhoneOwner;
	}

	public void setPatAddPhoneOwner(String patAddPhoneOwner) {
		this.patAddPhoneOwner = patAddPhoneOwner;
	}
	
	public String getPatVisitReason() {
		return patVisitReason;
	}

	public void setPatVisitReason(String patVisitReason) {
		this.patVisitReason = patVisitReason;
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
	

	public String getPatAddMobileNo() {
		return patAddMobileNo;
	}

	public void setPatAddMobileNo(String patAddMobileNo) {
		this.patAddMobileNo = patAddMobileNo;
	}
	//End:Sheeldarshi
	
	//Start:Sheeldarshi:20thOct'14:Duplicacy check
		public String getAsNewPatient() {
			return asNewPatient;
		}

		public void setAsNewPatient(String asNewPatient) {
			this.asNewPatient = asNewPatient;
		}
		public String getPatCatDocIsAlternateId() {
			return patCatDocIsAlternateId;
		}

		public void setPatCatDocIsAlternateId(String patCatDocIsAlternateId) {
			this.patCatDocIsAlternateId = patCatDocIsAlternateId;
		}

		//End:Sheeldarshi:20thOct'14:Duplicacy check
		/*  ## 		Modification Log							
		##		Modify Date				:10thMar'15 
		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
		##		Modify By				:Sheeldarshi 
	 */
	public String getIsApprovalReq() {
		return isApprovalReq;
	}

	public void setIsApprovalReq(String isApprovalReq) {
		this.isApprovalReq = isApprovalReq;
	}
	public String getPatRMOEmployee() {
		return patRMOEmployee;
	}

	public void setPatRMOEmployee(String patRMOEmployee) {
		this.patRMOEmployee = patRMOEmployee;
	}
	
	
	//End:sheeldarshi
	
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
	//Start:Sheeldarshi
	//Reason:Bug 10166 - System should be able to validate senior citizen category
	public String getSeniorCitizenAgeLimit() {
		return seniorCitizenAgeLimit;
	}

	public void setSeniorCitizenAgeLimit(String seniorCitizenAgeLimit) {
		this.seniorCitizenAgeLimit = seniorCitizenAgeLimit;
	}
	public String getSeniorCitizenCatCode() {
		return seniorCitizenCatCode;
	}

	public void setSeniorCitizenCatCode(String seniorCitizenCatCode) {
		this.seniorCitizenCatCode = seniorCitizenCatCode;
	}
	//End


	public String getIsAadharConsentGiven() {
		return isAadharConsentGiven;
	}


	public void setIsAadharConsentGiven(String isAadharConsentGiven) {
		this.isAadharConsentGiven = isAadharConsentGiven;
	}


	public String getPatEmgCntNo() {
		return patEmgCntNo;
	}


	public void setPatEmgCntNo(String patEmgCntNo) {
		this.patEmgCntNo = patEmgCntNo;
	}


	public String getIsBarcodeSlipPrint() {
		return isBarcodeSlipPrint;
	}


	public void setIsBarcodeSlipPrint(String isBarcodeSlipPrint) {
		this.isBarcodeSlipPrint = isBarcodeSlipPrint;
	}


	public String getRoomCode() {
		return roomCode;
	}


	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}


	public String getPaymentModeRefId() {
		return paymentModeRefId;
	}


	public void setPaymentModeRefId(String paymentModeRefId) {
		this.paymentModeRefId = paymentModeRefId;
	}


	public String getPaymentModeCode() {
		return paymentModeCode;
	}


	public void setPaymentModeCode(String paymentModeCode) {
		this.paymentModeCode = paymentModeCode;
	}
}
