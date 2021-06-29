/* (PACKAGE SERVICE MASTER)  */
package billing.masters.controller.util;
/* Package Service Master UTL
 * author: Debashis Sardar
 * Created on : 01-Sep-2011
 */
import hisglobal.masterutil.MasterInterface;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class PackageServiceMstUTL implements MasterInterface {

	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session) 
	{
		this.httpSession = session;
	}

	public String getMasterName() 
	{
		String masterName = "Package/Estimation Tariff Mapping Master";
		return masterName;
	}


	public String[] getColsWidth() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reportInterFaceRequired() 
	{
		// TODO Auto-generated method stub
		return false;
	}
	public int getRecord_per_page() 
	{
		return 10;
	}

	public int getPage_per_block() 
	{
		return 10;
	}

	/* All the Header List */
	public List<String> getViewHeader() 
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Package/Estimate Name");
		viewHdr.add("D");
		viewHdr.add("Tariff Type");
		viewHdr.add("D");
		viewHdr.add("Inclusive Tariff Name");
		viewHdr.add("D");
		viewHdr.add("Quantity");
		viewHdr.add("D");
		viewHdr.add("Unit");
		viewHdr.add("D");
/*		viewHdr.add("Effective From");
		viewHdr.add("D");*/
		viewHdr.add("Remarks");
		viewHdr.add("T");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
	
	}

	/*
	 * Order by is used to sort data in ascending or descending order of list
	 * page. First is index, and second is the index value which must be
	 * provided by the developer. Index and index value will be default order by
	 * values.
	 */
	public String[] getOrderBy() 
	{
		String orderBy[] = { "1", "A.TRF_NAME" };
		return orderBy;
		// return null;
	}

	/*
	 * This function generate the main query for LIST page
	 */
	public String getMainQuery(String cmb[]) 
	{
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(billing.qryHandler_billing.getQuery(1, "select.packservMst.0").replace("?", hosCode)).append(" WHERE "+ billing.qryHandler_billing.getQuery(1,"select.packservMst.cond.0").replace("?", "1"));

		if (cmb != null) 
		{
			if (!cmb[1].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[1]);
				httpSession.setAttribute("strPkgId", cmb[1]);
				httpSession.setAttribute("strTrfID", cmb[0]);
			}
			if (!cmb[2].equals("0")) 
			{				
				brMainQuery.append(" AND "+ billing.qryHandler_billing.getQuery(1,"select.packservMst.cond.1").replace("?",cmb[2]));
			}
		}		  
		 
		return brMainQuery.toString();
	}

	public String[] getSearchField() 
	{
		String search_field[] = {"1", "A.TRF_NAME" };
		return search_field;
		// return null;
	}

	public String[] getComboHeader() 
	{
		String comboHeader[] = {"1","Tariff Type","0", "Package/Estimation Name","1", "Record Status" };
		return comboHeader;
	}
	public String[] getColumnHeader() 
	{
		String col_header[] = { "Inclusive Tariff Name", "Quantity", "Unit"};
		return col_header;
	}
	public String[] getComboQuery() 
	{
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String comboQuery[] = new String[10];
		comboQuery[0]=  "1^Package#2^Estimation";
		comboQuery[1] = billing.qryHandler_billing.getQuery(1,"select.packservMst.1").replace("?", hosCode);
		comboQuery[2] = "1^Active#2^Inactive";	
		return comboQuery;
	}

	/*
	 * This function is generate the query for the column header which we
	 * display in our first page 
	 */
	public String getViewQuery() 
	{
		return billing.qryHandler_billing.getQuery(1, "select.packservMst.2");
		// return null;
	}

	public String getButtons() 
	{
		StringBuilder br = new StringBuilder();
		/*br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='return callMePack1(document.forms[0]);' onClick='return callMePack1(document.forms[0]);'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;'  border=0 tabindex='1' title='Select A Record To Modify' onKeyPress='return callMePack2(document.forms[0]);' onClick='return callMePack2(document.forms[0]);' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return callMePack1(document.forms[0]);' onClick='return callMePack1(document.forms[0]);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='return callMePack2(document.forms[0]);' onClick='return callMePack2(document.forms[0]);'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = billing.qryHandler_billing.getQuery(1,"delete.packserv.0").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ billing.qryHandler_billing.getQuery(1,"delete.packserv.cond.0"));
		
		return deleteQuery;
	}

	public String getJsFiles()
	{
		String files = "../../billing/js/billing.js";
		return files;
		// return null;
	}
	public String isGlobal() 
	{
		return "0";
	}
}
