package mms.transactions.controller.utl;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;

public class TransferRequestTransUTL extends TransInterface 
{
	
	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	String[] cmbVal = null;

	public void setHttpSession(HttpSession session) 
	{
		this.httpSession = session;
	}

	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	}

	public String getMasterName() {
		String masterName = "Transfer Request Detail(s)";
		return masterName;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[]) {
		
		StringBuffer brMainQuery = new StringBuffer(1000);
		String strFinancialStartYear = "";
        String strFinancialEndYear = "";
	    MmsConfigUtil mcu = null;
	    mcu = new MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());
	   
	    
        brMainQuery.append("SELECT HSTNUM_REQUEST_NO ||'^'||HSTNUM_REQUEST_NO||'^'||TO_CHAR(HSTDT_REQUEST_DATE,'DD-Mon-YYYY')|| '^'|| ");
		brMainQuery.append("MMS_MST.GET_ITEM_DTL(1,GNUM_HOSPITAL_CODE,HSTNUM_ITEMBRAND_ID) || '^'|| ");
		brMainQuery.append("NVL(HSTNUM_REQ_QTY,0) || ' ' || MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_REQ_QTY_UNITID) || '^'|| ");
		brMainQuery.append("NVL(HSTNUM_APPROVED_QTY,0) || ' ' || MMS_MST.GETUNITNAME(GNUM_HOSPITAL_CODE,HSTNUM_APPQTY_UNITID)||'^'||NVL(HSTNUM_IS_FORCE_CLOSE,0) ");
		brMainQuery.append(" AS DATA FROM HSTT_TRANSFER_EXCESS_REQ_DTL ");
		brMainQuery.append(" WHERE GNUM_ISVALID = 1 AND ");
		brMainQuery.append(" GNUM_HOSPITAL_CODE = ");
		brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
			    					
		if (cmb != null) 
		{ 			
			//financial date on the basis of store id
			if (!cmb[0].equals("0")) 
			{
				
				strFinancialStartYear = mcu.getStrFinancialStartDate(cmb[0] , httpSession.getAttribute("HOSPITAL_CODE").toString());
				strFinancialEndYear   = mcu.getStrFinancialEndDate(cmb[0] , httpSession.getAttribute("HOSPITAL_CODE").toString());
			}
						
			//pending
			if (cmb[1].equals("0"))
			{
				//brMainQuery.append( " AND  HSTNUM_INDENT_STATUS NOT IN(99,45,50)");
			    brMainQuery.append(" AND GNUM_STATUS = 0");
			}
			//partial approved
			else if (cmb[1].equals("40"))
			{
			    brMainQuery.append(" AND GNUM_STATUS IN (40,45,50 ) AND HSTNUM_TOT_PENDING_ORDER > 0");
			}			
			//Partial Processed
			else if (cmb[1].equals("99"))
			{
				brMainQuery.append(" AND GNUM_STATUS = 99");
			}			  
			//Partial Processed
			else if (cmb[1].equals("50"))
			{
				brMainQuery.append(" AND GNUM_STATUS = 50");
			}		
			//store id
			if (!cmb[0].equals("0")) 
			{				
				
                brMainQuery.append(" AND HSTDT_FINANCIAL_START_DATE = TO_DATE('"+strFinancialStartYear+"','dd-Mon-YYYY')");				
				brMainQuery.append(" AND HSTDT_FINANCIAL_END_DATE = TO_DATE('"+strFinancialEndYear+"','dd-Mon-YYYY')");
				brMainQuery.append(" AND HSTNUM_STORE_ID = ");
				brMainQuery.append(cmb[0]);
			}
		 }
		else
		{			
			brMainQuery.append(" AND GNUM_STATUS = 0 ");
		}	

		//System.out.println("Demand Request Main Query-->>>>"+brMainQuery.toString());
		return brMainQuery.toString();
		
	}

	/**
	 * Combo Option Search feature.
	 * 
	 *  1^0 represents  1 >> dummy value and 0 >> index of the Combo (first combo >> 0 , second combo >> 1 , etc., ) 
	 *  next value after "1^0" should be the Name of the Combo Header (first combo in this case) 
	 *  
	 *  by doing this in the transaction template , at the search combo box  a new field with the Combo Header will be available, by selecteing 
	 *  the option and corresponding value, a search can be made in the corresponding combo box
	 *   
	 */
	public String[] getSearchField() 
	{
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] search_field = new String[4];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{	

			search_field[0] = "1^0";
			search_field[1] = "ReqNo";		
			search_field[2] = "3^0";
			search_field[3] = "Drug Name";

		}		
 
		
		return search_field;
	}
	/**
	 * 0^0 0 Means Combo From Query,
	 * 1  Means User Defined Combo,0 After ^ Means Select Value,
	 * 1 Means All,2 Means Default Selected
	 * */
	public String[] getComboHeader() 
	{
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] strComboHeader = new String[4];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{	

			strComboHeader[0] = "0^2";
			strComboHeader[1] = "Drug WareHouse";
			strComboHeader[2] = "1";
			strComboHeader[3] = "Status";

		}
		else
		{
			strComboHeader[0] = "0^2";
			strComboHeader[1] = "Store Name";
			strComboHeader[2] = "1";
			strComboHeader[3] = "Status";

		}

		
		
	    return strComboHeader;
	}

	public String[] getColumnHeader() 
	{		
		
		String[] strColumnHeader = new String[5];			

			strColumnHeader[0] = "Req No";
			strColumnHeader[1] = "Req Date";
			strColumnHeader[2] = "Drug Name";
			strColumnHeader[3] = "Excess Qty";
			strColumnHeader[4] = "Ordered Qty";

			
		return strColumnHeader;
	}      

	public String[] getComboQuery(){
	
		String[] comboQuery = new String[3];

		comboQuery[0] = "select HSTNUM_STORE_ID,HSTSTR_STORE_NAME from HSTT_STORE_MST A where GNUM_ISVALID = 1 and  GNUM_HOSPITAL_CODE ="+
						httpSession.getAttribute("HOSPITAL_CODE").toString()+
						" AND EXISTS"+
						   "("+
						    "SELECT 'X'"+
						    "FROM GBLT_ROLE_SEAT_TABLE_DTL P, GBLT_METATABLE_COLUMN_MST q"+
						    " WHERE P.gnum_metatable_id = q.gnum_metatable_id"+
						    " AND P.gnum_hospital_code = q.gnum_hospital_code"+
						    " AND UPPER(gstr_table_name) = UPPER('HSTT_STORE_MST')"+
						    " AND UPPER(gstr_column_name) = UPPER('HSTNUM_STORE_ID')"+
						    " AND P.gnum_hospital_code = A.gnum_hospital_code"+
						  " AND gnum_column_value = A.HSTNUM_STORE_ID"+
						 " AND P.gnum_seatid = Pkg_Usermgmt.fun_getseatid("+httpSession.getAttribute("SEATID").toString()+				 
						 ",A.GNUM_HOSPITAL_CODE)"+
						") ORDER BY INITCAP(HSTSTR_STORE_NAME)";
		
		comboQuery[1] = "0^Active#40^Tobe-Transfered#99^Rejected#50^Processed";
		return comboQuery;
	}

	public String getViewQuery() {
		return "";
	}

	public String getButtons() {
		String strButtons = "";
		//"<a style=cursor:pointer><img src='../../hisglobal/images/btn-ccl.png'  border=0 tabindex='1' onKeyPress='cancelPage();' onClick='cancelPage();'></a>";
		return strButtons;
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		deleteQuery[0] = "";

		return deleteQuery;
	}

	public List<String> getDeleteData(String chk) {
//		String a[] = null;
//		String b[] = null;
//		String key[] = new String[1];
//
		List<String> deleteData = new ArrayList<String>();
//		a = (chk.replace('|', '#')).split("#");
//		b = (a[0].replace('@', '#')).split("#");
//
//		key[0] = b[0];
//		System.out.print("key[0] = " + key[0]);
//		deleteData.add(key);
//		return deleteData;
		return deleteData;
	}

	public String getJsFiles() {
		String files = "../../mms/js/transferRequestDesk_trans.js";
		return files;

	}

	public String[] getRowStatus() 
	{
		/*
		 * "1"--->> Value Which we Want to Maaped        1         2    3   4   5    6       7
		 * "7"--->> Column Name Primary Key + 1 e.g. 10@1001@108^Amole^XAS^1000^3^2-Jan-2011^1
		 * "blue"--->>
		 * "Exp Within"--->>Argument You Want to Show in Footer of Template
		 * 
		 */
		//String[] status = {"1", "7", "LightSlateGrey", "('*'Reserved/Branded Item ) Expired within " + mmscofigutil.getStrExpAlertDays() + " Day (s)","0","8",res.getString("RE_ORDER_COLOR"),"Quantity < Re-Oreder Level"};
		String[] status ={ "1", "7", "LightSlateGrey", "Force Fully Closed" };
		
		return status;
	}

	public String getEventState() 
	{
		String strEvent = "chkUserDefinedFunc";
		return strEvent;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	
		public String[] getUserDefinedButtons() 
		{
			
			         String[] strButtons = null; 
				     strButtons = new String[4];
					 strButtons[0] = "Generate@buttonLogicsOnClickGenerate(1,'GENERATE','Generate')@0@3b5998@glyphicon-plus";
					 strButtons[1] = "Modify@buttonLogicsOnClickModify(2,'MODIFY','Modify')@1@ff5057@glyphicon-edit";
					 strButtons[2] = "Cancel@buttonLogicsOnClickCancel(3,'REMOVE','Cancel')@1@bb0000@glyphicon-remove";
				  	 strButtons[3] = "View@buttonLogicsOnClickView(4,'VIEW','View')@1@007bb6@glyphicon-fullscreen";
				  				
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
		return "changeCombo";
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1","HSTNUM_REQUEST_NO" ,"2","HSTDT_REQUEST_DATE"};
		return orderBy;
	}
	
	
}
