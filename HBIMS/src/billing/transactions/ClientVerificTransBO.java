package billing.transactions;


import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class ClientVerificTransBO 
{

 public void setRequiredValues(ClientVerificTransVO voObj) 
 {
	 WebRowSet web = null;
		try {
			    ClientVerificTransDAO cltDao = new ClientVerificTransDAO();
     	 	    String paytype = cltDao.PaymentType(voObj);
			    voObj.setStrPaymentType(paytype);
		        web = cltDao.getBalanceAmt(voObj);
			    while (web.next())
				 {
			    	voObj.setStrBgAmount(web.getString(6));
			    	voObj.setStrBgReNewalDate(web.getString(7));
			    	voObj.setStrBankName(web.getString(8));
			    	voObj.setStrBankAddress(web.getString(9));
			    	voObj.setStrIPDDiscount(web.getString(11));	
			    	voObj.setStrOPDDiscount(web.getString(12));
			    	voObj.setStrEMEDiscount(web.getString(13));
				    voObj.setStrBalanceAmt (web.getString(14));
				    voObj.setStrOldExpiryDate(web.getString(15));
				    voObj.setStrEffectiveFrmDate(web.getString(21));
	     		 }
			    
	    		    
		    } catch (Exception e) {

			voObj.setStrMsgString("ClientVerificTransBO.setRequiredValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

  }
  
 public void getValues(ClientVerificTransVO voObj) 
 {
		  try {
			    ClientVerificTransDAO cltDao = new ClientVerificTransDAO();
			    ClientVerificTransVO vo = new ClientVerificTransVO();
			    vo.setStrPk(voObj.getStrChk());
			    voObj.setStrCrComboDtl(cltDao.CRCOMBO(vo));
			   
		    } catch (Exception e) {

			voObj.setStrMsgString("ClientVerificTransBO.setRequiredValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

  }
 
 public void getInvoiceComboValues(ClientVerificTransVO voObj) 
 {
		  try {
			    ClientVerificTransDAO cltDao = new ClientVerificTransDAO();
			    ClientVerificTransVO vo = new ClientVerificTransVO();
			    vo.setStrPk(voObj.getStrChk());
			    voObj.setStrInvoiceComboDtl(cltDao.INVOICECOMBO(vo));
		      }
		      catch (Exception e) {

			voObj.setStrMsgString("ClientVerificTransBO.setRequiredValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

  }
  
 public boolean INVOICEDTL(ClientVerificTransVO  myVOObj)
 {
	 boolean fretValue = true;  
	    HisDAO daoObj = null;
		WebRowSet web5 = null;
		int nqryIndex5,ncount=0,i;
     	String strQuery5 = new String();
	    try 
 		{         
 			     String s = myVOObj.getStrInvoiceNo();
 		     
 	             String  strTemp[] = s.replace('^', '#').split("#");
 			       
 				i = Integer.parseInt(strTemp[2]);
 				myVOObj.setStrInvoiceNo(strTemp[0]);
 				myVOObj.setStrInvoiceDate(strTemp[1]);
 				myVOObj.setStrCrNo(strTemp[3]);
 				myVOObj.setStrInvoiceAmt(strTemp[4]);
 				myVOObj.setStrDepositAmt(strTemp[5]);
 				myVOObj.setStrSancAmt(strTemp[5]);		//Confusion			
 	             
 				
 				daoObj = new HisDAO("Client Verification Transaction",
	                                "ClientVerificationDAO");
                strQuery5 = " SELECT HBLSTR_CLIENT_NAME,HBLSTR_REG_NO"+
                            " FROM hblt_client_MST  WHERE HBLNUM_CLIENT_NO = "+i+"  AND GNUM_ISVALID =1  ";			 
     
                    nqryIndex5 = daoObj.setQuery(strQuery5);
                    web5 = daoObj.executeQry(nqryIndex5);
                    while (web5.next())
					   {
						    myVOObj.setStrClientName(web5.getString(1));
						    myVOObj.setStrAutNo(web5.getString(2));
						    ncount = Integer.parseInt(web5.getString(1));
						}
						if (ncount != 0) {
							fretValue = true;
						} else {
							fretValue = false;
							myVOObj.setStrMsgString("MsApprovalTransDAO.occupationdtl() --> ");
							myVOObj.setStrMsgType("1");
						}
 			
			if(myVOObj.getMsgType().equals("1"))	
			{
				throw new Exception(myVOObj.getMsgString());
			}
		}
		catch(Exception e)
		{
			myVOObj.setMsgString("myBO.INVOICEDTL() --> " + e.getMessage());
			myVOObj.setMsgType("1");
		}
		return fretValue;
	}
 
 public boolean INSERT(ClientVerificTransVO  myVOObj)
 {
	 boolean fretValue = true;  
 		try 
 		{
			if (fretValue) 
		    {   
				fretValue = ClientVerificTransDAO.INSERT(myVOObj);
		    }
			
			if(myVOObj.getMsgType().equals("1"))	
			{
				throw new Exception(myVOObj.getMsgString());
			}
		}
		catch(Exception e)
		{
			myVOObj.setMsgString("myBO.INSERT POST() --> " + e.getMessage());
			myVOObj.setMsgType("1");
		}
		return fretValue;
	}

 public boolean INSERTPRE(ClientVerificTransVO  myVOObj)
 {
	 boolean fretValue = true;  
 		try 
 		{
			
			fretValue = ClientVerificTransDAO.INSERTPRE(myVOObj);
		  		
			if(myVOObj.getMsgType().equals("1"))	
			{
				throw new Exception(myVOObj.getMsgString());
			}
		}
		catch(Exception e)
		{
			myVOObj.setMsgString("myBO.INSERT PRE-PAID() --> " + e.getMessage());
			myVOObj.setMsgType("1");
		}
		return fretValue;
	}
 
/*
 public void InsertData(ClientVerificTransVO voObj) 
 {
		     
		try {
			    ClientVerificTransDAO billDao = new ClientVerificTransDAO();
			    ClientVerificTransVO voHdr = new ClientVerificTransVO();
		    } catch (Exception e) {

			voObj.setStrMsgString("ClientVerificTransBO.setRequiredValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

  }*/


}
