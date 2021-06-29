/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: BillSetup Master BO
 * 
 * Created on: 16-09-2011
 */

package billing.masters.bo;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.sql.rowset.WebRowSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import billing.BillConfigUtil;
import billing.masters.dao.BillSetupMstDAO;
import billing.masters.dao.ChargeMstDAO;
import billing.masters.vo.BillSetupMstVO;
import billing.setup.EmergencyType;
import billing.setup.GeneralType;
import billing.setup.IpdType;
import billing.setup.SetupType;

public class BillSetupMstBO {

	// private static final String pathFileName = "hisglobal.hisconfig.hisPath";
	private String path = null;

	public String getPath() {

		path = HisUtil.getParameterFromHisPathXML("BILLING_CONFIG");

		return path;
	}

	public void genInsert(BillSetupMstVO vo) {
		boolean resVal = this.genDBInsert(vo);
		if (resVal) {
			vo.setStrMsg("Record Updated Succesfully");
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
		}
		/* this.genPropInsert(vo); */
	}

	public boolean genDBInsert(BillSetupMstVO vo) {
		boolean retVal = false;
		try {
			retVal = BillSetupMstDAO.genUpdateQuery(vo);
		} catch (Exception e) {
			vo.setStrErr("Record Not Updated:: " + e.toString());
			new HisException("Bill Setup", "BOBillSetup.genDBInsert()",
					e.getMessage());
		}
		return retVal;
	}

	public void surInsert(BillSetupMstVO vo) {
		boolean resVal = this.surDBInsert(vo);
		if (resVal) {
			vo.setStrMsg("Record Updated Succesfully");
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
		}
		/* this.genPropInsert(vo); */
	}

	public boolean surDBInsert(BillSetupMstVO vo) {
		boolean retVal = false;
		try {
			retVal = BillSetupMstDAO.surUpdateQuery(vo);
		} catch (Exception e) {
			vo.setStrErr("Record Not Updated:: " + e.toString());
			new HisException("Bill Setup", "BOBillSetup.surDBInsert()",
					e.getMessage());
		}
		return retVal;
	}

	public void genPropInsert(BillSetupMstVO vo) {

		JAXBElement<SetupType> jaxB = this.readXMLDataObject();

		SetupType sut = (SetupType) jaxB.getValue();

		GeneralType gt = (GeneralType) sut.getGeneral();

		gt.setCancellationTime(vo.getCancelTime());
		gt.setDuplicatePrintCharge(vo.getPrintCharge());

		// gt.setRecordsDisplayMaster(vo.getDefNoRecords());
		gt.setLevelModifyMaster(vo.getModRecords());
		gt.setLevelDeleteMaster(vo.getDelRecords());
		gt.setDuplicatePrint(vo.getStrDuplicatePrint());
		gt.setReceiptType(vo.getStrReceiptType());
		gt.setRefundReceiptType(vo.getStrRefundReceiptType());
		gt.setIsWithoutCrNoRequired(vo.getStrIsWithoutCrNoRequired());
		gt.setOnlineRefundRequestAllowed(vo.getStrOnlineRefundRequestAllowed());
		gt.setIsPreviousCrNoRequiredInt(vo.getStrPreviousCrNoRequiredInt());
		gt.setIsReferringPhysicianRequiredInt(vo
				.getStrReferringPhysicianRequiredInt());
		gt.setIsPreviousCrNoRequiredExt(vo.getStrPreviousCrNoRequiredExt());
		gt.setIsReferringPhysicianRequiredExt(vo
				.getStrReferringPhysicianRequiredExt());
		gt.setCcConfermationType(vo.getStrCcConfirmationType());
		gt.setCcRefundRequired(vo.getStrCashCollectionOfflineRefundRequired());
		gt.setPrintMessageLimit(vo.getStrPrintMessageLimit());

		gt.setIsPrevCrNoSearchingReqInt(vo.getStrPreviousCrNoSearchingInt());
		gt.setIsPrevCrNoSearchingReqExt(vo.getStrPreviousCrNoSearchingExt());
		gt.setDayEndBillingProcessType(vo.getStrDayEndProcessType());
		gt.setDayEndBillingDateType(vo.getStrDayEndDateType());
		gt.setDayEndNonBillingProcessType(vo
				.getStrDayEndNonBillingProcessType());
		gt.setDayEndNonBillingDateType(vo.getStrDayEndNonBillingDateType());
		gt.setOpdReceiptType(vo.getStrOpdReceiptType());
		gt.setIpdReceiptType(vo.getStrIpdReceiptType());
		gt.setRefundAgainstRefundRequest(vo.getStrRefundAgainstRefundRequest());
		gt.setInternalPatient(vo.getStrInternalPatient());
		gt.setExternalPatient(vo.getStrExternalPatient());
		gt.setChargeTypeInt(vo.getStrChargeTypeInt());
		gt.setChargeTypeExt(vo.getStrChargeTypeExt());
		gt.setWardTypeInt(vo.getStrWardTypeInt());
		gt.setWardTypeExt(vo.getStrWardTypeExt());
		gt.setIsApprovalByRequired(vo.getStrIsApprovalByRequired());

		this.writeXMLDataObject(jaxB);

		vo.setStrMsg("Data Saved Successfully");

		gt = null;
		sut = null;
		jaxB = null;

		/*
		 * String stIndexString = "//GENERAL_SETUP_START"; String edIndexString
		 * = "//GENERAL_SETUP_END"; String[] reqContent =
		 * this.getPropContent(stIndexString, edIndexString); StringBuffer
		 * wtBuff = new StringBuffer("");
		 * 
		 * wtBuff.append(reqContent[0]); wtBuff.append(stIndexString);
		 * wtBuff.append("\n"); wtBuff.append("GEN_CANCELLATION_TIME = " +
		 * vo.getCancelTime()); wtBuff.append("\n");
		 * wtBuff.append("GEN_DUPLICATE_PRINT_CHARGE = "+ vo.getPrintCharge());
		 * wtBuff.append("\n"); wtBuff.append("GEN_PRINTER_NAME = " +
		 * vo.getPrinterName()); wtBuff.append("\n");
		 * wtBuff.append("GEN_RECORDS_DISPLAY_MASTER = "+ vo.getDefNoRecords());
		 * wtBuff.append("\n"); wtBuff.append("GEN_LEVEL_MODIFY_MASTER = " +
		 * vo.getModRecords()); wtBuff.append("\n");
		 * wtBuff.append("GEN_LEVEL_DELETE_MASTER = " + vo.getDelRecords());
		 * wtBuff.append("\n"); wtBuff.append("GEN_TEMP_PATH = " +
		 * vo.getTempPath()); wtBuff.append("\n"); wtBuff.append(edIndexString);
		 * wtBuff.append(reqContent[2]);
		 * 
		 * retVal = this.writeContent(wtBuff.toString());
		 */

	}

	/**
	 * @param vo
	 */
	public void setGenPropValues(BillSetupMstVO vo) {
		WebRowSet wb = null;
		String tmp_ParamName = "";
		String tmp_ParamValue = "";
		String wardTypeVal = "", strConsumableChargesTariff;
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.6");
		String qryChargeType = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.7");
		HisDAO hisDao = new HisDAO("BillING",
				"BillSetupMstBO.setGenPropValues()");

		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "1");
			hisDao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			wb = hisDao.executeQry(qryIndex);
			// ValueObject[] arrVOs = {};
			// List<Entry> lstData = HelperMethodsDAO.getAlOfEntryObjects(wb);
			// for(Entry ent : lstData)
			// {
			// if(ent.equals(obj))
			// }

