package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class OnLineRefundApprovalDAO 
{
	
	public static void getTodayApprovalDtl(OnLineRefundApprovalVO voObj) 
	{
	    HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";
		String strHosCode = voObj.getStrHosCode();
		
		
		String strProcName = "{call pkg_bill_view.proc_sblt_inbound_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
			
		
		try 
		{
			daoObj = new HisDAO("Billing ","OnLineRefundApprovalDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex, "mode_type", "9",1);
				daoObj.setProcInValue(nProcIndex, "crno", "",2);
				daoObj.setProcInValue(nProcIndex, "chargeTypeId", "",3);
				daoObj.setProcInValue(nProcIndex, "patListType", "",4);
				daoObj.setProcInValue(nProcIndex, "searchStr", "",5);
				daoObj.setProcInValue(nProcIndex, "searchType", "",6);
				daoObj.setProcInValue(nProcIndex, "frmRows", "",7);
				daoObj.setProcInValue(nProcIndex, "toRows", "",8);
				daoObj.setProcInValue(nProcIndex, "deptCode", "",10);
				daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,9);
				daoObj.setProcInValue(nProcIndex, "seatid", "",11);
				daoObj.setProcOutValue(nProcIndex, "err", 1,12);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,13);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	
				if (strErr.equals("")) 
				{
					
					voObj.setTodayRefundApprovalDetails(ws);

				} 
				else
				{
					throw new Exception(strErr);
				}
			

		}
		catch (Exception e)
		{
			e.printStackTrace();
		   voObj.setStrMsgString("OnLineRefundApprovalDAO.getTodayApprovalDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	
	public static void getOnLineRefundApprovalDtlOne(OnLineRefundApprovalVO voObj) 
	{
	    HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";
		String strHosCode = voObj.getStrHosCode();
				
		String strProcName = "{call pkg_bill_view.proc_sblt_inbound_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
				
		try 
		{
			daoObj = new HisDAO("Billing ","OnLineRefundApprovalDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			    daoObj.setProcInValue(nProcIndex, "mode_type", "10",1);
			    
			    daoObj.setProcInValue(nProcIndex, "crno", "",2);
				daoObj.setProcInValue(nProcIndex, "chargeTypeId", "",3);
				daoObj.setProcInValue(nProcIndex, "patListType", "",4);
				daoObj.setProcInValue(nProcIndex, "searchStr", "",5);
				daoObj.setProcInValue(nProcIndex, "searchType", "",6);
				daoObj.setProcInValue(nProcIndex, "frmRows", "",7);
				daoObj.setProcInValue(nProcIndex, "toRows", "",8);
				daoObj.setProcInValue(nProcIndex, "deptCode", "",10);
			    daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,9);
			    daoObj.setProcInValue(nProcIndex, "seatid","",11);
				daoObj.setProcOutValue(nProcIndex, "err", 1,12);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,13);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	
				if (strErr.equals("")) 
				{
					
					voObj.setRefundRequestDetails(ws);

				} 
				else
				{
					throw new Exception(strErr);
				}
			

		}
		catch (Exception e)
		{
		   e.printStackTrace();	
		   voObj.setStrMsgString("OnLineRefundApprovalDAO.getOnLineRefundApprovalDtlOne() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void getOnLineRefundApprovalDtlTwo(OnLineRefundApprovalVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";
		String strHosCode = voObj.getStrHosCode();
	//	String strModeType = "1";
		
		String strProcName = "{call pkg_bill_view.proc_sblt_inbound_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
				
		try 
		{
			daoObj = new HisDAO("Billing ","OnLineRefundApprovalDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			    daoObj.setProcInValue(nProcIndex, "mode_type", "10",1);
			    
			    daoObj.setProcInValue(nProcIndex, "crno", "",2);
				daoObj.setProcInValue(nProcIndex, "chargeTypeId", "",3);
				daoObj.setProcInValue(nProcIndex, "patListType", "",4);
				daoObj.setProcInValue(nProcIndex, "frmRows", "",7);
				daoObj.setProcInValue(nProcIndex, "toRows", "",8);
				daoObj.setProcInValue(nProcIndex, "deptCode", "",10);
				daoObj.setProcInValue(nProcIndex, "seatid", "",11);
				daoObj.setProcInValue(nProcIndex, "searchtype", voObj.getStrSearchMode(),6);
				daoObj.setProcInValue(nProcIndex, "searchstr",voObj.getStrSearchValue(),5);
				daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,9);
				daoObj.setProcOutValue(nProcIndex, "err", 1,12);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,13);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	
				if (strErr.equals("")) 
				{
					
					voObj.setRefundRequestDetails(ws);

				} 
				else
				{
					throw new Exception(strErr);
				}
			

		}
		catch (Exception e)
		{
		   e.printStackTrace();	
		   voObj.setStrMsgString("OnLineRefundApprovalDAO.getOnLineRefundApprovalDtlTwo() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	public static void getTariffDetails(OnLineRefundApprovalVO voObj) 
	{
	    HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";
		String strHosCode = voObj.getStrHosCode();
	//	String strModeType = "1";
		
		String strProcName = "{call pkg_bill_view.proc_hblt_billreq_tariff_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
			
		
		try 
		{
			daoObj = new HisDAO("Billing ","OnLineRefundApprovalDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex,  "modeval", "4",2);
				daoObj.setProcInValue(nProcIndex,  "chargeTypeId", "",4);
				daoObj.setProcInValue(nProcIndex,  "deptCode", "",5);
				
				daoObj.setProcInValue(nProcIndex,  "reqno", voObj.getStrReqNo(),1);
				daoObj.setProcInValue(nProcIndex,  "hosp_code",strHosCode,3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	
				if (strErr.equals("")) 
				{
					
					voObj.setRefundTariffDetails(ws);

				} 
				else
				{
					throw new Exception(strErr);
				}
			

		}
		catch (Exception e)
		{
			e.printStackTrace();
		   voObj.setStrMsgString("OnLineRefundApprovalDAO.getTariffDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getRefundBy(OnLineRefundApprovalVO vo)
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_SIMPLE_VIEW.Proc_user_list(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try {
			dao = new HisDAO("billing",
					"transactions.OnLineRefundApprovalDAO .getRefundBy()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHosCode(),2); // New Value
			dao.setProcInValue(nprocIndex, "processId", "9",1);
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(),3);
		
			dao.setProcOutValue(nprocIndex, "ERR", 1,4); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,5); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			vo.setApprovedBy(ws);
		} catch (Exception e) {
			vo.setStrMsgString("OnLineRefundApprovalDAO.getRefundRsn() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao.free();
			dao = null;
		}

	}
	
	public static boolean save(OnLineRefundApprovalVO vo) 
	{
		HisDAO dao = null;
		boolean retVal = false;
		String proc_name1 = "";
		String proc_name2 = "";
		proc_name1 = "{call PKG_BILL_DML.Dml_Online_refund_trf_approval(?,?,?,?,?,?,?,?,?,?,?)}";
		proc_name2 = "{call PKG_BILL_DML.Dml_Online_refund_approval(?,?,?,?,?,?,?,?,?)}";
		int procIndex1 = 0;
		int procIndex2 = 0;
	//	int count = 0;
	    try 
		{
			
		// Createing Object for Table Specific DAO
			/*
			 modval                           VARCHAR2 DEFAULT 1,
        hosp_code                        VARCHAR2 DEFAULT NULL,
        reqNo                            VARCHAR2 DEFAULT NULL,
        appBy                            VARCHAR2 DEFAULT NULL,
        remarks                          VARCHAR2 DEFAULT NULL,                                
        ipAddress                        VARCHAR2 DEFAULT NULL,
        seatId                           VARCHAR2 DEFAULT NULL,
        trfId                            VARCHAR2 DEFAULT NULL,
        reqQty                           VARCHAR2 DEFAULT NULL,    
        appQty                           VARCHAR2 DEFAULT NULL,
        err                              OUT   VARCHAR2
			
			*/
			dao = new HisDAO("BILLING","transactions.OnLineRefundApprovalVO.save()");
			/*
			vo.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
			vo.setStrHiddenVal(formBean.getStrHiddenVal());
			vo.setStrApprovedQty(formBean.getStrApprovedQty());
			vo.setStrRefundCost(formBean.getStrRefundCost());
			vo.setStrReqNo(formBean.getStrReqNo());
			vo.setStrRefundedByComboVal(formBean.getStrRefundedByComboVal());
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setTariffId(formBean.getTariffId());
			*/
			 
			
			if(vo.getTariffId() != null && vo.getTariffId().length > 0)
			{
			
		 			 
			    for(int i=0; i<vo.getTariffId().length ;i++)
			    {														
						
						String strTemp[] = vo.getStrHiddenVal()[i].replace('^', '#').split("#");
						
					    procIndex1 = dao.setProcedure(proc_name1);
			                                    
	
					    dao.setProcInValue(procIndex1, "modval", "1",1);             //1
					    
					    dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHosCode(),2);   //2
	
					    dao.setProcInValue(procIndex1, "reqNo", vo.getStrReqNo(),3);       //3
					//    System.out.println("Req No:::"+vo.getStrReqNo());
					
					    dao.setProcInValue(procIndex1, "appBy", vo.getStrRefundedByComboVal(),4);       //4
					  //  System.out.println("Appp By:::"+vo.getStrRefundedByComboVal());
					    dao.setProcInValue(procIndex1, "remarks", vo.getStrRemarks(),5);     //5
					
					    dao.setProcInValue(procIndex1, "ipAddress", vo.getStrIpAddress(),6);   //6
					
					    dao.setProcInValue(procIndex1, "seatId", vo.getStrSeatId(),7);      //7
					
					    dao.setProcInValue(procIndex1, "trfId", strTemp[7],8);       //8
					 ///   System.out.println("tariff Id:::"+strTemp[7]);
					    dao.setProcInValue(procIndex1, "reqQty", vo.getStrRequestQty()[i],9);      //9
					  //  System.out.println("reqQty::::"+ vo.getStrRequestQty()[i]);
					 
					    dao.setProcInValue(procIndex1, "appQty", vo.getStrApprovedQty()[i],10);      //10
					//    System.out.println("appQty::::"+vo.getStrApprovedQty()[i]);
					    dao.setProcOutValue(procIndex1, "err", 1,11); // 1 for string return    //11
	
					    dao.execute(procIndex1, 1);
						
					
			    }
				procIndex2 = dao.setProcedure(proc_name2);
  				
			    dao.setProcInValue(procIndex2, "modval", "1",1);             //1
			    
			    dao.setProcInValue(procIndex2, "hosp_code", vo.getStrHosCode(),2);   //2

			    dao.setProcInValue(procIndex2, "reqNo", vo.getStrReqNo(),3);       //3
			
			    dao.setProcInValue(procIndex2, "appBy", vo.getStrRefundedByComboVal(),4);       //4
			
			    dao.setProcInValue(procIndex2, "remarks", vo.getStrRemarks(),5);     //5
			
			    dao.setProcInValue(procIndex2, "ipAddress", vo.getStrIpAddress(),6);   //6
			
			    dao.setProcInValue(procIndex2, "seatId", vo.getStrSeatId(),7);      //7
			
			    dao.setProcInValue(procIndex2, "trfFlag", "1",8);       //8
			  			
			    dao.setProcOutValue(procIndex2, "err", 1,9); // 1 for string return    //11

			    dao.execute(procIndex2, 1);
			    
			    synchronized (dao) 
				{
					dao.fire();
					retVal = true;
				}
				
				
			    vo.setStrMsg("Refund Request Successfully Approved for CR No. "+vo.getStrCrNoVal());
			    
			}
			else
			{
				
				
				
                procIndex2 = dao.setProcedure(proc_name2);
  				
			    dao.setProcInValue(procIndex1, "modval", "1",1);             //1
			    
			    dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHosCode(),2);   //2

			    dao.setProcInValue(procIndex1, "reqNo", vo.getStrReqNo(),3);       //3
			    //System.out.println("Below:::"+vo.getStrReqNo());
			    dao.setProcInValue(procIndex1, "appBy", vo.getStrRefundedByComboVal(),4);       //4
			
			    dao.setProcInValue(procIndex1, "remarks", vo.getStrRemarks(),5);     //5
			
			    dao.setProcInValue(procIndex1, "ipAddress", vo.getStrIpAddress(),6);   //6
			
			    dao.setProcInValue(procIndex1, "seatId", vo.getStrSeatId(),7);      //7
			
			    dao.setProcInValue(procIndex1, "trfFlag", "0",8);       //8
			  			
			    dao.setProcOutValue(procIndex1, "err", 1,9); // 1 for string return    //11

			    dao.execute(procIndex1, 1);
			    
			    synchronized (dao) 
				{
					dao.fire();
					retVal = true;
				}
			    
			    vo.setStrMsg("Refund Request Rejected for CR No. "+vo.getStrCrNoVal());
			    
			}	
			

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("--> OnLineRefundApprovalDAO.save()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null)
				dao.free();
			dao = null;
		}
		return retVal;
	}


}
