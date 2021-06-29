package billing.masters.controller.util;

/* Group Master UTL
 * author: Debashis Sardar
 * Created on : 09-Sep-2011
 */

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class GroupMstUTL implements MasterInterface
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
		String masterName = "Local Group Master";
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
	public String[] getComboHeader()
	{
		String[] comboHeader = { "1", "Record Status" };
		return comboHeader;
	}
	public String[] getComboQuery()
	{
		String comboQuery[] = new String[1];
		comboQuery[0] = "1^Active#2^InActive";
		return comboQuery;
	}
	public String[] getColumnHeader()
	{
		String col_header[] = { "Group Code","Global Group Name","Group Name" };
		return col_header;
	}
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "HBLSTR_GROUP_NAME" };
		return orderBy;
	}
	public String getMainQuery(String[] cmb)
	{
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(billing.qryHandler_billing.getQuery(1,"select.grp.0").replace("?", hosCode)).append(
				" AND "	+ billing.qryHandler_billing.getQuery(1,"select.grp.cond.1").replace("?", "1"));

		if (cmb != null)
		{
			if (!cmb[0].equals("0"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("isvalid", cmb[0]);
			}
		}
		//System.out.println("mainQry" + brMainQuery.toString());
		return brMainQuery.toString();
	}	
	public String getViewQuery()
	{
		return billing.qryHandler_billing.getQuery(1, "select.grp.2");
	}
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Group Code");
		viewHdr.add("D");
		viewHdr.add("GLobal Group Name");
		viewHdr.add("D");
		viewHdr.add("Group Name");
		viewHdr.add("D");		
		viewHdr.add("Record Status");
		viewHdr.add("D");

		return viewHdr;
	}
	public String getButtons()
	{
		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record'  border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png'  style='cursor:pointer;' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteGroup2(\"DELETE\");' onClick='deleteGroup2(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteGroup2(\"DELETE\");' onClick='deleteGroup2(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[2];
		String seatId = httpSession.getAttribute("SEATID").toString();
		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,"billing.grp.delete.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ billing.qryHandler_billing.getQuery(1,"billing.grp.delete.cond.0"));
		deleteQuery[1] = billing.qryHandler_billing.getQuery(1,"billing.grp.delete.1").replace("?", seatId);
		deleteQuery[1] = deleteQuery[1].concat(" where "+ billing.qryHandler_billing.getQuery(1,"billing.grp.delete.cond.0"));
		return deleteQuery;
	}
	public String[] getSearchField()
	{
		String search_field[] = { "2", "HBLSTR_GROUP_NAME" };
		return search_field;
	}
	public String getJsFiles()
	{
		String files = "../../billing/js/billing.js";
		return files;
	}
	
	public String isGlobal()
	{
		return "0";
	}
}