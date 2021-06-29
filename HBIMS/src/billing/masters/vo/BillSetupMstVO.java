

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: BillSetup Master VO
 * 
 * Created on: 16-09-2011
 */



package billing.masters.vo;

import hisglobal.vo.ValueObject;

import java.util.LinkedHashMap;

public class BillSetupMstVO extends ValueObject {

	private static final long serialVersionUID = 02L;

	java.util.LinkedHashMap<String, String> lhm = new java.util.LinkedHashMap<String, String>();
	java.util.LinkedHashMap<String, String> lhm1 = new java.util.LinkedHashMap<String, String>();
	private int totalRows = 0;
	private String admittedPatient = "0";
	private String generalActivity = "0";
	private String strTotalRows = "0";

	private String strTotal3Rows = "0";
	private String strHospitalCode = "0";
	private String strChargeTypeId = "";
	
	private String strGroupId=""; // added by PAWAN KUMAR B N for getting/setting Group ID
	private String strGroupIdValues="";
	private String strConsumableChargesGroupId="";				// added by Adil Wasi
	private String strConsumableChargesGroupIdValues="";		// added by Adil Wasi
	private String strDefaultTariffCode=""; 					// added by PAWAN KUMAR B N for getting/setting Tariff ID based on Group
	private String strConsumableChargesTariffCode=""; 			// added by Adil Wasi for getting/setting CONSUMABLE Charges Tariff ID
	private String strConsumableChargesTariffValues="";			// added by Adil Wasi for getting/setting CONSUMABLE Charges Tariff Values
	private String strGeneralTariffValues="";
	
	private String ipdGenAdvanceReq="";

	/* generalsetup_bill start */

	private String finStartYear = "0";
	private String inBound = "0";
	private String outBound = "0";
	private String cancelTime = "0";
	private String defNoRecords = "0";
	private String modRecords = "0";
	private String delRecords = "0";
	private String printCharge = "0";

	// private String thirdWardTypeValue="";
	private String[] thirdWardName = null;
	private int total3Rows = 0;

	private String strReceiptType = "1";
	private String strDuplicatePrint = "1";
	private String strRefundReceiptType = "1";

	private String strIsWithoutCrNoRequired = "0";
	private String strOnlineRefundRequestAllowed = "0";
	private String strCashCollectionOfflineRefundRequired = "0";
	private String strCreditCashlessAppRequired="";

	private String strPrintMessageLimit = "0";
	private String strCcConfirmationType = "0";

	private String strPreviousCrNoSearchingInt = "0";
	private String strPreviousCrNoSearchingExt = "0";

	private String strDayEndProcessType = "0";
	private String strDayEndDateType = "0";
	private String strDayEndNonBillingProcessType = "0";
	private String strDayEndNonBillingDateType = "0";
	private String strOpdReceiptType = "0";
	private String strIpdReceiptType = "0";
	private String strRefundAgainstRefundRequest = "0";
	private String strIntPatChk = "0";
	private String strReferringPhysicianRequiredInt = "0";
	private String strPreviousCrNoRequiredInt = "0";
	private String strChargeTypeInt = "0";
	private String strWardTypeInt = "0";
	private String strExtPatChk = "0";
	private String strReferringPhysicianRequiredExt = "0";
	private String strPreviousCrNoRequiredExt = "0";
	private String strChargeTypeExt = "0";
	private String strWardTypeExt = "0";
	private String strInternalPatient = "0";
	private String strExternalPatient = "0";
	private String strWardTypeValueInt = "0";
	private String strChargeTypeValueInt = "0";
	private String strWardTypeValueExt = "0";
	private String strChargeTypeValueExt = "0";
	private String strIsApprovalByRequired = "0";
	

	/* generalsetup_bill End */

	/* opdsetup_bill start */

	private String opdThirdPartyBilling = "0";
	private String opdDiscount = "0";
	private String opdDiscountClerk = "1";
	private String opdRefundPenalty = "0";
	private String opdRoundOff = "0";
	private String opdServiceTax = "0";
	private String opdFreeCategory = "0";

	/* opdsetup_bill End */

	/* ipdcompulsorychargessetup_bill variables start */
	private String groupName[] = null;
	private String secTreatmentCategory[] = null;
	private String tariffName[] = null;
	private String unit[] = null;
	private String secWardName[] = null;
	private String secGroupName[] = null;
	private String secTariffName[] = null;
	private String secUnit[] = null;
	private String thirdGroupName[] = null;
	private String thirdTariffName[] = null;
	private String thirdUnit[] = null;
	private String strSeatId = null;

	private String ipdComplChrgGroupValues = null;
	private String ipdComplChrgTariffValues = null;

	private String ipdGrpTmpName = "0";
	private String ipdTrfTmpName = "0";
	private String ipdUntTmpName = "0";

	private String ipdComplChrgWardValues = null;
	private String ipdComplChrgSecGroupValues = null;
	private String ipdComplChrgSecTreatmentCategoryValues = null;
	private String ipdComplChrgSecTariffValuesI = null;

	private String ipdSecWrdTmpName = "0";
	private String ipdSecTreatCatTempName = "0";
	private String ipdSecGrpTmpName = "0";
	private String ipdSecTrfTmpName = "0";
	private String ipdSecUntTmpName = "0";

	private String ipdComplChrgThirdGroupValues = null;
	private String ipdComplChrgThirdTariffValues = null;

	private String ipdThirdGrpTmpName = "0";

	private String ipdThirdTrfTmpName = "0";
	private String ipdThirdUntTmpName = "0";

	private String ipdMultiRow = null;
	private String ipdSecMultiRow = null;
	private String ipdThirdMultiRow = null;
	


	/* ipdcompulsorychargessetup_bill variables end */

	/* ipdbedcalculationrulesetup_bill variables start */

	private String ipdBedCalcWard = "0";
	private String ipdBedCalcWardCharg = "1";
	private String ipdBedCalcServiceCharge = "1";
	private String ipdBedCalcWardValues = null;

	/* ipdbedcalculationrulesetup_bill variables start */

	/* ipdgeneralsetup_bill variables start */

	private String ipdGenThirdPartyBilling = "0";
	private String ipdGenDiscount = "0";
	private String ipdGenClerkDiscount = "0";
	private String ipdGenPenalty = "0";
	private String ipdGenCreditBilling = "0";
	private String ipdGenExcessCreditLimit = "0";

	private String ipdGenCheckOutTimeHr = "00";
	private String ipdGenCheckOutTimeMn = "00";

	private String ipdGenCheckOutTimePrivateHr = "00";
	private String ipdGenCheckOutTimePrivateMn = "00";
	private String ipdGenFlexibleTime = "0";
	private String ipdGenFlexibleTimeAdmission = "0";
	private String ipdGenPayment = "0";
	private String ipdGenRoundOff = "0";
	private String ipdGenServiceTaxOnTotalBill = "0";
	private String ipdGenAdtProcessType = "1";
	private String ipdServiceTax = "0";
	private String ipdFreeCategory = "0";
	private String ipdGenReOpenValidity = "0";   // Added By Pawan Kumar B N on 7-03-2012
	/* ipdgeneralsetup_bill variables end */

	/* emergencysetup_bill start */

	private String emgThirdPartyBilling = "0";
	private String emgDiscount = "0";
	private String emgDiscountClerk = "0";
	private String emgRefundPenalty = "0";
	private String emgRoundOff = "0";
	private String[] emgGroupName = null;
	private String[] emgTariffName = null;
	private String[] emgUnit = null;

	private String emgGrpTmpName = "0";
	private String emgTrfTmpName = "0";
	private String emgUntTmpName = "0";

