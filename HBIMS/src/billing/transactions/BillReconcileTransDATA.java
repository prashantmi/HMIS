package billing.transactions;

import java.io.IOException;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BillReconcileTransDATA {

	/*
	 * public static void initValue(HttpServletRequest request,
	 * BillReconcileTransFB formBean) {
	 * 
	 * BillReconcileTransVO vo = new BillReconcileTransVO();
	 * 
	 * String strChk = request.getParameter("chk"); System.out.println("chk = " +
	 * strChk); vo.setStrChk(strChk);
	 * 
	 * try {
	 * 
	 * formBean.setStrCrNo(strChk); } catch (Exception e) {
	 * 
	 * new HisException("BillReconcileTransDATA", "BillReconcileTransDATA",
	 * "BillReconcileTransDATA.initClientDetail()-->" + e.getMessage()); }
	 * finally { if (vo != null) vo = null; } }
	 */
	/**
	 * page is loaded, used to populate bill detail, called from body on load
	 */
	public static void getBillDetailsList(BillReconcileTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		BillReconcileTransBO bo = null;
		BillReconcileTransVO vo = null;

		String strCrNo = (String) request.getParameter("modName");

		try {
			vo = new BillReconcileTransVO();
			bo = new BillReconcileTransBO();

			vo.setStrCrNo(strCrNo); // CR No. is Here
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.getBillDetails(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

			String temp = BillReconcileTransHLP.getBillDetails(vo);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {

			HisException eObj = new HisException("BillReconcile Transaction",
					"BillReconcileTransDATA",
					"BillReconcileTransDATA.getBillDetailsList()-->"
							+ e.getMessage());
			eObj = null;

			try {
				response.getWriter().print(
						"ERROR####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (IOException e1) {

				new HisException("BillReconcile Transaction",
						"BillReconcileTransDATA",
						"BillReconcileTransDATA.getBillDetailsList()-->"
								+ e1.getMessage());
			}

		} finally {

			vo = null;
			bo = null;
		}
	}

	/**
	 * Called when Go button is clicked This will populate reconcile by/reason
	 * and discount by/reason value
	 * 
	 * @param formBean
	 */
	public static void initReconcilation(BillReconcileTransFB formBean) {

		BillReconcileTransVO vo = null;
		BillReconcileTransBO bo = null;
		HisUtil util = null;

		String temp = "";
		String strPatView = "";

		try {

			vo = new BillReconcileTransVO();
			bo = new BillReconcileTransBO();
			util = new HisUtil("Billing Transaction", "BillReconcileTrans");
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			// populate reconcile reason/by and discount by/discount reason

			// get patient details
			strPatView = PatientDtlHLP.patientWithAdmissionDtl(formBean
					.getStrCrNo(), formBean.getStrHospitalCode(), false);

			formBean.setStrPatientDetailsView(strPatView);

			bo.initReconcilation(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

			formBean.setStrCtDate(util.getASDate("dd-MMM-yyyy"));

			if (vo.getStrDiscAppByList() != null
					&& vo.getStrDiscAppByList().size() > 0) {

				vo.getStrDiscAppByList().beforeFirst();

				temp = util.getOptionValue(vo.getStrDiscAppByList(), "0",
						"0^Select Value", false);
			} else {
				temp = "<option value='0'>Select Value</option>";
			}

			formBean.setStrOfflineDiscountApprovedByDetails(temp);

			// set discount reason
			temp = "";
			if (vo.getStrDisByRmkList() != null
					&& vo.getStrDisByRmkList().size() != 0) {
				vo.getStrDisByRmkList().beforeFirst();

				temp = util.getOptionValue(vo.getStrDisByRmkList(), "", "",
						false);
			} else {
				temp += "<option value='0'>Others</option>";
			}

			formBean.setStrOfflineDiscountRemarksDetails(temp);

			// set reconciled by
			if (vo.getStrReconlByList() != null
					&& vo.getStrReconlByList().size() > 0) {
				vo.getStrReconlByList().beforeFirst();

				temp = util.getOptionValue(vo.getStrReconlByList(), "0",
						"0^Select Value", false);

			} else {
				temp = "<option value='0'>Select Value</option>";
			}
			formBean.setStrReconcilationByValues(temp);

			// set reconcile reason
			temp = "";

			if (vo.getStrReconlByRmkList() != null
					&& vo.getStrReconlByRmkList().size() > 0) {
				vo.getStrReconlByRmkList().beforeFirst();

				temp = util.getOptionValue(vo.getStrReconlByRmkList(), "", "",
						false);
				temp = temp + "<option value='0'>Others</option>";
			} else {
				temp = "<option value='0'>Others</option>";
			}

			formBean.setStrReconcilationReasonValues(temp);

		} catch (Exception e) {

			HisException eObj = new HisException("BillReconcile Transaction",
					"BillReconcileTransDATA",
					"BillReconcileTransDATA.initReconcilation()-->"
							+ e.getMessage());
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;

			vo = null;
			util = null;
		}
	}

	/**
	 * when bill is selected from bill detail option
	 * 
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void getTariffOrFinalSettlementDetails(
			BillReconcileTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		String strTempBServiceId[] = null;
		HisUtil util = null;
		BillReconcileTransVO vo = null;
		BillReconcileTransBO bo = null;
		try {
			String strValmode = (String) request.getParameter("optionVal");
			strTempBServiceId = strValmode.replace("^", "#").split("#");

			vo = new BillReconcileTransVO();
			bo = new BillReconcileTransBO();

			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			vo.setStrValmode(strValmode);
			bo.getTariffOrFinalSettlementDetails(vo);

			vo.setStrHospitalServices(strTempBServiceId[1]);

			bo.initGroupAndDiscountDetails(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

			String tariffDetails = null;
			String temp = "";
			String temp1 = "";

			if (Integer.parseInt(strTempBServiceId[3]) < 13) {

				tariffDetails = BillReconcileTransHLP.getTariffDtlsList(vo);

			} else if (Integer.parseInt(strTempBServiceId[3]) == 21) {

				tariffDetails = BillReconcileTransHLP
						.getFinalSettlementDtlsList(vo);
			}

			util = new HisUtil("Billing Transaction", "Bill Reconciliation");

			if (vo.getGroupList() != null && vo.getGroupList().size() > 0) {
				temp = util.getOptionValue(vo.getGroupList(), "0",
						"0^All Group", false);
			} else {
				temp = "<option value='0'>All Group</option>";
			}

			temp1 = "";
			tariffDetails = tariffDetails + "@" + temp + "@" + temp1;

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(tariffDetails);

		} catch (Exception e) {

			HisException eObj = new HisException("BillReconcile Transaction",
					"BillReconcileTransDATA",
					"BillReconcileTransDATA.getTariffOrFinalSettlementDetails()-->"
							+ e.getMessage());
			eObj = null;

			try {
				response.getWriter().print(
						"ERROR####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (IOException e1) {

				new HisException("BillReconcile Transaction",
						"BillReconcileTransDATA",
						"BillReconcileTransDATA.getTariffOrFinalSettlementDetails()-->"
								+ e1.getMessage());
			}

		} finally {

			vo = null;
			bo = null;
			util = null;
		}

	}

	/**
	 * To get group service details in final settlement (consolidated details)
	 * section
	 * 
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void finalSettlementTariffDetails(
			BillReconcileTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		String tariffDetails = null;
		String strValmode = "";
		String groupId = "";
		String index = "";

		BillReconcileTransVO vo = null;
		BillReconcileTransBO bo = null;

		try {
			strValmode = (String) request.getParameter("billNo"); // bill no
			groupId = (String) request.getParameter("groupId"); // group id
			// System.out.println("grp id in data---->"+groupId);
			index = (String) request.getParameter("index"); // index
			// corresponding to
			// group

			vo = new BillReconcileTransVO();
			bo = new BillReconcileTransBO();

			vo.setStrValmode(strValmode);
			vo.setStrGroup(groupId);
			vo.setStrValmode(strValmode);
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString()); // Hispital Code is Here

			// to get group wise details
			bo.getFinalSettlermentDtls(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

			tariffDetails = BillReconcileTransHLP.getFinalSettlementDtlsView(
					vo, index);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(tariffDetails);

		} catch (Exception e) {
			// String msgStr = e.getMessage();
			HisException eObj = new HisException("BillReconcile Transaction",
					"BillReconcileTransDATA",
					"BillReconcileTransDATA.fsetServDtl()-->" + e.getMessage());
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;

			vo = null;
		}

	}

	public static String popUpShw(HttpServletRequest request) {
		String res = "";
		String strmsgText = "";
		String strValmode = "";
		String strValModeType = "";
		BillReconcileTransVO vo = null;
		try {
			vo = new BillReconcileTransVO();
			strValmode = (String) request.getParameter("modName");
			strValModeType = (String) request.getParameter("modeType");
			vo.setStrValmode(strValmode);
			res = BillReconcileTransHLP.popUpShw(vo, strValModeType);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			strmsgText = "billing.transactions.RefundApprovalTransDATA.popUpShw(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"RefundApprovalTransaction->RefundApprovalTransDATA->popUpShw()",
					strmsgText);
			strmsgText = "ERROR###Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ";
			eObj = null;
		} finally {

			vo = null;
		}

		if (strmsgText.equals("")) {
			return res;
		} else {
			return strmsgText;
		}
	}

	/**
	 * returns populated Group Combo box (Option List) based on Hospital Service
	 * and Package Flag.
	 * 
	 * @param request
	 * @param response
	 */
	public static void getGroupDetails(HttpServletRequest request,
			HttpServletResponse response) {
		BillReconcileTransBO bo = new BillReconcileTransBO();
		BillReconcileTransVO voObj = new BillReconcileTransVO();

		try {
			String strHospitalService = request.getParameter("hService");
			String strIsPackWiseGroup = request.getParameter("withPack");
			if (strHospitalService == null)
				strHospitalService = "0";
			// please set the corresponding values below. currently hardcodded
			// values have been setted
			// voObj.setStrHospitalCode("108");
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString()); // Hispital Code is Here
			voObj.setStrHospitalServices(strHospitalService);
			voObj.setStrIsPackageWise(strIsPackWiseGroup);
			bo.getGroupDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			HisUtil util = new HisUtil("Billing Transaction",
					"CashCollectionTrans");
			String temp = "<option value='0'>All Group</option>";
			if (voObj.getGroupList().size() != 0) {
				temp = util.getOptionValue(voObj.getGroupList(), "0",
						"0^All Group", false);
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} catch (Exception e) {
			new HisException("Billing Tranasction : Bill Reconcile Trans ",
					"BillReconcileTransDATA.getGroupDetails()", e.getMessage());
		} finally {

			bo = null;

			voObj = null;
		}
	}

	/**
	 * returns Populated Tariff Drop Down Combo box (Option List) based on
	 * Selected Group.
	 * 
	 * @param request
	 * @param response
	 */
	public static void getTariffDetails(HttpServletRequest request,
			HttpServletResponse response) {

		BillReconcileTransBO bo = new BillReconcileTransBO();
		BillReconcileTransVO voObj = new BillReconcileTransVO();
		HisUtil util = null;
		try {

			util = new HisUtil("", "");

			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null)
				strWardCode = "0";

			// please set the corresponding values below. currently hardcodded
			// values have been setted
			// voObj.setStrHospitalCode("108");
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString()); // Hispital Code is Here
			voObj.setStrGroup(strGroupCode);
			voObj.setStrHospitalServices(strHospitalService);

			voObj.setStrCategoryCode(strTreatmentCategory);
			voObj.setStrWardCode(strWardCode);

			bo.getTariffDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			String temp = "";
			if (voObj.getTariffList().size() != 0) {

				temp = util.getDropDown(voObj.getTariffList(), 1, 5, false);
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} catch (Exception e) {
			new HisException("Billing Tranasction : Bill Reconcile Trans ",
					"BillReconcileTransDATA.getGroupDetails()", e.getMessage());
		} finally {

			bo = null;

			voObj = null;

		}
	}

	/**
	 * Used to get unit data based on tariff id
	 * 
	 * @param request
	 * @param response
	 */
	public static void getOffLineTariffUnitDetails(HttpServletRequest request,
			HttpServletResponse response) {

		HisUtil util = null;
		BillReconcileTransBO bo = new BillReconcileTransBO();
		BillReconcileTransVO voObj = new BillReconcileTransVO();

		try {

			String strRowIndex = request.getParameter("rowIndex");
			String strUnitId = request.getParameter("unitId");
			String strUnitBaseVal = request.getParameter("baseValue");

			// System.out.println("unit Id : "+strUnitId);
			// System.out.println("unit base val : "+strUnitBaseVal);

			if (strRowIndex == null)
				strRowIndex = "0";
			if (strUnitId == null)
				strUnitId = "0";
			if (strUnitBaseVal == null)
				strUnitBaseVal = "0";

			voObj.setStrTariffUnitTempId(strUnitId);
			// voObj.setStrHospitalCode("108");
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString()); // Hispital Code is Here

			bo.getOffLineTariffUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("BillReconcileTransDATA",
					"getOffLineTariffUnitDetails");

			String temp = "<option id='0'>Select Value</option>";

			if (voObj.getTariffUnitList() != null
					&& voObj.getTariffUnitList().size() > 0) {
				temp = util.getOptionValue(voObj.getTariffUnitList(), strUnitId
						+ "^" + strUnitBaseVal, "", false);
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strRowIndex + "@" + temp);

		} catch (Exception e) {
			new HisException("BillReconcileTransDATA",
					"getOffLineTariffUnitDetails", e.getMessage());
		} finally {

			bo = null;

			voObj = null;
			util = null;
		}
	}

	/**
	 * @param form
	 * @param request
	 * @param response
	 */
	public static void insertData(BillReconcileTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = null;
		BillReconcileTransBO bo = null;
		BillReconcileTransVO vo = null;

		int nLen = 0;

		try {
			bo = new BillReconcileTransBO();
			vo = new BillReconcileTransVO();

			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			if (request.getSession().getAttribute("USER_LEVEL") != null) {

				formBean.setStrUserLevel(request.getSession().getAttribute(
						"USER_LEVEL").toString());

			} else {
				formBean.setStrUserLevel("1");
			}

			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			vo.setStrCrNo(formBean.getStrCrNo());

			// Bill Details
			String strBillDetailsValTemp[] = formBean.getStrBillNoVal()
					.replace("^", "#").split("#");

			vo.setStrBillNo(strBillDetailsValTemp[0]);
			vo.setStrChargeTypeId(strBillDetailsValTemp[1]);
			vo.setStrTransactionType(strBillDetailsValTemp[2]);
			vo.setStrBserviceId(strBillDetailsValTemp[3]);
			vo.setStrPatAccountNo(strBillDetailsValTemp[4]);
			vo.setStrGblRequestNo(strBillDetailsValTemp[5]);
			vo.setStrWardCode(strBillDetailsValTemp[7]);
			vo.setStrReceiptNo(strBillDetailsValTemp[8]);
			vo.setStrIsBill(strBillDetailsValTemp[9]);
			vo.setStrIsOnline(strBillDetailsValTemp[10]);
			vo.setStrAdmissionNo(strBillDetailsValTemp[11]);
			vo.setStrEpisodeCode(strBillDetailsValTemp[12]);
			vo.setStrCatCode(strBillDetailsValTemp[13]);
			vo.setStrDepartmentCode(strBillDetailsValTemp[14]);
			vo.setStrIpdChargeTypeId(strBillDetailsValTemp[15]);
			vo.setStrServiceId(strBillDetailsValTemp[16]);

			// Refund Tariff's
			if (formBean.getStrBillTariffVal() != null) {
				nLen = formBean.getStrBillTariffVal().length;
			}
			String[] strTempTariffId = new String[nLen];
			String[] strTempQtyUnitId = new String[nLen];
			String[] strTempTariffRate = new String[nLen];
			String[] strTempRateUnit = new String[nLen];
			String[] strTempGroupId = new String[nLen];
			String[] strTempActualTariffRate = new String[nLen];
			String[] strTempServiceTax = new String[nLen];
			String[] strTempDiscountType = new String[nLen];
			String[] strTempDiscountUnit = new String[nLen];
			String[] strTempGTariffId = new String[nLen];
			String[] strTempDiscountApprovalId = new String[nLen];
			String[] strTempBaseUnitVal = new String[nLen];
			String[] strTempIsPackage = new String[nLen];
			String[] strTempNetAmount = new String[nLen];
			String[] strTempPenalty = new String[nLen];
			String[] strTempSServiceId = new String[nLen];
			String[] strTempBalanceQty = new String[nLen];
			String[] strTempRemainingQty = new String[nLen];
			String[] strTempTariffReceiptNo = new String[nLen];
			String[] strTempAccReqNo = new String[nLen];
			// System.out.println("bill Tariff Value
			// :"+formBean.getStrBillTariffVal().length);

			for (int i = 0; i < nLen; i++) {

				String strTempBillTariffVal[] = formBean.getStrBillTariffVal()[i]
						.replace("^", "#").split("#");

				if (vo.getStrBserviceId().equals("21")) {

					strTempAccReqNo[i] = strTempBillTariffVal[0];
					strTempTariffId[i] = strTempBillTariffVal[3];
					strTempQtyUnitId[i] = strTempBillTariffVal[14];
					strTempTariffRate[i] = strTempBillTariffVal[12];
					strTempRateUnit[i] = strTempBillTariffVal[13];
					strTempGroupId[i] = strTempBillTariffVal[22];
					strTempActualTariffRate[i] = strTempBillTariffVal[16];
					strTempServiceTax[i] = strTempBillTariffVal[20];
					strTempDiscountUnit[i] = strTempBillTariffVal[7];
					strTempDiscountType[i] = strTempBillTariffVal[8];
					strTempGTariffId[i] = strTempBillTariffVal[9];
					strTempDiscountApprovalId[i] = strTempBillTariffVal[11];
					strTempBaseUnitVal[i] = strTempBillTariffVal[19];
					strTempIsPackage[i] = strTempBillTariffVal[17];
					strTempNetAmount[i] = "0";
					strTempPenalty[i] = "0";
					strTempSServiceId[i] = strTempBillTariffVal[6];
					strTempBalanceQty[i] = strTempBillTariffVal[22].replace(
							" ", "#").split("#")[0];
					strTempRemainingQty[i] = strTempBillTariffVal[18].replace(
							"*", "#").split("#")[0];
					strTempTariffReceiptNo[i] = strTempBillTariffVal[15];

					if (strTempDiscountType[i].length() < 1) {

						strTempDiscountUnit[i] = "0";
						strTempDiscountType[i] = "1";
					}

				} else {

					strTempAccReqNo[i] = "0";
					strTempTariffId[i] = strTempBillTariffVal[0];
					strTempRemainingQty[i] = strTempBillTariffVal[1].replace(
							"*", "#").split("#")[0];
					strTempQtyUnitId[i] = strTempBillTariffVal[2];
					strTempTariffRate[i] = strTempBillTariffVal[3];
					strTempRateUnit[i] = strTempBillTariffVal[4];
					strTempGroupId[i] = strTempBillTariffVal[5];
					strTempActualTariffRate[i] = strTempBillTariffVal[6];
					strTempServiceTax[i] = strTempBillTariffVal[7];
					strTempDiscountUnit[i] = strTempBillTariffVal[8];
					strTempDiscountType[i] = strTempBillTariffVal[9];
					strTempGTariffId[i] = strTempBillTariffVal[10];
					strTempDiscountApprovalId[i] = strTempBillTariffVal[11];
					strTempBaseUnitVal[i] = strTempBillTariffVal[13];
					strTempIsPackage[i] = strTempBillTariffVal[14];
					strTempNetAmount[i] = strTempBillTariffVal[15];
					strTempPenalty[i] = strTempBillTariffVal[16];
					strTempSServiceId[i] = strTempBillTariffVal[17];
					strTempBalanceQty[i] = strTempBillTariffVal[1].replace("*",
							"#").split("#")[0];
					strTempTariffReceiptNo[i] = strTempBillTariffVal[18];

					if (strTempDiscountType[i].length() < 1) {

						strTempDiscountUnit[i] = "0";
						strTempDiscountType[i] = "1";
					}

				}

			}

			vo.setStrAccReqNo(strTempAccReqNo);
			vo.setStrTariffId(strTempTariffId);
			vo.setStrQtyUnitId(strTempQtyUnitId);
			vo.setStrTariffRate(strTempTariffRate);
			vo.setStrRateUnit(strTempRateUnit);
			vo.setStrGroupId(strTempGroupId);
			vo.setStrActualTariffRate(strTempActualTariffRate);
			vo.setStrServiceTax(strTempServiceTax);
			vo.setStrDiscountUnit(strTempDiscountUnit);
			vo.setStrDiscountType(strTempDiscountType);
			vo.setStrGTariffId(strTempGTariffId);
			vo.setStrDiscountApprovalId(strTempDiscountApprovalId);
			vo.setStrBaseUnitVal(strTempBaseUnitVal);
			vo.setStrIsPackage(strTempIsPackage);
			vo.setStrNetAmount(strTempNetAmount);
			vo.setStrPenalty(strTempPenalty);
			vo.setStrSServiceId(strTempSServiceId);
			vo.setStrBalanceQty(strTempBalanceQty);
			vo.setStrTariffReceiptNo(strTempTariffReceiptNo);
			vo.setStrRemainingQty(strTempRemainingQty);

			vo.setStrBillTariffRefund(formBean.getStrBillTariffRefund());
			vo.setStrBillTariffUnit(formBean.getStrBillTariffUnit());

			// New Tariffs

			// if(formBean.getStrOfflineTariffDetailsHidden() != null)
			// for (int j = 0; j <
			// formBean.getStrOfflineTariffDetailsHidden().length; j++) {

			// System.out.println("StrOfflineTariffDetailsHidden()"+formBean.getStrOfflineTariffDetailsHidden()[j]);
			// System.out.println("OfflineTariffDiscountConfigDetails()"+formBean.getStrOfflineTariffDiscountConfigDetails()[j]);

			// }

			vo.setStrOfflineTariffDetailsHidden(formBean
					.getStrOfflineTariffDetailsHidden());
			vo.setStrOfflineTariffDiscountConfigDetails(formBean
					.getStrOfflineTariffDiscountConfigDetails());

			vo.setStrOfflineTariffName(formBean.getStrOfflineTariffName());
			vo.setStrOfflineTariffDiscount(formBean
					.getStrOfflineTariffDiscount());
			vo.setStrOfflineTariffRateUnit(formBean
					.getStrOfflineTariffRateUnit());
			vo.setStrOfflineTariffQty(formBean.getStrOfflineTariffQty());
			vo.setStrOfflineTariffUnit(formBean.getStrOfflineTariffUnit());
			vo.setStrOfflineTariffServiceTax(formBean
					.getStrOfflineTariffServiceTax());
			vo.setStrOfflineTariffNetAmount(formBean
					.getStrOfflineTariffNetAmount());

			vo.setStrReconcilationBy(formBean.getStrReconcilationBy());
			vo.setStrReconcilationReason(formBean.getStrReconcilationReason());
			vo.setStrReconcilationReasonText(formBean
					.getStrReconcilationReasonText());

			bo.insertData(vo);

			if (vo.getStrMsgType().equals("1")) {

				formBean.setStrCrNo("");

				formBean.setStrErrMsg(vo.getStrMsgString());
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrCrNo("");
				formBean.setStrMsg(vo.getStrMsgString());
			}
		} catch (Exception e) {

			strmsgText = "billing.transactions.BillReconcileTransDATA.insertData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillReconcileTransaction->insertData()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;

		} finally {

			vo = null;

			bo = null;

		}
	}

	

	/**
	 * writes tariff Details based on
	 * Tariff Code as Ajax Response.
	 * 
	 * @param request
	 * @param response
	 */
	public static void getTariffCodeDetails(HttpServletRequest request,
			HttpServletResponse response) {

		BillReconcileTransBO bo = null;
		BillReconcileTransVO voObj = null;
		
		try {
			bo = new BillReconcileTransBO();
			voObj = new BillReconcileTransVO();

			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward");
			String strTariffCode = request.getParameter("tariffCode");

			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";

			voObj.setStrHospitalCode(request.getSession().getAttribute(
			"HOSPITAL_CODE").toString());
			voObj.setStrGroup(strGroupCode);
			voObj.setStrHospitalServices(strHospitalService);
			voObj.setStrCategoryCode(strTreatmentCategory);
			voObj.setStrWardCode(strWardCode);
			voObj.setStrTariffCode(strTariffCode);
			
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.getTariffCodeDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			String temp = "";

			if (voObj.getTariffList().size() != 0) {
				
				if(voObj.getTariffList().next()){
					
					temp =  "1@@"+voObj.getTariffList().getString(2)+"@@"+voObj.getTariffList().getString(1);
				}
							
			}else{
				
					temp = "0@@_@@0";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"BillReconcileTransDATA->getTariffCodeDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"BillReconcileTransDATA->getTariffCodeDetails()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;
		}

	}
	
	
}
