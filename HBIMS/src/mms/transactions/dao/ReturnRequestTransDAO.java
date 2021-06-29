package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import mms.dao.DmlHsttReturnReqitemDtlDAO;
import mms.dao.DmlIndentDtlDAO;
import mms.dao.DmlIndentReturnRequestDAO;
import mms.transactions.vo.ReturnRequestTransVO;

public class ReturnRequestTransDAO 
{
   

	/**
	 * for getting option value of Item Category Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void itemCategoryCombo(ReturnRequestTransVO vo) {
		String strProcName = "{call Pkg_Mms_View.PROC_SSTT_ITEM_CATEGORY_MST(?,?,?,?)}";

		int nProcIndex = 0;
		String strErr = "";
		String str = "";
		HisUtil hisutil = null;
		WebRowSet ws = null;
		HisDAO daoObj = null;

		try {
			hisutil = new HisUtil("MMS", "ReturnRequestTransDAO");
			daoObj = new HisDAO("Return Request", "ReturnRequestTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "mode_val", "2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo
					.getStrHospitalCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				if (ws != null && ws.size() != 0) {
					str = hisutil.getOptionValue(ws, "-1", "0^Select Value",
							true);
					vo.setStrItemCatgCombo(str);
				} else {
					str = "<option value='0'>DATA N/A</option>";
					vo.setStrItemCatgCombo(str);
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ReturnRequestTransDAO.itemCategoryCombo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/**
	 * for getting option value of combo on add page (store type name )
	 * 
	 * @param vo
	 */
	public static void GetGroupNameCombo(ReturnRequestTransVO vo) 
	{
		
		String err = "";
		String proc_name1 = "{call pkg_mms_view.proc_store_group_list(?,?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		HisUtil hisutil = null;
		String str = null;
		

		try {
			
			hisutil = new HisUtil("MMS", "ReturnRequestTransDAO");
			dao = new HisDAO("Return Request", "ReturnRequestTransDAO");
            
			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modeval", "1",1);
			
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),2);

			
			dao.setProcInValue(procIndex1, "item_category", vo.getStrItemCategoryNo(),3);
			dao.setProcInValue(procIndex1, "strPhyStockNo", "",4);
			dao.setProcInValue(procIndex1, "strStoreId", "",5);
			
			dao.setProcOutValue(procIndex1, "err", 1,6); // 1 for string return
			// value

			dao.setProcOutValue(procIndex1, "resultset", 2,7); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");