	private String emgGroupValues = null;
	private String emgTariffValues = null;

	private String emgMultiRow = null;
	private String emgServiceTax = "0";
	private String emgFreeCategory = "0";
	/* emergencysetup_bill End */

	/* billformatsetup_bill Start */

	private String billFormatHeader1 = "";
	private String billFormatHeader2 = "";
	private String billFormatHeader3 = "";
	private String billFormatHeader4 = "";
	private String billFormatFooter = "";
	private String billDisclaimerWithoutCrNo = "";
	private String billDisclaimerServices = "";
	private String billDisclaimerRefund = "";
	private String billDisclaimerAdvance = "";
	private String billDisclaimerPartPayment = "";
	private String billDisclaimerFinalBill = "";
	private String billDisclaimerDuplicatePrintRequired = "0";

	private String billLineOpdServices = "";

	/* billformatsetup_bill Start */

	/* jobssetup_bill Start */

	private String job1BedCharge = "0";
	private String job1CompusoryCharge = "0";
	private String job2FinStartYear = "0";
	private String job2EmgCompusoryCharge = "0";

	/* jobssetup_bill End */

	/* General Declaration */

	private String strErr = "";
	private String strMsg = "";

	/* General Declaration End */

	/*
	 * public void setAdmittedPatient(String admittedPatient) {
	 * if((this.job1BedCharge=="1") || (this.job1CompusoryCharge=="1")) {
	 * this.admittedPatient="1"; } }
	 */

	private String strGeneralWardType = "";
	private String strGeneralRoomType = "";

	private String strGeneralWardTypeValue = "";
	private String strGeneralRoomTypeValue = "";

	private String ipdGenServiceFreeTreatmentTime = "";

	private String[] secImposedChargeForTransfer = null;

	private String[] secNewBornCompCharge = null;

	private String ipdThirdWardTmpName = "";

	private String strIpdFreeCategoryValue = "";
	private String strOpdFreeCategoryValue = "";
	private String strEmgFreeCategoryValue = "";
	
	
	private String strPaidCategory="";
	private String strArogyaShreeCategory="";
	private String strArogyaShreeTSCategory="";
	private String strCGSHCategory="";
	private String strGenCategory="";

	private String StrPaidCategoryValue="";
	private String strArogyaShreeCategoryvalue="";
	private String strArogyaShreeTSCategoryvalue="";
	private String strCGSHCategoryvalue="";
	private String strGenCategoryValue="";

	private String strWSHCategoryValue="";
	private String strWSHCategory="";
	private String strDayEndTimeBoundFlag="";
	private String strDayEndTimeHour="";
	private String strDayEndTimeMin="";
	private String strDayEndTimeAMPM="";
	private String strDayEndTime="";
	private String strArogyaTSCreditLimit="";
	private String strUrgTrfSur="";
	private String logoReq="";
	private String strSurCc="";
	private String strSurDc="";
	private String strSurIc="";
	private String strSurId="";
	private String strSurCc1="";
	private String strSurDc1="";
	private String strSurIc1="";
	private String strSurId1="";
	private String strOstfCat="";
	private String strOSTFCategoryvalue="";
	private String niramayaValue="";
	private String niramaya="";
	private String hindiReq="";
	private String headerReq="";
	
	

	public String getNiramayaValue() {
	return niramayaValue;
}

public void setNiramayaValue(String niramayaValue) {
	this.niramayaValue = niramayaValue;
}

public String getNiramaya() {
	return niramaya;
}

public void setNiramaya(String niramaya) {
	this.niramaya = niramaya;
}

	public String getStrOSTFCategoryvalue() {
		return strOSTFCategoryvalue;
	}

	public void setStrOSTFCategoryvalue(String strOSTFCategoryvalue) {
		this.strOSTFCategoryvalue = strOSTFCategoryvalue;
	}
	
	public String getStrOstfCat() {
		return strOstfCat;
	}

	public void setStrOstfCat(String strOstfCat) {
		this.strOstfCat = strOstfCat;
	}

	public String getStrWSHCategoryValue() {
		return strWSHCategoryValue;
	}

	public void setStrWSHCategoryValue(String strWSHCategoryValue) {
		this.strWSHCategoryValue = strWSHCategoryValue;
	}

	public String getStrWSHCategory() {
		return strWSHCategory;
	}

	public void setStrWSHCategory(String strWSHCategory) {
		this.strWSHCategory = strWSHCategory;
	}

	public String getStrPaidCategoryValue() {
		return StrPaidCategoryValue;
	}

	public void setStrPaidCategoryValue(String strPaidCategoryValue) {
		StrPaidCategoryValue = strPaidCategoryValue;
	}

	public String getStrArogyaShreeCategoryvalue() {
		return strArogyaShreeCategoryvalue;
	}

	public void setStrArogyaShreeCategoryvalue(String strArogyaShreeCategoryvalue) {
		this.strArogyaShreeCategoryvalue = strArogyaShreeCategoryvalue;
	}

	public String getStrCGSHCategoryvalue() {
		return strCGSHCategoryvalue;
	}

	public void setStrCGSHCategoryvalue(String strCGSHCategoryvalue) {
		this.strCGSHCategoryvalue = strCGSHCategoryvalue;
	}

	public String getStrPaidCategory() {
		return strPaidCategory;
	}

	public void setStrPaidCategory(String strPaidCategory) {
		this.strPaidCategory = strPaidCategory;
	}

	public String getStrArogyaShreeCategory() {
		return strArogyaShreeCategory;
	}

	public void setStrArogyaShreeCategory(String strArogyaShreeCategory) {
		this.strArogyaShreeCategory = strArogyaShreeCategory;
	}

	public String getStrCGSHCategory() {
		return strCGSHCategory;
	}

	public void setStrCGSHCategory(String strCGSHCategory) {
		this.strCGSHCategory = strCGSHCategory;
	}

	public String getStrIpdFreeCategoryValue() {

		return strIpdFreeCategoryValue;
	}
	
	public String getStrOpdFreeCategoryValue() {

		return strOpdFreeCategoryValue;
	}
	
	
	public String getStrEmgFreeCategoryValue() {

		return strEmgFreeCategoryValue;
	}

	public void setIpdStrFreeCategoryValue(String strIpdFreeCategoryValue) {
		this.strIpdFreeCategoryValue = strIpdFreeCategoryValue;
	}

	public void setOpdStrFreeCategoryValue(String strOpdFreeCategoryValue) {
		this.strOpdFreeCategoryValue = strOpdFreeCategoryValue;
	}
	
	public void setEmgStrFreeCategoryValue(String strEmgFreeCategoryValue) {
		this.strEmgFreeCategoryValue = strEmgFreeCategoryValue;
	}
	
	/**
	 * @return the ipdThirdWardTmpName
	 */
	public String getIpdThirdWardTmpName() {
		return ipdThirdWardTmpName;
	}

	/**
	 * @param ipdThirdWardTmpName
	 *            the ipdThirdWardTmpName to set
	 */
	public void setIpdThirdWardTmpName(String ipdThirdWardTmpName) {
		this.ipdThirdWardTmpName = ipdThirdWardTmpName;
	}

	/**
	 * @return the secImposedChargeForTransfer
	 */
	public String[] getSecImposedChargeForTransfer() {
		return secImposedChargeForTransfer;
	}

	/**
	 * @param secImposedChargeForTransfer
	 *            the secImposedChargeForTransfer to set
	 */
	public void setSecImposedChargeForTransfer(
			String[] secImposedChargeForTransfer) {
		this.secImposedChargeForTransfer = secImposedChargeForTransfer;
	}

