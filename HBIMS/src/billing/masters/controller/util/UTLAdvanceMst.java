package billing.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;

public class UTLAdvanceMst implements MasterInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;

	/**
	 * returns List pages Buttons Strings.
	 */
	public String getButtons() {

		StringBuilder br = new StringBuilder();

		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record' border='0' tabindex='1' onKeyPress='advanceMstCheckHospitalService(document.forms[0]);' onClick='advanceMstCheckHospitalService(document.forms[0]);'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;' title='Select A Record To Modify' border='0' tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		//br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)' width='60' height='20' border=0  tabindex='1' onKeyPress='deleteData(this);' onClick='deleteData(this);'>");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)' border='0'  tabindex='1' onKeyPress='return deleteAdvanceRecords();' onClick='return deleteAdvanceRecords();'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border='0' tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reports' border='0'  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
		
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='advanceMstCheckHospitalService(document.forms[0]);' onClick='advanceMstCheckHospitalService(document.forms[0]);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return deleteAdvanceRecords();' onClick='return deleteAdvanceRecords();'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	/**
	 * returns Column Header String Array
	 */
	public String[] getColumnHeader() {

		String strColHeader[] = { "Ward Type", "Category", "Advance+Security Amount",
				 "Part Amount", "Validity" };

		return strColHeader;
	}

	/**
	 * returns Combo Header String Array
	 */
	public String[] getComboHeader() {

		String comboHeader[] = { "0", "Hospital Service" };
		return comboHeader;
	}

	/**
	 * returns Combo Query String Array
	 */
	public String[] getComboQuery() {

		String comboQuery[] = new String[2];
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE ;

		comboQuery[0] = billing.qryHandler_billing.getQuery(1,"gbl.chargetype.0").replace("?", hosCode);

		return comboQuery;
	}

	/**
	 * returns Delete Query String Array
	 */
	public String[] getDeleteQuery() {

		String[] strDeleteQuery = new String[1];
//		strDeleteQuery[0] = billing.qryHandler_billing.getQuery(1,
//				"delete.advanceMst.0");
//
//		return strDeleteQuery;
		String seatId = httpSession.getAttribute("SEATID").toString();
		strDeleteQuery[0] = billing.qryHandler_billing.getQuery(1,
				"delete.advanceMst.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" where "
				+ billing.qryHandler_billing.getQuery(1,
						"delete.advanceMst.cond.0"));
		return strDeleteQuery;
	}

	/**
	 * returns Jsp File Path String
	 */
	public String getJsFiles() {

		String js = "../../billing/js/billing.js";

		return js;
	}

	/**
	 * returns Main Query String
	 */
	public String getMainQuery(String[] strCmb) {

		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(
				billing.qryHandler_billing.getQuery(1, "select.advanceMst.0")
						.replace("?", hosCode));
				

		 brMainQuery = brMainQuery.append(" AND "+
		 billing.qryHandler_billing.getQuery(1,"select.advanceMst.cond.0").replace("?",
		 "5"));

		if (strCmb != null) {

			if (!strCmb[0].equals("0")) {
				//System.out.println("brMainQuery before updating index  :::::"+brMainQuery);
				brMainQuery.replace(brMainQuery.lastIndexOf("5"), brMainQuery
						.lastIndexOf("5") + 1, strCmb[0]);
				//System.out.println("brMainQuery after updating index  :::::"+brMainQuery);
				httpSession.setAttribute("ChargetypeId", strCmb[0]);
			}

		}
		//System.out.println("brMainQuery.toString()-->"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	/**
	 * returns Master Name String
	 */
	public String getMasterName() {

		String strMasterName = "Advance Master";

		return strMasterName;
	}

	/**
	 * returns Order By String Array
	 */
	public String[] getOrderBy() {

		String strOrderBy[] = { "1", "C.IPD_CHARGETYPE_NAME", "2",
				"C.PATIENT_CAT_NAME" };

		return strOrderBy;
	}

	/**
	 * returns Number of Blocks per Page
	 */
	public int getPage_per_block() {

		return 10;
	}

	/**
	 * returns Number of Record Per Page
	 */
	public int getRecord_per_page() {

		return 10;
	}

	/**
	 * returns Search Field String Array
	 */
	public String[] getSearchField() {

		String strSearchBy[] = { "1", "C.IPD_CHARGETYPE_NAME" ,"2", "C.PATIENT_CAT_NAME"};

		return strSearchBy;
	}

	/**
	 * returns View Header List
	 */
	public List<String> getViewHeader() {

		List<String> listViewHdr = new ArrayList<String>();
		listViewHdr.add("Ward Type");
		listViewHdr.add("D");
		listViewHdr.add("Category");
		listViewHdr.add("D");
		listViewHdr.add("Advance Amount");
		listViewHdr.add("D");
		listViewHdr.add("Part Amount");
		listViewHdr.add("D");
		listViewHdr.add("Effective From");
		listViewHdr.add("D");
		listViewHdr.add("Effective To");
		listViewHdr.add("D");
		listViewHdr.add("Remarks");
		listViewHdr.add("D");

		return listViewHdr;

	}

	/**
	 * returns View Query String Array
	 */
	public String getViewQuery() {

		String strViewQuery = billing.qryHandler_billing.getQuery(1,
				"select.advanceMst.4");

		return strViewQuery;

	}

	/**
	 * sets HttpSession Object.
	 */
	public void setHttpSession(HttpSession session) {

		this.httpSession = session;

	}

	public String[] getColsWidth() {
		return null;
	}

	public boolean reportInterFaceRequired() {
		return false;
	}

	public String isGlobal() {
		return null;
	}
}
