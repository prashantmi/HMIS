package bmed.transactions.controller.config;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class HemComplaintApprovalDeskCONFIG extends TransInterface {

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
		String masterName = "HEM Complaint Approval";
		return masterName;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[])
	{		
		
		 StringBuffer brMainQuery = new StringBuffer(1000);
		 brMainQuery.append(" SELECT  B.hemnum_req_id||'@'||B.HEMNUM_SLNO ||'@'|| B.gnum_hospital_code");
		 brMainQuery.append(" ||'@'|| (SELECT hemnum_warranty_slno from  HEMT_COMPLAINT_REQUEST_DTL e ");
		 brMainQuery.append(" where e.gnum_isvalid = 1 ");
		 brMainQuery.append(" AND  e.hemnum_req_id=B.hemnum_req_id) ||'@'||B.hemnum_dept_id ");		 
		 brMainQuery.append(" ||'^'|| B.hemnum_req_id ");
		 brMainQuery.append(" ||'^'||NVL ((SELECT GSTR_DEPT_NAME FROM GBLT_DEPARTMENT_MST Q WHERE Q.GNUM_DEPT_CODE = B.HEMNUM_DEPT_ID "				 
				 +" AND Q.GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE	AND Q.GNUM_ISVALID = '1'),'---')");
		 brMainQuery.append(" ||'^'|| NVL ((SELECT HSTSTR_STORE_NAME FROM HSTT_STORE_MST I  WHERE I.GNUM_ISVALID = '1' AND I.GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE "
				 	+" AND I.HSTNUM_STORE_ID = (select C.HEMNUM_STORE_ID from HEMT_COMPLAINT_REQUEST_DTL C "
				 	+ " WHERE C.GNUM_ISVALID = '1'	AND C.GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE	AND C.HEMNUM_DEPT_ID = B.hemnum_dept_id And rownum=1)),'---')");
		 brMainQuery.append("||'^'||TO_CHAR (B.GDT_ENTRY_DATE, 'DD-Mon-YYYY')");
		 brMainQuery.append(" ||'^'|| DECODE(B.HEMNUM_REQ_TYPE,'1','Internal','2','External')");
		 brMainQuery.append(" ||'^'|| ahis_function.fun_emp_name (B.hemstr_emp_id, B.gnum_hospital_code)");
		 brMainQuery.append(" ||'^'|| NVL ((SELECT hststr_item_name  FROM hstt_itembrand_mst c ");
		 brMainQuery.append(" WHERE c.hstnum_itembrand_id = (SELECT HEMNUM_ITEM_ID from  HEMT_COMPLAINT_REQUEST_DTL e ");
		 brMainQuery.append(" where e.HEMNUM_REQ_ID = b.hemnum_req_id AND e.GNUM_HOSPITAL_CODE =b.gnum_hospital_code)");
		 brMainQuery.append(" AND c.gnum_hospital_code = 100 ");
		 brMainQuery.append(" AND c.gnum_isvalid = 1),'---')AS DATA");
		 brMainQuery.append(" FROM HEMT_COMPLAINT_APPROVAL_DTL b");
		 brMainQuery.append(" WHERE ");
		 brMainQuery.append(" b.GNUM_HOSPITAL_CODE = ");
		 brMainQuery.append(  httpSession.getAttribute("HOSPITAL_CODE").toString());
		 brMainQuery.append(" AND B.gnum_isvalid = 1");
		 if (cmb != null) 
		 { 							
				if (!cmb[0].equals("1")) 
				{					
					brMainQuery.append(" AND B.HEMNUM_DEPT_ID = ");
					brMainQuery.append(cmb[0]);
				}
				if (!cmb[1].equals("0")) 
				{					
					brMainQuery.append(" AND B.HEMNUM_APPROVAL_STATUS = ");
					brMainQuery.append(cmb[1]);
				}
				else
				{
					brMainQuery.append(" AND B.HEMNUM_APPROVAL_STATUS = 0");
										
				}	
		 }
			 
		System.out.println("Main Query:::"+brMainQuery.toString()); 
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
		
		
			String[] strComboHeader = {"0^1","Department Name","1","Status"};
			return strComboHeader;
		
	   
	 
	}

	public String[] getColumnHeader() 
	{
		
		String[] strColumnHeader = { "Complaint Id","Department Name","Store Name","Complaint Date", "Complaint Type","Employee Name","Item Name"};
				
		return strColumnHeader;
		
		
	}      

	public String[] getComboQuery()
	{
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		String seatid = httpSession.getAttribute("SEATID").toString();
		String[] comboQuery = new String[2];
		comboQuery[0] = " SELECT DISTINCT GNUM_DEPT_CODE,INITCAP(GSTR_DEPT_NAME)  from GBLT_DEPARTMENT_MST  A WHERE GNUM_ISVALID =1" +
								" AND GNUM_HOSPITAL_CODE="+hosCode+
								//" ORDER BY UPPER(A.GSTR_DEPT_NAME) "
								//+ "--AND TRUNC(GDT_EFFECTIVE_FRM) <= TRUNC(SYSDATE) "	+
								"AND EXISTS	(	"	+
								" SELECT 'X'	"	+    
								" FROM GBLT_ROLE_SEAT_TABLE_DTL P,   GBLT_METATABLE_COLUMN_MST q	"	+
								" WHERE P.gnum_metatable_id = q.gnum_metatable_id	"	+
								//" AND P.gnum_hospital_code = q.gnum_hospital_code	"	+
								" AND UPPER(P.gstr_table_name) = UPPER('GBLT_DEPARTMENT_MST')	"	+
								" AND UPPER(P.gstr_column_name) = UPPER('GNUM_DEPT_CODE')	"	+
								" AND P.gnum_hospital_code = A.gnum_hospital_code	"	+
								" AND gnum_column_value = A.GNUM_DEPT_CODE	"	+
								" AND P.gnum_module_id = 39 "+
								" AND P.gnum_seatid = Pkg_Usermgmt.fun_getseatid("+seatid+",A.GNUM_HOSPITAL_CODE)	"	+                            
								" ) ";
		comboQuery[1] = "0^Pending#1^Approved#2^Rejected#3^Expired";
		
		System.out.println("comboQuery=================[0]="+comboQuery[0]);
		
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
		String files = "/HBIMS/bmed/transactions/js/bmed_HemComplaintApp_Desk_trans.js";
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
		
      	String[] status = {"1", "3", "56B575", ""};
		return status;
	}
	public String getEventState() 
	{
	//	String strEvent = "buttonLogicsOnRecordCheck";
		String strEvent = "chkUserDefinedFunc";
		return strEvent;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	public String[] getUserDefinedButtons() 
	{				
		         String[] strButtons = null; 
			     strButtons = new String[2];
			    
				 strButtons[0] = "Verify@buttonLogicsOnClick1(1,'VERIFY','Add')@0";
				 strButtons[1] = "View@buttonLogicsOnClick1(5,'VIEW','Print')@0";	
				
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
		String orderBy[] = { "1", "B.HEMNUM_REQ_ID" ,"4","B.GDT_ENTRY_DATE"};
		return orderBy;
	}
}