			if (ws != null && ws.size() != 0) {
				str = hisutil.getOptionValue(ws, "-1", "0^Select Value", true);
				vo.setStrGroupIdForItemSearch(str);
			} else {
				str = "<option value='0'>DATA N/A</option>";
				vo.setStrGroupIdForItemSearch(str);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("ReturnRequestTransDAO.GetGroupNameCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}
	
	
	/*
	 * This Function is used to get Store Name by Passing 2 variable a) Hospital
	 * Code b) Store Id
	 */
	public static void callingFunctionStoreName(ReturnRequestTransVO vo) {
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO dao = null;
		try {
			dao = new HisDAO("MMS", "ReturnRequestTransDAO");
			funcIndex = dao
					.setFunction("{? = call MMS_MST.get_store_dtl(?::numeric,?::numeric,?::numeric)}");
			// Set Value
			dao.setFuncInValue(funcIndex, 2, "1");
			dao.setFuncInValue(funcIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 4, vo.getStrStoreId());
			dao.setFuncOutValue(funcIndex, 1);
			// Execute Function
			dao.executeFunction(funcIndex);
			retVal = dao.getFuncString(funcIndex);
			if (retVal != null) {
				vo.setStrStoreName(retVal);
			} else {
				retVal = "-----";
				vo.setStrStoreName(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("ReturnRequestTransDAO.callingFunctionStoreName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/*
	 * This Function is used to get Item Category Name by Passing 2 variable a)
	 * Hospital Code b) Item Category
	 */

	public static void callingItemCategory(ReturnRequestTransVO vo) {

		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO dao = null;
		// Split the Value

		try {
			dao = new HisDAO("MMS", "ReturnRequestTransDAO");
			funcIndex = dao
					.setFunction("{? = call MMS_MST.get_itemcat_dtl(?::numeric,?::numeric,?::numeric)}");
			// Set Value
			dao.setFuncInValue(funcIndex, 2, "1");
			dao.setFuncInValue(funcIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 4, vo.getStrItemCategoryNo());
			dao.setFuncOutValue(funcIndex, 1);
			// Execute Function
			dao.executeFunction(funcIndex);
			retVal = dao.getFuncString(funcIndex);

			if (retVal != null) {
				vo.setStrItemCatg(retVal);
			} else {
				retVal = "-----";
				vo.setStrItemCatg(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("ReturnRequestTransDAO.callingItemCategory() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/**
	 * for getting option value of combo on add page (Grant Type Combo)
	 * 
	 * @param vo
	 */
	public static void GetGrantTypeCombo(ReturnRequestTransVO vo) {
		String err = "";
		String proc_name1 = "{call PKG_MMS_VIEW.proc_grant_list(?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		HisUtil hisutil = null;
		String str = null;

		try {
			hisutil = new HisUtil("master", "ReturnRequestTransDAO");
			dao = new HisDAO("mms",
					"ReturnRequestTransDAO");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modeval", "1",1);

			dao
					.setProcInValue(procIndex1, "hosp_code", vo
							.getStrHospitalCode(),2);

			dao.setProcOutValue(procIndex1, "err", 1,3); // 1 for string return

			dao.setProcOutValue(procIndex1, "resultset", 2,4); // 2 for object
			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");

			if (ws != null && ws.size() != 0) {
				str = hisutil.getOptionValue(ws, "-1", "0^Select Value", true);
				vo.setStrGrantTypeCombo(str);
			} else {
				str = "<option value='0'>DATA N/A</option>";
				vo.setStrGrantTypeCombo(str);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ReturnRequestTransDAO.GetGrantTypeCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}

	/**
	 * for getting option value of combo on add page (Recommende Combo)
	 * 
	 * @param vo
	 */
	public static void GetRecommendByCombo(ReturnRequestTransVO vo) {
		String err = "";
		String proc_name1 = "{call PKG_MMS_VIEW.Proc_Consultant_Name(?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		HisUtil hisutil = null;
		String str = null;

		try {
			hisutil = new HisUtil("master", "ReturnRequestTransDAO");
			dao = new HisDAO("mms",
					"ReturnRequestTransDAO");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modeval", "1",1);

			
			dao.setProcInValue(procIndex1, "deptunitcode", "0",2);
			
			
			dao
					.setProcInValue(procIndex1, "hosp_code", vo
							.getStrHospitalCode(),3);

			dao.setProcInValue(procIndex1, "seatId", vo.getStrSeatId(),4);
			
			dao.setProcOutValue(procIndex1, "err", 1,5); // 1 for string return

			dao.setProcOutValue(procIndex1, "resultset", 2,6); // 2 for object
			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");

			if (ws != null && ws.size() != 0) {
				str = hisutil.getOptionValue(ws, "-1", "0^Select Value", true);
				vo.setStrRecmndByCombo(str);
			} else {
				str = "<option value='0'>DATA N/A</option>";
				vo.setStrRecmndByCombo(str);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ReturnRequestTransDAO.GetRecommendByCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}

	/**
	 * for getting option value of combo on add page (store type name )
	 * 
	 * @param vo
	 */
	public static WebRowSet GetItemCategoryCombo(ReturnRequestTransVO vo) {
		String err = "";
		String proc_name1 = "{call PKG_MMS_VIEW.proc_item_category_list(?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		try {
			// hisutil = new HisUtil("master", "ReturnRequestTransDAO");
			dao = new HisDAO("mms",
					"ReturnRequestTransDAO");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modeval", "1",1);

			dao.setProcInValue(procIndex1, "store_id", vo.getStrStoreId(),2); // Check
																			// It
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),3);
			dao.setProcInValue(procIndex1, "reqType", "",4);

			dao.setProcOutValue(procIndex1, "err", 1,5); // 1 for string return

			dao.setProcOutValue(procIndex1, "resultset", 2,6); // 2 for object
			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");

		} catch (Exception e) {
			vo.setStrMsgString("ReturnRequestTransDAO.GetItemCategoryCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

		return ws;
	}

	/**
	 * for getting option value of combo on add page (store type name )
	 * 
	 * @param vo
	 */
	public static void ToStoreCombo(ReturnRequestTransVO vo) {
		String err = "";
		String proc_name1 = "{call Pkg_Mms_View.proc_hstt_toStore_mst(?,?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		HisUtil hisutil = null;
		String str = null;

		try {
			hisutil = new HisUtil("MMS", "ReturnRequestTransDAO");
			dao = new HisDAO("Return Request",
					"ReturnRequestTransDAO");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value
			
			
			dao.setProcInValue(procIndex1, "modeval", "1",1);

			dao
			.setProcInValue(procIndex1, "hosp_code", vo
					.getStrHospitalCode(),2);


			dao.setProcInValue(procIndex1, "storeid", vo.getStrStoreId(),3);
		    					
			dao.setProcInValue(procIndex1, "reqtype", vo.getStrReqType(),4);
						
			dao.setProcInValue(procIndex1, "catCode", vo.getStrItemCategoryNo(),5);
						
			dao.setProcOutValue(procIndex1, "err", 1,6); // 1 for string return
			// value

			dao.setProcOutValue(procIndex1, "resultset", 2,7); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");

			if (ws != null && ws.size() != 0) {
				str = hisutil.getOptionValue(ws, "", "", true);
				vo.setStrToStoreCombo(str);
			} else {
				str = "<option value='0'>DATA N/A</option>";
				vo.setStrToStoreCombo(str);
			}

		} catch (Exception e) {
			vo.setStrMsgString("ReturnRequestTransDAO.ToStoreCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}
	/**
	 * INSERT method is used to insert data in following table
	 * SSTT_INDENT_DTL,SSTT_APPROVALREQ_DTL,HSTT_CONDEMN_REQ_DTL,
	 * HSTT_CONDEMN_REQITEM_DTL
	 * @param vo
	 */

	public synchronized static void INSERT(ReturnRequestTransVO vo) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String indentNo = "";
		String approvalFlg ="";
		DmlIndentDtlDAO globalDao = null;
		boolean bFlag=false;
    	try 
		{
	    		// Createing Object for Table Specific DAO
	    		globalDao = new DmlIndentDtlDAO();	
	    		dao = new HisDAO("MMS","transactions.ReturnRequestTransDAO.INSERT()");
    		
		        globalDao.setStrId(vo.getStrStoreId());
				globalDao.setHosp_code(vo.getStrHospitalCode());
				globalDao.setReqTypeId(vo.getStrReqType());
				globalDao.setToStrId(vo.getStrToStore());  
				
				globalDao.setItemcatNo(vo.getStrItemCatg());
				globalDao.setItemTypeId("0");  //Check
				globalDao.setUrgentFlag(vo.getStrUrgetnFlg());  
				globalDao.setIndentPeriod("0");	 //check	
							
				globalDao.setFinStartDate(vo.getStrFinancialStartYear());
				globalDao.setFinEndDate(vo.getStrFinancialEndYear());
			    globalDao.setRemarks(vo.getStrRemarks());
				globalDao.setSeatId(vo.getStrSeatId());
			    
				globalDao.setGrantTypeId("0"); // Change after remove Grant Type Combo
				globalDao.setPuk("0");
				globalDao.setEmpNo("0");
				globalDao.setAdmNo("0");
				globalDao.setEpisodeCode("0");
				globalDao.setConsultantId("0");
				globalDao.setMemoNo("0");
				globalDao.setTotCost("0");	
			//	System.out.println("b4 globaldao");
				procIndex1 = globalDao.insert(dao);
			//	System.out.println("after globaldao");					
	         
		     // synchronized(dao)   
			 // {
	         	dao.fire();     // Here we Execute in Batch
	         	
	         	bFlag = true;
			 // }
		      
		      if(bFlag)
		      {
		    	
		    	  String strReturnReqNo="0";
		    	  
		    	  int funcIndex = 0;
					
					try {
						
						
						funcIndex = dao.setFunction("{? = call  mms_mst.generate_indentno (?, ?, ?, ?)}");
			

			
						
						dao.setFuncInValue(funcIndex, 2, vo.getStrHospitalCode());
						dao.setFuncInValue(funcIndex, 3, vo.getStrReqType());
						dao.setFuncInValue(funcIndex, 4, vo.getStrStoreId());
						dao.setFuncInValue(funcIndex, 5, vo.getStrItemCatg());
			
						dao.setFuncOutValue(funcIndex, 1);
			
						// Execute Function
						dao.executeFunction(funcIndex);
						strReturnReqNo = dao.getFuncString(funcIndex);
						//System.out.println("return req no:::  "+strReturnReqNo);
						long nTempReturnReqNo=Long.parseLong(strReturnReqNo);
						
							if(nTempReturnReqNo>0)
							{
								
								
								int nProcIndex_U;
	
								String strProcName_U = "";
	
								
								
								strProcName_U = "{call pkg_mms_dml.DML_UPDATE_HSTT_RETURN_REQ_DTL(?,?,?,?,?,?)}"; // Total 6 Values
								
								nProcIndex_U = dao.setProcedure(strProcName_U);
											
								
								//Total 6 variables
								
								
										dao.setProcInValue(nProcIndex_U, "p_mode", "1",1);
										
					                    dao.setProcInValue(nProcIndex_U, "p_hstnum_net_return_cost", vo.getStrNetCost(),5);
							
					                    dao.setProcInValue(nProcIndex_U, "p_hstnum_store_id", vo.getStrStoreId(),3);
					                    dao.setProcInValue(nProcIndex_U, "p_hstnum_retreq_no", Long.toString( (nTempReturnReqNo-1) ),2);
					                    dao.setProcInValue(nProcIndex_U, "p_gnum_hospital_code", vo.getStrHospitalCode(),4);
										
										
										/* Default Value */
							
										dao.setProcOutValue(nProcIndex_U, "err", 1,6);//16
							
										//dao.executeProcedureByPosition(nProcIndex_U);
							
										dao.executeProcedureByPosition(nProcIndex_U);
								
									
								vo.setStrMsgType("0");
							}
				      }
					  catch (Exception e) 
		    	      {
			    		//e.printStackTrace();
						vo.setStrMsgString("--> ReturnRequestTransDAO.INSERT()-->"	+ e.getMessage());
						vo.setStrMsgType("1");
				      } 
					
		      }	
		      
		      indentNo    = dao.getString(procIndex1, "indentNo");
		      approvalFlg = dao.getString(procIndex1, "approvalFlg");
			 // System.out.println("Indent No-->>"+indentNo);
			 // System.out.println("Approval Flg-->>"+approvalFlg);
			  INSERTINTABLE(vo,indentNo,approvalFlg); 
			 // System.out.println("After Inserttable");
	     	}
			catch (Exception e) 
    	    {
    		e.printStackTrace();
			vo.setStrMsgString("--> ReturnRequestTransDAO.INSERT()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} 
    	finally 
    	{
			if (dao != null)
				dao.free();
			dao = null;
		}

	}
	
	/**
	 * INSERT method is used to insert data in following table
	 * HSTT_CONDEMN_REQITEM_DTL
	 * @param vo
	 * @param indentNo
	 */
	
	public static void INSERTINTABLE(ReturnRequestTransVO vo,String indentNo,String approvalFlg) 
	{
		HisDAO dao = null;
		
	
		HisUtil hisutil = null;
		String[] temp = null;
		String[] strTemp = null;
		String[] reqQtyUnit=null;
		String strReqQty  = "";
		String strReqUnit ="";
		String strSancQty ="";
		String strSancQtyUnit="";

		DmlHsttReturnReqitemDtlDAO tableDao = null;
		DmlIndentReturnRequestDAO tableDao1 = null;
		try 
		{
			hisutil = new HisUtil("MMS","transactions.ReturnRequestTransDAO.INSERTINTABLE()");
			
			String strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			
			// Createing Object for Table Specific DAO
			tableDao1 = new DmlIndentReturnRequestDAO();
			tableDao  = new DmlHsttReturnReqitemDtlDAO();
    		dao = new HisDAO("MMS","transactions.ReturnRequestTransDAO.INSERTINTABLE()");
    		 		    		
 			int length = vo.getItemParamValue().length;
			
 			for(int i = 0;i<length;i++)
			{
		    				
				temp  = vo.getItemParamValue()[i].replace('#', '#').split("#");
				
				/*System.out.println("Display Value-->>>>"+temp[0]);
				System.out.println("Conversion  Value-->>>>"+temp[1]);
				System.out.println("User  Value-->>>>"+temp[2]);*/
			    
				strTemp         = temp[2].replace('^', '#').split("#");
						
				/*System.out.println("ItemID-1->>"+strTemp[0]);
				System.out.println("ItemBrandID-2->>"+strTemp[1]);
				System.out.println("GrpID-3->>"+strTemp[2]);
				System.out.println("Sub_GrpID-4->>"+strTemp[3]);
				System.out.println("Cosumble Flg-5->>"+strTemp[4]);
				System.out.println("Re-Order Qty-6->>"+strTemp[5]);
				System.out.println("Re-Order Qty unit Id-7->>"+strTemp[6]);
				System.out.println("In Hand Qty-8->>"+strTemp[7]);
				System.out.println("In Hand Qty Unit Id-9->>"+strTemp[8]);
				System.out.println("Last Rate-10->>"+strTemp[9]);
				System.out.println("Last Rate Unit Id-11->>"+strTemp[10]);
				
				
				System.out.println("Inventory Unit Id-12->>"+strTemp[11]);
				System.out.println("Last PO No-13->>"+strTemp[12]);
				System.out.println("Last PO Date-14->>"+strTemp[13]);
				System.out.println("Last Supplied By [Id]-15->>"+strTemp[14]);
				System.out.println("Batch No-16->>"+strTemp[15]);
				System.out.println("Expiry Date-17->>"+strTemp[16]);
				System.out.println("Manufacture Date-18->>"+strTemp[17]);
				System.out.println("Item Serial No-19->>"+strTemp[18]);
				System.out.println("Last Received Qty [PO]-20->>"+strTemp[19]);
				System.out.println("Last Received Qty Unit Id [PO]-21->>"+strTemp[20]);
				
				System.out.println("Last Indented Qty-22->>"+strTemp[21]);
				System.out.println("Last Indented Qty Unit Id-23->>"+strTemp[22]);
				System.out.println("Last Received Qty-24->>"+strTemp[23]);
				System.out.println("Last Received Qty Unit Id-25->>"+strTemp[24]);
				System.out.println("Last Year Consumption Qty-26->>"+strTemp[25]);
				System.out.println("Last Year Consumption Qty Unit Id-27->>"+strTemp[26]);
				System.out.println("Prefix-28->>"+strTemp[27]);
				System.out.println("Cost Parameter-29->>"+strTemp[28]);
				System.out.println("Cost Unit [on individual item or on total cost]-30->>"+strTemp[29]);
				System.out.println("Purchase Lead Time-31->>"+strTemp[30]);
				
				System.out.println("Purchase Lead Time Unit-32->>"+strTemp[31]);
				System.out.println("Stock Status-33->>"+strTemp[32]);
				
				  System.out.println("Return Request-in INDENT NO 2..->>>"+indentNo);
				  System.out.println("Return Request Store ID 2.....--->>>"+vo.getStrStoreId());*/
				     strReqQty      = vo.getStrReqQty()[i];
					 strReqUnit     = vo.getStrUnitName()[i];
		    	     reqQtyUnit = strReqUnit.split("\\^");

			    	 
				     if(approvalFlg.equals("0"))
				     {
				    	  strSancQty     = strReqQty;
				 		  strSancQtyUnit = reqQtyUnit[0];
				     }	
				     else
				     {
				    	 strSancQty     = "0";
				    	 strSancQtyUnit = "0";
				     }	  		    	 

				    tableDao.setStrExpiryDate(strTemp[16]);
				    
				    tableDao.setStrRetReqNo(indentNo);
				    tableDao.setStrStoreId(vo.getStrStoreId()); 
				 	tableDao.setStrHospCode(vo.getStrHospitalCode());
				 	tableDao.setStrGroupId(strTemp[2]);
				 	tableDao.setStrBatchNo(strTemp[15]);
				 	tableDao.setStrItemSlNo(strTemp[18]);
				 	tableDao.setStrStockStatusCode(strTemp[32]);
				 	tableDao.setStrSubGroupId(strTemp[3]);
				 	tableDao.setStrItemId(strTemp[0]);
				 	tableDao.setStrItemBrandId(strTemp[1]);
				 	tableDao.setStrRate(strTemp[9]);
				 	tableDao.setStrRateUnitId(strTemp[10]);
				 	tableDao.setStrReqQty(strReqQty);   
				 	tableDao.setStrReqQtyUnitId(reqQtyUnit[0]);
				 	tableDao.setStrSancQty(strSancQty);
				 	tableDao.setStrSancQtyUnitId(strSancQtyUnit);
				 	tableDao.setStrInHandQty(strTemp[7]);
				 	tableDao.setStrInHandQtyUnitId(strTemp[8]);
				 	tableDao.setStrReturnFlag("0");
				 	tableDao.setStrConsumableFlag("1");
				 	tableDao.setStrLastPONo(strTemp[12]);
				 	tableDao.setStrLastPODate(strTemp[13]);
				 	tableDao.setStrLastRecDate(strCtDate);
				 	tableDao.setStrRemarks(vo.getStrRemarks());
				 
				 	 tableDao1.setStrId(vo.getStrStoreId()); 
					 tableDao1.setToStoreId(vo.getStrToStore());
					 tableDao1.setHosp_code(vo.getStrHospitalCode());
					 tableDao1.setReqNo(indentNo); 
					 tableDao1.setItemId(strTemp[0]);
					 tableDao1.setItemBrandId(strTemp[1]);
					 tableDao1.setReqTypeId(vo.getStrReqType());
					 tableDao1.setStrBatchNo(strTemp[15]);
					 tableDao1.setStrFinancialEndDate(vo.getStrFinancialEndYear());
					 tableDao1.setStrFinancialStarDate(vo.getStrFinancialStartYear());
					 tableDao1.setStrIsValid("1");
					 tableDao1.setStrItemCatgNo(vo.getStrItemCatg());
					 tableDao1.setStrQty(strTemp[7]);
					 tableDao1.setStrSancQty(strReqQty);
					 tableDao1.setStrRecevedBy("");
					 tableDao1.setStrRemarks(vo.getStrRemarks());
					 tableDao1.setStrSancQtyUnit(reqQtyUnit[0]);
					 tableDao1.setStrStockStatusCode(strTemp[32]);
					 tableDao1.setStrUnitId(strTemp[8]);
					 tableDao1.setStrSeatId(vo.getStrSeatId());
					 tableDao1.setStrSlNo(strTemp[18]);
//					 System.out.println("Catg No-->>"+vo.getStrItemCatg());
//				 	 System.out.println("Staus Code-->>"+strTemp[32]);
//				 	 System.out.println("Sl No-->>"+strTemp[18]);
					// System.out.println("br4 insert in tabledao");
					tableDao.insert(dao);
					//System.out.println("after inser in tabledao");
				    tableDao1.insert(dao);
				  //  System.out.println("after inser in table1111dao");
					
	          }
		      synchronized(dao)   
			  {
	        	dao.fire();     // Here we Execute in Batch
			  }
		} 
    	catch (Exception e) 
    	{
    		//e.printStackTrace();
			vo.setStrMsgString("-->ReturnRequestTransVO.INSERTINTABLE()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} 
    	finally 
    	{
			if (dao != null)
				dao.free();
			dao = null;
		}

	}


}
