package ipd.masters.bo;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;
import ipd.IpdConfigUtil;
import ipd.masters.dao.InPatientConfigMstDAO;
import ipd.masters.vo.InPatientConfigMstVO;
import ipd.setup.GenWardApprovalType;
import ipd.setup.IPDBillDetails;
import ipd.setup.IPDConfig;
import ipd.setup.IPDGeneralConfigType;
import ipd.setup.IPDPassDetails;
import ipd.setup.IPDPrintConfigType;
import ipd.setup.IPDReportDetails;
import ipd.setup.PrivateWardApprovalType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.sql.rowset.WebRowSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import billing.masters.vo.BillSetupMstVO;

public class InPatientConfigMstBO {

	// private static final String pathFileName = "hisglobal.hisconfig.hisPath";
	private String path = null;

	/**
	 * method used to return the path of the IPD_CONFIG file from resource
	 * bundle
	 * 
	 * @return - path of the IPD_CONFIG file in String
	 */
	public String getPath() {
		path = HisUtil.getParameterFromHisPathXML("IPD_CONFIG");
		return path;
	}

	/**
	 * method used to retrieve values from the XML file and set them in formBean
	 * to display in the jsp page
	 * 
	 * @param formBean -
	 *            formBean Object
	 */
	public void displayValues(InPatientConfigMstVO formBean) {

		JAXBElement<IPDConfig> jaxB = null;

		IPDConfig ipdC = null;

		IPDGeneralConfigType ipdCType = null;
		
		GenWardApprovalType gwat = null;
		PrivateWardApprovalType pwat = null;
		
		try{
			jaxB = this.readXMLDataObject();

			ipdC = (IPDConfig) jaxB.getValue();

			ipdCType = (IPDGeneralConfigType) ipdC
				.getIpdGeneralConfig();

		gwat = (GenWardApprovalType) ipdCType
				.getGenWardApproval();
		
		pwat = (PrivateWardApprovalType) ipdCType
				.getPrivateWardApproval();
		String genWard[] = null;
		String priWard[] = null;

		if (gwat.getAuthorityId() != null) {

			genWard = new String[gwat.getAuthorityId().size()];

			for (int i = 0; i < gwat.getAuthorityId().size(); i++) {

				genWard[i] = gwat.getAuthorityId().get(i);

			}

		}

		if (pwat.getAuthorityId() != null) {

			priWard = new String[pwat.getAuthorityId().size()];

			for (int i = 0; i < pwat.getAuthorityId().size(); i++) {

				priWard[i] = pwat.getAuthorityId().get(i);

			}

		}

		formBean.setStrGenWardAdmission(ipdCType.getGenWardAdmission());
		formBean.setStrGenWardApprover(genWard);
		formBean.setStrPrivateWardAdmission(ipdCType.getPrivateWardAdmission());
		formBean.setStrPrivateWardApprover(priWard);
		formBean.setStrBookBed(ipdCType.getBookBed());
		formBean.setStrBilling(ipdCType.getBilling());
		formBean.setStrNegativeBilling(ipdCType.getNegativeBilling());
		formBean.setStrPatientCategory(ipdCType.getPatientCategory());
		formBean.setStrGovtEmpPayLimit(ipdCType.getGovtEmpBasicPayLimit());
		formBean.setStrPvtEmpIncomeLimit(ipdCType.getPvtEmpMonIncomeLimit());
		//formBean.setStrAdmissionChargeTakenAtCounter(ipdCType.getAdmissionChargeTakenAtAdmissionCounter());
		formBean.setStrAdmissionAdviceValidityTo(ipdCType
				.getAdmissionAdviceValidityTo());
		formBean.setStrAdmissionAdviceValidityFrom(ipdCType
				.getAdmissionAdviceValidityFrom());
		formBean.setStrNewBornBabyAdmissionCharge(ipdCType
				.getNewBornBabyAdmissionCharge());
		formBean.setStrNewBornBabyRegistrationCharge(ipdCType
				.getNewBornBabyRegistrationCharge());
		formBean.setStrDischargeCancellationTime(ipdCType
				.getDischargeCancellationTime());
		formBean.setStrModificationTimeValidity(ipdCType.getModificationTime());
		formBean.setStrAdvanceAmountNewBornBaby(ipdCType
				.getAdvanceAmountTakenFromNewBornBaby());
		formBean.setStrBlockedExpiryTime(ipdCType.getBlockBedExpiryTime());
		formBean.setStrAdmissionReprintCharge(ipdCType
				.getAdmissionReprintCharge());
		formBean.setStrAdmissionReprintCharge(ipdCType
				.getAdmissionReprintCharge());
		formBean.setStrDischargeReprintCharge(ipdCType
				.getDischargeReprintCharge());
		formBean.setStrNoOfLineInAdmissionSlip(ipdCType
				.getNoOfLineAmissionSlip());
		formBean.setStrNoOfLineInVisitorPassSlip(ipdCType
				.getNoOfLineVisitorPassSlip());
		formBean.setStrAdmissionOnline(ipdCType.getAdmissionProcessOnline());
		formBean
				.setStrMsApprovalOffline(ipdCType.getMsApprovalFormNoRequired());
		formBean.setStrPrivateWardType(ipdCType.getPrivateWardType());
		formBean.setStrIcuWardType(ipdCType.getIcuWardType());
		formBean.setStrPurgingRecordCurrentlyAdmissionDetails(ipdCType
				.getDaysForPurgingRecordFromCurrentlyAdmissionDetails());
		formBean.setStrPurgeTimeNotReportedPatient(ipdCType
				.getPurgeTimeForNotReported());
		formBean.setStrDischargeProcessType(ipdCType.getDischargeProcessType());
		formBean.setStrNewBornBabyProcessType(ipdCType.getNewBornBabyProcess());
		formBean.setStrMinAgeToBeMother(ipdCType.getMinAgeToBeMother());
		formBean.setStrMaxNoOfBabyMotherCanBorn(ipdCType
				.getMaxNoOfBabyMotherCanBorn());
		formBean.setStrNotReportedLeavehour(ipdCType
				.getNotReportedLeaveHours());
		formBean.setStrDiagType(ipdCType
				.getDiagnosisType());
		formBean.setStrAdmissionAdviceMode(ipdCType.getAdmissionAdviceMode());
		formBean.setStrBelongingRequired(ipdCType.getBelongingRequired());
		formBean.setStrIssueItemRequired(ipdCType.getIssueItemRequired());
		formBean.setStrNurseChecklistMandatory(ipdCType.getNurseChecklistMandatory());
		formBean.setStrUnitReq(ipdCType.getUnitNameReq());
		formBean.setStrRoomReq(ipdCType.getRoomNoReq());
		if(ipdCType.getNotReportedTimeLimit().equals("") || ipdCType.getNotReportedTimeLimit()==null)
			formBean.setStrNotReportedTimeLimit("10");
		else
			formBean.setStrNotReportedTimeLimit(ipdCType.getNotReportedTimeLimit());
		
		formBean.setStrBedLimit(ipdCType.getBedLimit());
		/*
		 * formBean.setStrIntegrationBilling(ipdBill.getIntegrationWithBilling());
		 * formBean.setStrAdvanceAmountAdmission(ipdBill.getAdvanceamountAdmission());
		 * formBean.setStrAdvanceRequestAdmissionAdvice(ipdBill.getAdvanceRequestAdmissionAdvice());
		 * formBean.setStrPatientAdjustedFinalDischargeBill(ipdBill.getPatientAdjustedFinalBillAtAdmission());
		 * formBean.setStrRefundRequestAdmissionCancellation(ipdBill.getAdvanceRefundRequestAtAdmissionCancellation());
		 * formBean.setStrBedChange(ipdBill.getBedChange());
		 */
		
		}catch(Exception e  ){
			
			e.printStackTrace();
		}
		
		pwat = null;
		gwat = null;
		jaxB = null;
	}

	
	/**
	 * @param inPatientConfigMstVO_p
	 */
	public void setGenPropValues(InPatientConfigMstVO inPatientConfigMstVO_p) {

		
		
		
		//InPatientConfigMstDAO.setGenPropValues(vo);
		
		
		
		WebRowSet wb=null;
		String tmp_ParamName="";
		String tmp_ParamValue="";
		String wardTypeVal="";
		
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.50");
		HisDAO hisDao = new HisDAO("InPatient Config Master","InPatientConfigMstBO.selectQuery()");
		
		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "1");
			hisDao.setQryValue(qryIndex, 2, inPatientConfigMstVO_p.getStrHospCode());
			wb=hisDao.executeQry(qryIndex);
			
			
			while(wb.next())
			{
				tmp_ParamName=wb.getString(1);
				tmp_ParamValue=wb.getString(2);
				if(tmp_ParamName.equals("BELONGING_REQUIRED"))
					inPatientConfigMstVO_p.setStrBelongingRequired(tmp_ParamValue);
				else if(tmp_ParamName.equals("BLOCKED_BED_EXPIRY_TIME"))
					inPatientConfigMstVO_p.setStrBlockedExpiryTime(tmp_ParamValue);
				else if(tmp_ParamName.equals("DAYS_PURGE_ADM_DTL"))
					inPatientConfigMstVO_p.setStrPurgingRecordCurrentlyAdmissionDetails(tmp_ParamValue);
				else if(tmp_ParamName.equals("DIAGNOSIS_TYPE"))
					inPatientConfigMstVO_p.setStrDiagType(tmp_ParamValue);
				else if(tmp_ParamName.equals("GOV_EMP_BASIC_PAY_LIMIT"))
					inPatientConfigMstVO_p.setStrGovtEmpPayLimit(tmp_ParamValue);
				else if(tmp_ParamName.equals("ICU_WARD_TYPE"))
					inPatientConfigMstVO_p.setStrIcuWardType(tmp_ParamValue);
				else if(tmp_ParamName.equals("ISSUE_ITEM_REQUIRED"))
					inPatientConfigMstVO_p.setStrIssueItemRequired(tmp_ParamValue);
				else if(tmp_ParamName.equals("MAX_BABIES_MOTHER_CAN_GIVE_BORN"))
					inPatientConfigMstVO_p.setStrMaxNoOfBabyMotherCanBorn(tmp_ParamValue);
				else if(tmp_ParamName.equals("MIN_AGE_TO_BE_A_MOTHER"))
					inPatientConfigMstVO_p.setStrMinAgeToBeMother(tmp_ParamValue);
				else if(tmp_ParamName.equals("MS_APPROVAL_FORM_NO"))
					inPatientConfigMstVO_p.setStrMsApprovalOffline(tmp_ParamValue);
				else if(tmp_ParamName.equals("NEW_BORN_BABY_PROCESS"))
					inPatientConfigMstVO_p.setStrNewBornBabyProcessType(tmp_ParamValue);
				else if(tmp_ParamName.equals("NURSE_CHECK_LIST_MANDATORY"))
					inPatientConfigMstVO_p.setStrNurseChecklistMandatory(tmp_ParamValue);
				else if(tmp_ParamName.equals("PATIENT_CATEGORY_STAFF"))
					inPatientConfigMstVO_p.setStrPatientCategory(tmp_ParamValue);
				else if(tmp_ParamName.equals("PRIV_EMPL_MONTHLY_INCOME_LIMIT"))
					inPatientConfigMstVO_p.setStrPvtEmpIncomeLimit(tmp_ParamValue);
				else if(tmp_ParamName.equals("PURGE_TIME_NOT_REPORTED_PAT"))
					inPatientConfigMstVO_p.setStrPurgeTimeNotReportedPatient(tmp_ParamValue);
				else if(tmp_ParamName.equals("PRIVATE_WARD_TYPE"))
					inPatientConfigMstVO_p.setStrPrivateWardType(tmp_ParamValue);
				else if(tmp_ParamName.equals("BED_LIMIT"))
					inPatientConfigMstVO_p.setStrBedLimit(tmp_ParamValue);
				else if(tmp_ParamName.equals("LEAVE_REQ_TYPE"))
					inPatientConfigMstVO_p.setStrLeaveReqType(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE SLIP REQUIRED"))
					inPatientConfigMstVO_p.setStrDischargeSlipReq(tmp_ParamValue);
				else if(tmp_ParamName.equals("NEWBORN BABY DEFAULT DEPT"))
					inPatientConfigMstVO_p.setStrBabyDept(tmp_ParamValue);
				else if(tmp_ParamName.equals("NOT_REPORTED_TIME_LIMIT"))
					inPatientConfigMstVO_p.setStrNotReportedTimeLimit(tmp_ParamValue);
			}
			/*HashMap<String, String> mapProcedureParam = new HashMap<String, String>();
			
			mapProcedureParam.clear();
			
			mapProcedureParam.put("hosp_code", inPatientConfigMstVO_p.getStrHospCode());
			mapProcedureParam.put("modeval", "1");
			mapProcedureParam.put("seatid", "0");
			mapProcedureParam.put("crNo", "0");
			mapProcedureParam.put("err", "#1");
			mapProcedureParam.put("resultset", "#2");
			
			
			//Charge Type Combo
			inPatientConfigMstVO_p.setStrChargeTypeValueInt(HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_sblt_chargetype_mst", mapProcedureParam, vo.getStrChargeTypeInt(), "0^Select Value", false));
			inPatientConfigMstVO_p.setStrChargeTypeValueExt(HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_sblt_chargetype_mst", mapProcedureParam,vo.getStrChargeTypeExt(), "0^Select Value", false));
			
			//Ward Type combo
			HisUtil hisUtil = new HisUtil("Bill Setup","BillSetupMstBO.setGenPropValues");
			
			String qryWard = billing.qryHandler_billing.getQuery(1,"select.advanceMst.1").replace("?",inPatientConfigMstVO_p.getStrHospitalCode());
			wardTypeVal = hisUtil.getOptionValue(qryWard, inPatientConfigMstVO_p.getStrWardTypeInt(), "0^Select Value");
			
			inPatientConfigMstVO_p.setStrWardTypeValueInt(wardTypeVal);
			wardTypeVal = hisUtil.getOptionValue(qryWard, inPatientConfigMstVO_p.getStrWardTypeExt(), "0^Select Value");
			
			inPatientConfigMstVO_p.setStrWardTypeValueExt(wardTypeVal);*/
			
			
		}
			catch (Exception e) {
				
				e.printStackTrace();
				
				inPatientConfigMstVO_p.setStrMsg("Error while retrieving Record");
				new HisException("InPatientConfigMstBO", "InPatientConfigMstBO.setGenPropValues()", e.getMessage());
			}
		
		
		
		
	}
	
	
	/**
	 * @param inPatientConfigMstVO_p
	 */
	public void displayIpdAdmDtls(InPatientConfigMstVO inPatientConfigMstVO_p) {

		
		
		
		//InPatientConfigMstDAO.setGenPropValues(vo);
		
		
		
		WebRowSet wb=null;
		String tmp_ParamName="";
		String tmp_ParamValue="";
		String wardTypeVal="";
		
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.50");
		HisDAO hisDao = new HisDAO("InPatient Config Master","InPatientConfigMstBO.selectQuery()");
		
		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "2");
			hisDao.setQryValue(qryIndex, 2, inPatientConfigMstVO_p.getStrHospCode());
			wb=hisDao.executeQry(qryIndex);
			
			
			while(wb.next())
			{
				tmp_ParamName=wb.getString(1);
				tmp_ParamValue=wb.getString(2);
				if(tmp_ParamName.equals("ADMISSION_PROCESS"))
					inPatientConfigMstVO_p.setStrAdmissionOnline(tmp_ParamValue);
				else if(tmp_ParamName.equals("ADMISSION_ADVICE_VALIDITY_POST"))
					inPatientConfigMstVO_p.setStrAdmissionAdviceValidityFrom(tmp_ParamValue);
				else if(tmp_ParamName.equals("ADMISSION_ADVICE_VALIDITY_PRE"))
					inPatientConfigMstVO_p.setStrAdmissionAdviceValidityTo(tmp_ParamValue);
				else if(tmp_ParamName.equals("ADMISSION_ADVICE_MODE"))
					inPatientConfigMstVO_p.setStrAdmissionAdviceMode(tmp_ParamValue);
				else if(tmp_ParamName.equals("ADMISSION_MODIFICATION_VALIDITY"))
					inPatientConfigMstVO_p.setStrModificationTimeValidity(tmp_ParamValue);
				else if(tmp_ParamName.equals("WHETHER_UNIT_NAME_REQ"))
					inPatientConfigMstVO_p.setStrUnitReq(tmp_ParamValue);
				else if(tmp_ParamName.equals("WHETHER_ROOM_NO_REQ"))
					inPatientConfigMstVO_p.setStrRoomReq(tmp_ParamValue);
				/*else if(tmp_ParamName.equals("ADM_CHARGE_TAKEN_AT_COUNTER"))
					inPatientConfigMstVO_p.setStrAdmissionChargeTakenAtCounter(tmp_ParamValue);*/
				else if(tmp_ParamName.equals("NEW_BORN_BABY_REG_CHARGE"))
					inPatientConfigMstVO_p.setStrNewBornBabyRegistrationCharge(tmp_ParamValue);
				else if(tmp_ParamName.equals("NEW_BORN_BABY_ADM_CHARGE"))
					inPatientConfigMstVO_p.setStrNewBornBabyAdmissionCharge(tmp_ParamValue);
				else if(tmp_ParamName.equals("ADV_AMOUNT_TAKEN_DURING_NEW_BABY"))
					inPatientConfigMstVO_p.setStrAdvanceAmountNewBornBaby(tmp_ParamValue);
				else if(tmp_ParamName.equals("ADM_REPRINT_CHARGE"))
					inPatientConfigMstVO_p.setStrAdmissionReprintCharge(tmp_ParamValue);
				else if(tmp_ParamName.equals("BED_ALLOTMENT_AT_TIME_OF_ADM"))
					inPatientConfigMstVO_p.setStrBedAllotmentAtAdmission(tmp_ParamValue);
				
			}
			
			
			
		}
			catch (Exception e) {
				
				e.printStackTrace();
				
				inPatientConfigMstVO_p.setStrMsg("Error while retrieving Record");
				new HisException("InPatientConfigMstBO", "InPatientConfigMstBO.displayIpdAdmDtls()", e.getMessage());
			}		
	}
	
	
	/**
	 * @param inPatientConfigMstVO_p
	 */
	public void displayIpdDischargeDtls(InPatientConfigMstVO inPatientConfigMstVO_p) {

		WebRowSet wb=null;
		String tmp_ParamName="";
		String tmp_ParamValue="";
		
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.50");
		HisDAO hisDao = new HisDAO("InPatient Config Master","InPatientConfigMstBO.selectQuery()");
		
		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "3");
			hisDao.setQryValue(qryIndex, 2, inPatientConfigMstVO_p.getStrHospCode());
			wb=hisDao.executeQry(qryIndex);
			
			
			while(wb.next())
			{
				tmp_ParamName=wb.getString(1);
				tmp_ParamValue=wb.getString(2);
				if(tmp_ParamName.equals("DISCHARGE_CANCELLATION_TIME"))
					inPatientConfigMstVO_p.setStrDischargeCancellationTime(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_PROCESS"))
					inPatientConfigMstVO_p.setStrDischargeProcessType(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_REPRINT_CHARGE"))
					inPatientConfigMstVO_p.setStrDischargeReprintCharge(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_TYPE_ABSCONDED"))
					inPatientConfigMstVO_p.setStrDischargeTypeAbsconded(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_TYPE_DEATH"))
					inPatientConfigMstVO_p.setStrDischargeTypeDeath(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_TYPE_LAMA"))
					inPatientConfigMstVO_p.setStrDischargeTypeLAMA(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_TYPE_REFERRAL"))
					inPatientConfigMstVO_p.setStrDischargeTypeReferral(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_TYPE_TRANSFER"))
					inPatientConfigMstVO_p.setStrDischargeTypeTransfer(tmp_ParamValue);
				else if(tmp_ParamName.equals("PERMISSIBLE_DISCHARGE_TIME_NOT_REPORTED_PAT"))
					inPatientConfigMstVO_p.setStrNotReportedLeavehour(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_TYPE_NORMAL"))
					inPatientConfigMstVO_p.setStrNormalDischargeType(tmp_ParamValue);
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				inPatientConfigMstVO_p.setStrMsg("Error while retrieving Record");
				new HisException("InPatientConfigMstBO", "InPatientConfigMstBO.displayIpdAdmDtls()", e.getMessage());
			}
	}
	
	
	public void displayPassDetailsValues(InPatientConfigMstVO inPatientConfigMstVO_p) {
		
		WebRowSet wb=null;
		String tmp_ParamName="";
		String tmp_ParamValue="";
		
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.50");
		HisDAO hisDao = new HisDAO("InPatient Config Master","InPatientConfigMstBO.selectQuery()");
		
		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "4");
			hisDao.setQryValue(qryIndex, 2, inPatientConfigMstVO_p.getStrHospCode());
			wb=hisDao.executeQry(qryIndex);
			
			while(wb.next())
			{
				tmp_ParamName=wb.getString(1);
				tmp_ParamValue=wb.getString(2);
					
				if(tmp_ParamName.equals("ATTENDANT_PASS_GEN_AT_ADM_TIME"))
					inPatientConfigMstVO_p.setStrAttendentPassGenerateAtAdmissionTime(tmp_ParamValue);
				else if(tmp_ParamName.equals("ATTENDANT_PASS_REQUIRED"))
					inPatientConfigMstVO_p.setStrAttendentPass(tmp_ParamValue);
				else if(tmp_ParamName.equals("NEW_PASS_PAID_PASS_CHARGE"))
					inPatientConfigMstVO_p.setStrPaidPassCharge(tmp_ParamValue);
				else if(tmp_ParamName.equals("NEW_PASS_VALIDITY_FOR_FREE_PASS"))
					inPatientConfigMstVO_p.setStrNewFreePassValidity(tmp_ParamValue);
				else if(tmp_ParamName.equals("NEW_PASS_VALIDITY_FOR_PAID_PASS"))
					inPatientConfigMstVO_p.setStrNewPaidPassValidity(tmp_ParamValue);
				else if(tmp_ParamName.equals("NO_OF_PAID_PASS"))
					inPatientConfigMstVO_p.setStrNoOfPaidPass(tmp_ParamValue);
				else if(tmp_ParamName.equals("NO_OF_SLIP_PRINTED_AT_ADMISSION"))
					inPatientConfigMstVO_p.setStrNoOfSlipPrintedAtAdmission(tmp_ParamValue);
				else if(tmp_ParamName.equals("PASS_REPRINT_CHARGE"))
					inPatientConfigMstVO_p.setStrPassReprintCharges(tmp_ParamValue);
				else if(tmp_ParamName.equals("PRINT_REQUEST"))
					inPatientConfigMstVO_p.setStrPrintRequest(tmp_ParamValue);
				else if(tmp_ParamName.equals("RENEW_PASS_PAID_PASS_RENEW_CHARGE"))
					inPatientConfigMstVO_p.setStrPaidPassRenewCharge(tmp_ParamValue);
				else if(tmp_ParamName.equals("RENEW_PASS_VALIDITY_FOR_FREE_PASS"))
					inPatientConfigMstVO_p.setStrRenewFreePassValidity(tmp_ParamValue);
				else if(tmp_ParamName.equals("RENEW_PASS_VALIDITY_FOR_PAID_PASS"))
					inPatientConfigMstVO_p.setStrRenewPaidPassValidity(tmp_ParamValue);
				else if(tmp_ParamName.equals("TIMINGS_SUMMER_MORNING")){
					inPatientConfigMstVO_p.setStrSummerMorningFromHr(tmp_ParamValue.split("-")[0].split(":")[0]);
					inPatientConfigMstVO_p.setStrSummerMorningFromMin(tmp_ParamValue.split("-")[0].split(":")[1]);
					inPatientConfigMstVO_p.setStrSummerMorningToHr(tmp_ParamValue.split("-")[1].split(":")[0]);
					inPatientConfigMstVO_p.setStrSummerMorningToMin(tmp_ParamValue.split("-")[1].split(":")[1]);
				}
				else if(tmp_ParamName.equals("TIMINGS_SUMMER_EVENING")){
					inPatientConfigMstVO_p.setStrSummerEveningFromHr(tmp_ParamValue.split("-")[0].split(":")[0]);
					inPatientConfigMstVO_p.setStrSummerEveningFromMin(tmp_ParamValue.split("-")[0].split(":")[1]);
					inPatientConfigMstVO_p.setStrSummerEveningToHr(tmp_ParamValue.split("-")[1].split(":")[0]);
					inPatientConfigMstVO_p.setStrSummerEveningToMin(tmp_ParamValue.split("-")[1].split(":")[1]);
				}
				else if(tmp_ParamName.equals("TIMINGS_WINTER_MORNING")){
					inPatientConfigMstVO_p.setStrWinterMorningFromHr(tmp_ParamValue.split("-")[0].split(":")[0]);
					inPatientConfigMstVO_p.setStrWinterMorningFromMin(tmp_ParamValue.split("-")[0].split(":")[1]);
					inPatientConfigMstVO_p.setStrWinterMorningToHr(tmp_ParamValue.split("-")[1].split(":")[0]);
					inPatientConfigMstVO_p.setStrWinterMorningToMin(tmp_ParamValue.split("-")[1].split(":")[1]);
				}
				else if(tmp_ParamName.equals("TIMINGS_WINTER_EVENING")){
					inPatientConfigMstVO_p.setStrWinterEveningFromHr(tmp_ParamValue.split("-")[0].split(":")[0]);
					inPatientConfigMstVO_p.setStrWinterEveningFromMin(tmp_ParamValue.split("-")[0].split(":")[1]);
					inPatientConfigMstVO_p.setStrWinterEveningToHr(tmp_ParamValue.split("-")[1].split(":")[0]);
					inPatientConfigMstVO_p.setStrWinterEveningToMin(tmp_ParamValue.split("-")[1].split(":")[1]);
				}
				else if(tmp_ParamName.equals("TOTAL_NO_OF_FREE_PASS"))
					inPatientConfigMstVO_p.setStrNoOfFreePass(tmp_ParamValue);
				else if(tmp_ParamName.equals("TOTAL_NO_OF_FREE_PASS_AT_ADMISSION"))
					inPatientConfigMstVO_p.setStrNoOfFreePassAdmisssionTime(tmp_ParamValue);								
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				inPatientConfigMstVO_p.setStrMsg("Error while retrieving Record");
				new HisException("InPatientConfigMstBO", "InPatientConfigMstBO.displayPassDetailsValues()", e.getMessage());
			}
		
	}
	
	public void displayReportDetailsValues(InPatientConfigMstVO inPatientConfigMstVO_p) {

		WebRowSet wb=null;
		String tmp_ParamName="";
		String tmp_ParamValue="";
		
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.50");
		HisDAO hisDao = new HisDAO("InPatient Config Master","InPatientConfigMstBO.selectQuery()");
		
		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "5");
			hisDao.setQryValue(qryIndex, 2, inPatientConfigMstVO_p.getStrHospCode());
			wb=hisDao.executeQry(qryIndex);
			
			while(wb.next())
			{
				tmp_ParamName=wb.getString(1);
				tmp_ParamValue=wb.getString(2);
					
				if(tmp_ParamName.equals("DISCHARGE_SUMMARY_REPORT_ADVICE"))
					inPatientConfigMstVO_p.setStrDischargeSummaryReportAdvice(tmp_ParamValue);
				else if(tmp_ParamName.equals("DISCHARGE_SUMMARY_REPORT_FOOTER"))
					inPatientConfigMstVO_p.setStrDischargeSummaryReportFooter(tmp_ParamValue);							
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				inPatientConfigMstVO_p.setStrMsg("Error while retrieving Record");
				new HisException("InPatientConfigMstBO", "InPatientConfigMstBO.displayPassDetailsValues()", e.getMessage());
			}
	}
	
	public void displayBillDetailsValues(InPatientConfigMstVO inPatientConfigMstVO_p) {

		WebRowSet wb=null;
		String tmp_ParamName="";
		String tmp_ParamValue="";
		
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.50");
		HisDAO hisDao = new HisDAO("InPatient Config Master","InPatientConfigMstBO.selectQuery()");
		
		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "6");
			hisDao.setQryValue(qryIndex, 2, inPatientConfigMstVO_p.getStrHospCode());
			wb=hisDao.executeQry(qryIndex);
			
			while(wb.next())
			{
				tmp_ParamName=wb.getString(1);
				tmp_ParamValue=wb.getString(2);
					
				if(tmp_ParamName.equals("ADVANCE_REQUEST_AT_ADM_ADVICE"))
					inPatientConfigMstVO_p.setStrAdvanceRequestAdmissionAdvice(tmp_ParamValue);
				else if(tmp_ParamName.equals("ADV_REFUND_REQ_ADM_CANCEL"))
					inPatientConfigMstVO_p.setStrRefundRequestAdmissionCancellation(tmp_ParamValue);	
				else if(tmp_ParamName.equals("Integration_With_Billing"))
					inPatientConfigMstVO_p.setStrIntegrationBilling(tmp_ParamValue);	
				else if(tmp_ParamName.equals("WHETHER_CHECK_ADV_AMOUNT_AT_ADM"))
					inPatientConfigMstVO_p.setStrAdvanceAmountAdmission(tmp_ParamValue);	
				else if (tmp_ParamName.equals("ADM_CHARGE_AT_ADM_COUNTER"))
					inPatientConfigMstVO_p.setStrAdmissionCharge(tmp_ParamValue);
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				inPatientConfigMstVO_p.setStrMsg("Error while retrieving Record");
				new HisException("InPatientConfigMstBO", "InPatientConfigMstBO.displayBillDetailsValues()", e.getMessage());
			}
			

		

	}
	
	
	public void displayPrintDetailsValues(InPatientConfigMstVO inPatientConfigMstVO_p) {

		WebRowSet wb=null;
		String tmp_ParamName="";
		String tmp_ParamValue="";
		
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.ipdconfig.50");
		HisDAO hisDao = new HisDAO("InPatient Config Master","InPatientConfigMstBO.selectQuery()");
		
		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "7");
			hisDao.setQryValue(qryIndex, 2, inPatientConfigMstVO_p.getStrHospCode());
			wb=hisDao.executeQry(qryIndex);
			
			while(wb.next())
			{
				tmp_ParamName=wb.getString(1);
				tmp_ParamValue=wb.getString(2);
					
				if(tmp_ParamName.equals("NO_OF_LINES_IN_ADM_SLIP"))
					inPatientConfigMstVO_p.setStrNoOfLineInAdmissionSlip(tmp_ParamValue);
				else if(tmp_ParamName.equals("NO_OF_LINES_IN_NEW_BABY_ADM_SLIP"))
					inPatientConfigMstVO_p.setStrNoOfLineInNewBornBabyAdmissionSlip(tmp_ParamValue);	
				else if(tmp_ParamName.equals("NO_OF_LINES_IN_VISITOR_PASS_SLIP"))
					inPatientConfigMstVO_p.setStrNoOfLineInVisitorPassSlip(tmp_ParamValue);	
				
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				inPatientConfigMstVO_p.setStrMsg("Error while retrieving Record");
				new HisException("InPatientConfigMstBO", "InPatientConfigMstBO.displayBillDetailsValues()", e.getMessage());
			}

	}
	
	
	/**
	 * saves the formBean data to the specified XML file
	 * 
	 * @param formBean -
	 *            formBean Object
	 */
	public void saveValues(InPatientConfigMstVO formBean) {

		JAXBElement<IPDConfig> jaxB = this.readXMLDataObject();

		IPDConfig ipdC = (IPDConfig) jaxB.getValue();

		IPDGeneralConfigType ipdCType = (IPDGeneralConfigType) ipdC
				.getIpdGeneralConfig();

		GenWardApprovalType gwat = (GenWardApprovalType) ipdCType
				.getGenWardApproval();
		PrivateWardApprovalType pwat = (PrivateWardApprovalType) ipdCType
				.getPrivateWardApproval();

		ipdCType.setGenWardAdmission(formBean.getStrGenWardAdmission());

		gwat.getAuthorityId().clear();

		if (formBean.getStrGenWardAdmission().equals("1")) {
			if (formBean.getStrGenWardApprover() != null)
				for (int i = 0; i < formBean.getStrGenWardApprover().length; i++)
					gwat.getAuthorityId().add(
							formBean.getStrGenWardApprover()[i]);
		} else {
			gwat.getAuthorityId().add("0");
		}

		ipdCType.setGenWardApproval(gwat);
		ipdCType.setPrivateWardAdmission(formBean.getStrPrivateWardAdmission());
		pwat.getAuthorityId().clear();

		if (formBean.getStrPrivateWardAdmission().equals("1")) {
			if (formBean.getStrPrivateWardApprover() != null)
				for (int i = 0; i < formBean.getStrPrivateWardApprover().length; i++)
					pwat.getAuthorityId().add(
							formBean.getStrPrivateWardApprover()[i]);
		} else {
			pwat.getAuthorityId().add("0");
		}

		ipdCType.setPrivateWardApproval(pwat);
		ipdCType.setBookBed(formBean.getStrBookBed());
		ipdCType.setBilling(formBean.getStrBilling());
		ipdCType.setNegativeBilling(formBean.getStrNegativeBilling());
		ipdCType.setPatientCategory(formBean.getStrPatientCategory());
		ipdCType.setGovtEmpBasicPayLimit(formBean.getStrGovtEmpPayLimit());
		ipdCType.setPvtEmpMonIncomeLimit(formBean.getStrPvtEmpIncomeLimit());
		ipdCType.setAdmissionAdviceValidityFrom(formBean
				.getStrAdmissionAdviceValidityFrom());
		ipdCType.setAdmissionAdviceValidityTo(formBean
				.getStrAdmissionAdviceValidityTo());
		//ipdCType.setAdmissionChargeTakenAtAdmissionCounter(formBean.getStrAdmissionChargeTakenAtCounter());
		ipdCType.setNewBornBabyAdmissionCharge(formBean
				.getStrNewBornBabyAdmissionCharge());
		ipdCType.setNewBornBabyRegistrationCharge(formBean
				.getStrNewBornBabyRegistrationCharge());
		ipdCType.setDischargeCancellationTime(formBean
				.getStrDischargeCancellationTime());
		ipdCType.setModificationTime(formBean.getStrModificationTimeValidity());
		ipdCType.setAdvanceAmountTakenFromNewBornBaby(formBean
				.getStrAdvanceAmountNewBornBaby());
		ipdCType.setBlockBedExpiryTime(formBean.getStrBlockedExpiryTime());
		ipdCType.setAdmissionReprintCharge(formBean
				.getStrAdmissionReprintCharge());
		ipdCType.setDischargeReprintCharge(formBean
				.getStrDischargeReprintCharge());
		ipdCType.setNoOfLineAmissionSlip(formBean
				.getStrNoOfLineInAdmissionSlip());
		ipdCType.setNoOfLineVisitorPassSlip(formBean
				.getStrNoOfLineInVisitorPassSlip());
		ipdCType.setAdmissionProcessOnline(formBean.getStrAdmissionOnline());
		ipdCType.setPrivateWardType(formBean.getStrPrivateWardType());
		ipdCType.setIcuWardType(formBean.getStrIcuWardType());
		ipdCType.setDaysForPurgingRecordFromCurrentlyAdmissionDetails(formBean
				.getStrPurgingRecordCurrentlyAdmissionDetails());
		ipdCType.setPurgeTimeForNotReported(formBean
				.getStrPurgeTimeNotReportedPatient());
		ipdCType
				.setMsApprovalFormNoRequired(formBean.getStrMsApprovalOffline());
		ipdCType.setDischargeProcessType(formBean.getStrDischargeProcessType());
		ipdCType.setNewBornBabyProcess(formBean.getStrNewBornBabyProcessType());
		ipdCType.setMinAgeToBeMother(formBean.getStrMinAgeToBeMother());
		ipdCType.setMaxNoOfBabyMotherCanBorn(formBean
				.getStrMaxNoOfBabyMotherCanBorn());
		ipdCType.setDiagnosisType(formBean.getStrDiagType());
		ipdCType.setNotReportedLeaveHours(formBean.getStrNotReportedLeavehour());
		ipdCType.setAdmissionAdviceMode(formBean.getStrAdmissionAdviceMode());
		ipdCType.setBelongingRequired(formBean.getStrBelongingRequired());
		ipdCType.setIssueItemRequired(formBean.getStrIssueItemRequired());
		ipdCType.setNurseChecklistMandatory(formBean.getStrNurseChecklistMandatory());
		ipdCType.setUnitNameReq(formBean.getStrUnitReq());
		ipdCType.setRoomNoReq(formBean.getStrRoomReq());
		ipdCType.setNotReportedTimeLimit(formBean.getStrNotReportedTimeLimit());
		ipdCType.setBedLimit(formBean.getStrBedLimit());
		ipdCType.setStrLeaveReqType(formBean.getStrLeaveReqType());
		/*
		 * formBean.setStrIntegrationBilling(ipdBill.getIntegrationWithBilling());
		 * formBean.setStrAdvanceAmountAdmission(ipdBill.getAdvanceamountAdmission());
		 * formBean.setStrAdvanceRequestAdmissionAdvice(ipdBill.getAdvanceRequestAdmissionAdvice());
		 * formBean.setStrPatientAdjustedFinalDischargeBill(ipdBill.getPatientAdjustedFinalBillAtAdmission());
		 * formBean.setStrRefundRequestAdmissionCancellation(ipdBill.getAdvanceRefundRequestAtAdmissionCancellation());
		 * formBean.setStrBedChange(ipdBill.getBedChange());
		 */

		this.writeXMLDataObject(jaxB);

		formBean.setStrMsg("Data Saved Successfully");

		pwat = null;
		gwat = null;
		jaxB = null;
	}

	

	

	public void saveBillDetailsValues(InPatientConfigMstVO formBean) {

		JAXBElement<IPDConfig> jaxB = this.readXMLDataObject();

		IPDConfig ipdC = (IPDConfig) jaxB.getValue();
		IPDBillDetails ipdBill = (IPDBillDetails) ipdC.getIpdBillDetails();

		ipdBill.setIntegrationWithBilling(formBean.getStrIntegrationBilling());
		ipdBill.setAdvanceamountAdmission(formBean
				.getStrAdvanceAmountAdmission());
		ipdBill.setAdvanceRefundRequestAtAdmissionCancellation(formBean
				.getStrRefundRequestAdmissionCancellation());
		ipdBill.setAdvanceRequestAdmissionAdvice(formBean
				.getStrAdvanceRequestAdmissionAdvice());
		ipdBill.setBedChange(formBean.getStrBedChange());
		ipdBill.setPatientAdjustedFinalBillAtAdmission(formBean
				.getStrPatientAdjustedFinalDischargeBill());
		this.writeXMLDataObject(jaxB);
		formBean.setStrMsg("Data Saved Successfully");
		ipdBill = null;
		jaxB = null;
		ipdC = null;
	}

	

	

	public void savePassDetailsDelete(InPatientConfigMstVO formBean) {

		JAXBElement<IPDConfig> jaxB = this.readXMLDataObject();

		IPDConfig ipdC = (IPDConfig) jaxB.getValue();
		IPDPassDetails ipdPassDtls = (IPDPassDetails) ipdC.getIpdPassDetails();

		ipdPassDtls.setNoOfFreePass(formBean.getStrNoOfFreePass());
		ipdPassDtls.setNoOfPaidPass(formBean.getStrNoOfPaidPass());
		ipdPassDtls
				.setNewFreePassValidity(formBean.getStrNewFreePassValidity());
		ipdPassDtls
				.setNewPaidPassValidity(formBean.getStrNewPaidPassValidity());
		ipdPassDtls.setRenewFreePassValidity(formBean
				.getStrRenewFreePassValidity());
		ipdPassDtls.setRenewPaidPassValidity(formBean
				.getStrRenewPaidPassValidity());

		ipdPassDtls.setSummerMorningFromTime(formBean
				.getStrSummerMorningFromHr()
				+ ":" + formBean.getStrSummerMorningFromMin());
		ipdPassDtls.setSummerMorningToTime(formBean.getStrSummerMorningToHr()
				+ ":" + formBean.getStrSummerMorningToMin());

		ipdPassDtls.setSummerEveningFromTime(formBean
				.getStrSummerEveningFromHr()
				+ ":" + formBean.getStrSummerEveningFromMin());
		ipdPassDtls.setSummerEveningToTime(formBean.getStrSummerEveningToHr()
				+ ":" + formBean.getStrSummerEveningToMin());
		ipdPassDtls.setNoOfFreePassAtTimeOfAdmission(formBean
				.getStrNoOfFreePassAdmisssionTime());
		ipdPassDtls.setWinterMorningFromTime(formBean
				.getStrWinterMorningFromHr()
				+ ":" + formBean.getStrWinterMorningFromMin());
		ipdPassDtls.setWinterMorningToTime(formBean.getStrWinterMorningToHr()
				+ ":" + formBean.getStrWinterMorningToMin());

		ipdPassDtls.setWinterEveningFromTime(formBean
				.getStrWinterEveningFromHr()
				+ ":" + formBean.getStrWinterEveningFromMin());
		ipdPassDtls.setWinterEveningToTime(formBean.getStrWinterEveningToHr()
				+ ":" + formBean.getStrWinterEveningToMin());

		ipdPassDtls.setPrintRequest(formBean.getStrPrintRequest());

		ipdPassDtls.setPaidPassCharges(formBean.getStrPaidPassCharge());
		ipdPassDtls.setPaidPassRenewCharges(formBean
				.getStrPaidPassRenewCharge());
		ipdPassDtls.setPassReprintCharges(formBean.getStrPassReprintCharges());
		ipdPassDtls.setAttendentPassRequired(formBean.getStrAttendentPass());
		ipdPassDtls.setAttendentPassGenerateAtAdmissionTime(formBean
				.getStrAttendentPassGenerateAtAdmissionTime());

		this.writeXMLDataObject(jaxB);

		formBean.setStrMsg("Data Saved Successfully");

		ipdPassDtls = null;
		ipdC = null;
		jaxB = null;
	}

	

	

	/**
	 * common method for creating a XML Root Elements Data Object
	 * 
	 * @return JAXBElement Object
	 */
	@SuppressWarnings("unchecked")
	public JAXBElement<IPDConfig> readXMLDataObject() {

		JAXBElement<IPDConfig> jaxB = null;

		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance("ipd.setup");

			Unmarshaller u = jc.createUnmarshaller();

			jaxB = (JAXBElement<IPDConfig>) u.unmarshal(new FileInputStream(
					this.getPath()));

		} catch (JAXBException e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.DISPLAY",
					"InPatientConfigMstBO.readXMLDataObject() -->"
							+ e.getMessage());
		} catch (FileNotFoundException e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.DISPLAY",
					"InPatientConfigMstBO.readXMLDataObject() -->"
							+ e.getMessage());
		}

		return jaxB;

	}

	/**
	 * retrieves JAXBElement Object and Updates the XML file
	 * 
	 * @param jaxB -
	 *            JAXBElement Object
	 */
	public void writeXMLDataObject(JAXBElement<IPDConfig> jaxB) {

		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance("ipd.setup");

			Marshaller m = jc.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(jaxB, new FileWriter(new File(this.getPath())));

		} catch (JAXBException e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.SAVE",
					"InPatientConfigMstBO.writeXMLDataObject() -->"
							+ e.getMessage());
		} catch (IOException e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.SAVE",
					"InPatientConfigMstBO.writeXMLDataObject() -->"
							+ e.getMessage());
		}

	}

	public String getPrivateWardType(String HospCode, InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getPrivateWardType(HospCode);
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrPrivateWardType(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getPrivateWardType",
					"InPatientConfigMstBO.writeXMLDataObject() -->"
							+ e.getMessage());
		}
		return temp;
	}

	public String getIcuWardType(InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getPrivateWardType(vo.getStrHospCode());
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrIcuWardType(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getIcuWardType",
					"InPatientConfigMstBO.writeXMLDataObject() -->"
							+ e.getMessage());
		}
		return temp;
	}

	public String getDischargeTypeLAMA(InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getDischargeType(vo);
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrDischargeTypeLAMA(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getDischargeTypeLAMA",
					"InPatientConfigMstBO.getDischargeType() -->"
							+ e.getMessage());
		}
		return temp;
	}
	
	public String getNormalDischargeType(InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getDischargeType(vo);
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrNormalDischargeType(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getNormalDischargeType",
					"InPatientConfigMstBO.getDischargeType() -->"
							+ e.getMessage());
		}
		return temp;
	}
	
	public String getDischargeTypeAbsconded(InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getDischargeType(vo);
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrDischargeTypeAbsconded(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getDischargeTypeAbsconded",
					"InPatientConfigMstBO.getDischargeType() -->"
							+ e.getMessage());
		}
		return temp;
	}
	public String getDischargeTypeReferral(InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getDischargeType(vo);
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrDischargeTypeReferral(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getDischargeTypeReferral",
					"InPatientConfigMstBO.getDischargeType() -->"
							+ e.getMessage());
		}
		return temp;
	}
	public String getDischargeTypeTransfer(InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getDischargeType(vo);
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrDischargeTypeTransfer(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getDischargeTypeTransfer",
					"InPatientConfigMstBO.getDischargeType() -->"
							+ e.getMessage());
		}
		return temp;
	}
	public String getDischargeTypeDeath(InPatientConfigMstVO vo) {
		HisUtil util = null;
		WebRowSet ws = null;
		String temp = "";
		try {
			ws = DAOIpd.getDischargeType(vo);
			util = new HisUtil("IPDConfig", "InPatientConfigMstBO");
			temp = util.getOptionValue(ws, vo.getStrDischargeTypeDeath(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.getDischargeTypeDeath",
					"InPatientConfigMstBO.getDischargeType() -->"
							+ e.getMessage());
		}
		return temp;
	}

	public void saveGeneralDatainDataBase(InPatientConfigMstVO vo) {
		try {
			boolean retVal=InPatientConfigMstDAO.generalUpdateQuery(vo);
			if(retVal)
			{
				IpdConfigUtil icu=new IpdConfigUtil(vo.getStrHospCode());
				icu.reloadcacheIpdVObj(vo.getStrHospCode());
			}
		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.saveGeneralDatainDataBase",
					"DAOInPatientConfigMst.generalUpdateQuery() -->"
							+ e.getMessage());
		}
		vo.setStrMsg("Data Saved Successfully");
	}
	
	public void saveAdmissionDtlDatainDataBase(InPatientConfigMstVO vo) {
		try {
			
			boolean retVal=InPatientConfigMstDAO.admissionUpdateQuery(vo);
			if(retVal)
			{
				IpdConfigUtil icu=new IpdConfigUtil(vo.getStrHospCode());
				icu.reloadcacheIpdVObj(vo.getStrHospCode());
			}
		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.saveAdmissionDtlDatainDataBase",
					"DAOInPatientConfigMst.admissionUpdateQuery() -->"
							+ e.getMessage());
		}
		vo.setStrMsg("Data Saved Successfully");
	}
	
	public void saveDischargeDtlDataInDataBase(InPatientConfigMstVO vo) {
		try {
			
			boolean retVal=InPatientConfigMstDAO.dischargeUpdateQuery(vo);
			if(retVal)
			{
				IpdConfigUtil icu=new IpdConfigUtil(vo.getStrHospCode());
				icu.reloadcacheIpdVObj(vo.getStrHospCode());
			}
		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.saveDischargeDtlDataInDataBase",
					"InPatientConfigMstBO.dischargeUpdateQuery() -->"
							+ e.getMessage());
		}
		vo.setStrMsg("Data Saved Successfully");
	}
	
	public void savePassDetails(InPatientConfigMstVO vo) {
		try {
			
			boolean retVal=InPatientConfigMstDAO.passUpdateQuery(vo);
			if(retVal)
			{
				IpdConfigUtil icu=new IpdConfigUtil(vo.getStrHospCode());
				icu.reloadcacheIpdVObj(vo.getStrHospCode());
			}
		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.savePassDetails",
					"DAOInPatientConfigMst.passUpdateQuery() -->"
							+ e.getMessage());
		}
		vo.setStrMsg("Data Saved Successfully");
	}
	
	public void saveReportDetails(InPatientConfigMstVO vo) {

		try {
			
			boolean retVal=InPatientConfigMstDAO.reportUpdateQuery(vo);
			if(retVal)
			{
				IpdConfigUtil icu=new IpdConfigUtil(vo.getStrHospCode());
				icu.reloadcacheIpdVObj(vo.getStrHospCode());
			}
		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.saveReportDetails",
					"DAOInPatientConfigMst.reportUpdateQuery() -->"
							+ e.getMessage());
		}

		vo.setStrMsg("Data Saved Successfully");
	}
	
	public void saveBillDatainDataBase(InPatientConfigMstVO vo) {

		try {
			
			boolean retVal=InPatientConfigMstDAO.billUpdateQuery(vo);
			if(retVal)
			{
				IpdConfigUtil icu=new IpdConfigUtil(vo.getStrHospCode());
				icu.reloadcacheIpdVObj(vo.getStrHospCode());
			}
			//InPatientConfigMstDAO.saveBillConfigData(vo);
		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.saveBillDatainDataBase",
					"InPatientConfigMstBO.billUpdateQuery() -->"
							+ e.getMessage());
		}
		vo.setStrMsg("Data Saved Successfully");

	}
	
	public void savePrintDetailsInDataBase(InPatientConfigMstVO vo) {

		try {
			
			boolean retVal=InPatientConfigMstDAO.printUpdateQuery(vo);
			if(retVal)
			{
				IpdConfigUtil icu=new IpdConfigUtil(vo.getStrHospCode());
				icu.reloadcacheIpdVObj(vo.getStrHospCode());
			}
			
		} catch (Exception e) {
			new HisException("In Patient Configuration Master",
					"InPatientConfigMstBO.savePrintDetailsInDataBase",
					"InPatientConfigMstBO.printUpdateQuery() -->"
							+ e.getMessage());
		}
		vo.setStrMsg("Data Saved Successfully");
	}

	
	
}
