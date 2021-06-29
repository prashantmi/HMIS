package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.EngineeringItemSubMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author   
 * Creation Date:- 11-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class EngineeringItemSubTypeMstDAO {
	/**
	 * for getting option value of parent parameter name combo on add page.
	 * 
	 * @param vo the vo
	 */

	public static void getEnggItemTypeCmb(EngineeringItemSubMstVO vo) {

		HisDAO   dao = null;
		WebRowSet wb = null;
		int nQryIndex;
		String strQuery;

		try {
			     dao = new HisDAO("bmed", "EngineeringItemSubTypeMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1, "enggItemSubTypeMst.engitemtype.add.cmb.1");
		   nQryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQryIndex, 2, "1");
			
			      wb = dao.executeQry(nQryIndex);

			vo.setEnggItemTypeCmbWS(wb);

		} catch (Exception e) {
			vo
			.setStrMsgString("--> EngineeringItemSubTypeMstDAO.getEnggItemTypeCmb()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null)
				dao.free();
			dao = null;
		}
	}



	/**
	 * Chk duplicate.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void chkDuplicate(EngineeringItemSubMstVO vo) {
		HisDAO      dao = null;
		int      ncount = 0;
		WebRowSet    wb = null;
		String strquery = new String();
		int nqryIndex;

		try {
			      dao = new HisDAO("bmed", "EngineeringItemSubTypeMstDAO");
			 strquery = bmed.qryHandler_bmed.getQuery(1,"enggItemSubTypeMst.insert.chkduplicate");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrEngItemSubTypeName());
			dao.setQryValue(nqryIndex, 2, vo.getStrEngItemTypeId());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			vo.setStrMsgType("0");
			      wb = dao.executeQry(nqryIndex);
			while (wb.next())
			{
				ncount = Integer.parseInt(wb.getString(1));
			}


			if (ncount == 0)
			{

				vo.setBExistStatus(false);
			}
			else
			{
				vo.setBExistStatus(true);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ItemMaintenanceMstDAO.chkDuplicate() --> "
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
	 * Chk duplicate for Modify.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void chkDuplicateUpdate(EngineeringItemSubMstVO vo) {
		HisDAO      dao = null;
		int      ncount = 0;
		WebRowSet    wb = null;
		String strquery = new String();
		int nqryIndex;

		try {
			      dao = new HisDAO("bmed", "EngineeringItemSubTypeMstDAO");
			 strquery = bmed.qryHandler_bmed.getQuery(1,"enggItemSubTypeMst.update.chkduplicate");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrEngItemSubTypeName());
			dao.setQryValue(nqryIndex, 2, vo.getStrEngItemTypeId());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 4, vo.getStrEngItemSubTypeId());
			vo.setStrMsgType("0");
			       wb = dao.executeQry(nqryIndex);
			while (wb.next())
			{
				ncount = Integer.parseInt(wb.getString(1));
			}


			if (ncount == 0)
			{

				vo.setBExistStatus(false);
			}
			else
			{
				vo.setBExistStatus(true);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ItemMaintenanceMstDAO.chkDuplicate() --> "
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
	 * to insert the data.
	 * 
	 * @param vo the vo
	 */

	public static void insertQuery(EngineeringItemSubMstVO vo) {

		int nProcIndex_U;
		HisDAO           dao = null;
		String strProcName_U = "";
		try 
		{
			dao = new HisDAO("bmed", "EngineeringItemSubTypeMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ENGGITEM_SUBTYPE_MST(?,?,?,?,?,?,?,?,?,?)}";  // Total 10 Values
			nProcIndex_U = dao.setProcedure(strProcName_U);
			dao.setProcInValue(nProcIndex_U, "p_mode","1"); 
			dao.setProcInValue(nProcIndex_U, "strEngItemSubTypeId",(vo.getStrEngItemSubTypeId() == null
					|| vo.getStrEngItemSubTypeId().equals("") ? "0" : vo.getStrEngItemSubTypeId()));
			dao.setProcInValue(nProcIndex_U, "strEngItemTypeId",(vo.getStrEnggItemTypeId() == null
					|| vo.getStrEnggItemTypeId().equals("") ? "0" : vo.getStrEnggItemTypeId())); 
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",(vo.getStrHospitalCode() == null
					|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode()));
			dao.setProcInValue(nProcIndex_U, "strEngItemSubTypeName",(vo.getStrEngItemSubTypeName() == null
					|| vo.getStrEngItemSubTypeName().equals("") ? "0" : vo.getStrEngItemSubTypeName()));
			dao.setProcInValue(nProcIndex_U, "strIsValid","1"); 
			dao.setProcInValue(nProcIndex_U, "strEffectiveDateFrom",(vo.getStrEffectiveFrom() == null
					|| vo.getStrEffectiveFrom().equals("") ? "0" : vo.getStrEffectiveFrom()));
			
			dao.setProcInValue(nProcIndex_U, "strRemarks",(vo.getStrRemarks() == null
					|| vo.getStrRemarks().equals("") ? "NA" : vo.getStrRemarks()));
			dao.setProcInValue(nProcIndex_U, "strSeatId",(vo.getStrSeatId() == null
					|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId())); 
			dao.setProcOutValue(nProcIndex_U, "err",1); 
			dao.executeProcedure(nProcIndex_U);

			vo.setStrMsgType("0");
			vo.setStrMsgType("0");

		}  
		catch (Exception e) 
		{
			//e.printStackTrace();
			vo.setStrMsgString("-->EngineeringItemSubTypeMstDAO.insert()-->"
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
	 * Modify.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void modify(EngineeringItemSubMstVO vo) 
	{
		HisDAO       dao = null;
		int  nQueryIndex = 0;
		int nQueryIndex1 = 0;
		int nQueryIndex2 = 0;
		WebRowSet    web = null;
		WebRowSet   web1 = null;
		WebRowSet   web2 = null;
		String strQuery,strQuery1,strQuery2;

		try
		{

			         dao = new HisDAO("bmed", "EngineeringItemSubTypeMstDAO");
			    strQuery = bmed.qryHandler_bmed.getQuery(1,"enggItemSubTypeMst.getEnggItemTypeName.1");
			 nQueryIndex = dao.setQuery(strQuery);

			//dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			 dao.setQryValue(nQueryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex, 2, vo.getStrEnggItemTypeId());
			         web = dao.executeQry(nQueryIndex);

			if (web.next())
			{


				vo.setStrEnggItemTypeCmb(web.getString(1));

			}

			   strQuery1 = bmed.qryHandler_bmed.getQuery(1,"enggItemSubTypeMst.getEnggItemTypeName.2");
			nQueryIndex1 = dao.setQuery(strQuery1);
			dao.setQryValue(nQueryIndex1, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex1, 2, vo.getStrEnggItemTypeId());
			dao.setQryValue(nQueryIndex1, 3, vo.getStrEngItemSubTypeId());
			        web1 = dao.executeQry(nQueryIndex1);

			if (web1.next())
			{

				vo.setStrEngItemSubTypeName(web1.getString(1));					
			}

			   strQuery2 = bmed.qryHandler_bmed.getQuery(1,"enggItemSubTypeMst.getEnggItemTypeName.3");
			nQueryIndex2 = dao.setQuery(strQuery2);

			dao.setQryValue(nQueryIndex2, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex2, 2, vo.getStrEngItemSubTypeId());
			        web2 = dao.executeQry(nQueryIndex2);

			if (web2.next())
			{
				vo.setStrRemarks(web2.getString(1));	
				vo.setStrEffectiveFrom(web2.getString(2));
			}

		} catch (Exception e) {
			//e.printStackTrace();
			vo.setStrMsgString("EngineeringItemSubMstDAO.modify() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;
		}
	}
	/**
	 * This function is used to update record during click of modify.
	 * 
	 * @param vo
	 *            the vo
	 */


	public static void updateRecord(EngineeringItemSubMstVO vo) 
	{
		HisDAO dao = null;
		int nProcIndex_U;
		String strProcName_U;
		try 
		{


			dao = new HisDAO("bmed", "EngineeringItemSubMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ENGGITEM_SUBTYPE_MST(?,?,?,?,?,?,?,?,?,?)}";  // Total 10 Values
			nProcIndex_U = dao.setProcedure(strProcName_U);
			dao.setProcInValue(nProcIndex_U, "p_mode","2");
			dao.setProcInValue(nProcIndex_U, "strEngItemSubTypeId",(vo.getStrEngItemSubTypeId() == null
					|| vo.getStrEngItemSubTypeId().equals("") ? "0" : vo.getStrEngItemSubTypeId())); 
			dao.setProcInValue(nProcIndex_U, "strEngItemTypeId",(vo.getStrEngItemTypeId() == null
					|| vo.getStrEngItemTypeId().equals("") ? "0" : vo.getStrEngItemTypeId())); 
			
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",(vo.getStrHospitalCode() == null
					|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode())); 
			dao.setProcInValue(nProcIndex_U, "strEngItemSubTypeName",(vo.getStrEngItemSubTypeName() == null
					|| vo.getStrEngItemSubTypeName().equals("") ? "0" : vo.getStrEngItemSubTypeName()));
			dao.setProcInValue(nProcIndex_U, "strIsValid",(vo.getStrIsValid() == null
					|| vo.getStrIsValid().equals("") ? "0" : vo.getStrIsValid()));
			
			dao.setProcInValue(nProcIndex_U, "strEffectiveDateFrom",(vo.getStrEffectiveFrom() == null
					|| vo.getStrEffectiveFrom().equals("") ? "0" : vo.getStrEffectiveFrom()));
			dao.setProcInValue(nProcIndex_U, "strRemarks",(vo.getStrRemarks() == null
					|| vo.getStrRemarks().equals("") ? "NA" : vo.getStrRemarks()));
			dao.setProcInValue(nProcIndex_U, "strSeatId",(vo.getStrSeatId() == null
					|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId())); 
			
			dao.setProcOutValue(nProcIndex_U, "err",1); 
			dao.executeProcedure(nProcIndex_U);
			vo.setStrMsgType("0");

		}  
		catch (Exception e) 
		{
			//e.printStackTrace();
			vo.setStrMsgString("-->EngineeringItemSubMstDAO.insert()-->"
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
