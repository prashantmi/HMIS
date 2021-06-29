package ipd.masters.controller.util;

import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class NurseCheckListMstUTIL implements MasterInterface {


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
		/*br.append("<img src='../../hisglobal/images/btn-add.png' title='Click To Add A Record' border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");' style='cursor:pointer'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'  style='cursor:pointer'>");
		br.append("<img src='../../hisglobal/images/btn-del.png' title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' style='cursor:pointer'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' style='cursor:pointer'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' title='Select A Record To Generate Reports'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");' style='cursor:pointer'>");
		*/
		br.append("<a class='btn btn-sm btn-primary' title='Click To Add A Record' tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>Add</a>");
		br.append("<a class='btn btn-sm btn-primary' title='Select A Record To Modify' tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'>Modify</a>");
		br.append("<a class='btn btn-sm btn-primary' title='Select One Or More CheckBox To Delete Record(s)' tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>Delete</a>");
		br.append("<a class='btn btn-sm btn-primary' title='Select A Record To View' tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>View</a>");
		br.append("<a class='btn btn-sm btn-primary' title='Select A Record To Generate Reports' tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>Report</a>");
		
	return br.toString();
	}

	public String[] getColumnHeader() {
		
		String strColHeader[] = {"Check List","Check List Unit", "Effective From"};
		return strColHeader;
	}

	public String[] getComboHeader() {
		String[] strComboHeader = {"1", "Record Status"  };
		return strComboHeader;
	}

	public String[] getComboQuery() {
		String[] strComboQuery = new String[1];
		strComboQuery[0] = "1^Active#2^InActive";
	return strComboQuery;
	}

	public String[] getDeleteQuery() { 
		
		String[] strDeleteQuery = new String[1];
		strDeleteQuery[0] = ipd.qryHandler_ipd.getQuery(1,"delete.nurseChkListMst.0");
	return strDeleteQuery;
	}

	public String getJsFiles() {
		return null;
	}

	public String getMainQuery(String[] strCmb) {
		StringBuffer brMainQuery = new StringBuffer(500);
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.nurseChkListMst.0").replace("?", hosCode));
		brMainQuery.append(" AND ");
		brMainQuery.append(ipd.qryHandler_ipd.getQuery(1,"select.nurseChkListMst.0.cond").replace("?", "1"));
		int nIndex = brMainQuery.lastIndexOf("1");
		
		if(strCmb != null) {


			if(!strCmb[0].equals("1")) {


			brMainQuery.replace(nIndex, nIndex+1, strCmb[0]);

			}
		}


		return brMainQuery.toString();
	}

	public String getMasterName() {
		String strMasterName = "Nurse Check List Master";
	return strMasterName;
	}

	public String[] getOrderBy() {
		
		String strOrderBy[] = {"1", "upper(trim(HIPSTR_CHECKLIST_DESC))"};
		return strOrderBy;
	}

	public int getPage_per_block() {
		return 10;
	}

	public int getRecord_per_page() {
		return 10;
	}

	public String[] getSearchField() {
		String strSearch[] = {"1", "HIPSTR_CHECKLIST_DESC"};
		return strSearch;
	}

	public List<String> getViewHeader() {
		
		List <String> listViewHdr = new ArrayList<String>();
		listViewHdr.add("Check List");
		listViewHdr.add("D");
		listViewHdr.add("Check List For");
		listViewHdr.add("D");
	return listViewHdr;
	}

	public String getViewQuery() {
		String strViewQuery = ipd.qryHandler_ipd.getQuery(1,"select.nurseChkListMst.1");

		return strViewQuery;
	}

	public void setHttpSession(HttpSession session) {

		this.httpSession = session;

	}
	public String isGlobal(){
		return "0";
	}
}
