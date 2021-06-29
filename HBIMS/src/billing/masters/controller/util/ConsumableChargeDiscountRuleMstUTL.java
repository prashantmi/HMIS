/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Consumable Charge Discount Rule Master UTIL
 * 
 * Created on: 08-09-2011
 */

package billing.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/*
 * Class : ConsumableChargeDiscountRuleMasterUTL
 */

public class ConsumableChargeDiscountRuleMstUTL implements MasterInterface {

	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session) {

		this.httpSession = session;
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
		String masterName = "Consumable Charge Discount Rule Master";
		return masterName;
	}

	public String[] getColumnHeader() {

		String[] col_header = { "Hospital Service", "Ward Type",
				"Patient Category", "Discount Value" };
		return col_header;
	}

	public String[] getSearchField() {

		String search_field[] = { "1", "HOSPITAL_SERVICE", "3",
				"PATIENT_CATEGORY" };
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
		comboQuery[0] = "1^Active#2^Inactive";

		return comboQuery;
	}

	public String getMainQuery(String[] cmb) {

		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String query=billing.qryHandler_billing.getQuery(1, "select.ccruleMst.0");
		try
		{
		
		query=query.replace("?","#");
		
		query=query.replaceFirst("#", hosCode);
				if (cmb != null) {
				if(cmb[0].equals("1"))
				{
				query+=billing.qryHandler_billing.getQuery(1, "select.ccruleMst.cond.0");
				}
				else
				{
					query+=billing.qryHandler_billing.getQuery(1, "select.ccruleMst.cond.1");
				}
		}
		else{
				query+=billing.qryHandler_billing.getQuery(1, "select.ccruleMst.cond.0");
				}
				
				
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return query;
	}

	public String[] getComboHeader() {

		String[] comboHeader = { "1", "Record Status" };
		return comboHeader;
	}

	public String getViewQuery() {

		return billing.qryHandler_billing.getQuery(1, "select.ccRuleMst.6");

	}

	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Hospital Service");
		viewHdr.add("D");
		viewHdr.add("Ward Type");
		viewHdr.add("D");
		viewHdr.add("Patient Category");
		viewHdr.add("D");
		viewHdr.add("Discount Value");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
	}

	public String[] getOrderBy() {

		String orderBy[] = { "1", "HOSPITAL_SERVICE" };
		return orderBy;
	}

	public String[] getDeleteQuery() {

		String[] strDeleteQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		strDeleteQuery[0] = billing.qryHandler_billing.getQuery(1,
				"delete.ccRuleMst.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" where "
				+ billing.qryHandler_billing.getQuery(1,
						"delete.ccRuleMst.cond.0"));
		return strDeleteQuery;
	}

	public String getButtons() {

		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;'border=0 tabindex='1' title='Click To Add A New Record' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png'  style='cursor:pointer;' border=0 tabindex='1' title='Select A Record To Modify' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'>");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)' border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View' border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Click To Generate Reoprt' border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
*/
		br.append("<a class='btn btn-sm btn-primary' tabindex='1' title='Click To Add A New Record' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>Add</a>");
		br.append("<a class='btn btn-sm btn-primary' border=0 tabindex='1' title='Select A Record To Modify' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'>Modify</a>");
		br.append("<a class='btn btn-sm btn-primary' title='Select One Or More CheckBox To Delete Record(s)' border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>Delete</a>");
		br.append("<a class='btn btn-sm btn-primary' title='Select A Record To View' border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>View</a>");
		br.append("<a class='btn btn-sm btn-primary' title='Click To Generate Reoprt' border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>Report</a>");

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
