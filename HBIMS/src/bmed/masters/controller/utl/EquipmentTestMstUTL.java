package bmed.masters.controller.utl;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author   
 * Creation Date:- 27-Jul-2012 
 * Modifying Date:- 27-Jul-2012
 * Used For:-	
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestMstUTL  extends TransInterface
{

	/* 
	 * The Constant serialVersionUID. 
	 */
	private static final long serialVersionUID = 02L;

	/*	
	 * The http session.
	 */
	HttpSession httpSession = null;
	
	String[] cmbVal = null;
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
	
	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	}
	
	public String getMasterName()
	{
		String strMasterName = "Equipment Test and Inspection Desk >> Equipment Test Master";
		return strMasterName;
	}
	
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Test Name");
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
	 * @see hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb)
	{
		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(bmed.qryHandler_bmed.getQuery(1, "equipmentTestMst.main")
					.replace("?", strHosCode)).append(" AND " + bmed.qryHandler_bmed.getQuery(1,"equipmentTestMst.main.cond.1")
							.replace("?", "1"));
		
		//System.out.println("Main qry::"+brMainQuery.toString());
		
		if (cmb != null) 
		{

			if (!cmb[0].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
				
			}
		}
		
		/*if (cmb != null) 
		{

			if (!cmb[2].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[2]);
				
			}
		}
			
			brMainQuery.append(" AND "+bmed.qryHandler_bmed.getQuery(1, "equipmentTestMst.main.cond.2").replace("?", "0"));
			
		if (cmb != null)
		{	
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery.lastIndexOf("0") + 1, cmb[0]);
			
			}		
		}	
			brMainQuery.append(" AND "+bmed.qryHandler_bmed.getQuery(1, "equipmentTestMst.main.cond.3").replace("?","0"));

		if (cmb != null)
		{
			if (!cmb[1].equals("0")) 
			{

				brMainQuery.replace(brMainQuery.lastIndexOf("0"), brMainQuery.lastIndexOf("0") + 1, cmb[1]);

			}
		}*/
		
		System.out.println("brMainQuery of testMst---"+brMainQuery.toString());
		return brMainQuery.toString();
	}
	
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getSearchField()
	 */	
	public String[] getSearchField()
	{
		String strSearchField[] = { "1", "HEMSTR_TEST_NAME" };
		return strSearchField;
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
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() 
	{
		String[] columnHdr = {"Test Name", "Effective From Date" };
		return columnHdr;
	}
	
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery()
	{		
		String[] strComboQuery = new String[1];
	//	String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		/*strComboQuery[0] = bmed.qryHandler_bmed.getQuery(1,"equipmentTestMst.enggItemtype.combo.0");
		strComboQuery[0] = strComboQuery[0].replace("?", hosCode);
		
		strComboQuery[1] = bmed.qryHandler_bmed.getQuery(1,"equipmentTestMst.enggItemSubtype.combo.1");
		strComboQuery[1] = strComboQuery[1].replace("?", hosCode);		
	*/
		strComboQuery[0] = "1^Active#2^Inactive";
		
		return strComboQuery;
	}
	
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery()
	{		
		String strViewQuery = bmed.qryHandler_bmed.getQuery(1,"equipmentTestMst.view");
		
		return strViewQuery;
	}
	
	public String getButtons() 
	{		
		StringBuilder strButtons = new StringBuilder();
		
		/*strButtons.append("<div class='control_button1'><table width='72%' align='center'>");
		strButtons.append("<tr><td align='center'><div>");
		strButtons.append("<a href='#' class='button' onClick='add(\"ADD\");'><span class='save'>Add</span></a>");
		strButtons.append("<a href='#' class='button'	onClick='edit(\"MODIFY\");'><span class='modify'>Modify</span></a></div>");
		strButtons.append("<a href='#' class='button'	onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
	//	strButtons.append("<a href='#' class='deletebutton'   onClick='deleteRecords(\"DELETE\");'><span class='delete'>Delete</span></a>");
	//	strButtons.append("<a href='#' class='button'	onClick='view(\"VIEWDATA\");'><span class='view'>View</span></a>");
	//	strButtons.append("<a href='#' class='button' onClick='buttonLogicsOnClick1(11,\"SHOWRPT\",\"Add\");'><span class='reports'>Report</span></a></div>");
		
		strButtons.append("</td></tr></table></div>");*/
		
		strButtons
		.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer; ' tabindex='1' title='Add Record' onClick='add(\"ADD\");' />");
		strButtons
		.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor: pointer; ' tabindex='1' title='Update Record' onClick='edit(\"MODIFY\");' />");

		strButtons
		.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer; ' tabindex='1' title='Select A Record To View' onClick='view(\"VIEWDATA\");'><span class='view' />");
		
			
				
		return strButtons.toString();	
		}

	
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getDeleteQuery()
	 */
	public String[] getDeleteQuery() 
	{	
		
		
		String[] strDeleteQuery = new String[2];
		String seatId = httpSession.getAttribute("SEATID").toString();
		String strHospitalCode= httpSession.getAttribute("HOSPITAL_CODE").toString();
		
		
		strDeleteQuery[0] = bmed.qryHandler_bmed.getQuery(1, "equipmentTestMst.delete.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" WHERE "+ bmed.qryHandler_bmed.getQuery(1, "equipmentTestMst.delete.cond.0"));
		strDeleteQuery[0] = strDeleteQuery[0].concat(" and "+ bmed.qryHandler_bmed.getQuery(1, "equipmentTestMst.delete.cond.1").replace("?", strHospitalCode));
		
		System.out.println("strDeleteQuery::"+strDeleteQuery[0]);
		return strDeleteQuery;
	}

	public List<String> getDeleteData(String chk) {
		List<String> deleteData = new ArrayList<String>();

		return deleteData;
	}
	
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getJsFiles()
	 */
	public String getJsFiles() 
	{
		//return "/HEMMS_ODISHA/WebContent/bmed/js/equipmentTestMst.js";
		String files = "../../bmed/js/equipment_test_inspection_desk.js";
		return files;
	}
	
	public String[] getRowStatus() 
	{
		/*
		 * "1"--->> Value Which we Want to Mapped        1         2    3   4   5    6       7
		 * "7"--->> Column Name Primary Key + 1 e.g. 10@1001@108^Amole^XAS^1000^3^2-Jan-2011^1
		 * "blue"--->>
		 * "Exp Within"--->>Argument You Want to Show in Footer of Template
		 * 
		 */
		/*ResourceBundle res = mms.qryHandler_mms.res;
		if(res == null) 
		{
			res = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
			mms.qryHandler_mms.res = res;
		}
		*/
      	String[] status = {"1","1"};
		return status;
      
	}

	public String getEventState() 
	{
		String strEvent = "buttonLogicsOnRecordCheck";
     	return strEvent;
	}

	public String getButtonConfiguration() {
		return "left";
	}
	
	public String[] getUserDefinedButtons() 
	{				
		         String[] strButtons = null; 
			     strButtons = new String[10];
			    
				 strButtons[0] = "Test Creation@buttonLogicsOnClick1(1,'TEST','Add')@1";
				 strButtons[1] = "Parameter@buttonLogicsOnClick1(2,'PARAMETER','Add')@0";
			  	 strButtons[2] = "Mapping@buttonLogicsOnClick1(3,'MAPPING','Add')@0";
			  	 strButtons[3] = "Entry@buttonLogicsOnClick1(4,'ENTRY','Add')@0";
			  	 strButtons[4] = "Correction@buttonLogicsOnClick1(5,'CORRECTION','Add')@1";
			  	 strButtons[5] = "Verification@buttonLogicsOnClick1(6,'VERIFICATION','Add')@1";
			  	 strButtons[6] = "Validation@buttonLogicsOnClick1(7,'VALIDATION','Add')@1";
			  	 
			  	 //change done by paras jain
			  	 strButtons[7] = "Rollback@buttonLogicsOnClick1(8,'ROLLBACK','Add')@1";		
			  	 strButtons[8] = "Print@buttonLogicsOnClick1(9,'PRINT','Add')@1";	
			  	 strButtons[9] = "Archieve@buttonLogicsOnClick1(10,'ARCHIEVE','Add')@1";	
				
		         return strButtons;
	}
	
	public String[] getDbButtons() {
		//	String[] str = { "1" };
			return null;
			
		}

		public int getMinPanelButton() {
			return 1;
		}
		
		public String getMenuOption() {
			String menuType = "Tiles";
			return menuType;
		}

		

		

		public String getComboEventState() {
			return "";
		}
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy()
	{
		String strOrderBy[] = { "1", " UPPER(HEMSTR_TEST_NAME) " };
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

	

	

	

	public String[] getColsWidth() {

		return null;
	}
	
	public boolean reportInterFaceRequired() {

		return true;
	}
}
