package mms.transactions.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.MmsConfigUtil;
import mms.transactions.bo.ReceiveFromThirdPartyTransBO;
import mms.transactions.controller.fb.GiftedItemDetailsTransFB;
import mms.transactions.controller.fb.ReceiveFromThirdPartyTransFB;
import mms.transactions.controller.hlp.ReceiveFromThirdPartyTransHLP;
import mms.transactions.vo.ReceiveFromThirdPartyTransVO;

/**
 * 
 * @author Tanvi Sappal
 * 
 */
public class ReceiveFromThirdPartyTransDATA {

	/**
	 * This function is used to set initial parameters required to display on
	 * main page
	 * 
	 * @param formBean
	 */
	public static void initialGenAdd(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request) {
		ReceiveFromThirdPartyTransVO vo = null;
		ReceiveFromThirdPartyTransBO bo = null;
		HisUtil hisutil = null;
		String strCurrentDate;


		try {
			vo = new ReceiveFromThirdPartyTransVO();
			bo = new ReceiveFromThirdPartyTransBO();

			hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();

			formBean.setStrSeatId(seatid);
			formBean.setStrHospitalCode(hosCode);
		
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);

			bo.initialGenAdd(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());

			}

			String temp = "";

			if (vo.getWsStoreNameCombo() != null
					&& vo.getWsStoreNameCombo().size() > 0) {
				if(vo.getWsStoreNameCombo().next())
				{
					vo.setStrStoreId(vo.getWsStoreNameCombo().getString(1));
					vo.getWsStoreNameCombo().beforeFirst();
				}
				temp = hisutil.getOptionValue(vo.getWsStoreNameCombo(), "",
						"", false, false);

			} else {

				temp = "<option value='0'>Select Value</option>";

			}

			formBean.setStrStoreNameCombo(temp);
			bo.getCategoryList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			String cmbstr = "";
			if (vo.getWsItemCategoryCombo() != null	&& vo.getWsItemCategoryCombo().size() > 0) 
			{
				cmbstr = hisutil.getOptionValue(vo.getWsItemCategoryCombo(),"", "", false);

			} else {

				cmbstr = "<option value='0'>Select Value</option>";
			}

			formBean.setStrItemCategoryCombo(cmbstr);
			if (vo.getWsInstituteList() != null	&& vo.getWsInstituteList().size() > 0)
			{
				temp = hisutil.getOptionValue(vo.getWsInstituteList(), "0",	"0^Select Value", true);

			} 
			else 
			{
				temp = "<option value='0'>Select Value</option>";

			}
			MmsConfigUtil mmscofigutil = new  MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
			formBean.setStrIssueRateConfigFlg(mmscofigutil.getStrIssueRateConfigFlg());			
			formBean.setStrInstituteValues(temp);
			
