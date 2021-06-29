package bmed.transactions.controller.config;

import hisglobal.hisconfig.Config;
import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import bmed.global.controller.util.BmedConfigUtil;

public class HemDeskCONFIG extends TransInterface {

	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	String[] cmbVal = null;
	

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}

	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	}

	public String getMasterName() {
		String masterName = "HEM Desk";
		return masterName;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[])
	{		
		 BmedConfigUtil bmed =  new BmedConfigUtil();
		 String strAmcComp = bmed.getStrAmcComplaint("14" , Config.SUPER_USER_HOSPITAL_CODE);
		 //String strCondition = " AND b.HEMNUM_MAIN_STATUS = ?";
		 StringBuffer brMainQuery = new StringBuffer(1000);
		 	    	    
		 brMainQuery.append(" SELECT    B.HEMNUM_REQ_ID || '@'|| B.GNUM_HOSPITAL_CODE||'@'||"+strAmcComp+"||'@'||B.HEMNUM_REQ_TYPE||'@'||B.HEMNUM_DEPT_ID ||'@'||B.HEMNUM_STORE_ID"); 
		 
		 
		 brMainQuery.append(" ||'^'|| B.HEMNUM_REQ_ID ");
		 brMainQuery.append(" ||'^'|| NVL ((SELECT GSTR_DEPT_NAME FROM GBLT_DEPARTMENT_MST Q  WHERE  Q.GNUM_ISVALID = '1' "	
				 +"AND Q.GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE	AND Q.GNUM_DEPT_CODE = B.HEMNUM_DEPT_ID),'---')");
		 brMainQuery.append(" ||'^'||NVL ((SELECT HSTSTR_STORE_NAME FROM HSTT_STORE_MST I WHERE I.GNUM_ISVALID = '1' "
				 +" AND I.GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE	AND I.HSTNUM_STORE_ID = B.HEMNUM_STORE_ID ),'---') ");
		 brMainQuery.append( "||'^'||TO_CHAR (B.HEMNUM_REQ_DATE, 'DD-Mon-YYYY') ");
		 brMainQuery.append(" ||'^'|| NVL ((SELECT hemstr_main_status_name ||'/'|| hemstr_sub_status_name ");
		 brMainQuery.append("  FROM semt_complaint_status_mst ");
		 brMainQuery.append(" WHERE hemnum_main_status_id = b.hemnum_main_status ");
		 brMainQuery.append(" AND hemnum_sub_status_id = b.hemnum_sub_status ");
		 brMainQuery.append(" AND hemnum_is_internal = b.hemnum_req_type ");
		 brMainQuery.append(" AND gnum_hospital_code = 101 ");  //AND gnum_hospital_code = b.gnum_hospital_code
		 brMainQuery.append(" AND gnum_isvalid = 1), '---' ) ");
		 brMainQuery.append(" ||'^'|| AHIS_FUNCTION.fun_emp_name (HEMSTR_EMP_ID,B.gnum_hospital_code) ");
		 brMainQuery.append(" ||'^'|| NVL ((SELECT hststr_item_name ");
		 brMainQuery.append(" FROM hstt_itembrand_mst c ");
		 brMainQuery.append(" WHERE c.hstnum_itembrand_id = b.hemnum_item_id ");
		 brMainQuery.append(" AND c.gnum_hospital_code = 100 ");
		 brMainQuery.append(" AND c.gnum_isvalid = 1),'---') "); 
		 brMainQuery.append(" ||'^'|| NVL ((Select HEMNUM_REMINDER_STATUS from  HEMT_REMINDER_DTL d "); 
		 brMainQuery.append(" WHERE d.gnum_hospital_code = b.gnum_hospital_code ");
		 brMainQuery.append(" AND d.HEMNUM_REQ_ID      = b.HEMNUM_REQ_ID ");
		 brMainQuery.append(" AND d.gnum_isvalid = 1 AND d.HEMNUM_REMINDER_ID = (select MAX(HEMNUM_REMINDER_ID)from hemt_reminder_dtl )),'0') ");  
		 brMainQuery.append(" ||'^'|| HEMNUM_IS_WORKING  AS DATA FROM HEMT_COMPLAINT_REQUEST_DTL b ");
		 brMainQuery.append(" WHERE ");
		 brMainQuery.append(" B.GNUM_HOSPITAL_CODE = ");
		 brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
		 if (cmb != null) 
		 { 							
				if (!cmb[0].equals("0")) 
				{					
					brMainQuery.append("  AND B.HEMNUM_ENGG_ITEM_TYPE_ID = ");
					brMainQuery.append(cmb[0]);					
					
					/*if (!cmb[2].equals("0")) 
					{
					  brMainQuery.append(strCondition.replace("?",cmb[2]));
					} 
					else
					{
					  brMainQuery.append(strCondition.replace("?","1"));
					}		*/				
								
				}
				if (!cmb[1].equals("0")) 
				{					
					brMainQuery.append("  AND B.HEMNUM_ENGG_ITEM_SUB_TYPE_ID = ");
					brMainQuery.append(cmb[1]);					
					/*if (!cmb[2].equals("0")) 
					{
					  brMainQuery.append(strCondition.replace("?",cmb[2]));
					} 
					else
					{
					  brMainQuery.append(strCondition.replace("?","1"));
					}	*/
					
				}
				if(strAmcComp.equals("1"))
				{
					if (!cmb[2].equals("0")) 
					{					
						brMainQuery.append("  AND B.HEMNUM_REQ_TYPE = ");
						brMainQuery.append(cmb[2]);
					}
					if (!cmb[3].equals("0")) 
					{					
						brMainQuery.append("  AND b.HEMNUM_MAIN_STATUS = ");
						brMainQuery.append(cmb[3]);
					}
				}
				else
				{
					
					if (!cmb[2].equals("0")) 
					{					
						brMainQuery.append(" AND b.HEMNUM_MAIN_STATUS = ");
						brMainQuery.append(cmb[2]);
					}
				}	
				
				brMainQuery.append(" AND gnum_isvalid = 1 ");
		 }
		 /*
		 else
		 {
			brMainQuery.append(" AND gnum_isvalid = 2 ");
		 }		 
		 */
		 brMainQuery.append(" AND EXISTS (SELECT 'X'   FROM HEMT_COMPLAINT_APPROVAL_DTL p  WHERE p.gnum_hospital_code = b.gnum_hospital_code "); 
		 brMainQuery.append(" AND p.HEMNUM_REQ_ID = b.hemnum_req_id ");
		 brMainQuery.append(" AND p.HEMNUM_APPROVAL_STATUS IN (1) AND p.GNUM_ISVALID = 1 )");
		 

		System.out.println("hem desk Main Query-->>>>"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String[] getSearchField() 
	{
		String search_field[] = {"1", "B.HEMNUM_REQ_ID"};
		return search_field;
	}
	
	/**
	 * 0^0 
	 * 0 Means Combo From Query,
	 * 1 Means User Defined Combo,0 After ^ Means Select Value,
	 * 1 Means All,2 Means Default Selected
	 * */

	public String[] getComboHeader() 
	{
		BmedConfigUtil bmed =  new BmedConfigUtil();
		String strAmcComp = bmed.getStrAmcComplaint("14" , httpSession.getAttribute("HOSPITAL_CODE").toString());
		if(strAmcComp.equals("1"))
		{	
		  String[] strComboHeader = {"0^2","Engineering Item Type","0^0","Engineering Item Sub Type","1","Complaint Type","0^0","Status"};
		  return strComboHeader;
		}
		else
		{
			String[] strComboHeader = {"0^0","Engineering Item Type","0^1","Engineering Item Sub Type","0^0","Status"};
			return strComboHeader;
		}
	   
	 
	}

	public String[] getColumnHeader() 
	{
		
		String[] strColumnHeader = { "Complaint Id","Department Name","Store Name","Complaint Date", "Complaint Status", "Complaint By","Item Name"};
				
		return strColumnHeader;
		
		
	}      

	public String[] getComboQuery()
	{
		BmedConfigUtil bmed =  new BmedConfigUtil();
		
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();

		String strAmcComp = bmed.getStrAmcComplaint("14" , Config.SUPER_USER_HOSPITAL_CODE);
		
		String[] comboQuery = new String[3];
	    
	   
		comboQuery[0] = " SELECT HEMNUM_ENGG_ITEM_TYPE_ID,HEMSTR_ENGG_ITEM_TYPE_NAME from SEMT_ENGG_ITEM_TYPE_MST A WHERE GNUM_ISVALID =1 AND GNUM_HOSPITAL_CODE="+Config.SUPER_USER_HOSPITAL_CODE+" ORDER BY UPPER(HEMSTR_ENGG_ITEM_TYPE_NAME)";
		
		comboQuery[1] = " SELECT HEMNUM_ENGG_ITEM_SUB_TYPE_ID,HEMSTR_ENGG_ITEM_SUB_TYPE_NAME   FROM HEMT_ENGG_ITEM_SUB_TYPE_MST  WHERE  GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE ="+hosCode+" " +
				        " AND  HEMNUM_ENGG_ITEM_TYPE_ID = #1# ORDER BY UPPER(HEMSTR_ENGG_ITEM_SUB_TYPE_NAME)";

		/*if(strAmcComp.equals("1"))
		{
		  comboQuery[2] = "0^Internal#1^External";
		  comboQuery[3] = "SELECT DISTINCT HEMNUM_MAIN_STATUS_ID,HEMSTR_MAIN_STATUS_NAME from SEMT_COMPLAINT_STATUS_MST  WHERE HEMNUM_MAIN_STATUS_ID!=1 AND GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE ="+101+" ORDER BY UPPER(HEMNUM_MAIN_STATUS_ID)";
		} 
		else
		{
		  comboQuery[2] = "SELECT DISTINCT HEMNUM_MAIN_STATUS_ID,HEMSTR_MAIN_STATUS_NAME from SEMT_COMPLAINT_STATUS_MST  WHERE HEMNUM_MAIN_STATUS_ID!=1 AND GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE ="+101+" ORDER BY UPPER(HEMNUM_MAIN_STATUS_ID)";
		}	
		*/
		comboQuery[2] ="SELECT DISTINCT HEMNUM_MAIN_STATUS_ID,HEMSTR_MAIN_STATUS_NAME from SEMT_COMPLAINT_STATUS_MST  WHERE HEMNUM_MAIN_STATUS_ID!=1 AND GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE =100 ORDER BY UPPER(HEMNUM_MAIN_STATUS_ID)";
		
					
		return comboQuery;
	}

	public String getViewQuery() {
		return "";
	}

	public String getButtons()
	{
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
		List<String> deleteData = new ArrayList<String>();

		return deleteData;
	}

	public String getJsFiles() {
		String files = "/HBIMS/bmed/transactions/js/bmed_HemDesk_trans.js";
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
		
      	String[] status = {"1", "6", "56B575", "<b>Not Working</b>"};
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
			     strButtons = new String[6];
			    
				 strButtons[0] = "Schedule@buttonLogicsOnClick1(1,'SCHEDULE','Add')@1";
				 strButtons[1] = "Attend@buttonLogicsOnClick1(2,'ATTEND','Remove')@1";
			  	 strButtons[2] = "Grievances@buttonLogicsOnClick1(3,'GRIEVANCES','Return')@0";
			  	 strButtons[3] = "Reminder Reply@buttonLogicsOnClick1(4,'initializeReminderReply','View')@1";
			  	 strButtons[4] = "Close@buttonLogicsOnClick1(5,'initializeClose','Print')@1";		
			  	 strButtons[5] = "View@buttonLogicsOnClick1(5,'initializeView','Print')@1";	
				
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

	public String[] getOrderBy() {
		String orderBy[] = { "1", "B.HEMNUM_REQ_ID" };
		return orderBy;
	}
}