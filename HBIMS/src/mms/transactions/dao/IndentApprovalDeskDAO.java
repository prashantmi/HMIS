package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import mms.dao.DmlApprovalDtlDAO;
import mms.dao.DmlApprovalItemDtlDAO;
import mms.transactions.vo.IndentApprovalDeskVO;
/**
 * Developer : Amit Kumar
 * Version : 1.0
 * Date : 1/June/2009
 * Modif Date : / /2009 
*/

public class IndentApprovalDeskDAO 
{
	/**
	 * for getting value of item details on view page
	 * 
	 * @param vo
	 */

	public static void getItemDetails(IndentApprovalDeskVO vo) 
	{
		WebRowSet  wb = null;
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String  strErr = "";

    	try 
		{
    		String strProcName = "{call PKG_MMS_VIEW.PROC_GET_INDENTITEM_DTL(?,?,?,?,?,?,?)}";

			daoObj = new HisDAO("MMS Transactions","IndentApprovalDeskDAO");
			
			nProcIndex = daoObj.setProcedure(strProcName);
     		daoObj.setProcInValue(nProcIndex, "modval", "1",1);
	     	daoObj.setProcInValue(nProcIndex, "storeid",vo.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "reqno",vo.getStrReqNo(),4);
			daoObj.setProcInValue(nProcIndex, "reqtypeid",vo.getStrReqTypeId(),5);
			daoObj.setProcOutValue(nProcIndex,"err", 1,6);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{

				wb = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setStrItemDetailsWs(wb);
				
			} else {
				throw new Exception(strErr);
			}
    		
		
		
		} 
    	catch (Exception e) 
    	{
			vo.setStrMsgString("-->IndentApprovalDeskDAO.getItemDetails()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

		}
	
	/**
	 * for getting value of item details on view page
	 * 
	 * @param vo
	 */

	public static void getItemDetailsForReceiveFromThirdParty(IndentApprovalDeskVO vo) 
	{
        String strProcName = "{call pkg_mms_view.proc_receivedItem_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Receive From Third Party Item Details","IndentApprovalTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
				  		
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "storeid", vo.getStrStoreId(),3);
			daoObj.setProcInValue(nProcIndex, "item_cat_code", vo.getStrItemCatgNo(),4);
			daoObj.setProcInValue(nProcIndex, "fin_st_date", vo.getStrFinancialStartYear(),5);
			daoObj.setProcInValue(nProcIndex, "fin_end_date", vo.getStrFinancialEndYear(),6);
			daoObj.setProcOutValue(nProcIndex, "err",1,7); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,8);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
						
			if (strErr.equals("")) 
			{
				vo.setStrItemDetailsWs(ws);
			}
			else 
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("IndentApprovalDeskDAO.getItemDetailsForReceiveFromThirdParty() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	
	/**
	 * This Function is used to get Store Name by Passing variable 
	 * @param vo
	 */
	public static void callingFunctionStoreName(IndentApprovalDeskVO vo) 
	{
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO dao = null;
		try {
			      dao = new HisDAO("MMSModule", "IndentApprovalDeskDAO");
			funcIndex = dao.setFunction("{? = call MMS_MST.get_storeType_dtl(?,?,?)}");
			// Set Value
			dao.setFuncInValue(funcIndex, 2, "1");
			dao.setFuncInValue(funcIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 4, vo.getStrStoreId());
			dao.setFuncOutValue(funcIndex, 1);
			// Execute Function
			dao.executeFunction(funcIndex);
			retVal = dao.getFuncString(funcIndex);
			
			if (retVal != null) 
			{
				vo.setStrIndentName(retVal);
			} 
			else 
			{
				retVal = "-----";
				vo.setStrIndentName(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IndentApprovalDeskDAO.callingFunctionIndentName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	


	/**
	 * Used to get Unit Combo
	 * for HLP
	 * @param vo
	 * @param Unit Id
	 */

	public static void getUnitCombo(IndentApprovalDeskVO vo,String unit_Id ) 
	{
		WebRowSet wb = null;
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
	   	try 
		{
    		String strProcName = "{call PKG_MMS_VIEW.proc_gblt_unit_mst(?,?,?,?,?,?)}";
    		daoObj = new HisDAO("MMS Transactions","IndentViewTransDAO");
    				
			nProcIndex = daoObj.setProcedure(strProcName);
			if(unit_Id.length() == 4)
				daoObj.setProcInValue(nProcIndex, "modeval", "5",4);
			else
				daoObj.setProcInValue(nProcIndex, "modeval", "1",4);
	     	daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),1);
	     	//System.out.println("Unit ID-->>"+unit_Id);
	     	
	     	daoObj.setProcInValue(nProcIndex, "unit_id",unit_Id,2);	
	     	daoObj.setProcInValue(nProcIndex, "module_id","",3);// default value
			daoObj.setProcOutValue(nProcIndex,"err", 1,5);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				wb = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setStrUnitComboWs(wb);
				
			} else {
				throw new Exception(strErr);
			}
		
		} 
    	catch (Exception e) 
    	{
			vo.setStrMsgString("--> IndentApprovalDeskDAO.getUnitCombo()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

		}
	
	
	
	/**
	 * This function is used to fetch details for committee type combo Receving Store Condemnation.
	 * @param DossierValidationDeskVO
	 */
	public static void setCommitteeTypeDtl(IndentApprovalDeskVO vo)
	{
		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			dao = new HisDAO("mms", "IndentApprovalDeskDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_committe_type_dtl(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "modeval","1",1);
			dao.setProcInValue(nprocIndex, "catCode", vo.getStrItemCatgNo(),2);
			//System.out.println("catCode-->"+vo.getStrItemCatgNo());
			dao.setProcInValue(nprocIndex, "reqType", vo.getStrReqTypeId(),3);
			//System.out.println("reqType-->"+vo.getStrReqTypeId());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),4);
			dao.setProcOutValue(nprocIndex, "err", 1,5);
			dao.setProcOutValue(nprocIndex, "resultset", 2,6); 
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			wb = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) 
			{
				vo.setStrCommitteTypeWS(wb);
			}
			else 
			{
				throw new Exception(strerr);
			}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("IndentApprovalDeskDAO.setCommitteeTypeDtl() --> "
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
	 * This Function is used to get Store Name by Passing variable 
	 * @param vo
	 */
	public static void callingFunctionIndentName(IndentApprovalDeskVO vo) 
	{
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO dao = null;
		try {
			      dao = new HisDAO("MMSModule", "IndentApprovalDeskDAO");
			funcIndex = dao.setFunction("{? = call MMS_MST.get_indentType_Name(?::numeric,?::numeric,?::numeric)}");
			// Set Value
			dao.setFuncInValue(funcIndex, 2, "1");
			dao.setFuncInValue(funcIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 4, vo.getStrReqTypeId());
			dao.setFuncOutValue(funcIndex, 1);
			// Execute Function
			dao.executeFunction(funcIndex);
			retVal = dao.getFuncString(funcIndex);
			
			if (retVal != null) 
			{
				vo.setStrIndentName(retVal);
			} 
			else 
			{
				retVal = "-----";
				vo.setStrIndentName(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IndentApprovalDeskDAO.callingFunctionIndentName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * This Function is used to get Store Name by Passing variable 
	 * @param vo
	 */
	public static void getGrpIDFunction(IndentApprovalDeskVO vo) 
	{
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO dao = null;
		try {
			      dao = new HisDAO("MMSModule", "IndentApprovalDeskDAO");
			      //get_group_id (hosp_code NUMBER,strCatCode NUMBER)
			funcIndex = dao.setFunction("{? = call MMS_MST.get_group_id (?::numeric,?::numeric)}");
			// Set Value
			
			dao.setFuncInValue(funcIndex, 2, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 3, vo.getStrItemCatgNo());
			dao.setFuncOutValue(funcIndex, 3);
			// Execute Function
			dao.executeFuncForNumeric(funcIndex);
			retVal = dao.getFuncNumeric(funcIndex).toString();
			//System.out.println("O/p of Function Grp Id--->>"+retVal);
			if (retVal != null) 
			{
				vo.setStrGrpId(retVal);
			} 
			else 
			{
				retVal = "-----";
				vo.setStrGrpId(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IndentApprovalDeskDAO.callingFunctionIndentName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * This Function is used to get Store Name by Passing 2 variable 
	 * @param vo
	 */
	public static void callingFunctionGetUserName(IndentApprovalDeskVO vo) 
	{
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO    dao = null;
		try {
			      dao = new HisDAO("MMSModule", "IndentApprovalDeskDAO");
			funcIndex = dao.setFunction("{? = call MMS_MST.get_user_name(?,?,?)}");
			// Set Value
			dao.setFuncInValue(funcIndex, 2, "1");
			dao.setFuncInValue(funcIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 4, vo.getStrSeatId());
			dao.setFuncOutValue(funcIndex, 1);
			// Execute Function
			dao.executeFunction(funcIndex);
			retVal = dao.getFuncString(funcIndex);
			
			if (retVal != null) 
			{
				vo.setStrApprovedBy(retVal);
			} 
			else 
			{
				retVal = "-----";
				vo.setStrApprovedBy(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IndentApprovalDeskDAO.callingFunctionIndentName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	/**
	 * Method used to get Indent Details for Approval Desk Approval
	 * 
	 * @param vo
	 */
	
	

	public static void getIndentDetails(IndentApprovalDeskVO vo) 
	{
		WebRowSet   wb = null;
		HisDAO  daoObj = null;
		String strProcName = "";
		int nProcIndex = 0;
		String  strErr = "";
	    daoObj = new HisDAO("MMS Transactions","IndentApprovalDeskDAO");
    	try 
		{
    	    if(vo.getStrReqTypeId().equals("64")||vo.getStrReqTypeId().equals("65")||vo.getStrReqTypeId().equals("69")||vo.getStrReqTypeId().equals("47"))
			{				
			    strProcName = "{call PKG_MMS_VIEW.Get_Indent_Details(?,?,?,?,?,?)}";
     			nProcIndex = daoObj.setProcedure(strProcName);
			    daoObj.setProcInValue(nProcIndex, "storeId",vo.getStrStoreId(),1);
			    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			    daoObj.setProcInValue(nProcIndex, "reqNo",vo.getStrReqNo(),3);
			    daoObj.setProcInValue(nProcIndex, "reqTypeId",vo.getStrReqTypeId(),4);
			}
			else
			{
				
				strProcName = "{call PKG_MMS_VIEW.Get_Indent_Details(?,?,?,?,?,?)}";
                nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "storeId",vo.getStrStoreId(),1);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "reqNo",vo.getStrReqNo(),3);
				daoObj.setProcInValue(nProcIndex, "reqTypeId","",4);// default value
				
				
			}	
			daoObj.setProcOutValue(nProcIndex,"err",1,5);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
    			wb = daoObj.getWebRowSet(nProcIndex, "resultset");
    			vo.setStrIndentDetailsWs(wb);
				
			} else {
				throw new Exception(strErr);
			}
    		
		
		
		} 
    	catch (Exception e) 
    	{
			vo.setStrMsgString("--> IndentApprovalDeskDAO.getIndentDetails()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

		}
	/**
	 * Method used to get Item Detail for View Page of
	 * Approval Desk 
	 * @param vo
	 */

	public static void getItemDetailsView(IndentApprovalDeskVO vo) 
	{
		WebRowSet wb = null;
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";

    	try 
		{
    		//System.out.println("vo.getStrReqTypeId()"+vo.getStrReqTypeId());
    		String strProcName = "{call PKG_MMS_VIEW.PROC_GET_INDENTITEM_DTL(?,?,?,?,?,?,?)}";

			daoObj = new HisDAO("MMS Transactions","IndentApprovalDeskDAO");
			
			nProcIndex = daoObj.setProcedure(strProcName);
     		daoObj.setProcInValue(nProcIndex, "modval", "1",1);
	     	daoObj.setProcInValue(nProcIndex, "storeId",vo.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "reqNo",vo.getStrReqNo(),4);
			daoObj.setProcInValue(nProcIndex, "reqTypeId",vo.getStrReqTypeId(),5);
			daoObj.setProcOutValue(nProcIndex,"err", 1,6);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,7);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				wb = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				vo.setStrItemDetailsWs(wb);
				
			} else {
				throw new Exception(strErr);
			}
    		
		
		
		} 
    	catch (Exception e) 
    	{
			vo.setStrMsgString("--> IndentApprovalDeskDAO.getItemDetails()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

		}
	
	/**
	 *  To get Blocked Item Detail from table
	 *  HSTT_BLOCKEDITEM_DTL
	 *  @param vo
	 */
	public static void getBlockItemDtl(IndentApprovalDeskVO vo) 
	{
		HisDAO    dao = null;
		WebRowSet ws  = null;
    	String strProcName = "";
		int nProcIndex = 0;
		String  strErr = "";

		try 
		{
			dao = new HisDAO("mms", "IndentApprovalDeskDAO");
			strProcName = "{call PKG_MMS_VIEW.Get_BlockedItem_Details(?,?,?,?,?,?,?,?,?,?)}";
			nProcIndex = dao.setProcedure(strProcName);
    		dao.setProcInValue(nProcIndex, "itemId", vo.getStrItemId(),1); 
    		if(vo.getStrRasingRecevingFlg().equals("0"))
    		{	
    		  
			  dao.setProcInValue(nProcIndex, "storeId", vo.getStrStoreId(),2);
    		}
    		else
    		{
    		  dao.setProcInValue(nProcIndex, "storeId", vo.getStrToStoreId(),2);	
    		}	
			dao.setProcInValue(nProcIndex, "itemBrandId", vo.getStrItemBrandId(),3); 
			dao.setProcInValue(nProcIndex, "reqNo", vo.getStrReqNo(),4);
			dao.setProcInValue(nProcIndex, "batchNo", vo.getStrBatchNoBlocked(),5); 
			dao.setProcInValue(nProcIndex, "itemSlNo", vo.getStrItemSlNoBlocked(),6); 
			dao.setProcInValue(nProcIndex, "stockStatusCode", vo.getStrStockStatusCodeBlocked(),7);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),8);
			dao.setProcOutValue(nProcIndex,"err", 1,9);
			dao.setProcOutValue(nProcIndex,"resultset", 2,10);

			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");


			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) 
			{
     			vo.setStrBlockedItemDetailsWs(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("IndentApprovalDeskDAO.getStockDtls() --> "+ e.getMessage());
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
	 * This Function is used to get Store Name by Passing 2 variable 
	 * @param vo
	 */
	public static void FunctionToGetResvQty(IndentApprovalDeskVO vo) 
	{
		// Declearing Variable
		String retVal      = null;
		int nFuncIndex     = 0;
		String strFuncName = "";
		HisDAO    dao = null;
		try 
		{
			dao = new HisDAO("MMSModule", "IndentApprovalDeskDAO");
			strFuncName = "{? = call MMS_MST.get_stock_dtl(?,?,?,?,?,?,?,?,?,?)}";   
			nFuncIndex = dao.setFunction(strFuncName);
			dao.setFuncInValue(nFuncIndex, 2, "9");
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospCode());
			dao.setFuncInValue(nFuncIndex, 4, vo.getStrItemId());
			dao.setFuncInValue(nFuncIndex, 5, vo.getStrItemBrandId());
			dao.setFuncInValue(nFuncIndex, 6, "0");
			dao.setFuncInValue(nFuncIndex, 7, "0");
			dao.setFuncInValue(nFuncIndex, 8, vo.getStrToStoreId());
			dao.setFuncInValue(nFuncIndex, 9, "1");
			dao.setFuncInValue(nFuncIndex,10, "2");
			dao.setFuncInValue(nFuncIndex,11, "1"); // default value
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			
			retVal = dao.getFuncString(nFuncIndex);
			
			if (retVal != null) 
			{
				vo.setStrReservUnReservQty(retVal);
			} 
			else 
			{
				retVal = "-----";
				vo.setStrReservUnReservQty(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IndentApprovalDeskDAO.FunctionToGetResvQty() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * This Function is used to get Indnet Name by Passing 2 variable 
	 * a) Hospital Code 
	 * b) Store Id
	 */
	public static void callingFunctionIndentNameView(IndentApprovalDeskVO vo) 
	{
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO    dao = null;
		try 
		{
			dao = new HisDAO("MMSModule", "IndentApprovalDeskDAO");
			funcIndex = dao.setFunction("{? = call MMS_MST.get_indentType_Name(?::numeric,?::numeric,?::numeric)}");
			// Set Value
			dao.setFuncInValue(funcIndex, 2, "1");
			dao.setFuncInValue(funcIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 4, vo.getStrReqTypeId());
			dao.setFuncOutValue(funcIndex, 1);
			// Execute Function
			dao.executeFunction(funcIndex);
			retVal = dao.getFuncString(funcIndex);
			if (retVal != null) 
			{
				vo.setStrIndentName(retVal);
			} 
			else 
			{
				retVal = "-----";
				vo.setStrIndentName(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IndentApprovalDeskDAO.callingFunctionIndentName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	

	/**
	 * This Function is used to get Committe Type Name by Passing 2 variable(Only In case of Condemnation) 
	 * a) Hospital Code 
	 * b) Committe Type ID
	 */
	public static void callingFunctionCommitteNameView(IndentApprovalDeskVO vo) 
	{
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		HisDAO    dao = null;
		try 
		{
			dao = new HisDAO("MMSModule", "IndentApprovalDeskDAO");
			funcIndex = dao.setFunction("{? = call MMS_MST.get_commType_dtl(?,?,?)}");
			// Set Value
			dao.setFuncInValue(funcIndex, 2, "1");
			dao.setFuncInValue(funcIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 4, vo.getStrCommitteTypeCode());
			dao.setFuncOutValue(funcIndex,1);
			// Execute Function
			dao.executeFunction(funcIndex);
			retVal = dao.getFuncString(funcIndex);
			if (retVal != null) 
			{
				vo.setStrCommitteTypeName(retVal);
			} 
			else 
			{
				retVal = "-----";
				vo.setStrCommitteTypeName(retVal);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IndentApprovalDeskDAO.callingFunctionCommitteNameView() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	
	/**
	 * To get Stock Details(qty and unit name)
	 * @param vo
	 * @param ItemId
	 * @param ItemBrandId  
	 */
	public static void getStockDtls(IndentApprovalDeskVO vo,String ItemId,String ItemBrandId) 
	{

		HisDAO dao = null;
		String strFuncName = "";
		int nFuncIndex = 0;
		String strStockDtl = "";
		String StockDtl = "";
	  try 
		{
			dao = new HisDAO("mms", "IndentApprovalDeskDAO");
    		strFuncName = "{? = call MMS_MST.get_stock_dtl(?,?,?,?,?,?,?,?,?,?)}";
    	
			nFuncIndex = dao.setFunction(strFuncName);

			dao.setFuncInValue(nFuncIndex, 2, "1");
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex, 4, ItemId);
			dao.setFuncInValue(nFuncIndex, 5, ItemBrandId);
			dao.setFuncInValue(nFuncIndex, 6, "0");
			dao.setFuncInValue(nFuncIndex, 7, "0");
			dao.setFuncInValue(nFuncIndex, 8, vo.getStrToStoreId());
			
			/* Setting Default Value Start*/
			dao.setFuncInValue(nFuncIndex, 9, "0");
			dao.setFuncInValue(nFuncIndex, 10, "1");
			dao.setFuncInValue(nFuncIndex, 11, "1");
			/* Setting Default Value End */
			
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			
			StockDtl = dao.getFuncString(nFuncIndex);
			
			if(StockDtl.equals("0")||StockDtl.equals("null"))
			{
				strStockDtl = "-----";
			}
			else
			{
				strStockDtl = StockDtl;
			}
			
			vo.setStrStockDtl(strStockDtl);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IndentApprovalDeskDAO.getStockDtls() --> "
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
	 * To get Stock Details(qty and unit name)
	 * @param vo
	 * @param ItemId
	 * @param ItemBrandId  
	 */
	public static void getStockDtlsTwo(IndentApprovalDeskVO vo,String ItemId,String ItemBrandId) 
	{

		HisDAO dao = null;
		String strFuncName = "";
		int nFuncIndex = 0;
		String strStockDtl = "";
		String StockDtl = "";
	  try 
		{
			dao = new HisDAO("mms", "IndentApprovalDeskDAO");
    		strFuncName = "{? = call MMS_MST.get_stock_dtl(?,?,?,?,?,?,?,?,?,?)}";
    	
			nFuncIndex = dao.setFunction(strFuncName);

			dao.setFuncInValue(nFuncIndex, 2, "1");
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex, 4, ItemId);
			dao.setFuncInValue(nFuncIndex, 5, ItemBrandId);
			dao.setFuncInValue(nFuncIndex, 6, "0");
			dao.setFuncInValue(nFuncIndex, 7, "0");
			dao.setFuncInValue(nFuncIndex, 8, vo.getStrStoreId());
			
			/* Setting Default Value Start*/
			dao.setFuncInValue(nFuncIndex, 9, "0");
			dao.setFuncInValue(nFuncIndex, 10, "1");
			dao.setFuncInValue(nFuncIndex, 11, "1");
			/* Setting Default Value End */
			
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			
			StockDtl = dao.getFuncString(nFuncIndex);
			
			if(StockDtl.equals("0")||StockDtl.equals("null"))
			{
				strStockDtl = "-----";
			}
			else
			{
				strStockDtl = StockDtl;
			}
			
			vo.setStrStockDtlTwo(strStockDtl);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IndentApprovalDeskDAO.getStockDtls() --> "
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
	 * Method is Used to get Indent Details for Approval Desk 
	 * View
	 * @param vo
	 */
	public static void getIndentDetailsView(IndentApprovalDeskVO vo) 
	{
		WebRowSet wb = null;
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";

    	try 
		{
    		daoObj = new HisDAO("MMS Transactions","IndentApprovalDeskDAO");
    		if(vo.getStrReqTypeId().equals("65")||vo.getStrReqTypeId().equals("69")||vo.getStrReqTypeId().equals("47"))
			{				
				String strProcName = "{call PKG_MMS_VIEW.Get_Indent_Details(?,?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);
			    daoObj.setProcInValue(nProcIndex, "storeId",vo.getStrStoreId(),1);
			    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			    daoObj.setProcInValue(nProcIndex, "reqNo",vo.getStrReqNo(),3);
			    daoObj.setProcInValue(nProcIndex, "reqTypeId",vo.getStrReqTypeId(),4);
			}
			else
			{
				String strProcName = "{call PKG_MMS_VIEW.Get_Indent_Details(?,?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "storeId",vo.getStrStoreId(),1);
				//System.out.println("Store Id->>"+vo.getStrStoreId());
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "reqNo",vo.getStrReqNo(),3);
				daoObj.setProcInValue(nProcIndex, "reqTypeId","",4); // Default value
				//System.out.println("Req no-->>"+vo.getStrReqNo());
				
			}	
			daoObj.setProcOutValue(nProcIndex,"err",1,5);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
    			wb = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setStrIndentDetailsWs(wb);
				
			 }
			 else 
			 {
				throw new Exception(strErr);
			 }
    	} 
    	catch (Exception e) 
    	{
			vo.setStrMsgString("--> IndentApprovalDeskDAO.getIndentDetails()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null)
				daoObj.free();
				daoObj = null;
			}

		}

	/**
	 *  Insert into Table  DML_APPROVAL_DTL
	 *  @param vo
	 *  
	 * */

	public synchronized static void INSERT(IndentApprovalDeskVO vo) 
	{
		HisDAO       dao  = null;
		int    procIndex1 = 0;
		String itemDetail = "";
		String      appNo = "";
		String     status = "";
		String  lstStatus = "";
		DmlApprovalDtlDAO globalDao = null;
		
    	try 
		{
    		// Createing Object for Table Specific DAO
    		globalDao = new DmlApprovalDtlDAO();	
    		dao = new HisDAO("MMS","transactions.Indent ApprovalDeskDAO.INSERT()");
       		
//    		   System.out.println("Req No-->>"+vo.getStrReqNo());
//    		   System.out.println("Req Typ Id-->>"+vo.getStrReqTypeId());
//    		   System.out.println("Store Id-->>"+vo.getStrStoreId());
//    		   System.out.println("Level tyep-->>"+vo.getStrLevelType());
//    		   System.out.println("Item cag No-->>"+vo.getStrItemCatgNo());
//    		   System.out.println("to Store Id-->>"+vo.getStrToStoreId());
//    		   System.out.println("Remarks-->>"+vo.getStrRemarks());
//    		   System.out.println("Approval Type-->>"+vo.getStrApprovalType());
//    		   System.out.println("Retunr Type-->>>"+vo.getStrReturnType());
    		
    		    
		        globalDao.setStrId(vo.getStrStoreId());
				globalDao.setHosp_code(vo.getStrHospitalCode());
    			globalDao.setReqNo(vo.getStrReqNo());
	    		globalDao.setLevelType(vo.getStrLevelType());
				globalDao.setApprovalType(vo.getStrApprovalType());
				globalDao.setIpAddress(vo.getStrIpAddr());
		    	globalDao.setFinStartDate(vo.getStrFinancialStartYear());
				globalDao.setFinEndDate(vo.getStrFinancialEndYear());
			    globalDao.setRemarks(vo.getStrRemarks());
				globalDao.setSeatId(vo.getStrSeatId());
				globalDao.setCommitteTypeID(vo.getStrCommitteTypeCode());
				
				globalDao.setDeliveryDate(vo.getStrDeliveryDate());
				globalDao.setReturnType(vo.getStrReturnType());
				globalDao.setCommRemarks(vo.getStrCommitteRemarks());
			    procIndex1 = globalDao.insert(dao);  //Procedure Name DML_APPROVAL_DTL
									
	         
			    dao.fire();     // Here we Execute the Procedure
			    
			      itemDetail    = dao.getString(procIndex1, "itemDetail");
			      appNo         = dao.getString(procIndex1, "appNo");
			      status        = dao.getString(procIndex1, "status");
			      lstStatus     = dao.getString(procIndex1, "lstStatus");
		     
//				   System.out.println("Item Dtl-->>"+itemDetail);
//				   System.out.println("Approval No-->>"+appNo);
//				   System.out.println("Status-->>>"+status);
//	       	       System.out.println("Last Status-->>"+lstStatus);
				  		      
			      if(itemDetail.equals("1"))
			      {	  
				    INSERTINTABLE(vo,itemDetail,appNo,status,lstStatus);   // DML_APPROVALITEM_DTL
			      }
			      if (vo.getStrMsgType().equals("1")) 
				  {
						
						throw new Exception(vo.getStrMsgString());
				  }
		      		    
		    	  
	     	}
			catch (Exception e) 
    	    {
    		e.printStackTrace();
			vo.setStrMsgString("-->IndentApprovalDeskDAO.INSERT()-->"
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
	 * SSTT_APPROVALREQ_DTL,SSTT_INDENT_DTL,HSTT_AGENDA_DTL
	 * HSTT_APPROVAL_DTL
	 * @param vo
	 * @param itemDetail
	 * @param appNo
	 * @param status
	 * @param lstStatus  
	 */
	
	public static void INSERTINTABLE(IndentApprovalDeskVO vo,String itemDetail,String appNo,String status,String lstStatus) 
	{
		HisDAO   dao         = null;
		HisUtil  util        = null;
		String[] temp        = null;
		String[] reqQtyUnit  = null;
		String[] sancQtyUnit = null;
		String   strReqQty   = "";
		String   strReqUnit  = "";
		String   strSancQty  = "";
		String   strSancQtyUnit = "";
		String   strPrevSancQty = "";
		String   strPrevSancQtyUnitId  = "";
		String   strCtDate             = "";
		DmlApprovalItemDtlDAO tableDao = null;
		
		try 
		{
		    /*
			 vo.setStrSeatId(seatid);	
			vo.setStrHospitalCode(hosCode);
			vo.setStrIpAddr(ipAddr);
			vo.setStrReqNo(strReqNo);
			vo.setStrReqTypeId(strReqTypeId);
			vo.setStrStoreId(strStoreId);
			vo.setStrLevelType(strLevelType);
			vo.setStrItemCatgNo(strItemCatg);
			vo.setStrToStoreId(strToStoreId);
			vo.setStrApproved(formBean.getStrApproved());
			vo.setStrRejected(formBean.getStrRejected());
			vo.setStrFinancialEndYear(strFinancialEndYear);
			vo.setStrFinancialStartYear(strFinancialStartYear);
			vo.setStrInsertHiddenValue(formBean.getStrInsertHiddenValue());	
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrInsSancQty(formBean.getStrInsSancQty());
			vo.setStrInsUnitCombo(formBean.getStrInsUnitCombo());
			*/
			            util = new HisUtil("MMS","transactions.IndentApprovalDeskDAO.INSERTINTABLE()");
			       strCtDate = util.getASDate("dd-MMM-yyyy");
			        tableDao = new DmlApprovalItemDtlDAO();
    		             dao = new HisDAO("MMS","transactions.IndentApprovalDeskDAO.INSERTINTABLE()");
    		 		    		
 			int length = vo.getStrInsertHiddenValue().length;
			
 			for(int i = 0;i<length;i++)
			{
 				temp = vo.getStrInsertHiddenValue()[i].split("\\@");
 				
 				    strReqQty            = temp[2];
 				  	strReqUnit           = temp[3];
					strPrevSancQty       = temp[4];
					strPrevSancQtyUnitId = temp[5];
					
					reqQtyUnit     = strReqUnit.split("\\^");
	 		        strSancQty     = vo.getStrInsSancQty()[i];
				 	sancQtyUnit    = vo.getStrInsUnitCombo()[i].split("\\^"); 
				  	strSancQtyUnit = sancQtyUnit[0];

					System.out.println("------------------------------------");
					System.out.println("strReqQty--"+i+"->>>"+strReqQty);		
				System.out.println("strReqUnit--"+i+"->>>"+strReqUnit);
					System.out.println("------------------------------------");
					System.out.println("strPrevSancQty--"+i+"->>>"+strPrevSancQty);		
					System.out.println("strPrevSancQtyUnitId--"+i+"->>>"+strPrevSancQtyUnitId);									
				 	System.out.println("------------------------------------");
					System.out.println("strSancQty--"+i+"->>>"+strSancQty);		
					System.out.println("strSancQtyUnit--"+i+"->>>"+strSancQtyUnit);
					if(vo.getStrReqTypeId().equals("16")||vo.getStrReqTypeId().equals("19")||vo.getStrReqTypeId().equals("47")||vo.getStrReqTypeId().equals("18")||vo.getStrReqTypeId().equals("65"))
					{
				System.out.println("------------------------------------");
					System.out.println("Batch No--"+i+"->>>"+vo.getStrBatchNo()[i]);		
			System.out.println("ItemSlNo()--"+i+"->>>"+vo.getStrItemSlNo()[i]);
					System.out.println("StockStatusCode()--"+i+"->>>"+vo.getStrStockStatusCode()[i]);
					}
					System.out.println("------------------------------------");
					System.out.println("Item Id()---"+i+"-->>>"+temp[0]);
					System.out.println("Item Brand Id()---"+i+"-->>>"+temp[1]);
					System.out.println("Req No()--"+i+"-->>>"+vo.getStrReqNo());
				System.out.println("Store Id()--"+i+"-->>>"+vo.getStrStoreId());
				System.out.println("------------------------------------");
				  	
				  	if(!vo.getStrReqTypeId().equals("13"))
				  	{	
				  		tableDao.setStrRemarks("");
				  	}
				  	else
				  	{	
				  		if(vo.getStrItemCatgNo().equals("21"))
				  			tableDao.setStrRemarks(vo.getStrItemRemarks()[i]);
				  		else
				  			tableDao.setStrRemarks("");
				  	}
				  	tableDao.setLstStatus(status);
					tableDao.setPrevSancQty(strPrevSancQty);
					tableDao.setPrevSancQtyUnitId(strPrevSancQtyUnitId);
				    tableDao.setStrId(vo.getStrStoreId());
				    tableDao.setToStrId(vo.getStrToStoreId());
				    tableDao.setReqNo(vo.getStrReqNo()); 
				 	tableDao.setReqTypeId(vo.getStrReqTypeId());
				 	tableDao.setCatNo(vo.getStrItemCatgNo());
				 	
				 	tableDao.setItemId(temp[0]);
				 	tableDao.setItemBrandId(temp[1]);
				 	
				 	if(vo.getStrReqTypeId().equals("16")||vo.getStrReqTypeId().equals("19")||vo.getStrReqTypeId().equals("47")||vo.getStrReqTypeId().equals("18")||vo.getStrReqTypeId().equals("65"))
					{
				 		tableDao.setBatchNo(vo.getStrBatchNo()[i]);
					 	tableDao.setItemSlNo(vo.getStrItemSlNo()[i]);
					 	tableDao.setStkStatus(vo.getStrStockStatusCode()[i]);
					}
				 	else
				 	{
				 		tableDao.setBatchNo("0");
					 	tableDao.setItemSlNo("0");
					 	tableDao.setStkStatus("0");
				 		
				 	}	
				 	tableDao.setExpiryDate(strCtDate);
				 	tableDao.setReqQty(strReqQty);
				 	tableDao.setReqQtyUnitId(reqQtyUnit[0]);
				 	tableDao.setSancQty(strSancQty);   
				 	tableDao.setSancQtyUnitId(strSancQtyUnit);
				 	if(vo.getStrReqTypeId().equals("17"))
				 	{	
				 	  tableDao.setReservedFlag("0");
				 	  //System.out.println("Issue from Reserv Stock-->>"+vo.getStrIssueFrmReservStock()[i]);
				 	}
				 	else
				 	{
				 	  tableDao.setReservedFlag("0");
				 	}	
				  	tableDao.setStatus(status);
				  	tableDao.setLstStatus(lstStatus);
				 	tableDao.setAppNo(appNo);
				 	tableDao.setHosp_code(vo.getStrHospitalCode());
				 	tableDao.setSeatId(vo.getStrSeatId());
	                tableDao.insert(dao);   // DML_APPROVALITEM_DTL Procedure Name
	               	                
	          }
		      synchronized(dao)   
			  {
	        	dao.fire();     // Here we Execute in Batch
			  }
		      
		} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		vo.setStrMsgType("1");
			vo.setStrMsgString("-->IndentApprovalDeskDAO.INSERTINTABLE()-->"+ e.getMessage());
			
			
		} 
    	finally 
    	{
			if (dao != null)
				dao.free();
			dao = null;
		}

	}
}
