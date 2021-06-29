package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;
public class ClientVerificTransDAO 
{
	
	public static String getCRCombo(ClientVerificTransVO voObj) 
	{
        
      	HisDAO daoObj = null;
		WebRowSet web5 = null;
		String str = voObj.getStrPk();
		String str2 = "";
		HisUtil hisutil = new HisUtil("transaction", "ClientVerificTransDAO");
		
		String strTemp[] = str.replace('@', '#').split("#");
		int i = Integer.parseInt(strTemp[0]);  //Customer ID
		int nqryIndex5;
        
		String strQuery5 = new String();

    	try {
			   daoObj = new HisDAO("Client Verification Transaction",
					               "ClientVerificationDAO");
			   
			    strQuery5 = " SELECT HRGNUM_PUK,HBLSTR_CARD_NO,HBLSTR_CARDHOLDER_NAME,SBLNUM_CHARGETYPE_ID,HBLSTR_AUTH_NO, TO_CHAR (HBLDT_AUTH_DATE, 'dd-Mon-yyyy'),HBLNUM_SANC_LIMIT, "+
		          " HBLNUM_CLIENT_RECD_AMT, HBLNUM_CLIENT_EXPENSE_AMT,HBLNUM_CLIENT_PATIENT_NO,HBLNUM_CLIENT_PATIENT_SLNO,HRGNUM_PUK "+ 
		          " FROM hblt_client_patient_dtl  WHERE HBLNUM_CLIENT_NO = "+i+" AND HBLNUM_STATUS = 1 AND GNUM_ISVALID =1 AND HBLNUM_PAYMENT_STATUS =0 ";			 

			         nqryIndex5 = daoObj.setQuery(strQuery5);
				     web5 = daoObj.executeQry(nqryIndex5);
				     str2 = hisutil.getOptionValue(web5, "0", "0^Select value", true);
		    }
		    catch (Exception e) 
		    {
            // System.out.println("ClientVerificationDAO"+e.getMessage());
            } 
		    finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
     return str2;
	}

	public String PaymentType(ClientVerificTransVO voObj) 
	{
        HisDAO daoObj = null;
        String payType = null;
		WebRowSet web = null;
		String strErr = "";
		String str = voObj.getStrChk();
		
		String strTemp[] = str.replace('@', '#').split("#");
		int i = Integer.parseInt(strTemp[0]);  //Customer ID
		int nqryIndex;
  	
		String strQuery = new String();
    	try {
			   daoObj = new HisDAO("Client Verification Transaction",
					               "ClientVerificationDAO");
		  	   
			   strQuery = "SELECT HBLNUM_PAYMENT_TYPE FROM HBLT_CLIENT_MST WHERE HBLNUM_CLIENT_NO = "+i+"  AND GNUM_ISVALID = 1";
			   
			   nqryIndex = daoObj.setQuery(strQuery); 
			   
			    if (strErr.equals(""))
				{

					web = daoObj.executeQry(nqryIndex);
					 while (web.next())
					 {
					    payType = web.getString(1);
					 }

				} 
			    else 
			    {
					throw new Exception(strErr);
				}
			

		} 
    	catch (Exception e) 
		{

			
			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.setClientDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}
	 finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	  return payType;  
    }
	
