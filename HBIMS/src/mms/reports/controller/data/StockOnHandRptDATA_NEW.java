/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         StockOnHandRptDATA.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package mms.reports.controller.data;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mms.reports.bo.StockOnHandRptBO_NEW;
import mms.reports.controller.fb.StockOnHandRptFB_NEW;
import mms.reports.vo.StockOnHandRptVO_NEW;

// TODO: Auto-generated Javadoc
/**
 * The Class StockOnHandRptDATA.
 */
public class StockOnHandRptDATA_NEW {


	/**
	 * Gets the district list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the district list
	 */
	public static void getDistrictList(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			String strUserLevel = request.getSession()
					.getAttribute("USER_LEVEL").toString();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			formBean.setStrUserLevel(strUserLevel);
			voObj.setStrUserLevel(strUserLevel);

			String strCircleId = request.getParameter("circleId");

			voObj.setStrCircleId(strCircleId);

			bo.getDistrictList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			String strDistrictCombo = "";

			if (strUserLevel.equals("1") || strUserLevel.equals("2")) {

				if (voObj.getStrDistrictWS() != null
						&& voObj.getStrDistrictWS().size() > 0) {
					strDistrictCombo = util.getOptionValue(
							voObj.getStrDistrictWS(), "0", "0^All", false);
				}

				else {
					strDistrictCombo = "<option value='0'>All</option>";
				}

			} else {
				if (strUserLevel.equals("3")) {
					if (voObj.getStrDistrictWS() != null
							&& voObj.getStrDistrictWS().size() > 0) {
						strDistrictCombo = util.getOptionValue(
								voObj.getStrDistrictWS(),
								voObj.getStrDistrictId(), "0^Select Value",
								false);
					}

					else {
						strDistrictCombo = "<option value='0'>All</option>";
					}
				}

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strDistrictCombo);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getDistrictList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getDistrictList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

	/**
	 * Gets the store combo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the store combo
	 */
	public static void getStoreCombo(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			bo.getStoreList(voObj);
			voObj.setStrItemCatId("10");
			voObj.setStrStoreId("0");
			voObj.setStrGroupId("0");
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			String strStoreVal = "";

			if (voObj.getStrStoreWs() != null
					&& voObj.getStrStoreWs().size() > 0) {

				if (voObj.getStrStoreWs().next()) {
					voObj.setStrStoreId(voObj.getStrStoreWs().getString(1));
				}
				voObj.getStrStoreWs().beforeFirst();

				strStoreVal = util.getOptionValue(voObj.getStrStoreWs(), "0",
						"0^All", false);

			} else
				strStoreVal = "<option value='0'>All</option>";

			String temp,temp1,temp2;
			formBean.setStrStoreValues(strStoreVal);
			bo.getItemCatList(voObj);
			if (voObj.getStrItemCatWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrItemCatWs(), "0", "",
						true);

			} else {

				temp = "<option value='0'>SelectValue</option>";
			}
			formBean.setStrItemCatgCombo(temp);
			
			/*bo.getGroupList(voObj);
			if (voObj.getStrGroupCmbWS().size() != 0) {

				temp1 = util.getOptionValue(voObj.getStrGroupCmbWS(), "0",
						"0^All", true);

			} else {

				temp1 = "<option value='0'>All</option>";
			}
			formBean.setStrGroupCombo(temp1);
			bo.getitemTypecmb(voObj);
			if (voObj.getItemTypeWs().size() != 0) {

				temp2 = util.getOptionValue(voObj.getItemTypeWs(), "0",
						"0^All", true);

			} else {

				temp2 = "<option value='0'>All</option>";
			}
			formBean.setStrItemTypeValues(temp2);*/
		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getStoreCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getStoreCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

	/**
	 * Gets the store type list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the store type list
	 */
	public static void getStoreTypeList(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo =null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		String strStoreTypeId="";
		try {

			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			String strUserLevel = request.getSession()
					.getAttribute("USER_LEVEL").toString();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			formBean.setStrUserLevel(strUserLevel);
			voObj.setStrUserLevel(strUserLevel);
			
			
			if(request.getParameter("strDistrictStoreId").length()>9){
				strStoreTypeId = request.getParameter("strDistrictStoreId").split("\\^")[1];
			}
				voObj.setStrStoreId(request.getParameter("strDistrictStoreId"));

			voObj.setStrStoreTypeId(strStoreTypeId);

			bo.getStoreTypeList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			String strStoreTypeCombo = "";

			if ((voObj.getStrStoreTypeWS() != null)
					&&( voObj.getStrStoreTypeWS().size() > 0)  ) {
 
				 
				if ( !voObj.getStrStoreId().equals("0"))
				{
					strStoreTypeCombo =
						"<option value='1'>Only District Warehouse</option>" + "<option value='2'>All Except District Warehouse</option>"
								+ "<option value='3'>All Including District Warehouse</option>";
				}
				else
				{
					strStoreTypeCombo = "<option value='0'>All</option>";
					
				}
				
				String temp = util.getOptionValue(voObj.getStrStoreTypeWS(), "1", "", false);
				
				strStoreTypeCombo = strStoreTypeCombo + temp;
}

			

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strStoreTypeCombo);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getStoreTypeList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getStoreTypeList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

	/**
	 * Gets the sub store combo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the sub store combo
	 */
	public static void getSubStoreCombo(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			String strUserLevel = request.getSession().getAttribute("USER_LEVEL").toString();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			formBean.setStrUserLevel(strUserLevel);
			voObj.setStrUserLevel(strUserLevel);

			String strCircleId = request.getParameter("strCircleId");
			String strDistrictId = request.getParameter("strDistrictId");
			String strDistrictStoreId = request
					.getParameter("strDistrictStoreId");
			String strStoreTypeId = request.getParameter("strStoreTypeId");

			voObj.setStrCircleId(strCircleId);
			voObj.setStrDistrictId(strDistrictId);

			voObj.setStrDistrictStoreId(strDistrictStoreId);
			voObj.setStrStoreTypeId(strStoreTypeId);

			voObj.setStrMode("11");
			bo.getSubStoreList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			String strStoreVal = "";

			if (voObj.getStrStoreWs().next()) {
				voObj.setStrStoreId(voObj.getStrStoreWs().getString(1));
			}
			voObj.getStrStoreWs().beforeFirst();

			if (voObj.getStrStoreWs() != null
					&& voObj.getStrStoreWs().size() > 0) {

				strStoreVal = util.getOptionValue(voObj.getStrStoreWs(), "0",
						"0^All", false);

			} else
				strStoreVal = "<option value='0'>All</option>";

		
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strStoreVal);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getStoreCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getStoreCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

	/**
	 * Gets the item cat list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the item cat list
	 */
	public static void getItemCatList(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			String strStoreId = request.getParameter("storeId");

			if (strStoreId == null)
				strStoreId = "0";

			voObj.setStrStoreId(strStoreId);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getItemCatList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			String temp = "<option value='0'>SelectValue</option>";

			if (voObj.getStrItemCatWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrItemCatWs(), "0", "",
						true);

			} else {

				temp = "<option value='0'>SelectValue</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getItemCatList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getItemCatList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

	/**
	 * Gets the group list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the group list
	 */
	public static void getGroupList(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			String strItemCatId = request.getParameter("itemcat");

			if (strItemCatId == null)
				strItemCatId = "0";

			voObj.setStrItemCatId(strItemCatId);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			
			bo.getGroupList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			String temp = "<option value='0'>All</option>";
			String temp1 = "<option value='0'>All</option>";
			if (voObj.getStrGroupCmbWS().size() != 0) {

				temp = util.getOptionValue(voObj.getStrGroupCmbWS(), "0",
						"0^All", true);

			} else {

				temp = "<option value='0'>All</option>";
			}
			if (voObj.getItemTypeWs().size() != 0) {

				temp1 = util.getOptionValue(voObj.getItemTypeWs(), "0",
						"0^All", true);

			} else {

				temp1 = "<option value='0'>All</option>";
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp+"^"+temp1);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getGroupList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getGroupList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

	/**
	 * Gets the drug list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the drug list
	 */
	public static void getDrugList(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		String hosCode = "";
		String storeId = "";
		String itemCatNO = "";
		String groupId = "";
		String statusId = "";
		String strItemCmb = "", itemType = "", districtStoreId = "";

		try {
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			storeId = (String) request.getParameter("storeId");
			itemCatNO = (String) request.getParameter("itemcat");
			groupId = (String) request.getParameter("groupId");
			itemType = (String) request.getParameter("itemType");
			statusId = (String) request.getParameter("statusId");

			

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrStoreId(storeId);

			
			
			
			
			
			formBean.setStrItemCatNo(itemCatNO);
			formBean.setStrGroupId(groupId);
			formBean.setStrItemType(itemType);
			formBean.setStrStatusId(statusId);

			voObj.setStrHospitalCode(hosCode);
			voObj.setStrDistrictStoreId("0");
			voObj.setStrStoreId(request.getParameter("storeId"));
			voObj.setStrItemCatId( request.getParameter("itemcat"));
			voObj.setStrGroupId(request.getParameter("groupId") + "^" + request.getParameter("itemType"));

			voObj.setStrStatusId(request.getParameter("statusId"));

			bo.getDrugList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			if (voObj.getStrDrugWs() != null && voObj.getStrDrugWs().size() > 0) {
				strItemCmb = util.getOptionValue(voObj.getStrDrugWs(), "", "",
						true);
			} else {
				strItemCmb = "";
			}
			bo.getGroupList(voObj);
			
			
			String temp = "<option value='0'>All</option>";
			String temp1 = "<option value='0'>All</option>";
			if (voObj.getStrGroupCmbWS().size() != 0) {

				temp = util.getOptionValue(voObj.getStrGroupCmbWS(), "0",
						"0^All", true);

			} else {

				temp = "<option value='0'>All</option>";
			}
			if (voObj.getItemTypeWs().size() != 0) {

				temp1 = util.getOptionValue(voObj.getItemTypeWs(), "0",
						"0^All", true);

			} else {

				temp1 = "<option value='0'>All</option>";
			}
			
			
			
			
			

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strItemCmb+"^"+temp+"^"+temp1);

			} catch (Exception e) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getDrugList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getDrugList()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {

			}
			eObj = null;
		} finally {
			if (voObj != null)
				voObj = null;
			if (formBean != null)
				formBean = null;
			util = null;
		}
	}
	public static void getDrugList1(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		String hosCode = "";
		String storeId = "";
		String itemCatNO = "";
		String groupId = "";
		String statusId = "";
		String strItemCmb = "", itemType = "", districtStoreId = "";

		try {
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			storeId = (String) request.getParameter("storeId");
			itemCatNO = (String) request.getParameter("itemcat");
			groupId = (String) request.getParameter("groupId");
			itemType = (String) request.getParameter("itemType");
			statusId = (String) request.getParameter("statusId");

			

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrStoreId(storeId);

			
			
			
			
			
			formBean.setStrItemCatNo(itemCatNO);
			formBean.setStrGroupId(groupId);
			formBean.setStrItemType(itemType);
			formBean.setStrStatusId(statusId);

			voObj.setStrHospitalCode(hosCode);
			voObj.setStrDistrictStoreId(formBean.getStrDistrictStoreId());
			voObj.setStrStoreId(formBean.getStrStoreId());
			voObj.setStrItemCatId(itemCatNO);
			voObj.setStrGroupId(groupId + "^" + itemType);

			voObj.setStrStatusId(statusId);

			bo.getDrugList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			if (voObj.getStrDrugWs() != null && voObj.getStrDrugWs().size() > 0) {
				strItemCmb = util.getOptionValue(voObj.getStrDrugWs(), "", "",
						true);
			} else {
				strItemCmb = "";
			}
			bo.getGroupList(voObj);
			
			
			String temp = "<option value='0'>All</option>";
			String temp1 = "<option value='0'>All</option>";
			if (voObj.getStrGroupCmbWS().size() != 0) {

				temp = util.getOptionValue(voObj.getStrGroupCmbWS(), "0",
						"0^All", true);

			} else {

				temp = "<option value='0'>All</option>";
			}
			if (voObj.getItemTypeWs().size() != 0) {

				temp1 = util.getOptionValue(voObj.getItemTypeWs(), "0",
						"0^All", true);

			} else {

				temp1 = "<option value='0'>All</option>";
			}
			
			
			
			
			

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strItemCmb+"^"+temp+"^"+temp1);

			} catch (Exception e) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getDrugList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getDrugList()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {

			}
			eObj = null;
		} finally {
			if (voObj != null)
				voObj = null;
			if (formBean != null)
				formBean = null;
			util = null;
		}
	}
	
	

