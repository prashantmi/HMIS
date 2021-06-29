package billing.transactions;

import hisglobal.transactionutil.TransInterface;

import javax.servlet.http.HttpSession;

public class ClientVerificTransUTL extends TransInterface
{

	HttpSession session = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	@Override
	public String getButtonConfiguration() {
		// TODO Auto-generated method stub
		return "left";
	}

	@Override
	public String getButtons() {
		String strButtons = "";
		return strButtons;
	}

	@Override
	public String[] getColumnHeader() {
		String[] strColumnHeader = {"Client Name","Client Type","Payment Type","Balance"};
		return strColumnHeader;
	}

	@Override
	public String[] getComboHeader() {
		String[] strComboHeader = {"1","Record Status"};
		
		return strComboHeader;
	}

	@Override
	public String[] getComboQuery() {
		 String[] strComboQry = {"0^Select Value#1^Non-Approval#2^Active#3^Client Expired"};
		//String[] strComboQry = {"1^Non-Approval#2^Active#3^Client Expired"};
		return strComboQry;
	}

	@Override
	public String[] getDbButtons() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * this method is use to get java script function
	 * 
	 * */
	public String getEventState() {
	//	String strEvent = "testFunction";
		String strEvent = "";
		return strEvent;
	}

	@Override
	public String getJsFiles() {
     	   String strJS = "../../hisglobal/transactionutil/js/IpdBillMangTrans.js";
    	// String strJS = "../../billing/js/clientapproval_trans.js";
		return strJS;
	}

	@Override
	public String getMainQuery(String[] cmb) {
		
		String strMainQry = "";

		strMainQry = "SELECT A.HBLNUM_CLIENT_NO||'@'||A.HBLNUM_CLIENT_SLNO,B.HBLSTR_CLIENT_NAME,"+  
		   " DECODE(B.HBLNUM_CLIENT_TYPE,1,'Insurance',2,'Corporate',3,'Customer'),"+ 
		   " DECODE(B.HBLNUM_PAYMENT_TYPE,1,'Pre-Paid',2,'Post-Paid'), A.HBLNUM_BALANCE_AMT "+
		   " FROM hblt_client_dtl A , hblt_client_mst B "; 
		    
		 
					
		if (cmb != null) 
		{
			
			if (cmb[0].equals("1"))
			{
			 	strMainQry = "";
			
				strMainQry = "SELECT A.HBLNUM_CLIENT_NO||'@'||A.HBLNUM_CLIENT_SLNO,B.HBLSTR_CLIENT_NAME,"+  
				   " DECODE(B.HBLNUM_CLIENT_TYPE,1,'Insurance',2,'Corporate',3,'Customer'), "+ 
				   " DECODE(B.HBLNUM_PAYMENT_TYPE,1,'Pre-Paid',2,'Post-Paid'), A.HBLNUM_BALANCE_AMT "+
				   " FROM hblt_client_dtl A , hblt_client_mst B  "+ 
				   " WHERE A.HBLNUM_CLIENT_NO = B.HBLNUM_CLIENT_NO  "; 
				   
	
			}
			
			if (cmb[0].equals("2"))
			{
				strMainQry = "";
			
				strMainQry =" SELECT A.HBLNUM_CLIENT_NO||'@'||A.HBLNUM_CLIENT_SLNO,B.HBLSTR_CLIENT_NAME, "+  
	            " DECODE(B.HBLNUM_CLIENT_TYPE,1,'Insurance',2,'Corporate',3,'Customer'), "+ 
	            " DECODE(B.HBLNUM_PAYMENT_TYPE,1,'Pre-Paid',2,'Post-Paid'), A.HBLNUM_BALANCE_AMT "+
	            " FROM hblt_client_dtl A , hblt_client_mst B  "+ 
	            " WHERE B.HBLNUM_CLIENT_NO != A.HBLNUM_CLIENT_NO ";
	            
	
			}
							
			// condition second
			if (cmb[0].equals("3"))
			{
				strMainQry = "";
			
				strMainQry =" SELECT A.HBLNUM_CLIENT_NO||'@'||A.HBLNUM_CLIENT_SLNO,B.HBLSTR_CLIENT_NAME,"+  
	            "DECODE(B.HBLNUM_CLIENT_TYPE,1,'Insurance',2,'Corporate',3,'Customer'), "+
	             "DECODE(B.HBLNUM_PAYMENT_TYPE,1,'Pre-Paid',2,'Post-Paid'), A.HBLNUM_BALANCE_AMT " +
	            "FROM hblt_client_dtl A , hblt_client_mst B "+  
	            "WHERE B.HBLNUM_CLIENT_NO = A.HBLNUM_CLIENT_NO  "+
	            "AND A.HBLNUM_CLIENT_SLNO = A.HBLNUM_CLIENT_SLNO "+ 
				"AND TRUNC(A.GDT_EFFECTIVE_FRM) <= TRUNC(SYSDATE) "+ 
				"AND TRUNC(NVL(A.GDT_EFFECTIVE_TO,SYSDATE)) >= TRUNC(SYSDATE) "+
				"AND TRUNC(A.HBLNUM_EXPIRY_DATE) >= TRUNC(SYSDATE) "+
				"AND A.HBLNUM_CLIENT_STATUS = 1 " ;		
				
			}
				
			// condition third
		   	if (cmb[0].equals("4"))
		   	{  
		   		strMainQry = "";
		   
		   		strMainQry =" SELECT A.HBLNUM_CLIENT_NO||'@'||A.HBLNUM_CLIENT_SLNO,B.HBLSTR_CLIENT_NAME,"+  
	            "DECODE(B.HBLNUM_CLIENT_TYPE,1,'Insurance',2,'Corporate',3,'Customer'), "+
	             "DECODE(B.HBLNUM_PAYMENT_TYPE,1,'Pre-Paid',2,'Post-Paid'), A.HBLNUM_BALANCE_AMT " +
	            "FROM hblt_client_dtl A , hblt_client_mst B "+  
	            "WHERE B.HBLNUM_CLIENT_NO = A.HBLNUM_CLIENT_NO "+
	            "AND A.HBLNUM_CLIENT_SLNO = A.HBLNUM_CLIENT_SLNO "+ 
	            "AND TRUNC(A.HBLNUM_EXPIRY_DATE) < TRUNC(SYSDATE) "+ 
				"AND A.HBLNUM_CLIENT_STATUS = 1 ";	
		   	}
				
		}  		
    return strMainQry;
	}

