package ipd.masters.controller.util;

import hisglobal.masterutil.MasterInterface;
import ipd.IpdConfigUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

//import com.jscape.inet.sftp.requests.Request;



public class GlobalBedTypeMstUTIL implements MasterInterface 
{

	public boolean reportInterFaceRequired() {
		return false;
	}
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;

	public String[] getGroupByCols() {
		return null;
	}
	public String getButtons()
	{

		StringBuilder br = new StringBuilder();
		/*br.append("<a style=cursor:hand><img src='../../hisglobal/images/btn-add.png' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'></a>");
		br.append("<a style=cursor:hand><img src='../../hisglobal/images/btn-mo.png'border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' style='cursor:pointer' onClick='edit(\"MODIFY\");' ></a>");
		//br.append("<a style=cursor:hand><img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)' border=0  tabindex='1' style='cursor:pointer' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'></a>");
		br.append("<a style=cursor:hand><img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)' border=0  tabindex='1' style='cursor:pointer' onKeyPress='deleteGlobalRecords(\"DELETE\",\"Bed Type\");' onClick='deleteGlobalRecords(\"DELETE\",\"Bed Type\");'></a>");
		br.append("<a style=cursor:hand><img src='../../hisglobal/images/btn-view.png' title='Select A Record To View' border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' style='cursor:pointer' onClick='view(\"VIEWDATA\");'></a>");
		br.append("<a style=cursor:hand><img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts' border=0  tabindex='1' onKeyPress='report(\"REPORT\");' style='cursor:pointer'  onClick='report(\"REPORT\");'></a>");
		*/
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteGlobalRecords(\"DELETE\",\"Bed Type\");' onClick='deleteGlobalRecords(\"DELETE\",\"Bed Type\");'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
	
		return br.toString();
	 }

	public String[] getColumnHeader() 
	{
			String col_header[] = { "Bed Type", "Effective Date" };
			return col_header;
	}

	public String[] getComboHeader() 
	{
			String[] comboHeader = { "1", "Record Status"};
     		return comboHeader;
	}

	public String[] getComboQuery() 
	{

			String[] comboQuery = new String[2];
			comboQuery[0] = "1^Active#2^InActive";
				
	      	return comboQuery;
	}

	public String[] getDeleteQuery()
	{
		    String[] strDeleteQuery = new String[1];
		    String strSeatId = httpSession.getAttribute("SEATID").toString();
		    strDeleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"delete.globalBedType.0").replace("?", ""+strSeatId+"");
		    strDeleteQuery[0] = strDeleteQuery[0].concat(" where ").concat(ipd.qryHandler_ipd.getQuery(1, "delete.globalBedType.cond.0"));
			return strDeleteQuery;
	}

	public String getJsFiles() 
	{
		String files = "../../ipd/js/ipd.js";
		return files;
	}

	public String getMainQuery(String[] cmb) {
			String strhcode = IpdConfigUtil.SUPER_HOSPITAL_CODE.toString();  
			StringBuffer brMainQuery = new StringBuffer(500);
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.globalBedType.0").replace("?", ""+strhcode+""));
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.globalBedType.cond.0").replace("?", "1"));
			//brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.globalBedType.cond.order"));
			int nIndex = brMainQuery.lastIndexOf("1");
			
			if (cmb != null) {
				
				if (!cmb[0].equals("1")) {

					brMainQuery.replace(nIndex, nIndex+1, cmb[0]);
				}
				
			}
			
			
 		/*	brMainQuery
			if (cmb != null) 
			{
				if (!cmb[0].equals("0"))
				{
						brMainQuery.replace(brMainQuery.indexOf("1"), brMainQuery.indexOf("1") + 1,cmb[0]);
						
						
						//brMainQuery.append(session.getAttribute("HOSPITAL_ID")); 
						
				
				
				}
          	}*/
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{
			String masterName = "Global Bed Type Master";
			return masterName;
	}

	public String[] getOrderBy() 
	{

	    	String orderBy[] = { "1", "TRIM(INITCAP(HGSTR_BED_TYPE_NAME))" }; 
			return orderBy;
	}

	public int getPage_per_block() 
	{
			return 10;
	}

	public int getRecord_per_page() 
	{
			return 10;
	}

	public String[] getSearchField() 
	{

			String search_field[] = { "1","HGSTR_BED_TYPE_NAME"};
			return search_field;
	}

	public List<String> getViewHeader() 
	{
    		List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Bed Type");
			viewHdr.add("D");
			viewHdr.add("Effective Date");
			viewHdr.add("D");
			viewHdr.add("Remarks");
			viewHdr.add("D");
			viewHdr.add("Record Status");
			viewHdr.add("D"); 
	    	return viewHdr;
	}

	public String getViewQuery()
	{
		    String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.globalBedType.1");
	    	return viewQuery;
	}

	public void setHttpSession(HttpSession session) 
	{
      		this.httpSession = session;
	}

	public String[] getColsWidth() {
		return null;
	}
	public String isGlobal(){
		return "1";
	}
			
}
