//Source file: C:\\Documents and Settings\\Pranav Vikas\\workspace\\AHIMS\\src\\registration\\master\\bo\\MasterBO.java

package registration.master.bo;



import hisglobal.backutil.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.DepartmentUnitRoomMasterVO;
import hisglobal.vo.DeptCounterMappingVO;
import hisglobal.vo.DeptUnitGroupingMasterVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.EmpMasterVO;
import hisglobal.vo.ExternalInstituteMasterVO;
import hisglobal.vo.LocationMasterVO;
import hisglobal.vo.PatientCategoryVO;
import hisglobal.vo.PatientCategoryVerificationMasterVO;
import hisglobal.vo.RegEmergencyCaseMstVO;

import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.DeptUnitConsultantInitialVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.LocationMasterVO;
import hisglobal.vo.PatientCategoryVerificationMasterVO;

import hisglobal.vo.EmployeeMasterVO;
import hisglobal.vo.RegInjuryTypeMstVO;
import hisglobal.vo.RegOccupMstVO;
import hisglobal.vo.RegPatStatusMstVO;
import hisglobal.vo.RegistrationCatMstVO;
import hisglobal.vo.RegistrationTimingMasterVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.RosterMasterVO;
import hisglobal.vo.SeasonMasterVO;
import hisglobal.vo.UnitConsultantMasterVO;
import hisglobal.vo.UnitConsultantRosterMasterVO;
import hisglobal.vo.UnitCounterMappingVO;
import hisglobal.vo.UnitMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import registration.RegistrationConfig;
import registration.dao.EssentialDAO;


import registration.master.dao.LocationMasterDAO;

import registration.master.dao.UnitMasterDAO;

import registration.dao.EssentialDAOi;
import registration.master.dao.*;


public class MasterBO implements MasterBOi{
	
	/**
	 * @roseuid 45C2EFD7005D
	 */
	public MasterBO(){
		
	}
	
	/**
	 * @param deptUnitCode
	 * @return java.util.Collection
	 * @roseuid 45C2EFD7006D
	 */
	
	public void saveEmployeeDetail(EmployeeMasterVO _empMasterVO,UserVO _userVO) {
		JDBCTransactionContext tx =new JDBCTransactionContext();
		tx.begin();	
		HisDAO daoObj = null;
		daoObj = new HisDAO("Registration","MasterBO");
		try{		
			
			EmployeeMasterDAO empMasterDAO=new EmployeeMasterDAO(tx);	
			empMasterDAO.saveEmployeeDetail(daoObj,_empMasterVO,_userVO);				
			}
		
		catch(HisApplicationExecutionException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
	  		 throw new HisApplicationExecutionException(e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
	 		tx.rollback();
	 		throw new HisDataAccessException(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage()); 
		}
		finally{
			if(daoObj!=null)
				daoObj.free();
			daoObj=null;
			System.out.println("Close the transaction...");
			tx.close();			
		}	
		
	}
	
	public Map getEmployeeMasterDetails(String _empCode,UserVO _userVO) 
	{
		Map essentialMap=new HashMap();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		RoomMasterVO roomMasterVO1=new RoomMasterVO();		
		try{
			tx.begin();			
			EmployeeMasterDAO _empMasterDAO=new EmployeeMasterDAO(tx);	
			EmployeeMasterVO _empMasterVO=_empMasterDAO.getEmployeeMasterDetails(_empCode, _userVO);
			essentialMap.put(RegistrationConfig.EMPLOYEE_MST_EMPLOYEEVO,_empMasterVO);
		   }			
		catch(HisRecordNotFoundException e){	
	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();  
	   		 throw new HisRecordNotFoundException(e.getMessage());
	   	 }
		catch(HisApplicationExecutionException e){		   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new  HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){	   		
	   		e.printStackTrace();
	  		tx.rollback();		  	
	  		throw new  HisDataAccessException();
	  		
	  	 }
	   	catch(Exception e){		   		
	   		 e.printStackTrace();
	   		 tx.rollback();
	   		throw new  HisApplicationExecutionException();  		
	   	}   
		finally{
			tx.close();			
		}		
		return essentialMap;	
		
	}
	
	public void updateEmployeeMaster(EmployeeMasterVO _employeeMasterVO, UserVO _userVO) {
		JDBCTransactionContext tx =new JDBCTransactionContext();
		tx.begin();	
		HisDAO daoObj = null;

		try{		
			daoObj = new HisDAO("Registration","MasterBO");
			EmployeeMasterDAO _empMasterDAO=new EmployeeMasterDAO(tx);
			_empMasterDAO.updateEmployeeDetail(daoObj,_employeeMasterVO,_userVO);
		}		
		
		catch(HisApplicationExecutionException e){	   		   	
	  		 tx.rollback();
	  		 e.printStackTrace(); 
	  		 throw new HisApplicationExecutionException(e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
	 		tx.rollback();
	 		throw new HisDataAccessException(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);
		}
		finally{
			if(daoObj!=null)
				daoObj.free();
			daoObj=null;
			System.out.println("Close the transaction...");
			tx.close();			
		}	
		
		
	}
	
}//end of class



