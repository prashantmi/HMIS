package mortuary.masters.bo;

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

import opd.dao.OpdEssentialDAO;

import mortuary.MortuaryConfig;
import mortuary.masters.dao.MortuaryMstEssentialDAO;
import mortuary.masters.dao.MortuaryMstEssentialDAOi;
import mortuary.transaction.dao.MortuaryEssDAO;
import mortuary.transaction.dao.MortuaryEssDAOi;

public class MortuaryEssentialBO implements MortuaryEssentialBOi
{
	//Getting Essential For Mortuary Master
	public Map getEssentialForMortuaryMst(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List allDept=new ArrayList();
		List allBuilding=new ArrayList();
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mstEssDAO=new MortuaryMstEssentialDAO(tx);
			
			allDept=mstEssDAO.getAllDepartment(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_DEPARTMENT,allDept);
			
			allBuilding=mstEssDAO.getAllBuilding(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_BUILDING_BLOCK, allBuilding);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException("");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	//getting List of Employee Based On Department
	public List getEmployeeListBasedOnDept(String deptCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List empList=new ArrayList();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mstEssDAO=new MortuaryMstEssentialDAO(tx);
			empList=mstEssDAO.getEmployeeListBasedOnDept(deptCode,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return empList;
		
	}
	
	//Getting Block List
	public List getBlockList(String buildingCode,UserVO userVO)

	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List blockList=new ArrayList();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mstEssDAO=new MortuaryMstEssentialDAO(tx);
			blockList=mstEssDAO.getBlockListBasedOnBuilding(buildingCode,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return blockList;
	}
	
	//Getting Floor List
	public List getFloorList(String blockId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List floorList=new ArrayList();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mstEssDAO=new MortuaryMstEssentialDAO(tx);
			floorList=mstEssDAO.getFloorListBasedOnBlock(blockId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return floorList;
	}
	
	//Getting Room List
	public List getRoomList(String floorId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaRoomList=new ArrayList();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mstEssDAO=new MortuaryMstEssentialDAO(tx);
			areaRoomList=mstEssDAO.getRoomListBasedOnFloor(floorId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return areaRoomList;
	}
	
	//Getting Essential For Mortuary Area Master
	public Map getEssentialForMortuaryAreaMst(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List allBuilding=new ArrayList();
		
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi mstEssDAO=new MortuaryMstEssentialDAO(tx);
			
			allBuilding=mstEssDAO.getAllBuilding(userVO);
			essentialMap.put(MortuaryConfig.ESSENTIAL_ALL_BUILDING_BLOCK, allBuilding);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException("");
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
//for fetching the essential details 
	
	public String getChamberName(String  _chamberId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		
		String chamberName="";

		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi daoObj = new MortuaryMstEssentialDAO(tx);
			
			
			//Getting and putting all mortuaries
			
			chamberName=daoObj.getChamberName(_chamberId,_userVO);
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return chamberName;
		
	}
	

	public List getAllDoctor(UserVO _UserVO)
	{
		List listAllDoctor = null;
		Map essentialMap=new HashMap();
		String dept="";
		String processId="";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MortuaryEssDAOi essDAO= new MortuaryEssDAO(tx);
			processId=MortuaryConfig.POSTMORTEM_REQUEST_APPROVED;
			listAllDoctor=essDAO.getEmployeeListDeptProcessWise(processId,dept,_UserVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listAllDoctor;
	}
	
	public List getDeathCause(UserVO _UserVO)
	{
		List listDeathCause = null;
		Map essentialMap=new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO= new MortuaryMstEssentialDAO(tx);
			listDeathCause=essDAO.getDeathMannerList(_UserVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listDeathCause;
	}
	
	public List getLab(UserVO _UserVO)
	{
		List listLab = null;
		Map essentialMap=new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO= new MortuaryMstEssentialDAO(tx);
			listLab=essDAO.getExternalLabList(_UserVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listLab;
	}
	
	public List getLabTest(UserVO _UserVO)
	{
		List listLabTest = null;
		Map essentialMap=new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MortuaryMstEssentialDAOi essDAO= new MortuaryMstEssentialDAO(tx);
			listLabTest=essDAO.getExternalLabTestList(_UserVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return listLabTest;
	}
}
