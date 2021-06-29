/**
 * 
 */
package ipd.transactions;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.controller.HisComboOptions;

/**
 * @author pankaj kumar
 * 
 */
public class WardDueIPDTransDATA {
	public static void initDetail(WardDueIPDTransFB _WardDueIPDTransFB,
			HttpServletRequest _Request) {
		LinkedHashMap<String, String> mapParam = null;
		try {
			mapParam = new LinkedHashMap<String, String>();
			mapParam.put("modeVal", "1");
			mapParam.put("hosp_code", _Request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			mapParam.put("err", "#1");
			mapParam.put("resultset", "#2");
			_WardDueIPDTransFB.setStrDeptValue(HisComboOptions
					.getOptionsFromProc("PKG_IPD_VIEW.proc_department",
							mapParam, "0", "0^Select Value", false));
		} catch (Exception _Err) {
			HisException hisException = new HisException(
					"IPD-->Ward Due Details",
					"WardDueIPDTransDATA-->initDetail()", _Err.getMessage());
			_WardDueIPDTransFB.setStrErr("Application Error [ERROR ID : "
					+ hisException.getErrorID()
					+ "], Contact System Administrator! ");
		}
	}

	public static void getUnitCombo(WardDueIPDTransFB _WardDueIPDTransFB,
			HttpServletRequest _Request, HttpServletResponse _Response) {
		LinkedHashMap<String, String> mapParam = null;
		try {
			mapParam = new LinkedHashMap<String, String>();
			mapParam.put("modeVal", "1");
			mapParam.put("hosp_code", _Request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			mapParam.put("dept_code", _WardDueIPDTransFB.getStrDept());
			mapParam.put("err", "#1");
			mapParam.put("resultset", "#2");
			_WardDueIPDTransFB.setStrDeptUnitValue(HisComboOptions
					.getOptionsFromProc("PKG_IPD_VIEW.proc_unit", mapParam,
							"0", "0^Select Value", false));
			_Response.getWriter().print(
					_WardDueIPDTransFB.getStrDeptUnitValue());
		} catch (Exception _Err) {
			try {
				HisException hisException = new HisException(
						"IPD-->Ward Due Details",
						"WardDueIPDTransDATA-->getUnitCombo()", _Err
								.getMessage());
				_Response.getWriter().print(
						"Error####Application Error [ERROR ID : "
								+ hisException.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception _Error) {
			}

		}
	}

	public static void getWardCombo(WardDueIPDTransFB _WardDueIPDTransFB,
			HttpServletRequest _Request, HttpServletResponse _Response) {
		LinkedHashMap<String, String> mapParam = null;
		try {
			mapParam = new LinkedHashMap<String, String>();
			mapParam.put("modeVal", "6");
			mapParam.put("hosp_code", _Request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			mapParam.put("deptunitcode", _WardDueIPDTransFB.getStrDeptUnit());
			mapParam.put("err", "#1");
			mapParam.put("resultset", "#2");
			_WardDueIPDTransFB.setStrWardValue(HisComboOptions
					.getOptionsFromProc("PKG_IPD_VIEW.proc_hipt_ward_mst",
							mapParam, "0", "0^Select Value", false));
			_Response.getWriter().print(_WardDueIPDTransFB.getStrWardValue());
		} catch (Exception _Err) {
			try {
				HisException hisException = new HisException(
						"IPD-->Ward Due Details",
						"WardDueIPDTransDATA-->getWardCombo()", _Err
								.getMessage());
				_Response.getWriter().print(
						"Error####Application Error [ERROR ID : "
								+ hisException.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception _Error) {
			}
		}
	}

	public static void getPatientList(WardDueIPDTransFB _WardDueIPDTransFB,
			HttpServletRequest _Request, HttpServletResponse _Response) {
		WardDueIPDTransVO wardDueIPDTransVO = null;
		String strPatientList = null;
		try {
			wardDueIPDTransVO = new WardDueIPDTransVO();
			wardDueIPDTransVO.setStrHospCode(_Request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			wardDueIPDTransVO.setStrDeptUnit(_WardDueIPDTransFB
					.getStrDeptUnit());
			wardDueIPDTransVO.setStrWard(_WardDueIPDTransFB.getStrWard());

			strPatientList = WardDueIPDTransHLP
					.getPatientList(wardDueIPDTransVO);
			_Response.getWriter().print(strPatientList);
		} catch (Exception _Err) {
			try {
				HisException hisException = new HisException(
						"IPD-->Ward Due Details",
						"WardDueIPDTransDATA-->getPatientList()", _Err
								.getMessage());
				_Response.getWriter().print(
						"Error####Application Error [ERROR ID : "
								+ hisException.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception _Error) {
			}
		}
	}

	public static void getPatientDueList(WardDueIPDTransFB _WardDueIPDTransFB,
			HttpServletRequest _Request, HttpServletResponse _Response) {
		WardDueIPDTransVO wardDueIPDTransVO = null;
		String strPatientDueList = null;
		try {
			wardDueIPDTransVO = new WardDueIPDTransVO();
			wardDueIPDTransVO.setStrHospCode(_Request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			wardDueIPDTransVO.setStrDeptUnit(_WardDueIPDTransFB
					.getStrDeptUnit());
			wardDueIPDTransVO.setStrWard(_WardDueIPDTransFB.getStrWard());
			wardDueIPDTransVO.setStrAdmNo(_WardDueIPDTransFB.getStrPatient()
					.replace("^", "#").split("#")[1]);
			strPatientDueList = WardDueIPDTransHLP
					.getPatientDueList(wardDueIPDTransVO);
			_Response.getWriter().print(strPatientDueList);
		} catch (Exception _Err) {
			try {
				HisException hisException = new HisException(
						"IPD-->Ward Due Details",
						"WardDueIPDTransDATA-->getPatientDueList()", _Err
								.getMessage());
				_Response.getWriter().print(
						"Error####Application Error [ERROR ID : "
								+ hisException.getErrorID()
								+ "], Contact System Administrator! ");
			} catch (Exception _Error) {
			}
		}
	}

	public static void save(WardDueIPDTransFB _WardDueIPDTransFB,
			HttpServletRequest _Request) {
		WardDueIPDTransVO wardDueIPDTransVO = null;
		WardDueIPDTransBO wardDueIPDTransBO = null;
		try {
			wardDueIPDTransBO = new WardDueIPDTransBO();
			wardDueIPDTransVO = (WardDueIPDTransVO) TransferObjectFactory
					.populateData("ipd.transactions.WardDueIPDTransVO",
							_WardDueIPDTransFB);
			String[] strTmpSlNo = new String[_WardDueIPDTransFB.getStrChkItem().length];
			String[] strTmpItemType = new String[_WardDueIPDTransFB
					.getStrChkItem().length];
			for (int nTmpI = 0; nTmpI < _WardDueIPDTransFB.getStrChkItem().length; nTmpI++) {
				strTmpSlNo[nTmpI] = _WardDueIPDTransFB.getStrChkItem()[nTmpI]
						.replace("^", "#").split("#")[0];
				strTmpItemType[nTmpI] = _WardDueIPDTransFB.getStrChkItem()[nTmpI]
						.replace("^", "#").split("#")[1];
			}
			wardDueIPDTransVO.setStrAdmNo(_WardDueIPDTransFB.getStrPatient()
					.replace("^", "#").split("#")[1]);
			wardDueIPDTransVO.setStrHospCode(_Request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			wardDueIPDTransVO.setStrSeatId(_Request.getSession()
					.getAttribute("SEATID").toString());
			wardDueIPDTransVO.setStrSlno(strTmpSlNo);
			wardDueIPDTransVO.setStrItemTypes(strTmpItemType);
			wardDueIPDTransBO.save(wardDueIPDTransVO);
			if (wardDueIPDTransVO.getStrMsgType().equals("1"))
				throw new Exception(wardDueIPDTransVO.getStrMsg());
			_WardDueIPDTransFB.setStrMsg("Data has been Saved Successfully.");
		} catch (Exception _Err) {
			_Err.printStackTrace();
			HisException hisException = new HisException(
					"IPD-->Ward Due Details", "WardDueIPDTransDATA-->save()",
					_Err.getMessage());
			_WardDueIPDTransFB.setStrErr("Application Error [ERROR ID : "
					+ hisException.getErrorID()
					+ "], Contact System Administrator! ");
		}
	}
}