	/**
	 * @return the secNewBornCompCharge
	 */
	public String[] getSecNewBornCompCharge() {
		return secNewBornCompCharge;
	}

	/**
	 * @param secNewBornCompCharge
	 *            the secNewBornCompCharge to set
	 */
	public void setSecNewBornCompCharge(String[] secNewBornCompCharge) {
		this.secNewBornCompCharge = secNewBornCompCharge;
	}

	/**
	 * @return the ipdGenServiceFreeTreatmentTime
	 */
	public String getIpdGenServiceFreeTreatmentTime() {
		return ipdGenServiceFreeTreatmentTime;
	}

	/**
	 * @param ipdGenServiceFreeTreatmentTime
	 *            the ipdGenServiceFreeTreatmentTime to set
	 */
	public void setIpdGenServiceFreeTreatmentTime(
			String ipdGenServiceFreeTreatmentTime) {
		this.ipdGenServiceFreeTreatmentTime = ipdGenServiceFreeTreatmentTime;
	}

	/**
	 * @return the strGeneralWardType
	 */
	public String getStrGeneralWardType() {
		return strGeneralWardType;
	}

	/**
	 * @param strGeneralWardType
	 *            the strGeneralWardType to set
	 */
	public void setStrGeneralWardType(String strGeneralWardType) {
		this.strGeneralWardType = strGeneralWardType;
	}

	/**
	 * @return the strGeneralRoomType
	 */
	public String getStrGeneralRoomType() {
		return strGeneralRoomType;
	}

	/**
	 * @param strGeneralRoomType
	 *            the strGeneralRoomType to set
	 */
	public void setStrGeneralRoomType(String strGeneralRoomType) {
		this.strGeneralRoomType = strGeneralRoomType;
	}

	/**
	 * @return the strGeneralWardTypeValue
	 */
	public String getStrGeneralWardTypeValue() {
		return strGeneralWardTypeValue;
	}

	/**
	 * @param strGeneralWardTypeValue
	 *            the strGeneralWardTypeValue to set
	 */
	public void setStrGeneralWardTypeValue(String strGeneralWardTypeValue) {
		this.strGeneralWardTypeValue = strGeneralWardTypeValue;
	}

	/**
	 * @return the strGeneralRoomTypeValue
	 */
	public String getStrGeneralRoomTypeValue() {
		return strGeneralRoomTypeValue;
	}

	/**
	 * @param strGeneralRoomTypeValue
	 *            the strGeneralRoomTypeValue to set
	 */
	public void setStrGeneralRoomTypeValue(String strGeneralRoomTypeValue) {
		this.strGeneralRoomTypeValue = strGeneralRoomTypeValue;
	}

	public String getAdmittedPatient() {
		if ((this.getJob1BedCharge().equals("1"))
				|| (this.getJob1CompusoryCharge().equals("1"))) {
			this.admittedPatient = "1";

		}
		// System.out.println("admittedPatient:"+admittedPatient);
		return admittedPatient;
	}

	public String getGeneralActivity() {

		if ((this.getJob2EmgCompusoryCharge().equals("1"))
				|| (this.getJob2FinStartYear().equals("1"))) {
			this.generalActivity = "1";
		}
		return generalActivity;
	}

	public String getStrErr() {
		return strErr;
	}

	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getJob1BedCharge() {
		return job1BedCharge;
	}

	public void setJob1BedCharge(String job1BedCharge) {
		this.job1BedCharge = job1BedCharge;
	}

	public String getJob1CompusoryCharge() {
		return job1CompusoryCharge;
	}

	public void setJob1CompusoryCharge(String job1CompusoryCharge) {
		this.job1CompusoryCharge = job1CompusoryCharge;
	}

	public String getJob2FinStartYear() {
		return job2FinStartYear;
	}

	public void setJob2FinStartYear(String job2FinStartYear) {
		this.job2FinStartYear = job2FinStartYear;
	}

	public String getJob2EmgCompusoryCharge() {
		return job2EmgCompusoryCharge;
	}

	public void setJob2EmgCompusoryCharge(String job2EmgCompusoryCharge) {
		this.job2EmgCompusoryCharge = job2EmgCompusoryCharge;
	}

	public String[] getGroupName() {
		return groupName;
	}

	public void setGroupName(String[] groupName) {
		this.groupName = groupName;
	}

	public String getIpdComplChrgWardValues() {

		return ipdComplChrgWardValues;
	}

	public String getThirdWardTypeValue() {

		return ipdComplChrgWardValues;
	}

	public String getIpdComplChrgGroupValues() {

		return ipdComplChrgGroupValues;
	}

	public String getIpdComplChrgTariffValues() {

		return ipdComplChrgTariffValues;
	}

	public String getIpdComplChrgSecTariffValues() {

		return ipdComplChrgSecTariffValuesI;
	}

	public String getIpdComplChrgThirdGroupValues() {

		return ipdComplChrgThirdGroupValues;
	}

	public String getIpdComplChrgThirdTariffValues() {

		return ipdComplChrgThirdTariffValues;
	}

	public String getFinStartYear() {
		return finStartYear;
	}

	public void setFinStartYear(String finStartYear) {
		this.finStartYear = finStartYear;
	}

	public String getInBound() {
		return inBound;
	}

	public void setInBound(String inBound) {
		this.inBound = inBound;
	}

	public String getOutBound() {
		return outBound;
	}