			strCurrentDate=hisutil.getASDate("dd-MMM-yyyy");
			formBean.setStrCurrentDate(strCurrentDate);	
			
		} catch (Exception e) {

			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",	"ReceiveFromThirdPartyTransDATA->initialGenAdd()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			if (vo != null)
				vo = null;
			
			hisutil = null;
	
		}
	}
	/**
	 * 
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void getCategoryListTwo(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{
		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
	 
		String strTemp =  null;
		try {

			vo = new ReceiveFromThirdPartyTransVO();
			bo = new ReceiveFromThirdPartyTransBO();
 

			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strStatus = (String) request.getParameter("storeId");
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrStatus(strStatus);
			
			
			bo.getReceivedItemListTwo(vo);

			formBean.setWsReceivedItemList(vo.getWsReceivedItemList());

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}
						
            if(strStatus.equals("4"))
            {	
			  strTemp = ReceiveFromThirdPartyTransHLP.getNewReceivedItemsList(formBean);
            }
            if(strStatus.equals("2"))
            {
              strTemp = ReceiveFromThirdPartyTransHLP.getUpdateReceivedItemsList(formBean);	
            }	

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getReceivedItemList()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		 
		}
	}

	public static void getCategoryListTwoNEW(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{
		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
	 
		String strTemp =  null;
		try {

			vo = new ReceiveFromThirdPartyTransVO();
			bo = new ReceiveFromThirdPartyTransBO();
 

			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strStatus = (String) request.getParameter("storeId");
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrStatus(strStatus);
			
			
			bo.getReceivedItemListTwo(vo);

			formBean.setWsReceivedItemList(vo.getWsReceivedItemList());

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}
						
            if(strStatus.equals("4"))
            {	
			  strTemp = ReceiveFromThirdPartyTransHLP.getNewReceivedItemsListNEW(formBean);
            }
            if(strStatus.equals("2"))
            {
              strTemp = ReceiveFromThirdPartyTransHLP.getUpdateReceivedItemsList(formBean);	
            }	

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strTemp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getReceivedItemList()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		 
		}
	}

	
	/**
	 * 
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void getCategoryListThree(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{
		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		MmsConfigUtil mmsUtil = null;
		String strTemp =  null;
		String strStatus = "";
		try {

			vo = new ReceiveFromThirdPartyTransVO();
			bo = new ReceiveFromThirdPartyTransBO();

			mmsUtil = new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			strStatus = formBean.getStrStatus();
			String[] tmp = formBean.getStrItemCategoryCode().split("\\^");
			
			vo.setStrStatus(formBean.getStrStatus());
			vo.setStrStoreId(formBean.getStrStoreId());
			vo.setStrItemCategoryId(tmp[0]);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrFinancialStartYear(mmsUtil.getStrFinancialStartDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStrFinancialEndYear(mmsUtil.getStrFinancialEndDate(vo.getStrStoreId() , vo.getStrHospitalCode()));

			bo.getReceivedItemListThree(vo);

			formBean.setWsReceivedItemList(vo.getWsReceivedItemList());

			if (vo.getStrMsgType().equals("1")) 
			{

				throw new Exception(vo.getStrMsgString());

			}
            if(strStatus.equals("4"))
            {	
			  strTemp = ReceiveFromThirdPartyTransHLP.getNewReceivedItemsList(formBean);
            }
            if(strStatus.equals("2"))
            {
              strTemp = ReceiveFromThirdPartyTransHLP.getUpdateReceivedItemsList(formBean);	
            }	
            formBean.setStrItemHlp(strTemp);
			

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getReceivedItemList()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			mmsUtil = null;
		}
	}

	public static void getCategoryListThreeNEW(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{
		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		MmsConfigUtil mmsUtil = null;
		String strTemp =  null;
		String strStatus = "";
		try {

			vo = new ReceiveFromThirdPartyTransVO();
			bo = new ReceiveFromThirdPartyTransBO();

			mmsUtil = new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			strStatus = formBean.getStrStatus();
			String[] tmp = formBean.getStrItemCategoryCode().split("\\^");
			
			vo.setStrStatus(formBean.getStrStatus());
			vo.setStrStoreId(formBean.getStrStoreId());
			vo.setStrItemCategoryId(tmp[0]);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrFinancialStartYear(mmsUtil.getStrFinancialStartDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStrFinancialEndYear(mmsUtil.getStrFinancialEndDate(vo.getStrStoreId() , vo.getStrHospitalCode()));

			bo.getReceivedItemListThree(vo);

			formBean.setWsReceivedItemList(vo.getWsReceivedItemList());

			if (vo.getStrMsgType().equals("1")) 
			{

				throw new Exception(vo.getStrMsgString());

			}
            if(strStatus.equals("4"))
            {	
			  strTemp = ReceiveFromThirdPartyTransHLP.getNewReceivedItemsListNEW(formBean);
            }
            if(strStatus.equals("2"))
            {
              strTemp = ReceiveFromThirdPartyTransHLP.getUpdateReceivedItemsListNEW(formBean);	
            }	
            formBean.setStrItemHlp(strTemp);
			

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getReceivedItemList()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			mmsUtil = null;
		}
	}
	

	public static void getCategoryList(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		HisUtil hisutil = null;
		try {

			vo = new ReceiveFromThirdPartyTransVO();
			bo = new ReceiveFromThirdPartyTransBO();

			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();

			String strStoreId = (String) request.getParameter("storeId");

			vo.setStrStoreId(strStoreId);
			vo.setStrHospitalCode(strHospitalCode);

			bo.getCategoryList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			hisutil = new HisUtil("mms", "ItemInventoryTransDATA");

			String cmbstr = "";

			if (vo.getWsItemCategoryCombo() != null
					&& vo.getWsItemCategoryCombo().size() > 0) {

				cmbstr = hisutil.getOptionValue(vo.getWsItemCategoryCombo(),
						"", "", false);

			} else {

				cmbstr = "<option value='0'>Select Value</option>";
			}

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getCategoryList()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			hisutil = null;
		}
	}

	public static void getFinancialYear(GiftedItemDetailsTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		MmsConfigUtil mmsUtil = null;

		try {
				
			mmsUtil = new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();

			String strStoreId = (String) request.getParameter("storeId");

			String strFinYear = "";	
			
					
			if(mmsUtil.getStrFinancialStartDate(strStoreId , strHospitalCode ).length() > 10)
			strFinYear = mmsUtil.getStrFinancialStartDate(strStoreId , strHospitalCode ).substring(7,11) +" - "+mmsUtil.getStrFinancialEndDate(strStoreId , strHospitalCode).substring(7,11);
			
			
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strFinYear);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getFinancialYear()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			mmsUtil = null;
		}
	}
	

	
	public static void getReceivedItemList(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		MmsConfigUtil mmsUtil = null;
		try {

			vo = new ReceiveFromThirdPartyTransVO();
			bo = new ReceiveFromThirdPartyTransBO();

			mmsUtil = new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());

			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();

			String strStoreId = (String) request.getParameter("storeId");
			String strItemCategoryId = (String) request.getParameter("strCategoryId");

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryId(strItemCategoryId);
			vo.setStrHospitalCode(strHospitalCode);
//			vo.setStrFinancialStartYear(mmsUtil.getStrFinancialStartDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
//			vo.setStrFinancialEndYear(mmsUtil.getStrFinancialEndDate(vo.getStrStoreId() , vo.getStrHospitalCode()));


			vo.setStrFinancialStartYear((String) request.getParameter("startDate"));
			vo.setStrFinancialEndYear((String) request.getParameter("endDate"));
			
			vo.setStrMode("4");			
			
			
			bo.getReceivedItemList(vo);

			formBean.setWsReceivedItemList(vo.getWsReceivedItemList());

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

			String strTemp = ReceiveFromThirdPartyTransHLP.getReceivedItemsList(formBean);

			try
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strTemp);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		} 
		catch (Exception e) 
		{

			e.printStackTrace();

			strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getReceivedItemList()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			mmsUtil = null;
		}
	}

	public static void initialAdd(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request) {

		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;

		String strmsgText = "";
		HisUtil hisutil = null;
		String ItemCmb = "";

		try {
			bo = new ReceiveFromThirdPartyTransBO();
			vo = new ReceiveFromThirdPartyTransVO();
			hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");

			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			formBean.setStrCtDate(ctDate);

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid = request.getSession().getAttribute("SEATID")
					.toString();

			String[] strTempVal = formBean.getStrItemCategoryId().replace("^","#").split("#");

			String strItemCategoryId = strTempVal[0];
			String strWarrantyFlag = strTempVal[1];
			String strInstallFlag = strTempVal[2];

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId(formBean.getStrStoreId());
			vo.setStrItemCategoryNo(strItemCategoryId);

			formBean.setStrDefaultCurrencyCode(MmsConfigUtil.DEFAULT_CURRENCY_CODE);
			formBean.setStrItemCategoryNo(strItemCategoryId);
			formBean.setStrWarrantyFlag(strWarrantyFlag);
			formBean.setStrInstallFlag(strInstallFlag);
			
			if(formBean.getStrInstituteId().equals("108") && strItemCategoryId.equals("10")) // 108 for local purchase 
				vo.setStrLpFlagMode("8");	
			else
				vo.setStrLpFlagMode("1");
			
			bo.initialAdd(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			if (vo.getStrGroupComboWs() != null
					&& vo.getStrGroupComboWs().size() > 0) {

				ItemCmb = hisutil.getOptionValue(vo.getStrGroupComboWs(), "0",
						"0^Select Value", true);

			} else {
				ItemCmb = "<option value='0'>Select Value</option>";

			}

			formBean.setStrGroupCombo(ItemCmb);

			if (vo.getStrManufactureComboWS() != null
					&& vo.getStrManufactureComboWS().size() > 0) {

				ItemCmb = hisutil.getOptionValue(vo.getStrManufactureComboWS(),
						"0", "0^Select Value", true);

			} else {
				ItemCmb = "<option value='0'>Select Value</option>";

			}

			formBean.setStrSuppliedByValues(ItemCmb);

			if (vo.getStrCurrencyCodeWs() != null
					&& vo.getStrCurrencyCodeWs().size() > 0) {
				ItemCmb = hisutil.getOptionValue(vo.getStrCurrencyCodeWs(),
						MmsConfigUtil.DEFAULT_CURRENCY_CODE, "0^Select Value",
						true);

			} else {
				ItemCmb = "<option value='0'>Select Value</option>";

			}

			formBean.setStrCurrencyCodeValues(ItemCmb);

			if (vo.getStrStockStatusWs() != null
					&& vo.getStrStockStatusWs().size() > 0) {
				ItemCmb = hisutil.getOptionValue(vo.getStrStockStatusWs(), "10",
						"0^Select Value", true);

			} else {
				ItemCmb = "<option value='0'>Select Value</option>";

			}

			formBean.setStrStockStatusValues(ItemCmb);
			MmsConfigUtil mmscofigutil = new  MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
			formBean.setStrIssueRateConfigFlg(mmscofigutil.getStrIssueRateConfigFlg());
			formBean.setStrConfigIssueRate(mmscofigutil.getStrConfigIssueRate());
			
			
		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "Gifted Item Inventory.ReceiveFromThirdPartyTransDATA.initialAdd(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->initialAdd()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			hisutil = null;
		}

	}

	public static void getSubGroupList(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		HisUtil hisutil = null;
		try {

			bo = new ReceiveFromThirdPartyTransBO();
			vo = new ReceiveFromThirdPartyTransVO();

			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();

			String strGroupId = (String) request.getParameter("GroupId");

			vo.setStrHospitalCode(strHospitalCode);

			vo.setStrGroupId(strGroupId);

			bo.getSubGroupList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");

			String cmbstr = "<option value='0'>All</option>";

			if (vo.getStrSubGroupComboWs() != null
					&& vo.getStrSubGroupComboWs().size() > 0) {

				cmbstr = hisutil.getOptionValue(vo.getStrSubGroupComboWs(), vo
						.getStrItemId(), "0^All", false);

			}

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "DrugInventory.ReceiveFromThirdPartyTransDATA.getSubGroupList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getSubGroupList()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			hisutil = null;
		}
	}

	/**
	 * To get values of SubgroupCombo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void itemName(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		HisUtil hisutil = null;
		try {

			bo = new ReceiveFromThirdPartyTransBO();
			vo = new ReceiveFromThirdPartyTransVO();

			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();

			String subGroupId = (String) request.getParameter("strSubGroupId");
			String strGroupId = (String) request.getParameter("GroupId");
			String strStoreId = (String) request.getParameter("storeId");
			String strItemCatNo = (String) request.getParameter("catCode");

			if (subGroupId == null)
				subGroupId = "0";

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo(strItemCatNo);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);

			vo.setStrSubGroupId(subGroupId);
			vo.setStrGroupId(strGroupId);

			bo.getItemName(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");
			String cmbstr = hisutil.getOptionValue(vo.getStrItemNameComboWS(),
					vo.getStrItemId(), "0^Select Value", false);

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "DrugInventory.ReceiveFromThirdPartyTransDATA.itemName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->itemName()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			hisutil = null;
		}
	}

	/**
	 * To get values of Item Name Combo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void itemBrandName(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;

		try {

			bo = new ReceiveFromThirdPartyTransBO();
			vo = new ReceiveFromThirdPartyTransVO();

			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String itemId = (String) request.getParameter("strItemId");
			String strStoreId = (String) request.getParameter("storeId");
			String strGroupId = (String) request.getParameter("strGroupId");
			String strSubGroupId = (String) request
					.getParameter("strSubGroupId");
			String strCategoryCode = (String) request
					.getParameter("strCatCode");

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo(strCategoryCode);
			vo.setStrGroupId(strGroupId);
			vo.setStrSubGroupId(strSubGroupId);

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);

			bo.getItemBrandName(vo);
			vo.getStrItemNameComboWS();

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");
			String cmbstr = hisutil.getOptionValue(vo.getStrItemBrandComboWS(),
					vo.getStrItemBrandId(), "0^Select Value", false);

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.ReceiveFromThirdPartyTransDATA.itemBrandName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->itemBrandName()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		}
	}

	
	/**
	 * To get Brand Details of a Branded Item
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void getBrandDetails(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		HisUtil hisutil = null;
		String ItemCmb = "";

		try {

			bo = new ReceiveFromThirdPartyTransBO();
			vo = new ReceiveFromThirdPartyTransVO();
			hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");
			 
			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			  
			String strItemBrandId = (String) request.getParameter("strItemBrandId");

			vo.setStrItemBrandId(strItemBrandId);
			vo.setStrHospitalCode(strHospitalCode);
		 
			vo.setStrItemCategoryNo("10");//formBean.getStrItemCategoryNo()
			
			bo.getBrandDetails(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			String strBrandDetails = vo.getStrRegFlag()+"^"+vo.getStrBrandDetails(); 
		  
			if (vo.getStrStockStatusWs() != null && vo.getStrStockStatusWs().size() > 0) 
			{
				ItemCmb = hisutil.getOptionValue(vo.getStrStockStatusWs(),	"0", "0^Select Value",	true);

			} else {
				ItemCmb = "<option value='0'>Select Value</option>";

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strBrandDetails+"$$"+ItemCmb);
			
			
		 
		} catch (Exception e) {

			e.printStackTrace();

			String strmsgText = "ReceiveFromThirdPartyTransDATA.getBrandDetails(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->getBrandDetails()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		}
	}
	
	
	
	/**
	 * To get values of Manufacture Name combo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void manufectuteName(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;

		try {

			bo = new ReceiveFromThirdPartyTransBO();
			vo = new ReceiveFromThirdPartyTransVO();

			formBean.setStrModuleId(MmsConfigUtil.MODULE_ID);
			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();

			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");

			String strCatCode = (String) request.getParameter("strCatCode");

			vo.setStrItemCategoryNo(strCatCode);

			vo.setStrItemBrandId(strItemBrandId);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);

			bo.getmanufectuteName(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");
			String cmbstr = hisutil.getOptionValue(vo
					.getStrManufactureComboWS(), vo.getStrManufactureId(),
					"0^Select Value", false);

			try {
				response.setHeader("Cache-Control", "no-cache");
				formBean.setStrManufactureCombo(cmbstr);
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "DrugInventory.ReceiveFromThirdPartyTransDATA.manufectuteName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->manufectuteName()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		}
	}

	/**
	 * to insert the data
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void insert(ReceiveFromThirdPartyTransFB formBean) {
		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;

		MmsConfigUtil mmsUtil = null;

		try {
			bo = new ReceiveFromThirdPartyTransBO();

			vo = (ReceiveFromThirdPartyTransVO) TransferObjectFactory
					.populateData(
							"mms.transactions.vo.ReceiveFromThirdPartyTransVO",
							formBean);

			mmsUtil = new MmsConfigUtil(formBean.getStrHospitalCode());

			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			vo.setStrInHandQuantityUnitID(formBean.getStrInHandQuantityUnitID()
					.replace("^", "#").split("#")[0]);
			vo.setStrUnitRateID(formBean.getStrUnitRateID().replace("^", "#")
					.split("#")[0]);
			vo.setStrUnitSaleID(formBean.getStrUnitSaleID().replace("^", "#")
					.split("#")[0]);
			vo.setStrItemCategoryNo(formBean.getStrItemCategoryNo());

			if (formBean.getStrBatchNo().trim().equals("0")
					|| formBean.getStrBatchNo().trim().length() < 1) {

				vo.setStrBatchNo(mmsUtil.getStrBatchNo());

			}

			vo.setStrFinancialStartYear(mmsUtil.getStrFinancialStartDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStrFinancialEndYear(mmsUtil.getStrFinancialEndDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
            vo.setStrReceivedFromThirdPartyName(formBean.getStrReceivedFromThirdPartyName());
			bo.insert(vo);

			if (vo.getStrMsgType().equals("1"))
				throw new Exception(vo.getStrMsgString());

			formBean.setStrSerialNo(vo.getStrSerialNo());

			formBean.setStrNormalMsg("Data has been Successfully Saved ");

			formBean.setStrIsWarrantyDetails("0");
			formBean.setStrIsInstallDetails("0");
			
		} catch (Exception e) {

			strmsgText = "DrugInventory.ReceiveFromThirdPartyTransDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->insertRecord()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			vo = null;
			mmsUtil = null;

		}
	}

	/**
	 * To get values of Unit on the basics of Item Name combo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void unitNameCombo(ReceiveFromThirdPartyTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ReceiveFromThirdPartyTransBO bo = null;
		ReceiveFromThirdPartyTransVO vo = null;
		String strItemCategoryNo="";
		// MmsConfigUtil mcu = null;

		try {

			bo = new ReceiveFromThirdPartyTransBO();
			vo = new ReceiveFromThirdPartyTransVO();
			// mcu = new MmsConfigUtil();

			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String itemId = (String) request.getParameter("strItemId");
			strItemCategoryNo=request.getParameter("itemCategNo");
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);
			vo.setStrItemCategoryNo(strItemCategoryNo);
			// vo.setStrModuleId(mcu.getStrModuleId());
			vo.setStrModuleId(MmsConfigUtil.MODULE_ID);
			bo.unitNameCombo(vo);
			vo.getStrItemNameComboWS();

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "ReceiveFromThirdPartyTransDATA");
			String cmbstr = hisutil.getOptionValue(vo.getStrUnitRateComboWS(),vo.getStrUnitRateID(), "", false);

			/*
			 * HisUtil hisutil1 = new HisUtil("mms",
			 * "ReceiveFromThirdPartyTransDATA"); String cmbstr1 =
			 * hisutil1.getOptionValue(vo.getStrUnitSaleComboWS(),
			 * vo.getStrUnitSaleID(), "0^Select Value", false);
			 */

			try {
				// System.out.println("cmbstr" + cmbstr);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.ReceiveFromThirdPartyTransDATA.unitNameCombo(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ReceiveFromThirdPartyTransDATA->unitNameCombo()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {

			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			// mcu = null;
		}
	}

}
