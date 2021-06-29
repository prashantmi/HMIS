package dutyroster.transaction.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.vo.DutyRoleDetailVO;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.BlockwiseRosterDtlVO;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.RosterReliverDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.DutyAreaEmployeeMstDAO;
import dutyroster.masters.dao.EssentialDAO;
import dutyroster.masters.dao.RosterPrintPropertiesDAO;
import dutyroster.transaction.dao.BlockwiseRosterDtlDAO;
import dutyroster.transaction.dao.DutyRoleDetailDAO;
import dutyroster.transaction.dao.RosterDtlDAO;
import dutyroster.transaction.dao.RosterDtlDAOi;
import dutyroster.transaction.dao.RosterExChangeDetailDAO;
import dutyroster.transaction.dao.RosterGenerationDtlDAO;
import dutyroster.transaction.dao.RosterReliverDtlDAO;
import dutyroster.transaction.dao.RosterWiseReliversDtlDAO;
import dutyroster.transaction.dao.RosterWiseReliversDtlDAOi;


public class DutyRosterBO implements DutyRosterBOi{

	// Function for Form EmployeeDuty  Roster  and Table HDRT_ROSTER_DTL
	
	public void saveEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			String generatedRosterId="";
			
			int counter=0;
			

			RosterDtlDAO daoobj = new RosterDtlDAO(tx);
			
			
			
			
	for(int i=0 ;i < _rosterDtlVO.length ; i++)
			{
		
			
		if(counter==0)
				{
			tx.begin();
			
			if(generatedRosterId.equals(""))
					generatedRosterId=daoobj.getGeneratedRosterId(_userVO);
				}
			counter++;
		if(counter < 100)
			{
			_rosterDtlVO[i].setGeneratedRosterId(generatedRosterId);
				daoobj.create(_rosterDtlVO[i],_userVO);	
			}
			else
		
			{
			
			_rosterDtlVO[i].setGeneratedRosterId(generatedRosterId);
			daoobj.create(_rosterDtlVO[i],_userVO);	
			counter=0; //re-initializing the counter
			tx.close(); //closing connection after inserting 100 records	
			}
			
			
			
			
			}
		}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
	  		 throw new HisDuplicateRecordException(e.getMessage());
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
	}
		
	
	public void saveAndModifyEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			int counter=1;
			String generatedRosterId="";

			RosterDtlDAO daoobj = new RosterDtlDAO(tx);
			
		//	String _rosterId=_rosterDtlVO[0].getRosterId();			
		//	String _areaTypeCode=_rosterDtlVO[0].getAreaTypeCode();
		//	String _areaCode=_rosterDtlVO[0].getAreaCode();
			
			tx.begin();
			
			
			
			
			daoobj.update(_rosterId,_areaTypeCode,_areaCode,empListToBeUpdated,daysListToBeUpdated,_year, _month,_userVO);
				
			
