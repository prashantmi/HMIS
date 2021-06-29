package inpatient.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;
import inpatient.masters.dao.UnitInvParaMappingMstDAO;

import java.util.List;

public class UnitInvParaMappingMstBO implements UnitInvParaMappingMstBOi{


	
	public void saveUnitWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
			daoobj.createUnitWise(_voUDMT,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
	}
	
	public void updateTableUnitWise(String _unitId, UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
			daoobj.updateTableUnitWise(_unitId,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
	}

	
	public void updateTableWardWise(String _unitId,String _wardCode, UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
			daoobj.updateTableWardWise(_unitId,_wardCode,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
	}
	public void saveUnitWardWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
			daoobj.createUnitWardWise(_voUDMT,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
	}
	
	public List gettingWards(String _deptUnitCode,UserVO _UserVO)
	{
		
		List wards=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
			wards=daoobj.gettingWards(_deptUnitCode,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Master BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return wards;
	}
	
	
}
