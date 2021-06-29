package bmed.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtCompEscMstVO;

public class HemtCompEscMstDAO 
{
	
	public static void getComplaintEscCmb(HemtCompEscMstVO hemtCompEscMstVO, HisDAO dao) 
	{
		String proc_name1 = "";
		proc_name1 = "{call pkg_bmed_view.proc_HEMT_COMP_ESCALATION_MST(?,?,?,?,?, ?,?)}";

		int procIndex1 = 0;
		String strErr = "";
		WebRowSet ws = null;
		try 
		{
			procIndex1 = dao.setProcedure(proc_name1);
			// set value
//			System.out.println("P_mode::::"+hemtCompEscMstVO.getStrMode());
//			System.out.println("Engg Item Type::::"+hemtCompEscMstVO.getStrEnggItemType());
//			System.out.println("Engg Item Sub Type:::"+hemtCompEscMstVO.getStrEnggItemSubType());
//			System.out.println("Emp ID:::"+hemtCompEscMstVO.getStrEmpId());
			
			dao.setProcInValue(procIndex1, "p_mode",hemtCompEscMstVO.getStrMode(),1);
			dao.setProcInValue(procIndex1, "p_engg_item_type_id", hemtCompEscMstVO.getStrEnggItemType(),2);
			dao.setProcInValue(procIndex1, "p_engg_item_sub_type_id", hemtCompEscMstVO.getStrEnggItemSubType(),3);
			dao.setProcInValue(procIndex1, "p_emp_id", hemtCompEscMstVO.getStrEmpId(),4);           	
			dao.setProcInValue(procIndex1, "p_hospital_code", hemtCompEscMstVO.getStrHospitalCode(),5);

			dao.setProcOutValue(procIndex1, "err", 1,6); // 1 for string return

			dao.setProcOutValue(procIndex1, "resultset", 2,7); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(procIndex1);

			// get value
			strErr = dao.getString(procIndex1, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				hemtCompEscMstVO.setWrsData(ws);
				
			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{			
			new HisException("billing", "IpdBillManagementTransDAO","getEnggItemTypeCmb() --> " + e.getMessage());
			hemtCompEscMstVO.setStrMsgString("IpdBillManagementTransDAO.getEnggItemTypeCmb() --> "+ e.getMessage());
			hemtCompEscMstVO.setStrMsgType("1");
		}
		
		
	}
	
}
