/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DrugInventoryTransWithProgramDATA.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package mms.transactions.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.MmsConfigUtil;
import mms.transactions.bo.ItemInventoryWithProgramTransBO;
import mms.transactions.controller.fb.ItemInventoryWithProgramTransFB;
import mms.transactions.controller.hlp.ItemInventoryTransWithProgramHLP;
import mms.transactions.vo.ItemInventoryWithProgramTransVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DrugInventoryTransWithProgramDATA.
 */
public class ItemInventoryTransWithProgramDATA {

	/**
	 * to display the Store Name and Group Name on Add page.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */

	public static void initialAdd(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request) {

		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String strmsgText = "";
		HisUtil hisutil = null;
		String SubGroupCmb = "";
		String ItemCmb = "";
		String strTemp = "";

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			hisutil = new HisUtil("mms", "DrugInventoryTransDATA");

			if (!formBean.getComboValue().equals("")) {
				String strCmbNames = formBean.getComboValue();
				String[] temp = strCmbNames.replace("^", "#").split("#");
				formBean.setStrStoreName(temp[0]);
				formBean.setStrGroupName(temp[1]);
			}

			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			formBean.setStrCtDate(ctDate);

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid = request.getSession().getAttribute("SEATID")
					.toString();
			System.out.println("formBean.getCombo()"+formBean.getCombo());
			String strStoreId = formBean.getCombo()[0].split("\\^")[0];
			String strGroupId = formBean.getCombo()[2];
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId(strStoreId);
			vo.setStrGroupId(strGroupId);

			vo.setStrItemCategoryNo("10");

			formBean.setStrGroupId(strGroupId);
			formBean.setStrDefaultCurrencyCode(MmsConfigUtil.DEFAULT_CURRENCY_CODE);

			bo.initialAdd(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			formBean.setStrGroupNameCombo(vo.getStrGroupNameCombo());

			if (vo.getStrSubGroupComboWs().size() == 0
					|| vo.getStrSubGroupComboWs() == null) {
				SubGroupCmb = "<option value='0'>All</option>";
			} else {
				SubGroupCmb = hisutil.getOptionValue(
						vo.getStrSubGroupComboWs(), "0", "0^All", true);
			}

			formBean.setStrSubGroupCombo(SubGroupCmb);

			if (vo.getStrItemNameComboWS().size() == 0
					|| vo.getStrItemNameComboWS() == null) {
				ItemCmb = "<option value='0'>Select Value</option>";
			} else {
				ItemCmb = hisutil.getOptionValue(vo.getStrItemNameComboWS(),
						"0", "0^Select Value", true);

			}

			formBean.setStrItemNameCombo(ItemCmb);

			if (vo.getStrManufactureComboWS().size() == 0
					|| vo.getStrManufactureComboWS() == null) {
				ItemCmb = "<option value='0'>Select Value</option>";
			} else {
				ItemCmb = hisutil.getOptionValue(vo.getStrManufactureComboWS(),
						"", "0^Select Value", false);

			}

			formBean.setStrSuppliedByValues(ItemCmb);

			if (vo.getStrCurrencyCodeWs().size() == 0
					|| vo.getStrCurrencyCodeWs() == null) {
				ItemCmb = "<option value='0'>Select Value</option>";
			} else {
				ItemCmb = hisutil.getOptionValue(vo.getStrCurrencyCodeWs(),
						MmsConfigUtil.DEFAULT_CURRENCY_CODE, "0^Select Value",
						true);

			}

			if (vo.getStrStockStatusWs().size() == 0
					|| vo.getStrStockStatusWs() == null) {
				strTemp = "<option value='0'>Select Value</option>";
			} else {
				strTemp = hisutil.getOptionValue(vo.getStrStockStatusWs(),
						"10", "0^Select Value", true);
			}

			formBean.setStrStockStatusValues(strTemp);

			MmsConfigUtil mmscofigutil = new MmsConfigUtil(hosCode);
			formBean.setStrIssueRateConfigFlg(mmscofigutil
					.getStrIssueRateConfigFlg());
			formBean.setStrConfigIssueRate(mmscofigutil.getStrConfigIssueRate());

			formBean.setStrCurrencyCodeValues(ItemCmb);

		} catch (Exception e) {

			strmsgText = "Drug Inventory.DrugInventoryTransDATA.initialAdd(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->initialAdd()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			hisutil = null;
		}

	}

	/**
	 * to display the Store Name and Group Name on Add page.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */

