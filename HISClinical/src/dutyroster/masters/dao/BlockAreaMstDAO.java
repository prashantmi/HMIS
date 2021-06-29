package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.BlockAreaMstVO;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dutyroster.DutyRosterConfig;


public class BlockAreaMstDAO extends DataAccessObject implements BlockAreaMstDAOi {

	Logger log;

	public BlockAreaMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
	
	
	public List getAssignedAreaCode(BlockAreaMstVO blockAreaVO, UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List assignedAreaCodeList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.ASSIGNED_AREA_CODE.HDRT_DUTY_BLOCK_AREA_MST";
		
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
			populateMAP.put(sq.next(),blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(),blockAreaVO.getAreaTypeCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				assignedAreaCodeList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{	
				
			throw new HisRecordNotFoundException("Record Not Found");
			}	
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return assignedAreaCodeList;
	}

	
	public void modifyBlockArea(BlockAreaMstVO blockAreaVO, UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_DUTY_BLOCK_AREA_MST";
		
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
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), blockAreaVO.getAreaCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), blockAreaVO.getAreaCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate" + e);
		}
		
	}
	
	
	public void modifyInsertBlockArea(BlockAreaMstVO blockAreaVO,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.MODIFY.HDRT_DUTY_BLOCK_AREA_MST";
		
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
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), blockAreaVO.getAreaCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), blockAreaVO.getAreaCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), blockAreaVO.getAreaTypeCode());
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate" + e);
		}
		
	}
	
	public void modifyWorkPrefrenceDuringModify(BlockAreaMstVO blockAreaVO,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.SEQUENCE.DURING_MODIFY.HDRT_DUTY_BLOCK_AREA_MST";
		
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
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), blockAreaVO.getWorkPrefrence());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate" + e);
		}
		
	}
	
	public BlockAreaMstVO[] getBlockArea(String dutyBlockId,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_DUTY_BLOCK_AREA_MST";
		BlockAreaMstVO blockAreaVOs[]=null;
		
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
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//userVO.getHospitalCode()); By Pragya dated 2016.02.23 Global
			populateMAP.put(sq.next(), dutyBlockId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			int i=0;
			if(!rs.next()){
				throw new HisRecordNotFoundException("Record Not Found");
			}
			else{
				rs.beforeFirst();
				while(rs.next()){
					i++;
				}
				blockAreaVOs=new BlockAreaMstVO[i];
				i=0;
				rs.beforeFirst();
				while(rs.next()){
					blockAreaVOs[i]=new BlockAreaMstVO();
					blockAreaVOs[i].setBlockId(rs.getString(1));
					blockAreaVOs[i].setAreaCode(rs.getString(2));
					blockAreaVOs[i].setAreaTypeCode(rs.getString(3));
					blockAreaVOs[i].setWorkPrefrence(rs.getString(4));
					i++;
				}
			}	
			
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return blockAreaVOs;
	}
	
	public List getAreaCodeList(String  blockId, UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List areaCodeList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRSTR_DUTY_AREA_CODE.HDRT_DUTY_BLOCK_AREA_MST";
		
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
			populateMAP.put(sq.next(),blockId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				areaCodeList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{	
				
			throw new HisRecordNotFoundException("Record Not Found");
			}	
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return areaCodeList;
	}
	
	public void modifyWorkPrefrence(BlockAreaMstVO blockAreaVO, UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.SEQUENCE.HDRT_DUTY_BLOCK_AREA_MST";
		
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
			populateMAP.put(sq.next(), blockAreaVO.getWorkPrefrence());
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), blockAreaVO.getAreaCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), blockAreaVO.getBlockId());
			populateMAP.put(sq.next(), blockAreaVO.getAreaCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate" + e);
		}
		
	}
}
