package billing.transactions;

import java.util.ArrayList;
import java.util.List;

import hisglobal.transactionutil.TransInterface;

import javax.servlet.http.HttpSession;

public class ClientApprovalDetailsTransUTL extends TransInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	

	HttpSession session = null;

	@Override
	public String getButtonConfiguration()
	{
		return "left";
	}

	@Override
	public String getButtons() 
	{
		return "";
	}

	@Override
	public String[] getColumnHeader() {

		String[] strColumnHeader = {"Client Patient No","Approval Date","Client Name","Patient Name","CR No","Validity","Sanc Amt"};
		return strColumnHeader;
	}

	@Override
	public String getComboEventState()
	{

		return "changeRecordStatus";
	}

	@Override
	public String[] getComboHeader() 
	{

		String[] strComboHeader = { "1", "Record Status" };
		
		return strComboHeader;
	}

	@Override
	public String[] getComboQuery() {
		
		String[] strComboQry = { "1^Active#2^Close" };
		return strComboQry;
	}

	@Override
	public String[] getDbButtons() {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEventState() {
		
      String strEvent = "";//"testFunction";
		
		return strEvent;
	}

	@Override
	public String getJsFiles() 
	{
		return "../../billing/js/clientapproval_trans.js";
	}

	@Override
	public String getMainQuery(String[] cmb) 
	{
		    StringBuffer sb = null;
			sb = new StringBuffer("");
			
			sb.append("Select hblnum_client_patient_no || '@' || hblnum_client_patient_slno||'@'||Hrgnum_puk|| '^' ||hblnum_client_patient_no ");
			sb.append("||'^'||approval|| '^'||INITCAP(clt_name)|| '^'||pat_name||'^'||Hrgnum_puk|| '^'||(effectiv_frm||'   - '||exp_date)||'^'||hblnum_sanc_limit as data "); 
			sb.append("from (select a.hblnum_client_patient_no, a.hblnum_client_patient_slno,a.hrgnum_puk, ");
			sb.append("(select hrgstr_fname|| ' '|| hrgstr_mname|| ' '|| hrgstr_lname From hrgt_patient_dtl b where b.hrgnum_puk = a.hrgnum_puk) pat_name, "); 
			sb.append("(select hblstr_client_name from hblt_client_MST c Where c.hblnum_client_no = a.hblnum_client_no) clt_name, ");
			sb.append("to_char((select gdt_effective_frm from hblt_client_MST c	Where c.hblnum_client_no = a.hblnum_client_no),'dd/MM/yyyy') effectiv_frm, ");
			sb.append("to_char (hbldt_expiry_date, 'dd/MM/yyyy') exp_date,	Hblnum_sanc_limit, hbldt_expiry_date, hblnum_status, gnum_isvalid , ");
			sb.append("to_char (hbldt_auth_date, 'dd/MM/yyyy')approval	From hblt_client_patient_dtl a) t Where nvl (hbldt_expiry_date, sysdate) >= TRUNC(sysdate) "); 
			sb.append("and gnum_isvalid = 1 and hblnum_status = 1");
			   
		
        if (cmb != null) 
		{
        	
        	if (cmb[0].equals("1"))
			{
        		sb = new StringBuffer("");
        		sb.append("Select hblnum_client_patient_no || '@' || hblnum_client_patient_slno||'@'||Hrgnum_puk|| '^' ||hblnum_client_patient_no ");
    			sb.append("||'^'||approval|| '^'||INITCAP(clt_name)|| '^'||pat_name||'^'||Hrgnum_puk|| '^'||(effectiv_frm||'   - '||exp_date)||'^'||hblnum_sanc_limit as data "); 
    			sb.append("from (select a.hblnum_client_patient_no, a.hblnum_client_patient_slno,a.hrgnum_puk, ");
    			sb.append("(select hrgstr_fname|| ' '|| hrgstr_mname|| ' '|| hrgstr_lname From hrgt_patient_dtl b where b.hrgnum_puk = a.hrgnum_puk) pat_name, "); 
    			sb.append("(select hblstr_client_name from hblt_client_MST c Where c.hblnum_client_no = a.hblnum_client_no) clt_name, ");
    			sb.append("to_char((select gdt_effective_frm from hblt_client_MST c	Where c.hblnum_client_no = a.hblnum_client_no),'dd/Mon/yyyy') effectiv_frm, ");
    			sb.append("to_char (hbldt_expiry_date, 'dd/Mon/yyyy') exp_date,	Hblnum_sanc_limit, hbldt_expiry_date, hblnum_status, gnum_isvalid , ");
    			sb.append("to_char (hbldt_auth_date, 'dd/Mon/yyyy')approval	From hblt_client_patient_dtl a) t Where nvl (hbldt_expiry_date, sysdate) >= TRUNC(sysdate) "); 
    			sb.append("and gnum_isvalid = 1 and hblnum_status = 1 ");
				
			}
			
			if (cmb[0].equals("2"))
			{
				sb = new StringBuffer("");
				sb.append("Select hblnum_client_patient_no || '@' || hblnum_client_patient_slno||'@'||Hrgnum_puk|| '^' ||hblnum_client_patient_no ");
    			sb.append("||'^'||approval|| '^'||INITCAP(clt_name)|| '^'||pat_name||'^'||Hrgnum_puk|| '^'||(effectiv_frm||'   - '||exp_date)||'^'||hblnum_sanc_limit as data "); 
    			sb.append("from (select a.hblnum_client_patient_no, a.hblnum_client_patient_slno,a.hrgnum_puk, ");
    			sb.append("(select hrgstr_fname|| ' '|| hrgstr_mname|| ' '|| hrgstr_lname From hrgt_patient_dtl b where b.hrgnum_puk = a.hrgnum_puk) pat_name, "); 
    			sb.append("(select hblstr_client_name from hblt_client_MST c Where c.hblnum_client_no = a.hblnum_client_no) clt_name, ");
    			sb.append("to_char((select gdt_effective_frm from hblt_client_MST c	Where c.hblnum_client_no = a.hblnum_client_no),'dd/Mon/yyyy') effectiv_frm, ");
    			sb.append("to_char (hbldt_expiry_date, 'dd/Mon/yyyy') exp_date,	Hblnum_sanc_limit, hbldt_expiry_date, hblnum_status, gnum_isvalid , ");
    			sb.append("to_char (hbldt_auth_date, 'dd/Mon/yyyy')approval	From hblt_client_patient_dtl a) t Where nvl (hbldt_expiry_date, sysdate) >= TRUNC(sysdate) "); 
    			sb.append("and gnum_isvalid = 1 and hblnum_status = 0 ");
				
			}
			
			
	    }  	
         // System.out.println("sb.toString()--"+sb.toString());
        
        return sb.toString();
	}

	@Override
	public String getMasterName() 
	{
	
		return "Client Patient Desk";
	}

	@Override
	public String getMenuOption() {
		
		// TODO Auto-generated method stub
		return "tiles";
	}

	@Override
	public int getMinPanelButton() {
		
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String[] getRowStatus() {
		
		String[] strRowStatus = new String[0];
		return strRowStatus;
	}

	@Override
	public String[] getSearchField() {
		
		String[] strSearch = { "1", "hblnum_client_patient_no"};
		return strSearch;
	//	return null;
	}

	@Override
	public String[] getUserDefinedButtons() {

		String[] strButtons = new String[4];
	//	strButtons[0] = "<a style='cursor:pointer;cursor:hand;'id='approvalBtId' onClick='return callMe(document.forms[0],\"ADDSERVICE\",\"AddService\");'>Add Service</a>";
	//	strButtons[0] = "<a style='cursor:pointer;cursor:hand' id='approvalBtId' onClick='return callMe(document.forms[0],\"APPROVAL\",\"Approval\");'>Approval</a>";
	//	strButtons[0] = "<a style='cursor:pointer;cursor:hand' id='approvalBtId' onClick='return checkAnyOne(\"APPROVAL\",\"Approval\");'>Approval</a>";
	//	strButtons[1] = "<a style='cursor:pointer;cursor:hand' id='reapprovalBtId' onClick='return checkAnyOne(\"REAPPROVAL\",\"Re-Approval\");'>Re-Approval</a>";
	//	strButtons[2] = "<a style='cursor:pointer;cursor:hand' id='closeBtId' onClick='return checkAnyOne(\"CLOSE\",\"Close\");'>Close</a>";
	//	strButtons[2] = "<a style='cursor:pointer;cursor:hand' id='closeBtId' onClick='return callMe(document.forms[0],\"CLOSE\",\"Close\");'>Close</a>";
	//  strButtons[3] = "<a style='cursor:pointer;cursor:hand' id='viewBtId' onClick='return checkAnyOne(\"VIEW\",\"View\");'>View</a>";
		
	    strButtons[0] = "Approval@WithoutCheckAnyOne(document.forms[0],'APPROVAL','Approval')@0";
	    strButtons[1] = "Re-Approval@checkAnyOne(document.forms[0],'REAPPROVAL','Re-Approval')@0";
	    strButtons[2] = "Close@checkAnyOne(document.forms[0],'CLOSE','Close')@0";
	    strButtons[3] = "View@checkAnyOne(document.forms[0],'VIEW','View')@0";
	    
	    
	    
		return strButtons;
	}

	@Override
	public void setHttpSession(HttpSession session) {
		
		this.session = session;
	}

	@Override
	public void setCombo(String[] cmb) {
		// TODO Auto-generated method stub
		
	}
	public String[] getOrderBy()
    {
    	String orderBy[] = {"1","HBLNUM_CLIENT_PATIENT_NO"};
    	return orderBy;
    }
	/**
	 * returns View Header List
	 */
	public List<String> getViewHeader() 
	{
	    List <String> listViewHdr = new ArrayList<String>();
		return listViewHdr;
	}

	/**
	 * returns View Query String Array
	 */
	public String getViewQuery() 
	{
		return "";
	}

}
