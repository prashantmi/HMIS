package billing.transactions;
import java.util.ArrayList;
import java.util.List;

import hisglobal.transactionutil.TransInterface;

import javax.servlet.http.HttpSession;

import billing.BillConfigUtil;


public class IpdBillManagementTransUTL extends TransInterface 
{
	HttpSession session = null;
	
	
	
	String[] cmbVal = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	
    
	@Override
	public String getButtonConfiguration() 
	{
		return "left";
	}

	@Override
	public String getButtons() 
	{
		String strButtons = "";
		return strButtons;
	}

	@Override
	public String[] getColumnHeader() 
	{		
		String[] strColumnHeader = {"Account No","Patient Name","CR No","Admission No","Admission Date","Net Payble Amount","Appl. Package"};
		return strColumnHeader;
	}

	@Override
	public String[] getComboHeader() 
	{		
		BillConfigUtil bcu = new BillConfigUtil(session.getAttribute("HOSPITAL_CODE").toString());		
		String[] strComboHeader = null;
		
		// strComboHeader[0] = "0^2" MEANS 0- QUERY BASED COMBO VALUES,1-HARD CODED COMBO VALUES. AFTER ^ 1-ALL,2-DEFAULT SELECTED FIRST VALUE,0-SELECT VALUE
		if(bcu.getIpdGenAdtProcessType().equals("1"))
		{			
			 strComboHeader =  new String[6];			 
			 strComboHeader[0] = "1";
			 strComboHeader[1] = "Account Status";
			 strComboHeader[2] = "0";
			 strComboHeader[3] = "Department Name";
			 strComboHeader[4] = "0";
			 strComboHeader[5] = "Ward Name";
		}
		else
		{
			 strComboHeader =  new String[6];
			 strComboHeader[0] = "0^2";
			 strComboHeader[1] = "Account Status";
			 strComboHeader[2] = "1";
			 strComboHeader[3] = "Department Name";
			 strComboHeader[4] = "1";
			 strComboHeader[5] = "Ward Name";			 
		}		
	    return strComboHeader;		
	}

	@Override
	public String[] getComboQuery() 
	{
		BillConfigUtil bcu = new BillConfigUtil(session.getAttribute("HOSPITAL_CODE").toString());		
		String hosp_code = session.getAttribute("HOSPITAL_CODE").toString();		
		String[] strComboQry = null;
		
		if(bcu.getIpdGenAdtProcessType().equals("1"))
		{
			strComboQry = new String[3];			

			//strComboQry[0] = "1^Active#3^On-Hold#4^Dormant#5^Approved Request#6^Cancelled";
			
			strComboQry[0]= "SELECT SBLNUM_ACCSTATUS_ID,SBLSTR_ACCSTATUS_NAME FROM SBLT_ACCOUNTSTATUS_MST WHERE GNUM_ISVALID=1 ";
				
			strComboQry[1] = "SELECT GNUM_DEPT_CODE,INITCAP(GSTR_DEPT_NAME) "+ 
			                 "FROM GBLT_DEPARTMENT_MST WHERE SYSDATE BETWEEN GDT_EFFECTIVE_FRM AND NVL(GDT_EFFECTIVE_TO,SYSDATE) "+
			                 "AND GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE ="+hosp_code+" ORDER BY GSTR_DEPT_NAME ";
//			if(cmbVal!=null)
//			System.out.println("COMBO VAL"+cmbVal[1]);
			
			strComboQry[2] = " SELECT ward_id,ward_name	FROM(SELECT DISTINCT HIPNUM_WARD_CODE || '^0' AS ward_id, " +
	                         " ipd_mst.getWardName(HIPNUM_WARD_CODE,GNUM_HOSPITAL_CODE) AS ward_name FROM HIPT_DUWRBED_MST " +
	                         " WHERE GNUM_ISVALID = 1 AND GDT_EFFECTIVE_FRM <= TRUNC(SYSDATE) " +
	                         " AND GNUM_HOSPITAL_CODE = "+hosp_code+" AND GNUM_DEPT_CODE = #2# )A WHERE NVL(LENGTH(TRIM(WARD_NAME)),0) > 0 ORDER BY ward_name ";
		}
		else
		{			
			strComboQry = new String[3];
			//strComboQry[0] = "1^Active#3^On-Hold#4^Dormant#5^Approved Request#6^Cancelled";
			
			strComboQry[0]="SELECT SBLNUM_ACCSTATUS_ID,SBLSTR_ACCSTATUS_NAME  FROM SBLT_ACCOUNTSTATUS_MST WHERE GNUM_ISVALID=1";
				
			strComboQry[1] = "1^All";
//			if(cmbVal!=null)
//			System.out.println("COMBO VAL"+cmbVal[1]);
			
			strComboQry[2] = "1^All";			
		}		
		return strComboQry;
	}

