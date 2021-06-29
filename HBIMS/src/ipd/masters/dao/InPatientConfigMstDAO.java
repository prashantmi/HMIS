package ipd.masters.dao;

import ipd.masters.vo.InPatientConfigMstVO;

import javax.sql.rowset.WebRowSet;

import billing.masters.vo.BillSetupMstVO;

import hisglobal.transactionmgnt.HisDAO;

public class InPatientConfigMstDAO {

	
public static boolean generalUpdateQuery(InPatientConfigMstVO inPatientConfigMstVO_p) throws Exception {

		boolean retVal = false;
		String strProcName1 ="{call pkg_ipd_dml.Proc_In_Patient_Config(?,?,?,?,?,?,?::numeric)}";
		int nProcIndex1 = 0;
		HisDAO hisDao = new HisDAO("In-patient Config Master","InPatientConfigMstDAO.generalUpdateQueryProc()");
		String[] arrParamName = { 	"BELONGING_REQUIRED",
									"BLOCKED_BED_EXPIRY_TIME",
									"DAYS_PURGE_ADM_DTL",
									
									"DIAGNOSIS_TYPE",
									"GOV_EMP_BASIC_PAY_LIMIT",
									"ICU_WARD_TYPE",
							
									"ISSUE_ITEM_REQUIRED",
									"MAX_BABIES_MOTHER_CAN_GIVE_BORN",
									"MIN_AGE_TO_BE_A_MOTHER",
									
									"MS_APPROVAL_FORM_NO",
									"NEW_BORN_BABY_PROCESS",
									"NURSE_CHECK_LIST_MANDATORY",
							
									"PATIENT_CATEGORY_STAFF",
									"PRIVATE_WARD_TYPE",
									"PRIV_EMPL_MONTHLY_INCOME_LIMIT",
									"PURGE_TIME_NOT_REPORTED_PAT",
									"BED_LIMIT",
									"LEAVE_REQ_TYPE",
									"DISCHARGE SLIP REQUIRED",
									"NEWBORN BABY DEFAULT DEPT",
									"NOT_REPORTED_TIME_LIMIT"};
		
		String[] arrParamValue = {  inPatientConfigMstVO_p.getStrBelongingRequired(),  
									inPatientConfigMstVO_p.getStrBlockedExpiryTime(),
									inPatientConfigMstVO_p.getStrPurgingRecordCurrentlyAdmissionDetails(),
									
									inPatientConfigMstVO_p.getStrDiagType(),
									inPatientConfigMstVO_p.getStrGovtEmpPayLimit(), 
									inPatientConfigMstVO_p.getStrIcuWardType(), 
									
									inPatientConfigMstVO_p.getStrIssueItemRequired(),
									inPatientConfigMstVO_p.getStrMaxNoOfBabyMotherCanBorn(), 
									inPatientConfigMstVO_p.getStrMinAgeToBeMother(),
									
									inPatientConfigMstVO_p.getStrMsApprovalOffline(),
									inPatientConfigMstVO_p.getStrNewBornBabyProcessType(),
									inPatientConfigMstVO_p.getStrNurseChecklistMandatory(),
									
									inPatientConfigMstVO_p.getStrPatientCategory(),
									inPatientConfigMstVO_p.getStrPrivateWardType(),
									inPatientConfigMstVO_p.getStrPvtEmpIncomeLimit(),
									inPatientConfigMstVO_p.getStrPurgeTimeNotReportedPatient(),
									inPatientConfigMstVO_p.getStrBedLimit(),
									inPatientConfigMstVO_p.getStrLeaveReqType(),
									inPatientConfigMstVO_p.getStrDischargeSlipReq(),
									inPatientConfigMstVO_p.getStrBabyDept(),
									inPatientConfigMstVO_p.getStrNotReportedTimeLimit()
								};
		

		try {
			
			
			for(int i=0;i<arrParamName.length;i++)
			{
				nProcIndex1 = hisDao.setProcedure(strProcName1);
				
				hisDao.setProcInValue(nProcIndex1, "paraType", "1",1);
				hisDao.setProcInValue(nProcIndex1, "paraName", arrParamName[i],2);
				hisDao.setProcInValue(nProcIndex1, "paraValue", arrParamValue[i],3);
				hisDao.setProcInValue(nProcIndex1, "gnum_seatid", inPatientConfigMstVO_p.getStrSeatId(),4);
				hisDao.setProcInValue(nProcIndex1, "gnum_hospital_code", inPatientConfigMstVO_p.getStrHospCode(),5);
				hisDao.setProcOutValue(nProcIndex1, "err",1,6);
				hisDao.setProcOutValue(nProcIndex1, "dml_count",1,7);
				hisDao.execute(nProcIndex1,1);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("InPatientConfigMstDAO.generalUpdateQueryProc()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}
	

	public static boolean admissionUpdateQuery(InPatientConfigMstVO inPatientConfigMstVO_p) throws Exception {

		boolean retVal = false;
		String strProcName1 ="{call pkg_ipd_dml.Proc_In_Patient_Config(?,?,?,?,?,?,?::numeric)}";
		int nProcIndex1 = 0;
		HisDAO hisDao = new HisDAO("In-patient Config Master","InPatientConfigMstDAO.admissionUpdateQuery()");
		String[] arrParamName = { 	"ADMISSION_ADVICE_MODE",
									"ADMISSION_ADVICE_VALIDITY_POST",
									"ADMISSION_ADVICE_VALIDITY_PRE",
									"ADMISSION_MODIFICATION_VALIDITY",									
									"ADMISSION_PROCESS",
									//"ADM_CHARGE_TAKEN_AT_COUNTER",
									"ADM_REPRINT_CHARGE",
									"ADV_AMOUNT_TAKEN_DURING_NEW_BABY",									
									"BED_ALLOTMENT_AT_TIME_OF_ADM",
									"NEW_BORN_BABY_ADM_CHARGE",
									"NEW_BORN_BABY_REG_CHARGE",
									"WHETHER_ROOM_NO_REQ",
									"WHETHER_UNIT_NAME_REQ"
								};
		
		String[] arrParamValue = {  inPatientConfigMstVO_p.getStrAdmissionAdviceMode(),  
									inPatientConfigMstVO_p.getStrAdmissionAdviceValidityFrom(),
									inPatientConfigMstVO_p.getStrAdmissionAdviceValidityTo(),
									inPatientConfigMstVO_p.getStrModificationTimeValidity(),									
									inPatientConfigMstVO_p.getStrAdmissionOnline(),
									//inPatientConfigMstVO_p.getStrAdmissionChargeTakenAtCounter(),
									inPatientConfigMstVO_p.getStrAdmissionReprintCharge(),
									inPatientConfigMstVO_p.getStrAdvanceAmountNewBornBaby(),									
									inPatientConfigMstVO_p.getStrBedAllotmentAtAdmission(),
									inPatientConfigMstVO_p.getStrNewBornBabyAdmissionCharge(),
									inPatientConfigMstVO_p.getStrNewBornBabyRegistrationCharge(),
									inPatientConfigMstVO_p.getStrRoomReq(),
									inPatientConfigMstVO_p.getStrUnitReq()
								};
		

		try 
		{			
			for(int i=0;i<arrParamName.length;i++)
			{
				nProcIndex1 = hisDao.setProcedure(strProcName1);
				
				hisDao.setProcInValue(nProcIndex1, "paraType", "2",1);
				hisDao.setProcInValue(nProcIndex1, "paraName", arrParamName[i],2);
				hisDao.setProcInValue(nProcIndex1, "paraValue", arrParamValue[i],3);
				hisDao.setProcInValue(nProcIndex1, "gnum_seatid", inPatientConfigMstVO_p.getStrSeatId(),4);
				hisDao.setProcInValue(nProcIndex1, "gnum_hospital_code", inPatientConfigMstVO_p.getStrHospCode(),5);
				hisDao.setProcOutValue(nProcIndex1, "err",1,6);
				hisDao.setProcOutValue(nProcIndex1, "dml_count",1,7);
				hisDao.execute(nProcIndex1,1);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;
		} 
		catch (Exception e) 
		{
			retVal = false;
			throw new Exception("InPatientConfigMstDAO.admissionUpdateQuery()-->"+ e.getMessage());
		} 
		finally 
		{
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}
	

	public static boolean dischargeUpdateQuery(InPatientConfigMstVO inPatientConfigMstVO_p) throws Exception
	{
		
		boolean retVal = false;
		String strProcName1 ="{call pkg_ipd_dml.Proc_In_Patient_Config(?,?,?,?,?,?,?::numeric)}";
		int nProcIndex1 = 0;
		
		HisDAO hisDao = new HisDAO("In-patient Config Master","InPatientConfigMstDAO.dischargeUpdateQuery()");
		String[] arrParamName = { 	"DISCHARGE_CANCELLATION_TIME",
									"DISCHARGE_PROCESS",
									"DISCHARGE_REPRINT_CHARGE",
									
									"DISCHARGE_TYPE_ABSCONDED",
									"DISCHARGE_TYPE_DEATH",
									"DISCHARGE_TYPE_LAMA",
									
									"DISCHARGE_TYPE_REFERRAL",
									"DISCHARGE_TYPE_TRANSFER",
									"PERMISSIBLE_DISCHARGE_TIME_NOT_REPORTED_PAT",
									"DISCHARGE_TYPE_NORMAL"
									};
		
		String[] arrParamValue = {  inPatientConfigMstVO_p.getStrDischargeCancellationTime(),  
									inPatientConfigMstVO_p.getStrDischargeProcessType(),
									inPatientConfigMstVO_p.getStrDischargeReprintCharge(),
									
									inPatientConfigMstVO_p.getStrDischargeTypeAbsconded(),
									inPatientConfigMstVO_p.getStrDischargeTypeDeath(),
									inPatientConfigMstVO_p.getStrDischargeTypeLAMA(),
									
									inPatientConfigMstVO_p.getStrDischargeTypeReferral(),
									inPatientConfigMstVO_p.getStrDischargeTypeTransfer(),
									inPatientConfigMstVO_p.getStrNotReportedLeavehour(),
									inPatientConfigMstVO_p.getStrNormalDischargeType()
								};
		

		try {
			
			
			for(int i=0;i<arrParamName.length;i++)
			{
				nProcIndex1 = hisDao.setProcedure(strProcName1);
				
				hisDao.setProcInValue(nProcIndex1, "paraType", "3",1);
				hisDao.setProcInValue(nProcIndex1, "paraName", arrParamName[i],2);
				hisDao.setProcInValue(nProcIndex1, "paraValue", arrParamValue[i],3);
				hisDao.setProcInValue(nProcIndex1, "gnum_seatid", inPatientConfigMstVO_p.getStrSeatId(),4);
				hisDao.setProcInValue(nProcIndex1, "gnum_hospital_code", inPatientConfigMstVO_p.getStrHospCode(),5);
				hisDao.setProcOutValue(nProcIndex1, "err",1,6);
				hisDao.setProcOutValue(nProcIndex1, "dml_count",1,7);
				hisDao.execute(nProcIndex1,1);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("InPatientConfigMstDAO.dischargeUpdateQuery()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
		
	}

	public static boolean passUpdateQuery(InPatientConfigMstVO inPatientConfigMstVO_p) throws Exception 
	{
		boolean retVal = false;
		String strProcName1 ="{call pkg_ipd_dml.Proc_In_Patient_Config(?,?,?,?,?,?,?::numeric)}";
		int nProcIndex1 = 0;
		String strSummerMorning="",strSummerEvening="",strWinterMorning="",strWinterEvening="";
		HisDAO hisDao = new HisDAO("In-patient Config Master","InPatientConfigMstDAO.passUpdateQuery()");
		
		strSummerMorning	=	inPatientConfigMstVO_p.getStrSummerMorningFromHr()+":"+
								inPatientConfigMstVO_p.getStrSummerMorningFromMin()+"-"+
								inPatientConfigMstVO_p.getStrSummerMorningToHr()+":"+
								inPatientConfigMstVO_p.getStrSummerMorningToMin();
		strSummerEvening	=	inPatientConfigMstVO_p.getStrSummerEveningFromHr()+":"+
								inPatientConfigMstVO_p.getStrSummerEveningFromMin()+"-"+
								inPatientConfigMstVO_p.getStrSummerEveningToHr()+":"+
								inPatientConfigMstVO_p.getStrSummerEveningToMin();
		strWinterMorning	=	inPatientConfigMstVO_p.getStrWinterMorningFromHr()+":"+
								inPatientConfigMstVO_p.getStrWinterMorningFromMin()+"-"+
								inPatientConfigMstVO_p.getStrWinterMorningToHr()+":"+
								inPatientConfigMstVO_p.getStrWinterMorningToMin();
		strWinterEvening	=	inPatientConfigMstVO_p.getStrWinterEveningFromHr()+":"+
								inPatientConfigMstVO_p.getStrWinterEveningFromMin()+"-"+
								inPatientConfigMstVO_p.getStrWinterEveningToHr()+":"+
								inPatientConfigMstVO_p.getStrWinterEveningToMin();
		
		
		String[] arrParamName = { 	"ATTENDANT_PASS_GEN_AT_ADM_TIME",
									"ATTENDANT_PASS_REQUIRED",
									"NEW_PASS_PAID_PASS_CHARGE",
				
									"NEW_PASS_VALIDITY_FOR_FREE_PASS",
									"NEW_PASS_VALIDITY_FOR_PAID_PASS",
									"NO_OF_PAID_PASS",
									
									"NO_OF_SLIP_PRINTED_AT_ADMISSION",
									"PASS_REPRINT_CHARGE",
									"PRINT_REQUEST",
									
									"RENEW_PASS_PAID_PASS_RENEW_CHARGE",
									"RENEW_PASS_VALIDITY_FOR_FREE_PASS",
									"RENEW_PASS_VALIDITY_FOR_PAID_PASS",
									
									"TIMINGS_SUMMER_MORNING",
									"TIMINGS_SUMMER_EVENING",
									"TIMINGS_WINTER_MORNING",
									"TIMINGS_WINTER_EVENING",
									"TOTAL_NO_OF_FREE_PASS",
									"TOTAL_NO_OF_FREE_PASS_AT_ADMISSION"};
		
		
		String[] arrParamValue = {  
									inPatientConfigMstVO_p.getStrAttendentPassGenerateAtAdmissionTime(),
									inPatientConfigMstVO_p.getStrAttendentPass(),
									inPatientConfigMstVO_p.getStrPaidPassCharge(),
									
									inPatientConfigMstVO_p.getStrNewFreePassValidity(),
									inPatientConfigMstVO_p.getStrNewPaidPassValidity(),
									inPatientConfigMstVO_p.getStrNoOfPaidPass(),
									
									inPatientConfigMstVO_p.getStrNoOfSlipPrintedAtAdmission(),
									inPatientConfigMstVO_p.getStrPassReprintCharges(),
									inPatientConfigMstVO_p.getStrPrintRequest(),
									
									inPatientConfigMstVO_p.getStrPaidPassRenewCharge(),
									inPatientConfigMstVO_p.getStrRenewFreePassValidity(),
									inPatientConfigMstVO_p.getStrRenewPaidPassValidity(),
									
									strSummerMorning,
									strSummerEvening,
									strWinterMorning,
									strWinterEvening,
									inPatientConfigMstVO_p.getStrNoOfFreePass(),
									inPatientConfigMstVO_p.getStrNoOfFreePassAdmisssionTime()								
								};		

		try 
		{
			
			
			for(int i=0;i<arrParamName.length;i++)
			{
				nProcIndex1 = hisDao.setProcedure(strProcName1);
				
				hisDao.setProcInValue(nProcIndex1, "paraType", "4",1);
				hisDao.setProcInValue(nProcIndex1, "paraName", arrParamName[i],2);
				hisDao.setProcInValue(nProcIndex1, "paraValue", arrParamValue[i],3);
				hisDao.setProcInValue(nProcIndex1, "gnum_seatid", inPatientConfigMstVO_p.getStrSeatId(),4);
				hisDao.setProcInValue(nProcIndex1, "gnum_hospital_code", inPatientConfigMstVO_p.getStrHospCode(),5);
				hisDao.setProcOutValue(nProcIndex1, "err",1,6);
				hisDao.setProcOutValue(nProcIndex1, "dml_count",1,7);
				hisDao.execute(nProcIndex1,1);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;

		} 
		catch (Exception e) 
		{
			retVal = false;
			throw new Exception("InPatientConfigMstDAO.passUpdateQuery()-->"+ e.getMessage());
		} 
		finally 
		{
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}

	public static boolean reportUpdateQuery(InPatientConfigMstVO inPatientConfigMstVO_p) throws Exception {

		boolean retVal = false;
		String strProcName1 ="{call pkg_ipd_dml.Proc_In_Patient_Config(?,?,?,?,?,?,?::numeric)}";
		int nProcIndex1 = 0;
		
		HisDAO hisDao = new HisDAO("In-patient Config Master","InPatientConfigMstDAO.reportUpdateQuery()");
		
		
		
		String[] arrParamName = { 	"DISCHARGE_SUMMARY_REPORT_ADVICE",
									"DISCHARGE_SUMMARY_REPORT_FOOTER",
								};
		
		
		String[] arrParamValue = {  
									inPatientConfigMstVO_p.getStrDischargeSummaryReportAdvice(),
									inPatientConfigMstVO_p.getStrDischargeSummaryReportFooter()																
								 };
		

		try {
			
			
			for(int i=0;i<arrParamName.length;i++)
			{
				nProcIndex1 = hisDao.setProcedure(strProcName1);
				
				hisDao.setProcInValue(nProcIndex1, "paraType", "5",1);
				hisDao.setProcInValue(nProcIndex1, "paraName", arrParamName[i],2);
				hisDao.setProcInValue(nProcIndex1, "paraValue", arrParamValue[i],3);
				hisDao.setProcInValue(nProcIndex1, "gnum_seatid", inPatientConfigMstVO_p.getStrSeatId(),4);
				hisDao.setProcInValue(nProcIndex1, "gnum_hospital_code", inPatientConfigMstVO_p.getStrHospCode(),5);
				hisDao.setProcOutValue(nProcIndex1, "err",1,6);
				hisDao.setProcOutValue(nProcIndex1, "dml_count",1,7);
				hisDao.execute(nProcIndex1,1);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("InPatientConfigMstDAO.reportUpdateQuery()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}

	public static boolean billUpdateQuery(InPatientConfigMstVO inPatientConfigMstVO_p) throws Exception {

		boolean retVal = false;
		String strProcName1 ="{call pkg_ipd_dml.Proc_In_Patient_Config(?,?,?,?,?,?,?::numeric)}";
		int nProcIndex1 = 0;
		HisDAO hisDao = new HisDAO("In-patient Config Master","InPatientConfigMstDAO.billUpdateQuery()");
		
		
		
			
		String[] arrParamName = { 	"ADVANCE_REQUEST_AT_ADM_ADVICE",
									"ADV_REFUND_REQ_ADM_CANCEL",
									"Integration_With_Billing",
									"WHETHER_CHECK_ADV_AMOUNT_AT_ADM",
									"ADM_CHARGE_AT_ADM_COUNTER"
								};
		
		
		String[] arrParamValue = {  
									inPatientConfigMstVO_p.getStrAdvanceRequestAdmissionAdvice(),
									inPatientConfigMstVO_p.getStrRefundRequestAdmissionCancellation(),
									inPatientConfigMstVO_p.getStrIntegrationBilling(),
									inPatientConfigMstVO_p.getStrAdvanceAmountAdmission(),
									inPatientConfigMstVO_p.getStrAdmissionCharge()
								 };
		

		try {
			
			
			for(int i=0;i<arrParamName.length;i++)
			{
				nProcIndex1 = hisDao.setProcedure(strProcName1);
				
				hisDao.setProcInValue(nProcIndex1, "paraType", "6",1);
				hisDao.setProcInValue(nProcIndex1, "paraName", arrParamName[i],2);
				hisDao.setProcInValue(nProcIndex1, "paraValue", arrParamValue[i],3);
				hisDao.setProcInValue(nProcIndex1, "gnum_seatid", inPatientConfigMstVO_p.getStrSeatId(),4);
				hisDao.setProcInValue(nProcIndex1, "gnum_hospital_code", inPatientConfigMstVO_p.getStrHospCode(),5);
				hisDao.setProcOutValue(nProcIndex1, "err",1,6);
				hisDao.setProcOutValue(nProcIndex1, "dml_count",1,7);
				hisDao.execute(nProcIndex1,1);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("InPatientConfigMstDAO.billUpdateQuery()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}
		
	public static boolean printUpdateQuery(InPatientConfigMstVO inPatientConfigMstVO_p) throws Exception {

		boolean retVal = false;
		String strProcName1 ="{call pkg_ipd_dml.Proc_In_Patient_Config(?,?,?,?,?,?,?::numeric)}";
		int nProcIndex1 = 0;
		HisDAO hisDao = new HisDAO("In-patient Config Master","InPatientConfigMstDAO.printUpdateQuery()");
		
		
		
			
		String[] arrParamName = { 	"NO_OF_LINES_IN_ADM_SLIP",
									"NO_OF_LINES_IN_NEW_BABY_ADM_SLIP",
									"NO_OF_LINES_IN_VISITOR_PASS_SLIP"								
								};
		
		
		String[] arrParamValue = {  
									inPatientConfigMstVO_p.getStrNoOfLineInAdmissionSlip(),
									inPatientConfigMstVO_p.getStrNoOfLineInNewBornBabyAdmissionSlip(),	
									inPatientConfigMstVO_p.getStrNoOfLineInVisitorPassSlip()	
								 };
		

		try {
			
			
			for(int i=0;i<arrParamName.length;i++)
			{
				nProcIndex1 = hisDao.setProcedure(strProcName1);
				
				hisDao.setProcInValue(nProcIndex1, "paraType", "7",1);
				hisDao.setProcInValue(nProcIndex1, "paraName", arrParamName[i],2);
				hisDao.setProcInValue(nProcIndex1, "paraValue", arrParamValue[i],3);
				hisDao.setProcInValue(nProcIndex1, "gnum_seatid", inPatientConfigMstVO_p.getStrSeatId(),4);
				hisDao.setProcInValue(nProcIndex1, "gnum_hospital_code", inPatientConfigMstVO_p.getStrHospCode(),5);
				hisDao.setProcOutValue(nProcIndex1, "err",1,6);
				hisDao.setProcOutValue(nProcIndex1, "dml_count",1,7);
				hisDao.execute(nProcIndex1,1);
			}
			synchronized (hisDao) 
			{
				hisDao.fire();
			}
			retVal = true;

		} catch (Exception e) {
			retVal = false;
			throw new Exception("InPatientConfigMstDAO.printUpdateQuery()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

		return retVal;
	}

	public static void saveBillConfigData(InPatientConfigMstVO vo)
			throws Exception {
		HisDAO dao = null;
		int nqryIndex;
		int nqryIndex1;
		String strquery = new String();
		String strquery1 = "";
		String parameterName[] = { "IPD_INTERFACE_BILL", "BED_CHARGE_FLAG" };
		WebRowSet wb = null;
		try {
			dao = new HisDAO("ipd", "DAOInPatientConfigMst");

			strquery = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.0");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospCode());
			wb = dao.executeQry(nqryIndex);
			if (wb.size() == 0) {
				strquery = ipd.qryHandler_ipd.getQuery(1, "insert.ipdconfig.0");
				for (int i = 0; i < 2; i++) {

					nqryIndex = dao.setQuery(strquery);

					dao.setQryValue(nqryIndex, 1, parameterName[i]);
					if (parameterName[i].equals("IPD_INTERFACE_BILL")) {
						dao.setQryValue(nqryIndex, 2, vo
								.getStrIntegrationBilling());
					} else {
						dao.setQryValue(nqryIndex, 2, vo.getStrBedChange());
					}
					dao.setQryValue(nqryIndex, 3, vo.getStrSeatId());
					dao.setQryValue(nqryIndex, 4, vo.getStrHospCode());
					dao.execute(nqryIndex, 0);

				}
				synchronized (dao) {
					dao.fire();

				}
			} else if (wb.size() == 1) {
				strquery = ipd.qryHandler_ipd.getQuery(1, "insert.ipdconfig.0");
				strquery1 = ipd.qryHandler_ipd
						.getQuery(1, "update.ipdconfig.0");
				if (wb.next()) {
					nqryIndex = dao.setQuery(strquery);
					nqryIndex1 = dao.setQuery(strquery1);
					if (wb.getString(1).equals("IPD_INTERFACE_BILL")) {
						dao.setQryValue(nqryIndex1, 1, vo
								.getStrIntegrationBilling());
						dao.setQryValue(nqryIndex1, 2, "IPD_INTERFACE_BILL");
						dao.execute(nqryIndex1, 0);

						dao.setQryValue(nqryIndex, 1, parameterName[1]);
						dao.setQryValue(nqryIndex, 2, vo.getStrBedChange());
						dao.setQryValue(nqryIndex, 3, vo.getStrSeatId());
						dao.setQryValue(nqryIndex, 4, vo.getStrHospCode());
						dao.execute(nqryIndex, 0);
					} else {
						dao.setQryValue(nqryIndex1, 1, vo.getStrBedChange());
						dao.setQryValue(nqryIndex1, 2, "BED_CHARGE_FLAG");
						dao.execute(nqryIndex1, 0);

						dao.setQryValue(nqryIndex, 1, parameterName[0]);
						dao.setQryValue(nqryIndex, 2, vo
								.getStrIntegrationBilling());
						dao.setQryValue(nqryIndex, 3, vo.getStrSeatId());
						dao.setQryValue(nqryIndex, 4, vo.getStrHospCode());
						dao.execute(nqryIndex, 0);
					}

				}
				synchronized (dao) {
					dao.fire();
				}
			} else {
				strquery = ipd.qryHandler_ipd.getQuery(1, "update.ipdconfig.0");
				while (wb.next()) {

					nqryIndex = dao.setQuery(strquery);

					if (wb.getString(1).equals("IPD_INTERFACE_BILL")) {

						dao.setQryValue(nqryIndex, 1, vo
								.getStrIntegrationBilling());
						dao.setQryValue(nqryIndex, 2, "IPD_INTERFACE_BILL");
					} else {

						dao.setQryValue(nqryIndex, 1, vo.getStrBedChange());
						dao.setQryValue(nqryIndex, 2, "BED_CHARGE_FLAG");

					}
					dao.execute(nqryIndex, 0);

				}
				synchronized (dao) {
					dao.fire();

				}
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}

	}

	public static void saveData(InPatientConfigMstVO vo) throws Exception {
		HisDAO dao = null;
		int nqryIndex;
		int nqryIndex1;
		String strquery = new String();
		String strquery1 = "";
		String parameterName[] = { "ADMISSION_PURGED_DAY", "NOT_REPORTED_HOUR" };
		WebRowSet wb = null;
		try {
			dao = new HisDAO("ipd", "DAOInPatientConfigMst");

			strquery = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.1");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospCode());
			wb = dao.executeQry(nqryIndex);
			if (wb.size() == 0) {
				strquery = ipd.qryHandler_ipd.getQuery(1, "insert.ipdconfig.1");
				for (int i = 0; i < 2; i++) {

					nqryIndex = dao.setQuery(strquery);

					dao.setQryValue(nqryIndex, 1, parameterName[i].trim());
					if (parameterName[i].equals("ADMISSION_PURGED_DAY")) {
						dao
								.setQryValue(
										nqryIndex,
										2,
										vo
												.getStrPurgingRecordCurrentlyAdmissionDetails());
					} else {
						dao.setQryValue(nqryIndex, 2, vo
								.getStrPurgeTimeNotReportedPatient());
					}
					dao.setQryValue(nqryIndex, 3, vo.getStrSeatId());
					dao.setQryValue(nqryIndex, 4, vo.getStrHospCode());
					dao.execute(nqryIndex, 0);

				}
				synchronized (dao) {
					dao.fire();

				}
			} else if (wb.size() == 1) {
				strquery = ipd.qryHandler_ipd.getQuery(1, "insert.ipdconfig.1");
				strquery1 = ipd.qryHandler_ipd
						.getQuery(1, "update.ipdconfig.1");
				if (wb.next()) {
					nqryIndex = dao.setQuery(strquery);
					nqryIndex1 = dao.setQuery(strquery1);
					if (wb.getString(1).equals("ADMISSION_PURGED_DAY")) {
						dao
								.setQryValue(
										nqryIndex1,
										1,
										vo
												.getStrPurgingRecordCurrentlyAdmissionDetails());
						dao.setQryValue(nqryIndex1, 2, "ADMISSION_PURGED_DAY");
						dao.execute(nqryIndex1, 0);

						dao.setQryValue(nqryIndex, 1, parameterName[1].trim());
						dao.setQryValue(nqryIndex, 2, vo
								.getStrPurgeTimeNotReportedPatient());
						dao.setQryValue(nqryIndex, 3, vo.getStrSeatId());
						dao.setQryValue(nqryIndex, 4, vo.getStrHospCode());
						dao.execute(nqryIndex, 0);
					} else {
						dao.setQryValue(nqryIndex1, 1, vo
								.getStrPurgeTimeNotReportedPatient());
						dao.setQryValue(nqryIndex1, 2, "NOT_REPORTED_HOUR");
						dao.execute(nqryIndex1, 0);

						dao.setQryValue(nqryIndex, 1, parameterName[0].trim());
						dao
								.setQryValue(
										nqryIndex,
										2,
										vo
												.getStrPurgingRecordCurrentlyAdmissionDetails());
						dao.setQryValue(nqryIndex, 3, vo.getStrSeatId());
						dao.setQryValue(nqryIndex, 4, vo.getStrHospCode());
						dao.execute(nqryIndex, 0);
					}

				}
				synchronized (dao) {
					dao.fire();
				}
			} else {
				strquery = "";
				nqryIndex = 0;

				while (wb.next()) {

					strquery = ipd.qryHandler_ipd.getQuery(1,
							"update.ipdconfig.1");
					nqryIndex = dao.setQuery(strquery);
					if (wb.getString(1).equals("ADMISSION_PURGED_DAY")) {
						// System.out.println("PurgingAdmission"+vo.getStrPurgingRecordCurrentlyAdmissionDetails());
						dao
								.setQryValue(
										nqryIndex,
										1,
										vo
												.getStrPurgingRecordCurrentlyAdmissionDetails());
						dao.setQryValue(nqryIndex, 2, "ADMISSION_PURGED_DAY");

					} else {
						// System.out.println("PurgingAdmission
						// n"+vo.getStrPurgeTimeNotReportedPatient());
						dao.setQryValue(nqryIndex, 1, vo
								.getStrPurgeTimeNotReportedPatient());
						dao.setQryValue(nqryIndex, 2, "NOT_REPORTED_HOUR");

					}

					// dao.executeQry(nqryIndex);
					dao.execute(nqryIndex, 0);
					// dao.executeQry(nqryIndex);

				}
				synchronized (dao) {
					dao.fire();

				}
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}

	}

	public static void saveIpdConfigData(InPatientConfigMstVO _VOInPatientConfigMst)
			throws Exception {
		HisDAO mDao = null;
		int mnSelectQryIndex;
		int mnUpdateQryIndex;
		int mnInsertQryIndex;
		String mstrSelectQuery;
		String mstrUpdateQuery;
		String mstrInsertQuery;
		String[] mstrArrParameter = { "NOT_REPORTED_HOUR", "ADMISSION_PURGED_DAY",
				"NOT_REPORTED_LEAVE_HOUR", "NOT_REPORTED_OUTSIDE_HOUR" };
		String[] mstrArrParameterValue = new String[4];
		WebRowSet mWb = null;
		try {
			mDao = new HisDAO("ipd.DAOInPatientConfigMst", "saveIpdConfigData()");
			mstrSelectQuery = ipd.qryHandler_ipd.getQuery(1, "select.ipdcofigmst.1");
			mstrUpdateQuery = ipd.qryHandler_ipd.getQuery(1, "update.ipdcofigmst.1");
			mstrInsertQuery = ipd.qryHandler_ipd.getQuery(1, "insert.ipdcofigmst.1");
			mnSelectQryIndex = mDao.setQuery(mstrSelectQuery);
			mDao.setQryValue(mnSelectQryIndex, 1, _VOInPatientConfigMst.getStrHospCode());
			mWb = mDao.executeQry(mnSelectQryIndex);
			
			_VOInPatientConfigMst.setStrNotReportedOutsidehour("2");
			
			mstrArrParameterValue[0]=_VOInPatientConfigMst.getStrPurgeTimeNotReportedPatient();
			mstrArrParameterValue[1]=_VOInPatientConfigMst.getStrPurgingRecordCurrentlyAdmissionDetails();
			mstrArrParameterValue[2]=_VOInPatientConfigMst.getStrNotReportedLeavehour();
			mstrArrParameterValue[3]=_VOInPatientConfigMst.getStrNotReportedOutsidehour();
			
			if (mWb != null) {
				while (mWb.next()) {
					for (int nTmpI = 0; nTmpI < mstrArrParameter.length; nTmpI++) {
						if(mWb.getString(1).equals(mstrArrParameter[nTmpI])){
							mnUpdateQryIndex = mDao.setQuery(mstrUpdateQuery);
							mDao.setQryValue(mnUpdateQryIndex, 1, mstrArrParameterValue[nTmpI]);
							mDao.setQryValue(mnUpdateQryIndex, 2, _VOInPatientConfigMst.getStrSeatId());
							mDao.setQryValue(mnUpdateQryIndex, 3, _VOInPatientConfigMst.getStrHospCode());
							mDao.setQryValue(mnUpdateQryIndex, 4, mstrArrParameter[nTmpI]);
							mDao.execute(mnUpdateQryIndex, 0);
							mstrArrParameter[nTmpI]=null;
						}
					}
				}
				synchronized (mDao) {
					mDao.fire();
				}
			}
			
			if (mWb == null || mWb.size()<=0) {
				for (int nTmpI = 0; nTmpI < mstrArrParameter.length; nTmpI++) {
				if(mstrArrParameter[nTmpI]!=null){
					mnInsertQryIndex = mDao.setQuery(mstrInsertQuery);
					mDao.setQryValue(mnInsertQryIndex, 1, mstrArrParameter[nTmpI]);
					mDao.setQryValue(mnInsertQryIndex, 2, mstrArrParameterValue[nTmpI]);
					mDao.setQryValue(mnInsertQryIndex, 3, _VOInPatientConfigMst.getStrSeatId());
					mDao.setQryValue(mnInsertQryIndex, 4, _VOInPatientConfigMst.getStrHospCode());
					mDao.execute(mnInsertQryIndex, 0);
					mstrArrParameter[nTmpI]=null;
				}
			}
				synchronized (mDao) {
					mDao.fire();
				}
			}
			
		} catch (Exception _Err) {
			throw new Exception(_Err.getMessage());
		} finally {
			mDao.free();
			mDao = null;
		}
	}

	
}