	/**
	 * Gets the programme combo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the programme combo
	 */
	public static void getProgrammeCombo(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StockOnHandRptBO_NEW bo = null;
		StockOnHandRptVO_NEW voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		String strProgCmb = "", districtStoreId = "";

		try {
			util = new HisUtil("MMS Reports", "StockOnHandRptDATA");
			bo = new StockOnHandRptBO_NEW();
			voObj = new StockOnHandRptVO_NEW();

			districtStoreId = (String) request.getParameter("districtStoreId");
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDistrictStoreId(districtStoreId);
			voObj.setStrSeatId(formBean.getStrSeatId());

			bo.getProgrammeCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			if (voObj.getStrProgNameComboWS() != null
					&& voObj.getStrProgNameComboWS().size() > 0) {
				strProgCmb = util.getOptionValue(voObj.getStrProgNameComboWS(),
						"0", "0^All", true);
			} else {
				strProgCmb = "<option value=0>All</option>";
			}

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strProgCmb);

			} catch (Exception e) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.reports.StockOnHandRptDATA.getDrugList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"StockOnHandRptDATA->getDrugList()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR####Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {

			}
			eObj = null;
		} finally {
			if (voObj != null)
				voObj = null;
			if (formBean != null)
				formBean = null;
			util = null;
		}
	}

	/**
	 * Show report.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void showReport(StockOnHandRptFB_NEW formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportFormat = "html";
		String strReportName = "";
		String reportPath = "";
		Map<String, Object> params = new HashMap<String, Object>();
		String Isbatchno="";
		String strCircleId = "0";
		String strDistrictId = "0";
		String strDistrictStoreId = "0";
		String strStoreTypeId = "0";
		String strStoreId = "";

		String strItemBrandId = "";

		//String strDistrictName = formBean.getStrDistrictName();
		//String strStoreName = formBean.getStrStoreName();
		//String strStoreTypeName = formBean.getStrStoreTypeName();
		String strSubStoreName = formBean.getStrSubStoreName();
		String strItemType = formBean.getStrItemType() == null ? "0" : formBean.getStrItemType();// Item Type Id (e.g Tablets, Drops etc.)
		String strIsGroupWise = formBean.getStrIsGroupWise() == null ? "0" : formBean.getStrIsGroupWise();
		String strIsItemWise = formBean.getStrIsItemWise() == null ? "0" : formBean.getStrIsItemWise();
		System.err.println("strIsGroupWise"+strIsGroupWise);
		System.out.println("strSubStoreName"+strSubStoreName);
		try {

			String strHospitalCode = formBean.getStrHospitalCode();
			String strStatusId = formBean.getStrStatusId();
			String strCatCode = formBean.getStrItemCatNo();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strGroupId = formBean.getStrGroupId() == null ? "0" : formBean.getStrGroupId();
			//String strCurrentDate = formBean.getStrCurrentDate();
			String strBatchNo = formBean.getStrBatchNo();

			strCircleId = "0";
			strDistrictId = "0";
			strDistrictStoreId = "0";
			strStoreTypeId = formBean.getStrStoreTypeId() == null ? "0" : formBean.getStrStoreTypeId();
			strStoreId = formBean.getStrStoreId() == null ? "0" : formBean.getStrStoreId();
			Isbatchno=formBean.getStrBatchNo() ==null? "0" : formBean.getStrBatchNo();
			strItemBrandId = formBean.getStrItemBrandId() == null ? "0" : formBean.getStrItemBrandId();
			strReportName = "Stock In Hand Report";
			reportFormat = formBean.getStrReportFormat();

			boolean footerVisible = true;

			if (formBean.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;

			}

			if (formBean.getStrIsFooter() == null)
				formBean.setStrIsFooter("0");

			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;

			}
			params.put("report_id", "0");
			params.put("report_Name", strReportName+"(Store Name:- "+strSubStoreName+")");
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("username",strSubStoreName);
			
			params.put("hospCode", strHospitalCode);
			params.put("StatusId", strStatusId);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("catCode", strCatCode );
			params.put("groupId", strGroupId);
			params.put("item_type_id", strItemType);
			params.put("p_district_id", "0");
			params.put("p_substore_id", "0");
			params.put("p_hstnum_store_id",strStoreId);
			params.put("itembrand_id", strItemBrandId);
			params.put("p_stockavail", "0");
			params.put("dwhtypeId", "0");
			System.out.println(strStoreId+"strStoreId"+"+\nstrCatCode"+strCatCode);
			System.out.println("strItemBrandId"+strItemBrandId);
			System.out.println("formBean.getStrIsGroupWise()"+formBean.getStrIsGroupWise());
			
			
			if(Isbatchno.equalsIgnoreCase("1") && strIsGroupWise.equalsIgnoreCase("1") && strIsItemWise.equalsIgnoreCase("1"))
			{
				reportPath = "/mms/reports/StockOnHand_WithBatch_withGroup_withItem.rptdesign";
			}
			if(Isbatchno.equalsIgnoreCase("0") && strIsGroupWise.equalsIgnoreCase("1") && strIsItemWise.equalsIgnoreCase("1"))
			{
				reportPath = "/mms/reports/StockOnHand_withGroup_withItem.rptdesign";
			}
			if(Isbatchno.equalsIgnoreCase("1") && strIsGroupWise.equalsIgnoreCase("0") && strIsItemWise.equalsIgnoreCase("0"))
			{
				reportPath = "/mms/reports/StockOnHand_WithBatch1.rptdesign";
				
			}
			if(strIsGroupWise.equalsIgnoreCase("1") && strIsItemWise.equalsIgnoreCase("0") && Isbatchno.equalsIgnoreCase("0"))
			{
				reportPath = "/mms/reports/StockOnHand_GroupWise.rptdesign";
			}
			if(strIsItemWise.equalsIgnoreCase("1") && strIsGroupWise.equalsIgnoreCase("0") && Isbatchno.equalsIgnoreCase("0"))
			{
				reportPath = "/mms/reports/StockOnHand_ItemWise.rptdesign";
			}
			if(Isbatchno.equalsIgnoreCase("0") && strIsGroupWise.equalsIgnoreCase("0") &&  strIsItemWise.equalsIgnoreCase("0"))
			{
				reportPath = "/mms/reports/StockOnHand_WithBatch2.rptdesign";
			}	
			if(Isbatchno.equalsIgnoreCase("1") && strIsGroupWise.equalsIgnoreCase("0") && strIsItemWise.equalsIgnoreCase("1"))
			{
				reportPath = "/mms/reports/StockOnHand_WithBatch_withGroup_withItem.rptdesign";
			}
			System.out.println("reportPath"+reportPath);
			ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			
		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