	public void setOutBound(String outBound) {
		this.outBound = outBound;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getDefNoRecords() {
		return defNoRecords;
	}

	public void setDefNoRecords(String defNoRecords) {
		this.defNoRecords = defNoRecords;
	}

	public String getModRecords() {
		return modRecords;
	}

	public void setModRecords(String modRecords) {
		this.modRecords = modRecords;
	}

	public String getDelRecords() {
		return delRecords;
	}

	public void setDelRecords(String delRecords) {
		this.delRecords = delRecords;
	}

	public String getPrintCharge() {
		return printCharge;
	}

	public void setPrintCharge(String printCharge) {
		this.printCharge = printCharge;
	}

	public String getOpdThirdPartyBilling() {
		return opdThirdPartyBilling;
	}

	public void setOpdThirdPartyBilling(String opdThirdPartyBilling) {
		this.opdThirdPartyBilling = opdThirdPartyBilling;
	}

	public String getOpdDiscount() {
		return opdDiscount;
	}

	public void setOpdDiscount(String opdDiscount) {
		this.opdDiscount = opdDiscount;
	}

	public String getOpdDiscountClerk() {
		return opdDiscountClerk;
	}

	public void setOpdDiscountClerk(String opdDiscountClerk) {
		this.opdDiscountClerk = opdDiscountClerk;
	}

	public String getOpdRefundPenalty() {
		return opdRefundPenalty;
	}

	public void setOpdRefundPenalty(String opdRefundPenalty) {
		this.opdRefundPenalty = opdRefundPenalty;
	}

	public String getOpdRoundOff() {
		return opdRoundOff;
	}

	public void setOpdRoundOff(String opdRoundOff) {
		this.opdRoundOff = opdRoundOff;
	}

	public String getEmgThirdPartyBilling() {
		return emgThirdPartyBilling;
	}

	public void setEmgThirdPartyBilling(String emgThirdPartyBilling) {
		this.emgThirdPartyBilling = emgThirdPartyBilling;
	}

	public String getEmgDiscount() {
		return emgDiscount;
	}

	public void setEmgDiscount(String emgDiscount) {
		this.emgDiscount = emgDiscount;
	}

	public String getEmgDiscountClerk() {
		return emgDiscountClerk;
	}

	public void setEmgDiscountClerk(String emgDiscountClerk) {
		this.emgDiscountClerk = emgDiscountClerk;
	}

	public String getEmgRefundPenalty() {
		return emgRefundPenalty;
	}

	public void setEmgRefundPenalty(String emgRefundPenalty) {
		this.emgRefundPenalty = emgRefundPenalty;
	}

	public String getEmgRoundOff() {
		return emgRoundOff;
	}

	public void setEmgRoundOff(String emgRoundOff) {
		this.emgRoundOff = emgRoundOff;
	}

	public String getBillFormatFooter() {
		return billFormatFooter;
	}

	public void setBillFormatFooter(String billFormatFooter) {
		this.billFormatFooter = billFormatFooter;
	}

	public String[] getTariffName() {
		return tariffName;
	}

	public void setTariffName(String[] tariffName) {
		this.tariffName = tariffName;
	}

	public String[] getUnit() {
		return unit;
	}

	public void setUnit(String[] unit) {
		this.unit = unit;
	}

	public String[] getSecWardName() {
		return secWardName;
	}

	public void setSecWardName(String[] secWardName) {
		this.secWardName = secWardName;
	}

	public String[] getSecTariffName() {
		return secTariffName;
	}

	public void setSecTariffName(String[] secTariffName) {
		this.secTariffName = secTariffName;
	}

	public String[] getSecUnit() {
		return secUnit;
	}

	public void setSecUnit(String[] secUnit) {
		this.secUnit = secUnit;
	}

	public String[] getThirdGroupName() {
		return thirdGroupName;
	}

	public void setThirdGroupName(String[] thirdGroupName) {
		this.thirdGroupName = thirdGroupName;
	}

	public String[] getThirdTariffName() {
		return thirdTariffName;
	}

	public void setThirdTariffName(String[] thirdTariffName) {
		this.thirdTariffName = thirdTariffName;
	}

	public String[] getThirdUnit() {
		return thirdUnit;
	}

	public void setThirdUnit(String[] thirdUnit) {
		this.thirdUnit = thirdUnit;
	}

	public String getIpdBedCalcWard() {
		return ipdBedCalcWard;
	}

	public void setIpdBedCalcWard(String ipdBedCalcWard) {
		this.ipdBedCalcWard = ipdBedCalcWard;
	}

	public String getIpdBedCalcWardCharg() {
		return ipdBedCalcWardCharg;
	}

	public void setIpdBedCalcWardCharg(String ipdBedCalcWardCharg) {
		this.ipdBedCalcWardCharg = ipdBedCalcWardCharg;
	}

	public String getIpdBedCalcServiceCharge() {
		return ipdBedCalcServiceCharge;
	}

	public void setIpdBedCalcServiceCharge(String ibdBedCalcServiceCharge) {
		this.ipdBedCalcServiceCharge = ibdBedCalcServiceCharge;
	}

	public String getIpdGenThirdPartyBilling() {
		return ipdGenThirdPartyBilling;
	}

	public void setIpdGenThirdPartyBilling(String ipdGenThirdPartyBilling) {
		this.ipdGenThirdPartyBilling = ipdGenThirdPartyBilling;
	}

	public String getIpdGenDiscount() {
		return ipdGenDiscount;
	}

	public void setIpdGenDiscount(String ipdGenDiscount) {
		this.ipdGenDiscount = ipdGenDiscount;
	}

	public String getIpdGenClerkDiscount() {
		return ipdGenClerkDiscount;
	}

	public void setIpdGenClerkDiscount(String ipdGenClerkDiscount) {
		this.ipdGenClerkDiscount = ipdGenClerkDiscount;
	}

	public String getIpdGenPenalty() {
		return ipdGenPenalty;
	}

	public void setIpdGenPenalty(String ipdGenPenalty) {
		this.ipdGenPenalty = ipdGenPenalty;
	}

	public String getIpdGenCreditBilling() {
		return ipdGenCreditBilling;
	}

	public void setIpdGenCreditBilling(String ipdGenCreditBilling) {
		this.ipdGenCreditBilling = ipdGenCreditBilling;
	}

	public String getIpdGenFlexibleTime() {
		return ipdGenFlexibleTime;
	}

	public void setIpdGenFlexibleTime(String ipdGenFlexibleTime) {
		this.ipdGenFlexibleTime = ipdGenFlexibleTime;
	}

	public String getIpdGenPayment() {
		return ipdGenPayment;
	}

	public void setIpdGenPayment(String ipdGenPayment) {
		this.ipdGenPayment = ipdGenPayment;
	}

	public String getIpdGenRoundOff() {
		return ipdGenRoundOff;
	}

	public void setIpdGenRoundOff(String ipdGenRoundOff) {
		this.ipdGenRoundOff = ipdGenRoundOff;
	}

	public String[] getEmgGroupName() {
		return emgGroupName;
	}

	public void setEmgGroupName(String[] emgGroupName) {
		this.emgGroupName = emgGroupName;
	}

	public String[] getEmgTariffName() {
		return emgTariffName;
	}

	public void setEmgTariffName(String[] emgTariffName) {
		this.emgTariffName = emgTariffName;
	}

	public String[] getEmgUnit() {
		return emgUnit;
	}

	public void setEmgUnit(String[] emgUnit) {
		this.emgUnit = emgUnit;
	}

	public String getIpdBedCalcWardValues() {
		return ipdBedCalcWardValues;
	}

	public String[] getSecGroupName() {
		return secGroupName;
	}

	public void setSecGroupName(String[] secGroupName) {
		this.secGroupName = secGroupName;
	}

	public String getIpdComplChrgSecGroupValues() {

		return ipdComplChrgSecGroupValues;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public String getEmgGroupValues() {

		return emgGroupValues;
	}

	public String getEmgTariffValues() {

		return emgTariffValues;
	}

	public String getEmgGrpTmpName() {
		return emgGrpTmpName;
	}

	public void setEmgGrpTmpName(String emgGrpTmpName) {
		this.emgGrpTmpName = emgGrpTmpName;
	}

	public String getEmgTrfTmpName() {
		return emgTrfTmpName;
	}

	public void setEmgTrfTmpName(String emgTrfTmpName) {
		this.emgTrfTmpName = emgTrfTmpName;
	}

	public String getEmgUntTmpName() {
		return emgUntTmpName;
	}

	public void setEmgUntTmpName(String emgUntTmpName) {
		this.emgUntTmpName = emgUntTmpName;
	}

	public String getIpdGrpTmpName() {
		return ipdGrpTmpName;
	}

	public void setIpdGrpTmpName(String ipdGrpTmpName) {
		this.ipdGrpTmpName = ipdGrpTmpName;
	}

	public String getIpdTrfTmpName() {
		return ipdTrfTmpName;
	}

	public void setIpdTrfTmpName(String ipdTrfTmpName) {
		this.ipdTrfTmpName = ipdTrfTmpName;
	}

	public String getIpdUntTmpName() {
		return ipdUntTmpName;
	}

	public void setIpdUntTmpName(String ipdUntTmpName) {
		this.ipdUntTmpName = ipdUntTmpName;
	}

	public String getIpdSecWrdTmpName() {
		return ipdSecWrdTmpName;
	}

	public void setIpdSecWrdTmpName(String ipdSecWrdTmpName) {
		this.ipdSecWrdTmpName = ipdSecWrdTmpName;
	}

	public String getIpdSecGrpTmpName() {
		return ipdSecGrpTmpName;
	}

	public void setIpdSecGrpTmpName(String ipdSecGrpTmpName) {
		this.ipdSecGrpTmpName = ipdSecGrpTmpName;
	}

	public String getIpdSecTrfTmpName() {
		return ipdSecTrfTmpName;
	}

	public void setIpdSecTrfTmpName(String ipdSecTrfTmpName) {
		this.ipdSecTrfTmpName = ipdSecTrfTmpName;
	}

	public String getIpdSecUntTmpName() {
		return ipdSecUntTmpName;
	}

	public void setIpdSecUntTmpName(String ipdSecUntTmpName) {
		this.ipdSecUntTmpName = ipdSecUntTmpName;
	}

	public String getIpdThirdGrpTmpName() {
		return ipdThirdGrpTmpName;
	}

	public void setIpdThirdGrpTmpName(String ipdThirdGrpTmpName) {
		this.ipdThirdGrpTmpName = ipdThirdGrpTmpName;
	}

	public String getIpdThirdTrfTmpName() {
		return ipdThirdTrfTmpName;
	}

	public void setIpdThirdTrfTmpName(String ipdThirdTrfTmpName) {
		this.ipdThirdTrfTmpName = ipdThirdTrfTmpName;
	}

	public String getIpdThirdUntTmpName() {
		return ipdThirdUntTmpName;
	}

	public void setIpdThirdUntTmpName(String ipdThirdUntTmpName) {
		this.ipdThirdUntTmpName = ipdThirdUntTmpName;
	}

	public String getIpdMultiRow() {

			return ipdMultiRow;
	}

	public String getEmgMultiRow() {

		return emgMultiRow;
	}

	public String getIpdSecMultiRow() {

		return ipdSecMultiRow;
	}

	public String getIpdThirdMultiRow() {

			
		return ipdThirdMultiRow;
	}

	public String getIpdGenCheckOutTimeMn() {
		return ipdGenCheckOutTimeMn;
	}

	public void setIpdGenCheckOutTimeMn(String ipdGenCheckOutTimeMn) {
		this.ipdGenCheckOutTimeMn = ipdGenCheckOutTimeMn;
	}

	public String getIpdGenCheckOutTimeHr() {
		return ipdGenCheckOutTimeHr;
	}

	public void setIpdGenCheckOutTimeHr(String ipdGenCheckOutTimeHr) {
		this.ipdGenCheckOutTimeHr = ipdGenCheckOutTimeHr;
	}

	public String getStrTotalRows() {

		strTotalRows = String.valueOf(this.getTotalRows());

		return strTotalRows;
	}

	public LinkedHashMap<String, String> getLhm() {
		lhm.put("General", "general");
		lhm.put("Opd", "opd");
		lhm.put("Ipd", "ipd");
		lhm.put("Emergency", "emergency");
		lhm.put("Bill Format", "billFormat");
		lhm.put("Surcharge", "surcharge");
		// lhm.put("Jobs", "jobs");
		return lhm;
	}

	public LinkedHashMap<String, String> getLhm1() {
		lhm1.put("Compulsory Charge", "compulsoryCharge");
		// lhm1.put("Bed Calculation Rule", "bedCalculationRule");
		lhm1.put("General", "generalIpd");

		return lhm1;
	}

	/**
	 * @return the ipdGenServiceTaxOnTotalBill
	 */
	public String getIpdGenServiceTaxOnTotalBill() {
		return ipdGenServiceTaxOnTotalBill;
	}

	/**
	 * @param ipdGenServiceTaxOnTotalBill
	 *            the ipdGenServiceTaxOnTotalBill to set
	 */
	public void setIpdGenServiceTaxOnTotalBill(
			String ipdGenServiceTaxOnTotalBill) {
		this.ipdGenServiceTaxOnTotalBill = ipdGenServiceTaxOnTotalBill;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getBillFormatHeader1() {
		return billFormatHeader1;
	}

	public void setBillFormatHeader1(String billFormatHeader1) {
		this.billFormatHeader1 = billFormatHeader1;
	}

	public String getBillFormatHeader2() {
		return billFormatHeader2;
	}

	public void setBillFormatHeader2(String billFormatHeader2) {
		this.billFormatHeader2 = billFormatHeader2;
	}

	public String getBillFormatHeader3() {
		return billFormatHeader3;
	}

	public void setBillFormatHeader3(String billFormatHeader3) {
		this.billFormatHeader3 = billFormatHeader3;
	}

	public String getBillFormatHeader4() {
		return billFormatHeader4;
	}

	public void setBillFormatHeader4(String billFormatHeader4) {
		this.billFormatHeader4 = billFormatHeader4;
	}

	public String[] getSecTreatmentCategory() {
		return secTreatmentCategory;
	}

	public void setSecTreatmentCategory(String[] secTreatmentCategory) {
		this.secTreatmentCategory = secTreatmentCategory;
	}

	public String getIpdGenExcessCreditLimit() {
		return ipdGenExcessCreditLimit;
	}

	public void setIpdGenExcessCreditLimit(String ipdGenExcessCreditLimit) {
		this.ipdGenExcessCreditLimit = ipdGenExcessCreditLimit;
	}

	public String getIpdGenCheckOutTimePrivateHr() {
		return ipdGenCheckOutTimePrivateHr;
	}

	public void setIpdGenCheckOutTimePrivateHr(
			String ipdGenCheckOutTimePrivateHr) {
		this.ipdGenCheckOutTimePrivateHr = ipdGenCheckOutTimePrivateHr;
	}

	public String getIpdGenCheckOutTimePrivateMn() {
		return ipdGenCheckOutTimePrivateMn;
	}

	public void setIpdGenCheckOutTimePrivateMn(
			String ipdGenCheckOutTimePrivateMn) {
		this.ipdGenCheckOutTimePrivateMn = ipdGenCheckOutTimePrivateMn;
	}

	public String getIpdGenFlexibleTimeAdmission() {
		return ipdGenFlexibleTimeAdmission;
	}

	public void setIpdGenFlexibleTimeAdmission(
			String ipdGenFlexibleTimeAdmission) {
		this.ipdGenFlexibleTimeAdmission = ipdGenFlexibleTimeAdmission;
	}

	public String getIpdComplChrgSecTreatmentCategoryValues() {

		return ipdComplChrgSecTreatmentCategoryValues;
	}

	public String getIpdSecTreatCatTempName() {
		return ipdSecTreatCatTempName;
	}

	public void setIpdSecTreatCatTempName(String ipdSecTreatCatTempName) {
		this.ipdSecTreatCatTempName = ipdSecTreatCatTempName;
	}

	public String getStrReceiptType() {
		return strReceiptType;
	}

	public String getStrDuplicatePrint() {
		return strDuplicatePrint;
	}

	public void setStrReceiptType(String strReceiptType) {
		this.strReceiptType = strReceiptType;
	}

	public void setStrDuplicatePrint(String strDuplicatePrint) {
		this.strDuplicatePrint = strDuplicatePrint;
	}

	public String getStrIsWithoutCrNoRequired() {
		return strIsWithoutCrNoRequired;
	}

	public void setStrIsWithoutCrNoRequired(String strIsWithoutCrNoRequired) {
		this.strIsWithoutCrNoRequired = strIsWithoutCrNoRequired;
	}

	public String getStrWardTypeInt() {
		return strWardTypeInt;
	}

	public void setStrWardTypeInt(String strWardTypeInt) {
		this.strWardTypeInt = strWardTypeInt;
	}

	public String getStrWardTypeExt() {
		return strWardTypeExt;
	}

	public void setStrWardTypeExt(String strWardTypeExt) {
		this.strWardTypeExt = strWardTypeExt;
	}

	/**
	 * @return the thirdWardName
	 */
	public String[] getThirdWardName() {
		return thirdWardName;
	}

	/**
	 * @param thirdWardName
	 *            the thirdWardName to set
	 */
	public void setThirdWardName(String[] thirdWardName) {
		this.thirdWardName = thirdWardName;
	}

	/**
	 * @return the total3Rows
	 */
	public int getTotal3Rows() {
		return total3Rows;
	}

	/**
	 * @param total3Rows
	 *            the total3Rows to set
	 */
	public void setTotal3Rows(int total3Rows) {
		this.total3Rows = total3Rows;
	}

	/**
	 * @return the strTotal3Rows
	 */
	public String getStrTotal3Rows() {
		strTotal3Rows = String.valueOf(this.getTotal3Rows());

		return strTotal3Rows;
	}

	/**
	 * @param strTotal3Rows
	 *            the strTotal3Rows to set
	 */
	public void setStrTotal3Rows(String strTotal3Rows) {
		this.strTotal3Rows = strTotal3Rows;
	}

	public String getIpdGenAdtProcessType() {
		return ipdGenAdtProcessType;
	}

	public void setIpdGenAdtProcessType(String ipdGenAdtProcessType) {
		this.ipdGenAdtProcessType = ipdGenAdtProcessType;
	}

	public String getStrOnlineRefundRequestAllowed() {
		return strOnlineRefundRequestAllowed;
	}

	public void setStrOnlineRefundRequestAllowed(
			String strOnlineRefundRequestAllowed) {
		this.strOnlineRefundRequestAllowed = strOnlineRefundRequestAllowed;
	}

	public String getOpdServiceTax() {
		return opdServiceTax;
	}

	public void setOpdServiceTax(String opdServiceTax) {
		this.opdServiceTax = opdServiceTax;
	}

	public String getIpdServiceTax() {
		return ipdServiceTax;
	}

	public void setIpdServiceTax(String ipdServiceTax) {
		this.ipdServiceTax = ipdServiceTax;
	}

	public String getEmgServiceTax() {
		return emgServiceTax;
	}

	public void setEmgServiceTax(String emgServiceTax) {
		this.emgServiceTax = emgServiceTax;
	}

	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}

	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}

	public String getStrIntPatChk() {
		return strIntPatChk;
	}

	public void setStrIntPatChk(String strIntPatChk) {
		this.strIntPatChk = strIntPatChk;
	}

	public String getStrReferringPhysicianRequiredInt() {
		return strReferringPhysicianRequiredInt;
	}

	public void setStrReferringPhysicianRequiredInt(
			String strReferringPhysicianRequiredInt) {
		this.strReferringPhysicianRequiredInt = strReferringPhysicianRequiredInt;
	}

	public String getStrPreviousCrNoRequiredInt() {
		return strPreviousCrNoRequiredInt;
	}

	public void setStrPreviousCrNoRequiredInt(String strPreviousCrNoRequiredInt) {
		this.strPreviousCrNoRequiredInt = strPreviousCrNoRequiredInt;
	}

	public String getStrExtPatChk() {
		return strExtPatChk;
	}

	public void setStrExtPatChk(String strExtPatChk) {
		this.strExtPatChk = strExtPatChk;
	}

	public String getStrReferringPhysicianRequiredExt() {
		return strReferringPhysicianRequiredExt;
	}

	public void setStrReferringPhysicianRequiredExt(
			String strReferringPhysicianRequiredExt) {
		this.strReferringPhysicianRequiredExt = strReferringPhysicianRequiredExt;
	}

	public String getStrPreviousCrNoRequiredExt() {
		return strPreviousCrNoRequiredExt;
	}

	public void setStrPreviousCrNoRequiredExt(String strPreviousCrNoRequiredExt) {
		this.strPreviousCrNoRequiredExt = strPreviousCrNoRequiredExt;
	}

	public String getStrChargeTypeInt() {
		return strChargeTypeInt;
	}

	public void setStrChargeTypeInt(String strChargeTypeInt) {
		this.strChargeTypeInt = strChargeTypeInt;
	}

	public String getStrChargeTypeExt() {
		return strChargeTypeExt;
	}

	public void setStrChargeTypeExt(String strChargeTypeExt) {
		this.strChargeTypeExt = strChargeTypeExt;
	}

	public String getStrCcConfirmationType() {
		return strCcConfirmationType;
	}

	public void setStrCcConfirmationType(String strCcConfirmationType) {
		this.strCcConfirmationType = strCcConfirmationType;
	}

	public String getStrPreviousCrNoSearchingInt() {
		return strPreviousCrNoSearchingInt;
	}

	public void setStrPreviousCrNoSearchingInt(
			String strPreviousCrNoSearchingInt) {
		this.strPreviousCrNoSearchingInt = strPreviousCrNoSearchingInt;
	}

	public String getStrPreviousCrNoSearchingExt() {
		return strPreviousCrNoSearchingExt;
	}

	public void setStrPreviousCrNoSearchingExt(
			String strPreviousCrNoSearchingExt) {
		this.strPreviousCrNoSearchingExt = strPreviousCrNoSearchingExt;
	}

	public String getBillDisclaimerWithoutCrNo() {
		return billDisclaimerWithoutCrNo;
	}

	public void setBillDisclaimerWithoutCrNo(String billDisclaimerWithoutCrNo) {
		this.billDisclaimerWithoutCrNo = billDisclaimerWithoutCrNo;
	}

	public String getBillDisclaimerServices() {
		return billDisclaimerServices;
	}

	public void setBillDisclaimerServices(String billDisclaimerServices) {
		this.billDisclaimerServices = billDisclaimerServices;
	}

	public String getBillDisclaimerRefund() {
		return billDisclaimerRefund;
	}

	public void setBillDisclaimerRefund(String billDisclaimerRefund) {
		this.billDisclaimerRefund = billDisclaimerRefund;
	}

	public String getBillDisclaimerAdvance() {
		return billDisclaimerAdvance;
	}

	public void setBillDisclaimerAdvance(String billDisclaimerAdvance) {
		this.billDisclaimerAdvance = billDisclaimerAdvance;
	}

	public String getBillDisclaimerPartPayment() {
		return billDisclaimerPartPayment;
	}

	public void setBillDisclaimerPartPayment(String billDisclaimerPartPayment) {
		this.billDisclaimerPartPayment = billDisclaimerPartPayment;
	}

	public String getBillDisclaimerFinalBill() {
		return billDisclaimerFinalBill;
	}

	public void setBillDisclaimerFinalBill(String billDisclaimerFinalBill) {
		this.billDisclaimerFinalBill = billDisclaimerFinalBill;
	}

	public String getBillDisclaimerDuplicatePrintRequired() {
		return billDisclaimerDuplicatePrintRequired;
	}

	public void setBillDisclaimerDuplicatePrintRequired(
			String billDisclaimerDuplicatePrintRequired) {
		this.billDisclaimerDuplicatePrintRequired = billDisclaimerDuplicatePrintRequired;
	}

	public String getStrDayEndProcessType() {
		return strDayEndProcessType;
	}

	public void setStrDayEndProcessType(String strDayEndProcessType) {
		this.strDayEndProcessType = strDayEndProcessType;
	}

	public String getStrDayEndDateType() {
		return strDayEndDateType;
	}

	public void setStrDayEndDateType(String strDayEndDateType) {
		this.strDayEndDateType = strDayEndDateType;
	}

	public String getStrDayEndNonBillingProcessType() {
		return strDayEndNonBillingProcessType;
	}

	public void setStrDayEndNonBillingProcessType(
			String strDayEndNonBillingProcessType) {
		this.strDayEndNonBillingProcessType = strDayEndNonBillingProcessType;
	}

	public String getStrDayEndNonBillingDateType() {
		return strDayEndNonBillingDateType;
	}

	public void setStrDayEndNonBillingDateType(
			String strDayEndNonBillingDateType) {
		this.strDayEndNonBillingDateType = strDayEndNonBillingDateType;
	}

	public String getStrOpdReceiptType() {
		return strOpdReceiptType;
	}

	public void setStrOpdReceiptType(String strOpdReceiptType) {
		this.strOpdReceiptType = strOpdReceiptType;
	}

	public String getStrIpdReceiptType() {
		return strIpdReceiptType;
	}

	public void setStrIpdReceiptType(String strIpdReceiptType) {
		this.strIpdReceiptType = strIpdReceiptType;
	}

	public String getBillLineOpdServices() {
		return billLineOpdServices;
	}

	public void setBillLineOpdServices(String billLineOpdServices) {
		this.billLineOpdServices = billLineOpdServices;
	}

	public String getStrCashCollectionOfflineRefundRequired() {
		return strCashCollectionOfflineRefundRequired;
	}

	public void setStrCashCollectionOfflineRefundRequired(
			String strCashCollectionOfflineRefundRequired) {
		this.strCashCollectionOfflineRefundRequired = strCashCollectionOfflineRefundRequired;
	}

	public String getStrPrintMessageLimit() {
		return strPrintMessageLimit;
	}

	public void setStrPrintMessageLimit(String strPrintMessageLimit) {
		this.strPrintMessageLimit = strPrintMessageLimit;
	}

	public String getStrRefundReceiptType() {
		return strRefundReceiptType;
	}

	public void setStrRefundReceiptType(String strRefundReceiptType) {
		this.strRefundReceiptType = strRefundReceiptType;
	}

	public String getStrRefundAgainstRefundRequest() {
		return strRefundAgainstRefundRequest;
	}

	public void setStrRefundAgainstRefundRequest(
			String strRefundAgainstRefundRequest) {
		this.strRefundAgainstRefundRequest = strRefundAgainstRefundRequest;
	}

	public String getStrInternalPatient() {
		return strInternalPatient;
	}

	public void setStrInternalPatient(String strInternalPatient) {
		this.strInternalPatient = strInternalPatient;
	}

	public String getStrExternalPatient() {
		return strExternalPatient;
	}

	public void setStrExternalPatient(String strExternalPatient) {
		this.strExternalPatient = strExternalPatient;
	}

	public String getStrWardTypeValueInt() {
		return strWardTypeValueInt;
	}

	public void setStrWardTypeValueInt(String strWardTypeValueInt) {
		this.strWardTypeValueInt = strWardTypeValueInt;
	}

	public String getStrChargeTypeValueInt() {
		return strChargeTypeValueInt;
	}

	public void setStrChargeTypeValueInt(String strChargeTypeValueInt) {
		this.strChargeTypeValueInt = strChargeTypeValueInt;
	}

	public String getStrWardTypeValueExt() {
		return strWardTypeValueExt;
	}

	public void setStrWardTypeValueExt(String strWardTypeValueExt) {
		this.strWardTypeValueExt = strWardTypeValueExt;
	}

	public String getStrChargeTypeValueExt() {
		return strChargeTypeValueExt;
	}

	public void setStrChargeTypeValueExt(String strChargeTypeValueExt) {
		this.strChargeTypeValueExt = strChargeTypeValueExt;
	}

	public String getOpdFreeCategory() {
		return opdFreeCategory;
	}

	public void setOpdFreeCategory(String opdFreeCategory) {
		this.opdFreeCategory = opdFreeCategory;
	}

	public String getIpdFreeCategory() {
		return ipdFreeCategory;
	}

	public void setIpdFreeCategory(String ipdFreeCategory) {
		this.ipdFreeCategory = ipdFreeCategory;
	}

	public String getEmgFreeCategory() {
		return emgFreeCategory;
	}

	public void setEmgFreeCategory(String emgFreeCategory) {
		this.emgFreeCategory = emgFreeCategory;
	}

	public String getStrIsApprovalByRequired() {
		return strIsApprovalByRequired;
	}

	public void setStrIsApprovalByRequired(String strIsApprovalByRequired) {
		this.strIsApprovalByRequired = strIsApprovalByRequired;
	}

	public void setLhm(java.util.LinkedHashMap<String, String> lhm) {
		this.lhm = lhm;
	}

	public void setLhm1(java.util.LinkedHashMap<String, String> lhm1) {
		this.lhm1 = lhm1;
	}

	public void setAdmittedPatient(String admittedPatient) {
		this.admittedPatient = admittedPatient;
	}

	public void setGeneralActivity(String generalActivity) {
		this.generalActivity = generalActivity;
	}

	public void setStrTotalRows(String strTotalRows) {
		this.strTotalRows = strTotalRows;
	}

	public void setIpdComplChrgGroupValues(String ipdComplChrgGroupValues) {
		this.ipdComplChrgGroupValues = ipdComplChrgGroupValues;
	}

	public void setIpdComplChrgTariffValues(String ipdComplChrgTariffValues) {
		this.ipdComplChrgTariffValues = ipdComplChrgTariffValues;
	}

	public void setIpdComplChrgWardValues(String ipdComplChrgWardValues) {
		this.ipdComplChrgWardValues = ipdComplChrgWardValues;
	}

	public void setIpdComplChrgSecGroupValues(String ipdComplChrgSecGroupValues) {
		this.ipdComplChrgSecGroupValues = ipdComplChrgSecGroupValues;
	}

	public void setIpdComplChrgSecTreatmentCategoryValues(
			String ipdComplChrgSecTreatmentCategoryValues) {
		this.ipdComplChrgSecTreatmentCategoryValues = ipdComplChrgSecTreatmentCategoryValues;
	}

	public void setIpdComplChrgSecTariffValuesI(String ipdComplChrgSecTariffValuesI) {
		this.ipdComplChrgSecTariffValuesI = ipdComplChrgSecTariffValuesI;
	}
	

	public void setIpdComplChrgThirdGroupValues(String ipdComplChrgThirdGroupValues) {
		this.ipdComplChrgThirdGroupValues = ipdComplChrgThirdGroupValues;
	}

	public void setIpdComplChrgThirdTariffValues(
			String ipdComplChrgThirdTariffValues) {
		this.ipdComplChrgThirdTariffValues = ipdComplChrgThirdTariffValues;
	}

	public void setIpdMultiRow(String ipdMultiRow) {
		this.ipdMultiRow = ipdMultiRow;
	}

	public void setIpdSecMultiRow(String ipdSecMultiRow) {
		this.ipdSecMultiRow = ipdSecMultiRow;
	}

	public void setIpdThirdMultiRow(String ipdThirdMultiRow) {
		this.ipdThirdMultiRow = ipdThirdMultiRow;
	}

	public void setIpdBedCalcWardValues(String ipdBedCalcWardValues) {
		this.ipdBedCalcWardValues = ipdBedCalcWardValues;
	}

	public void setEmgGroupValues(String emgGroupValues) {
		this.emgGroupValues = emgGroupValues;
	}

	public void setEmgTariffValues(String emgTariffValues) {
		this.emgTariffValues = emgTariffValues;
	}

	public void setEmgMultiRow(String emgMultiRow) {
		this.emgMultiRow = emgMultiRow;
	}

	public void setStrIpdFreeCategoryValue(String strIpdFreeCategoryValue) {
		this.strIpdFreeCategoryValue = strIpdFreeCategoryValue;
	}

	public void setStrOpdFreeCategoryValue(String strOpdFreeCategoryValue) {
		this.strOpdFreeCategoryValue = strOpdFreeCategoryValue;
	}

	public void setStrEmgFreeCategoryValue(String strEmgFreeCategoryValue) {
		this.strEmgFreeCategoryValue = strEmgFreeCategoryValue;
	}

	public String getIpdComplChrgSecTariffValuesI() {
		return ipdComplChrgSecTariffValuesI;
	}

	public String getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}
	
