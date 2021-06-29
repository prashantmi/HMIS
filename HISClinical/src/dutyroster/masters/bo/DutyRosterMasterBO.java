package dutyroster.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.BlockAreaMstVO;
import hisglobal.vo.DutyBlockMstVO;
import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.DutyRosterOtherAreaMstVO;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.DutyRosterShiftTimingsMasterVO;
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.RosterDesignationMstVO;
import hisglobal.vo.RosterRoleMstVO;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.BlockAreaMstDAO;
import dutyroster.masters.dao.BlockAreaMstDAOi;
import dutyroster.masters.dao.DutyAreaEmployeeMstDAO;
import dutyroster.masters.dao.DutyBlockMstDAO;
import dutyroster.masters.dao.DutyBlockMstDAOi;
import dutyroster.masters.dao.DutyRoleMstDAO;
import dutyroster.masters.dao.DutyRoleMstDAOi;
import dutyroster.masters.dao.EssentialDAO;
import dutyroster.masters.dao.EssentialDAOi;

import dutyroster.masters.dao.DutyRosterOtherAreaMstDAO;
import dutyroster.masters.dao.RosterAreaCapacityMstDAO;
import dutyroster.masters.dao.RosterCategoryMstDAO;
import dutyroster.masters.dao.RosterDesignationMstDAO;
import dutyroster.masters.dao.RosterDesignationMstDAOi;
import dutyroster.masters.dao.RosterPrintPropertiesDAO;
import dutyroster.masters.dao.RosterRoleMstDAO;
import dutyroster.masters.dao.RosterRoleMstDAOi;
import dutyroster.masters.dao.RosterShiftMstDAO;
import dutyroster.masters.dao.RosterShiftMstDAOi;
import dutyroster.masters.dao.RosterTypeMstDAO;
import dutyroster.masters.dao.RosterTypeMstDAOi;
import dutyroster.masters.dao.ShiftMstDAO;
import dutyroster.masters.dao.ShiftTimingsMstDAO;


public class DutyRosterMasterBO implements DutyRosterMasterBOi{

	
	// Functions for Form Roster Category Master and Table HDRT_ROSTER_CAT_MST
	
	public void saveRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			RosterCategoryMstDAO daoobj = new RosterCategoryMstDAO(tx);
			
			
			duplicateCheck=daoobj.duplicateCheckInsert(_rosterCatMstVO, _userVO);
			
			if(duplicateCheck==0)
				daoobj.create(_rosterCatMstVO,_userVO);	
			else
				throw new HisDuplicateRecordException("Roster Category Already Exists");
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public Map fetchRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RosterCategoryMstVO rostCatMstVO=new RosterCategoryMstVO();
		List rosterCatglist=new ArrayList();
		Map EssentialMap=new HashMap(); 
		
