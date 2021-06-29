package ipd.masters.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.masterutil.MasterInterface;

public class PropertyMstUTIL implements MasterInterface {
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;


	public boolean reportInterFaceRequired() {
		return false;
	}
	public String[] getColsWidth() {
		return null;
	}
	public String[] getGroupByCols() {
		return null;
	}
	public String getButtons() {

			StringBuilder br = new StringBuilder();
			/*
			br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;cursor:hand;' title='Add Records' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
			br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;cursor:hand;' title='Modify Records'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
			br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;cursor:hand;' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
			br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;cursor:hand;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;cursor:hand;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		*/
			
			br.append("<a class='btn btn-sm btn-primary' title='Add Records' tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>Add</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Modify Records' tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >Modify</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select One Or More CheckBox To Delete Record(s)' tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>Delete</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To View' tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>View</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To Generate Reoprts' tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>Report</a>");
		
			return br.toString();
	}

	public String[] getColumnHeader() {
	String col_header[] = { "Property Name","Effective From"};
	return col_header;
	}
	public String[] getComboHeader() {
		String cmb_header[]={"1","Record Status"};
		return cmb_header;
	}

	public String[] getComboQuery() {

			String[] comboQuery = new String[1];
			comboQuery[0] = "1^Active#2^InActive";
			
			return comboQuery;
	}

	public String[] getDeleteQuery() {
		
			String[] deleteQuery = new String[1];
			deleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"delete.property.0");
			
			return deleteQuery;
	}

	public String getJsFiles() {
		
		String jsFile = "../../ipd/js/ipd.js";
		return jsFile;
	}

	public String getMainQuery(String[] cmb) {

			StringBuffer brMainQuery = new StringBuffer(500);
			String hosCode =(String)httpSession.getAttribute("HOSPITAL_CODE");//httpSession.getAttribute("HOSPITAL_CODE").toString();
			
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.property.0").replace("?",hosCode));
			
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.property.cond.0").replace("?","1"));
			if (cmb != null) {
				
				if (!cmb[0].equals("0")) {
	
					brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
				}
			
			}
        return brMainQuery.toString();
	}

	public String getMasterName() {

			String masterName = "Property  Master";
		
		return masterName;
	}

	public String[] getOrderBy() {

			String orderBy[] = { "1", "HIPSTR_PROPERTY_NAME"}; 
			
		return orderBy;
	}

	public int getPage_per_block() {
	
		return 10;
	}

	public int getRecord_per_page() {
	
		return 10;
	}

	public String[] getSearchField() {

			String search_field[] = { "1", "HIPSTR_PROPERTY_NAME" };
			
		return search_field;
	}

	public List<String> getViewHeader() 
	{
    		List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Property Name");
			viewHdr.add("D");
			viewHdr.add("Effective From");
			viewHdr.add("D");
			viewHdr.add("Remarks");
			viewHdr.add("D");
			viewHdr.add("Record Status");
			viewHdr.add("D");
			
		return viewHdr;
	}

	public String getViewQuery() {
		
		String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.property.view.1");
		return  viewQuery;
	}

	public void setHttpSession(HttpSession session) {

		this.httpSession = session;
	}
	public String isGlobal(){
		return "0";
	}
}
