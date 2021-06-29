package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.ComplaintEscalationMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author   Amit kr
 * Creation Date:- 17-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class ComplaintEscalationMstDAO 
{
	/**
	 * Gets the Service Name.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public static void getServiceEmpNameCmb(ComplaintEscalationMstVO vo) 
	{
		HisDAO dao = null;
		String strquery = "";
		int nqryIndex = 0;
		WebRowSet web = null;

		try
		{
				dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
				strquery = bmed.qryHandler_bmed.getQuery(1,"complaintEscalationMst.empName.combo.1");
				nqryIndex = dao.setQuery(strquery);
				
				dao.setQryValue(nqryIndex, 1, "1");
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
				
				web = dao.executeQry(nqryIndex);
				
				vo.setWsEnggNameCmb(web);
				vo.setStrMsgType("0");
					
				
				

		} catch (Exception e) {
			
			vo.setStrMsgString("ComplaintEscalationMstDAO.getServiceEnggNameCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}
	
	/**
	 * Gets Unit Combo.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public static void getUnitCmb(ComplaintEscalationMstVO vo) 
	{
		HisDAO dao = null;
		String strquery = "";
		int nqryIndex = 0;
		WebRowSet web = null;

		try
		{
				dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
				strquery = bmed.qryHandler_bmed.getQuery(1,"itemMaintenanceMst.unitName.combo.5");
				nqryIndex = dao.setQuery(strquery);
				
				dao.setQryValue(nqryIndex, 1, vo.getStrIsValid());
				dao.setQryValue(nqryIndex, 2, Config.SUPER_USER_HOSPITAL_CODE);
				
				web = dao.executeQry(nqryIndex);
				vo.setWsUnitName(web);
					
					
				
				

		} catch (Exception e) {
			
			vo.setStrMsgString("ComplaintEscalationMstDAO.getMaintenanceNameCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}
	
	/**
	 * Gets Level Type Combo.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public static void getLevelTypeCmb(ComplaintEscalationMstVO vo) 
	{
		HisDAO dao = null;
		String strquery = "";
		int nqryIndex = 0;
		WebRowSet web = null;

		try
		{
				dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
				strquery = bmed.qryHandler_bmed.getQuery(1,"complaintEscalationMst.levelType.combo.1");
				nqryIndex = dao.setQuery(strquery);
				
				
				dao.setQryValue(nqryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);
				
				web = dao.executeQry(nqryIndex);
				
				vo.setWsLevelTypeCmb(web);
					
					
				
				

		} catch (Exception e) {
			
			vo.setStrMsgString("ComplaintEscalationMstDAO.getLevelTypeCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}
	
 

	
	/**
	 * Get Add page Component details.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getAddPageComponent(ComplaintEscalationMstVO vo) 
	{
		HisDAO dao = null;
		String strQuery2,strQuery3;
		
		int nQueryIndex2 = 0;
		int nQueryIndex3 = 0;
		
		WebRowSet web2 = null;
		WebRowSet web3 = null;
		try
		{

			dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
						
			strQuery2 = bmed.qryHandler_bmed.getQuery(1,"itemMaintenanceMst.getEnggItemTypeName.3");
			nQueryIndex2 = dao.setQuery(strQuery2);
			
			dao.setQryValue(nQueryIndex2, 1, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nQueryIndex2, 2, vo.getStrEnggItemTypeId());
			web2 = dao.executeQry(nQueryIndex2);

			if (web2.next())
			{
				vo.setStrEnggItemTypeCmb(web2.getString(1));				
			}
			
			
			strQuery3 = bmed.qryHandler_bmed.getQuery(1,"itemMaintenanceMst.getEnggItemSubTypeName.4");
			nQueryIndex3 = dao.setQuery(strQuery3);
			
			dao.setQryValue(nQueryIndex3, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex3, 2, vo.getStrEnggItemTypeId());
			dao.setQryValue(nQueryIndex3, 3, vo.getStrEnggItemSubTypeId());
			web3 = dao.executeQry(nQueryIndex3);

			if (web3.next())
			{					
				vo.setStrEnggItemSubTypeCmb(web3.getString(1));
								
			}
			

		} catch (Exception e) {
//			e.printStackTrace();
			vo.setStrMsgString("ComplaintEscalationMstDAO.modify() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web2 = null;
			web3 = null;
		}
	}
	
	
	/**
	 * Get Modify page data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getModifyPageData(ComplaintEscalationMstVO vo) 
	{
		HisDAO dao = null;
		String strQuery2,strQuery3;
		
		int nQueryIndex2 = 0;
		int nQueryIndex3 = 0;
		
		WebRowSet web2 = null;
		WebRowSet web3 = null;
	
		try
		{

			dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
						
			
			
			
			strQuery2 = bmed.qryHandler_bmed.getQuery(1,"complaintEscalationMst.getModify.dtl.1");
			nQueryIndex2 = dao.setQuery(strQuery2);
			
			dao.setQryValue(nQueryIndex2, 1, vo.getStrEmpNo());
			dao.setQryValue(nQueryIndex2, 2, vo.getStrEnggItemTypeId());
			dao.setQryValue(nQueryIndex2, 3, vo.getStrEnggItemSubTypeId());
			dao.setQryValue(nQueryIndex2, 4, vo.getStrLevelType());
			dao.setQryValue(nQueryIndex2, 5, vo.getStrHospitalCode());
			
			web2 = dao.executeQry(nQueryIndex2);
			if (web2.next())
			{					
				vo.setStrLevelTypeCmb(web2.getString(1));
				vo.setStrPeriod(web2.getString(2));
				vo.setStrUnitId(web2.getString(3));
				vo.setStrRemarks(web2.getString(4));	
				
			}
			
			
			strQuery3 = bmed.qryHandler_bmed.getQuery(1,"complaintEscalationMst.getEmpName.3");
			nQueryIndex3 = dao.setQuery(strQuery3);
			
			dao.setQryValue(nQueryIndex3, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex3, 2, vo.getStrEmpNo());
			web3 = dao.executeQry(nQueryIndex3);

			if (web3.next())
			{					
				vo.setStrEmpNameCmb(web3.getString(1));
								
			}

		} catch (Exception e) {
//			e.printStackTrace();
			vo.setStrMsgString("ComplaintEscalationMstDAO.modify() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web2 = null;
		
		}
	}
	
	/**
	 * To getEmpInfo .
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getEmpInfo(ComplaintEscalationMstVO vo) 
	{
		HisDAO dao = null;
		String strQuery2;
		
		int nQueryIndex2 = 0;
		int nQueryIndex3 = 0;
		
		WebRowSet web2 = null;
		
		try
		{

			dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
						
			strQuery2 = bmed.qryHandler_bmed.getQuery(1,"complaintEscalationMst.empInfo.dtl.1");
			nQueryIndex2 = dao.setQuery(strQuery2);
			dao.setQryValue(nQueryIndex3, 1, "1");
			dao.setQryValue(nQueryIndex2, 2, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex2, 3, vo.getStrEmpNo());
			web2 = dao.executeQry(nQueryIndex2);

			if (web2.next())
			{
				vo.setStrEmpInfo(web2.getString(1));				
			}
			vo.setStrMsgType("0");
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			vo.setStrMsgString("ComplaintEscalationMstDAO.getEmpInfo() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web2 = null;
			
		}
	}
	
	/**
	 * to insert the data into Table HEMT_COMP_ESCALATION_MST.
	 * 
	 * @param vo
	 *            the vo
	 */
	 	public static void insertData(ComplaintEscalationMstVO vo) 
	 	{
			HisDAO dao = null;
			int nProcIndex_U;
			String strProcName_U = "";
			//String strTemp[] = null; 

		try 
		{
				//	System.out.println("inside dao");
					dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
			       strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_COMP_ESCALATION_MST(?,?,?,?,?,?,?,?,?,?,?,?)}";  // Total 12 Values
					
					nProcIndex_U = dao.setProcedure(strProcName_U);
					
					dao.setProcInValue(nProcIndex_U, "p_mode","1"); 
					dao.setProcInValue(nProcIndex_U, "strEmpNameId",(vo.getStrEmpNameId() == null
							|| vo.getStrEmpNameId().equals("") ? "0" : vo.getStrEmpNameId())); 
					dao.setProcInValue(nProcIndex_U, "strEnggItemTypeId",(vo.getStrEnggItemTypeId() == null
							|| vo.getStrEnggItemTypeId().equals("") ? "0" : vo.getStrEnggItemTypeId())); 
					dao.setProcInValue(nProcIndex_U, "strEnggItemSubTypeId",(vo.getStrEnggItemSubTypeId() == null
							|| vo.getStrEnggItemSubTypeId().equals("") ? "0" : vo.getStrEnggItemSubTypeId())); 
					dao.setProcInValue(nProcIndex_U, "strLevelType",(vo.getStrLevelType() == null
							|| vo.getStrLevelType().equals("") ? "0" : vo.getStrLevelType()));
					dao.setProcInValue(nProcIndex_U, "strPeriod",(vo.getStrPeriod() == null
							|| vo.getStrPeriod().equals("") ? "0" : vo.getStrPeriod()));
					dao.setProcInValue(nProcIndex_U, "strUnitId",(vo.getStrUnitId() == null
							|| vo.getStrUnitId().equals("") ? "0" : vo.getStrUnitId()));
					dao.setProcInValue(nProcIndex_U, "strHospitalCode",(vo.getStrHospitalCode() == null
							|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode())); 	
					dao.setProcInValue(nProcIndex_U, "strRemarks",(vo.getStrRemarks() == null
							|| vo.getStrRemarks().equals("") ? "NA" : vo.getStrRemarks()));					
					dao.setProcInValue(nProcIndex_U, "strSeatId",(vo.getStrSeatId() == null
							|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId())); 
					dao.setProcInValue(nProcIndex_U, "strIsValid","1");
					dao.setProcOutValue(nProcIndex_U, "err",1); 
					dao.executeProcedure(nProcIndex_U);
				
			vo.setStrMsgType("0");			
			
		}  
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("-->ComplaintEscalationMstDAO.insertData()-->"
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
		 * Chk duplicate.
		 * 
		 * @param vo
		 *            the vo
		 */
		public static void chkDuplicate(ComplaintEscalationMstVO vo) {
			HisDAO dao = null;
			int nqryIndex;
			int ncount = 0;
			WebRowSet wb = null;
			String strquery;
			
			try 
			{
				dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
				strquery = bmed.qryHandler_bmed.getQuery(1,"complaintEscalationMst.insert.chkduplicate");
				nqryIndex = dao.setQuery(strquery);
		
				dao.setQryValue(nqryIndex, 1, vo.getStrEmpNameId());
				dao.setQryValue(nqryIndex, 2, vo.getStrEnggItemTypeId());
				dao.setQryValue(nqryIndex, 3, vo.getStrEnggItemSubTypeId());
				dao.setQryValue(nqryIndex, 4, vo.getStrLevelType());
				dao.setQryValue(nqryIndex, 5, vo.getStrHospitalCode());
				
																	
				wb = dao.executeQry(nqryIndex);
				
				while (wb.next())
				{					
					ncount = Integer.parseInt(wb.getString(1));
					System.out.println("ncount--"+ncount);
				}
				
				if (ncount == 0)
				{
                    
					vo.setBExistStatus(true);
				}
				else
				{					
					vo.setBExistStatus(false);
				}
				vo.setStrMsgType("0");
			} catch (Exception e)
			{
				
				vo.setStrMsgString("ComplaintEscalationMstDAO.chkDuplicate() --> "
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
		 * to Update the data in table HEMT_COMP_ESCALATION_MST.
		 * 
		 * @param vo
		 *            the vo
		 */
		 	public static void updateRecord(ComplaintEscalationMstVO vo) 
		 	{
				HisDAO dao = null;
				int nProcIndex_U;
				String strProcName_U = "";
				

			try 
			{
				dao = new HisDAO("bmed", "ComplaintEscalationMstDAO");
				strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_COMP_ESCALATION_MST(?,?,?,?,?,?,?,?,?,?,?,?)}";  // Total 12 Values
		
						
						nProcIndex_U = dao.setProcedure(strProcName_U);
						
						dao.setProcInValue(nProcIndex_U, "p_mode","2"); 
						dao.setProcInValue(nProcIndex_U, "strEmpNameId",(vo.getStrEmpNo() == null
								|| vo.getStrEmpNo().equals("") ? "0" : vo.getStrEmpNo())); 
						dao.setProcInValue(nProcIndex_U, "strEnggItemTypeId",(vo.getStrEnggItemTypeId() == null
								|| vo.getStrEnggItemTypeId().equals("") ? "0" : vo.getStrEnggItemTypeId())); 
						dao.setProcInValue(nProcIndex_U, "strEnggItemSubTypeId",(vo.getStrEnggItemSubTypeId() == null
								|| vo.getStrEnggItemSubTypeId().equals("") ? "0" : vo.getStrEnggItemSubTypeId())); 
						dao.setProcInValue(nProcIndex_U, "strLevelType",(vo.getStrLevelType() == null
								|| vo.getStrLevelType().equals("") ? "0" : vo.getStrLevelType()));
												
						dao.setProcInValue(nProcIndex_U, "strPeriod",(vo.getStrPeriod() == null
								|| vo.getStrPeriod().equals("") ? "0" : vo.getStrPeriod()));
						dao.setProcInValue(nProcIndex_U, "strUnitId",(vo.getStrUnitId() == null
								|| vo.getStrUnitId().equals("") ? "0" : vo.getStrUnitId()));
						dao.setProcInValue(nProcIndex_U, "strHospitalCode",(vo.getStrHospitalCode() == null
								|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode())); 
							
						dao.setProcInValue(nProcIndex_U, "strRemarks",(vo.getStrRemarks() == null
								|| vo.getStrRemarks().equals("") ? "NA" : vo.getStrRemarks()));					
						dao.setProcInValue(nProcIndex_U, "strSeatId",(vo.getStrSeatId() == null
								|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId())); 
						dao.setProcInValue(nProcIndex_U, "strIsValid",(vo.getStrIsValid() == null
								|| vo.getStrIsValid().equals("") ? "0" : vo.getStrIsValid()));
						dao.setProcOutValue(nProcIndex_U, "err",1); 
						dao.executeProcedure(nProcIndex_U);
									
				vo.setStrMsgType("0");			
				
			}  
			catch (Exception e) 
			{
				e.printStackTrace();
				vo.setStrMsgString("-->ComplaintEscalationMstDAO.insertData()-->"
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