	public String getStrGroupIdValues() {
		return strGroupIdValues;
	}

	public void setStrGroupIdValues(String strGroupIdValues) {
		this.strGroupIdValues = strGroupIdValues;
	}

	public String getStrDefaultTariffCode() {
		return strDefaultTariffCode;
	}

	public void setStrDefaultTariffCode(String strDefaultTariffCode) {
		this.strDefaultTariffCode = strDefaultTariffCode;
	}

	public String getStrConsumableChargesGroupId() {
		return strConsumableChargesGroupId;
	}

	public void setStrConsumableChargesGroupId(String strConsumableChargesGroupId) {
		this.strConsumableChargesGroupId = strConsumableChargesGroupId;
	}

	public String getStrConsumableChargesGroupIdValues() {
		return strConsumableChargesGroupIdValues;
	}

	public void setStrConsumableChargesGroupIdValues(
			String strConsumableChargesGroupIdValues) {
		this.strConsumableChargesGroupIdValues = strConsumableChargesGroupIdValues;
	}

	public String getStrConsumableChargesTariffCode() {
		return strConsumableChargesTariffCode;
	}

	public void setStrConsumableChargesTariffCode(
			String strConsumableChargesTariffCode) {
		this.strConsumableChargesTariffCode = strConsumableChargesTariffCode;
	}

