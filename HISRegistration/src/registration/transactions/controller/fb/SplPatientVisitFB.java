package registration.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionMapping;

public class SplPatientVisitFB  extends CRNoFB{
	
	//for old department Visit
	private String patPrimaryCatCode;
	    private String[] index;
	    private String patSecondaryCatCode;
	    private String searchName;
	    private String crNoToRetrieve;
	    private String allCheckbox;
	    private String departmentCode;
		private String hmode;
		private String patFirstName;
	    private String patMiddleName;
	    private String patLastName;
	    private String patGender;
		private String patAge;
	    private String patGuardianName;
	    private String patAgeUnit;    
	    private String prevCrNo;
		private String departmentdiv;
		private String valid;
		private String[] departmentsToVisitStamp;
		private String[] chkdepartmentsToVisitStamp;
		private String[] alreadyVisitedDept;
		private String removeDept;
		private String[] deptsAlreadyVisited;
		private String patRegCatCode;
		private String getCrNoToRetrieve;
	    private String patPrimaryCat;
	    private String patSecondaryCat;
	    private String referringInst;
	    private String patGenderCode;
		private String stateRadio;
		private String[] newdepartmentsToVisitStamp;
		private String newremoveDept;
		private String[] hiddenEpisode;
		private String hcode;
		private String episodeCode;
		private String isReferred;
		private String isRefferInOut;
		private String referringInstType;
		private String isAssociated;
		private String patRefGnctdHospitalDept;
		private String patRefDoctor;
		private String patRefGnctdHospitalCode;
		private String patRefHospitalName;
		private String patRefGnctdHospitalCrno;
		private String patRefGnctdHospitalDeptUnit;
		private String fromDepartment;
		private String refferringOPDEpisode;
		private String renewal;
		private String amount;
		private String[] departmentsToRenew;
		private String indexID;
		private String selectedRefCrNo;
		private String isPatReferByList;
		private String deptOrUnitName;
		private String onRequestVisit="";
		private String patRefHospitalDeptOther;
		private String saveSuccessful;
		private String newDepartmentVisit="";
		private String oldDepartmentVisit="";
		private String modeCase;
		
	
		
		//for new department Visit
		
		private String patAmountCollected;
		private String fileNo;
		
		private String[] arrFileNo;
		private String fromDepartmentCode;
		private String fromDepartmentUnitCode;
		private String fromDepartmentUnit;
		private String toDepartment;
		private String toDepartmentCode;
		private String toDepartmentUnit;
		private String toDepartmentUnitCode;
		private String selectedFromDept;
		private String patBillAmountWithoutGrouping;
		private String referInternalExternal;
		private String referingRowIndex;
		private String selectedReffereCrNo;
		private String onlineReferedIndex;
		private String captureMandatoryField="false";
		///for mandaoty fields
		private String patReligionCode;
		
		private String patNickName;
		private String patMaritalStatusCode;
		private String patMotherName;
		private String patHusbandName;
		private String patMonthlyIncome;
		private String patOccupation;
		private String patFatherOccupation;
		private String patHusbandOccupation;
		private String patNationalId;
		private String patCardNo;
		private String errorMessage;
		private String entryDate;
		private String episodeVisitNo;
		
		private String departmentUnitCode;
		
		private String selectedCheckBox;
		private String print;
				
		//Added by Garima for Hospital Wise Renewal for Maharshtra
		private String regRenewalRequired;
		private String isPrintCard;
		private String selectedReferal;
		private String department;
		private String searchId;
		private String searchValue;
		private String patAptId;
		private String patAptStatus;
		private String patAptNo;	
		private String patAptSlot;
		private String patAptQueueNO;
		private String isAppointment;
		
		private String patPrimaryCatGrpCode;
		private String patActualAmount;
		private String patAmountCrConsultation;
		private String patAmountNCrConsultation;
		private String patIsCrossConsultantWithReferal;
		
		private String creditLetterRefNo;
		private String clientCode;
		private String creditLetterDate="";
		private String clientName;
		private String staffCardNo;
		private String staffCardName;
		private String relationWithStaff;
		private String cardvalidityDate;
		private String relationNameWithStaff;

		private String agsDistrictCode;
		private String agsCounterNo;
		private String agsNo;
		private String splRenewalRequired;
		private WebRowSet strResultWs = null;
		
	//added by Mukund on 27.07.2016
		private String creditLimit;
		private String letterType;
		private String agsCreditLimit;
		private String strRenewalType;
		private String renewalRequired;
		private String patAmountDeptWise;
		private String patRenewalAmountDeptWise;
		private String deptToSearchFrom;
		private String paymentModeCode;
		private String paymentModeRefId;
		
