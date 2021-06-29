package bmed.masters.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;

/**
 * @author	Vivek Aggarwal   
 * Creation Date:- 11/April/2011 
 * Modifying Date:- 12/April/2011
 *  Module:- BMED(HIS Project)
 * 
 */
public class NonItemMstUTL  implements MasterInterface
{

	/* 
	 * The Constant serialVersionUID. 
	 */
	private static final long serialVersionUID = 02L;

	/*	
	 * The http session.
	 */
	HttpSession httpSession = null;
	

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#setHttpSession(javax.servlet.http.HttpSession)
	 */
	public void setHttpSession(HttpSession session)
	{		
			httpSession = session;		
	}
	
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getButtons()
	 */
	public String getButtons() 
	{		
		StringBuilder strButtons = new StringBuilder();
		strButtons
				.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer; ' title='Add' tabindex='0' onKeyPress='if(event.keyCode==13) add(\"ADD\");' onClick='add(\"ADD\");' />");
		strButtons
				.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor: pointer; ' title='Modify' tabindex='0' onKeyPress='if(event.keyCode==13) edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' />");
		strButtons
				.append("<img src='../../hisglobal/images/btn-del.png' style='cursor: pointer; ' title='Delete' tabindex='0' onKeyPress='if(event.keyCode==13) deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");' />");
		strButtons
				.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer; ' title='View' tabindex='0' onKeyPress='if(event.keyCode==13) view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");' />");
		strButtons
				.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor: pointer; ' title='Report' tabindex='0' onKeyPress='if(event.keyCode==13) report(\"REPORT\");'  onClick='report(\"REPORT\");' />");

		return strButtons.toString();	
		}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() 
	{
		String[] columnHdr = { "Non-Item Name", "Effective From Date" };
		return columnHdr;
	}

		
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() 
	{
		String[] strColumnHdr = {"0" , "Engineering Item Type" , "0" , "Engineering Item Sub Type" , "1" , "Is Active" };
		return strColumnHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery()
	{		
		String[] strComboQuery = new String[3];
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		
		strComboQuery[0] = bmed.qryHandler_bmed.getQuery(1,"nonItemMst.enggItemtype.combo.0");
		strComboQuery[0] = strComboQuery[0].replace("?", Config.SUPER_USER_HOSPITAL_CODE);
		
		strComboQuery[1] = bmed.qryHandler_bmed.getQuery(1,"nonItemMst.enggItemSubtype.combo.1");
		strComboQuery[1] = strComboQuery[1].replace("?", hosCode);		
	
		strComboQuery[2] = "1^Active#2^Inactive";
		
		return strComboQuery;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() 
	{		
		String[] strDeleteQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		strDeleteQuery[0] = bmed.qryHandler_bmed
				.getQuery(1, "nonItemMst.delete.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" WHERE "
				+ bmed.qryHandler_bmed.getQuery(1, "nonItemMst.delete.cond.0"));

		return strDeleteQuery;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() 
	{
		return "/HEMMS_ODISHA/WebContent/bmed/js/nonItemMst.js";
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb)
	{
		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(bmed.qryHandler_bmed.getQuery(1, "nonItemMst.main")
					.replace("?", strHosCode)).append(" AND " + bmed.qryHandler_bmed.getQuery(1,"nonItemMst.main.cond.1")
							.replace("?", "1"));
		
		
		if (cmb != null) 
		{

			if (!cmb[2].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[2]);
				
			}
		}
			
			brMainQuery.append(" AND "+bmed.qryHandler_bmed.getQuery(1, "nonItemMst.main.cond.2").replace("?", "0"));
			
		if (cmb != null)
		{	
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery.lastIndexOf("0") + 1, cmb[0]);
			
			}		
		}	
			brMainQuery.append(" AND "+bmed.qryHandler_bmed.getQuery(1, "nonItemMst.main.cond.3").replace("?","0"));

		if (cmb != null)
		{
			if (!cmb[1].equals("0")) 
			{

				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery.lastIndexOf("0") + 1, cmb[1]);

			}
		}
		return brMainQuery.toString();
	}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName()
	{
		String strMasterName = "Non-Item Master";
		return strMasterName;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy()
	{
		String strOrderBy[] = { "1", " UPPER(HEMSTR_NONITEM_NAME) " };
		return strOrderBy;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getPage_per_block()
	 */
	public int getPage_per_block() 
	{
		return 10;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getRecord_per_page()
	 */
	public int getRecord_per_page()
	{		
		return 10;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */	
	public String[] getSearchField()
	{
		String strSearchField[] = { "1", "HEMSTR_NONITEM_NAME" };
		return strSearchField;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Engineering Item Type");
		viewHdr.add("D");
		viewHdr.add("Engineering Item Sub Type");
		viewHdr.add("D");
		viewHdr.add("Non-Item Name");
		viewHdr.add("D");
		viewHdr.add("Effective From");
		viewHdr.add("D");
		viewHdr.add("Remarks");
		viewHdr.add("D");
		viewHdr.add("Record Status");
		viewHdr.add("D");
		return viewHdr;
	}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery()
	{		
		String strViewQuery = bmed.qryHandler_bmed.getQuery(1,"nonItemMst.view");
		
		return strViewQuery;
	}

	public String[] getColsWidth() {

		return null;
	}
	
	public boolean reportInterFaceRequired() {

		return true;
	}

	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

}