	public String getStrConsumableChargesTariffValues() {
		return strConsumableChargesTariffValues;
	}

	public void setStrConsumableChargesTariffValues(
			String strConsumableChargesTariffValues) {
		this.strConsumableChargesTariffValues = strConsumableChargesTariffValues;
	}

	
	public String getStrGeneralTariffValues() {
		return strGeneralTariffValues;
	}

	public void setStrGeneralTariffValues(String strGeneralTariffValues) {
		this.strGeneralTariffValues = strGeneralTariffValues;
	}

	public String getIpdGenAdvanceReq() {
		return ipdGenAdvanceReq;
	}

	public void setIpdGenAdvanceReq(String ipdGenAdvanceReq) {
		this.ipdGenAdvanceReq = ipdGenAdvanceReq;
	}

	public String getIpdGenReOpenValidity() {
		return ipdGenReOpenValidity;
	}

	public void setIpdGenReOpenValidity(String ipdGenReOpenValidity) {
		this.ipdGenReOpenValidity = ipdGenReOpenValidity;
	}

	public String getStrCreditCashlessAppRequired() {
		return strCreditCashlessAppRequired;
	}

	public void setStrCreditCashlessAppRequired(String strCreditCashlessAppRequired) {
		this.strCreditCashlessAppRequired = strCreditCashlessAppRequired;
	}

