package billing.masters.controller.util;

/* Global Tariff Master UTL
 * author: Debashis Sardar
 * Created on : 14-Sep-2011
 */

import hisglobal.masterutil.MasterInterface;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import billing.BillConfigUtil;

public class GlobalTariffMstUTL implements MasterInterface
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
		String masterName = "Global Tariff Master";
		return masterName;
	}
	public String[] getColumnHeader()
	{
		String[] col_header = { "Tariff Name" };
		return col_header;
	}
	public String[] getSearchField()
	{
		String search_field[] = { "1", "TARIFF_NAME" };
		return search_field;
	}
	public int getRecord_per_page()
	{
		return 10;
	}
	public int getPage_per_block()
	{
		return 10;
	}
	public String[] getComboQuery()
	{
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE.toString();
		String[] comboQuery = new String[10];
		comboQuery[1] = "1^Active#2^InActive";
		comboQuery[0] = billing.qryHandler_billing.getQuery(1,"select.tariffMst.1").replace("?", hosCode);
		return comboQuery;
	}
	public String getMainQuery(String[] cmb)
	{
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE.toString();
		StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(billing.qryHandler_billing.getQuery(1, "select.global.tariffMst.0").replace("?", hosCode)).append(
				" WHERE " + billing.qryHandler_billing.getQuery(1,"select.tariffMst.cond.1").replace("?", "1"));

		if (cmb != null)
		{
			if (!cmb[1].equals("1"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[1]);				
			}
			if (!cmb[0].equals("0"))
			{
				httpSession.setAttribute("GroupId", cmb[0]);
				brMainQuery.append(" AND "+ billing.qryHandler_billing.getQuery(1,"select.tariffMst.cond.0").replace("?",cmb[0]));
			}
		}
		
		else
		{
			httpSession.setAttribute("GroupId", "0");
			brMainQuery.append(" AND "+ billing.qryHandler_billing.getQuery(1,"select.tariffMst.cond.0").replace("?","0"));
		}
			
		System.out.println("main query"+brMainQuery.toString());
		return brMainQuery.toString();
	}
	public String[] getComboHeader()
	{
		String[] comboHeader = { "0", "Group Name", "1", "Record Status" };
		return comboHeader;
	}
	public String getViewQuery()
	{
		return billing.qryHandler_billing.getQuery(1, "select.tariffMst.10");

	}
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Group Name");
		viewHdr.add("D");
		viewHdr.add("Sub Group Name");
		viewHdr.add("D");
		viewHdr.add("Tariff Name");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
	}
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "TARIFF_NAME" };
		return orderBy;
	}
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[3];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,"delete.tariffMst.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ billing.qryHandler_billing.getQuery(1,"delete.tariffMst.cond.0"));

		deleteQuery[1] = billing.qryHandler_billing.getQuery(1,"delete.tariffMst.1").replace("?", seatId);
		deleteQuery[1] = deleteQuery[1].concat(" where "+ billing.qryHandler_billing.getQuery(1,"delete.tariffMst.cond.1"));

		deleteQuery[2] = billing.qryHandler_billing.getQuery(1,"delete.tariffMst.2").replace("?", seatId);
		deleteQuery[2] = deleteQuery[2].concat(" where "+ billing.qryHandler_billing.getQuery(1,"delete.tariffMst.cond.2"));

		return deleteQuery;
	}
	public String getButtons()
	{
		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png'  style='cursor:pointer;' border=0 tabindex='1' title='Click To Add A New Record' onKeyPress='return checkGlobalGroup(document.forms[0]);' onClick='return checkGlobalGroup(document.forms[0]);'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png'   style='cursor:pointer;' border=0 tabindex='1' title='Select A Record To Modify' onKeyPress='modifyGlobalTariff(\"MODIFY\");' onClick='modifyGlobalTariff(\"MODIFY\");'>");
		br.append("<img src='../../hisglobal/images/btn-del.png'  style='cursor:pointer;' border=0 title='Select One Or More CheckBox To Delete Record(s)' tabindex='1' onKeyPress='deleteGlobalTariff(\"DELETE\");' onClick='deleteGlobalTariff(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' border=0 title='Select A Record To View' tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png'  style='cursor:pointer;' border=0 title='Click To Generate Reoprt' tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
			*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return checkGlobalGroup(document.forms[0]);' onClick='return checkGlobalGroup(document.forms[0]);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='modifyGlobalTariff(\"MODIFY\");' onClick='modifyGlobalTariff(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteGlobalTariff(\"DELETE\");' onClick='deleteGlobalTariff(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}
	public String getJsFiles()
	{
		String files = "../../billing/js/billing.js";
		return files;
	}
	public String isGlobal()
	{
		return "1";
	}
}