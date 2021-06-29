package bmed.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemDeskVO;

public class PistEmpPersonalDtlDAO 
{
	public static void getEmpDtl(HemDeskVO hemDeskVO_p, HisDAO dao) 
	{
		String proc_name1 = "";
		proc_name1 = "{call pkg_bmed_view.proc_PIST_EMP_CONTACT_DTL(?,?,?,?,?)}";

		int procIndex1 = 0;
		String strErr = "";
		WebRowSet ws = null;
		try 
		{
			procIndex1 = dao.setProcedure(proc_name1);
			// set value
//			System.out.println("P_mode::::"+hemDeskVO_p.getStrMode());
//			System.out.println("Engg Item Type::::"+hemDeskVO_p.getStrEmpId());
//			System.out.println("Engg Item Sub Type:::"+hemDeskVO_p.getStrHospCode());

			
			dao.setProcInValue(procIndex1, "p_mode",hemDeskVO_p.getStrMode(),1);
			dao.setProcInValue(procIndex1, "p_STR_EMP_NO", hemDeskVO_p.getStrEmpId(),2);           	
			dao.setProcInValue(procIndex1, "p_hospital_code", hemDeskVO_p.getStrHospCode(),3);
			dao.setProcOutValue(procIndex1, "err", 1,4); // 1 for string return
		    dao.setProcOutValue(procIndex1, "resultset", 2,5); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(procIndex1);

			// get value
			strErr = dao.getString(procIndex1, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				
				hemDeskVO_p.setWrsData(ws);
				
			} 
			else 
			{
				
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{			
			//e.printStackTrace();
			new HisException("billing", "IpdBillManagementTransDAO","getEnggItemTypeCmb() --> " + e.getMessage());
			hemDeskVO_p.setStrMsgString("IpdBillManagementTransDAO.getEnggItemTypeCmb() --> "+ e.getMessage());
			hemDeskVO_p.setStrMsgType("1");
		}
		
		
	}	
	
}
