package billing.transactions;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import billing.BillingBO;
import billing.BillingVO;
import billing.PaymentModeHLP1;

public class ClientVerificTransDATA 
{
	
	
	public static void genrateBill(HttpServletRequest request , ClientVerificTransFB formBean)
	{
     try
       {
	  	//	System.out.println("Inside DATA length is:::"+formBean.getStrCat().length);
//    		System.out.println("Gr Name ::::-->"+formBean.getStrGrName());
//    		System.out.println("Gr Name Date::::-->"+formBean.getStrGRDate());
    		
			for(int i=0;i<formBean.getStrCat().length;i++)
			{
//				System.out.println("Category::::"+formBean.getStrCat()[i]);
//				System.out.println("PKG::::"+formBean.getStrPkg()[i]);
//				System.out.println("Batch::::"+formBean.getStrBatch()[i]);
			}

		}
	  	catch (Exception e) 
	  	{
			new HisException("Billing Tranasction : Client Verification Transaction","ClientVerificTransDATA.initWithoutCrNo()", e.getMessage());

		} 
	  	
	}
	
	
	public static void ClientDetail(String strChk,ClientVerificTransFB formBean) 
	{
		
		ClientVerificTransVO vo = new ClientVerificTransVO();
		vo.setStrChk(strChk);
	
		ClientVerificTransBO bo = new ClientVerificTransBO();
		try
		{
          bo.setRequiredValues(vo);
	      TransferObjectFactory.populateData(formBean,vo);
        
		} 
		catch (Exception e) 
		{
			new HisException("Client Verification Transaction",
					"ClientVerificationDATA",
					"ClientVerificationTransDATA.ClientDetail()-->"
							+ e.getMessage());
		} finally {

		
				vo = null;
				bo = null;
			
		}

	}
	public static void initClientDetail(String strChk,ClientVerificTransFB formBean) 
	{
		
		ClientVerificTransVO vo = null;
	
		ClientVerificTransBO bo = null;
		
		try
		{
			
			
			vo = new ClientVerificTransVO();
			 bo = new ClientVerificTransBO();
			
			 vo.setStrChk(strChk);
			 
          bo.setRequiredValues(vo);
          String strTemp[] = strChk.replace('@', '#').split("#");
 	      formBean.setStrChk(strTemp[0]);
          formBean.setStrPaymentType(vo.getStrPaymentType());	    
  		} catch (Exception e) {

			new HisException("Client Verification Transaction",
					"ClientVerificationDATA",
					"ClientVerificationTransDATA.initClientDetail()-->"
							+ e.getMessage());
		} finally {

			
				vo = null;
				bo = null;
					}

	}
	//////////////////////Getting WS Value ////////////////////////////////////////
	public static WebRowSet getClientDtlsWs(String strChk,ClientVerificTransFB formBean)
	{
		
		WebRowSet ws = null;
		BillingBO boObj = new BillingBO();
		BillingVO voObj = new BillingVO();
		voObj.setStrValue1(strChk);
	//	boObj.getPatientDetails(voObj);
		boObj.getClientDetails(voObj);
		
		ws = voObj.getGblWs1();
		
    	formBean.setStrMsgString(voObj.getStrMsgString());
		formBean.setStrMsgType(voObj.getStrMsgType());
		
		if(formBean.getStrMsgType().equals("0")){
			
			if(voObj.getGblWs1().size() == 0){
				
				formBean.setStrMsgString("Invalid Client Detail");
				formBean.setStrMsgType("1");
			}
			else
			{
		    	ws = voObj.getGblWs1();
		   	}
		}
		
		return ws;
		
	}

	
	/////////////////////////GET AJAX FUNCTION RESPONSE//////////////////
	public static String AjaxResponse(ClientVerificTransFB formBean) 
	{

		ClientVerificTransVO vo = null;
		
		ClientVerificTransBO bo = null;
		
		String paymentDetails = null;		
	    Integer i = Integer.parseInt(formBean.getStrPaymentMode());

		
		try
		{
			
			vo = new ClientVerificTransVO();
			bo = new ClientVerificTransBO();
			
        ///////////////Set the Web Row Set in formBean///////////////////// 
	     bo.setRequiredValues(vo);
	     if(i==2)
	     {
	    	 paymentDetails = PaymentModeHLP1.getChequeDetails();
	     }
	     if(i==3)
	     {
	    	 paymentDetails = PaymentModeHLP1.getDDDetails();	 
	     } 
	     if(i==4||i==5)
	     {
	    	 paymentDetails = PaymentModeHLP1.getCardDetails();
	     }
  
         ////////////////////////////////////////////////////////
		} catch (Exception e) {

			new HisException("Client Verification Transaction",
					"ClientVerificationDATA",
					"ClientVerificationTransDATA.AjaxResponse()-->"
							+ e.getMessage());
		} finally {

		
				vo = null;
				bo = null;
		}
     return paymentDetails;
	}
	
