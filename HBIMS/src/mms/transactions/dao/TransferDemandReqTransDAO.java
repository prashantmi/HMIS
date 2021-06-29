package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import mms.transactions.vo.TransferDemandReqTransVO;

public class TransferDemandReqTransDAO 
{
	
	
	
	
	
	/**
	 * The following procedure is used to populate the value of Item Name combo
	 * using Pkg_Mms_View.proc_storeitem_list procedure.
	 * 
	 * 
	 */

	public static void getTransferItemDtl(TransferDemandReqTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{			
			dao = new HisDAO("mms", "TransferDemandReqTransDAO");	
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_transitem_dtls(?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);
			
			dao.setProcInValue(nprocIndex, "modeval", "1");		
			dao.setProcInValue(nprocIndex, "strId",       vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "tarnsfer_No", vo.getStrRequestNo());
			dao.setProcInValue(nprocIndex, "hosp_code",   vo.getStrHospitalCode());
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
				if(wb.size()>0 && wb!=null)
				{
					vo.setWbTransferOrderDetail(wb);
				}
				else
				{
					throw new Exception("Web Row Set empty!!");
				}

			} 
			else
			{
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("TransferDemandReqTransDAO.getTransferItemDtl() --> "
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

	public static void getTransferDtl(TransferDemandReqTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{			
			dao = new HisDAO("mms", "TransferDemandReqTransDAO");			       
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
				if(wb.size()>0 && wb!=null)
				{
					vo.setWbTransferOrderDetail(wb);
					
				}
				else
				{
					throw new Exception("Web Row Set empty!!");
				}

			} 
			else
			{
				throw new Exception(strerr);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("TransferDemandReqTransDAO.getTransferDtl() --> "
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

	public static void getTransferOrderDtlForView(TransferDemandReqTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{
		
			dao = new HisDAO("mms", "TransferDemandReqTransDAO");			
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,?,?,?,?)}";
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
					vo.setWbTransferOrderDetail(wb);
				}
				 
			} 
			 
		} catch (Exception e) {
			vo.setStrMsgString("TransferDemandReqTransDAO.getTransferDtlForView() --> "
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

	public static void getTransferDtlForModify(TransferDemandReqTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{
		
			dao = new HisDAO("mms", "TransferDemandReqTransDAO");			
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_demand_dtls(?,?,?,?,?,?)}";
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
				if(wb.size()>0 && wb!=null)
				{
					vo.setStrModifyDtlWS(wb);
				}
				else
				{
					throw new Exception("Modify Web Row Set empty!!");
				}

			} 
			else
			{
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("TransferDemandReqTransDAO.getTransferDtlForModify() --> "
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

	public static void getTransferDtlForView(TransferDemandReqTransVO vo) 
	{

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try 
		{
		
			dao = new HisDAO("mms", "TransferDemandReqTransDAO");	
			strproc_name = "{call PKG_MMS_VIEW.proc_transfer_demand_dtls(?,?,?,?,?,?)}";
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
			vo.setStrMsgString("TransferDemandReqTransDAO.getTransferDtlForView() --> "
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

	public static void getItemName(TransferDemandReqTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		HisUtil hisutil = null;
		String str = null;
		try 
		{
			hisutil = new HisUtil("master", "TransferDemandReqTransDAO");
			dao = new HisDAO("mms", "TransferDemandReqTransDAO");
			strproc_name = "{call PKG_MMS_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);
//			System.out.println("group_id==>"+vo.getStrGroupId());
//			System.out.println("sub_group_id==>"+vo.getStrSubGroupId());
//			System.out.println("store_id==>"+vo.getStrStoreId());
//			System.out.println("cat_no==>"+vo.getStrItemCategoryNo());
//			System.out.println("hosp_code==>"+ vo.getStrHospitalCode());
			
			
			
			dao.setProcInValue(nprocIndex, "modeval", "5");
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
			vo.setStrMsgString("TransferDemandReqTransDAO.getItemName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	
	
	public static void GetGroupNameCombo(TransferDemandReqTransVO vo) 
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
			hisutil = new HisUtil("master", "TransferDemandReqTransDAO");
			dao = new HisDAO("mms","TransferDemandReqTransDAO.GetGroupNameCombo(TransferDemandReqTransVO vo)");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

            dao.setProcInValue(procIndex1, "modeval", "2");
			dao.setProcInValue(procIndex1, "item_category", vo.getStrItemCategoryNo());
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());
			/*start*/
			dao.setProcInValue(procIndex1, "strPhyStockNo","");
			dao.setProcInValue(procIndex1, "strStoreId", "");
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
					.setStrMsgString("TransferDemandReqTransDAO.GetGroupNameCombo() --> "
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
	 * Gets the sub group list.
	 * 
	 * @param vo the vo
	 * 
	 * @return the sub group list
	 */
	public static void getSubGroupList(TransferDemandReqTransVO vo) 
	{
		String err = "";
		String proc_name1 = "{call pkg_MMS_view.proc_store_subgroup_list(?,?,?,?,?)}";//4+1=5
		int procIndex1 = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		HisUtil hisutil = null;
		String str ="";
		try 
		{
			hisutil = new HisUtil("master", "TransferDemandReqTransDAO");
			dao = new HisDAO("TransferDemandReqTransDAO", "getSubGroupList");
			procIndex1 = dao.setProcedure(proc_name1);
			// set value
			dao.setProcInValue(procIndex1, "modeval", "1");
			dao.setProcInValue(procIndex1, "group_id", vo.getStrGroupId());
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());
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
			
			vo.setStrMsgString("TransferDemandReqTransDAO.getSubGroupList() --> "
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
	public static void getApprovedByCombo(TransferDemandReqTransVO  vo)
	
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
			hisutil = new HisUtil("master", "TransferDemandReqTransDAO");
			daoObj  = new HisDAO("MMSModule","TransferDemandReqTransDAO");
			
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
			vo.setStrMsgString("TransferDemandReqTransDAO.getApprovedByCombo() --> "
					+ _err.getMessage());
			vo.setStrMsgType("1");
		}
	}

	
	public synchronized static void insert(TransferDemandReqTransVO vo)
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
	  
			             daoObj = new HisDAO("MMS", "TransferDemandReqTransDAO");
			       strProcName3 = "{call PKG_MMS_DML.dml_transfer_demand_dtls(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";// 14 Varibale's
			        nProcIndex3 = daoObj.setProcedure(strProcName3);								
					daoObj.setProcInValue(nProcIndex3, "modval", "1");                  //1
					daoObj.setProcInValue(nProcIndex3, "store_id", vo.getStrStoreId()); //2
					daoObj.setProcInValue(nProcIndex3, "groupId",vo.getStrGroupId());  	     //3
					daoObj.setProcInValue(nProcIndex3, "hospital_code", vo.getStrHospitalCode());  //4
					daoObj.setProcInValue(nProcIndex3, "itemId","0");            	     //5
					daoObj.setProcInValue(nProcIndex3, "itemBrandId",vo.getStrItemBrandId().split("\\^")[0]); //6
					daoObj.setProcInValue(nProcIndex3, "demandPriority",vo.getStrReqStatus()); //7
					daoObj.setProcInValue(nProcIndex3, "reqQty",vo.getStrDemandedQty());    //8
					daoObj.setProcInValue(nProcIndex3, "reqQtyUnit","6301");      	 	    //9
					daoObj.setProcInValue(nProcIndex3, "approvBy",	vo.getStrApprovedBy()); 	 //10
					daoObj.setProcInValue(nProcIndex3, "appDate",	vo.getStrApprovedDate());    //11
					daoObj.setProcInValue(nProcIndex3, "remarks",vo.getStrRemarks());    	  	 //12					
					daoObj.setProcInValue(nProcIndex3, "seat_id",vo.getStrSeatId()); //13
					daoObj.setProcOutValue(nProcIndex3, "err", 1);                         //14

					daoObj.execute(nProcIndex3, 1);

					strStoreName = vo.getStrStoreName();

					daoObj.fire();		
			
		} 
	  catch (Exception e) 
	  {
			e.printStackTrace();			
			        vo.setStrMsgString("--> TransferDemandReqTransDAO.INSERT()-->"+ e.getMessage());
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
	
	
	public synchronized static void update(TransferDemandReqTransVO vo)
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
			             daoObj = new HisDAO("MMS", "TransferDemandReqTransDAO");
			       strProcName3 = "{call PKG_MMS_DML.dml_transfer_demand_dtls(?,?,?,?,?,?,?,?,?,?,?)}";// 11 Varibale's
			        nProcIndex3 = daoObj.setProcedure(strProcName3);								
					daoObj.setProcInValue(nProcIndex3, "modval", "2");                  //1
					daoObj.setProcInValue(nProcIndex3, "store_id", vo.getStrStoreId()); //2
					daoObj.setProcInValue(nProcIndex3, "hospital_code", vo.getStrHospitalCode());  //3
					daoObj.setProcInValue(nProcIndex3, "demandPriority",vo.getStrReqStatus()); //4
					daoObj.setProcInValue(nProcIndex3, "reqQty",vo.getStrDemandedQty());    //5
					daoObj.setProcInValue(nProcIndex3, "approvBy",	vo.getStrApprovedBy()); 	 //6
					daoObj.setProcInValue(nProcIndex3, "appDate",	vo.getStrApprovedDate());    //7
					daoObj.setProcInValue(nProcIndex3, "remarks",vo.getStrRemarks());    	  	 //8					
					daoObj.setProcInValue(nProcIndex3, "seat_id",vo.getStrSeatId()); //9
					daoObj.setProcInValue(nProcIndex3, "req_No",vo.getStrRequestNo()); //10
					daoObj.setProcOutValue(nProcIndex3, "err", 1);                   //11

					daoObj.execute(nProcIndex3, 1);

					strStoreName = vo.getStrStoreName();

					daoObj.fire();		
			
		} 
	  catch (Exception e) 
	  {
			e.printStackTrace();			
			        vo.setStrMsgString("--> TransferDemandReqTransDAO.UPDATE()-->"+ e.getMessage());
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
	public synchronized static void cancel(TransferDemandReqTransVO vo) 
	{
		String   strProcName3 = "";
		int       nProcIndex3 = 0;
		HisDAO         daoObj = null;
		String   strStoreName = "";

		try 
		{
			 daoObj = new HisDAO("MMS", "TransferDemandReqTransDAO");
		       strProcName3 = "{call PKG_MMS_DML.dml_transfer_demand_dtls(?,?,?,?,?,?,?)}";// 7 Varibale's
		        nProcIndex3 = daoObj.setProcedure(strProcName3);								
				daoObj.setProcInValue(nProcIndex3, "modval", "3");                  //1
				daoObj.setProcInValue(nProcIndex3, "store_id", vo.getStrStoreId()); //2
				daoObj.setProcInValue(nProcIndex3, "hospital_code", vo.getStrHospitalCode());  //3				
				daoObj.setProcInValue(nProcIndex3, "remarks",vo.getStrCancelReson());    	  	 //4					
				daoObj.setProcInValue(nProcIndex3, "seat_id",vo.getStrSeatId()); //5
				daoObj.setProcInValue(nProcIndex3, "req_No",vo.getStrRequestNo()); //6
				daoObj.setProcOutValue(nProcIndex3, "err", 1);                   //7

				daoObj.execute(nProcIndex3, 1);

				strStoreName = vo.getStrStoreName();		     
			    				     
				daoObj.fire();     // Here we Execute in Batch
		     
		} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			vo.setStrMsgString("--> TransferDemandReqTransDAO.CANCEL()-->"+ e.getMessage());
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
