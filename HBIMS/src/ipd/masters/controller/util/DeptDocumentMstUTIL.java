package ipd.masters.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;

import hisglobal.masterutil.MasterInterface;

public class DeptDocumentMstUTIL implements MasterInterface {


	public boolean reportInterFaceRequired() {
		return false;
	}
private static final long serialVersionUID = 02L;
	
	HttpSession httpSession = null;
	

	public String[] getColsWidth() {
		return null;
	}
	public String[] getGroupByCols() {
		return null;
	}
	public String getButtons() {
		 StringBuilder br=new StringBuilder();
			/*br.append("<img src='../../hisglobal/images/btn-add.png' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-mo.png'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='componentView();' onClick='componentView();' style='cursor:pointer'>");
			br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");' style='cursor:pointer'>");
			*/
		 
		 	br.append("<a class='btn btn-sm btn-primary' tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>Add</a>");
			br.append("<a class='btn btn-sm btn-primary' tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'>Modify</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select One Or More CheckBox To Delete Record(s)' tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>Delete</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To View' tabindex='1' onKeyPress='componentView();' onClick='componentView();'>View</a>");
			br.append("<a class='btn btn-sm btn-primary' title='Select A Record To Generate Reoprts' tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>Report</a>");
			
			return br.toString();
	}

	public String[] getColumnHeader() {
		
		String col_header[] = { "Component Name", "File Name", "IsDefault", "Effective From" };
		return col_header;
	}

	public String[] getComboHeader() {
		String[] comboHeader = {"0","Deparment Name","0","Document Name","1","Record Status"};
		return  comboHeader;
	}

	public String[] getComboQuery() {
		String[] comboQuery = new String[3];
		comboQuery[0] = ipd.qryHandler_ipd.getQuery(1,"combo.department.0").replace("?", (String)httpSession.getAttribute("HOSPITAL_CODE"));	
		comboQuery[1] = ipd.qryHandler_ipd.getQuery(1,"combo.document.0").replace("?", (String)BillConfigUtil.SUPER_HOSPITAL_CODE);
		comboQuery[2] = "1^Active#2^InActive";
      	return comboQuery;
	}

	public String[] getDeleteQuery() {
		 
		 String[] deleteQuery = new String[1];
		 deleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"delete.deptdocMst.0");
		 return deleteQuery;
	}

	public String getJsFiles() 
	{
		String jsFile = new String("../../ipd/js/ipd.js");
		return jsFile;
	}

	public String getMainQuery(String[] cmb) {
		//String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();	
		StringBuffer brMainQuery = new StringBuffer("");
		brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.deptdocMst.0").replace("?", (String)httpSession.getAttribute("HOSPITAL_CODE")));
		
		brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.deptdocMst.cond.1").replace("?", "00"));
		
		brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.deptdocMst.cond.2").replace("?", "000"));
		
		brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.deptdocMst.cond.3").replace("?", "1"));
		
        if (cmb != null) {
			
			if (!cmb[0].equals("0")) {

				brMainQuery.replace(brMainQuery.indexOf("00"), brMainQuery.indexOf("00")+2, cmb[0]);
			}
			if (!cmb[1].equals("0")) {

				brMainQuery.replace(brMainQuery.indexOf("000"), brMainQuery.indexOf("000")+3, cmb[1]);
			}
			
			if(!cmb[2].equals("0")){
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1")+1, cmb[2]);
					}
			
		}/*else{
			
			System.out.println("inside main else");
					
			brMainQuery.replace(brMainQuery.indexOf("?"), brMainQuery.indexOf("?")+1, "1");
			brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.deptdocMst.cond.1").replace("?", "0"));
			brMainQuery.append(" ").append(ipd.qryHandler_ipd.getQuery(1,"select.deptdocMst.cond.2").replace("?", "0"));
	
		}
        */
        
		/*if (cmb != null) 
		{
			if (!cmb[0].equals("0"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1,cmb[0]);
			}
			if(!cmb[1].equals("0"))
			{
				brMainQuery.append(" "+ipd.qryHandler_ipd.getQuery(1,"select.compexamvalue.cond.1").replace("?",cmb[1]));
			}
		}else{
			brMainQuery.append(" "+ipd.qryHandler_ipd.getQuery(1,"select.compexamvalue.cond.1").replace("?","1"));
		}*/
	    
	return brMainQuery.toString();
	}

	public String getMasterName() {
		String masterName = "Department Document Master";
		return masterName;
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "upper(C.COMPONENT_NAME)","2","upper(C.HEPNUM_COMPONENT_FILENAME)" }; 
		return orderBy;
	}

	public int getPage_per_block() {
		return 10;
	}

	public int getRecord_per_page() {
		return 10;
	}

	public String[] getSearchField() {
		String search_field[] = { "1","C.COMPONENT_NAME","2","C.HEPNUM_COMPONENT_FILENAME"};
		return search_field;
	}

	public List<String> getViewHeader() {
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Department Name");
		viewHdr.add("D");
		viewHdr.add("Document Name");
		viewHdr.add("D");
		viewHdr.add("Component Name");
		viewHdr.add("D");
		viewHdr.add("Effective From");
		viewHdr.add("D"); 
    	return viewHdr;
		//return null;
	}

	public String getViewQuery() {
		String viewQuery = ipd.qryHandler_ipd.getQuery(1, "select.deptdocMst.1");
    	return viewQuery;
		//return null;
	}

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}
	public String isGlobal(){
		return "0";
	}
}