		try
		{
			tx.begin();

			RosterCategoryMstDAO daoobj = new RosterCategoryMstDAO(tx);
			EssentialDAO essDaObj=new EssentialDAO(tx);
			
			rosterCatglist=essDaObj.getListOfRosterMainCategory(_userVO);
			EssentialMap.put(DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY, rosterCatglist);
		
			rostCatMstVO=daoobj.fetch(_rosterCatMstVO, _userVO);
			EssentialMap.put(DutyRosterConfig.VO_OF_ROSTER_CATEGORY, rostCatMstVO);
		
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

	public void updateRosterCategoryInfo(String sRosterId,String sRosterSlno,RosterCategoryMstVO _rosterCatMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			RosterCategoryMstDAO daoobj = new RosterCategoryMstDAO(tx);
				
			duplicateCheck=daoobj.duplicateCheckModify(_rosterCatMstVO, _userVO);
			
			if(duplicateCheck==0)
			{
				daoobj.update(sRosterId,sRosterSlno,_userVO);
				daoobj.createwhileUpdate(sRosterSlno,_rosterCatMstVO,_userVO);
			}
			else
				throw new HisDuplicateRecordException("Roster Category Already Exists");
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
	}

	// Function for Form Shift Type Master and Table HDRT_SHIFT_MST
	
	
	public void saveShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck1=0;
		int duplicateCheck2=0;
		try
		{
			tx.begin();

			ShiftMstDAO daoobj = new ShiftMstDAO(tx);
			ShiftTimingsMstDAO daObjTimings = new ShiftTimingsMstDAO(tx);
			
			duplicateCheck1=daoobj.duplicateCheckForShiftDescriptionWhileInsert(_shiftMstVO, _userVO);
			
if(duplicateCheck1 > 0) 	
				throw new HisDuplicateRecordException("Shift Description  Already Exists");
			else			
			duplicateCheck2=daoobj.duplicateCheckForShiftShortNameWhileInsert(_shiftMstVO, _userVO);


			//System.out.println("duplicateCheck1------->"+duplicateCheck1);
			//System.out.println("duplicateCheck2------->"+duplicateCheck2);

if(duplicateCheck2 > 0) 	
			throw new HisDuplicateRecordException("Shift Short Name Alreay Exists");
			else
if(duplicateCheck1==0 && duplicateCheck2==0)
				{
				daoobj.create(_shiftMstVO,_userVO);
				
				for(int i=0 ; i < _shiftTimingsMstVO.length ; i++)
					daObjTimings.create(_shiftTimingsMstVO[i],_userVO);
				
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

	public Map fetchShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DutyRosterShiftMasterVO shiftMstVO=new DutyRosterShiftMasterVO();
		DutyRosterShiftTimingsMasterVO[]  _shiftTimeVO=null;
		String shiftCodeAndTime="";
		List shiftTypesList=new ArrayList();
		Map essentialMap=new HashMap(); 
		try
		{
			tx.begin();

			ShiftTimingsMstDAO daObjTimings = new ShiftTimingsMstDAO(tx);
			ShiftMstDAO daoobj = new ShiftMstDAO(tx);
			EssentialDAO esslDao= new EssentialDAO(tx);
			
			
			shiftTypesList=esslDao.getShiftTypesList(_userVO);
			essentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_SHIFT_TYPE, shiftTypesList);
			
			
		
			
			
			shiftMstVO=daoobj.fetch(_shiftMstVO, _userVO);
			essentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_VO_SHIFT_MST, shiftMstVO);
			
			 
if(shiftMstVO.getIsDayWiseShift().equals("1"))
			{
			_shiftTimeVO=daObjTimings.fetch(_shiftMstVO.getShiftCode(), _userVO);
			essentialMap.put(DutyRosterConfig.VO_OF_SHIFT_TIMINGS, _shiftTimeVO); 
			}	
	
shiftCodeAndTime=esslDao.getShiftTypeCodeAndTime(_userVO.getHospitalCode(),shiftMstVO.getShiftTypeCode());
			
			shiftMstVO.setShiftTypeCode(shiftCodeAndTime);
			
			
			
			
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
		return essentialMap;
	}

	public void updateShiftTypeInfo(String shiftCode,String serialNo,DutyRosterShiftMasterVO _shiftMstVO, UserVO _userVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck1=0;
		int duplicateCheck2=0;
		try
		{
			tx.begin();



ShiftMstDAO daoobj = new ShiftMstDAO(tx);
ShiftTimingsMstDAO daObjTimings = new ShiftTimingsMstDAO(tx);

duplicateCheck1=daoobj.duplicateCheckForShiftDescriptionWhileModify(_shiftMstVO, _userVO);

if(duplicateCheck1 > 0) 	
	throw new HisDuplicateRecordException("Shift Description  Already Exists");
else			
duplicateCheck2=daoobj.duplicateCheckForShiftShortNameWhileModify(_shiftMstVO, _userVO);


//System.out.println("duplicateCheck1------->"+duplicateCheck1);
//System.out.println("duplicateCheck2------->"+duplicateCheck2);

if(duplicateCheck2 > 0) 	
throw new HisDuplicateRecordException("Shift Short Name Alreay Exists");
else
if(duplicateCheck1==0 && duplicateCheck2==0)
	{
	
	//updating the shift master
	daoobj.update(shiftCode, serialNo, _userVO);
	
	///inserting new row having same shift code with serial no increased
	daoobj.createwhileUpdate(serialNo, _shiftMstVO, _userVO);
	
	//updating the shift Timings master
	daObjTimings.update(shiftCode, serialNo, _userVO);
	
	
	///inserting new rows having same shift code with serial no increased
	for(int i=0 ; i < _shiftTimingsMstVO.length ; i++)
		{
		daObjTimings.createwhileUpdate(serialNo, _shiftTimingsMstVO[i], _userVO);
	
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

// Functions for Form Duty Area Master and Table HDRT_DUTY_BLOCK_AREA_MST
	
	
	
	
	
	
	public Map getRoleAndDesignation(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List dutyRoleList=new ArrayList();
		List designationList=new ArrayList();
		Map EssentialMap=new HashMap();
		
		
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			
			
			dutyRoleList=daoobj.getDutyRoleList(_userVO);
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_ROLE_TYPE, dutyRoleList);
			designationList=daoobj.getDesignationList(_userVO);
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_DESIGNATION, designationList);
			
	
	
	
		}
		catch (HisRecordNotFoundException e)
		{
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_ROLE_TYPE, new ArrayList());
			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_DESIGNATION, new ArrayList());
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(),EssentialMap);
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
	public List getLeftEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		
		List empLeftList=new ArrayList();
		
		
		
		try
		{
			tx.begin();


			EssentialDAO daoobj = new EssentialDAO(tx);
						
			empLeftList=daoobj.getEmpDetailsUnselected(empDesg,_dutyAreaEmpVO,_userVO);
	
		}
		catch (HisRecordNotFoundException e)
		{
			empLeftList=new ArrayList();


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
		return empLeftList;
	}
	
	
	public List getRightEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		
		
		List empRightList=new ArrayList();
		Map EssentialMap=new HashMap();
		
		try
		{
			tx.begin();
			

			EssentialDAO daoobj = new EssentialDAO(tx);

			

			empRightList=daoobj.getEmpDetailsSelected(empDesg,_dutyAreaEmpVO,_userVO);

			EssentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED, empRightList);			
			

			
	

		}
		catch (HisRecordNotFoundException e)
		{
			empRightList=new ArrayList();

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
		return empRightList;
	}
	
	
	public void saveAndModifyDutyAreaEmpInfo(DutyRosterAreaEmployeeVO[] _addDutyAreaEmpVO,DutyRosterAreaEmployeeVO[] _deleteDutyAreaEmpVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			DutyAreaEmployeeMstDAO daoobj = new DutyAreaEmployeeMstDAO(tx);
			
			
			for(int i=0; i <_addDutyAreaEmpVO.length ; i++)
				daoobj.create(_addDutyAreaEmpVO[i],_userVO);	
			
			for(int i=0; i <_deleteDutyAreaEmpVO.length ; i++)
				daoobj.update(_deleteDutyAreaEmpVO[i],_userVO);	
			
			
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
			System.out.println("error.... BloodBank BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
		
/* ******************* functions for Duty Role Master *******************/

public boolean saveDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO userVO) {

	JDBCTransactionContext tx = new JDBCTransactionContext();
	boolean flag=false ;
	try
	{
		tx.begin();

			DutyRoleMstDAOi daoObj = new DutyRoleMstDAO(tx);
			

			
			daoObj.checkDuplicateBeforeInsert(dutyRoleMstVO.getDutyRoleDesc(), userVO);
			daoObj.checkDuplicateShortNameBeforeInsert(dutyRoleMstVO.getRoleShortName(), userVO);
			
			daoObj.saveDutyRole(dutyRoleMstVO, userVO);
			flag=true;

			
		}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
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
		return flag;
	}
	
	public boolean updateDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();
			
				DutyRoleMstDAOi daoObj = new DutyRoleMstDAO(tx);
			
				daoObj.checkDuplicateBeforeModify(dutyRoleMstVO, userVO);
				
				daoObj.checkDuplicateShortNameBeforeModify(dutyRoleMstVO, userVO);
			
				daoObj.modifyDutyRole(dutyRoleMstVO, userVO);
				daoObj.modifyInsertDutyRole(dutyRoleMstVO, userVO);
				
				flag=true;
			
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
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
		return flag;
	}
	
	
	public DutyRoleMstVO getDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DutyRoleMstDAOi daoObj = new DutyRoleMstDAO(tx);
			dutyRoleMstVO=daoObj.getDutyRole(dutyRoleMstVO, userVO);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
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
		return dutyRoleMstVO;
	}
	
	
	/* ***********************functions for Roster Type Master *******************/
	
	public void saveRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

			RosterTypeMstDAOi daoObj = new RosterTypeMstDAO(tx);
			int record=daoObj.checkDuplicateBeforeInsert(_rosterTypeVO.getRosterTypeName(), _userVO);
			List checkDutyType=new ArrayList();
	
	if(record > 0)
			throw new HisDuplicateRecordException("Roster Name Already Exsists");
	else
			{
		checkDutyType=daoObj.checkDutyTypeWhileInsert(_rosterTypeVO, _userVO);
		
			}		
	
	//Condition checks that if a  24x7  Roster ,already  exists,then Offical / Holiday Roster cannot be created further				
	if(checkDutyType.contains(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN)  && (_rosterTypeVO.getDutyType().equals(DutyRosterConfig.DUTY_TYPE_OFFICIAL_DAYS) || _rosterTypeVO.getDutyType().equals(DutyRosterConfig.DUTY_TYPE_SUNDAY_HOLIDAY)))
		throw new HisDuplicateRecordException("You Cannot Create a Offical/Holiday   Roster ,For the Selected Combination of Roster Category and Area Type ,Since There Exists a 24x7 Roster ");
				
	
	/*	
//Condition checks that if a 24x7,already  exists,then no roster can be made further
	
if(checkDutyType.containsKey(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
	throw new HisDuplicateRecordException("You Cannot Create a Roster ,For the Selected Combination of Roster Category and Area Type ,Since There Exists a 24 x 7 Roster ");
			else*/
	
