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
import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfileRestrictedCatDAO extends DataAccessObject implements ProfileRestrictedCatDAOi
{ 

	Logger log;
	
	public ProfileRestrictedCatDAO(TransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public String checkDuplicateBeforeModify(
			ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_MODIFYSAVE.HPMRT_PROFILE_RESTRICTED_CAT";
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
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
			ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DUPLICATE_CHECK_BEFORE_SAVE.HPMRT_PROFILE_RESTRICTED_CAT";
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
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
	
	
	public void save(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HPMRT_PROFILE_RESTRICTED_CAT";
		
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
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
	
	public ProfileRestrictedCategoryMasterVO fetchPatientCatModify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HPMRT_PROFILE_RESTRICTED_CAT";
		
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("Record Not Found");
			}
			else{
				HelperMethods.populateVOfrmRS(profileRestrictedCategoryMasterVO, rs);
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
		return profileRestrictedCategoryMasterVO;
	}
	
	public void modify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HPMRT_PROFILE_RESTRICTED_CAT";
		
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileTypeModify());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getSerialNo());
			
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
	
	
	public void updateIsValid(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.ISVALID.HPMRT_PROFILE_RESTRICTED_CAT";
		
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
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

	public void modifyInsert(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFY_INSERT.HPMRT_PROFILE_RESTRICTED_CAT";
		
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getPatientCategoryCode());
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
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
	
	public List getCategoryBasedOnProfileType(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.BY_PROFILE_TYPE.HPMRT_PROFILE_RESTRICTED_CAT";
		List categoryList=new ArrayList();
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
			populateMAP.put(sq.next(), profileRestrictedCategoryMasterVO.getProfileType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileRestrictedCatDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("Record Not Found");
			}
		else{*/	
			categoryList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
			
			//}
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
		return categoryList;
	}

}