		public String getDeptToSearchFrom() {
			return deptToSearchFrom;
		}


		public void setDeptToSearchFrom(String deptToSearchFrom) {
			this.deptToSearchFrom = deptToSearchFrom;
		}


		public String getStrRenewalType() {
			return strRenewalType;
		}


		public void setStrRenewalType(String strRenewalType) {
			this.strRenewalType = strRenewalType;
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


			//End : Mukund 
		
		public String getPatActualAmount() {
			return patActualAmount;
		}

		public void setPatActualAmount(String patActualAmount) {
			this.patActualAmount = patActualAmount;
		}

		public String getPatPrimaryCatGrpCode() {
			return patPrimaryCatGrpCode;
		}

		public void setPatPrimaryCatGrpCode(String patPrimaryCatGrpCode) {
			this.patPrimaryCatGrpCode = patPrimaryCatGrpCode;
		}

		public String getRegRenewalRequired() {
			return regRenewalRequired;
		}

		public void setRegRenewalRequired(String regRenewalRequired) {
			this.regRenewalRequired = regRenewalRequired;
		}

		public String getSelectedCheckBox() {
			return selectedCheckBox;
		}

		public void setSelectedCheckBox(String selectedCheckBox) {
			this.selectedCheckBox = selectedCheckBox;
		}

		public String getDepartmentUnitCode() {
			return departmentUnitCode;
		}

		public void setDepartmentUnitCode(String departmentUnitCode) {
			this.departmentUnitCode = departmentUnitCode;
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

		public String getModeCase() {
			return modeCase;
		}

		public void setModeCase(String modeCase) {
			this.modeCase = modeCase;
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

		public String getSaveSuccessful() {
			return saveSuccessful;
		}

		public void setSaveSuccessful(String saveSuccessful) {
			this.saveSuccessful = saveSuccessful;
		}

	
		public String getOnRequestVisit() {
			return onRequestVisit;
		}

		public void setOnRequestVisit(String onRequestVisit) {
			this.onRequestVisit = onRequestVisit;
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

		public String getSelectedRefCrNo() {
			return selectedRefCrNo;
		}

		public void setSelectedRefCrNo(String selectedRefCrNo) {
			this.selectedRefCrNo = selectedRefCrNo;
		}

		public String[] getDepartmentsToRenew() {
			return departmentsToRenew;
		}

		public void setDepartmentsToRenew(String[] departmentsToRenew) {
			this.departmentsToRenew = departmentsToRenew;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getRefferringOPDEpisode() {
			return refferringOPDEpisode;
		}

		public void setRefferringOPDEpisode(String refferringOPDEpisode) {
			this.refferringOPDEpisode = refferringOPDEpisode;
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

		public String[] getHiddenEpisode() {
			return hiddenEpisode;
		}

		public void setHiddenEpisode(String[] hiddenEpisode) {
			this.hiddenEpisode = hiddenEpisode;
		}
		
		public void reset(ActionMapping mapping,HttpServletRequest request){
			this.setPatGenderCode("");
			this.setPatCrNo("            ");
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
			this.setRemoveDept("");
			this.setHmode("");
			this.setDepartmentdiv("");
			this.setHiddenEpisode(new String[]{});
			this.setEpisodeCode("");
			this.setHcode("");
			this.setIsReferred("");
			this.setAmount("0");
			this.setIsPatReferByList("");
			this.setOnRequestVisit("");
			//this.setSaveSuccessful("");
			
			this.setSearchId("1");
			this.setSearchValue("");
			this.setDeptToSearchFrom("0");
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

		
		
		
		public String getPatLastName() {
			return patLastName;
		}

		public void setPatLastName(String patLastName) {
			this.patLastName = patLastName;
		}

		

		public String getPatMiddleName() {
			return patMiddleName;
		}

		public void setPatMiddleName(String patMiddleName) {
			this.patMiddleName = patMiddleName;
		}

		

		public String getPatPrimaryCatCode() {
			return patPrimaryCatCode;
		}

		public void setPatPrimaryCatCode(String patPrimaryCatCode) {
			this.patPrimaryCatCode = patPrimaryCatCode;
		}

		

		public String getPatRegCatCode() {
			return patRegCatCode;
		}

		public void setPatRegCatCode(String patRegCatCode) {
			this.patRegCatCode = patRegCatCode;
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

		
		


		public String getDepartmentCode() {
			return departmentCode;
		}


		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
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

		
		public String getPatAge() {
			return patAge;
		}

		public void setPatAge(String patAge) {
			this.patAge = patAge;
		}








		public String[] getDeptsAlreadyVisited() {
			return deptsAlreadyVisited;
		}


		public void setDeptsAlreadyVisited(String[] deptsAlreadyVisited) {
			this.deptsAlreadyVisited = deptsAlreadyVisited;
		}





		public String[] getIndex() {
			return index;
		}





		public void setIndex(String[] index) {
			this.index = index;
		}





		public String getAllCheckbox() {
			return allCheckbox;
		}





		public void setAllCheckbox(String allCheckbox) {
			this.allCheckbox = allCheckbox;
		}





		public String[] getChkdepartmentsToVisitStamp() {
			return chkdepartmentsToVisitStamp;
		}





		public void setChkdepartmentsToVisitStamp(String[] chkdepartmentsToVisitStamp) {
			this.chkdepartmentsToVisitStamp = chkdepartmentsToVisitStamp;
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




		public String getGetCrNoToRetrieve() {
			return getCrNoToRetrieve;
		}




		public void setGetCrNoToRetrieve(String getCrNoToRetrieve) {
			this.getCrNoToRetrieve = getCrNoToRetrieve;
		}

		public String getFromDepartment() {
			return fromDepartment;
		}

		public void setFromDepartment(String fromDepartment) {
			this.fromDepartment = fromDepartment;
		}

		public String getIsAssociated() {
			return isAssociated;
		}

		public void setIsAssociated(String isAssociated) {
			this.isAssociated = isAssociated;
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

		public String getPatRefHospitalName() {
			return patRefHospitalName;
		}

		public void setPatRefHospitalName(String patRefHospitalName) {
			this.patRefHospitalName = patRefHospitalName;
		}

		public String getReferringInstType() {
			return referringInstType;
		}

		public void setReferringInstType(String referringInstType) {
			this.referringInstType = referringInstType;
		}

		public String getRenewal() {
			return renewal;
		}

		public void setRenewal(String renewal) {
			this.renewal = renewal;
		}

		public String getIndexID() {
			return indexID;
		}

		public void setIndexID(String indexID) {
			this.indexID = indexID;
		}

		public String getPatRefHospitalDeptOther() {
			return patRefHospitalDeptOther;
		}

		public void setPatRefHospitalDeptOther(String patRefHospitalDeptOther) {
			this.patRefHospitalDeptOther = patRefHospitalDeptOther;
		}

		public String getPrint() {
			return print;
		}

		public void setPrint(String print) {
			this.print = print;
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

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
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

		public String getPatAptSlot() {
			return patAptSlot;
		}

		public void setPatAptSlot(String patAptSlot) {
			this.patAptSlot = patAptSlot;
		}

		public String getPatAptQueueNO() {
			return patAptQueueNO;
		}

		public void setPatAptQueueNO(String patAptQueueNO) {
			this.patAptQueueNO = patAptQueueNO;
		}

		public String getIsAppointment() {
			return isAppointment;
		}

		public void setIsAppointment(String isAppointment) {
			this.isAppointment = isAppointment;
		}

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

		public String getSplRenewalRequired() {
			return splRenewalRequired;
		}

		public void setSplRenewalRequired(String splRenewalRequired) {
			this.splRenewalRequired = splRenewalRequired;
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


		public String getRenewalRequired() {
			return renewalRequired;
		}


		public void setRenewalRequired(String renewalRequired) {
			this.renewalRequired = renewalRequired;
		}


		public String getPatAmountDeptWise() {
			return patAmountDeptWise;
		}


		public void setPatAmountDeptWise(String patAmountDeptWise) {
			this.patAmountDeptWise = patAmountDeptWise;
		}


		public String getPatRenewalAmountDeptWise() {
			return patRenewalAmountDeptWise;
		}


		public void setPatRenewalAmountDeptWise(String patRenewalAmountDeptWise) {
			this.patRenewalAmountDeptWise = patRenewalAmountDeptWise;
		}


		public String getPaymentModeCode() {
			return paymentModeCode;
		}


		public void setPaymentModeCode(String paymentModeCode) {
			this.paymentModeCode = paymentModeCode;
		}


		public String getPaymentModeRefId() {
			return paymentModeRefId;
		}


		public void setPaymentModeRefId(String paymentModeRefId) {
			this.paymentModeRefId = paymentModeRefId;
		}
		
}

