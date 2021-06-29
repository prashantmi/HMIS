package ipd.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class WardMstUTIL implements MasterInterface {

	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;

	public String[] getColsWidth() {
		return null;
	}
	public String[] getGroupByCols() {
		return null;
	}
	public String getButtons() {

			StringBuilder br = new StringBuilder();
			/*br.append("<img src='../../hisglobal/images/btn-add.png' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-mo.png'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'  style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='Wardview();' onClick='Wardview();' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");' style='cursor:pointer'>");
			*/
			br.append("<br><a href='#' class='button btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
			br.append("<a href='#' class='button btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
			br.append("<a href='#' class='button btn btn-sm btn-primary' id='' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
			br.append("<a href='#' class='button btn btn-sm btn-primary' id=''  onKeyPress='Wardview();' onClick='Wardview();'><span class='view'>View</span></a>");
			br.append("<a href='#' class='button btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	public String[] getColumnHeader() {
	String col_header[] = { "Ward","Ward Type","Building/Block","Effective From"};
	return col_header;
	}
	public String[] getComboHeader() {
		
		String[] comboHeader = {"0","Ward Type","1","Record Status"};
		return comboHeader;
	}

	public String[] getComboQuery() {

			String[] comboQuery = new String[2];
			String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
			comboQuery[0] =  ipd.qryHandler_ipd.getQuery(1,"select.ward.21").replace("?", hosCode);	
			comboQuery[1] = "1^Active#2^InActive";
			
			return comboQuery;//comboQuery;
	}
	public String[] getDeleteQuery() {
		
			String[] deleteQuery = new String[2];
			deleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"delete.ward.0");
			deleteQuery[1]=ipd.qryHandler_ipd.getQuery(1, "delete.ward.1");
			return deleteQuery;
	}

	public String getJsFiles() {
		
		String jsFile = "../../ipd/js/ipd.js";
		return jsFile;
	}

	public String getMainQuery(String[] cmb) {

			StringBuffer brMainQuery = new StringBuffer(500);
			String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
			//brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.ward.0").replace("?", hashCode()));
			brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.ward.0").replace("?",hosCode));
			//System.out.println("ok"+ipd.qryHandler_ipd.getQuery(1,"select.ward.cond.0"));
			//brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.ward.cond.0").replace("?","1"));
			if (cmb != null) {
				
				if (!cmb[0].equals("0")) {
	
					brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.ward.cond.21").replace("?", cmb[0]));
				}
				if (!cmb[1].equals("0")){
					brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[1]);
				
				}
			
			}
        return brMainQuery.toString();
	}

	public String getMasterName() {

			String masterName = "Ward  Master";
		
		return masterName;
	}

	public String[] getOrderBy() {

			String orderBy[] = { "1", "upper(b.HIPSTR_WARD_NAME)","2", "upper(WARDTYPE)","3", "upper(BUILDINGBLOCNAME)"}; 
			
		return orderBy;
	}

	public int getPage_per_block() {
	
		return 10;
	}

	public int getRecord_per_page() {
	
		return 10;
	}

	public String[] getSearchField() {

			String search_field[] = { "1", "b.HIPSTR_WARD_NAME","2", "WARDTYPE","3", "BUILDINGBLOCNAME" };
			
		return search_field;
	}

	public List<String> getViewHeader() 
	{
    		List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Ward Type");
			viewHdr.add("D");
			viewHdr.add("Ward");
			viewHdr.add("D");
			viewHdr.add("Bed No");
			viewHdr.add("D");
			viewHdr.add("Building");
			viewHdr.add("D");
			viewHdr.add("Building");
			viewHdr.add("D");
			viewHdr.add("Block");
			viewHdr.add("D");
			viewHdr.add("Effective From");
			viewHdr.add("D");
			viewHdr.add("Effective To");
			viewHdr.add("D");
			viewHdr.add("Remark");
			viewHdr.add("D");
			viewHdr.add("Record Status");
			viewHdr.add("D");
			
		return viewHdr;
	}

	public String getViewQuery() {
		
		String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.ward.view.1");
		return  viewQuery;
	}

	public void setHttpSession(HttpSession session) {

		this.httpSession = session;
	}
	public boolean reportInterFaceRequired() {
		return false;
	}
	public String isGlobal(){
		return "0";
	}
}
