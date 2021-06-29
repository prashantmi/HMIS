package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.HemConfigMstVO;
import hisglobal.transactionmgnt.HisDAO;

public class HemConfigMstDAO
{
	
	/**
	 * to get the data for Hem Configuration.
	 * 
	 * @param vo_p	the HemConfigMstVO
	 */
	public static void getRecord(HemConfigMstVO vo_p) 
	{
		HisDAO dao = null;
		int nqryIndex = 0;

		String strQuery;

		WebRowSet web = null;
		
		int nConfigPropertyId=0;
		try 
		{

			dao = new HisDAO("bmed", "HemConfigMstDAO");

			

			strQuery = bmed.qryHandler_bmed.getQuery(1, "hemConfigMst.getRecord");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo_p.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, Config.IS_VALID_ACTIVE);

			web = dao.executeQry(nqryIndex);
			
			while (web.next()) 
			{	
				nConfigPropertyId	=	Integer.parseInt(web.getString(1));
				
				switch(nConfigPropertyId)
				{
				case 10:
					vo_p.setStrShowMcDetailsUpto(web.getString(2));
				break;
				
				case 11:
					vo_p.setStrShowWarrantyDetailsUpto(web.getString(2));
				break;
					
				case 12:
					vo_p.setStrFinancialStartDate(web.getString(2));
				break;
					
				case 13:
					vo_p.setStrFinancialEndDate(web.getString(2));
				break;
					
				case 14:
					vo_p.setStrItemUnderAmcOrWarranty(web.getString(2));
					break;
				case 15:
					vo_p.setStrFolderName(web.getString(2));
				}			
			}
			web.close();
			
		}