	@Override
	public String getMasterName() {
		// TODO Auto-generated method stub
		return "Client Desk";
	}

	@Override
	public String getMenuOption() {
		// TODO Auto-generated method stub
		return "tiles";
	}

	@Override
	public int getMinPanelButton() {
		// TODO Auto-generated method stub
		return 4;
	}

    /*
     * This method for Display the Content in bottom Line 
     * */	
	public String[] getRowStatus() {
		String[] strRowStatus = new String[0];
		return strRowStatus;
	}

	@Override
	public String[] getSearchField() {
		String[] strSearch = {"1","B.HBLSTR_CLIENT_NAME"};
		return strSearch;
	}

	@Override
	public String[] getUserDefinedButtons() {
		
		String[] strButtons = new String[4];
	
//      These Code use to Perform Enable\Disable Action...!  		
		strButtons[0] = "<a id ='ClientVerification' onClick='checkColor(\"CLIENTVERIFICATION\",\"Client Verification\");'>ClientVerification</a>";
	  	strButtons[1] = "<a id ='Invoice'            onClick='checkColor(\"INVOICE\",\"Invoice \");'>Invoice</a>";
		strButtons[2] = "<a id ='Deposit'            onClick='checkColor(\"DEPOSIT\",\"Deposit \");'>Deposit</a>";
		strButtons[3] = "<a id ='Update Client'      onClick='checkColor(\"UPDATECLIENT\",\"Update Client\");'>UpdateClient</a>";
		
//		strButtons[0] = "<a id ='ClientVerification' onClick='add(\"CLIENTVERIFICATION\",\"Client Verification\");'>ClientVerification</a>";
//	 	strButtons[1] = "<a id ='Invoice'            onClick='add(\"INVOICE\");'>Invoice</a>";
//		strButtons[2] = "<a id ='Deposit'            onClick='add(\"DEPOSIT\");'>Deposit</a>";
//		strButtons[3] = "<a id ='RenewExtend'        onClick='add(\"RENEWEXTEND\");'>Renew-Extend</a>";
		return strButtons;
	}

	@Override
	public void setHttpSession(HttpSession session) {
		
		this.session = session;
	}

	@Override
	public String getComboEventState() 
	{
		// TODO Auto-generated method stub
	//	return "abcd";
		return "changeRecordStatus";
	}

	@Override
	public void setCombo(String[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getOrderBy() {
		// TODO Auto-generated method stub
		return null;
	}

}
