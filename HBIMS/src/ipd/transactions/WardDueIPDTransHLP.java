/**
 * 
 */
package ipd.transactions;

import ipd.IpdConfigUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
//import java.util.ResourceBundle;

import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

/**
 * @author pankaj kumar
 * 
 */
public class WardDueIPDTransHLP {
	
	public static String getPatientDueList(WardDueIPDTransVO _WardDueIPDTransVO)
			throws Exception {
		StringBuffer strBuff = new StringBuffer("");
		IpdConfigUtil ipdC=new IpdConfigUtil(_WardDueIPDTransVO.getStrHospCode());
		//ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		if(_WardDueIPDTransVO.getStrBelMode().equals("1") && ipdC.getStrBelongingRequired().equals("1"))//Belonging
		strBuff.append(getPatientBelongingDueList(_WardDueIPDTransVO));
		/*if(resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED").equals("1"))
			strBuff.append(getPatientIssueDueList(_WardDueIPDTransVO));*/
		if(_WardDueIPDTransVO.getStrBelMode().equals("2") && ipdC.getStrIssueItemRequired().equals("1"))//Issue Items
			strBuff.append(getPatientIssueDueList(_WardDueIPDTransVO));		
		return strBuff.toString();
	}
	
	public static String getPatientBelongingDueList(WardDueIPDTransVO _WardDueIPDTransVO)
			throws Exception {
		StringBuffer strBuff = new StringBuffer("");
		_WardDueIPDTransVO.setStrItemType("2");
		WardDueIPDTransBO wardDueIPDTransBO = new WardDueIPDTransBO();
		wardDueIPDTransBO.getPatientDueList(_WardDueIPDTransVO);
		if(_WardDueIPDTransVO.getStrMsgType().equals("1"))
			throw new Exception(_WardDueIPDTransVO.getStrMsg());
		WebRowSet wb = _WardDueIPDTransVO.getWBPatientDueList();
		LinkedHashMap<String, String> mapParam = null;
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("WardDueIPDTransHLP","getPatientBelongingDueList()");
			_WardDueIPDTransVO.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yy"));
			mapParam = new LinkedHashMap<String, String>();
			mapParam.put("hosp_code", _WardDueIPDTransVO.getStrHospCode());
			mapParam.put("err", "#1");
			mapParam.put("resultset", "#2");
			
			String relationValues = HisComboOptions.getOptionsFromProc("pkg_ipd_view.proc_gblt_relation_list", mapParam, "0", "0^Select Value", false);
			
			strBuff.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			strBuff.append("<tr class='TITLE'>\n");
			strBuff.append("<td  colspan='8'>Patient Belonging Details</td>\n");
			strBuff.append("</tr>\n");
			strBuff.append("<tr><td width='5%' class='multiLabel'>#</td>");
			strBuff.append("<td WIDTH='20%' class='multiLabel'>Item Name</td>\n");
			strBuff.append("<td WIDTH='6%' class='multiLabel'>Qty</td>\n");
			strBuff.append("<td WIDTH='20%' class='multiLabel'>Description</td>\n");
			strBuff.append("<td WIDTH='16%' class='multiLabel'>Returning Date</td>\n");
			strBuff.append("<td WIDTH='15%' class='multiLabel'>Return To</td>\n");
			strBuff.append("<td WIDTH='8%' class='multiLabel'>Relation</td>\n");
			strBuff.append("<td WIDTH='10%' class='multiLabel'>Status</td>\n");
			strBuff.append("</tr>\n");
			if (wb != null && wb.size() > 0) {
				while (wb.next()) {
					String strTemp = wb.getString(5);
					if (strTemp == null || strTemp.equals("null"))
						strTemp = "";
					strBuff.append("<tr>");
					strBuff.append("<td class='multiControl'>");
					strBuff.append("<input type='checkbox' name='strChkItem' value='");
					strBuff.append(wb.getString(4));
					strBuff.append("^2' >");
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(1));	
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(2));
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(3));
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(HisUtil.getDatePicker("strItemReturnDate",_WardDueIPDTransVO.getStrCurrentDate(), true));
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append("<input type='text' name='strItemReturnTo' value='");
					strBuff.append(strTemp); 
					strBuff.append("' class='txtFldNormal' onkeypress='return validateData(event,9);' maxlength='50' ></td>");
					strBuff.append("<td class='multiControl'>");
					strBuff.append("<select name='strRelation' onChange='relationSet(this)' class='comboSmall'>");
					strBuff.append(relationValues);
					strBuff.append("</select></td><td class='multiControl'><select name='strStatus'>");
					strBuff.append("<option value='3'>Return</option>");
					strBuff.append("<option value='4'>Lost</option>");
					strBuff.append("</select></td></tr>");
				}
				strBuff.append("</table>");
			} else {
				strBuff.append("<table class='TABLEWIDTH' align='center'  border='0'>");
				strBuff.append("<tr><td colspan='8' CLASS='multiControl'>");
				strBuff.append("<DIV class='errMsg' align='center'> NO RECORD FOUND </div>");
				strBuff.append("</TD></tr>");
				strBuff.append("</table>");
			}
		} catch (Exception _Err) {
			throw new Exception("WardDueIPDTransHLP.getPatientBelongingDueList() --> "
							+ _Err.getMessage());
		}
		return strBuff.toString();
	}
	
	public static String getPatientIssueDueList(WardDueIPDTransVO _WardDueIPDTransVO)
			throws Exception {
		StringBuffer strBuff = new StringBuffer("");
		_WardDueIPDTransVO.setStrItemType("1");
		WardDueIPDTransBO wardDueIPDTransBO = new WardDueIPDTransBO();
		wardDueIPDTransBO.getPatientDueList(_WardDueIPDTransVO);
		if(_WardDueIPDTransVO.getStrMsgType().equals("1"))
			throw new Exception(_WardDueIPDTransVO.getStrMsg());
		WebRowSet wb = _WardDueIPDTransVO.getWBPatientDueList();
		HisUtil hisUtil = null;
		try {
			hisUtil = new HisUtil("WardDueIPDTransHLP","getPatientIssueDueList()");
			_WardDueIPDTransVO.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yy"));
			strBuff.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			strBuff.append("<tr class='TITLE'>\n");
			strBuff.append("<td  colspan='8'>Patient Issued Items Details</td>\n");
			strBuff.append("</tr>\n");
			strBuff.append("<tr><td width='5%' class='multiLabel'>#</td>");
			strBuff.append("<td WIDTH='25%' class='multiLabel'>Item Name</td>\n");
			strBuff.append("<td WIDTH='10%' class='multiLabel'>Qty</td>\n");
			strBuff.append("<td WIDTH='30%' class='multiLabel'>Description</td>\n");
			strBuff.append("<td WIDTH='20%' class='multiLabel'>Returning Date</td>\n");
			strBuff.append("<td WIDTH='10%' class='multiLabel'>Status</td>\n");
			strBuff.append("</tr>\n");
			if (wb != null && wb.size() > 0) {
				while (wb.next()) {
					String strTemp = wb.getString(5);
					if (strTemp == null || strTemp.equals("null"))
						strTemp = "";
					strBuff.append("<tr>");
					strBuff.append("<td width='5%' class='multiControl'>");
					strBuff.append("<input type='checkbox' name='strChkItem' value='");
					strBuff.append(wb.getString(4));
					strBuff.append("^1' >");
					strBuff.append("</td><td width='18%' class='multiControl'>");
					strBuff.append(wb.getString(1));	
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(2));
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(3));
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(HisUtil.getDatePicker("strItemReturnDate",_WardDueIPDTransVO.getStrCurrentDate(), true));
					strBuff.append("</td><td class='multiControl'><select name='strStatus'>");
					strBuff.append("<option value='3'>Return</option>");
					strBuff.append("<option value='4'>Lost</option>");
					strBuff.append("</select></td></tr>");
				}
				strBuff.append("</table>");
			} else {
				strBuff.append("<table class='TABLEWIDTH' align='center'  border='0'>");
				strBuff.append("<tr><td colspan='8' CLASS='multiControl'>");
				strBuff.append("<DIV class='errMsg' align='center'> NO RECORD FOUND </div>");
				strBuff.append("</TD></tr>");
				strBuff.append("</table>");
			}
		} catch (Exception _Err) {
			throw new Exception("WardDueIPDTransHLP.getPatientIssueDueList() --> "
							+ _Err.getMessage());
		}
		return strBuff.toString();
	}
	
	public static String getPatientList(WardDueIPDTransVO _WardDueIPDTransVO)
			throws Exception {
		StringBuffer strBuff = new StringBuffer("");
		WardDueIPDTransBO wardDueIPDTransBO = new WardDueIPDTransBO();
		wardDueIPDTransBO.getPatienteList(_WardDueIPDTransVO);
		if(_WardDueIPDTransVO.getStrMsgType().equals("1"))
			throw new Exception(_WardDueIPDTransVO.getStrMsg());
		WebRowSet wb = _WardDueIPDTransVO.getWBPatientList();
		try {
			if (wb != null && wb.size() > 0) {
			strBuff.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			strBuff.append("<tr><td width='5%' class='multiLabel'></td>");
			strBuff.append("<td WIDTH='25%' class='multiLabel'>CR No.</td>\n");
			strBuff.append("<td WIDTH='25%' class='multiLabel'>Adm. No.</td>\n");
			strBuff.append("<td WIDTH='25%' class='multiLabel'>Patient Name</td>\n");
			strBuff.append("<td WIDTH='20%' class='multiLabel'>Age/Sex</td>\n");
			strBuff.append("</tr>\n");
			
				while (wb.next()) {
					strBuff.append("<tr>");
					strBuff.append("<td class='multiControl'>");
					strBuff.append("<input type='radio' name='strPatient' value='");
					strBuff.append(wb.getString(1));
					strBuff.append("^");
					strBuff.append(wb.getString(2));
					strBuff.append("' onClick='chkPatient(this);'>");
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(1));	
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(2));
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(3));
					strBuff.append("</td><td class='multiControl'>");
					strBuff.append(wb.getString(4));
					strBuff.append("</td></tr>");
				}
				strBuff.append("</table>");
			} else {
				strBuff.append("<table class='TABLEWIDTH' align='center'  border='0'>");
				strBuff.append("<tr><td colspan='8' CLASS='multiControl'>");
				strBuff.append("<DIV class='errMsg' align='center'>NO RECORD FOUND </div>");
				strBuff.append("</TD></tr>");
				strBuff.append("</table>");
			}
		} catch (Exception _Err) {
			_Err.printStackTrace();
			throw new Exception("WardDueIPDTransHLP.getPatientList() --> "
							+ _Err.getMessage());
		}
		return strBuff.toString();
	}
}