	public String getStrDayEndTimeBoundFlag() {
		return strDayEndTimeBoundFlag;
	}

	public void setStrDayEndTimeBoundFlag(String strDayEndTimeBoundFlag) {
		this.strDayEndTimeBoundFlag = strDayEndTimeBoundFlag;
	}

	public String getStrDayEndTimeHour() {
		return strDayEndTimeHour;
	}

	public void setStrDayEndTimeHour(String strDayEndTimeHour) {
		this.strDayEndTimeHour = strDayEndTimeHour;
	}

	public String getStrDayEndTimeMin() {
		return strDayEndTimeMin;
	}

	public void setStrDayEndTimeMin(String strDayEndTimeMin) {
		this.strDayEndTimeMin = strDayEndTimeMin;
	}

	public String getStrDayEndTimeAMPM() {
		return strDayEndTimeAMPM;
	}

	public void setStrDayEndTimeAMPM(String strDayEndTimeAMPM) {
		this.strDayEndTimeAMPM = strDayEndTimeAMPM;
	}
	public String getStrDayEndTime() {
		return strDayEndTime;
	}

	public void setStrDayEndTime(String strDayEndTime) {
		this.strDayEndTime = strDayEndTime;
	}
	public String getStrGenCategoryValue() {
		return strGenCategoryValue;
	}

