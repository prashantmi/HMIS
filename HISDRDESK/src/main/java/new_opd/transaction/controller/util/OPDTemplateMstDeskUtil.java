package new_opd.transaction.controller.util;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


public class OPDTemplateMstDeskUtil implements MasterInterface {

	@Override
	public void setHttpSession(HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMasterName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getColumnHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSearchField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRecord_per_page() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPage_per_block() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getComboQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMainQuery(String[] cmb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getComboHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getViewQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getViewHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getOrderBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getButtons() {
		StringBuilder br = new StringBuilder();

		/*br.append("<img src='../../hisglobal/images/btn-add.png'	style='cursor: pointer;'	tabindex='0'	title='Add' 	onKeyPress='if(event.keyCode==13) return callComboAdd(document.forms[0],3);'	onClick='return callComboAdd(document.forms[0],3);' />");
		br.append("<img src='../../hisglobal/images/btn-mo.png'		style='cursor: pointer;'	tabindex='0'	title='Modify' 	onKeyPress='if(event.keyCode==13) return callComboModify(document.forms[0],3);'	onClick='return callComboModify(document.forms[0],3);' />");
		br.append("<img src='../../hisglobal/images/btn-del.png'	style='cursor: pointer;'	tabindex='0'	title='Delete' 	onKeyPress='if(event.keyCode==13) deleteGroupRecords(\"DELETE\");'					onClick='deleteGroupRecords(document.forms[0]);' />");
		br.append("<img src='../../hisglobal/images/btn-view.png'	style='cursor: pointer;'	tabindex='0'	title='View' 	onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");'							onClick='view(\"VIEWDATA\");' />");
		br.append("<img src='../../hisglobal/images/btn-rpt.png'	style='cursor: pointer;'	tabindex='0'	title='Report' 	onKeyPress='if(event.keyCode==13) report(\"REPORT\");'							onClick='report(\"REPORT\");' />");
		*/
		
		br.append("<br><a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) return callComboAdd(document.forms[0],3);'	onClick='return callComboAdd(document.forms[0],3);'><span class='add'>Add</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) return callComboModify(document.forms[0],3);'	onClick='return callComboModify(document.forms[0],3);'><span class='modify'>Modify</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");'					onClick='deleteRecords(document.forms[0]);'><span class='delete'>Delete</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' 							onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
		br.append("<a href='#' class='btn btn-sm btn-primary' id='' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  						onClick='report(\"REPORT\");'><span class='report'>Report</span></a>");
		return br.toString();
	}

	@Override
	public String getJsFiles() {
		String jsFile = new String("../../mms/js/mms.js");
		return jsFile;
	}

	@Override
	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}}