//Condition checks that if a Offical / Holiday Roster ,already  exists,then  24x7 Roster cannot be created further				
if((checkDutyType.contains(DutyRosterConfig.DUTY_TYPE_OFFICIAL_DAYS) || checkDutyType.contains(DutyRosterConfig.DUTY_TYPE_SUNDAY_HOLIDAY)) && _rosterTypeVO.getDutyType().equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
	throw new HisDuplicateRecordException("You Cannot Create a 24x 7  Roster ,For the Selected Combination of Roster Category and Area Type ,Since There Exists a Offical Days/Holiday Roster for the Same Duty Type");
		/*	else	
//Condition checks that if a Roster(Duty) Type ,already  exists,then it's same Type cannot be made further				
if(checkDutyType.containsKey(_rosterTypeVO.getDutyType()))
	throw new HisDuplicateRecordException("You Cannot Create a Roster ,For the Selected Combination of Roster Category and Area Type ,Since There Exists a  Roster for the Same Duty Type");
			else
			{*/
				daoObj.create(_rosterTypeVO, _userVO);
				
			//}
			
		}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
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
	
	
	public RosterTypeMstVO getRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RosterTypeMstVO rosterTypeVO=new RosterTypeMstVO();
		try
		{
			tx.begin();
			
			RosterTypeMstDAOi daoObj = new RosterTypeMstDAO(tx);
			rosterTypeVO=daoObj.fetch(_rosterTypeVO, _UserVO);
						
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
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
		return rosterTypeVO;
	}
	
	
	public void modifyRosterType(RosterTypeMstVO _rosterTypeVO, UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

			Map checkDutyType=new HashMap();
			RosterTypeMstDAOi daoObj = new RosterTypeMstDAO(tx);
			int record=daoObj.checkDuplicateBeforeModify(_rosterTypeVO, _userVO);
			
if(record > 0)
				throw new HisDuplicateRecordException("Roster Name Already Exsists");
			else
			{
		checkDutyType=daoObj.checkDutyTypeWhileModify(_rosterTypeVO, _userVO);
		
			}		
	
if(checkDutyType.containsKey(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
	throw new HisDuplicateRecordException("You Cannot Create a Roster ,For the Selected Combination of Roster Category and Area Type ,Since There Exists a 24 x 7 Roster ");
			else
if((checkDutyType.containsKey(DutyRosterConfig.DUTY_TYPE_OFFICIAL_DAYS) || checkDutyType.containsKey(DutyRosterConfig.DUTY_TYPE_SUNDAY_HOLIDAY)) && _rosterTypeVO.getDutyType().equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
	throw new HisDuplicateRecordException("You Cannot Create a 24x 7  Roster ,For the Selected Combination of Roster Category and Area Type ,Since There Exists a Offical Days/Holiday Roster for the Same Duty Type");
			else	
if(checkDutyType.containsKey(_rosterTypeVO.getDutyType()))
	throw new HisDuplicateRecordException("You Cannot Create a Roster ,For the Selected Combination of Roster Category and Area Type ,Since There Exists a  Roster for the Same Duty Type");
			else
			{
				daoObj.update(_rosterTypeVO, _userVO);
				daoObj.createwhileUpdate(_rosterTypeVO, _userVO);
				
			}

			
			
			
		}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
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
	
	public Map getRosterTypeEssentials(UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List rosterCatList=new ArrayList();
		List areaTypeList=new ArrayList();
		try
		{
			tx.begin();
			
			RosterTypeMstDAOi daoObj = new RosterTypeMstDAO(tx);
			rosterCatList=daoObj.getRosterCat(userVO);
			essentialMap.put(DutyRosterConfig.ROSTER_CAT_LIST, rosterCatList);
			areaTypeList=daoObj.getDutyAreaType(userVO);
			essentialMap.put(DutyRosterConfig.DUTY_AREA_TYPE_LIST, areaTypeList);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			essentialMap.put(DutyRosterConfig.ROSTER_CAT_LIST, rosterCatList);
			essentialMap.put(DutyRosterConfig.DUTY_AREA_TYPE_LIST, areaTypeList);
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

	/* *************************** function for Roster Shift Master *************************/
	
	public List getRosterShiftEssentials(UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List rosterTypeList=new ArrayList();
		List shiftEssentialList=new ArrayList();
		try
		{
			tx.begin();
			
			RosterShiftMstDAOi daoObj = new RosterShiftMstDAO(tx);
			rosterTypeList=daoObj.getRosterType(userVO);
		

		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
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
		return rosterTypeList;
	}
	
	
	
	
	public List getShiftsBasedOnRoster(String shiftId,String shiftType,UserVO _UserVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		
		List shiftEssentialList=new ArrayList();
		try
		{
			tx.begin();
			
			RosterShiftMstDAOi daoObj = new RosterShiftMstDAO(tx);			
			shiftEssentialList=daoObj.getShiftsBasedOnRoster(shiftId,shiftType,_UserVO);
		
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
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
		return shiftEssentialList;
	}
	
	
	public void saveRosterShift(RosterShiftMstVO rosterShiftVOs[],UserVO _UserVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RosterShiftMstVO rosterShiftVo=new RosterShiftMstVO();
		try
		{
			tx.begin();
			RosterShiftMstDAOi daoObj = new RosterShiftMstDAO(tx);
			for(int i=0;i<rosterShiftVOs.length;i++){
				rosterShiftVo.setRosterTypeId(rosterShiftVOs[i].getRosterTypeId());
				rosterShiftVo.setShiftId(rosterShiftVOs[i].getShiftId());
				daoObj.saveRosterShift(rosterShiftVo, _UserVO);
			}
			
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

//***********************Function for Form Roster Area Capacity Master***************************	
//************************Table HDRT_ROSTERAREA_CAPACITY_MST*************************************
	
	
	
	

	
	
	public void saveRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();

			RosterAreaCapacityMstDAO daoobj = new RosterAreaCapacityMstDAO(tx);
			
			daoobj.create(_rosterAreaCapMstVO,_userVO);	
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

	public Map fetchRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RosterAreaCapacityMstVO rostAreaCapMstVO=new RosterAreaCapacityMstVO();
		String shiftCodeAndTime="";
		List areaTypesList=new ArrayList();
		List shiftTypes=new ArrayList();
		Map essentialMap=new HashMap(); 
		try
		{
			tx.begin();

			RosterAreaCapacityMstDAO daoobj = new RosterAreaCapacityMstDAO(tx);
			EssentialDAO essentlDaoobj= new EssentialDAO(tx);
			
			rostAreaCapMstVO=daoobj.fetch(_rosterAreaCapMstVO, _userVO);
			essentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_VO_OF_ROSTER_AREA_CAPACITY_MST, rostAreaCapMstVO);
			
			areaTypesList=essentlDaoobj.getDutyAreaBasedOnDutyAreaType(rostAreaCapMstVO.getAreaTypeCode(), _userVO);

		//	areaTypesList=essentlDaoobj.getDutyAreaBasedOnRosterAndDutyAreaType(rostAreaCapMstVO.getRosterId(),rostAreaCapMstVO.getAreaTypeCode(),_userVO);
			essentialMap.put(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, areaTypesList);
			
			shiftTypes=essentlDaoobj.getShiftTypesRosterWise(_rosterAreaCapMstVO.getRosterId(),_userVO);
			essentialMap.put(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE, shiftTypes);
			
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
		return essentialMap;
	}

	public void updateRosterAreaCapacityInfo(String areaCode,RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			RosterAreaCapacityMstDAO daoobj = new RosterAreaCapacityMstDAO(tx);
				daoobj.update(areaCode,_rosterAreaCapMstVO,_userVO);
				daoobj.create(_rosterAreaCapMstVO,_userVO);
	
		}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
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



	public Map getRosterShift(RosterShiftMstVO rosterShiftVo,UserVO _UserVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List  rosterShift=new ArrayList();
		List  shiftEssential=new ArrayList();
		Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			RosterShiftMstDAOi daoObj = new RosterShiftMstDAO(tx);
			
			
			//right hand side combo
			
			rosterShift=daoObj.getRosterShift(rosterShiftVo, _UserVO);
			essentialMap.put(DutyRosterConfig.ROSTER_SHIFT_DETAIL, rosterShift);
			
			//left hand side combo
			shiftEssential=daoObj.getShiftsBasedOnRosterModify(rosterShiftVo.getRosterTypeId(),_UserVO);
			essentialMap.put(DutyRosterConfig.ESSENTIAL_SHIFT_LIST, shiftEssential);
			
			
			//roster list
			String rosterTypeName=daoObj.getRosterTypeNameById(rosterShiftVo.getRosterTypeId(), _UserVO);
			essentialMap.put(DutyRosterConfig.ROSTER_TYPE_NAME, rosterTypeName);
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
		return essentialMap;
	}
	
	public void modifyRosterShift(RosterShiftMstVO[] insertRosterShiftVO,RosterShiftMstVO[] updateRosterShiftVO, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RosterShiftMstVO rosterShiftVo=new RosterShiftMstVO();
		try
		{
			tx.begin();
			RosterShiftMstDAOi daoObj = new RosterShiftMstDAO(tx);
			for(int i=0;i<updateRosterShiftVO.length;i++){
				rosterShiftVo.setRosterTypeId(updateRosterShiftVO[i].getRosterTypeId());
				rosterShiftVo.setSelectedShiftId(updateRosterShiftVO[i].getSelectedShiftId());
				
				daoObj.modifyRosterShift(rosterShiftVo, userVO);
			}
			for(int i=0;i<insertRosterShiftVO.length;i++){
				rosterShiftVo.setRosterTypeId(insertRosterShiftVO[i].getRosterTypeId());
				rosterShiftVo.setSelectedShiftId(insertRosterShiftVO[i].getSelectedShiftId());
				rosterShiftVo.setIsActive(insertRosterShiftVO[i].getIsActive());
				
				daoObj.modifyInsertRosterShift(rosterShiftVo, userVO);
				
			}
			
			
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
	
	/* *****************************function for Roster Role Master*********************** */
	
	public List getDutyRoleNotIn(String rosterTypeId,UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List dutyRoleNotInList=new ArrayList();
		try
		{
			tx.begin();
			
			RosterRoleMstDAOi daoObjRosterRole = new RosterRoleMstDAO(tx);
			dutyRoleNotInList=daoObjRosterRole.getDutyRoleNotIn(rosterTypeId, _userVO);
				
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
		return dutyRoleNotInList;
	}
	
	public List getDutyRole(String rosterTypeId,UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List dutyRoleList=new ArrayList();
		try
		{
			tx.begin();
			
			RosterRoleMstDAOi daoObjRosterRole = new RosterRoleMstDAO(tx);
			dutyRoleList=daoObjRosterRole.getAllDutyRole(rosterTypeId, _userVO);
			
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
		return dutyRoleList;
	}
	
	public void saveRosterRole(RosterRoleMstVO[] insertRosterRoleVO,RosterRoleMstVO[] updateRosterRoleVO, UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RosterRoleMstVO rosterRoleVo=new RosterRoleMstVO();
		try
		{
			tx.begin();
			RosterRoleMstDAOi daoObj = new RosterRoleMstDAO(tx);
			for(int i=0;i<updateRosterRoleVO.length;i++){
				rosterRoleVo.setRosterTypeId(updateRosterRoleVO[i].getRosterTypeId());
				rosterRoleVo.setDutyRole(updateRosterRoleVO[i].getDutyRole());
				daoObj.modifyRosterRole(rosterRoleVo, userVO);
			}
			for(int i=0;i<insertRosterRoleVO.length;i++){
				rosterRoleVo.setRosterTypeId(insertRosterRoleVO[i].getRosterTypeId());
				rosterRoleVo.setDutyRole(insertRosterRoleVO[i].getDutyRole());
							
				daoObj.modifyInsertRosterRole(rosterRoleVo, userVO);
				
			}
			
			
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
	/* *****************************function for Roster Designation Master*********************** */
	
	public List getNotAssignedDesignation(String rosterTypeId,UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List notAssignedDesignationList=new ArrayList();
		try
		{
			tx.begin();
			
			RosterDesignationMstDAOi daoObjRosterRole = new RosterDesignationMstDAO(tx);
			notAssignedDesignationList=daoObjRosterRole.getNotAssignedDesignation(rosterTypeId, _userVO);
			
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
		return notAssignedDesignationList;
	}
	
	public List getAssignedDesignation(String rosterTypeId,UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List assignedDesignationList=new ArrayList();
		try
		{
			tx.begin();
			
			RosterDesignationMstDAOi daoObjRosterRole = new RosterDesignationMstDAO(tx);
			assignedDesignationList=daoObjRosterRole.getAssignedDesignation(rosterTypeId, _userVO);
			
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
		return assignedDesignationList;
	}
	
	public void saveRosterDesignation(RosterDesignationMstVO[] insertRosterRoleVO,RosterDesignationMstVO[] updateRosterRoleVO, UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RosterDesignationMstVO rosterDesignationVo=new RosterDesignationMstVO();
		try
		{
			tx.begin();
			RosterDesignationMstDAOi daoObj = new RosterDesignationMstDAO(tx);
			for(int i=0;i<updateRosterRoleVO.length;i++){
				rosterDesignationVo.setRosterTypeId(updateRosterRoleVO[i].getRosterTypeId());
				rosterDesignationVo.setDesignationId(updateRosterRoleVO[i].getDesignationId());
				daoObj.modifyRosterDesignation(rosterDesignationVo, userVO);
			}
			for(int i=0;i<insertRosterRoleVO.length;i++){
				rosterDesignationVo.setRosterTypeId(insertRosterRoleVO[i].getRosterTypeId());
				rosterDesignationVo.setDesignationId(insertRosterRoleVO[i].getDesignationId());
				
				daoObj.modifyInsertRosterDesignation(rosterDesignationVo, userVO);
				
			}
			
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
	
	/* ******************* functions for Duty Block Master *******************/

	public boolean saveDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();

				DutyBlockMstDAOi daoObj = new DutyBlockMstDAO(tx);
				String record=daoObj.checkDuplicateBeforeInsert(dutyBlockMstVO.getDutyBlockName(), userVO);
				if(record==null)
				{
					daoObj.saveDutyBlock(dutyBlockMstVO, userVO);
					flag=true;
				}
				
		}
		catch(HisDuplicateRecordException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
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
		return flag;
	}
		
	public boolean updateDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO userVO) {
			
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=false ;
		try
		{
			tx.begin();
			
			DutyBlockMstDAOi daoObj = new DutyBlockMstDAO(tx);
			String record=daoObj.checkDuplicateBeforeModify(dutyBlockMstVO, userVO);
			if(record==null)
			{
				daoObj.modifyDutyBlock(dutyBlockMstVO, userVO);
				daoObj.modifyInsertDutyBlock(dutyBlockMstVO, userVO);
				flag=true;
			}
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
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
		return flag;
	}
		
		
	public DutyBlockMstVO getDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO userVO) {
			
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			DutyBlockMstDAOi daoObj = new DutyBlockMstDAO(tx);
			dutyBlockMstVO=daoObj.getDutyBlock(dutyBlockMstVO, userVO);
			
		}
		catch(HisDuplicateRecordException e){	   		   	
			tx.rollback();
			e.printStackTrace(); 
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
		return dutyBlockMstVO;
	}
	
	/* ******************************** function for Block Area Master ****************************/
	
	public Map getEssentialForBlockArea(UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List blockIdList=new ArrayList();
		List areaTypeList=new ArrayList();
		try
		{
			tx.begin();
			EssentialDAOi daoObj = new EssentialDAO(tx);
			blockIdList=daoObj.getDutyBlockList(_userVO);
			essentialMap.put(DutyRosterConfig.ESSENTIAL_DUTY_BLOCK_LIST, blockIdList);
			areaTypeList=daoObj.getDutyAreaTypesList(_userVO);
			essentialMap.put(DutyRosterConfig.DUTY_AREA_TYPE_LIST, areaTypeList);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			essentialMap.put(DutyRosterConfig.ESSENTIAL_DUTY_BLOCK_LIST, blockIdList);
			essentialMap.put(DutyRosterConfig.DUTY_AREA_TYPE_LIST, areaTypeList);
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
	
	
	public List getAssignedAreaCode(BlockAreaMstVO blockAreaVO, UserVO _userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List assignedAreaCodeList=new ArrayList();
		
		try
		{
			tx.begin();
			BlockAreaMstDAOi daoObj = new BlockAreaMstDAO(tx);
			assignedAreaCodeList=daoObj.getAssignedAreaCode(blockAreaVO, _userVO);
						
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
		return assignedAreaCodeList;
	}
		
	public void saveBlockArea(BlockAreaMstVO[] insertBlockAreaVO,BlockAreaMstVO[] updateBlockAreaVO, UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		BlockAreaMstVO blockAreaVO=new BlockAreaMstVO();
		try
		{
			tx.begin();
			BlockAreaMstDAOi daoObj = new BlockAreaMstDAO(tx);
			for(int i=0;i<updateBlockAreaVO.length;i++){
				blockAreaVO.setBlockId(updateBlockAreaVO[i].getBlockId());
				blockAreaVO.setAreaCode(updateBlockAreaVO[i].getAreaCode());
				blockAreaVO.setWorkPrefrence(updateBlockAreaVO[i].getWorkPrefrence());
				
				daoObj.modifyBlockArea(blockAreaVO, userVO);
				daoObj.modifyWorkPrefrenceDuringModify(blockAreaVO, userVO);
			}
			for(int i=0;i<insertBlockAreaVO.length;i++){
				blockAreaVO.setBlockId(insertBlockAreaVO[i].getBlockId());
				blockAreaVO.setAreaCode(insertBlockAreaVO[i].getAreaCode());
				blockAreaVO.setAreaTypeCode(insertBlockAreaVO[i].getAreaTypeCode());
				
				daoObj.modifyInsertBlockArea(blockAreaVO, userVO);
			}
					
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
	
	public BlockAreaMstVO[] getBlockArea(String dutyBlockId, UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		BlockAreaMstVO [] blockAreaVs=null;
		try
		{
			tx.begin();
			BlockAreaMstDAOi daoObj = new BlockAreaMstDAO(tx);
			blockAreaVs=daoObj.getBlockArea(dutyBlockId, userVO);
			
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
		return blockAreaVs;
	}
	
	public List getAreaCode(String dutyBlockId, UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List areaCodeList=new ArrayList();
		try
		{
			tx.begin();
			BlockAreaMstDAOi daoObj = new BlockAreaMstDAO(tx);
			areaCodeList=daoObj.getAreaCodeList(dutyBlockId, userVO);
			
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
		return areaCodeList;
	}
	
	public void modifyWorkPreference(BlockAreaMstVO [] updateBlockAreaVO, UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		BlockAreaMstVO blockAreaVO=new BlockAreaMstVO();
		try
		{
			tx.begin();
			BlockAreaMstDAOi daoObj = new BlockAreaMstDAO(tx);
			for(int i=0;i<updateBlockAreaVO.length;i++){
				blockAreaVO.setBlockId(updateBlockAreaVO[i].getBlockId());
				blockAreaVO.setAreaCode(updateBlockAreaVO[i].getAreaCode());
				blockAreaVO.setWorkPrefrence((i+1)+"");
				daoObj.modifyWorkPrefrence(blockAreaVO, userVO);
			}
					
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
	
	
	
// Functions for Form Other Area Master and Table HDRT_OTHER_AREA_MST
	
	public void saveOtherAreaInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			DutyRosterOtherAreaMstDAO daoobj = new DutyRosterOtherAreaMstDAO(tx);
			
			
			duplicateCheck=daoobj.duplicateCheckInsert(_otherAreaMstVO, _userVO);
			
			if(duplicateCheck==0)
				daoobj.create(_otherAreaMstVO,_userVO);	
			else
				throw new HisDuplicateRecordException("Area Name Already Exists");
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public DutyRosterOtherAreaMstVO fetchOtherAreaInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DutyRosterOtherAreaMstVO otherAreaMstVO=new DutyRosterOtherAreaMstVO();
		try
		{
			tx.begin();

			DutyRosterOtherAreaMstDAO daoobj = new DutyRosterOtherAreaMstDAO(tx);
			otherAreaMstVO=daoobj.fetch(_otherAreaMstVO, _userVO);
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
		return otherAreaMstVO;
	}

	public void updateOtherAreaInfo(String sRosterId,String sRosterSlno,DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			DutyRosterOtherAreaMstDAO daoobj = new DutyRosterOtherAreaMstDAO(tx);
				
			duplicateCheck=daoobj.duplicateCheckModify(_otherAreaMstVO, _userVO);
			
			if(duplicateCheck==0)
			{
				daoobj.update(sRosterId,sRosterSlno,_userVO);
				daoobj.createwhileUpdate(sRosterSlno,_otherAreaMstVO,_userVO);
			}
			else
				throw new HisDuplicateRecordException("Area Name Already Exists");
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
	}

	

/////////Functions for Roster Print Master  Prperties/////////////////////////
	
	
	//This function is used to fetch the last saved records for the corresponding roster type	
	
	public DutyRosterPrintPropertiesVO[] getRosterPrintDetailsBasedOnRosterType(String rosterType,UserVO _userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DutyRosterPrintPropertiesVO[] _rosterPrintVO=null;
		try
		{
			tx.begin();
			RosterPrintPropertiesDAO daoObj = new RosterPrintPropertiesDAO(tx);
			_rosterPrintVO=daoObj.fetch(rosterType, _userVO);
			
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
		return _rosterPrintVO;
	}
	
	

	//This function is used to save the  records Only 
	
	public void saveRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			RosterPrintPropertiesDAO daoObj = new RosterPrintPropertiesDAO(tx);
			
		for(int i=0 ; i < _rosterPrintVO.length ; i++ )
				daoObj.create(_rosterPrintVO[i],_userVO);	
		
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
	
	//This function is used to save new  records and modify old records
	
	public void saveAndModifyRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			RosterPrintPropertiesDAO daoObj = new RosterPrintPropertiesDAO(tx);
			
				daoObj.update(_rosterPrintVO[0].getRosterType(),_userVO);
				
		for(int i=0 ; i < _rosterPrintVO.length ; i++ )
				daoObj.create(_rosterPrintVO[i],_userVO);	
		
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
	
	
//This function is used to changeDisplayOrder of the records Only 
	
	public void changeDisplayOrder(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		int duplicateCheck=0;
		try
		{
			tx.begin();

			RosterPrintPropertiesDAO daoObj = new RosterPrintPropertiesDAO(tx);
			
		for(int i=0 ; i < _rosterPrintVO.length ; i++ )
				daoObj.changeDisplayOrder(_rosterPrintVO[i],_userVO);	
		
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
			System.out.println("error.... DutyRoster BO");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}
	
}






