package bmed.masters.controller.utl;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Arun V.R Creation Date:- 11-jan-2011 Modifying Date:- 21-jan-2011
 *         Used For:-Engineering Item Sub Type Master Team Lead By:- Amit Kumar
 *         Module:- BMED(HIS Project)
 * 
 */
public class EngineeringItemSubTypeMstUTL implements MasterInterface {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The http session. */
	HttpSession httpSession = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http.HttpSession)
	 */
	public void setHttpSession(HttpSession session) {

		httpSession = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {

		String strMasterName = " Engineering Item Sub-Type Master ";
		return strMasterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {

		String[] strColumnHdr = { "Engineering Item Sub Type Name",
				"Effective From Date" };
		return strColumnHdr;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */
	public String[] getSearchField() {

		String strSearch_field[] = { "1", "HEMSTR_ENGG_ITEM_SUB_TYPE_NAME" };
		return strSearch_field;

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
	 * @see hisglobal.masterutil.MasterInterface#getPage_per_block()
	 */
	public int getPage_per_block() {

		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {

		String[] strComboHdr = { "0", "Engineering Item Type", "1",
				"Record Status" };
		return strComboHdr;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {

		String[] strComboQuery = new String[2];
		String strHosCode = Config.SUPER_USER_HOSPITAL_CODE;
		strComboQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"enggItemSubTypeMst.enggItemtype.combo.0");
		strComboQuery[0] = strComboQuery[0].replace("?", strHosCode);
		strComboQuery[1] = "1^Active#2^Inactive";
		return strComboQuery;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb) {

		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();
		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(
				bmed.qryHandler_bmed.getQuery(1, "enggItemSubTypeMst.main")
						.replace("?", strHosCode)).append(
				" AND "
						+ bmed.qryHandler_bmed.getQuery(1,
								"enggItemSubTypeMst.main.cond.1").replace("?",
								"1"));

		if (cmb != null) {

			if (!cmb[1].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[1]);

			}
		}

		brMainQuery.append(" AND "
				+ bmed.qryHandler_bmed.getQuery(1,
						"enggItemSubTypeMst.main.cond.2").replace("?", "0"));

		if (cmb != null) {

			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery
						.lastIndexOf("0") + 1, cmb[0]);

			}
		}
		System.out.println("main Qry::::" + brMainQuery.toString());
		return brMainQuery.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {
		/*
		 * // System.out.println("view query: " +
		 * bmed.qryHandler_bmed.getQuery(1, "select.storeGroup.1"));
		 */
		return bmed.qryHandler_bmed.getQuery(1, "enggItemSubTypeMst.view");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Engineering Item Type ");
		viewHdr.add("D");
		viewHdr.add("Engineering Item Sub Type Name ");
		viewHdr.add("D");
		viewHdr.add("Effective From ");
		viewHdr.add("D");
		viewHdr.add("Remarks");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {

		String strOrderBy[] = { "1", "UPPER(HEMSTR_ENGG_ITEM_SUB_TYPE_NAME)",
				"2", "gdt_effective_from" };
		return strOrderBy;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() {

		String[] strDelQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		strDelQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"enggItemSubTypeMst.delete").replace("?", seatId);

		strDelQuery[0] = strDelQuery[0].concat(" WHERE "
				+ bmed.qryHandler_bmed.getQuery(1,
						"enggItemSubTypeMst.delete.cond.1"));
		return strDelQuery;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() {

		StringBuilder br = new StringBuilder();

	/*	br
				.append("<a href='#' class='button' title='Add' tabindex='0' onKeyPress='if(event.keyCode==13) return callComboAdd(document.forms[0],3);' onClick='return callComboAdd(document.forms[0],3);'><span class='add'>Add</span></a>");
		br
				.append("<a href='#' class='button' title='Modify' tabindex='0'	onKeyPress='if(event.keyCode==13) return callComboModify(document.forms[0],3);' onClick='return callComboModify(document.forms[0],3);'>Modify</span></a>");
		br
				.append("<a href='#' class='button' title='Delete' tabindex='0'	onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br
				.append("<a href='#' class='button' title='View' tabindex='0' 	onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br
				.append("<a href='#' class='button' title='Report' tabindex='0' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  onClick='report(\"REPORT\");'>Report</span></a>");
		return br.toString();
		
	*/	
		

			StringBuilder strButtons = new StringBuilder();

			strButtons
					.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer;' title='Add' tabindex='0' onKeyPress='if(event.keyCode==13) return callComboAdd(document.forms[0],3);' onClick='return callComboAdd(document.forms[0],3);'/>");
			strButtons
					.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor: pointer;' title='Modify' tabindex='0' onKeyPress='if(event.keyCode==13) return callComboModify(document.forms[0],3);' onClick='return callComboModify(document.forms[0],3);'  />");
			strButtons
					.append("<img src='../../hisglobal/images/btn-del.png' style='cursor: pointer;' title='Delete' tabindex='0' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");

			strButtons
					.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer;' title='View' tabindex='0' onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' />");
			strButtons
					.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor: pointer;' title='Report' tabindex='0' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  onClick='report(\"REPORT\");' />");

			return strButtons.toString();
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() {

		String jsFile = new String("../../bmed/js/engItemSubTypeMst.js");
		return jsFile;

	}

	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return true;
	}

	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

}
