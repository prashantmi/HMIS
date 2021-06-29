package bmed.masters.controller.utl;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Amit Kumar Creation Date:- 11-jan-2011 Modifying Date:- Used For:-
 *         Configure Item Maintenance Master Team Lead By:- Module:- BMED(HIS
 *         Project)
 * 
 */
public class ItemMaintenanceMstUTL implements MasterInterface {

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

		String masterName = " Item Maintenance Master ";
		return masterName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {

		String[] arrStrColumnHdr = { "Item Name", "Maintenance Name",
				"Maintenance Duration", "Effective From Date" };
		return arrStrColumnHdr;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */
	public String[] getSearchField() {
		String arrStrSearch_field[] = {
				"1",
				" MMS_MST.get_item_dtl(1,A.GNUM_HOSPITAL_CODE,A.HEMNUM_ITEMBRAND_ID) ",
				"2",
				" BMED_FUNCTION.GET_MAINTENANCE_NAME(A.GNUM_HOSPITAL_CODE,A.HEMNUM_MAINTE_ID) " };

		return arrStrSearch_field;
		// return null;
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

		String[] arrStrComboHdr = { "0", "Department/Store Name", "1",
				"Status" };
		return arrStrComboHdr;

		// return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {

		String[] arrStrComboQuery = new String[2];
		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE")
				.toString();
		
		
		System.out.println("strHosCode--"+strHosCode);

		arrStrComboQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"itemMaintenanceMst.Store.combo.0");
		arrStrComboQuery[0] = arrStrComboQuery[0].replace("?", strHosCode);
		arrStrComboQuery[1] = "1^Active#2^Inactive";

		return arrStrComboQuery;

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
				bmed.qryHandler_bmed.getQuery(1, "itemMaintenanceMst.main")
						.replace("?", strHosCode)).append(
				" AND "
						+ bmed.qryHandler_bmed.getQuery(1,
								"itemMaintenanceMst.main.cond.1").replace("?",
								"1"));

		if (cmb != null) {

			if (!cmb[1].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[1]);

			}

			if (!cmb[0].equals("0")) {
				brMainQuery.append(" AND "
						+ bmed.qryHandler_bmed.getQuery(1,
								"itemMaintenanceMst.main.cond.2").replace("?",
								"0"));
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery
						.lastIndexOf("0") + 1, cmb[0]);

			}
		}

		return brMainQuery.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {

		String strViewQuery = null;
		return strViewQuery;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {

		List<String> listViewHdr = new ArrayList<String>();
		listViewHdr.add("Record Status");
		listViewHdr.add("D");

		return listViewHdr;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {
		String arrOrderBy[] = {
				"1",
				" UPPER(MMS_MST.get_item_dtl(1,A.GNUM_HOSPITAL_CODE,A.HEMNUM_ITEMBRAND_ID)) ",
				"2",
				" UPPER(BMED_FUNCTION.GET_MAINTENANCE_NAME(A.GNUM_HOSPITAL_CODE,A.HEMNUM_MAINTE_ID)) " };

		return arrOrderBy;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() {

		String[] arrDelQuery = new String[1];
		String strSeatId = httpSession.getAttribute("SEATID").toString();

		arrDelQuery[0] = bmed.qryHandler_bmed.getQuery(1,
				"itemMaintenanceMst.delete.0").replace("?", strSeatId);
		arrDelQuery[0] = arrDelQuery[0].concat("  WHERE "
				+ bmed.qryHandler_bmed.getQuery(1,
						"itemMaintenanceMst.delete.cond.0"));
		
		System.out.println("arrDelQuery[0]"  +arrDelQuery[0]);

		/*arrDelQuery[1] = bmed.qryHandler_bmed.getQuery(1,
				"itemMaintenanceMst.delete.1");
		arrDelQuery[1] = arrDelQuery[1].concat("  WHERE "
				+ bmed.qryHandler_bmed.getQuery(1,
						"itemMaintenanceMst.delete.cond.1"));
*/
		return arrDelQuery;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() {

		StringBuilder strButtons = new StringBuilder();

		strButtons
				.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer;' title='Add' tabindex='0' onKeyPress='if(event.keyCode==13) buttonLogicsOnClick1(\"ADD\");' onClick='buttonLogicsOnClick1(\"ADD\");'/>");
		strButtons
				.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor: pointer;' title='Modify' tabindex='0' onKeyPress='if(event.keyCode==13) edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'  />");
		strButtons
				.append("<img src='../../hisglobal/images/btn-del.png' style='cursor: pointer;' title='Delete' tabindex='0' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' />");

		strButtons
				.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer;' title='View' tabindex='0' onKeyPress='if(event.keyCode==13) showViewItemMaintenance(document.forms[0]);' onClick='showViewItemMaintenance(document.forms[0]);' />");
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

		String jsFile = new String("../../bmed/js/itemMaintenance_mst.js");
		return jsFile;
		// return null;

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
