

	package billing.reports;
	
			import hisglobal.exceptions.HisException;
			import hisglobal.transactionutil.GenericFormBean;
			import hisglobal.utility.HisUtil;

			import javax.sql.rowset.WebRowSet;

			public class IPDPatientProvisionalBillRptFB extends GenericFormBean  {

				/**
				 * 
				 */
				private static final long serialVersionUID = 02L;
				
				java.util.LinkedHashMap<String, String> lhm = new java.util.LinkedHashMap<String, String>();
				private String strReportFormat = "";
				private String strReportId = "";
				
				private String strVal = "";
				private String strCtDate = null;
				private String strcurrentdate = null;
				private String strchkvalue = "";
				
				
				private String strIsIpdDiscount = "0";
				private String strExcessCreditLimit = "0";
				private String 	strArogyaIpdCreditLimit = "0";
				
				private WebRowSet strPatientDetailsWs = null;
				private WebRowSet strOnLineReqDiscountWs = null;
				private WebRowSet strPrimaryKeyWs = null;
				
				private WebRowSet strParticularDtlsListWs = null;
				private WebRowSet strParticularDtlsWs = null;
				
				
				private String strAdmissionDtlHidVal = "";
				private String strChkVal = "";
				private String strApprovedByCombo = "";
				private String strApprovedByCombo2 ="";
				private String strRemarksCombo = "";
				private String strEffectiveFrmDate = "";
				private String strEffectiveToDate = "";
				 private String strDeptName ="";
				 private String strDeptId = "";
				 private String strWardName = "";
				 private String strGroupId = "";
				 private String strPkgBreakUp = "";
				 private String strTotalExpenseAmt = "";
				 private String strTotalDisAmt = "";
				 private String strTotalReceAmtByPatient = "";
				 private String strTotalBenifitFrmClt = "";
				 private String strNetPaymentAmt = "";
				 private String strHidden="";
				 private String strClientAcctDtl = "";
				 private String totalRecAmtDivId1 = "";
				 private String strDeptCode = "";
				 //Billing Config Variable	 
				 private String strIpdThirdPartyBilling ="";
			     private String strIpdRoundOff = "";
			     private String strPatientDetailsView = "";
			     private String strChkBoxComboValue = "";
			     private String strBillTypeCombo="";
			     
			     private String strTempWardCode = "";
				 
				 private String[] strDiscAmt = null;
				 private String[] strUnProcHiddVal = null;
				 private String[] chkBox1= null;
				 private String[] strSeviceTaxAmt = null;
				 
				 private String[] chkBoxHidden = null;
				 
				 private String[] strReqDate = null;
				 private String[] strUnProcTarriffName = null;
				 private String[] strUnProcessQty = null;
				 private String[] strRefundQty = null;
				 private String[] strUnitCombo = null;
				 private String[] strUnProcNetAmt = null;
				 
				 private String[] strServiceTax = null;
				 
				 // Bill Approval
				 private String strMaxBenifitAmt = "";	 
				 private String strReceivedAmt ="";
				 private String strTariffDiscountAmtConfgDtlBillApproval = "";
				 private String strTotalServiceTax = "";
				 private String strNetPaybleAmt="";
				 private String strChkRcdOpen ="";
				 
				 private String strNetAmt[] = null;
				 
				 private String strActualTariffNetAmt[] = null;
				 
				 // Drop Down Data
				 private String strOffLineTariffDiscountUnit = "";
				 private String strOffLineTariffDiscountType ="";
				 private String strOffLineTariffDiscountBy ="";
				 private String strOfflineDiscountApprovedByDetails ="";
				 private String strOffLineTariffDiscountReason = "";
				 private String strOfflineDiscountRemarksDetails="";
				 private String strOffLineTariffDiscountDate="";
				 private String strOnlinePaymentMode = "";
				 private String strOnlinePaymentDtls = "";
				 private String strOnlineAmount = "";
				 
				 private String[] strCompChargeCheck = null;
				 private String[] strSpecialChargeCheck = null;
			 	 private String[] strOfflineTariffDetailsHidden = null;
			 	 private String[] strOfflineTariffRateUnit = null;
				 private String[] strOfflineTariffQty = null;
				 private String[] strOfflineTariffUnit = null;
				 private String[] strOfflineTariffDate = null;
				 private String[] strOfflineTariffServiceTax = null;
				 private String[] strOfflineTariffServiceTaxAmtVal = null;
				 private String[] strOfflineTariffNetAmount = null;
				 private String[] strOfflineActualTariffAmtVal = null;
				 
				 private String strOfflinePaymentMode ="";
				 private String strOfflinePaymentDtls ="";
				 private String strOfflineAmount ="";

				 // Drop Down Multi-Row Data
				 private String[] strOfflinePackageName= null;
				 private String[] strOfflinePackageRateUnit =null;
				 private String[] strOfflinePackageReqQty ={"0"};
				 private String[] strOfflinePackageUnit =null;
				 private String[] strOfflinePackageServiceTax ={"0"};
				 private String[] strOfflinePackageDiscount={"0"};
				 private String[] strOfflinePackageNetAmount={"0"};
				 
				 
				 private String strOfflineGroupDetails ="";
				 private String strOfflinePackageGroup="";
				 private String strOfflineTarffList="";
				 private String strOfflineDiscountApprovedBy="";
				 private String strOfflineDiscountRemarks="";
				 private String strOffLineTariffDiscountReasonText = "";
				 private String strOfflineTariffDetails = "";
				 
				 
				 private String strCost = "";
				 private String strUnitName = "";
				 private String strIsRefund = "";
				 private String strIsAdavnce ="";
				 private String strParticulaDtl = "";
				 private String strBillAprovalDtl1 = "";
				 private String strBillAprovalDtl2 = "";

				 
			    private String strChk ="";
			    private String strCrNo = "";
			    private String strCrNo1 = "";
			    private String strPresentAcctStatus ="";
			    private String strNewAcctStatus = "";
			    private String strApprovalBy ="";
			    private String strAccStatusCombo = "";
			    private String strPartPaymentAmt = "";
			    private String strRemarks="";
			    private String strPrimaryCategory ="";
			    private String strTreatmentCategory ="";
			    private String strDeptWard ="";
			    private String strAccountNo="";
			    private String strAddmissionNo="";
			    private String strAdmissionDate="";
			    private String strDeptUnitId = "";
			    private String strGroupNameCombo = "";
			    private String strTariffNameCombo = "";
			    private String strHospitalServiceCombo = "";
			    private String strHospitalCode = "";
			    private String strPatientCategoryCombo ="";
			    private String strWardnameCombo ="";
			    private String[] strComboValue = {"0"};
			    private String strDropDownValues = "";
			    private String strChargeTypeID = "";
				private String strPatientCatCode = "";
			    private String strClientApprovalDtl = "";
			    
			    private String strPendingPartPaymentReq = "";
			    private String strCltApprBalanceAmt ="";
			    private String strRemarksCombo2 = "";
			    private String strSeatId = "";
			    private String strApprovalId="";
			    private String strReqNo="";
			    private String strApprovedDate = "";
			    private String strWardCode = "";
			    private String dr =null;
			    private String strPartpayment = "";
			    
			    private String strClientName ="";
			    private String strClientType ="";
			    private String strApplNoDate ="";
			    private String strAcctNoAcctTyp="";
			    private String strAcctOpngDate="";
			    private String strTreatmentCatg="";
			    private String strCltAppSancAmt ="";
			    
			    private String strSancAmt = "";
			    private String strBalanceAmt ="";
			    private String strClientPatNo = "";
			    private String strPatAcctNo = "";
			    private String strEpisodeNo = "";
			 
			    private String strIpdBillManagementMode = "0";
			    
			    private String strNewTreatmentCategory = "0";
			    private String strStartDate = "";
			    private String strEndDate = "";
			    private String strWardType = "0";
			    private String strTariffCode = "";
			    private String strSpecialWardType = "0";
			    private String strDepartment = "0";
			    
			    private String strTreatmentCategoryValues = "";
			    private String strWardTypeValues = "";
			    private String strSpecialWardTypeValues = "";
			    private String strDepartmentValues = "";
			    
			    private String strPreviousDates = "";
			    private String strPreviousDateValues = "";
			    private String[] strPreviousCheck = null;
			    
				private String strSearchLetter = "";
			    
			    
			    private String strMsgType = "";
				private String strWarning = "";
				private String strMsg ="";
				private String strErrMsg ="";
				private String strMsgString = null;
				
				private String isBillFinal="";
				private String isApproved="1";
				
				private String strPoorFreeWelfareAmt = "0";
				private String strPoorFreeGrantAmt = "0";
				private String primaryCategoryCode="";
				
				private String strPresentAcctStatusCode="0";
			    private String dietChargeId="";
			    private String strAgeInMonths="";
				
			    
			    /*Consumable Charges*/
			    
			    private String strConsumableCharge="";
			    private String strOfflineTotalPayAmountWithoutConsumable="";
			    private String strGroupIdForConsumableConcatenated="";
			    private String strConsumableChargesGroupId="";
			    private String strConsumableChargesTariffCode="";
			    private String strUrgSur="";
			    private String[] strPriority= null;
			    private String[] strDiscount= null;
			    private String[] strDiscountType= null;
			    private String[] strDiscountAmt= null;
			    private String strOfflineTariffName[] = null;
			    private String[] date=null;
			    private String[] qty=null;
			    private String[] rate=null;
			    private String[] amt=null;
			    private String[] name=null;
			    private String strIsCalledFromIpdBillNew="0";
			    private String strAcctStatMode="1";
			    private String strAllTrfJSON=""; 
			    private String strAllTrfHLP="";
			    private String strNumRows="";
			    private String deleteFlag[] = null;
			    private String strPrevReqNo[] = null;
			    private String strPrevBedTransfer = "";
			    private String strTransferDeptCode[]=null;
			    private String strTransferWardCode[]=null;
			    private String strTransferChargeType[]=null;
			    private String strInDate[]=null;
			    private String strOutDate[]=null;
			    private String strMovNo[]=null;
			    private String strSaveFlag[]=null;
			    private String adtOnlineFlag[]=null;
			    private String strPatientAdmndtl="";
			    private String printFlag="";
			    private String strDischargeDate="";
			    private String strBillNo="";
			    private String filePath="";
				private String isOpenPopUp="";
				private String printMode="0";
				private String strAdvanceamt ="";
				private String grpid ="";
				private String strUnitold="";
				private String strUnitNameValues="0";
				private String strUnitNew ="";
				private String strDocNameValues="";
				private String strDocOld="0";
				private String strDocNew ="";
				private String[] strUnitCode =null;
				private String[] strConsultant=null;
				private String[] strIsDoubleOc=null;
				private String[] strDblOccDate =null;
				private String[] strDisActAmt=null;
				private String strAccStatus="";
				private String accType="1";
				private String strbcflag="0";
				private String finalBillFlag="";
				private String serviceFlag="";
				private String[] sNo = { "0" };
				private String strPatAcctStatus ="1";
				
				
				
				public String getServiceFlag() {
					return serviceFlag;
				}
				public void setServiceFlag(String serviceFlag) {
					this.serviceFlag = serviceFlag;
				}
				public String getStrbcflag() {
					return strbcflag;
				}
				public void setStrbcflag(String strbcflag) {
					this.strbcflag = strbcflag;
				}
				public String getAccType() {
					return accType;
				}
				public void setAccType(String accType) {
					this.accType = accType;
				}
				public String[] getStrDisActAmt() {
					return strDisActAmt;
				}
				public void setStrDisActAmt(String[] strDisActAmt) {
					this.strDisActAmt = strDisActAmt;
				}
				public String getStrAdvanceamt() {
					return strAdvanceamt;
				}
				public void setStrAdvanceamt(String strAdvanceamt) {
					this.strAdvanceamt = strAdvanceamt;
				}
				public String[] getDate() {
					return date;
				}
				public void setDate(String[] date) {
					this.date = date;
				}
				public String[] getQty() {
					return qty;
				}
				public void setQty(String[] qty) {
					this.qty = qty;
				}
				public String[] getRate() {
					return rate;
				}
				public void setRate(String[] rate) {
					this.rate = rate;
				}
				public String[] getAmt() {
					return amt;
				}
				public void setAmt(String[] amt) {
					this.amt = amt;
				}
				public String[] getName() {
					return name;
				}
				public void setName(String[] name) {
					this.name = name;
				}
				public String getPrimaryCategoryCode() {
					return primaryCategoryCode;
				}
				public void setPrimaryCategoryCode(String primaryCategoryCode) {
					this.primaryCategoryCode = primaryCategoryCode;
				}
				public String getStrPoorFreeWelfareAmt() {
					return strPoorFreeWelfareAmt;
				}
				public void setStrPoorFreeWelfareAmt(String strPoorFreeWelfareAmt) {
					this.strPoorFreeWelfareAmt = strPoorFreeWelfareAmt;
				}
				public String getStrPoorFreeGrantAmt() {
					return strPoorFreeGrantAmt;
				}
				public void setStrPoorFreeGrantAmt(String strPoorFreeGrantAmt) {
					this.strPoorFreeGrantAmt = strPoorFreeGrantAmt;
				}
				public String getIsApproved() {
					return isApproved;
				}
				public void setIsApproved(String isApproved) {
					this.isApproved = isApproved;
				}
				public String getIsBillFinal() {
					return isBillFinal;
				}
				public void setIsBillFinal(String isBillFinal) {
					this.isBillFinal = isBillFinal;
				}
				public String getStrTreatmentCategoryValues() {
					return strTreatmentCategoryValues;
				}
				public void setStrTreatmentCategoryValues(String strTreatmentCategoryValues) {
					this.strTreatmentCategoryValues = strTreatmentCategoryValues;
				}
				public String getStrWardTypeValues() {
					return strWardTypeValues;
				}
				public void setStrWardTypeValues(String strWardTypeValues) {
					this.strWardTypeValues = strWardTypeValues;
				}
				public String getStrErrMsg() {
					return strErrMsg;
				}
				public void setStrErrMsg(String strErrMsg) {
					this.strErrMsg = strErrMsg;
				}
				public String getStrPrimaryCategory() {
					return strPrimaryCategory;
				}
				public void setStrPrimaryCategory(String strPrimaryCategory) {
					this.strPrimaryCategory = strPrimaryCategory;
				}
				public String getStrTreatmentCategory() {
					return strTreatmentCategory;
				}
				public void setStrTreatmentCategory(String strTreatmentCategory) {
					this.strTreatmentCategory = strTreatmentCategory;
				}
				public String getStrDeptWard() {
					return strDeptWard;
				}
				public void setStrDeptWard(String strDeptWard) {
					this.strDeptWard = strDeptWard;
				}
				public String getStrAccountNo() {
					return strAccountNo;
				}
				public void setStrAccountNo(String strAccountNo) {
					this.strAccountNo = strAccountNo;
				}
				public String getStrAddmissionNo() {
					return strAddmissionNo;
				}
				public void setStrAddmissionNo(String strAddmissionNo) {
					this.strAddmissionNo = strAddmissionNo;
				}
				public String getStrAdmissionDate() {
					return strAdmissionDate;
				}
				public void setStrAdmissionDate(String strAdmissionDate) {
					this.strAdmissionDate = strAdmissionDate;
				}
				public String getStrPresentAcctStatus() {
					return strPresentAcctStatus;
				}
				public void setStrPresentAcctStatus(String strPresentAcctStatus) {
					this.strPresentAcctStatus = strPresentAcctStatus;
				}
				public String getStrNewAcctStatus() {
					return strNewAcctStatus;
				}
				public void setStrNewAcctStatus(String strNewAcctStatus) {
					this.strNewAcctStatus = strNewAcctStatus;
				}
				public String getStrApprovalBy() {
					return strApprovalBy;
				}
				public void setStrApprovalBy(String strApprovalBy) {
					this.strApprovalBy = strApprovalBy;
				}
				public String getStrRemarks() {
					return strRemarks;
				}
				public void setStrRemarks(String strRemarks) {
					this.strRemarks = strRemarks;
				}
				public WebRowSet getStrPatientDetailsWs() {
					return strPatientDetailsWs;
				}
				public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
					this.strPatientDetailsWs = strPatientDetailsWs;
				}
				public WebRowSet getStrOnLineReqDiscountWs() {
					return strOnLineReqDiscountWs;
				}
				public void setStrOnLineReqDiscountWs(WebRowSet strOnLineReqDiscountWs) {
					this.strOnLineReqDiscountWs = strOnLineReqDiscountWs;
				}
				public String getStrMsgString() {
					return strMsgString;
				}
				public void setStrMsgString(String strMsgString) {
					this.strMsgString = strMsgString;
				}
				public String getStrMsgType() {
					return strMsgType;
				}
				public void setStrMsgType(String strMsgType) {
					this.strMsgType = strMsgType;
				}
				public String getStrChk() {
					return strChk;
				}
				public void setStrChk(String strChk) {
					this.strChk = strChk;
				}
				public String getStrCrNo() {
					return strCrNo;
				}
				public void setStrCrNo(String strCrNo) {
					this.strCrNo = strCrNo;
				}
				public String getStrCrNo1() {
					return strCrNo1;
				}
				public void setStrCrNo1(String strCrNo1) {
					this.strCrNo1 = strCrNo1;
				}
				public String getStrApprovedByCombo() {
					return strApprovedByCombo;
				}
				public void setStrApprovedByCombo(String strApprovedByCombo) {
					this.strApprovedByCombo = strApprovedByCombo;
				}
				public String getStrRemarksCombo() {
					return strRemarksCombo;
				}
				public void setStrRemarksCombo(String strRemarksCombo) 
				{
					this.strRemarksCombo = strRemarksCombo;
				}
				public String[] getStrComboValue() {
					return strComboValue;
				}
				public void setStrComboValue(String[] strComboValue) {
					this.strComboValue = strComboValue;
				}
				public String getStrDeptUnitId() {
					return strDeptUnitId;
				}
				public void setStrDeptUnitId(String strDeptUnitId) {
					this.strDeptUnitId = strDeptUnitId;
				}
				public String getStrDeptName() {
					return strDeptName;
				}
				public void setStrDeptName(String strDeptName) {
					this.strDeptName = strDeptName;
				}
				public String getStrWardName() {
					return strWardName;
				}
				public void setStrWardName(String strWardName) {
					this.strWardName = strWardName;
				}
				public String getStrGroupNameCombo() {
					return strGroupNameCombo;
				}
				public void setStrGroupNameCombo(String strGroupNameCombo) {
					this.strGroupNameCombo = strGroupNameCombo;
				}
				public String getStrTariffNameCombo() {
					return strTariffNameCombo;
				}
				public void setStrTariffNameCombo(String strTariffNameCombo) {
					this.strTariffNameCombo = strTariffNameCombo;
				}
				public String getStrHospitalServiceCombo() {
					return strHospitalServiceCombo;
				}
				public void setStrHospitalServiceCombo(String strHospitalServiceCombo) {
					this.strHospitalServiceCombo = strHospitalServiceCombo;
				}
				public String getStrPatientCategoryCombo() {
					return strPatientCategoryCombo;
				}
				public void setStrPatientCategoryCombo(String strPatientCategoryCombo) {
					this.strPatientCategoryCombo = strPatientCategoryCombo;
				}
				public String getStrWardnameCombo() {
					return strWardnameCombo;
				}
				public void setStrWardnameCombo(String strWardnameCombo) {
					this.strWardnameCombo = strWardnameCombo;
				}
				public String getStrDeptId() {
					return strDeptId;
				}
				public void setStrDeptId(String strDeptId) {
					this.strDeptId = strDeptId;
				}
				public String getStrGroupId() {
					return strGroupId;
				}
				public void setStrGroupId(String strGroupId) {
					this.strGroupId = strGroupId;
				}
				public String getStrEffectiveFrmDate()
				{
					HisUtil util = new HisUtil("IpdBillManagement", "IpdBillManagementTransVO");
					try {
						strEffectiveFrmDate = util.getASDate("dd-MMM-yyyy");
						util = null;
					} catch (Exception e) {
						new HisException("IpdBillManagement", "IpdBillManagementTransFB",
								"IpdBillManagementTransFB.getStrCtDate()-->" + e.getMessage());
					}
					return strEffectiveFrmDate;
				}
				public void setStrEffectiveFrmDate(String strEffectiveFrmDate) {
					this.strEffectiveFrmDate = strEffectiveFrmDate;
				}
				public String getStrEffectiveToDate() {
					return strEffectiveToDate;
				}
				public void setStrEffectiveToDate(String strEffectiveToDate) {
					this.strEffectiveToDate = strEffectiveToDate;
				}
				public String getStrCtDate() {
					return strCtDate;
				}
				public void setStrCtDate(String strCtDate) {
					this.strCtDate = strCtDate;
				}
				public String getStrDropDownValues() {
					return strDropDownValues;
				}
				public void setStrDropDownValues(String strDropDownValues) {
					this.strDropDownValues = strDropDownValues;
				}
				public String getStrWarning() {
					return strWarning;
				}
				public void setStrWarning(String strWarning) {
					this.strWarning = strWarning;
				}
				public String getStrMsg() {
					return strMsg;
				}
				public void setStrMsg(String strMsg) {
					this.strMsg = strMsg;
				}
				public String getStrChargeTypeID() {
					return strChargeTypeID;
				}
				public void setStrChargeTypeID(String strChargeTypeID) {
					this.strChargeTypeID = strChargeTypeID;
				}
				public String getStrPatientCatCode() {
					return strPatientCatCode;
				}
				public void setStrPatientCatCode(String strPatientCatCode) {
					this.strPatientCatCode = strPatientCatCode;
				}
				public String getStrClientApprovalDtl() {
					return strClientApprovalDtl;
				}
				public void setStrClientApprovalDtl(String strClientApprovalDtl) {
					this.strClientApprovalDtl = strClientApprovalDtl;
				}
				public String getStrClientAcctDtl() {
					return strClientAcctDtl;
				}
				public void setStrClientAcctDtl(String strClientAcctDtl) {
					this.strClientAcctDtl = strClientAcctDtl;
				}
				public String getStrPartPaymentAmt() {
					return strPartPaymentAmt;
				}
				public void setStrPartPaymentAmt(String strPartPaymentAmt) {
					this.strPartPaymentAmt = strPartPaymentAmt;
				}
				public String getStrSancAmt() {
					return strSancAmt;
				}
				public void setStrSancAmt(String strSancAmt) {
					this.strSancAmt = strSancAmt;
				}
				public String getStrBalanceAmt() {
					return strBalanceAmt;
				}
				public void setStrBalanceAmt(String strBalanceAmt) {
					this.strBalanceAmt = strBalanceAmt;
				}
				public String getStrClientPatNo() {
					return strClientPatNo;
				}
				public void setStrClientPatNo(String strClientPatNo) {
					this.strClientPatNo = strClientPatNo;
				}
				public String getStrPatAcctNo() {
					return strPatAcctNo;
				}
				public void setStrPatAcctNo(String strPatAcctNo) {
					this.strPatAcctNo = strPatAcctNo;
				}
				public String getStrClientName() {
					return strClientName;
				}
				public void setStrClientName(String strClientName) {
					this.strClientName = strClientName;
				}
				public String getStrClientType() {
					return strClientType;
				}
				public void setStrClientType(String strClientType) {
					this.strClientType = strClientType;
				}
				public String getStrApplNoDate() {
					return strApplNoDate;
				}
				public void setStrApplNoDate(String strApplNoDate) {
					this.strApplNoDate = strApplNoDate;
				}
				public String getStrAcctNoAcctTyp() {
					return strAcctNoAcctTyp;
				}
				public void setStrAcctNoAcctTyp(String strAcctNoAcctTyp) {
					this.strAcctNoAcctTyp = strAcctNoAcctTyp;
				}
				public String getStrAcctOpngDate() {
					return strAcctOpngDate;
				}
				public void setStrAcctOpngDate(String strAcctOpngDate) {
					this.strAcctOpngDate = strAcctOpngDate;
				}
				public String getStrTreatmentCatg() {
					return strTreatmentCatg;
				}
				public void setStrTreatmentCatg(String strTreatmentCatg) {
					this.strTreatmentCatg = strTreatmentCatg;
				}
				public String getStrCltAppSancAmt() {
					return strCltAppSancAmt;
				}
				public void setStrCltAppSancAmt(String strCltAppSancAmt) {
					this.strCltAppSancAmt = strCltAppSancAmt;
				}
				public String getStrPendingPartPaymentReq() {
					return strPendingPartPaymentReq;
				}
				public void setStrPendingPartPaymentReq(String strPendingPartPaymentReq) {
					this.strPendingPartPaymentReq = strPendingPartPaymentReq;
				}
				public String getStrCltApprBalanceAmt() {
					return strCltApprBalanceAmt;
				}
				public void setStrCltApprBalanceAmt(String strCltApprBalanceAmt) {
					this.strCltApprBalanceAmt = strCltApprBalanceAmt;
				}
				public String getStrEpisodeNo() {
					return strEpisodeNo;
				}
				public void setStrEpisodeNo(String strEpisodeNo) {
					this.strEpisodeNo = strEpisodeNo;
				}
				public String getStrRemarksCombo2() {
					return strRemarksCombo2;
				}
				public void setStrRemarksCombo2(String strRemarksCombo2) {
					this.strRemarksCombo2 = strRemarksCombo2;
				}
				public String getStrApprovedByCombo2() {
					return strApprovedByCombo2;
				}
				public void setStrApprovedByCombo2(String strApprovedByCombo2) {
					this.strApprovedByCombo2 = strApprovedByCombo2;
				}
				public String getStrSeatId() {
					return strSeatId;
				}
				public void setStrSeatId(String strSeatId) {
					this.strSeatId = strSeatId;
				}
				public String getStrApprovalId() {
					return strApprovalId;
				}
				public void setStrApprovalId(String strApprovalId) {
					this.strApprovalId = strApprovalId;
				}
				public String getStrReqNo() {
					return strReqNo;
				}
				public void setStrReqNo(String strReqNo) {
					this.strReqNo = strReqNo;
				}
				public WebRowSet getStrPrimaryKeyWs() {
					return strPrimaryKeyWs;
				}
				public void setStrPrimaryKeyWs(WebRowSet strPrimaryKeyWs) {
					this.strPrimaryKeyWs = strPrimaryKeyWs;
				}
				public String getStrApprovedDate() {
					return strApprovedDate;
				}
				public void setStrApprovedDate(String strApprovedDate) {
					this.strApprovedDate = strApprovedDate;
				}
				public String getStrWardCode() {
					return strWardCode;
				}
				public void setStrWardCode(String strWardCode) {
					this.strWardCode = strWardCode;
				}
				public String getDr() {
					return dr;
				}
				public void setDr(String dr) {
					this.dr = dr;
				}
				public String getStrPartpayment() {
					return strPartpayment;
				}
				public void setStrPartpayment(String strPartpayment) {
					this.strPartpayment = strPartpayment;
				}
				public String getStrHospitalCode() {
					return strHospitalCode;
				}
				public void setStrHospitalCode(String strHospitalCode) {
					this.strHospitalCode = strHospitalCode;
				}
				public String getStrPkgBreakUp() {
					return strPkgBreakUp;
				}
				public void setStrPkgBreakUp(String strPkgBreakUp) {
					this.strPkgBreakUp = strPkgBreakUp;
				}
				public String getStrCost() {
					return strCost;
				}
				public void setStrCost(String strCost) {
					this.strCost = strCost;
				}
				public String getStrUnitName() {
					return strUnitName;
				}
				public void setStrUnitName(String strUnitName) {
					this.strUnitName = strUnitName;
				}
				public String getStrIsRefund() {
					return strIsRefund;
				}
				public void setStrIsRefund(String strIsRefund) {
					this.strIsRefund = strIsRefund;
				}
				public String getStrIsAdavnce() {
					return strIsAdavnce;
				}
				public void setStrIsAdavnce(String strIsAdavnce) {
					this.strIsAdavnce = strIsAdavnce;
				}
				public String getStrParticulaDtl() {
					return strParticulaDtl;
				}
				public void setStrParticulaDtl(String strParticulaDtl) {
					this.strParticulaDtl = strParticulaDtl;
				}
				public String getStrTotalExpenseAmt() {
					return strTotalExpenseAmt;
				}
				public void setStrTotalExpenseAmt(String strTotalExpenseAmt) {
					this.strTotalExpenseAmt = strTotalExpenseAmt;
				}
				public String getStrTotalDisAmt() {
					return strTotalDisAmt;
				}
				public void setStrTotalDisAmt(String strTotalDisAmt) {
					this.strTotalDisAmt = strTotalDisAmt;
				}
				public String getStrTotalReceAmtByPatient() {
					return strTotalReceAmtByPatient;
				}
				public void setStrTotalReceAmtByPatient(String strTotalReceAmtByPatient) {
					this.strTotalReceAmtByPatient = strTotalReceAmtByPatient;
				}
				public String getStrTotalBenifitFrmClt() {
					return strTotalBenifitFrmClt;
				}
				public void setStrTotalBenifitFrmClt(String strTotalBenifitFrmClt) {
					this.strTotalBenifitFrmClt = strTotalBenifitFrmClt;
				}
				public String getStrNetPaymentAmt() {
					return strNetPaymentAmt;
				}
				public void setStrNetPaymentAmt(String strNetPaymentAmt) {
					this.strNetPaymentAmt = strNetPaymentAmt;
				}
				public String getStrOffLineTariffDiscountUnit() {
					return strOffLineTariffDiscountUnit;
				}
				public void setStrOffLineTariffDiscountUnit(String strOffLineTariffDiscountUnit) {
					this.strOffLineTariffDiscountUnit = strOffLineTariffDiscountUnit;
				}
				public String getStrOffLineTariffDiscountType() {
					return strOffLineTariffDiscountType;
				}
				public void setStrOffLineTariffDiscountType(String strOffLineTariffDiscountType) {
					this.strOffLineTariffDiscountType = strOffLineTariffDiscountType;
				}
				public String getStrOffLineTariffDiscountBy() {
					return strOffLineTariffDiscountBy;
				}
				public void setStrOffLineTariffDiscountBy(String strOffLineTariffDiscountBy) {
					this.strOffLineTariffDiscountBy = strOffLineTariffDiscountBy;
				}
				public String getStrOfflineDiscountApprovedByDetails() {
					return strOfflineDiscountApprovedByDetails;
				}
				public void setStrOfflineDiscountApprovedByDetails(
						String strOfflineDiscountApprovedByDetails) {
					this.strOfflineDiscountApprovedByDetails = strOfflineDiscountApprovedByDetails;
				}
				public String getStrOffLineTariffDiscountReason() {
					return strOffLineTariffDiscountReason;
				}
				public void setStrOffLineTariffDiscountReason(
						String strOffLineTariffDiscountReason) {
					this.strOffLineTariffDiscountReason = strOffLineTariffDiscountReason;
				}
				public String getStrOfflineDiscountRemarksDetails() {
					return strOfflineDiscountRemarksDetails;
				}
				public void setStrOfflineDiscountRemarksDetails(
						String strOfflineDiscountRemarksDetails) {
					this.strOfflineDiscountRemarksDetails = strOfflineDiscountRemarksDetails;
				}
				public String getStrOffLineTariffDiscountDate() {
					return strOffLineTariffDiscountDate;
				}
				public void setStrOffLineTariffDiscountDate(String strOffLineTariffDiscountDate) {
					this.strOffLineTariffDiscountDate = strOffLineTariffDiscountDate;
				}
				public String getStrOnlinePaymentMode() {
					return strOnlinePaymentMode;
				}
				public void setStrOnlinePaymentMode(String strOnlinePaymentMode) {
					this.strOnlinePaymentMode = strOnlinePaymentMode;
				}
				public String getStrOnlinePaymentDtls() {
					return strOnlinePaymentDtls;
				}
				public void setStrOnlinePaymentDtls(String strOnlinePaymentDtls) {
					this.strOnlinePaymentDtls = strOnlinePaymentDtls;
				}
				public String getStrOnlineAmount() {
					return strOnlineAmount;
				}
				public void setStrOnlineAmount(String strOnlineAmount) {
					this.strOnlineAmount = strOnlineAmount;
				}
				
				public String getStrOfflinePaymentMode() {
					return strOfflinePaymentMode;
				}
				public void setStrOfflinePaymentMode(String strOfflinePaymentMode) {
					this.strOfflinePaymentMode = strOfflinePaymentMode;
				}
				public String getStrOfflinePaymentDtls() {
					return strOfflinePaymentDtls;
				}
				public void setStrOfflinePaymentDtls(String strOfflinePaymentDtls) {
					this.strOfflinePaymentDtls = strOfflinePaymentDtls;
				}
				public String getStrOfflineAmount() {
					return strOfflineAmount;
				}
				public void setStrOfflineAmount(String strOfflineAmount) {
					this.strOfflineAmount = strOfflineAmount;
				}
				
				public String[] getStrOfflinePackageName() {
					return strOfflinePackageName;
				}
				public void setStrOfflinePackageName(String[] strOfflinePackageName) {
					this.strOfflinePackageName = strOfflinePackageName;
				}
				public String[] getStrOfflinePackageRateUnit() {
					return strOfflinePackageRateUnit;
				}
				public void setStrOfflinePackageRateUnit(String[] strOfflinePackageRateUnit) {
					this.strOfflinePackageRateUnit = strOfflinePackageRateUnit;
				}
				public String[] getStrOfflinePackageReqQty() {
					return strOfflinePackageReqQty;
				}
				public void setStrOfflinePackageReqQty(String[] strOfflinePackageReqQty) {
					this.strOfflinePackageReqQty = strOfflinePackageReqQty;
				}
				public String[] getStrOfflinePackageUnit() {
					return strOfflinePackageUnit;
				}
				public void setStrOfflinePackageUnit(String[] strOfflinePackageUnit) {
					this.strOfflinePackageUnit = strOfflinePackageUnit;
				}
				public String[] getStrOfflinePackageServiceTax() {
					return strOfflinePackageServiceTax;
				}
				public void setStrOfflinePackageServiceTax(String[] strOfflinePackageServiceTax) {
					this.strOfflinePackageServiceTax = strOfflinePackageServiceTax;
				}
				public String[] getStrOfflinePackageDiscount() {
					return strOfflinePackageDiscount;
				}
				public void setStrOfflinePackageDiscount(String[] strOfflinePackageDiscount) {
					this.strOfflinePackageDiscount = strOfflinePackageDiscount;
				}
				public String[] getStrOfflinePackageNetAmount() {
					return strOfflinePackageNetAmount;
				}
				public void setStrOfflinePackageNetAmount(String[] strOfflinePackageNetAmount) {
					this.strOfflinePackageNetAmount = strOfflinePackageNetAmount;
				}
				public String getStrOfflineGroupDetails() {
					return strOfflineGroupDetails;
				}
				public void setStrOfflineGroupDetails(String strOfflineGroupDetails) {
					this.strOfflineGroupDetails = strOfflineGroupDetails;
				}
				public String getStrOfflinePackageGroup() {
					return strOfflinePackageGroup;
				}
				public void setStrOfflinePackageGroup(String strOfflinePackageGroup) {
					this.strOfflinePackageGroup = strOfflinePackageGroup;
				}
				public String getStrOfflineTarffList() {
					return strOfflineTarffList;
				}
				public void setStrOfflineTarffList(String strOfflineTarffList) {
					this.strOfflineTarffList = strOfflineTarffList;
				}
				public String getStrOfflineDiscountApprovedBy() {
					return strOfflineDiscountApprovedBy;
				}
				public void setStrOfflineDiscountApprovedBy(String strOfflineDiscountApprovedBy) {
					this.strOfflineDiscountApprovedBy = strOfflineDiscountApprovedBy;
				}
				public String getStrOfflineDiscountRemarks() {
					return strOfflineDiscountRemarks;
				}
				public void setStrOfflineDiscountRemarks(String strOfflineDiscountRemarks) {
					this.strOfflineDiscountRemarks = strOfflineDiscountRemarks;
				}
				public String getStrHidden() {
					return strHidden;
				}
				public void setStrHidden(String strHidden) {
					this.strHidden = strHidden;
				}
				public String getStrOfflineTariffDetails() {
					return strOfflineTariffDetails;
				}
				public void setStrOfflineTariffDetails(String strOfflineTariffDetails) {
					this.strOfflineTariffDetails = strOfflineTariffDetails;
				}
				public String getStrBillAprovalDtl1() {
					return strBillAprovalDtl1;
				}
				public void setStrBillAprovalDtl1(String strBillAprovalDtl1) {
					this.strBillAprovalDtl1 = strBillAprovalDtl1;
				}
				public String getStrBillAprovalDtl2() {
					return strBillAprovalDtl2;
				}
				public void setStrBillAprovalDtl2(String strBillAprovalDtl2) {
					this.strBillAprovalDtl2 = strBillAprovalDtl2;
				}
				
				public String[] getStrOfflineTariffQty() {
					return strOfflineTariffQty;
				}
				public void setStrOfflineTariffQty(String[] strOfflineTariffQty) {
					this.strOfflineTariffQty = strOfflineTariffQty;
				}
				public String[] getStrOfflineTariffUnit() {
					return strOfflineTariffUnit;
				}
				public void setStrOfflineTariffUnit(String[] strOfflineTariffUnit) {
					this.strOfflineTariffUnit = strOfflineTariffUnit;
				}
				public String[] getStrOfflineTariffServiceTax() {
					return strOfflineTariffServiceTax;
				}
				public void setStrOfflineTariffServiceTax(String[] strOfflineTariffServiceTax) {
					this.strOfflineTariffServiceTax = strOfflineTariffServiceTax;
				}
				
				public String[] getStrOfflineTariffNetAmount() {
					return strOfflineTariffNetAmount;
				}
				public void setStrOfflineTariffNetAmount(String[] strOfflineTariffNetAmount) {
					this.strOfflineTariffNetAmount = strOfflineTariffNetAmount;
				}
				public String[] getStrOfflineTariffDetailsHidden() {
					return strOfflineTariffDetailsHidden;
				}
				public void setStrOfflineTariffDetailsHidden(
						String[] strOfflineTariffDetailsHidden) {
					this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
				}
				public String getStrTariffDiscountAmtConfgDtlBillApproval() {
					return strTariffDiscountAmtConfgDtlBillApproval;
				}
				public void setStrTariffDiscountAmtConfgDtlBillApproval(
						String strTariffDiscountAmtConfgDtlBillApproval) {
					this.strTariffDiscountAmtConfgDtlBillApproval = strTariffDiscountAmtConfgDtlBillApproval;
				}
				public String getStrTotalServiceTax() {
					return strTotalServiceTax;
				}
				public void setStrTotalServiceTax(String strTotalServiceTax) {
					this.strTotalServiceTax = strTotalServiceTax;
				}
				public String getStrNetPaybleAmt() {
					return strNetPaybleAmt;
				}
				public void setStrNetPaybleAmt(String strNetPaybleAmt) {
					this.strNetPaybleAmt = strNetPaybleAmt;
				}
				public String getStrMaxBenifitAmt() {
					return strMaxBenifitAmt;
				}
				public void setStrMaxBenifitAmt(String strMaxBenifitAmt) {
					this.strMaxBenifitAmt = strMaxBenifitAmt;
				}
				public String getStrReceivedAmt() {
					return strReceivedAmt;
				}
				public void setStrReceivedAmt(String strReceivedAmt) {
					this.strReceivedAmt = strReceivedAmt;
				}
				public String[] getChkBox1() {
					return chkBox1;
				}
				public void setChkBox1(String[] chkBox1) {
					this.chkBox1 = chkBox1;
				}
				public String[] getStrReqDate() {
					return strReqDate;
				}
				public void setStrReqDate(String[] strReqDate) {
					this.strReqDate = strReqDate;
				}
				
				public String[] getStrUnProcessQty() {
					return strUnProcessQty;
				}
				public void setStrUnProcessQty(String[] strUnProcessQty) {
					this.strUnProcessQty = strUnProcessQty;
				}
				public String[] getStrRefundQty() {
					return strRefundQty;
				}
				public void setStrRefundQty(String[] strRefundQty) {
					this.strRefundQty = strRefundQty;
				}
				public String[] getStrUnitCombo() {
					return strUnitCombo;
				}
				public void setStrUnitCombo(String[] strUnitCombo) {
					this.strUnitCombo = strUnitCombo;
				}
				public String[] getStrUnProcNetAmt() {
					return strUnProcNetAmt;
				}
				public void setStrUnProcNetAmt(String[] strUnProcNetAmt) {
					this.strUnProcNetAmt = strUnProcNetAmt;
				}
				public String[] getStrUnProcTarriffName() {
					return strUnProcTarriffName;
				}
				public void setStrUnProcTarriffName(String[] strUnProcTarriffName) {
					this.strUnProcTarriffName = strUnProcTarriffName;
				}
				public String[] getStrUnProcHiddVal() {
					return strUnProcHiddVal;
				}
				public void setStrUnProcHiddVal(String[] strUnProcHiddVal) {
					this.strUnProcHiddVal = strUnProcHiddVal;
				}
				public String[] getStrDiscAmt() {
					return strDiscAmt;
				}
				public void setStrDiscAmt(String[] strDiscAmt) {
					this.strDiscAmt = strDiscAmt;
				}
				public String[] getStrSeviceTaxAmt() {
					return strSeviceTaxAmt;
				}
				public void setStrSeviceTaxAmt(String[] strSeviceTaxAmt) {
					this.strSeviceTaxAmt = strSeviceTaxAmt;
				}
				
				public String getTotalRecAmtDivId1() {
					return totalRecAmtDivId1;
				}
				public void setTotalRecAmtDivId1(String totalRecAmtDivId1) {
					this.totalRecAmtDivId1 = totalRecAmtDivId1;
				}
				public String getStrDeptCode() {
					return strDeptCode;
				}
				public void setStrDeptCode(String strDeptCode) {
					this.strDeptCode = strDeptCode;
				}
				public String getStrIpdThirdPartyBilling() {
					return strIpdThirdPartyBilling;
				}
				public void setStrIpdThirdPartyBilling(String strIpdThirdPartyBilling) {
					this.strIpdThirdPartyBilling = strIpdThirdPartyBilling;
				}
				public String getStrIpdRoundOff() {
					return strIpdRoundOff;
				}
				public void setStrIpdRoundOff(String strIpdRoundOff) {
					this.strIpdRoundOff = strIpdRoundOff;
				}
				public String getStrPatientDetailsView() {
					return strPatientDetailsView;
				}
				public void setStrPatientDetailsView(String strPatientDetailsView) {
					this.strPatientDetailsView = strPatientDetailsView;
				}
				public String getStrChkBoxComboValue() {
					return strChkBoxComboValue;
				}
				public void setStrChkBoxComboValue(String strChkBoxComboValue) {
					this.strChkBoxComboValue = strChkBoxComboValue;
				}
				public String getStrChkRcdOpen() {
					return strChkRcdOpen;
				}
				public void setStrChkRcdOpen(String strChkRcdOpen) {
					this.strChkRcdOpen = strChkRcdOpen;
				}
				public String getStrBillTypeCombo() {
					return strBillTypeCombo;
				}
				public void setStrBillTypeCombo(String strBillTypeCombo) {
					this.strBillTypeCombo = strBillTypeCombo;
				}
				public String getStrReportFormat() {
					return strReportFormat;
				}
				public void setStrReportFormat(String strReportFormat) {
					this.strReportFormat = strReportFormat;
				}
				public String getStrReportId() {
					return strReportId;
				}
				public void setStrReportId(String strReportId) {
					this.strReportId = strReportId;
				}
				public String getStrVal() {
					return strVal;
				}
				public void setStrVal(String strVal) {
					this.strVal = strVal;
				}
				public WebRowSet getStrParticularDtlsListWs() {
					return strParticularDtlsListWs;
				}
				public void setStrParticularDtlsListWs(WebRowSet strParticularDtlsListWs) {
					this.strParticularDtlsListWs = strParticularDtlsListWs;
				}
				public WebRowSet getStrParticularDtlsWs() {
					return strParticularDtlsWs;
				}
				public void setStrParticularDtlsWs(WebRowSet strParticularDtlsWs) {
					this.strParticularDtlsWs = strParticularDtlsWs;
				}
				public String[] getChkBoxHidden() {
					return chkBoxHidden;
				}
				public void setChkBoxHidden(String[] chkBoxHidden) {
					this.chkBoxHidden = chkBoxHidden;
				}
				public String[] getStrNetAmt() {
					return strNetAmt;
				}
				public void setStrNetAmt(String[] strNetAmt) {
					this.strNetAmt = strNetAmt;
				}
				public String[] getStrServiceTax() {
					return strServiceTax;
				}
				public void setStrServiceTax(String[] strServiceTax) {
					this.strServiceTax = strServiceTax;
				}
				public String[] getStrOfflineTariffServiceTaxAmtVal() {
					return strOfflineTariffServiceTaxAmtVal;
				}
				public void setStrOfflineTariffServiceTaxAmtVal(
						String[] strOfflineTariffServiceTaxAmtVal) {
					this.strOfflineTariffServiceTaxAmtVal = strOfflineTariffServiceTaxAmtVal;
				}
				public String[] getStrOfflineActualTariffAmtVal() {
					return strOfflineActualTariffAmtVal;
				}
				public void setStrOfflineActualTariffAmtVal(
						String[] strOfflineActualTariffAmtVal) {
					this.strOfflineActualTariffAmtVal = strOfflineActualTariffAmtVal;
				}
				public String[] getStrOfflineTariffRateUnit() {
					return strOfflineTariffRateUnit;
				}
				public void setStrOfflineTariffRateUnit(String[] strOfflineTariffRateUnit) {
					this.strOfflineTariffRateUnit = strOfflineTariffRateUnit;
				}
				public String[] getStrActualTariffNetAmt() {
					return strActualTariffNetAmt;
				}
				public void setStrActualTariffNetAmt(String[] strActualTariffNetAmt) {
					this.strActualTariffNetAmt = strActualTariffNetAmt;
				}
				public String getStrIsIpdDiscount() {
					return strIsIpdDiscount;
				}
				public void setStrIsIpdDiscount(String strIsIpdDiscount) {
					this.strIsIpdDiscount = strIsIpdDiscount;
				}
				public String getStrExcessCreditLimit() {
					return strExcessCreditLimit;
				}
				public void setStrExcessCreditLimit(String strExcessCreditLimit) {
					this.strExcessCreditLimit = strExcessCreditLimit;
				}
				/**
				 * @return the strOffLineTariffDiscountReasonText
				 */
				public String getStrOffLineTariffDiscountReasonText() {
					return strOffLineTariffDiscountReasonText;
				}
				/**
				 * @param strOffLineTariffDiscountReasonText the strOffLineTariffDiscountReasonText to set
				 */
				public void setStrOffLineTariffDiscountReasonText(
						String strOffLineTariffDiscountReasonText) {
					this.strOffLineTariffDiscountReasonText = strOffLineTariffDiscountReasonText;
				}
				public String getStrIpdBillManagementMode() {
					return strIpdBillManagementMode;
				}
				public void setStrIpdBillManagementMode(String strIpdBillManagementMode) {
					this.strIpdBillManagementMode = strIpdBillManagementMode;
				}
				public String getStrNewTreatmentCategory() {
					return strNewTreatmentCategory;
				}
				public void setStrNewTreatmentCategory(String strNewTreatmentCategory) {
					this.strNewTreatmentCategory = strNewTreatmentCategory;
				}
				public String getStrStartDate() {
					return strStartDate;
				}
				public void setStrStartDate(String strStartDate) {
					this.strStartDate = strStartDate;
				}
				public String getStrEndDate() {
					return strEndDate;
				}
				public void setStrEndDate(String strEndDate) {
					this.strEndDate = strEndDate;
				}
				public String getStrWardType() {
					return strWardType;
				}
				public void setStrWardType(String strWardType) {
					this.strWardType = strWardType;
				}
				public String getStrTariffCode() {
					return strTariffCode;
				}
				public void setStrTariffCode(String strTariffCode) {
					this.strTariffCode = strTariffCode;
				}
				public String[] getStrCompChargeCheck() {
					return strCompChargeCheck;
				}
				public void setStrCompChargeCheck(String[] strCompChargeCheck) {
					this.strCompChargeCheck = strCompChargeCheck;
				}
				public String getStrSpecialWardType() {
					return strSpecialWardType;
				}
				public void setStrSpecialWardType(String strSpecialWardType) {
					this.strSpecialWardType = strSpecialWardType;
				}
				public String getStrSpecialWardTypeValues() {
					return strSpecialWardTypeValues;
				}
				public void setStrSpecialWardTypeValues(String strSpecialWardTypeValues) {
					this.strSpecialWardTypeValues = strSpecialWardTypeValues;
				}
				public String getStrDepartmentValues() {
					return strDepartmentValues;
				}
				public void setStrDepartmentValues(String strDepartmentValues) {
					this.strDepartmentValues = strDepartmentValues;
				}
				public String getStrDepartment() {
					return strDepartment;
				}
				public void setStrDepartment(String strDepartment) {
					this.strDepartment = strDepartment;
				}
				public String[] getStrSpecialChargeCheck() {
					return strSpecialChargeCheck;
				}
				public void setStrSpecialChargeCheck(String[] strSpecialChargeCheck) {
					this.strSpecialChargeCheck = strSpecialChargeCheck;
				}
				public String getStrPreviousDates() {
					return strPreviousDates;
				}
				public void setStrPreviousDates(String strPreviousDates) {
					this.strPreviousDates = strPreviousDates;
				}
				public String getStrPreviousDateValues() {
					return strPreviousDateValues;
				}
				public void setStrPreviousDateValues(String strPreviousDateValues) {
					this.strPreviousDateValues = strPreviousDateValues;
				}
				public String[] getStrPreviousCheck() {
					return strPreviousCheck;
				}
				public void setStrPreviousCheck(String[] strPreviousCheck) {
					this.strPreviousCheck = strPreviousCheck;
				}
				public String getStrSearchLetter() {
					return strSearchLetter;
				}
				public void setStrSearchLetter(String strSearchLetter) {
					this.strSearchLetter = strSearchLetter;
				}
				public String getStrTempWardCode() {
					return strTempWardCode;
				}
				public void setStrTempWardCode(String strTempWardCode) {
					this.strTempWardCode = strTempWardCode;
				}
				public String getStrAdmissionDtlHidVal() {
					return strAdmissionDtlHidVal;
				}
				public void setStrAdmissionDtlHidVal(String strAdmissionDtlHidVal) {
					this.strAdmissionDtlHidVal = strAdmissionDtlHidVal;
				}
				public String getStrChkVal() {
					return strChkVal;
				}
				public void setStrChkVal(String strChkVal) {
					this.strChkVal = strChkVal;
				}
				public String getStrPresentAcctStatusCode() {
					return strPresentAcctStatusCode;
				}
				public void setStrPresentAcctStatusCode(String strPresentAcctStatusCode) {
					this.strPresentAcctStatusCode = strPresentAcctStatusCode;
				}
				public String getDietChargeId() {
					return dietChargeId;
				}
				public void setDietChargeId(String dietChargeId) {
					this.dietChargeId = dietChargeId;
				}
				public String getStrAgeInMonths() {
					return strAgeInMonths;
				}
				public void setStrAgeInMonths(String strAgeInMonths) {
					this.strAgeInMonths = strAgeInMonths;
				}
				public String getStrAccStatusCombo() {
					return strAccStatusCombo;
				}
				public void setStrAccStatusCombo(String strAccStatusCombo) {
					this.strAccStatusCombo = strAccStatusCombo;
				}
				public String getStrConsumableCharge() {
					return strConsumableCharge;
				}
				public void setStrConsumableCharge(String strConsumableCharge) {
					this.strConsumableCharge = strConsumableCharge;
				}
				public String getStrOfflineTotalPayAmountWithoutConsumable() {
					return strOfflineTotalPayAmountWithoutConsumable;
				}
				public void setStrOfflineTotalPayAmountWithoutConsumable(
						String strOfflineTotalPayAmountWithoutConsumable) {
					this.strOfflineTotalPayAmountWithoutConsumable = strOfflineTotalPayAmountWithoutConsumable;
				}
				public String getStrGroupIdForConsumableConcatenated() {
					return strGroupIdForConsumableConcatenated;
				}
				public void setStrGroupIdForConsumableConcatenated(
						String strGroupIdForConsumableConcatenated) {
					this.strGroupIdForConsumableConcatenated = strGroupIdForConsumableConcatenated;
				}
				public String getStrConsumableChargesGroupId() {
					return strConsumableChargesGroupId;
				}
				public void setStrConsumableChargesGroupId(String strConsumableChargesGroupId) {
					this.strConsumableChargesGroupId = strConsumableChargesGroupId;
				}
				public String getStrConsumableChargesTariffCode() {
					return strConsumableChargesTariffCode;
				}
				public void setStrConsumableChargesTariffCode(
						String strConsumableChargesTariffCode) {
					this.strConsumableChargesTariffCode = strConsumableChargesTariffCode;
				}
				public String[] getStrOfflineTariffDate() {
					return strOfflineTariffDate;
				}
				public void setStrOfflineTariffDate(String[] strOfflineTariffDate) {
					this.strOfflineTariffDate = strOfflineTariffDate;
				}
				public String getStrUrgSur() {
					return strUrgSur;
				}
				public void setStrUrgSur(String strUrgSur) {
					this.strUrgSur = strUrgSur;
				}
				public String[] getStrPriority() {
					return strPriority;
				}
				public void setStrPriority(String[] strPriority) {
					this.strPriority = strPriority;
				}
				public String[] getStrDiscount() {
					return strDiscount;
				}
				public void setStrDiscount(String[] strDiscount) {
					this.strDiscount = strDiscount;
				}
				public String[] getStrDiscountType() {
					return strDiscountType;
				}
				public void setStrDiscountType(String[] strDiscountType) {
					this.strDiscountType = strDiscountType;
				}
				public String getStrAllTrfHLP() {
					return strAllTrfHLP;
				}
				public void setStrAllTrfHLP(String strAllTrfHLP) {
					this.strAllTrfHLP = strAllTrfHLP;
				}
				public String[] getStrDiscountAmt() {
					return strDiscountAmt;
				}
				public void setStrDiscountAmt(String[] strDiscountAmt) {
					this.strDiscountAmt = strDiscountAmt;
				}
				public String[] getStrOfflineTariffName() {
					return strOfflineTariffName;
				}
				public void setStrOfflineTariffName(String[] strOfflineTariffName) {
					this.strOfflineTariffName = strOfflineTariffName;
				}
				public String getStrIsCalledFromIpdBillNew() {
					return strIsCalledFromIpdBillNew;
				}
				public void setStrIsCalledFromIpdBillNew(String strIsCalledFromIpdBillNew) {
					this.strIsCalledFromIpdBillNew = strIsCalledFromIpdBillNew;
				}
				public String getStrAcctStatMode() {
					return strAcctStatMode;
				}
				public void setStrAcctStatMode(String strAcctStatMode) {
					this.strAcctStatMode = strAcctStatMode;
				}
				public java.util.LinkedHashMap<String, String> getLhm() 
				{
					lhm.put(" Final Bill Processing ", "ADDSERVICE");
					lhm.put(" Bed Transfers ", "BEDTRANSFER");
					lhm.put(" View Consolidated/Detail Bill", "VIEWBILL");
					lhm.put(" Finalize Bill ", "BILLAPPROVAL");
					lhm.put(" No Dues Printing ", "NODUESPRINT");
					//lhm.put(" Final Bill Data Editing ", "IPDBILLREOPEN");
					lhm.put(" Update Account ", "UPDATEACCOUNTSTATUS");
					return lhm;
				}
				public String getStrAllTrfJSON() {
					return strAllTrfJSON;
				}
				public void setStrAllTrfJSON(String strAllTrfJSON) {
					this.strAllTrfJSON = strAllTrfJSON;
				}
				public String getStrNumRows() {
					return strNumRows;
				}
				public void setStrNumRows(String strNumRows) {
					this.strNumRows = strNumRows;
				}
				public String[] getDeleteFlag() {
					return deleteFlag;
				}
				public void setDeleteFlag(String[] deleteFlag) {
					this.deleteFlag = deleteFlag;
				}
				public String[] getStrPrevReqNo() {
					return strPrevReqNo;
				}
				public void setStrPrevReqNo(String[] strPrevReqNo) {
					this.strPrevReqNo = strPrevReqNo;
				}
				public String getStrPrevBedTransfer() {
					return strPrevBedTransfer;
				}
				public void setStrPrevBedTransfer(String strPrevBedTransfer) {
					this.strPrevBedTransfer = strPrevBedTransfer;
				}
				public String[] getStrTransferDeptCode() {
					return strTransferDeptCode;
				}
				public void setStrTransferDeptCode(String[] strTransferDeptCode) {
					this.strTransferDeptCode = strTransferDeptCode;
				}
				public String[] getStrTransferWardCode() {
					return strTransferWardCode;
				}
				public void setStrTransferWardCode(String[] strTransferWardCode) {
					this.strTransferWardCode = strTransferWardCode;
				}
				public String[] getStrTransferChargeType() {
					return strTransferChargeType;
				}
				public void setStrTransferChargeType(String[] strTransferChargeType) {
					this.strTransferChargeType = strTransferChargeType;
				}
				public String[] getStrInDate() {
					return strInDate;
				}
				public void setStrInDate(String[] strInDate) {
					this.strInDate = strInDate;
				}
				public String[] getStrOutDate() {
					return strOutDate;
				}
				public void setStrOutDate(String[] strOutDate) {
					this.strOutDate = strOutDate;
				}
				public String[] getStrMovNo() {
					return strMovNo;
				}
				public void setStrMovNo(String[] strMovNo) {
					this.strMovNo = strMovNo;
				}
				public String[] getStrSaveFlag() {
					return strSaveFlag;
				}
				public void setStrSaveFlag(String[] strSaveFlag) {
					this.strSaveFlag = strSaveFlag;
				}
				public String getStrcurrentdate() {
					return strcurrentdate;
				}
				public void setStrcurrentdate(String strcurrentdate) {
					this.strcurrentdate = strcurrentdate;
				}
				public String getStrPatientAdmndtl() {
					return strPatientAdmndtl;
				}
				public void setStrPatientAdmndtl(String strPatientAdmndtl) {
					this.strPatientAdmndtl = strPatientAdmndtl;
				}
				public String getPrintFlag() {
					return printFlag;
				}
				public void setPrintFlag(String printFlag) {
					this.printFlag = printFlag;
				}
				public String getStrDischargeDate() {
					return strDischargeDate;
				}
				public void setStrDischargeDate(String strDischargeDate) {
					this.strDischargeDate = strDischargeDate;
				}
				public String getStrBillNo() {
					return strBillNo;
				}
				public void setStrBillNo(String strBillNo) {
					this.strBillNo = strBillNo;
				}
				public String getFilePath() {
					return filePath;
				}
				public void setFilePath(String filePath) {
					this.filePath = filePath;
				}
				public String getIsOpenPopUp() {
					return isOpenPopUp;
				}
				public void setIsOpenPopUp(String isOpenPopUp) {
					this.isOpenPopUp = isOpenPopUp;
				}
				public String getPrintMode() {
					return printMode;
				}
				public void setPrintMode(String printMode) {
					this.printMode = printMode;
				}
				public String getGrpid() {
					return grpid;
				}
				public void setGrpid(String grpid) {
					this.grpid = grpid;
				}
				public String getStrchkvalue() {
					return strchkvalue;
				}
				public void setStrchkvalue(String strchkvalue) {
					this.strchkvalue = strchkvalue;
				}
				public String getStrUnitold() {
					return strUnitold;
				}
				public void setStrUnitold(String strUnitold) {
					this.strUnitold = strUnitold;
				}
				public String getStrUnitNameValues() {
					return strUnitNameValues;
				}
				public void setStrUnitNameValues(String strUnitNameValues) {
					this.strUnitNameValues = strUnitNameValues;
				}
				public String getStrUnitNew() {
					return strUnitNew;
				}
				public void setStrUnitNew(String strUnitNew) {
					this.strUnitNew = strUnitNew;
				}
				public String getStrDocNameValues() {
					return strDocNameValues;
				}
				public void setStrDocNameValues(String strDocNameValues) {
					this.strDocNameValues = strDocNameValues;
				}
				public String getStrDocOld() {
					return strDocOld;
				}
				public void setStrDocOld(String strDocOld) {
					this.strDocOld = strDocOld;
				}
				public String getStrDocNew() {
					return strDocNew;
				}
				public void setStrDocNew(String strDocNew) {
					this.strDocNew = strDocNew;
				}
				public String[] getStrIsDoubleOc() {
					return strIsDoubleOc;
				}
				public void setStrIsDoubleOc(String[] strIsDoubleOc) {
					this.strIsDoubleOc = strIsDoubleOc;
				}
				public String[] getStrDblOccDate() {
					return strDblOccDate;
				}
				public void setStrDblOccDate(String[] strDblOccDate) {
					this.strDblOccDate = strDblOccDate;
				}
				
				public String[] getStrConsultant() {
					return strConsultant;
				}
				public void setStrConsultant(String[] strConsultant) {
					this.strConsultant = strConsultant;
				}
				public String[] getStrUnitCode() {
					return strUnitCode;
				}
				public void setStrUnitCode(String[] strUnitCode) {
					this.strUnitCode = strUnitCode;
				}
				public String getStrAccStatus() {
					return strAccStatus;
				}
				public void setStrAccStatus(String strAccStatus) {
					this.strAccStatus = strAccStatus;
				}
				public String getStrArogyaIpdCreditLimit() {
					return strArogyaIpdCreditLimit;
				}
				public void setStrArogyaIpdCreditLimit(String strArogyaIpdCreditLimit) {
					this.strArogyaIpdCreditLimit = strArogyaIpdCreditLimit;
				}
				public String getFinalBillFlag() {
					return finalBillFlag;
				}
				public void setFinalBillFlag(String finalBillFlag) {
					this.finalBillFlag = finalBillFlag;
				}
				public String[] getsNo() {
					return sNo;
				}
				public void setsNo(String[] sNo) {
					this.sNo = sNo;
				}
				public String[] getAdtOnlineFlag() {
					return adtOnlineFlag;
				}
				public void setAdtOnlineFlag(String[] adtOnlineFlag) {
					this.adtOnlineFlag = adtOnlineFlag;
				}
				public String getStrPatAcctStatus() {
					return strPatAcctStatus;
				}
				public void setStrPatAcctStatus(String strPatAcctStatus) {
					this.strPatAcctStatus = strPatAcctStatus;
				}
				
				
			}