
package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods; 
import hisglobal.utility.Sequence;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager; 
import org.apache.log4j.Logger;
import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.DutyAreaEmployeeMstDAOi;

public class DutyAreaEmployeeMstDAO extends DataAccessObject implements DutyAreaEmployeeMstDAOi{
	
	Logger log;

	public DutyAreaEmployeeMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	
	
	
	// on Add page for Saving Data
	public void create(DutyRosterAreaEmployeeVO _dutyAreaMstVO, UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_DUTY_AREA_EMPLOYEE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try  
		{
					 
			
			populateMAP.put(sq.next(), _dutyAreaMstVO.getEmpId());
			populateMAP.put(sq.next(), _dutyAreaMstVO.getAreaTypeCode()); 			
			populateMAP.put(sq.next(), _dutyAreaMstVO.getAreaCode());
			populateMAP.put(sq.next(), _dutyAreaMstVO.getDutyRoleId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}


	// for Updating The old Record
	public void update(DutyRosterAreaEmployeeVO _dutyAreaMstVO, UserVO _userVO)
	{
		
		   
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_DUTY_AREA_EMPLOYEE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _dutyAreaMstVO.getEmpId());			
			populateMAP.put(sq.next(), _dutyAreaMstVO.getAreaTypeCode());
			populateMAP.put(sq.next(), _dutyAreaMstVO.getAreaCode()); 
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

	}

	
	//---QUERY------FOR---FETCHING----THE--ROSTERS---FOR----ALL---THE----MAPPED----EMPLOYEES
	//--FOR THE WHOLE MONTH ---LEAVING THE CURRENT ROSTER AND AREA SELECTED ----
	//I.E. THE EMPLOYEE ROSTER IS PREPARED SOMEWHERE ELSE ALSO

	public DutyRosterAreaEmployeeVO[] fetchVOofAllAreaEmpMapping(String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_AREA_EMP_MAPPING.HDRT_DUTY_AREA_EMPLOYEE_MST";
		DutyRosterAreaEmployeeVO[] _areaEmpMappingVO=null;
		ValueObject[] _valueObjtsVo=null; 
		
		
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		
		log.info(query);
		try  
		{
		
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_rosterId);
				
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			if(!rs.next()){
		//	 throw new HisRecordNotFoundException("No Roster Found ");
			               }else
			             {
			            	   rs.beforeFirst();
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(DutyRosterAreaEmployeeVO.class, rs);
			       			
			            	   _areaEmpMappingVO=new DutyRosterAreaEmployeeVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       				_areaEmpMappingVO[i]=(DutyRosterAreaEmployeeVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		


	return _areaEmpMappingVO;
	}


}
