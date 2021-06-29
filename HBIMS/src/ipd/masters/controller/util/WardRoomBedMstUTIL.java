package ipd.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


public class WardRoomBedMstUTIL implements MasterInterface {

	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;

	public String[] getColsWidth() {
		String[] strColsWidth= {"25","20","25","25"};
		return strColsWidth;
	}
	public String[] getGroupByCols() {
		String[] strGroupByCols= {"1","2"};
		return strGroupByCols;
	}
	public void setHttpSession(HttpSession session) {
		
		httpSession = session;

	}

	public String getMasterName() {
	    String masterName = "Room Bed Master";
		return masterName;
	}

       public String[] getColumnHeader() {
		
		String[] columnHeader = {"Room","Bed","Bed Type","Effective From"};
		return columnHeader;
	}

	public String getViewQuery()
	{
		
	   String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.19");
	   //System.out.println("View Query=>"+viewQuery);
    	return viewQuery;
		//return null;

	}
	
	public String[] getSearchField() 
	{
		String[] searchField = {"1","C.ROOM_DESC","2","C.BED_NAME","3","BED_TYPE_NAME"};
     	return searchField;
		//return null;
	}

	public int getRecord_per_page() {
		return 10;
	}

	public int getPage_per_block() {
		return 10;
	}
	public String getMainQuery(String[] cmb) {
		
		
		StringBuffer brMainQuery = new StringBuffer(500);
		
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		//brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.wardroombed").replace("?",hosCode));
		brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.wardroombed.0").replace("?",hosCode));
		//System.out.println("Hello");
		//System.out.println("Query"+brMainQuery.toString());
		if (cmb != null) 
		{
			if (!cmb[0].equals("0")){
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
			System.out.println("brMainQuery :"+brMainQuery.toString());
			}
			// condition second
			
		  }
	      return brMainQuery.toString();
	   
     }

	public String[] getComboHeader() 
	{
		String[] comboHeader = {"1","Record Status"};
		return  comboHeader;
		//return null;
	}
	public String[] getComboQuery() 
	{
		
		String comboQuery[] = new String[2];
		comboQuery[0] = "1^Active#2^InActive";
		return comboQuery;
		//return null;
	}	
	public List<String> getViewHeader() {
		
		List<String> viewHdr = new ArrayList<String>();
		
		viewHdr.add("Room");
		viewHdr.add("D");
		viewHdr.add("Bed");
		viewHdr.add("D");
		viewHdr.add("Bed Type");
		viewHdr.add("D");
		viewHdr.add("Bed Status");
		viewHdr.add("D");
		viewHdr.add("Is Sharable");
		viewHdr.add("D");
		viewHdr.add("Effective From");
		viewHdr.add("D");
		viewHdr.add("Remarks");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D"); 
    	return viewHdr;
		
		//return viewHdr;
		
	}

	public String[] getOrderBy() {
		String orderBy[] = {"1","upper(C.ROOM_DESC)","2","upper(C.BED_NAME)","3","upper(BED_TYPE_NAME)"};
		//System.out.println("Order By");
		return orderBy;
		//return null;
	}

	 public String[] getDeleteQuery() {
      String deleteQuery[]=new String[1];
      
	  deleteQuery[0]=ipd.qryHandler_ipd.getQuery(1,"delete.wardroombed.0");
	//  System.out.println("Deleting data 1------>"+deleteQuery[0]);
	  return deleteQuery;	
	 // return null;
	}

	public String getButtons() {
		  StringBuilder br=new StringBuilder();
			/*br.append("<img src='../../hisglobal/images/btn-add.png' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-mo.png'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");' style='cursor:pointer'>");
			*/
			br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
			return br.toString();
	}

	public String getJsFiles() {
		String jsFile = "../../ipd/js/ipd.js";
		return jsFile;
	}
	public boolean reportInterFaceRequired() {
		return false;
	}
	public String isGlobal(){
		return "0";
	}
}
