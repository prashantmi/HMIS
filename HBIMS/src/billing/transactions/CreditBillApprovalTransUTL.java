package billing.transactions;

import hisglobal.transactionutil.TransInterface;

import javax.servlet.http.HttpSession;

public class CreditBillApprovalTransUTL extends TransInterface
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
		String[] strColumnHeader = {"Req No","Req Date","CR No","Patient Name","Hospital Service","Billing Request"};
		return strColumnHeader;
	}

	@Override
	public String[] getComboHeader() {
		String[] strComboHeader = {"1","Record Status"};
		
		return strComboHeader;
	}

	@Override
	public String[] getComboQuery() 
	{
		 String[] strComboQry = {"1^Approved#2^Cancelled"};
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
	@Override
	public String getEventState() {
		String strEvent = "testFunction";
		//String strEvent = "";
		return strEvent;
	}

	@Override
	public String getJsFiles() {
     	   String strJS = "../../transUtil/js/DiscountApprovalTrans.js";
    	// String strJS = "../../billing/js/clientapproval_trans.js";
		return strJS;
	}

	@Override
	public String getMainQuery(String[] cmb) {
		
		String strMainQry = "";
		strMainQry = "SELECT HRGNUM_PUK||'^'||HBLNUM_REQ_NO||'^'||SBLNUM_BSERVICE_ID,HBLNUM_REQ_NO,HBLDT_REQ_DATE,A.HRGNUM_PUK, "+ 
        "(SELECT HRGSTR_FNAME||' '||HRGSTR_MNAME||' '||HRGSTR_LNAME FROM HRGT_PATIENT_DTL WHERE HRGNUM_PUK = A.HRGNUM_PUK) PATIENT_NAME, "+
        "(SELECT SBLSTR_CHARGETYPE_NAME FROM SBLT_CHARGETYPE_MST WHERE SBLNUM_CHARGETYPE_ID = A.SBLNUM_CHARGETYPE_ID) HOSPITAL_SERVICE, "+
        "(SELECT SBLSTR_BSERVICE_NAME FROM SBLT_BILLSERVICE_MST WHERE SBLNUM_BSERVICE_ID = A.SBLNUM_BSERVICE_ID) REQUEST "+ 
        " FROM SBLT_INBOUND_DTL A  WHERE SBLNUM_BSERVICE_ID IN (10,11,12,13,14,15,21) AND HBLNUM_STATUS = 1 " +
        " AND GNUM_ISVALID = 1 AND EXISTS ( SELECT 'X' FROM HBLT_BILLREQ_TARIFF_DTL WHERE HBLNUM_REQ_NO = A.HBLNUM_REQ_NO " + 
	        " AND HBLNUM_TARIFF_ID >= 0 AND HBLNUM_TARIFF_ID <= 9999999 AND NVL(HBLNUM_REQ_QTY,0) > NVL(HBLNUM_BILL_QTY,0) AND HBLNUM_DISCOUNT_UNIT > 0 AND HBLNUM_APPROVAL_ID IS NOT NULL "+ 
	        " AND HBLNUM_STATUS = 1 AND GNUM_ISVALID = 1  ) ORDER BY HBLDT_REQ_DATE "; 

       

		if (cmb != null) 
		{
			//System.out.println("inside cmb");
			if (cmb[0].equals("1"))
			{
				
			 	strMainQry = "";
			
			 	strMainQry = "SELECT HRGNUM_PUK||'^'||HBLNUM_REQ_NO||'^'||SBLNUM_BSERVICE_ID,HBLNUM_REQ_NO,HBLDT_REQ_DATE,A.HRGNUM_PUK, "+ 
		        "(SELECT HRGSTR_FNAME||' '||HRGSTR_MNAME||' '||HRGSTR_LNAME FROM HRGT_PATIENT_DTL WHERE HRGNUM_PUK = A.HRGNUM_PUK) PATIENT_NAME, "+
		        "(SELECT SBLSTR_CHARGETYPE_NAME FROM SBLT_CHARGETYPE_MST WHERE SBLNUM_CHARGETYPE_ID = A.SBLNUM_CHARGETYPE_ID) HOSPITAL_SERVICE, "+
		        "(SELECT SBLSTR_BSERVICE_NAME FROM SBLT_BILLSERVICE_MST WHERE SBLNUM_BSERVICE_ID = A.SBLNUM_BSERVICE_ID) REQUEST "+ 
		        " FROM SBLT_INBOUND_DTL A  WHERE SBLNUM_BSERVICE_ID IN (10,11,12,13,14,15,21) AND HBLNUM_STATUS = 1 " +
		        " AND GNUM_ISVALID = 1 AND EXISTS ( SELECT 'X' FROM HBLT_BILLREQ_TARIFF_DTL WHERE HBLNUM_REQ_NO = A.HBLNUM_REQ_NO " + 
			        " AND HBLNUM_TARIFF_ID >= 0 AND HBLNUM_TARIFF_ID <= 9999999 AND NVL(HBLNUM_REQ_QTY,0) > NVL(HBLNUM_BILL_QTY,0) AND HBLNUM_DISCOUNT_UNIT > 0 AND HBLNUM_APPROVAL_ID IS NOT NULL "+ 
			        " AND HBLNUM_STATUS = 1 AND GNUM_ISVALID = 1  ) ORDER BY HBLDT_REQ_DATE "; 
			}
			
			if (cmb[0].equals("2"))
			{
				strMainQry = "";
				
				strMainQry =  " SELECT HBLNUM_REQ_NO,HBLDT_REQ_DATE, " +
                              "(SELECT HRGSTR_FNAME||' '||HRGSTR_MNAME||' '||HRGSTR_LNAME FROM HRGT_PATIENT_DTL WHERE HRGNUM_PUK = A.HRGNUM_PUK) PATIENT_NAME, "+
                              "(SELECT SBLSTR_CHARGETYPE_NAME FROM SBLT_CHARGETYPE_MST  "+
                              "WHERE SBLNUM_CHARGETYPE_ID = A.SBLNUM_CHARGETYPE_ID) HOSPITAL_SERVICE, "+
                              "(SELECT SBLSTR_BSERVICE_NAME FROM SBLT_BILLSERVICE_MST WHERE SBLNUM_BSERVICE_ID = A.SBLNUM_BSERVICE_ID) REQUEST "+
                              "FROM SBLT_INBOUND_DTL A WHERE SBLNUM_BSERVICE_ID IN (10,11,12,13,14,15,21) AND HBLNUM_STATUS = 1 AND GNUM_ISVALID = 1 " +
                              "AND EXISTS "+ 
                              "( " +
                              " SELECT 'X' " +
                              " FROM HBLT_BILLREQ_TARIFF_DTL "+
                              " WHERE HBLNUM_REQ_NO = A.HBLNUM_REQ_NO AND "+ 
                              " HBLNUM_TARIFF_ID >= 0 AND HBLNUM_TARIFF_ID <= 9999999 "+
                              " AND NVL(HBLNUM_REQ_QTY,0) > NVL(HBLNUM_BILL_QTY,0) "+
                              " AND HBLNUM_DISCOUNT_AMT = 0 AND HBLNUM_APPROVAL_ID IS NOT NULL "+ 
                              "AND HBLNUM_STATUS = 1 AND GNUM_ISVALID = 1 "+
                              ") "+ 
                              "ORDER BY HBLDT_REQ_DATE ";
    	}
	}  		
		
    return strMainQry;
	}

	@Override
	public String getMasterName() {
		// TODO Auto-generated method stub
		return "Discount Approval";
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
	@Override
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
	public String[] getUserDefinedButtons()
	{
		
		String[] strButtons = new String[2];
	
	
		strButtons[0] = "<a style=cursor:hand id ='Approved'  onClick='add(\"APPROVED\",\"Approved\");'>APPROVED</a>";
	    strButtons[1] = "<a style=cursor:hand id ='Cancelled'  onClick='return checkColor2();'>CANCELLED</a>";
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
	
		return "changeRecordStatus2";
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
