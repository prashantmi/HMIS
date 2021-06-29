package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.MaintenanceMstVO;
import hisglobal.transactionmgnt.HisDAO;


/**
 * @author   Vivek Aggarwal
 * Creation Date:- 19-Jan-2011 
 * Modifying Date:- 25-Jan-2011
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class MaintenanceMstDAO {
	
	
	/**
	 * to get Engineering Item Type Combo Value.
	 * 
	 * @param vo the vo	 
	 */
	public static void getEnggItemTypeCmbValues(MaintenanceMstVO vo)
	{
		HisDAO dao = null;
		int nqryIndex;
		String strQuery = "";
		WebRowSet wb = null;

		try {
			dao = new HisDAO("bmed", "MaintenanceMstDAO");

			strQuery = bmed.qryHandler_bmed.getQuery(1,"maintenanceMst.enggItemtype.combo.0");
			nqryIndex = dao.setQuery(strQuery);
			
			dao.setQryValue(nqryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);
			
			
			wb = dao.executeQry(nqryIndex);
			
			vo.setStrEngineeringItemTypeWS(wb);
			
		}
		catch (Exception e) 
		{
			vo.setStrMsgString("--> MaintenanceMstDAO.getEnggItemTypeCmbValues()-->"
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
	 * to get Engineering Item Sub Type Combo Value.
	 * 
	 * @param vo the vo	 
	 */
	public static void getEngineeringItemSubTypeCmbValues(MaintenanceMstVO vo) 
	{

		HisDAO dao = null;
		int nqryIndex;
		String strQuery;
		WebRowSet wb = null;

		try {
			dao = new HisDAO("bmed", "MaintenanceMstDAO");

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"maintenanceMst.enggItemSubtype.combo.add.1");
			nqryIndex = dao.setQuery(strQuery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrEngineeringItemTypeId());
						
			wb = dao.executeQry(nqryIndex);
			
			vo.setStrEngineeringItemSubTypeWS(wb);
			
		} 
		catch (Exception e)
		{
			vo.setStrMsgString("--> MaintenanceMstDAO.getEnggItemTypeCmbValues(vo)-->"	+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally
		{
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}

	
	/**
	 * Check duplicate while inserting records
	 * 
	 * @param vo the vo
	 * @param strInsertUpdate the String variable used to catch the parameter to know whether duplicacy is checked for inserting a record or updating it
	 */
	public static void chkDuplicate(MaintenanceMstVO vo,String strInsertUpdate) 
	{
		HisDAO dao = null;
		int nqryIndex = 0;
		int ncount = 0;
		WebRowSet wb ;
		String strquery;

		try {
			dao = new HisDAO("bmed", "MaintenanceMstDAO");
			
			if(strInsertUpdate.equals("insert"))
			{
			strquery = bmed.qryHandler_bmed.getQuery(1,"maintenanceMst.insert.chkduplicate");
			nqryIndex = dao.setQuery(strquery);
			
			
					System.out.println("1--"+vo.getStrEngineeringItemTypeId());
					System.out.println("2---"+vo.getStrEngineeringItemSubTypeId());
					System.out.println("3---"+vo.getStrMaintenanceName());
					System.out.println("4---"+vo.getStrHospitalCode());
					
					dao.setQryValue(nqryIndex, 1, vo.getStrEngineeringItemTypeId());
					dao.setQryValue(nqryIndex, 2, vo.getStrEngineeringItemSubTypeId());
					
					dao.setQryValue(nqryIndex, 3, vo.getStrMaintenanceName());
										
					dao.setQryValue(nqryIndex, 4, vo.getStrHospitalCode());
			}
			
			else if(strInsertUpdate.equals("update"))
			{
				strquery = bmed.qryHandler_bmed.getQuery(1,"maintenanceMst.update.chkduplicate");
				nqryIndex = dao.setQuery(strquery);
						
						dao.setQryValue(nqryIndex, 1, vo.getStrMaintenanceId());
						dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
						
						dao.setQryValue(nqryIndex, 3, vo.getStrEngineeringItemTypeId());
						dao.setQryValue(nqryIndex, 4, vo.getStrEngineeringItemSubTypeId());
						
						dao.setQryValue(nqryIndex, 5, vo.getStrMaintenanceName());	
						dao.setQryValue(nqryIndex, 6, vo.getStrHospitalCode());
			}	

					vo.setStrMsgType("0");
					
			wb = dao.executeQry(nqryIndex);
			
			while (wb.next())
			{
				ncount = Integer.parseInt(wb.getString(1));	
			}
			if (ncount == 0)
			{

				vo.setBExistStatus(true);	// no duplicates found
			}
			else
			{
				vo.setBExistStatus(false);	// There is a duplicate record found
			}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("MaintenanceMstDAO.chkDuplicate() --> "	+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
			wb=null;

		}
	}	
	
	
	/**
	 * to insert the data into the table HEMT_ENGG_MAINTENANCE_MST
	 * 
	 * @param vo
	 */
	public static void insertValues(MaintenanceMstVO vo) {
		
		HisDAO dao = null;

		int nProcIndex_U;
		
		String strProcName_U;
		

	try 
	{
		dao = new HisDAO("bmed", "MaintenanceMstDAO");

		strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ENGG_MAINTENANCE_MST(?,?,?,?,?,?,?,?,?,?,?)}";  // Total 11 Values
		nProcIndex_U = dao.setProcedure(strProcName_U);
		
		
		dao.setProcInValue(nProcIndex_U, "p_mode","1"); 
		dao.setProcInValue(nProcIndex_U, "strEngineeringItemSubTypeId",(vo.getStrEngineeringItemSubTypeId()==null || vo.getStrEngineeringItemSubTypeId().equals("")?"0":vo.getStrEngineeringItemSubTypeId()));
		dao.setProcInValue(nProcIndex_U, "strEngineeringItemTypeId",(vo.getStrEngineeringItemTypeId()==null || vo.getStrEngineeringItemTypeId().equals("")?"0":vo.getStrEngineeringItemTypeId())); 
		dao.setProcInValue(nProcIndex_U, "strHospitalCode",(vo.getStrHospitalCode()==null ||vo.getStrHospitalCode().equals("")?"0":vo.getStrHospitalCode()));
		dao.setProcInValue(nProcIndex_U, "strIsValid","1"); 
		
		dao.setProcInValue(nProcIndex_U, "strMaintenanceName",(vo.getStrMaintenanceName()==null || vo.getStrMaintenanceName().equals("")?"0":vo.getStrMaintenanceName())); 
		dao.setProcInValue(nProcIndex_U, "strMaintenanceId","0");
		
		dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",(vo.getStrEffectiveFrom()==null || vo.getStrEffectiveFrom().equals("")?"0":vo.getStrEffectiveFrom()));		
		dao.setProcInValue(nProcIndex_U, "strRemarks",(vo.getStrRemarks()==null || vo.getStrRemarks().equals("")?"NA":vo.getStrRemarks())); 
		
		dao.setProcInValue(nProcIndex_U, "strSeatId",(vo.getStrSeatId()==null || vo.getStrSeatId().equals("")?"0":vo.getStrSeatId())); 
		
		/*Default Values*/
		
		
		/*Default Value End*/
		dao.setProcOutValue(nProcIndex_U, "err",1); 

		dao.executeProcedure(nProcIndex_U);
			
			 vo.setStrMsgType("0");
			 
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
		    vo.setStrMsgString("--> MaintenanceMstDAO.insertQuery()-->"
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
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 */
	public static void modifyRecord(MaintenanceMstVO vo) {
		
		HisDAO dao = null;
		int nqryIndex = 0;

		String strQuery;

		WebRowSet web = null;

		try {

			dao = new HisDAO("bmed", "TaskMstDAO");

			// Getting the Engineering Item Type Combo on the modify page

			strQuery = bmed.qryHandler_bmed.getQuery(1, "maintenanceMst.modify");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrMaintenanceId());

			web = dao.executeQry(nqryIndex);
			if (web.next()) {
				vo.setStrEngineeringItemTypeName(web.getString(1));
				vo.setStrEngineeringItemSubTypeName(web.getString(2));
				vo.setStrMaintenanceName(web.getString(3));
				vo.setStrEffectiveFrom(web.getString(4));
				vo.setStrRemarks(web.getString(5));
				vo.setStrIsValid(web.getString(6));
				vo.setStrEngineeringItemTypeId(web.getString(7));
				vo.setStrEngineeringItemSubTypeId(web.getString(8));
				
			}
			web.close();
		}

		catch (Exception e) {
			vo.setStrMsgString("--> TaskMstDAO.modifyRecord()-->"
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
	 * to update the record in table HEMT_ENGG_MAINTENANCE_MST
	 * 
	 * @param vo the vo
	 * 
	 * @throws Exception 	 */
	public static void updateQuery(MaintenanceMstVO vo)
	{

		HisDAO dao = null;

		int nProcIndex_U;
		
		String strProcName_U;
		

	try 
	{
		dao = new HisDAO("bmed", "MaintenanceMstDAO");
		strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ENGG_MAINTENANCE_MST(?,?,?,?,?,?,?,?,?,?,?)}";  // Total 11 Values
		nProcIndex_U = dao.setProcedure(strProcName_U);
		
		
		dao.setProcInValue(nProcIndex_U, "p_mode","2"); 
		dao.setProcInValue(nProcIndex_U, "strEngineeringItemSubTypeId",(vo.getStrEngineeringItemSubTypeId()==null || vo.getStrEngineeringItemSubTypeId().equals("")?"0":vo.getStrEngineeringItemSubTypeId()));
		dao.setProcInValue(nProcIndex_U, "strEngineeringItemTypeId",(vo.getStrEngineeringItemTypeId()==null || vo.getStrEngineeringItemTypeId().equals("")?"0":vo.getStrEngineeringItemTypeId())); 
		dao.setProcInValue(nProcIndex_U, "strHospitalCode",(vo.getStrHospitalCode()==null || vo.getStrHospitalCode().equals("")?"0":vo.getStrHospitalCode()));
		dao.setProcInValue(nProcIndex_U, "strIsValid",(vo.getStrIsValid()==null || vo.getStrIsValid().equals("")?"0":vo.getStrIsValid())); 
		dao.setProcInValue(nProcIndex_U, "strMaintenanceName",(vo.getStrMaintenanceName()==null || vo.getStrMaintenanceName().equals("")?"0":vo.getStrMaintenanceName())); 
		dao.setProcInValue(nProcIndex_U, "strMaintenanceId",(vo.getStrMaintenanceId()==null || vo.getStrMaintenanceId().equals("")?"0":vo.getStrMaintenanceId()));
		dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",(vo.getStrEffectiveFrom()==null || vo.getStrEffectiveFrom().equals("")?"0":vo.getStrEffectiveFrom()));
		dao.setProcInValue(nProcIndex_U, "strRemarks",(vo.getStrRemarks()==null || vo.getStrRemarks().equals("")?"NA":vo.getStrRemarks()));
		
		dao.setProcInValue(nProcIndex_U, "strSeatId",(vo.getStrSeatId()==null || vo.getStrSeatId().equals("")?"0":vo.getStrSeatId())); 
		
		
		dao.setProcOutValue(nProcIndex_U, "err",1); 

		dao.executeProcedure(nProcIndex_U);
		
			 vo.setStrMsgType("0");
			 
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
		    vo.setStrMsgString("--> MaintenanceMstDAO.updateQuery()-->"
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

}