	/////////////////////////GET AJAX FUNCTION RESPONSE//////////////////
	public static String InvoiceDtl(ClientVerificTransFB formBean) 
	{
		
		ClientVerificTransVO vo = null;
		ClientVerificTransBO bo = null;
		
        boolean fretValue = false;
	
//		String paymentDetails = null;
        String InvoiceDtl = new String();
	
		PaymentModeHLP1 hlp = new PaymentModeHLP1();
		try
		{
			
			vo = new ClientVerificTransVO();
			bo = new ClientVerificTransBO();
			
			String s = formBean.getStrInvoiceNo();
		    vo.setStrInvoiceNo(s);
		
			
			fretValue =bo.INVOICEDTL(vo);
			if (fretValue) 
		    {   
				InvoiceDtl=hlp.getInvoiceDetails(vo);
		    }	
		}
		catch (Exception e) 
		{

			new HisException("Client Verification Transaction",
					"ClientVerificationDATA",
					"ClientVerificationTransDATA.InvoiceDtl()-->"
							+ e.getMessage());
		} finally {

			bo = null;
				vo = null;
			
		}
		
     return InvoiceDtl;
	}
	
	
	
	//////////////////////////INVOICE////////////////////////////////////
	public static void INVOICE(String strChk,ClientVerificTransFB formBean) 
	{
		
		ClientVerificTransVO vo = null;
		ClientVerificTransBO bo = null;
		
	
		try
		{
			
			vo = new ClientVerificTransVO();
			bo = new ClientVerificTransBO();
            
			vo.setStrChk(strChk);
			
	        bo.getValues(vo);
	        formBean.setStrCrComboDtl(vo.getStrCrComboDtl());
//	        System.out.println("Combo Dtl--->"+formBean.getStrCrComboDtl());
     
		}
		catch (Exception e) {

			new HisException("Client Verification Transaction",
					"ClientVerificationDATA",
					"ClientVerificationTransDATA.initClientDetail()-->"
							+ e.getMessage());
		} finally {

				bo = null;
				vo = null;
			
		}
	}
	//////////////////////////DEPOSIT////////////////////////////////////
	public static void DEPOSIT(String strChk,ClientVerificTransFB formBean) 
	{
		
		ClientVerificTransVO vo = null;
		ClientVerificTransBO bo = null;
		
	
		try
		{
			
			vo = new ClientVerificTransVO();
			bo = new ClientVerificTransBO();
            
			vo.setStrChk(strChk);
			
			
            bo.getValues(vo);
            bo.getInvoiceComboValues(vo);
	        formBean.setStrInvoiceComboDtl(vo.getStrInvoiceComboDtl());
	    //    System.out.println("Combo Dtl--->"+formBean.getStrInvoiceComboDtl());
		}
		catch (Exception e) {

			new HisException("Client Verification Transaction",
					"ClientVerificationDATA",
					"ClientVerificationTransDATA.initClientDetail()-->"
							+ e.getMessage());
		} finally {

				bo = null;
				vo = null;
		
		}
	}
	//////////////////////////INSERT POST-PAID///////////////////////////////////////
	public static void INSERTPOST(ClientVerificTransFB myBeanObj) 
	{
		String msgStr = "";
		boolean fretValue = true;

		ClientVerificTransVO voObj = null;
		ClientVerificTransBO bo = null;

		try {

			voObj = (ClientVerificTransVO)TransferObjectFactory.populateData("billing.transactions.ClientVerificTransVO",myBeanObj);
			
			bo = new ClientVerificTransBO();
			
		     fretValue =  bo.INSERT(voObj);
			 if(fretValue == true)
			 {
				 TransferObjectFactory.populateData(myBeanObj,voObj);
			 }
					
			myBeanObj.setMsgString(voObj.getMsgString());
			myBeanObj.setMsgType(voObj.getMsgType());
			
			//check error
			if(myBeanObj.getMsgType().equals("1"))
			{	
				//error
				throw new Exception(myBeanObj.getMsgString());
			}
		}
		catch(Exception e) {
			msgStr = "ClientVerificTransDATA.INSERT() --> " + e.getMessage();
			new HisException("Module_Name","file_name",msgStr);
		}
		finally {
			bo = null;
			 voObj = null;
		}
	}
	////////////////////////////////INSERT PRE-PAID//////////////////////////////////////////////////////////
	public static void INSERTPRE(ClientVerificTransFB myBeanObj) 
	{
		String msgStr = "";
		boolean fretValue = true;

		ClientVerificTransVO voObj = null;
		ClientVerificTransBO bo = null;


		try {

			voObj = (ClientVerificTransVO)TransferObjectFactory.populateData("billing.transactions.ClientVerificTransVO",myBeanObj);
				
			bo = new ClientVerificTransBO();
			
			 fretValue =  bo.INSERTPRE(voObj);
			
			 if(fretValue == true)
			 {
				 TransferObjectFactory.populateData(myBeanObj,voObj);
			  // myBeanObj.setStrInvoiceDate(myBeanObj.getStrInvoiceDate());
			 }
								
			myBeanObj.setMsgString(voObj.getMsgString());
			myBeanObj.setMsgType(voObj.getMsgType());
			
			//check error
			if(myBeanObj.getMsgType().equals("1"))
			{	
				//error
				throw new Exception(myBeanObj.getMsgString());
			}
		}
		catch(Exception e) {
			msgStr = "ClientVerificTransDATA.INSERT() --> " + e.getMessage();
			new HisException("Module_Name","file_name",msgStr);
		}
		finally {
			bo = null;
			voObj = null;
		}
	}
	
	///////////////////////////TEST NEW ARCHITECTURE///////////////////////
//	public static void ClientDetailString(String strChk,ClientVerificTransFB formBean) 
//	{
//		
//		ClientVerificTransVO vo = new ClientVerificTransVO();
//		HisUtil util = null;
//	    ClientVerificTransBO bo = new ClientVerificTransBO();
//		try
//		{
//        ///////////////Set the Web Row Set in formBean///////////////////// 
//	     String clientstr = HLPbilling.getHeaderDtl(strChk);
//	     System.out.println("Is Here-->"+clientstr);
//        //////////////////////////////////////////////////////////////////   
//        
//		} catch (Exception e) {
//
//			new HisException("Client Verification Transaction",
//					"ClientVerificationDATA",
//					"ClientVerificationTransDATA.initClientDetail()-->"
//							+ e.getMessage());
//		} finally {
//
//			if (vo != null)
//				vo = null;
//			if (formBean != null)
//				formBean = null;
//			if (util != null)
//				util = null;
//		}
//
//	}
	
	
	/////////////////////////////////////////////////////////////////////
	
  }