	@Override
	public String[] getDbButtons() 
	{
		String[] str= {"4"};
		return str;
	}

	/*
	 * this method is use to get java script function
	 * 
	 * */
	public String getEventState() 
	{
		String strEvent = "buttonLogicsOnRecordCheck";
		return strEvent;
	}
	@Override
	public String getJsFiles() 
	{
     	   String strJS = "../../billing/js/IpdBillMangTrans.js";
    	   return strJS;
	}
	@Override
	public String getMainQuery(String[] cmb) 
	{		
		BillConfigUtil bcu = new BillConfigUtil(session.getAttribute("HOSPITAL_CODE").toString());
		
		String hosp_code = session.getAttribute("HOSPITAL_CODE").toString();
		String userValue="";
		String finalBillFlag="";
		if(session.getAttribute("USERVALUE")!=null)
			userValue= session.getAttribute("USERVALUE").toString();
		
		if(userValue == null || userValue.equals(""))
		{
			finalBillFlag="90";
			session.setAttribute("USERVALUE", "0");
		}
		if(userValue!= null && !userValue.equals("") && userValue.equals("0"))
			finalBillFlag="90";//for clerk
		if(userValue!= null && !userValue.equals("") && userValue.equals("1"))
			finalBillFlag="91";//for auditor
		
		StringBuffer sb = new StringBuffer("");
		sb.append("SELECT HBLNUM_PATACCOUNT_NO||'@'|| HRGNUM_PUK || '@'|| HBLNUM_CLIENT_PATIENT_NO ||'@'|| GNUM_PATIENT_CAT_CODE ||'@'|| ");
        sb.append("SBLNUM_CHARGETYPE_ID ||'@'||HASTR_ADM_NO||'@'|| AHIS_FUNCTION.GETCATEGORYNAME(A.GNUM_PATIENT_CAT_CODE,A.GNUM_HOSPITAL_CODE) || '@'|| ");
        sb.append("HBLDT_PATACCOUNT_OPDATE ||'@'||HRGNUM_EPISODE_CODE||'^'||HBLNUM_PATACCOUNT_NO||'^'||AHIS_FUNCTION.FUN_PAT_NAME(A.HRGNUM_PUK) ||'^'||HRGNUM_PUK||'^'||HASTR_ADM_NO||'^'|| TO_CHAR(HBLDT_ADMISSION_DATE,'DD-Mon-YYYY HH24:MI') ||'^'|| ");
        sb.append("((NVL(HBLNUM_TARIFF_NET_AMT,0)) - (NVL(HBLNUM_PATIENT_RECD_AMT,0)+NVL(HBLNUM_SANC_AMT,0)))||'^'||");
        sb.append("BILL_MST.GETTARIFFNAME(HBLNUM_AVAILED_PACKAGE_TARIFF_ID, 1, A.GNUM_HOSPITAL_CODE)||'^'||HBLNUM_REOPEN_FLAG||'^'||decode((select B.HIPDT_ADMSTATUS_CODE from HIPT_PATADMISSION_DTL B where B.HRGNUM_PUK=a.HRGNUM_PUK AND B.HIPNUM_ADMNO=a.HASTR_ADM_NO),18,1,0) AS DATA FROM HBLT_PATACCOUNT_DTL a ");
        sb.append("WHERE GNUM_ISVALID = 1  ");
        //sb.append("AND SBLNUM_CHARGETYPE_ID = 2 "); FOR WALLET CHARGE TYPE ID CAN BE ANYTHING
        sb.append("AND HBLNUM_PATACCOUNT_STATUS IN (1 , 2) AND HBLNUM_FINALBILL_FLAG = "+finalBillFlag+" AND GNUM_HOSPITAL_CODE = "+hosp_code+" ");
               
        if(bcu.getIpdGenAdtProcessType().equals("1"))//IPD Interface-Online
        {        
        	 sb.append("AND EXISTS ");
             sb.append("(SELECT 'X' FROM HIPT_PATADMISSION_DTL WHERE GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE ");
             sb.append("AND HIPNUM_ADMNO = A.HASTR_ADM_NO AND GNUM_DEPT_CODE = 0 AND HIPNUM_WARD_CODE = 0) ");
        }
        
        sb.append("AND HBLNUM_PATACCOUNT_NO >= 0  ");
        sb.append("AND HBLNUM_PATACCOUNT_NO <= 999999999999999 "); 
        sb.append("AND EXISTS(SELECT HRGNUM_PUK FROM HIPT_PATADMISSION_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK AND A.GNUM_HOSPITAL_CODE=B.GNUM_HOSPITAL_CODE AND B.HIPDT_DISDATETIME IS NULL AND B.HIPDT_BEDALLOC_DATETIME IS NOT NULL)");
        
        
        if (cmb != null) 
		{        	
        	if(bcu.getIpdGenAdtProcessType().equals("1"))
        	{          
	        	if (cmb[0].equals("1")||cmb[0].equals("2")||cmb[0].equals("0"))
				{	        		
	        		String strWardCode = cmb[2].replace("^", "#").split("#")[0];
	        		
	        		sb = new StringBuffer(""); 
	        		sb.append("SELECT HBLNUM_PATACCOUNT_NO||'@'|| HRGNUM_PUK || '@'|| HBLNUM_CLIENT_PATIENT_NO ||'@'|| GNUM_PATIENT_CAT_CODE ||'@'|| ");
	                sb.append("SBLNUM_CHARGETYPE_ID ||'@'||HASTR_ADM_NO||'@'|| AHIS_FUNCTION.GETCATEGORYNAME(A.GNUM_PATIENT_CAT_CODE,A.GNUM_HOSPITAL_CODE) || '@'|| ");
	                sb.append("HBLDT_PATACCOUNT_OPDATE ||'@'||HRGNUM_EPISODE_CODE||'^'||HBLNUM_PATACCOUNT_NO||'^'||AHIS_FUNCTION.FUN_PAT_NAME(A.HRGNUM_PUK) ||'^'||HRGNUM_PUK||'^'||HASTR_ADM_NO||'^'|| TO_CHAR(HBLDT_ADMISSION_DATE,'DD-Mon-YYYY HH24:MI') ||'^'||");
	                sb.append("((NVL(HBLNUM_TARIFF_NET_AMT,0)) - (NVL(HBLNUM_PATIENT_RECD_AMT,0)+NVL(HBLNUM_SANC_AMT,0)))||'^'||");
	                sb.append("BILL_MST.GETTARIFFNAME(HBLNUM_AVAILED_PACKAGE_TARIFF_ID, 1, A.GNUM_HOSPITAL_CODE)||'^'||HBLNUM_REOPEN_FLAG||'^'||decode((select B.HIPDT_ADMSTATUS_CODE from HIPT_PATADMISSION_DTL B where B.HRGNUM_PUK=a.HRGNUM_PUK AND B.HIPNUM_ADMNO=a.HASTR_ADM_NO),18,1,0) AS DATA FROM HBLT_PATACCOUNT_DTL A ");
	                sb.append("WHERE GNUM_ISVALID = 1  ");
	                //sb.append("AND SBLNUM_CHARGETYPE_ID = 2 "); FOR WALLET CHARGE TYPE ID CAN BE ANYTHING
	                sb.append("AND HBLNUM_PATACCOUNT_STATUS IN (1 , 2 ) AND HBLNUM_FINALBILL_FLAG = "+finalBillFlag+"  AND EXISTS ");
	                sb.append("(SELECT 'X' FROM HIPT_PATADMISSION_DTL WHERE GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE ");
	                sb.append("AND HIPNUM_ADMNO = A.HASTR_ADM_NO AND GNUM_DEPT_CODE = "+cmb[1]+" AND HIPNUM_WARD_CODE = "+strWardCode+" ) AND GNUM_HOSPITAL_CODE = "+hosp_code+" ");
	                sb.append("AND HBLNUM_PATACCOUNT_NO >= 0  ");
	                sb.append("AND HBLNUM_PATACCOUNT_NO <= 999999999999999 ");  
				}
	        	else
				{
	        		String strWardCode = cmb[2].replace("^", "#").split("#")[0];
	        		
	 				sb = new StringBuffer(""); 
		    		sb.append("SELECT HBLNUM_PATACCOUNT_NO||'@'|| HRGNUM_PUK || '@'|| HBLNUM_CLIENT_PATIENT_NO ||'@'|| GNUM_PATIENT_CAT_CODE ||'@'|| ");
		            sb.append("SBLNUM_CHARGETYPE_ID ||'@'||HASTR_ADM_NO||'@'|| AHIS_FUNCTION.GETCATEGORYNAME(A.GNUM_PATIENT_CAT_CODE,A.GNUM_HOSPITAL_CODE) || '@'|| ");
		            sb.append("HBLDT_PATACCOUNT_OPDATE ||'@'||HRGNUM_EPISODE_CODE||'^'||HBLNUM_PATACCOUNT_NO||'^'||AHIS_FUNCTION.FUN_PAT_NAME(A.HRGNUM_PUK) ||'^'||HRGNUM_PUK||'^'||HASTR_ADM_NO||'^'|| TO_CHAR(HBLDT_ADMISSION_DATE,'DD-Mon-YYYY HH24:MI') ||'^'||");
		            sb.append("((NVL(HBLNUM_TARIFF_NET_AMT,0)) - (NVL(HBLNUM_PATIENT_RECD_AMT,0)+NVL(HBLNUM_SANC_AMT,0)))||'^'||");
		            sb.append("BILL_MST.GETTARIFFNAME(HBLNUM_AVAILED_PACKAGE_TARIFF_ID, 1, A.GNUM_HOSPITAL_CODE)||'^'||HBLNUM_REOPEN_FLAG||'^'||decode((select B.HIPDT_ADMSTATUS_CODE from HIPT_PATADMISSION_DTL B where B.HRGNUM_PUK=a.HRGNUM_PUK AND B.HIPNUM_ADMNO=a.HASTR_ADM_NO),18,1,0) AS DATA FROM HBLT_PATACCOUNT_DTL A ");
		            sb.append("WHERE GNUM_ISVALID = 1  ");
		            //sb.append("AND SBLNUM_CHARGETYPE_ID = 2 "); FOR WALLET CHARGE TYPE ID CAN BE ANYTHING
		            sb.append("AND HBLNUM_PATACCOUNT_STATUS = "+cmb[0]+" AND HBLNUM_FINALBILL_FLAG = "+finalBillFlag+"  AND EXISTS ");
		            sb.append("(SELECT 'X' FROM HIPT_PATADMISSION_DTL WHERE GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE ");
		            sb.append("AND HIPNUM_ADMNO = A.HASTR_ADM_NO AND GNUM_DEPT_CODE = "+cmb[1]+" AND HIPNUM_WARD_CODE = "+strWardCode+" ) AND GNUM_HOSPITAL_CODE = "+hosp_code+" ");
		            sb.append("AND HBLNUM_PATACCOUNT_NO >= 0  ");
		            sb.append("AND HBLNUM_PATACCOUNT_NO <= 999999999999999 ");	 	
		            sb.append("AND EXISTS(SELECT HRGNUM_PUK FROM HIPT_PATADMISSION_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK AND A.GNUM_HOSPITAL_CODE=B.GNUM_HOSPITAL_CODE AND B.HIPDT_DISDATETIME IS NULL AND B.HIPDT_BEDALLOC_DATETIME IS NOT NULL)");
				}
        	}
        	else
        	{        		
        		if (cmb[0].equals("1")||cmb[0].equals("2")||cmb[0].equals("0"))//1-Active,2-?,0-Closed
    			{ 
            		sb = new StringBuffer(""); 
            		sb.append("SELECT HBLNUM_PATACCOUNT_NO||'@'|| HRGNUM_PUK || '@'|| HBLNUM_CLIENT_PATIENT_NO ||'@'|| GNUM_PATIENT_CAT_CODE ||'@'|| ");
                    sb.append("SBLNUM_CHARGETYPE_ID ||'@'||HASTR_ADM_NO||'@'|| AHIS_FUNCTION.GETCATEGORYNAME(A.GNUM_PATIENT_CAT_CODE,A.GNUM_HOSPITAL_CODE) || '@'|| ");
                    sb.append("HBLDT_PATACCOUNT_OPDATE ||'@'||HRGNUM_EPISODE_CODE||'^'||HBLNUM_PATACCOUNT_NO||'^'||AHIS_FUNCTION.FUN_PAT_NAME(A.HRGNUM_PUK) ||'^'||HRGNUM_PUK||'^'||HASTR_ADM_NO||'^'|| TO_CHAR(HBLDT_ADMISSION_DATE,'DD-Mon-YYYY HH24:MI') ||'^'||");
                    sb.append("((NVL(HBLNUM_TARIFF_NET_AMT,0)) - (NVL(HBLNUM_PATIENT_RECD_AMT,0)+NVL(HBLNUM_SANC_AMT,0)))||'^'||");
                    sb.append("BILL_MST.GETTARIFFNAME(HBLNUM_AVAILED_PACKAGE_TARIFF_ID, 1, A.GNUM_HOSPITAL_CODE)||'^'||HBLNUM_REOPEN_FLAG||'^'||decode((select B.HIPDT_ADMSTATUS_CODE from HIPT_PATADMISSION_DTL B where B.HRGNUM_PUK=a.HRGNUM_PUK AND B.HIPNUM_ADMNO=a.HASTR_ADM_NO),18,1,0) AS DATA FROM HBLT_PATACCOUNT_DTL a ");
                    sb.append("WHERE GNUM_ISVALID = 1  ");
                    //sb.append("AND SBLNUM_CHARGETYPE_ID = 2 "); FOR WALLET CHARGE TYPE ID CAN BE ANYTHING
                    sb.append("AND HBLNUM_PATACCOUNT_STATUS IN (1 , 2 ) AND HBLNUM_FINALBILL_FLAG = "+finalBillFlag+"  AND GNUM_HOSPITAL_CODE = "+hosp_code+" ");
                    sb.append("AND HBLNUM_PATACCOUNT_NO >= 0  ");
                    sb.append("AND HBLNUM_PATACCOUNT_NO <= 999999999999999 ");
                    
                    if (cmb[0].equals("1")||cmb[0].equals("2"))//1-Active,2-?,0-Closed---Do Not Currently Admitted Condition if Account Closed
                    	sb.append("AND EXISTS(SELECT HRGNUM_PUK FROM HIPT_PATADMISSION_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK AND A.GNUM_HOSPITAL_CODE=B.GNUM_HOSPITAL_CODE AND B.HIPDT_DISDATETIME IS NULL AND B.HIPDT_BEDALLOC_DATETIME IS NOT NULL)");
    			}
            	else
    			{            	            		
     				sb = new StringBuffer(""); 
	        		sb.append("SELECT HBLNUM_PATACCOUNT_NO||'@'|| HRGNUM_PUK || '@'|| HBLNUM_CLIENT_PATIENT_NO ||'@'|| GNUM_PATIENT_CAT_CODE ||'@'|| ");
	                sb.append("SBLNUM_CHARGETYPE_ID ||'@'||HASTR_ADM_NO||'@'|| AHIS_FUNCTION.GETCATEGORYNAME(A.GNUM_PATIENT_CAT_CODE,A.GNUM_HOSPITAL_CODE) || '@'|| ");
	                sb.append("HBLDT_PATACCOUNT_OPDATE ||'@'||HRGNUM_EPISODE_CODE||'^'||HBLNUM_PATACCOUNT_NO||'^'||AHIS_FUNCTION.FUN_PAT_NAME(A.HRGNUM_PUK) ||'^'||HRGNUM_PUK||'^'||HASTR_ADM_NO||'^'|| TO_CHAR(HBLDT_ADMISSION_DATE,'DD-Mon-YYYY HH24:MI') ||'^'||");
	                sb.append("((NVL(HBLNUM_TARIFF_NET_AMT,0)) - (NVL(HBLNUM_PATIENT_RECD_AMT,0)+NVL(HBLNUM_SANC_AMT,0)))||'^'||");
	                sb.append("BILL_MST.GETTARIFFNAME(HBLNUM_AVAILED_PACKAGE_TARIFF_ID, 1, A.GNUM_HOSPITAL_CODE)||'^'||HBLNUM_REOPEN_FLAG||'^'||decode((select B.HIPDT_ADMSTATUS_CODE from HIPT_PATADMISSION_DTL B where B.HRGNUM_PUK=a.HRGNUM_PUK AND B.HIPNUM_ADMNO=a.HASTR_ADM_NO),18,1,0) AS DATA FROM HBLT_PATACCOUNT_DTL A ");
	                sb.append("WHERE GNUM_ISVALID = 1 ");
	                //sb.append("AND SBLNUM_CHARGETYPE_ID = 2 "); FOR WALLET CHARGE TYPE ID CAN BE ANYTHING
	                sb.append("AND HBLNUM_PATACCOUNT_STATUS = "+cmb[0]+" AND HBLNUM_FINALBILL_FLAG = "+finalBillFlag+"  AND GNUM_HOSPITAL_CODE = "+hosp_code+" ");
	                sb.append("AND HBLNUM_PATACCOUNT_NO >= 0  ");
	                sb.append("AND HBLNUM_PATACCOUNT_NO <= 999999999999999 ");
	                sb.append("AND EXISTS(SELECT HRGNUM_PUK FROM HIPT_PATADMISSION_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK AND A.GNUM_HOSPITAL_CODE=B.GNUM_HOSPITAL_CODE AND B.HIPDT_DISDATETIME IS NULL AND B.HIPDT_BEDALLOC_DATETIME IS NOT NULL)");
     			}        		
        	}
		}	
        
       System.out.println("Main Query : "+sb.toString());
        
       return sb.toString();
	}

