
package mms.transactions.controller.utl;

import hisglobal.hisconfig.Config;
import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;
/**
 * @author Amit Kumar
 * Date of Creation : 31/3/2009
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Store
 */

public class LocalPurchaseDeskUTL extends TransInterface {

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
		String masterName = "Local Purchase Desk";
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
	    String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();
	    mcu = new MmsConfigUtil(hosCode);
	    String[] strTemp1 = null;
	    String[] strTemp = null;
	    
	    if(cmb != null)
	    {
				
	    	/*if(cmb[1].equals("1"))
		    {*/
	    		brMainQuery.append("select hstnum_trans_no||'@'||hstnum_store_id||'@'||gnum_hospital_code||'^'||hstnum_trans_no||'^'||to_char(gdt_entry_date,'dd-Mon-yyyy')||'^'||mms_mst.get_supp_dtl(1,gnum_hospital_code,hstnum_supplier_id)||'^'||decode(nvl(hstnum_uc_flg,0),0,'No', 1 ,'Yes' ,'NA')||'^'||round(sum((hstnum_rate*(hstnum_tax+100)/100)*hstnum_lp_qty),2)  from hstt_lp_dtl where sstnum_item_cat_no = "+cmb[1]+" and gnum_hospital_code ="+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" and hstnum_store_id = "+cmb[0]+" group by hstnum_trans_no,hstnum_store_id,gnum_hospital_code,hstnum_supplier_id,sstnum_item_cat_no,gdt_entry_date,hstnum_uc_flg");
		    /*}
	    	else if(cmb[1].equals("2"))
	    	{
	    		brMainQuery.append("select hstnum_trans_no||'@'||hstnum_store_id||'@'||gnum_hospital_code||'^'||hstnum_trans_no||'^'||to_char(gdt_entry_date,'dd-Mon-yyyy')||'^'||mms_mst.get_supp_dtl(1,gnum_hospital_code,hstnum_supplier_id)||'^'||decode(nvl(hstnum_uc_flg,0),0,'No', 1 ,'Yes' ,'NA')||'^'||round(sum(hstnum_rate*hstnum_lp_qty),2)  from hstt_lp_dtl where sstnum_item_cat_no = 13 and gnum_hospital_code ="+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" and hstnum_store_id = "+cmb[0]+" group by hstnum_trans_no,hstnum_store_id,gnum_hospital_code,hstnum_supplier_id,sstnum_item_cat_no,gdt_entry_date,hstnum_uc_flg");
	    	}*/
	    
	    }
	    else
	    {
	    	brMainQuery.append("select hstnum_trans_no||'@'||hstnum_store_id||'@'||gnum_hospital_code||'^'||hstnum_trans_no||'^'||to_char(gdt_entry_date,'dd-Mon-yyyy')||'^'||mms_mst.get_supp_dtl(1,gnum_hospital_code,hstnum_supplier_id)||'^'||decode(nvl(hstnum_uc_flg,0),0,'No', 1 ,'Yes' ,'NA')||'^'||round(sum((hstnum_rate*(hstnum_tax+100)/100)*hstnum_lp_qty),2)   from hstt_lp_dtl where sstnum_item_cat_no = 10 and gnum_hospital_code ="+ httpSession.getAttribute("HOSPITAL_CODE").toString()+" group by hstnum_trans_no,hstnum_store_id,gnum_hospital_code,hstnum_supplier_id,sstnum_item_cat_no,gdt_entry_date,hstnum_uc_flg"); 
	    }

		mcu = null;
		
