package billing.masters.controller.util;
/* Unit Value Master UTL
 * author: Debashis Sardar
 * Created on : 16-Sep-2011
 */
import hisglobal.masterutil.MasterInterface;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;

public class UnitValueMstBSUTL implements MasterInterface {

	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;
	String strModuleId = "";

	public void setHttpSession(HttpSession session) {

		httpSession = session;

	}


	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getMasterName() {

		String masterName = "Unit Value Master";

		return masterName;
	}

	public String[] getColumnHeader() {

		String[] columnHeader = { "From Unit", "To Unit", "Converted Value",
				"Effective From" };

		return columnHeader;

	}

	public String[] getSearchField() {

		String[] searchField = { "1",
				"BILL_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,GNUM_FRMUNIT_ID)",
				"2", "BILL_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,GNUM_TOUNIT_ID)",
				"3", "GNUM_CONVERTED_UNITVALUE" };

		return searchField;

	}

	public int getRecord_per_page() {

		return 10;
	}

	public int getPage_per_block() {

		return 10;
	}

	public String getMainQuery(String[] strCmb) {
		StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(
				billing.qryHandler_billing.getQuery(1, "select.unitvalue.7")
						.replace(
								"?",
								httpSession.getAttribute("HOSPITAL_CODE")
										.toString())).append(
				" and "
						+ billing.qryHandler_billing.getQuery(1,
								"select.unitvalue.cond.0").replace("?", "3"));

		if (strCmb != null) {
			// condition first
			if (!strCmb[1].equals("0")) {

				brMainQuery.replace(brMainQuery.lastIndexOf("3"), brMainQuery
						.lastIndexOf("3") + 1, strCmb[1]);
				
			}

			// condition second
			brMainQuery=brMainQuery.append(" AND "
					+ billing.qryHandler_billing.getQuery(1,
							"select.unitvalue.cond.1").replace("?","0"));
			if (!strCmb[0].equals("0"))
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery
						.lastIndexOf("0") + 1, strCmb[0]);
			httpSession.setAttribute("strModuleId", strCmb[0]);
		}

	
		return brMainQuery.toString();

	}

	public String[] getComboHeader() {
		String[] comboHeader = { "0", "Module Name", "1", "Record Status" };
		return comboHeader;
	}

	public String[] getComboQuery() {

		String[] comboQuery = new String[4];
		
		
		if(httpSession.getAttribute("USERVALUE").toString() != null){
			strModuleId = httpSession.getAttribute("USERVALUE").toString();
		}else{
			strModuleId = "0";
		}


		//String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;

		comboQuery[0] = billing.qryHandler_billing.getQuery(1,
		"select.unitvalue.1").replace("?",hosCode);
		
	if(!strModuleId.equals("0"))
		{
		comboQuery[0] = comboQuery[0].concat(" AND "
				+ billing.qryHandler_billing.getQuery(1,
						"select.unitValMst.cond.2")).replace("?", strModuleId);
		}
		
		comboQuery[1] = "1^Active#2^InActive";

	
		return comboQuery;

	}

	public String getViewQuery() {
		return billing.qryHandler_billing.getQuery(1, "select.unitvalue.2");

	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Module Name");
		viewHdr.add("D");
		viewHdr.add("From Unit");
		viewHdr.add("D");
		viewHdr.add("To Unit");
		viewHdr.add("D");
		viewHdr.add("Converted Value");
		viewHdr.add("D");
		viewHdr.add("Effective From");
		viewHdr.add("D");
		viewHdr.add("Remarks");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");

		return viewHdr;

	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "GNUM_FRMUNIT_ID" };

		return orderBy;

	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,
				"delete.unitvalue.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "
				+ billing.qryHandler_billing.getQuery(1,
						"delete.unitvalue.cond.0"));
		return deleteQuery;

	}

	public String getButtons() {
		StringBuilder br = new StringBuilder();

		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='return checkModule(document.forms[0]);' onClick='return checkModule(document.forms[0]);' >");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		 */
		
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return checkModule(document.forms[0]);' onClick='return checkModule(document.forms[0]);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) edit(\"MODIFY\");' 			onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' 	onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' data-toggle='modal' data-target='#viewModal' onKeyPress='if(event.keyCode==13) viewBS(\"VIEWDATABS\");' 							onClick='viewBS(\"VIEWDATABS\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  						onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		
		return br.toString();
	}

	public String getJsFiles() {
		String files = "../../billing/js/billing.js";
		return files;

	}
	public String isGlobal() {
		return "0";
	}

}
