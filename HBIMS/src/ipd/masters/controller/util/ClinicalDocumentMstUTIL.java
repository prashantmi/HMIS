package ipd.masters.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.masterutil.MasterInterface;

public class ClinicalDocumentMstUTIL implements MasterInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;

	public String[] getColsWidth() {
		return null;
	}
	public boolean reportInterFaceRequired() {
		return false;
	}
	public String[] getGroupByCols() {
		return null;
	}
	public String getButtons()
	{
			StringBuilder br = new StringBuilder();
			/*
			br.append("<img src='../../hisglobal/images/btn-add.png' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-mo.png'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'  style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");' style='cursor:pointer'>");
			 */
			br.append("<a class='btn btn-sm btn-primary' tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>Add</a>");
			br.append("<a class='btn btn-sm btn-primary' tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'>Modify</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select One Or More CheckBox To Delete Record(s)' tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>Delete</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To View' tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>View</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To Generate Reoprts' tabindex='1' onKeyPress='report(\"REPORT\");' onClick='report(\"REPORT\");'>Report</a>");
	
			return br.toString();
	 }
	public String[] getColumnHeader() 
	{
			String col_header[] = { "Document Name", "Effective From" };
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
		    String[] deleteQuery = new String[1];
			deleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"delete.clinicaldoc.0");
		    return deleteQuery;
	}
	public String getJsFiles() 
	{
	     	return null;
	}
	public String getMainQuery(String[] cmb) 
	{
		    String strhcode = httpSession.getAttribute("HOSPITAL_CODE").toString();  
			StringBuffer brMainQuery = new StringBuffer(500);
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.clinicaldoc.0").replace("?", ""+strhcode+""));
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.clinicaldoc.cond.1").replace("?", "1"));
            int nIndex = brMainQuery.lastIndexOf("1");
			if (cmb != null) 
			{
				if (!cmb[0].equals("1")) 
				{
					brMainQuery.replace(nIndex, nIndex+1, cmb[0]);
				}
			}
		return brMainQuery.toString();
	}
	public String getMasterName() 
	{
			String masterName = "Clinical Document Master";
			return masterName;
	}
	public String[] getOrderBy() 
	{
	    	String orderBy[] = { "1", "upper(HEPNUM_DOCUMENT_NAME)" }; 
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
			String search_field[] = { "1","HEPNUM_DOCUMENT_NAME"};
			return search_field;
	}
	public List<String> getViewHeader() 
	{
    		List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Document Name");
			viewHdr.add("D");
			viewHdr.add("Effective From");
			viewHdr.add("D");
			viewHdr.add("Remarks");
			viewHdr.add("D");
			viewHdr.add("Record Status");
			viewHdr.add("D"); 
	    	return viewHdr;
	}
	public String getViewQuery()
	{
		    String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.clinicaldoc.1");
		    return viewQuery;
	}
	public void setHttpSession(HttpSession session) 
	{
      		this.httpSession = session;
	}
	public String isGlobal(){
		return "0";
	}
}