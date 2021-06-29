package billing.masters.controller.util;
/* Charge Master UTL
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import hisglobal.masterutil.MasterInterface;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import billing.BillConfigUtil;
import billing.qryHandler_billing;

public class ChargeMstUTL implements MasterInterface {

	private static final long serialVersionUID = 02L;


	public String[] getColsWidth() {
		return null;
	}

	public boolean reportInterFaceRequired() {
		return false;
	}
	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session) {

		httpSession = session;
	}

	public String getMasterName() {

		String masterName = "Charge Master";
		return masterName;
	}

	public String[] getColumnHeader() {

		String[] columnHdr = {  "Tariff Code", "Tariff Name","Patient Category Name","Rate / Unit",
				"Is Package", "Validity(From-To)" };
		return columnHdr;
	}

	public String[] getSearchField() {

		String search_field[] = { "1", "A.TARIFF_CODE", "2", "A.TARIFF_NAME" };
		return search_field;
	}

	public int getRecord_per_page() {

		return 10;
	}

	public int getPage_per_block() {

		return 10;
	}

	public String[] getComboQuery() {

		String[] comboQuery = new String[10];
		String superHospCode=BillConfigUtil.SUPER_HOSPITAL_CODE ;
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		comboQuery[0] = billing.qryHandler_billing.getQuery(1,"gbl.chargetype.0").replace("?", superHospCode);
		comboQuery[1] = billing.qryHandler_billing.getQuery(1, "gbl.group.1").replace("?", hosCode);
		comboQuery[2] = billing.qryHandler_billing.getQuery(1, "gbl.cat.x").replace("?", hosCode);
		comboQuery[3] = billing.qryHandler_billing.getQuery(1,"gbl.ipdchargetype.1").replace("?", hosCode);
		System.out.println(comboQuery[2]);

		return comboQuery;
	}

	public String getMainQuery(String[] cmb) {

		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(
				billing.qryHandler_billing.getQuery(1, "select.chargeMst.0")
						.replace("?", hosCode)).append(
				" AND "
						+ billing.qryHandler_billing.getQuery(1,
								"select.chargeMst.cond.0").replace("?", "8"));
				
		if (cmb != null) {

			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("8"), brMainQuery
						.lastIndexOf("8") + 1, cmb[0]);
				
			}

			if (!cmb[1].equals("0")) {
				
				brMainQuery.append(
						" AND "
						+ billing.qryHandler_billing.getQuery(1,
								"select.chargeMst.cond.3").replace("?",
								"6"));
				
				brMainQuery.replace(brMainQuery.lastIndexOf("6"), brMainQuery
						.lastIndexOf("6") + 1, cmb[1]);
			}

			if (!cmb[2].equals("0")) {
				
				brMainQuery.append(
						" AND "
						+ billing.qryHandler_billing.getQuery(1,
								"select.chargeMst.cond.1").replace("?",
								"9"));
				
				
				brMainQuery.replace(brMainQuery.lastIndexOf("9"), brMainQuery
						.lastIndexOf("9") + 1, cmb[2]);
			}
			

			
			if (!cmb[3].equals("0")) {

				brMainQuery.append("AND ").append(
						billing.qryHandler_billing.getQuery(1,
								"select.chargeMst.cond.2").replace("?", "7"));

				
				brMainQuery.replace(brMainQuery.lastIndexOf("7"), brMainQuery
						.lastIndexOf("7") + 1, cmb[3]);

			}
		}
		
		return brMainQuery.toString();
	}

	public String[] getComboHeader() {

		String[] comboHdr = { "0", "Hospital Service", "0", "Group Name", "0",
				"Patient Category", "0", "Ward Type" };
		return comboHdr;
	}

	public String getViewQuery() {
		
		return qryHandler_billing.getQuery(1, "select.chargeMst.11");
		// return null;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Tariff Code");
		viewHdr.add("D");
		viewHdr.add("Tariff/Package Name");
		viewHdr.add("D");
		viewHdr.add("Patient Category Name");
		viewHdr.add("D");
		viewHdr.add("Procedure Cost");
		viewHdr.add("D");
		viewHdr.add("Tariff Cost");
		viewHdr.add("D");
		viewHdr.add("Total Cost");
		viewHdr.add("D");
		viewHdr.add("Is Package");
		viewHdr.add("D");
		viewHdr.add("Is Advance");
		viewHdr.add("D");
		viewHdr.add("Is Refundable");
		viewHdr.add("D");
		viewHdr.add("Effective From");
		viewHdr.add("D");
		viewHdr.add("Effective To");
		viewHdr.add("D");
		viewHdr.add("Remarks");
		viewHdr.add("D");
		return viewHdr;
		// return null;
	}

	public String[] getOrderBy() {

		String orderBy[] = {  "1", "A.TARIFF_CODE", "2", "A.TARIFF_NAME" };
		return orderBy;
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[2];
		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,
				"delete.chargeMst.0");
		deleteQuery[1] = billing.qryHandler_billing.getQuery(1,
				"delete.chargeMst.1");
		return deleteQuery;
	}

	public String getButtons() {

		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='modifyPage(document.forms[0]);' onClick='modifyPage(document.forms[0]);' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onClick='deleteData(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Click To Generate Report'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");		
		*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='modifyPage(document.forms[0]);' onClick='modifyPage(document.forms[0]);'><span class='modify'>Modify</span></a>");
		//br.append("<a href='#' class='btn btn-sm btn-primary' id=''onClick='deleteData(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	public String getJsFiles() {

		return "../js/ipdCharge.js";
	}
	public String isGlobal() {
		return "0";
	}
}
