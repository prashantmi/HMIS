package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.dao.ClientPatientDAO;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class ClientApprovalDetailsTransDAO 
{
	
	/* ---------------Check If Approval For Amount Already Given and Not Expired---------------------*/
	public static void callingFunctionView(ClientApprovalDetailsTransVO VO)
	{
		HisDAO dao = null;	
		String  retVal = null;
		int funcIndex = 0 ;
		
       	try
		{
       			dao = new HisDAO("BillingModule","ClientApprovalDetailsTransDAO");
				funcIndex = dao.setFunction("{? = call bill_mst.getClientApprovalStatus(?,?)}");
				dao.setFuncInValue(funcIndex,2,VO.getStrCrNo());
				dao.setFuncInValue(funcIndex,3,VO.getStrHospitalCode());
				dao.setFuncOutValue(funcIndex,3);
				dao.executeFuncForNumeric(funcIndex);
				retVal = dao.getFuncNumeric(funcIndex);
    		  
				VO.setStrRetValue(retVal);
	 	}
		catch(Exception e)
		{
			VO.setStrMsgString("ClientApprovalDetailsTransDAO.callingFunctionView() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}
	}
	
	/*
     * This Method is Used to perform Insert Oepration 
     * for Approval Status 
     */
	public static boolean InsertApproval(ClientApprovalDetailsTransVO vo) 
	{
		HisDAO dao= null;
    	boolean retVal = false;
    	int clientFlag = 0;
	    PrimaryKeyDAO        pKeyDao     = new PrimaryKeyDAO();
		PrimaryKeyLogDAO     pKeyLogDao  = new PrimaryKeyLogDAO();
		ClientPatientDAO     cltDtlDAO   = new ClientPatientDAO();   
	  
		try
	    {
		  
			dao = new HisDAO("billing","transactions.ClientApprovalDetailsTrans.InsertUpdateAcctStatus()");
			if(vo.getStrCrNo()!= null)
	        {
				/* ---------------Check If Approval For Advance Already Given and Not Expired---------------------*/
				callingFunctionView(vo);
			  //advance can be approved only once..thus would not enter into this if, if advance is already collected..
        	  if( vo.getStrRetValue().equals("0"))
    	      {  
        		  pKeyDao.setStrKeyname("PAT_CLIENT_NO");
    			  pKeyDao.setStrBlockkey("1");
    			  pKeyDao.setStrHospCode(vo.getStrHospitalCode());
    		      pKeyDao.select(dao);
    		      
    		      vo.setStrClientPatientNo(pKeyDao.getStrPrimrayKeyValue());
    		      clientFlag = 1;
    		      cltDtlDAO.setStrClientPatNo(vo.getStrClientPatientNo());
    		      cltDtlDAO.setStrClientPatSlNo("1");
    		      cltDtlDAO.setStrPuk(vo.getStrCrNo());
    		      cltDtlDAO.setStrClientNo(vo.getStrCltNo());
    		      cltDtlDAO.setStrCardNo(vo.getStrCardNumber());
    		      cltDtlDAO.setStrCardExpiryDt(vo.getStrCardExpiryDate());
    		      cltDtlDAO.setStrCardHolderName(vo.getStrCardHolderName());
    		      cltDtlDAO.setStrAuthNo(vo.getStrAuthenticationNumber());
    		      cltDtlDAO.setStrAuthDt(vo.getStrAuthenticationDate());
    		      cltDtlDAO.setStrSancLimit(vo.getStrSanctionAmount());
    		      cltDtlDAO.setStrClientExpenseAmt("0");
    		      cltDtlDAO.setStrExpiryDt(vo.getStrExpiryDate());
    		      cltDtlDAO.setStrStatus("1");
    		      cltDtlDAO.setStrTariffStatus("0");
    		      cltDtlDAO.setStrSeatId(vo.getStrSeatId());
    		      cltDtlDAO.setStrIsValid("1");
    		      cltDtlDAO.setStrPaymentStatus("0");
    		      cltDtlDAO.setStrClientAmt(vo.getStrSanctionAmount());
    		      cltDtlDAO.setStrIsIpdApproval(vo.getIPD());
    		      cltDtlDAO.setStrIsOpdApproval(vo.getOPD());
    		      cltDtlDAO.setStrIsEmgrApproval(vo.getEME());
    		      cltDtlDAO.setStrIsOneTimeService(vo.getStrOneTimeService());
    		      cltDtlDAO.setStrHospitalCode(vo.getStrHospitalCode());
    		      cltDtlDAO.insert(dao);
			   }
        	  else
        	  {
        	     throw new Exception(vo.getStrMsgString());
			  }
	        }
		    synchronized(dao)
			  {
				dao.fire();
				retVal = true;
			  }
		 
	  }	
		catch (Exception e) 
		  {
			e.printStackTrace();
			vo.setStrErrPrimKeyLog(e.getMessage());
			if (clientFlag == 1)
			{	
			 try
			 {
			  	pKeyLogDao.setStrKeyname("PAT_CLIENT_NO");
		     	pKeyLogDao.setStrStartkey(vo.getStrClientPatientNo());
		     	pKeyLogDao.setStrBlockkey("1");
		     	pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
		     	pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
		     	pKeyLogDao.setStrSeatid(vo.getStrSeatId());
		     // Calling Insert Method of Primary Key Log
				pKeyLogDao.insert(dao);
			 } 
			 catch (Exception e1)
			 {
				e.printStackTrace();				 	
				 new HisException("billing","ClientApprovalDetailsTrans","ClientApprovalDetailsTransDAO.InsertApproval() --> "
							+ e.getMessage());
					vo.setStrMsgString("ClientApprovalDetailsTransDAO.InsertApproval()-->"+e1.getMessage());
					vo.setStrMsgType("1");	
					retVal = false;
			    
             }
			}
			new HisException("billing","ClientApprovalDetailsTrans","ClientApprovalDetailsTransDAO.InsertApproval() --> "
					+ e.getMessage());
			vo.setStrMsgString("ClientApprovalDetailsTransDAO.InsertApproval()-->"+e.getMessage());
			vo.setStrMsgType("1");	
			retVal = false;
          }

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}
			if ( pKeyLogDao!=null || pKeyDao != null ) 
    		{
    		       pKeyDao           = null; 
    		       pKeyLogDao        = null;
    		}
		}
	 return retVal;

	}
	
	
	/*
     * This Method is Used to perform Insert Operation 
     * for Re-Approval 
     */
	//used
	public static boolean InsertReApproval(ClientApprovalDetailsTransVO vo) 
	{
		HisDAO dao= null;
    	boolean retVal = false;
    	ClientPatientDAO     cltDtlDAO   = new ClientPatientDAO();   
		
	  try
	  {
		  dao = new HisDAO("billing","ClientApprovalDetailsTrans.InsertReApproval()");
          if(vo.getStrCrNo()!= null)
	        {
        	   cltDtlDAO.setStrPuk(vo.getStrCrNo());
 		      cltDtlDAO.setStrClientNo(vo.getStrCltNo());
 		      cltDtlDAO.setStrCardNo(vo.getStrCardNumber());
 		      cltDtlDAO.setStrCardExpiryDt(vo.getStrCardExpiryDate());
 		      cltDtlDAO.setStrCardHolderName(vo.getStrCardHolderName());
 		      cltDtlDAO.setStrStatus("2");
		      cltDtlDAO.setStrClientPatNo(vo.getStrClientPatientNo());
		      cltDtlDAO.setStrClientPatSlNo(vo.getStrClientPatientSrNo());
		      cltDtlDAO.setStrAuthNo(vo.getStrAuthenticationNumber());
		      cltDtlDAO.setStrAuthDt(vo.getStrAuthenticationDate());
		      cltDtlDAO.setStrSancLimit(vo.getStrSanctionAmount());
		      cltDtlDAO.setStrExpiryDt(vo.getStrExpiryDate());
		      cltDtlDAO.setStrSeatId(vo.getStrSeatId());
		      cltDtlDAO.setStrHospitalCode(vo.getStrHospitalCode());	
		      cltDtlDAO.setStrIsIpdApproval(vo.getIPD());
		      cltDtlDAO.setStrIsOpdApproval(vo.getOPD());
		      cltDtlDAO.setStrIsEmgrApproval(vo.getEME());
		      cltDtlDAO.setStrClientNo(vo.getStrClientNo());
		      cltDtlDAO.setStrHospitalCode(vo.getStrHospitalCode());
		      
	          cltDtlDAO.insert1(dao);
	        }
		    synchronized(dao)
			  {
				dao.fire();
				retVal = true;
			  }
		  
	  }	
		catch (Exception e) 
		  {
				vo.setStrMsgString("ClientApprovalDetailsTransDAO.InsertReApproval()-->"+e.getMessage());
				vo.setStrMsgType("1");			
          }

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		 return retVal;

	}
	/*
     * This Method is Used to perform Insert Operation 
     * for Close 
     */
	//used
	public static boolean InsertClose(ClientApprovalDetailsTransVO vo) 
	{
		HisDAO dao= null;
    	boolean retVal = false;
    	ClientPatientDAO     cltDtlDAO   = new ClientPatientDAO();   
		
	  try
	  {
		  dao = new HisDAO("billing","ClientApprovalDetailsTrans.InsertClose()");
          if(vo.getStrCrNo()!= null)
	        {
		      cltDtlDAO.setStrClientPatNo(vo.getStrClientPatNo());
		      cltDtlDAO.setStrClientPatSlNo(vo.getStrClientPatientSrNo());
		      cltDtlDAO.setStrHospitalCode(vo.getStrHospitalCode());
		      cltDtlDAO.insert2(dao);
	        }
		    synchronized(dao)
			  {
				dao.fire();
				retVal = true;
			  }
		  
	  }	
		catch (Exception e) 
		  {
				vo.setStrMsgString("ClientApprovalDetailsTransDAO.InsertClose()-->"+e.getMessage());
				vo.setStrMsgType("1");			
          }

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		 return retVal;

	}
	
	// //used
	public static void checkApprovalDtl(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.Proc_Hblt_Client_Patient_Dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			//System.out.println("dao checkApprovalDtl voObj.getStrCrNo()"+voObj.getStrCrNo());
			//System.out.println("dao checkApprovalDtl voObj.getStrHospitalCode()"+voObj.getStrHospitalCode());
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval","4");
			
			daoObj.setProcInValue(nProcIndex, "clientpatno","");
			daoObj.setProcInValue(nProcIndex, "clientpatslno","");
			daoObj.setProcInValue(nProcIndex, "chargetypeid","");
			daoObj.setProcInValue(nProcIndex, "billNo","");
			
			daoObj.setProcInValue(nProcIndex, "puk",voObj.getStrCrNo());
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals(""))
			{
				//System.out.println("dao checkApprovalDtl ws size"+ws.size());
			 while(ws.next())
			 {
				voObj.setStrApprovalStatus(ws.getString(1));
				//System.out.println("dao voObj.getStrApprovalStatus()"+voObj.getStrApprovalStatus());
				
			 }	

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.checkApprovalDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}
	/*public static void setClientDtl(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call proc_client_dtl_list(?,?,?)}";
		int nProcIndex = 0;
        
		String strErr = "";

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue (nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) 
			{
				voObj.setStrClientNameWs(ws);

			}else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.setClientDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}*/
//used
	public static void getClientList(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_View.proc_hblt_client_mst(?,?,?,?,?)}";
		int nProcIndex = 0;
        
		String strErr = "";

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			
		 	daoObj.setProcInValue (nProcIndex, "modeval","1"); 
			daoObj.setProcInValue (nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value
			daoObj.setProcInValue (nProcIndex, "client_no","");
			 			
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals(""))
			{
               // System.out.println("dao getClientList Size is :"+ws.size());
				voObj.setStrClientList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.setGroupDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	//used
	public static void getReApproval(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call PKG_BILL_VIEW.Proc_Hblt_Client_Patient_Dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");
          
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "clientpatno", voObj.getStrClientPatientNo());
			daoObj.setProcInValue(nProcIndex, "clientpatslno", voObj.getStrClientPatientSrNo());
			daoObj.setProcInValue(nProcIndex, "modeval", "2");
			daoObj.setProcInValue (nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value
			
			daoObj.setProcInValue(nProcIndex, "puk", "");
			daoObj.setProcInValue(nProcIndex, "chargetypeid", "");
			daoObj.setProcInValue(nProcIndex, "billNo", "");
			
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals(""))
			{
			  voObj.setStrCltReApprovalData(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.setChargeTypeDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	//used

	public static void UNITVAL12(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_View.proc_hblt_client_mst(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue (nProcIndex, "modeval","2");  
			daoObj.setProcInValue(nProcIndex,  "client_no", voObj.getStrClientPatientNo()); 
			daoObj.setProcInValue (nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals(""))
			{
				voObj.setStrClientDtlWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.UNITVAL12() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/*public static void setTariffDtl(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call proc_tariff_list(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		String groupCode = voObj.getStrGroupTempCode();

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (groupCode != null) {

				daoObj.setProcInValue(nProcIndex, "groupid", groupCode);
				daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value

				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) 
				{
    				voObj.setStrTariffWs(ws);

				} 
				else
				{
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.setTariffDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
*/
	/*public static void initReApprovalDetails(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call proc_client_approval_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		String strClientPatNo = voObj.getStrClientPatientNo();
		String strClientPatSrNo = voObj.getStrClientPatientSrNo();

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strClientPatNo != null && strClientPatSrNo != null) {

				daoObj.setProcInValue(nProcIndex, "client_pat_no",
						strClientPatNo);
				daoObj.setProcInValue(nProcIndex, "client_pat_sno",
						strClientPatSrNo);
				daoObj.setProcInValue (nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value  
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) 
				{
      				 if (ws != null) 
      				 {
						if (ws.next()) 
						{

							voObj.setStrCrNo(ws.getString(1));
							voObj.setStrClientName(ws.getString(2));
							voObj.setStrClientAddress(ws.getString(3));
							voObj.setStrApprovalFor(ws.getString(4));
							voObj.setStrCardNumber(ws.getString(5));
							voObj.setStrCardExpiryDate(ws.getString(6));
							voObj.setStrCardHolderName(ws.getString(7));
							voObj.setStrAuthenticationNumber(ws.getString(8));
							voObj.setStrAuthenticationDate(ws.getString(9));
							voObj.setStrSanctionAmount(ws.getString(10));
							voObj.setStrExpiryDate(ws.getString(11));

						}

					}

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.initReApprovalDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}*/

	/*public static void initReFundDetails(ClientApprovalDetailsTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call proc_client_approval_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		String strClientPatNo = voObj.getStrClientPatientNo();
		String strClientPatSrNo = voObj.getStrClientPatientSrNo();

		try {
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strClientPatNo != null && strClientPatSrNo != null) {

				daoObj.setProcInValue(nProcIndex, "client_pat_no",
						strClientPatNo);
				daoObj.setProcInValue(nProcIndex, "client_pat_sno",
						strClientPatSrNo);
				daoObj.setProcInValue (nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					if (ws != null) {

						if (ws.next()) {

							voObj.setStrCrNo(ws.getString(1));
							voObj.setStrClientName(ws.getString(2));
							voObj.setStrClientAddress(ws.getString(3));
							voObj.setStrApprovalFor(ws.getString(4));
							voObj.setStrCardNumber(ws.getString(5));
							voObj.setStrCardExpiryDate(ws.getString(6));
							voObj.setStrCardHolderName(ws.getString(7));
							voObj.setStrAuthenticationNumber(ws.getString(8));
							voObj.setStrAuthenticationDate(ws.getString(9));
							voObj.setStrSanctionAmount(ws.getString(10));
							voObj.setStrExpiryDate(ws.getString(11));
							voObj.setStrAmountReceivedFromClient(ws
									.getString(12));
							voObj.setStrAmountReceivedFromPatient(ws
									.getString(13));
							voObj.setStrClientExpenseAmount(ws.getString(14));
							voObj.setStrRefundAmount(ws.getString(15));
						}

					}

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.initReFundDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
*/
	//used
	public static void setClientApprovalViewDtls(
			ClientApprovalDetailsTransVO voObj) 
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.Proc_Hblt_Client_Patient_Dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		String strClientPatNo = voObj.getStrClientPatNo();
		try
		{
			daoObj = new HisDAO("Client Approval Details Transaction",
					"ClientApprovalDetailsTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strClientPatNo != null) 
			{
				daoObj.setProcInValue(nProcIndex, "clientpatno",strClientPatNo); 
				daoObj.setProcInValue(nProcIndex, "modeval","3");
				daoObj.setProcInValue (nProcIndex, "hosp_code",voObj.getStrHospitalCode());  // New Value
				daoObj.setProcInValue(nProcIndex, "clientpatslno", "");
				daoObj.setProcInValue(nProcIndex, "puk", "");
				daoObj.setProcInValue(nProcIndex, "chargetypeid", "");
				daoObj.setProcInValue(nProcIndex, "billNo", "");
				
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");
                if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) 
				{
					voObj.setStrApprovalDetailsWs(ws);
				} 
				else
				{
					throw new Exception(strErr);
				}
			}

		}
		catch (Exception e) 
		{

			voObj
					.setStrMsgString("ClientApprovalDetailsTransDAO.setClientApprovalViewDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

}