    public  String CRCOMBO(ClientVerificTransVO VO) 
	{
		String	str2 = null;
		String str = VO.getStrPk();
		HisUtil hisutil = new HisUtil("transaction", "ClientVerificTransDAO");
		String strTemp[] = str.replace('@', '#').split("#");
		String proc_name ="";
		
		proc_name ="{call cr_combo1(?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;


		String strerr = "";

		WebRowSet ws = null;


		try {

		dao = new HisDAO("ClientVerificTrans","transactions.ClientVerificTransDAO.CRCOMBO()");

		nprocIndex = dao.setProcedure(proc_name);
	
		//set value
  
		dao.setProcInValue(nprocIndex,"CLIENTNO",strTemp[0]);

		dao.setProcOutValue(nprocIndex,"ERR",1); //1 for string return value

		dao.setProcOutValue(nprocIndex,"RESULTSET",2); //2 for object


		//execute procedure

		dao.executeProcedure(nprocIndex);


		//get value

		strerr = dao.getString(nprocIndex,"ERR");

	
		if(strerr == null) strerr = "";


		ws = dao.getWebRowSet(nprocIndex,"RESULTSET");
	   
		if(strerr.equals("")) 
		{
			 str2 = hisutil.getOptionValue(ws, "0", "0^Select value", true);
		}

		else 
		{
			VO.setMsgString("err"); 
		 // throw new Exception(err);

		}

		}

		catch(Exception e) {

		e.printStackTrace();

		VO.setStrMsgString("ClientVerificTransDAO.CRCOMBO() --> " + e.getMessage());

		VO.setStrMsgType("1");

		}

		finally {

		if(dao != null) {

		dao.free();

		dao = null;

		}

		}
		 return str2;
		}
	public String INVOICECOMBO(ClientVerificTransVO VO) 
	{
		
		
		String	str2 = null;

		HisUtil hisutil = new HisUtil("transaction", "ClientVerificTransDAO");

		String proc_name ="";
		
		proc_name ="{call Invoice_No_combo(?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;


		String strerr = "";

		WebRowSet ws = null;


		try {

		dao = new HisDAO("ClientVerificTrans","transactions.ClientVerificTransDAO.INVOICECOMBO()");

		nprocIndex = dao.setProcedure(proc_name);

		//set value

		dao.setProcOutValue(nprocIndex,"ERR",1); //1 for string return value

		dao.setProcOutValue(nprocIndex,"RESULTSET",2); //2 for object


		//execute procedure

		dao.executeProcedure(nprocIndex);


		//get value

		strerr = dao.getString(nprocIndex,"ERR");

		

		if(strerr == null) strerr = "";


		ws = dao.getWebRowSet(nprocIndex,"RESULTSET");
	   
		if(strerr.equals("")) 
		{
			 str2 = hisutil.getOptionValue(ws, "0", "0^Select value", true);
	 	}

		else 
		{
          VO.setMsgString(strerr);
		 
		}

		}

		catch(Exception e) {

		e.printStackTrace();

		VO.setStrMsgString("ClientVerificTransDAO.INVOICECOMBO() --> " + e.getMessage());

		VO.setStrMsgType("1");

		}

		finally {

		if(dao != null) {

		dao.free();

		dao = null;

		}

		}
		 return str2;
		}
	
	////////////////////////INSERT//////////////////////////////////////
	public static boolean INSERT(ClientVerificTransVO myVOObj)
	{
		boolean fretValue = true;
		boolean fretValue1 = false;

		HisDAO daoObj = null;
		WebRowSet web5 = null;
	
        int nqryIndex5,ncount=0;
        
        String strQuery5 = new String();
		if (fretValue) 
	    {   
			
		    fretValue1 = INSERTWTHPROC(myVOObj);

	      
			if (fretValue1) 
			{
				try {
					   daoObj = new HisDAO("Client Verification Transaction",
							               "ClientVerificationDAO");
			//		   clientID = Integer.parseInt(myVOObj.getStrClientID());
			//		   String strPayemntmode = myVOObj.getStrPaymentType();
					   
					   strQuery5 = " SELECT HBLNUM_BILL_NO, TO_CHAR (HBLDT_PAYMENT_DATE, 'dd-Mon-yyyy'),SBLNUM_BSERVICE_ID,HBLNUM_AMOUNT" +
				                   " FROM HBLT_PAYMENT_DETAIL  WHERE HBLNUM_BILL_NO= 1001 AND HBLNUM_PAYMENT_MODE = 6 AND HBLNUM_SLNO =1 ";			 
		     		

					   nqryIndex5 = daoObj.setQuery(strQuery5);
					   web5 = daoObj.executeQry(nqryIndex5);
					 
						     
					   while (web5.next())
					   {
						    myVOObj.setStrBillNo(web5.getString(1));
						    myVOObj.setStrBillDate(web5.getString(2));
						    myVOObj.setStrService(web5.getString(3));
						    myVOObj.setStrBillAmt(web5.getString(4));
						    ncount = Integer.parseInt(web5.getString(1));
						}
						if (ncount != 0) 
						{
							fretValue = true;
						} else 
						{
							fretValue = false;
						}
					     
						 ////////////////////////////////////////
				    }
				    catch (Exception e) 
				    {
		             System.out.println("ClientVerificationDAO"+e.getMessage());
					} 
				    finally {
					if (daoObj != null) 
					{
						daoObj.free();
						daoObj = null;
					}
			     }
			}
		}
		return fretValue;
		
	}

