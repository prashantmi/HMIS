package dossier.transaction.controller.util;

import hisglobal.transactionutil.TransInterface;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import mms.MmsConfigUtil;

public class LPIssueDeskTransUTL extends TransInterface {

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
		String masterName = "Dossier Issue-Return Desk";
		return masterName;
	}

	public List<String> getViewHeader() {

		List<String> viewHdr = new ArrayList<String>();
		return viewHdr;
	}

	public String getMainQuery(String cmb[]) 
	{
		StringBuffer brMainQuery = new StringBuffer(500);
		String strReqId="";
  // added by shefali garg 1-jan-2015 & changed by shalini on 13-01-2015 for item category and hardcoded hospital code
		brMainQuery.append("select "+
			" hnum_dossier_req_id||'@'||gnum_hospital_code||'@'||hrgnum_puk||'@'||hnum_req_mode||'@'|| "+
			" hnum_ref_req_id||'@'||hnum_servicetype_id||'@'||hnum_service_id||'@'||hstr_service_name||'@'|| "+
			" hnum_dossier_id||'@'||hnum_dossier_status||'@'||hstr_entry_remark||'@'||hstr_post_remark||'@'||  "+
			" hnum_cancel_reason_id||'@'|| hstr_cancel_remark||'@'|| gnum_isvalid||'@'|| gdt_entry_date||'@'|| "+
			" gnum_seat_id ||'@'|| nvl(hstnum_patient_type,0)  ||'^'|| dossier_mst.get_service_name(hnum_dossier_id::numeric, gnum_hospital_code::numeric, hnum_servicetype_id::numeric)||'^'|| "+
			" Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'||HRGNUM_PUK  ||'^'||to_char(gdt_entry_date ,'DD-MON-YYYY') ||'^'|| hnum_dossier_req_id from hgdt_dossier_req_dtl A where gnum_hospital_code="+httpSession.getAttribute("HOSPITAL_CODE").toString()+"  and gnum_isvalid=1 and decode(hstnum_patient_type,1,hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1 AND (SELECT count(*) FROM HBLT_PATACCOUNT_DTL B WHERE GNUM_ISVALID = 1 AND HBLNUM_pataccount_status in (1,2) AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND B.HRGNUM_PUK= A.HRGNUM_PUK AND B.HASTR_ADM_NO = A.HIPNUM_ADMNO)>0  ORDER BY HIPDT_ADMDATETIME  DESC),hrgnum_puk = hrgnum_puk) " );
		
		
		if(cmb!=null)
		{
			if(cmb[2]!=null && cmb[2].equalsIgnoreCase("1")){
				brMainQuery.delete(0, brMainQuery.length());
				brMainQuery.append(" select "+
			" hnum_dossier_req_id||'@'||gnum_hospital_code||'@'||hrgnum_puk||'@'||hnum_req_mode||'@'|| "+
			" hnum_ref_req_id||'@'||hnum_servicetype_id||'@'||hnum_service_id||'@'||hstr_service_name||'@'|| "+
			" hnum_dossier_id||'@'||hnum_dossier_status||'@'||hstr_entry_remark||'@'||hstr_post_remark||'@'||  "+
			" hnum_cancel_reason_id||'@'|| hstr_cancel_remark||'@'|| gnum_isvalid||'@'|| gdt_entry_date||'@'|| "+
			" gnum_seat_id  ||'@'|| nvl(hstnum_patient_type,0) ||'@'||  dossier_mst.get_dossier_sort_name(hnum_dossier_id::numeric, gnum_hospital_code::numeric, hnum_servicetype_id::numeric)  ||'^'|| dossier_mst.get_service_name(hnum_dossier_id::numeric, gnum_hospital_code::numeric, hnum_servicetype_id::numeric)||'^'|| "+
			" Ahis_Function.FUN_PAT_NAME (HRGNUM_PUK)||'^'|| A.HRGNUM_PUK  ||'^'||to_char(A.gdt_entry_date ,'DD-MON-YYYY') ||'^'|| hnum_dossier_req_id  ||'^'|| nvl(hstnum_patient_type,0) from hgdt_dossier_req_dtl  A  where gnum_hospital_code="+httpSession.getAttribute("HOSPITAL_CODE").toString()+"  and gnum_isvalid=1 and hnum_dossier_status = 1 and decode(hstnum_patient_type,1,hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1 AND (SELECT count(*) FROM HBLT_PATACCOUNT_DTL B WHERE GNUM_ISVALID = 1 AND HBLNUM_pataccount_status in (1,2) AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND B.HRGNUM_PUK= A.HRGNUM_PUK AND B.HASTR_ADM_NO = A.HIPNUM_ADMNO)>0  ORDER BY HIPDT_ADMDATETIME  DESC),hrgnum_puk = hrgnum_puk) and hnum_tostore_id="+cmb[0] );
			}
			if(cmb[2]!=null){
				if(cmb[1].equals("98") && cmb[2].equalsIgnoreCase("1") && cmb[2].equalsIgnoreCase("4"))
				{
				brMainQuery.delete(0, brMainQuery.length());
				brMainQuery.append(" select A.hrgnum_puk||'@'|| hstnum_issue_no||'@'|| hnum_dossier_req_id||'@'||A.gnum_hospital_code||'@'|| A.hrgnum_puk||'@'||hnum_req_mode||'@'|| to_char(hstdt_issue_date ,'DD-MON-YYYY') || '@' ||  hnum_ref_req_id||'@'|| "+
									" hnum_servicetype_id||'@'||hnum_service_id||'@'||hstr_service_name||'@'||  hnum_dossier_id||'@'||hnum_dossier_status||'@'||"+
									" hstr_entry_remark||'@'||hstr_post_remark||'@'||   hnum_cancel_reason_id||'@'|| hstr_cancel_remark||'@'|| A.gnum_isvalid||'@'||"+ 
									" A.gdt_entry_date||'@'||  gnum_seat_id  ||'@'|| nvl(hstnum_patient_type,0) ||'^'|| dossier_mst.get_service_name(hnum_dossier_id::numeric, "+
									" A.gnum_hospital_code::numeric, hnum_servicetype_id::numeric)||'^'||  "+
									" Ahis_Function.FUN_PAT_NAME (A.HRGNUM_PUK)||'^'||"+
									" A.HRGNUM_PUK  ||'^'||to_char(A.gdt_entry_date ,'DD-MON-YYYY') ||'^'|| hnum_dossier_req_id  ||'^'|| nvl(hstnum_patient_type,0) from hgdt_dossier_req_dtl A ,SSTT_PATEMP_ISSUE_DTL b where  A.gnum_hospital_code=b.gnum_hospital_code AND "+
									" A.gnum_hospital_code="+httpSession.getAttribute("HOSPITAL_CODE").toString()+"  AND hnum_dossier_req_id=HSTNUM_REQ_NO "+
									" AND A.gnum_isvalid=b.gnum_isvalid "+
									" and A.gnum_isvalid=1 and hnum_dossier_status = 2 AND SSTNUM_REQTYPE_ID=98 and decode(hstnum_patient_type,1,a.hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1 AND (SELECT count(*) FROM HBLT_PATACCOUNT_DTL B WHERE GNUM_ISVALID = 1 AND HBLNUM_pataccount_status in (1,2) AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND B.HRGNUM_PUK= A.HRGNUM_PUK AND B.HASTR_ADM_NO = A.HIPNUM_ADMNO)>0  ORDER BY HIPDT_ADMDATETIME  DESC),a.hrgnum_puk = a.hrgnum_puk) and hstnum_store_id="+cmb[0]);
				}
				if(cmb[1].equals("99"))
				{
					if(cmb[2].equalsIgnoreCase("1"))
					{
					brMainQuery.delete(0, brMainQuery.length());
					brMainQuery.append(" select A.hrgnum_puk||'@'|| a.hstnum_issue_no||'@'|| hnum_dossier_req_id||'@'||A.gnum_hospital_code||'@'|| A.hrgnum_puk||'@'|| "+
					" hnum_req_mode||'@'|| to_char(hstdt_issue_date ,'DD-MON-YYYY') || '@' ||  hnum_ref_req_id||'@'||  hnum_servicetype_id||'@'|| "+
					" hnum_service_id||'@'||hstr_service_name||'@'||  hnum_dossier_id||'@'||hnum_dossier_status||'@'|| hstr_entry_remark||'@'||hstr_post_remark||'@'|| "+  
					" hnum_cancel_reason_id||'@'|| hstr_cancel_remark||'@'|| A.gnum_isvalid||'@'|| A.gdt_entry_date||'@'||mms_mst.get_store_dtl(1::numeric, a.gnum_hospital_code::numeric, hnum_tostore_id::numeric) ||'@'||  dossier_mst.get_dossier_sort_name(hnum_dossier_id::numeric, a.gnum_hospital_code::numeric, hnum_service_id::numeric) || '@'||  hstnum_req_no || '@' || hstnum_patient_type ||'^'||  "+
					" dossier_mst.get_service_name(hnum_dossier_id::numeric,  A.gnum_hospital_code::numeric, hnum_servicetype_id::numeric)||'^'|| "+
					 " Ahis_Function.FUN_PAT_NAME (A.HRGNUM_PUK)||'^'|| A.HRGNUM_PUK  ||'^'||to_char(A.gdt_entry_date ,'DD-MON-YYYY') ||'^'|| "+
					" hnum_dossier_req_id || '^'|| dossier_mst.get_dossier_sort_name(hnum_dossier_id::numeric, a.gnum_hospital_code::numeric, hnum_service_id::numeric)  from hgdt_dossier_return_dtl a , sstt_issue_dtl b  where  A.gnum_hospital_code=b.gnum_hospital_code AND  A.gnum_hospital_code="+httpSession.getAttribute("HOSPITAL_CODE").toString()+" "+ 
					" and a.HSTNUM_ISSUE_NO=b.HSTNUM_ISSUE_NO "+
					" AND A.gnum_isvalid=b.gnum_isvalid  "+
					 " and A.gnum_isvalid=1 "+
					" and hnum_dossier_status = 3 and decode(hipnum_adm_no,0,a.hrgnum_puk = b.hrgnum_puk,a.hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1 AND (SELECT count(*) FROM HBLT_PATACCOUNT_DTL B WHERE GNUM_ISVALID = 1 AND HBLNUM_pataccount_status in (1,2) AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND B.HRGNUM_PUK= A.HRGNUM_PUK AND B.HASTR_ADM_NO = A.HIPNUM_ADMNO)>0  ORDER BY HIPDT_ADMDATETIME  DESC)) and hstnum_store_id="+cmb[0]);
					}
					else
					{
						brMainQuery.delete(0, brMainQuery.length());
						brMainQuery.append(" select A.hrgnum_puk||'@'|| a.hstnum_issue_no||'@'|| hnum_dossier_req_id||'@'||A.gnum_hospital_code||'@'|| A.hrgnum_puk||'@'|| "+
						" hnum_req_mode||'@'|| to_char(hstdt_issue_date ,'DD-MON-YYYY') || '@' ||  hnum_ref_req_id||'@'||  hnum_servicetype_id||'@'|| "+
						" hnum_service_id||'@'||hstr_service_name||'@'||  hnum_dossier_id||'@'||hnum_dossier_status||'@'|| hstr_entry_remark||'@'||hstr_post_remark||'@'|| "+  
						" hnum_cancel_reason_id||'@'|| hstr_cancel_remark||'@'|| A.gnum_isvalid||'@'|| A.gdt_entry_date||'@'||  gnum_seat_id ||'^'||  "+
						" dossier_mst.get_service_name(hnum_dossier_id::numeric,  A.gnum_hospital_code::numeric, hnum_servicetype_id::numeric)||'^'|| "+
						 " Ahis_Function.FUN_PAT_NAME (A.HRGNUM_PUK)||'^'|| A.HRGNUM_PUK  ||'^'||to_char(A.gdt_entry_date ,'DD-MON-YYYY') ||'^'|| "+
						" hnum_dossier_req_id from hgdt_dossier_return_dtl a , sstt_issue_dtl b  where  A.gnum_hospital_code=b.gnum_hospital_code AND  A.gnum_hospital_code="+httpSession.getAttribute("HOSPITAL_CODE").toString()+" "+ 
						" and a.HSTNUM_ISSUE_NO=b.HSTNUM_ISSUE_NO "+
						" AND A.gnum_isvalid=b.gnum_isvalid  "+
						 " and A.gnum_isvalid=1 "+
						" and hnum_dossier_status = 4 and decode(hstnum_patient_type,1,b.hrgnum_puk in (SELECT HRGNUM_PUK AS PUK FROM HIPT_PATADMISSION_DTL A  WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND HIPDT_DISDATETIME IS NULL AND HIPNUM_ISACCEPTED = 1 AND HIPNUM_IS_DEAD<>1 AND (SELECT count(*) FROM HBLT_PATACCOUNT_DTL B WHERE GNUM_ISVALID = 1 AND HBLNUM_pataccount_status in (1,2) AND GNUM_HOSPITAL_CODE = "+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND B.HRGNUM_PUK= A.HRGNUM_PUK AND B.HASTR_ADM_NO = A.HIPNUM_ADMNO)>0  ORDER BY HIPDT_ADMDATETIME  DESC),b.hrgnum_puk = b.hrgnum_puk) and hstnum_store_id="+cmb[0]);
					}
			   }
			}
		}
		System.out.println("Dossier Main Query-->>"+brMainQuery.toString());
		
		return brMainQuery.toString();

	}

	public String[] getSearchField() 
	{
		
		String search_field[] = {"3", "A.HRGNUM_PUK" ,"5", "A.hnum_dossier_req_id"};
		
			return search_field;

	}

	/**
	 * 0^0 0 Means Combo From Query,
	 * 1  Means User Defined Combo,0 After ^ Means Select Value,
	 * 1 Means All,2 Means Default Selected
	 * */
	public String[] getComboHeader() 
	{
		//String[] strComboHeader = {"0^2","Drug Warehouse Name","1","Request From","1","Status"};
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] strComboHeader = new String[6];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{	

			strComboHeader[0] = "0^0";
			strComboHeader[1] = "Store Name";
			strComboHeader[2] = "0^0";
			strComboHeader[3] = "Request Type";
			strComboHeader[4] = "1";
			strComboHeader[5] = "Status";

		}
		else
		{
			strComboHeader[0] = "0^0";
			strComboHeader[1] = "Store Name";
			strComboHeader[2] = "0^0";
			strComboHeader[3] = "Request Type";
			strComboHeader[4] = "1";
			strComboHeader[5] = "Status";

		}

		
		
	    return strComboHeader;
	}

	public String[] getColumnHeader() 
	{
String[] strColumnHeader = { "Dossier Name", "Patient Name", "CR.No" ,"Request Date" ,"Request No."};
		
		
		return strColumnHeader;
	}      

	public String[] getComboQuery() 
	{
			
		String[] comboQuery = new String[3];
		comboQuery[0] = "SELECT HSTNUM_STORE_ID, HSTSTR_STORE_NAME FROM HSTT_STORE_MST A WHERE GNUM_HOSPITAL_CODE = "+ httpSession.getAttribute("HOSPITAL_CODE").toString()+
				" AND GDT_EFFECTIVE_FRM <= SYSDATE AND GNUM_ISVALID = 1 "+
				" AND EXISTS"+
				   "("+
				    "SELECT 'X'"+
				    "FROM GBLT_ROLE_SEAT_TABLE_DTL P, GBLT_METATABLE_COLUMN_MST q"+
				    " WHERE P.gnum_metatable_id = q.gnum_metatable_id"+
				    " AND UPPER(P.gstr_table_name) = UPPER('HSTT_STORE_MST')"+
				    " AND UPPER(P.gstr_column_name) = UPPER('HSTNUM_STORE_ID')"+
				    " AND gnum_column_value = A.HSTNUM_STORE_ID"+
				 " AND P.gnum_seatid = Pkg_Usermgmt.fun_getseatid("+httpSession.getAttribute("SEATID").toString()+				 
				 ",A.GNUM_HOSPITAL_CODE)"+
				") ORDER BY INITCAP(HSTSTR_STORE_NAME)";
		
		//comboQuery[1] = "13^Patient#12^Staff#14^Dept";
		//comboQuery[2] = "1^Active#2^To Be Return#3^Issued#4^Returned";    changed as per req. of merging of all issue desk
		
		comboQuery[1] = "SELECT   SSTNUM_INDENTTYPE_ID,"
				+ " SSTSTR_INDENTTYPE_NAME"
				+ "  FROM SSTT_INDENTTYPE_MST A"
                   + " WHERE SSTNUM_REQ_FOR = 3"
                   + " AND GNUM_HOSPITAL_CODE = 100"
                 + "   AND GNUM_ISVALID = 1 and SSTNUM_INDENTTYPE_ID in (98,99)"
                 + "   AND EXISTS ("
                  + "  SELECT 'X'"
                  + "  FROM HSTT_STORE_REQTYPE_MST"
                  + "  WHERE GNUM_ISVALID = 1 "
                  + "  AND GNUM_HOSPITAL_CODE ='"+ httpSession.getAttribute("HOSPITAL_CODE").toString()+"'"
                  + "  AND SSTNUM_INDENTTYPE_ID = A.SSTNUM_INDENTTYPE_ID" 
                  + "  AND HSTNUM_STORE_ID =#1#)"
                  + "  ORDER BY SSTNUM_FILE_TYPE";
		
		//comboQuery[2] ="1^New Request#2^In process#3^Item Received#4^Processed";
		comboQuery[2] ="1^New Request#4^Processed";
		return comboQuery;
		
	}

	public String getViewQuery() {
		return "";
	}

	public String getButtons() {

		String strButtons = ""; 
		//"<a style=cursor:pointer><img src='../../hisglobal/images/btn-ccl.png'  border=0 tabindex='1' onKeyPress='window.parent.closeTab();' onClick='window.parent.closeTab();' ></a>";

//		String strButtons ="";
		//"<br><a href='#' class='button' onKeyPress='window.parent.closeTab();' onClick='window.parent.closeTab();'><span class='cancel'>Cancel</span></a> ";
		//"<a style=cursor:pointer><img src='../../hisglobal/images/btn-ccl.png'  border=0 tabindex='1' onKeyPress='window.parent.closeTab();' onClick='window.parent.closeTab();' ></a>";

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
		String files = "../js/LpIssuedesk_Trans.js";
		return files;

	}

	public String[] getRowStatus() {
		String[] status = { "Urgent", "2", "CYAN", "Urgent-----*Return applicable for IPD patients with active billing account only"};
		return status;
	}

	public String getEventState() {
		String str = "issueDeskButtonStatus";
		return str;
	}

	public String getButtonConfiguration() {
		return "left";
	}

	public String[] getUserDefinedButtons() {
		String[] strButtons = {"Issue@validateIssue()@1@3b5998@glyphicon-plus",
				"Return@validateReturn()@1@32506d@glyphicon-menu-left"
				,"View@callViewCnt()@1@007bb6@glyphicon-fullscreen"};  //,"View@callViewCnt()@1"	
		return strButtons;
	}

	public String[] getDbButtons() {
		String[] str = { "2" };
		return str;
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
		String orderBy[] = { "1", "A.GDT_ENTRY_DATE"};
		
	
		
		return orderBy;
	}
	
	@Override
	public String[] getButtonIcons() {
		String[] str={"IssueOnDesk.png","ReturnOnDesk.png","ViewOnDesk.png"};  //,"ViewOnDesk.png"
		// TODO Auto-generated method stub
		return str;
	}
	

}
