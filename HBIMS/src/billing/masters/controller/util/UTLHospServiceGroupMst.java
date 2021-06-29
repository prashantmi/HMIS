package billing.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;

public class UTLHospServiceGroupMst implements MasterInterface {

	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session) {

		httpSession = session;

	}


	public String[] getColsWidth() {
		return null;
	}

	public boolean reportInterFaceRequired() {
		return false;
	}
	public String getMasterName() {
		String masterName = "Hospital Service Group Master";
		return masterName;
	}

	public String[] getColumnHeader() {

		String[] columnHeader = { "Service Name", "Group Name"};
		return columnHeader;
	}

	public String getViewQuery() {
		// System.out.println("View Name");
		// System.out.println("View
		// Name"+billing.qryHandler_billing.getQuery(1,"select.hservicegroup.2"));
		return billing.qryHandler_billing.getQuery(1, "select.hservicegroup.2");
		// return null;

	}

	public String[] getSearchField() {
		String[] searchField = { "2", "C.GRP" };
		return searchField;
	}

	public int getRecord_per_page() {
		// TODO Auto-generated method stub
		return 10;
	}

	public int getPage_per_block() {
		// TODO Auto-generated method stub
		return 10;
	}

	public String getMainQuery(String[] cmb) {

		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		// System.out.println("hosCode-->"+hosCode);
		StringBuffer brMainQuery = new StringBuffer();

		brMainQuery.append(
				billing.qryHandler_billing
						.getQuery(1, "select.hservicegroup.0").replace("?",hosCode)).append(
				" WHERE "
						+ billing.qryHandler_billing.getQuery(1,"select.hservicegroup.cond.0")
								.replace("?", "0"));

		if (cmb != null) {

			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery.lastIndexOf("0") + 1, cmb[0]);

			}
			if (!cmb[1].equals("0")) {
				brMainQuery.append(" and "
						+ billing.qryHandler_billing.getQuery(1,
								"select.hservicegroup.cond.1").replace("?",cmb[1]));

				// httpSession.setAttribute("isvalid", cmb[1]);
			}

		}

		// System.out.println("brMainQuery after execute qry-->"+brMainQuery);
		return brMainQuery.toString();
	}

	public String[] getComboHeader() {
		String[] comboHeader = { "0", "Service Name", "1", "Record Status" };
		return comboHeader;
	}

	public String[] getComboQuery() {
		// //System.out.println("inside combo query");

		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE ;

		String comboQuery[] = new String[5];
		comboQuery[0] = billing.qryHandler_billing.getQuery(1,"select.hservicegroup.1").replace("?", hosCode);
		comboQuery[1] = "1^Active#2^InActive";
		// System.out.println("comboQuery[1]"+comboQuery[1]);
		return comboQuery;
		// return null;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Hospital Service");
		viewHdr.add("D");
		viewHdr.add("Group Name");
		viewHdr.add("D");
		viewHdr.add("Effective From");
		viewHdr.add("D");
		viewHdr.add("Remark");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		// System.out.println("view header");
		return viewHdr;
		// return null;
	}

	public String[] getOrderBy() {
		String orderBy[] = { "2", " C.GRP " };
		// System.out.println("Order By");
		return orderBy;
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,
				"delete.hservicegroup.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "
				+ billing.qryHandler_billing.getQuery(1,
						"delete.hservicegroup.cond.0"));
		// System.out.println("Delet Query : "+deleteQuery[0]);
		return deleteQuery;
		// return null;
	}

	public String getButtons() {
		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;cursor:hand;' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;cursor:hand;' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;cursor:hand;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;cursor:hand;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;cursor:hand;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
		
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	public String getJsFiles() {
		return null;
	}


	public String isGlobal() {
		return null;
	}

}
