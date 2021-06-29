package bmed.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtServiceEnggMstVO;

public class HemtServiceEnggMstDAO 
{
	public static void getServiceEnggMstCmb(HemtServiceEnggMstVO hemtServiceEnggMstVO, HisDAO dao) 
	{
		String proc_name1 = "";
		proc_name1 = "{call pkg_bmed_view.proc_hemt_service_engg_mst(?,?,?,?,?, ?,?,?)}";
	
		int procIndex1 = 0;
		String strErr = "";
		WebRowSet ws = null;
		try 
		{
			procIndex1 = dao.setProcedure(proc_name1);
			// set value
			dao.setProcInValue(procIndex1, "p_mode",hemtServiceEnggMstVO.getStrMode(),1);
			dao.setProcInValue(procIndex1, "p_engg_item_type_id", hemtServiceEnggMstVO.getStrEnggItemTypeId(),2);
			dao.setProcInValue(procIndex1, "p_engg_item_sub_type_id", hemtServiceEnggMstVO.getStrEnggItemSubTypeId(),3);
			dao.setProcInValue(procIndex1, "p_emp_id", hemtServiceEnggMstVO.getStrEmpId(),4);   
			dao.setProcInValue(procIndex1, "p_hospital_code", hemtServiceEnggMstVO.getStrHospCode(),5);
			dao.setProcInValue(procIndex1, "p_skill_id", hemtServiceEnggMstVO.getStrSkillId(),6);

			dao.setProcOutValue(procIndex1, "err", 1,7); // 1 for string return

			dao.setProcOutValue(procIndex1, "resultset", 2,8); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(procIndex1);

			// get value
			strErr = dao.getString(procIndex1, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				hemtServiceEnggMstVO.setWrsData(ws);
				
			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{			
			new HisException("billing", "IpdBillManagementTransDAO","getEnggItemTypeCmb() --> " + e.getMessage());
			hemtServiceEnggMstVO.setStrMsgString("IpdBillManagementTransDAO.getEnggItemTypeCmb() --> "+ e.getMessage());
			hemtServiceEnggMstVO.setStrMsgType("1");
		}
		
		
	}


}
