/**
 * 
 */
package mms.reports.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;


import mms.MmsConfigUtil;
import mms.reports.vo.ListItemWiseSupplierRptVO;
import mms.reports.vo.ReturnDetailRptVO;

/**
 * @author user
 *
 */
public class ListItemWiseSupplierRptDAO {
	

	/**
	 * for getting option value of Item Category Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void itemCategoryName(ListItemWiseSupplierRptVO vo)
	{
		String strProcName = "{call Pkg_Mms_Rpt.Rptm_item_category_list(?,?,?,?,?)}";
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Item Location","ListItemWiseSupplierRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", MmsConfigUtil.GLOBAL_HOSPITAL_CODE,2);
			daoObj.setProcInValue(nProcIndex, "storeid", "",3);
			daoObj.setProcOutValue(nProcIndex, "err",1,4); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setItemCategoryWS(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ListItemWiseSupplierRptDAO.itemCategoryName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * for getting option value of Group Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void groupName(ListItemWiseSupplierRptVO vo)
	{
		String strProcName = "{call Pkg_Mms_Rpt.rptm_store_group_list(?,?,?,?,?)}";
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Item Location","ListItemWiseSupplierRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", MmsConfigUtil.GLOBAL_HOSPITAL_CODE,2);
			daoObj.setProcInValue(nProcIndex, "item_category", vo.getStrItemCategoryNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err",1,4); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setGroupIdWS(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ListItemWiseSupplierRptDAO.groupName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * The following procedure is used to populate the value of Supplier Name
	 * combo using Pkg_Mms_View.proc_supplier_list procedure.
	 * 
	 * 
	 */

	public static void getSuppliedByList(ListItemWiseSupplierRptVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("mms", "ListItemWiseSupplierRptDAO");
		//	dao.setConnType("2");
			strproc_name = "{call PKG_MMS_VIEW.proc_supplier_list(?,?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeVal",  vo.getStrMode(),1);
			dao.setProcInValue(nprocIndex, "catCode", "10",2);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),5);
			
			/* Start */
			dao.setProcInValue(nprocIndex, "branditem_id",  vo.getStrSeatId(),3);
			dao.setProcInValue(nprocIndex, "contractType", "0",4);
			/* End */
			
			dao.setProcOutValue(nprocIndex, "err", 1,6);
			dao.setProcOutValue(nprocIndex, "resultset", 2,7);
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
			vo.setStrMsgString("DrugInventoryTransDAO.getSuppliedByList() --> "
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
	 * for getting option value of SubGroup Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void subGroupName(ListItemWiseSupplierRptVO vo)
	{
		String strProcName = "{call Pkg_Mms_Rpt.rptm_store_subgroup_list(?,?,?,?,?)}";
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Item Location","ListItemWiseSupplierRptDAO");
		//	daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "groupId", vo.getStrGroupId(),2);
			daoObj.setProcOutValue(nProcIndex, "err",1,4); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setSubGroupIdWS(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ListItemWiseSupplierRptDAO.subGroupName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/**
	 * for getting option value of Generic Item Name on page
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public static void ItemName(ListItemWiseSupplierRptVO vo)
	{
		String strProcName = "{call Pkg_Mms_Rpt.rptm_itembrand_list(?,?,?,?,?,?,?)}";
		
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj=null;
		
		try
		{
			daoObj=new HisDAO("Item Location","ListItemWiseSupplierRptDAO");
			//daoObj.setConnType("2");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			//System.out.println( "modeval"+ "1");
			//System.out.println( "hosp_code"+ vo.getStrHospitalCode());
			//System.out.println( "catCode"+ vo.getStrItemCategoryNo());
			//System.out.println( "groupid"+ vo.getStrGroupId());
			//System.out.println( "subgrpid"+vo.getStrSubGroupId());
			
			
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", MmsConfigUtil.GLOBAL_HOSPITAL_CODE,5);
			daoObj.setProcInValue(nProcIndex, "catCode", vo.getStrItemCategoryNo(),2);
			daoObj.setProcInValue(nProcIndex, "groupid", vo.getStrGroupId(),3);
			daoObj.setProcInValue(nProcIndex, "subgrpid", vo.getStrSubGroupId(),4);
			daoObj.setProcOutValue(nProcIndex, "err",1,6); 
			daoObj.setProcOutValue(nProcIndex, "resultset",2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setItemIdWS(ws);
			} else {
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("ListItemWiseSupplierRptDAO.ItemName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public static void getHospitalName(ListItemWiseSupplierRptVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_rpt.RPT_GET_HOSPITAL_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;	
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("MMS","ListItemWiseSupplierRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue( nProcIndex, "modeval","1",1);
			daoObj.setProcInValue( nProcIndex, "hosp_code",voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue( nProcIndex, "seatId",voObj.getStrSeatId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrHospitalWs(ws);				
				
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("ItemWiseConsumptionRptDAO.getHospitalName() --> "+ e.getMessage());
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
	


}
