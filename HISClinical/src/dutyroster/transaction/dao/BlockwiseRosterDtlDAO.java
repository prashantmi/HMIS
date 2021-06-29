
package dutyroster.transaction.dao;




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
import hisglobal.vo.BlockwiseRosterDtlVO;
import hisglobal.vo.BloodStockEnquiryVO;
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
import dutyroster.transaction.dao.BlockwiseRosterDtlDAOi;
import enquiry.vo.BloodDonorEnquriyVO;

public class BlockwiseRosterDtlDAO extends DataAccessObject implements BlockwiseRosterDtlDAOi{
	
	Logger log;

	public BlockwiseRosterDtlDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	
	
	
	// on Add page for Saving Data
	public void create(BlockwiseRosterDtlVO _BlockwiseRosterDtlVO,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "INSERT.HDRT_BLOCKWISE_ROSTER_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		System.out.println("CHeck 1"+_BlockwiseRosterDtlVO.getFromDate());
		System.out.println("CHeck 2"+_BlockwiseRosterDtlVO.getToDate());

		try  
		{
						         	
						
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getRosterId());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getAreaCode());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getAreaTypeCode());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getShiftId());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getCapacity());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getStartDateTime());
			populateMAP.put(sq.next(),_BlockwiseRosterDtlVO.getEndDateTime());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getRoleId());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getDutyDone());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getDutyRemarks());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getCancelRemarks());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getBlockId());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getDesignationId());			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getFromDate());
			populateMAP.put(sq.next(), _BlockwiseRosterDtlVO.getToDate());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
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


	//Fetching the Record for the Distinct Rosters Prepared Blockwise
	
	public List fetchDistinctRostersBlockWise(String _rosterId,String _blockId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DISTINCT_ROSTERS_BLOCKWISE.HDRT_BLOCKWISE_ROSTER_DTL";
		List rosterList=new ArrayList();
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
			 
			
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_blockId);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			if(!rs.next()){
		//		 throw new HisRecordNotFoundException("Details Not Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
	return rosterList;
	}
	
	