			while (wb.next()) {
				tmp_ParamName = wb.getString(1);
				tmp_ParamValue = wb.getString(2);
				if (tmp_ParamName.equals("FINANCIAL_START_YEAR"))
					vo.setFinStartYear(tmp_ParamValue);
				else if (tmp_ParamName.equals("INBOUND_PURGED_DAY"))
					vo.setInBound(tmp_ParamValue);
				else if (tmp_ParamName.equals("OUTBOUND_PURGED_DAY"))
					vo.setOutBound(tmp_ParamValue);
				else if (tmp_ParamName.equals("CANCELLATION_TIME"))
					vo.setCancelTime(tmp_ParamValue);
				else if (tmp_ParamName.equals("DUPLICATE_PRINT_CHARGE"))
					vo.setPrintCharge(tmp_ParamValue);
				else if (tmp_ParamName.equals("RECORDS_DISPLAY_MASTER"))
					vo.setDefNoRecords(tmp_ParamValue);
				else if (tmp_ParamName.equals("LEVEL_MODIFY_MASTER"))
					vo.setModRecords(tmp_ParamValue);
				else if (tmp_ParamName.equals("LEVEL_DELETE_MASTER"))
					vo.setDelRecords(tmp_ParamValue);
				else if (tmp_ParamName.equals("RECEIPT_TYPE"))
					vo.setStrReceiptType(tmp_ParamValue);
				else if (tmp_ParamName.equals("DUPLICATE_PRINT"))
					vo.setStrDuplicatePrint(tmp_ParamValue);
				else if (tmp_ParamName.equals("IS_WITHOUT_CRNO_REQUIRED"))
					vo.setStrIsWithoutCrNoRequired(tmp_ParamValue);
				else if (tmp_ParamName.equals("ONLINE_REFUND_REQUEST_ALLOWED"))
					vo.setStrOnlineRefundRequestAllowed(tmp_ParamValue);
				else if (tmp_ParamName
						.equals("IS_REFERRING_PHYSICIAN_REQUIRED_INT"))
					vo.setStrReferringPhysicianRequiredInt(tmp_ParamValue);
				else if (tmp_ParamName
						.equals("IS_REFERRING_PHYSICIAN_REQUIRED_EXT"))
					vo.setStrReferringPhysicianRequiredExt(tmp_ParamValue);
				else if (tmp_ParamName.equals("IS_PREVIOUS_CRNO_REQUIRED_INT"))
					vo.setStrPreviousCrNoRequiredInt(tmp_ParamValue);
				else if (tmp_ParamName.equals("IS_PREVIOUS_CRNO_REQUIRED_EXT"))
					vo.setStrPreviousCrNoRequiredExt(tmp_ParamValue);
				else if (tmp_ParamName.equals("CC_CONFERMATION_TYPE"))
					vo.setStrCcConfirmationType(tmp_ParamValue);
				else if (tmp_ParamName.equals("IS_PREV_CRNO_SEARCHING_REQ_INT"))
					vo.setStrPreviousCrNoSearchingInt(tmp_ParamValue);
				else if (tmp_ParamName.equals("IS_PREV_CRNO_SEARCHING_REQ_EXT"))
					vo.setStrPreviousCrNoSearchingExt(tmp_ParamValue);
				else if (tmp_ParamName.equals("CHARGE_TYPE_EXT"))
					vo.setStrChargeTypeExt(tmp_ParamValue);
				else if (tmp_ParamName.equals("WARD_TYPE_EXT"))
					vo.setStrWardTypeExt(tmp_ParamValue);
				else if (tmp_ParamName.equals("CHARGE_TYPE_INT"))
					vo.setStrChargeTypeInt(tmp_ParamValue);
				else if (tmp_ParamName.equals("WARD_TYPE_INT"))
					vo.setStrWardTypeInt(tmp_ParamValue);
				else if (tmp_ParamName.equals("DAY_END_BILLING_PROCESS_TYPE"))
					vo.setStrDayEndProcessType(tmp_ParamValue);
				else if (tmp_ParamName.equals("DAY_END_BILLING_DATE_TYPE"))
					vo.setStrDayEndDateType(tmp_ParamValue);
				else if (tmp_ParamName
						.equals("DAY_END_NON_BILLING_PROCESS_TYPE"))
					vo.setStrDayEndNonBillingProcessType(tmp_ParamValue);
				else if (tmp_ParamName.equals("DAY_END_NON_BILLING_DATE_TYPE"))
					vo.setStrDayEndNonBillingDateType(tmp_ParamValue);
				else if (tmp_ParamName.equals("OPD_RECEIPT_TYPE"))
					vo.setStrOpdReceiptType(tmp_ParamValue);
				else if (tmp_ParamName.equals("IPD_RECEIPT_TYPE"))
					vo.setStrIpdReceiptType(tmp_ParamValue);
				else if (tmp_ParamName.equals("CC_REFUND_REQUIRED"))
					vo.setStrCashCollectionOfflineRefundRequired(tmp_ParamValue);
				else if (tmp_ParamName.equals("PRINT_MESSAGE_LIMIT"))
					vo.setStrPrintMessageLimit(tmp_ParamValue);
				else if (tmp_ParamName.equals("REFUND_RECEIPT_TYPE"))
					vo.setStrRefundReceiptType(tmp_ParamValue);
				else if (tmp_ParamName.equals("REFUND_AGAINST_REFUND_REQUEST"))
					vo.setStrRefundAgainstRefundRequest(tmp_ParamValue);
				else if (tmp_ParamName.equals("INTERNAL_PATIENT"))
					vo.setStrInternalPatient(tmp_ParamValue);
				else if (tmp_ParamName.equals("EXTERNAL_PATIENT"))
					vo.setStrExternalPatient(tmp_ParamValue);
				else if (tmp_ParamName.equals("IS_APPROVAL_BY_REQUIRED"))
					vo.setStrIsApprovalByRequired(tmp_ParamValue);
				else if (tmp_ParamName.equals("DEFAULT_TARIFF_CODE"))
					vo.setStrDefaultTariffCode(tmp_ParamValue);
				else if (tmp_ParamName.equals("CONSUMABLE_CHARGES_GROUP"))
					vo.setStrConsumableChargesGroupId(tmp_ParamValue);
				else if (tmp_ParamName.equals("CONSUMABLE_CHARGES_TARIFF"))
					vo.setStrConsumableChargesTariffCode(tmp_ParamValue);
				else if (tmp_ParamName.equals("CREDIT_BILL_APP_REQUIRED"))
					vo.setStrCreditCashlessAppRequired(tmp_ParamValue);
				else if (tmp_ParamName.equals("PAID_CAT"))
					vo.setStrPaidCategory(tmp_ParamValue);
				else if (tmp_ParamName.equals("AROGYASHREE_CAT"))
					vo.setStrArogyaShreeCategory(tmp_ParamValue);
				else if (tmp_ParamName.equals("GEN_CREDIT_CAT"))
					vo.setStrGenCategory(tmp_ParamValue);
				else if (tmp_ParamName.equals("CGSH_CAT"))
					vo.setStrCGSHCategory(tmp_ParamValue);
				else if (tmp_ParamName.equals("WSH_CAT"))
					vo.setStrWSHCategory(tmp_ParamValue);
				else if (tmp_ParamName.equals("DAY_END_TIME_BOUND_FLAG"))
					vo.setStrDayEndTimeBoundFlag(tmp_ParamValue);
				else if (tmp_ParamName.equals("DAY_END_TIME"))
					vo.setStrDayEndTime(tmp_ParamValue);
				else if (tmp_ParamName.equals("AROGYASHREE_TS_CAT"))
					vo.setStrArogyaShreeTSCategory(tmp_ParamValue);
				else if (tmp_ParamName
						.equals("AROGYASHREE_TS_OPD_CREDIT_LIMIT"))
					vo.setStrArogyaTSCreditLimit(tmp_ParamValue);
				else if (tmp_ParamName.equals("URGENT_TARIFF_SURCHARGE"))
					vo.setStrUrgTrfSur(tmp_ParamValue);
				else if (tmp_ParamName.equals("LOGO_REQ"))
					vo.setLogoReq(tmp_ParamValue);
				else if (tmp_ParamName.equals("OSTF_CAT"))
					vo.setStrOstfCat(tmp_ParamValue);
				else if (tmp_ParamName.equals("NIRAMAYA"))
					vo.setNiramaya(tmp_ParamValue);
				else if (tmp_ParamName.equals("HINDI_HEAD_REQ"))
					vo.setHindiReq(tmp_ParamValue);
				else if (tmp_ParamName.equals("HEAD_REQ"))
					vo.setHeaderReq(tmp_ParamValue);
				System.out.println("==============>>nirmaya1="+vo.getNiramaya());
				System.out.println("==============>>tmp_ParamName="+tmp_ParamName);
			}
			vo.setStrIsWithoutCrNoRequired("0");
			LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
			mapProcedureParam.clear();
			mapProcedureParam.put("1", BillConfigUtil.SUPER_HOSPITAL_CODE);
			// Charge Type Combo
			vo.setStrChargeTypeValueInt(HisComboOptions.getOptionsFromQuery(
					qryChargeType, mapProcedureParam, vo.getStrChargeTypeInt(),
					"0^Select Value", false));
			vo.setStrChargeTypeValueExt(HisComboOptions.getOptionsFromQuery(
					qryChargeType, mapProcedureParam, vo.getStrChargeTypeInt(),
					"0^Select Value", false));
			// vo.setStrChargeTypeValueExt(HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_sblt_chargetype_mst",
			// mapProcedureParam,vo.getStrChargeTypeExt(), "0^Select Value",
			// false));