	@Override
	public String getMasterName() 
	{
		return "IPD Bill Management";
	}

	@Override
	public String getMenuOption() 
	{
		return "tiles";
	}

	@Override
	public int getMinPanelButton() 
	{
		return 4;
	}

    /*
     * This method for Display the Content in bottom Line 
     * */	
	 public String[] getRowStatus() 
	 {
		String[] strRowStatus = { "1", "9", "#C0FBD3", "Re-Opened","1", "10", "#D96131", "Ready for Discharge"};
		return strRowStatus;
	 }

	
	@Override
	public String[] getSearchField() 
	{
		String[] strSearch = {"3","HRGNUM_PUK","1","HBLNUM_PATACCOUNT_NO"};
		return strSearch;
	}

	@Override
	public String[] getUserDefinedButtons()
	{
		
		String[] strButtons = new String[4];
		// PGIMER 
		strButtons[0] = "Add Service@buttonLogicsOnClick(1,'ADDSERVICE','AddService')@1";
		//strButtons[1] = "Part Payment@buttonLogicsOnClick(2,'PARTPAYMENTREQUEST','PartPayRequest')@1";
		strButtons[1] = "Update Account@buttonLogicsOnClick(3,'UPDATEACCOUNTSTATUS','UpdateAccountStatus')@1";
	  	strButtons[2] = "Bill Approval@buttonLogicsOnClick(4,'BILLAPPROVAL','BillApproval')@1";
		strButtons[3] = "View Bill@buttonLogicsOnClick(5,'VIEWBILL','ViewBill')@1";
		//strButtons[5] = "View Charges@buttonLogicsOnClick(6,'VIEWCHARGES','ViewCharges')@0";

	// GNCTD 
		/*
		strButtons[0] = "Part Payment@buttonLogicsOnClick(2,'PARTPAYMENTREQUEST','PartPayRequest')@1";
	  	strButtons[1] = "Bill Approval@buttonLogicsOnClick(4,'BILLAPPROVAL','BillApproval')@1";
		strButtons[2] = "View Bill@buttonLogicsOnClick(5,'VIEWBILL','ViewBill')@1";
		strButtons[3] = "View Charges@buttonLogicsOnClick(6,'VIEWCHARGES','ViewCharges')@0";
		*/
		return null;
	}

	@Override
	public void setHttpSession(HttpSession session) 
	{		
		this.session = session;
	}

	@Override
	public String getComboEventState() 
	{
		return "buttonLogicsOnChangeCombos";
	}

	@Override
	public void setCombo(String[] cmb) 
	{
		cmbVal = cmb ;		
	}
	 /**
	 * To Order By Result Set
	 */
    public String[] getOrderBy()
    {
    	String orderBy[] = {"1","HBLNUM_PATACCOUNT_NO"};
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

	public String[] getButtonIcons()
	{
		String[] strButtonIcons = new String[6];
		strButtonIcons[0] = "AddOnDesk.png";
		//strButtonIcons[1] = "Payment.png";
		strButtonIcons[1] = "UpdateOnDesk.png";
		strButtonIcons[2] = "ApprovalOnDesk.png";
		strButtonIcons[3] = "ViewOnDesk.png";
		strButtonIcons[5] = "Charges.png";
		
    	return strButtonIcons;
	}
	public String showHelp()
	{
		/*String[] str = {"icon-default.png"};
		return str;*/
		return "In Case Of Active Only Those Patient Accounts Will Be Shown Here Who Are Currently Admitted in Wards";
	}
}