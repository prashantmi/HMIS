package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.ServiceEngineerMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author  Amit Kumar 
 * Creation Date:- 17-Jan-2011 
 * Modifying Date:- 0-0-2011
 * Used For:-	
 * Team Lead By:- 
 * Module:- BMED(HIS Project)
 */
public class ServiceEngineerMstDAO 
{
	
	/**
	 * Gets the component name.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public static void getAddPageComponent(ServiceEngineerMstVO vo) 
	{
		HisDAO       dao = null;
		int nQueryIndex2 = 0;
		int nQueryIndex3 = 0;
		WebRowSet   web2 = null;
		WebRowSet   web3 = null;
		String strQuery2,strQuery3;
		try
		{	
			         dao = new HisDAO("bmed", "ServiceEngineerMstDAO");
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
			vo.setStrMsgType("0");
			
		} catch (Exception e) {
			
			vo.setStrMsgString("ServiceEngineerMstDAO.getAddPageComponent() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			

		}
	}
	
	
	/**
	 * Gets the component name.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public static void getServiceEnggNameCmb(ServiceEngineerMstVO vo) 
	{
		HisDAO    dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try
		{
				      dao = new HisDAO("bmed", "ServiceEngineerMstDAO");
				 strquery = bmed.qryHandler_bmed.getQuery(1,"serviceEnggMst.enggName.combo.1");
				nqryIndex = dao.setQuery(strquery);
				
				dao.setQryValue(nqryIndex, 1, "1");
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
				dao.setQryValue(nqryIndex, 3, vo.getStrEnggItemTypeId());
				dao.setQryValue(nqryIndex, 4, vo.getStrEnggItemSubTypeId());
				
				
				      web = dao.executeQry(nqryIndex);
				
				vo.setWsEnggNameCmb(web);
				vo.setStrMsgType("0");
					
				
				

		} catch (Exception e) {
			
			vo.setStrMsgString("ServiceEngineerMstDAO.getServiceEnggNameCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}
	
	
	/**
	 * Gets the component name.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public static void getServiceEnggNameHlp(ServiceEngineerMstVO vo) 
	{
		HisDAO      dao = null;
		String strquery = "";
		int   nqryIndex = 0;
		WebRowSet   web = null;

		try
		{
				      dao = new HisDAO("bmed", "ServiceEngineerMstDAO");
				 strquery = bmed.qryHandler_bmed.getQuery(1,"serviceEnggMst.enggName.hlp.1");
				nqryIndex = dao.setQuery(strquery);
				
//				System.out.println("Enff Item type:::"+vo.getStrEnggItemTypeId());
//				System.out.println("Enff Item Sub type:::"+vo.getStrEnggItemSubTypeId());
//				System.out.println("Hosp Code::::"+vo.getStrHospitalCode());
//				System.out.println("Emp Id::::"+vo.getStrEmpId());
				
				dao.setQryValue(nqryIndex, 1, vo.getStrIsValid());
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
				dao.setQryValue(nqryIndex, 3, vo.getStrEnggItemTypeId());
				dao.setQryValue(nqryIndex, 4, vo.getStrEnggItemSubTypeId());
				dao.setQryValue(nqryIndex, 5, vo.getStrEmpId());
				
				
				     web = dao.executeQry(nqryIndex);
				
				while(web.next())
				{
					vo.setStrRemarks(web.getString(2));
					vo.setStrRemarks(web.getString(2));
					vo.setStrEffectiveFrom(web.getString(3));
				}
				web.beforeFirst();
				vo.setWsEnggNameHlp(web);
				vo.setStrMsgType("0");
					
				
				

		} catch (Exception e) {
			
			vo.setStrMsgString("ServiceEngineerMstDAO.getServiceEnggNameCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}
	
	/**
	 * to insert the data.
	 * 
	 * @param vo
	 *            the vo
	 */
	 	public static void insertData(ServiceEngineerMstVO vo) 
	 	{
			HisDAO           dao = null;
			String strProcName_U = "";
			String     strTemp[] = null; 
			int nProcIndex_U;

		try 
		{
			          dao = new HisDAO("bmed", "ServiceEngineerMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_SERVICE_ENGG_MST(?,?,?,?,?,?,?,?,?,?)}";  // Total 10 Values
			      strTemp = vo.getStrPkey().replace('@', '#').split("#");
			
			for (int i = 0 , stopI = vo.getStrServiceEnggNameId().length; i < stopI; i++)
			{				
				
				if (!vo.getStrServiceEnggNameId()[i].equals("0")) 
				{
					
					nProcIndex_U = dao.setProcedure(strProcName_U);
					
					dao.setProcInValue(nProcIndex_U, "p_mode","1",1); 
					dao.setProcInValue(nProcIndex_U, "strServiceEnggNameId",vo.getStrServiceEnggNameId()[i],2); 
					dao.setProcInValue(nProcIndex_U, "strEnggItemTypeId",strTemp[0],3); 
					dao.setProcInValue(nProcIndex_U, "strEnggItemSubTypeId",strTemp[1],4); 
					dao.setProcInValue(nProcIndex_U, "strHospitalCode",vo.getStrHospitalCode(),5); 					
					dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",vo.getStrEffectiveFrom(),6);
					dao.setProcInValue(nProcIndex_U, "strRemarks",vo.getStrRemarks(),7);					
					dao.setProcInValue(nProcIndex_U, "strSeatId",vo.getStrSeatId(),8); 
					dao.setProcInValue(nProcIndex_U, "strIsValid","1",9);
					dao.setProcOutValue(nProcIndex_U, "err",1,10); 
					dao.execute(nProcIndex_U, 1);
				}
			}
			synchronized(dao )
			{
				dao.fire();
			    
			}
			vo.setStrMsgType("0");			
			
		}  
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("-->ServiceEngineerMstDAO.insertData()-->"
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
		 * @param vo
		 *            the vo
		 */
		 	public static void updateRecord(ServiceEngineerMstVO vo) 
		 	{
				HisDAO   dao = null;
				String strProcName_U;
				int nProcIndex_U;
			try 
			{
				          dao = new HisDAO("bmed", "ServiceEngineerMstDAO");
				strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_SERVICE_ENGG_MST(?,?,?,?,?,?,?,?,?,?)}";  // Total 10 Values
						 
						nProcIndex_U = dao.setProcedure(strProcName_U);
						
						dao.setProcInValue(nProcIndex_U, "p_mode","2"); 
						dao.setProcInValue(nProcIndex_U, "strServiceEnggNameId",vo.getStrEmpId()); 
						dao.setProcInValue(nProcIndex_U, "strEnggItemTypeId",vo.getStrEnggItemTypeId()); 
						dao.setProcInValue(nProcIndex_U, "strEnggItemSubTypeId",vo.getStrEnggItemSubTypeId()); 
						dao.setProcInValue(nProcIndex_U, "strHospitalCode",vo.getStrHospitalCode()); 
						dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",vo.getStrEffectiveFrom());
						dao.setProcInValue(nProcIndex_U, "strRemarks",vo.getStrRemarks());
						
						dao.setProcInValue(nProcIndex_U, "strSeatId",vo.getStrSeatId()); 
						dao.setProcInValue(nProcIndex_U, "strIsValid",vo.getStrIsValid());
						
						dao.setProcOutValue(nProcIndex_U, "err",1); 
						dao.executeProcedure(nProcIndex_U);
				
				
				vo.setStrMsgType("0");			
				
			}  
			catch (Exception e) 
			{
				//e.printStackTrace();
				vo.setStrMsgString("-->ServiceEngineerMstDAO.insertData()-->"
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
			public static void chkDuplicate(ServiceEngineerMstVO vo) {
				// Not required now.
			}

	 	
	

}