			// Ward Type combo
			HisUtil hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstBO.setGenPropValues");

			String qryWard = billing.qryHandler_billing.getQuery(1,
					"select.advanceMst.1")
					.replace("?", vo.getStrHospitalCode());
			wardTypeVal = hisUtil.getOptionValue(qryWard,
					vo.getStrWardTypeInt(), "0^Select Value");

			vo.setStrWardTypeValueInt(wardTypeVal);
			wardTypeVal = hisUtil.getOptionValue(qryWard,
					vo.getStrWardTypeExt(), "0^Select Value");
			vo.setStrWardTypeValueExt(wardTypeVal);

			// Consumable Charges Group
			String strGroupQuery = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.1");
			vo.setStrConsumableChargesGroupIdValues(hisUtil.getOptionValue(
					strGroupQuery.replace("?", vo.getStrHospitalCode()),
					vo.getStrConsumableChargesGroupId(), "0^Select Value"));

			/***** Paid ,Arogya Shree And CGSH category combo addded by vikrant *****/

			vo.setStrPaidCategoryValue(this.getStrPaidCategoryCombo(
					vo.getStrPaidCategory(), vo.getStrHospitalCode()));
			vo.setStrArogyaShreeCategoryvalue(this
					.getStrArogyaShreeCategoryCombo(
							vo.getStrArogyaShreeCategory(),
							vo.getStrHospitalCode()));
			vo.setStrArogyaShreeTSCategoryvalue(this
					.getStrArogyaShreeTSCategoryCombo(
							vo.getStrArogyaShreeTSCategory(),
							vo.getStrHospitalCode()));
			vo.setStrOSTFCategoryvalue(this.getStrOSTFCategoryCombo(
					vo.getStrOstfCat(), vo.getStrHospitalCode()));
			vo.setNiramayaValue(this.getNiramayaCombo(vo.getNiramaya(),
					vo.getStrHospitalCode()));
			System.out.println("===================>>>>>>>>>>niramaya="+vo.getNiramaya());
			vo.setStrGenCategoryValue(this.getStrGenCategoryCombo(
					vo.getStrGenCategory(), vo.getStrHospitalCode()));
			vo.setStrCGSHCategoryvalue(this.getStrCGSHCategoryCombo(
					vo.getStrCGSHCategory(), vo.getStrHospitalCode()));
			vo.setStrWSHCategoryValue(this.getStrWSHCategoryCombo(
					vo.getStrWSHCategory(), vo.getStrHospitalCode()));

