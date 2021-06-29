package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ProfileGroupMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfileGroupMasterDAO extends DataAccessObject implements ProfileGroupMasterDAOi
{

Logger log;
	
	public ProfileGroupMasterDAO(TransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public String checkDuplicateBeforeModify(
			ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_MODIFYSAVE.HPMRT_PROFILE_GROUP_MST";
		String disasterTypeName=null;
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
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupName());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), profileGroupMasterVO.getDeptUnitCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DisasterTypeMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				disasterTypeName=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}	
		}
		return disasterTypeName;
	}

	public String checkDuplicateBeforeSave(
			ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HPMRT_PROFILE_GROUP_MST";
		String patientCategoryCode=null;
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
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupName());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), profileGroupMasterVO.getDeptUnitCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				patientCategoryCode=rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}	
		}
		return patientCategoryCode;
	}	
	
	
	public void save(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO) {
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HPMRT_PROFILE_GROUP_MST";
		
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
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupName());
			populateMAP.put(sq.next(), profileGroupMasterVO.getDeptUnitCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
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
	
	public ProfileGroupMasterVO fetchProfileGroupDetailModify(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HPMRT_PROFILE_GROUP_MST";
		
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
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("Record Not Found");
			}
			else{
				HelperMethods.populateVOfrmRS(profileGroupMasterVO, rs);
			}
		}
		catch (Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}	
		}
		return profileGroupMasterVO;
	}
	
	public void modify(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HPMRT_PROFILE_GROUP_MST";
		
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
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileGroupMasterVO.getSerialNo());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
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

	public void modifyInsert(ProfileGroupMasterVO profileGroupMasterVO,UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFY_INSERT.HPMRT_PROFILE_GROUP_MST";
		
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
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileGroupMasterVO.getProfileGroupName());
			populateMAP.put(sq.next(), profileGroupMasterVO.getDeptUnitCode());
			populateMAP.put(sq.next(), profileGroupMasterVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
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
