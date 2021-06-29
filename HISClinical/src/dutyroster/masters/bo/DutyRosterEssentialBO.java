package dutyroster.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import registration.RegistrationConfig;
import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.EssentialDAO;
import dutyroster.masters.dao.EssentialDAOi;
import dutyroster.masters.dao.RosterPrintPropertiesDAO;
import dutyroster.transaction.dao.BlockwiseRosterDtlDAO;
import dutyroster.transaction.dao.RosterDtlDAO;
import dutyroster.transaction.dao.RosterGenerationDtlDAO;


/**
 * @author ankur
 *
 */
public class DutyRosterEssentialBO implements DutyRosterEssentialBOi{

	
// Function for Form Shift Type Master and Table HDRT_SHIFT_MST
	
	public List getListOfRosterMainCategory(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterCatglist=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterCatglist=daoobj.getListOfRosterMainCategory(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterCatglist;
	}
	
	
// Function for Form Shift Type Master and Table HDRT_SHIFT_MST
	
	public List getShiftTypes(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List shiftType=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			shiftType=daoobj.getShiftTypesList(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return shiftType;
	}
	
	public List getDutyAreaTypeList(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		
		

		List dutyAreaType=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			dutyAreaType=daoobj.getDutyAreaTypesList(_userVO);
			
				
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
		return dutyAreaType;
	}

	
	public List getAllDutyAreaTypeList(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		
		

		List dutyAreaType=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			dutyAreaType=daoobj.getAllDutyAreaTypesList(_userVO);
			
				
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
		return dutyAreaType;
	}
	
	
	
	public List getDutyAreaBasedOnDutyAreaType(String areaTypeCode,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List dutyArea=new ArrayList();
				
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			dutyArea=daoobj.getDutyAreaBasedOnDutyAreaType(areaTypeCode,_userVO);
			
			
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
		return dutyArea;
	}
	
	public Map getDutyAreaAndShiftsBasedOnRosterType(String _rosterId,String areaTypeCode,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List dutyArea=new ArrayList();
		List shiftTypes=new ArrayList();
		Map EssentialMap=new HashMap();
				
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			dutyArea=daoobj.getDutyAreaBasedOnRosterAndDutyAreaType(_rosterId,areaTypeCode,_userVO);
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, dutyArea);
			
			
			shiftTypes=daoobj.getShiftTypesRosterWise(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftTypes);
			
		}
		catch (HisRecordNotFoundException e)
		{
		
		//if(shiftTypes.size()==0)
		//	EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, new ArrayList());
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
		return EssentialMap;
	}
	
	public Map getDutyAreaAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List dutyAreaList=new ArrayList();
		List desigList=new ArrayList();
		Map EssentialMap=new HashMap(); 
		
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			dutyAreaList=daoobj.getDutyAreaBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, dutyAreaList);
			
			
			desigList=daoobj.getDesignationBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, desigList);
			
			
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
		return EssentialMap;
	}
	

	public Map getDutyAreaWithCapacityAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List dutyAreaList=new ArrayList();
		List desigList=new ArrayList();
		Map EssentialMap=new HashMap(); 
		
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			dutyAreaList=daoobj.getDutyAreaWithCapacityBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, dutyAreaList);
			
			
			desigList=daoobj.getDesignationBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, desigList);
			
			
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
		return EssentialMap;
	}
	
	public List getRosterIdList(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterList=daoobj.getRosterIdList(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterList;
	}
	

	public List getDutyRosterCategory(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getDutyRosterCategory(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	
	public List getRosterAndAreaTypeList(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getRosterAndAreaTypeList(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	

	
	public Map getEmployeesBasedOnDutyAreaAndDesignation(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map EssentialMap=new HashMap();
		List empList=new ArrayList();
		List shiftList=new ArrayList();
		List dayOffShiftList=new ArrayList();
		
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			
//if we get the designationID as ALL,then we fetch all the Employees, corresponding to ALL the 
//designation mapped with that Roster 			
			
	if(_desigId.equals("ALL"))
			{
		empList=daoobj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId,_areaTypeCode,_areaCode,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empList);

			}
		else//else we fetch Employees List only for that particular Designation
			{
			empList=daoobj.getEmployeesBasedOnDutyAreaAndDesignation(_desigId,_areaTypeCode,_areaCode,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empList);
			}
	
			shiftList=daoobj.getShiftListBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
	
	
			dayOffShiftList=daoobj.getDayOffShiftTypeList(_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
			
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return EssentialMap;
	}
	
	
	
	
	

	public List getRosterType(UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List rosterTypeList=new ArrayList();

		
		try
		{
			tx.begin();
			
			EssentialDAOi daoObjRosterRole = new EssentialDAO(tx);
			rosterTypeList=daoObjRosterRole.getRosterType(_userVO);
						
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	

	public List getRosterAndAreaTypeListHavingRosterModeLocation(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getRosterAndAreaTypeListHavingRosterModeLocation(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	public Map getLocationWiseRosterEssentials(String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map EssentialMap=new HashMap();
		List areaList=new ArrayList();
		List shiftList=new ArrayList();
		List desigList=new ArrayList();
		List rosterList=new ArrayList();
		
		RosterAreaCapacityMstVO _rosterAreaCapacity=new RosterAreaCapacityMstVO();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			BlockwiseRosterDtlDAO blockwiseDao=new BlockwiseRosterDtlDAO(tx);
			
			
			
			
			
//in case the area type code is block ,then fetch the area list for the corresponding 
//BLOCK and fetching the distinct roster List for the corresponding roster and block 
		if(_areaTypeCode.equals(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK))
		{
			areaList=daoobj.getAreaBasedOnBlockId(_areaCode,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_AREA_BASED_ON_BLOCK, areaList);
			
			//in case of block the area code becomes the code for block
			String _blockId=_areaCode;
			rosterList=blockwiseDao.fetchDistinctRostersBlockWise(_rosterId, _blockId, _userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_ROSTERS_LOCATION_WISE_FOR_BLOCK, rosterList);
			
		}//fetch the list of rosters based on area code (NOT BLOCK)	
		else
		{
			
			rosterList=blockwiseDao.fetchDistinctRostersAreaWise(_rosterId, _areaTypeCode, _areaCode, _userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_ROSTERS_LOCATION_WISE_FOR_AREA, rosterList);
		}
			shiftList=daoobj.getShiftListBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
			
			desigList=daoobj.getDesignationBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, desigList);
			
			
			
			_rosterAreaCapacity=daoobj.getRosterShiftWiseCapacity(_rosterId, _areaTypeCode, _areaCode, _userVO);
			EssentialMap.put(DutyRosterConfig.VO_OF_ROSTER_SHIFT_CAPACITY, _rosterAreaCapacity);
			
			
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return EssentialMap;
	}
	

	////////List of Rosters on the Basis of Roster Category////////////////
	
	public List getDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	
	////////List of Monthwise Rosters on the Basis of Roster Category////////////////
	
	
	
	public List getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	
	////////List of DateWise Rosters on the Basis of Roster Category ////////////////
	
	
	
	public List getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	//list of Empwise roster category	
	
	public List getEmpWiseRosterCategory(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getEmpWiseRosterCategory(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	
	//list of Empwise roster category	
	
	public List getRosterCategoryList(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getRosterCategoryList(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	
	//list of Locationwise roster category
	
	public List getLocationWiseRosterCategory(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rosterTypeList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rosterTypeList=daoobj.getLocationWiseRosterCategory(_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rosterTypeList;
	}
	
	
	
	// Function for Form Shift Type Master and Table HDRT_SHIFT_MST
		
		public List getDesignationList(UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			List rosterCatglist=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				rosterCatglist=daoobj.getDesignationList(_userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return rosterCatglist;
		}
		
		
// Function for Form Shift Type Master and Table HDRT_SHIFT_MST
		
		public List getEmpListBasedOnDesignation(String desigId,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			List rosterCatglist=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				rosterCatglist=daoobj.getEmpListBasedOnDesignation(desigId,_userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return rosterCatglist;
		}
		
		public List getDutyAreaBasedOnEmployee(String _empId,UserVO _userVO)
		{

			JDBCTransactionContext tx = new JDBCTransactionContext();
			List areaList=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				areaList=daoobj.getDutyAreaBasedOnEmployee(_empId,_userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return areaList;
		
			
		}
		
		
		public List getRosterListBasedOnAreaEmployee(String _empId,String _areaCode,UserVO _userVO)
		{

			JDBCTransactionContext tx = new JDBCTransactionContext();
			List rosterList=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				rosterList=daoobj.getRosterListBasedonAreaEmp(_empId,_areaCode,_userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return rosterList;
		
			
		}

	public Map getEmpRosterWiseEmpReport(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,String _empId,UserVO _userVO){
			

			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map EssentialMap=new HashMap();
			RosterDtlVO[] _rosterDtlVO=null;
			List shiftList=new ArrayList();
			List empMappedList=new ArrayList();
			List dayOffShiftList=new ArrayList();
			DutyRosterPrintPropertiesVO[]  _rosterPrintVO=null;
			RosterGenerationDtlVO[] _rosterGenerationDtlVO=null;
			Map _holidayMap=new LinkedHashMap();
			
			try
			{
				tx.begin();

				EssentialDAO _essentialDaoObj = new EssentialDAO(tx);
				RosterDtlDAO _rosterDaoObj=new RosterDtlDAO(tx);
				RosterPrintPropertiesDAO _printDao=new RosterPrintPropertiesDAO(tx);
				
				RosterGenerationDtlDAO _rosterGenerationDao=new RosterGenerationDtlDAO(tx);
				
				
				
				

				//getting and then putting the monthly gazetted holiday Map
				
				_holidayMap=_essentialDaoObj.getMonthlyGazettedHolidays(_year, _month, _userVO);
				EssentialMap.put(DutyRosterConfig.MAP_OF_MONTHLY_GAZETTED_HOLIDAYS, _holidayMap);
				
				
				
				//	getting the complete monthly days for which the roster has been generated 
				 		
		/*if(_areaCode.equals("ALL"))
				_rosterGenerationDtlVO=_rosterGenerationDao.fetchForAllMappedAreas(_year, _month, _areaTypeCode, _rosterId, _userVO);
				else*/
				_rosterGenerationDtlVO=_rosterGenerationDao.fetch(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);


				EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS, _rosterGenerationDtlVO);
			
				
				
				
				_rosterDtlVO=_rosterDaoObj.fetchAreaWiseEmpWiseRosterReport(_year, _month, _rosterId, _areaTypeCode, _areaCode, _empId, _userVO); 
				EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
				
				
				
		/*if(_desigId.equals("ALL"))
				{

		//if area code is selected as all,then corresponding tp all mapped areas data will be fetched  	
			if(_areaCode.equals("ALL"))
				_rosterDtlVO=_rosterDaoObj.fetchAllMappedAreaWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			else
			    _rosterDtlVO=_rosterDaoObj.fetchAllMappedDesignationWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			
			
				EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
				}
				else
				{
				_rosterDtlVO=_rosterDaoObj.fetchDesignationWiseRosterReport(_desigId,_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
				}*/

				
				

				shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
				EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
				
				
				dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
				EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
				
				
				
			/*	if(_desigId.equals("ALL"))
				{

					
		//if area code is selected as all,then corresponding tp all mapped areas data will be fetched			
					if(_areaCode.equals("ALL"))
						empMappedList=_essentialDaoObj.getEmployeesMappedWithAllMappedAreasWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
						else
						empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
					
					
					
					EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
					
				}
				else
				{	
				empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndDesignation(_desigId,_areaTypeCode,_areaCode,_userVO);
				EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
				}*/
				
				//for printing properties
				
				_rosterPrintVO=_printDao.fetch(_rosterId,_userVO);

		if(_rosterPrintVO!=null)
				EssentialMap.put(DutyRosterConfig.VO_OF_ROSTER_PRINT_DETAILS, _rosterPrintVO);
				
				
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return EssentialMap;

			
			
			}
	
	
	public List getEmpListofSupervisior(UserVO _userVO){
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
	
		List empList=new ArrayList();
		
		try
		{
			tx.begin();

			EssentialDAO _essentialDaoObj = new EssentialDAO(tx);
							

			empList=_essentialDaoObj.getEmpListofSupervisior(_userVO);
			
			
			
		
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return empList;

		
		
		}
	
		public Map getEmpWiseEmpReport(String _year,String _month,String empId,UserVO _userVO){
			

			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map EssentialMap=new HashMap();
			RosterDtlVO[] _rosterDtlVO=null;
			List shiftList=new ArrayList();
			List empMappedList=new ArrayList();
			List dayOffShiftList=new ArrayList();
			DutyRosterPrintPropertiesVO[]  _rosterPrintVO=null;
			RosterGenerationDtlVO[] _rosterGenerationDtlVO=null;
			Map _holidayMap=new LinkedHashMap();
			
			try
			{
				tx.begin();

				EssentialDAO _essentialDaoObj = new EssentialDAO(tx);
				RosterDtlDAO _rosterDaoObj=new RosterDtlDAO(tx);
				RosterPrintPropertiesDAO _printDao=new RosterPrintPropertiesDAO(tx);
				
				RosterGenerationDtlDAO _rosterGenerationDao=new RosterGenerationDtlDAO(tx);
				
				

				
				_rosterGenerationDtlVO=_rosterGenerationDao.fetchMonthlyForAllRosters(_year, _month, _userVO);
				EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS, _rosterGenerationDtlVO);
			
				
				
				
				_rosterDtlVO=_rosterDaoObj.fetchEmpWiseRosterReport(_year, _month,empId, _userVO); 
				EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL, _rosterDtlVO);
				
				
				
			
			}
			catch (HisRecordNotFoundException e)
			{
				EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL, _rosterDtlVO);	
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return EssentialMap;

			
			
			}

		public List getRosterCategoryBasedOnHospital(String hospitalCode,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			List categoryList=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
		        categoryList=daoobj.getRosterCategoryBasedOnHospital(hospitalCode,_userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return categoryList;
		}
		

	

		// Function for getting the area list based on the roster category<-->mapped to rosters--<>mapped to areas
		//and Table HDRT_SHIFT_MST
			
			public List getDutyAreasBasedOnRosterCategory(String rosterCategory,UserVO _userVO)
			{
				JDBCTransactionContext tx = new JDBCTransactionContext();
				List areaList=new ArrayList();
				try
				{
					tx.begin();

					EssentialDAO daoobj = new EssentialDAO(tx);
					areaList=daoobj.getDutyAreasBasedOnRosterCategory(rosterCategory,_userVO);
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
					System.out.println("error.... DutyRoster BO");
					tx.rollback();
					throw new HisApplicationExecutionException();
				}
				finally
				{
					tx.close();
				}
				return areaList;
			}
			
			
			

			// Function for Form Shift Type Master and Table HDRT_SHIFT_MST
				
				public List getRostersBasedOnDutyAreaAndRosterCatg(String rosterCategory,String _areaTypeCode,String _areaCode,UserVO _userVO)
				{
					JDBCTransactionContext tx = new JDBCTransactionContext();
					List rosterCatglist=new ArrayList();
					try
					{
						tx.begin();

						EssentialDAO daoobj = new EssentialDAO(tx);
						rosterCatglist=daoobj.getRostersBasedOnDutyAreaAndRosterCatg(rosterCategory,_areaTypeCode,_areaCode,_userVO);
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
						System.out.println("error.... DutyRoster BO");
						tx.rollback();
						throw new HisApplicationExecutionException();
					}
					finally
					{
						tx.close();
					}
					return rosterCatglist;
				}


			
				
// Function for getting the shifts based on roster type
				
	public List getListOfShiftsBasedOnRosterType(String _rosterId,UserVO _userVO)
				{
					JDBCTransactionContext tx = new JDBCTransactionContext();
					List shiftList=new ArrayList();
					try
					{
						tx.begin();

						EssentialDAO daoobj = new EssentialDAO(tx);
						shiftList=daoobj.getShiftTypesRosterWise(_rosterId,_userVO);
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
						System.out.println("error.... DutyRoster BO");
						tx.rollback();
						throw new HisApplicationExecutionException();
					}
					finally
					{
						tx.close();
					}
					return shiftList;
				}
	
	public Map RosterWiseShiftsAndArea(String _rosterId,String areaTypeCode,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List dutyArea=new ArrayList();
		List shiftTypes=new ArrayList();
		Map EssentialMap=new HashMap();
				
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			dutyArea=daoobj.getDutyAreaBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, dutyArea);
			
			
			shiftTypes=daoobj.getShiftListForRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftTypes);
			
		}
		catch (HisRecordNotFoundException e)
		{
		
		//if(shiftTypes.size()==0)
		//	EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, new ArrayList());
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
		return EssentialMap;
	}
	

				public List getRosterForRoleAssignment(UserVO _userVO) {
					JDBCTransactionContext tx = new JDBCTransactionContext();
					List rosterlist=new ArrayList();
					try
					{
						tx.begin();

						EssentialDAO daoobj = new EssentialDAO(tx);
						rosterlist=daoobj.getRosterForRoleAssignment(_userVO);
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
						System.out.println("error.... DutyRoster BO");
						tx.rollback();
						throw new HisApplicationExecutionException();
					}
					finally
					{
						tx.close();
					}
					return rosterlist;
				}


				public Map getShiftAndAreaForRoster(String _rosterTypeID, UserVO _userVO) {
					JDBCTransactionContext tx = new JDBCTransactionContext();
					Map essentialMap=new HashMap();
					List shiftlist=new ArrayList();
					List areaList=new ArrayList();
					try
					{
						tx.begin();

						EssentialDAO daoobj = new EssentialDAO(tx);
						shiftlist=daoobj.getShiftForRoster(_rosterTypeID,_userVO);
						essentialMap.put(DutyRosterConfig.ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER, shiftlist);
						areaList=daoobj.getDutyAreaBasedOnRosterType(_rosterTypeID, _userVO);
						essentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, areaList);
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
						System.out.println("error.... DutyRoster BO");
						tx.rollback();
						throw new HisApplicationExecutionException();
					}
					finally
					{
						tx.close();
					}
					return essentialMap;
				}

				
				
public Map getEmployeeMapAndRole(RosterDtlVO _rosterDtlVO, UserVO _userVO) {
					JDBCTransactionContext tx = new JDBCTransactionContext();
					Map essentaiMap=new HashMap();
					RosterDtlVO[] rosterDtlVO=null;
					List roleList=new ArrayList();
					List dateRangeRoleAssignmentList=new ArrayList();
					try
					{
						tx.begin();

						EssentialDAO daoobj = new EssentialDAO(tx);
						
						rosterDtlVO=daoobj.getShiftBasedEmployeeList(_rosterDtlVO,_userVO);
						essentaiMap.put(DutyRosterConfig.EMPLOYEE_LIST_ROSTER_DTL_VO_ARRAY, rosterDtlVO);
						
						
						roleList=daoobj.getRosterWiseDutyRole(_rosterDtlVO.getRosterId(),_userVO);
						essentaiMap.put(DutyRosterConfig.ESSENTIAL_DUTY_ROLE_FOR_ROSTER, roleList);
						
						dateRangeRoleAssignmentList=daoobj.getDateRangeDutyRoleAssignmentList(_rosterDtlVO.getRosterId(),_rosterDtlVO.getAreaCode(),_userVO);
						essentaiMap.put(DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE, dateRangeRoleAssignmentList);
						
						
						
					}
					catch (HisRecordNotFoundException e)
					{
						
						essentaiMap.put(DutyRosterConfig.ESSENTIAL_DUTY_ROLE_FOR_ROSTER, new ArrayList());
						essentaiMap.put(DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE, new ArrayList());
						essentaiMap.put(DutyRosterConfig.EMPLOYEE_LIST_ROSTER_DTL_VO_ARRAY, rosterDtlVO);
						e.setEssentialMap(essentaiMap);
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
						System.out.println("error.... DutyRoster BO");
						tx.rollback();
						throw new HisApplicationExecutionException();
					}
					finally
					{
						tx.close();
					}
					return essentaiMap;
				}


				




		public List RosterWiseMappedAreas(String _rosterId,UserVO _userVO){

			JDBCTransactionContext tx = new JDBCTransactionContext();
			List areaList=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				areaList=daoobj.getDutyAreaBasedOnRosterType(_rosterId, _userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return areaList;
		
		}


		
		public List getEmpListToBeExchanged(String _rosterCatgId,UserVO _userVO)
		{

			JDBCTransactionContext tx = new JDBCTransactionContext();
			List empList=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				empList=daoobj.getEmpListToBeExchanged(_rosterCatgId,_userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return empList;
		
		}
		
		public Map getNurseRoleDetail(UserVO _userVO) 
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map essentialMap=new HashMap();
			List categoryList=null;
			List allDutyRoleList=null;
			try
			{
				tx.begin();
				
				EssentialDAO daoobj = new EssentialDAO(tx);
				
				categoryList=daoobj.getCatListForNurse(_userVO);
				essentialMap.put(DutyRosterConfig.ESSENTIAL_CATEGORY_LIST_FOR_NURSE, categoryList);
				
				allDutyRoleList=daoobj.getAllDutyRoleList(_userVO);
				essentialMap.put(DutyRosterConfig.ESSENTIAL_ALL_DUTY_ROLE_LIST, allDutyRoleList);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return essentialMap;
		}
		
		
//////////For fetching Roster Category on the Basis of Main category///////////////
		
		
		public List getRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg,UserVO _userVO){

			JDBCTransactionContext tx = new JDBCTransactionContext();
			List areaList=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				areaList=daoobj.getRosterCategoryBasedOnRosterMainCategory(_rosterMainCatg, _userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return areaList;
		
		}
	
		
///////////////////For getting the emp duty list for exchanged//////////////////////////
		
		public RosterDtlVO[] getEmpDutyListForExchange(String _year,String _month,String _rosterCatgId,String empId,UserVO _userVO)
		{

			JDBCTransactionContext tx = new JDBCTransactionContext();
			RosterDtlVO[] rosterDtlVO=null;
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				rosterDtlVO=daoobj.getEmpDutyListForExchange(_year, _month, _rosterCatgId, empId, _userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return rosterDtlVO;
		
		}
		
		
///////////////////For getting the emp duty list for Changed//////////////////////////
		
		public RosterDtlVO[] getEmpDutyListForChange(String _year,String _month,String _day,String _rosterCatgId,String empId,UserVO _userVO)
		{

			JDBCTransactionContext tx = new JDBCTransactionContext();
			RosterDtlVO[] rosterDtlVO=null;
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				rosterDtlVO=daoobj.getEmpDutyListForChange(_year, _month,_day, _rosterCatgId, empId, _userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return rosterDtlVO;
		
		}

		//********************************************FOR RELIVERS***************************************//
		
public List getAreaListBasedOnRosterCategory(String _rosterCatgId,UserVO _userVO)
	{

			JDBCTransactionContext tx = new JDBCTransactionContext();
			List areaList=new ArrayList();
			try
			{
				tx.begin();

				EssentialDAO daoobj = new EssentialDAO(tx);
				areaList=daoobj.getAreaListBasedOnRosterCategory(_rosterCatgId,_userVO);
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
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return areaList;
		
		}
		
	
public Map getEmpAndShiftListBasedOnRosterCategory(String _year,String _month,String _rosterCatgId,String _areaCode,String _areaTypeCode,String _reason,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	List empList=new ArrayList();
	List reliverEmpList=new ArrayList();
	List shiftTypes=new ArrayList();
	Map EssentialMap=new HashMap();
			
	try
	{
		tx.begin();

		EssentialDAO daoobj = new EssentialDAO(tx);
		
/*if(_reason.equals(DutyRosterConfig.RELIVER_REASON_EMPLOYEE))
		{
		empList=daoobj.getEmpListBasedOnRosterCategory(_year, _month, _rosterCatgId, _userVO);		
		EssentialMap.put(DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY, empList);
		}
	else
if(_reason.equals(DutyRosterConfig.RELIVER_REASON_OVERLOAD))		
		EssentialMap.put(DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY, empList);

*/
		
		
		empList=daoobj.getEmpListBasedOnRosterCategory(_year, _month, _rosterCatgId, _userVO);		
		EssentialMap.put(DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY, empList);
		
		
		reliverEmpList=daoobj.getReliverEmpListBasedOnRosterCategory(_year, _month, _rosterCatgId, _userVO);
		EssentialMap.put(DutyRosterConfig.RELIVER_EMP_LIST_BASED_ON_ROSTER_CATEGORY, reliverEmpList);
	
				
		shiftTypes=daoobj.getShiftListBasedOnRosterCategory(_year, _month, _areaCode, _areaTypeCode, _rosterCatgId, _userVO); 
		EssentialMap.put(DutyRosterConfig.SHIFT_LIST_BASED_ON_ROSTER_CATEGORY, shiftTypes);
		
	}
	catch (HisRecordNotFoundException e)
	{
	
	//if(shiftTypes.size()==0)
	//	EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, new ArrayList());
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
	return EssentialMap;
}

//***********************************For Employee Daily Work Report************************************/////////////

public List getDutyAreasBasedOnRosterType(String _rosterCategory,UserVO _userVO)
{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			areaList=daoobj.getDutyAreaBasedOnRosterType(_rosterCategory,_userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return areaList;
	
	}



public List getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(String _rosterId,String _areaCode,String _areaTypeCode,UserVO _userVO)
{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			areaList=daoobj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return areaList;
	
	}


//////////////////////List of Role Based  Roster Main Category/////////////////////////////////////

public List getListOfRoleBasedRosterMainCategory(UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	List rosterCatglist=new ArrayList();
	try
	{
		tx.begin();

		EssentialDAO daoobj = new EssentialDAO(tx);
		rosterCatglist=daoobj.getListOfRoleBasedRosterMainCategory(_userVO);
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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return rosterCatglist;
}


////////List of Role Based Roster  Category  on the Basis of Roster Main Category////////////////


public List getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg,UserVO _userVO){

	JDBCTransactionContext tx = new JDBCTransactionContext();
	List areaList=new ArrayList();
	try
	{
		tx.begin();

		EssentialDAO daoobj = new EssentialDAO(tx);
		areaList=daoobj.getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(_rosterMainCatg, _userVO);
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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return areaList;

}


////////List of Role Based Rosters on the Basis of Roster Category////////////////


public List getListOfRoleBasedRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	List rosterTypeList=new ArrayList();
	try
	{
		tx.begin();

		EssentialDAO daoobj = new EssentialDAO(tx);
		rosterTypeList=daoobj.getListOfRoleBasedRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return rosterTypeList;
}


public List getListOfAllMappedEmployeesHavingUserId(String _rosterId,String _areaCode,String _areaTypeCode,UserVO _userVO)
{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			areaList=daoobj.getListOfAllMappedEmployeesHavingUserId(_rosterId, _areaTypeCode, _areaCode, _userVO);
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return areaList;
	
	}
public Map<String,String> getHospitalEssentialCombo(UserVO _userVO)
{
	Map essentialMap = new HashMap();
	List lstHospitalCombo = null;


	JDBCTransactionContext tx = new JDBCTransactionContext();
	try
	{
		tx.begin();
		EssentialDAO objEssentialDAO = new EssentialDAO(tx);

		lstHospitalCombo = objEssentialDAO.getAllHospitalsSeatIDWise(_userVO);
		essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, lstHospitalCombo);


	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		tx.rollback();
		throw new HisDataAccessException();
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	catch (HisException e)
	{
		tx.rollback();
		throw new HisException();
	}
	catch (Exception e)
	{
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return essentialMap;
}

public List getDesignationBasedOnHospital(String hospitalCode,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	List categoryList=new ArrayList();
	try
	{
		tx.begin();

		EssentialDAO daoobj = new EssentialDAO(tx);
        categoryList=daoobj.getDesignationBasedOnHospital(hospitalCode,_userVO);
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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return categoryList;
}



}
