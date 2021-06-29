package ipd.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class DUWRBedMstUTIL implements MasterInterface 
{
	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;
	
	public String[] getColsWidth() {
		return null;
	}
	public String[] getGroupByCols() {
		String[] strGroupByCols= {"1","2"};
		return strGroupByCols;
	}
	public String getButtons()
	{

			StringBuilder br = new StringBuilder();
			/*br.append("<img src='../../hisglobal/images/btn-add.png' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-mo.png'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'  style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='Bedview();' onClick='Bedview();' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");' style='cursor:pointer'>");
			*/
			/*br.append("<br><a href='#' class='button' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
			br.append("<a href='#' class='button' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
			br.append("<a href='#' class='button' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
			br.append("<a href='#' class='button' id='' onKeyPress='Bedview();' onClick='Bedview();' ><span class='view'>View</span></a>");
			br.append("<a href='#' class='button' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		*/
			br.append("<br><a href='#'  class='btn btn-sm btn-primary' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
			br.append("<a href='#'  class='btn btn-sm btn-primary' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
			br.append("<a href='#'  class='btn btn-sm btn-primary' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
			br.append("<a href='#'  class='btn btn-sm btn-primary' onKeyPress='Bedview();' onClick='Bedview();' ><span class='view'>View</span></a>");
			br.append("<a href='#'  class='btn btn-sm btn-primary' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
			
			return br.toString();
	 }

	public String[] getColumnHeader() 
	{
			String col_header[] = {"Dept/Unit", "Ward","Room","Bed","Effective From"};
			return col_header;
	}

	public String[] getComboHeader() 
	{
			String[] comboHeader = { "1", "Record Status","0","Department","0","Unit","0","Ward","0","Room"};
     		return comboHeader;
	}

	public String[] getComboQuery() 
	{

			String[] comboQuery = new String[5];
			String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
			comboQuery[0] = "1^Active#2^InActive";
			comboQuery[1] = ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.8").replace("?", hosCode);	
			comboQuery[2] = ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.9").replace("?", hosCode);	
			comboQuery[3] = ipd.qryHandler_ipd.getQuery(1,"select.wardroombed.25").replace("?", hosCode);	
			comboQuery[4] = ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.24").replace("?", hosCode);	
			return comboQuery;
	}

	public String[] getDeleteQuery()
	{
		    String[] deleteQuery = new String[1];
			deleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"update.duwrbed.0");
			return deleteQuery;
	}

	public String getJsFiles() 
	{
		String jsFile = new String("../../ipd/js/ipd.js");
		return jsFile;
	}

	public String getMainQuery(String[] cmb) 
	{
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
	    StringBuffer brMainQuery = new StringBuffer(500);
	    if (cmb != null) 
		{
	    if (!cmb[0].equals("0")) 
	    {
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.0").replace("?",""+hosCode+""))
			.append(" AND "+ ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.1").replace("?","1")).append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.2").replace("?", cmb[1]));
	    }
	    
		} else
	    {
	    	brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.0").replace("?",""+hosCode+""))
			.append(" AND "+ ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.1").replace("?","1")).append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.2").replace("?", "0"));	
	    }
	   
		
		if (cmb != null) 
		{
			if (!cmb[1].equals("0")){
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
			}
			// condition 2
			/*if (!cmb[1].equals("0")) {

				brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.2").replace("?", cmb[1]));
			}*/
			// condition 3
            if(!cmb[2].equals("0")){
				
            	brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.3").replace("?", cmb[2]));
			}
			// condition 4
			  if(!cmb[3].equals("0")){
				
            	brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.4").replace("?", cmb[3]));
			}
			// condition 5
			 if(!cmb[4].equals("0")){
				
            	brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.duwrbed.cond.5").replace("?", cmb[4]));
			} 
		  }
		//System.out.println("qry"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String getMasterName() 
	{
			String masterName ="DUWR Bed Master";
			return masterName;
	}

	public String[] getOrderBy() 
	{
			String orderBy[] = { "1","upper(C.DEPTUNITNAME)","2","upper(C.WARD_NAME)","3","upper(C.ROOM_DESC)","4","upper(C.BED_NAME)" }; 
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
			String search_field[] ={ "1","C.DEPTUNITNAME","2","C.WARD_NAME","3","C.ROOM_DESC","4","C.BED_NAME"};
			return search_field;
	}

	public List<String> getViewHeader() 
	{
    		List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Department");
			viewHdr.add("D");
			viewHdr.add("Unit ");
			viewHdr.add("D");
			viewHdr.add("Ward");
			viewHdr.add("D");
			viewHdr.add("Room");
			viewHdr.add("D");
			viewHdr.add("Bed");
			viewHdr.add("D");
			viewHdr.add("Effective From");
			viewHdr.add("D");
			viewHdr.add("Record Status");
			viewHdr.add("D");
	    	return viewHdr;
	}

	public String getViewQuery()
	{
		    String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.duwrbed.1");
	    	return viewQuery;
	}

	public void setHttpSession(HttpSession session) 
	{
      		this.httpSession = session;
	}
	public boolean reportInterFaceRequired() {
		return false;
	}
	public String isGlobal(){
		return "0";
	}
}