		System.out.println("LP desk Main Query-->>>>"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String[] getSearchField() 
	{
		String search_field[] = {"1", "hstnum_trans_no"};	   
		return search_field;
	}
	
	/**
	 * 0^0 0 Means Combo From Query,
	 * 1  Means User Defined Combo,0 After ^ Means Select Value,
	 * 1 Means All,2 Means Default Selected
	 * */

	public String[] getComboHeader() 
	{
	/*	String[] strComboHeader = new String[4];
		strComboHeader[0] = "0^0";
		strComboHeader[1] = "Store Name";
		strComboHeader[2] = "0^0";
		strComboHeader[3] = "Item Category";*/
		String[] strComboHeader = { "0^0", "Store Name", "0^0",
				"Item Category " };
		return strComboHeader;
		
	    //return strComboHeader;
	 
	}

	public String[] getColumnHeader() 
	{	
		String[] strColumnHeader = new String[5];
		strColumnHeader[0] = "LPO No";
		strColumnHeader[1] = "LP Date";
		strColumnHeader[2] = "Supplier";
		strColumnHeader[3] = "Whether UC";
		strColumnHeader[4] = "LP Value";
		//strColumnHeader[5] = "Supplier";
		return strColumnHeader;
		
		
	}      

	public String[] getComboQuery()
	{
		
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		
		
		String[] comboQuery = new String[2];
	    
	   
		comboQuery[0] = "select HSTNUM_STORE_ID,HSTSTR_STORE_NAME from HSTT_STORE_MST A where  GNUM_ISVALID = 1 and  GNUM_HOSPITAL_CODE ="+hosCode+
			" AND EXISTS"+
						   "("+
						    "SELECT 'X'"+
						    "FROM GBLT_ROLE_SEAT_TABLE_DTL P"+
						    " WHERE UPPER(gstr_table_name) = UPPER('HSTT_STORE_MST')"+
						    " AND UPPER(gstr_column_name) = UPPER('HSTNUM_STORE_ID') and gnum_module_id = 63 "+
						    " AND P.gnum_hospital_code = A.gnum_hospital_code AND gbl_isvalid = 1 "+
						  " AND gnum_column_value = A.HSTNUM_STORE_ID"+
						 " AND P.gnum_seatid = Pkg_Usermgmt.fun_getseatid("+httpSession.getAttribute("SEATID").toString()+				 
						 ",A.GNUM_HOSPITAL_CODE)"+")"+
						" ORDER BY INITCAP(HSTSTR_STORE_NAME)";


		System.out.println("comboQuery[0]:::   "+comboQuery[0]);
		
		String strHospitalCode = " "
				+ httpSession.getAttribute("HOSPITAL_CODE").toString() + " ";

		//comboQuery[1] = "1^Drug#2^Item";
		comboQuery[1] = "SELECT   SSTNUM_ITEM_CAT_NO, "
				+ "MMS_MST.GET_ITEMCAT_DTL (1, "
				+ "100, "
				+ "SSTNUM_ITEM_CAT_NO "
				+ ") AS CATNAME "
				+ "FROM HSTT_STORE_CATEGORY_MST A "
				+ "WHERE GNUM_ISVALID = 1 "
				+ "AND HSTNUM_STORE_ID = #1# "
				+ "  and SSTNUM_ITEM_CAT_NO IN (select SSTNUM_ITEM_CAT_NO from sstt_item_category_mst where gnum_hospital_code = 100 and gnum_isvalid=1) " // SSTNUM_ITEM_CAT_NO is 10
													// for Drug
				+ "AND GNUM_HOSPITAL_CODE = "
				+ strHospitalCode
				/*+ "AND EXISTS ( "
				+ "SELECT 'x' "
				+ "FROM HSTT_STORE_REQTYPE_MST "
				+ "WHERE HSTNUM_STORE_ID = A.HSTNUM_STORE_ID "
				+ "AND SSTNUM_ITEM_CAT_NO = A.SSTNUM_ITEM_CAT_NO "
				+ "AND SSTNUM_INDENTTYPE_ID = 57 " // SSTNUM_INDENTTYPE_ID is 57
													// for 'Item Inventory'
													// request
				+ "AND GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE) "*/
				+ "ORDER BY CATNAME";

		System.out.println("comboQuery[1]:::   "+comboQuery[1]);
		
		return comboQuery;
	}

	public String getViewQuery() {
		return ""; 
	}

	public String getButtons()
	{
		String strButtons = "";
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
		String files = "../../mms/js/LocalPurchase.js";
		return files;

	}

	public String[] getRowStatus()
	{
		String[] status = {};
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
				 strButtons[0] = "Generate@buttonLogicsOnClick1(1,'ADD','Add')@0@3b5998@glyphicon-plus";
				// strButtons[1] = "Modify@buttonLogicsOnClickCancel(2,'MODIFY','Remove')@1@bb0000@glyphicon-remove";
			  	 strButtons[1] = "View@buttonLogicsOnClickPrint(5,'PRINT','Print')@1@0c8d20@glyphicon-print";	
				
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
		//String orderBy[] = { "2", "gdt_entry_date", "1", "hstnum_trans_no" };
		String orderBy[] = {"1", "hstnum_trans_no" };
		return orderBy;
	}
	@Override
	public String[] getButtonIcons() {
	//	String[] str={"GenerateOnDesk.png","CancelOnDesk.png","ReturnOnDesk.png","ViewOnDesk.png","PrintOnDesk.png","icon-default.png"};
		String[] str={"GenerateOnDesk.png","ViewOnDesk.png"};
		// TODO Auto-generated method stub
		return str;
	}
	

}
