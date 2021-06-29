
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

public class IndentTransUTL extends TransInterface {

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
		String masterName = "Indent Desk";
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
	    	if(!cmb[2].equals("0") && (cmb[2].replace("^","#").split("#")[1].equals("86")))
		    {
	        brMainQuery.append("SELECT HSTNUM_REQ_NO || '@' || HSTNUM_STORE_ID  || '@' || SSTNUM_REQTYPE_ID || '@' || A.SSTNUM_ITEM_CAT_NO || '@' || A.HSTNUM_URGENT_FLAG ||'@'|| HSTSTR_INDENT_PERIOD||'@'||HSTNUM_TOSTORE_ID || '^'|| A.HRGNUM_PUK || '^' || AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK::numeric)  || '^'|| HSTNUM_REQ_NO ||  decode(is_auto_indent,1,'*')  || '^'||TO_CHAR(HSTDT_REQ_DATE,'DD-Mon-YYYY hh24:mi')|| '^'|| ");
			brMainQuery.append("MMS_MST.GET_STORE_DTL(1,A.GNUM_HOSPITAL_CODE,HSTNUM_TOSTORE_ID) || '^' || decode(HSTNUM_INDENT_STATUS,0,'Pending',1,'I Approval',10,'II Approval',45,'Indent Closed',20,'Partial Approved',40,'Final Approved',49,'In Process',99,'Rejected',111,'Items Received',50,'Processed',53,'Processed',HSTNUM_INDENT_STATUS)  || '^'||");
		//	brMainQuery.append(" || '^'|| ");
			brMainQuery.append(" DECODE(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')");
			brMainQuery.append(" AS DATA FROM SSTT_INDENT_DTL A ");
			brMainQuery.append(" WHERE GNUM_ISVALID = 1 AND ");
			brMainQuery.append(" GNUM_HOSPITAL_CODE = ");
			brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
			brMainQuery.append(" AND  SSTNUM_REQTYPE_ID NOT IN(80,81,82,83,84,85)");
		    }
	    	else if(!cmb[2].equals("0") && (cmb[2].replace("^","#").split("#")[1].equals("13") ))
	    	{
	    		if(!cmb[3].equals("2"))
	    		{
	    			brMainQuery.append("SELECT HSTNUM_REQ_NO || '@' || HSTNUM_STORE_ID  || '@' || SSTNUM_REQTYPE_ID || '@' || A.SSTNUM_ITEM_CAT_NO || '@' || A.HSTNUM_URGENT_FLAG ||'@'|| HSTSTR_INDENT_PERIOD||'@'||HSTNUM_TOSTORE_ID || '^'|| A.HRGNUM_PUK  || '^'|| AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK::numeric)  || '^' || HSTNUM_REQ_NO ||  decode(is_auto_indent,1,'*')  || '^'||TO_CHAR(HSTDT_REQ_DATE,'DD-Mon-YYYY  hh24:mi')|| '^'|| ");
		 			brMainQuery.append("MMS_MST.GET_STORE_DTL(1,A.GNUM_HOSPITAL_CODE,HSTNUM_TOSTORE_ID) || '^' ||  decode(HSTNUM_INDENT_STATUS,0,'Pending',1,'I Approval',10,'II Approval',45,'Indent Closed',20,'Partial Approved',40,'Final Approved',49,'In Process',99,'Rejected',111,'Items Received',50,'Processed',53,'Processed',HSTNUM_INDENT_STATUS) || '^'||");
		 			brMainQuery.append(" DECODE(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')");
		 			brMainQuery.append(" AS DATA FROM SSTT_INDENT_DTL A ");
		 			brMainQuery.append(" WHERE GNUM_ISVALID = 1 AND ");
		 			brMainQuery.append(" GNUM_HOSPITAL_CODE = ");
		 			brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
		 			brMainQuery.append(" AND  SSTNUM_REQTYPE_ID NOT IN(80,81,82,83,84,85)");
	    		}
	    		else
	    		{
	    			brMainQuery.append("SELECT HSTNUM_REQ_NO || '@' || HSTNUM_STORE_ID  || '@' || SSTNUM_REQTYPE_ID || '@' || A.SSTNUM_ITEM_CAT_NO || '@' || A.HSTNUM_URGENT_FLAG ||'@'|| HSTSTR_INDENT_PERIOD||'@'||HSTNUM_TOSTORE_ID || '^'|| A.HRGNUM_PUK  || '^'|| AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK::numeric)  || '^' || HSTNUM_REQ_NO ||  decode(is_auto_indent,1,'*')  || '^'||TO_CHAR(HSTDT_REQ_DATE,'DD-Mon-YYYY  hh24:mi')|| '^'|| ");
		 			brMainQuery.append("MMS_MST.GET_STORE_DTL(1,A.GNUM_HOSPITAL_CODE,HSTNUM_TOSTORE_ID) || '^' ||  decode(HSTNUM_INDENT_STATUS,0,'Pending',1,'I Approval',10,'II Approval',45,'Indent Closed',20,'Partial Approved',40,'Final Approved',49,'In Process',99,'Rejected',111,'Items Received',50,'Processed',53,'Processed',HSTNUM_INDENT_STATUS) || '^'||");
		 			brMainQuery.append(" DECODE(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')");
		 			brMainQuery.append(" AS DATA FROM SSTT_INDENT_DTL A ");
		 			brMainQuery.append(" WHERE GNUM_ISVALID = 1 AND nvl(hstnum_bs_refno,0) <> 0 AND ");
		 			brMainQuery.append(" GNUM_HOSPITAL_CODE = ");
		 			brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
		 			brMainQuery.append(" AND  SSTNUM_REQTYPE_ID NOT IN(80,81,82,83,84,85)");
	    		}
	 				
	 				
	    	}
	    	else if(!cmb[2].equals("0") && (cmb[2].replace("^","#").split("#")[1].equals("18")))
			    {
			    	brMainQuery.append("SELECT HSTNUM_REQ_NO || '@' || HSTNUM_STORE_ID  || '@' || SSTNUM_REQTYPE_ID || '@' || A.SSTNUM_ITEM_CAT_NO || '@' || A.HSTNUM_URGENT_FLAG ||'@'|| HSTSTR_INDENT_PERIOD||'@'||HSTNUM_TOSTORE_ID || '^'||HSTNUM_REQ_NO || '^'||TO_CHAR(HSTDT_REQ_DATE,'DD-Mon-YYYY  hh24:mi')|| '^'|| ");
			 		brMainQuery.append("MMS_MST.GET_STORE_DTL(1,A.GNUM_HOSPITAL_CODE,HSTNUM_TOSTORE_ID) || '^' || decode(HSTNUM_INDENT_STATUS,0,'Pending',1,'I Approval',10,'II Approval',45,'Issued',20,'Partial Approved',40,'Final Approved',49,'In Process',99,'Rejected',111,'Items Received',50,'Processed',53,'Processed',HSTNUM_INDENT_STATUS) || '^'|| ");
			 		brMainQuery.append("AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK::numeric) || '^'|| ");
			 		brMainQuery.append(" DECODE(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')");
			 		brMainQuery.append(" AS DATA FROM SSTT_INDENT_DTL A ");
			 		brMainQuery.append(" WHERE GNUM_ISVALID = 1 AND ");
			 		brMainQuery.append(" GNUM_HOSPITAL_CODE = ");
			 		brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
			 		brMainQuery.append(" AND  SSTNUM_REQTYPE_ID NOT IN(80,81,82,83,84,85)");
			    }
		    else
		    {
		    	brMainQuery.append("SELECT HSTNUM_REQ_NO || '@' || HSTNUM_STORE_ID  || '@' || SSTNUM_REQTYPE_ID || '@' || A.SSTNUM_ITEM_CAT_NO || '@' || A.HSTNUM_URGENT_FLAG ||'@'|| HSTSTR_INDENT_PERIOD||'@'||HSTNUM_TOSTORE_ID || '^'||HSTNUM_REQ_NO ||  decode(is_auto_indent,1,'*')  || '^'||TO_CHAR(HSTDT_REQ_DATE,'DD-Mon-YYYY  hh24:mi')|| '^'|| ");
		 		brMainQuery.append("MMS_MST.GET_STORE_DTL(1,A.GNUM_HOSPITAL_CODE,HSTNUM_TOSTORE_ID) || '^' || decode(HSTNUM_INDENT_STATUS,0,'Pending',1,'I Approval',10,'II Approval',45,'Issued',20,'Partial Approved',40,'Final Approved',49,'In Process',99,'Rejected',111,'Items Received',50,'Processed',53,'Processed',HSTNUM_INDENT_STATUS) || '^'|| ");
		 		brMainQuery.append("AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK::numeric) || '^'|| ");
		 		brMainQuery.append(" DECODE(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')");
		 		brMainQuery.append(" AS DATA FROM SSTT_INDENT_DTL A ");
		 		brMainQuery.append(" WHERE GNUM_ISVALID = 1 AND ");
		 		brMainQuery.append(" GNUM_HOSPITAL_CODE = ");
		 		brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
		 		brMainQuery.append(" AND  SSTNUM_REQTYPE_ID NOT IN(80,81,82,83,84,85)");
		    }
	    
	    }
	    else
	    {
	    	brMainQuery.append("SELECT HSTNUM_REQ_NO || '@' || HSTNUM_STORE_ID  || '@' || SSTNUM_REQTYPE_ID || '@' || A.SSTNUM_ITEM_CAT_NO || '@' || A.HSTNUM_URGENT_FLAG ||'@'|| HSTSTR_INDENT_PERIOD||'@'||HSTNUM_TOSTORE_ID || '^'||HSTNUM_REQ_NO  ||  decode(is_auto_indent,1,'*')  || '^'||TO_CHAR(HSTDT_REQ_DATE,'DD-Mon-YYYY  hh24:mi')|| '^'|| ");
	 		brMainQuery.append("MMS_MST.GET_STORE_DTL(1,A.GNUM_HOSPITAL_CODE,HSTNUM_TOSTORE_ID) || '^' ||  decode(HSTNUM_INDENT_STATUS,0,'Pending',1,'I Approval',10,'II Approval',45,'Issued',20,'Partial Approved',40,'Final Approved',49,'In Process',99,'Rejected',111,'Items Received',50,'Processed',53,'Processed',HSTNUM_INDENT_STATUS) || '^'|| ");
	 		brMainQuery.append("AHIS_FUNCTION.FUN_PAT_NAME(HRGNUM_PUK::numeric) || '^'|| ");
	 		brMainQuery.append(" DECODE(HSTNUM_URGENT_FLAG,1,'Urgent','Normal')");
	 		brMainQuery.append(" AS DATA FROM SSTT_INDENT_DTL A ");
	 		brMainQuery.append(" WHERE GNUM_ISVALID = 1 AND ");
	 		brMainQuery.append(" GNUM_HOSPITAL_CODE = ");
	 		brMainQuery.append( httpSession.getAttribute("HOSPITAL_CODE").toString());
	 		brMainQuery.append(" AND HSTNUM_STORE_ID in ( select HSTNUM_STORE_ID from HSTT_STORE_MST A where  GNUM_ISVALID = 1 and  GNUM_HOSPITAL_CODE ="+httpSession.getAttribute("HOSPITAL_CODE").toString()+" AND EXISTS ( SELECT 'X' FROM GBLT_ROLE_SEAT_TABLE_DTL P WHERE UPPER(gstr_table_name) = UPPER('HSTT_STORE_MST') AND UPPER(gstr_column_name) = UPPER('HSTNUM_STORE_ID')");
	 		brMainQuery.append(" AND P.gnum_hospital_code = A.gnum_hospital_code AND gnum_column_value = A.HSTNUM_STORE_ID AND P.gnum_seatid = Pkg_Usermgmt.fun_getseatid("+httpSession.getAttribute("SEATID").toString()+",A.GNUM_HOSPITAL_CODE)"+"))");
	 		brMainQuery.append(" AND  SSTNUM_REQTYPE_ID NOT IN(80,81,82,83,84,85)");
	    }
	    
	  
	    
	    
	   
	   
	    					
		if (cmb != null) 
		{ 			
			//financial date on the basis of store id
			if (!cmb[0].equals("0")) 
			{
				strTemp  =  cmb[0].split("\\^"); 
				strFinancialStartYear = mcu.getStrFinancialStartDate(strTemp[0] , httpSession.getAttribute("HOSPITAL_CODE").toString());
				strFinancialEndYear   = mcu.getStrFinancialEndDate(strTemp[0] , httpSession.getAttribute("HOSPITAL_CODE").toString());
			}
						
			//pending
//			if (cmb[3].equals("0"))
//			{
//				//brMainQuery.append( " AND  HSTNUM_INDENT_STATUS NOT IN(99,45,50)");
//			    brMainQuery.append(" AND HSTNUM_INDENT_STATUS = 0 AND HSTSTR_LST_APPROVE_SEATID IS NULL");
//			}
//			//partial approved
//			else
				if (cmb[3].equals("0"))
			{
			   brMainQuery.append(" AND ((HSTNUM_INDENT_STATUS >= 0 AND HSTNUM_INDENT_STATUS < 49) or HSTNUM_INDENT_STATUS = 99) ");
			}
//			//final approved
//			else if (cmb[3].equals("2"))
//			{
//				brMainQuery.append(" AND HSTNUM_INDENT_STATUS = 40");               
//			} 
//			//Partial Processed
//			else if (cmb[3].equals("3"))
//			{
//				brMainQuery.append(" AND HSTNUM_INDENT_STATUS = 49");
//			}
			//processed
			else if(cmb[3].equals("1") || cmb[3].equals("111"))
			{
				brMainQuery.append(" AND HSTDT_FINANCIAL_START_DATE = TO_DATE('"+strFinancialStartYear+"','dd-Mon-YYYY')");
				brMainQuery.append(" AND HSTDT_FINANCIAL_END_DATE = TO_DATE('"+strFinancialEndYear+"','dd-Mon-YYYY')");
				brMainQuery.append(" AND HSTNUM_INDENT_STATUS in (50,53)");
			}
//			//Rejected
//			else if (cmb[3].equals("5"))
//			{
//				brMainQuery.append(" AND HSTDT_FINANCIAL_START_DATE = TO_DATE('"+strFinancialStartYear+"','dd-Mon-YYYY')");
//				brMainQuery.append(" AND HSTDT_FINANCIAL_END_DATE = TO_DATE('"+strFinancialEndYear+"','dd-Mon-YYYY')");
//				brMainQuery.append(" AND HSTNUM_INDENT_STATUS = 99");
//			}	
			
			//request type
			if (!cmb[2].equals("0")) 
			{
				strTemp1 =  cmb[2].split("\\^");
				brMainQuery.append(" AND SSTNUM_REQTYPE_ID = ");
				brMainQuery.append(strTemp1[1]);
			}
			
			//category
			if (!cmb[1].equals("0")) 
			{
				brMainQuery.append(" AND SSTNUM_ITEM_CAT_NO = ");
				brMainQuery.append(cmb[1]);
			}
            
			//store id
			if (!cmb[0].equals("0")) 
			{
				strTemp  =  cmb[0].split("\\^"); 
				brMainQuery.append(" AND HSTNUM_STORE_ID = ");
				brMainQuery.append(strTemp[0]);
			}
			if(cmb[3].equals("111"))
			{
				brMainQuery.append(" AND HSTNUM_REQ_NO NOT IN (SELECT HSTNUM_BS_REFNO FROM SSTT_INDENT_DTL WHERE GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE AND HSTNUM_BS_REFNO IS NOT NULL AND HSTNUM_BS_REFNO <> 0 ) AND EXISTS ( SELECT 'X' FROM HSTT_ACKNOWLEDGE_DTL WHERE GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE AND SSTNUM_ITEM_CAT_NO = A.SSTNUM_ITEM_CAT_NO AND GNUM_ISVALID = 1 AND HSTNUM_REQUEST_NO in (SELECT HSTNUM_REQ_NO FROM HSTT_AGENDA_DTL WHERE GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE AND SSTNUM_ITEM_CAT_NO = A.SSTNUM_ITEM_CAT_NO AND GNUM_ISVALID = 1 AND HSTNUM_AGENDA_NO in (SELECT DISTINCT HSTNUM_AGENDA_NO");
				brMainQuery.append(" FROM HSTT_AGENDA_INDENT_DTL WHERE GNUM_HOSPITAL_CODE = A.GNUM_HOSPITAL_CODE AND SSTNUM_ITEM_CAT_NO = A.SSTNUM_ITEM_CAT_NO AND GNUM_ISVALID = 1 AND HSTNUM_REQ_NO = A.HSTNUM_REQ_NO) ))");
			}
		 }
		else
		{
			brMainQuery.append(" AND HSTNUM_INDENT_STATUS = 0 ");
		}	

		mcu = null;
		
		System.out.println("Demand desk Main Query-->>>>"+brMainQuery.toString());
		return brMainQuery.toString();
	}

	public String[] getSearchField() 
	{
		String search_field[] = {"1", "HSTNUM_REQ_NO"};
		
		
		if(cmbVal != null  && !cmbVal[2].equals("0") && cmbVal[2].replace("^","#").split("#")[1].equals("86") )
	    {
			search_field = new String[2];
			search_field[0] = "1";
			search_field[1] = "HRGNUM_PUK";
			//search_field[2] = "2";
			//search_field[3] = "HSTNUM_REQ_NO";
	    }
		if(cmbVal != null  && !cmbVal[2].equals("0") &&  (cmbVal[2].replace("^","#").split("#")[1].equals("13") || cmbVal[2].replace("^","#").split("#")[1].equals("18")))
		{
			search_field = new String[2];
			search_field[0] = "1";
			search_field[1] = "HRGNUM_PUK";
			//search_field[2] = "4";
			//search_field[3] = "HSTNUM_REQ_NO";
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
		//String[] strComboHeader = {"0^2","Drug Warehouse Name","0^0","Item Category","0^0","Request Type","1","Status"};
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] strComboHeader = new String[8];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{	

			strComboHeader[0] = "0^1";
			strComboHeader[1] = "Store Name";
			strComboHeader[2] = "0^2";
			strComboHeader[3] = "Category";
			strComboHeader[4] = "0^0";
			strComboHeader[5] = "Request Type";
			strComboHeader[6] = "1";
			strComboHeader[7] = "Status";

		}
		else
		{
			strComboHeader[0] = "0^1";
			strComboHeader[1] = "Store Name";
			strComboHeader[2] = "0^2";
			strComboHeader[3] = "Category";
			strComboHeader[4] = "0^0";
			strComboHeader[5] = "Request Type";
			strComboHeader[6] = "1";
			strComboHeader[7] = "Status";

		}
		
	    return strComboHeader;
	 
	}

	public String[] getColumnHeader() 
	{
		
		
		MmsConfigUtil mmscofigutil = new  MmsConfigUtil(httpSession.getAttribute("HOSPITAL_CODE").toString());	
		String[] strColumnHeader = new String[4];
		if (mmscofigutil.getStrStoreConfigCatg().equals("10")) 
		{	

			strColumnHeader[0] = "Indent No";
			strColumnHeader[1] = "Indent Date";
			strColumnHeader[2] = "Issuing Store";
			strColumnHeader[3] = "Status";
			
			if (cmbVal != null  && !cmbVal[2].equals("0") &&  cmbVal[2].replace("^","#").split("#")[1].equals("86"))
			{
				strColumnHeader = new String[5];
				strColumnHeader[0] =  "CR No.";
				strColumnHeader[1] =  "Indent No";
				strColumnHeader[2] =  "Indent Date";
				strColumnHeader[3] =  "Store";
				strColumnHeader[4] = "Status";
				
			}
			
		    
			
			if(cmbVal != null && !cmbVal[2].equals("0"))
			{			
				String[] tmp =  cmbVal[2].replace("^", "#").split("#");
				if(tmp[1].equals("13")  || tmp[1].equals("86"))
				{
					strColumnHeader = new String[6];
					
					strColumnHeader[0] =  "CR No.";
					strColumnHeader[1] =  "Patient Name";
				//	strColumnHeader[2] =  "Employee Name";
					strColumnHeader[2] =  "Indent No";
					strColumnHeader[3] =  "Indent Date";
					strColumnHeader[4] =  "Issuing Store";
					strColumnHeader[5] = "Status";
					
				}
				
				
				 if(cmbVal[3]!=null)
				 {	 
					if(tmp[1].equals("13")  || tmp[1].equals("86"))
					{
						strColumnHeader = new String[6];
						
						strColumnHeader[0] =  "CR No.";
						strColumnHeader[1] =  "Patient Name";
						//strColumnHeader[2] =  "Employee Name";
						strColumnHeader[2] =  "Indent No";
						strColumnHeader[3] =  "Indent Date";
						strColumnHeader[4] =  "Issuing Store";
						strColumnHeader[5] = "Status";
						
					}
				 }	
								
			}
			

		}
		else
		{
			strColumnHeader[0] = "Indent No";
			strColumnHeader[1] = "Indent Date";
			strColumnHeader[2] = "Issuing Store";
			strColumnHeader[3] = "Status";
			
			if(cmbVal != null && !cmbVal[2].equals("0"))
			{			
				String[] tmp =  cmbVal[2].replace("^", "#").split("#");
				if(tmp[1].equals("13")  || tmp[1].equals("86"))
				{
					strColumnHeader = new String[6];
					
					strColumnHeader[0] =  "CR No.";
					strColumnHeader[1] =  "Patient Name";
				//	strColumnHeader[2] =  "Employee Name";
					strColumnHeader[2] =  "Indent No";
					strColumnHeader[3] =  "Indent Date";
					strColumnHeader[4] =  "Issuing Store";
					strColumnHeader[5] = "Status";
					
					
				}
				 if(cmbVal[3]!=null)
				 {	 
					if(tmp[1].equals("13")  || tmp[1].equals("86"))
					{
						strColumnHeader = new String[6];
						
						strColumnHeader[0] =  "CR No.";
						strColumnHeader[1] =  "Patient Name";
					//	strColumnHeader[2] =  "Employee Name";
						strColumnHeader[2] =  "Indent No";
						strColumnHeader[3] =  "Indent Date";
						strColumnHeader[4] =  "Issuing Store";
						strColumnHeader[5] = "Status";
						
					}
				 }	
								
			}
		}
		/*
		String[] strColumnHeader = { "Indent No", "Indent Date", "Issuing Store"};
		
		
		if(cmbVal != null && !cmbVal[2].equals("0"))
		{			
			String[] tmp =  cmbVal[2].replace("^", "#").split("#");
			if(tmp[1].equals("12")||tmp[1].equals("13"))
			{
				strColumnHeader = new String[5];
				
				strColumnHeader[0] =  "Indent No";
				strColumnHeader[1] =  "Indent Date";
				strColumnHeader[2] =  "Issuing Store";
				strColumnHeader[3] =  "Patient Name";
				strColumnHeader[4] =  "Employee Name";
				
			}
			 if(cmbVal[3]!=null)
			 {	 
				if(tmp[1].equals("12")||tmp[1].equals("13"))
				{
					strColumnHeader = new String[5];
					
					strColumnHeader[0] =  "Indent No";
					strColumnHeader[1] =  "Indent Date";
					strColumnHeader[2] =  "Issuing Store";
					strColumnHeader[3] =  "Patient Name";
					strColumnHeader[4] =  "Employee Name";
					
				}
			 }	
							
		}
		*/
		
		return strColumnHeader;
		
		
	}      

	public String[] getComboQuery()
	{
		
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		
		
		String[] comboQuery = new String[5];
	    
	   
		comboQuery[0] = "select HSTNUM_STORE_ID|| '^' ||HSTNUM_STORETYPE_ID,HSTSTR_STORE_NAME from HSTT_STORE_MST A where  GNUM_ISVALID = 1 and  GNUM_HOSPITAL_CODE ="+hosCode+
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
		
		comboQuery[1] = mms.qryHandler_mms.getQuery(1,"select.StoreItemCategory.0");
        comboQuery[1] = comboQuery[1].concat(mms.qryHandler_mms.getQuery(1,"select.StoreItemCategory.cond.0")).replace("?",hosCode);

        System.out.println(comboQuery[1]);
		
        
        comboQuery[2] = "SELECT SSTNUM_FILE_TYPE||'^'||SSTNUM_INDENTTYPE_ID,SSTSTR_INDENTTYPE_NAME from SSTT_INDENTTYPE_MST A where SSTNUM_REQ_FOR = 1 and " +
		                " GNUM_HOSPITAL_CODE = "+MmsConfigUtil.GLOBAL_HOSPITAL_CODE+"  AND GNUM_ISVALID = 1 and sstnum_indenttype_id != 88  and  EXISTS (SELECT 'X' FROM HSTT_STORE_REQTYPE_MST " +
		                " WHERE  GNUM_ISVALID = 1 AND GNUM_HOSPITAL_CODE = "+hosCode+" AND SSTNUM_INDENTTYPE_ID = A.SSTNUM_INDENTTYPE_ID " +
		                " AND SSTNUM_ITEM_CAT_NO =#2#  AND  HSTNUM_STORE_ID = SUBSTR ( NVL(decode('#1#','null','00000000'),'#1#') , 1, 8)) " +
		                "ORDER BY SSTSTR_INDENTTYPE_NAME" ;
		
		System.out.println("comboQuery[2] "+comboQuery[2]);
					
	//	comboQuery[3] = "0^Pending#1^Partial Approved#2^Final Approved#3^In Process#4^Processed#5^Rejected#111^Items Received";
		comboQuery[3] = "0^Pending#2^Partial#1^Processed";
				

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
		String files = "../../mms/js/IndentTrans.js";
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
			    // strButtons = new String[6];
		         strButtons = new String[5];
				 strButtons[0] = "Generate@buttonLogicsOnClick1(1,'ADD','Add')@0@3b5998@glyphicon-plus";
				// strButtons[1] = "Modify@buttonLogicsOnClickModify(2,'MODIFY','Modify')@1";
				 strButtons[1] = "Cancel@buttonLogicsOnClickCancel(2,'REMOVE','Remove')@1@bb0000@glyphicon-remove";
			  	 strButtons[2] = "Return@buttonLogicsOnClickReturn(3,'RETURN','Return')@1@32506d@glyphicon-menu-left";
			  	 strButtons[3] = "View@buttonLogicsOnClick2(4,'VIEW','View')@1@007bb6@glyphicon-fullscreen";
			  	 strButtons[4] = "Print@buttonLogicsOnClickPrint(5,'PRINT','Print')@1@0c8d20@glyphicon-print";	
			  //	strButtons[5] = "Place Regular Indent@buttonLogicsOnClick2(6,'PLACEREGULARINDENT','')@1";
				
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
		String orderBy[] = { "2", "HSTDT_REQ_DATE", "1", "A.HSTNUM_REQ_NO" };
		return orderBy;
	}
	@Override
	public String[] getButtonIcons() {
	//	String[] str={"GenerateOnDesk.png","CancelOnDesk.png","ReturnOnDesk.png","ViewOnDesk.png","PrintOnDesk.png","icon-default.png"};
		String[] str={"GenerateOnDesk.png","CancelOnDesk.png","ReturnOnDesk.png","ViewOnDesk.png","PrintOnDesk.png"};
		// TODO Auto-generated method stub
		return str;
	}
	

}
