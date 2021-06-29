package billing.masters.controller.util;

/* Global Sub Group Master UTIL
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;

public class GlobalSubGroupMstUTIL implements MasterInterface
{

	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session)
	{
		this.httpSession = session;
	}
	public String[] getColsWidth()
	{
		return null;
	}
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public String getMasterName()
	{
		String masterName = "Global Sub Group Master";
		return masterName;
	}
	public int getRecord_per_page()
	{
		return 10;
	}
	public int getPage_per_block()
	{
		return 10;
	}
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Group Name");
		viewHdr.add("D");
		
		viewHdr.add("Sub Group Code");
		viewHdr.add("D");
		
		viewHdr.add("Sub Group Name");
		viewHdr.add("D");

		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
	}
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "HBLSTR_SUBGROUP_CODE", "2", "HBLSTR_SUBGROUP_NAME" };
		return orderBy;
	}
	public String getMainQuery(String[] cmb)
	{
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;

		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(billing.qryHandler_billing.getQuery(1, "select.subgroup.0").replace("?", hosCode)).append(
				" AND "+ billing.qryHandler_billing.getQuery(1,"select.subgroup.cond.1").replace("?", "1"));

		if (cmb != null)
		{
			if (!cmb[1].equals("0"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[1]);
				httpSession.setAttribute("isvalid", cmb[1]);
			}
			if (!cmb[0].equals("0"))
			{
				httpSession.setAttribute("GroupId", cmb[0]);
				brMainQuery.append(" AND "+ billing.qryHandler_billing.getQuery(1,"select.subgroupMst.cond.0").replace("?",cmb[0]));
			}
		} 
		else
			httpSession.setAttribute("isvalid", "1");
		
		System.out.println("main query"+brMainQuery.toString());
		
		return brMainQuery.toString();

	}
	public String[] getSearchField()
	{
		String search_field[] = { "1", "HBLSTR_SUBGROUP_CODE","2", "HBLSTR_SUBGROUP_NAME" };
		return search_field;
	}
	public String[] getColumnHeader()
	{
		String col_header[] = { "Sub Group Code", "Sub Group Name" };
		return col_header;
	}
	public String getViewQuery()
	{
		return billing.qryHandler_billing.getQuery(1, "select.subgroup.2");
	}
	public String getButtons()
	{
		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record'  border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='modifyGlobalGroup(\"MODIFY\");' onClick='modifyGlobalGroup(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteGlobalGroup(\"DELETE\");' onClick='deleteGlobalGroup(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
			*/
		
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return checkGlobalGroup1(document.forms[0]);' onClick='return checkGlobalGroup1(document.forms[0]);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='modifyGlobalGroup(\"MODIFY\");' onClick='modifyGlobalGroup(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id=''  onKeyPress='deleteGlobalGroup(\"DELETE\");' onClick='deleteGlobalGroup(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,"billing.delete.1").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ billing.qryHandler_billing.getQuery(1,"billing.delete.cond.1"));

		return deleteQuery;
	}
	public String getJsFiles()
	{
		String files = "../../billing/js/billing.js";
		return files;
	}
	public String[] getComboHeader()
	{
		String[] comboHeader = { "0", "Group Name", "1", "Record Status" };
		return comboHeader;
	}
	public String[] getComboQuery()
	{
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE.toString();
		String[] comboQuery = new String[10];
		comboQuery[1] = "1^Active#2^InActive";
		comboQuery[0] = billing.qryHandler_billing.getQuery(1,"select.subgroupMst.1").replace("?", hosCode);
		return comboQuery;
		
	}
	public String isGlobal()
	{
		return "1";
	}
}