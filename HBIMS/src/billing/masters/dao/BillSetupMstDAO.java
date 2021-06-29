
/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: BillSetup Master DAO
 * 
 * Created on: 16-09-2011
 */


package billing.masters.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import billing.masters.vo.BillSetupMstVO;

public class BillSetupMstDAO {

	public static boolean genUpdateQuery(BillSetupMstVO vo) throws Exception 
	{
		boolean retVal = false;
		/*String qry = billing.qryHandler_billing.getQuery(1,"update.billsetup.0");*/
		String qryUpdate = billing.qryHandler_billing.getQuery(1,"update.billsetup.1");
		HisDAO hisDao = new HisDAO("Billing","DAOBillSetup.genUpdateQuery()");
		String[] arrParamName = { "FINANCIAL_START_YEAR", "INBOUND_PURGED_DAY",
				"OUTBOUND_PURGED_DAY", "CANCELLATION_TIME",
				"DUPLICATE_PRINT_CHARGE", "RECORDS_DISPLAY_MASTER",
				"LEVEL_MODIFY_MASTER", "LEVEL_DELETE_MASTER", "RECEIPT_TYPE",
				"DUPLICATE_PRINT", "IS_WITHOUT_CRNO_REQUIRED",
				"ONLINE_REFUND_REQUEST_ALLOWED",
				"IS_REFERRING_PHYSICIAN_REQUIRED_INT",
				"IS_REFERRING_PHYSICIAN_REQUIRED_EXT",
				"IS_PREVIOUS_CRNO_REQUIRED_INT",
				"IS_PREVIOUS_CRNO_REQUIRED_EXT", "CC_CONFERMATION_TYPE",
				"IS_PREV_CRNO_SEARCHING_REQ_INT",
				"IS_PREV_CRNO_SEARCHING_REQ_EXT", "CHARGE_TYPE_EXT",
				"WARD_TYPE_EXT", "CHARGE_TYPE_INT", "WARD_TYPE_INT",
				"DAY_END_BILLING_PROCESS_TYPE", "DAY_END_BILLING_DATE_TYPE",
				"DAY_END_NON_BILLING_PROCESS_TYPE",
				"DAY_END_NON_BILLING_DATE_TYPE", "OPD_RECEIPT_TYPE",
				"IPD_RECEIPT_TYPE", "CC_REFUND_REQUIRED","PRINT_MESSAGE_LIMIT","REFUND_RECEIPT_TYPE",
				"REFUND_AGAINST_REFUND_REQUEST","INTERNAL_PATIENT","EXTERNAL_PATIENT","IS_APPROVAL_BY_REQUIRED"
				,"DEFAULT_TARIFF_CODE","PAID_CAT","AROGYASHREE_CAT","GEN_CREDIT_CAT","CGSH_CAT","WSH_CAT",
				//"CONSUMABLE_CHARGES_GROUP","CONSUMABLE_CHARGES_TARIFF",
				"CREDIT_BILL_APP_REQUIRED","DAY_END_TIME_BOUND_FLAG","DAY_END_TIME","AROGYASHREE_TS_CAT","AROGYASHREE_TS_OPD_CREDIT_LIMIT","URGENT_TARIFF_SURCHARGE","LOGO_REQ","OSTF_CAT","NIRAMAYA","HINDI_HEAD_REQ","HEAD_REQ"};
		
		String[] arrParamValue = { vo.getFinStartYear(),  vo.getInBound(),
				vo.getOutBound(), vo.getCancelTime(),
				vo.getPrintCharge(), vo.getDefNoRecords(),
				vo.getModRecords(), vo.getDelRecords(), vo.getStrReceiptType(),
				vo.getStrDuplicatePrint(), vo.getStrIsWithoutCrNoRequired(),
				vo.getStrOnlineRefundRequestAllowed(),
				vo.getStrReferringPhysicianRequiredInt(),
				vo.getStrReferringPhysicianRequiredExt(),
				vo.getStrPreviousCrNoRequiredInt(),
				vo.getStrPreviousCrNoRequiredExt(), vo.getStrCcConfirmationType(),
				vo.getStrPreviousCrNoSearchingInt(),
				vo.getStrPreviousCrNoSearchingExt(), vo.getStrChargeTypeExt(),
				vo.getStrWardTypeExt(), vo.getStrChargeTypeInt(), vo.getStrWardTypeInt(),
				vo.getStrDayEndProcessType(), vo.getStrDayEndDateType(),
				vo.getStrDayEndNonBillingProcessType(),
				vo.getStrDayEndNonBillingDateType(), vo.getStrOpdReceiptType(),
				vo.getStrIpdReceiptType(),vo.getStrCashCollectionOfflineRefundRequired(),vo.getStrPrintMessageLimit(),vo.getStrRefundReceiptType(),
				vo.getStrRefundAgainstRefundRequest(),vo.getStrInternalPatient(),vo.getStrExternalPatient(),vo.getStrIsApprovalByRequired()
				,vo.getStrDefaultTariffCode(),vo.getStrPaidCategoryValue(),vo.getStrArogyaShreeCategoryvalue(),vo.getStrGenCategoryValue(),vo.getStrCGSHCategoryvalue(),vo.getStrWSHCategoryValue(),
				//vo.getStrConsumableChargesGroupId(),vo.getStrConsumableChargesTariffCode(),
				vo.getStrCreditCashlessAppRequired(),vo.getStrDayEndTimeBoundFlag(),vo.getStrDayEndTime(),vo.getStrArogyaShreeTSCategoryvalue(),vo.getStrArogyaTSCreditLimit(),vo.getStrUrgTrfSur(),vo.getLogoReq(), vo.getStrOSTFCategoryvalue(), vo.getNiramayaValue(),vo.getHindiReq(),vo.getHeaderReq()};
		try 
		{
			/*int qryIndex1 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex1, 1, vo.getFinStartYear());
			hisDao.setQryValue(qryIndex1, 2, "1");
			hisDao.setQryValue(qryIndex1, 3, "FINANCIAL_START_YEAR");
			hisDao.setQryValue(qryIndex1, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex1, 0);

			int qryIndex2 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex2, 1, vo.getInBound());
			hisDao.setQryValue(qryIndex2, 2, "1");
			hisDao.setQryValue(qryIndex2, 3, "INBOUND_PURGED_DAY");
			hisDao.setQryValue(qryIndex2, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex2, 0);

			int qryIndex3 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex3, 1, vo.getOutBound());
			hisDao.setQryValue(qryIndex3, 2, "1");
			hisDao.setQryValue(qryIndex3, 3, "OUTBOUND_PURGED_DAY");
			hisDao.setQryValue(qryIndex3, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex3, 0);*/
			
			for(int i=0;i<arrParamName.length;i++)
			{
				int qryIndex1 = hisDao.setQuery(qryUpdate);
				hisDao.setQryValue(qryIndex1, 1, arrParamValue[i]);
				hisDao.setQryValue(qryIndex1, 2, vo.getStrSeatId());
				hisDao.setQryValue(qryIndex1, 3, "1");
				hisDao.setQryValue(qryIndex1, 4, arrParamName[i]);								
				hisDao.setQryValue(qryIndex1, 5, vo.getStrHospitalCode());
				hisDao.execute(qryIndex1, 0);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			retVal = false;
			throw new Exception("DAOBillSetup.genUpdateQuery()-->"+ e.getMessage());
		} 
		finally 
		{
			hisDao.free();
			hisDao = null;
		}
		return retVal;
	}
	
	public static boolean surUpdateQuery(BillSetupMstVO vo) throws Exception 
	{
		boolean retVal = false;
		/*String qry = billing.qryHandler_billing.getQuery(1,"update.billsetup.0");*/
		String qryUpdate = billing.qryHandler_billing.getQuery(1,"update.billsetup.1");
		HisDAO hisDao = new HisDAO("Billing","DAOBillSetup.genUpdateQuery()");
		String[] arrParamName = { "SURCHARGE_Ccu%", "SURCHARGE_Dcu%",
				"SURCHARGE_Icu%", "SURCHARGE_Idu%",
				"SURCHARGE_Cca%", "SURCHARGE_Dca%",
				"SURCHARGE_Ica%", "SURCHARGE_Ida%"};
		
		String[] arrParamValue = { vo.getStrSurCc(),
		        vo.getStrSurDc(),
		        vo.getStrSurIc(),
		        vo.getStrSurId(),
		        vo.getStrSurCc1(),
		        vo.getStrSurDc1(),
		        vo.getStrSurIc1(),
		        vo.getStrSurId1()};
		try 
		{
			/*int qryIndex1 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex1, 1, vo.getFinStartYear());
			hisDao.setQryValue(qryIndex1, 2, "1");
			hisDao.setQryValue(qryIndex1, 3, "FINANCIAL_START_YEAR");
			hisDao.setQryValue(qryIndex1, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex1, 0);

			int qryIndex2 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex2, 1, vo.getInBound());
			hisDao.setQryValue(qryIndex2, 2, "1");
			hisDao.setQryValue(qryIndex2, 3, "INBOUND_PURGED_DAY");
			hisDao.setQryValue(qryIndex2, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex2, 0);

			int qryIndex3 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex3, 1, vo.getOutBound());
			hisDao.setQryValue(qryIndex3, 2, "1");
			hisDao.setQryValue(qryIndex3, 3, "OUTBOUND_PURGED_DAY");
			hisDao.setQryValue(qryIndex3, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex3, 0);*/
			
			for(int i=0;i<arrParamName.length;i++)
			{
				int qryIndex1 = hisDao.setQuery(qryUpdate);
				hisDao.setQryValue(qryIndex1, 1, arrParamValue[i]);
				hisDao.setQryValue(qryIndex1, 2, vo.getStrSeatId());
				hisDao.setQryValue(qryIndex1, 3, "8");
				hisDao.setQryValue(qryIndex1, 4, arrParamName[i]);								
				hisDao.setQryValue(qryIndex1, 5, vo.getStrHospitalCode());
				hisDao.execute(qryIndex1, 0);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			retVal = false;
			throw new Exception("DAOBillSetup.surUpdateQuery()-->"+ e.getMessage());
		} 
		finally 
		{
			hisDao.free();
			hisDao = null;
		}
		return retVal;
	}

	public static boolean jobsUpdateQuery(BillSetupMstVO vo)
			throws Exception {

		boolean retVal = false;
		String qry = billing.qryHandler_billing.getQuery(1,
				"update.billsetup.0");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.jobsUpdateQuery()");

		try {
			int qryIndex1 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex1, 1, vo.getJob1BedCharge());
			hisDao.setQryValue(qryIndex1, 2, "6");
			hisDao.setQryValue(qryIndex1, 3, "JOB1_BED_CHARGE");
			hisDao.setQryValue(qryIndex1, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex1, 0);

			int qryIndex2 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex2, 1, vo.getJob1CompusoryCharge());
			hisDao.setQryValue(qryIndex2, 2, "6");
			hisDao.setQryValue(qryIndex2, 3, "JOB1_COMPULSORY_CHARGE");
			hisDao.setQryValue(qryIndex2, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex2, 0);

			int qryIndex3 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex3, 1, vo
					.getJob2EmgCompusoryCharge());
			hisDao.setQryValue(qryIndex3, 2, "6");
			hisDao.setQryValue(qryIndex3, 3, "JOB2_EMG_COMPULSORY_CHARGE");
			hisDao.setQryValue(qryIndex3, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex3, 0);

			int qryIndex4 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex4, 1, vo.getJob2FinStartYear());
			hisDao.setQryValue(qryIndex4, 2, "6");
			hisDao.setQryValue(qryIndex4, 3, "JOB2_FINANCIAL_START_YEAR");
			hisDao.setQryValue(qryIndex4, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex4, 0);

