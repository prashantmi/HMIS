package bmed.masters.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;

/**
 * @author   Arun V.R
 * Creation Date:- 20-jan-2011 
 * Modifying Date:- 
 * Used For:-Expertise Master
 * Team Lead By:- Amit Kumar
 * Module:- BMED(HIS Project)
 * 
 */
public class ExpertiseMstUTL implements MasterInterface{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The http session. */
	HttpSession httpSession = null;


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http.HttpSession)
	 */
	public void setHttpSession(HttpSession session) {

		httpSession = session;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName() {

		String strMasterName = " Expertise Master ";
		return strMasterName;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() {

		String[] arrStrColumnHdr = { "Expertise Name", "Effective From Date" };
		return arrStrColumnHdr;

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */
	public String[] getSearchField() {

		String arrStrSearch_field[] = { "1", "hemstr_skill_name" };
		return arrStrSearch_field;

		
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getRecord_per_page()
	 */
	public int getRecord_per_page() {

		return 10;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getPage_per_block()
	 */
	public int getPage_per_block() {

		return 5;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() {

		String[] arrStrComboHdr = { "1", "Record Status" };
		return arrStrComboHdr;

	

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery() {


		String[] arrStrComboQuery = new String[1];
		arrStrComboQuery[0] = "1^Active#2^Inactive";
		return arrStrComboQuery;
	
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] arrCmb) {

		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		//String strHosCode = Config.SUPER_USER_HOSPITAL_CODE;
		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(
				bmed.qryHandler_bmed.getQuery(1, "expertiseMst.main")
				.replace("?", strHosCode)).append(
						" AND "
						+ bmed.qryHandler_bmed.getQuery(1,
						"expertiseMst.main.cond.1").replace("?", "1"));

	if (arrCmb != null) 
		{

			if (!arrCmb[0].equals("0"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, arrCmb[0]);

			}
		}

		
		
		return brMainQuery.toString();

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery() {
		
		return bmed.qryHandler_bmed.getQuery(1, "expertiseMst.view");
		

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Expertise Name ");
		viewHdr.add("D");
		viewHdr.add("Effective From ");
		viewHdr.add("D");
		viewHdr.add("Remarks ");
		viewHdr.add("D");
		viewHdr.add("Record Status ");
		viewHdr.add("D");
	return viewHdr;
		

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy() {

		String arrStrOrderBy[] = { "1", "UPPER(HEMSTR_SKILL_NAME)" };
		return arrStrOrderBy;
	

	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() {

		String[] arrStrDelQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		arrStrDelQuery[0] = bmed.qryHandler_bmed.getQuery(1, "expertiseMst.delete")
		.replace("?", seatId);

		arrStrDelQuery[0] = arrStrDelQuery[0].concat(" WHERE "
				+ bmed.qryHandler_bmed.getQuery(1, "expertiseMst.delete.cond.1"));
		return arrStrDelQuery;
		
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() {

		StringBuilder br = new StringBuilder();

		br
		.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer;' title='Add' tabindex='0' onKeyPress='if(event.keyCode==13) return callComboAdd(document.forms[0],3);' onClick='return callComboAdd(document.forms[0],3);' />");
		br
		.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor: pointer;' title='Update' tabindex='0' onKeyPress='if(event.keyCode==13) return callComboModify(document.forms[0],3);' onClick='return callComboModify(document.forms[0],3);' />");
		br
		.append("<img src='../../hisglobal/images/btn-del.png' style='cursor: pointer;' title='Delete' tabindex='0' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' />");
		br
		.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer;' title='View' tabindex='0' onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' />");
		br
		.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor: pointer;' title='Report' tabindex='0' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  onClick='report(\"REPORT\");' />");

		return br.toString();
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() {

		String jsFile = new String("../../bmed/js/expertiseMst.js");
		return jsFile;
	//	return null;
	}

	public String[] getColsWidth() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reportInterFaceRequired() {
		// TODO Auto-generated method stub
		return true;
	}

	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}
}
