package billing.masters.controller.util;
import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;

public class AccStatUTIL implements MasterInterface
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
		String masterName = "ACCOUNT STATUS";
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
		viewHdr.add("Account Status");
		viewHdr.add("D");

		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
	}
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "SBLSTR_ACCSTATUS_NAME" };
		return orderBy;
	}
	public String getMainQuery(String[] cmb)
	{
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;

		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(billing.qryHandler_billing.getQuery(1, "select.acst.1"));

		if (cmb != null)
		{
			if (!cmb[0].equals("0"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("isvalid", cmb[0]);
			}
		} 
		else
			httpSession.setAttribute("isvalid", "1"); 
		
		System.out.println("main query"+brMainQuery.toString());
		
		return brMainQuery.toString();

	}
	public String[] getSearchField()
	{
		String search_field[] = { "1", "SBLSTR_ACCSTATUS_NAME" };
		return search_field;
	}
	public String[] getColumnHeader()
	{
		String col_header[] = { "Account Status" };
		return col_header;
	}
	public String getViewQuery()
	{
		return billing.qryHandler_billing.getQuery(1, "select.acst.1");
	}
	public String getButtons()
	{
		StringBuilder br = new StringBuilder();
/*			br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
			br.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:pointer;' title='CANCEL'  border=0  tabindex='1' onKeyPress='cancel(\"CANCEL\");'  onClick='cancel(\"CANCEL\");'>");

*/			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To Generate Reoprts' tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>Report</a>");
			br.append("<a class='btn btn-sm btn-primary' title='CANCEL' tabindex='1' onKeyPress='cancel(\"CANCEL\");'  onClick='cancel(\"CANCEL\");'>Cancel</a>");

		return br.toString();
	}
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,"billing.delete.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ billing.qryHandler_billing.getQuery(1,"billing.delete.cond.0"));

		return deleteQuery;
	}
	public String getJsFiles()
	{
		String files = "../../billing/js/billing.js";
		return files;
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
	public String isGlobal()
	{
		return "1";
	}
}