if(_rosterDtlVO!=null && _rosterDtlVO.length!=0)
{	
			
	for(int i=0 ;i < _rosterDtlVO.length ; i++)
			{
		
			
		if(counter==0)
				{
			tx.begin();
				}
				

			
		counter++;
		if(counter < 100)
			{
				
				daoobj.create(_rosterDtlVO[i],_userVO);	
			}
			else
		
			{
					
			daoobj.create(_rosterDtlVO[i],_userVO);	
			counter=0; //re-initializing the counter
			tx.close(); //closing connection after inserting 100 records	
			}
			
			
			
			
			}
  }

}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
	  		 throw new HisDuplicateRecordException(e.getMessage());
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
	}


	
	public Map getEmployeesWithRoster(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId, String _areaId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map EssentialMap=new HashMap();
		//List empRosterList=new ArrayList();
		List shiftList=new ArrayList();
		List empMappedList=new ArrayList();
		List dayOffShiftList=new ArrayList();
		Map _holidayMap=new HashMap();
		String shiftIdOfDayShift="";
		Map shiftTimingsMap=new HashMap();
		
		RosterDtlVO[] _rosterDtlVO=null;
		RosterDtlVO[] _monthlyEmpRosterDtlVO=null;
		DutyRosterAreaEmployeeVO[] _areaEmpmappingVO=null;
		RosterDtlVO[] _empMonthlyLeave=null;
		RosterGenerationDtlVO[] _rosterGenerationDtlVO=null;
		
		RosterWiseReliversDtlVO[] empReliverVO=null;
		
		try
		{
			tx.begin();

			EssentialDAO _essentialDaoObj = new EssentialDAO(tx);
			RosterDtlDAO _rosterDaoObj=new RosterDtlDAO(tx);
			DutyAreaEmployeeMstDAO _areaEmpMappingDAO=new DutyAreaEmployeeMstDAO(tx);
			RosterGenerationDtlDAO _rosterGenerationDao=new RosterGenerationDtlDAO(tx);
			RosterWiseReliversDtlDAO _reliverDAO=new RosterWiseReliversDtlDAO(tx);
			
			
			//	getting the complete monthly days for which the roster has been generated 
			 

			
			_rosterGenerationDtlVO=_rosterGenerationDao.fetch(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);
	
	
			EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS, _rosterGenerationDtlVO);
	
			
			
			
			//getting the complete monthly roster of all the mapped employees 
			//excluding the selected  roster Type and area code
			
			_areaEmpmappingVO=_areaEmpMappingDAO.fetchVOofAllAreaEmpMapping(_rosterId, _userVO);
			EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_AREA_EMP_MAPPING, _areaEmpmappingVO);
		
			
			
			//getting the complete monthly Leave of all the mapped employees 

			
			_empMonthlyLeave=_essentialDaoObj.fetchEmpLeaveDetails(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);
			EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ALL_MAPPED_EMP_LEAVE, _empMonthlyLeave);
		
			
			
			//getting the complete monthly roster of all the mapped employees 
			//excluding the selected  roster Type and area code
			
			_monthlyEmpRosterDtlVO=_rosterDaoObj.fetchVOofAllMappeDEmpsForOtherRosterOftheMonth(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);
			EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_EMP_WISE_MONTHLY_ROSTER, _monthlyEmpRosterDtlVO);
		
			
			
			
			
			//getting and then putting the monthly gazetted holiday Map
			
			_holidayMap=_essentialDaoObj.getMonthlyGazettedHolidays(_year, _month, _userVO);
			EssentialMap.put(DutyRosterConfig.MAP_OF_MONTHLY_GAZETTED_HOLIDAYS, _holidayMap);
			
			
			///if we get the desig id to be all then we fetch employee list  for all the 
			//mapped desiglist  else we fetch for that particular desigid 
			//all the rosters prepared
			
	if(_desigId.equals("ALL"))
			{
		//	empRosterList=_rosterDaoObj.fetchAllMappedEmp(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			
			_rosterDtlVO=_rosterDaoObj.fetchAllMappedDesignationWiseRoster(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);
			EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
			}
			else
			{
			//empRosterList=_rosterDaoObj.fetch(_desigId,_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			
			_rosterDtlVO=_rosterDaoObj.fetchDesignationWiseRoster(_desigId, _year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO)	;
				EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
			}
	
			//getting the list of shifts
			shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
			
			
			//getting the list of day of shifts
			dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
			
			
			///if we get the desig id to be all then we fetch employee list  for all the 
			//mapped desiglist  else we fetch for that particular desigid
			
			if(_desigId.equals("ALL"))
			{
				empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaId, _areaCode, _userVO);
				EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
				
			}
			else
			{	
			empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndDesignation(_desigId,_areaTypeCode,_areaCode,_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
			}
			
			
			
			//For Creating arrays for the Dynamic Shift Types,with there StartTime and EndTime
	
if(shiftList!=null)
{			
			for(int i=0 ; i <shiftList.size();i++)
			{
			Entry objEntryShift = new Entry();
			objEntryShift=(Entry)shiftList.get(i);
			
			String[] shiftNameConcate=objEntryShift.getLabel().split("@");

			if(shiftNameConcate[4].equals(Integer.toString(DutyRosterConfig.DAY_SHIFT_TYPE_ID)))
				shiftIdOfDayShift=objEntryShift.getValue();
			}
}			
			if(shiftIdOfDayShift!=null && !shiftIdOfDayShift.equals(""))
				{
				shiftTimingsMap=_essentialDaoObj.getShiftTimingsOfShift(shiftIdOfDayShift, _userVO);
				EssentialMap.put(DutyRosterConfig.MAP_OF_DAY_WISE_SHIFT_TIMINGS, shiftTimingsMap);
				}
			
			
			if(_rosterDtlVO!=null)
				empReliverVO=_reliverDAO.fetch(_rosterDtlVO[0].getGeneratedRosterId(),_userVO);
				EssentialMap.put(DutyRosterConfig.VO_OF_EMP_RELIVERS_ROSTER_WISE, empReliverVO);
			
			
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
	
	
	// Function for Form LocationWise Roster  and Table HDRT_BLOCKWISE_ROSTER_DTL
	
	
	public void checkDateRange(BlockwiseRosterDtlVO[] _blockWiseRosterVO,UserVO _userVO,String modifyStatus,String startDateTimeOld,String endDateTimeOld,String fromDateCheck,String toDateCheck){
			
			JDBCTransactionContext tx = new JDBCTransactionContext();
			
	try
	  {			int validateDate=0;
	  			
	  			int result=0;
	  			
				
				
				tx.begin();
				BlockwiseRosterDtlDAO daoobj = new BlockwiseRosterDtlDAO(tx);
				
				
				//Now Checking whether we are updating an old record or just inserting a new entry
				//1st for Area type Not Equal to Block and 
				//2nd for Area type Equal to Block
				
				if(modifyStatus.equals("MODIFY")&& (_blockWiseRosterVO[0].getBlockId()==null || _blockWiseRosterVO[0].getBlockId().equals("")))
					result=this.deleteAreaWiseRoster(_blockWiseRosterVO[0], _userVO,startDateTimeOld,endDateTimeOld, tx);
						else
				if(modifyStatus.equals("MODIFY")&& (_blockWiseRosterVO[0].getBlockId()!=null || !_blockWiseRosterVO[0].getBlockId().equals("")))
					result=this.deleteBlockWiseRoster(_blockWiseRosterVO[0], _userVO,startDateTimeOld,endDateTimeOld, tx);
				
				System.out.println("result in bo------="+result);
				
				
				
				//First we are checking from any particular VO whether roster being prepared is  BlockWise
				//or AreaWise
				
		if(_blockWiseRosterVO[0].getBlockId()==null || _blockWiseRosterVO[0].getBlockId().equals(""))
				{
			validateDate=daoobj.checkDateRangeForAreaWiseRoster(_blockWiseRosterVO[0].getRosterId(), _blockWiseRosterVO[0].getAreaTypeCode(), _blockWiseRosterVO[0].getAreaCode(),_blockWiseRosterVO[0].getFromDate(),_blockWiseRosterVO[0].getToDate(),_userVO);
		//	validateToDate=daoobj.checkDateRangeForAreaWiseRoster(_blockWiseRosterVO[0].getRosterId(), _blockWiseRosterVO[0].getAreaTypeCode(), _blockWiseRosterVO[0].getAreaCode(),_blockWiseRosterVO[0].getEndDateTime(), _userVO);
			
				}
		else
				{
			validateDate=daoobj.checkDateRangeForBlockWiseRoster(_blockWiseRosterVO[0].getRosterId(),_blockWiseRosterVO[0].getBlockId(),_blockWiseRosterVO[0].getFromDate(),_blockWiseRosterVO[0].getToDate(),_userVO);
		//	validateToDate=daoobj.checkDateRangeForBlockWiseRoster(_blockWiseRosterVO[0].getRosterId(),_blockWiseRosterVO[0].getBlockId(),_blockWiseRosterVO[0].getEndDateTime(), _userVO);
			
				}
		
		//Now we are Validating the Form and To Date whether it exists  in any other Roster Already Made
	 if(validateDate>0 )//&& !fromDateCheck.equals("true"))
				{
					
			   		throw new HisRecordNotFoundException("A Roster Already Exists for the Particular Roster Type and Duty Area Type in the given Range of Date ");
				}
	  	 	else				
	if(validateDate==0)
	 	{	
		
		   this.saveLocationDutyRoster(_blockWiseRosterVO, _userVO,tx);	
	
	    }
	}	
		catch (HisRecordNotFoundException e)
				{
			System.out.println("Inside Date Range Not Valid");
			tx.rollback();		
			tx.close();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace();
		  		tx.close();
		  		 throw new HisDuplicateRecordException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				tx.close();
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				tx.close();
				throw new HisDataAccessException();
			}
			catch (HisException e)
			{
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				tx.close();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("error.... DutyRoster BO");
				tx.rollback();
				tx.close();
				throw new HisApplicationExecutionException();
			}
			finally
			{
		//		tx.close();
			}
		}
	// Function for Form LocationWise Roster  and Table HDRT_BLOCKWISE_ROSTER_DTL
	
	
public void saveLocationDutyRoster(BlockwiseRosterDtlVO[] _blockWiseRosterVO,UserVO _userVO,JDBCTransactionContext tx){
		
		
		
try
  {			
			int counter=1;		
			
			BlockwiseRosterDtlDAO daoobj = new BlockwiseRosterDtlDAO(tx);
			
			
		
			
	for(int i=0 ;i < _blockWiseRosterVO.length ; i++)
		{
		
			
		if(counter==0  )
				{
			tx.begin();
				}
			counter++;
		if(counter < 100)
			{
				daoobj.create(_blockWiseRosterVO[i],_userVO);	
			}
			else
		
			{
			daoobj.create(_blockWiseRosterVO[i],_userVO);	
			counter=0; //re-initializing the counter
			tx.close(); //closing connection after inserting 100 records	
			}
			
			
			
			
		
	   }
    
}	
	catch (HisRecordNotFoundException e)
			{
		 tx.rollback();
		 e.printStackTrace();
	System.out.println("Inside HisRecordNotFoundException");
	throw new HisRecordNotFoundException(e.getMessage());
	
		}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
	  		 throw new HisDuplicateRecordException(e.getMessage());
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
	}


public Map fetchLocationRosterAreaWise(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map EssentialMap=new HashMap();
	BlockwiseRosterDtlVO[] _locationRosterVO=null;
	List shiftList=new ArrayList();
	List desigList=new ArrayList();
	List areaList=new ArrayList();
	try
	{
		tx.begin();

		EssentialDAO _essentialDaoObj = new EssentialDAO(tx);
		BlockwiseRosterDtlDAO _blockWiseDaoObj=new BlockwiseRosterDtlDAO(tx);
		
		
		shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
		
		desigList=_essentialDaoObj.getDesignationBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, desigList);
		
		
		
//Now we are checking whether the area type code is block ,then fetch the data according to block 		
if(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK.equals(_areaTypeCode))
		{
	String _blockId=_areaCode;
	
		areaList=_essentialDaoObj.getAreaBasedOnBlockId(_blockId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_AREA_BASED_ON_BLOCK, areaList);
	
		_locationRosterVO=_blockWiseDaoObj.fetchLocationRosterBlockWise( _startDate, _endDate, _blockId, _rosterId, _userVO);
		EssentialMap.put(DutyRosterConfig.VO_OF_ROSTERS_LOCATION_WISE_FOR_BLOCK, _locationRosterVO);
		}
else//else fetch the data acording to the area code
		{
	_locationRosterVO=_blockWiseDaoObj.fetchLocationRosterAreaWise( _startDate, _endDate, _areaTypeCode, _areaCode, _rosterId, _userVO);
	EssentialMap.put(DutyRosterConfig.VO_OF_ROSTERS_LOCATION_WISE_FOR_AREA, _locationRosterVO);
		}

		
		
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



public void generateLocationWiseRoster(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();

	try
	{
		tx.begin();


		BlockwiseRosterDtlDAO _blockWiseDaoObj=new BlockwiseRosterDtlDAO(tx);
		
		
		
		
//Now we are checking whether the area type code is block ,then fetch the data according to block 		
if(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK.equals(_areaTypeCode))
		{
	String _blockId=_areaCode;
	
		
	_blockWiseDaoObj.generateLocationWiseRosterForBlock( _startDate, _endDate, _blockId, _rosterId, _userVO);
		
		}
else//else fetch the data acording to the area code
		{
	_blockWiseDaoObj.generateLocationWiseRosterForArea( _startDate, _endDate, _areaTypeCode, _areaCode, _rosterId, _userVO);
	
		}

		
		
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
	
}


//Function for getting datewise Emp Roster


public void saveEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO){
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	try
	{
		String generatedRosterId="";
		
		int counter=0;
		int checkFlag=-1;

		RosterDtlDAO daoobj = new RosterDtlDAO(tx);
		
		
		
		
for(int i=0 ;i < _rosterDtlVO.length ; i++)
		{
	
		
	if(counter==0)
			{
		tx.begin();
		
		if(generatedRosterId.equals(""))
				generatedRosterId=daoobj.getGeneratedRosterId(_userVO);
		
		if(checkFlag==-1)
			checkFlag=daoobj.checkDuplicateDateWiseRoster("-1",_rosterDtlVO[0],_userVO);
		
		if(checkFlag > 0)
				throw new HisDuplicateRecordException("A DateWise Emp Roster Already Exists For the Giver Date Range");
		
			}
		counter++;
	if(counter < 100)
		{
		_rosterDtlVO[i].setGeneratedRosterId(generatedRosterId);
			daoobj.create(_rosterDtlVO[i],_userVO);	
		}
		else
	
		{
		
		_rosterDtlVO[i].setGeneratedRosterId(generatedRosterId);
		daoobj.create(_rosterDtlVO[i],_userVO);	
		counter=0; //re-initializing the counter
		tx.close(); //closing connection after inserting 100 records	
		}
		
		
		
		
		}
	}
	catch(HisDuplicateRecordException e){	   		   	
  		 tx.rollback();
  		 e.printStackTrace(); 
  		 throw new HisDuplicateRecordException(e.getMessage());
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
}


public void saveAndModifyEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO){
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	try
	{
	
		
		int counter=0;
		int checkFlag=-1;

		RosterDtlDAO daoobj = new RosterDtlDAO(tx);
		
		
		
		
for(int i=0 ;i < _rosterDtlVO.length ; i++)
		{
	
		
	if(counter==0)
		{
		tx.begin();
		
			
		if(checkFlag==-1)
				{
			checkFlag=daoobj.checkDuplicateDateWiseRoster(_rosterDtlVO[0].getGeneratedRosterId(),_rosterDtlVO[0],_userVO);
			daoobj.updateDateWiseEmpDutyRoster(_rosterDtlVO[0], _userVO);	
				}
		
		if(checkFlag > 0)
				throw new HisDuplicateRecordException("A DateWise Emp Roster Already Exists For the Giver Date Range");
			
		
		}
		counter++;
	if(counter < 100)
		{
			
			daoobj.create(_rosterDtlVO[i],_userVO);	
		}
		else
	
		{
			
		daoobj.create(_rosterDtlVO[i],_userVO);	
		counter=0; //re-initializing the counter
		tx.close(); //closing connection after inserting 100 records	
		}
		
		
		
		
		}
	}
	catch(HisDuplicateRecordException e){	   		   	
  		 tx.rollback();
  		 e.printStackTrace(); 
  		 throw new HisDuplicateRecordException(e.getMessage());
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
}


public Map getDateWiseEmployeesRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map EssentialMap=new HashMap();
	List empRosterList=new ArrayList();
	List shiftList=new ArrayList();
	List empMappedList=new ArrayList();
	List dayOffShiftList=new ArrayList();
	RosterDtlVO[] _rosterDtlVO=null;
	
	try
	{
		tx.begin();

		EssentialDAO _essentialDaoObj = new EssentialDAO(tx);
		RosterDtlDAO _rosterDaoObj=new RosterDtlDAO(tx);
		
if(_desigId.equals("ALL"))
		{
		empRosterList=_rosterDaoObj.fetchAllMappedDesignationDateWiseRoster(_areaTypeCode, _areaCode, _rosterId, _userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_EMPLOYEES_WITH_ROSTER, empRosterList);
		}
		else
		{
			empRosterList=_rosterDaoObj.fetchDesignationWiseDateWiseRoster(_desigId, _areaTypeCode, _areaCode, _rosterId, _userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_EMPLOYEES_WITH_ROSTER, empRosterList);
		}


		shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
		
		
		dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
		
		
		
		if(_desigId.equals("ALL"))
		{
			empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
			
		}
		else
		{	
		empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndDesignation(_desigId,_areaTypeCode,_areaCode,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
		}
		
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



//Function for getting datewise Emp Roster in modify case 

public Map getDateWiseEmployeesRosterModify(String _generatedRosterId,String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	Map EssentialMap=new HashMap();
	
	List shiftList=new ArrayList();
	List empMappedList=new ArrayList();
	List dayOffShiftList=new ArrayList();
	RosterDtlVO[] _rosterDtlVO=null;
	
	try
	{
		tx.begin();

		EssentialDAO _essentialDaoObj = new EssentialDAO(tx);
		RosterDtlDAO _rosterDaoObj=new RosterDtlDAO(tx);
		
if(_desigId.equals("ALL"))
		{
		_rosterDtlVO=_rosterDaoObj.fetchVOofAllMappedDesignationDateWiseRoster(_generatedRosterId, _userVO);
		EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}
		else
		{
	//		empRosterList=_rosterDaoObj.fetchVOofDesignationWiseDateWiseRoster(_desigId, _areaTypeCode, _areaCode, _rosterId, _userVO);
	//	EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}


		shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
		
		
		dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
		
		
		
		if(_desigId.equals("ALL"))
		{
			empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
			
		}
		else
		{	
		empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndDesignation(_desigId,_areaTypeCode,_areaCode,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
		}
		
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


//fOR DELETING THE OLD LOCATION DUTY ROSTER for Area Wise
public int deleteAreaWiseRoster(BlockwiseRosterDtlVO _blockWiseRosterVO,UserVO _userVO,String startDateTimeOld,String endDateTimeOld,JDBCTransactionContext tx){
	
	int result=0;
	
	try
	  {			
					
				
				BlockwiseRosterDtlDAO daoobj = new BlockwiseRosterDtlDAO(tx);
				
				result=daoobj.deleteAreaWiseRoster(_blockWiseRosterVO, _userVO,startDateTimeOld,endDateTimeOld);
			
	
	   
	}	
		catch (HisRecordNotFoundException e)
				{
			 tx.rollback();
			 e.printStackTrace();
		System.out.println("Inside HisRecordNotFoundException");
		throw new HisRecordNotFoundException(e.getMessage());
		
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage());
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
				//tx.close();
			}
		
 return result;
}

//fOR DELETING THE OLD LOCATION DUTY ROSTER for Blockwise
public int deleteBlockWiseRoster(BlockwiseRosterDtlVO _blockWiseRosterVO,UserVO _userVO,String startDateTimeOld,String endDateTimeOld,JDBCTransactionContext tx){
	
	int result=0;
	
	try
	  {			
			BlockwiseRosterDtlDAO daoobj = new BlockwiseRosterDtlDAO(tx);				
				
			result=daoobj.deleteBlockWiseRoster(_blockWiseRosterVO, _userVO,startDateTimeOld,endDateTimeOld);
				 
	    
	}	
		catch (HisRecordNotFoundException e)
				{
			 tx.rollback();
			 e.printStackTrace();
		System.out.println("Inside HisRecordNotFoundException");
		throw new HisRecordNotFoundException(e.getMessage());
		
			}
			catch(HisDuplicateRecordException e){	   		   	
		  		 tx.rollback();
		  		 e.printStackTrace(); 
		  		 throw new HisDuplicateRecordException(e.getMessage());
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
				//tx.close();
			}
		
return result;
}


//Functions for the Monthwise Employee Roster Report

public Map getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat)
{
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
		 		
if(_areaCode.equals("ALL"))
		_rosterGenerationDtlVO=_rosterGenerationDao.fetchForAllMappedAreas(_year, _month, _areaTypeCode, _rosterId, _userVO);
		else
		_rosterGenerationDtlVO=_rosterGenerationDao.fetch(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);


		EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS, _rosterGenerationDtlVO);
	
		
		
		
if(_desigId.equals("ALL"))
		{

//if area code is selected as all,then corresponding tp all mapped areas data will be fetched
	
	
	//if printing format is C,then combined printing
	if(printFormat.equals("C"))
			{
	
		if(_areaCode.equals("ALL"))
				_rosterDtlVO=_rosterDaoObj.fetchAllMappedAreaWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			else
				_rosterDtlVO=_rosterDaoObj.fetchAllMappedDesignationWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			}
			else//else separate list
	if(printFormat.equals("S"))
				{
		
			if(_areaCode.equals("ALL"))
					_rosterDtlVO=_rosterDaoObj.fetchSeperateAllMappedAreaWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				else
					_rosterDtlVO=_rosterDaoObj.fetchSeperateAllMappedDesignationWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				}
				
				
				
			
			
		EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}
		else
		{
		_rosterDtlVO=_rosterDaoObj.fetchDesignationWiseRosterReport(_desigId,_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}


		shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
		
		
		dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
		
		
		
		if(_desigId.equals("ALL"))
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
		}
		
		
		
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


//Functions for the Monthwise Duty Roster Report

public Map getMonthWiseDutyRosterReportBasedOnDutyAreaAndDesignation(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map EssentialMap=new HashMap();
	RosterDtlVO[] _rosterDtlVO=null;
	List shiftList=new ArrayList();
	List empMappedList=new ArrayList();
	List dayOffShiftList=new ArrayList();
	List<RosterReliverDtlVO> lstRosterReliverVO;
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
		 		
		if(_areaCode.equals("ALL"))
			_rosterGenerationDtlVO=_rosterGenerationDao.fetchForAllMappedAreas(_year, _month, _areaTypeCode, _rosterId, _userVO);
		else
			_rosterGenerationDtlVO=_rosterGenerationDao.fetch(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);

		EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS, _rosterGenerationDtlVO);
	
		lstRosterReliverVO=(List<RosterReliverDtlVO>)_rosterDaoObj.getReliverDetail(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);
		EssentialMap.put(DutyRosterConfig.EMP_RELIVER_LIST, lstRosterReliverVO);
		if(_desigId.equals("ALL"))
		{
			//if area code is selected as all,then corresponding tp all mapped areas data will be fetched
	
			//if printing format is C,then combined printing
			if(printFormat.equals("C"))
			{
	
				if(_areaCode.equals("ALL"))
					_rosterDtlVO=_rosterDaoObj.fetchAllMappedAreaWiseDutyRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				else
					_rosterDtlVO=_rosterDaoObj.fetchAllMappedDesignationWiseDutyRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			}
			else//else separate list
				if(printFormat.equals("S"))
				{
					if(_areaCode.equals("ALL"))
						_rosterDtlVO=_rosterDaoObj.fetchSeperateAllMappedAreaWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
					else
						_rosterDtlVO=_rosterDaoObj.fetchSeperateAllMappedDesignationWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				}
			
				EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}
		else
		{
			_rosterDtlVO=_rosterDaoObj.fetchDesignationWiseRosterReport(_desigId,_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}

		shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
		
		
		dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
		
		if(_desigId.equals("ALL"))
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
		}
		
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

/**
 * For generating the emp duty roster
 * @author ankur
 *
 */

public void generateEmpDutyRoster(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO) {
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	try
	{
		int counter=0;
		

		RosterGenerationDtlDAO daoobj = new RosterGenerationDtlDAO(tx);
		RosterWiseReliversDtlDAO relObj=new RosterWiseReliversDtlDAO(tx);
		
			tx.begin();
			
			counter=daoobj.checkDateRange(_rosterGenerationDtlVO, _userVO);

		if(counter > 0)
			throw new HisDuplicateRecordException("Generation From/To Date Already Exsists");
		else
			{
			daoobj.create(_rosterGenerationDtlVO,_userVO);	
			
			if(_reliverRosterVO!=null)
					{
			for(int i=0;i< _reliverRosterVO.length;i++)
						{
				    relObj.update(_reliverRosterVO[i].getRosterId(), _userVO);
					relObj.create(_reliverRosterVO[i], _userVO);
						}
					
					}
					
			}
		

	}
	catch(HisDuplicateRecordException e){	   		   	
  		 tx.rollback();
  		 e.printStackTrace(); 
  		 throw new HisDuplicateRecordException(e.getMessage());
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
}




/**
 * For dropping  the emp duty roster
 * @author ankur
 *
 */

public void dropEmpDutyRoster(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String generatedRosterId) {
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	try
	{
		int counter=0;
		

		RosterGenerationDtlDAO daoobj = new RosterGenerationDtlDAO(tx);
		RosterDtlDAO daoDtlobj = new RosterDtlDAO(tx);
		RosterWiseReliversDtlDAO relDaoObj=new RosterWiseReliversDtlDAO(tx);
		
		
			tx.begin();
		
			relDaoObj.update(generatedRosterId, _userVO);
			
			daoobj.update(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);	
		
			daoDtlobj.update(_rosterId,_areaTypeCode,_areaCode,empListToBeUpdated,daysListToBeUpdated,_year, _month,_userVO);
					

	}
	catch(HisDuplicateRecordException e){	   		   	
  		 tx.rollback();
  		 e.printStackTrace(); 
  		 throw new HisDuplicateRecordException(e.getMessage());
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
}




//Functions for the Monthwise Employee Roster Report

public Map getAreaWiseReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String _rosterCatg)
{
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
		 		
if(_rosterId.equals("ALL"))
		_rosterGenerationDtlVO=_rosterGenerationDao.fetchForAllRostersOfaCategoryAreaWise(_year, _month,_areaCode,_areaTypeCode,_rosterCatg,_userVO);
		else
		_rosterGenerationDtlVO=_rosterGenerationDao.fetch(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);


		EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS, _rosterGenerationDtlVO);
	
		
		


//if area code is selected as all,then corresponding tp all mapped areas data will be fetched
	
	
	//if printing format is C,then combined printing
	if(_rosterId.equals("ALL"))
			{
			_rosterDtlVO=_rosterDaoObj.fetchAllRostersCategAreaWiseReport(_year,_month,_areaTypeCode,_areaCode,_rosterCatg,_userVO);
			}
			else//else separate list
	if(!_rosterId.equals("ALL"))
				{
			_rosterDtlVO=_rosterDaoObj.fetchSeperateAllMappedDesignationWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				}
				
			
			
		EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL, _rosterDtlVO);
		
		
	if(!_rosterId.equals("ALL"))
	{
		shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
		
		
		dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);

	}	
	

			
//if area code is selected as all,then corresponding tp all mapped areas data will be fetched			
			/*if(_areaCode.equals("ALL"))
				empMappedList=_essentialDaoObj.getEmployeesMappedWithAllMappedAreasWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
				else*/
	
	
	if(!_rosterId.equals("ALL"))
				empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
			
			
			
			EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
			
		
		
		
		
		/*_rosterPrintVO=_printDao.fetch(_rosterId,_userVO);

if(_rosterPrintVO!=null)
		EssentialMap.put(DutyRosterConfig.VO_OF_ROSTER_PRINT_DETAILS, _rosterPrintVO);*/
		
		
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




public RosterWiseReliversDtlVO[] RosterShiftWiseReliversVO(String _rosterId,String _shiftId,String _selectedDate,UserVO _userVO)
{
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	RosterWiseReliversDtlVO[] empReliverVO=null;
	try
	{
		tx.begin();
		RosterWiseReliversDtlDAOi daoObj = new RosterWiseReliversDtlDAO(tx);
		empReliverVO=daoObj.fetchReliverListForDutyAssignment(_rosterId, _shiftId, _selectedDate, _userVO);
		
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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return empReliverVO;
}




public void saveReliverOfDuty(RosterDtlVO[] _reliverEmpInsertNewVO,RosterDtlVO[] _reliverEmpCancelOldVO,RosterDtlVO[] _requestedEmpCancelOldVO,RosterDtlVO _reliverEmpModifyDayOffOldVO,RosterReliverDtlVO[] _rosterReliverDtlVO,UserVO _userVO)
{
	
	JDBCTransactionContext tx = new JDBCTransactionContext();

	try
	{
		tx.begin();
		RosterDtlDAO daoObj = new RosterDtlDAO(tx);
		RosterReliverDtlDAO daoObjRosterDtl=new RosterReliverDtlDAO(tx);
		

		
//For inserting into the HDRT_ROSTER_DTL Table
//for reliver assigned new duty with duty type=2
		
if(_reliverEmpInsertNewVO!=null && _reliverEmpInsertNewVO.length > 0)
{
	for(int i =0;i< _reliverEmpInsertNewVO.length;i++)
		{
			daoObj.create(_reliverEmpInsertNewVO[i], _userVO);
			
		}
			
			
}
		


//For Cancelling  the old duty of the  reliver from  HDRT_ROSTER_DTL Table
///modifying as dity done =5


if(_reliverEmpCancelOldVO!=null && _reliverEmpCancelOldVO.length > 0)
{
	for(int i =0;i< _reliverEmpCancelOldVO.length;i++)
		{
			daoObj.updateDutyDoneCancelForReliverDutyAssignment(_reliverEmpCancelOldVO[i], _userVO);
			
		}
			
			
}
		

//For Cancelling  the old duty of the  emp selected  from  HDRT_ROSTER_DTL Table
///modifying as dity done =3



if(_requestedEmpCancelOldVO!=null && _requestedEmpCancelOldVO.length > 0)
{
	for(int i =0;i< _requestedEmpCancelOldVO.length;i++)
		{
			daoObj.updateDutyDoneCancelForReliverDutyAssignment(_requestedEmpCancelOldVO[i], _userVO);
			
		}
			
			
}



//For Modifying the next duty to Day OFF  duty of the reliver emp selected  from  HDRT_ROSTER_DTL Table

if(_reliverEmpModifyDayOffOldVO!=null )
		daoObj.updateShiftTODayOff(_reliverEmpModifyDayOffOldVO, _userVO);





if(_rosterReliverDtlVO!=null && _rosterReliverDtlVO.length > 0)
{
	int serialNo=0;
	
	for(int i =0;i< _rosterReliverDtlVO.length;i++)
		{
		
		//if the entry is 1st entry ,then we calculate the max serial no 
		if(i==0)
			 serialNo=daoObjRosterDtl.getMaxSNo(_rosterReliverDtlVO[i], _userVO);
		
		System.out.println("serialNo-----------"+serialNo);
		
	//now setting the serial no.
		 _rosterReliverDtlVO[i].setSerialNo(Integer.toString(serialNo));

	//inserting new row	 
		 daoObjRosterDtl.create(_rosterReliverDtlVO[i], _userVO);
		
	//incrementing the serial no	 
		 serialNo++;
		
		
	
		}//for closed 
			
			
}//if closed 







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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	
}



public void saveEmployeeRoleDetail(DutyRoleDetailVO[] _dutyRoleDetailVO,RosterDtlVO[] _insertRosterDtlVO,RosterDtlVO[] _updateRosterDtlVO,UserVO _userVO,String mode) {

	JDBCTransactionContext tx=new JDBCTransactionContext();
	
	
	try{
		tx.begin();
		DutyRoleDetailDAO dao=new DutyRoleDetailDAO(tx);
		RosterDtlDAO rosterDAO=new RosterDtlDAO(tx);
		
		///updating previos roles
		for(int i=0;i<_updateRosterDtlVO.length;i++)
		{
			_updateRosterDtlVO[i].setRoleId("");
			rosterDAO.updateDutyRole(_updateRosterDtlVO[i],_userVO);
		}
		/////inserting new roles
		for(int i=0;i<_insertRosterDtlVO.length;i++)
		{
			rosterDAO.updateDutyRole(_insertRosterDtlVO[i],_userVO);
		}
		
//FOR UPDATING OLD  ROWS  in HDRT_DUTY_ROLE_DTL
		
		if(mode.equals("MODIFY"))
			dao.update(_dutyRoleDetailVO[0], _userVO);
		
		
	//FOR INSERTING NEW ROWS in HDRT_DUTY_ROLE_DTL
		for(int i=0;i<_dutyRoleDetailVO.length;i++)
		{
		dao.create(_dutyRoleDetailVO[i], _userVO);
		}
		
		
		
		
		
	}catch (HisRecordNotFoundException e)
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
		e.printStackTrace();
		throw new HisException();
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
	
}


///////////For Emp Duty Roster Date Range Wise/////////////////

public void saveEmpDutyRosterDateRangeWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO){
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	try
	{
		String generatedRosterId="";
		
		int counter=0;
		

		RosterDtlDAO daoobj = new RosterDtlDAO(tx);
		
		
		
		
for(int i=0 ;i < _rosterDtlVO.length ; i++)
		{
	
		
	if(counter==0)
			{
		tx.begin();
		
		if(generatedRosterId.equals(""))
				generatedRosterId=daoobj.getGeneratedRosterId(_userVO);
			}
		counter++;
	if(counter < 100)
		{
		_rosterDtlVO[i].setGeneratedRosterId(generatedRosterId);
			daoobj.create(_rosterDtlVO[i],_userVO);	
		}
		else
	
		{
		
		_rosterDtlVO[i].setGeneratedRosterId(generatedRosterId);
		daoobj.create(_rosterDtlVO[i],_userVO);	
		counter=0; //re-initializing the counter
		tx.close(); //closing connection after inserting 100 records	
		}
		
		
		
		
		}
	}
	catch(HisDuplicateRecordException e){	   		   	
  		 tx.rollback();
  		 e.printStackTrace(); 
  		 throw new HisDuplicateRecordException(e.getMessage());
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
}
	


public void generateEmpDutyRosterDateWise(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO) {
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	try
	{
		int counter=0;
		

		RosterGenerationDtlDAO daoobj = new RosterGenerationDtlDAO(tx);
		
		
			tx.begin();
			
		

			daoobj.create(_rosterGenerationDtlVO,_userVO);	
			
				
		
		

	}
	catch(HisDuplicateRecordException e){	   		   	
  		 tx.rollback();
  		 e.printStackTrace(); 
  		 throw new HisDuplicateRecordException(e.getMessage());
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
}




//Functions for the Monthwise Employee Roster Report

public Map getMonthlyEmpRosterReportDateWise(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat,String _fromDate,String _toDate)
{
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
		
		
		
		
/*
		//getting and then putting the monthly gazetted holiday Map
		
		_holidayMap=_essentialDaoObj.getMonthlyGazettedHolidays(_year, _month, _userVO);
		EssentialMap.put(DutyRosterConfig.MAP_OF_MONTHLY_GAZETTED_HOLIDAYS, _holidayMap);
	*/	
		
		
		//	getting the complete monthly days for which the roster has been generated 
		 		
/*if(_areaCode.equals("ALL"))
		_rosterGenerationDtlVO=_rosterGenerationDao.fetchForAllMappedAreas(_year, _month, _areaTypeCode, _rosterId, _userVO);
		else
*/	/*	_rosterGenerationDtlVO=_rosterGenerationDao.fetch(_year, _month, _areaTypeCode, _areaCode, _rosterId, _userVO);


		EssentialMap.put(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS, _rosterGenerationDtlVO);*/
	
		
		
		
if(_desigId.equals("ALL"))
		{

//if area code is selected as all,then corresponding tp all mapped areas data will be fetched
	
	
	//if printing format is C,then combined printing
	if(printFormat.equals("C"))
			{
	
		/*if(_areaCode.equals("ALL"))
				_rosterDtlVO=_rosterDaoObj.fetchAllMappedAreaWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
			else*/
				_rosterDtlVO=_rosterDaoObj.fetchMonthlyEmpRosterReportDateWise(_year,_month,_areaTypeCode,_areaCode,_rosterId,_fromDate,_toDate,_userVO);
			}
			/*else//else separate list
	if(printFormat.equals("S"))
				{
		
			if(_areaCode.equals("ALL"))
					_rosterDtlVO=_rosterDaoObj.fetchSeperateAllMappedAreaWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				else
					_rosterDtlVO=_rosterDaoObj.fetchSeperateAllMappedDesignationWiseRosterReport(_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
				}*/
				
				
				
			
			
		EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}
		/*else
		{
		_rosterDtlVO=_rosterDaoObj.fetchDesignationWiseRosterReport(_desigId,_year,_month,_areaTypeCode,_areaCode,_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER, _rosterDtlVO);
		}*/


		shiftList=_essentialDaoObj.getShiftListBasedOnRosterType(_rosterId,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftList);
		
		
		dayOffShiftList=_essentialDaoObj.getDayOffShiftTypeList(_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS, dayOffShiftList);
		
		
		
		if(_desigId.equals("ALL"))
		{

			
//if area code is selected as all,then corresponding tp all mapped areas data will be fetched			
			/*if(_areaCode.equals("ALL"))
				empMappedList=_essentialDaoObj.getEmployeesMappedWithAllMappedAreasWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
				else*/
				empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(_rosterId, _areaTypeCode, _areaCode, _userVO);
			
			
			
			EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
			
		}
		/*else
		{	
		empMappedList=_essentialDaoObj.getEmployeesBasedOnDutyAreaAndDesignation(_desigId,_areaTypeCode,_areaCode,_userVO);
		EssentialMap.put(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empMappedList);
		}*/
		
		
		
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


//////////////////Function used for updating ,inserting into roster_dtl table as well as insert into 
////////////////////Exchange_dtl table//////////////////


public void saveExchangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _reliverEmpCancelOldVO,RosterDtlVO _exchangeEmpCancelOldVO,RosterDtlVO _reliverEmpInsertNewVO,RosterDtlVO _exchangeEmpInsertNewVO,UserVO _userVO)
{
	
	JDBCTransactionContext tx = new JDBCTransactionContext();

	try
	{
		tx.begin();
		
		RosterExChangeDetailDAO exchangeDaoObj = new RosterExChangeDetailDAO(tx);
		RosterDtlDAO rosterDtlObj=new RosterDtlDAO(tx);
		
		rosterDtlObj.checkEmpDutyExists(_reliverEmpInsertNewVO.getEmpId(),_reliverEmpInsertNewVO.getStartDateTime(),_reliverEmpInsertNewVO.getEndDateTime(),_exchangeEmpInsertNewVO.getEmpId(),_exchangeEmpInsertNewVO.getStartDateTime(), _exchangeEmpInsertNewVO.getEndDateTime(), _userVO);
		
		
		//for updating of reliver emp duty  HDRT_ROSTER_DTL
		
		rosterDtlObj.updateforExchange(_reliverEmpCancelOldVO, _userVO);
	
		//for updating of Exchanged  emp duty HDRT_ROSTER_DTL
		
		rosterDtlObj.updateforExchange(_exchangeEmpCancelOldVO, _userVO);
		
		//for inserting  of reliver emp new duty HDRT_ROSTER_DTL
		
		rosterDtlObj.create(_reliverEmpInsertNewVO, _userVO);
		
		//for updating of Exchanged  emp duty  HDRT_ROSTER_DTL
		
		rosterDtlObj.create(_exchangeEmpInsertNewVO, _userVO);
		
		
		//for updating of Exchanged  emp duty  HDRT_ROSTER_EXCHANGE_DTL
		
		exchangeDaoObj.create(_exchangeDtlVO, _userVO);
		
	}	
	catch (HisDuplicateRecordException e)
	{
		tx.rollback();
		throw new HisDuplicateRecordException(e.getMessage());
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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	
}



//////////////////Function used for updating ,inserting into roster_dtl table as well as insert into 
////////////////////Exchange_dtl table//////////////////


public void saveChangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _requestedEmpCancelOldVO,RosterDtlVO _changeEmpInsertNewVO,UserVO _userVO)
{

JDBCTransactionContext tx = new JDBCTransactionContext();

try
{
tx.begin();

RosterExChangeDetailDAO exchangeDaoObj = new RosterExChangeDetailDAO(tx);
RosterDtlDAO rosterDtlObj=new RosterDtlDAO(tx);






//for updating of reliver emp duty  HDRT_ROSTER_DTL
//udating the duty done flag of requested emp

rosterDtlObj.updateforExchange(_requestedEmpCancelOldVO, _userVO);


//for updating of Exchanged  emp duty  HDRT_ROSTER_DTL
//for inserting a new line 

rosterDtlObj.create(_changeEmpInsertNewVO, _userVO);


//for updating of Exchanged  emp duty  HDRT_ROSTER_EXCHANGE_DTL

exchangeDaoObj.create(_exchangeDtlVO, _userVO);

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
System.out.println("error.... DutyRoster BO");
tx.rollback();
throw new HisApplicationExecutionException();
}
finally
{
tx.close();
}

}


//**********************************Function for getting roster detail********************************************//////



public List getRosterDetail(String rosterCatId,String dutyAreaCode,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	List rosterDetailVoList=null;
	try
	{
		tx.begin();
		RosterDtlDAOi daoObj = new RosterDtlDAO(tx);
		rosterDetailVoList=daoObj.getRosterDetail(rosterCatId,dutyAreaCode, _userVO);
		
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
		System.out.println("error.... DutyRoster BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return rosterDetailVoList;
}





//Functions for the Monthwise Employee Roster Report

public Map getEmpDailyWorkReport(String _rosterId,String _areaTypeCode,String _areaCode,String _empId,String _workingDate,UserVO _userVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map EssentialMap=new HashMap();
	
	RosterDtlVO[] _rosterDtlVO=null;
	List roleList=new ArrayList();
	
	try
	{
		tx.begin();

		EssentialDAO essentialDaoObj = new EssentialDAO(tx);
		RosterDtlDAO rosterDaoObj=new RosterDtlDAO(tx);
		
		
		_rosterDtlVO=rosterDaoObj.getEmpDailyWorkReport(_rosterId, _areaTypeCode, _areaCode, _empId, _workingDate, _userVO);
		EssentialMap.put(DutyRosterConfig.EMP_WORK_REPORT_LIST, _rosterDtlVO);	
	
		roleList=essentialDaoObj.getDutyRoleList(_userVO);
		EssentialMap.put(DutyRosterConfig.ESSENTIAL_ALL_DUTY_ROLE_LIST, roleList);	
		
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



public void saveEmpReliverDtl(UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO) {
	
	JDBCTransactionContext tx = new JDBCTransactionContext();
	
	try
	{
		int counter=0;
		RosterWiseReliversDtlDAO relObj=new RosterWiseReliversDtlDAO(tx);
		
			tx.begin();
			
			if(_reliverRosterVO!=null)
			{
				for(int i=0;i< _reliverRosterVO.length;i++)
				{
				    relObj.duplicateCheckOfReliverOfEmp(_reliverRosterVO[i], _userVO);
					relObj.create(_reliverRosterVO[i], _userVO);
				}
					
			}
					
		}
	catch(HisDuplicateRecordException e){	   		   	
  		 tx.rollback();
  		 e.printStackTrace(); 
  		 throw new HisDuplicateRecordException(e.getMessage());
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
}


}






