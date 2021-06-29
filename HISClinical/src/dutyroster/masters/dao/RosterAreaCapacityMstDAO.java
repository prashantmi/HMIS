
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
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager; 
import org.apache.log4j.Logger;
import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.DutyAreaEmployeeMstDAOi;

public class RosterAreaCapacityMstDAO extends DataAccessObject implements RosterAreaCapacityMstDAOi{
	
	Logger log;

	public RosterAreaCapacityMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	// on Add page for Saving Data
	public void create(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_ROSTERAREA_CAPACITY_MST";

		 	
								
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
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getRosterId()); 
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getAreaCode()); 
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getRosterId()); 
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getAreaCode()); 
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getMorningCapacity()); 
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getEveningCapacity());
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getEarlyMorningCapacity());
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getNightCapacity());
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getDayCapacity());
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getAreaTypeCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
					
	
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

	// on modify Page  for Showing Data of Selected Record
	public RosterAreaCapacityMstVO fetch(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		RosterAreaCapacityMstVO rostAreaCapMstVO=new RosterAreaCapacityMstVO(); 
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_ROSTERAREA_CAPACITY_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getRosterId());
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getAreaCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getSerialNo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("Details Not Found");
			               }else
			               {
			            	   rs.beforeFirst();
			            	   while(rs.next())
			            	   {
			            		  HelperMethods.populateVOfrmRS(rostAreaCapMstVO,rs);
			            	   }
			            		   
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	 return rostAreaCapMstVO;
		
	}

	// for Updating The old Record
	public void update(String areaCode,RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_ROSTERAREA_CAPACITY_MST";

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
			populateMAP.put(sq.next(), _userVO.getSeatId());			
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getRosterId());
			populateMAP.put(sq.next(), areaCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 
			populateMAP.put(sq.next(), _rosterAreaCapMstVO.getSerialNo());

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

	
	

	
	
	
	


}
