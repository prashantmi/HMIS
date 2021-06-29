package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.EprRestrictedCategoryVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import registration.RegistrationConfig;

public class EprRestrictedCategoryMstDAO extends DataAccessObject implements EprRestrictedCategoryMstDAOi
{
	public EprRestrictedCategoryMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	/**
	 * get the list of the department unit/seatid for the specified tab. 
	 */
	public List getAddedPatientCategoryList(UserVO userVO){
	
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List eprAddedPatCatList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey ="SELECT.PAT_CAT_ADDED.HPMRT_EPR_RESTRICTED_CAT";
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
	
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added By Pawan Kumar B N on 27-07-2012
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprRestrictedCategoryMstDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
				eprAddedPatCatList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	
	
	return eprAddedPatCatList;

	}
	
	
	public List getNotAddedPatCatList(UserVO userVO){
		
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List notAddedPatCatList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey ="SELECT.PAT_CAT_NOT_ADDED.HPMRT_EPR_RESTRICTED_CAT";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added By Pawan Kumar B N on 27-07-2012
			populateMAP.put(sq.next(), RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprRestrictedCategoryMstDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(rs.next())
				notAddedPatCatList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		return notAddedPatCatList;
		
	}
	
	
	public void create(EprRestrictedCategoryVO eprRestrictedCatVO,UserVO userVO){
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey ="INSERT.HPMRT_EPR_RESTRICTED_CAT";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{
			populateMAP.put(sq.next(), eprRestrictedCatVO.getPatCatCode());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added By Pawan Kumar B N on 27-07-2012
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), eprRestrictedCatVO.getPatCatCode());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);  // Added By Pawan Kumar B N on 27-07-2012
			populateMAP.put(sq.next(),userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprRestrictedCategoryMstDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
	
	public void updateIsValid(UserVO userVO){
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey ="UPDATE.HPMRT_EPR_RESTRICTED_CAT";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added By Pawan Kumar B N on 27-07-2012
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EprRestrictedCategoryMstDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
	
		
	
}

	