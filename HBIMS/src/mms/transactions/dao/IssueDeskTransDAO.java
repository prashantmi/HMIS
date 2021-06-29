/**
 * 
 */
package mms.transactions.dao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.dao.IssueDetailDAO;
import mms.dao.IssueItemDetailDAO;
import mms.transactions.vo.AnnualPurchaseIndentVO;
import mms.transactions.vo.IssueDeskTransVO;



/**
 * @author Amit Kumar)
 * @version 1.0
 * @since 01/Apr/2009
 * 
 */

public class IssueDeskTransDAO {
	
	
	/**
	 * To Get Data
	 * 
	 * @param budgetAllocationDetailProcessTransVO_p
	 */
	public static void getAvalBudgetDetails(IssueDeskTransVO vo) 
	{
		String err = "";
		String proc_name1 = "{call pkg_mms_view.proc_hstt_budget_dtl(?,?,?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;

		try {

				dao = new HisDAO("MMS Transactions","IssueDeskTransDAO");
						
				procIndex1 = dao.setProcedure(proc_name1);
				
				dao.setProcInValue(procIndex1, "p_mode", "4",1);
				// set value
				dao.setProcInValue(procIndex1, "p_hstnum_store_id", vo.getStrStoreId(),2);
//				System.out.println("Store ID::::"+vo.getStrStoreId());
				System.out.println("Fin Start Date::::"+vo.getStrFinancialStartDate());
                System.out.println("Fin End Date:::::"+vo.getStrFinancialEndDate());
//                System.out.println("Hosp Code:::::"+vo.getStrHospitalCode());
				dao.setProcInValue(procIndex1, "p_hstdt_finstart_date", vo.getStrFinancialStartDate(),3);
				dao.setProcInValue(procIndex1, "p_hstdt_finend_date", vo.getStrFinancialEndDate(),4);
				dao.setProcInValue(procIndex1, "p_gnum_hospital_code", vo.getStrHospitalCode(),5);
				dao.setProcInValue(procIndex1, "p_hstnum_slno", "0",6);
				/* Setting Default Value End */
				
				dao.setProcOutValue(procIndex1, "err", 1,7); // 1 for string return
				// value
				dao.setProcOutValue(procIndex1, "resultset", 2,8); // 2 for object
		
				// execute procedure
		
				dao.executeProcedureByPosition(procIndex1);
		
				// get value
				err = dao.getString(procIndex1, "err");
		
				if (err == null)
					err = "";
		
				ws = dao.getWebRowSet(procIndex1, "resultset");
						
				while(ws.next())
				{
					vo.setStrAvalaibleBudget(ws.getString(1));
					vo.setStrAvalaibleBudgetDtl(ws.getString(2)+"$$"+ws.getString(3));
				}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IssueTransDAO.getAvalBudgetDetails() --> "	+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
		
	}
	
	
	
	/**
	 * This function is used to set initial values in Received by combo.
	 * @param _ItemTransferTransVO
	 */
	public static void setApprovedByCombo(IssueDeskTransVO  _IssueDeskTransVO)
	
	{
		
		String strProcName = "{call PKG_MMS_VIEW.proc_store_emp_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		WebRowSet ws = null;
		HisDAO daoObj=null;
		try
		{
			daoObj  = new HisDAO("MMSModule","IssueDeskTransDAO");
			
			nProcIndex = daoObj.setProcedure(strProcName);
						
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "storeId", _IssueDeskTransVO.getStrRaisingStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", _IssueDeskTransVO.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err",1,4); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,5);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(strErr.equals(""))
			{
				_IssueDeskTransVO.setRecievedByWS(ws);
			}
		}
		catch(Exception _err)
		{
			_IssueDeskTransVO.setStrMsgString("IssueDeskTransDAO.setApprovedByCombo() --> "
					+ _err.getMessage());
			_IssueDeskTransVO.setStrMsgType("1");
		}
	}
	

	/**
	 * To get Indent Details i.e.(Store Name,Indent No.,Indent Date,Indent
	 * Type,Item Category,Raising Store) on 'issue' page
	 * 
	 * @param vo
	 */
	public static void getIndentDetail(IssueDeskTransVO vo) 
	{
		String err = "";
		String proc_name1 = "{call pkg_mms_view.proc_indent_dtl(?,?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;

		try 
		{
			dao = new HisDAO("MMS","IssueDeskTransDAO.getIndentDetail(IssueDeskTransVO vo)");

			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modeval", "1",1);
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(procIndex1, "indent_no", vo.getStrIndentNo(),3);
			dao.setProcInValue(procIndex1, "store_id", vo.getStrStoreId(),4);						
			dao.setProcInValue(procIndex1, "itemcat_no", "",5); 


			dao.setProcOutValue(procIndex1, "err", 1,6); // 1 for string return
			dao.setProcOutValue(procIndex1, "resultset", 2,7); // 2 for object

			dao.executeProcedureByPosition(procIndex1);

			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");

				if (ws.size() > 0) 
				{
					ws.next();

					vo.setStrIndentNo(ws.getString(1));
					vo.setStrIndentDate(ws.getString(2));
					vo.setStrIndentType(ws.getString(3));
					vo.setStrItemCategory(ws.getString(4));
					vo.setStrRaisingStoreId(ws.getString(5));
					vo.setStrRaisingStoreName(ws.getString(6));
					vo.setStrItemCategoryNo(ws.getString(7));
					vo.setStrReqStatusName(ws.getString(8));
				}
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("IssueDeskTransDAO.getIndentDetail() --> "+ e.getMessage());
			vo.setStrMsgType("1");
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

	/**
	 * To get Item Details on 'Issue' page
	 * 
	 * @param vo
	 */
	public static void getItemDetail(IssueDeskTransVO vo) {

		String err = "";
		String proc_name1 = "{call Pkg_Mms_View.proc_indentissue_item_dtls(?,?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;

		try {

			dao = new HisDAO("mms", "IssueDeskTransDAO");

			procIndex1 = dao.setProcedure(proc_name1);

		System.out.println(" Issuing StoreId()-"+vo.getStrStoreId());
		System.out.println(" vo	.getStrRaisingStoreId()"+vo.getStrRaisingStoreId());
		System.out.println(" vo.getStrIndentNo()-" + vo.getStrIndentNo());
			
			// set value
			dao.setProcInValue(procIndex1, "modeval", "1",1);
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(procIndex1, "indentno", vo.getStrIndentNo(),3);
			dao.setProcInValue(procIndex1, "frmstoreid", vo.getStrRaisingStoreId(),4);
			dao.setProcInValue(procIndex1, "issuingStoreid", vo.getStrStoreId(),5);
			dao.setProcOutValue(procIndex1, "err", 1,6); // 1 for string return
			// value

			dao.setProcOutValue(procIndex1, "resultset", 2,7); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			if (err.equals("")) {

				ws = dao.getWebRowSet(procIndex1, "resultset");

				vo.setItemDetailsWS(ws);
		
			} else {

				throw new Exception(err);
			}

		} catch (Exception e) {

			vo.setStrMsgString("IssueDeskTransDAO.getItemDetail() --> "
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
	 * This function is used to to populate the value of Unit combo
	 * 
	 * @param vo
	 */
	public static void getUnitCombo(IssueDeskTransVO vo) {

		HisDAO dao = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strErr = "";

		try {
			dao = new HisDAO("mms", "IssueDeskTransDAO");

			strProcName = "{call Pkg_Mms_View.proc_GBLT_UNIT_MST(?,?,?,?,?,?)}";
			nProcIndex = dao.setProcedure(strProcName);

			if (!vo.getStrSancUnitId().equals("0"))
			{
				dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode() ,1);
				dao.setProcInValue(nProcIndex, "unit_id", vo.getStrSancUnitId(),2);
				if(vo.getStrSancUnitId().length() == 4)
					dao.setProcInValue(nProcIndex, "modeval", "5",4);
				else
					dao.setProcInValue(nProcIndex, "modeval", "3",4);
				//dao.setProcInValue(nProcIndex, "modeval", "5",4);
				dao.setProcInValue(nProcIndex, "module_id", "",3); // default value.
				dao.setProcOutValue(nProcIndex, "err", 1,5);
				dao.setProcOutValue(nProcIndex, "resultset", 2,6);

				dao.executeProcedureByPosition(nProcIndex);

				strErr = dao.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = dao.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					vo.setUnitComboWS(ws);

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			vo.setStrMsgString("IssueDeskTransDAO.getUnitCombo() --> "
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
	 * This function is used to GET THE BATCH WISE ITEM DETAILS FOR POPUP
	 * 
	 * @param vo
	 */
	public static void getPopUpInfoProc(IssueDeskTransVO vo) {

		HisDAO dao = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strErr = "";

		try {
			dao = new HisDAO("mms", "IssueDeskTransDAO");

			//strProcName = "{call pkg_mms_view.Proc_stock_dtl(?,?,?,?,?,?,?,?,?,?)}";
			strProcName = "{call Pkg_Mms_View.proc_itemStock_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
			nProcIndex = dao.setProcedure(strProcName);
			//System.out.println("vo.getStrItemId()-" + vo.getStrItemId());
			//System.out.println("vo.getStrItemBrand()-" + vo.getStrItemBrand());
			//System.out.println("dao popup vo.getStrStoreId()-" + vo.getStrStoreId());
			//System.out.println("dao popup vo.getStrReservedFlag()-" + vo.getStrReservedFlag());

			if (vo.getStrItemCategoryNo().equals("10"))//1 modified to 10 by shalini
				dao.setProcInValue(nProcIndex, "modeval", "4",1);
			else
				dao.setProcInValue(nProcIndex, "modeval", "5",1);

			dao
					.setProcInValue(nProcIndex, "hosp_code", vo
							.getStrHospitalCode(),2);

			dao.setProcInValue(nProcIndex, "item_id", vo.getStrItemId(),3);
			dao
					.setProcInValue(nProcIndex, "itembrand_id", vo
							.getStrItemBrand(),4);
			dao.setProcInValue(nProcIndex, "store_id", vo.getStrStoreId(),5); 

			dao.setProcInValue(nProcIndex, "reserved_flag", vo.getStrReservedFlag(),9);
			
			/* Setting Default Value Start*/
			dao.setProcInValue(nProcIndex, "batch_no", "",6);
			dao.setProcInValue(nProcIndex, "item_sl_no", "",7);
			dao.setProcInValue(nProcIndex, "sl_no", "",8);
			/* Setting Default Value End */
			

			dao.setProcOutValue(nProcIndex, "err", 1,10);
			dao.setProcOutValue(nProcIndex, "resultset", 2,11);

			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(nProcIndex, "resultset"); 
			/*nProcIndex = dao.setProcedure(strProcName);

			// set value
			dao.setProcInValue(nProcIndex, "modval", "1");
			dao.setProcInValue(nProcIndex, "store_id", vo.getStrStoreId());
			dao
					.setProcInValue(nProcIndex, "catCode", vo
							.getStrItemCategoryNo());
			dao.setProcInValue(nProcIndex, "item_id", vo.getStrItemId());
			dao.setProcInValue(nProcIndex, "itembrand_id", vo.getStrItemBrand());
			dao.setProcInValue(nProcIndex, "stock_status", vo
					.getStrStockStatus());
			dao.setProcInValue(nProcIndex, "reservedStockFlag", vo
					.getStrReservedFlag());
			dao
					.setProcInValue(nProcIndex, "hosp_code", vo
							.getStrHospitalCode());

			dao.setProcOutValue(nProcIndex, "ERR", 1); // 1 for string return
			// value

			dao.setProcOutValue(nProcIndex, "RESULTSET", 2); // 2 for object

			// execute procedure

			dao.executeProcedure(nProcIndex);

			// get value
			err = dao.getString(nProcIndex, "ERR");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(nProcIndex, "RESULTSET"); */

			if (strErr.equals("")) {

				vo.setPopupWS(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			//e.printStackTrace();
			vo.setStrMsgString("IssueDeskTransDAO.getPopUpInfoProc() --> "
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
	 * To insert data
	 * 
	 * @param vo
	 */
	public static synchronized void insertData(IssueDeskTransVO vo) 
	{

		HisDAO                            dao = null;
		IssueDetailDAO issueDetailDAO         = null;
		IssueItemDetailDAO issueItemDetailDAO = null;
		String                    strFuncName = "";		
		String                     strIssueNo = "0";
		String                 strItemTotCost = "0.00";
		String      strStochStatusCodeArray[] = null;
		String            strBatchSlNoArray[] = null;
		String             strItemSlNoArray[] = null;
		String       strIssueQtyBtchWsArray[] = null;
		String   strIssueQtyUnitBtchWsArray[] = null;
		String            strManufDateArray[] = null;
		String           strExpiryDateArray[] = null;
		String                 strRateArray[] = null;
		String           strRateUnitIdArray[] = null;
		String                       values[] = null;
		String                         temp[] = null;		
		double                         cltamt = 0.00;
		double                      totalCost = 0.00;
		String                  strProcName_U = "";
		NumberFormat                formatter = null;
		int               strIndentStatusFlag = 0;
		int                       batchLength = 0;		
		int                        nFuncIndex = 0;
		int                        nProcIndex_U;
		String 							bsReqNo="0";
		int 							pIndex;
		String 							p_name="";
		try
		{			
							              dao = new HisDAO("mms", "IssueDeskTransDAO");
							   issueDetailDAO = new IssueDetailDAO();
						   issueItemDetailDAO = new IssueItemDetailDAO();
			for (int i = 0; i < vo.getStrItemIdArray().length; i++) 
			{               
				if (!vo.getStrIssueQtyArray()[i].equals("0") && strIssueNo.equals("0")) 
				{
					strFuncName = "{? = call MMS_MST.generate_issueNo(?,?,?,?)}";
					       
					nFuncIndex = dao.setFunction(strFuncName);
					dao.setFuncInValue(nFuncIndex, 2, vo.getStrHospitalCode());
					dao.setFuncInValue(nFuncIndex, 3, vo.getStrStoreId());
					dao.setFuncInValue(nFuncIndex, 4, vo.getStrReqTypeId());
					dao.setFuncInValue(nFuncIndex, 5, vo.getStrItemCategoryNo());
		
					dao.setFuncOutValue(nFuncIndex, 1);
					dao.executeFunction(nFuncIndex);
					strIssueNo = dao.getFuncString(nFuncIndex);
				}
			}
			 //System.out.println("strIssueNo-" + strIssueNo);
			// vo.setStrIssueNo(strIssueNo);
			
			// insert data in

						//System.out.println("dao getStrHospitalCode-"+vo.getStrHospitalCode());
						issueDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());
						issueDetailDAO.setStrFinancialEndYr(vo.getStrFinancialEndYear());
						issueDetailDAO.setStrFinancialStartYr(vo.getStrFinancialStartYear());
						issueDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());
						issueDetailDAO.setStrIndentDate(vo.getStrIndentDate());
						issueDetailDAO.setStrIndentNO(vo.getStrIndentNo());
						issueDetailDAO.setStrIssueNO(strIssueNo);
						issueDetailDAO.setStrItemCategoryNo(vo.getStrItemCategoryNo());
						issueDetailDAO.setStrNetCost(Double.toString(totalCost));
						issueDetailDAO.setStrReceivedBy(vo.getStrReceivedBy());
						issueDetailDAO.setStrRemarks(vo.getStrRemarks());
						issueDetailDAO.setStrReqStoreId(vo.getStrRaisingStoreId());
						issueDetailDAO.setStrSeatId(vo.getStrSeatId());
						issueDetailDAO.setStrStoreId(vo.getStrStoreId());
						issueDetailDAO.setStrReqTypeId(vo.getStrReqTypeId());
						//System.out.println("Issue No::::"+strIssueNo);
						
			            // Calling BO method
						//if(!strIssueNo.equals("0"))
							issueDetailDAO.insert(dao);		

			// insert data in

			for (int i = 0; i < vo.getStrItemIdArray().length; i++) 
			{               
				if (!vo.getStrIssueQtyArray()[i].equals("0")) 
				{					
					if (vo.getStockDtlsId()!=null && !vo.getStockDtlsId().equals(""))
					{
						if (!vo.getStockDtlsId()[i].equals("") && vo.getStockDtlsId()[i] != null) 
						{
						values = vo.getStockDtlsId()[i].split("#"); 
                        //    0       1         		2			3	            4        5         6           7                8               9               10                  11              12      13          14           15          16   17  18      19       
						// StoreId^Generic_ItemId^Brand_ItemId^Stock_Status_Code^Batch_No^Serial_No^Expiry date^Manufactre Date^In Hand Qty ^ In Hand Qty Unit ^ Purchase Rate ^ Purchase Rate Unit ^ Rate ^ Rate Unit ^ Issue Qty ^ Issue Qty Unit ^   ^   ^ Cost ^ Cost
						// 99901120^10000067^10100067^UIT11738^10^0^31-May-2014^01-Dec-2011^24990^6301^21.54^6306^22.62^6306^10^6301^1^0^2.26^2.26
						batchLength = values.length;
						for (int j = 0; j < batchLength; j++) 
						{
							   strStochStatusCodeArray = new String[batchLength];
							         strBatchSlNoArray = new String[batchLength];
							          strItemSlNoArray = new String[batchLength];
							    strIssueQtyBtchWsArray = new String[batchLength];
							strIssueQtyUnitBtchWsArray = new String[batchLength];
							         strManufDateArray = new String[batchLength];
							        strExpiryDateArray = new String[batchLength];
							              strRateArray = new String[batchLength];
							        strRateUnitIdArray = new String[batchLength];							
							                      temp = values[j].replace("^", "#").split("#");
							strStochStatusCodeArray[j] = temp[4];
								  strBatchSlNoArray[j] = temp[3];
							
							if(temp[4]==null)
							{	
								strStochStatusCodeArray[j] = "0";
							}
							else
							{
								strStochStatusCodeArray[j] = temp[4];
							}
							
							if(temp[5]==null)
							{
								strItemSlNoArray[j] = "0";
							}
							else
							{
								strItemSlNoArray[j] = temp[5];
							}
							if(temp[7]==null)
							{
								strManufDateArray[j] = "";
							}
							else
							{
								strManufDateArray[j]  = temp[7];
							}
							if(temp[6]!=null && temp.length > 10 )
							{
								strExpiryDateArray[j] = temp[6];
							}
							else
							{
								strExpiryDateArray[j] = "";
							}
							              strRateArray[j] = temp[12];                 // O.V 10
							        strRateUnitIdArray[j] = temp[13];                 // O.V 11
							    strIssueQtyBtchWsArray[j] = temp[14];
							strIssueQtyUnitBtchWsArray[j] = temp[15];

							issueItemDetailDAO.setStrBalQty(vo.getStrBalQtyArray()[i]);
							issueItemDetailDAO.setStrBalQtyUnitId(vo.getStrBalQtyUnitIdArray()[i]);
							issueItemDetailDAO.setStrGroupId(vo.getStrGroupIdArray()[i]);
							issueItemDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());
							issueItemDetailDAO.setStrIndentNO(vo.getStrIndentNo());
							issueItemDetailDAO.setStrInHandQty(vo.getStrAvlQtyArray()[i]);
							issueItemDetailDAO.setStrInHandQtyUnitId(vo.getStrAvlQtyUnitIdArray()[i]);
							issueItemDetailDAO.setStrIssueNO(strIssueNo);
							issueItemDetailDAO.setStrItemBrandId(vo.getStrBrandIdArray()[i]);
							issueItemDetailDAO.setStrItemId(vo.getStrItemIdArray()[i]);
							issueItemDetailDAO.setStrRemarks(vo.getStrItemRemarksArray()[i]);
							issueItemDetailDAO.setStrSeatId(vo.getStrSeatId());
							issueItemDetailDAO.setStrIssuingStoreId(vo.getStrStoreId());
							issueItemDetailDAO.setStrRaisingStoreId(vo.getStrRaisingStoreId());
							issueItemDetailDAO.setStrSubGroupId(vo.getStrSubGroupIdArray()[i]);
							issueItemDetailDAO.setStrReservedQtyFlag(vo.getStrReservedFlagArray()[i]);
							issueItemDetailDAO.setStrCategoryNo(vo.getStrItemCategoryNo());
							issueItemDetailDAO.setStrReqTypeId(vo.getStrReqTypeId());
							issueItemDetailDAO.setStrStockStatusCode(strStochStatusCodeArray[j]);
							issueItemDetailDAO.setStrConsumableFlag(vo.getStrConsumableFlagArray()[i]);
							issueItemDetailDAO.setStrBatchSlNo(strBatchSlNoArray[j]);
							issueItemDetailDAO.setStrItemSlno(strItemSlNoArray[j]);
							//issueItemDetailDAO.setStrStckStatusCode(strStochStatusCodeArray[j]);
							issueItemDetailDAO.setStrManufDate(strManufDateArray[j]);
							issueItemDetailDAO.setStrExpiryDate(strExpiryDateArray[j]);
							/*issueItemDetailDAO
							.setStrExpiryDate("21-May-2010");*/
							issueItemDetailDAO.setStrRate(strRateArray[j]);
							issueItemDetailDAO.setStrRateUnitId(strRateUnitIdArray[j]);
							issueItemDetailDAO.setStrIssueQty(strIssueQtyBtchWsArray[j]);
							issueItemDetailDAO.setStrIssueQtyUnitId(strIssueQtyUnitBtchWsArray[j]);
							// Calling BO method
							issueItemDetailDAO.insert(dao);   // Pkg_Mms_Dml.Dml_Issue_Items_Dtls  Mode  = 1
							/*
							nProcIndex = issueItemDetailDAO.insert(dao);
							*/							
											   //  formatter = new DecimalFormat("############.##");  						    
											/*if(vo.getStrFinalCost()!=null && vo.getStrFinalCost()[i]!=null){
											     strItemTotCost = formatter.format(new BigDecimal(vo.getStrFinalCost()[i])); 							
											       cltamt  = Double.parseDouble(strItemTotCost);												
											     totalCost = totalCost + cltamt;	
											}*/
							
									if(strIssueQtyBtchWsArray[j].equals(vo.getStrBalQtyArray()[i]))
									{
									   strIndentStatusFlag = strIndentStatusFlag+0;
									}
							
									else
									{
									   strIndentStatusFlag = strIndentStatusFlag+1;
									}	
						}
						
						}
						else 
						{
							
							issueItemDetailDAO.setStrBalQty(vo.getStrBalQtyArray()[i]);
							issueItemDetailDAO.setStrBalQtyUnitId(vo.getStrBalQtyUnitIdArray()[i]);
							issueItemDetailDAO.setStrGroupId(vo.getStrGroupIdArray()[i]);
							issueItemDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());
							issueItemDetailDAO.setStrIndentNO(vo.getStrIndentNo());
							issueItemDetailDAO.setStrInHandQty(vo.getStrAvlQtyArray()[i]);
							issueItemDetailDAO.setStrInHandQtyUnitId(vo.getStrAvlQtyUnitIdArray()[i]);
							issueItemDetailDAO.setStrIssueNO(strIssueNo);
							issueItemDetailDAO.setStrIssueQty(vo.getStrIssueQtyArray()[i]);
							             temp = vo.getStrIssueQtyUnitIdArray()[i].replace("^","#").split("#");
							issueItemDetailDAO.setStrIssueQtyUnitId(temp[0]);
							issueItemDetailDAO.setStrItemBrandId(vo.getStrBrandIdArray()[i]);
							issueItemDetailDAO.setStrItemId(vo.getStrItemIdArray()[i]);
							issueItemDetailDAO.setStrRate("0"); // not used in proc mode 2
							issueItemDetailDAO.setStrRateUnitId("0"); // not used in proc mode 2
							issueItemDetailDAO.setStrRemarks(vo.getStrItemRemarksArray()[i]);
							issueItemDetailDAO.setStrSeatId(vo.getStrSeatId());
							issueItemDetailDAO.setStrIssuingStoreId(vo.getStrStoreId());
							issueItemDetailDAO.setStrRaisingStoreId(vo.getStrRaisingStoreId());
							issueItemDetailDAO.setStrSubGroupId(vo.getStrSubGroupIdArray()[i]);
							issueItemDetailDAO.setStrReservedQtyFlag(vo.getStrReservedFlagArray()[i]);
							issueItemDetailDAO.setStrCategoryNo(vo.getStrItemCategoryNo());
							issueItemDetailDAO.setStrReqTypeId(vo.getStrReqTypeId());
							issueItemDetailDAO.setStrStockStatusCode(vo.getStrStochStatusCodeArray()[i]);
							issueItemDetailDAO.setStrConsumableFlag(vo.getStrConsumableFlagArray()[i]);
	                        // Calling BO Method
							issueItemDetailDAO.insert2(dao);
							if(vo.getStrIssueQtyArray()[i].equals(vo.getStrBalQtyArray()[i]))
							{
								strIndentStatusFlag = strIndentStatusFlag+0;
							}
							else
							{
								strIndentStatusFlag = strIndentStatusFlag+1;
							}	
							
						}
					}
					else 
					{
						
						issueItemDetailDAO.setStrBalQty(vo.getStrBalQtyArray()[i]);
						issueItemDetailDAO.setStrBalQtyUnitId(vo.getStrBalQtyUnitIdArray()[i]);
						issueItemDetailDAO.setStrGroupId(vo.getStrGroupIdArray()[i]);
						issueItemDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());
						issueItemDetailDAO.setStrIndentNO(vo.getStrIndentNo());
						issueItemDetailDAO.setStrInHandQty(vo.getStrAvlQtyArray()[i]);
						issueItemDetailDAO.setStrInHandQtyUnitId(vo.getStrAvlQtyUnitIdArray()[i]);
						issueItemDetailDAO.setStrIssueNO(strIssueNo);
						issueItemDetailDAO.setStrIssueQty(vo.getStrIssueQtyArray()[i]);
						             temp = vo.getStrIssueQtyUnitIdArray()[i].replace("^","#").split("#");
						issueItemDetailDAO.setStrIssueQtyUnitId(temp[0]);
						issueItemDetailDAO.setStrItemBrandId(vo.getStrBrandIdArray()[i]);
						issueItemDetailDAO.setStrItemId(vo.getStrItemIdArray()[i]);
						issueItemDetailDAO.setStrRate("0"); // not used in proc mode 2
						issueItemDetailDAO.setStrRateUnitId("0"); // not used in proc mode 2
						issueItemDetailDAO.setStrRemarks(vo.getStrItemRemarksArray()[i]);
						issueItemDetailDAO.setStrSeatId(vo.getStrSeatId());
						issueItemDetailDAO.setStrIssuingStoreId(vo.getStrStoreId());
						issueItemDetailDAO.setStrRaisingStoreId(vo.getStrRaisingStoreId());
						issueItemDetailDAO.setStrSubGroupId(vo.getStrSubGroupIdArray()[i]);
						issueItemDetailDAO.setStrReservedQtyFlag(vo.getStrReservedFlagArray()[i]);
						issueItemDetailDAO.setStrCategoryNo(vo.getStrItemCategoryNo());
						issueItemDetailDAO.setStrReqTypeId(vo.getStrReqTypeId());
						issueItemDetailDAO.setStrStockStatusCode(vo.getStrStochStatusCodeArray()[i]);
						issueItemDetailDAO.setStrConsumableFlag(vo.getStrConsumableFlagArray()[i]);
                        // Calling BO Method
						issueItemDetailDAO.insert2(dao);
						if(vo.getStrIssueQtyArray()[i].equals(vo.getStrBalQtyArray()[i]))
						{
							strIndentStatusFlag = strIndentStatusFlag+0;
						}
						else
						{
							strIndentStatusFlag = strIndentStatusFlag+1;
						}	
						
					}
					
					issueItemDetailDAO.setStrTotIssueQty(vo.getStrIssueQtyArray()[i]);
					temp = vo.getStrIssueQtyUnitIdArray()[i].replace("^","#").split("#");
					issueItemDetailDAO.setStrTotIssueQtyUnit(temp[0]);
					issueItemDetailDAO.setStrItemBrandId(vo.getStrBrandIdArray()[i]);
					issueItemDetailDAO.setStrItemId(vo.getStrItemIdArray()[i]);
					issueItemDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());
					issueItemDetailDAO.setStrRaisingStoreId(vo.getStrRaisingStoreId());
					/*This Method is Used To Update HSTNUM_LAST_RECEIVEQTY 
					 * In Table hstt_storeitem_mst
					 * */
					issueItemDetailDAO.update1(dao);   // PKG_MMS_DML.dml_update_store_item_dtls  Mode  = 1
					
				}
			}
			
			//strNetCost = Float.toString(netCost);
			//strNetCost = HisUtil.getAmountWithDecimal(strNetCost, 2);
			// ////System.out.println("strNetSetRate-"+strNetSetRate);
			// ////System.out.println("strNetSalePrice-"+strNetSalePrice);

			
			
			/*
			issueDetailDAO.setStrAvalaibleBudget(vo.getStrAvalaibleBudget());
			// Calling BO method
			issueDetailDAO.update2(dao);
			*/
			
			if(vo.getStrBudgetFlg().equals("1")) //Here We Check 
			{
				/*
			   issueDetailDAO.setStrIssueNO(strIssueNo);
			   issueDetailDAO.setStrReqStoreId(vo.getStrRaisingStoreId());
			   issueDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());	
			   issueDetailDAO.setStrAvalaibleBudget(vo.getStrFinalApproxAmt());  // Final Approx Amount
			   issueDetailDAO.update2(dao);
			  */
			   
			    strProcName_U = "{call pkg_mms_dml.dml_offline_issue_budget_dtl(?,?,?,?,?,?,?,?,?)}"; // Total 16 Values
				
				nProcIndex_U = dao.setProcedure(strProcName_U);
																		
				dao.setProcInValue(nProcIndex_U, "modeval", "1");
				dao.setProcInValue(nProcIndex_U, "hosp_code",	vo.getStrHospitalCode());
				dao.setProcInValue(nProcIndex_U, "store_id",	vo.getStrStoreId());
				dao.setProcInValue(nProcIndex_U, "issueno",	strIssueNo);
				dao.setProcInValue(nProcIndex_U, "reqstore_id", vo.getStrRaisingStoreId()); 
				dao.setProcInValue(nProcIndex_U, "budgetAvl", vo.getStrFinalApproxAmt()); 
				dao.setProcInValue(nProcIndex_U, "finStartDate", vo.getStrFinancialStartDate());
				dao.setProcInValue(nProcIndex_U, "finEndDate",	vo.getStrFinancialEndDate());
				
				/* Default Value */
				dao.setProcOutValue(nProcIndex_U, "err", 1);
				dao.execute(nProcIndex_U,1);
			  		  
			}
			if(vo.getStrBSIndent().equals("1"))
			{
				int itmln = vo.getStrBSQuantity().length;
				
				AnnualPurchaseIndentVO indentvo = new AnnualPurchaseIndentVO();
				indentvo.setStrStoreId(vo.getStrStoreId());
				indentvo.setStrHospitalCode(vo.getStrHospitalCode());
				indentvo.setStrReqType("11");
				indentvo.setStrToStoreCombo(vo.getStrStoreId());
				indentvo.setStrItemCategoryNo(vo.getStrItemCategoryNo());
				indentvo.setStrUrgentFlg("0");//need to change later
				indentvo.setStrFinancialEndYear(vo.getStrFinancialEndDate());
				indentvo.setStrFinancialStartYear(vo.getStrFinancialStartDate());
				indentvo.setStrRemarks(vo.getStrRemarks());
				indentvo.setStrSeatId(vo.getStrSeatId());
				
				List<String> tArr=new ArrayList<String>();
				List<String> reqQty=new ArrayList<String>();
				List<String> reqUnit=new ArrayList<String>();
				for(int i=0;i<itmln;i++)
				{
					//10000002@10100002@11.00@6301@997533.00@gt5@2.36
				//	temp = indentvo.getStrItemParamValue()[i].replace('@', '#').split("#");
				   
					 //int j=0;
					
					 if(!vo.getStrBSQuantity()[i].equals("0"))
					 {
						tArr.add("0#0#"+vo.getStrItemIdArray()[i]+"^"+vo.getStrBrandIdArray()[i]+"^"+vo.getStrGroupIdArray()[i]+"^0^0^0^0^"+vo.getStrAvlQtyArray()[i]+"^"+vo.getStrAvlQtyUnitIdArray()[i]+"^"+"0"+"^"+"0"+"^0^0^^0^0^0^0^0^0^0^0^0^0^0^0^0") ;
						reqQty.add(vo.getStrBSQuantity()[i]);
						reqUnit.add(vo.getStrAvlQtyUnitIdArray()[i]);
						//j++;
					 }
					 
				
				}
				
				String [] arr = tArr.toArray(new String[tArr.size()]);
				String [] arr1 = reqQty.toArray(new String[reqQty.size()]);
				String [] arr2 = reqUnit.toArray(new String[reqUnit.size()]);
				
				indentvo.setItemParamValue(arr);
				indentvo.setStrReqQty(arr1);
				indentvo.setStrUnitName(arr2);
				indentvo.setStrCostRequired("0");
				AnnualPurchaseIndentDAO.INSERT(indentvo);
				
				if(indentvo.getStrIndentNo() != null && !indentvo.getStrIndentNo().equals(""))
					bsReqNo = indentvo.getStrIndentNo();
			//	
				vo.setStrBSReqNo(bsReqNo);
				// added to update Bs req no in sstt_indent_dtl table
				p_name = "{call pkg_mms_dml.Dml_update_sstt_indent_dtl(?,?,?,?,? ,?)}";
			
				pIndex = dao.setProcedure(p_name);
			
				dao.setProcInValue(pIndex, "modval", "1", 1);
				dao.setProcInValue(pIndex, "hosp_code", indentvo.getStrHospitalCode(), 2);
				dao.setProcInValue(pIndex, "reqNo", vo.getStrIndentNo(), 3);
				dao.setProcInValue(pIndex, "bsNo", bsReqNo, 4);
				dao.setProcInValue(pIndex, "raising_store",vo.getStrRaisingStoreId(), 5);
				dao.setProcOutValue(pIndex, "err", 1, 6);
				dao.executeProcedureByPosition(pIndex);
			}
			/*
			if(vo.getStrIsDemandActiveFlag().equals("1"))  // Here We Check Whether Demand is in Active Mode
			{					
			   issueDetailDAO.setStrIssueNO(strIssueNo);
			   issueDetailDAO.setStrStoreId(vo.getStrRaisingStoreId());
			   issueDetailDAO.setStrHospitalCode(vo.getStrHospitalCode());	
			   issueDetailDAO.setStrIndentNO(vo.getStrIndentNo());
			   if(strIndentStatusFlag > 0)
			   {
				   issueDetailDAO.setStrStatus("49");  // Un - Processed [If Issue Qty less than Balance Qty ]Means Some Qty Remaining 
			   }
			   else
			   {	   
			       issueDetailDAO.setStrStatus("50"); // Processed [If Issue Qty equals to Balance Qty ] Means All Qty is Issued
			   } 
			   issueDetailDAO.update3(dao);			   			  
			} */
				//if(!strIssueNo.equals("0"))
					dao.fire();
				vo.setStrIssueNo(strIssueNo);

		

		} catch (Exception e) {

			e.printStackTrace();
			vo.setStrMsgString("IssueDeskTransDAO.insertData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

			issueDetailDAO = null;
			issueItemDetailDAO = null;
		}

	}

	
	/**
	 * To get Issue Details i.e.(Store Name,Issue No.,Issue Date,Indent
	 * Type,Item Category,Raising Store) on 'issue' page
	 * 
	 * @param vo
	 */
	
	public static void getIssueDetail(IssueDeskTransVO vo) {

		String err = "";
		String proc_name1 = "{call Pkg_Mms_View.proc_issue_item_detail(?,?,?,?,?,?)}";

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;

		try {

			dao = new HisDAO("mms","IssueDeskTransDAO.getIssueDetail(IssueDeskTransVO vo)");

			procIndex1 = dao.setProcedure(proc_name1);
			
			

			// set value
			dao.setProcInValue(procIndex1, "modeval", "1",1);

			dao.setProcInValue(procIndex1, "issueNo", vo.getStrIssueNo(),2);

			dao.setProcInValue(procIndex1, "strId", vo.getStrStoreId(),3);

			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),4);
			
			dao.setProcOutValue(procIndex1, "err", 1,5); // 1 for string return
			// value

			dao.setProcOutValue(procIndex1, "resultset", 2,6); // 2 for object
			
			// execute procedure

			dao.executeProcedureByPosition(procIndex1);
			
			
			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			/*if (err == null)
				err = "";*/

			if (err.equals("")) {

				ws = dao.getWebRowSet(procIndex1, "resultset");
				System.out.println("ws.size()::"+ws.size());
                while(ws.next())
                {
                	vo.setStrIndentNo(ws.getString(16));
                	vo.setStrIndentDate(ws.getString(18));
                	vo.setStrRaisingStoreName(ws.getString(14));
                }
                ws.beforeFirst();
				vo.setItemDetailsWS(ws);
				
			} else {

				throw new Exception(err);
			}

		} catch (Exception e) {

			vo.setStrMsgString("IssueDeskTransDAO.getIssueDetail() --> "
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
	 * Gets the item dtls.
	 * 
	 * @param voObj the vo obj
	 * 
	 * @return the item dtls
	 */
	public static void getItemDtls(IssueDeskTransVO voObj) {

		HisDAO daoObj = null;
		String strFuncName = "";
		String strItemName = "";
		int nFuncIndex = 0;

		try {
			daoObj = new HisDAO("IssueDeskTrans", "SetSachetDetailsTransDAO");

			strFuncName = "{? = call MMS_MST.get_item_dtl(?::numeric,?::numeric,?::numeric)}"; //4
			nFuncIndex = daoObj.setFunction(strFuncName);
			daoObj.setFuncInValue(nFuncIndex, 2, "1");
			daoObj.setFuncInValue(nFuncIndex, 3, voObj.getStrHospitalCode());
			daoObj.setFuncInValue(nFuncIndex, 4, voObj.getStrItemId());
			daoObj.setFuncOutValue(nFuncIndex, 1);
			daoObj.executeFunction(nFuncIndex);
			strItemName = daoObj.getFuncString(nFuncIndex);

			voObj.setStrItemName(strItemName);

		} catch (Exception e) {
            e.printStackTrace();
			voObj.setStrMsgString("IssueDeskTransDAO.getItemDtls --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;

			}
		}

	}
	/**
	 * Gets the stock item dtls list.
	 * 
	 * @param vo the vo
	 * 
	 * @return the stock item dtls list
	 */
	public static void getStockItemDtlsList(IssueDeskTransVO vo) 
	{
        // This Procedure Return 44 Value(Ist Generic Item Name + Last : Rack No(44))
		String err = "";
		String proc_name1 = "{call pkg_mms_view.Proc_stock_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}"; //10+3=13

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		String strMode = "1" ;
		try 
		{

			dao = new HisDAO("IssueDeskTrans","global.IssueDeskTransDAO.getStockItemDtlsList(IssueDeskTransVO vo)");
			procIndex1 = dao.setProcedure(proc_name1);
			// set value
//			System.out.println("Mode::IN IssueDeskTrans DAO::"+strMode);
//			System.out.println("Store ID:::::::"+vo.getStrFromStoreId());
//			System.out.println("Item ID::IssueDeskTrans DAO::"+vo.getStrGenricItemId());
//			System.out.println("Item BrandId::IssueDeskTrans DAO::"+vo.getStrItemId());
//			System.out.println("STOCK STATUS::IssueDeskTrans DAO::"+vo.getStrStockStatus());
//			System.out.println("RESER_STOCK FLAG::IssueDeskTrans DAO::"+vo.getStrReservedFlag());			
			dao.setProcInValue(procIndex1, "modval", strMode,1);
			dao.setProcInValue(procIndex1, "store_id", vo.getStrFromStoreId(),2);
			dao.setProcInValue(procIndex1, "catCode", vo.getStrItemId().substring(0,2),3);
			dao.setProcInValue(procIndex1, "item_id", vo.getStrGenricItemId(),4);
			dao.setProcInValue(procIndex1, "itembrand_id", vo.getStrItemId(),5);
			dao.setProcInValue(procIndex1, "stock_status", "10",7);
			dao.setProcInValue(procIndex1, "reservedStockFlag", vo.getStrReservedFlag(),10);
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),8);
			dao.setProcOutValue(procIndex1, "err", 1,12); // 1 for string return
			// value
			dao.setProcOutValue(procIndex1, "resultset", 2,13); // 2 for object
			/* Start Adding Default value*/
			 dao.setProcInValue(procIndex1, "batch_no", "0",6);
			 dao.setProcInValue(procIndex1, "itemSlNo", "0",9);  
		     dao.setProcInValue(procIndex1, "blockedQtyFlag", "1",11); 
		     /* End Adding Default value*/
			
			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");

			
			if(vo.getStrModeVal().equals("3") && ws != null && ws.size() > 0)
			{
			
				if(ws.next())
				{
					
					vo.setStrRateUnit(ws.getString(41));
					vo.setStrRateInBaseValue(ws.getString(42));
					
				}
				
				ws.beforeFirst();
						
		     }
		
						
			vo.setWsStockDetails(ws);

		} 
		catch (Exception e) 
		{

			e.printStackTrace();
			
			vo.setStrMsgString("IssueDeskTransDAO.getStockItemDtlsList() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

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
	
	/**
	 * Gets the unit list.
	 * 
	 * @param vo the vo
	 * 
	 * @return the unit list
	 */
	public static void getUnitList(IssueDeskTransVO vo) {

		String err = "";

		String strModeVal = "1";
		String proc_name1 = "{call pkg_MMS_view.Proc_Gblt_Unit_Mst(?,?,?,?,?,?)}";  //5+1=6

		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;

		try {

			dao = new HisDAO("IssueDeskTrans", "global.IssueDeskTransDAO.getUnitList(IssueDeskTransVO vo)");
			procIndex1 = dao.setProcedure(proc_name1);		
			if(vo.getStrUnitId().length() == 4)
				dao.setProcInValue(procIndex1, "modeval", "5",4);
			else
				dao.setProcInValue(procIndex1, "modeval", "1",4);
			//dao.setProcInValue(procIndex1, "modeval","5",4);// strModeVal,4);
			dao.setProcInValue(procIndex1, "unit_id", vo.getStrUnitId(),2);
			dao.setProcInValue(procIndex1, "module_id", "",3);
		
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(),1);

			dao.setProcOutValue(procIndex1, "err", 1,5); // 1 for string return
			// value

			dao.setProcOutValue(procIndex1, "resultset", 2,6); // 2 for object

			/* Start Adding Default value*/

			/* End Adding Default value*/
			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "resultset");

			vo.setStrUnitListWs(ws);

		} catch (Exception e) {

			vo.setStrMsgString("IssueDeskTransDAO.getUnitList() --> " + e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	/**
	 * This function is used to GET THE BATCH WISE ITEM DETAILS FOR POPUP.
	 * 
	 * @param vo
	 *            the vo
	 * @return the single batch item dtl
	 */
	public static void getSingleBatchItemDtl(IssueDeskTransVO vo) {

		HisDAO dao = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strErr = "";

		try {
			dao = new HisDAO("mms", "IssueDeskTransDAO");
			
			strProcName = "{call Pkg_Mms_View.proc_itemStock_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
			nProcIndex = dao.setProcedure(strProcName);
			System.out.println("vo.getStrItemCategoryNo()"+ vo.getStrSingleItemDtl().split("\\^")[4]);
			dao.setProcInValue(nProcIndex, "modeval", "7");
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
			dao.setProcInValue(nProcIndex, "item_id", vo.getStrSingleItemDtl()
					.split("\\^")[0]);
			dao.setProcInValue(nProcIndex, "itembrand_id", vo
					.getStrSingleItemDtl().split("\\^")[1]);
			dao.setProcInValue(nProcIndex, "store_id", vo.getStrSingleItemDtl()
					.split("\\^")[3]);
			dao.setProcInValue(nProcIndex, "batch_no", vo.getStrSingleItemDtl()
					.split("\\^")[2]);
			dao.setProcInValue(nProcIndex, "item_sl_no", "0");
			dao.setProcInValue(nProcIndex, "sl_no", vo.getStrSingleItemDtl().split("\\^")[4]); // Passing As a ItemCat No.
			dao.setProcInValue(nProcIndex, "reserved_flag", "0");
			dao.setProcOutValue(nProcIndex, "err", 1);
			dao.setProcOutValue(nProcIndex, "resultset", 2);
			dao.executeProcedure(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				vo.setStrSingleBatchDtlWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IssueDeskTransDAO.getPopUpInfoProc() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}
}
