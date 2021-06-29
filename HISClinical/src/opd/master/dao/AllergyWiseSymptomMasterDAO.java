package opd.master.dao;

/**
 * @author  CDAC
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.AllergyWiseSymptomMasterVO;
import hisglobal.vo.UserVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;

public class AllergyWiseSymptomMasterDAO extends DataAccessObject
{
	Logger log;
	
	public AllergyWiseSymptomMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log=LogManager.getLogger(this.getClass());
	}
	
	
	
	
	// * Getting Allergy Type List
	public List getAllergyTypeNotInAllergyWiseSymptom(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT_ALLERGY_TYPE_NOTIN_ALLERGYWISE_SYMPTOM";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AllergyWiseSymptomMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return alRecord;
	}
	
	
	
	
	// * Getting Symptom List
	public List getSymptomListWhereSymptomTypeIsOne(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT_SYMPTOM_LIST_WHERE_SYMPTOMTYPE_IS_ONE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AllergyWiseSymptomMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
	
	
	
	public void addAllergyTypeAgainstSymptom(AllergyWiseSymptomMasterVO _voAllergyWiseSymptom, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT_HGBT_ALLERGYWISE_SYMPTOM_MST";

        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),_voAllergyWiseSymptom.getSymptomCode());
        	populateMAP.put(sq.next(),_voAllergyWiseSymptom.getAllergyTypeCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),_voAllergyWiseSymptom.getAllergyTypeCode());
        	populateMAP.put(sq.next(),_voAllergyWiseSymptom.getSymptomCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
	
	
	
	public List getSelectedList(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT_SELECTED_SYMPTOM_LIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),allergyWiseSymptomMasterVO.getAllergyTypeCode());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AllergyWiseSymptomMasterDAO.populateMAP::" + e);
		}
		
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Department record Exists in database  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return alRecord;
	}
	
	
	public List getRemainingList(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT_REMAINING_SYMPTOM_LIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),allergyWiseSymptomMasterVO.getAllergyTypeCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AllergyWiseSymptomMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Department record Exists in database  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return alRecord;
	}
	
	
	
	
	
	
	
	
	
	public List getListForView(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT_ALLERGY_TYPE_AND_SYMPTOM_LIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), allergyWiseSymptomMasterVO.getAllergyTypeCode());
			populateMAP.put(sq.next(), allergyWiseSymptomMasterVO.getSymptomCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AllergyWiseSymptomMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Department record Exists in database  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
		}
		return alRecord;
	}
	
	//getting allergy type
	
	
	
	public String getAllergyType(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.ALLERGYTYPE.HGBT_ALLERGY_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), allergyWiseSymptomMasterVO.getAllergyTypeCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AllergyWiseSymptomMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Department record Exists in database  ");
			return rs.getString(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
	
	
	public void updateAlergySymMasterDetail(AllergyWiseSymptomMasterVO allergyWiseSymptomMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_ALLERGYWISE_SYMPTOM_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), allergyWiseSymptomMasterVO.getAllergyTypeCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
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