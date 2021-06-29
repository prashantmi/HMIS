/* Counter Master UTIL
 * Created By: Pawan Kumar B N
 * Created On: 01-Sep-2011
 */
package billing.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;


public class CounterMstUTL implements MasterInterface {

	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}


	public String[] getColsWidth() {
		return null;
	}

	public boolean reportInterFaceRequired() {
		return false;
	}
	public String getMasterName() {
		String masterName = "Counter Master";
		return masterName;
	}

	public int getRecord_per_page() {
		return 10;
	}

	public int getPage_per_block() {
		return 10;
	}

	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();

		String moduleId=httpSession.getAttribute("USERVALUE").toString();
		if(moduleId!=null && Integer.parseInt(moduleId)>0){
			viewHdr.add("Module Name");
			viewHdr.add("D");
		}
		viewHdr.add("Counter Name");
		viewHdr.add("D");
		viewHdr.add("I P Address");
		viewHdr.add("D");
		viewHdr.add("Location");
		viewHdr.add("D");
		viewHdr.add("Effective From");
		viewHdr.add("D");
		viewHdr.add("Remarks");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
		// return null;
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "GBLSTR_COUNTER_NAME" };
		return orderBy;
		// return null;
	}

	public String getMainQuery(String strCmb[]) {

		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String moduleId= httpSession.getAttribute("USERVALUE").toString();
		StringBuffer brMainQuery = new StringBuffer(500);
		
		brMainQuery.append(
				billing.qryHandler_billing.getQuery(1, "select.counterMst.0")
						.replace("?", hosCode)).append(
				" AND "
						+ billing.qryHandler_billing.getQuery(1,
								"select.counterMst.cond.1").replace("?", "1"));
		if (strCmb != null) {
			
			if (!strCmb[0].equals("0")) {
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, strCmb[0]);
			}
		};
		
		if(moduleId!=null && Integer.parseInt(moduleId)>0){
		brMainQuery.append(" AND "
				+ billing.qryHandler_billing.getQuery(1,
				"select.counterMst.cond.2").replace("?", moduleId));
	}
		
		return brMainQuery.toString();

	}

	public String[] getSearchField() {
		String search_field[] = { "1", "GBLSTR_COUNTER_NAME", "2",
				"GBLSTR_IP_ADDR" };
		return search_field;
		// return null;
	}

	public String[] getComboHeader() {
		String comboHeader[] = { "1", "Record Status" };
		return comboHeader;
		// return null;
	}

	public String[] getColumnHeader() {
		String col_header[] = { "Counter Name", "I P Address",
				"Location", "Effective From" };
		return col_header;
	}

	public String[] getComboQuery() {
		String comboQuery[] = new String[1];
		
		comboQuery[0] = "1^Active#2^InActive";

		return comboQuery;
		// return null;
	}

	public String getViewQuery() {
		String qry = billing.qryHandler_billing.getQuery(1, "select.counterMst.2");
		
		String moduleId= httpSession.getAttribute("USERVALUE").toString();
		if(moduleId!=null && Integer.parseInt(moduleId)>0){
			qry = billing.qryHandler_billing.getQuery(1, "select.counterMst.6");
		}
		
		System.out.println("View Query"+qry);
		return qry;
	}

	public String getButtons() {
		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;'; title='Click To Add A Record' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;'; title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;'; title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;'; title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;'; title='Click To Generate Reports'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		 */
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,
				"delete.counterMst.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "
				+ billing.qryHandler_billing.getQuery(1,
						"delete.counterMst.cond.0"));
		return deleteQuery;
		// return null;
	}

	public String getJsFiles() {
		String jsFile = new String("../../billing/js/billing.js");
		return jsFile;
		// return null;
	}
	public String isGlobal() {
		return "0";
	}
}