//Fetching the Record for the Distinct Rosters Prepared AreaWise
	
	public List fetchDistinctRostersAreaWise(String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DISTINCT_ROSTERS_AREAWISE.HDRT_BLOCKWISE_ROSTER_DTL";
		List rosterList=new ArrayList();
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
			 
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			if(!rs.next()){
		//		 throw new HisRecordNotFoundException("Details Not Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
	return rosterList;
	}
	
	
	//Fetching the Record for the Distinct Rosters Prepared Blockwise
	
	public int  checkDateRangeForBlockWiseRoster(String _rosterId,String _blockId,String _startDate,String _endDate,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DATE_CHECK_BLOCKWISE.HDRT_BLOCKWISE_ROSTER_DTL";
		int count=0;
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
			
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			populateMAP.put(sq.next(),_endDate);
			
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			
			
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_blockId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			if(!rs.next()){
				// throw new HisRecordNotFoundException("Details Not Found");
			               }
			else
			{
				count=rs.getInt(1);
			}
				
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	return count;
	}
	
	
//Checking the date range Record for the Distinct Rosters Prepared AreaWise
	
	public int checkDateRangeForAreaWiseRoster(String _rosterId,String _areaTypeCode,String _areaCode,String _startDate,String _endDate, UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DATE_CHECK_AREAWISE.HDRT_BLOCKWISE_ROSTER_DTL";
		int count=0;
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
			
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			populateMAP.put(sq.next(),_endDate);
			
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			
			
			
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			if(!rs.next()){
		//		 throw new HisRecordNotFoundException("Details Not Found");
			               }
			else
			        {
				count=rs.getInt(1);
			        }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	return count;
	}
	
	
	public BlockwiseRosterDtlVO[] fetchLocationRosterAreaWise(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ROSTER_AREAWISE.HDRT_BLOCKWISE_ROSTER_DTL";
		int count=0;
		ResultSet rs;
		BlockwiseRosterDtlVO[] _locationRosterVO=null;
		ValueObject[] valueObjects=null;
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
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);			
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(),_areaCode );			
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			 if(!rs.next()){
				 throw new HisRecordNotFoundException("Details Not Found");
			               }
			 			else {
			       			rs.beforeFirst();
			    			valueObjects=HelperMethods.populateVOfrmRS(BlockwiseRosterDtlVO.class, rs);
			    			_locationRosterVO=new BlockwiseRosterDtlVO[valueObjects.length];
			    			for(int i=0;i<valueObjects.length;i++){
			    				_locationRosterVO[i]=(BlockwiseRosterDtlVO)valueObjects[i];
			    			}
			            		   
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	return _locationRosterVO;
	}
	
	public BlockwiseRosterDtlVO[] fetchLocationRosterBlockWise(String _startDate,String _endDate,String _blockId,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ROSTER_BLOCKWISEWISE.HDRT_BLOCKWISE_ROSTER_DTL";
		int count=0;
		ResultSet rs;
		BlockwiseRosterDtlVO[] _locationRosterVO=null;
		ValueObject[] valueObjects=null;
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
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);			
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_blockId );
			populateMAP.put(sq.next(),_startDate);
			populateMAP.put(sq.next(),_endDate);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			 if(!rs.next()){
				 throw new HisRecordNotFoundException("Details Not Found");
			               }
			 			else {
			       			rs.beforeFirst();
			    			valueObjects=HelperMethods.populateVOfrmRS(BlockwiseRosterDtlVO.class, rs);
			    			_locationRosterVO=new BlockwiseRosterDtlVO[valueObjects.length];
			    			for(int i=0;i<valueObjects.length;i++){
			    				_locationRosterVO[i]=(BlockwiseRosterDtlVO)valueObjects[i];
			    			}
			            		   
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	return _locationRosterVO;
	}
	
	

	

	// for Updating The old Record in case of area wise
	public int deleteAreaWiseRoster(BlockwiseRosterDtlVO _blockwiseRosterDtlVO,UserVO _userVO,String startDateTimeOld,String endDateTimeOld)
	{
		
		   int result=0;
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "UPDATE_ROSTER_AREAWISE.HDRT_BLOCKWISE_ROSTER_DTL";

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
			populateMAP.put(sq.next(), _blockwiseRosterDtlVO.getRosterId());			
			populateMAP.put(sq.next(), _blockwiseRosterDtlVO.getAreaTypeCode());
			populateMAP.put(sq.next(), _blockwiseRosterDtlVO.getAreaCode());
			populateMAP.put(sq.next(), startDateTimeOld);
			populateMAP.put(sq.next(), endDateTimeOld);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("Bllock Wise Roster DtlDAO.populateMAP::" + e);
		}
		try
		{
			result=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return result;
	}
	
	// for Updating The old Record in case of block
	public int deleteBlockWiseRoster(BlockwiseRosterDtlVO _blockwiseRosterDtlVO,UserVO _userVO,String startDateTimeOld,String endDateTimeOld)
	{
		 int result=0;
		   
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "UPDATE_ROSTER_BLOCKWISEWISE.HDRT_BLOCKWISE_ROSTER_DTL";

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
			populateMAP.put(sq.next(), _blockwiseRosterDtlVO.getRosterId());			
			populateMAP.put(sq.next(), _blockwiseRosterDtlVO.getBlockId());
			populateMAP.put(sq.next(), startDateTimeOld);
			populateMAP.put(sq.next(), endDateTimeOld);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			result=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return result;
	}

	
	public int generateLocationWiseRosterForArea(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{

		 int result=0;
		   
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "UPDATE_AREAWISE_GENERATE.HDRT_BLOCKWISE_ROSTER_DTL";

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
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), DutyRosterConfig.LOCATION_WISE_ROSTER_IS_GENERATED);
			populateMAP.put(sq.next(), _rosterId);			
			populateMAP.put(sq.next(), _areaTypeCode);
			populateMAP.put(sq.next(), _areaCode);
			populateMAP.put(sq.next(), _startDate);
			populateMAP.put(sq.next(), _endDate);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			result=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return result;
	
	}
	
	public int generateLocationWiseRosterForBlock(String _startDate,String _endDate,String _blockId,String _rosterId,UserVO _userVO) 
	{

		 int result=0;
		   
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "UPDATE_BLOCKWISEWISE_GENERATE.HDRT_BLOCKWISE_ROSTER_DTL";

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
			

			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), DutyRosterConfig.LOCATION_WISE_ROSTER_IS_GENERATED);
			populateMAP.put(sq.next(), _rosterId);			
			populateMAP.put(sq.next(), _blockId);
			populateMAP.put(sq.next(), _startDate);
			populateMAP.put(sq.next(), _endDate);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			result=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return result;
	
	}
	
}
