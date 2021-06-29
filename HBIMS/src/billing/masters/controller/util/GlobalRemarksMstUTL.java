/* Global  Remarks Master UTIL
 * author: Pawan Kumar B N
 * Created on : 26-Aug-2011
 */
package billing.masters.controller.util;

import billing.BillConfigUtil;
import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/*
 * The Class RemarksMstUTIL.
 */
public class GlobalRemarksMstUTL implements MasterInterface
{

	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;

	/**
	 * returns Master Name String
	 */
	public String getMasterName()
	{

		String strMasterName = "Global Remarks Master";

		return strMasterName;
	}
	public String[] getColsWidth()
	{
		return null;
	}
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	/**
	 * returns Column Header String Array
	 */
	public String[] getColumnHeader()
	{
		String strColHeader[] = { "Remarks"};
		return strColHeader;
	}
	/**
	 * returns Combo Header String Array
	 */
	public String[] getComboHeader()
	{
		String[] strComboHeader = { "0", "Remarks Type", "1", "Record Status" };
		return strComboHeader;
	}
	/**
	 * returns Combo Query String Array
	 */
	public String[] getComboQuery()
	{
		String[] strComboQuery = new String[3];
		httpSession.setAttribute("isvalid", "1");
		strComboQuery[0] = billing.qryHandler_billing.getQuery(1,"gbl.remarkstype.0").replace("?",BillConfigUtil.SUPER_HOSPITAL_CODE);
		strComboQuery[1] = "1^Active#2^InActive";

		return strComboQuery;
	}
	/**
	 * returns Delete Query String Array
	 */
	public String[] getDeleteQuery()
	{
		String[] strDeleteQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		strDeleteQuery[0] = billing.qryHandler_billing.getQuery(1,"delete.remarksMst.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" where "+ billing.qryHandler_billing.getQuery(1,"delete.remarksMst.cond.0"));
		return strDeleteQuery;
	}
	/**
	 * returns Jsp File Path String
	 */
	public String getJsFiles()
	{
		return null;
	}
	/**
	 * returns Main Query String
	 */
	public String getMainQuery(String[] strCmb)
	{
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;
		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(billing.qryHandler_billing.getQuery(1, "select.remarksMst.0").replace("?", hosCode)).append(
				" AND "+ billing.qryHandler_billing.getQuery(1,"select.remarksMst.cond.0").replace("?", "5"));

		if (strCmb != null)
		{
			if (!strCmb[0].equals("0"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("5"), brMainQuery.lastIndexOf("5") + 1, strCmb[0]);
				httpSession.setAttribute("isvalid", strCmb[0]);
			}
			if (!strCmb[0].equals("0"))
			{
				httpSession.setAttribute("rmkType", strCmb[1]);
				brMainQuery.append(" AND "+ billing.qryHandler_billing.getQuery(1,"select.remarksMst.cond.1").replace("?",strCmb[1]));
			}
		}
		return brMainQuery.toString();
	}
	/**
	 * returns Order By String Array
	 */
	public String[] getOrderBy()
	{
		String strOrderBy[] = { "1", "HBLSTR_REMARKS" };
		return strOrderBy;
	}
	/**
	 * returns Number of Blocks per Page
	 */
	public int getPage_per_block()
	{
		return 10;
	}
	/**
	 * returns Number of Record Per Page
	 */
	public int getRecord_per_page()
	{
		return 10;
	}
	/**
	 * returns Search Field String Array
	 */
	public String[] getSearchField()
	{
		String strSearchField[] = { "1", "HBLSTR_REMARKS" };
		return strSearchField;
	}
	/**
	 * returns View Header List
	 */
	public List<String> getViewHeader()
	{
		List<String> listViewHdr = new ArrayList<String>();
		listViewHdr.add("Remarks Description");
		listViewHdr.add("T");
		listViewHdr.add("Remarks Type");
		listViewHdr.add("D");
		listViewHdr.add("Record Status");
		listViewHdr.add("D");
		return listViewHdr;
	}

	/**
	 * returns View Query String Array
	 */
	public String getViewQuery()
	{
		String strViewQuery = billing.qryHandler_billing.getQuery(1,"select.remarksMst.3");
		return strViewQuery;
	}
	public String getButtons()
	{
		StringBuilder br = new StringBuilder();

		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'></a>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reports'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}
	/**
	 * sets HttpSession Object.
	 */
	public void setHttpSession(HttpSession session)
	{
		this.httpSession = session;
	}
	public String isGlobal()
	{
		return "1";
	}
}