			// Consumable Charges Tariff
			// String strConsumableChargesTariffQuery =
			// billing.qryHandler_billing.getQuery(1,"select.billsetup.consumableChargesTariff.1").replace("?",vo.getStrHospitalCode());
			// strConsumableChargesTariffQuery+=
			// " AND "+billing.qryHandler_billing.getQuery(1,"select.billsetup.consumableChargesTariff.2").replace("?",vo.getStrConsumableChargesGroupId());
			// strConsumableChargesTariff =
			// hisUtil.getOptionValue(strConsumableChargesTariffQuery,
			// vo.getStrConsumableChargesTariffCode(), "0^Select Value");
			// vo.setStrConsumableChargesTariffValues(strConsumableChargesTariff);
		} catch (Exception e) {
			vo.setStrErr("Data Not Found!!!");
			new HisException("Bill Setup", "BOBillSetup.setGenPropValues()",
					e.getMessage());
		}
	}

	public void opdInsert(BillSetupMstVO vo) {

		// JAXBElement<SetupType> jaxB = this.readXMLDataObject();
		//
		// SetupType sut = (SetupType) jaxB.getValue();
		//
		// OpdType opd = (OpdType) sut.getOpd();
		//
		// opd.setThirdParyBilling(vo.getOpdThirdPartyBilling());
		// opd.setDiscount(vo.getOpdDiscount());
		// opd.setDiscountClerk(vo.getOpdDiscountClerk());
		// opd.setRefundPenalty(vo.getOpdRefundPenalty());
		// opd.setRoundOff(vo.getOpdRoundOff());
		// opd.setOpdServiceTax(vo.getOpdServiceTax());
		// opd.setOpdFreeCategory(vo.getOpdFreeCategory());
		//
		// vo.setStrChargeTypeId("1");
		//
		// if(vo.getOpdServiceTax().equals("0")){
		//
		// BillSetupMstDAO.serviceTaxSetOff(vo);
		//
		// }else{
		//
		// BillSetupMstDAO.serviceTaxSetOn(vo);
		//
		// }
		//
		// this.writeXMLDataObject(jaxB);
		// vo.setStrMsg("Data Saved Successfully");
		//
		// opd = null;
		// sut = null;
		// jaxB = null;

		BillSetupMstDAO dao = null;
		try {
			dao = new BillSetupMstDAO();
			boolean fval = dao.opdInsert(vo);
			if (fval) {
				vo.setStrMsg("Record Updated Succesfully");
				// Reload Cache
				BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
				bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
			}
		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setGenPropValues()",
					e.getMessage() + "==" + e.toString());
		}

	}

	public void setOpdValues(BillSetupMstVO vo) {

		// JAXBElement<SetupType> jaxB = this.readXMLDataObject();
		//
		// SetupType sut = (SetupType) jaxB.getValue();
		//
		// OpdType opd = (OpdType) sut.getOpd();
		//
		// vo.setOpdThirdPartyBilling(opd.getThirdParyBilling());
		// vo.setOpdDiscount(opd.getDiscount());
		// vo.setOpdDiscountClerk(opd.getDiscountClerk());
		// vo.setOpdRefundPenalty(opd.getRefundPenalty());
		// vo.setOpdRoundOff(opd.getRoundOff());
		// vo.setOpdServiceTax(opd.getOpdServiceTax());
		// vo.setOpdFreeCategory(opd.getOpdFreeCategory());
		//
		//
		// opd = null;
		// sut = null;
		// jaxB = null;

		WebRowSet wb = null;
		String tmp_ParamName = "";
		String tmp_ParamValue = "";
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.6");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"BillSetupMstBO.selectOPDQuery()");

		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "2");
			hisDao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			wb = hisDao.executeQry(qryIndex);

			while (wb.next()) {
				tmp_ParamName = wb.getString(1);
				tmp_ParamValue = wb.getString(2);
				if (tmp_ParamName.equals("OPD_THIRD_PARY_BILLING"))
					vo.setOpdThirdPartyBilling(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCOUNT"))
					vo.setOpdDiscount(tmp_ParamValue);
				else if (tmp_ParamName.equals("OPD_DISCOUNT_CLERK"))
					vo.setOpdDiscountClerk(tmp_ParamValue);
				else if (tmp_ParamName.equals("REFUND_PENALTY"))
					vo.setOpdRefundPenalty(tmp_ParamValue);
				else if (tmp_ParamName.equals("OPD_ROUND_OFF"))
					vo.setOpdRoundOff(tmp_ParamValue);
				else if (tmp_ParamName.equals("OPD_SERVICE_TAX"))
					vo.setOpdServiceTax(tmp_ParamValue);
				else if (tmp_ParamName.equals("OPD_FREE_CATEGORY"))
					vo.setOpdFreeCategory(tmp_ParamValue);
			}

			vo.setStrOpdFreeCategoryValue(this.getStrOpdFreeCategoryValue(vo
					.getOpdFreeCategory()));
		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setOpdPropValues()",
					e.getMessage());
		}

	}

	public void billFormatInsert(BillSetupMstVO vo) throws Exception {

		// JAXBElement<SetupType> jaxB = this.readXMLDataObject();
		//
		// SetupType sut = (SetupType) jaxB.getValue();
		//
		// BillFormatType bft = (BillFormatType) sut.getBillformat();
		//
		// bft.setFooter(vo.getBillFormatFooter());
		// bft.setHeader1(vo.getBillFormatHeader1());
		// bft.setHeader2(vo.getBillFormatHeader2());
		// bft.setHeader3(vo.getBillFormatHeader3());
		// bft.setHeader4(vo.getBillFormatHeader4());
		// bft.setDisclaimerWithoutCrNo(vo.getBillDisclaimerWithoutCrNo());
		// bft.setDisclaimerServices(vo.getBillDisclaimerServices());
		// bft.setDisclaimerRefund(vo.getBillDisclaimerRefund());
		// bft.setDisclaimerAdvance(vo.getBillDisclaimerAdvance());
		// bft.setDisclaimerPartPayment(vo.getBillDisclaimerPartPayment());
		// bft.setDisclaimerFinalBill(vo.getBillDisclaimerFinalBill());
		// bft.setDuplicatePrintReq(vo.getBillDisclaimerDuplicatePrintRequired());
		// bft.setBillLineOpdServices(vo.getBillLineOpdServices());
		//
		//
		// this.writeXMLDataObject(jaxB);
		// vo.setStrMsg("Data Saved Successfully");
		//
		// bft = null;
		// sut = null;
		// jaxB = null;

		String qryUpdate = billing.qryHandler_billing.getQuery(1,
				"update.billsetup.1");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"BillSetupMstBO.billformatUpdateQuery()");
		String[] arrParamName = { "HEADER1", "HEADER2", "HEADER3", "HEADER4",
				"FOOTER", "DISCLAIMER_WITHOUT_CRNO", "DISCLAIMER_SERVICES",
				"DISCLAIMER_REFUND", "DISCLAIMER_ADVANCE",
				"DISCLAIMER_PART_PAYMENT", "DISCLAIMER_FINAL_BILL",
				"DUPLICATE_PRINT_REQ", "BILL_LINE_OPD_SERVICES" };

		String[] arrParamValue = { vo.getBillFormatHeader1(),
				vo.getBillFormatHeader2(), vo.getBillFormatHeader3(),
				vo.getBillFormatHeader4(), vo.getBillFormatFooter(),
				vo.getBillDisclaimerWithoutCrNo(),
				vo.getBillDisclaimerServices(), vo.getBillDisclaimerRefund(),
				vo.getBillDisclaimerAdvance(),
				vo.getBillDisclaimerPartPayment(),
				vo.getBillDisclaimerFinalBill(),
				vo.getBillDisclaimerDuplicatePrintRequired(),
				vo.getBillLineOpdServices() };
		for (int i = 0; i < arrParamValue.length; i++) {
			if (arrParamValue[i] == "")
				arrParamValue[i] = "-";
		}

		try {

			for (int i = 0; i < arrParamName.length; i++) {
				int qryIndex1 = hisDao.setQuery(qryUpdate);
				hisDao.setQryValue(qryIndex1, 1, arrParamValue[i]);
				hisDao.setQryValue(qryIndex1, 2, vo.getStrSeatId());
				hisDao.setQryValue(qryIndex1, 3, "5");
				hisDao.setQryValue(qryIndex1, 4, arrParamName[i]);
				hisDao.setQryValue(qryIndex1, 5, vo.getStrHospitalCode());
				hisDao.execute(qryIndex1, 0);
			}

			synchronized (hisDao) {
				hisDao.fire();
			}
			vo.setStrMsg("Record Updated Successfully");
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());

		} catch (Exception e) {
			throw new Exception("DAOBillSetup.ipdGenUpdateQuery()-->"
					+ e.getMessage());

		} finally {
			hisDao.free();
			hisDao = null;
		}

	}

	public void setBillFormatValues(BillSetupMstVO vo) {

		// JAXBElement<SetupType> jaxB = this.readXMLDataObject();
		//
		// SetupType sut = (SetupType) jaxB.getValue();
		//
		// BillFormatType bft = (BillFormatType) sut.getBillformat();
		//
		// vo.setBillFormatHeader1(bft.getHeader1());
		// vo.setBillFormatHeader2(bft.getHeader2());
		// vo.setBillFormatHeader3(bft.getHeader3());
		// vo.setBillFormatHeader4(bft.getHeader4());
		// vo.setBillFormatFooter(bft.getFooter());
		// vo.setBillDisclaimerWithoutCrNo(bft.getDisclaimerWithoutCrNo() );
		// vo.setBillDisclaimerServices(bft.getDisclaimerServices());
		// vo.setBillDisclaimerRefund(bft.getDisclaimerRefund());
		// vo.setBillDisclaimerAdvance(bft.getDisclaimerAdvance());
		// vo.setBillDisclaimerPartPayment(bft.getDisclaimerPartPayment());
		// vo.setBillDisclaimerFinalBill(bft.getDisclaimerFinalBill());
		// vo.setBillDisclaimerDuplicatePrintRequired(bft.getDuplicatePrintReq());
		// vo.setBillLineOpdServices(bft.getBillLineOpdServices());
		//
		//
		// bft = null;
		// sut = null;
		// jaxB = null;

		WebRowSet wb = null;
		String tmp_ParamName = "";
		String tmp_ParamValue = "";
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.6");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"BillSetupMstBO.selectBillformatQuery()");

		try {

			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "5");
			hisDao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			wb = hisDao.executeQry(qryIndex);

			while (wb.next()) {
				tmp_ParamName = wb.getString(1);
				tmp_ParamValue = wb.getString(2);
				if (tmp_ParamName.equals("HEADER1"))
					vo.setBillFormatHeader1(tmp_ParamValue);
				else if (tmp_ParamName.equals("HEADER2"))
					vo.setBillFormatHeader2(tmp_ParamValue);
				else if (tmp_ParamName.equals("HEADER3"))
					vo.setBillFormatHeader3(tmp_ParamValue);
				else if (tmp_ParamName.equals("HEADER4"))
					vo.setBillFormatHeader4(tmp_ParamValue);
				else if (tmp_ParamName.equals("FOOTER"))
					vo.setBillFormatFooter(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCLAIMER_WITHOUT_CRNO"))
					vo.setBillDisclaimerWithoutCrNo(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCLAIMER_SERVICES"))
					vo.setBillDisclaimerServices(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCLAIMER_REFUND"))
					vo.setBillDisclaimerRefund(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCLAIMER_ADVANCE"))
					vo.setBillDisclaimerAdvance(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCLAIMER_PART_PAYMENT"))
					vo.setBillDisclaimerPartPayment(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCLAIMER_FINAL_BILL"))
					vo.setBillDisclaimerFinalBill(tmp_ParamValue);
				else if (tmp_ParamName.equals("DUPLICATE_PRINT_REQ"))
					vo.setBillDisclaimerDuplicatePrintRequired(tmp_ParamValue);
				else if (tmp_ParamName.equals("BILL_LINE_OPD_SERVICES"))
					vo.setBillLineOpdServices(tmp_ParamValue);

			}

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setBillFormatValues()",
					e.getMessage());
		}

	}

	public void setSurchargeValues(BillSetupMstVO vo) {

		// JAXBElement<SetupType> jaxB = this.readXMLDataObject();
		//
		// SetupType sut = (SetupType) jaxB.getValue();
		//
		// BillFormatType bft = (BillFormatType) sut.getBillformat();
		//
		// vo.setBillFormatHeader1(bft.getHeader1());
		// vo.setBillFormatHeader2(bft.getHeader2());
		// vo.setBillFormatHeader3(bft.getHeader3());
		// vo.setBillFormatHeader4(bft.getHeader4());
		// vo.setBillFormatFooter(bft.getFooter());
		// vo.setBillDisclaimerWithoutCrNo(bft.getDisclaimerWithoutCrNo() );
		// vo.setBillDisclaimerServices(bft.getDisclaimerServices());
		// vo.setBillDisclaimerRefund(bft.getDisclaimerRefund());
		// vo.setBillDisclaimerAdvance(bft.getDisclaimerAdvance());
		// vo.setBillDisclaimerPartPayment(bft.getDisclaimerPartPayment());
		// vo.setBillDisclaimerFinalBill(bft.getDisclaimerFinalBill());
		// vo.setBillDisclaimerDuplicatePrintRequired(bft.getDuplicatePrintReq());
		// vo.setBillLineOpdServices(bft.getBillLineOpdServices());
		//
		//
		// bft = null;
		// sut = null;
		// jaxB = null;

		WebRowSet wb = null;
		String tmp_ParamName = "";
		String tmp_ParamValue = "";
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.6");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"BillSetupMstBO.setSurchargeValues()");

		try {

			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "8");
			hisDao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			wb = hisDao.executeQry(qryIndex);

			while (wb.next()) {
				tmp_ParamName = wb.getString(1);
				tmp_ParamValue = wb.getString(2);
				if (tmp_ParamName.equals("SURCHARGE_Ccu%"))
					vo.setStrSurCc(tmp_ParamValue);
				else if (tmp_ParamName.equals("SURCHARGE_Dcu%"))
					vo.setStrSurDc(tmp_ParamValue);
				else if (tmp_ParamName.equals("SURCHARGE_Icu%"))
					vo.setStrSurIc(tmp_ParamValue);
				else if (tmp_ParamName.equals("SURCHARGE_Idu%"))
					vo.setStrSurId(tmp_ParamValue);
				else if (tmp_ParamName.equals("SURCHARGE_Cca%"))
					vo.setStrSurCc1(tmp_ParamValue);
				else if (tmp_ParamName.equals("SURCHARGE_Dca%"))
					vo.setStrSurDc1(tmp_ParamValue);
				else if (tmp_ParamName.equals("SURCHARGE_Ica%"))
					vo.setStrSurIc1(tmp_ParamValue);
				else if (tmp_ParamName.equals("SURCHARGE_Ida%"))
					vo.setStrSurId1(tmp_ParamValue);

			}

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setSurchargeValues()",
					e.getMessage());
		}

	}

	public void jobsInsert(BillSetupMstVO vo) {

		try {
			BillSetupMstDAO.jobsUpdateQuery(vo);
			vo.setStrMsg("Data Saved Successfully");
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
		} catch (Exception e) {
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.jobsInsert()",
					e.getMessage());
		}
	}

	public void setJobsValues(BillSetupMstVO vo) {

		try {
			vo.setJob1BedCharge(BillSetupMstDAO.getRetriveValue("6",
					"JOB1_BED_CHARGE", vo.getStrHospitalCode()));
			vo.setJob1CompusoryCharge(BillSetupMstDAO.getRetriveValue("6",
					"JOB1_COMPULSORY_CHARGE", vo.getStrHospitalCode()));
			vo.setJob2EmgCompusoryCharge(BillSetupMstDAO.getRetriveValue("6",
					"JOB2_EMG_COMPULSORY_CHARGE", vo.getStrHospitalCode()));
			vo.setJob2FinStartYear(BillSetupMstDAO.getRetriveValue("6",
					"JOB2_FINANCIAL_START_YEAR", vo.getStrHospitalCode()));
		} catch (Exception e) {

			new HisException("Bill Setup", "BOBillSetup.setJobsValues()",
					e.getMessage());
		}
	}

	public void ipdBedCalcInsert(BillSetupMstVO vo) {

		try {
			BillSetupMstDAO.ipdBedCalcUpdateQuery(vo);
			vo.setStrMsg("Data Saved Successfully");
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
		} catch (Exception e) {
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.ipdBedCalcInsert()",
					e.getMessage());
		}
	}

	public void setIpdBedCalc(BillSetupMstVO vo) {

		try {
			vo.setIpdBedCalcWard(BillSetupMstDAO.getRetriveValue("3",
					"CHARGE_WARD_DAY", vo.getStrHospitalCode()));

			// System.out.println("inside BO : "+vo.getIpdBedCalcWard());

			vo.setIpdBedCalcWardCharg(BillSetupMstDAO.getRetriveValue("3",
					"TRANSFER_BEDCHARGE", vo.getStrHospitalCode()));
			vo.setIpdBedCalcServiceCharge(BillSetupMstDAO.getRetriveValue("3",
					"TRANSFER_SERVICES", vo.getStrHospitalCode()));
		} catch (Exception e) {
			new HisException("Bill Setup", "BOBillSetup.setIpdBedCalc()",
					e.getMessage());
		}

	}

	public void ipdGenInsert(BillSetupMstVO vo) {

		boolean resVal = this.ipdGenDBInsert(vo);

		if (resVal) {
			// this.ipdGenPropInsert(vo);
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
		}

	}

	public boolean ipdGenDBInsert(BillSetupMstVO vo) {

		boolean retVal = false;
		try {
			retVal = BillSetupMstDAO.ipdGenUpdateQuery(vo);

			vo.setStrChargeTypeId("2");

			if (vo.getIpdServiceTax().equals("0")) {

				retVal = BillSetupMstDAO.serviceTaxSetOff(vo);

			} else {

				retVal = BillSetupMstDAO.serviceTaxSetOn(vo);

			}

			vo.setStrMsg("Data Updated Successfully");
		} catch (Exception e) {
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setIpdBedCalc()",
					e.getMessage());
		}

		return retVal;
	}

	public void ipdGenPropInsert(BillSetupMstVO vo) {

		JAXBElement<SetupType> jaxB = this.readXMLDataObject();

		SetupType sut = (SetupType) jaxB.getValue();

		IpdType ipd = (IpdType) sut.getIpd();

		ipd.setThirdParyBilling(vo.getIpdGenThirdPartyBilling());
		ipd.setDiscount(vo.getIpdGenDiscount());
		ipd.setDiscountClerk(vo.getIpdGenClerkDiscount());
		ipd.setRefundPenalty(vo.getIpdGenPenalty());
		ipd.setCreditBilling(vo.getIpdGenCreditBilling());
		ipd.setPartPaymentDuration(vo.getIpdGenPayment());
		ipd.setRoundOff(vo.getIpdGenRoundOff());
		ipd.setServiceTaxOnTotalBill(vo.getIpdGenServiceTaxOnTotalBill());
		ipd.setExcessCreditLimit(vo.getIpdGenExcessCreditLimit());
		ipd.setIpdGenAdtProcessType(vo.getIpdGenAdtProcessType());
		ipd.setIpdServiceTax(vo.getIpdServiceTax());
		ipd.setIpdFreeCategory(vo.getIpdFreeCategory());

		this.writeXMLDataObject(jaxB);
		vo.setStrMsg("Data Saved Successfully");

		ipd = null;
		sut = null;
		jaxB = null;

	}

	public void setIpdGen(BillSetupMstVO vo) {

		// JAXBElement<SetupType> jaxB = this.readXMLDataObject();
		//
		// SetupType sut = (SetupType) jaxB.getValue();
		//
		// IpdType ipd = (IpdType) sut.getIpd();
		//
		// String strCheckOutTime[];
		// String strCheckOutTimePrivate[];
		// try {
		// strCheckOutTime = BillSetupMstDAO.getRetriveValue("3",
		// "CHECK_OUT_TIME_GENERAL", vo.getStrHospitalCode())
		// .split(":");
		// vo.setIpdGenCheckOutTimeHr(strCheckOutTime[0]);
		// vo.setIpdGenCheckOutTimeMn(strCheckOutTime[1]);
		//
		// strCheckOutTimePrivate = BillSetupMstDAO.getRetriveValue("3",
		// "CHECK_OUT_TIME_PRIVATE", vo.getStrHospitalCode())
		// .split(":");
		//
		// vo.setIpdGenCheckOutTimePrivateHr(strCheckOutTimePrivate[0]);
		// vo.setIpdGenCheckOutTimePrivateMn(strCheckOutTimePrivate[1]);
		//
		// vo.setIpdGenFlexibleTime(BillSetupMstDAO.getRetriveValue("3",
		// "FLEXIBLE_TIME", vo.getStrHospitalCode()));
		//
		// vo.setIpdGenFlexibleTimeAdmission(BillSetupMstDAO
		// .getRetriveValue("3", "CHECK_IN_FLEXIBILITY", vo
		// .getStrHospitalCode()));
		//
		// vo.setIpdGenCreditBilling(BillSetupMstDAO.getRetriveValue("3",
		// "CREDIT_BILL_ALLOWED", vo.getStrHospitalCode()));
		//
		// vo.setIpdGenExcessCreditLimit(BillSetupMstDAO.getRetriveValue(
		// "3", "CREDIT_LIMIT", vo.getStrHospitalCode()));
		//
		// vo.setStrGeneralRoomType(BillSetupMstDAO.getRetriveValue(
		// "3", "GENERAL_ROOM_TYPE_ID", vo.getStrHospitalCode()));
		// vo.setStrGeneralWardType(BillSetupMstDAO.getRetriveValue(
		// "3", "GENERAL_WARD_TYPE_ID", vo.getStrHospitalCode()));
		// vo.setIpdGenServiceFreeTreatmentTime(BillSetupMstDAO.getRetriveValue(
		// "3", "EMG_FREECHARGE_HOUR", vo.getStrHospitalCode()));
		// HashMap<String, String> mapProcedureParam = new HashMap<String,
		// String>();
		// mapProcedureParam.put("hosp_code", vo.getStrHospitalCode());
		// mapProcedureParam.put("err", "#1");
		// mapProcedureParam.put("resultset", "#2");
		// vo.setStrGeneralWardTypeValue(HisComboOptions.getOptionsFromProc("pkg_ipd_view.Proc_Hipt_Wardtype_Mst",
		// mapProcedureParam, vo.getStrGeneralWardType(), "0^Select Value",
		// false));
		// vo.setStrGeneralRoomTypeValue(HisComboOptions.getOptionsFromProc("pkg_ipd_view.Proc_Hipt_Wardtype_Mst",
		// mapProcedureParam, vo.getStrGeneralRoomType(), "0^Select Value",
		// false));
		//
		//
		//
		// } catch (Exception e) {
		//
		// // e.printStackTrace();
		//
		// new HisException("Bill Setup", "BOBillSetup.setIpdGen()", e
		// .getMessage());
		// }
		//
		//
		// vo.setIpdGenThirdPartyBilling(ipd.getThirdParyBilling());
		// vo.setIpdGenDiscount(ipd.getDiscount());
		// vo.setIpdGenClerkDiscount(ipd.getDiscountClerk());
		// vo.setIpdGenPenalty(ipd.getRefundPenalty());
		// vo.setIpdGenCreditBilling(ipd.getCreditBilling());
		// vo.setIpdGenPayment(ipd.getPartPaymentDuration());
		// vo.setIpdGenRoundOff(ipd.getRoundOff());
		// vo.setIpdGenServiceTaxOnTotalBill(ipd.getServiceTaxOnTotalBill());
		// vo.setIpdGenAdtProcessType(ipd.getIpdGenAdtProcessType());
		// vo.setIpdServiceTax(ipd.getIpdServiceTax());
		// vo.setIpdFreeCategory(ipd.getIpdFreeCategory());
		//
		// ipd = null;
		// sut = null;
		// jaxB = null;

		WebRowSet wb = null;
		String tmp_ParamName = "";
		String tmp_ParamValue = "";
		String strTmp[];
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.6");
		String qryWardType = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.8");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"BillSetupMstBO.selectIpdGenQuery()");

		try {
			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "3");
			hisDao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			wb = hisDao.executeQry(qryIndex);

			while (wb.next()) {
				tmp_ParamName = wb.getString(1);
				tmp_ParamValue = wb.getString(2);
				if (tmp_ParamName.equals("IPD_THIRD_PARY_BILLING"))
					vo.setIpdGenThirdPartyBilling(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCOUNT"))
					vo.setIpdGenDiscount(tmp_ParamValue);
				else if (tmp_ParamName.equals("IPD_DISCOUNT_CLERK"))
					vo.setIpdGenClerkDiscount(tmp_ParamValue);
				else if (tmp_ParamName.equals("REFUND_PENALTY"))
					vo.setIpdGenPenalty(tmp_ParamValue);
				else if (tmp_ParamName.equals("CREDIT_BILLING"))
					vo.setIpdGenCreditBilling(tmp_ParamValue);
				else if (tmp_ParamName.equals("PART_PAYMENT_DURATION"))
					vo.setIpdGenPayment(tmp_ParamValue);
				else if (tmp_ParamName.equals("IPD_ROUND_OFF"))
					vo.setIpdGenRoundOff(tmp_ParamValue);
				else if (tmp_ParamName.equals("SERVICE_TAX_ON_TOTAL_BILL"))
					vo.setIpdGenServiceTaxOnTotalBill(tmp_ParamValue);
				else if (tmp_ParamName.equals("EXCESS_CREDIT_LIMIT"))
					vo.setIpdGenExcessCreditLimit(tmp_ParamValue);
				else if (tmp_ParamName.equals("IPD_GEN_ADT_PROCESS_TYPE"))
					vo.setIpdGenAdtProcessType(tmp_ParamValue);
				else if (tmp_ParamName.equals("IPD_SERVICE_TAX"))
					vo.setIpdServiceTax(tmp_ParamValue);
				else if (tmp_ParamName.equals("CHECK_OUT_TIME_GENERAL")) {
					strTmp = tmp_ParamValue.replace(':', '#').split("#");
					vo.setIpdGenCheckOutTimeHr(strTmp[0]);
					vo.setIpdGenCheckOutTimeMn(strTmp[1]);
				} else if (tmp_ParamName.equals("CHECK_OUT_TIME_PRIVATE")) {
					strTmp = tmp_ParamValue.replace(':', '#').split("#");
					vo.setIpdGenCheckOutTimePrivateHr(strTmp[0]);
					vo.setIpdGenCheckOutTimePrivateMn(strTmp[1]);
				} else if (tmp_ParamName.equals("FLEXIBLE_TIME"))
					vo.setIpdGenFlexibleTime(tmp_ParamValue);
				else if (tmp_ParamName.equals("CHECK_IN_FLEXIBILITY"))
					vo.setIpdGenFlexibleTimeAdmission(tmp_ParamValue);
				else if (tmp_ParamName.equals("GENERAL_ROOM_TYPE_ID"))
					vo.setStrGeneralRoomType(tmp_ParamValue);
				else if (tmp_ParamName.equals("GENERAL_WARD_TYPE_ID"))
					vo.setStrGeneralWardType(tmp_ParamValue);
				else if (tmp_ParamName.equals("EMG_FREECHARGE_HOUR"))
					vo.setIpdGenServiceFreeTreatmentTime(tmp_ParamValue);
				else if (tmp_ParamName.equals("IPD_FREE_CATEGORY"))
					vo.setIpdFreeCategory(tmp_ParamValue);
				else if (tmp_ParamName.equals("IS_ADVANCE_REQUIRED"))
					vo.setIpdGenAdvanceReq(tmp_ParamValue);
				else if (tmp_ParamName.equals("IPD_RE_OPEN_VALIDITY_DAYS"))
					vo.setIpdGenReOpenValidity(tmp_ParamValue);

			}

			LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
			mapProcedureParam.put("1", vo.getStrHospitalCode());
			vo.setStrGeneralWardTypeValue(HisComboOptions.getOptionsFromQuery(
					qryWardType, mapProcedureParam, vo.getStrGeneralWardType(),
					"0^Select Value", false));
			vo.setStrGeneralRoomTypeValue(HisComboOptions.getOptionsFromQuery(
					qryWardType, mapProcedureParam, vo.getStrGeneralRoomType(),
					"0^Select Value", false));

			vo.setStrIpdFreeCategoryValue(this.getStrIpdFreeCategoryValue(vo
					.getIpdFreeCategory()));
		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setOpdPropValues()",
					e.getMessage());
		}

	}

	public void emgInsert(BillSetupMstVO vo) {

		boolean resVal = this.emgDBInsert(vo);

		if (resVal) {
			// this.emgPropInsert(vo);
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
		}

	}

	public boolean emgDBInsert(BillSetupMstVO vo) {

		boolean retVal = false;
		try {
			BillSetupMstDAO.deleteMultiRowValues("4", "3",
					vo.getStrHospitalCode());
			retVal = BillSetupMstDAO.emgUpdateQuery(vo);

			vo.setStrChargeTypeId("3");

			if (vo.getEmgServiceTax().equals("0")) {

				retVal = BillSetupMstDAO.serviceTaxSetOff(vo);

			} else {

				retVal = BillSetupMstDAO.serviceTaxSetOn(vo);

			}

			vo.setStrMsg("Data Saved Successfully");
		} catch (Exception e) {
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setIpdGen()",
					e.getMessage());
		}

		return retVal;
	}

	public void emgPropInsert(BillSetupMstVO vo) {

		JAXBElement<SetupType> jaxB = this.readXMLDataObject();

		SetupType sut = (SetupType) jaxB.getValue();

		EmergencyType emg = (EmergencyType) sut.getEmergency();

		emg.setThirdPartyBilling(vo.getEmgThirdPartyBilling());
		emg.setDiscount(vo.getEmgDiscount());
		emg.setDiscountClerk(vo.getEmgDiscountClerk());
		emg.setRefundPenalty(vo.getEmgRefundPenalty());
		emg.setRoundOff(vo.getEmgRoundOff());
		emg.setEmgServiceTax(vo.getEmgServiceTax());
		emg.setEmgFreeCategory(vo.getEmgFreeCategory());

		this.writeXMLDataObject(jaxB);
		vo.setStrMsg("Data Saved Successfully");

		emg = null;
		sut = null;
		jaxB = null;

	}

	public void setEmgValues(BillSetupMstVO vo) {

		// try {
		// vo.setEmgGroupName(BillSetupMstDAO.getMultiRowValue("4", "3", 2,
		// vo.getStrHospitalCode()));
		//
		// vo.setEmgTariffName(BillSetupMstDAO.getMultiRowValue("4", "3",
		// 3, vo.getStrHospitalCode()));
		// vo.setEmgUnit(BillSetupMstDAO.getMultiRowValue("4", "3", 4,
		// vo.getStrHospitalCode()));
		// } catch (Exception e) {
		// new HisException("Bill Setup", "BOBillSetup.setEmgValues()", e
		// .getMessage());
		// }

		// JAXBElement<SetupType> jaxB = this.readXMLDataObject();
		//
		// SetupType sut = (SetupType) jaxB.getValue();
		//
		// EmergencyType emg = (EmergencyType) sut.getEmergency();
		//
		// vo.setEmgThirdPartyBilling(emg.getThirdPartyBilling());
		// vo.setEmgDiscount(emg.getDiscount());
		// vo.setEmgDiscountClerk(emg.getDiscountClerk());
		// vo.setEmgRefundPenalty(emg.getRefundPenalty());
		// vo.setEmgRoundOff(emg.getRoundOff());
		// vo.setEmgServiceTax(emg.getEmgServiceTax());
		// vo.setEmgFreeCategory(emg.getEmgFreeCategory());
		//
		// emg = null;
		// sut = null;
		// jaxB = null;

		WebRowSet wb = null;
		String tmp_ParamName = "";
		String tmp_ParamValue = "";
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.billsetup.6");
		HisDAO hisDao = new HisDAO("Bill Setup",
				"BillSetupMstBO.selectEmgQuery()");

		try {

			vo.setEmgGroupName(BillSetupMstDAO.getMultiRowValue("4", "3", 2,
					vo.getStrHospitalCode()));

			vo.setEmgTariffName(BillSetupMstDAO.getMultiRowValue("4", "3", 3,
					vo.getStrHospitalCode()));
			vo.setEmgUnit(BillSetupMstDAO.getMultiRowValue("4", "3", 4,
					vo.getStrHospitalCode()));

			int qryIndex = hisDao.setQuery(qry);
			hisDao.setQryValue(qryIndex, 1, "4");
			hisDao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			wb = hisDao.executeQry(qryIndex);

			while (wb.next()) {
				tmp_ParamName = wb.getString(1);
				tmp_ParamValue = wb.getString(2);
				if (tmp_ParamName.equals("EMG_THIRD_PARY_BILLING"))
					vo.setEmgThirdPartyBilling(tmp_ParamValue);
				else if (tmp_ParamName.equals("DISCOUNT"))
					vo.setEmgDiscount(tmp_ParamValue);
				else if (tmp_ParamName.equals("EMG_DISCOUNT_CLERK"))
					vo.setEmgDiscountClerk(tmp_ParamValue);
				else if (tmp_ParamName.equals("REFUND_PENALTY"))
					vo.setEmgRefundPenalty(tmp_ParamValue);
				else if (tmp_ParamName.equals("EMG_ROUND_OFF"))
					vo.setEmgRoundOff(tmp_ParamValue);
				else if (tmp_ParamName.equals("EMG_SERVICE_TAX"))
					vo.setEmgServiceTax(tmp_ParamValue);
				else if (tmp_ParamName.equals("EMG_FREE_CATEGORY"))
					vo.setEmgFreeCategory(tmp_ParamValue);

			}

			vo.setStrEmgFreeCategoryValue(this.getStrEmgFreeCategoryValue(vo
					.getEmgFreeCategory()));
		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setOpdPropValues()",
					e.getMessage());
		}

	}

	public void ipdComplChargeInsert(BillSetupMstVO vo) {

		try {
			BillSetupMstDAO.deleteMultiRowValues("2", "2",
					vo.getStrHospitalCode());
			BillSetupMstDAO.deleteMultiRowValues("3", "2",
					vo.getStrHospitalCode());
			// BillSetupMstDAO.deleteMultiRowValues("3", "2" ,
			// vo.getStrHospitalCode());

			BillSetupMstDAO.ipdComplChargeUpdateQuery(vo);
			vo.setStrMsg("Data Saved Successfully");
			// Reload Cache
			BillConfigUtil bcu = new BillConfigUtil(vo.getStrHospitalCode());
			bcu.reloadCacheBillVObj(vo.getStrHospitalCode());
		} catch (Exception e) {
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup",
					"BOBillSetup.ipdComplChargeInsert()", e.getMessage());
		}

	}

	public void setIpdComplCharge(BillSetupMstVO vo) {

		try {

			vo.setSecWardName(BillSetupMstDAO.getMultiRowValue("2", "2", 1,
					vo.getStrHospitalCode()));
			vo.setSecGroupName(BillSetupMstDAO.getMultiRowValue("2", "2", 3,
					vo.getStrHospitalCode()));
			vo.setSecTariffName(BillSetupMstDAO.getMultiRowValue("2", "2", 4,
					vo.getStrHospitalCode()));

			vo.setThirdWardName(BillSetupMstDAO.getMultiRowValue("3", "2", 1,
					vo.getStrHospitalCode()));
			vo.setThirdGroupName(BillSetupMstDAO.getMultiRowValue("3", "2", 3,
					vo.getStrHospitalCode()));
			vo.setThirdTariffName(BillSetupMstDAO.getMultiRowValue("3", "2", 4,
					vo.getStrHospitalCode()));

		} catch (Exception e) {
			new HisException("Bill Setup", "BOBillSetup.setIpdComplCharge()",
					e.getMessage());
		}

	}

	/**
	 * common method for creating a XML Root Elements Data Object
	 * 
	 * @return JAXBElement Object
	 */
	@SuppressWarnings("unchecked")
	public JAXBElement<SetupType> readXMLDataObject() {

		JAXBElement<SetupType> jaxB = null;

		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance("billing.setup");

			Unmarshaller u = jc.createUnmarshaller();

			jaxB = (JAXBElement<SetupType>) u.unmarshal(new FileInputStream(
					this.getPath()));

		} catch (JAXBException e) {
			new HisException("Bill Setup", "BOBillSetup.readXMLDataObject()",
					e.getMessage());
		} catch (FileNotFoundException e) {
			new HisException("Bill Setup", "BOBillSetup.readXMLDataObject()",
					e.getMessage());
		}

		return jaxB;

	}

	/**
	 * retrieves JAXBElement Object and Updates the XML file
	 * 
	 * @param jaxB
	 *            - JAXBElement Object
	 */
	public void writeXMLDataObject(JAXBElement<SetupType> jaxB) {

		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance("billing.setup");

			Marshaller m = jc.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(jaxB, new FileWriter(new File(this.getPath())));

		} catch (JAXBException e) {
			new HisException("Bill Setup", "BOBillSetup.writeXMLDataObject()",
					e.getMessage());
		} catch (IOException e) {
			new HisException("Bill Setup", "BOBillSetup.writeXMLDataObject()",
					e.getMessage());
		}

	}

	public String getStrOpdFreeCategoryValue(String opdFreeCategory) {

		String strOpdFreeCategoryValue = null;
		HisUtil hisUtil = null;
		try {

			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrOpdFreeCategoryValue");

			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat").replace("?",
					BillConfigUtil.SUPER_HOSPITAL_CODE);
			strOpdFreeCategoryValue = hisUtil.getOptionValue(qry,
					opdFreeCategory, "0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrOpdFreeCategoryValue",
					e.getMessage());
		} finally {

			hisUtil = null;
		}

		return strOpdFreeCategoryValue;
	}

	public String getStrIpdFreeCategoryValue(String ipdFreeCategory) {

		String strIpdFreeCategoryValue = null;
		HisUtil hisUtil = null;
		try {

			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrIpdFreeCategoryValue");

			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat").replace("?",
					BillConfigUtil.SUPER_HOSPITAL_CODE);
			strIpdFreeCategoryValue = hisUtil.getOptionValue(qry,
					ipdFreeCategory, "0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrIpdFreeCategoryValue",
					e.getMessage());
		} finally {

			hisUtil = null;
		}

		return strIpdFreeCategoryValue;
	}

	public String getStrEmgFreeCategoryValue(String emgFreeCategory) {

		String strEmgFreeCategoryValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrEmgFreeCategoryValue");

			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat").replace("?",
					BillConfigUtil.SUPER_HOSPITAL_CODE);
			strEmgFreeCategoryValue = hisUtil.getOptionValue(qry,
					emgFreeCategory, "0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrEmgFreeCategoryValue",
					e.getMessage());
		} finally {

			hisUtil = null;
		}

		return strEmgFreeCategoryValue;
	}

	public String getStrPaidCategoryCombo(String paidCategory, String hospCode) {

		String strPaidCategoryValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrEmgFreeCategoryValue");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat0").replace("?", hospCode);
			if (paidCategory != null)
				strPaidCategoryValue = hisUtil.getOptionValue(qry,
						paidCategory, "0^Select Value");
			else
				strPaidCategoryValue = hisUtil.getOptionValue(qry, "0",
						"0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrEmgFreeCategoryValue",
					e.getMessage());
		} finally {

			hisUtil = null;
		}

		return strPaidCategoryValue;
	}

	public String getStrArogyaShreeCategoryCombo(String arogyashreeCategory,
			String hospCode) {

		String strArogyaShreeCategoryValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrEmgFreeCategoryValue");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat1").replace("?", hospCode);
			if (arogyashreeCategory != null)
				strArogyaShreeCategoryValue = hisUtil.getOptionValue(qry,
						arogyashreeCategory, "0^Select Value");
			else
				strArogyaShreeCategoryValue = hisUtil.getOptionValue(qry, "0",
						"0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrEmgFreeCategoryValue",
					e.getMessage());
		} finally {

			hisUtil = null;
		}

		return strArogyaShreeCategoryValue;
	}

	public String getStrArogyaShreeTSCategoryCombo(
			String arogyashreeTSCategory, String hospCode) {
		String strArogyaShreeTSCategoryValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("BILLING", "BillSetupMstBO");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat1").replace("?", hospCode);
			if (arogyashreeTSCategory != null)
				strArogyaShreeTSCategoryValue = hisUtil.getOptionValue(qry,
						arogyashreeTSCategory, "0^Select Value");
			else
				strArogyaShreeTSCategoryValue = hisUtil.getOptionValue(qry,
						"0", "0^Select Value");
		} catch (Exception e) {
			new HisException("BillING",
					"BillSetupMstBO.getStrArogyaShreeTSCategoryCombo",
					e.getMessage());
		} finally {

			hisUtil = null;
		}
		return strArogyaShreeTSCategoryValue;
	}

	public String getStrOSTFCategoryCombo(String OSTFCategory, String hospCode) {
		String strOSTFCategoryValue = null;

		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("BILLING", "BillSetupMstBO");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat4").replace("?", hospCode);
			if (OSTFCategory != null)
				strOSTFCategoryValue = hisUtil.getOptionValue(qry,
						OSTFCategory, "0^Select Value");
			else
				strOSTFCategoryValue = hisUtil.getOptionValue(qry, "0",
						"0^Select Value");
		} catch (Exception e) {
			new HisException("BillING",
					"BillSetupMstBO.getStrOSTFCategoryCombo", e.getMessage());
		} finally {

			hisUtil = null;
		}
		return strOSTFCategoryValue;
	}

	public String getNiramayaCombo(String niramaya, String hospCode) {
		String niramayaValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("BILLING", "BillSetupMstBO");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat5").replace("?", hospCode);
			if (niramaya != null)
				niramayaValue = hisUtil.getOptionValue(qry, niramaya,
						"0^Select Value");
			else
				niramayaValue = hisUtil.getOptionValue(qry, "0",
						"0^Select Value");
		} catch (Exception e) {
			new HisException("BillING", "BillSetupMstBO.getNiramayaCombo",
					e.getMessage());
		} finally {

			hisUtil = null;
		}
		return niramayaValue;
	}

	public String getStrGenCategoryCombo(String genCategory, String hospCode) {

		String strGenCategoryValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrEmgFreeCategoryValue");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat2").replace("?", hospCode);
			if (genCategory != null)
				strGenCategoryValue = hisUtil.getOptionValue(qry, genCategory,
						"0^Select Value");
			else
				strGenCategoryValue = hisUtil.getOptionValue(qry, "0",
						"0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrEmgFreeCategoryValue",
					e.getMessage());
		} finally {
			hisUtil = null;
		}
		return strGenCategoryValue;
	}

	public String getStrCGSHCategoryCombo(String cgshCategory, String hospCode) {

		String strCGSHCategoryValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrEmgFreeCategoryValue");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat2").replace("?", hospCode);
			if (cgshCategory != null)
				strCGSHCategoryValue = hisUtil.getOptionValue(qry,
						cgshCategory, "0^Select Value");
			else
				strCGSHCategoryValue = hisUtil.getOptionValue(qry, "0",
						"0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrEmgFreeCategoryValue",
					e.getMessage());
		} finally {
			hisUtil = null;
		}
		return strCGSHCategoryValue;
	}

	public String getStrWSHCategoryCombo(String wshCategory, String hospCode) {

		String strWSHCategoryValue = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("Bill Setup",
					"BillSetupMstVO.getStrEmgFreeCategoryValue");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.treatcat3").replace("?", hospCode);
			if (wshCategory != null)
				strWSHCategoryValue = hisUtil.getOptionValue(qry, wshCategory,
						"0^Select Value");
			else
				strWSHCategoryValue = hisUtil.getOptionValue(qry, "0",
						"0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getStrEmgFreeCategoryValue",
					e.getMessage());
		} finally {
			hisUtil = null;
		}
		return strWSHCategoryValue;
	}

	public WebRowSet getConsumableChargesTariffCombo(BillSetupMstVO vo) {

		WebRowSet wb = null;

		try {
			wb = BillSetupMstDAO.getConsumableChargesTariffCombo(vo);
		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstBO.getConsumableChargesTariffCombo",
					e.getMessage());
		}

		return wb;

	}
}
