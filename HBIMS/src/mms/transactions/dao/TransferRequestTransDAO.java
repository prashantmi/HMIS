package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import mms.transactions.vo.TransferRequestTransVO;

public class TransferRequestTransDAO {

	
	
	
	
	/**
	 * The following procedure is used to get Transfer Item Detail(s)
	 * 
	 */

	public static void getTransferItemDtl(TransferRequestTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{			
			dao = new HisDAO("mms", "TransferRequestTransDAO");	
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_transitem_dtls(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "1");	
			dao.setProcInValue(nprocIndex, "strId", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "tarnsfer_No", vo.getStrRequestNo());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			/* End */
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) 
			{
				if( wb!=null && wb.size()>0)
				{
					vo.setWbTransferOrderDetail(wb);
				}
				
			}
		} catch (Exception e) {
			vo.setStrMsgString("TransferRequestTransDAO.getTransferItemDtl() --> "
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
	 * The following procedure is used to populate the Transfer Order Detail(s)
	 * 
	 */

	public static void getOrderDtlForView(TransferRequestTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{
			dao = new HisDAO("mms", "TransferRequestTransDAO");	
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);
           	dao.setProcInValue(nprocIndex, "modeval", "3");	
			dao.setProcInValue(nprocIndex, "strId", "0");
			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrOrderNumber());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			/* End */
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) 
			{
				if( wb!=null && wb.size()>0)
				{
					vo.setWbTransferOrderDetailView(wb);
				}
				

			} 
			
		} catch (Exception e) {
			vo.setStrMsgString("TransferRequestTransDAO.getOrderDtlForView() --> "
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
	 * The following procedure is used to populate the Transfer Details(s) 
	 * 
	 */

	public static void getTransferDtl(TransferRequestTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{			
			dao = new HisDAO("mms", "TransferRequestTransDAO");		
		
			strproc_name = "{call PKG_MMS_VIEW.proc_trasfer_trans_dtls(?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);
 
			dao.setProcInValue(nprocIndex, "modeval", "1");		
			dao.setProcInValue(nprocIndex, "order_No", vo.getStrOrderNumber());			
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			/* End */
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) 
			{
				if( wb!=null && wb.size()>0)
				{
					vo.setWbTransferOrderDetail(wb);
					
				}		
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("TransferRequestTransDAO.getTransferDtl() --> "
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
	 * The following procedure is used to populate the Transfer Order Detail(s)
	 * 
	 */

	public static void getTransferOrderDtlForView(TransferRequestTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{
			dao = new HisDAO("mms", "TransferRequestTransDAO");	
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "2");	
			dao.setProcInValue(nprocIndex, "strId", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrRequestNo());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			/* End */
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) 
			{
				if( wb!=null && wb.size()>0)
				{
					vo.setWbTransferOrderDetail(wb);
				}
				

			} 
			
		} catch (Exception e) {
			vo.setStrMsgString("TransferRequestTransDAO.getTransferDtlForView() --> "
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
	 * The following procedure is used to get Drug Transfer Detial(s)
	 * for Modify
     * 
	 */

	public static void getTransferDtlForModify(TransferRequestTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{
			
			
			dao = new HisDAO("mms", "TransferRequestTransDAO");			
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_transreq_dtls(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "1");	
			dao.setProcInValue(nprocIndex, "strId", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrRequestNo());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			/* End */
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) 
			{
				if( wb!=null && wb.size()>0)
				{
					vo.setStrModifyDtlWS(wb);
				}				

			} 
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("TransferRequestTransDAO.getTransferDtlForModify() --> "
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
	 * The following procedure is used to get The Drug Transfer Details for View
	 * 
	 */

	public static void getTransferDtlForView(TransferRequestTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{
			dao = new HisDAO("mms", "TransferRequestTransDAO");		
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_transreq_dtls(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "2");	
			dao.setProcInValue(nprocIndex, "strId", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrRequestNo());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			/* End */
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) 
			{
				if( wb!=null && wb.size()>0)
				{
					vo.setStrModifyDtlWS(wb);
				}
				

			} 
			
		} catch (Exception e) {
			vo.setStrMsgString("TransferRequestTransDAO.getTransferDtlForView() --> "
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
	 * The following procedure is used to populate the value of Drug Name combo
	 * 
	 */

	public static void getItemName(TransferRequestTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		HisUtil hisutil = null;
		String str = null;
		try 
		{
			hisutil = new HisUtil("master", "TransferRequestTransDAO");
			dao = new HisDAO("mms", "TransferRequestTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "6");
			dao.setProcInValue(nprocIndex, "group_id", vo.getStrGroupId());
			dao.setProcInValue(nprocIndex, "sub_group_id", vo.getStrSubGroupId());
			dao.setProcInValue(nprocIndex, "store_id", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "cat_no", vo.getStrItemCategoryNo());
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			/* Start */    
			dao.setProcInValue(nprocIndex, "item_id", "0");  
			/* End */
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) 
			{
				if (wb != null && wb.size() != 0) 
				{
					str = hisutil.getOptionValue(wb, "0", "Select Value", true);
					vo.setStrItemNameCombo(str);
				}
				else 
				{
					str = "<option value='0'>Select Value</option>";
					vo.setStrItemNameCombo(str);
				}

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("TransferRequestTransDAO.getItemName() --> "
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
	 * The following procedure is used to populate the value of Group Name combo
	 * 
	 */
	
	public static void GetGroupNameCombo(TransferRequestTransVO vo) 
	{
	      
		String err = "";
		String proc_name1 = "{call pkg_mms_view.proc_store_group_list(?,?,?,?,?,?,?)}";
		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		HisUtil hisutil = null;
		String str = null;
		try 
		{
			hisutil = new HisUtil("master", "TransferRequestTransDAO");
			dao = new HisDAO("mms","TransferRequestTransDAO.GetGroupNameCombo(TransferRequestTransVO vo)");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

            dao.setProcInValue(procIndex1, "modeval", "5");
			dao.setProcInValue(procIndex1, "item_category", vo.getStrItemCategoryNo());
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());
			/*start*/
			dao.setProcInValue(procIndex1, "strPhyStockNo","");
			dao.setProcInValue(procIndex1, "strStoreId", vo.getStrStoreId());
			/*end*/

			dao.setProcOutValue(procIndex1, "ERR", 1); // 1 for string return
			// value

			dao.setProcOutValue(procIndex1, "RESULTSET", 2); // 2 for object

			// execute procedure

			dao.executeProcedure(procIndex1);

			// get value
			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "RESULTSET");
			 
			if (ws != null && ws.size() != 0) 
			{
				str = hisutil.getOptionValue(ws, "-1", "0^All", true);
				vo.setStrGroupNameCombo(str);
			}
			else 
			{
				str = "<option value='0'>Select Value</option>";
				vo.setStrGroupNameCombo(str);
			}
		} 
		catch (Exception e) 
		{
			vo
					.setStrMsgString("TransferRequestTransDAO.GetGroupNameCombo() --> "
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
	 * The following procedure is used to populate the value of Sub - Group Name combo
	 * 
	 */
	public static void getSubGroupList(TransferRequestTransVO vo) 
	{
		String err = "";
		String proc_name1 = "{call pkg_MMS_view.proc_subgroup_list(?,?,?,?,?,?,?)}";//4+1=5
		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		HisUtil hisutil = null;
		String str ="";
		try 
		{
			hisutil = new HisUtil("master", "TransferRequestTransDAO");
			dao = new HisDAO("TransferRequestTransDAO", "getSubGroupList");
			procIndex1 = dao.setProcedure(proc_name1);
			// set value
		      
			dao.setProcInValue(procIndex1, "modeval", "1");
			dao.setProcInValue(procIndex1, "group_id", vo.getStrGroupId());
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());			
			dao.setProcInValue(procIndex1, "strId", vo.getStrStoreId());
			dao.setProcInValue(procIndex1, "catCode", vo.getStrItemCategoryNo());			
			dao.setProcOutValue(procIndex1, "ERR", 1); // 1 for string return
			dao.setProcOutValue(procIndex1, "RESULTSET", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(procIndex1);
			// get value
			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			ws = dao.getWebRowSet(procIndex1, "RESULTSET");

			if (ws != null && ws.size() != 0) 
			{
				str = hisutil.getOptionValue(ws, "-1", "0^All", true);
				vo.setStrSubGroupCombo(str);
			}
			else 
			{
				str = "<option value='0'>Select value</option>";
				vo.setStrSubGroupCombo(str);
			}		

		} catch (Exception e) {

			//e.printStackTrace();
			
			vo.setStrMsgString("TransferRequestTransDAO.getSubGroupList() --> "
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
	 * This function is used to set initial values in Received by combo.
	 * @param _ItemTransferTransVO
	 */
	public static void getApprovedByCombo(TransferRequestTransVO  vo)
	
	{
		
		String strProcName = "{call PKG_MMS_VIEW.proc_store_emp_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		WebRowSet ws = null;
		HisDAO daoObj=null;
		HisUtil hisutil = null;
		String str ="";
		try 
		{
			hisutil = new HisUtil("master", "TransferRequestTransDAO");
			daoObj  = new HisDAO("MMSModule","TransferRequestTransDAO");
			
			nProcIndex = daoObj.setProcedure(strProcName);
						
			daoObj.setProcInValue(nProcIndex, "modeVal", "1");
			daoObj.setProcInValue(nProcIndex, "storeId", vo.getStrStoreId());
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err",1); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2);
			
			daoObj.executeProcedure(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(strErr.equals(""))
			{
				if(ws!=null)
				{
					str=hisutil.getOptionValue(ws,"0", "Select Value", true);
					str=str+"<option value='1'>Other</option>";
				}
				else
				{
					str="<option value='0'>Select Value</option>";
				}
				vo.setStrApprovedByCombo(str);
			}
		}
		catch(Exception _err)
		{
			_err.printStackTrace();
			vo.setStrMsgString("TransferRequestTransDAO.getApprovedByCombo() --> "
					+ _err.getMessage());
			vo.setStrMsgType("1");
		}
	}

	
	public synchronized static void insert(TransferRequestTransVO vo)
	{

		String   strProcName3 = "";
		int       nProcIndex3 = 0;
		HisDAO         daoObj = null;
		String   strStoreName = "";
	  try 
		{
//		  System.out.println("strStoreId==>"+vo.getStrStoreId());
//		  System.out.println("strGroupId==>"+vo.getStrGroupId());
//		  System.out.println("strSubGroupCode==>"+vo.getStrSubGroupCode());
//		  System.out.println("strItemBrandId==>"+vo.getStrItemBrandId().split("\\^")[0]);
//		  System.out.println("strDemandedQty==>"+vo.getStrDemandedQty());
//		  System.out.println("strApprovedDate==>"+vo.getStrApprovedDate());
//		  System.out.println("strReqStatus==>"+vo.getStrReqStatus());
//		  System.out.println("strApprovedBy==>"+vo.getStrApprovedBy());
//		  System.out.println("strRemarks==>"+vo.getStrRemarks());
		  
		  
			             daoObj = new HisDAO("MMS", "TransferRequestTransDAO");
			       strProcName3 = "{call PKG_MMS_DML.dml_transfer_transreq_dtls(?,?,?,?,?,?,?,?,?,?,?,?,?)}";// 13 Varibale's
			        nProcIndex3 = daoObj.setProcedure(strProcName3);								
					daoObj.setProcInValue(nProcIndex3, "modval", "1");                          //1
					daoObj.setProcInValue(nProcIndex3, "store_id", vo.getStrStoreId());         //2
					daoObj.setProcInValue(nProcIndex3, "groupId",vo.getStrGroupId());  	        //3
					daoObj.setProcInValue(nProcIndex3, "hospital_code", vo.getStrHospitalCode());             //4
					daoObj.setProcInValue(nProcIndex3, "itemId",vo.getStrItemBrandId().split("\\^")[1]);      //5
					daoObj.setProcInValue(nProcIndex3, "itemBrandId",vo.getStrItemBrandId().split("\\^")[0]); //6
					daoObj.setProcInValue(nProcIndex3, "reqQty",vo.getStrDemandedQty());         //7
					daoObj.setProcInValue(nProcIndex3, "reqQtyUnit","6301");      	 	         //8
					daoObj.setProcInValue(nProcIndex3, "approvBy",	vo.getStrApprovedBy()); 	 //9
					daoObj.setProcInValue(nProcIndex3, "appDate",	vo.getStrApprovedDate());    //10
					daoObj.setProcInValue(nProcIndex3, "remarks",vo.getStrRemarks());    	  	 //11					
					daoObj.setProcInValue(nProcIndex3, "seat_id",vo.getStrSeatId());       //12
					daoObj.setProcOutValue(nProcIndex3, "err", 1);                         //13

					daoObj.execute(nProcIndex3, 1);

					strStoreName = vo.getStrStoreName();

					daoObj.fire();	
			
		} 
	  catch (Exception e) 
	  {
			e.printStackTrace();			
			        vo.setStrMsgString("--> TransferRequestTransDAO.INSERT()-->"+ e.getMessage());
					vo.setStrStoreName(strStoreName);
					vo.setStrMsgType("1");
						
		   
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
	
	
	public synchronized static void update(TransferRequestTransVO vo)
	{

		String   strProcName3 = "";
		int       nProcIndex3 = 0;
		HisDAO         daoObj = null;
		String   strStoreName = "";
	  try 
		{
	  
//		  System.out.println("strStoreId==>"+vo.getStrStoreId());
//		  System.out.println("srReq No==>"+vo.getStrRequestNo());
//		  System.out.println("strDemandedQty==>"+vo.getStrDemandedQty());
//		  System.out.println("strApprovedDate==>"+vo.getStrApprovedDate());
//		  System.out.println("strReqStatus==>"+vo.getStrReqStatus());
//		  System.out.println("strApprovedBy==>"+vo.getStrApprovedBy());
//		  System.out.println("strRemarks==>"+vo.getStrRemarks());
		  
		  
			             daoObj = new HisDAO("MMS", "TransferRequestTransDAO");
			       strProcName3 = "{call PKG_MMS_DML.dml_transfer_transreq_dtls(?,?,?,?,?,?,?,?,?,?)}";// 10 Varibale's
			        nProcIndex3 = daoObj.setProcedure(strProcName3);								
					daoObj.setProcInValue(nProcIndex3, "modval", "2");                  //1
					daoObj.setProcInValue(nProcIndex3, "store_id", vo.getStrStoreId()); //2
					daoObj.setProcInValue(nProcIndex3, "hospital_code", vo.getStrHospitalCode());  //3
					daoObj.setProcInValue(nProcIndex3, "reqQty",vo.getStrDemandedQty());    //4
					daoObj.setProcInValue(nProcIndex3, "approvBy",	vo.getStrApprovedBy()); 	 //5
					daoObj.setProcInValue(nProcIndex3, "appDate",	vo.getStrApprovedDate());    //6
					daoObj.setProcInValue(nProcIndex3, "remarks",vo.getStrRemarks());    	  	 //7					
					daoObj.setProcInValue(nProcIndex3, "seat_id",vo.getStrSeatId()); //8
					daoObj.setProcInValue(nProcIndex3, "req_No",vo.getStrRequestNo()); //9
					daoObj.setProcOutValue(nProcIndex3, "err", 1);                   //10

					daoObj.execute(nProcIndex3, 1);

					strStoreName = vo.getStrStoreName();

					daoObj.fire();			
		} 
	  catch (Exception e) 
	  {
			e.printStackTrace();			
			        vo.setStrMsgString("--> TransferRequestTransDAO.UPDATE()-->"+ e.getMessage());
					vo.setStrStoreName(strStoreName);
					vo.setStrMsgType("1");
					
						
		   
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
	
	
	/**
	 * This method is used to Cancel data into Table'
	 *  
	 * @param vo
	 */
	public synchronized static void cancel(TransferRequestTransVO vo) 
	{
		String   strProcName3 = "";
		int       nProcIndex3 = 0;
		HisDAO         daoObj = null;
		String   strStoreName = "";

		try 
		{
						
			 daoObj = new HisDAO("MMS", "TransferRequestTransDAO");
		       strProcName3 = "{call PKG_MMS_DML.dml_transfer_transreq_dtls(?,?,?,?,?,?,?)}";// 7 Varibale's
		        nProcIndex3 = daoObj.setProcedure(strProcName3);								
				daoObj.setProcInValue(nProcIndex3, "modval", "3");                             //1
				daoObj.setProcInValue(nProcIndex3, "store_id", vo.getStrStoreId());            //2
				daoObj.setProcInValue(nProcIndex3, "hospital_code", vo.getStrHospitalCode());  //3				
				daoObj.setProcInValue(nProcIndex3, "remarks",vo.getStrCancelReson());    	   //4					
				daoObj.setProcInValue(nProcIndex3, "seat_id",vo.getStrSeatId());               //5
				daoObj.setProcInValue(nProcIndex3, "req_No",vo.getStrRequestNo());             //6
				daoObj.setProcOutValue(nProcIndex3, "err", 1);                                 //7

				daoObj.execute(nProcIndex3, 1);

				strStoreName = vo.getStrStoreName();		     
			    				     
				daoObj.fire();     // Here we Execute in Batch
		     
		} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			vo.setStrMsgString("--> TransferRequestTransDAO.CANCEL()-->"+ e.getMessage());
			vo.setStrMsgType("1");
			vo.setStrStoreName(strStoreName);
		} 
    	finally 
    	{
			if (daoObj != null)
				daoObj.free();
			daoObj = null;
		}

	}


}