			synchronized (hisDao) {
				hisDao.fire();
			}

			retVal = true;
			

		} catch (Exception e) {
			retVal = false;
			throw new Exception("DAOBillSetup.jobsUpdateQuery()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}

	public static boolean ipdBedCalcUpdateQuery(BillSetupMstVO vo)
			throws Exception {

		boolean retVal = false;
		String qry = billing.qryHandler_billing.getQuery(1,
				"update.billsetup.0");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.ipdBedCalcUpdateQuery()");

		try {
			int qryIndex1 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex1, 1, vo.getIpdBedCalcWard());
			hisDao.setQryValue(qryIndex1, 2, "3");
			hisDao.setQryValue(qryIndex1, 3, "CHARGE_WARD_DAY");
			hisDao.setQryValue(qryIndex1, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex1, 0);

			int qryIndex2 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex2, 1, vo.getIpdBedCalcWardCharg());
			hisDao.setQryValue(qryIndex2, 2, "3");
			hisDao.setQryValue(qryIndex2, 3, "TRANSFER_BEDCHARGE");
			hisDao.setQryValue(qryIndex2, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex2, 0);

			int qryIndex3 = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex3, 1, vo
					.getIpdBedCalcServiceCharge());
			hisDao.setQryValue(qryIndex3, 2, "3");
			hisDao.setQryValue(qryIndex3, 3, "TRANSFER_SERVICES");
			hisDao.setQryValue(qryIndex3, 4, vo.getStrHospitalCode());
			hisDao.execute(qryIndex3, 0);

			synchronized (hisDao) {
				hisDao.fire();
			}

			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("DAOBillSetup.ipdBedCalcUpdateQuery()-->"
					+ e.getMessage());
		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}

	public static boolean ipdGenUpdateQuery(BillSetupMstVO vo)
			throws Exception {

//		boolean retVal = false;
//		String qry = billing.qryHandler_billing.getQuery(1,
//				"update.billsetup.0");
//		HisDAO hisDao = new HisDAO("Bill Setup",
//				"DAOBillSetup.ipdGenUpdateQuery()");
//
//		try {
//			int qryIndex1 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex1, 1, vo.getIpdGenCheckOutTimeHr()
//					+ ":" + vo.getIpdGenCheckOutTimeMn());
//			hisDao.setQryValue(qryIndex1, 2, "3");
//			hisDao.setQryValue(qryIndex1, 3, "CHECK_OUT_TIME_GENERAL");
//			hisDao.setQryValue(qryIndex1, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex1, 0);
//
//			int qryIndex3 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex3, 1, vo.getIpdGenCheckOutTimePrivateHr()
//					+ ":" + vo.getIpdGenCheckOutTimePrivateMn());
//			hisDao.setQryValue(qryIndex3, 2, "3");
//			hisDao.setQryValue(qryIndex3, 3, "CHECK_OUT_TIME_PRIVATE");
//			hisDao.setQryValue(qryIndex3, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex3, 0);
//
//			
//			int qryIndex2 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex2, 1, vo.getIpdGenFlexibleTime());
//			hisDao.setQryValue(qryIndex2, 2, "3");
//			hisDao.setQryValue(qryIndex2, 3, "FLEXIBLE_TIME");
//			hisDao.setQryValue(qryIndex2, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex2, 0);
//
//			
//			int qryIndex4 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex4, 1, vo.getIpdGenFlexibleTimeAdmission());
//			hisDao.setQryValue(qryIndex4, 2, "3");
//			hisDao.setQryValue(qryIndex4, 3, "CHECK_IN_FLEXIBILITY");
//			hisDao.setQryValue(qryIndex4, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex4, 0);
//
//			
//			int qryIndex5 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex5, 1, vo.getIpdGenCreditBilling());
//			hisDao.setQryValue(qryIndex5, 2, "3");
//			hisDao.setQryValue(qryIndex5, 3, "CREDIT_BILL_ALLOWED");
//			hisDao.setQryValue(qryIndex5, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex5, 0);
//			
//			int qryIndex6 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex6, 1, vo.getIpdGenExcessCreditLimit());
//			hisDao.setQryValue(qryIndex6, 2, "3");
//			hisDao.setQryValue(qryIndex6, 3, "CREDIT_LIMIT");
//			hisDao.setQryValue(qryIndex6, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex6, 0);
//			
//			int qryIndex7 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex7, 1, vo.getStrGeneralRoomType());
//			hisDao.setQryValue(qryIndex7, 2, "3");
//			hisDao.setQryValue(qryIndex7, 3, "GENERAL_ROOM_TYPE_ID");
//			hisDao.setQryValue(qryIndex7, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex7, 0);
//			
//			int qryIndex8 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex8, 1, vo.getStrGeneralWardType());
//			hisDao.setQryValue(qryIndex8, 2, "3");
//			hisDao.setQryValue(qryIndex8, 3, "GENERAL_WARD_TYPE_ID");
//			hisDao.setQryValue(qryIndex8, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex8, 0);
//			
//			int qryIndex9 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex9, 1, vo.getIpdGenServiceFreeTreatmentTime());
//			hisDao.setQryValue(qryIndex9, 2, "3");
//			hisDao.setQryValue(qryIndex9, 3, "EMG_FREECHARGE_HOUR");
//			hisDao.setQryValue(qryIndex9, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex9, 0);
//			
//			String strBedChargeFlag = "0";
//			
//			if(vo.getIpdGenAdtProcessType().equals("2")){
//				strBedChargeFlag = "1";
//			}
//			
//			
//			int qryIndex10 = hisDao.setQuery(qry);
//			hisDao.setQryValue(qryIndex10, 1, strBedChargeFlag );
//			hisDao.setQryValue(qryIndex10, 2, "10");
//			hisDao.setQryValue(qryIndex10, 3, "BED_CHARGE_FLAG");
//			hisDao.setQryValue(qryIndex10, 4, vo.getStrHospitalCode());
//			hisDao.execute(qryIndex10, 0);
//			
//			synchronized (hisDao) {
//				hisDao.fire();
//			}
//
//			retVal = true;
//
//		} catch (Exception e) {
//		 
//			retVal = false;
//			throw new Exception("DAOBillSetup.ipdGenUpdateQuery()-->"
//					+ e.getMessage());
//		} finally {
//			hisDao.free();
//			hisDao = null;
//		}
//
//		return retVal;
		
		
		
		
		
		boolean retVal = false;
		/*String qry = billing.qryHandler_billing.getQuery(1,
				"update.billsetup.0");*/
		String qryUpdate = billing.qryHandler_billing.getQuery(1,"update.billsetup.1");
		HisDAO hisDao = new HisDAO("Bill Setup","DAOBillSetup.ipdGenUpdateQuery()");
		String advanceReq="0";
		String[] arrParamName = { "IPD_THIRD_PARY_BILLING", "DISCOUNT",
				"IPD_DISCOUNT_CLERK", "REFUND_PENALTY",
				"CREDIT_BILLING", "PART_PAYMENT_DURATION",
				"IPD_ROUND_OFF", "SERVICE_TAX_ON_TOTAL_BILL", "EXCESS_CREDIT_LIMIT",
				"IPD_GEN_ADT_PROCESS_TYPE", "IPD_SERVICE_TAX",
				"CHECK_OUT_TIME_GENERAL",
				"CHECK_OUT_TIME_PRIVATE",
				"FLEXIBLE_TIME",
				"CHECK_IN_FLEXIBILITY",
				"GENERAL_ROOM_TYPE_ID",
				"GENERAL_WARD_TYPE_ID",
				"EMG_FREECHARGE_HOUR",
				"IPD_FREE_CATEGORY",
				"IS_ADVANCE_REQUIRED","IPD_RE_OPEN_VALIDITY_DAYS"};
		if(vo.getIpdGenAdvanceReq()!=null)
			advanceReq=vo.getIpdGenAdvanceReq();
		
		String[] arrParamValue = { vo.getIpdGenThirdPartyBilling(),vo.getIpdGenDiscount(),
				vo.getIpdGenClerkDiscount(),vo.getIpdGenPenalty(),
				vo.getIpdGenCreditBilling(),vo.getIpdGenPayment(),
				vo.getIpdGenRoundOff(),vo.getIpdGenServiceTaxOnTotalBill(),vo.getIpdGenExcessCreditLimit(),
				vo.getIpdGenAdtProcessType(),vo.getIpdServiceTax(),
				vo.getIpdGenCheckOutTimeHr()+ ":" + vo.getIpdGenCheckOutTimeMn(), 
				vo.getIpdGenCheckOutTimePrivateHr()+ ":" + vo.getIpdGenCheckOutTimePrivateMn(),
				vo.getIpdGenFlexibleTime(),
				vo.getIpdGenFlexibleTimeAdmission(),
				vo.getStrGeneralRoomType(),
				vo.getStrGeneralWardType(),
				vo.getIpdGenServiceFreeTreatmentTime(),
				vo.getIpdFreeCategory(),
				vo.getIpdGenAdvanceReq(),vo.getIpdGenReOpenValidity()};
		

		try {
						
			for(int i=0;i<arrParamName.length;i++)
			{
				int qryIndex1 = hisDao.setQuery(qryUpdate);
				hisDao.setQryValue(qryIndex1, 1, arrParamValue[i]);
				hisDao.setQryValue(qryIndex1, 2, vo.getStrSeatId());
				hisDao.setQryValue(qryIndex1, 3, "3");
				hisDao.setQryValue(qryIndex1, 4, arrParamName[i]);								
				hisDao.setQryValue(qryIndex1, 5, vo.getStrHospitalCode());
				hisDao.execute(qryIndex1, 0);
			}
			

			synchronized (hisDao) {
				hisDao.fire();
			}

			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("DAOBillSetup.ipdGenUpdateQuery()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
		
		
				
		
	}

	public static boolean emgUpdateQuery(BillSetupMstVO vo) throws Exception {

//		boolean retVal = false;
//		String qry = billing.qryHandler_billing.getQuery(1,
//				"insert.billsetup.0");
//
//		HisDAO hisDao = new HisDAO("Bill Setup",
//				"DAOBillSetup.emgUpdateQuery()");
//
//		try {
//			int gnLen = 0;
//
//			if (vo.getEmgGroupName() != null) {
//				gnLen = vo.getEmgGroupName().length;
//
//				for (int i = 0; i < gnLen; i++) {
//					int qryIndex1 = hisDao.setQuery(qry);
//					hisDao.setQryValue(qryIndex1, 1, "4");
//					hisDao.setQryValue(qryIndex1, 2, "3");
//					hisDao.setQryValue(qryIndex1, 3, "");
//					hisDao.setQryValue(qryIndex1, 4,
//							vo.getEmgGroupName()[i]);
//					hisDao.setQryValue(qryIndex1, 5, vo
//							.getEmgTariffName()[i]);
//					hisDao.setQryValue(qryIndex1, 6, vo.getEmgUnit()[i]);
//					hisDao.setQryValue(qryIndex1, 7, "10001");
//					hisDao.setQryValue(qryIndex1, 8, "");
//					hisDao.setQryValue(qryIndex1, 9, vo.getStrHospitalCode());
//					hisDao.execute(qryIndex1, 0);
//				}
//
//				synchronized (hisDao) {
//					hisDao.fire();
//				}
//			}
//			retVal = true;
//
//		} catch (Exception e) {
//			retVal = false;
//			throw new Exception("DAOBillSetup.emgUpdateQuery()-->"
//					+ e.getMessage());
//
//		} finally {
//			hisDao.free();
//			hisDao = null;
//		}
//
//		return retVal;
		
		
		boolean retVal=false;
		String qryUpdate = billing.qryHandler_billing.getQuery(1,"update.billsetup.1");
		HisDAO hisDao = new HisDAO("Bill Setup","BillSetupMstDAO.EmgInsert()");
		
		String[] arrParamName = {"EMG_THIRD_PARY_BILLING","DISCOUNT",
				"EMG_DISCOUNT_CLERK","REFUND_PENALTY",
				"EMG_ROUND_OFF","EMG_SERVICE_TAX",
				 "EMG_FREE_CATEGORY"};
		
		String[] arrParamValue = {vo.getEmgThirdPartyBilling(),vo.getEmgDiscount(),
				vo.getEmgDiscountClerk(),vo.getEmgRefundPenalty(),
				vo.getEmgRoundOff(),vo.getEmgServiceTax(),
				vo.getEmgFreeCategory()};
		
		try {
			
			for(int i=0;i<arrParamName.length;i++)
			{
				int qryIndex1 = hisDao.setQuery(qryUpdate);
				hisDao.setQryValue(qryIndex1, 1, arrParamValue[i]);
				hisDao.setQryValue(qryIndex1, 2, vo.getStrSeatId());
				hisDao.setQryValue(qryIndex1, 3, "4");
				hisDao.setQryValue(qryIndex1, 4, arrParamName[i]);								
				hisDao.setQryValue(qryIndex1, 5, vo.getStrHospitalCode());
				hisDao.execute(qryIndex1, 0);
			}
			
			synchronized (hisDao) {
				hisDao.fire();
			}
			
			
			
			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("BillSetupMstDAO.opdUpdateQuery()-->"
					+ e.getMessage()+ "=="+e.toString());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
		
		
		
	}

	public static boolean ipdComplChargeUpdateQuery(BillSetupMstVO vo)
			throws Exception {

		boolean retVal = false;
		String qry = billing.qryHandler_billing.getQuery(1,
				"insert.billsetup.0");

		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.emgUpdateQuery()");

		try {

			int gnLen = 0;
			 
			
			if (vo.getThirdGroupName() != null) {
				gnLen = vo.getThirdGroupName().length;

				for (int i = 0; i < gnLen; i++) {

					int qryIndex2 = hisDao.setQuery(qry);
					hisDao.setQryValue(qryIndex2, 1, "3");
					hisDao.setQryValue(qryIndex2, 2, "2");
					hisDao.setQryValue(qryIndex2, 3,
							vo.getThirdWardName()[i]);
					hisDao.setQryValue(qryIndex2, 4,
							vo.getThirdGroupName()[i]);
					hisDao.setQryValue(qryIndex2, 5, vo.getThirdTariffName()[i]);
					hisDao.setQryValue(qryIndex2, 6, "0");
					hisDao.setQryValue(qryIndex2, 7, "0");
					hisDao.setQryValue(qryIndex2, 8, vo.getStrSeatId());
					hisDao.setQryValue(qryIndex2, 9, "0");
					hisDao.setQryValue(qryIndex2, 10, vo.getStrHospitalCode());
					hisDao.execute(qryIndex2, 0);
				}
			}
			
			
			if (vo.getSecGroupName() != null) {
				gnLen = vo.getSecGroupName().length;

				for (int i = 0; i < gnLen; i++) {

					int qryIndex2 = hisDao.setQuery(qry);
					hisDao.setQryValue(qryIndex2, 1, "2");
					hisDao.setQryValue(qryIndex2, 2, "2");
					hisDao.setQryValue(qryIndex2, 3,
							vo.getSecWardName()[i]);
					hisDao.setQryValue(qryIndex2, 4,
							vo.getSecGroupName()[i]);
					hisDao.setQryValue(qryIndex2, 5, vo.getSecTariffName()[i]);
					hisDao.setQryValue(qryIndex2, 6, "0");
					hisDao.setQryValue(qryIndex2, 7, "0");
					hisDao.setQryValue(qryIndex2, 8, vo.getStrSeatId());
					hisDao.setQryValue(qryIndex2, 9, "0");
					hisDao.setQryValue(qryIndex2, 10, vo.getStrHospitalCode());
					hisDao.execute(qryIndex2, 0);
				}
			}
			
			
			 
			synchronized (hisDao) {
				hisDao.fire();
			}

			retVal = true;

		} catch (Exception e) {
		 
			retVal = false;
			throw new Exception("DAOBillSetup.ipdComplChargeUpdateQuery()-->"
					+ e.getMessage());
		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;

	}

	/**
	 * Returns Required Value from the table "HBLT_BILLING_PARAMETER_DTL"
	 * 
	 * type --> Value for the Field "HBLNUM_PARAM_TYPE" in the Table
	 * "HBLT_BILLING_PARAMETER_DTL" name --> Value for the Field
	 * "HBLSTR_PARAM_NAME" in the Table "HBLT_BILLING_PARAMETER_DTL"
	 */
	public static String getRetriveValue(String type, String name , String strHospitalCode)
			throws Exception {

		String qry = billing.qryHandler_billing.getQuery(1,"select.billsetup.3");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.getRetriveValue()");
		String res = null;

		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, type);
			hisDao.setQryValue(qryIndex, 2, name);
			hisDao.setQryValue(qryIndex, 3, strHospitalCode);
			WebRowSet wb = hisDao.executeQry(qryIndex);
			while (wb.next()) {
				res = wb.getString(1);
			}

		} catch (SQLException e) {

			throw new Exception("DAOBillSetup.getRetriveValue()-->"
					+ e.getMessage());

		} catch (Exception e) {

			throw new Exception("DAOBillSetup.getRetriveValue()-->"
					+ e.getMessage());
		}

		return res;
	}

	/**
	 * Returns Required Values from the table "HBLT_COMPULSORY_PARAMETER_DTL"
	 * 
	 * type --> Value for the Field "HBLNUM_COMP_TYPE" in the Table
	 * "HBLT_COMPULSORY_PARAMETER_DTL" chrgId --> Value for the Field
	 * "SBLNUM_CHARGETYPE_ID" in the Table "HBLT_COMPULSORY_PARAMETER_DTL" reqId
	 * --> will be one of the following value 1 --> Ward Ids 2 --> Group Ids 3
	 * --> Tariff Ids 4 --> Unit Ids
	 */
	public static String[] getMultiRowValue(String type, String chrgId,
			int reqId , String strHospitalCode) throws Exception {

		String qry = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.4");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.getMultiRowValue()");

		String[] resVal = null;
		int count = 0;
		try {

			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, type);
			hisDao.setQryValue(qryIndex, 2, chrgId);
			hisDao.setQryValue(qryIndex, 3, strHospitalCode);
			WebRowSet wb = hisDao.executeQry(qryIndex);

			resVal = new String[wb.size()];

			while (wb.next()) {

				resVal[count] = wb.getString(reqId);
				count++;
			}

		} catch (SQLException e) {
			throw new Exception("DAOBillSetup.getMultiRowValue()-->"
					+ e.getMessage());

		} catch (Exception e) {
			throw new Exception("DAOBillSetup.getMultiRowValue()-->"
					+ e.getMessage());

		}
		for(int i=0;i<count;i++)
		System.out.println("Result ["+i+"]::=="+resVal[i]);
		return resVal;
		
	}

	public static void deleteMultiRowValues(String type, String chrgId , String strHospitalCode)
			throws Exception {

		String qry = billing.qryHandler_billing.getQuery(1,
				"delete.billsetup.0");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.deleteMultiRowValues()");

		int qryIndex = hisDao.setQuery(qry);
		try {
			hisDao.setQryValue(qryIndex, 1, type);
			hisDao.setQryValue(qryIndex, 2, chrgId);
			hisDao.setQryValue(qryIndex, 3, strHospitalCode);
			hisDao.execute(qryIndex, 0);
			hisDao.fire();

		} catch (Exception e) {
			throw new Exception("DAOBillSetup.deleteMultiRowValues()-->"
					+ e.getMessage());
		}
	}
	
	
	public static boolean serviceTaxSetOff(BillSetupMstVO vo) {

		boolean retVal = false;
		String qry = billing.qryHandler_billing.getQuery(1,
				"update.billsetup.servicetax.0");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.genUpdateQuery()");

		try {
			
			int qryIndex = hisDao.setQuery(qry);
						
			hisDao.setQryValue(qryIndex, 1, vo.getStrHospitalCode());
			hisDao.setQryValue(qryIndex, 2, vo.getStrChargeTypeId());
			hisDao.execute(qryIndex, 0);
		

		synchronized (hisDao) {
			hisDao.fire();
		}
			retVal = true;

		} catch (Exception e) {
			
		 
			retVal = false;
			new HisException("Billing","DAOBillSetup.serviceTaxSetOff()-->" ,
					e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}

	public static boolean serviceTaxSetOn(BillSetupMstVO vo) {

		boolean retVal = false;
		String qry = billing.qryHandler_billing.getQuery(1,
				"update.billsetup.servicetax.1");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"DAOBillSetup.genUpdateQuery()");

		try {
			
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, vo.getStrHospitalCode());
			hisDao.setQryValue(qryIndex, 2, vo.getStrChargeTypeId());
			hisDao.execute(qryIndex, 0);
			

			synchronized (hisDao) {
				hisDao.fire();
			}

			retVal = true;

		} catch (Exception e) {
			
		 
			
			retVal = false;
			new HisException("Billing","DAOBillSetup.serviceTaxSetOn()-->" ,
					e.getMessage());
		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}
	
	public boolean opdInsert(BillSetupMstVO vo) throws Exception {
		
		boolean retVal=false;
		String qryUpdate = billing.qryHandler_billing.getQuery(1,"update.billsetup.1");
		HisDAO hisDao = new HisDAO("Bill Setup","BillSetupMstDAO.opdInsert()");
		
		String[] arrParamName = {"OPD_THIRD_PARY_BILLING","DISCOUNT",
				"OPD_DISCOUNT_CLERK","REFUND_PENALTY",
				"OPD_ROUND_OFF","OPD_SERVICE_TAX",
				 "OPD_FREE_CATEGORY"};
		
		String[] arrParamValue = {vo.getOpdThirdPartyBilling(),vo.getOpdDiscount(),
				vo.getOpdDiscountClerk(),vo.getOpdRefundPenalty(),
				vo.getOpdRoundOff(),vo.getOpdServiceTax(),
				vo.getOpdFreeCategory()};
		
		try {
			
			for(int i=0;i<arrParamName.length;i++)
			{
				int qryIndex1 = hisDao.setQuery(qryUpdate);
				hisDao.setQryValue(qryIndex1, 1, arrParamValue[i]);
				hisDao.setQryValue(qryIndex1, 2, vo.getStrSeatId());
				hisDao.setQryValue(qryIndex1, 3, "2");
				hisDao.setQryValue(qryIndex1, 4, arrParamName[i]);								
				hisDao.setQryValue(qryIndex1, 5, vo.getStrHospitalCode());
				hisDao.execute(qryIndex1, 0);
			}
			
			synchronized (hisDao) {
				hisDao.fire();
			}
			
			
			
			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("BillSetupMstDAO.opdUpdateQuery()-->"
					+ e.getMessage()+ "=="+e.toString());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
		
		
		
		
		
	}

	public static WebRowSet getConsumableChargesTariffCombo(BillSetupMstVO vo) throws Exception{
		
		String strQry = billing.qryHandler_billing.getQuery(1,"select.billsetup.consumableChargesTariff.1");
		HisDAO hisDao = new HisDAO("Bill Setup","DAOBillSetup.getConsumableChargesTariffCombo()");
		strQry+=" and "+billing.qryHandler_billing.getQuery(1,"select.billsetup.consumableChargesTariff.2");
		WebRowSet wb;
		try {
			int qryIndex = hisDao.setQuery(strQry);
			hisDao.setQryValue(qryIndex, 1, vo.getStrHospitalCode());
			hisDao.setQryValue(qryIndex, 2, vo.getStrConsumableChargesGroupId());
			wb = hisDao.executeQry(qryIndex);
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("DAOBillSetup.getConsumableChargesTariffCombo()-->"
					+ e.getMessage());
		
		}
		
		return wb;
	}
		
	
}