	////////////////////INSERT1-WITH PROC////////////////////////////
	
	public static boolean INSERTWTHPROC(ClientVerificTransVO myVOObj) 
	{
		
		String strerr = "";
		boolean fretValue = false;
		HisDAO dao = null;
		int procIndex=0,procIndex1=0,procIndex2=0,expAmt=0,sencAmt=0,InvoAmt=0;
		String str = null;
        String proc_name ="",proc_name1 ="",proc_name2 ="";
		
		 //Calling Procedure
            proc_name  ="{call Insert_Proc_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            proc_name1 ="{call update_proc(?,?,?,?,?)}";
            proc_name2 ="{call update_proc1(?,?,?,?,?,?)}";

		try {
		
		    dao = new HisDAO("Client Verificaton","transactions.Client VerificatonDAO.INSERTWTPROC()");
			str = myVOObj.getStrCrNo();
			String  strTemp[] = str.replace('^', '#').split("#");
			String strTemp2[] = strTemp[2].replace('^', '#').split("#");
			//String strTemp3[] = strTemp[3].replace('^', '#').split("#");
			String strTemp4[] = strTemp[4].replace('^', '#').split("#");
			String strTemp5[] = strTemp[5].replace('^', '#').split("#");
			String strTemp6[] = strTemp[6].replace('^', '#').split("#");
			String strTemp7[] = strTemp[7].replace('^', '#').split("#");
			String strTemp8[] = strTemp[8].replace('^', '#').split("#");
			String strTemp9[] = strTemp[9].replace('^', '#').split("#");
			String strTemp10[] = strTemp[10].replace('^', '#').split("#");
			String CDate = myVOObj.getStrCtDate();
			expAmt  = Integer.parseInt(strTemp8[0]);
		    sencAmt = Integer.parseInt(strTemp6[0]);
			InvoAmt  = Integer.parseInt(strTemp8[0]);
           if(expAmt>sencAmt)
           {
           	InvoAmt = sencAmt - expAmt;

           }
           else
           {
           	InvoAmt =  expAmt - sencAmt+1000;

           }  	
            String InvoAmt1 = String.valueOf(InvoAmt); 
            String rmk = myVOObj.getStrRemarks();
            myVOObj.setStrInvoiceNo("1004");
			myVOObj.setStrInvoiceDate(CDate);
			myVOObj.setStrInvoiceAmt(InvoAmt1);
			myVOObj.setStrClientName("Amit");
			myVOObj.setStrClientAdd("NOIDA");
            myVOObj.setStrCrNo(strTemp[0]);
            myVOObj.setStrCardNo(strTemp[1]);
            myVOObj.setStrCardHolderName(strTemp2[0]);
            myVOObj.setStrApprovedFor("Amit");
            myVOObj.setStrAutNo(strTemp4[0]);
            myVOObj.setStrAuthDate(strTemp5[0]);
            myVOObj.setStrExpenseAmt(strTemp8[0]);
            myVOObj.setStrRecAmt(strTemp7[0]);
            myVOObj.setStrSancAmt(strTemp6[0]);
            String cltno = new String("1001");
            String receiveamt = new String("0");
            String invoicestatus = new  String("1");
            String lstmodseatid = new String("1001");
            String seatid = new String("1");
            String isvalid = new String("1");
            
            //Ist Insert Procedure  
            procIndex = dao.setProcedure(proc_name);
			//set value
			dao.setProcInValue(procIndex,"cltinvoiceno",strTemp9[0]);
			dao.setProcInValue(procIndex,"cltinvoicedate",strTemp5[0]);
			dao.setProcInValue(procIndex,"cltno",cltno);
			dao.setProcInValue(procIndex,"pukno",strTemp[0]);
			dao.setProcInValue(procIndex,"invoiceamt",InvoAmt1);
			dao.setProcInValue(procIndex,"receiveamt",receiveamt);
			dao.setProcInValue(procIndex,"invoicestatus",invoicestatus);
			dao.setProcInValue(procIndex,"remarks",rmk);
			dao.setProcInValue(procIndex,"lstmoddate",CDate);
			dao.setProcInValue(procIndex,"lstmodseatid",lstmodseatid);
			dao.setProcInValue(procIndex,"entrydate",CDate);
			dao.setProcInValue(procIndex,"seatid",seatid);
			dao.setProcInValue(procIndex,"isvalid",isvalid);
			dao.setProcOutValue(procIndex,"ERR",1); //1 for string return value
           
          
            // II nd Update Procedure
              procIndex1 = dao.setProcedure(proc_name1);
            //Set Value
            dao.setProcInValue(procIndex1,"paymentstatus","1");
		    dao.setProcInValue(procIndex1,"clientpaitno",strTemp9[0]);
		    dao.setProcInValue(procIndex1,"clientpaitslno",strTemp10[0]);
		    dao.setProcInValue(procIndex1,"isvalid  ","1");
		    dao.setProcOutValue(procIndex1,"ERR",1); //1 for string return value
		    
		    //III rd Update Procedure
		    procIndex2 = dao.setProcedure(proc_name2);
		    //Set Values
		    dao.setProcInValue(procIndex2,"paymentstatus","1");
		    dao.setProcInValue(procIndex2,"clientpaitslno",strTemp10[0]);
		    dao.setProcInValue(procIndex2,"paymentmode","6");
		    dao.setProcInValue(procIndex2,"paymentmodeno",strTemp9[0]);
		    dao.setProcInValue(procIndex2,"billno","1001");
		    dao.setProcOutValue(procIndex2,"ERR",1); //1 for string return value
         
    		//execute procedure
    		    	
    	
    		  dao.execute(procIndex,1); 	
              dao.execute(procIndex1,1);
              dao.execute(procIndex2,1);
           
			synchronized(dao)
			{
				dao.fire();
				fretValue = true;
			}
			
			//get value
			strerr = dao.getString(procIndex,"ERR");
			
			
					
			if(strerr == null) strerr = "";
			if(strerr.equals("")) 
			{
				strerr = dao.getString(procIndex1,"ERR");
				
				if(strerr == null) strerr = "";
				if(strerr.equals("")) 
				{
					strerr = dao.getString(procIndex2,"ERR");
					
					
					if(strerr == null) strerr = "";
					if(strerr.equals("")) 
					{
						
						myVOObj.setStrMsgType("0");
						
						
				     }else{
				    	 
				    	 throw new Exception(strerr);
				    	 
				     }
					
					
			     }else{
			    	 
			    	 throw new Exception(strerr);
			    	 
			     }
				
			     
		     }else{
		    	 
		    	 throw new Exception(strerr);
		    	 
		     }
		}
		catch(Exception e)
		{
			
			myVOObj.setMsgString("ClientVerificationDAO.INSERTWITHPROC() --> " + e.getMessage());
			myVOObj.setMsgType("1");
		}
		finally 
		{
			if(dao != null)
			{
				dao.free();
				dao = null;
			}
		}
		return fretValue;
	}
 
//////////////////////////////////////////////////////////////////////////////
	public WebRowSet getBalanceAmt(ClientVerificTransVO VO)
	{
		String str = VO.getStrChk();
		
		String strTemp[] = str.replace('@', '#').split("#");
	
		String proc_name ="";
		
		proc_name ="{call Balance_Amt(?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strErr = "";

		WebRowSet ws = null;


		try {

		dao = new HisDAO("ipd","transactions.ClientVerificTransDAO.getBalanceAmt()");

		nprocIndex = dao.setProcedure(proc_name);

 	   //set value


		dao.setProcInValue(nprocIndex,"clientno",strTemp[0]);
		
		dao.setProcInValue(nprocIndex,"clientslno",strTemp[1]);

		dao.setProcOutValue(nprocIndex,"ERR",1); //1 for string return value

		dao.setProcOutValue(nprocIndex,"RESULTSET",2); //2 for object


		//execute procedure

		dao.executeProcedure(nprocIndex);


		//get value

		strErr = dao.getString(nprocIndex,"ERR");

	
		if(strErr == null) strErr = "";


		ws = dao.getWebRowSet(nprocIndex,"RESULTSET");
	   
		}

		catch(Exception e) {

		e.printStackTrace();

		VO.setStrMsgString("MsApprovalTransDAO.occupationdtl() --> " + e.getMessage());

		VO.setStrMsgType("1");

		}

		finally {

		if(dao != null) {

		dao.free();

		dao = null;

		}

		}
		
		 return ws;
		
	}

//////////////////////////////////////////////////////////////////////////////
public static boolean INSERTPRE(ClientVerificTransVO myVOObj) 
{
	
	String strErr = "";
	
	boolean fretValue = false;

	HisDAO dao = null;

	int nprocIndex = 0,nexpAmt=0,nsencAmt=0,nInvoAmt=0;

	String str = null;

	try {
		str = myVOObj.getStrCrNo();
		String  strTemp[] = str.replace('^', '#').split("#");
	
		String strTemp2[] = strTemp[2].replace('^', '#').split("#");
	//	String strTemp3[] = strTemp[3].replace('^', '#').split("#");
		String strTemp4[] = strTemp[4].replace('^', '#').split("#");
		String strTemp5[] = strTemp[5].replace('^', '#').split("#");
		String strTemp6[] = strTemp[6].replace('^', '#').split("#");
		String strTemp7[] = strTemp[7].replace('^', '#').split("#");
		String strTemp8[] = strTemp[8].replace('^', '#').split("#");
		String strTemp9[] = strTemp[9].replace('^', '#').split("#");
		//String strTemp10[] = strTemp[10].replace('^', '#').split("#");
		String rmk = myVOObj.getStrRemarks();
		String CDate = myVOObj.getStrCtDate();
		nexpAmt   = Integer.parseInt(strTemp8[0]);
		nsencAmt  = Integer.parseInt(strTemp6[0]);
		nInvoAmt  = Integer.parseInt(strTemp8[0]);
            if(nexpAmt>nsencAmt)
           {
           	nInvoAmt = nsencAmt - nexpAmt;
         //  	System.out.println("InvAmt-->"+InvoAmt);
           }
           else
           {
           	nInvoAmt =  nexpAmt - nsencAmt+1000;
        // 	System.out.println("InvAmt-->"+InvoAmt);
           }  	
        String InvoAmt1 = String.valueOf(nInvoAmt); 
        String aprovdFor = new String("Akshay");
        myVOObj.setStrInvoiceNo(strTemp9[0]);
		myVOObj.setStrInvoiceDate(CDate);
		myVOObj.setStrInvoiceAmt(InvoAmt1);
		myVOObj.setStrClientName("Amit");
		myVOObj.setStrClientAdd("NOIDA");
        myVOObj.setStrCrNo(strTemp[0]);
        myVOObj.setStrCardNo(strTemp[1]);
        myVOObj.setStrCardHolderName(strTemp2[0]);
        myVOObj.setStrApprovedFor(aprovdFor);
        myVOObj.setStrAutNo(strTemp4[0]);
        myVOObj.setStrAuthDate(strTemp5[0]);
        myVOObj.setStrExpenseAmt(strTemp8[0]);
        myVOObj.setStrRecAmt(strTemp7[0]);
        myVOObj.setStrSancAmt(strTemp6[0]);
		myVOObj.setStrRemarks(rmk);
		
//		System.out.println("PUK->"+strTemp[0]);
//		System.out.println("Card No->"+strTemp[1]);
//		System.out.println("CarHolder->"+strTemp2[0]);
//		System.out.println("Charg Type->"+strTemp3[0]);
//		System.out.println("Auth No->"+strTemp4[0]);
//		System.out.println("Auth Date->"+strTemp5[0]);
//		System.out.println("Sac Lmt->"+strTemp6[0]);
//		System.out.println("Clt Rec Amt->"+strTemp7[0]);
//		System.out.println("Clt Exp->"+strTemp8[0]);
//		System.out.println("Clt Pt No->"+strTemp9[0]);
//		System.out.println("Clt Pt Sl No->"+strTemp10[0]);
		
		
		fretValue = true;

		strErr = dao.getString(nprocIndex,"err");
		if(strErr == null) strErr = "";
		if(strErr.equals("")) 
		{
			 WebRowSet ws = dao.getWebRowSet(nprocIndex,"data");
		     if(strErr.equals(""))
		     {
			 myVOObj.setWs(ws);
		 	 myVOObj.setMsgType("0");
		     }
		     else
		     {
		       myVOObj.setMsgString(strErr);	 
		    }
	     }
	}
	catch(Exception e)
	{
		myVOObj.setMsgString("myDAO.INSERT() --> " + e.getMessage());
		myVOObj.setMsgType("1");
	}
	finally 
	{
		if(dao != null)
		{
			dao.free();
			dao = null;
		}
	}
	return fretValue;
}
}

 


