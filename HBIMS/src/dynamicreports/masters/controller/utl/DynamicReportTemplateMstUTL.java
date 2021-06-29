/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportTemplateMstUTL.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.controller.utl;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportTemplateMstUTL.
 */
public class DynamicReportTemplateMstUTL implements MasterInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The http session. */
	HttpSession httpSession = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() {

		StringBuilder br = new StringBuilder();
		br.append("<a href='#' class='btn btn-sm btn-primary' tabindex='0' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='return callComboRptTemplate(document.forms[0],\"ADD\");' onClick='return callComboRptTemplate(document.forms[0],\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' tabindex='0'	title='Select A Record To Modify' border=0 tabindex='1' onKeyPress='return callComboRptTemplate(document.forms[0],\"MODIFY\");' onClick='return callComboRptTemplate(document.forms[0],\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' title='Select One Or More CheckBox To Delete Record(s)' border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' title='Select A Record To View' border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' title='Select A Record To Generate Reports' border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='reports'>Report</span></a>");
		return br.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {
		String strColHeader[] = { "Report Template Name", "Display Name",
				"Report Header", "Report Width", "Border Required" };

		return strColHeader;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {
		String comboHeader[] = { "0", "Report Type", "1", "Status" };
		return comboHeader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {

		String comboQuery[] = new String[2];
		String strHospitalCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();

		comboQuery[0] = dynamicreports.qryHandler_dynamicreports.getQuery(1,
				"select.drptType.1").replace("?", strHospitalCode);
		comboQuery[1] = "1^Active#2^Inactive";

		return comboQuery;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() {

		String[] deleteQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		deleteQuery[0] = dynamicreports.qryHandler_dynamicreports.getQuery(1,
				"delete.drptTemplateMst.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat("  WHERE "
				+ dynamicreports.qryHandler_dynamicreports.getQuery(1,
						"delete.drptTemplateMst.cond.0"));

		return deleteQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() {
		String js = "../../dynamicreports/js/drpt_templateMst.js";
		return js;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String strCmb[]) {

		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(dynamicreports.qryHandler_dynamicreports.getQuery(1,
				"drptTemplateMst.main").replace("?", hosCode));
		brMainQuery.append(" AND 1=2");

		if (strCmb != null) {

			brMainQuery = new StringBuffer();
			brMainQuery.append(dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "drptTemplateMst.main").replace("?", hosCode));
			if (!strCmb[0].equals("0")) {

				brMainQuery.append(" AND "
						+ dynamicreports.qryHandler_dynamicreports.getQuery(1,
								"drptTemplateMst.main.cond.1").replace("?",
								strCmb[1]));

				brMainQuery.append(" AND "
						+ dynamicreports.qryHandler_dynamicreports.getQuery(1,
								"drptTemplateMst.main.cond.2").replace("?",
								strCmb[0]));

			} else {
				brMainQuery.append(" AND 1=2");
			}

		}

		return brMainQuery.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {
		String strMasterName = "Dynamic Report Template Master ";

		return strMasterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {

		String orderBy[] = { "1", "DRPSTR_TEMPLATE_NAME", "2",
				"DRPSTR_DISPLAY_NAME" };
		return orderBy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getPage_per_block()
	 */
	public int getPage_per_block() {
		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getRecord_per_page()
	 */
	public int getRecord_per_page() {
		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */
	public String[] getSearchField() {
		String search_field[] = { "1", "DRPSTR_TEMPLATE_NAME", "2",
				"DRPSTR_DISPLAY_NAME" };
		return search_field;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();

		viewHdr.add("Report Name");
		viewHdr.add("D");
		viewHdr.add("Display Name");
		viewHdr.add("D");
		viewHdr.add("Report Header Type");
		viewHdr.add("D");
		viewHdr.add("Report Width");
		viewHdr.add("D");
		viewHdr.add("Border Required");
		viewHdr.add("D");

		return viewHdr;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {
		String viewQuery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
				"select.drptTemplateMst.0");
		return viewQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http
	 * .HttpSession)
	 */
	public void setHttpSession(HttpSession session) {

		this.httpSession = session;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColsWidth()
	 */
	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#reportInterFaceRequired()
	 */
	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

}
