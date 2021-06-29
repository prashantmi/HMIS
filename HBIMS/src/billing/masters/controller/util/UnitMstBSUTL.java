

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Unit Master UTIL
 * 
 * Created on: 14-09-2011
 */
package billing.masters.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;
import hisglobal.masterutil.MasterInterface;

public class UnitMstBSUTL implements MasterInterface 
{	

	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;
	HttpServletRequest hisrequest = null;
	String strModuleId = "";

	public String[] getColsWidth() 
	{
		return null;
	}
	public boolean reportInterFaceRequired() 
	{
		return false;
	}
	public String getButtons() 
	{
		StringBuilder br = new StringBuilder();
		
		/*br.append("<img src='/HBIMS/hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='return callMe(document.forms[0]);' onClick='return callMe(document.forms[0]);'>");
		br.append("<img src='/HBIMS/hisglobal/images/btn-mo.png' style='cursor:pointer;' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='/HBIMS/hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='return isBaseUnitDelete(document.forms[0]);' onClick='return isBaseUnitDelete(document.forms[0]);'>");
		br.append("<img src='/HBIMS/hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='/HBIMS/hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reports'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return callMe(document.forms[0]);' onClick='return callMe(document.forms[0]);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) edit(\"MODIFY\");' 			onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return isBaseUnitDelete(document.forms[0]);' onClick='return isBaseUnitDelete(document.forms[0]);'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' data-toggle='modal' data-target='#viewModal' onKeyPress='if(event.keyCode==13) viewBS(\"VIEWDATABS\");' 							onClick='viewBS(\"VIEWDATABS\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  						onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		
		return br.toString();
	}

	public String[] getColumnHeader() 
	{
		String strColHeader[] = { "Unit Name", "Base Unit Name"};
		return strColHeader;
	}
	public String[] getComboHeader() 
	{
		String[] strComboHeader = { "0", "Module Name", "1", "Record Status" };
		return strComboHeader;
	}

	public String[] getComboQuery() 
	{
		//strModuleId = httpSession.getAttribute("USERVALUE").toString();
		if(httpSession.getAttribute("USERVALUE").toString() != null)
		{
			strModuleId = httpSession.getAttribute("USERVALUE").toString();
		}
		else
		{
			strModuleId = "0";
		}

		String[] strComboQuery = new String[2];

		strComboQuery[0] = billing.qryHandler_billing.getQuery(1,"select.unitMst.2");
		if(!strModuleId.equals("0"))
		{
			strComboQuery[0] = strComboQuery[0].concat(" AND "+ billing.qryHandler_billing.getQuery(1,"select.unitMst.cond.2")).replace("?", strModuleId);
		}
		
		strComboQuery[1] = "1^Active#2^InActive";

		//System.out.println("strComboQuery[0] "+strComboQuery[0] );
		return strComboQuery;
	}

	public String[] getDeleteQuery() 
	{
		String[] strDeleteQuery = new String[2];
		// HttpServletRequest request = new HttpServletRequest();
		String seatId = httpSession.getAttribute("SEATID").toString();

		strDeleteQuery[0] = billing.qryHandler_billing.getQuery(1,"delete.unitMst.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" where "+ billing.qryHandler_billing.getQuery(1,"delete.unitMst.cond.0"));

		strDeleteQuery[1] = billing.qryHandler_billing.getQuery(1,"delete.unitMst.1").replace("?", seatId);
		strDeleteQuery[1] = strDeleteQuery[1].concat(" where "+ billing.qryHandler_billing.getQuery(1,"delete.unitMst.cond.1"));

		return strDeleteQuery;
	}

	public String getJsFiles() 
	{
		String jsFile = new String("../../billing/js/billing.js");
		return jsFile;
	}

	public String getMainQuery(String[] strCmb) 
	{
		//String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;

		StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(billing.qryHandler_billing.getQuery(1, "select.unitMst.0").replace("?", hosCode)).append(" AND "+ billing.qryHandler_billing.getQuery(1,"select.unitMst.cond.0").replace("?", "9"));

		if (strCmb != null) 
		{
			if (!strCmb[0].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("9"), brMainQuery.lastIndexOf("9") + 1, strCmb[0]);
				httpSession.setAttribute("strModuleId", strCmb[0]);
			}

			if (!strCmb[1].equals("0")) 
			{
				brMainQuery.append(" AND "+ billing.qryHandler_billing.getQuery(1,"gbl.unit.cond.4").replace("?", strCmb[1]));
			}
		}
		//System.out.println("brMainQuery.toString()-"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{
		String strMasterName = "Unit Master";
		return strMasterName;
	}

	public String[] getOrderBy() 
	{
		String strOrderBy[] = { "1", "C.GSTR_UNIT_NAME", "2", "C.GSTR_UNIT_NAME" };
		return strOrderBy;
	}

	public int getPage_per_block() 
	{
		return 10;
	}

	public int getRecord_per_page() 
	{
		return 15;
	}

	public String[] getSearchField() 
	{
		String strSearchField[] = { "1", "C.GSTR_UNIT_NAME" };
		return strSearchField;
	}

	public List<String> getViewHeader() 
	{

		List<String> listViewHdr = new ArrayList<String>();

		listViewHdr.add("Module Name");
		listViewHdr.add("D");
		listViewHdr.add("Unit Name");
		listViewHdr.add("D");
		listViewHdr.add("Base Unit Name");
		listViewHdr.add("D");
		listViewHdr.add("Decimal Allowed");
		listViewHdr.add("D");
		listViewHdr.add("Remarks");
		listViewHdr.add("D");
		listViewHdr.add("Record Status");
		listViewHdr.add("D");

		return listViewHdr;
	}

	public String getViewQuery() 
	{
		String strViewQuery = billing.qryHandler_billing.getQuery(1,"select.unitMst.4");
		return strViewQuery;
	}

	public void setHttpSession(HttpSession session) 
	{
		this.httpSession = session;
	}
	public String isGlobal() 
	{
		return "1";
	}
}
