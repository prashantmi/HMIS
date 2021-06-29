/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportTemplateMstDATA.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

import dynamicreports.masters.bo.DynamicReportTemplateMstBO;
import dynamicreports.masters.controller.fb.DynamicReportTemplateMstFB;
import dynamicreports.masters.vo.DynamicReportTemplateMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportTemplateMstDATA.
 */
public class DynamicReportTemplateMstDATA {

	/**
	 * to display the add page.
	 * 
	 * @param formBean
	 *            the formBean
	 * @param request
	 *            HttpServletRequest
	 */

	public static void initialAdd(DynamicReportTemplateMstFB formBean,
			HttpServletRequest request) {

		DynamicReportTemplateMstVO vo = null;
		DynamicReportTemplateMstBO bo = null;

		HisUtil util = null;

		String strReportType = "";
		String strmsgText = "";

		try {

			vo = new DynamicReportTemplateMstVO();
			bo = new DynamicReportTemplateMstBO();

			util = new HisUtil("dynamicreports", "DynamicReportTemplateMstDATA");

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			String seatid = request.getSession().getAttribute("SEATID")
					.toString();

			if (request.getParameter("comboValue") != null) {
				strReportType = request.getParameter("comboValue");
				formBean.setStrReportTypeName(strReportType);
			}

			formBean.setStrSeatId(seatid);
			formBean.setStrHospitalCode(hosCode);

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);

			bo.getReportHeaderTypes(vo);

			formBean.setStrReportHeaderTypes(util.getOptionValue(
					vo.getWsReportHeaderTypes(), "0", "0^Select Value", false));

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = "Dynamic Report Template Master.DynamicReportTemplateMstDATA.initialAdd(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportTemplateMstDATA->initialAdd()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

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
	 * @param request
	 *            the request
	 */

	public static void insertRecord(DynamicReportTemplateMstFB formBean,
			HttpServletRequest request) {

		String strmsgText = "";
		DynamicReportTemplateMstVO vo = null;
		DynamicReportTemplateMstBO bo = null;

		try {
			bo = new DynamicReportTemplateMstBO();
			vo = new DynamicReportTemplateMstVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid = request.getSession().getAttribute("SEATID")
					.toString();

			String strReportTypeId = formBean.getCombo()[0];
			String strStatus = formBean.getCombo()[1];

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);
			formBean.setStrReportTypeId(strReportTypeId);

			vo.setStrSeatId(seatid);
			vo.setStrHospitalCode(hosCode);
			vo.setStrIsValid(strStatus);
			vo.setStrReportTypeId(strReportTypeId);

			vo.setStrReportName(formBean.getStrReportName());
			vo.setStrDisplayName(formBean.getStrDisplayName());
			vo.setStrReportHeaderType(formBean.getStrReportHeaderTypeId());
			vo.setStrReportWidth(formBean.getStrReportWidth());
			vo.setStrReportWidthUnit(formBean.getStrReportWidthUnit());
			vo.setStrIsBorder(formBean.getStrIsBorder());

			bo.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			if (!vo.isbExistStatus()) {
				formBean.setStrWarningMsg("Data already exist");
			} else {
				formBean.setStrNormalMsg("Data Saved Successfully");
			}
		} catch (Exception e) {

			strmsgText = "DynamicReportTemplateMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportTemplateMstDATA->insertRecord()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			vo = null;
			bo = null;
		}

	}

	/**
	 * to get the data for modify page.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void modifyRecord(DynamicReportTemplateMstFB formBean,
			HttpServletRequest request) {

		String strmsgText = "";

		String chk = "";

		String strtemp[] = null;
		String strtemp2[] = null;

		DynamicReportTemplateMstVO vo = null;
		DynamicReportTemplateMstBO bo = null;

		HisUtil util = null;

		try {
			bo = new DynamicReportTemplateMstBO();
			vo = new DynamicReportTemplateMstVO();

			util = new HisUtil("dynamicreports", "DynamicReportTemplateMstDATA");

			String strReportType = request.getParameter("comboValue");
			formBean.setStrReportTypeName(strReportType);

			chk = request.getParameter("chk");

			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace('$', '#').split("#");

			vo.setStrHospitalCode(strtemp2[0]);
			vo.setStrReportTemplateId(strtemp[0]);

			bo.modifyRecord(vo);
			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());
			}

			formBean.setStrDisplayName(vo.getStrDisplayName());
			formBean.setStrReportName(vo.getStrReportName());
			formBean.setStrReportWidth(vo.getStrReportWidth());
			formBean.setStrReportWidthUnit(vo.getStrReportWidthUnit());
			formBean.setStrIsBorder(vo.getStrIsBorder());

			formBean.setStrReportHeaderTypes(util.getOptionValue(
					vo.getWsReportHeaderTypes(), vo.getStrReportHeaderType(),
					"0^Select Value", false));

			formBean.setStrChkValue(chk);

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "DynamicReportTemplateMstDATA.modifyRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportTemplateMstDATA->modifyRecord()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

	}

	/**
	 * to update the record.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * 
	 * @return true, if update record
	 */
	public static boolean updateRecord(DynamicReportTemplateMstFB formBean,
			HttpServletRequest request) {

		String strmsgText = "";
		boolean retValue = true;
		String strtemp[] = null;
		String strtemp2[] = null;
		String chk = "";

		DynamicReportTemplateMstVO vo = null;
		DynamicReportTemplateMstBO bo = null;

		try {
			bo = new DynamicReportTemplateMstBO();
			vo = new DynamicReportTemplateMstVO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid = request.getSession().getAttribute("SEATID")
					.toString();

			chk = request.getParameter("chk");

			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace('$', '#').split("#");

			vo.setStrReportTemplateId(strtemp[0]);
			vo.setStrHospitalCode(strtemp2[0]);

			String strReportTypeId = formBean.getCombo()[0];
			String strStatus = formBean.getCombo()[1];

			formBean.setStrHospitalCode(hosCode);
			formBean.setStrSeatId(seatid);
			formBean.setStrReportTypeId(strReportTypeId);

			vo.setStrSeatId(seatid);
			vo.setStrHospitalCode(hosCode);
			vo.setStrIsValid(strStatus);

			vo.setStrReportName(formBean.getStrReportName());
			vo.setStrDisplayName(formBean.getStrDisplayName());
			vo.setStrReportHeaderType(formBean.getStrReportHeaderTypeId());
			vo.setStrReportWidth(formBean.getStrReportWidth());
			vo.setStrReportWidthUnit(formBean.getStrReportWidthUnit());
			vo.setStrIsBorder(formBean.getStrIsBorder());

			bo.updateRecord(vo);

			if (vo.getStrMsgType().equals("1")) {

				retValue = false;
				throw new Exception(vo.getStrMsgString());
			}

			if (!vo.isbExistStatus()) {
				retValue = false;
				formBean.setStrWarningMsg("Data already exist");
				retValue = false;
			} else {
				if (vo.getStrMsgType().equals("1")) {
					retValue = false;
					throw new Exception(vo.getStrMsgString());
				} else {
					formBean.setStrNormalMsg("Record Modify Successfully");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

			retValue = false;
			strmsgText = "DynamicReportTemplateMstDATA.updateRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportTemplateMstDATA->updateRecord()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
		return retValue;
	}

}
