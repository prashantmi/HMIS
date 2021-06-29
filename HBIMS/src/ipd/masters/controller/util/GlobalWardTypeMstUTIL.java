package ipd.masters.controller.util;

import hisglobal.masterutil.MasterInterface;
import ipd.IpdConfigUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class GlobalWardTypeMstUTIL implements MasterInterface {

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
			//br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteGlobalRecords(\"DELETE\",\"Ward Type\");' onClick='deleteGlobalRecords(\"DELETE\",\"Ward Type\");'></a>");
			br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");' style='cursor:pointer'>");
			*/
			
			br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'><span class='add'>Add</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='deleteGlobalRecords(\"DELETE\",\"Ward Type\");' onClick='deleteGlobalRecords(\"DELETE\",\"Ward Type\");'><span class='delete'>Delete</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
			br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	public String[] getColumnHeader() {

			String col_header[] = { "Ward Type"};
	
		return col_header;
	}

	public String[] getComboHeader() {

			String[] comboHeader = {"1", "Record Status"};

		return comboHeader;
	}

	public String[] getComboQuery() {

			String[] comboQuery = new String[2];
			comboQuery[0] ="1^Active#2^InActive"; 
			return comboQuery;
	}

	public String[] getDeleteQuery() {
		
			String[] deleteQuery = new String[1];
			deleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"update.globalWardtype.0");	
		return deleteQuery;
	}

	public String getJsFiles() {
		
		String files = "../../ipd/js/ipd.js";
		return files;
	}

	public String getMainQuery(String[] cmb) {

	  	//String strhcode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String strhcode = IpdConfigUtil.SUPER_HOSPITAL_CODE.toString();
	    StringBuffer brMainQuery = new StringBuffer(500);
		brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.globalWardtype.0").replace("?", ""+strhcode+""));
		brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.globalWardtype.cond.0").replace("?", "1"));
		int nIndex = brMainQuery.lastIndexOf("1");
        if (cmb != null) {
			
			if (!cmb[0].equals("1")) {

				brMainQuery.replace(nIndex, nIndex+1, cmb[0]);
			}
			
		}
		/*if (cmb != null) {
			
			if (!cmb[0].equals("0")) {

				brMainQuery.replace(brMainQuery.indexOf("1"), brMainQuery.indexOf("1") + 1, cmb[0]);
			}

			
		}*/

		return brMainQuery.toString();
	}

	public String getMasterName() {

			String masterName = "Global Ward Type Master";
	
		return masterName;
	}

	public String[] getOrderBy() {

			String orderBy[] = { "1", "upper(HIPSTR_WARDTYPENAME)" }; 
							
		return orderBy;
	}

	public int getPage_per_block() {
	
		return 10;
	}

	public int getRecord_per_page() {
	
		return 10;
	}

	public String[] getSearchField() {

			String search_field[] = { "1", "HIPSTR_WARDTYPENAME" };
	
		return search_field;
	}

	public List<String> getViewHeader() {

			List<String> viewHdr = new ArrayList<String>();
			viewHdr.add("Ward Type");
			viewHdr.add("D");
			viewHdr.add("Record Status");
			viewHdr.add("D");
			
		return viewHdr;
	}

	public String getViewQuery() {
		
			String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.globalWardtype.1");
			
	
		return viewQuery;
	}

	public void setHttpSession(HttpSession session) {

		this.httpSession = session;
	}
	public boolean reportInterFaceRequired() {
		return false;
	}
	public String isGlobal(){
		return "1";
	}
}
