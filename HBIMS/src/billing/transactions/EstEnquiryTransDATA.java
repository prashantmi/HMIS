package billing.transactions;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;
import billing.HLPTariffDtl;
import billing.PrintHLP;

// TODO: Auto-generated Javadoc
/**
 * The Class CashCollectionTransDATA.
 */
public class EstEnquiryTransDATA {

	/**
	 * method used to check whether the System's IP Address is Registered in
	 * Counter Master or Not.
	 * 
	 * @param request
	 * @param formBean
	 */
	public static void checkCounterStatus(HttpServletRequest request,
			EstEnquiryTransFB formBean) {

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;

		try {

			formBean.setStrIpAddress(request.getSession().getAttribute(
					"IP_ADDR").toString());

			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo = new EstEnquiryTransBO();
			voObj = new EstEnquiryTransVO();

			voObj.setStrIpAddress(formBean.getStrIpAddress());
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.checkCounterStatus(voObj);

			String strCounterId = voObj.getStrCounterId();

			if (strCounterId != null) {

				if (strCounterId.trim().length() < 4) {

					formBean
							.setStrErrMsg("Cash Collection is Not Applicable, System IP Address is not Registered as Counter");
			
				}

			}

			formBean.setStrConfirmationType(new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString()).getGeneralCashCollectionConfrimType());
			
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"CashCollectionTransDATA->checkCounterStatus()", msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

		
			eObj = null;
		} finally {

			bo = null;
			voObj = null;

		}

	}
 

	/**
	 * returns populated Group Combo box (Option List) based on Hospital Service
	 * and Package Flag as Ajax response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void getGroupDetails(HttpServletRequest request,
			HttpServletResponse response) {

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;
		HisUtil util = null;

		try {
			bo = new EstEnquiryTransBO();
			voObj = new EstEnquiryTransVO();

			String strHospitalService = request.getParameter("hService");
			String strIsPackWiseGroup = request.getParameter("withPack");
			if (strHospitalService == null)
				strHospitalService = "0";

			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineIsPackageGroup(strIsPackWiseGroup);

			bo.getGroupDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Transaction", "CashCollectionTrans");
			String temp = "<option value='0'>All Group</option>";

			if (strIsPackWiseGroup.equals("0")) {
				if (voObj.getOfflineGroupList().size() != 0) {

					temp = util.getOptionValue(voObj.getOfflineGroupList(),
							"0", "0^All Group", false);

				}
			} else {

				if (voObj.getOfflinePackageGroup().size() != 0) {

					temp = util.getOptionValue(voObj.getOfflinePackageGroup(),
							"0", "0^All Group", false);

				}

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"IpdBillManagementTransDATA->UNITVAL12()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->UNITVAL12()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;

			util = null;
		}

	}
 
	/**
	 * gets Online Third Party Amount View as Ajax response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	/*
	 * public static void getOnlineThirdPartyAmtView(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * String strChargeTypeId = (String) request
	 * .getParameter("strChargeTypeId"); String strRequestTypeId = (String)
	 * request .getParameter("strRequestTypeId"); String strBServiceId =
	 * (String) request.getParameter("strBServiceId");
	 * 
	 * String strClientDtlsStauts = (String)
	 * request.getParameter("clientStatus");
	 * 
	 * BillConfigUtil billConfigUtil = new BillConfigUtil();
	 * 
	 * String temp = ""; // condition to check whether charge type id 1 - OPD ,
	 * 2 - IPD , 3 - // Emergency // and whether third party billing in on or
	 * off in the respective cases
	 * 
	 * if (((strChargeTypeId.equals("1") &&
	 * billConfigUtil.getOpdThirdPartyBilling().equals("1")) ||
	 * (strChargeTypeId.equals("2") &&
	 * billConfigUtil.getIpdThirdPartyBilling().equals("1")) ||
	 * (strChargeTypeId.equals("3") &&
	 * billConfigUtil.getEmergencyThirdPartyBilling().equals("1"))) &&
	 * strClientDtlsStauts.equals("1")) { // whether request type : 1 - Receipt &
	 * bill service : 10 - Service if (strRequestTypeId.equals("1") &&
	 * (strBServiceId.equals("10") || strBServiceId .equals("21"))) temp =
	 * EstEnquiryTransHLP .getOnLineThirdPartyAmountDtlView(strBServiceId ,
	 * "1");
	 * 
	 * }else if((strRequestTypeId.equals("1") || strRequestTypeId.equals("2")) &&
	 * strBServiceId .equals("21")){
	 * 
	 * temp = EstEnquiryTransHLP
	 * .getOnLineThirdPartyAmountDtlView(strBServiceId , "2"); }
	 * 
	 * response.setHeader("Cache-Control", "no-cache"); try {
	 * 
	 * response.getWriter().print(temp); } catch (IOException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * new HisException("Billing",
	 * "IpdBillManagementTransDATA->getOnlineThirdPartyAmtView()",
	 * e.getMessage()); } }
	 */

	/**
	 * gets Off-line Third Party Amount Details as Ajax Response.
	 * 
	 * @param request
	 * @param response
	 */
	/*
	 * public static void getOfflineThirdPartyAmtView(HttpServletRequest
	 * request, HttpServletResponse response) {
	 * 
	 * String strChargeTypeId = (String) request
	 * .getParameter("strChargeTypeId"); String strRequestTypeId = (String)
	 * request .getParameter("strRequestTypeId"); String strBServiceId =
	 * (String) request.getParameter("strBServiceId");
	 * 
	 * String strClientDtlsStauts = (String)
	 * request.getParameter("clientStatus");
	 * 
	 * BillConfigUtil billConfigUtil = new BillConfigUtil();
	 * 
	 * String temp = ""; // condition to check whether charge type id 1 - OPD ,
	 * 3 - Emergency // and whether third party billing in on or off in the
	 * respective cases if (((strChargeTypeId.equals("1") &&
	 * billConfigUtil.getOpdThirdPartyBilling().equals("1")) ||
	 * (strChargeTypeId.equals("3") &&
	 * billConfigUtil.getEmergencyThirdPartyBilling().equals("1"))) &&
	 * strClientDtlsStauts.equals("1")) { // whether request type : 1 - Receipt &
	 * bill service : 10 - Service if (strRequestTypeId.equals("1") &&
	 * strBServiceId.equals("10")) temp = EstEnquiryTransHLP
	 * .getOffLineThirdPartyAmountDtlView(); }
	 * 
	 * response.setHeader("Cache-Control", "no-cache"); try {
	 * 
	 * response.getWriter().print(temp); } catch (IOException e) {
	 * 
	 * new HisException( "Billing",
	 * "IpdBillManagementTransDATA->getOfflineThirdPartyAmtView()",
	 * e.getMessage()); } }
	 */

	/**
	 * writes Populated Tariff Drop Down Combo box (Option List) based on
	 * Selected Group as Ajax Response.
	 * 
	 * @param request
	 * @param response
	 */
	public static void getTariffDetails(HttpServletRequest request,
			HttpServletResponse response) {

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;
		HisUtil util = null;
		try {
			bo = new EstEnquiryTransBO();
			voObj = new EstEnquiryTransVO();

			util = new HisUtil("Billing",
					"CashCollectionTransDATA.getTariffDetails()");

			String strGroupCode = request.getParameter("groupId");
			String strHospitalService = request.getParameter("hService");
			String strTreatmentCategory = request.getParameter("treatmentCat");
			String strWardCode = request.getParameter("ward");
			String strSearchLetter = request.getParameter("searchLetter");
			
			if (strGroupCode == null)
				strGroupCode = "0";
			if (strHospitalService == null)
				strHospitalService = "0";
			if (strTreatmentCategory == null)
				strTreatmentCategory = "0";
			if (strWardCode == null || !strHospitalService.equals("2"))
				strWardCode = "0";

			if(strSearchLetter == null) strSearchLetter = "";
			
			voObj.setStrHospitalCode(request.getSession().getAttribute(
			"HOSPITAL_CODE").toString());
			voObj.setStrOffLineGroup(strGroupCode);
			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineTreatmentCategory(strTreatmentCategory);
			voObj.setStrOffLineWard(strWardCode);
			voObj.setStrSearchLetter(strSearchLetter);
			
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.getTariffDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			String temp = "";

			if (voObj.getOfflineTariffList().size() != 0) {

				// temp = HisUtil.getDropDown(voObj.getOfflineTariffList(), 1,
				// 5, false);
				temp = util.getDropDown(voObj.getOfflineTariffList(), 1, 5,
						false);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getTariffDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->getTariffDetails()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;
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

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;
		
		try {
			bo = new EstEnquiryTransBO();
			voObj = new EstEnquiryTransVO();

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
			voObj.setStrOffLineGroup(strGroupCode);
			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineTreatmentCategory(strTreatmentCategory);
			voObj.setStrOffLineWard(strWardCode);
			voObj.setStrTariffCode(strTariffCode);
			
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.getTariffCodeDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			String temp = "";

			if (voObj.getOfflineTariffList().size() != 0) {
				
				if(voObj.getOfflineTariffList().next()){
					
					temp =  "1@@"+voObj.getOfflineTariffList().getString(2)+"@@"+voObj.getOfflineTariffList().getString(1);
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
						"CashCollectionTransDATA->getTariffCodeDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->getTariffCodeDetails()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;
		}

	}

	
	/**
	 * writes Package Details based on rowIndex as Ajax Response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void getPackageDetails(HttpServletRequest request,
			HttpServletResponse response) {

		EstEnquiryTransVO voObj = null;

		try {

			voObj = new EstEnquiryTransVO();

			String strRowIndex = request.getParameter("rowIndex");

			if (strRowIndex == null)
				strRowIndex = "0";

			voObj.setStrOffLinePackageIndex(strRowIndex);

			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			String temp = EstEnquiryTransHLP
					.getOffLinePackageDetailsView(voObj);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strRowIndex + "@" + temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getPackageDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->getPackageDetails()", e1
								.getMessage());
			}
		} finally {

			voObj = null;
		}

	}

	/**
	 * writes Tariff Unit Combo (Option list) based on RowIndex, Unit Id and
	 * Base Value as Ajax Response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void getOffLineTariffUnitDetails(HttpServletRequest request,
			HttpServletResponse response) {

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;
		HisUtil util = null;

		try {

			bo = new EstEnquiryTransBO();
			voObj = new EstEnquiryTransVO();

			String strRowIndex = request.getParameter("rowIndex");
			String strUnitId = request.getParameter("unitId");
			String strUnitBaseVal = request.getParameter("baseValue");

			if (strRowIndex == null)
				strRowIndex = "0";
			if (strUnitId == null)
				strUnitId = "0";
			if (strUnitBaseVal == null)
				strUnitBaseVal = "0";

			voObj.setStrOffLineTariffUnitTempId(strUnitId);
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			//bo.getOffLineTariffUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Transaction", "CashCollectionTrans");

			String temp = "<option value='0'>Select Value</option>";

			/*if (voObj.getOfflineTariffUnit().size() != 0) {

				temp = util.getOptionValue(voObj.getOfflineTariffUnit(),
						strUnitId + "^" + strUnitBaseVal, "", false);

			}*/
			temp=BillConfigUtil.getDefaultUnitComboWithBaseValue(voObj.getStrOffLineTariffUnitTempId()+"^1");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strRowIndex + "@" + temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException(
						"Billing",
						"CashCollectionTransDATA->getOffLineTariffUnitDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException(
						"Billing",
						"IpdBillManagementTransDATA->getOffLineTariffUnitDetails()",
						e1.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;

			util = null;
		}

	}
 
 
	/**
	 * writes Refund Bill Tariff Pop-up Details (HTML) in String Format by
	 * Retrieving hidden Value as Ajax Response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void getOffLineRefundBillTariffPopupDetails(
			HttpServletRequest request, HttpServletResponse response) {

		EstEnquiryTransVO voObj = null;

		try {

			voObj = new EstEnquiryTransVO();

			String strHidVal = request.getParameter("hidValue");

			if (strHidVal == null)
				strHidVal = "0";

			voObj.setStrOffLineTariffDetailsHiddenValue(strHidVal);
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			String temp = EstEnquiryTransHLP.getPopUp(voObj);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException(
						"Billing",
						"CashCollectionTransDATA->getOfflineTariffUnitBillDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException(
						"Billing",
						"IpdBillManagementTransDATA->getOfflineTariffUnitBillDetails()",
						e1.getMessage());
			}
		} finally {

			voObj = null;

		}

	}

	/**
	 * gets Unit Values as Ajax Response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void UNITVAL12(HttpServletRequest request,
			HttpServletResponse response) {
		// Declaring Variables
		String strData = request.getParameter("modName");

		String strTariffDtl = "";
		String strTariffDtl1 = "";
		// String msgStr = "";
		String[] data = strData.split("\\^");

		// Creating Object for BO & VO.
		EstEnquiryTransVO vo = new EstEnquiryTransVO();

		try {
			String strHospCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();
			// Calling TariffDtls
			strTariffDtl1 = HLPTariffDtl.getTariffDtl(data[0], data[1],
					strHospCode);
			strTariffDtl = strTariffDtl1 + '@' + data[2];

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strTariffDtl);

		} catch (Exception e) {

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->UNITVAL12()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {

				new HisException("Billing",
						"IpdBillManagementTransDATA->UNITVAL12()", e1
								.getMessage());
			}
		} finally {

			if (vo != null)
				vo = null;
		}

	}

	/**
	 * gets Online Refund Tariff Pop-up as Ajax Response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void getOnLineRefundTariffPopupDetails(
			HttpServletRequest request, HttpServletResponse response) {

		EstEnquiryTransVO voObj = null;

		try {

			voObj = new EstEnquiryTransVO();

			String strHidVal = request.getParameter("hidValue");

			if (strHidVal == null)
				strHidVal = "0";

			voObj.setStrRefundTariffHiddenValue(strHidVal);
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			String temp = EstEnquiryTransHLP.getRefundTariffPopUp(voObj);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException(
						"Billing",
						"CashCollectionTransDATA->getOnLineRefundTariffPopupDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException(
						"Billing",
						"IpdBillManagementTransDATA->getOnLineRefundTariffPopupDetails()",
						e1.getMessage());
			}
		} finally {

			voObj = null;

		}

	}

	/**
	 * Initialize Without Cr. No. Page.
	 * 
	 * @param request
	 *            the request
	 * @param formBean
	 *            the form bean
	 */
	public static void initWithoutCrNo(HttpServletRequest request,
			EstEnquiryTransFB formBean) {

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;

		HisUtil util = null;
		BillConfigUtil billConfigUtil = null;

		String strHospitalService = BillConfigUtil.DEFAULT_HOSPITAL_SERVICE;	

		formBean.setStrOffLineHospitalService(strHospitalService);
		

		try {

			billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			String strCategoryCode = billConfigUtil.getStrPaidCategory()==""?BillConfigUtil.NORMAL_CATEGORY:billConfigUtil.getStrPaidCategory();
			formBean.setStrOffLineTreatmentCategory(strCategoryCode);

			formBean.setStrConsumableChargesGroupId(billConfigUtil.getStrConsumableChargesGroupId());
			formBean.setStrConsumableChargesTariffCode(billConfigUtil.getStrConsumableChargesTariffCode());
			
			formBean.setStrIsOpdDiscount(billConfigUtil.getOpdDiscountClerk());
			
			if(billConfigUtil.getGeneralInternalPatient().equals("1") && billConfigUtil.getGeneralExternalPatient().equals("1"))
			{
				formBean.setStrPatTypeBoth("1");//Both
				formBean.setStrPatientType("1");
				formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredInt());
				formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredInt());
				//formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredExt());
				//formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredExt());
				formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingInt());
				//formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingExt());
				
				formBean.setStrGeneralChargeTypeId(billConfigUtil.getGeneralChargeTypeInt());
				formBean.setStrWardTypeId(billConfigUtil.getGeneralWardTypeInt());
				
			}
			if(billConfigUtil.getGeneralInternalPatient().equals("1") && !billConfigUtil.getGeneralExternalPatient().equals("1"))
			{
				formBean.setStrPatTypeBoth("2");//Internal
				formBean.setStrPatientType("1");
				formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredInt());
				formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredInt());
				//formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredExt());
				//formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredExt());
				formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingInt());
				//formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingExt());
				
				formBean.setStrGeneralChargeTypeId(billConfigUtil.getGeneralChargeTypeInt());
				formBean.setStrWardTypeId(billConfigUtil.getGeneralWardTypeInt());
				
			}
			if(!billConfigUtil.getGeneralInternalPatient().equals("1") && billConfigUtil.getGeneralExternalPatient().equals("1"))
			{
				formBean.setStrPatTypeBoth("3");//External
				formBean.setStrPatientType("2");
				//formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredInt());
				//formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredInt());
				formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredExt());
				formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredExt());
				//formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingInt());
				formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingExt());
				
				formBean.setStrGeneralChargeTypeId(billConfigUtil.getGeneralChargeTypeExt());
				formBean.setStrWardTypeId(billConfigUtil.getGeneralWardTypeExt());
				
			}

			bo = new EstEnquiryTransBO();
			voObj = new EstEnquiryTransVO();

			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			
			voObj.setStrOffLineIsPackageGroup("0");

			voObj.setStrOffLinePackageIndex("0");

			voObj.setStrOffLineHospitalService(strHospitalService);
			voObj.setStrOffLineTreatmentCategory(strCategoryCode);
			voObj.setStrOffLineRequestType("1");
			
			bo.initwithoutCrNoDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Transaction", "CashCollectionTrans");
			String temp = "";


			if (voObj.getStrClientNameList() != null
					&& voObj.getStrClientNameList().size() != 0) {
				temp = util.getOptionValue(voObj.getStrClientNameList(), voObj
						.getStrOffLineWard(), "0^Select Value", false);

				formBean.setStrClientNameContents(temp);
			} else {
				formBean
						.setStrClientNameContents("<option value='0'>Select Value</option>");
			}
			
			if (voObj.getStrPaymentModeList() != null
					&& voObj.getStrPaymentModeList().size() != 0) {
				temp = util.getOptionValue(voObj.getStrPaymentModeList(), "", "", false);

				formBean.setStrPaymentModeContents(temp);
			} 
			
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			if (voObj.getOfflineBillingServiceList().size() > 0) {
				temp = util.getOptionValue(
						voObj.getOfflineBillingServiceList(), voObj
								.getStrOffLineBillingService(), "", false);

				formBean.setStrBillingServiceDetails(temp);
			}

			if (voObj.getOfflineRaisingDepartmentList().size() > 0) {
				temp = util.getOptionValue(voObj
						.getOfflineRaisingDepartmentList(), "146", "0^Select Value",
						false);

				formBean.setStrRaisingDepartmentDetails(temp);
			} else {
				formBean
						.setStrRaisingDepartmentDetails("<option value='0'>Select Value</option>");
			}

			if (voObj.getOfflineGroupList().size() > 0) {
				temp = util.getOptionValue(voObj.getOfflineGroupList(), voObj
						.getStrOffLineWard(), "0^All Group", false);

				formBean.setStrOfflineGroupDetails(temp);
			}

			/*if (voObj.getOfflineTariffList().size() > 0) {

				// temp = HisUtil.getDropDown(voObj.getOfflineTariffList(), 1,
				// 5, false);
				temp = util.getDropDown(voObj.getOfflineTariffList(), 1, 5,
						false);

				formBean.setStrOfflineTariffDetails(temp);

			}*/

		 
			if (voObj.getOfflineApprovedByList().size() > 0) {
				temp = util.getOptionValue(voObj.getOfflineApprovedByList(),
						"0", "0^Select Value", false);

				formBean.setStrOfflineDiscountApprovedByDetails(temp);
			} else {

				temp = "<option value='0'>Select Value</option>";

				formBean.setStrOfflineDiscountApprovedByDetails(temp);

			}

			if (voObj.getOfflineRemarksList().size() > 0) {
				temp = util.getOptionValue(voObj.getOfflineRemarksList(), "",
						"", false);

				temp = temp + "<option value='0'>Others</option>";

				formBean.setStrOfflineDiscountRemarksDetails(temp);
			} else {

				temp = "<option value='0'>Others</option>";

				formBean.setStrOfflineDiscountRemarksDetails(temp);

			}

			if (voObj.getStrRequestNoList().size() > 0) {
				temp = util.getOptionValue(voObj.getStrRequestNoList(), "0",
						"0^Select Value", true);

				formBean.setStrRequestNoValues(temp);
			} else {

				temp = "<option value='0'>Select Value</option>";

				formBean.setStrRequestNoValues(temp);

			}
			
			
			if (voObj.getWsGenderList().size() > 0) {
				temp = util.getOptionValue(voObj.getWsGenderList(), "0",
						"0^Select Value", true);

				formBean.setStrGenderList(temp);
			} else {

				temp = "<option value='0'>Select Value</option>";

				formBean.setStrGenderList(temp);

			}
			
			if(voObj.getWsServiceTypeList().size() > 0)
			{
				temp =  util.getOptionValue(voObj.getWsServiceTypeList(), "","", true);
				
				formBean.setStrServiceTypeDetails(temp);
			}
			else
			{
				temp =  "<option value='0'>Select Value</option>";
				
				formBean.setStrServiceTypeDetails(temp);
			}
			
			formBean.setStrIsWithoutCrNoRequired(billConfigUtil.getGeneralIsWithoutCrNoRequired());
			formBean.setStrPrintMessageLimit(billConfigUtil.getGeneralPrintMessageLimit());
			formBean.setPrintMode(BillConfigUtil.PRINT_MODE.toString());//Laser Printing or DMP Printing
			
			

		} catch (Exception e) {

			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"CashCollectionTransDATA->initWithoutCrNo()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			voObj = null;
			util = null;
			billConfigUtil = null;
		}
	}

	/**
	 * Initialize without Cr. No. Bill Page Details.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void initWithoutCrNoBillDetails(HttpServletRequest request,
			HttpServletResponse response) {

		EstEnquiryTransVO voObj = null;
		EstEnquiryTransBO bo = null;

		BillConfigUtil billConfigUtil = null;
	//	HisUtil util = null;
		
		String temp = "";
		try {

			voObj = new EstEnquiryTransVO();
			bo = new EstEnquiryTransBO();

			billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			voObj.setStrOffLineRefundPenalty(billConfigUtil
					.getOpdRefundPenalty());

			String strBillNo = request.getParameter("billNo");

			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			voObj.setStrOffLineBillNumber(strBillNo);

			bo.initWithoutCrNoBillDetails(voObj);

			
		//	String strGaurantorVals = voObj.getStrGuarantorHiddenVal().trim();
			
			//String[] strGaurantorVal = strGaurantorVals.replace("^", "#").split("#");
			
			
			/*util = new HisUtil("Billing Transaction", "CashCollectionTrans");
						
			if (voObj.getWsGenderList().size() > 0) {
				temp = util.getOptionValue(voObj.getWsGenderList(), strGaurantorVal[6],
						"0^Select Value", true);
				
				voObj.setStrGenderList(temp);
				
			} else {

				temp = "<option value='0'>Select Value</option>";
				
				voObj.setStrGenderList(temp);
				
			}
			*/
			
			
			
			if (voObj.getOfflineBillTariffList().size() > 0) {

				temp = EstEnquiryTransHLP.getGuarantorDetails(voObj);
				temp = temp + " "
						+ EstEnquiryTransHLP.getTariffDetails(voObj);

			} else {

				temp = temp + "@" + "Invalid Bill No.";

			}

			if (voObj.getStrMsgType().equals("1")) {

				temp = temp + "@" + "Invalid Bill No.";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			e.printStackTrace();
					
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException(
						"Billing",
						"CashCollectionTransDATA->initWithoutCrNoBillDetails()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException(
						"Billing",
						"IpdBillManagementTransDATA->initWithoutCrNoBillDetails()",
						e1.getMessage());
			}
		} finally {

			bo = null;
			voObj = null;
			billConfigUtil = null;
		//	util = null;

		}

	}

	

	/**
	 * gets Bills list as Ajax Response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param formBean
	 *            the form bean
	 */
	public static void getBillListingDtls(HttpServletRequest request,
			HttpServletResponse response, EstEnquiryTransFB formBean) {

		EstEnquiryTransVO voObj = null;
		EstEnquiryTransBO bo = null;

		try {

			voObj = new EstEnquiryTransVO();
			bo = new EstEnquiryTransBO();

			String strSearchString = request.getParameter("searchString");
			String strSearchType = request.getParameter("searchType");
			String strFromRow = request.getParameter("fromRow");
			String strRowPerPage = request.getParameter("rowPerPage");
			String strCtBlockSet = request.getParameter("ctBlockSet");
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			if (strSearchString.contains("^")) {
				strSearchString = strSearchString.replace("^", "%");
			}
			
			 
			voObj.setStrBillSearchString(strSearchString);
			voObj.setStrBillSearchType(strSearchType);
			voObj.setStrBillFromRow(strFromRow);

			int nToRow = Integer.parseInt(strFromRow)
					+ Integer.parseInt(strRowPerPage) * 10;

			voObj.setStrBillToRow(String.valueOf(nToRow));
			voObj.setStrBillRowPerPage(strRowPerPage);
			voObj.setStrBillCtBlockSet(strCtBlockSet);
			
			bo.getBillListingDtl(voObj);

			if (voObj.getStrMsgType().equals("0")) {

				String val = EstEnquiryTransHLP.getBillListingView(voObj);

				response.setHeader("Cache-Control", "no-cache");

				response.getWriter().print(val);

			} else {

				throw new Exception(voObj.getStrMsgString());

			}

		} catch (Exception e) {

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getBillListingDtls()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->getBillListingDtls()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;
		}
	}

	
	
	/**
	 * gets Previous Cr No. Details as Ajax Response.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param formBean
	 *            the form bean
	 */
	public static void getPreviousCrNoDtls(HttpServletRequest request,
			HttpServletResponse response, EstEnquiryTransFB formBean) {

		EstEnquiryTransVO voObj = null;
		EstEnquiryTransBO bo = null;

		try {

			voObj = new EstEnquiryTransVO();
			bo = new EstEnquiryTransBO();

			String strPreviousCrNo = formBean.getStrPreviousCrNo();
			 
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

		 
			voObj.setStrPreviousCrNo(strPreviousCrNo);
 
			bo.getPreviousCrNoDtls(voObj);

			if (voObj.getStrMsgType().equals("0")) {

				 

				response.setHeader("Cache-Control", "no-cache");

				response.getWriter().print(voObj.getStrPreviousCrNoPatientDtls());

			} else {

				throw new Exception(voObj.getStrMsgString());

			}

		} catch (Exception e) {

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"CashCollectionTransDATA->getPreviousCrNoDtls()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->getPreviousCrNoDtls()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;
		}
	}
    

	public static void getWithoutCrNoOnlineReqTariffDtls(
			HttpServletRequest request, HttpServletResponse response) {

		EstEnquiryTransVO voObj = null;
		EstEnquiryTransBO bo = null;

		String temp = "";

		try {

			voObj = new EstEnquiryTransVO();
			bo = new EstEnquiryTransBO();

			String strReqNo = request.getParameter("strReqNo");
			String strChargeTypeId = request.getParameter("strChargeTypeId");

			if (strReqNo == null)
				strReqNo = "0";
			if (strChargeTypeId == null)
				strChargeTypeId = "0";

			voObj.setStrRequestNo(strReqNo);

			voObj.setStrChargeTypeId(strChargeTypeId);

			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.getWithoutCrNoOnlineReqTariffDtls(voObj);

			temp = EstEnquiryTransHLP
					.getWithoutCrNoOnLineTariffDetailsView(voObj);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException(
						"Billing",
						"CashCollectionTransDATA->getWithoutCrNoOnlineReqTariffDtls()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException(
						"Billing",
						"IpdBillManagementTransDATA->getWithoutCrNoOnlineReqTariffDtls()",
						e1.getMessage());
			}
		} finally {

			voObj = null;
			bo = null;

		}

	}

	public static void updatePrintStatus(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String strBillNo = request.getParameter("billNo");
			String strReceiptNo = request.getParameter("recNo");

			if (strBillNo == null)
				strBillNo = "0";
			if (strReceiptNo == null)
				strReceiptNo = "0";

			PrintHLP
					.updatePrintStatus(strBillNo, strReceiptNo, request
							.getSession().getAttribute("HOSPITAL_CODE")
							.toString(), "0");

		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"CashCollectionTransDATA->updatePrintStatus()", e
							.getMessage());
			String response1 = "ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator!";

			eObj = null;

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(response1);
			} catch (IOException e1) {

				new HisException("Billing",
						"IpdBillManagementTransDATA->updatePrintStatus()", e1
								.getMessage());
			}

		}
	}

	

	public static void rePrint(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			 

			PrintHLP.reprintFile(request);

		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"CashCollectionTransDATA->rePrint()", e
							.getMessage());
			String response1 = "ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator!";

			eObj = null;

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(response1);
			} catch (IOException e1) {

				new HisException("Billing",
						"IpdBillManagementTransDATA->rePrint()", e1
								.getMessage());
			}

		}
	}
	
	/**
	 * Insert's Without CR. No. Receipt Services.
	 * 
	 * @param formBean
	 *            the form bean
	 * 
	 * @return true, if insert without CR No. receipt services
	 */
	public static boolean insertWithoutCrNoReceiptServices(EstEnquiryTransFB formBean  , HttpServletRequest request) 
	{
		boolean fRes = false;
		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;
		//String[] strDirectTariffList = null;
		
		try 
		{
			bo = new EstEnquiryTransBO();
			voObj = (EstEnquiryTransVO) TransferObjectFactory.populateData("billing.transactions.EstEnquiryTransVO", formBean);

			if(voObj.getStrPreviousCrNo().length() <= 0)
				voObj.setStrPreviousCrNo("0");

			if(voObj.getStrAge().equals(""))
			{
				voObj.setStrAge("0");
			}
			
			bo.insertWithoutCrNoReceiptServices(voObj);
		
			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrReceiptNo("1");
		
			Map tariffPrintMap=new LinkedHashMap();
			 if(formBean.getStrOfflineTariffDetailsHidden() != null)
			 {
					
				  //strDirectTariffList = new String[formBean.getStrOfflineTariffDetailsHidden().length];
				 String[] strArrayTariffList=new String[formBean.getStrOfflineTariffDetailsHidden().length];
				 for (int i = 0 , stopI = formBean.getStrOfflineTariffDetailsHidden().length ; i < stopI; i++) 
				 {					 
					 String strTemp[] = formBean.getStrOfflineTariffDetailsHidden()[i].replace("^", "#").split("#");
					 String strDirectTariffList=formBean.getStrOfflineTariffName()[i].substring(0, formBean.getStrOfflineTariffName()[i].lastIndexOf("- (")-1)+"@"+
										  		strTemp[4]+"/"+strTemp[14]+"^"+
											  formBean.getStrOfflineTariffQty()[i]+" "+strTemp[14]+"^"+
											  "0"+"^"+
											  "0"+"^"+
											  formBean.getStrOfflineTariffNetAmount()[i];
					
					 strArrayTariffList[i]=strDirectTariffList+"#"+strTemp[1]+"#"+strTemp[19];
				 }
				 tariffPrintMap=PrintHLP.populateGroupMapForPrinting(strArrayTariffList,tariffPrintMap,new BillConfigUtil(formBean.getStrHospitalCode()));
				
			}			 
			
			if (!voObj.getStrMsgType().equals("1")) 
			{				 				
				PrintHLP.OPD_ESTIMATION(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean.getStrHospitalCode(), formBean.getStrReceiptNo() , request , tariffPrintMap , "1" , 0,"0","1");

				formBean.setStrNormalMsg("Estimation Receipt No. '"+ formBean.getStrBillNo()+ "' Generated Successfully for the Amount <img src='../../hisglobal/images/INR.png'> '"+ HisUtil.getAmountWithDecimal(formBean.getStrOfflineTotalRecAmount(), 2) + "'");

				formBean.setStrTempBillNo(formBean.getStrBillNo());
				formBean.setStrTempReceiptNo("1");
				
				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				System.out.println("fileName"+fileName);
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				formBean.setIsOpenPopUp("1");
				request.setAttribute("isEstimation", "1");
				fRes = true;
			} 
			else 
			{				
				fRes = false;
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			String err = e.getMessage();
			HisException eObj = new HisException("Billing Transaction","CashCollectionTransDATA.insertWithoutCrNoReceiptServices()-->",err);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			voObj = null;
		}
		return fRes;
	}

	/**
	 * Insert's Without CR. No. Refund Services.
	 * 
	 * @param formBean
	 *            the form bean
	 * 
	 * @return true, if insert without CR No. refund services
	 */
	public static boolean insertWithoutCrNoRefundServices(
			EstEnquiryTransFB formBean  , HttpServletRequest request) {

		boolean fRes = false;

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;

		try {
			bo = new EstEnquiryTransBO();
			voObj = (EstEnquiryTransVO) TransferObjectFactory.populateData(
					"billing.transactions.EstEnquiryTransVO", formBean);

			bo.insertWithoutCrNoRefundServices(voObj);

		
			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrRefundReceiptNo(voObj.getStrRefundReceiptNo());

			if (!voObj.getStrMsgType().equals("1")) {

				PrintHLP.OPD_REFUND(formBean.getStrBillNo(), formBean.getStrRefundReceiptNo(), formBean.getStrOffLineBillingService(), formBean.getStrHospitalCode() , request , 0,"1");

				formBean.setStrNormalMsg("Bill '"+ formBean.getStrBillNo()+ "/"+ formBean.getStrRefundReceiptNo()+ "' Generated Successfully for the Collected Amount '"+ HisUtil.getAmountWithDecimal(formBean.getStrOfflineTotalRecAmount(), 2) + "'");

				formBean.setStrTempBillNo(formBean.getStrBillNo());
				formBean.setStrTempReceiptNo(formBean.getStrRefundReceiptNo());
				
				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				formBean.setIsOpenPopUp("1");

				fRes = true;
			} else {

				fRes = false;

				throw new Exception(voObj.getStrMsgString());

			}
		} catch (Exception e) {
			e.printStackTrace();
			String err = e.getMessage();

			HisException eObj = new HisException(
					"Billing Transaction",
					"CashCollectionTransDATA.insertWithoutCrNoRefundServices()-->",
					err);

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			voObj = null;
		}

		return fRes;

	}

	public static boolean insertOnlineWithoutCrNoReceiptServices(
			EstEnquiryTransFB formBean  , HttpServletRequest request){

		boolean fRes = false;

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;

		try {

			String strReqDtls[] = formBean.getStrWithoutCrNoOnlineRequestNo()
					.replace("^", "#").split("#");

			formBean.setStrRequestNo(strReqDtls[0]);

			formBean.setStrGuarantorName(strReqDtls[4]);
			formBean.setStrGuartorAddress(strReqDtls[5]);
			formBean.setStrGuarantorContactNo(strReqDtls[6]);

			formBean.setStrOffLineBillingService(strReqDtls[8]);
			formBean.setStrOffLineRaisingDepartment(strReqDtls[9]);
			formBean.setStrService(strReqDtls[10]);
			formBean.setStrGlobalRequestNo(strReqDtls[11]);
			formBean.setStrRequestDate(strReqDtls[13]);
			formBean.setStrAge(strReqDtls[14]);
			formBean.setStrAgeUnit(strReqDtls[15]);
			formBean.setStrReferringPhysician(strReqDtls[16]);
			formBean.setStrGender(strReqDtls[17]);
			formBean.setStrReferringPhysicianContactNo(strReqDtls[18]);
		
			//formBean.setStrOfflineTariffDiscountConfigDetails(formBean
			//		.getStrTariffDiscountConfigDetails());

			bo = new EstEnquiryTransBO();
			voObj = (EstEnquiryTransVO) TransferObjectFactory.populateData(
					"billing.transactions.EstEnquiryTransVO", formBean);

			bo.insertOnlineWithoutCrNoReceiptServices(voObj);

			formBean.setStrBillNo(voObj.getStrBillNo());
			formBean.setStrReceiptNo("1");

		
			if (!voObj.getStrMsgType().equals("1")) {

				PrintHLP.OPD_SERVICES(formBean.getStrBillNo(), formBean.getStrOffLineBillingService(), formBean.getStrHospitalCode(), formBean.getStrReceiptNo() , request , null , "0" , 0,"0","1");

				formBean.setStrNormalMsg("Receipt No. '"+ formBean.getStrBillNo()+ "/1"+ "' Generated Successfully for the Collected Amount <img src='../../hisglobal/images/INR.png'> '"+ HisUtil.getAmountWithDecimal(formBean.getStrOfflineTotalRecAmount(), 2) + "'");

				formBean.setStrTempBillNo(formBean.getStrBillNo());
				formBean.setStrTempReceiptNo("1");

				fRes = true;
			} else {

				fRes = false;

				throw new Exception(voObj.getStrMsgString());

			}
		} catch (Exception e) {

			String err = e.getMessage();

			HisException eObj = new HisException(
					"Billing Transaction",
					"CashCollectionTransDATA.insertWithoutCrNoReceiptServices()-->",
					err);

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			voObj = null;
		}

		return fRes;

	}
	
	public static void fetchServiceList(HttpServletRequest request,
			HttpServletResponse response) {

		EstEnquiryTransBO bo = null;
		EstEnquiryTransVO voObj = null;
		HisUtil util = null;

		try {
			bo = new EstEnquiryTransBO();
			voObj = new EstEnquiryTransVO();

			String strHospitalService = request.getParameter("serviceType");
			
			if (strHospitalService == null)
				strHospitalService = "1";

			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			voObj.setStrOffLineHospitalService(strHospitalService);
			

			bo.fetchServiceList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Transaction", "CashCollectionTrans");
			String temp = "<option value='0'>All Group</option>";

			if(voObj.getWsBillServiceList().size() != 0)
				temp=util.getOptionValue(voObj.getWsBillServiceList(),"", "", false);
			else
				temp="<option value='0'>Select Value</option>";

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"IpdBillManagementTransDATA->UNITVAL12()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"IpdBillManagementTransDATA->UNITVAL12()", e1
								.getMessage());
			}
		} finally {

			bo = null;

			voObj = null;

			util = null;
		}

	}

}
