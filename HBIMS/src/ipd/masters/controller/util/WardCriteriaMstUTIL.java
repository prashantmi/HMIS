package ipd.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class WardCriteriaMstUTIL  implements MasterInterface {

	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;

	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}
	public String[] getGroupByCols() {
		String[] strGroupByCols= {"1","2"};
		return strGroupByCols;
	}
	public String getButtons() {

			StringBuilder br = new StringBuilder();
			/*
			br.append("<img src='../../hisglobal/images/btn-add.png' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-mo.png'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'  style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='reportCrite(\"REPORT\");'  onClick='reportCrite(\"REPORT\");' style='cursor:pointer'>");
	*/
			br.append("<a class='btn btn-sm btn-primary' tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>Add</a>");
			br.append("<a class='btn btn-sm btn-primary' tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'>Modify</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select One Or More CheckBox To Delete Record(s)' tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>Delete</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To View' tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>View</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To Generate Reoprts' tabindex='1' onKeyPress='reportCrite(\"REPORT\");' onClick='reportCrite(\"REPORT\");'>Report</a>");
	
		return br.toString();
	}

	public String[] getColumnHeader() {
	String col_header[] = { "Ward","Room Desc","Age","Gender","Patient Category","Disease Type" };
	return col_header;
	}

	public String[] getComboHeader() {
		String[] comboHeader = {"0","Ward","1","Record Status"};
		return  comboHeader;
	}

	public String[] getComboQuery() {
			String strhcode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		    String[] comboQuery = new String[2];
		    comboQuery[0] = ipd.qryHandler_ipd.getQuery(1,"select.wardcriteria.12").replace("?",""+strhcode+"");
		    comboQuery[1] = "1^Active#2^InActive";
			return comboQuery;
	}

	public String[] getDeleteQuery() {
			String[] deleteQuery = new String[1];
			deleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"delete.wardcriteria.0");
			return deleteQuery;
	}

	public String getJsFiles() {
		String jsFile = "../../ipd/js/ipd.js";
		return jsFile;
	}

	public String getMainQuery(String[] cmb) {

			StringBuffer brMainQuery = new StringBuffer(500);
			String strhcode = httpSession.getAttribute("HOSPITAL_CODE").toString();
			
			brMainQuery.append(" "+ipd.qryHandler_ipd.getQuery(1,"select.wardcriteria.0").replace("?", ""+strhcode+""));
			brMainQuery.append(" WHERE ").append(ipd.qryHandler_ipd.getQuery(1,"select.wardcriteria.cond.1"));
			if(cmb != null)	
			{
			if (!cmb[0].equals("0")){
				brMainQuery.append(" AND "+ipd.qryHandler_ipd.getQuery
						(1,"select.wardcriteria.cond.0").replace("?", cmb[0]));
			}
			if (!cmb[1].equals("0")){
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[1]);
			}
			}
			return brMainQuery.toString();
		}

	public String getMasterName() {
		String masterName = "Ward Criteria Master";
		return masterName;
	}

	public String[] getOrderBy() {
			String orderBy[] = { "1", "upper(C.ward)","2","C.roomname","3","C.age","4","C.sex","5","C.trcatg","6","C.dscatg"}; 
			return orderBy;
	}

	public int getPage_per_block() {
		return 10;
	}

	public int getRecord_per_page() {
		return 10;
	}

	public String[] getSearchField() {
			String search_field[] = { "1", "C.ward","2","C.roomname","3","C.age","4","C.sex","5","C.trcatg","6","C.dscatg" };
			return search_field;
	}

	public List<String> getViewHeader() 
	{
    		List<String> viewHdr = new ArrayList<String>();
    		viewHdr.add("Ward");
			viewHdr.add("D");
    		viewHdr.add("Age");
			viewHdr.add("D");
			viewHdr.add("Sex");
			viewHdr.add("D");
			viewHdr.add("Patient Category");
			viewHdr.add("D");
			viewHdr.add("Disease Type");
			viewHdr.add("D");
			viewHdr.add("Remark");
			viewHdr.add("D");
			viewHdr.add("Effective Date");
			viewHdr.add("D");
			viewHdr.add("Record Status");
			viewHdr.add("D");
			
		return viewHdr;
	}

	public String getViewQuery() {
		String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.wardcriteria.1");
		return  viewQuery;
	}

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}
	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return false;
	}
	public String isGlobal(){
		return "0";
	}
}