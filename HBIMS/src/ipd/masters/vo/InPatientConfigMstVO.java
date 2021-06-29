package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;
import ipd.masters.bo.InPatientConfigMstBO;
import ipd.masters.controller.hlp.InPatientConfigMstHLP;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class InPatientConfigMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;
	
	java.util.LinkedHashMap<String,String> lhm	=	new java.util.LinkedHashMap<String,String>();
	
	private String strOnlineAdvice = "0";
	private String strGenWardAdmission = "0";
	private String strGenWardApprover[] = null;
	private String strPrivateWardAdmission = "0";
	private String strPrivateWardApprover[] = null;
	private String strBookBed = "0";
	private String strBilling = "0";
	private String strNegativeBilling = "0";
	private String strPatientCategory= "0";
	private String strGovtEmpPayLimit="0.00";
	private String strPvtEmpIncomeLimit="0.00";
	private String strAdmissionAdviceValidity = "0";
	private String strNoOfLineInNewBornBabyAdmissionSlip="";
	private String strGenWardApproverValues = "0";
	private String strPrivateWardApproverValues = "0";
	private String strPatientCategoryValues = "0";

	private String strNoOfLineInAdmissionSlip="0";
	private String strNoOfLineInVisitorPassSlip="0";
	private String strAdmissionReprintCharge = "0.00";
	private String strPassReprintCharges = "0.00";
	private String strDischargeReprintCharge = "0.00";
	private String strMsApprovalOffline="0";
	private String strAttendentPass="0";
	private String strAttendentPassGenerateAtAdmissionTime="0";
	private int nTotalRows = 0;
	private String strTotalRows = "0";
	private String strGenWardTempName = "0";
	private String strPrivateWardTempName = "0";
	//private String strAdmissionChargeTakenAtCounter="0";
	private String strAdmissionOffline="0";
	private String  strAdmissionOnline="0";
	private String strNoOfFreePass ="0";
	private String strNoOfPaidPass ="0";
	private String strNewFreePassValidity="0";
	private String strNewPaidPassValidity="0";
	private String strRenewFreePassValidity="0";
	private String strRenewPaidPassValidity="0";
	
	private String strPaidPassCharge="0.00";
	private String strPaidPassRenewCharge="0.00";
	
	private String strSummerMorningFromTime="00:00";
	private String strSummerMorningFromHr="00";
	private String strSummerMorningFromMin="00";
	
	private String strSummerMorningToTime="00:00";
	private String strSummerMorningToHr="00";
	private String strSummerMorningToMin="00";
	
	private String strSummerEveningFromTime="00:00";
	private String strSummerEveningFromHr="00";
	private String strSummerEveningFromMin="00";
	
	private String strSummerEveningToTime="00:00";
	private String strSummerEveningToHr="00";
	private String strSummerEveningToMin="00";
	
	private String strWinterMorningFromTime="00:00";
	private String strWinterMorningFromHr="00";
	private String strWinterMorningFromMin="00";
	
	private String strWinterMorningToTime="00:00";
	private String strWinterMorningToHr="00";
	private String strWinterMorningToMin="00";
	
	private String strWinterEveningFromTime="00:00";
	private String strWinterEveningFromHr="00";
	private String strWinterEveningFromMin="00";
	
	private String strWinterEveningToTime="00:00";
	private String strWinterEveningToHr="00";
	private String strWinterEveningToMin="00";
	
	private String strPrintRequest="0";
	
	private String strGenMultiRow = null;
	private String strPrivateMultiRow = null;
	private String strNoOfFreePassAdmisssionTime="0";
	private String strMsg = "";
	private String strAdmissionAdviceValidityFrom="0";
	private String strAdmissionAdviceValidityTo="0";
	private String strNewBornBabyAdmissionCharge="0";
	private String strNewBornBabyRegistrationCharge="0";
	private String strModificationTimeValidity="0";
	private String strDischargeCancellationTime="0";
	private String strAdvanceAmountNewBornBaby="0";
	private String strBlockedExpiryTime="00";
	private String strPrivateWardType="0";
	private String strIcuWardType="0";
	private String strDischargeTypeLAMA="0";
	private String strDischargeTypeAbsconded="0";
	private String strDischargeTypeReferral="0";
	private String strDischargeTypeTransfer="0";
	private String strDischargeTypeDeath="0";
	
	private String strIcuWardTypeValues="";
	private String strPrivateWardTypeValues="";
	private String strDischargeTypeLAMAValues="";
	private String strDischargeTypeAbscondedValues="";
	private String strDischargeTypeReferralValues="";
	private String strDischargeTypeTransferValues="";
	private String strDischargeTypeDeathValues="";
	
	private String strNormalDischargeTypeValues="";
	private String strNormalDischargeType="";
	private String strHospCode="";
	// IPD Report Dtls
	
	private String strDischargeSummaryReportAdvice = "";
	private String strDischargeSummaryReportFooter = "";
	
	private String strIntegrationBilling="";
	private String strAdvanceRequestAdmissionAdvice="";
	private String strAdvanceAmountAdmission="";
	private String strPatientAdjustedFinalDischargeBill="";
	private String strBedChange="";
	private String strRefundRequestAdmissionCancellation="";
	private String strPurgingRecordCurrentlyAdmissionDetails="";
	private String strPurgeTimeNotReportedPatient="";
	private String strSeatId="";
	private String strDischargeProcessType="";
	private String strNewBornBabyProcessType="";
	private String strMinAgeToBeMother="";
	private String strMaxNoOfBabyMotherCanBorn="";
	private String strNotReportedLeavehour = "";
	private String strNotReportedOutsidehour = "";
	private String strDiagType="";
	private String strAdmissionAdviceMode="";
	private String strBelongingRequired="";
	private String strIssueItemRequired="";
	private String strNurseChecklistMandatory="";
	private String strRoomReq="";
	private String strUnitReq="";
	private String strNotReportedTimeLimit="10";
	private String strBedAllotmentAtAdmission="0";
	private String strNoOfSlipPrintedAtAdmission="";
	private String strBedLimit="1";
	private String strLeaveReqType="2";
	
	private String strAdmissionCharge="";
	private String  strDischargeSlipReq="1";
	private String strBabyDept="0";
	private String strBabyDeptValues="0";
	
	public String getStrDischargeSlipReq() {
		return strDischargeSlipReq;
	}

	public void setStrDischargeSlipReq(String strDischargeSlipReq) {
		this.strDischargeSlipReq = strDischargeSlipReq;
	}

	public String getStrNormalDischargeType() {
		return strNormalDischargeType;
	}

	public void setStrNormalDischargeType(String strNormalDischargeType) {
		this.strNormalDischargeType = strNormalDischargeType;
	}

	public String getStrAdmissionCharge() {
		return strAdmissionCharge;
	}

	public void setStrAdmissionCharge(String strAdmissionCharge) {
		this.strAdmissionCharge = strAdmissionCharge;
	}

	public String getStrLeaveReqType()
	{
		return strLeaveReqType;
	}

	public void setStrLeaveReqType(String strLeaveReqType)
	{
		this.strLeaveReqType = strLeaveReqType;
	}

	public String getStrBedLimit() {
		return strBedLimit;
	}

	public void setStrBedLimit(String strBedLimit) {
		this.strBedLimit = strBedLimit;
	}

	public String getStrDiagType() {
		return strDiagType;
	}

	public void setStrDiagType(String strDiagType) {
		this.strDiagType = strDiagType;
	}

	/**
	 * @return the strNotReportedLeavehour
	 */
	public String getStrNotReportedLeavehour() {
		return strNotReportedLeavehour;
	}

	/**
	 * @param strNotReportedLeavehour the strNotReportedLeavehour to set
	 */
	public void setStrNotReportedLeavehour(String strNotReportedLeavehour) {
		this.strNotReportedLeavehour = strNotReportedLeavehour;
	}

	/**
	 * @return the strNotReportedOutsidehour
	 */
	public String getStrNotReportedOutsidehour() {
		return strNotReportedOutsidehour;
	}

	/**
	 * @param strNotReportedOutsidehour the strNotReportedOutsidehour to set
	 */
	public void setStrNotReportedOutsidehour(String strNotReportedOutsidehour) {
		this.strNotReportedOutsidehour = strNotReportedOutsidehour;
	}

	/**
	 * @return the strMinAgeToBeMother
	 */
	public String getStrMinAgeToBeMother() {
		return strMinAgeToBeMother;
	}

	/**
	 * @param strMinAgeToBeMother the strMinAgeToBeMother to set
	 */
	public void setStrMinAgeToBeMother(String strMinAgeToBeMother) {
		this.strMinAgeToBeMother = strMinAgeToBeMother;
	}

	/**
	 * @return the strMaxNoOfBabyMotherCanBorn
	 */
	public String getStrMaxNoOfBabyMotherCanBorn() {
		return strMaxNoOfBabyMotherCanBorn;
	}

	/**
	 * @param strMaxNoOfBabyMotherCanBorn the strMaxNoOfBabyMotherCanBorn to set
	 */
	public void setStrMaxNoOfBabyMotherCanBorn(String strMaxNoOfBabyMotherCanBorn) {
		this.strMaxNoOfBabyMotherCanBorn = strMaxNoOfBabyMotherCanBorn;
	}

	/**
	 * @return the strDischargeProcessType
	 */
	public String getStrDischargeProcessType() {
		return strDischargeProcessType;
	}

	/**
	 * @param strDischargeProcessType the strDischargeProcessType to set
	 */
	public void setStrDischargeProcessType(String strDischargeProcessType) {
		this.strDischargeProcessType = strDischargeProcessType;
	}

	/**
	 * @return the strNewBornBabyProcessType
	 */
	public String getStrNewBornBabyProcessType() {
		return strNewBornBabyProcessType;
	}

	/**
	 * @param strNewBornBabyProcessType the strNewBornBabyProcessType to set
	 */
	public void setStrNewBornBabyProcessType(String strNewBornBabyProcessType) {
		this.strNewBornBabyProcessType = strNewBornBabyProcessType;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrDischargeSummaryReportAdvice() {
		return strDischargeSummaryReportAdvice;
	}

	public void setStrDischargeSummaryReportAdvice(
			String strDischargeSummaryReportAdvice) {
		this.strDischargeSummaryReportAdvice = strDischargeSummaryReportAdvice;
	}

	public String getStrDischargeSummaryReportFooter() {
		return strDischargeSummaryReportFooter;
	}

	public void setStrDischargeSummaryReportFooter(
			String strDischargeSummaryReportFooter) {
		this.strDischargeSummaryReportFooter = strDischargeSummaryReportFooter;
	}

	public String getStrBlockedExpiryTime() {
		return strBlockedExpiryTime;
	}

	public void setStrBlockedExpiryTime(String strBlockedExpiryTime) {
		this.strBlockedExpiryTime = strBlockedExpiryTime;
	}

	public String getStrAdvanceAmountNewBornBaby() {
		return strAdvanceAmountNewBornBaby;
	}

	public void setStrAdvanceAmountNewBornBaby(String strAdvanceAmountNewBornBaby) {
		this.strAdvanceAmountNewBornBaby = strAdvanceAmountNewBornBaby;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}


	public String getStrGenMultiRow() {

		InPatientConfigMstHLP hlp = new InPatientConfigMstHLP();
		strGenMultiRow = hlp.getGenWardMultiRow(this, 1);

		return strGenMultiRow;
	}

	public String getStrPrivateMultiRow() {

		InPatientConfigMstHLP hlp = new InPatientConfigMstHLP();
		strPrivateMultiRow = hlp.getPrivateWardMultiRow(this, 2);

		return strPrivateMultiRow;
	}

	public int getNTotalRows() {
		return nTotalRows;
	}

	public void setNTotalRows(int totalRows) {
		nTotalRows = totalRows;
	}

	public String getStrGenWardTempName() {
		return strGenWardTempName;
	}

	public void setStrGenWardTempName(String strGenWardTempName) {
		this.strGenWardTempName = strGenWardTempName;
	}

	public String getStrPrivateWardTempName() {
		return strPrivateWardTempName;
	}

	public void setStrPrivateWardTempName(String strPrivateWardTempName) {
		this.strPrivateWardTempName = strPrivateWardTempName;
	}

	public String getStrGenWardApproverValues() {

		DAOIpd dao = new DAOIpd("Ipd Configuration Master",
				"VOInPatientConfigMst");
		HisUtil util = new HisUtil("Ipd Configuration Master",
				"VOInPatientConfigMst");
		WebRowSet ws = null;
		try {
			//ws = dao.getEmployeeDtl(true);
			//strGenWardApproverValues = util.getOptionValue(ws, this
			//		.getStrGenWardTempName(), "0^Select Value", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrGenWardApproverValues -->", e
							.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Ipd Configuration Master",
							"VOInPatientConfigMst.ws.close() -->", e
									.getMessage());
				}
				ws = null;
			}
		}

		return strGenWardApproverValues;
	}

	public String getStrPrivateWardApproverValues() {

		DAOIpd dao = new DAOIpd("Ipd Configuration Master",
				"VOInPatientConfigMst");
		HisUtil util = new HisUtil("Ipd Configuration Master",
				"VOInPatientConfigMst");
		WebRowSet ws = null;
		try {
//			ws = dao.getEmployeeDtl(true);


			//strPrivateWardApproverValues = util.getOptionValue(ws, this
			//		.getStrPrivateWardTempName(), "0^Select Value", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrPrivateWardApproverValues -->",
					e.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Ipd Configuration Master",
							"VOInPatientConfigMst.ws.close() -->", e
									.getMessage());
				}
				ws = null;
			}
		}

		return strPrivateWardApproverValues;
	}

	public String getStrOnlineAdvice() {
		return strOnlineAdvice;
	}

	public void setStrOnlineAdvice(String strOnlineAdvice) {
		this.strOnlineAdvice = strOnlineAdvice;
	}

	public String getStrGenWardAdmission() {
		return strGenWardAdmission;
	}

	public void setStrGenWardAdmission(String strGenWardAdmission) {
		this.strGenWardAdmission = strGenWardAdmission;
	}

	public String[] getStrGenWardApprover() {
		return strGenWardApprover;
	}

	public void setStrGenWardApprover(String[] strGenWardApprover) {
		this.strGenWardApprover = strGenWardApprover;
	}

	public String getStrPrivateWardAdmission() {
		return strPrivateWardAdmission;
	}

	public void setStrPrivateWardAdmission(String strPrivateWardAdmission) {
		this.strPrivateWardAdmission = strPrivateWardAdmission;
	}

	public String[] getStrPrivateWardApprover() {
		return strPrivateWardApprover;
	}

	public void setStrPrivateWardApprover(String[] strPrivateWardApprover) {
		this.strPrivateWardApprover = strPrivateWardApprover;
	}

	public String getStrBookBed() {
		return strBookBed;
	}

	public void setStrBookBed(String strBookBed) {
		this.strBookBed = strBookBed;
	}

	public String getStrBilling() {
		return strBilling;
	}

	public void setStrBilling(String strBilling) {
		this.strBilling = strBilling;
	}

	public String getStrNegativeBilling() {
		return strNegativeBilling;
	}

	public void setStrNegativeBilling(String strNegativeBilling) {
		this.strNegativeBilling = strNegativeBilling;
	}

	public String getStrTotalRows() {
		
		strTotalRows = String.valueOf(this.getNTotalRows());
		
		return strTotalRows;
	}

	public String getStrPatientCategoryValues() {
		
		DAOIpd dao = new DAOIpd("Ipd Configuration Master",
		"VOInPatientConfigMst");
HisUtil util = new HisUtil("Ipd Configuration Master",
		"VOInPatientConfigMst");
WebRowSet ws = null;
try {
	ws = dao.getPatCategoryDtl(true);
	strPatientCategoryValues = util.getOptionValue(ws, this.getStrPatientCategory(), "0^Select Value", false);

} catch (Exception e) {
	// TODO Auto-generated catch block
	new HisException("Ipd Configuration Master",
			"VOInPatientConfigMst.getStrPatientCategoryValues -->", e
					.getMessage());
} finally {
	dao = null;
	util = null;
	if (ws != null) {
		try {
			ws.close();
		} catch (SQLException e) {
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.ws.close() -->", e
							.getMessage());
		}
		ws = null;
	}
}
		
		return strPatientCategoryValues;
	}

	public String getStrPatientCategory() {
		return strPatientCategory;
	}

	public void setStrPatientCategory(String strPatientCategory) {
		this.strPatientCategory = strPatientCategory;
	}

	public String getStrGovtEmpPayLimit() {
		return strGovtEmpPayLimit;
	}

	public void setStrGovtEmpPayLimit(String strGovtEmpPayLimit) {
		this.strGovtEmpPayLimit = strGovtEmpPayLimit;
	}

	public String getStrPvtEmpIncomeLimit() {
		return strPvtEmpIncomeLimit;
	}

	public void setStrPvtEmpIncomeLimit(String strPvtEmpIncomeLimit) {
		this.strPvtEmpIncomeLimit = strPvtEmpIncomeLimit;
	}

	public String getStrAdmissionAdviceValidity() {
		return strAdmissionAdviceValidity;
	}

	public void setStrAdmissionAdviceValidity(String strAdmissionAdviceValidity) {
		this.strAdmissionAdviceValidity = strAdmissionAdviceValidity;
	}

	public java.util.LinkedHashMap<String, String> getLhm() {
		
		lhm.put("General","ipdgeneral");
		lhm.put("Admission Details","ipdadmdtls");
		lhm.put("Discharge Details","ipddischargedtls");
		lhm.put("Pass Details","ipdpassdtls");
		lhm.put("Report Details", "ipdreportdtls");
		lhm.put("Bill Details", "ipdbilldtls");
		lhm.put("Print Details", "ipdprintdtls");
		return lhm;
	}

	public String getStrNoOfFreePass() {
		return strNoOfFreePass;
	}

	public void setStrNoOfFreePass(String strNoOfFreePass) {
		this.strNoOfFreePass = strNoOfFreePass;
	}

	public String getStrNoOfPaidPass() {
		return strNoOfPaidPass;
	}

	public void setStrNoOfPaidPass(String strNoOfPaidPass) {
		this.strNoOfPaidPass = strNoOfPaidPass;
	}

	public String getStrNewFreePassValidity() {
		return strNewFreePassValidity;
	}

	public void setStrNewFreePassValidity(String strNewFreePassValidity) {
		this.strNewFreePassValidity = strNewFreePassValidity;
	}

	public String getStrNewPaidPassValidity() {
		return strNewPaidPassValidity;
	}

	public void setStrNewPaidPassValidity(String strNewPaidPassValidity) {
		this.strNewPaidPassValidity = strNewPaidPassValidity;
	}

	public String getStrRenewFreePassValidity() {
		return strRenewFreePassValidity;
	}

	public void setStrRenewFreePassValidity(String strRenewFreePassValidity) {
		this.strRenewFreePassValidity = strRenewFreePassValidity;
	}

	public String getStrRenewPaidPassValidity() {
		return strRenewPaidPassValidity;
	}

	public void setStrRenewPaidPassValidity(String strRenewPaidPassValidity) {
		this.strRenewPaidPassValidity = strRenewPaidPassValidity;
	}

	public String getStrSummerMorningFromTime() {
		return strSummerMorningFromTime;
	}

	public void setStrSummerMorningFromTime(String strSummerMorningFromTime) {
		this.strSummerMorningFromTime = strSummerMorningFromTime;
	}

	public String getStrSummerMorningToTime() {
		return strSummerMorningToTime;
	}

	public void setStrSummerMorningToTime(String strSummerMorningToTime) {
		this.strSummerMorningToTime = strSummerMorningToTime;
	}

	public String getStrSummerEveningFromTime() {
		return strSummerEveningFromTime;
	}

	public void setStrSummerEveningFromTime(String strSummerEveningFromTime) {
		this.strSummerEveningFromTime = strSummerEveningFromTime;
	}

	public String getStrSummerEveningToTime() {
		return strSummerEveningToTime;
	}

	public void setStrSummerEveningToTime(String strSummerEveningToTime) {
		this.strSummerEveningToTime = strSummerEveningToTime;
	}

	public String getStrWinterMorningFromTime() {
		return strWinterMorningFromTime;
	}

	public void setStrWinterMorningFromTime(String strWinterMorningFromTime) {
		this.strWinterMorningFromTime = strWinterMorningFromTime;
	}

	public String getStrWinterMorningToTime() {
		return strWinterMorningToTime;
	}

	public void setStrWinterMorningToTime(String strWinterMorningToTime) {
		this.strWinterMorningToTime = strWinterMorningToTime;
	}

	public String getStrWinterEveningFromTime() {
		return strWinterEveningFromTime;
	}

	public void setStrWinterEveningFromTime(String strWinterEveningFromTime) {
		this.strWinterEveningFromTime = strWinterEveningFromTime;
	}

	public String getStrWinterEveningToTime() {
		return strWinterEveningToTime;
	}

	public void setStrWinterEveningToTime(String strWinterEveningToTime) {
		this.strWinterEveningToTime = strWinterEveningToTime;
	}

	public String getStrPrintRequest() {
		return strPrintRequest;
	}

	public void setStrPrintRequest(String strPrintRequest) {
		this.strPrintRequest = strPrintRequest;
	}

	public void setStrPrivateWardApproverValues(String strPrivateWardApproverValues) {
		this.strPrivateWardApproverValues = strPrivateWardApproverValues;
	}

	public void setStrPrivateMultiRow(String strPrivateMultiRow) {
		this.strPrivateMultiRow = strPrivateMultiRow;
	}

	public String getStrSummerMorningFromHr() {
		return strSummerMorningFromHr;
	}

	public void setStrSummerMorningFromHr(String strSummerMorningFromHr) {
		this.strSummerMorningFromHr = strSummerMorningFromHr;
	}

	public String getStrSummerMorningFromMin() {
		return strSummerMorningFromMin;
	}

	public void setStrSummerMorningFromMin(String strSummerMorningFromMin) {
		this.strSummerMorningFromMin = strSummerMorningFromMin;
	}

	public String getStrSummerMorningToHr() {
		return strSummerMorningToHr;
	}

	public void setStrSummerMorningToHr(String strSummerMorningToHr) {
		this.strSummerMorningToHr = strSummerMorningToHr;
	}

	public String getStrSummerMorningToMin() {
		return strSummerMorningToMin;
	}

	public void setStrSummerMorningToMin(String strSummerMorningToMin) {
		this.strSummerMorningToMin = strSummerMorningToMin;
	}

	public String getStrSummerEveningFromHr() {
		return strSummerEveningFromHr;
	}

	public void setStrSummerEveningFromHr(String strSummerEveningFromHr) {
		this.strSummerEveningFromHr = strSummerEveningFromHr;
	}

	public String getStrSummerEveningFromMin() {
		return strSummerEveningFromMin;
	}

	public void setStrSummerEveningFromMin(String strSummerEveningFromMin) {
		this.strSummerEveningFromMin = strSummerEveningFromMin;
	}

	public String getStrSummerEveningToHr() {
		return strSummerEveningToHr;
	}

	public void setStrSummerEveningToHr(String strSummerEveningToHr) {
		this.strSummerEveningToHr = strSummerEveningToHr;
	}

	public String getStrSummerEveningToMin() {
		return strSummerEveningToMin;
	}

	public void setStrSummerEveningToMin(String strSummerEveningToMin) {
		this.strSummerEveningToMin = strSummerEveningToMin;
	}

	public String getStrWinterMorningFromHr() {
		return strWinterMorningFromHr;
	}

	public void setStrWinterMorningFromHr(String strWinterMorningFromHr) {
		this.strWinterMorningFromHr = strWinterMorningFromHr;
	}

	public String getStrWinterMorningFromMin() {
		return strWinterMorningFromMin;
	}

	public void setStrWinterMorningFromMin(String strWinterMorningFromMin) {
		this.strWinterMorningFromMin = strWinterMorningFromMin;
	}

	public String getStrWinterMorningToHr() {
		return strWinterMorningToHr;
	}

	public void setStrWinterMorningToHr(String strWinterMorningToHr) {
		this.strWinterMorningToHr = strWinterMorningToHr;
	}

	public String getStrWinterMorningToMin() {
		return strWinterMorningToMin;
	}

	public void setStrWinterMorningToMin(String strWinterMorningToMin) {
		this.strWinterMorningToMin = strWinterMorningToMin;
	}

	public String getStrWinterEveningFromHr() {
		return strWinterEveningFromHr;
	}

	public void setStrWinterEveningFromHr(String strWinterEveningFromHr) {
		this.strWinterEveningFromHr = strWinterEveningFromHr;
	}

	public String getStrWinterEveningFromMin() {
		return strWinterEveningFromMin;
	}

	public void setStrWinterEveningFromMin(String strWinterEveningFromMin) {
		this.strWinterEveningFromMin = strWinterEveningFromMin;
	}

	public String getStrWinterEveningToHr() {
		return strWinterEveningToHr;
	}

	public void setStrWinterEveningToHr(String strWinterEveningToHr) {
		this.strWinterEveningToHr = strWinterEveningToHr;
	}

	public String getStrWinterEveningToMin() {
		return strWinterEveningToMin;
	}

	public void setStrWinterEveningToMin(String strWinterEveningToMin) {
		this.strWinterEveningToMin = strWinterEveningToMin;
	}

	/*public String getStrAdmissionChargeTakenAtCounter() {
		return strAdmissionChargeTakenAtCounter;
	}

	public void setStrAdmissionChargeTakenAtCounter(
			String strAdmissionChargeTakenAtCounter) {
		this.strAdmissionChargeTakenAtCounter = strAdmissionChargeTakenAtCounter;
	}*/

	public String getStrAdmissionAdviceValidityFrom() {
		return strAdmissionAdviceValidityFrom;
	}

	public void setStrAdmissionAdviceValidityFrom(
			String strAdmissionAdviceValidityFrom) {
		this.strAdmissionAdviceValidityFrom = strAdmissionAdviceValidityFrom;
	}

	public String getStrAdmissionAdviceValidityTo() {
		return strAdmissionAdviceValidityTo;
	}

	public void setStrAdmissionAdviceValidityTo(String strAdmissionAdviceValidityTo) {
		this.strAdmissionAdviceValidityTo = strAdmissionAdviceValidityTo;
	}

	public String getStrNewBornBabyAdmissionCharge() {
		return strNewBornBabyAdmissionCharge;
	}

	public void setStrNewBornBabyAdmissionCharge(
			String strNewBornBabyAdmissionCharge) {
		this.strNewBornBabyAdmissionCharge = strNewBornBabyAdmissionCharge;
	}

	public String getStrNewBornBabyRegistrationCharge() {
		return strNewBornBabyRegistrationCharge;
	}

	public void setStrNewBornBabyRegistrationCharge(
			String strNewBornBabyRegistrationCharge) {
		this.strNewBornBabyRegistrationCharge = strNewBornBabyRegistrationCharge;
	}

	public String getStrNoOfFreePassAdmisssionTime() {
		return strNoOfFreePassAdmisssionTime;
	}

	public void setStrNoOfFreePassAdmisssionTime(
			String strNoOfFreePassAdmisssionTime) {
		this.strNoOfFreePassAdmisssionTime = strNoOfFreePassAdmisssionTime;
	}

	public String getStrModificationTimeValidity() {
		return strModificationTimeValidity;
	}

	public void setStrModificationTimeValidity(String strModificationTimeValidity) {
		this.strModificationTimeValidity = strModificationTimeValidity;
	}

	public String getStrDischargeCancellationTime() {
		return strDischargeCancellationTime;
	}

	public void setStrDischargeCancellationTime(String strDischargeCancellationTime) {
		this.strDischargeCancellationTime = strDischargeCancellationTime;
	}

	public String getStrPaidPassCharge() {
		return strPaidPassCharge;
	}

	public void setStrPaidPassCharge(String strPaidPassCharge) {
		this.strPaidPassCharge = strPaidPassCharge;
	}

	public String getStrPaidPassRenewCharge() {
		return strPaidPassRenewCharge;
	}

	public void setStrPaidPassRenewCharge(String strPaidPassRenewCharge) {
		this.strPaidPassRenewCharge = strPaidPassRenewCharge;
	}

	public String getStrAdmissionReprintCharge() {
		return strAdmissionReprintCharge;
	}

	public void setStrAdmissionReprintCharge(String strAdmissionReprintCharge) {
		this.strAdmissionReprintCharge = strAdmissionReprintCharge;
	}

	public String getStrPassReprintCharges() {
		return strPassReprintCharges;
	}

	public void setStrPassReprintCharges(String strPassReprintCharges) {
		this.strPassReprintCharges = strPassReprintCharges;
	}

	public String getStrDischargeReprintCharge() {
		return strDischargeReprintCharge;
	}

	public void setStrDischargeReprintCharge(String strDischargeReprintCharge) {
		this.strDischargeReprintCharge = strDischargeReprintCharge;
	}

	public String getStrNoOfLineInAdmissionSlip() {
		return strNoOfLineInAdmissionSlip;
	}

	public void setStrNoOfLineInAdmissionSlip(String strNoOfLineInAdmissionSlip) {
		this.strNoOfLineInAdmissionSlip = strNoOfLineInAdmissionSlip;
	}

	public String getStrNoOfLineInVisitorPassSlip() {
		return strNoOfLineInVisitorPassSlip;
	}

	public void setStrNoOfLineInVisitorPassSlip(String strNoOfLineInVisitorPassSlip) {
		this.strNoOfLineInVisitorPassSlip = strNoOfLineInVisitorPassSlip;
	}

	public String getStrAdmissionOffline() {
		return strAdmissionOffline;
	}

	public void setStrAdmissionOffline(String strAdmissionOffline) {
		this.strAdmissionOffline = strAdmissionOffline;
	}

	public String getStrAdmissionOnline() {
		return strAdmissionOnline;
	}

	public void setStrAdmissionOnline(String strAdmissionOnline) {
		this.strAdmissionOnline = strAdmissionOnline;
	}

	
		
		
	public String getStrHospCode() {
		return strHospCode;
	}

	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}

	public String getStrPrivateWardType() {
		return strPrivateWardType;
	}

	public void setStrPrivateWardType(String strPrivateWardType) {
		this.strPrivateWardType = strPrivateWardType;
	}

	public String getStrPrivateWardTypeValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strPrivateWardTypeValues=bo.getPrivateWardType(this.strHospCode,this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrPrivateWardTypeValues -->", e
							.getMessage());
		}
		return strPrivateWardTypeValues;
	}

	public String getStrIcuWardTypeValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strIcuWardTypeValues=bo.getIcuWardType(this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrPrivateWardTypeValues -->", e
							.getMessage());
		}
		
		return strIcuWardTypeValues;
	}

	

	public void setStrPrivateWardTypeValues(String strPrivateWardTypeValues) {
		this.strPrivateWardTypeValues = strPrivateWardTypeValues;
	}

	
	public String getStrDischargeTypeLAMA() {
		return strDischargeTypeLAMA;
	}

	public void setStrDischargeTypeLAMA(String strDischargeTypeLAMA) {
		this.strDischargeTypeLAMA = strDischargeTypeLAMA;
	}

	public String getStrDischargeTypeAbsconded() {
		return strDischargeTypeAbsconded;
	}

	public void setStrDischargeTypeAbsconded(String strDischargeTypeAbsconded) {
		this.strDischargeTypeAbsconded = strDischargeTypeAbsconded;
	}

	public String getStrDischargeTypeReferral() {
		return strDischargeTypeReferral;
	}

	public void setStrDischargeTypeReferral(String strDischargeTypeReferral) {
		this.strDischargeTypeReferral = strDischargeTypeReferral;
	}

	public String getStrDischargeTypeTransfer() {
		return strDischargeTypeTransfer;
	}

	public void setStrDischargeTypeTransfer(String strDischargeTypeTransfer) {
		this.strDischargeTypeTransfer = strDischargeTypeTransfer;
	}

	public String getStrDischargeTypeDeath() {
		return strDischargeTypeDeath;
	}

	public void setStrDischargeTypeDeath(String strDischargeTypeDeath) {
		this.strDischargeTypeDeath = strDischargeTypeDeath;
	}

	public String getStrDischargeTypeLAMAValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strDischargeTypeLAMAValues=bo.getDischargeTypeLAMA(this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrDischargeTypeLAMAValues -->", e
							.getMessage());
		}
		return strDischargeTypeLAMAValues;
	}

	public void setStrDischargeTypeLAMAValues(String strDischargeTypeLAMAValues) {
		this.strDischargeTypeLAMAValues = strDischargeTypeLAMAValues;
	}

	
	public String getStrNormalDischargeTypeValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strNormalDischargeTypeValues=bo.getNormalDischargeType(this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master","VOInPatientConfigMst.getStrNormalDischargeTypeValues -->", e.getMessage());
		}
		return strNormalDischargeTypeValues;
	}

	public void setStrNormalDischargeTypeValues(String strNormalDischargeTypeValues) {
			this.strNormalDischargeTypeValues = strNormalDischargeTypeValues;
	}

	
	public String getStrDischargeTypeAbscondedValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strDischargeTypeAbscondedValues=bo.getDischargeTypeAbsconded(this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrDischargeTypeAbscondedValues -->", e.getMessage());
		}
		return strDischargeTypeAbscondedValues;
	}

	public void setStrDischargeTypeAbscondedValues(
			String strDischargeTypeAbscondedValues) {
		this.strDischargeTypeAbscondedValues = strDischargeTypeAbscondedValues;
	}

	public String getStrDischargeTypeReferralValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strDischargeTypeReferralValues=bo.getDischargeTypeReferral(this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrDischargeTypeReferralValues -->", e.getMessage());
		}
		return strDischargeTypeReferralValues;
	}

	public void setStrDischargeTypeReferralValues(
			String strDischargeTypeReferralValues) {
		this.strDischargeTypeReferralValues = strDischargeTypeReferralValues;
	}

	public String getStrDischargeTypeTransferValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strDischargeTypeTransferValues=bo.getDischargeTypeTransfer(this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrDischargeTypeTransferValues -->", e.getMessage());
		}
		return strDischargeTypeTransferValues;
	}

	public void setStrDischargeTypeTransferValues(String strDischargeTypeTransferValues) {
		this.strDischargeTypeTransferValues = strDischargeTypeTransferValues;
	}

	public String getStrDischargeTypeDeathValues() {
		try
		{
			InPatientConfigMstBO bo = new InPatientConfigMstBO();
			strDischargeTypeDeathValues=bo.getDischargeTypeDeath(this);
		}
		catch(Exception e)
		{
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrDischargeTypeDeathValues -->", e.getMessage());
		}
		return strDischargeTypeDeathValues;
	}

	public void setStrDischargeTypeDeathValues(String strDischargeTypeDeathValues) {
		this.strDischargeTypeDeathValues = strDischargeTypeDeathValues;
	}

	public String getStrIcuWardType() {
		return strIcuWardType;
	}

	public void setStrIcuWardType(String strIcuWardType) {
		this.strIcuWardType = strIcuWardType;
	}

	public String getStrAdvanceRequestAdmissionAdvice() {
		return strAdvanceRequestAdmissionAdvice;
	}

	public void setStrAdvanceRequestAdmissionAdvice(
			String strAdvanceRequestAdmissionAdvice) {
		this.strAdvanceRequestAdmissionAdvice = strAdvanceRequestAdmissionAdvice;
	}

	public String getStrAdvanceAmountAdmission() {
		return strAdvanceAmountAdmission;
	}

	public void setStrAdvanceAmountAdmission(String strAdvanceAmountAdmission) {
		this.strAdvanceAmountAdmission = strAdvanceAmountAdmission;
	}

	public String getStrPatientAdjustedFinalDischargeBill() {
		return strPatientAdjustedFinalDischargeBill;
	}

	public void setStrPatientAdjustedFinalDischargeBill(
			String strPatientAdjustedFinalDischargeBill) {
		this.strPatientAdjustedFinalDischargeBill = strPatientAdjustedFinalDischargeBill;
	}

	public String getStrBedChange() {
		return strBedChange;
	}

	public void setStrBedChange(String strBedChange) {
		this.strBedChange = strBedChange;
	}

	public String getStrRefundRequestAdmissionCancellation() {
		return strRefundRequestAdmissionCancellation;
	}

	public void setStrRefundRequestAdmissionCancellation(
			String strRefundRequestAdmissionCancellation) {
		this.strRefundRequestAdmissionCancellation = strRefundRequestAdmissionCancellation;
	}

	public void setStrPatientCategoryValues(String strPatientCategoryValues) {
		this.strPatientCategoryValues = strPatientCategoryValues;
	}

	public String getStrIntegrationBilling() {
		return strIntegrationBilling;
	}

	public void setStrIntegrationBilling(String strIntegrationBilling) {
		this.strIntegrationBilling = strIntegrationBilling;
	}

	public String getStrNoOfLineInNewBornBabyAdmissionSlip() {
		return strNoOfLineInNewBornBabyAdmissionSlip;
	}

	public void setStrNoOfLineInNewBornBabyAdmissionSlip(
			String strNoOfLineInNewBornBabyAdmissionSlip) {
		this.strNoOfLineInNewBornBabyAdmissionSlip = strNoOfLineInNewBornBabyAdmissionSlip;
	}

	public String getStrPurgingRecordCurrentlyAdmissionDetails() {
		return strPurgingRecordCurrentlyAdmissionDetails;
	}

	public void setStrPurgingRecordCurrentlyAdmissionDetails(
			String strPurgingRecordCurrentlyAdmissionDetails) {
		this.strPurgingRecordCurrentlyAdmissionDetails = strPurgingRecordCurrentlyAdmissionDetails;
	}

	public String getStrPurgeTimeNotReportedPatient() {
		return strPurgeTimeNotReportedPatient;
	}

	public void setStrPurgeTimeNotReportedPatient(
			String strPurgeTimeNotReportedPatient) {
		this.strPurgeTimeNotReportedPatient = strPurgeTimeNotReportedPatient;
	}

	public String getStrMsApprovalOffline() {
		return strMsApprovalOffline;
	}

	public void setStrMsApprovalOffline(String strMsApprovalOffline) {
		this.strMsApprovalOffline = strMsApprovalOffline;
	}

	public String getStrAttendentPass() {
		return strAttendentPass;
	}

	public void setStrAttendentPass(String strAttendentPass) {
		this.strAttendentPass = strAttendentPass;
	}

	public String getStrAttendentPassGenerateAtAdmissionTime() {
		return strAttendentPassGenerateAtAdmissionTime;
	}

	public void setStrAttendentPassGenerateAtAdmissionTime(
			String strAttendentPassGenerateAtAdmissionTime) {
		this.strAttendentPassGenerateAtAdmissionTime = strAttendentPassGenerateAtAdmissionTime;
	}

	public String getStrAdmissionAdviceMode() {
		return strAdmissionAdviceMode;
	}

	public void setStrAdmissionAdviceMode(String strAdmissionAdviceMode) {
		this.strAdmissionAdviceMode = strAdmissionAdviceMode;
	}

	public String getStrBelongingRequired() {
		return strBelongingRequired;
	}

	public void setStrBelongingRequired(String strBelongingRequired) {
		this.strBelongingRequired = strBelongingRequired;
	}

	public String getStrIssueItemRequired() {
		return strIssueItemRequired;
	}

	public void setStrIssueItemRequired(String strIssueItemRequired) {
		this.strIssueItemRequired = strIssueItemRequired;
	}

	public String getStrNurseChecklistMandatory() {
		return strNurseChecklistMandatory;
	}

	public void setStrNurseChecklistMandatory(String strNurseChecklistMandatory) {
		this.strNurseChecklistMandatory = strNurseChecklistMandatory;
	}

	public String getStrRoomReq() {
		return strRoomReq;
	}

	public void setStrRoomReq(String strRoomReq) {
		this.strRoomReq = strRoomReq;
	}
	public String getStrUnitReq() {
		return strUnitReq;
	}
	public void setStrUnitReq(String strUnitReq) {
		this.strUnitReq = strUnitReq;
	}

	public String getStrNotReportedTimeLimit() {
		return strNotReportedTimeLimit;
	}

	public void setStrNotReportedTimeLimit(String strNotReportedTimeLimit) {
		this.strNotReportedTimeLimit = strNotReportedTimeLimit;
	}

	public String getStrBedAllotmentAtAdmission() {
		return strBedAllotmentAtAdmission;
	}

	public void setStrBedAllotmentAtAdmission(String strBedAllotmentAtAdmission) {
		this.strBedAllotmentAtAdmission = strBedAllotmentAtAdmission;
	}

	public String getStrNoOfSlipPrintedAtAdmission() {
		return strNoOfSlipPrintedAtAdmission;
	}

	public void setStrNoOfSlipPrintedAtAdmission(
			String strNoOfSlipPrintedAtAdmission) {
		this.strNoOfSlipPrintedAtAdmission = strNoOfSlipPrintedAtAdmission;
	}
	public String getStrBabyDept() {
		return strBabyDept;
	}

	public void setStrBabyDept(String strBabyDept) {
		this.strBabyDept = strBabyDept;
	}

	public String getStrBabyDeptValues() {
		
		
		DAOIpd dao = new DAOIpd("Ipd Configuration Master",
				"VOInPatientConfigMst");
		HisUtil util = new HisUtil("Ipd Configuration Master",
				"VOInPatientConfigMst");
		WebRowSet ws = null;
		try {
			ws = dao.getDepartmentDtl("1",false, getStrHospCode());
			strBabyDeptValues = util.getOptionValue(ws, this.getStrBabyDept(), "0^Select Value", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new HisException("Ipd Configuration Master",
					"VOInPatientConfigMst.getStrPatientCategoryValues -->", e
							.getMessage());
		} finally {
			dao = null;
			util = null;
			if (ws != null) {
				try {
					ws.close();
				} catch (SQLException e) {
					new HisException("Ipd Configuration Master",
							"VOInPatientConfigMst.ws.close() -->", e
									.getMessage());
				}
				ws = null;
			}
		}
				
							
		return strBabyDeptValues;
	}

	public void setStrBabyDeptValues(String strBabyDeptValues) {
		this.strBabyDeptValues = strBabyDeptValues;
	}

	
}
