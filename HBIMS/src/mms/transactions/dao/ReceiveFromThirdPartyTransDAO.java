package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.dao.ReceivedFromThirdPartyItemDtlsDAO;
import mms.global.dao.ItemParameterDetailDAO;
import mms.transactions.vo.ReceiveFromThirdPartyTransVO;


/**
 * 
 * @author Tanvi Sappal
 *
 */
public class ReceiveFromThirdPartyTransDAO {
	

	/**
	 * for getting option value of Store Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void storeName(ReceiveFromThirdPartyTransVO vo)
	{
		String strProcName = "{call Pkg_Mms_View.proc_hstt_store_mst(?,?,?,?,?,?,?)}"; //4+3=7
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Gifted Item Details","ReceiveFromThirdPartyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "seatId", vo.getStrSeatId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err",1,6); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,7);
			
			/* Start Adding Default value*/
			daoObj.setProcInValue(nProcIndex, "modeval","11",1);
			daoObj.setProcInValue(nProcIndex, "storeid","0",4);
			daoObj.setProcInValue(nProcIndex, "storetype_id","0",5);
			/* End Adding Default value*/
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setWsStoreNameCombo(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.storeName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * for getting option value of Item Category Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void itemCategory(ReceiveFromThirdPartyTransVO vo)
	{
		String strProcName = "{call pkg_mms_view.proc_item_category_list(?,?,?,?,?,?)}"; //6
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Gifted Item Details","ReceiveFromThirdPartyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			  		
			daoObj.setProcInValue(nProcIndex, "modeval", "4",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "store_id", vo.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "reqType", "64",4);
			daoObj.setProcOutValue(nProcIndex, "err",1,5); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,6);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setWsItemCategoryCombo(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.itemCategory() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * for getting option value of Item Category Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void getReceivedItemListTwo(ReceiveFromThirdPartyTransVO vo)
	{
     String strProcName = "{call pkg_mms_view.proc_receivedItem_dtl(?,?,?,?,?,?,?,?)}"; //4+4=8
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Gifted Item Details","ReceiveFromThirdPartyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			if(vo.getStrStatus().equals("4"))
			{	
			  daoObj.setProcInValue(nProcIndex, "modeval", "4");
			}
			else
			{
			  daoObj.setProcInValue(nProcIndex, "modeval", "5");
			}	
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err",1); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2);
			
			/* Start Adding Default value*/
			daoObj.setProcInValue(nProcIndex, "item_cat_code", "1");   
			daoObj.setProcInValue(nProcIndex, "storeid", "");
			daoObj.setProcInValue(nProcIndex, "fin_st_date", "");
			daoObj.setProcInValue(nProcIndex, "fin_end_date", "");
			/* End Adding Default value*/
			
			daoObj.executeProcedure(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
							
			if (strErr.equals("")) 
			{
				vo.setWsReceivedItemList(ws);
			}
			else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.itemCategory() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * for getting option value of Item Category Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void getReceivedItemListThree(ReceiveFromThirdPartyTransVO vo)
	{
		String strProcName = "{call pkg_mms_view.proc_receivedItem_dtl(?,?,?,?,?,?,?,?)}"; //8
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Gifted Item Details","ReceiveFromThirdPartyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			if(vo.getStrStatus().equals("4"))
			{	
			  daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			}
			else
			{
			  daoObj.setProcInValue(nProcIndex, "modeval", "3",2);
			}	
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "storeid", vo.getStrStoreId(),4);
			daoObj.setProcInValue(nProcIndex, "item_cat_code", vo.getStrItemCategoryId(),3);
			daoObj.setProcInValue(nProcIndex, "fin_st_date", vo.getStrFinancialStartYear(),5);
			daoObj.setProcInValue(nProcIndex, "fin_end_date", vo.getStrFinancialEndYear(),6);
			daoObj.setProcOutValue(nProcIndex, "err",1,7); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,8);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
						
			if (strErr.equals("")) {
				vo.setWsReceivedItemList(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.itemCategory() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * for getting option value of Item Category Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void getReceivedItemList(ReceiveFromThirdPartyTransVO vo)
	{
		String strProcName = "{call pkg_mms_view.proc_receivedItem_dtl(?,?,?,?,?,?,?,?)}"; //8
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Received Item Details","ReceiveFromThirdPartyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			  
			daoObj.setProcInValue(nProcIndex, "modeval",  (vo.getStrMode()!=null && !vo.getStrMode().equals("")) ? vo.getStrMode() : "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "storeid", vo.getStrStoreId(),4);
			daoObj.setProcInValue(nProcIndex, "item_cat_code", vo.getStrItemCategoryId(),3);
			daoObj.setProcInValue(nProcIndex, "fin_st_date", vo.getStrFinancialStartYear(),5);
			daoObj.setProcInValue(nProcIndex, "fin_end_date", vo.getStrFinancialEndYear(),6);
			daoObj.setProcOutValue(nProcIndex, "err",1,7); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,8);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
						
			if (strErr.equals("")) {
				vo.setWsReceivedItemList(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.itemCategory() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	
	public static void initAddQuery(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			
					
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_store_group_list(?,?,?,?,?,?,?)}"; //5+2=7
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "2",1);
			dao.setProcInValue(nprocIndex, "item_category", vo.getStrItemCategoryNo(),3);
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode(),2);
			dao.setProcOutValue(nprocIndex, "err", 1,6);
			dao.setProcOutValue(nprocIndex, "resultset", 2,7);
			
			/* Start Adding Default value*/
			dao.setProcInValue(nprocIndex, "strPhyStockNo", "",4);
			dao.setProcInValue(nprocIndex, "strStoreId", "",5);
			/* End Adding Default value*/
			
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");
			
			if (strerr.equals("")) {
				vo.setStrGroupComboWs(wb);

			} else {
				throw new Exception(strerr);
			}

			

		} catch (Exception e) {
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.initAddQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void getSubGroupList(ReceiveFromThirdPartyTransVO vo){
		
		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_store_subgroup_list(?,?,?,?,?)}"; //5
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "1",1);
			dao.setProcInValue(nprocIndex, "group_id", vo.getStrGroupId(),2);
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode(),3);
			dao.setProcOutValue(nprocIndex, "err", 1,4);
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				vo.setStrSubGroupComboWs(wb);

			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.getSubGroupList() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void getGenericItemList(ReceiveFromThirdPartyTransVO vo){
		
		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
		
			strproc_name = "{call PKG_MMS_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}";//7+2=9
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "1");
			dao.setProcInValue(nprocIndex, "cat_no", vo.getStrItemCategoryNo());
			dao.setProcInValue(nprocIndex, "store_id", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "group_id", vo.getStrGroupId());
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			
			/* Start Adding Default value*/
			dao.setProcInValue(nprocIndex, "item_id", "0"); 
			dao.setProcInValue(nprocIndex, "sub_group_id", "0");
			/* End Adding Default value*/
			
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				vo.setStrItemNameComboWS(wb);

			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.getGenericItemList() --> "
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
	 * The following procedure is used to populate the value of Item Name combo
	 * using Pkg_Mms_View.proc_storeitem_list procedure.
	 * 
	 * 
	 */

	public static void getItemName(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}"; //8+1=9
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "store_id", vo.getStrStoreId(),2);
			dao.setProcInValue(nprocIndex, "item_id", "0",3); // default set for item_id
			dao.setProcInValue(nprocIndex, "cat_no", vo.getStrItemCategoryNo(),4);
			dao.setProcInValue(nprocIndex, "group_id", vo.getStrGroupId(),5);
			dao.setProcInValue(nprocIndex, "sub_group_id", vo.getStrSubGroupId(),6);
			//Passing 100 as Global hospital code,if get from vo object it would get session code which is 101->not work -Priyesh
			dao.setProcInValue(nprocIndex, "hosp_code","100",7);
			
			dao.setProcOutValue(nprocIndex, "err", 1,8);
			dao.setProcOutValue(nprocIndex, "resultset", 2,9);
			
			
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				vo.setStrItemNameComboWS(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.getItemName() --> "
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
	 * The following procedure is used to populate the value of ItemBrand Name
	 * combo using Pkg_Mms_View.proc_itembrand_list procedure.
	 * 
	 * 
	 */

	public static void getItemBrandName(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}"; //9
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "2",1);
			dao.setProcInValue(nprocIndex, "store_id", vo.getStrStoreId(),2);
			dao.setProcInValue(nprocIndex, "item_id", vo.getStrItemId(),3);
			dao.setProcInValue(nprocIndex, "cat_no", vo.getStrItemCategoryNo(),4);
			dao.setProcInValue(nprocIndex, "group_id", vo.getStrGroupId(),5);
			dao.setProcInValue(nprocIndex, "sub_group_id", vo.getStrSubGroupId(),6);	
		//	dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),7);
			dao.setProcInValue(nprocIndex, "hosp_code", "100",7);
			dao.setProcOutValue(nprocIndex, "err", 1,8);
			dao.setProcOutValue(nprocIndex, "resultset", 2,9);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				vo.setStrItemBrandComboWS(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.getItemBrandName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	
	public static void getBrandDetails(ReceiveFromThirdPartyTransVO vo) {

		 
		HisDAO dao = null;
	  
		try {
			
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			
			String strFuncName = "{? = call mms_mst.get_brand_ResFlag(?::numeric, ?::numeric, ?::numeric)}"; //4
			
			int nFuncIndex = dao.setFunction(strFuncName);
			dao.setFuncInValue(nFuncIndex, 2, "1");
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex, 4, vo.getStrItemBrandId());
			dao.setFuncOutValue(nFuncIndex,1);
			dao.executeFunction_new(nFuncIndex);
			
			String strRegFlag = dao.getFuncNumeric(nFuncIndex).toString();
			
			vo.setStrRegFlag(strRegFlag);
			
			
			String strFuncName2 = "{? = call mms_mst.get_brand_dtl(?::numeric, ?::numeric, ?::numeric)}";
			
			int nFuncIndex2 = dao.setFunction(strFuncName2);
			dao.setFuncInValue(nFuncIndex2, 2, "1");
			dao.setFuncInValue(nFuncIndex2, 3, vo.getStrHospitalCode());
			dao.setFuncInValue(nFuncIndex2, 4, vo.getStrItemBrandId());
			dao.setFuncOutValue(nFuncIndex2, 1);
			dao.executeFunction(nFuncIndex2);
			
			String strBrandDetails = dao.getFuncString(nFuncIndex2);
			
			vo.setStrBrandDetails(strBrandDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			vo
					.setStrMsgString("ReceiveFromThirdPartyTransDAO.getBrandDetails() --> "
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
	 * The following procedure is used to populate the value of Supplier Name
	 * combo using Pkg_Mms_View.proc_supplier_list procedure.
	 * 
	 * 
	 */

	public static void getSuppliedByList(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_supplier_list(?,?,?,?,?,?,?)}"; //5+2=7
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "14",1);
			dao.setProcInValue(nprocIndex, "catCode", vo.getStrItemCategoryNo(),2);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),5);
			dao.setProcOutValue(nprocIndex, "err", 1,6);
			dao.setProcOutValue(nprocIndex, "resultset", 2,7);
			
			/* Start Adding Default value*/
			dao.setProcInValue(nprocIndex, "branditem_id", "0",3);
			dao.setProcInValue(nprocIndex, "contractType", "0",4);
			/* End Adding Default value*/
			
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			wb = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// System.out.println("DAO -->" + wb.size());
				vo.setStrManufactureComboWS(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ReceiveFromThirdPartyTransDAO.getmanufectuteName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void getWarrantyManufList(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_supplier_list(?,?,?,?,?,?,?)}"; //5+2=7
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "1");
			dao
					.setProcInValue(nprocIndex, "catCode", vo
							.getStrItemCategoryNo());
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			
			/* Start Adding Default value*/
			dao.setProcInValue(nprocIndex, "branditem_id", "0");
			dao.setProcInValue(nprocIndex, "contractType", "0");
			/* End Adding Default value*/
			
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			wb = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// System.out.println("DAO -->" + wb.size());
				vo.setStrWarrantyManufactureComboWS(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ReceiveFromThirdPartyTransDAO.getWarrantyManufList() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void getCurrencyList(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_currency_list(?,?,?,?,?)}"; //4+1=5
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "1",1);
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode(),2);
			dao.setProcOutValue(nprocIndex, "err", 1,4);
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			
			dao.setProcInValue(nprocIndex, "isDefault", "0",3); // default set for isDefault
			
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			wb = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {

				vo.setStrCurrencyCodeWs(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.getCurrencyList() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void getStockStatusList(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			
			if(vo.getStrItemCategoryNo().equals("10")){
				strproc_name = "{call PKG_MMS_VIEW.proc_stock_status_list(?,?,?,?,?,?)}"; //6
			}else{
				strproc_name = "{call PKG_MMS_VIEW.proc_stock_status_list(?,?,?,?,?,?)}";  //5+1=6
			}
			
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", (vo.getStrMode()!=null && !vo.getStrMode().equals("")) ? vo.getStrMode() : "1"  ,1);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),2);
			
			if(vo.getStrItemCategoryNo().equals("10")){
				dao.setProcInValue(nprocIndex, "itemCat", vo.getStrItemCategoryNo(),3);
			}
			else{
				dao.setProcInValue(nprocIndex, "itemCat","0",3);
			}
			dao.setProcInValue(nprocIndex, "itemBrandId", vo.getStrItemBrandId(),4);
			dao.setProcOutValue(nprocIndex, "err", 1,5);
			dao.setProcOutValue(nprocIndex, "resultset", 2,6);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			wb = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {

				vo.setStrStockStatusWs(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ReceiveFromThirdPartyTransDAO.getStockStatusList() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void getInstituteList(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_institute_list(?,?,?,?)}"; //4
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcOutValue(nprocIndex, "err", 1,3);
			dao.setProcOutValue(nprocIndex, "resultset", 2,4);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			wb = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {

				vo.setWsInstituteList(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ReceiveFromThirdPartyTransDAO.getInstituteList() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void getmanufectuteName(ReceiveFromThirdPartyTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("mms", "ReceiveFromThirdPartyTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_supplier_list(?,?,?,?,?,?,?)}"; //6+1=7
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal", "2",1);
			dao
					.setProcInValue(nprocIndex, "catCode", vo
							.getStrItemCategoryNo(),2);
			dao.setProcInValue(nprocIndex, "branditem_id", vo
					.getStrItemBrandId(),3);
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode(),5);
			dao.setProcOutValue(nprocIndex, "err", 1,6);
			dao.setProcOutValue(nprocIndex, "resultset", 2,7);
			
			 dao.setProcInValue(nprocIndex, "contractType", "0",4); //default set for contractType 
		     
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			wb = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				// System.out.println("DAO -->" + wb.size());
				vo.setStrManufactureComboWS(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ReceiveFromThirdPartyTransDAO.getmanufectuteName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public synchronized static void insert(ReceiveFromThirdPartyTransVO vo) {

		String strProcName = "";
		String strProcName2 = "";
		String strProcName3 = "";
		String strProcName4 = "";
		String strProcName5 = "";

		int nProcIndex = 0;
		int nProcIndex2 = 0;
		int nProcIndex3 = 0;
		int nProcIndex4 = 0;
		int nProcIndex5 = 0;

		HisDAO daoObj = null;
		boolean flag = false;
		ItemParameterDetailDAO itemParameterDetailDAO = null;
		
		ReceivedFromThirdPartyItemDtlsDAO receivedItemDtlDAO = null; 
		try {
			daoObj = new HisDAO("MMS", "ReceiveFromThirdPartyTransDAO");
			itemParameterDetailDAO = new ItemParameterDetailDAO();
			
			receivedItemDtlDAO = new ReceivedFromThirdPartyItemDtlsDAO();
			
			receivedItemDtlDAO.setStrStoreId(vo.getStrStoreId());
			receivedItemDtlDAO.setStrItemId(vo.getStrItemId());
			receivedItemDtlDAO.setStrBrandId(vo.getStrItemBrandId());
			receivedItemDtlDAO.setStrBatchNo(vo.getStrInstituteId().equals("108")?vo.getStrBatchNo()+"~LP":vo.getStrBatchNo());
			receivedItemDtlDAO.setStrHospitalCode(vo.getStrHospitalCode());
			receivedItemDtlDAO.setStrStockStatus(vo.getStrStockStatus());
			//receivedItemDtlDAO.setStrInstituteCode(vo.getStrInstituteId().replace("^", "#").split("#")[0]);  Orignal value
			receivedItemDtlDAO.setStrInstituteCode(vo.getStrInstituteId());
			receivedItemDtlDAO.setStrItemCatCode(vo.getStrItemCategoryNo());
			receivedItemDtlDAO.setStrGroupId(vo.getStrGroupId());
			receivedItemDtlDAO.setStrSubGroupId(vo.getStrSubGroupId());
			receivedItemDtlDAO.setStrManufacturerId(vo.getStrManufactureId());
			receivedItemDtlDAO.setStrReceivedQuantity(vo.getStrInHandQuantity());
			receivedItemDtlDAO.setStrReceivedQuantityUnitId(vo.getStrInHandQuantityUnitID());
			receivedItemDtlDAO.setStrManufacturerDate(vo.getStrManufactureDate());
			receivedItemDtlDAO.setStrExpiryDate(vo.getStrExpiryDate());
			receivedItemDtlDAO.setStrRate(vo.getStrRate());
			receivedItemDtlDAO.setStrRateUnitId(vo.getStrUnitRateID());
			receivedItemDtlDAO.setStrCurrencyID(vo.getStrCurrencyCode());
			receivedItemDtlDAO.setStrCurrencyValue(vo.getStrCurrencyValue());
			receivedItemDtlDAO.setStrFinancialStartYear(vo.getStrFinancialStartYear());
			receivedItemDtlDAO.setStrFinancialEndYear(vo.getStrFinancialEndYear());
			receivedItemDtlDAO.setStrRemarks(vo.getStrRemarks());
			receivedItemDtlDAO.setStrSeatId(vo.getStrSeatId());
			receivedItemDtlDAO.setStrReceivedDate(vo.getStrReceivedDate());
			receivedItemDtlDAO.setStrSuppliedBy(vo.getStrSuppliedBy());
			receivedItemDtlDAO.insert(daoObj);
			
			
			strProcName = "{call PKG_MMS_DML.Dml_Stock_Update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; //33+21=54

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			
			daoObj.setProcInValue(nProcIndex, "strid", vo.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "itemid", vo.getStrItemId(),3);
			daoObj.setProcInValue(nProcIndex, "itembrandid", vo.getStrItemBrandId(),4);
			daoObj.setProcInValue(nProcIndex, "batchno", vo.getStrInstituteId().equals("108")?vo.getStrBatchNo()+"~LP":vo.getStrBatchNo(),5);
			daoObj.setProcInValue(nProcIndex, "itemcatno", vo.getStrItemCategoryNo(),6);
			daoObj.setProcInValue(nProcIndex, "groupid", vo.getStrGroupId(),7);
			daoObj.setProcInValue(nProcIndex, "subgroupid", vo.getStrSubGroupId(),8);
			daoObj.setProcInValue(nProcIndex, "expirydate", vo.getStrExpiryDate(),9);
			daoObj.setProcInValue(nProcIndex, "manufdate", vo.getStrManufactureDate(),10);
			daoObj.setProcInValue(nProcIndex, "stockstatuscode", vo.getStrStockStatus(),11);
			daoObj.setProcInValue(nProcIndex, "inventoryflag", "0",12);
			daoObj.setProcInValue(nProcIndex, "inhandqty", vo
					.getStrInHandQuantity(),13);
			daoObj.setProcInValue(nProcIndex, "inhandqtyunitid", vo
					.getStrInHandQuantityUnitID(),14);
			daoObj.setProcInValue(nProcIndex, "suppid", vo
					.getStrManufactureId(),15);
			daoObj.setProcInValue(nProcIndex, "rate", vo.getStrRate(),16);
			daoObj.setProcInValue(nProcIndex, "rateunitid", vo
					.getStrUnitRateID(),17);
			daoObj
					.setProcInValue(nProcIndex, "saleprice", vo
							.getStrSalePrice(),18);
			daoObj.setProcInValue(nProcIndex, "salepriceunitid", vo
					.getStrUnitSaleID(),19);
			
			daoObj.setProcInValue(nProcIndex, "suppliedBy", vo
					.getStrSuppliedBy(),23);
			daoObj.setProcInValue(nProcIndex, "recievedDate", vo
					.getStrReceivedDate(),24);
			daoObj.setProcInValue(nProcIndex, "seatid", vo.getStrSeatId(),22);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo
					.getStrHospitalCode(),27);

			daoObj.setProcInValue(nProcIndex, "description","Received from Third Party : "+vo.getStrReceivedFromThirdPartyName(),42);

			daoObj.setProcInValue(nProcIndex, "currencyCode", vo
					.getStrCurrencyCode(),25);
			daoObj.setProcInValue(nProcIndex, "currencyValue", vo
					.getStrCurrencyValue(),28);

			daoObj.setProcInValue(nProcIndex, "item_specification", vo.getStrItemSpecification(),52);
			
			if (vo.getStrItemOtherDtls() != null
					&& vo.getStrItemOtherDtls().length > 0) {

				daoObj.setProcInValue(nProcIndex, "freeItemFlag", "1",26);

			} else {

				daoObj.setProcInValue(nProcIndex, "freeItemFlag", "0",26);
			}

			if (vo.getStrItemPartDtls() != null
					&& vo.getStrItemPartDtls().length > 0) {

				daoObj.setProcInValue(nProcIndex, "partFlag", "1",36);

			} else {

				daoObj.setProcInValue(nProcIndex, "partFlag", "0",36);
			}

			if (vo.getStrWarrantyFlag().equals("1")) {

				daoObj.setProcInValue(nProcIndex, "warrentyFlag", "1",37);

			} else {

				daoObj.setProcInValue(nProcIndex, "warrentyFlag", "0",37);
			}

			if (vo.getStrItemParamDtls() != null
					&& vo.getStrItemParamDtls().length > 0) {

				daoObj.setProcInValue(nProcIndex, "itemParamFlag", "1",35);

			} else {

				daoObj.setProcInValue(nProcIndex, "itemParamFlag", "0",35);
			}

			daoObj.setProcOutValue(nProcIndex, "err", 1,54);
			daoObj.setProcOutValue(nProcIndex, "retSerialNo", 1,53);

			/* Start Adding Default value*/
			
			  daoObj.setProcInValue(nProcIndex, "pono", "0",20); 
			  daoObj.setProcInValue(nProcIndex, "podate", "",21);
		      daoObj.setProcInValue(nProcIndex, "old_stockstatuscode", "1",29);  
		      daoObj.setProcInValue(nProcIndex, "old_batchno", "0",30);
		      daoObj.setProcInValue(nProcIndex, "old_itemserialno", "0",31);
		      daoObj.setProcInValue(nProcIndex, "old_itemid", "0",32);
		      daoObj.setProcInValue(nProcIndex, "old_itembrandid", "0",33);
		      daoObj.setProcInValue(nProcIndex, "old_strid", "0",34);
		      daoObj.setProcInValue(nProcIndex, "tostrid", "",38);
		      daoObj.setProcInValue(nProcIndex, "reservedflag", "0",39);
		      daoObj.setProcInValue(nProcIndex, "transno", "0",40);
		      daoObj.setProcInValue(nProcIndex, "transdate", vo.getStrReceivedDate(),41);
		      daoObj.setProcInValue(nProcIndex, "reqtypeid", "58",43);
		      daoObj.setProcInValue(nProcIndex, "blockqtyflag", "0",44);
		      daoObj.setProcInValue(nProcIndex, "blockedqty", "0",45);
		      daoObj.setProcInValue(nProcIndex, "blockedqtyunitid", "0",46);
		      daoObj.setProcInValue(nProcIndex, "releaseqty", "0",47);
		      daoObj.setProcInValue(nProcIndex, "releaseqtyunitid", "0",48);
		      daoObj.setProcInValue(nProcIndex, "invoiceNo", "",49);
		      daoObj.setProcInValue(nProcIndex, "invoiceDate", "",50); 
		      daoObj.setProcInValue(nProcIndex, "item_serialNoFlag", "1",51);
		      /* End Adding Default value*/			
			
			daoObj.execute(nProcIndex, 1);

			
//			strProcName2 = "{call PKG_MMS_DML.Dml_Hstt_Free_Items_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; //15+2=17
//
//			if (vo.getStrItemOtherDtls() != null
//					&& vo.getStrItemOtherDtls().length > 0)
//				for (int i = 0; i < vo.getStrItemOtherDtls().length; i++) {
//
//					String strTemp = vo.getStrItemOtherDtls()[i];
//					String[] strItemOtherVal = strTemp.replace("^", "#").split(
//							"#");
//
//					nProcIndex2 = daoObj.setProcedure(strProcName2);
//
//					daoObj.setProcInValue(nProcIndex2, "modval", "1");
//					daoObj.setProcInValue(nProcIndex2, "itemid", vo
//							.getStrItemId());
//					daoObj.setProcInValue(nProcIndex2, "itembrandid", vo
//							.getStrItemBrandId());
//					daoObj.setProcInValue(nProcIndex2, "batchno", vo
//							.getStrBatchNo());
//					daoObj.setProcInValue(nProcIndex2, "freeitemid",
//							strItemOtherVal[1]);
//					daoObj.setProcInValue(nProcIndex2, "freeitembrandid",
//							strItemOtherVal[2]);
//					daoObj.setProcInValue(nProcIndex2, "freeitembatchno",
//							strItemOtherVal[3]);
//					daoObj.setProcInValue(nProcIndex2, "freeitemcatno",
//							strItemOtherVal[0]);
//					daoObj.setProcInValue(nProcIndex2, "expirydate",
//							strItemOtherVal[4]);
//					daoObj.setProcInValue(nProcIndex2, "manufdate",
//							strItemOtherVal[5]);
//					daoObj.setProcInValue(nProcIndex2, "qty",
//							strItemOtherVal[6]);
//					daoObj.setProcInValue(nProcIndex2, "qtyunitid",
//							strItemOtherVal[7]);
//					daoObj.setProcInValue(nProcIndex2, "hosp_code", vo
//							.getStrHospitalCode());
//
//					daoObj.setProcOutValue(nProcIndex2, "err", 1);
//					daoObj.setProcOutValue(nProcIndex2, "dml_count", 1);
//
//					/* Start Adding Default value*/
//					daoObj.setProcInValue(nProcIndex2, "transNo", "0");
//					daoObj.setProcInValue(nProcIndex2, "strId", "0");
//					/* End Adding Default value*/
//					
//					daoObj.execute(nProcIndex2, 1);
//
//				}
//
//			strProcName3 = "{call PKG_MMS_DML.Dml_Hstt_Part_Items_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";//20+2=22
//
//			if (vo.getStrItemPartDtls() != null
//					&& vo.getStrItemPartDtls().length > 0)
//				for (int i = 0; i < vo.getStrItemPartDtls().length; i++) {
//
//					String strTemp = vo.getStrItemPartDtls()[i];
//					String[] strItemPartVal = strTemp.replace("^", "#").split(
//							"#");
//
//					nProcIndex3 = daoObj.setProcedure(strProcName3);
//
//					daoObj.setProcInValue(nProcIndex3, "modval", "1");
//					daoObj.setProcInValue(nProcIndex3, "itemid", vo
//							.getStrItemId());
//					daoObj.setProcInValue(nProcIndex3, "itembrandid", vo
//							.getStrItemBrandId());
//					daoObj.setProcInValue(nProcIndex3, "batchno", vo
//							.getStrBatchNo());
//					daoObj.setProcInValue(nProcIndex3, "partitemid",
//							strItemPartVal[1]);
//					daoObj.setProcInValue(nProcIndex3, "partitembrandid",
//							strItemPartVal[2]);
//					
//					if(strItemPartVal[3] != null && strItemPartVal[3].length() != 0){
//						
//						daoObj.setProcInValue(nProcIndex3, "partitembatchno",
//								strItemPartVal[3]);
//					}else{
//						daoObj.setProcInValue(nProcIndex3, "partitembatchno",
//								"0");
//					}
//					
//					
//					daoObj.setProcInValue(nProcIndex3, "partitemcatno",
//							strItemPartVal[0]);
//					daoObj.setProcInValue(nProcIndex3, "expirydate",
//							strItemPartVal[4]);
//					daoObj.setProcInValue(nProcIndex3, "manufdate",
//							strItemPartVal[5]);
//					daoObj
//							.setProcInValue(nProcIndex3, "qty",
//									strItemPartVal[6]);
//					daoObj.setProcInValue(nProcIndex3, "qtyunitid",
//							strItemPartVal[7]);
//
//					daoObj.setProcInValue(nProcIndex3, "manufid",
//							strItemPartVal[8]);
//
//					daoObj.setProcInValue(nProcIndex3, "comp_type",
//							strItemPartVal[9]);
//					
//					daoObj.setProcInValue(nProcIndex3, "is_separate",
//							strItemPartVal[10]);
//					
//					daoObj.setProcInValue(nProcIndex3, "warranty_period",
//							strItemPartVal[11]);
//					
//					daoObj.setProcInValue(nProcIndex3, "warranty_Unit",
//							strItemPartVal[12]);
//					
//					daoObj.setProcInValue(nProcIndex3, "hosp_code", vo
//							.getStrHospitalCode());
//
//					daoObj.setProcOutValue(nProcIndex3, "err", 1);
//					daoObj.setProcOutValue(nProcIndex3, "dml_count", 1);
//
//					/* Start Adding Default value*/
//					daoObj.setProcInValue(nProcIndex3, "transNo", "0"); 
//					daoObj.setProcInValue(nProcIndex3, "strId", "0"); 
//					/* End Adding Default value*/
//										
//					daoObj.execute(nProcIndex3, 1);
//
//				}
//
//			if (vo.getStrIsWarrantyDetails().equals("1")) {
//
//				strProcName4 = "{call PKG_MMS_DML.dml_warrenty_dtls(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; //14+3=17
//				nProcIndex4 = daoObj.setProcedure(strProcName4);
//
//				daoObj.setProcInValue(nProcIndex4, "modval", "1");
//				daoObj
//						.setProcInValue(nProcIndex4, "item_id", vo
//								.getStrItemId());
//				daoObj.setProcInValue(nProcIndex4, "item_brand_id", vo
//						.getStrItemBrandId());
//				daoObj.setProcInValue(nProcIndex4, "batch_sl_no", vo
//						.getStrBatchNo());
//				daoObj.setProcInValue(nProcIndex4, "hosp_code", vo
//						.getStrHospitalCode());
//				daoObj.setProcInValue(nProcIndex4, "warrenty_date", vo
//						.getStrWarrantyDate());
//				daoObj.setProcInValue(nProcIndex4, "manuf_id", vo
//						.getStrWarantyManufacturer());
//				
//				daoObj.setProcInValue(nProcIndex4, "warrenty_upto", vo
//						.getStrWarrantyUpTo());
//				daoObj.setProcInValue(nProcIndex4, "warrenty_unitid", vo
//						.getStrWarrantyUpToUnit());
//				daoObj.setProcInValue(nProcIndex4, "fin_start_yr", vo
//						.getStrFinancialStartYear());
//				daoObj.setProcInValue(nProcIndex4, "fin_end_yr", vo
//						.getStrFinancialEndYear());
//				daoObj.setProcInValue(nProcIndex4, "remarks", vo
//						.getStrWarrantyRemarks());
//				daoObj
//						.setProcInValue(nProcIndex4, "seat_id", vo
//								.getStrSeatId());
//
//				daoObj.setProcOutValue(nProcIndex4, "err", 1);
//
//				/* Start Adding Default value*/
//				daoObj.setProcInValue(nProcIndex4, "po_no", ""); 
//				daoObj.setProcInValue(nProcIndex4, "strId", "0");
//				daoObj.setProcInValue(nProcIndex4, "transno", "0");
//				/* End Adding Default value*/
//				
//				daoObj.execute(nProcIndex4, 1);
//
//			}
//
//			if (vo.getStrIsInstallDetails().equals("1")) {
//
//				strProcName5 = "{call PKG_MMS_DML.dml_installation_dtls(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; //15+3=18
//				nProcIndex5 = daoObj.setProcedure(strProcName5);
//
//				daoObj.setProcInValue(nProcIndex5, "modval", "1");
//				daoObj
//						.setProcInValue(nProcIndex5, "item_id", vo
//								.getStrItemId());
//				daoObj.setProcInValue(nProcIndex5, "item_brand_id", vo
//						.getStrItemBrandId());
//				daoObj.setProcInValue(nProcIndex5, "batch_sl_no", vo
//						.getStrBatchNo());
//				daoObj.setProcInValue(nProcIndex5, "hosp_code", vo
//						.getStrHospitalCode());
//				daoObj.setProcInValue(nProcIndex5, "install_start_date", vo
//						.getStrInstallStartDate());
//
//				daoObj.setProcInValue(nProcIndex5, "install_end_date", vo
//						.getStrInstallEndDate());
//
//				daoObj.setProcInValue(nProcIndex5, "install_status", vo
//						.getStrInstallStatus());
//				daoObj.setProcInValue(nProcIndex5, "install_by", vo
//						.getStrInstallBy());
//
//				daoObj.setProcInValue(nProcIndex5, "installer_contactNo", vo
//						.getStrInstallerContactNo());
//
//				daoObj.setProcInValue(nProcIndex5, "fin_start_yr", vo
//						.getStrFinancialStartYear());
//				daoObj.setProcInValue(nProcIndex5, "fin_end_yr", vo
//						.getStrFinancialEndYear());
//				daoObj.setProcInValue(nProcIndex5, "remarks", vo
//						.getStrWarrantyRemarks());
//				daoObj
//						.setProcInValue(nProcIndex5, "seat_id", vo
//								.getStrSeatId());
//
//				daoObj.setProcOutValue(nProcIndex5, "err", 1);
//
//				/* Start Adding Default value*/
//				daoObj.setProcInValue(nProcIndex5, "po_no", "");
//				daoObj.setProcInValue(nProcIndex5, "seat_id", "");
//				daoObj.setProcInValue(nProcIndex5, "transno", "0");
//				/* End Adding Default value*/
//				
//				daoObj.execute(nProcIndex5, 1);
//
//			}
//			
//			if(vo.getStrParamCheck() != null)
//			if (vo.getStrParamCheck().length > 0) {
//
//				for (int i = 0; i < vo.getStrParamCheck().length; i++) {
//
//					String[] strTemp = vo.getStrParamCheck()[i].replace("^",
//							"#").split("#");
//
//					itemParameterDetailDAO.setStrHospitalCode(vo
//							.getStrHospitalCode());
//					itemParameterDetailDAO.setStrSeatId(vo.getStrSeatId());
//					itemParameterDetailDAO.setStrItemId(vo.getStrItemBrandId());
//					itemParameterDetailDAO.setStrParentParamId(strTemp[0]);
//					itemParameterDetailDAO.setStrParamId(strTemp[1]);
//					itemParameterDetailDAO.setStrParamValue(vo
//							.getStrParamValue()[i]);
//					itemParameterDetailDAO.setStrRemarks("");
//
//					itemParameterDetailDAO.insertItemParameterDtls(daoObj);
//
//				}
//
//			}

			flag = true;
			daoObj.fire();

			if(flag)
			{
				ReceiveFromThirdPartyTransDAO.updateCurrStock(vo);
			}
			String strSlNo = daoObj.getString(nProcIndex, "retSerialNo");

			if (strSlNo != null)
				vo.setStrSerialNo(strSlNo);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.update() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;

			}

			itemParameterDetailDAO = null;

		}

	}
	public static void updateCurrStock(ReceiveFromThirdPartyTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;

		try 
		{
			dao = new HisDAO("mms", "DrugInventoryTransDAO");
			strproc_name = "{call PKG_MMS_DML.Update_Rac_CurrStock(?,?,?,?,?,?,?,?,?,?,?)}"; // 11 variables
   
		
			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex,  "modval", "1",1);                 //1
			dao.setProcInValue(nprocIndex,  "strid", vo.getStrStoreId(),5);   //2
			dao.setProcInValue(nprocIndex,  "itemid", vo.getStrItemId(),8);   //3
			dao.setProcInValue(nprocIndex,  "itembrandid", vo.getStrItemBrandId(),7);//4
			dao.setProcInValue(nprocIndex,  "batchno", vo.getStrBatchNo(),6);        //5
			dao.setProcInValue(nprocIndex,  "hosp_code", vo.getStrHospitalCode(),2); //6
			dao.setProcInValue(nprocIndex,  "item_cat_no", vo.getStrItemCategoryNo(),3);//7
			dao.setProcInValue(nprocIndex,  "stockstatuscode",vo.getStrStockStatus(),4);        //8
			dao.setProcInValue(nprocIndex,  "rackNumber", (vo.getStrRackNumber()==null?"0":vo.getStrRackNumber()),9);//9
			dao.setProcInValue(nprocIndex,  "old_itemserialno", "0",10);//10
			dao.setProcOutValue(nprocIndex, "err", 1,11);//11
			dao.executeProcedureByPosition(nprocIndex);
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.updateCurrStock() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	

	/**
	 * The following procedure is used to populate the value of Unit Name combo
	 * using Pkg_Mms_View.Proc_Gblt_Unit_Mst procedure.
	 * 
	 * 
	 */
	public static void unitNameCombo(ReceiveFromThirdPartyTransVO vo) {
		String strFuncName = "";
		String strProcName = "";

		int nFuncIndex = 0;
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj = null;
		String strUnitRate = "";

		try {

			daoObj = new HisDAO("ItemInventoryTrans", "ReceiveFromThirdPartyTransDAO");
			strFuncName = "{? = call mms_mst.get_inventory_unitid(?::numeric, ?::numeric, ?::numeric, ?::numeric)}"; //5
			strProcName = "{call Pkg_Mms_View.Proc_Gblt_Unit_Mst(?,?,?,?,?,?)}"; //5+1=6

			nFuncIndex = daoObj.setFunction(strFuncName);
			daoObj.setFuncInValue(nFuncIndex, 2, "1");
			daoObj.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			daoObj.setFuncInValue(nFuncIndex, 4, vo.getStrItemCategoryNo());
			daoObj.setFuncInValue(nFuncIndex, 5, vo.getStrItemId());;
			daoObj.setFuncOutValue(nFuncIndex, 3);
			daoObj.executeFuncForNumeric(nFuncIndex);
			strUnitRate = daoObj.getFuncNumeric(nFuncIndex).toString();
			vo.setStrUnitRateID(strUnitRate);

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo
					.getStrHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "unit_id", vo.getStrUnitRateID(),2);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			
			daoObj.setProcInValue(nProcIndex, "module_id", "",3); // Defaut set for module_id
			
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setStrUnitRateComboWS(ws);

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.unitNameCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public static void unitInHandNameCombo(ReceiveFromThirdPartyTransVO vo) {

		String strProcName = "";

		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj = null;

		try {

			daoObj = new HisDAO("ItemInventoryTrans", "ReceiveFromThirdPartyTransDAO");

			strProcName = "{call Pkg_Mms_View.Proc_Gblt_Unit_Mst(?,?,?,?,?,?)}"; //5+1=6

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo
					.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "unit_id", vo
					.getStrInHandQuantityUnitID());
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "module_id", ""); // Defaut set for module_id
			
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				vo.setStrUnitNameComboWS(ws);

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.unitInHandNameCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public static void unitSaleNameCombo(ReceiveFromThirdPartyTransVO vo) {

		String strProcName = "";

		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj = null;

		try {

			daoObj = new HisDAO("ItemInventoryTrans", "ReceiveFromThirdPartyTransDAO");

			strProcName = "{call Pkg_Mms_View.Proc_Gblt_Unit_Mst(?,?,?,?,?,?)}"; //5+1=6

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo
					.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "unit_id", vo.getStrUnitSaleID());
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "module_id", ""); // Defaut set for module_id
			
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				vo.setStrUnitSaleComboWS(ws);

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.unitSaleNameCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public static void unitRateNameCombo(ReceiveFromThirdPartyTransVO vo) {

		String strProcName = "";

		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj = null;

		try {

			daoObj = new HisDAO("ItemInventoryTrans", "ReceiveFromThirdPartyTransDAO");

			strProcName = "{call Pkg_Mms_View.Proc_Gblt_Unit_Mst(?,?,?,?,?,?)}"; //5+1=6

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo
					.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "unit_id", vo.getStrUnitRateID());
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "module_id", ""); // Defaut set for module_id
			
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				vo.setStrUnitRateComboWS(ws);

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ReceiveFromThirdPartyTransDAO.unitRateNameCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

}
