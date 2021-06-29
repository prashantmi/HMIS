package mms.transactions.controller.utl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;

import hisglobal.transactionutil.TransInterface;

/**
 * 
 * @author Tanvi Sappal
 *
 */
public class BillApprovalDeskTransUTL extends TransInterface {

	private static final long serialVersionUID = 02L;
	HttpSession httpSession = null;
	String[] cmbVal = null;

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}

	public void setCombo(String[] cmb) {
		this.cmbVal = cmb;
	}

	public String getMasterName() {
		String masterName = "Bill Approval/Verification Desk";
		return masterName;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}
	public String getMainQuery(String cmb[]) {
		StringBuffer brMainQuery = new StringBuffer(500);
		//String strHospitalCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		 MmsConfigUtil mmsConfig = null;
		if (cmb != null) 
		{
			mmsConfig = new MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());
			brMainQuery.append(" (SELECT  C.HSTNUM_STORE_ID || '@' || C.HSTNUM_PO_NO  || '@' || C.HSTNUM_INVOICE_NO ");
			brMainQuery.append(" || '@' || C.HSTNUM_SUPPLIER_ID || '@' || C.HSTNUM_INVOICE_STATUS ||'@'|| HSTNUM_CURRENCY_ID || '@' || C.HSTNUM_PO_STOREID ||'^'|| C.HSTNUM_INVOICE_NO ||'^'|| C.HSTNUM_INVOICE_DATE ");
			brMainQuery.append(" || '^' || C.HSTNUM_PO_NO || '^' ||");
			brMainQuery.append(" Mms_Mst.get_supp_dtl(1,C.GNUM_HOSPITAL_CODE,C.HSTNUM_SUPPLIER_ID)  || '^ '|| C.HSTNUM_SUPP_INVOICE_AMT || '^' || C.HSTNUM_CALCULATED_COST AS DATA");
			brMainQuery.append(" FROM HSTT_INVOICE_DTL C WHERE C.GNUM_HOSPITAL_CODE=");
			brMainQuery.append(  httpSession.getAttribute("HOSPITAL_CODE").toString());
			brMainQuery.append(" AND GNUM_ISVALID=1");
			//System.out.println("(cmb[0].Value->"+cmb[0]);
			//System.out.println("(cmb[1].Value->"+cmb[1]);
			//System.out.println("(cmb[2].Value->"+cmb[2]);
			brMainQuery.append(" AND HSTNUM_STORE_ID="+cmb[0]);
			brMainQuery.append(" AND HSTNUM_INVOICETYPE_ID="+cmb[1]);
			brMainQuery.append(" AND HSTNUM_INVOICE_STATUS="+cmb[2]);
			brMainQuery.append(" AND HSTNUM_INVOICE_DATE BETWEEN  TO_DATE('"+mmsConfig.getStrFinancialStartDate(cmb[0] , httpSession.getAttribute("HOSPITAL_CODE").toString() )+"' )   AND  TO_DATE('"+mmsConfig.getStrFinancialEndDate(cmb[0] , httpSession.getAttribute("HOSPITAL_CODE").toString() )+"'))");
		}
		else
		{
			brMainQuery.append(" (SELECT  C.HSTNUM_STORE_ID || '@' || C.HSTNUM_PO_NO  || '@' || C.HSTNUM_INVOICE_NO ");
			brMainQuery.append(" || '@' || C.HSTNUM_SUPPLIER_ID || '@' || C.HSTNUM_INVOICE_STATUS ||'@'|| HSTNUM_CURRENCY_ID || '@' || C.HSTNUM_PO_STOREID ||'^'|| C.HSTNUM_INVOICE_NO ||'^'|| C.HSTNUM_INVOICE_DATE ");
			brMainQuery.append(" || '^' || C.HSTNUM_PO_NO || '^' ||");
			brMainQuery.append(" Mms_Mst.get_supp_dtl(1,C.GNUM_HOSPITAL_CODE,C.HSTNUM_SUPPLIER_ID)  || '^ '|| C.HSTNUM_SUPP_INVOICE_AMT || '^' || C.HSTNUM_CALCULATED_COST AS DATA");
			brMainQuery.append(" FROM HSTT_INVOICE_DTL C WHERE C.GNUM_HOSPITAL_CODE=");
			brMainQuery.append(  httpSession.getAttribute("HOSPITAL_CODE").toString());
			brMainQuery.append(" AND GNUM_ISVALID=1");
			brMainQuery.append(" AND HSTNUM_STORE_ID=0)");
		}
		
	    //System.out.println("Main Query-->>"+brMainQuery.toString());
		return brMainQuery.toString();

	}

	public String[] getSearchField() 
	{
		String search_field[] = {"1", "A.HSTNUM_INVOICE_NO"};
		return search_field;
	}
	/**
	 * 0^0 0 Means Combo From Query,
	 * 1  Means User Defined Combo,0 After ^ Means Select Value,
	 * 1 Means All,2 Means Default Selected
	 * */
	public String[] getComboHeader() 
	{		
			String[] strComboHeader = new String[6];		
			strComboHeader[0] = "0^2";
			strComboHeader[1] = "Department/Store Name";
			strComboHeader[2] = "0^0";
			strComboHeader[3] = "Bill Type";
			strComboHeader[4] = "1";
			strComboHeader[5] = "Status";
	    return strComboHeader;
	}

	public String[] getColumnHeader() 
	{
		String[] strColumnHeader = { "Invoice No." , "Invoice Date", "PO No." , "Supplier Name" , "Bill Amount" , "Calculated Amount" };
		return strColumnHeader;
	}      

	public String[] getComboQuery() 
	{
		String[] comboQuery = new String[3];
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		comboQuery[0] = "select HSTNUM_STORE_ID,HSTSTR_STORE_NAME from HSTT_STORE_MST A where GNUM_ISVALID = 1" +
				        "and  GNUM_HOSPITAL_CODE ="+hosCode +
				        /*" AND EXISTS"+
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
						")
						+ "*/
						"ORDER BY INITCAP(HSTSTR_STORE_NAME)";
		
		comboQuery[1] = "SELECT HSTNUM_INVOICETYPE_ID ,HSTNUM_INVOICETYPE_NAME  FROM HSTT_INVOICETYPE_MST WHERE GNUM_ISVALID = 1 AND  GNUM_HOSPITAL_CODE ="+MmsConfigUtil.GLOBAL_HOSPITAL_CODE+" ORDER BY HSTNUM_INVOICETYPE_NAME";
		comboQuery[2] = "1^Verified#2^Dispatched";
		return comboQuery;
	}

	public String getViewQuery() {
		return "";
	}

	public String getButtons() {
		String strButtons = "";
		//"<a style=cursor:pointer><img src='../../hisglobal/images/btn-ccl.png'  border=0 tabindex='1' onKeyPress='cancelPage();' onClick='cancelPage();' ></a>";
		return strButtons;
	}

	public String[] getDeleteQuery() {
		String deleteQuery[] = new String[1];
		deleteQuery[0] = "";

		return deleteQuery;
	}

	/*public List getDeleteData(String chk) {
		String a[] = null;
		String b[] = null;
		String key[] = new String[1];

		List deleteData = new ArrayList();
		a = (chk.replace('|', '#')).split("#");
		b = (a[0].replace('@', '#')).split("#");

		key[0] = b[0];
		System.out.print("key[0] = " + key[0]);
		deleteData.add(key);
		return deleteData;
	}*/
	
	public List<String> getDeleteData(String chk) {
		List<String> deleteData = new ArrayList<String>();

		return deleteData;
	}

	public String getJsFiles() {
		String files = "../../mms/js/bill_approval_desk_trans.js";
		return files;

	}

	public String[] getRowStatus() {
		String[] status = {"Autrin Cap", "2", "brown", "Expired"};
		return status;
	}

	public String getEventState() {
		String str = "";
		return str;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	public String[] getUserDefinedButtons() {
		
		String[] strButtons = {"Verify@validateBillApproval(document.forms[0],'BILL')@0@00aced@glyphicon-check",
				"View@validateBillApproval(document.forms[0],'VIEW')@1@007bb6@glyphicon-fullscreen",
				"Print@validateBillApproval(document.forms[0],'PRINT')@1@0c8d20@glyphicon-print"};
		return strButtons;
	}

	public String[] getDbButtons() {
		//String[] str = { "1" };
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
		return "comboChangeStatus";
	}

	public String[] getOrderBy() {
		String orderBy[] = { "1", "C.HSTNUM_INVOICE_NO"};
		return orderBy;
	}

}
