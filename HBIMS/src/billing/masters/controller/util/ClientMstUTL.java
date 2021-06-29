

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Client Master UTIL
 * 
 * Created on: 30-08-2011
 */
package billing.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;


public class ClientMstUTL implements MasterInterface 
{

	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;
	public String[] getColsWidth()
	{
		return null;
	}

	public boolean reportInterFaceRequired() 
	{
		return false;
	}
	/**
	 * returns List pages Buttons Strings.
	 */
	public String getButtons()
	{

		StringBuilder br = new StringBuilder();

		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' border=0 tabindex='1' onKeyPress='addClientMst(\"ADD\");' title='Click To Add A Record' onClick='addClientMst(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;'  border=0 tabindex='1' title='Select A Record To Modify' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reports'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='addClientMst(\"ADD\");' title='Click To Add A Record' onClick='addClientMst(\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	/**
	 * returns Column Header String Array
	 */
	public String[] getColumnHeader() 
	{
	
		//String strColHeader[] = {"Client Name", "Registration No", "Patient Category","Payment Type"};
		
		/* Modified by : Manisha 07/09/2015
		   To remove one column from List Page of Client Master
		*/
		
		String strColHeader[] = {"Client Code","Client Name", "Registration No","Payment Type"};
		return strColHeader;
	}

	/**
	 * returns Combo Header String Array
	 */
	
	public String[] getComboHeader() 
	{

		String[] comboHdr = {"0", "Client Type","1","Record Status" };
		return comboHdr;
	}

	/**
	 * returns Combo Query String Array
	 */
	public String[] getComboQuery() 
	{
		String[] comboQuery = new String[2];
		comboQuery[0] = billing.qryHandler_billing.getQuery(1,"select.clientTypeName.0");
		comboQuery[1] = "1^Active#2^InActive";
		
		return comboQuery;
		// return null;
	}

	/**
	 * returns Delete Query String Array
	 */
	public String[] getDeleteQuery() 
	{

		String[] strDeleteQuery = new String[1];
		
		String seatId = httpSession.getAttribute("SEATID").toString();
		
		strDeleteQuery[0] = billing.qryHandler_billing.getQuery(1,"delete.ClientMst.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0] +" WHERE "+ billing.qryHandler_billing.getQuery(1,"delete.ClientMst.cond.0");
		// System.out.println("in utl del qry-->"+strDeleteQuery);
		return strDeleteQuery;
		// return null;
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

		String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();
		String superHospCode=BillConfigUtil.SUPER_HOSPITAL_CODE;	
		StringBuffer brMainQuery = new StringBuffer(500);	
		brMainQuery.append(billing.qryHandler_billing.getQuery(1, "select.ClientMst.0").replace("?", hosCode)).append(	" AND "	+ billing.qryHandler_billing.getQuery(1,"select.ClientMst.cond.0").replace("?", "1"));
		if (strCmb != null)
		{

			if (!strCmb[1].equals("0")) 
			{
				// 1param:start index 2param:end index,3 param value to be replaced..
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, strCmb[1]);
				
			}
			
			if (!strCmb[0].equals("0")) 
			{
				brMainQuery.append(" and sblnum_client_type_id="+strCmb[0]);
				//brMainQuery = brMainQuery.append(")");
			}
			else
			{
				//brMainQuery = brMainQuery.append(")");
			}
			
		}
		else
		{
			//brMainQuery = brMainQuery.append(")");
		}

		//System.out.println("--------------->utl main qry clientMstUTL-->"+ brMainQuery.toString());

		return brMainQuery.toString();
		
	}

	/**
	 * returns Master Name String
	 */
	public String getMasterName()
	{
		String strMasterName = "Client Master";
		return strMasterName;
	}

	/**
	 * returns Order By String Array
	 */
	public String[] getOrderBy()
	{

		String strOrderBy[] = { "1", "clientCode", "2", "clientName" };

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

		String strSearchField[] = { "1", "HBLSTR_CLIENT_CODE", "2", "HBLSTR_CLIENT_NAME" };
		return strSearchField;
	}

	/**
	 * returns View Header List
	 */
	public List<String> getViewHeader()
	{

		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Client Code");
		viewHdr.add("D");
		viewHdr.add("Client Name");
		viewHdr.add("D");
		viewHdr.add("Reg No");
		viewHdr.add("D");
		viewHdr.add("Category");
		viewHdr.add("D");
		viewHdr.add("Client Type");
		viewHdr.add("D");
		viewHdr.add("Ipd Payment Rule(Paid By Client)");
		viewHdr.add("D");
		viewHdr.add("Payment Type");
		viewHdr.add("D");
		
		return viewHdr;
	}

	/**
	 * returns View Query String Array
	 */
	public String getViewQuery() 
	{

		String strViewQuery = billing.qryHandler_billing.getQuery(1,"view.ClientMst.0");
		// System.out.println("View Query is=>"+strViewQuery);
		return strViewQuery;
		// return null;
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
		return "0";
	}
}