	public void setStrGenCategoryValue(String strGenCategoryValue) {
		this.strGenCategoryValue = strGenCategoryValue;
	}
	public String getStrGenCategory() {
		return strGenCategory;
	}

	public void setStrGenCategory(String strGenCategory) {
		this.strGenCategory = strGenCategory;
	}

	public String getStrArogyaShreeTSCategory() {
		return strArogyaShreeTSCategory;
	}

	public void setStrArogyaShreeTSCategory(String strArogyaShreeTSCategory) {
		this.strArogyaShreeTSCategory = strArogyaShreeTSCategory;
	}

	public String getStrArogyaShreeTSCategoryvalue() {
		return strArogyaShreeTSCategoryvalue;
	}

	public void setStrArogyaShreeTSCategoryvalue(
			String strArogyaShreeTSCategoryvalue) {
		this.strArogyaShreeTSCategoryvalue = strArogyaShreeTSCategoryvalue;
	}

	public String getStrArogyaTSCreditLimit() {
		return strArogyaTSCreditLimit;
	}

	public void setStrArogyaTSCreditLimit(String strArogyaTSCreditLimit) {
		this.strArogyaTSCreditLimit = strArogyaTSCreditLimit;
	}

	public String getStrUrgTrfSur() {
		return strUrgTrfSur;
	}

	public void setStrUrgTrfSur(String strUrgTrfSur) {
		this.strUrgTrfSur = strUrgTrfSur;
	}

	public String getLogoReq() {
		return logoReq;
	}

	public void setLogoReq(String logoReq) {
		this.logoReq = logoReq;
	}

	public String getStrSurCc() {
		return strSurCc;
	}

	public void setStrSurCc(String strSurCc) {
		this.strSurCc = strSurCc;
	}

	public String getStrSurDc() {
		return strSurDc;
	}

	public void setStrSurDc(String strSurDc) {
		this.strSurDc = strSurDc;
	}

	public String getStrSurIc() {
		return strSurIc;
	}

	public void setStrSurIc(String strSurIc) {
		this.strSurIc = strSurIc;
	}
	
	
	public String getStrSurId() {
		return strSurId;
	}

	public void setStrSurId(String strSurId) {
		this.strSurId = strSurId;
	}

	public String getStrSurCc1() {
		return strSurCc1;
	}

	public void setStrSurCc1(String strSurCc1) {
		this.strSurCc1 = strSurCc1;
	}

	public String getStrSurDc1() {
		return strSurDc1;
	}

	public void setStrSurDc1(String strSurDc1) {
		this.strSurDc1 = strSurDc1;
	}

	public String getStrSurIc1() {
		return strSurIc1;
	}

	public void setStrSurIc1(String strSurIc1) {
		this.strSurIc1 = strSurIc1;
	}

	public String getStrSurId1() {
		return strSurId1;
	}

	public void setStrSurId1(String strSurId1) {
		this.strSurId1 = strSurId1;
	}
	
	public String getHindiReq() {
		return hindiReq;
	}

	public void setHindiReq(String hindiReq) {
		this.hindiReq = hindiReq;
	}
	
	public String getHeaderReq() {
		return headerReq;
	}

	public void setHeaderReq(String headerReq) {
		this.headerReq = headerReq;
	}
	
}