	public static void initialAddDummy(
			ItemInventoryWithProgramTransFB formBean, HttpServletRequest request) {

		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String strmsgText = "";
		HisUtil hisutil = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			hisutil = new HisUtil("mms", "DrugInventoryTransDATA");

			if (!formBean.getComboValue().equals("")) {
				String strCmbNames = formBean.getComboValue();
				String[] temp = strCmbNames.replace("^", "#").split("#");
				formBean.setStrStoreName(temp[0]);
				formBean.setStrGroupName(temp[1]);

			}

			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			formBean.setStrCtDate(ctDate);

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid = request.getSession().getAttribute("SEATID")
					.toString();

			String strStoreId = formBean.getCombo()[0].split("\\^")[0];
			String strGroupId = formBean.getCombo()[1];
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId(strStoreId);
			vo.setStrGroupId(strGroupId);

			vo.setStrItemCategoryNo("10");

			formBean.setStrGroupId(strGroupId);
			formBean.setStrDefaultCurrencyCode(MmsConfigUtil.DEFAULT_CURRENCY_CODE);

			bo.initialAddDummy(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			formBean.setStrGroupNameCombo(vo.getStrGroupNameCombo());
			MmsConfigUtil mmscofigutil = new MmsConfigUtil(hosCode);
			formBean.setStrIssueRateConfigFlg(mmscofigutil
					.getStrIssueRateConfigFlg());
			formBean.setStrConfigIssueRate(mmscofigutil.getStrConfigIssueRate());

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "Drug Inventory.DrugInventoryTransDATA.initialAddDummy(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->initialAdd()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			hisutil = null;
		}

	}

	/**
	 * to display the Store Name and Group Name on Add page.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */

	public static void initialAddPHD(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request) {

		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String strmsgText = "";
		HisUtil hisutil = null;
		String ItemCmb = "";
		String manufCmb = "";
		String strProgrammeCombo = "";

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			hisutil = new HisUtil("mms", "DrugInventoryTransDATA");

			/*if (!formBean.getComboValue().equals("")) {
				String strCmbNames = formBean.getComboValue();
				String[] temp = strCmbNames.replace("^", "#").split("#");
				formBean.setStrStoreName(temp[0]);
				formBean.setStrGroupName(temp[1]);
			}
			 */			
			String strCmbNames =request.getParameter("catId");
			String[] temp = strCmbNames.replace("^", "#").split("#");
			
			
			
			formBean.setStrStoreName(request.getParameter("storeName"));
			
			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			formBean.setStrCtDate(ctDate);

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();
			
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId(request.getParameter("storeId"));
			vo.setStrGroupId(request.getParameter("groupId"));
			vo.setStrItemCategoryNo(temp[0]);
			formBean.setStrGroupId(request.getParameter("groupId"));
			formBean.setStrDefaultCurrencyCode(MmsConfigUtil.DEFAULT_CURRENCY_CODE);
			// Calling BO Method
			bo.initialPhdADD(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			//System.out.println("vo.getStrGroupNameCombo()"+vo.getStrGroupNameCombo());
			//formBean.setStrGroupNameCombo(vo.getStrGroupNameCombo());
			MmsConfigUtil mmscofigutil = new MmsConfigUtil(hosCode);
			formBean.setStrIssueRateConfigFlg(mmscofigutil.getStrIssueRateConfigFlg());
			formBean.setStrConfigIssueRate(mmscofigutil.getStrConfigIssueRate());

			if (vo.getStrItemNameComboWS().size() == 0
					|| vo.getStrItemNameComboWS() == null) {
				ItemCmb = "<option value='0'>Select Value</option>";
			} else {
				ItemCmb = hisutil.getOptionValue(vo.getStrItemNameComboWS(),
						"0", "0^Select Value", true);

			}
			formBean.setStrItemBrandCombo(ItemCmb);

			/*if (vo.getStrProgNameComboWS().size() == 0
					|| vo.getStrProgNameComboWS() == null) {
				strProgrammeCombo = "<option value='0'>Select Value</option>";
			} else {
				strProgrammeCombo = hisutil.getOptionValue(vo.getStrProgNameComboWS(), "","", true);

			}
			formBean.setStrProgrammeCombo(strProgrammeCombo);*/

			if (vo.getStrManufactureComboWS().size() == 0 || vo.getStrManufactureComboWS() == null) {
				manufCmb = "<option value='0'>Select Value</option>";
			} else {
				manufCmb = hisutil.getOptionValue(vo.getStrManufactureComboWS(), "", "0^Select Value",	false);
			}
			formBean.setStrManufactureCombo(manufCmb);			
			formBean.setStrStoreTypeFlag(vo.getStrStoreTypeFlag());

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "Drug Inventory.DrugInventoryTransDATA.initialAddDummy(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->initialAdd()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			hisutil = null;
		}

	}
	

	
	/**
	 * Modify.
	 * 
	 * @param formBean
	 *            the form bean
	 */
	public static void modify(ItemInventoryWithProgramTransFB formBean,HttpServletRequest request, HttpServletResponse response) {
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String strmsgText = "";
		HisUtil hisutil = null;
		String ItemCmb = "";
		String manufCmb = "";
		String strProgrammeCombo = "";
		String path = "";		
		String combo[] = null;
		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();
			hisutil = new HisUtil("mms", "DrugInventoryTransDATA");
			
			combo = request.getParameterValues("combo");
			path = "/mms" + request.getParameter("cnt_page") + ".cnt";

			if (request.getParameter("cnt_page") == null) {

				path = request.getParameter("strPath");
			}

			formBean.setStrPath(path.trim());
			
			if (!formBean.getComboValue().equals("")) {
				
				String strCmbNames = request.getParameter("concatVal");
				String[] temp = strCmbNames.replace("^", "#").split("#");
				formBean.setStrStoreName(temp[0]);
				System.out.println("strCmbNames"+temp[0]);
				formBean.setStrGroupName(temp[1]);
				//formBean.setStrProgrammeName(temp[2]);
				formBean.setStrItemName(temp[3]);
				formBean.setStrItemBrandId(temp[4]);
				formBean.setStrSelBatch(temp[5]);
			}

			String ctDate = hisutil.getASDate("dd-MMM-yyyy");
			formBean.setStrCtDate(ctDate);
			String strCmbNames = request.getParameter("catId");
			String[] temp = strCmbNames.replace("^", "#").split("#");
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrStoreId(request.getParameter("storeId"));
			vo.setStrGroupId(request.getParameter("groupId"));

			vo.setStrItemCategoryNo(request.getParameter("catId").split("\\^")[0]);

			formBean.setStrGroupId(request.getParameter("groupId"));
			formBean.setStrStoreId(request.getParameter("storeId"));
			formBean.setStrDefaultCurrencyCode(MmsConfigUtil.DEFAULT_CURRENCY_CODE);
			// Calling BO Method
			bo.initialPhdADD(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			formBean.setStrGroupNameCombo(vo.getStrGroupNameCombo());
			MmsConfigUtil mmscofigutil = new MmsConfigUtil(formBean.getStrHospitalCode());
			formBean.setStrIssueRateConfigFlg(mmscofigutil
					.getStrIssueRateConfigFlg());
			formBean.setStrConfigIssueRate(mmscofigutil.getStrConfigIssueRate());

			if (vo.getStrItemNameComboWS().size() == 0
					|| vo.getStrItemNameComboWS() == null) {
				ItemCmb = "<option value='0'>Select Value</option>";
			} else {
				ItemCmb = hisutil.getOptionValue(vo.getStrItemNameComboWS(),
						"0", "0^Select Value", true);

			}
			formBean.setStrItemBrandCombo(ItemCmb);

			/*if (vo.getStrProgNameComboWS().size() == 0
					|| vo.getStrProgNameComboWS() == null) {
				strProgrammeCombo = "<option value='0'>Select Value</option>";
			} else {
				strProgrammeCombo = hisutil
						.getOptionValue(vo.getStrProgNameComboWS(), "0",
								"0^Select Value", true);

			}
			formBean.setStrProgrammeCombo(strProgrammeCombo);
			*/
			if (vo.getStrManufactureComboWS().size() == 0
					|| vo.getStrManufactureComboWS() == null) {
				manufCmb = "<option value='0'>Select Value</option>";
			} else {
				manufCmb = hisutil.getOptionValue(
						vo.getStrManufactureComboWS(), "", "0^Select Value",
						false);
			}
			formBean.setStrManufactureCombo(manufCmb);
			formBean.setStrStoreTypeFlag(vo.getStrStoreTypeFlag());

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "DrugInventory.DrugInventoryTransDATA.modify(vo) --> "	+ e.getMessage();
			HisException eObj = new HisException("mms",	"DrugInventoryTransDATA->insertRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "	+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;

		} finally {

			bo = null;
			vo = null;
			hisutil = null;

		}
	}
	

	/**
	 * To get values of SubgroupCombo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */

	public static void itemName(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		HisUtil hisutil = null;
		try {

			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();

			String subGroupId = (String) request.getParameter("strSubGroupId");
			String strGroupId = (String) request.getParameter("GroupId");
			String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];
			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrSubGroupId(subGroupId);
			vo.setStrGroupId(strGroupId);

			bo.getItemName(vo);
			vo.getStrItemNameComboWS();

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			hisutil = new HisUtil("mms", "DrugInventoryTransDATA");
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

			strmsgText = "DrugInventory.DrugInventoryTransDATA.itemName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->itemName()", strmsgText);
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
	 * To get values of SubgroupCombo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */

	public static void subGroupCombo(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		HisUtil hisutil = null;
		try {

			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();

			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String strGroupId = (String) request.getParameter("GroupId");
			String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrGroupId(strGroupId);

			bo.subGrpAndItemCmb(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			hisutil = new HisUtil("mms", "DrugInventoryTransDATA");

			String subGrpCmbStr = hisutil.getOptionValue(
					vo.getStrSubGroupComboWs(), "0", "0^Select Value", false);
			String itemCmbStr = hisutil.getOptionValue(
					vo.getStrItemNameComboWS(), "0", "0^Select Value", false);

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(subGrpCmbStr + "@@" + itemCmbStr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "DrugInventory.DrugInventoryTransDATA.subGroupCombo(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->itemName()", strmsgText);
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
	 * To get values of Existing Batch Number for Selected Drug.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the group name
	 */

	public static void getGroupName(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String itemId = (String) request.getParameter("strItemId");

			vo.setStrItemCategoryNo("10");

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);

			bo.getGroupId(vo);

			String cmbstr = vo.getStrGroupId() + "@@" + vo.getStrGroupName();

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransDATA.getExistingBatchList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getExistingBatchList()",
					strmsgText);
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
	 * To get values of Existing Batch Number In Stock Before Save for Selected
	 * Drug.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the existing batch in stock
	 */

	public static void getExistingBatchInStock(
			ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String itemId = "0";
			String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];
			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");
			String strGroupId = (String) request.getParameter("strGroupId");
			String strBatchNo = (String) request.getParameter("strBatchNo");

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrItemBrandId(strItemBrandId);

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);
			vo.setStrGroupId(strGroupId);
			vo.setStrBatchNo(strBatchNo);
			// Calling BO Method
			bo.getExistingBatchInStock(vo);

			formBean.setStrBatchExistInStockFlg(vo.getStrBatchExistInStockFlg());

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			String cmbstr = formBean.getStrBatchExistInStockFlg();

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransDATA.getExistingBatchInStock(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getExistingBatchInStock()",
					strmsgText);
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
	 * To get values of Existing Batch Number In Stock Before Save for Selected
	 * Supplier.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the existing supp batch in stock
	 */

	public static void getExistingSuppBatchInStock(
			ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();

			String storeId = (String) request.getParameter("storeId").split("\\^")[0];
			String supplierId = (String) request.getParameter("supplierId");
			String batchNo = (String) request.getParameter("batchNo");
			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");
			String Index = (String) request.getParameter("Index");

			vo.setStrStoreId(storeId);
			vo.setStrItemCategoryNo("10");
			vo.setStrItemBrandId(strItemBrandId);
			vo.setStrSupplierId(supplierId);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrBatchNo(batchNo);

			// Calling BO Method
			bo.getExistingSuppBatchInStock(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			String cmbstr = formBean.getStrBatchExistSuppBatchInStockFlg();

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr + "^" + Index);
			} catch (Exception e) {
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransDATA.getExistingBatchInStock(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getExistingBatchInStock()",
					strmsgText);
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
	 * To get values of Existing Batch Number for Selected Drug.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the existing batch list
	 */

	public static void getExistingBatchList(
			ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String itemId = "0";
			String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];
			String strItemBrandId = (String) request.getParameter("strItemBrandId");
			String strIndex = (String) request.getParameter("index");
			String strProgrammeId=request.getParameter("strProgrammeId");
			
			System.out.println("catid"+request.getParameter("catId"));
			
			
			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo(request.getParameter("catId"));
			vo.setStrItemBrandId(strItemBrandId);
			vo.setStrProgrammeId(strProgrammeId);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);

			vo.setStrMode("2");
			// Calling BO method
			bo.getExistingBatchList(vo);

			vo.getStrExistingBatchComboWS();

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "DrugInventoryTransDATA");
			String cmbstr = hisutil.getOptionValue(
					vo.getStrExistingBatchComboWS(), "", "0^New Batch/SerialNo", false);
			

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr + "$" + strIndex);
			} catch (Exception e) {
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransWithProgramDATA.getExistingBatchList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getExistingBatchList()",
					strmsgText);
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
	 * To get values of Existing Batch Number for Selected Drug.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the existing batch list
	 */

	public static void getExistingBatchListModify(
			ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();
			String strCmbNames = request.getParameter("storeId");
			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID").toString();
			String strStoreId = request.getParameter("storeId");
			String strItemBrandId = (String) request.getParameter("strItemBrandId");
			String strIndex = (String) request.getParameter("index");
			String strProgrammeId=request.getParameter("strProgrammeId");
			String strSelBatch = request.getParameter("batchSel"); 
			vo.setStrStoreId(request.getParameter("storeId"));
			vo.setStrItemCategoryNo(request.getParameter("catId"));
			vo.setStrItemBrandId(strItemBrandId);
			vo.setStrProgrammeId(strProgrammeId);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(strSelBatch);

			vo.setStrMode("4");
			// Calling BO method
			bo.getExistingBatchList(vo);

			vo.getStrExistingBatchComboWS();

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "DrugInventoryTransDATA");
			String cmbstr = hisutil.getOptionValue(
					vo.getStrExistingBatchComboWS(), "", "", false);
			

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr + "$" + strIndex);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "DrugInventory.DrugInventoryTransWithProgramDATA.getExistingBatchListModify(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getExistingBatchListModify()",
					strmsgText);
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
	 * To get values of Existing Batch Number for Selected Drug.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the rate unit
	 */

	public static void getRateUnit(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String cmbstr = "";

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();

			//String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];
			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");
			String strIndex = (String) request.getParameter("index");

			//vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo(formBean.getStrItemBrandId().replace("^","#").split("#")[0].substring(0,2));
			vo.setStrItemBrandId(strItemBrandId.split("\\^")[0]);
			vo.setStrInventoryUnitId(strItemBrandId.split("\\^")[1]);
			

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);

			// Calling BO Method
			bo.getRateUnitCmb(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "ItemInventoryWithProgramTransDATA");

			if (vo.getStrUnitListWs().size() == 0
					|| vo.getStrUnitListWs() == null) {
				cmbstr = "<option value='0'>Select Value</option>";
			} else {
				cmbstr = hisutil.getOptionValue(vo.getStrUnitListWs(), "", "",
						true);

			}

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr + "$" + strIndex);
			} catch (Exception e) {
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "ItemInventory.ItemInventoryWithProgramTransDATA.getRateUnit(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ItemInventoryWithProgramTransDATA->getRateUnit()", strmsgText);
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
	 * To get values of Existing Batch Number for Selected Drug.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the existing batch details
	 */

	public static void getExistingBatchDetails(
			ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String cmbstr;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String itemId = (String) request.getParameter("strItemId");
			String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];
			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");
			String strExistingBatchId = (String) request
					.getParameter("strExistingBatchId");

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrStockStatus("10");
			vo.setStrItemBrandId(strItemBrandId);
			vo.setStrBatchNo(strExistingBatchId);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);

			bo.getExistingBatchDetails(vo);

			cmbstr = vo.getStrStoreName() + "^" + vo.getStrGroupName() + "^"
					+ vo.getStrSubGroupName() + "^" + vo.getStrItemName() + "^"
					+ vo.getStrItemBrandName() + "^" + ""
					+ vo.getStrManufactureName() + "^" + vo.getStrBatchNo()
					+ "^" + vo.getStrExpiryDate() + "^" + ""
					+ vo.getStrManufactureDate() + "^"
					+ vo.getStrInHandQuantity() + "^"
					+ vo.getStrInHandQuantityUnitName() + "^"
					+ vo.getStrInHandQuantityUnitID() + "^" + ""
					+ vo.getStrRate() + "^" + vo.getStrUnitRateName() + "^"
					+ vo.getStrUnitRateID() + "^" + vo.getStrSalePrice() + "^"
					+ "" + vo.getStrUnitNameSale() + "^"
					+ vo.getStrUnitSaleID() + "^" + vo.getStrPoNo() + "^"
					+ vo.getStrPoDate() + "^" + vo.getStrSuppliedBy() + "^"
					+ "" + vo.getStrReceivedDate() + "^"
					+ vo.getStrCurrencyCode() + "^" + vo.getStrCurrencyValue()
					+ "^" + vo.getStrBillNo() + "^" + "" + vo.getStrBillDate()
					+ "^" + vo.getStrItemSpecification() + "^"
					+ vo.getStrManufactureId() + "^" + vo.getStrRackNumber();

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(cmbstr);

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransDATA.getExistingBatchList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getExistingBatchList()",
					strmsgText);
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
	 * To get values of Item Name Combo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */

	public static void itemBrandName(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String ItemCmb = "";

		try {

			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String itemId = (String) request.getParameter("strItemId");
			String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];
			String strGroupId = (String) request.getParameter("strGroupId");

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrGroupId(strGroupId);
			vo.setStrSubGroupId("0");

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);

			bo.getItemBrandName(vo);
			vo.getStrItemNameComboWS();

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "DrugInventoryTransDATA");
			if (vo.getStrItemNameComboWS().size() == 0
					|| vo.getStrItemNameComboWS() == null) {
				ItemCmb = "<option value='0'>Select Value</option>";
			} else {
				ItemCmb = hisutil.getOptionValue(vo.getStrItemNameComboWS(),
						"0", "0^Select Value", true);

			}
			formBean.setStrItemBrandCombo(ItemCmb);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(ItemCmb);

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransDATA.itemBrandName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->itemBrandName()", strmsgText);
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
	 * To get Brand Details of a Branded Item.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the brand details
	 */

	public static void getBrandDetails(
			ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {

			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();

			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");

			vo.setStrItemBrandId(strItemBrandId);
			vo.setStrHospitalCode(strHospitalCode);

			bo.getBrandDetails(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			String strBrandDetails = vo.getStrRegFlag() + "^"
					+ vo.getStrBrandDetails() + "^" + vo.getStrQcTypeFlg();

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strBrandDetails);

		} catch (Exception e) {

			e.printStackTrace();

			String strmsgText = "DrugInventory.DrugInventoryTransDATA.getBrandDetails(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getBrandDetails()", strmsgText);
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
	 * To get values of Manufacture Name combo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */

	public static void manufectuteName(
			ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {

			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			formBean.setStrModuleId(MmsConfigUtil.MODULE_ID);
			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");
			String strStoreId = (String) request.getParameter("storeId").split("\\^")[0];

			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrItemBrandId(strItemBrandId);

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);

			bo.getmanufectuteName(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "DrugInventoryTransDATA");
			String cmbstr = hisutil.getOptionValue(
					vo.getStrManufactureComboWS(), "",
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

			strmsgText = "DrugInventory.DrugInventoryTransDATA.manufectuteName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->manufectuteName()", strmsgText);
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
	 * to insert the data.
	 * 
	 * @param formBean
	 *            the form bean
	 */

	public static void insert2(ItemInventoryWithProgramTransFB formBean) {
		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			String[] tmp = formBean.getStrExistingBatchDetails().split("\\^");

			vo = (ItemInventoryWithProgramTransVO) TransferObjectFactory
					.populateData(
							"mms.transactions.vo.ItemInventoryWithProgramTransVO",
							formBean);
			vo.setStrBatchNo(tmp[6]);
			vo.setStrItemCategoryNo("10");
			vo.setStrGroupId(formBean.getStrGroupId());
			vo.setStrSubGroupId(formBean.getStrSubGroupId());
			vo.setStrExpiryDate(tmp[7]);
			vo.setStrManufactureDate(tmp[8]);
			vo.setStrStockStatus(formBean.getCombo()[2]);
			vo.setStrInHandQuantity(formBean.getStrInHandQuantity());
			vo.setStrInHandQuantityUnitID(tmp[11]);
			vo.setStrManufactureId(tmp[27]);
			vo.setStrRate(tmp[12]);
			vo.setStrUnitRateID(tmp[14]);
			vo.setStrSalePrice(tmp[15]);
			vo.setStrUnitSaleID(tmp[17]);
			vo.setStrPoNo(tmp[18]);
			vo.setStrPoDate(tmp[19]);
			vo.setStrSuppliedBy(tmp[20]);
			vo.setStrReceivedDate(tmp[21]);
			vo.setStrCurrencyCode(formBean.getStrCurrencyCode());
			vo.setStrCurrencyValue(formBean.getStrCurrencyValue());
			vo.setStrItemSpecification(tmp[26]);
			vo.setStrBillNo(tmp[24]);
			vo.setStrBillDate(tmp[25]);
			vo.setStrRackNumber(tmp[28]);
			vo.setStrDrugTotCost(formBean.getStrDrugTotCost());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			vo.setStrStockStatus(formBean.getStrStockStatus());

			bo.insert(vo);

			if (vo.getStrMsgType().equals("5")) {
				formBean.setStrErr(vo.getStrMsgString());

			} else {
				if (vo.getStrMsgType().equals("1")) {
					formBean.setStrErr(vo.getStrMsgString());

					throw new Exception(vo.getStrMsgString());
				} else {
					formBean.setStrMsg("Data Has been Successfully Saved");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			strmsgText = "DrugInventory.DrugInventoryTransDATA.insert(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->insertRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			vo = null;

		}
	}

	/**
	 * to insert the data.
	 * 
	 * @param formBean
	 *            the form bean
	 */

	public static void insert(ItemInventoryWithProgramTransFB formBean) {
		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		MmsConfigUtil mmsUtil = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();

			vo = (ItemInventoryWithProgramTransVO) TransferObjectFactory
					.populateData(
							"mms.transactions.vo.ItemInventoryWithProgramTransVO",
							formBean);

			mmsUtil = new MmsConfigUtil(formBean.getStrHospitalCode());

			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			vo.setStrInHandQuantityUnitID(formBean.getStrInHandQuantityUnitID()
					.replace("^", "#").split("#")[0]);
			vo.setStrUnitRateID(formBean.getStrUnitRateID().replace("^", "#")
					.split("#")[0]);
			vo.setStrUnitSaleID(formBean.getStrUnitSaleID().replace("^", "#")
					.split("#")[0]);
			vo.setStrItemCategoryNo("10");

			if (formBean.getStrBatchNo().trim().equals("0")
					|| formBean.getStrBatchNo().trim().length() < 1) {
				vo.setStrBatchNo(mmsUtil.getStrBatchNo());
			}

			vo.setStrStockStatus(formBean.getStrStockStatus());

			bo.insert(vo);

			if (vo.getStrMsgType().equals("5")) {
				formBean.setStrErr(vo.getStrMsgString());

			} else {
				if (vo.getStrMsgType().equals("1")) {
					formBean.setStrErr(vo.getStrMsgString());

					throw new Exception(vo.getStrMsgString());
				} else {
					formBean.setStrMsg("Data Has been Successfully Saved");
				}
			}

		} catch (Exception e) {

			strmsgText = "DrugInventory.DrugInventoryTransDATA.insert(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->insertRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			vo = null;
			mmsUtil = null;

		}
	}

	/**
	 * to insert the data.
	 * 
	 * @param formBean
	 *            the form bean
	 */

	public static void dummyinsert(ItemInventoryWithProgramTransFB formBean) {
		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = (ItemInventoryWithProgramTransVO) TransferObjectFactory
					.populateData("mms.transactions.vo.ItemInventoryWithProgramTransVO",formBean);

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			String strCmbNames = formBean.getStrCatId();
			String[] temp = strCmbNames.replace("^", "#").split("#");
			vo.setStrItemCategoryNo(temp[0]);
			vo.setStrBatchNo(formBean.getStrBatchNo());
			vo.setStrManufactureId(formBean.getStrManufactureId());
			vo.setStrGroupCmbId(formBean.getStrGroupCmbId());
			vo.setStrRate(formBean.getStrRate());
			vo.setStrUnitRateID(formBean.getStrUnitRateID());
			vo.setStrDrugTotCost(formBean.getStrDrugTotCost());
		
			bo.dummyinsert(vo);			
			
			
			formBean.setTotalBatchNumber(vo.getTotalBatchNumber());
			formBean.setStrItemBrandId(vo.getStrItemBrandId());
			formBean.setStrStoreId(vo.getStrStoreId());

			if (vo.getStrMsgType().equals("1")) {
				formBean.setStrErr(vo.getStrMsgString());
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrMsg("Data Has been Successfully Saved");
				formBean.setStrSavedDrugName(formBean.getStrSelDrugName());
				formBean.setStrSavedBatchName(vo.getStrSavedBatchName());
				formBean.setStrIsDataSaveFlg("1");
				
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransDATA.dummyinsert(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms","DrugInventoryTransDATA->insertRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;

		} finally {
			bo = null;
			vo = null;

		}
	}
	
	public static void updateRecord(ItemInventoryWithProgramTransFB formBean,HttpServletRequest request) {
		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {
			bo = new ItemInventoryWithProgramTransBO();
			vo = (ItemInventoryWithProgramTransVO) TransferObjectFactory
					.populateData("mms.transactions.vo.ItemInventoryWithProgramTransVO",formBean);

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrItemCategoryNo(formBean.getStrCatId().split("\\^")[0]);
			vo.setStrBatchNo(formBean.getStrBatchNo());
			vo.setStrManufactureId(formBean.getStrManufactureId());
			vo.setStrGroupCmbId(formBean.getStrGroupCmbId());
			vo.setStrRate(formBean.getStrRate());
			vo.setStrUnitRateID(formBean.getStrUnitRateID());
			vo.setStrDrugTotCost(formBean.getStrDrugTotCost());
			bo.updateRecord(vo);			

			if (vo.getStrMsgType().equals("1")) {
				formBean.setStrErr(vo.getStrMsgString());
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrMsg("Data Has been Updated Successfully");
				formBean.setStrSavedDrugName(formBean.getStrSelDrugName());
				formBean.setStrSavedBatchName(vo.getStrSavedBatchName());
				formBean.setStrIsDataSaveFlg("1");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "DrugInventory.DrugInventoryTransDATA.updateRecord(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms","DrugInventoryTransDATA->updateRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;

		} finally {
			bo = null;
			vo = null;

		}
	}

	

	/**
	 * To get values of Unit on the basics of Item Name combo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */

	public static void unitNameCombo(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {

			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			System.out.println("cat id is "+request.getParameter("catId"));
			String itemId = (String) request.getParameter("strItemId");
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrItemId(itemId);
			vo.setStrItemCategoryNo(request.getParameter("catId"));
			vo.setStrModuleId(MmsConfigUtil.MODULE_ID);
			bo.unitNameCombo(vo);
			vo.getStrItemNameComboWS();

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			HisUtil hisutil = new HisUtil("mms", "DrugInventoryTransDATA");
			String cmbstr = hisutil.getOptionValue(vo.getStrUnitRateComboWS(),
					vo.getStrUnitRateID(), "0^Select Value", false);

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.DrugInventoryTransDATA.unitNameCombo(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->unitNameCombo()", strmsgText);
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

	/**
	 * Update.
	 * 
	 * @param formBean
	 *            the form bean
	 * @return true, if successful
	 */
	public static boolean update(ItemInventoryWithProgramTransFB formBean) {
		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		boolean res = false;

		try {
			bo = new ItemInventoryWithProgramTransBO();

			vo = (ItemInventoryWithProgramTransVO) TransferObjectFactory
					.populateData(
							"mms.transactions.vo.ItemInventoryWithProgramTransVO",
							formBean);

			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			String[] strValues = formBean.getStrChk().replace("@", "#")
					.split("#");

			vo.setStrStoreId(strValues[0].split("\\^")[0]);
			vo.setStrItemId(strValues[1]);
			vo.setStrItemBrandId(strValues[2]);
			vo.setStrBatchNo(strValues[3]);

			vo.setStrInHandQuantityUnitID(formBean.getStrInHandQuantityUnitID()
					.replace("^", "#").split("#")[0]);
			vo.setStrUnitRateID(formBean.getStrUnitRateID().replace("^", "#")
					.split("#")[0]);
			vo.setStrUnitSaleID(formBean.getStrUnitSaleID().replace("^", "#")
					.split("#")[0]);
			vo.setStrItemCategoryNo("10");

			vo.setStrOldStockStatus(formBean.getCombo()[2]);

			bo.update(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			res = vo.getBExistStatus();

		} catch (Exception e) {

			strmsgText = "DrugInventory.DrugInventoryTransDATA.update(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->update()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			vo = null;

		}

		return res;

	}

	/**
	 * Gets the checks if is modify.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the checks if is modify
	 */
	public static void getIsModify(ItemInventoryWithProgramTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = "";
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;

		try {

			bo = new ItemInventoryWithProgramTransBO();
			vo = new ItemInventoryWithProgramTransVO();

			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String strTemp = (String) request.getParameter("strChkVal");
			String strTempVal[] = strTemp.replace("@", "#").split("#");

			vo.setStrStoreId(strTempVal[0]);
			vo.setStrItemId(strTempVal[1]);
			vo.setStrItemBrandId(strTempVal[2]);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrBatchNo(strTempVal[3]);

			bo.getModifyValue(vo);

			String cmbstr = vo.getStrModifyValue();

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "DrugInventoryTransDATA.getIsModify(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"DrugInventoryTransDATA->getIsModify()", strmsgText);
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
	
	
	public static void printVoucher(HttpServletRequest request,
			HttpServletResponse response, ItemInventoryWithProgramTransFB formBean) 
	{

		String strItemAddedView = "";
		//boolean printFlag = false;
		
		ItemInventoryWithProgramTransBO bo = null;
		ItemInventoryWithProgramTransVO vo = null;
		String strMode = "";
		String strStoreId = "";
		String strIssueNo = "";

		try {

			    strMode = (String) request.getParameter("strMode");
			 strStoreId = (String) request.getParameter("strStoreId");
			 strIssueNo = (String) request.getParameter("strIssueNo");			
			         bo = new ItemInventoryWithProgramTransBO();
			         vo = new ItemInventoryWithProgramTransVO();
			         
			         HelperMethods.populate(vo, formBean);
			
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString()); 
			formBean.setStrStoreId(strStoreId);

			 bo.getItemDtls(vo);
			
			formBean.setWsItemDetails(vo.getWsItemDetails());;
		
 		/*	UserVO userVO=ControllerUTIL.getUserVO(request);
			
			formBean.setUserName(userVO.getUserName());*/
			
			strItemAddedView = ItemInventoryTransWithProgramHLP.getAddItemVoucherView(formBean);
			
			
			
			
			//strSearchItemInitView = IssueTransHLP.getIssueDtlsInitView(formBean);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strItemAddedView);

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			 
			 String strmsgText = "IssueTransDATA.issueDtlsInit(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"IssueTransDATA.issueDtlsInit(vo) --> ", strmsgText);
			eObj.printStackTrace();
			eObj = null;
			  
		}finally {

			bo = null;
			vo = null;
		}
	}

}
