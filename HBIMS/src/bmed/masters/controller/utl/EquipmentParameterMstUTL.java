package bmed.masters.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import hisglobal.masterutil.MasterInterface;

/**
 * @author   
 * Creation Date:- 06-Aug-2012 
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentParameterMstUTL  implements MasterInterface
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
		String[] columnHdr = { "Parameter Name", "Effective From Date" };
		return columnHdr;
	}

		
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() 
	{
		//String[] strColumnHdr = {"0" , "Engineering Item Type" , "0" , "Engineering Item Sub Type" , "1" , "Is Active" };
		String[] strColumnHdr = {"1" , "Is Active" };
		return strColumnHdr;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery()
	{		
		String[] strComboQuery = new String[3];
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		strComboQuery[0] = "1^Active#2^Inactive";
		
		return strComboQuery;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() 
	{		
		String[] strDeleteQuery = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
		strDeleteQuery[0] = bmed.qryHandler_bmed.getQuery(1, "equipmentParameterMst.delete.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" WHERE "+ bmed.qryHandler_bmed.getQuery(1, "equipmentParameterMst.delete.cond.0"));

		return strDeleteQuery;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() 
	{
		return "/AHIMS/WebContent/bmed/js/equipmentParameterMst.js";
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb)
	{
		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(bmed.qryHandler_bmed.getQuery(1, "equipmentParameterMst.main")
					.replace("?", strHosCode)).append(" AND " + bmed.qryHandler_bmed.getQuery(1,"equipmentParameterMst.main.cond.1")
							.replace("?", "1"));
		
		
		
		if (cmb != null) 
		{

			if (!cmb[0].equals("0"))
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);

			}
		}

			
		
		return brMainQuery.toString();
		
		
	}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName()
	{
		String strMasterName = "Equipment Parameter Master";
		return strMasterName;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy()
	{
		String strOrderBy[] = { "1", " UPPER(HEMSTR_PARAMETER_NAME) " };
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
		String strSearchField[] = { "1", "HEMSTR_PARAMETER_NAME" };
		return strSearchField;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Parameter Name");
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
		String strViewQuery = bmed.qryHandler_bmed.getQuery(1,"equipmentParameterMst.view");
		
		return strViewQuery;
	}

	public String[] getColsWidth() {

		return null;
	}
	
	public boolean reportInterFaceRequired() {

		return true;
	}

	@Override
	public String isGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

}
