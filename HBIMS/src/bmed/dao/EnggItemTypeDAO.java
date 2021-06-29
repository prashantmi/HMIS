package bmed.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.EnggItemTypeVO;

public class EnggItemTypeDAO 
{
	  
	public WebRowSet getEnggItemTypeCmb(EnggItemTypeVO enggItemTypeVO, HisDAO dao) 
	{
		String proc_name1 = "";
		proc_name1 = "{call pkg_bmed_view.proc_SEMT_ENG_ITEM_TYPMST_cmb(?,?,?,?)}";
		int procIndex1 = 0;
		String strErr = "";
		WebRowSet ws = null;
		try 
		{
			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "p_mode", "1",1);
           	
			dao.setProcInValue(procIndex1, "p_hospital_code", enggItemTypeVO.getStrHospCode(),2);

			dao.setProcOutValue(procIndex1, "err", 1,3); // 1 for string return

			dao.setProcOutValue(procIndex1, "resultset", 2,4); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(procIndex1);

			// get value
			strErr = dao.getString(procIndex1, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				
				
			} 
			else 
			{
				throw new Exception(strErr);
			}

		} 
		catch (Exception e) 
		{			
			new HisException("billing", "IpdBillManagementTransDAO","getEnggItemTypeCmb() --> " + e.getMessage());
			enggItemTypeVO.setStrMsgString("IpdBillManagementTransDAO.getEnggItemTypeCmb() --> "+ e.getMessage());
			enggItemTypeVO.setStrMsgType("1");
		}
		
		return ws;
	}
}
