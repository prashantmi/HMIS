package new_investigation.reports.bo;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.reports.dao.MachineTestReportNewDAO;
import new_investigation.vo.MachineTestReportNewVO;

public class MachineTestReportNewBO 
{

public Map AjaxGetLabList(MachineTestReportNewVO vo, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, List<MachineTestReportNewVO>> mp=new HashMap<String, List<MachineTestReportNewVO>>();
		List<MachineTestReportNewVO> listMachineTestReportNewVO=new ArrayList<MachineTestReportNewVO>();
		
		try {
			tx.begin();
			MachineTestReportNewDAO machineTestReportNewDAO = new MachineTestReportNewDAO (tx);
			
			listMachineTestReportNewVO=machineTestReportNewDAO.AjaxGetLabList(vo, userVO);
			
			mp.put("labList", listMachineTestReportNewVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			//tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			//tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			//tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			//tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			//tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mp;
	}


	

public Map AjaxGetMachineList(MachineTestReportNewVO vo, UserVO userVO) {
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map<String, List<MachineTestReportNewVO>> mp=new HashMap<String, List<MachineTestReportNewVO>>();
	List<MachineTestReportNewVO> listMachineTestReportNewVO=new ArrayList<MachineTestReportNewVO>();
	
	try {
		tx.begin();
		MachineTestReportNewDAO machineTestReportNewDAO = new MachineTestReportNewDAO (tx);
		
		listMachineTestReportNewVO=machineTestReportNewDAO.AjaxGetMachineList(vo, userVO);
		
		mp.put("machineList", listMachineTestReportNewVO);
		
	}
	catch (HisRecordNotFoundException e)
	{
		//tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	}
	catch (HisDataAccessException e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	}
	catch (HisException e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	}
	catch (Exception e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return mp;
}


public Map AjaxGetMachineTestReportList(MachineTestReportNewVO vo, UserVO userVO) {
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map<String, List<MachineTestReportNewVO>> mp=new HashMap<String, List<MachineTestReportNewVO>>();
	List<MachineTestReportNewVO> listMachineTestReportNewVO=new ArrayList<MachineTestReportNewVO>();
	
	try {
		tx.begin();
		MachineTestReportNewDAO machineTestReportNewDAO = new MachineTestReportNewDAO (tx);
		
		listMachineTestReportNewVO=machineTestReportNewDAO.AjaxGetMachineTestReportList(vo, userVO);
		
		mp.put("machineTestList", listMachineTestReportNewVO);
		
	}
	catch (HisRecordNotFoundException e)
	{
		//tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	}
	catch (HisDataAccessException e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisDataAccessException();
	}
	catch (HisException e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisException();
	}
	catch (Exception e)
	{
		//tx.rollback();
		System.out.println(e.getMessage());
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return mp;
}

}




