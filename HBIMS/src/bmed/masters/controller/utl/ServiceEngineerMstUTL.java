package bmed.masters.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;

/**
 * @author  Amit Kumar 
 * Creation Date:- 17-Jan-2011 
 * Modifying Date:- 0-0-2011
 * Used For:-	
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class ServiceEngineerMstUTL  implements MasterInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	/*	
	 * The http session.
	 */
	HttpSession httpSession = null;
	
	
	public String getButtons() {

		StringBuilder br = new StringBuilder();

		br
				.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer; ' title='Add' tabindex='0' onKeyPress='if(event.keyCode==13) buttonLogicsOnClick(\"ADD\");' onClick='buttonLogicsOnClick(\"ADD\");'/>");
		br
				.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor: pointer; ' title='Modify' tabindex='0' onKeyPress='if(event.keyCode==13) edit(\"MODIFY\");' onClick='edit(\"MODIFY\");'  />");
		br
				.append("<img src='../../hisglobal/images/btn-del.png' style='cursor: pointer; ' title='Delete' tabindex='0' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' />");
		br
				.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer; ' title='View' tabindex='0' onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' />");
		br
				.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor: pointer; ' title='Report' tabindex='0' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  onClick='report(\"REPORT\");' />");

		return br.toString();
	}


	public String[] getColsWidth() {

		return null;
	}

	public String[] getColumnHeader() {
		String[] arrStrColumnHdr = { "Service Enginner Name", "Effective From" };
		return arrStrColumnHdr;
	}

	public String[] getComboHeader() {
		String[] arrStrColumnHdr = {"0" , "Engineering Item Type" , "0" , "Engineering Item Sub Type" , "1" , "Record Status" };
		return arrStrColumnHdr;
	}

	public String[] getComboQuery() {
		
		String[] arrStrComboQuery = new String[3];
		String strHospCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		arrStrComboQuery[0] = bmed.qryHandler_bmed.getQuery(1,"serviceEnggMst.enggItemtype.combo.0");
		arrStrComboQuery[0] = arrStrComboQuery[0].replace("?", Config.SUPER_USER_HOSPITAL_CODE);
		
		arrStrComboQuery[1] = bmed.qryHandler_bmed.getQuery(1,"serviceEnggMst.enggItemSubtype.combo.1");
		arrStrComboQuery[1] = arrStrComboQuery[1].replace("?", strHospCode);		
	
		arrStrComboQuery[2] = "1^Active#2^Inactive";
		
		return arrStrComboQuery;
	}

	public String[] getDeleteQuery() 
	{
		
		String[] arrStrDeleteQuery = new String[1];
		
		arrStrDeleteQuery[0] = bmed.qryHandler_bmed.getQuery(1, "serviceEnggMst.delete.0");
		arrStrDeleteQuery[0] = arrStrDeleteQuery[0].concat(" WHERE "
				+ bmed.qryHandler_bmed.getQuery(1, "serviceEnggMst.delete.cond.0"));

		return arrStrDeleteQuery;
	}

	public String getJsFiles() 
	{
		
		String jsFile = new String("../../bmed/js/serviceEngg_mst.js");
		return jsFile;
		
	}

	public String getMainQuery(String[] cmb) {

		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(bmed.qryHandler_bmed.getQuery(1, "serviceEnggMst.main")
					.replace("?", strHosCode)).append(" AND " + bmed.qryHandler_bmed.getQuery(1,"serviceEnggMst.main.cond.1")
							.replace("?", "1"));
		
		
		if (cmb != null) 
		{

			if (!cmb[2].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[2]);
				
			}
		}
			
			brMainQuery.append(" AND "+bmed.qryHandler_bmed.getQuery(1, "serviceEnggMst.main.cond.2").replace("?", "0"));
			
		if (cmb != null)
		{	
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery.lastIndexOf("0") + 1, cmb[0]);
			
			}		
		}	
			brMainQuery.append(" AND "+bmed.qryHandler_bmed.getQuery(1, "serviceEnggMst.main.cond.3").replace("?","0"));

		if (cmb != null)
		{
			if (!cmb[1].equals("0")) 
			{

				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery.lastIndexOf("0") + 1, cmb[1]);

			}
		}

		return brMainQuery.toString();
	}

	public String getMasterName() {
		String strMasterName = "Service Engineer Master";
		return strMasterName;
	}

	public String[] getOrderBy() {
		String arrStrOrderBy[] = { "1", "UPPER(BMED_FUNCTION.get_Emp_Name(A.GNUM_HOSPITAL_CODE,A.HEMSTR_EMP_ID)) " };
		return arrStrOrderBy;
	}

	public int getPage_per_block() {

		return 10;
	}

	public int getRecord_per_page() {
		
		return 10;
	}

	public String[] getSearchField() {
		String arrStrSearchField[] = { "1", "BMED_FUNCTION.get_Emp_Name(A.GNUM_HOSPITAL_CODE,A.HEMSTR_EMP_ID)" };
		return arrStrSearchField;
	}

	public List<String> getViewHeader() {
		
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Engineering Item Type");
		viewHdr.add("D");
		viewHdr.add("Engineering Item Sub Type");
		viewHdr.add("D");
		viewHdr.add("Emp Name");
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
		
		String strViewQuery = bmed.qryHandler_bmed.getQuery(1,"serviceEnggMst.view");
		
		return strViewQuery;
	}

	public boolean reportInterFaceRequired() {

		return true;
	}

	public void setHttpSession(HttpSession session) {
		
			httpSession = session;		
	}


	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

}
