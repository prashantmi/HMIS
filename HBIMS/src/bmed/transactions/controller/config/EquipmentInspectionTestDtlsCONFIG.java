package bmed.transactions.controller.config;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class EquipmentInspectionTestDtlsCONFIG extends TransInterface {

	/* 
	 * The Constant serialVersionUID. 
	 */
	private static final long serialVersionUID = 02L;

	/*	
	 * The http session.
	 */
	HttpSession httpSession = null;
	
	String[] cmbVal = null;
	
	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	}


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
		
		
		/*strButtons.append("<div class='control_button1'><table width='49%' align='center'>");
		strButtons.append("<tr><td align='center'><div>");
		strButtons
				.append("<a href='#' class='button' onKeyPress='if(event.keyCode==13) add(\"ADD\");' onKeyPress='if(event.keyCode==13) " +
						"(\"ADD\");' onClick='add(\"ADD\");'><span class='save'>Add</span></a>");
		
		strButtons.append("<a href='#' class='button' onKeyPress='if(event.keyCode==13) view(\"VIEW\");'  onClick='buttonLogicsOnClick1(4,\"VIEW\",\"VIEW\")'><span class='view'>View</span></a>");
	
		
		strButtons.append("</td></tr></table></div>");*/
		
		strButtons
		.append("<img src='../../hisglobal/images/btn-add.png' style='cursor: pointer; ' tabindex='1' title='Add Record' onKeyPress='if(event.keyCode==13) add(\"ADD\");' onKeyPress='if(event.keyCode==13) " +
						"(\"ADD\");' onClick='add(\"ADD\");' />");
		
		strButtons
		.append("<img src='../../hisglobal/images/btn-view.png' style='cursor: pointer; ' tabindex='1' title='Select A Record To View' onKeyPress='if(event.keyCode==13) view(\"VIEW\");'  onClick='buttonLogicsOnClick1(4,\"VIEW\",\"VIEW\")'><span class='view' />");
		
		return strButtons.toString();	
		}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getColumnHeader()
	 */
	public String[] getColumnHeader() 
	{
		String[] columnHdr = { "Test/Inspection Name","Equipment Name","Serial No","Date","Time", "Result" };
		return columnHdr;
	}

		
	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboHeader()
	 */
	public String[] getComboHeader() 
	{
		String[] strColumnHdr = {"1" , "Is Active" };
		return strColumnHdr;
		
		///"0^1", "Hospital Name", "0^1", "Lab Name",
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getComboQuery()
	 */
	public String[] getComboQuery()
	{		
		String[] strComboQuery = new String[1];
		/*String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		
		String strSeatId = httpSession.getAttribute("SEATID").toString();

		strComboQuery[0] = "SELECT A.HSTNUM_STORE_ID ,HSTSTR_STORE_NAME FROM HSTT_STORE_MST A,GBLT_ROLE_SEAT_TABLE_DTL p, HSTT_STORE_CATEGORY_MST C "
				+ "WHERE SSTNUM_DWH_TYPE_ID <>122 AND A.HSTNUM_STORE_ID = C.HSTNUM_STORE_ID AND SSTNUM_ITEM_CAT_NO=18 and C.gnum_isvalid=1 "
				+ "and C.gnum_hospital_code="
				+ hosCode
				+ " AND P.GNUM_METATABLE_ID = 101 AND P.GNUM_HOSPITAL_CODE = "
				+ hosCode
				+ " AND P.GNUM_COLUMN_VALUE  = A.HSTNUM_STORE_ID AND A.GNUM_HOSPITAL_CODE="
				+ hosCode
				+ " AND P.GNUM_SEATID =(SELECT PKG_USERMGMT.FUN_GETSEATID("
				+ strSeatId
				+ ","
				+ hosCode
				+ ") FROM DUAL) ORDER BY INITCAP(HSTSTR_STORE_NAME) ";

		if (cmbVal != null) {
			strComboQuery[1] = "SELECT A.HSTNUM_STORE_ID ,HSTSTR_STORE_NAME FROM HSTT_STORE_MST A, GBLT_ROLE_SEAT_TABLE_DTL P,HSTT_STORE_CATEGORY_MST C "
					+ " WHERE SSTNUM_DWH_TYPE_ID =122 AND A.GNUM_ISVALID=1 AND HSTNUM_PARENT_STORE_ID ="
					+ cmbVal[0]
					+ " "
					+ "AND A.HSTNUM_STORE_ID = C.HSTNUM_STORE_ID AND c.GNUM_HOSPITAL_CODE = "
					+ hosCode
					+ " AND  SSTNUM_ITEM_CAT_NO=18 AND C.GNUM_ISVALID =1  AND P.GNUM_METATABLE_ID = 101 AND P.GBL_ISVALID=1 "
					+ "AND P.GNUM_HOSPITAL_CODE = "
					+ hosCode
					+ " AND P.GNUM_COLUMN_VALUE  = A.HSTNUM_STORE_ID "
					+ "AND P.GNUM_SEATID =(SELECT PKG_USERMGMT.FUN_GETSEATID("
					+ strSeatId
					+ ","
					+ hosCode
					+ ") FROM DUAL) ORDER BY INITCAP(HSTSTR_STORE_NAME) ";
		}else {
			strComboQuery[1] = "SELECT A.HSTNUM_STORE_ID ,HSTSTR_STORE_NAME FROM HSTT_STORE_MST A, GBLT_ROLE_SEAT_TABLE_DTL P,HSTT_STORE_CATEGORY_MST C "
				+ " WHERE SSTNUM_DWH_TYPE_ID =122 AND A.GNUM_ISVALID=1 AND HSTNUM_PARENT_STORE_ID =#1#"
				+ "AND A.HSTNUM_STORE_ID = C.HSTNUM_STORE_ID AND c.GNUM_HOSPITAL_CODE = "
				+ hosCode
				+ " AND  SSTNUM_ITEM_CAT_NO=18 AND C.GNUM_ISVALID =1  AND P.GNUM_METATABLE_ID = 101 AND P.GBL_ISVALID=1 "
				+ "AND P.GNUM_HOSPITAL_CODE = "
				+ hosCode
				+ " AND P.GNUM_COLUMN_VALUE  = A.HSTNUM_STORE_ID "
				+ "AND P.GNUM_SEATID =(SELECT PKG_USERMGMT.FUN_GETSEATID("
				+ strSeatId
				+ ","
				+ hosCode
				+ ") FROM DUAL) ORDER BY INITCAP(HSTSTR_STORE_NAME) ";
	}*/
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
		strDeleteQuery[0] = bmed.qryHandler_bmed.getQuery(1, "equipmentTestParameterMst.delete.0").replace("?", seatId);
		strDeleteQuery[0] = strDeleteQuery[0].concat(" WHERE "+ bmed.qryHandler_bmed.getQuery(1, "equipmentTestParameterMst.delete.cond.0"));

		return strDeleteQuery;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMainQuery(java.lang.String[])
	 */
	public String getMainQuery(String[] cmb)
	{
		String strHosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

	
		StringBuffer brMainQuery = new StringBuffer();
		brMainQuery.append(bmed.qryHandler_bmed.getQuery(1, "equipmentTestInspectionDtl.main")
					.replace("?", strHosCode)).append(" AND " + bmed.qryHandler_bmed.getQuery(1,"equipmentTestInspectionDtl.main.cond.1")
							.replace("?", "1"));
		/*brMainQuery.append(bmed.qryHandler_bmed.getQuery(1, "equipmentTestParameterMst.main")
					.replace("?", strHosCode)).append(" AND " + bmed.qryHandler_bmed.getQuery(1,"equipmentTestParameterMst.main.cond.1")
							.replace("?", "1"));*/
		
	/*	brMainQuery.append("select HEMNUM_EQU_TEST_ID||'@'||HEMNUM_TEST_ID||'@'||GNUM_HOSPITAL_CODE");
		brMainQuery.append("||'^'||(select HEMSTR_TEST_NAME from hemt_test_mst where GNUM_ISVALID=1 and HEMNUM_TEST_ID=T.HEMNUM_TEST_ID and " +
				"GNUM_HOSPITAL_CODE=T.GNUM_HOSPITAL_CODE)");
		brMainQuery.append("||'^' ||hemms_dss_util.get_hemms_dss (3, gnum_hospital_code, hemnum_item_id)|| '^'|| nvl(hemstr_batch_sl_no,'N/A')");
		brMainQuery.append("||'^'||to_char(HEMDT_TEST_DT,'DD-Mon-YYYY')||'^'||HEMSTR_TEST_TIME||'^'||HEMSTR_RESULT");
		brMainQuery.append(" from  HEMT_TEST_DTL T WHERE T.GNUM_HOSPITAL_CODE="+strHosCode);
	*/	
		
		/*if (cmb != null) 
		{
			if (!cmb[0].equals("0"))
			{
				brMainQuery.append(" AND T.HEMNUM_DEPT_ID = "+cmb[0]);
			}
			if (!cmb[1].equals("0"))
			{
				brMainQuery.append(" AND T.HEMNUM_STORE_ID = "+cmb[1]);
			}
			if (!cmb[0].equals("0"))
			{
				brMainQuery.append(" AND T.GNUM_IS_VALID = "+cmb[0]);
			}
			
		}*/
		

		/*if (cmb != null) 
		{

			if (!cmb[0].equals("0")) 
			{
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery.lastIndexOf("1") + 1, cmb[0]);
				
			}
		}*/

			
		System.out.println("brMainQuery of inspectionTestDtl---"+brMainQuery.toString());
		return brMainQuery.toString();
		
		
	}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getMasterName()
	 */
	public String getMasterName()
	{
		String strMasterName = "Equipment Test and Inspection Desk >> Equipment Test/Inspection Entry";
		return strMasterName;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getOrderBy()
	 */
	public String[] getOrderBy()
	{
		String strOrderBy[] = { "1", " UPPER(BMED_FUNCTION.get_test_name(GNUM_HOSPITAL_CODE,HEMNUM_TEST_ID)) ","2" ,"HEMNUM_EQU_TEST_ID","3","HEMDT_TEST_DT" };
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
		String strSearchField[] = { "1", "UPPER(BMED_FUNCTION.get_test_name(GNUM_HOSPITAL_CODE,HEMNUM_TEST_ID))"};
		return strSearchField;
	}

	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewHeader()
	 */
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Test/Inspection Name");
		viewHdr.add("D");
		viewHdr.add("Equipment Name");
		viewHdr.add("D");
		viewHdr.add("Serial No.");
		viewHdr.add("D");
		viewHdr.add("Date");
		viewHdr.add("D");
		viewHdr.add("Time");
		viewHdr.add("D");
		viewHdr.add("Result");
		viewHdr.add("D");
		//viewHdr.add("Record Status");
		//viewHdr.add("D");
		return viewHdr;
	}


	/* (non-Javadoc)
	 * @see hisglobal.masterutil.MasterInterface#getViewQuery()
	 */
	public String getViewQuery()
	{		
		String strViewQuery = bmed.qryHandler_bmed.getQuery(1,"equipmentTestParameterMst.view");
		
		return strViewQuery;
	}

	public String[] getColsWidth() {

		return null;
	}
	
	public boolean reportInterFaceRequired() {

		return true;
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
		
      	String[] status = {"1", "3", "56B575", "<b>Not Working</b>","1","3","#FA8072","Reminders"};*/
		String[] status = {"1","1"};
		return status;
      
	}

	public String getEventState() 
	{
		String strEvent = "buttonLogicsOnRecordCheck1";
     	return strEvent;
	}

	public String getButtonConfiguration() {
		return "left";
	}
	
	public String[] getUserDefinedButtons() 
	{				
		         String[] strButtons = null; 
			     strButtons = new String[10];
			    
				 strButtons[0] = "Test Creation@buttonLogicsOnClick1(1,'TEST','Add')@0";
				 strButtons[1] = "Parameter@buttonLogicsOnClick1(2,'PARAMETER','Add')@0";
			  	 strButtons[2] = "Mapping@buttonLogicsOnClick1(3,'MAPPING','Add')@0";
			  	 strButtons[3] = "Entry@buttonLogicsOnClick1(4,'ENTRY','Add')@1";
			  	 strButtons[4] = "Correction@buttonLogicsOnClick1(5,'CORRECTION','Add')@1";
			  	 strButtons[5] = "Verification@buttonLogicsOnClick1(6,'VERIFICATION','Add')@1";
			  	 strButtons[6] = "Validation@buttonLogicsOnClick1(7,'VALIDATION','Add')@1";
			  	 
			  	 //change done by paras jain
			  	 strButtons[7] = "Rollback@buttonLogicsOnClick1(8,'ROLLBACK','Add')@1";		
			  	 strButtons[8] = "Print@buttonLogicsOnClick1(9,'PRINT','Add')@0";	
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
}