		catch (Exception e) 
		{
			vo_p.setStrMsgString("--> HemConfigMstDAO.getRecord()-->"	+ e.getMessage());
			vo_p.setStrMsgType("1");
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
	 * to save the record.
	 * 
	 * @param vo_p	the HemConfigMstVO
	 * 
	 * @throws Exception
	 */
	public static void saveRecord(HemConfigMstVO vo_p) 
	{
		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";
		try {
			dao = new HisDAO("bmed", "HemConfigMstDAO");
			// Total 5 Values to be executed in batch
			strProcName_U = "{call PKG_BMED_DML.PROC_SEMT_CONFIG_PROPERTY_MST(?,?,?,?,?)}"; 
			nProcIndex_U = dao.setProcedure(strProcName_U);

			//1. Setting the 	p_str_property_value	 for	p_num_config_property_id=10	                                
			dao.setProcInValue(nProcIndex_U, "p_mode", "1",1); //p_mode
			dao.setProcInValue(nProcIndex_U, "p_num_config_property_id", "10",2);  //p_num_config_property_id
			dao.setProcInValue(nProcIndex_U, "p_num_hospital_code", vo_p.getStrHospitalCode(),3);//p_num_hospital_code
			dao.setProcInValue(nProcIndex_U, "p_str_property_value", vo_p.getStrShowMcDetailsUpto(),4); //p_str_property_value
			dao.setProcOutValue(nProcIndex_U, "err", 1,5); // default values 
			
			//dao.execute(nProcIndex_U, 1);
			dao.executeProcedureByPosition(nProcIndex_U);
			
			strProcName_U = "{call PKG_BMED_DML.PROC_SEMT_CONFIG_PROPERTY_MST(?,?,?,?,?)}"; 
			nProcIndex_U = dao.setProcedure(strProcName_U);
			//2. Setting the 	p_str_property_value	 for	p_num_config_property_id=11
			dao.setProcInValue(nProcIndex_U, "p_mode", "1",1); //p_mode
			dao.setProcInValue(nProcIndex_U, "p_num_config_property_id", "11",2);  //p_num_config_property_id
			dao.setProcInValue(nProcIndex_U, "p_num_hospital_code", vo_p.getStrHospitalCode(),3);//p_num_hospital_code
			dao.setProcInValue(nProcIndex_U, "p_str_property_value", vo_p.getStrShowWarrantyDetailsUpto(),4); //p_str_property_value
			dao.setProcOutValue(nProcIndex_U, "err", 1,5); // default value
			
			dao.executeProcedureByPosition(nProcIndex_U);
			//dao.execute(nProcIndex_U, 1);
			
			strProcName_U = "{call PKG_BMED_DML.PROC_SEMT_CONFIG_PROPERTY_MST(?,?,?,?,?)}"; 
			nProcIndex_U = dao.setProcedure(strProcName_U);
			//3. Setting the 	p_str_property_value	 for	p_num_config_property_id=12
			dao.setProcInValue(nProcIndex_U, "p_mode", "1",1); //p_mode
			dao.setProcInValue(nProcIndex_U, "p_num_config_property_id", "12",2);  //p_num_config_property_id			
			dao.setProcInValue(nProcIndex_U, "p_num_hospital_code", vo_p.getStrHospitalCode(),3);//p_num_hospital_code
			dao.setProcInValue(nProcIndex_U, "p_str_property_value", vo_p.getStrFinancialStartDate(),4); //p_str_property_value
			dao.setProcOutValue(nProcIndex_U, "err", 1,5); // default value
			
			dao.executeProcedureByPosition(nProcIndex_U);
			//dao.execute(nProcIndex_U, 1);
			
			strProcName_U = "{call PKG_BMED_DML.PROC_SEMT_CONFIG_PROPERTY_MST(?,?,?,?,?)}"; 
			nProcIndex_U = dao.setProcedure(strProcName_U);
			
			//4. Setting the 	p_str_property_value	 for	p_num_config_property_id=13
			dao.setProcInValue(nProcIndex_U, "p_mode", "1",1); //p_mode
			dao.setProcInValue(nProcIndex_U, "p_num_config_property_id", "13",2);  //p_num_config_property_id
			dao.setProcInValue(nProcIndex_U, "p_num_hospital_code", vo_p.getStrHospitalCode(),3);//p_num_hospital_code
			dao.setProcInValue(nProcIndex_U, "p_str_property_value", vo_p.getStrFinancialEndDate(),4); //p_str_property_value
			dao.setProcOutValue(nProcIndex_U, "err", 1,5); // default value
			
			dao.executeProcedureByPosition(nProcIndex_U);
			//dao.execute(nProcIndex_U, 1);
			
			strProcName_U = "{call PKG_BMED_DML.PROC_SEMT_CONFIG_PROPERTY_MST(?,?,?,?,?)}"; 
			nProcIndex_U = dao.setProcedure(strProcName_U);
			//5. Setting the 	p_str_property_value	 for	p_num_config_property_id=14
			dao.setProcInValue(nProcIndex_U, "p_mode", "1",1); //p_mode
			dao.setProcInValue(nProcIndex_U, "p_num_config_property_id", "14",2);  //p_num_config_property_id
			dao.setProcInValue(nProcIndex_U, "p_num_hospital_code", vo_p.getStrHospitalCode(),3);//p_num_hospital_code
			dao.setProcInValue(nProcIndex_U, "p_str_property_value", vo_p.getStrItemUnderAmcOrWarranty(),4); //p_str_property_value
			dao.setProcOutValue(nProcIndex_U, "err", 1,5); // default value
			
			dao.executeProcedureByPosition(nProcIndex_U);
			//dao.execute(nProcIndex_U, 1);
			
			strProcName_U = "{call PKG_BMED_DML.PROC_SEMT_CONFIG_PROPERTY_MST(?,?,?,?,?)}"; 
			nProcIndex_U = dao.setProcedure(strProcName_U);
			//6. Setting the 	p_str_property_value	 for	p_num_config_property_id=14
			dao.setProcInValue(nProcIndex_U, "p_mode", "1",1); //p_mode
			dao.setProcInValue(nProcIndex_U, "p_num_config_property_id", "15",2);  //p_num_config_property_id
			dao.setProcInValue(nProcIndex_U, "p_num_hospital_code", vo_p.getStrHospitalCode(),3);//p_num_hospital_code
			dao.setProcInValue(nProcIndex_U, "p_str_property_value", vo_p.getStrFolderName(),4); //p_str_property_value
			dao.setProcOutValue(nProcIndex_U, "err", 1,5); // default value
			
			dao.executeProcedureByPosition(nProcIndex_U);
			//dao.execute(nProcIndex_U, 1);
			//Anushika
			/*synchronized (dao) {
				dao.fire();
			}
			*/

			vo_p.setStrMsgType("0");

		} catch (Exception e)
		{
			e.printStackTrace();
			vo_p.setStrMsgString("--> HemConfigMstDAO.saveRecord()-->" + e.getMessage());
			vo_p.setStrMsgType("1");
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
